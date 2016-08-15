package ch.smartclue.docker.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

class ReflectionUtil {

	public static List<ValidatorInstance> createValidationInstancesFromPackageName(String packageName) {
		Reflections reflections = new Reflections(packageName);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(YamlProperty.class);
		
		List<ValidatorInstance> validatorInstanceList = new ArrayList<ValidatorInstance>();
		
		for (Class<?> c : annotated) {
			try {
				Class<?> clazz = Class.forName(c.getName());
				YamlValidator<?> newInstance = (YamlValidator<?>) clazz.newInstance();
				validatorInstanceList.add(new ValidatorInstance(newInstance, true));
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
		
		return validatorInstanceList;
	}
	
}
