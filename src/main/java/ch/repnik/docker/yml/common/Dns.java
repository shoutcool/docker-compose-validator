package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=Dns.PATH)
public class Dns extends AbstractGenericStringOrList{

	protected final static String PATH = "/dns";
	
	public Dns() {
		super(PATH);
	}

	
	
}
