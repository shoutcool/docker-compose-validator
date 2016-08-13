package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.validation.AbstractValidatorImpl;

public class ValidatorV2Impl extends AbstractValidatorImpl {
	public ValidatorV2Impl(String content) throws DockerComposeValidationException {
		readStructure(content);
	}

	public void validate() throws DockerComposeValidationException {
		
		if (structure.containsKey("/build/dockerfile") && !structure.containsKey("/build/context")){
			throw new DockerComposeValidationException("'/build/context' must be specified if using '/build/dockerfile'");
		}
		
		validate("ch.smartclue.docker.yml.v2", "ch.smartclue.docker.yml.common");
	}
}
