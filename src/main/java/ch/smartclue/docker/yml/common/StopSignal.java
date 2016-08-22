package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=StopSignal.PATH, version=DockerComposeVersion.ALL)
public class StopSignal extends AbstractGenericString{

	protected final static String PATH = "/stop_signal";
	
	public StopSignal() {
		super(PATH);
	}
}
