package ch.smartclue.docker.validation;

import ch.smartclue.docker.yml.common.DockerComposeVersion;

@SuppressWarnings("rawtypes") 
class ValidatorInstance {

	private String path;
	private YamlValidator validator;
	private boolean isBuiltInValidator;
	private DockerComposeVersion version;
	
	public ValidatorInstance(YamlValidator validator, boolean isBuiltInValidator){
		
		YamlProperty annotation = validator.getClass().getAnnotation(YamlProperty.class);
		
		this.path = annotation.path();
		this.version = annotation.version();
		this.isBuiltInValidator = isBuiltInValidator;
		this.validator = validator;
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
}
