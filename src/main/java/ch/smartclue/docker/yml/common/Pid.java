package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Pid.PATH, version=DockerComposeVersion.ALL)
public class Pid extends AbstractGenericString{

	protected final static String PATH = "/pid";
	
	public Pid() {
		super(PATH);
	}
}
