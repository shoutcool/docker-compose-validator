package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Devices.PATH)
public class Devices extends AbstractGenericList{

	protected final static String PATH = "/devices";
	
	public Devices() {
		super(PATH);
	}
}
