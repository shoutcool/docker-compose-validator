package ch.smartclue.docker.validation;

import ch.smartclue.docker.exception.DockerComposeValidationException;

interface Validator {
	void validate() throws DockerComposeValidationException;
}
