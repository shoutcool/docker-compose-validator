package ch.repnik.docker.yml.v1;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.AbstractValidatorImpl;

public class ValidatorV1Impl extends AbstractValidatorImpl {

	public ValidatorV1Impl(String content) throws DockerComposeValidationException {
		readStructure(content);
	}

	public void validate() throws DockerComposeValidationException {
		
		if (structure.containsKey("/build") && structure.containsKey("/image")){
			throw new DockerComposeValidationException("It is not allowed to use 'build' and 'image' together");
		}

		if (structure.containsKey("/dockerfile") && structure.containsKey("/image")){
			throw new DockerComposeValidationException("It is not allowed to use 'dockerfile' and 'image' together");
		}
		
		if (structure.containsKey("/dockerfile") && !structure.containsKey("/build")){
			throw new DockerComposeValidationException("'/build' must be specified if using '/dockerfile'");
		}
		
		
		validate("ch.repnik.docker.yml.v1", "ch.repnik.docker.yml.common");
	}
}
