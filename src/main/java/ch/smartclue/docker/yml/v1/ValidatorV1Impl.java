package ch.smartclue.docker.yml.v1;

import java.util.Map;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.validation.AbstractValidatorImpl;
import ch.smartclue.docker.validation.YamlValidator;

public class ValidatorV1Impl extends AbstractValidatorImpl {

	public ValidatorV1Impl(String content, Map<String,YamlValidator<?>> additionalValidators) throws DockerComposeValidationException {
		this.additionalValidators = additionalValidators;
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
		
		
		validate(additionalValidators, "ch.smartclue.docker.yml.v1", "ch.smartclue.docker.yml.common");
	}
}
