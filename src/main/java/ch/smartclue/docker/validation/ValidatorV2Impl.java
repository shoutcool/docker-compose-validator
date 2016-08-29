package ch.smartclue.docker.validation;

import java.util.List;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.yml.generic.DockerComposeVersion;

class ValidatorV2Impl extends AbstractValidatorImpl {
	
	public ValidatorV2Impl(String content, ValidatorManager validatorManager) throws DockerComposeValidationException {
		super(validatorManager);
		readStructure(content);
	}

	public void validate() throws DockerComposeValidationException {
		if (structure.containsKey("/build/dockerfile") && !structure.containsKey("/build/context")){
			throw new DockerComposeValidationException("'/build/context' must be specified if using '/build/dockerfile'");
		}
		
		List<ValidatorInstance> instances = validatorManager.getValidatorInstancesByVersion(DockerComposeVersion.ALL,
				DockerComposeVersion.V2);
		
		validate(instances);
	}
	
}
