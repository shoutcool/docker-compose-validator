package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericMapOrList;

@YamlProperty(path=Environment.PATH, version=DockerComposeVersion.ALL)
public class Environment extends AbstractGenericMapOrList{

	protected final static String PATH = "/environment";
	
	public Environment() {
		super(PATH);
	}
}
