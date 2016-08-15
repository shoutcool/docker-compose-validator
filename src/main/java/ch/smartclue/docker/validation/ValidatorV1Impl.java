package ch.smartclue.docker.validation;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

class ValidatorV1Impl extends AbstractValidatorImpl {

	public ValidatorV1Impl(String content, ValidatorManager validatorManager) throws DockerComposeValidationException {
		super(validatorManager);
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
		
		validate(DockerComposeVersion.ALL, DockerComposeVersion.V1);
	}
}
