package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=ExtraHosts.PATH, version=DockerComposeVersion.ALL)
public class ExtraHosts extends AbstractGenericList{

	protected final static String PATH = "/extra_hosts";
	
	public ExtraHosts() {
		super(PATH);
	}
}
