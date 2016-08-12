package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=CapDrop.PATH)
public class CapDrop extends AbstractGenericList{

	protected final static String PATH = "/cap_drop";
	
	public CapDrop() {
		super(PATH);
	}
}
