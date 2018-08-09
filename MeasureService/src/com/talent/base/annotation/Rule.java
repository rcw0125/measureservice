package com.talent.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Rule {
	
	public String GETING_INFO = "getinginfo";
	
	public String BEFORE_SAVE = "beforesave";
	
	public String ALL_VALID = "allvalid";
	
	String name() default "";
	
	String ctrlflag() default "";
	
	String memo() default "";
	
	String validplace() default GETING_INFO;
}
