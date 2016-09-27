package ch.smartclue.docker.reader;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import ch.smartclue.docker.exception.YamlParsingException;

public class StructureReader {

	private Map<String, Object> entries = new TreeMap<String, Object>();
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> readStructure(String content){
		YamlReader reader = new YamlReader(content);
		
		try {
			Map<String, Object> structure = (Map<String, Object>) reader.read();
			return readSubStructure(structure, entries, "");
		} catch (YamlException e) {
			throw new YamlParsingException("Failure while parsing yaml content", e);
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> readSubStructure(Map<String, Object> input, Map<String, Object> map, String parent) {
		for (Entry<String, Object> item : input.entrySet()) {
			if (item.getValue() instanceof Map) {
				map.put(parent + "/" + item.getKey(), item.getValue());
				readSubStructure((Map<String, Object>) item.getValue(), map, parent + "/" + item.getKey());
			} else {
				map.put(parent + "/" + item.getKey(), item.getValue());
			}
		}
		return map;
	}

}
