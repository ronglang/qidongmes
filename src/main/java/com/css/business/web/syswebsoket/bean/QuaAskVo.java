package com.css.business.web.syswebsoket.bean;

import java.util.Date;

/**
 * @Title: QuaAskVo.java
 * @Package com.css.business.web.syswebsoket.bean
 * @Description: 质检呼叫VO 也就是查询和来料 "未处理的"质检
 * @author rb
 * @date 2017年9月7日 上午10:12:12
 * @company SMTC
 */

public class QuaAskVo {
	/** 测试类型 */
	private String testType;
	/** 质检报告编号 */
	private String reportCode;
	/** 呼叫人 */
	private String createBy;
	/** 呼叫时间 */
	private String createDate;

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
	 * @return reportCode
	 */
	public String getReportCode() {
		return reportCode;
	}

	/**
	 * @param reportCode
	 *            要设置的 reportCode
	 * 
	 */
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	/**
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            要设置的 createBy
	 * 
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            要设置的 createDate
	 * 
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
