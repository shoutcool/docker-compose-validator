package ch.smartclue.docker.yml.v2;

import java.util.Map;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.validation.AbstractValidatorImpl;
import ch.smartclue.docker.validation.YamlValidator;

public class ValidatorV2Impl extends AbstractValidatorImpl {
	public ValidatorV2Impl(String content, Map<String,YamlValidator<?>> additionalValidators) throws DockerComposeValidationException {
		this.additionalValidators = additionalValidators;
		readStructure(content);
	}

	public void validate() throws DockerComposeValidationException {
		
		if (structure.containsKey("/build/dockerfile") && !structure.containsKey("/build/context")){
			throw new DockerComposeValidationException("'/build/context' must be specified if using '/build/dockerfile'");
		}
		
		validate(additionalValidators, "ch.smartclue.docker.yml.v2", "ch.smartclue.docker.yml.common");
	}
}
