package ch.smartclue.docker.validation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;

import ch.smartclue.docker.exception.YamlParsingException;
import ch.smartclue.docker.yml.generic.DockerComposeVersion;

public class ValidatorFactoryTest {

	@Test
	public void create_version2_returnsV2Impl() throws Exception {
		ValidatorManager validatorManager = mock(ValidatorManager.class);
		String content = "version: '2'\nfoo:";
		
		Validator validator = ValidatorFactory.create(content, validatorManager);
		
		assertTrue(validator instanceof ValidatorV2Impl);
		verify(validatorManager).getValidatorInstancesByVersion(DockerComposeVersion.ALL, DockerComposeVersion.V2);
	}
	
	@Test
	public void create_version2WithDoubleQuotes_returnsV2Impl() throws Exception {
		ValidatorManager validatorManager = mock(ValidatorManager.class);
		String content = "version: \"2\"\nfoo:";
		
		Validator validator = ValidatorFactory.create(content, validatorManager);
		
		assertTrue(validator instanceof ValidatorV2Impl);
		verify(validatorManager).getValidatorInstancesByVersion(DockerComposeVersion.ALL, DockerComposeVersion.V2);
	}
	
	@Test
	public void create_version2WithoutQuotes_returnsV2Impl() throws Exception {
		ValidatorManager validatorManager = mock(ValidatorManager.class);
		String content = "version: 2\nfoo:";
		
		Validator validator = ValidatorFactory.create(content, validatorManager);
		
		assertTrue(validator instanceof ValidatorV2Impl);
		verify(validatorManager).getValidatorInstancesByVersion(DockerComposeVersion.ALL, DockerComposeVersion.V2);
	}
	
	@Test
	public void create_withoutVersion_returnsV1Impl() throws Exception {
		ValidatorManager validatorManager = mock(ValidatorManager.class);
		String content = "foo:\n build: bla";
		
		Validator validator = ValidatorFactory.create(content, validatorManager);
		
		assertTrue(validator instanceof ValidatorV1Impl);
		verify(validatorManager).getValidatorInstancesByVersion(DockerComposeVersion.ALL, DockerComposeVersion.V1);
	}
	
	@Test(expected=YamlParsingException.class)
	public void create_version3_returnsV1Impl() throws Exception {
		ValidatorManager validatorManager = mock(ValidatorManager.class);
		String content = "version: 3\nfoo:";
		
		ValidatorFactory.create(content, validatorManager);
		verifyZeroInteractions(validatorManager);
	}
}
