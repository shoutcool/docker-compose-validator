package ch.smartclue.docker.yml.common;

import java.util.List;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.validation.YamlValidator;

public abstract class AbstractGenericList implements YamlValidator<List<String>> {

	private String path;
	
	public AbstractGenericList(String path){
		this.path = path;
	}
	
	public void validate(List<String> value) throws DockerComposeValidationException {
		if (value.isEmpty()){
			throw new DockerComposeValidationException(String.format("List '%s' must not be empty", path));
		}
	}

}
