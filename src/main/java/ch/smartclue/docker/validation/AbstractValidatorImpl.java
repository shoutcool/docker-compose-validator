package ch.smartclue.docker.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.esotericsoftware.yamlbeans.YamlException;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.reader.StructureReader;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

abstract class AbstractValidatorImpl implements Validator {
	protected ValidatorManager validatorManager;
	protected Map<String, Object> structure = new HashMap<String, Object>();
	
	public AbstractValidatorImpl(ValidatorManager validatorManager){
		this.validatorManager = validatorManager;
	}
	
	protected void readStructure(String content) throws DockerComposeValidationException{
		StructureReader reader = new StructureReader();
		try {
			structure = reader.readStructure(content);
			System.out.println(structure.keySet());
		} catch (YamlException e) {
			throw new DockerComposeValidationException("Error while reading structure of yaml file", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void validate(DockerComposeVersion... versions) throws DockerComposeValidationException {
		for (Entry<String, Object> entry : structure.entrySet()) {
			List<ValidatorInstance> validatorInstancesByVersion = validatorManager.getValidatorInstancesByVersion(versions);
			List<ValidatorInstance> filterValidatorsByPath = ValidatorInstanceFilter.filterValidatorsByPath(validatorInstancesByVersion, entry.getKey());

			for (ValidatorInstance instance : filterValidatorsByPath){
				instance.getValidator().validate(entry.getValue());
			}
		}
	}

	private boolean isContainerNode(String key) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
