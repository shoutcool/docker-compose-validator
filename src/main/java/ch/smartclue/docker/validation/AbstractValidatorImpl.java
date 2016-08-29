package ch.smartclue.docker.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.yamlbeans.YamlException;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.reader.StructureReader;

abstract class AbstractValidatorImpl implements Validator {
	protected ValidatorManager validatorManager;
	protected List<Service> services = new ArrayList<Service>();
	protected Map<String, Object> structure = new HashMap<String, Object>();
	private Logger logger = LoggerFactory.getLogger(AbstractValidatorImpl.class);
    
	
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
	
	protected void validate(List<ValidatorInstance> validatorInstances) throws DockerComposeValidationException {
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
	void executeValidators(List<ValidatorInstance> validators, String path, Object nodeValue) throws DockerComposeValidationException{
		for (ValidatorInstance instance : validators){
			instance.getValidator().validate(path, nodeValue);
		}
	}
}
