package ch.smartclue.docker.validation;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.yml.common.ExtendsValidator;
import ch.smartclue.docker.yml.generic.DockerComposeVersion;
import ch.smartclue.docker.yml.generic.GenericListValidator;
import ch.smartclue.docker.yml.generic.GenericMapOrListValidator;
import ch.smartclue.docker.yml.generic.GenericMapValidator;
import ch.smartclue.docker.yml.generic.GenericStringOrListValidator;
import ch.smartclue.docker.yml.generic.GenericStringOrMapValidator;
import ch.smartclue.docker.yml.generic.GenericStringValidator;
import ch.smartclue.docker.yml.v2.BuildValidator;

public class DockerComposeValidator {
	
	private ValidatorManager validatorManager = new ValidatorManager();
	
	public DockerComposeValidator(){
		
		//COMMON VALIDATORS
		addDefaultValidator("/cap_add", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/cap_drop", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/cgroup_parent", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/command", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/container_name", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/devices", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/dns", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/dns_search", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/entrypoint", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/env_file", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/environment", DockerComposeVersion.ALL, new GenericMapOrListValidator());
		addDefaultValidator("/expose", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/extends", DockerComposeVersion.ALL, new ExtendsValidator());
		addDefaultValidator("/external_links", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/extra_hosts", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/image", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/labels", DockerComposeVersion.ALL, new GenericMapOrListValidator());
		addDefaultValidator("/${container}/links", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/pid", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/ports", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/security_opt", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/stop_signal", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/ulimits", DockerComposeVersion.ALL, new GenericStringOrMapValidator());
		
		
		
		//V1 VALIDATORS
		addDefaultValidator("/build", DockerComposeVersion.V1, new GenericStringValidator());
		addDefaultValidator("/dockerfile", DockerComposeVersion.V1, new GenericStringValidator());
		addDefaultValidator("/log_driver", DockerComposeVersion.V1, new GenericStringValidator());
		addDefaultValidator("/log_opt", DockerComposeVersion.V1, new GenericMapValidator());
		addDefaultValidator("/net", DockerComposeVersion.V1, new GenericStringValidator());
		
		//V2 VALIDATORS
		addDefaultValidator("/build/args", DockerComposeVersion.V2, new GenericMapOrListValidator());
		addDefaultValidator("/build", DockerComposeVersion.V2, new BuildValidator());
		addDefaultValidator("/build/context", DockerComposeVersion.V2, new GenericStringValidator());
		addDefaultValidator("/build/dockerfile", DockerComposeVersion.V2, new GenericStringValidator());
		addDefaultValidator("/logging/driver", DockerComposeVersion.V2, new GenericStringValidator());
		addDefaultValidator("/logging", DockerComposeVersion.V2, new GenericMapValidator());
		addDefaultValidator("/net", DockerComposeVersion.V2, new GenericMapOrListValidator());
		addDefaultValidator("/logging/options", DockerComposeVersion.V2, new GenericMapValidator());
		addDefaultValidator("/tmpfs", DockerComposeVersion.V2, new GenericStringOrListValidator());
	}

	public void addCustomValidator(String path, DockerComposeVersion version, YamlValidator<?> validator){
		addValidator(path, version, validator, true);
	}
	
	private void addDefaultValidator(String path, DockerComposeVersion version, YamlValidator<?> validator){
		addValidator(path, version, validator, true);
	}
	
	private void addValidator(String path, DockerComposeVersion version, YamlValidator<?> validator, boolean isBuiltInValidator){
		ValidatorInstance instance = new ValidatorInstance(path, version, validator, isBuiltInValidator);
		validatorManager.addValidatorInstance(instance);
	}
	
	ValidatorManager getValidatorManager(){
		return validatorManager;
	}
	
	public void validate(String content) throws DockerComposeValidationException{
		Validator validator = ValidatorFactory.create(content, validatorManager);
		validator.validate();
	}
}
