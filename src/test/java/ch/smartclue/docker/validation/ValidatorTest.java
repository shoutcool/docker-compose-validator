package ch.smartclue.docker.validation;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ValidatorTest{

	@Test
	public void validatorV1Test() throws Exception{
		File file = new File(ValidatorTest.class.getResource("/docker-compose-v1.yml").toURI());
		String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		DockerComposeValidator testee = new DockerComposeValidator();
		testee.validate(content);
	}

	@Test
	public void validatorV2Test() throws Exception{
		File file = new File(ValidatorTest.class.getResource("/docker-compose-v2.yml").toURI());
		String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		DockerComposeValidator testee = new DockerComposeValidator();
		testee.validate(content);
	}
}
