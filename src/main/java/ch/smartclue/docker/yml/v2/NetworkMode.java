package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=NetworkMode.PATH, version=DockerComposeVersion.V2)
public class NetworkMode extends AbstractGenericString{

	protected final static String PATH = "/net";
	
	public NetworkMode() {
		super(PATH);
	}
}
