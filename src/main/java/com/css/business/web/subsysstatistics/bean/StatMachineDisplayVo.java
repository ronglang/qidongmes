package com.css.business.web.subsysstatistics.bean;

/**
 * @Title: StatMachineDisplayVo.java
 * @Package com.css.business.web.subsysstatistics.bean
 * @Description: 统计机台使用情况的vo
 * @author rb
 * @date 2017年7月25日 下午3:45:49
 * @company SMTC
 */

public class StatMachineDisplayVo {

	/** 机台名称 */
	private String machineName;
	/** 完成工单数 */
	private String courseCount;
	/** 生产长度 */
	private String productLen;
	/** 异常次数 */
	private String exceptionCount;
	/** 维修次数 */
	private String repairCount;
	/** 当前状态 */
	private String state;

	/**
	 * @return machineName
	 */
	public String getMachineName() {
		return machineName;
	}

	/**
	 * @param machineName
	 *            要设置的 machineName
	 * 
	 */
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	/**
	 * @return courseCount
	 */
	public String getCourseCount() {
		return courseCount;
	}

	/**
	 * @param courseCount
	 *            要设置的 courseCount
	 * 
	 */
	public void setCourseCount(String courseCount) {
		this.courseCount = courseCount;
	}

	/**
	 * @return productLen
	 */
	public String getProductLen() {
		return productLen;
	}

	/**
	 * @param productLen
	 *            要设置的 productLen
	 * 
	 */
	public void setProductLen(String productLen) {
		this.productLen = productLen;
	}

	/**
	 * @return exceptionCount
	 */
	public String getExceptionCount() {
		return exceptionCount;
	}

	/**
	 * @param exceptionCount
	 *            要设置的 exceptionCount
	 * 
	 */
	public void setExceptionCount(String exceptionCount) {
		this.exceptionCount = exceptionCount;
	}

	/**
	 * @return repairCount
	 */
	public String getRepairCount() {
		return repairCount;
	}

	/**
	 * @param repairCount
	 *            要设置的 repairCount
	 * 
	 */
	public void setRepairCount(String repairCount) {
		this.repairCount = repairCount;
	}

	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            要设置的 state
	 * 
	 */
	public void setState(String state) {
		this.state = state;
	}

}
