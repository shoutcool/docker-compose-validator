package ch.smartclue.docker.yml.generic;

import java.util.Map;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class GenericMapValidator implements YamlValidator<Map<String, Object>> {

	public void validate(String path, Map<String, Object> value) throws DockerComposeValidationException {
		if (value.isEmpty()){
			throw new DockerComposeValidationException(String.format("Map '%s' must not be empty", path));
		} 
	}

}
