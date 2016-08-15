package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericStringOrList;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Tmpfs.PATH, version=DockerComposeVersion.V2)
public class Tmpfs extends AbstractGenericStringOrList{

	protected final static String PATH = "/tmpfs";
	
	public Tmpfs() {
		super(PATH);
	}
}
