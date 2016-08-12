package ch.repnik.docker.yml.common;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlValidator;

public abstract class AbstractGenericString implements YamlValidator<String> {

	private String path;
	
	public AbstractGenericString(String path){
		this.path = path;
	}
	
	public void validate(String value) throws DockerComposeValidationException {
		if (value.trim().isEmpty()){
			throw new DockerComposeValidationException(String.format("String '%s' must not be empty", path));
		}
	}

}
