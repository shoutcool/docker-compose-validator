package ch.smartclue.docker.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.smartclue.docker.exception.DockerComposeValidationException;

class ValidatorV2Impl implements Validator {
	
	private List<ValidatorInstance> instances;
	private ValidationExecutor executor;
	private Map<String, Object> structure;
	private List<Service> services;

	public ValidatorV2Impl(List<ValidatorInstance> instances, ValidationExecutor executor, Map<String, Object> structure) throws DockerComposeValidationException {
		this.instances = instances;
		this.executor = executor;
		this.structure = structure;
		
		services = readServices();
	}

	public void validate() throws DockerComposeValidationException {
		for (Service service : services){
			if (service.hasSubNode("/build/dockerfile") && !service.hasSubNode("/build/context")) {
				throw new DockerComposeValidationException("'/build/context' must be specified if using '/build/dockerfile'");
			}
		}
		executor.validate(instances, structure);
	}

	@SuppressWarnings("unchecked")
	List<Service> readServices() {
		List<Service> serviceList = new ArrayList<Service>();
		for (Entry<String, Object> entry : structure.entrySet()){
			if (entry.getKey().split("/").length == 3 && entry.getKey().startsWith("/services/")){
				Service service = new Service(entry.getKey(), (Map<String, Object>) entry.getValue());
				serviceList.add(service);
			}
		}
		return serviceList;
	}
	
}
