package ch.repnik.docker.yml.v2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.repnik.docker.DockerComposeValidationException;
import ch.repnik.docker.Validator;

public class ComposeTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void build_validString_successful() throws Exception {
		String content = "build: test";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void build_emptyString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "build:";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void build_validObject_successful() throws Exception {
		String content = "build:\n   context: .";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void context_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "build:\n   context:";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void context_missing_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("/context' is missing");
		String content = "build:\n   args:\n   buildno: 1";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}

	@Test
	public void dockerfile_missingContext_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("'/build/context' must be specified");
		String content = "build:\n   dockerfile: .";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void args_asList_successful() throws Exception {
		String content = "build:\n  context: .\n  args:\n    - buildno=1\n    - password=secret";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void args_asMap_successful() throws Exception {
		String content = "build:\n  context: .\n  args:\n    buildno: 1\n    password: secret";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void args_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		String content = "build:\n  context: .\n  args: blub";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void capAdd_validList_successful() throws Exception {
		String content = "cap_add:\n   - ALL";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}

	@Test
	public void capAdd_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "cap_add: .";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void capDrop_validList_successful() throws Exception {
		String content = "cap_drop:\n   - ALL";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}

	@Test
	public void capDrop_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "cap_drop: .";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
}
