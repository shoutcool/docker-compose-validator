package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Devices.PATH, version=DockerComposeVersion.ALL)
public class Devices extends AbstractGenericList{

	protected final static String PATH = "/devices";
	
	public Devices() {
		super(PATH);
	}
}
