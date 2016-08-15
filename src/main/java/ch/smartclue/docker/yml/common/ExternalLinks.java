package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=ExternalLinks.PATH, version=DockerComposeVersion.ALL)
public class ExternalLinks extends AbstractGenericList{

	protected final static String PATH = "/external_links";
	
	public ExternalLinks() {
		super(PATH);
	}
}
