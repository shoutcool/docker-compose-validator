package ch.smartclue.docker.validation;

import static ch.smartclue.docker.validation.ValidatorInstanceFilter.filterValidatorsByVersion;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import ch.smartclue.docker.yml.generic.DockerComposeVersion;

public class DockerComposeValidatorTest {

	private DockerComposeValidator testee;

	private ValidatorManager validatorManager = mock(ValidatorManager.class);

	@Before
	public void init() {
		testee = new DockerComposeValidator(validatorManager);
	}

	@Test
	public void validator_countInstances_noDuplicatesFound() throws Exception {
		ArgumentCaptor<ValidatorInstance> argumentCaptor = ArgumentCaptor.forClass(ValidatorInstance.class);

		verify(validatorManager, times(40)).addValidatorInstance(argumentCaptor.capture());

		List<ValidatorInstance> allValues = argumentCaptor.getAllValues();

		List<ValidatorInstance> v1Instances = filterValidatorsByVersion(allValues, DockerComposeVersion.V1);
		assertThat(findDuplicatePath(v1Instances), IsEmptyCollection.<String>empty());

		List<ValidatorInstance> v2Instances = filterValidatorsByVersion(allValues, DockerComposeVersion.V2);
		assertThat(findDuplicatePath(v2Instances), IsEmptyCollection.<String>empty());

		List<ValidatorInstance> vAllInstances = filterValidatorsByVersion(allValues, DockerComposeVersion.ALL);
		assertThat(findDuplicatePath(vAllInstances), IsEmptyCollection.<String>empty());
	}

	@Test
	public void validator_initialManager_successfullyCreated() throws Exception {
		testee = new DockerComposeValidator();
		assertNotNull(testee.getValidatorManager());
	}

	private List<String> findDuplicatePath(List<ValidatorInstance> input) {
		List<String> duplicates = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();

		for (ValidatorInstance value : new HashSet<ValidatorInstance>(input)) {
			String path = value.getPath();
			if (temp.contains(path)) {
				// duplicate found
				duplicates.add(path);
			} else {
				temp.add(path);
			}
		}

		return duplicates;
	}
}
