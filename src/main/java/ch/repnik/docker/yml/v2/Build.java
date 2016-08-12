package ch.repnik.docker.yml.v2;

import java.util.HashMap;
import java.util.Map;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.validation.YamlValidator;

@YamlProperty(path=Build.PATH)
public class Build implements YamlValidator<Object>{
	
	protected final static String PATH = "/build";
	
	public void validate(Object value) throws DockerComposeValidationException {
		
		if (value instanceof String){
			if (String.valueOf(value).trim().isEmpty()){
				throw new DockerComposeValidationException(String.format("String '%s' must not be empty", PATH));
			}
		}else if (value instanceof Map){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			if (map.isEmpty()){
				throw new DockerComposeValidationException(String.format("Map '%s' must not be empty", PATH));
			} else if (!map.containsKey("context")){
				throw new DockerComposeValidationException(String.format("String '%s/context' is missing", PATH));
			}
		}else{
			throw new DockerComposeValidationException(String.format("'%s' must be either from type String or Map", PATH));
		}
	}
}
