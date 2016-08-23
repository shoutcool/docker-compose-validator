package ch.smartclue.docker.yml.common;

import java.util.HashMap;
import java.util.Map;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class ExtendsValidator implements YamlValidator<Object> {

	public void validate(String path, Object value) throws DockerComposeValidationException {

		if (value instanceof Map) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			if (!map.containsKey("service")) {
				throw new DockerComposeValidationException(String.format("String '%s/service' is missing", path));
			} 
		} else {
			throw new DockerComposeValidationException(
					String.format("'%s' must be from type Map", path));
		}
	}

}
