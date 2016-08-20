package ch.smartclue.docker.yml.v1;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=LogDriver.PATH, version=DockerComposeVersion.ALL)
public class LogDriver extends AbstractGenericString{

	protected final static String PATH = "/log_driver";
	
	public LogDriver() {
		super(PATH);
	}
}
