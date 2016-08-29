package ch.smartclue.docker.validation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.yml.generic.DockerComposeVersion;

class ValidatorV1Impl extends AbstractValidatorImpl {

	public ValidatorV1Impl(String content, ValidatorManager validatorManager) throws DockerComposeValidationException {
		super(validatorManager);
		readStructure(content);
		getServices();
		
	}

	@SuppressWarnings("unchecked")
	private void getServices(){
		for (Entry<String, Object> entry : structure.entrySet()){
			if (entry.getKey().split("/").length == 2){
				Service service = new Service(entry.getKey(), (Map<String, Object>) entry.getValue());
				services.add(service);
			}
		}
	}
	
	public void validate() throws DockerComposeValidationException {

		for (Service service : services){
			
			if (service.hasSubNode("/build") && service.hasSubNode("/image")) {
				throw new DockerComposeValidationException("It is not allowed to use 'build' and 'image' together");
			}

			if (service.hasSubNode("/dockerfile") && service.hasSubNode("/image")) {
				throw new DockerComposeValidationException("It is not allowed to use 'dockerfile' and 'image' together");
			}

			if (service.hasSubNode("/dockerfile") && !service.hasSubNode("/build")) {
				throw new DockerComposeValidationException("'/build' must be specified if using '/dockerfile'");
			}
		}
		
		

		List<ValidatorInstance> instances = validatorManager.getValidatorInstancesByVersion(DockerComposeVersion.ALL,
				DockerComposeVersion.V1);
		;

		validate(instances);
	}

}
