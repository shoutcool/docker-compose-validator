package ch.repnik.docker.validation;

import ch.repnik.docker.DockerComposeValidationException;

public interface YamlValidator<T> {
	void validate(T value) throws DockerComposeValidationException;
}
