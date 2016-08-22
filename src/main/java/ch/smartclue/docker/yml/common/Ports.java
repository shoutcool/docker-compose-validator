package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Ports.PATH, version=DockerComposeVersion.ALL, containerProperty=true)
public class Ports extends AbstractGenericList{

	protected final static String PATH = "/ports";
	
	public Ports() {
		super(PATH);
	}
}
