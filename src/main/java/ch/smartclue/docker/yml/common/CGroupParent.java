package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=CGroupParent.PATH, version=DockerComposeVersion.ALL)
public class CGroupParent extends AbstractGenericString{

	protected final static String PATH = "/cgroup_parent";
	
	public CGroupParent() {
		super(PATH);
	}
}
