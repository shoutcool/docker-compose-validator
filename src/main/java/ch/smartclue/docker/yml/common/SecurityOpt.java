package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=SecurityOpt.PATH, version=DockerComposeVersion.ALL, containerProperty=true)
public class SecurityOpt extends AbstractGenericList{

	protected final static String PATH = "/security_opt";
	
	public SecurityOpt() {
		super(PATH);
	}
}
