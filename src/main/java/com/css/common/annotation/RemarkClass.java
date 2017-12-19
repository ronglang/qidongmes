package com.css.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 *TODO
 * @author huangaho
 *2015-4-16下午1:47:56
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE) 
@Documented 
@Inherited
public @interface  RemarkClass {
	 public String toDo() ;
}
