package ch.smartclue.docker.validation;

public class ServiceNameHelper {
	
	public static String getServiceNameFromPath(String path){
		if (path.startsWith("/services/")){
			return String.format("%s/%s", getPathPart(path, 1), getPathPart(path, 2)); 
		}else {
			return getPathPart(path, 1);
		}
	}
	
	private static String getPathPart(String path, int partNumber){
		String[] parts = path.split("/");
		return parts[partNumber];
	}
	
}
