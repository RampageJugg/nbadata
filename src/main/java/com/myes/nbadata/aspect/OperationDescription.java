package com.myes.nbadata.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.myes.nbadata.monitor.LogConfig;


@Target(ElementType.METHOD)    
@Retention(RetentionPolicy.RUNTIME)    
@Documented   
@Inherited  
public @interface OperationDescription {
	public LogConfig[] type();
	public String[] getOperFun();
}
