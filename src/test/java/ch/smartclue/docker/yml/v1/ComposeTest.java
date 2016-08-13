package ch.smartclue.docker.yml.v1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.Validator;
import ch.smartclue.docker.validation.YamlValidator;

public class ComposeTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void build_validString_successful() throws Exception {
		String content = "build: test";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void build_emptyString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "build:";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void build_withImage_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("not allowed to use 'build' and 'image' together");
		String content = "build: .\nimage: .";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void dockerfile_missingBuild_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("'/build' must be specified");
		String content = "dockerfile: .";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void dockerfile_withImage_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("not allowed to use 'dockerfile' and 'image' together");
		String content = "dockerfile: .\nimage: .";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void capAdd_validList_successful() throws Exception {
		String content = "cap_add:\n   - ALL";
		Validator testee = createTestee(content);
		testee.validate();
	}

	@Test
	public void capAdd_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "cap_add: .";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void customValidator_image_throwsException() throws Exception{
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be 'foo'");
		String content = "image: foo";
		
		Map<String, YamlValidator<?>> customValidators = new HashMap<String, YamlValidator<?>>();
		customValidators.put(CustomImageValidator.PATH, new CustomImageValidator());
		
		Validator testee = new ValidatorV1Impl(content, customValidators);
		testee.validate();
	}
	
	private Validator createTestee(String content) throws DockerComposeValidationException{
		return new ValidatorV1Impl(content, Collections.<String, YamlValidator<?>>emptyMap());
	}
	
}


