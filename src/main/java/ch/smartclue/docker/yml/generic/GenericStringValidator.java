package ch.smartclue.docker.yml.generic;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class GenericStringValidator implements YamlValidator<String> {

	public void validate(String path, String value) throws DockerComposeValidationException {
		if (value.trim().isEmpty()){
			throw new DockerComposeValidationException(String.format("String '%s' must not be empty", path));
		}
	}

}
