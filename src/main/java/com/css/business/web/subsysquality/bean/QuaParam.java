package com.css.business.web.subsysquality.bean;

/**
 * @Title: QuaParam.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: 制程质检 的参数......电缆质检呢??
 * @author rb
 * @date 2017年9月5日 下午4:20:41
 * @company SMTC
 */

public class QuaParam {
	/** 参数名称 */
	private String paramName;
	/** 第一次值 */
	private String firstValue;
	/** 第二次值 */
	private String secondValue;

	/**
	 * @return paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName
	 *            要设置的 paramName
	 * 
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return firstValue
	 */
	public String getFirstValue() {
		return firstValue;
	}

	/**
	 * @param firstValue
	 *            要设置的 firstValue
	 * 
	 */
	public void setFirstValue(String firstValue) {
		this.firstValue = firstValue;
	}

	/**
	 * @return secondValue
	 */
	public String getSecondValue() {
		return secondValue;
	}

	/**
	 * @param secondValue
	 *            要设置的 secondValue
	 * 
	 */
	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}

}
