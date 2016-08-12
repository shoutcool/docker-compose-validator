package ch.repnik.docker.yml.v2;

import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.yml.common.AbstractGenericString;

@YamlProperty(path=Context.PATH)
public class Context extends AbstractGenericString{

	protected final static String PATH = "/build/context";
	
	public Context() {
		super(PATH);
	}
}
