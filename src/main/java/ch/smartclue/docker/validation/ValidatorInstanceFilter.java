package ch.smartclue.docker.validation;

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import ch.smartclue.docker.yml.common.DockerComposeVersion;

class ValidatorInstanceFilter {

	public static List<ValidatorInstance> filterValidatorsByVersion(List<ValidatorInstance> validators, DockerComposeVersion[] versions){
		 return Lists.newArrayList(Iterables.filter(validators, createVersionPredicate(versions)));
	}
	
	public static List<ValidatorInstance> filterValidatorsByPath(List<ValidatorInstance> validators, String path){
		 return Lists.newArrayList(Iterables.filter(validators, createPathPredicate(path)));
	}
	
	public static List<ValidatorInstance> filterValidatorsByContainerNode(List<ValidatorInstance> validators, boolean isContainerNode, String key){
		 return Lists.newArrayList(Iterables.filter(validators, createContainerNodePredicate(isContainerNode, key)));
	}
	
	private static Predicate<ValidatorInstance> createVersionPredicate(final DockerComposeVersion[] versions) {
	    return new Predicate<ValidatorInstance>() {
	        public boolean apply(ValidatorInstance input) {
	        	for(DockerComposeVersion version : versions){
	        		if (input.getVersion().equals(version)){
	        			return true;
	        		}
	        	}
	            return false;
	        }
	    };
	}
	
	private static Predicate<ValidatorInstance> createPathPredicate(final String path) {
	    return new Predicate<ValidatorInstance>() {
	        public boolean apply(ValidatorInstance input) {
	            return input.getPath().equals(path);
	        }
	    };
	}

	private static Predicate<ValidatorInstance> createContainerNodePredicate(final boolean isContainerNode, final String key) {
	    return new Predicate<ValidatorInstance>() {
	        public boolean apply(ValidatorInstance input) {
	            return input.isContainerNode() == isContainerNode &&
	            		input.getPath().replace("${container}", getContainerName(key)).equals(key);
	        }
	    };
	}

	private static String getContainerName(String key) {
		int endIndex = key.indexOf("/", 1);
		if (endIndex != -1){
			return key.substring(1, endIndex);			
		}else{
			return key.substring(1);
		}
	}
}
