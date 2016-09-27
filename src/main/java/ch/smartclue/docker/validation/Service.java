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
	
	@SuppressWarnings("unchecked")
	public boolean hasSubNode(String node){
		String workNode = node;
		if (workNode.startsWith("/")){
			workNode = workNode.substring(1);
		}
		
		String[] elements = workNode.split("/");
		
		Map<String, Object> workStructure = structure;
		for(int i = 0; i<elements.length; i++){
			String element = elements[i];
			if (workStructure.containsKey(element)){
				if (i < elements.length -1){
					//Noch nicht das letzte Element --> Muss eine Map sein
					Object object = workStructure.get(element);
					if (object instanceof Map){
						workStructure = (Map<String, Object>) workStructure.get(element);
					}
				}
			}else{
				return false;
			}
		}

		return true;
	}

}
