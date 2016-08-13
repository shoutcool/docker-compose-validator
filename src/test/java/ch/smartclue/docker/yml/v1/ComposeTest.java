package ch.smartclue.docker.yml.v1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.Validator;

public class ComposeTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void build_validString_successful() throws Exception {
		String content = "build: test";
		Validator testee = new ValidatorV1Impl(content);
		testee.validate();
	}
	
	@Test
	public void build_emptyString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "build:";
		Validator testee = new ValidatorV1Impl(content);
		testee.validate();
	}
	
	@Test
	public void build_withImage_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("not allowed to use 'build' and 'image' together");
		String content = "build: .\nimage: .";
		Validator testee = new ValidatorV1Impl(content);
		testee.validate();
	}
	
	@Test
	public void dockerfile_missingBuild_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("'/build' must be specified");
		String content = "dockerfile: .";
		Validator testee = new ValidatorV1Impl(content);
		testee.validate();
	}
	
	@Test
	public void dockerfile_withImage_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("not allowed to use 'dockerfile' and 'image' together");
		String content = "dockerfile: .\nimage: .";
		Validator testee = new ValidatorV1Impl(content);
		testee.validate();
	}
	
	@Test
	public void capAdd_validList_successful() throws Exception {
		String content = "cap_add:\n   - ALL";
		Validator testee = new ValidatorV1Impl(content);
		testee.validate();
	}

	@Test
	public void capAdd_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "cap_add: .";
		Validator testee = new ValidatorV1Impl(content);
		testee.validate();
	}
}

