package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Dockerfile.PATH, version=DockerComposeVersion.V2)
public class Dockerfile extends AbstractGenericString{

	protected final static String PATH = "/build/dockerfile";
	
	public Dockerfile() {
		super(PATH);
	}
}
