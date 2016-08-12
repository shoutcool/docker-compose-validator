package ch.repnik.docker.yml.common;

import ch.repnik.docker.validation.YamlProperty;

@YamlProperty(path=DnsSearch.PATH)
public class DnsSearch extends AbstractGenericStringOrList{

	protected final static String PATH = "/dns_search";
	
	public DnsSearch() {
		super(PATH);
	}
}
