package ch.smartclue.docker.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.yml.generic.DockerComposeVersion;

public class ValidatorV1Test {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void customValidator_image_throwsException() throws Exception{
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be 'foo'");
		String content = "foo:\n  image: foo";
		
		DockerComposeValidator testee = new DockerComposeValidator();
		testee.addCustomValidator("/foo/image", DockerComposeVersion.V1, new CustomImageValidator());
		testee.validate(content);
	}
	
}


