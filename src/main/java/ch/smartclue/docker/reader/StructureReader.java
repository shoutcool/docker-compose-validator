package ch.smartclue.docker.reader;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

public class StructureReader {

	private Map<String, Object> entries = new TreeMap<String, Object>();

	@SuppressWarnings("unchecked")
	public Map<String, Object> readStructure(String content) throws YamlException {
		YamlReader reader = new YamlReader(content);
		return readSubStructure((Map<String, Object>) reader.read(), entries, "");
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
