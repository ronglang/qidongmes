package com.css.common.web.apachemq.bean;

import java.sql.Timestamp;

/**
 * @Title: ParamInfo.java
 * @Package com.css.common.web.apachemq.bean
 * @Description: 机台交互公用模型
 * @author rb
 * @date 2017年7月20日 下午3:52:15
 * @company SMTC
 */

public class ParamInfo {
	/** 参数code */
	// public Integer paramCode;
	public String paramCode;

	public String paramName;
	/** 参数值 */
	// public Double paramValue;
	public String paramValue;
	/** 时间 */
	public Timestamp pickTimestamp;
	/** 是否异常 */
	public String isExceptin;

	/**
	 * @return pickTimestamp
	 */
	public Timestamp getPickTimestamp() {
		return pickTimestamp;
	}

	/**
	 * @param pickTimestamp
	 *            要设置的 pickTimestamp
	 * 
	 */
	public void setPickTimestamp(Timestamp pickTimestamp) {
		this.pickTimestamp = pickTimestamp;
	}

	/**
	 * @return isExceptin
	 */
	public String getIsExceptin() {
		return isExceptin;
	}

	/**
	 * @param isExceptin
	 *            要设置的 isExceptin
	 * 
	 */
	public void setIsExceptin(String isExceptin) {
		this.isExceptin = isExceptin;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}
