package ch.smartclue.docker.validation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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

@RunWith(Parameterized.class)
public class ParametrizedValidatorV1Test {

	@Parameter(0)
	public String content;

	@Parameter(1)
	public Class<? extends Exception> expectedException;
	
	@Parameter(2)
	public String expectedExceptionMsg;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private static DockerComposeValidator validator = new DockerComposeValidator();
	
	@Parameters(name = "{index}: {0} throws {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
        	{createNamedString("build"), null, null},
        	{"build: .\nimage: .", DockerComposeValidationException.class, "not allowed to use 'build' and 'image' together"},
        	{"dockerfile: .", DockerComposeValidationException.class, "'/build' must be specified"},
        	{"dockerfile: .\nimage: .", DockerComposeValidationException.class, "not allowed to use 'dockerfile' and 'image' together"},
        	{createNamedList("cap_add"), null, null},
            {createNamedString("log_driver"), null, null},
            {createNamedMap("log_opt"), null, null},
            {String.format("%s\n%s", createNamedString("net"), createNamedString("net")), null, null}
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
	    
	    Validator testee = new ValidatorV1Impl(content, validator.getValidatorManager());
	    AbstractValidatorImpl spiedTestee = spy((AbstractValidatorImpl)testee);
	    spiedTestee.validate();
	    
	    if (expectedException != null) {
	    	ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
	    	verify(spiedTestee).executeValidators(captor.capture(), anyString(), anyObject());
	    		assertTrue(captor.getValue().size() > 0);
	    }
	}
	
	
	private static String createNamedList(String name){
		return String.format("%s:\n   - dummyListEntry", name);
	}
	
	private static String createNamedMap(String name){
		return String.format("%s:\n   dummyKey: dummyValue", name);
	}
	
	private static String createNamedString(String name){
		return String.format("%s: dummyString", name);
	}

}
