package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=EnvFile.PATH)
public class EnvFile extends AbstractGenericStringOrList{

	protected final static String PATH = "/env_file";
	
	public EnvFile() {
		super(PATH);
	}

	
	
}
