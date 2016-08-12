package ch.repnik.docker.yml.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlValidator;

public abstract class AbstractGenericMapOrList implements YamlValidator<Object> {

	private String path;
	
	public AbstractGenericMapOrList(String path){
		this.path = path;
	}
	
	@SuppressWarnings("unchecked")
	public void validate(Object value) throws DockerComposeValidationException {
		if (value instanceof List){
			List<String> list = (List<String>) value;
			if (list.isEmpty()){
				throw new DockerComposeValidationException(String.format("List '%s' must not be empty", path));
			}
		}else if (value instanceof Map){
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			if (map.isEmpty()){
				throw new DockerComposeValidationException(String.format("Map '%s' must not be empty", path));
			} 
		}else{
			throw new DockerComposeValidationException(String.format("'%s' must be either from type List or Map", path));
		}
	}

}
