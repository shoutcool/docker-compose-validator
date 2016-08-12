package ch.repnik.docker.yml.v1;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.validation.YamlValidator;

@YamlProperty(path = Build.PATH)
public class Build implements YamlValidator<String> {

	public final static String PATH = "/build";

	public void validate(String value) throws DockerComposeValidationException {
		if (value.trim().isEmpty()) {
			throw new DockerComposeValidationException(String.format("String '%s' must not be empty", PATH));
		}
	}
}
