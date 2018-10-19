package com.demo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator {
	
	//flag 的有效值， 多个用 , 隔开
	String values();
	
	String message() default "flag不存在";
	
	Class<?> [] groups() default{};
	
	Class<? extends Payload> [] payload() default{};
	
	
}
