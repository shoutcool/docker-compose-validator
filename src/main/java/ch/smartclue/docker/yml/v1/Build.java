package ch.smartclue.docker.yml.v1;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.DockerComposeVersion;

@YamlProperty(path = Build.PATH, version=DockerComposeVersion.V1)
public class Build extends AbstractGenericString {

	protected final static String PATH = "/build";

	public Build() {
		super(PATH);
	}
}
