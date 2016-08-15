package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericMapOrList;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path=Args.PATH, version=DockerComposeVersion.V2)
public class Args extends AbstractGenericMapOrList{

	protected final static String PATH = "/build/args";
	
	public Args() {
		super(PATH);
	}
}
