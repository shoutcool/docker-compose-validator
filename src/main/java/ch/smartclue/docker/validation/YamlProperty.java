package ch.smartclue.docker.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ch.smartclue.docker.yml.common.DockerComposeVersion;

//Maybe YamlNode is the better name for that interface

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface YamlProperty {
	public String path();
	public DockerComposeVersion version();
	public boolean containerProperty() default false;
}
