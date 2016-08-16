package ch.smartclue.docker.yml.common;

import java.util.Map;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public abstract class AbstractGenericMap implements YamlValidator<Map<String, Object>> {

	private String path;
	
	public AbstractGenericMap(String path){
		this.path = path;
	}
	
	public void validate(Map<String, Object> value) throws DockerComposeValidationException {
		if (value.isEmpty()){
			throw new DockerComposeValidationException(String.format("Map '%s' must not be empty", path));
		} 
	}

}
