package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=CapDrop.PATH, version=DockerComposeVersion.ALL)
public class CapDrop extends AbstractGenericList{

	protected final static String PATH = "/cap_drop";
	
	public CapDrop() {
		super(PATH);
	}
}
