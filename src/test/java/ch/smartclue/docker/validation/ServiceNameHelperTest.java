package ch.smartclue.docker.validation;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServiceNameHelperTest {

	@Test
	public void serviceName_validV1_retursServiceName() throws Exception{
		assertEquals("foo", ServiceNameHelper.getServiceNameFromPath("/foo"));
	}
	
	@Test
	public void serviceName_validV1WithBuild_retursServiceName() throws Exception{
		assertEquals("foo", ServiceNameHelper.getServiceNameFromPath("/foo/build"));
	}
	
	@Test
	public void serviceName_validV2_retursServiceName() throws Exception{
		assertEquals("services/foo", ServiceNameHelper.getServiceNameFromPath("/services/foo/"));
	}
	
	@Test
	public void serviceName_validV2WithBuild_retursServiceName() throws Exception{
		assertEquals("services/foo", ServiceNameHelper.getServiceNameFromPath("/services/foo/build"));
	}
	
}
