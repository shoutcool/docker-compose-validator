package ch.repnik.docker.yml.v2;

import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.yml.common.AbstractGenericStringOrList;

@YamlProperty(path=Tmpfs.PATH)
public class Tmpfs extends AbstractGenericStringOrList{

	protected final static String PATH = "/tmpfs";
	
	public Tmpfs() {
		super(PATH);
	}
}
