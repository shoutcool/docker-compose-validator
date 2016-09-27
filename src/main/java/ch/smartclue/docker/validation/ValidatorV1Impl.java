package ch.smartclue.docker.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.smartclue.docker.exception.DockerComposeValidationException;

class ValidatorV1Impl implements Validator {

	private List<Service> services;
	private List<ValidatorInstance> instances;
	private ValidationExecutor executor;
	private Map<String, Object> structure;

	public ValidatorV1Impl(List<ValidatorInstance> instances, ValidationExecutor executor,
			Map<String, Object> structure) throws DockerComposeValidationException {
		this.instances = instances;
		this.executor = executor;
		this.structure = structure;

		services = readServices();

	}

	@SuppressWarnings("unchecked")
	List<Service> readServices() {
		List<Service> serviceList = new ArrayList<Service>();
		for (Entry<String, Object> entry : structure.entrySet()) {
			if (entry.getKey().split("/").length == 2) {
				Service service = new Service(entry.getKey(), (Map<String, Object>) entry.getValue());
				serviceList.add(service);
			}
		}
		return serviceList;
	}

	public void validate() throws DockerComposeValidationException {
		for (Service service : services) {
			if (service.hasSubNode("/build") && service.hasSubNode("/image")) {
				throw new DockerComposeValidationException("It is not allowed to use 'build' and 'image' together");
			}

			if (service.hasSubNode("/dockerfile") && service.hasSubNode("/image")) {
				throw new DockerComposeValidationException(
						"It is not allowed to use 'dockerfile' and 'image' together");
			}

			if (service.hasSubNode("/dockerfile") && !service.hasSubNode("/build")) {
				throw new DockerComposeValidationException("'/build' must be specified if using '/dockerfile'");
			}
		}

		// 

		executor.validate(instances, structure);
	}

}
