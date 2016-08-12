package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=EntryPoint.PATH)
public class EntryPoint extends AbstractGenericStringOrList{

	protected final static String PATH = "/entrypoint";
	
	public EntryPoint() {
		super(PATH);
	}
}
