package ch.repnik.docker.yml.v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.validation.YamlProperty;
import ch.repnik.docker.validation.YamlValidator;

@YamlProperty(path=Args.PATH)
public class Args implements YamlValidator<Object>{
	
	public final static String PATH = "/build/args";
	
	@SuppressWarnings("unchecked")
	public void validate(Object value) throws DockerComposeValidationException {
		
		if (value instanceof List){
			List<String> list = (List<String>) value;
			if (list.isEmpty()){
				//TODO: Is this really a problem?
				throw new DockerComposeValidationException(String.format("List '%s' must not be empty", PATH));
			}
		}else if (value instanceof Map){
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			if (map.isEmpty()){
				throw new DockerComposeValidationException(String.format("Map '%s' must not be empty", PATH));
			} 
		}else{
			throw new DockerComposeValidationException(String.format("'%s' must be either from type List or Map", PATH));
		}
	}
}
