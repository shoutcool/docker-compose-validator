package ch.smartclue.docker.validation;

import java.util.ArrayList;
import java.util.List;

import ch.smartclue.docker.yml.common.DockerComposeVersion;

class ValidatorManager {

	private List<ValidatorInstance> validatorInstances = new ArrayList<ValidatorInstance>();
	
	public void addValidatorInstance(ValidatorInstance instance){
		validatorInstances.add(instance);
	}
	
	public List<ValidatorInstance> getValidatorInstancesByVersion(DockerComposeVersion... version){
		 return ValidatorInstanceFilter.filterValidatorsByVersion(validatorInstances, version);
	}
	
	public List<ValidatorInstance> getValidatorInstancesByPath(String path){
		 return ValidatorInstanceFilter.filterValidatorsByPath(validatorInstances, path);
	}
	
	
}
