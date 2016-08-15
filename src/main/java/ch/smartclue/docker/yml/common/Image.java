package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Image.PATH, version=DockerComposeVersion.ALL)
public class Image extends AbstractGenericString{

	protected final static String PATH = "/image";
	
	public Image() {
		super(PATH);
	}
}
