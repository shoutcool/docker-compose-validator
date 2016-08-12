package ch.repnik.docker.yml.common;

import java.util.List;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.validation.YamlValidator;

@YamlProperty(path=CapDrop.PATH)
public class CapDrop implements YamlValidator<List<String>>{
	
	public final static String PATH = "/cap_drop";
	
	public void validate(List<String> value) throws DockerComposeValidationException {
		if (value.isEmpty()){
			throw new DockerComposeValidationException(String.format("List '%s' must not be empty", PATH));
		}
	}
}
