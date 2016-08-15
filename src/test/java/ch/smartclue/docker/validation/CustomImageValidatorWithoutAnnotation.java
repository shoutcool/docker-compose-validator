package ch.smartclue.docker.validation;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class CustomImageValidatorWithoutAnnotation implements YamlValidator<String> {

	public static final String PATH = "/image";
	
	public void validate(String value) throws DockerComposeValidationException {
		if ("foo".equals(value)){
			throw new DockerComposeValidationException(PATH + " must not be 'foo'");
		}
	}

}
