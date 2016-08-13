package ch.smartclue.docker;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ch.smartclue.docker.DockerComposeValidator.DockerComposeVersion;
import ch.smartclue.docker.validation.YamlValidator;
import ch.smartclue.docker.yml.v1.ValidatorV1Impl;
import ch.smartclue.docker.yml.v2.ValidatorV2Impl;

class ValidatorFactory {

	public static Validator create(String content, Map<DockerComposeVersion, Map<String, YamlValidator<?>>> additionalValidators) throws DockerComposeValidationException {
		Scanner scanner = null;

		try {
			scanner = new Scanner(content);
			if (scanner.hasNextLine()) {
				String firstLine = scanner.nextLine();
				if (isVersion2(firstLine)){
					//Collect additional validators
					Map<String, YamlValidator<?>> v1Validators = new HashMap<String, YamlValidator<?>>();
					v1Validators.putAll(additionalValidators.get(DockerComposeVersion.V1));
					v1Validators.putAll(additionalValidators.get(DockerComposeVersion.ALL));
					
					return new ValidatorV1Impl(content, v1Validators);
				} else {
					//Collect additional validators
					Map<String, YamlValidator<?>> v2Validators = new HashMap<String, YamlValidator<?>>();
					v2Validators.putAll(additionalValidators.get(DockerComposeVersion.V2));
					v2Validators.putAll(additionalValidators.get(DockerComposeVersion.ALL));

					return new ValidatorV2Impl(content, v2Validators);
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
