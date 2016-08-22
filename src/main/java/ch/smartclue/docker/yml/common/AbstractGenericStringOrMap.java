package ch.smartclue.docker.yml.common;

import java.util.List;
import java.util.Map;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public abstract class AbstractGenericStringOrMap implements YamlValidator<Object> {

	private String path;
	
	public AbstractGenericStringOrMap(String path){
		this.path = path;
	}
	
	@SuppressWarnings("unchecked")
	public void validate(Object value) throws DockerComposeValidationException {
		if (value instanceof String){
			String command = String.valueOf(value);
			if (command.trim().isEmpty()){
				throw new DockerComposeValidationException(String.format("String '%s' must not be empty", path));
			}
		} else if (value instanceof Map){
			Map<String, Object> map = (Map<String, Object>) value;
			if (map.isEmpty()){
				throw new DockerComposeValidationException(String.format("Map '%s' must not be empty", path));
			}
		} else {
			throw new DockerComposeValidationException(String.format("'%s' must be either from type String or Map", path));
		}
	}

}
