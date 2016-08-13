package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=ExtraHosts.PATH)
public class ExtraHosts extends AbstractGenericString{

	protected final static String PATH = "/image";
	
	public ExtraHosts() {
		super(PATH);
	}
}
