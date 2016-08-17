package ch.smartclue.docker.validation;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import ch.smartclue.docker.yml.common.AbstractGenericList;
import ch.smartclue.docker.yml.common.AbstractGenericMap;
import ch.smartclue.docker.yml.common.AbstractGenericMapOrList;
import ch.smartclue.docker.yml.common.AbstractGenericString;
import ch.smartclue.docker.yml.common.AbstractGenericStringOrList;
import ch.smartclue.docker.yml.common.CGroupParent;
import ch.smartclue.docker.yml.common.CapAdd;
import ch.smartclue.docker.yml.common.CapDrop;
import ch.smartclue.docker.yml.common.Command;
import ch.smartclue.docker.yml.common.ContainerName;
import ch.smartclue.docker.yml.common.Devices;
import ch.smartclue.docker.yml.common.Dns;
import ch.smartclue.docker.yml.common.DnsSearch;
import ch.smartclue.docker.yml.common.EntryPoint;
import ch.smartclue.docker.yml.common.EnvFile;
import ch.smartclue.docker.yml.common.Environment;
import ch.smartclue.docker.yml.common.Expose;
import ch.smartclue.docker.yml.common.ExternalLinks;
import ch.smartclue.docker.yml.common.ExtraHosts;
import ch.smartclue.docker.yml.common.Image;
import ch.smartclue.docker.yml.common.Labels;
import ch.smartclue.docker.yml.common.Links;
import ch.smartclue.docker.yml.v1.Build;
import ch.smartclue.docker.yml.v1.Dockerfile;
import ch.smartclue.docker.yml.v2.Args;
import ch.smartclue.docker.yml.v2.Context;
import ch.smartclue.docker.yml.v2.Logging;
import ch.smartclue.docker.yml.v2.Options;
import ch.smartclue.docker.yml.v2.Tmpfs;

@RunWith(Parameterized.class)
public class TypeTest {

	@Parameter(0)
	public Class<? extends YamlValidator<?>> validator;

	@Parameter(1)
	public Class<? extends YamlValidator<?>> baseclass;
	
	@Parameters(name = "{index}: {0} instanceof {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { 
                 {Args.class, AbstractGenericMapOrList.class}, 
                 {Build.class, AbstractGenericString.class}, 
                 {CapAdd.class, AbstractGenericList.class},
                 {CapDrop.class, AbstractGenericList.class},
                 {CGroupParent.class, AbstractGenericString.class},
                 {Command.class, AbstractGenericStringOrList.class},
                 {ContainerName.class, AbstractGenericString.class},
                 {Context.class, AbstractGenericString.class},
                 {Devices.class, AbstractGenericList.class},
                 {Dns.class, AbstractGenericStringOrList.class},
                 {DnsSearch.class, AbstractGenericStringOrList.class},
                 {Dockerfile.class, AbstractGenericString.class},
                 {ch.smartclue.docker.yml.v2.Dockerfile.class, AbstractGenericString.class},
                 {EntryPoint.class, AbstractGenericStringOrList.class},
                 {EnvFile.class, AbstractGenericStringOrList.class},
                 {Environment.class, AbstractGenericMapOrList.class},
                 {Expose.class, AbstractGenericList.class},
                 {ExternalLinks.class, AbstractGenericList.class},
                 {ExtraHosts.class, AbstractGenericList.class},
                 {Image.class, AbstractGenericString.class},
                 {Labels.class, AbstractGenericMapOrList.class},
                 {Links.class, AbstractGenericList.class},
                 {Logging.class, AbstractGenericMap.class},
                 {Options.class, AbstractGenericMap.class},
                 {Tmpfs.class, AbstractGenericStringOrList.class}
                 
                 
                 
                 

           });
    }
    
    @Test
	public void testValidator() throws Exception {
	    assertTrue(baseclass.isInstance(validator.newInstance()));
	}
}
