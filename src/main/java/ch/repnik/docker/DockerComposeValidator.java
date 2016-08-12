package ch.repnik.docker;

public class DockerComposeValidator {
	public void validate(String content) throws DockerComposeValidationException{
		Validator validator = ValidatorFactory.create(content);
	}
}
