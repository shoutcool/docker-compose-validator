package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Links.PATH, version=DockerComposeVersion.ALL, containerProperty=true)
public class Links extends AbstractGenericList{

	protected final static String PATH = "/${container}/links";
	
	public Links() {
		super(PATH);
	}
}
