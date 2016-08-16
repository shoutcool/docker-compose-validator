package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Driver.PATH, version=DockerComposeVersion.V2)
public class Driver extends AbstractGenericString{

	protected final static String PATH = "/logging/driver";
	
	public Driver() {
		super(PATH);
	}
}
