package ch.smartclue.docker.validation;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ServiceTest {

	@Test
	public void subnode_level1_successful() throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("build", "bla");
		
		Service service = new Service("/services/foo2", root);
		
		assertTrue(service.hasSubNode("/build"));
	}
	
	@Test
	public void subnode_notExisting_returnsFalse() throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("build", "bla");
		
		Service service = new Service("/services/foo2", root);
		
		assertFalse(service.hasSubNode("/build/bla/bli/blu"));
	}
	
	@Test
	public void subnode_level2_successful() throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		Map<String, Object> sub = new HashMap<String, Object>();
		sub.put("dockerfile", ".");
		root.put("build", sub);
		
		Service service = new Service("/services/foo2", root);
		
		assertTrue(service.hasSubNode("/build/dockerfile"));
	}
}
