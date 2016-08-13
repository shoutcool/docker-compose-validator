package ch.smartclue.docker;

import java.util.HashMap;
import java.util.Map;

import ch.smartclue.docker.validation.YamlValidator;

public class DockerComposeValidator {
	
	public enum DockerComposeVersion {
		V1, V2, ALL
	}
	
	Map<DockerComposeVersion, Map<String, YamlValidator<?>>> additionalValidators = new HashMap<DockerComposeValidator.DockerComposeVersion, Map<String, YamlValidator<?>>>();
	
	public void addAdditionalValidators(DockerComposeVersion version, String path, YamlValidator<?> validator){
		Map<String, YamlValidator<?>> validatorMap = null;
		
		if (additionalValidators.containsKey(version)){
			validatorMap = additionalValidators.get(version);
		} else {
			validatorMap = new HashMap<String, YamlValidator<?>>();
		}

		validatorMap.put(path, validator);
		
		additionalValidators.put(version, validatorMap);
	}
	
	public void validate(String content) throws DockerComposeValidationException{
		Validator validator = ValidatorFactory.create(content, additionalValidators);
	}
}
