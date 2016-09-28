package ch.smartclue.docker.validation;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.exception.YamlParsingException;
import ch.smartclue.docker.reader.StructureReader;
import ch.smartclue.docker.yml.generic.DockerComposeVersion;

class ValidatorFactory {

	public static Validator create(String content, ValidatorManager validatorManager) throws DockerComposeValidationException {
		Scanner scanner = null;

		try {
			scanner = new Scanner(content);
			if (scanner.hasNextLine()) {
				StructureReader structureReader = new StructureReader();
				Map<String, Object> structure = structureReader.readStructure(content);
				
				if (isVersion2(structure)){
					List<ValidatorInstance> instances = validatorManager.getValidatorInstancesByVersion(DockerComposeVersion.ALL,DockerComposeVersion.V2);
					return new ValidatorV2Impl(instances, new ValidationExecutor(), structure);
				} else {
					List<ValidatorInstance> instances = validatorManager.getValidatorInstancesByVersion(DockerComposeVersion.ALL,	DockerComposeVersion.V1);
					return new ValidatorV1Impl(instances, new ValidationExecutor(), structure);
				}
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		throw new IllegalArgumentException("No Content in YAML File found!");
	}

	private static boolean isVersion2(Map<String, Object> structure) {
		
		if (structure.containsKey("/version")){
			String value = structure.get("/version").toString();
			if ("2".equals(value)){
				return true;
			}else{
				throw new YamlParsingException(value + " is no valid version!");
			}
		}
		
		return false;
	}
}
