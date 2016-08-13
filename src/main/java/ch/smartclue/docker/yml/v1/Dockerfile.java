package ch.smartclue.docker.yml.v1;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;

@YamlProperty(path=Dockerfile.PATH)
public class Dockerfile extends AbstractGenericString{

	protected final static String PATH = "/dockerfile";
	
	public Dockerfile() {
		super(PATH);
	}
}
