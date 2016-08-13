package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;
import ch.smartclue.docker.yml.common.AbstractGenericMapOrList;

@YamlProperty(path=Labels.PATH)
public class Labels extends AbstractGenericMapOrList{

	protected final static String PATH = "/labels";
	
	public Labels() {
		super(PATH);
	}
}
