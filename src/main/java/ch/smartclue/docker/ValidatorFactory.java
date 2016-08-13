package ch.smartclue.docker;

import java.util.Scanner;

import ch.smartclue.docker.yml.v1.ValidatorV1Impl;
import ch.smartclue.docker.yml.v2.ValidatorV2Impl;

class ValidatorFactory {

	public static Validator create(String content) throws DockerComposeValidationException {
		Scanner scanner = null;

		try {
			scanner = new Scanner(content);
			if (scanner.hasNextLine()) {
				String firstLine = scanner.nextLine();
				if (isVersion2(firstLine)){
					return new ValidatorV1Impl(content);
				} else {
					return new ValidatorV2Impl(content);
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
