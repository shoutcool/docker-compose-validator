package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=ContainerName.PATH)
public class ContainerName extends AbstractGenericString{

	protected final static String PATH = "/container_name";
	
	public ContainerName() {
		super(PATH);
	}
}
