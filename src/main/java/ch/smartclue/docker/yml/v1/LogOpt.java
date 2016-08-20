package ch.smartclue.docker.yml.v1;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericMap;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=LogOpt.PATH, version=DockerComposeVersion.V1)
public class LogOpt extends AbstractGenericMap{

	protected final static String PATH = "/log_opt";
	
	public LogOpt() {
		super(PATH);
	}
}
