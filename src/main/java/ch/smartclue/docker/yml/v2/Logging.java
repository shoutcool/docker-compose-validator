package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericMap;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Logging.PATH, version=DockerComposeVersion.V2)
public class Logging extends AbstractGenericMap{

	protected final static String PATH = "/logging";
	
	public Logging() {
		super(PATH);
	}
}
