package ch.smartclue.docker.validation;

import java.util.Scanner;

import ch.smartclue.docker.exception.DockerComposeValidationException;

class ValidatorFactory {

	public static Validator create(String content, ValidatorManager validatorManager) throws DockerComposeValidationException {
		Scanner scanner = null;

		try {
			scanner = new Scanner(content);
			if (scanner.hasNextLine()) {
				String firstLine = scanner.nextLine();
				
				if (isVersion2(firstLine)){
					return new ValidatorV2Impl(content, validatorManager);
				} else {
					return new ValidatorV1Impl(content, validatorManager);
				}
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		throw new IllegalArgumentException("No Content in YAML File found!");
	}

	private static boolean isVersion2(String firstLine) {
		return false;
	}

}
