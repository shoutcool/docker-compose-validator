package ch.smartclue.docker.exception;

public class YamlParsingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public YamlParsingException(String message) {
		super(message);
	}
	
	public YamlParsingException(String message, Throwable cause) {
		super(message, cause);
	}
}
