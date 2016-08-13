package ch.smartclue.docker.yml.v1;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericString;

@YamlProperty(path = Build.PATH)
public class Build extends AbstractGenericString {

	protected final static String PATH = "/build";

	public Build() {
		super(PATH);
	}
}
