package ch.repnik.docker.yml.v2;

import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.yml.common.AbstractGenericString;

@YamlProperty(path=Dockerfile.PATH)
public class Dockerfile extends AbstractGenericString{

	protected final static String PATH = "/build/dockerfile";
	
	public Dockerfile() {
		super(PATH);
	}
}
