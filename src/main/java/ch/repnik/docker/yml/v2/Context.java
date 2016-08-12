package ch.repnik.docker.yml.v2;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.validation.YamlValidator;

@YamlProperty(path=Context.PATH)
public class Context implements YamlValidator<String>{
	
	public final static String PATH = "/build/context";
	
	public void validate(String value) throws DockerComposeValidationException {
		if (value.trim().isEmpty()){
			throw new DockerComposeValidationException(String.format("String '%s' must not be empty", PATH));
		}
	}
}
