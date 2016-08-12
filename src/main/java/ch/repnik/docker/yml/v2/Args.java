package ch.repnik.docker.yml.v2;

import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.yml.common.AbstractGenericMapOrList;

@YamlProperty(path=Args.PATH)
public class Args extends AbstractGenericMapOrList{

	protected final static String PATH = "/build/args";
	
	public Args() {
		super(PATH);
	}
}
