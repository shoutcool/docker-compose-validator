package ch.smartclue.docker.validation;

import java.util.Set;

import org.reflections.Reflections;

import ch.smartclue.docker.exception.DockerComposeValidationException;

public class DockerComposeValidator {
	
	private ValidatorManager validatorManager = new ValidatorManager();
	
	public DockerComposeValidator(){
		//Create all validator instances
		createValidationInstancesFromPackageName("ch.smartclue.docker.yml.common");
		createValidationInstancesFromPackageName("ch.smartclue.docker.yml.v1");
		createValidationInstancesFromPackageName("ch.smartclue.docker.yml.v2");
	}
	
	public void addAdditionalValidatorsByClass(Class<? extends YamlValidator<?>> validator){
		
		YamlProperty annotation = validator.getAnnotation(YamlProperty.class);
		if (annotation == null){
			throw new IllegalArgumentException(String.format("The custom validor class '%s' needs to have the 'YamlProperty' annotation set", validator.getName()));
		}
		
		YamlValidator<?> validatorInstance = null;
		try {
			validatorInstance = validator.newInstance();
			validatorManager.addValidatorInstance(new ValidatorInstance(validatorInstance, false));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void validate(String content) throws DockerComposeValidationException{
		Validator validator = ValidatorFactory.create(content, validatorManager);
		validator.validate();
	}
	
	private void createValidationInstancesFromPackageName(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(YamlProperty.class);
		
		for (Class<?> c : annotated) {
			try {
				Class<?> clazz = Class.forName(c.getName());
				YamlValidator<?> newInstance = (YamlValidator<?>) clazz.newInstance();
				validatorManager.addValidatorInstance(new ValidatorInstance(newInstance, true));
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
}
