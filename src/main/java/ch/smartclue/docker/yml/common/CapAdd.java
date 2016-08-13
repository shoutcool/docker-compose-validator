package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=CapAdd.PATH)
public class CapAdd extends AbstractGenericList{

	protected final static String PATH = "/cap_add";
	
	public CapAdd() {
		super(PATH);
	}
}