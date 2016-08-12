package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.yml.common.AbstractGenericMapOrList;

@YamlProperty(path=Environment.PATH)
public class Environment extends AbstractGenericMapOrList{

	protected final static String PATH = "/environment";
	
	public Environment() {
		super(PATH);
	}
}
