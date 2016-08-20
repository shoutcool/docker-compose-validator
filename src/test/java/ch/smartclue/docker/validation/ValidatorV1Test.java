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


