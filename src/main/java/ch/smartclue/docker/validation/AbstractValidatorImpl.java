package ch.smartclue.docker.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.reflections.Reflections;

import com.esotericsoftware.yamlbeans.YamlException;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.Validator;
import ch.smartclue.docker.reader.StructureReader;

@SuppressWarnings("rawtypes")
public abstract class AbstractValidatorImpl implements Validator {
	protected Map<String, Object> structure = new HashMap<String, Object>();
	protected Map<String, YamlValidator<?>> additionalValidators;

	
	protected Map<String, List<YamlValidator>> validators = new HashMap<String, List<YamlValidator>>();
	
	protected void readStructure(String content) throws DockerComposeValidationException{
		StructureReader reader = new StructureReader();
		try {
			structure = reader.readStructure(content);
		} catch (YamlException e) {
			throw new DockerComposeValidationException("Error while reading structure of yaml file", e);
		}
	}
	
	private void createAllValidationInstances(String... packageNames) {
		for (String packageName : packageNames){
			createValidationInstancesFromPackageName(packageName);
		}
	}

	private void createValidationInstancesFromPackageName(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(YamlProperty.class);
		
		for (Class<?> c : annotated) {
			try {
				Class<?> clazz = Class.forName(c.getName());
				String path = clazz.getAnnotation(YamlProperty.class).path();
				YamlValidator<?> newInstance = (YamlValidator<?>) clazz.newInstance();
				
				List<YamlValidator> currentValidators = null;
				
				if (validators.containsKey(path)){
					currentValidators = validators.get(path);
				}else{
					currentValidators = new ArrayList<YamlValidator>();
				}
				
				currentValidators.add(newInstance);
				
				validators.put(path, currentValidators);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	@SuppressWarnings("unchecked")
	protected void validate(Map<String, YamlValidator<?>> additionalValidators, String... packageNamesOfValidators) throws DockerComposeValidationException {
		
		//Insert custom validators first
		for (Entry<String, YamlValidator<?>> entry : additionalValidators.entrySet()){
			List<YamlValidator> currentValidators = null;
			
			if (validators.containsKey(entry.getKey())){
				currentValidators = validators.get(entry.getKey());
			}else{
				currentValidators = new ArrayList<YamlValidator>();
			}
			
			currentValidators.add(entry.getValue());
			
			validators.put(entry.getKey(), currentValidators);
		}
		
		
		//Overwrite custom validators with official ones (in case of conflict only)
		createAllValidationInstances(packageNamesOfValidators);

		for (Entry<String, Object> entry : structure.entrySet()) {
			if (validators.containsKey(entry.getKey())) {
				
				List<YamlValidator> foundValidators = validators.get(entry.getKey());
				for (YamlValidator instance : foundValidators){
					instance.validate(entry.getValue());
				}
			} 
		}
	}
	
}
