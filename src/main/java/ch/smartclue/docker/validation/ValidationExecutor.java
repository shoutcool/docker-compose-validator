package ch.smartclue.docker.validation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.smartclue.docker.exception.DockerComposeValidationException;

class ValidationExecutor{
	private Logger logger = LoggerFactory.getLogger(ValidationExecutor.class);
	
	
	public void validate(List<ValidatorInstance> validatorInstances, Map<String, Object> structure) throws DockerComposeValidationException {
		for (Entry<String, Object> entry : structure.entrySet()) {

			List<ValidatorInstance> containerValidators = ValidatorInstanceFilter.filterValidatorsByServiceNode(validatorInstances, true, entry.getKey());
			if (!containerValidators.isEmpty()){
				executeValidators(containerValidators, entry.getKey(), entry.getValue());
			}else{
				logger.info(String.format("No validators found for node '%s'", entry.getKey()));
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	public void executeValidators(List<ValidatorInstance> validators, String path, Object nodeValue) throws DockerComposeValidationException{
		for (ValidatorInstance instance : validators){
			instance.getValidator().validate(path, nodeValue);
		}
	}
}
