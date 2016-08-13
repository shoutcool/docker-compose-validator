package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericMapOrList;

@YamlProperty(path=Args.PATH)
public class Args extends AbstractGenericMapOrList{

	protected final static String PATH = "/build/args";
	
	public Args() {
		super(PATH);
	}
}
