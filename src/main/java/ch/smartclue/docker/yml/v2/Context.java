package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Context.PATH, version=DockerComposeVersion.V2)
public class Context extends AbstractGenericString{

	protected final static String PATH = "/build/context";
	
	public Context() {
		super(PATH);
	}
}
