package ch.smartclue.docker.yml.v2;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericStringOrList;

@YamlProperty(path=Tmpfs.PATH)
public class Tmpfs extends AbstractGenericStringOrList{

	protected final static String PATH = "/tmpfs";
	
	public Tmpfs() {
		super(PATH);
	}
}
