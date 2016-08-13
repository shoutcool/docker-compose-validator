package ch.smartclue.docker.yml.v2;

import java.util.Collections;

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
		Validator testee = createTestee(createNamedString("build"));
		testee.validate();
	}
	
	@Test
	public void build_emptyString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		//TODO: Is this really a problem?
		String content = "build:";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void build_validObject_successful() throws Exception {
		String content = "build:\n   context: .";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void context_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "build:\n   context:";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void context_missing_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("/context' is missing");
		String content = "build:\n   args:\n      buildno: 1";
		Validator testee = createTestee(content);
		testee.validate();
	}

	@Test
	public void dockerfile_missingContext_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("'/build/context' must be specified");
		String content = "build:\n   dockerfile: .";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void args_asList_successful() throws Exception {
		String content = "build:\n  context: .\n  args:\n    - buildno=1\n    - password=secret";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void args_asMap_successful() throws Exception {
		String content = "build:\n  context: .\n  args:\n    buildno: 1\n    password: secret";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void args_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		String content = "build:\n  context: .\n  args: blub";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void capAdd_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("cap_add"));
		testee.validate();
	}

	@Test
	public void capAdd_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = createTestee(createNamedString("cap_add"));
		testee.validate();
	}
	
	@Test
	public void capDrop_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("cap_drop"));
		testee.validate();
	}

	@Test
	public void capDrop_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = createTestee(createNamedString("cap_drop"));
		testee.validate();
	}
	
	@Test
	public void command_validString_successful() throws Exception {
		Validator testee = createTestee(createNamedString("command"));
		testee.validate();
	}
	
	@Test
	public void command_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("command"));
		testee.validate();
	}
	
	@Test
	public void command_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = createTestee(createNamedMap("command"));
		testee.validate();
	}
	
	@Test
	public void cGroupParent_valid_successful() throws Exception {
		Validator testee = createTestee(createNamedString("cgroup_parent"));
		testee.validate();
	}
	
	@Test
	public void cGroupParent_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "cgroup_parent:";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void containerName_valid_successful() throws Exception {
		Validator testee = createTestee(createNamedString("container_name"));
		testee.validate();
	}
	
	@Test
	public void containerName_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "container_name:";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void devices_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("devices"));
		testee.validate();
	}
	
	//TODO: depends_on seems a little more complicated...not yet implemented
	
	@Test
	public void dns_validString_successful() throws Exception {
		Validator testee = createTestee(createNamedString("dns"));
		testee.validate();
	}
	
	@Test
	public void dns_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("dns"));
		testee.validate();
	}
	
	@Test
	public void dns_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = createTestee(createNamedMap("dns"));
		testee.validate();
	}
	
	@Test
	public void dnsSearch_validString_successful() throws Exception {
		Validator testee = createTestee(createNamedString("dns_search"));
		testee.validate();
	}
	
	@Test
	public void dnsSearch_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("dns_search"));
		testee.validate();
	}
	
	@Test
	public void dnsSearch_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = createTestee(createNamedMap("dns_search"));
		testee.validate();
	}
	
	@Test
	public void tmpfs_validString_successful() throws Exception {
		Validator testee = createTestee(createNamedString("tmpfs"));
		testee.validate();
	}
	
	@Test
	public void tmpfs_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("tmpfs"));
		testee.validate();
	}
	
	@Test
	public void tmpfs_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = createTestee(createNamedMap("tmpfs"));
		testee.validate();
	}
	
	@Test
	public void entrypoint_validString_successful() throws Exception {
		Validator testee = createTestee(createNamedString("entrypoint"));
		testee.validate();
	}
	
	@Test
	public void entrypoint_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("entrypoint"));
		testee.validate();
	}
	
	@Test
	public void entrypoint_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = createTestee(createNamedMap("entrypoint"));
		testee.validate();
	}
	
	@Test
	public void envfile_validString_successful() throws Exception {
		Validator testee = createTestee(createNamedString("env_file"));
		testee.validate();
	}
	
	@Test
	public void envfile_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("env_file"));
		testee.validate();
	}
	
	@Test
	public void envfile_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = createTestee(createNamedMap("env_file"));
		testee.validate();
	}
	
	@Test
	public void environment_validMap_successful() throws Exception {
		Validator testee = createTestee(createNamedMap("environment"));
		testee.validate();
	}
	
	@Test
	public void environment_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("environment"));
		testee.validate();
	}
	
	@Test
	public void environment_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		Validator testee = createTestee(createNamedString("environment"));
		testee.validate();
	}
	
	@Test
	public void expose_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("expose"));
		testee.validate();
	}
	
	@Test
	public void expose_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = createTestee(createNamedString("expose"));
		testee.validate();
	}
	
	@Test
	public void extends_validMap_successful() throws Exception {
		String content = "extends:\n  service: common";
		Validator testee = createTestee(content);
		testee.validate();
	}

	@Test
	public void extends_missingService_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("/service' is missing");
		String content = "extends:\n  file: common.yml";
		Validator testee = createTestee(content);
		testee.validate();
	}
	
	@Test
	public void extends_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be from type Map");
		Validator testee = createTestee(createNamedString("extends"));
		testee.validate();
	}
	
	@Test
	public void externalLinks_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("content"));
		testee.validate();
	}
	
	@Test
	public void externalLinks_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = createTestee(createNamedString("external_links"));
		testee.validate();
	}
	
	@Test
	public void extraHosts_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("extra_hosts"));
		testee.validate();
	}
	
	@Test
	public void extraHosts_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = createTestee(createNamedString("extra_hosts"));
		testee.validate();
	}
	
	@Test
	public void image_validString_successful() throws Exception {
		Validator testee = createTestee(createNamedString("image"));
		testee.validate();
	}
	
	@Test
	public void image_asList_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = createTestee(createNamedList("image"));
		testee.validate();
	}
	
	@Test
	public void labels_validMap_successful() throws Exception {
		Validator testee = createTestee(createNamedMap("labels"));
		testee.validate();
	}
	
	@Test
	public void labels_validList_successful() throws Exception {
		Validator testee = createTestee(createNamedList("labels"));
		testee.validate();
	}
	
	@Test
	public void labels_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		Validator testee = createTestee(createNamedString("labels"));
		testee.validate();
	}
	
	
	
	
	
	
	
	
	
	private String createNamedList(String name){
		return String.format("%s:\n   - dummyListEntry", name);
	}
	
	private String createNamedMap(String name){
		return String.format("%s:\n   dummyKey: dummyValue", name);
	}
	
	private String createNamedString(String name){
		return String.format("%s: dummyString", name);
	}
	
	private Validator createTestee(String content) throws DockerComposeValidationException{
		return new ValidatorV2Impl(content, Collections.<String, YamlValidator<?>>emptyMap());
	}
}
