package com.css.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义注解：获取字段中文注解名称,用于高级查询配置查询字段
 * @author leitao
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.FIELD,ElementType.METHOD}) 
@Documented 
@Inherited
public @interface  TableNameAnnotiation {
	
	/**
	 * 字段名称
	*@return 
	*@author leitao 
	*@date 2016-8-8 下午3:22:17
	 */
    public String fieldName() default ""; 
    /**
     * 字段类型
    *@return 
    *@author leitao 
    *@date 2016-8-8 下午3:22:31
     */
    public String fieldType() default "";
    /**
     * 此字段是否为树选择
    *@return 
    *@author leitao 
    *@date 2016-8-8 下午3:22:44
     */
    public boolean isTree() default false;
    /**
     * 如果是树选择，加载树的实体名称（树数据的来源实体名称）
    *@return 
    *@author leitao 
    *@date 2016-8-8 下午3:23:13
     */
    public String treeActionName() default "";
    /**
     * 查询方法名
    *@return 
    *@author leitao 
    *@date 2016-8-11 上午10:00:35
     */
    public String treeMethod() default "";
    /**
     * 查询条件名
    *@return 
    *@author leitao 
    *@date 2016-8-11 上午10:00:57
     */
    public String queryName() default "";
    /**
     * 查询条件值
    *@return 
    *@author leitao 
    *@date 2016-8-11 上午10:01:10
     */
    public String queryValue() default "";
}
