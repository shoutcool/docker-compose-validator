package ch.smartclue.docker.yml.v1;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Dockerfile.PATH, version=DockerComposeVersion.V1)
public class Dockerfile extends AbstractGenericString{

	protected final static String PATH = "/dockerfile";
	
	public Dockerfile() {
		super(PATH);
	}
}
