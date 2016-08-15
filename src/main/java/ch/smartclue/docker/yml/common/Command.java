package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Command.PATH, version=DockerComposeVersion.ALL)
public class Command extends AbstractGenericStringOrList{
	protected final static String PATH = "/command";
	
	public Command() {
		super(PATH);
	}
}
