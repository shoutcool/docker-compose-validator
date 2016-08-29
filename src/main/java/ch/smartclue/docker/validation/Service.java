package ch.smartclue.docker.validation;

import java.util.Map;

public class Service {

	private Map<String, Object> structure;
	private String path;
	
	public Service(String path, Map<String, Object> structure){
		this.path = path;
		this.structure = structure;
	}

	public String getPath() {
		return path;
	}

	public Map<String, Object> getStructure() {
		return structure;
	}
	
	public boolean hasSubNode(String node){
		return structure.containsKey(node.substring(1));
	}

}
