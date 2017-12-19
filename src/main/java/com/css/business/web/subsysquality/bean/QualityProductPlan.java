package com.css.business.web.subsysquality.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Title: QualityProductPlan.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: 成品,半成品质检计划
 * @author rb
 * @date 2017年8月11日 上午9:34:30
 * @company SMTC
 */
@Entity
@Table(name = "Quality_Product_Plan")
@SequenceGenerator(name = "SEQ_Quality_Product_Plan", sequenceName = "SEQ_Quality_Product_Plan")
public class QualityProductPlan implements BaseEntity {
	/** 检测结果:合格 */
	@Transient
	public static final String TEST_RESULT_PASS = "合格";
	/** 检测结果:异议 */
	@Transient
	public static final String TEST_RESULT_DISSENT_PASS = "异议";
	/** 检测结果:不合格 */
	@Transient
	public static final String TEST_RESULT_NOT_PASS = "不合格";

	/** 检测类型:成品检测 */
	@Transient
	public static final String TEST_TYPE_END_PRODUCT = "成品检测";
	/** 检测类型:半成品检测 */
	@Transient
	public static final String TEST_TYPE_SEMI_PRODUCT = "半成品检测";

	/** 检测状态:未检测 */
	@Transient
	public static final String TEST_STATE_NO_TEST = "未检测";
	/** 检测状态:检测中 */
	@Transient
	public static final String TEST_STATE_TESTING = "检测中";
	/** 检测状态:已检测 */
	@Transient
	public static final String TEST_STATE_TESTED = "已检测";
	@Transient
	private static final long serialVersionUID = 7566096659991777263L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	/** 计划检测时间 */
	@Column(name = "plan_test_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date planTestDate;
	/** 实际检测时间 */
	@Column(name = "fact_test_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date factTestDate;

	/** 检测类型 */
	@Column(name = "type")
	private String type;
	/** 轴名称 */
	@Column(name = "axis_name")
	private String axisName;
	/** 工序编码 */
	@Column(name = "seq_code")
	private String seqCode;
	/** 规格型号 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 机器编号 */
	@Column(name = "mac_code")
	private String macCode;
	/** 测试人 */
	@Column(name = "test_by")
	private String testBy;
	/** 检测结果 */
	@Column(name = "test_result")
	private String testResult;
	/** 检测状态 */
	@Column(name = "test_state")
	private String testState;
	/** 工序名称 */
	@Column(name = "seq_name")
	private String seqName;
	/** 纸质质检报告编号 */
	@Column(name = "report_code")
	private String reportCode;
	/** 图片地址 */
	@Column(name = "report_url")
	private String reportUrl;
	/** 生产令id */
	@Column(name = "pla_order_id")
	private Integer plaOrderId;
	/** 处理意见 */
	@Column(name = "advice")
	private String advice;
	/** 系统自动生成的code */
	@Column(name = "survy_code")
	private String survyCode;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            要设置的 createDate
	 * 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return planTestDate
	 */
	public Date getPlanTestDate() {
		return planTestDate;
	}

	/**
	 * @param planTestDate
	 *            要设置的 planTestDate
	 * 
	 */
	public void setPlanTestDate(Date planTestDate) {
		this.planTestDate = planTestDate;
	}

	/**
	 * @return factTestDate
	 */
	public Date getFactTestDate() {
		return factTestDate;
	}

	/**
	 * @param factTestDate
	 *            要设置的 factTestDate
	 * 
	 */
	public void setFactTestDate(Date factTestDate) {
		this.factTestDate = factTestDate;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            要设置的 type
	 * 
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return seqCode
	 */
	public String getSeqCode() {
		return seqCode;
	}

	/**
	 * @param seqCode
	 *            要设置的 seqCode
	 * 
	 */
	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	/**
	 * @return proGgxh
	 */
	public String getProGgxh() {
		return proGgxh;
	}

	/**
	 * @param proGgxh
	 *            要设置的 proGgxh
	 * 
	 */
	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	/**
	 * @return macCode
	 */
	public String getMacCode() {
		return macCode;
	}

	/**
	 * @param macCode
	 *            要设置的 macCode
	 * 
	 */
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	/**
	 * @return testBy
	 */
	public String getTestBy() {
		return testBy;
	}

	/**
	 * @param testBy
	 *            要设置的 testBy
	 * 
	 */
	public void setTestBy(String testBy) {
		this.testBy = testBy;
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

	/**
	 * @return testState
	 */
	public String getTestState() {
		return testState;
	}

	/**
	 * @param testState
	 *            要设置的 testState
	 * 
	 */
	public void setTestState(String testState) {
		this.testState = testState;
	}

	/**
	 * @return seqName
	 */
	public String getSeqName() {
		return seqName;
	}

	/**
	 * @param seqName
	 *            要设置的 seqName
	 * 
	 */
	public void setSeqName(String seqName) {
		this.seqName = seqName;
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
	 * @return reportUrl
	 */
	public String getReportUrl() {
		return reportUrl;
	}

	/**
	 * @param reportUrl
	 *            要设置的 reportUrl
	 * 
	 */
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	/**
	 * @return plaOrderId
	 */
	public Integer getPlaOrderId() {
		return plaOrderId;
	}

	/**
	 * @param plaOrderId
	 *            要设置的 plaOrderId
	 * 
	 */
	public void setPlaOrderId(Integer plaOrderId) {
		this.plaOrderId = plaOrderId;
	}

	/**
	 * @return advice
	 */
	public String getAdvice() {
		return advice;
	}

	/**
	 * @param advice
	 *            要设置的 advice
	 * 
	 */
	public void setAdvice(String advice) {
		this.advice = advice;
	}

	/**
	 * @return survyCode
	 */
	public String getSurvyCode() {
		return survyCode;
	}

	/**
	 * @param survyCode
	 *            要设置的 survyCode
	 * 
	 */
	public void setSurvyCode(String survyCode) {
		this.survyCode = survyCode;
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

}
