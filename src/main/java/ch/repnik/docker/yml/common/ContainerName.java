package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=ContainerName.PATH)
public class ContainerName extends AbstractGenericString{

	protected final static String PATH = "/container_name";
	
	public ContainerName() {
		super(PATH);
	}
}
