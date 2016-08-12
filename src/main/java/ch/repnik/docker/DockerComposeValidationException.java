package ch.repnik.docker;

public class DockerComposeValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public DockerComposeValidationException() {
		super();
	}

	public DockerComposeValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DockerComposeValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DockerComposeValidationException(String message) {
		super(message);
	}

	public DockerComposeValidationException(Throwable cause) {
		super(cause);
	}
}
