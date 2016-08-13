package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Dns.PATH)
public class Dns extends AbstractGenericStringOrList{

	protected final static String PATH = "/dns";
	
	public Dns() {
		super(PATH);
	}

	
	
}