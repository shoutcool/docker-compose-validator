package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=Image.PATH)
public class Image extends AbstractGenericList{

	protected final static String PATH = "/extra_hosts";
	
	public Image() {
		super(PATH);
	}
}
