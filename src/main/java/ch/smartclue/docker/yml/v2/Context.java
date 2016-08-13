package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;

@YamlProperty(path=Context.PATH)
public class Context extends AbstractGenericString{

	protected final static String PATH = "/build/context";
	
	public Context() {
		super(PATH);
	}
}
