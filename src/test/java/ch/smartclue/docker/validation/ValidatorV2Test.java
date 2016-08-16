package ch.smartclue.docker.validation;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.smartclue.docker.exception.DockerComposeValidationException;
import ch.smartclue.docker.validation.Validator;
import ch.smartclue.docker.validation.ValidatorV2Impl;

public class ValidatorV2Test {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private static ValidatorManager validatorManager = new ValidatorManager();
	
	@BeforeClass
	public static void initClass(){
		List<ValidatorInstance> instances = ReflectionUtil.createValidationInstancesFromPackageName("ch.smartclue.docker.yml.common");
		instances.addAll(ReflectionUtil.createValidationInstancesFromPackageName("ch.smartclue.docker.yml.v2"));
		for (ValidatorInstance instance : instances){
			validatorManager.addValidatorInstance(instance);
		}
	}
	
	@Test
	public void build_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("build"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void build_emptyString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		//TODO: Is this really a problem?
		String content = "build:";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void build_validObject_successful() throws Exception {
		String content = "build:\n   context: .";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void context_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "build:\n   context:";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void context_missing_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("/context' is missing");
		String content = "build:\n   args:\n      buildno: 1";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}

	@Test
	public void dockerfile_missingContext_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("'/build/context' must be specified");
		String content = "build:\n   dockerfile: .";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void args_asList_successful() throws Exception {
		String content = "build:\n  context: .\n  args:\n    - buildno=1\n    - password=secret";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void args_asMap_successful() throws Exception {
		String content = "build:\n  context: .\n  args:\n    buildno: 1\n    password: secret";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void args_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		String content = "build:\n  context: .\n  args: blub";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void capAdd_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("cap_add"), validatorManager);
		testee.validate();
	}

	@Test
	public void capAdd_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = new ValidatorV2Impl(createNamedString("cap_add"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void capDrop_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("cap_drop"), validatorManager);
		testee.validate();
	}

	@Test
	public void capDrop_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = new ValidatorV2Impl(createNamedString("cap_drop"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void command_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("command"), validatorManager);
		testee.validate();
	}
	
	//TODO: Container names with list of "links"
	
	
	
	@Test
	public void command_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("command"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void command_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = new ValidatorV2Impl(createNamedMap("command"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void cGroupParent_valid_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("cgroup_parent"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void cGroupParent_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "cgroup_parent:";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void containerName_valid_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("container_name"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void containerName_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "container_name:";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void devices_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("devices"), validatorManager);
		testee.validate();
	}
	
	//TODO: depends_on seems a little more complicated...not yet implemented
	
	@Test
	public void dns_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("dns"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void dns_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("dns"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void dns_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = new ValidatorV2Impl(createNamedMap("dns"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void dnsSearch_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("dns_search"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void dnsSearch_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("dns_search"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void dnsSearch_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = new ValidatorV2Impl(createNamedMap("dns_search"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void tmpfs_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("tmpfs"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void tmpfs_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("tmpfs"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void tmpfs_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = new ValidatorV2Impl(createNamedMap("tmpfs"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void entrypoint_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("entrypoint"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void entrypoint_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("entrypoint"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void entrypoint_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = new ValidatorV2Impl(createNamedMap("entrypoint"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void envfile_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("env_file"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void envfile_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("env_file"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void envfile_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		Validator testee = new ValidatorV2Impl(createNamedMap("env_file"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void environment_validMap_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedMap("environment"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void environment_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("environment"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void environment_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		Validator testee = new ValidatorV2Impl(createNamedString("environment"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void expose_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("expose"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void expose_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = new ValidatorV2Impl(createNamedString("expose"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void extends_validMap_successful() throws Exception {
		String content = "extends:\n  service: common";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}

	@Test
	public void extends_missingService_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("/service' is missing");
		String content = "extends:\n  file: common.yml";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void extends_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be from type Map");
		Validator testee = new ValidatorV2Impl(createNamedString("extends"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void externalLinks_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("content"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void externalLinks_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = new ValidatorV2Impl(createNamedString("external_links"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void extraHosts_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("extra_hosts"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void extraHosts_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = new ValidatorV2Impl(createNamedString("extra_hosts"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void image_validString_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedString("image"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void image_asList_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = new ValidatorV2Impl(createNamedList("image"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void labels_validMap_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedMap("labels"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void labels_validList_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedList("labels"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void labels_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		Validator testee = new ValidatorV2Impl(createNamedString("labels"), validatorManager);
		testee.validate();
	}
	
	
	@Test
	public void links_validList_successful() throws Exception {
		String content = "web:\n   links:\n      - db";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void links_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "web:\n   links: bla";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void links_unknownNode_successful() throws Exception {
		String content = "build:\n   context: .\n   links:\n      foo: bar";
		Validator testee = new ValidatorV2Impl(content, validatorManager);
		testee.validate();
	}
	
	@Test
	public void logging_validMap_successful() throws Exception {
		Validator testee = new ValidatorV2Impl(createNamedMap("logging"), validatorManager);
		testee.validate();
	}
	
	@Test
	public void logging_asString_successful() throws Exception {
		expectedException.expect(ClassCastException.class);
		Validator testee = new ValidatorV2Impl(createNamedString("logging"), validatorManager);
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

}
