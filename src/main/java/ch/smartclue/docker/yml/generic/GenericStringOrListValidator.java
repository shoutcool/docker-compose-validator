package ch.smartclue.docker.yml.generic;

import java.util.List;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public class GenericStringOrListValidator implements YamlValidator<Object> {

	@SuppressWarnings("unchecked")
	public void validate(String path, Object value) throws DockerComposeValidationException {
		if (value instanceof String){
			String command = String.valueOf(value);
			if (command.trim().isEmpty()){
				throw new DockerComposeValidationException(String.format("String '%s' must not be empty", path));
			}
		} else if (value instanceof List){
			List<String> list = (List<String>) value;
			if (list.isEmpty()){
				throw new DockerComposeValidationException(String.format("List '%s' must not be empty", path));
			}
		} else {
			throw new DockerComposeValidationException(String.format("'%s' must be either from type String or List", path));
		}
	}

}
