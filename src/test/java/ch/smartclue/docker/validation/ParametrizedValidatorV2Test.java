package ch.smartclue.docker.validation;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
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
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private static ValidatorManager validatorManager = new ValidatorManager();
	
	@BeforeClass
	public static void initClass(){
		List<ValidatorInstance> instances = ReflectionUtil.createValidationInstancesFromPackageName("ch.smartclue.docker.yml.common");
		instances.addAll(ReflectionUtil.createValidationInstancesFromPackageName("ch.smartclue.docker.yml.v2"));
		for (ValidatorInstance instance : instances){
			validatorManager.addValidatorInstance(instance);
		}
	}
	
	@Parameters(name = "{index}: {0} throws {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
                 {createNamedString("build"), null, null}, 
                 {"build:", DockerComposeValidationException.class, "must not be empty"},
                 {"build:\n   context: .", null, null},
                 {"build:\n   context:", DockerComposeValidationException.class, "must not be empty"},
                 {"build:\n   args:\n      buildno: 1", DockerComposeValidationException.class, "/context' is missing"},
                 {"build:\n   dockerfile: .", DockerComposeValidationException.class, "'/build/context' must be specified"},
                 {"build:\n   context: .\n   args:\n      - buildno=1", null, null},
                 {"build:\n   context: .\n   args:\n      buildno: 1", null, null},
                 {createNamedList("cap_add"), null, null},
                 {createNamedList("cap_drop"), null, null},
                 {createNamedString("command"), null, null},
                 {createNamedList("command"), null, null},
                 {createNamedString("cgroup_parent"), null, null},
                 {createNamedString("container_name"), null, null},
                 {createNamedList("devices"), null, null},
                 {createNamedString("dns"), null, null},
                 {createNamedList("dns"), null, null},
                 {createNamedString("dns_search"), null, null},
                 {createNamedList("dns_search"), null, null},
                 {createNamedString("tmpfs"), null, null},
                 {createNamedList("tmpfs"), null, null},
                 {createNamedString("entrypoint"), null, null},
                 {createNamedList("entrypoint"), null, null},
                 {createNamedString("env_file"), null, null},
                 {createNamedList("env_file"), null, null},
                 {createNamedMap("environment"), null, null},
                 {createNamedList("environment"), null, null},
                 {createNamedList("expose"), null, null},
                 {"extends:\n  service: common", null, null},
                 {"extends:\n  file: common.yml", DockerComposeValidationException.class, "/service' is missing"},
                 {createNamedString("extends"), DockerComposeValidationException.class, "must be from type Map"},
                 {createNamedList("content"), null, null},
                 {createNamedList("extra_hosts"), null, null},
                 {createNamedString("image"), null, null},
                 {createNamedMap("labels"), null, null},
                 {"web:\n   links:\n      - db", null, null},
                 {"build:\n   context: .\n   links:\n      foo: bar", null, null},
                 {createNamedMap("logging"), null, null},
                 {"logging:\n  driver: foo", null, null},
                 {"logging:\n  options:\n      foo: bar", null, null},
                 {String.format("%s\n%s", createNamedString("network_mode"), createNamedString("network_mode")), null, null}

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
	    
	    Validator testee = new ValidatorV2Impl(content, validatorManager);
	    AbstractValidatorImpl spiedTestee = Mockito.spy((AbstractValidatorImpl)testee);
	    spiedTestee.validate();
	    
	    if (expectedException != null) {
	    	ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
	    	Mockito.verify(spiedTestee).executeValidators(captor.capture(), Mockito.anyObject());
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
