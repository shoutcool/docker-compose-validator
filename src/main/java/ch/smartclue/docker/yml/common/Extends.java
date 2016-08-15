package ch.smartclue.docker.yml.common;

import java.util.HashMap;
import java.util.Map;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.validation.YamlValidator;

@YamlProperty(path = Extends.PATH, version=DockerComposeVersion.ALL)
public class Extends implements YamlValidator<Object> {

	protected final static String PATH = "/extends";

	public void validate(Object value) throws DockerComposeValidationException {

		if (value instanceof Map) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			if (!map.containsKey("service")) {
				throw new DockerComposeValidationException(String.format("String '%s/service' is missing", PATH, PATH));
			} 
		} else {
			throw new DockerComposeValidationException(
					String.format("'%s' must be from type Map", PATH));
		}
	}

}
