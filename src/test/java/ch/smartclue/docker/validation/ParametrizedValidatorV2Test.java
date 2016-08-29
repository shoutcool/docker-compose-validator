package ch.smartclue.docker.validation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

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
import org.mockito.Mockito;

import ch.smartclue.docker.exception.DockerComposeValidationException;

@RunWith(Parameterized.class)
public class ParametrizedValidatorV2Test {

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
                 {"services:\n  foo:\n    build:", DockerComposeValidationException.class, "must not be empty", 1},
                 {"services:\n  foo:\n    build:\n      context: .", null, null, 1},
                 {"services:\n  foo:\n    build:\n      context:", DockerComposeValidationException.class, "must not be empty", 1},
                 {"services:\n  foo:\n    build:\n      args:\n        buildno: 1", DockerComposeValidationException.class, "/context' is missing", 1},
                 {"services:\n  foo:\n    build:\n      dockerfile: .", DockerComposeValidationException.class, "/build/context' is missing", 1},
                 {"services:\n  foo:\n    build:\n      context: .\n      args:\n        - buildno=1", null, null, 1},
                 {"services:\n  foo:\n    build:\n      context: .\n      args:\n        buildno: 1", null, null, 1},
                 {createNamedList("cap_add"), null, null, 1},
                 {createNamedList("cap_drop"), null, null, 1},
                 {createNamedString("command"), null, null, 1},
                 {createNamedList("command"), null, null, 1},
                 {createNamedString("cgroup_parent"), null, null, 1},
                 {createNamedString("container_name"), null, null, 1},
                 {createNamedList("devices"), null, null, 1},
                 {createNamedString("dns"), null, null, 1},
                 {createNamedList("dns"), null, null, 1},
                 {createNamedString("dns_search"), null, null, 1},
                 {createNamedList("dns_search"), null, null, 1},
                 {createNamedString("tmpfs"), null, null, 1},
                 {createNamedList("tmpfs"), null, null, 1},
                 {createNamedString("entrypoint"), null, null, 1},
                 {createNamedList("entrypoint"), null, null, 1},
                 {createNamedString("env_file"), null, null, 1},
                 {createNamedList("env_file"), null, null, 1},
                 {createNamedMap("environment"), null, null, 1},
                 {createNamedList("environment"), null, null, 1},
                 {createNamedList("expose"), null, null, 1},
                 {"services:\n  foo:\n    extends:\n      service: common", null, null, 1},
                 {"services:\n  foo:\n    extends:\n      file: common.yml", DockerComposeValidationException.class, "/service' is missing", 1},
                 {createNamedString("extends"), DockerComposeValidationException.class, "must be from type Map", 1},
                 {createNamedList("content"), null, null, 1},
                 {createNamedList("extra_hosts"), null, null, 1},
                 {createNamedString("image"), null, null, 1},
                 {createNamedMap("labels"), null, null, 1},
                 {"services:\n  foo:\n    links:\n      - db", null, null, 1},
                 {"services:\n  foo:\n    build:\n      context: .\n      links:\n      foo: bar", null, null, 1},
                 {createNamedMap("logging"), null, null, 1},
                 {"services:\n  foo:\n    logging:\n      driver: foo", null, null, 1},
                 {"services:\n  foo:\n    logging:\n      options:\n        foo: bar", null, null, 1},
                 {String.format("services:\n  foo:\n    %s\n    %s", createNamedString("network_mode"), createNamedString("network_mode")), null, null, 1},
                 //TODO: Networks (as sub node of the services)
                 //TODO: Aliases (as sub node of the networks)
                 //TODO: ipv4_address, ipv6_address (as sub node of the services)
                 {createNamedString("pid"), null, null, 1},
                 {createNamedList("ports"), null, null, 1},
                 {createNamedList("security_opt"), null, null, 1},
                 {createNamedString("stop_signal"), null, null, 1},
                 {createNamedMap("ulimits"), null, null, 1}
                 
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
	    
	    Validator testee = new ValidatorV2Impl(content, validator.getValidatorManager());
	    AbstractValidatorImpl spiedTestee = Mockito.spy((AbstractValidatorImpl)testee);
	    spiedTestee.validate();
	    
	    if (expectedException != null) {
	    	ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
	    	Mockito.verify(spiedTestee, Mockito.times(validatorExecutions)).executeValidators(captor.capture(), anyString(), anyObject());
	    	assertTrue(captor.getValue().size() > 0);
	    }
	}
		
	
	
	private static String createNamedList(String name){
		return String.format("services:\n  foo:\n    %s:\n      - dummyListEntry", name);
	}
	
	private static String createNamedMap(String name){
		return String.format("services:\n  foo:\n    %s:\n      dummyKey: dummyValue", name);
	}
	
	private static String createNamedString(String name){
		return String.format("services:\n  foo:\n    %s: dummyString", name);
	}

}
