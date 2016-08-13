package ch.smartclue.docker.yml.v1;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class CustomImageValidator implements YamlValidator<String> {

	public static final String PATH = "/image";
	
	public void validate(String value) throws DockerComposeValidationException {
		if ("foo".equals(value)){
			throw new DockerComposeValidationException(PATH + " must not be 'foo'");
		}
	}

}
