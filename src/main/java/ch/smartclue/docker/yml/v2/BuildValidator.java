package ch.smartclue.docker.yml.v2;

import java.util.HashMap;
import java.util.Map;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class BuildValidator implements YamlValidator<Object>{
	
	public void validate(String path, Object value) throws DockerComposeValidationException {
		
		if (value instanceof String){
			if (String.valueOf(value).trim().isEmpty()){
				throw new DockerComposeValidationException(String.format("String '%s' must not be empty", path));
			}
		}else if (value instanceof Map){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			if (map.isEmpty()){
				throw new DockerComposeValidationException(String.format("Map '%s' must not be empty", path));
			} else if (!map.containsKey("context")){
				throw new DockerComposeValidationException(String.format("String '%s/context' is missing", path));
			}
		}else{
			throw new DockerComposeValidationException(String.format("'%s' must be either from type String or Map", path));
		}
	}
}
