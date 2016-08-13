package ch.smartclue.docker.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.reflections.Reflections;

import com.esotericsoftware.yamlbeans.YamlException;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.Validator;
import ch.smartclue.docker.reader.StructureReader;

public abstract class AbstractValidatorImpl implements Validator {
	protected Map<String, Object> structure = new HashMap<String, Object>();
	@SuppressWarnings("rawtypes")
	protected Map<String, YamlValidator> validators = new HashMap<String, YamlValidator>();
	
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
				validators.put(path, newInstance);
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
	protected void validate(String... packageNamesOfValidators) throws DockerComposeValidationException {
		createAllValidationInstances(packageNamesOfValidators);

		for (Entry<String, Object> entry : structure.entrySet()) {
			if (validators.containsKey(entry.getKey())) {
				validators.get(entry.getKey()).validate(entry.getValue());
			} 
			
		}
	}
	
}
