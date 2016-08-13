package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=EntryPoint.PATH)
public class EntryPoint extends AbstractGenericStringOrList{

	protected final static String PATH = "/entrypoint";
	
	public EntryPoint() {
		super(PATH);
	}
}
