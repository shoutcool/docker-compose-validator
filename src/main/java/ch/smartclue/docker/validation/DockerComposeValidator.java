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
		addDefaultValidator("/${service}/cap_add", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/cap_drop", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/cgroup_parent", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/${service}/command", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/${service}/container_name", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/${service}/devices", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/dns", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/${service}/dns_search", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/${service}/entrypoint", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/${service}/env_file", DockerComposeVersion.ALL, new GenericStringOrListValidator());
		addDefaultValidator("/${service}/environment", DockerComposeVersion.ALL, new GenericMapOrListValidator());
		addDefaultValidator("/${service}/expose", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/extends", DockerComposeVersion.ALL, new ExtendsValidator());
		addDefaultValidator("/${service}/external_links", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/extra_hosts", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/image", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/${service}/labels", DockerComposeVersion.ALL, new GenericMapOrListValidator());
		addDefaultValidator("/${service}/links", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/pid", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/${service}/ports", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/security_opt", DockerComposeVersion.ALL, new GenericListValidator());
		addDefaultValidator("/${service}/stop_signal", DockerComposeVersion.ALL, new GenericStringValidator());
		addDefaultValidator("/${service}/ulimits", DockerComposeVersion.ALL, new GenericStringOrMapValidator());
		
		
		
		//V1 VALIDATORS
		addDefaultValidator("/${service}/build", DockerComposeVersion.V1, new GenericStringValidator());
		addDefaultValidator("/${service}/dockerfile", DockerComposeVersion.V1, new GenericStringValidator());
		addDefaultValidator("/${service}/log_driver", DockerComposeVersion.V1, new GenericStringValidator());
		addDefaultValidator("/${service}/log_opt", DockerComposeVersion.V1, new GenericMapValidator());
		addDefaultValidator("/${service}/net", DockerComposeVersion.V1, new GenericStringValidator());
		
		//V2 VALIDATORS
		addDefaultValidator("/services", DockerComposeVersion.V2, new GenericMapOrListValidator());
		addDefaultValidator("/volumes", DockerComposeVersion.V2, new GenericMapOrListValidator());
		addDefaultValidator("/networks", DockerComposeVersion.V2, new GenericMapOrListValidator());

		addDefaultValidator("/${service}/build/args", DockerComposeVersion.V2, new GenericMapOrListValidator());
		addDefaultValidator("/${service}/build", DockerComposeVersion.V2, new BuildValidator());
		addDefaultValidator("/${service}/build/context", DockerComposeVersion.V2, new GenericStringValidator());
		addDefaultValidator("/${service}/build/dockerfile", DockerComposeVersion.V2, new GenericStringValidator());
		addDefaultValidator("/${service}/logging/driver", DockerComposeVersion.V2, new GenericStringValidator());
		addDefaultValidator("/${service}/logging", DockerComposeVersion.V2, new GenericMapValidator());
		addDefaultValidator("/${service}/net", DockerComposeVersion.V2, new GenericMapOrListValidator());
		addDefaultValidator("/${service}/logging/options", DockerComposeVersion.V2, new GenericMapValidator());
		addDefaultValidator("/${service}/tmpfs", DockerComposeVersion.V2, new GenericStringOrListValidator());
	}

	public void addCustomValidator(String path, DockerComposeVersion version, YamlValidator<?> validator){
		addValidator(path, version, validator, false);
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
