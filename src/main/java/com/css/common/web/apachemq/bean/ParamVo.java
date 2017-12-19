
package com.css.common.web.apachemq.bean;

import java.io.Serializable;

/**     
 * @Title: ParamVo.java   
 * @Package com.css.business.web.syswebsoket.vo   
 * @Description: 机台实时数据的vo,通过mq发送  
 * @author   rb
 * @date 2017年7月18日 上午10:27:36   
 * @company  SMTC   
 */

public class ParamVo implements Serializable{
	private static final long serialVersionUID = 6157073198699480234L;
	
	/** 参数名称 */
	private String param;
	/** 参数数值   */
	//机台打包提交时，回填；设定值、最大、最小
	private String value;
	private String valueMax;
	private String valueMin;
	//参数状态（发送到机台时不填。 从机台回写时：要么没数据，要么发回的数据是必须要更新到数据库的；
	//flag:更新; 不更新） 必须要更新参数的：flag=更新
	private String flag;
	
	/**
	 * @return  param
	 */
	public String getParam() {
		return param;
	}
	/**
	 *  @param param 要设置的 param 
	 *    
	 */
	public void setParam(String param) {
		this.param = param;
	}
	/**
	 * @return  value
	 */
	public String getValue() {
		return value;
	}
	/**
	 *  @param value 要设置的 value 
	 *    
	 */
	public void setValue(String value) {
		this.value = value;
	}
	public String getValueMax() {
		return valueMax;
	}
	public void setValueMax(String valueMax) {
		this.valueMax = valueMax;
	}
	public String getValueMin() {
		return valueMin;
	}
	public void setValueMin(String valueMin) {
		this.valueMin = valueMin;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
