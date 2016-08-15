package ch.smartclue.docker.validation;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.validation.YamlValidator;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=CustomImageValidator.PATH, version=DockerComposeVersion.V1)
public class CustomImageValidator implements YamlValidator<String> {

	public static final String PATH = "/image";
	
	public void validate(String value) throws DockerComposeValidationException {
		if ("foo".equals(value)){
			throw new DockerComposeValidationException(PATH + " must not be 'foo'");
		}
	}

}
