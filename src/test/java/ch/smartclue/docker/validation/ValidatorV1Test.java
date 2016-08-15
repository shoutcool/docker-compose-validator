package ch.smartclue.docker.validation;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.smartclue.docker.exception.DockerComposeValidationException;

public class ValidatorV1Test {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private static ValidatorManager validatorManager = new ValidatorManager();
	
	@BeforeClass
	public static void initClass(){
		List<ValidatorInstance> instances = ReflectionUtil.createValidationInstancesFromPackageName("ch.smartclue.docker.yml.common");
		instances.addAll(ReflectionUtil.createValidationInstancesFromPackageName("ch.smartclue.docker.yml.v1"));
		for (ValidatorInstance instance : instances){
			validatorManager.addValidatorInstance(instance);
		}
	}
	
	@Test
	public void build_validString_successful() throws Exception {
		String content = "build: test";
		Validator testee = new ValidatorV1Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void build_emptyString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "build:";
		Validator testee = new ValidatorV1Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void build_withImage_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("not allowed to use 'build' and 'image' together");
		String content = "build: .\nimage: .";
		Validator testee = new ValidatorV1Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void dockerfile_missingBuild_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("'/build' must be specified");
		String content = "dockerfile: .";
		Validator testee = new ValidatorV1Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void dockerfile_withImage_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("not allowed to use 'dockerfile' and 'image' together");
		String content = "dockerfile: .\nimage: .";
		Validator testee = new ValidatorV1Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void capAdd_validList_successful() throws Exception {
		String content = "cap_add:\n   - ALL";
		Validator testee = new ValidatorV1Impl(content, validatorManager);
		testee.validate();
	}

	@Test
	public void capAdd_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "cap_add: .";
		Validator testee = new ValidatorV1Impl(content, validatorManager);
		testee.validate();
	}
	
	
	@Test
	public void customValidator_image_throwsException() throws Exception{
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be 'foo'");
		String content = "image: foo";
		
		DockerComposeValidator testee = new DockerComposeValidator();
		testee.addAdditionalValidatorsByClass(CustomImageValidator.class);
		testee.validate(content);
	}
	
	@Test
	public void customValidator_withoutAnnotation_throwsException() throws Exception{
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("needs to have the 'YamlProperty' annotation set");
		String content = "image: foo";
		
		DockerComposeValidator testee = new DockerComposeValidator();
		testee.addAdditionalValidatorsByClass(CustomImageValidatorWithoutAnnotation.class);
		testee.validate(content);
	}
}


