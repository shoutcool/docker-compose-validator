package ch.smartclue.docker.validation;

import ch.smartclue.docker.exception.DockerComposeValidationException;

public class CustomImageValidator implements YamlValidator<String> {
	
	public void validate(String path, String value) throws DockerComposeValidationException {
		if ("foo".equals(value)){
			throw new DockerComposeValidationException(path + " must not be 'foo'");
		}
	}
}
