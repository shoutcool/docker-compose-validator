package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=Devices.PATH)
public class Devices extends AbstractGenericList{

	protected final static String PATH = "/devices";
	
	public Devices() {
		super(PATH);
	}
}
