package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=CapAdd.PATH)
public class CapAdd extends AbstractGenericList{

	protected final static String PATH = "/cap_add";
	
	public CapAdd() {
		super(PATH);
	}
}
