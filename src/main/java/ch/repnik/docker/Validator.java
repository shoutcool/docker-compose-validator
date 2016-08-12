package ch.repnik.docker;

public interface Validator {

	void validate() throws DockerComposeValidationException;
	
}
