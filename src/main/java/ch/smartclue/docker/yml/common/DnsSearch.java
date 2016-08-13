package ch.smartclue.docker.yml.common;

import ch.smartclue.docker.validation.YamlProperty;

@YamlProperty(path=DnsSearch.PATH)
public class DnsSearch extends AbstractGenericStringOrList{

	protected final static String PATH = "/dns_search";
	
	public DnsSearch() {
		super(PATH);
	}
}
