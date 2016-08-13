package ch.smartclue.docker;

public interface Validator {
	void validate() throws DockerComposeValidationException;
}
