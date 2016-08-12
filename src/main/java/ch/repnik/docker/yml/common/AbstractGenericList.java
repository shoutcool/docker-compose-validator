package ch.repnik.docker.yml.common;

import java.util.List;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlValidator;

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
