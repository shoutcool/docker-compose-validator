package ch.smartclue.docker.validation;

import java.util.ArrayList;
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
	private List<String> containerNodes = new ArrayList<String>();
	
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
	
	protected void validate(DockerComposeVersion... versions) throws DockerComposeValidationException {
		for (Entry<String, Object> entry : structure.entrySet()) {
			List<ValidatorInstance> validatorInstancesByVersion = validatorManager.getValidatorInstancesByVersion(versions);
			List<ValidatorInstance> filterValidatorsByPath = ValidatorInstanceFilter.filterValidatorsByPath(validatorInstancesByVersion, entry.getKey());

			if (filterValidatorsByPath.isEmpty() 
					&& (checkAndAddTopLevelNode(entry.getKey()) || isSubNodeOfExistingContainerNode(entry.getKey()))){
				//threat as container node
				List<ValidatorInstance> containerValidators = ValidatorInstanceFilter.filterValidatorsByContainerNode(validatorInstancesByVersion, true, entry.getKey());
				executeValidators(containerValidators, entry.getValue());
			}else{
				executeValidators(filterValidatorsByPath, entry.getValue());
			}
		}
	}
	
	private boolean isSubNodeOfExistingContainerNode(String key) {
		for (String containerNodeName : containerNodes){
			if (key.startsWith(containerNodeName)){
				return true;
			}
		}
		
		return false;
	}

	private boolean checkAndAddTopLevelNode(String nodePath){
		boolean isTopLevelNode = nodePath.indexOf("/", 1) == -1;
		if (isTopLevelNode){
			containerNodes.add(nodePath);
		}
		
		return isTopLevelNode;
	}
	
	@SuppressWarnings("unchecked")
	void executeValidators(List<ValidatorInstance> validators, Object nodeValue) throws DockerComposeValidationException{
		for (ValidatorInstance instance : validators){
			instance.getValidator().validate(nodeValue);
		}
	}

	private boolean isContainerNode(String key) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
