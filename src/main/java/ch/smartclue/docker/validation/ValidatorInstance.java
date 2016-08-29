package ch.smartclue.docker.validation;

import ch.smartclue.docker.yml.generic.DockerComposeVersion;

@SuppressWarnings("rawtypes") 
class ValidatorInstance {

	private String path;
	private YamlValidator validator;
	private boolean isBuiltInValidator;
	private DockerComposeVersion version;
	private boolean isContainerNode;
	
	public ValidatorInstance(String path, DockerComposeVersion version, YamlValidator validator, boolean isBuiltInValidator){
		this.path = path;
		this.version = version;
		this.isBuiltInValidator = isBuiltInValidator;
		this.validator = validator;
		this.isContainerNode = path.contains("${service}");
	}

	public String getPath() {
		return path;
	}

	public YamlValidator getValidator() {
		return validator;
	}

	public boolean isBuiltInValidator() {
		return isBuiltInValidator;
	}

	public DockerComposeVersion getVersion() {
		return version;
	}

	public boolean isContainerNode() {
		return isContainerNode;
	}

}
