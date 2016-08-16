package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericMap;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Options.PATH, version=DockerComposeVersion.V2)
public class Options extends AbstractGenericMap{

	protected final static String PATH = "/logging/options";
	
	public Options() {
		super(PATH);
	}
}
