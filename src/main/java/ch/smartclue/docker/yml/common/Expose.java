package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Expose.PATH)
public class Expose extends AbstractGenericList{

	protected final static String PATH = "/expose";
	
	public Expose() {
		super(PATH);
	}
}
