package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Ulimits.PATH, version=DockerComposeVersion.ALL, containerProperty=true)
public class Ulimits extends AbstractGenericStringOrMap{

	protected final static String PATH = "/ulimits";
	
	public Ulimits() {
		super(PATH);
	}
}
