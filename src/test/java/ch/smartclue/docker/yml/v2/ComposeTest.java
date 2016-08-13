package ch.smartclue.docker.yml.v2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.smartclue.docker.DockerComposeValidationException;
import ch.smartclue.docker.Validator;
import ch.smartclue.docker.yml.v2.ValidatorV2Impl;

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
		String content = "build:\n   args:\n      buildno: 1";
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
	
	@Test
	public void command_validString_successful() throws Exception {
		String content = "command: .";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void command_validList_successful() throws Exception {
		String content = "command: [bundle, exec, thin, -p, 3000]";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void command_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		String content = "command:\n   cmd1: exec";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void cGroupParent_valid_successful() throws Exception {
		String content = "cgroup_parent: m-executor-abcd";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void cGroupParent_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "cgroup_parent:";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void containerName_valid_successful() throws Exception {
		String content = "container_name: my-web-container";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void containerName_empty_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must not be empty");
		String content = "container_name:";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void devices_validList_successful() throws Exception {
		String content = "devices:\n  - /dev/ttyUSB0:/dev/ttyUSB0";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	//TODO: depends_on seems a little more complicated...not yet implemented
	
	@Test
	public void dns_validString_successful() throws Exception {
		String content = "dns: 8.8.8.8";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void dns_validList_successful() throws Exception {
		String content = "dns:\n  - 8.8.8.8\n  - 9.9.9.9";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void dns_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		String content = "dns:\n   cmd1: exec";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void dnsSearch_validString_successful() throws Exception {
		String content = "dns_search: example.com";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void dnsSearch_validList_successful() throws Exception {
		String content = "dns_search:\n  - dc1.example.com\n  - dc2.example.com";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void dnsSearch_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		String content = "dns_search:\n   dns1: dc1.example.com";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void tmpfs_validString_successful() throws Exception {
		String content = "tmpfs: /run";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void tmpfs_validList_successful() throws Exception {
		String content = "tmpfs:\n  - /run\n  - /tmp";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void tmpfs_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		String content = "tmpfs:\n   fs1: /run";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void entrypoint_validString_successful() throws Exception {
		String content = "entrypoint: /code/entrypoint.sh";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void entrypoint_validList_successful() throws Exception {
		String content = "entrypoint:\n  - php\n  - d";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void entrypoint_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		String content = "entrypoint:\n   dummy: php";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void envfile_validString_successful() throws Exception {
		String content = "env_file: .env";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void envfile_validList_successful() throws Exception {
		String content = "env_file:\n  - ./common.env";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void envfile_asMap_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type String or List");
		String content = "env_file:\n   dummy: php";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void environment_validMap_successful() throws Exception {
		String content = "environment:\n  RACK_ENV: development";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void environment_validList_successful() throws Exception {
		String content = "environment:\n  - RACK_ENV=development";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void environment_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		String content = "environment: .";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void expose_validList_successful() throws Exception {
		String content = "expose:\n - \"3000\"";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void expose_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "expose: 3000";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void extends_validMap_successful() throws Exception {
		String content = "extends:\n  service: common";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}

	@Test
	public void extends_missingService_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("/service' is missing");
		String content = "extends:\n  file: common.yml";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void extends_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be from type Map");
		String content = "extends: common";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void externalLinks_validList_successful() throws Exception {
		String content = "external_links:\n - redis_1";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void externalLinks_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "external_links: redis_1";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void extraHosts_validList_successful() throws Exception {
		String content = "extra_hosts:\n - \"somehost:162.242.195.82\"";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void extraHosts_asString_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "extra_hosts: \"somehost:162.242.195.82\"";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void image_validString_successful() throws Exception {
		String content = "image: myImage";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void image_asList_throwsException() throws Exception {
		expectedException.expect(ClassCastException.class);
		String content = "image:\n   - myImage";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void labels_validMap_successful() throws Exception {
		String content = "labels:\n  RACK_ENV: development";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void labels_validList_successful() throws Exception {
		String content = "labels:\n  - RACK_ENV=development";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
	
	@Test
	public void labels_asString_throwsException() throws Exception {
		expectedException.expect(DockerComposeValidationException.class);
		expectedException.expectMessage("must be either from type List or Map");
		String content = "labels: .";
		Validator testee = new ValidatorV2Impl(content);
		testee.validate();
	}
}
