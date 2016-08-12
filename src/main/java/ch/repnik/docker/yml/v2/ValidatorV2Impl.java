package ch.repnik.docker.yml.v2;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.AbstractValidatorImpl;

public class ValidatorV2Impl extends AbstractValidatorImpl {
	public ValidatorV2Impl(String content) throws DockerComposeValidationException {
		readStructure(content);
	}

	public void validate() throws DockerComposeValidationException {
		
		if (structure.containsKey("/build/dockerfile") && !structure.containsKey("/build/context")){
			throw new DockerComposeValidationException("'/build/context' must be specified if using '/build/dockerfile'");
		}
		
		validate("ch.repnik.docker.yml.v2", "ch.repnik.docker.yml.common");
	}
}
