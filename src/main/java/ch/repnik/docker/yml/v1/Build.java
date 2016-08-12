package ch.repnik.docker.yml.v1;

import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.yml.common.AbstractGenericString;

@YamlProperty(path = Build.PATH)
public class Build extends AbstractGenericString {

	protected final static String PATH = "/build";

	public Build() {
		super(PATH);
	}
}
