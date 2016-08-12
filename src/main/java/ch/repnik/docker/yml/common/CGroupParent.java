package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=CGroupParent.PATH)
public class CGroupParent extends AbstractGenericString{

	protected final static String PATH = "/cgroup_parent";
	
	public CGroupParent() {
		super(PATH);
	}
}
