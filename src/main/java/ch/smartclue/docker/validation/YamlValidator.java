package ch.smartclue.docker.validation;

import ch.smartclue.docker.exception.DockerComposeValidationException;

public interface YamlValidator<T> {
	void validate(T value) throws DockerComposeValidationException;
}
