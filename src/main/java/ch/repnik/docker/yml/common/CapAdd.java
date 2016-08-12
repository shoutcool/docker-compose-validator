package ch.repnik.docker.yml.common;

import java.util.List;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.validation.YamlValidator;

@YamlProperty(path=CapAdd.PATH)
public class CapAdd implements YamlValidator<List<String>>{
	
	public final static String PATH = "/cap_add";
	
	public void validate(List<String> value) throws DockerComposeValidationException {
		if (value.isEmpty()){
			throw new DockerComposeValidationException(String.format("List '%s' must not be empty", PATH));
		}
	}
}
