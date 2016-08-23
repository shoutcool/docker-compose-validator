package ch.smartclue.docker.yml.generic;

import java.util.List;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class GenericListValidator implements YamlValidator<List<String>> {
	
	public void validate(String path, List<String> value) throws DockerComposeValidationException {
		if (value.isEmpty()){
			throw new DockerComposeValidationException(String.format("List '%s' must not be empty", path));
		}
	}

}
