package com.css.business.web.subsysquality.bean;

import java.util.List;

/**
 * @Title: MacQuaVo.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: 机台发送的质检请求,现在能保证制程质检,电缆质检??
 * @author rb
 * @date 2017年9月5日 下午4:08:47
 * @company SMTC
 */

public class MacQuaVo {
	/**
	 * 机台上传web:保证轴名称,质检类型,参数List web下发到机台:保证机台id 轴名称,参数List
	 */
	/** 机台id */
	private Integer machineId;
	/** 机台编号 */
	private String machineCode;
	/** 质检类型 :制程质检,电缆质检 */
	private String testType;
	/** 轴名称 */
	private String axisName;
	/** 模版名称 */
	private String modelName;
	/** 参数List */
	private List<QuaParam> quaParam;
	/** 质检结果 */
	private String testResult;

	/**
	 * @return machineId
	 */
	public Integer getMachineId() {
		return machineId;
	}

	/**
	 * @param machineId
	 *            要设置的 machineId
	 * 
	 */
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	/**
	 * @return machineCode
	 */
	public String getMachineCode() {
		return machineCode;
	}

	/**
	 * @param machineCode
	 *            要设置的 machineCode
	 * 
	 */
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	/**
	 * @return axisName
	 */
	public String getAxisName() {
		return axisName;
	}

	/**
	 * @param axisName
	 *            要设置的 axisName
	 * 
	 */
	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	/**
	 * @return modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            要设置的 modelName
	 * 
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return quaParam
	 */
	public List<QuaParam> getQuaParam() {
		return quaParam;
	}

	/**
	 * @param quaParam
	 *            要设置的 quaParam
	 * 
	 */
	public void setQuaParam(List<QuaParam> quaParam) {
		this.quaParam = quaParam;
	}

	/**
	 * @return testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * @param testType
	 *            要设置的 testType
	 * 
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}

	/**
	 * @return testResult
	 */
	public String getTestResult() {
		return testResult;
	}

	/**
	 * @param testResult
	 *            要设置的 testResult
	 * 
	 */
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

}
