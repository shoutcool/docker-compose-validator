package ch.smartclue.docker.validation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.reader.StructureReader;
import ch.smartclue.docker.yml.generic.DockerComposeVersion;

@RunWith(Parameterized.class)
public class ParametrizedValidatorV1Test {

	@Parameter(0)
	public String content;

	@Parameter(1)
	public Class<? extends Exception> expectedException;
	
	@Parameter(2)
	public String expectedExceptionMsg;
	
	@Parameter(3)
	public int validatorExecutions;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private static DockerComposeValidator validator = new DockerComposeValidator();
	
	@Parameters(name = "{index}: {0} throws {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
        	{createNamedString("build"), null, null, 1},
        	{"foo:\n  build: .\n  image: .", DockerComposeValidationException.class, "not allowed to use 'build' and 'image' together", 1},
        	{"foo:\n  dockerfile: .", DockerComposeValidationException.class, "'/build' must be specified", 1},
        	{"foo:\n  dockerfile: .\n  image: .", DockerComposeValidationException.class, "not allowed to use 'dockerfile' and 'image' together", 1},
        	{createNamedList("cap_add"), null, null, 1},
            {createNamedString("log_driver"), null, null, 1},
            {createNamedMap("log_opt"), null, null, 1},
            {String.format("%s\n%s", createNamedString("net"), createNamedString("net")), null, null, 1}
        });
    }
	
	@Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testValidator() throws Exception {
	    //setup expected exception
	    if (expectedException != null) {
	        thrown.expect(expectedException);
	        thrown.expectMessage(expectedExceptionMsg);
	    }
	    
	    StructureReader structureReader = new StructureReader();
	    List<ValidatorInstance> instances = validator.getValidatorManager().getValidatorInstancesByVersion(DockerComposeVersion.ALL,DockerComposeVersion.V1);
	    
	    ValidationExecutor spiedExecutor = spy(new ValidationExecutor());
	    
	    Validator testee = new ValidatorV1Impl(instances, spiedExecutor, structureReader.readStructure(content));
	    testee.validate();
	    
	    if (expectedException == null) {
	    	ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
	    	verify(spiedExecutor, times(validatorExecutions)).executeValidators(captor.capture(), anyString(), anyObject());
	    		assertTrue(captor.getValue().size() > 0);
	    }
	}
	
	
	private static String createNamedList(String name){
		return String.format("foo:\n  %s:\n    - dummyListEntry", name);
	}
	
	private static String createNamedMap(String name){
		return String.format("foo:\n  %s:\n    dummyKey: dummyValue", name);
	}
	
	private static String createNamedString(String name){
		return String.format("foo:\n  %s: dummyString", name);
	}

}
