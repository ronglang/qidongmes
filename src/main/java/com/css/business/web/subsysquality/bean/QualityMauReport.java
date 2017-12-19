package com.css.business.web.subsysquality.bean;

import java.util.Date;
import java.util.List;

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
 * 
 * @Title: QualityMauReport.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: new生产质检表 (成品,半成品都在这里面)
 * @author rb
 * @date 2017年8月31日 上午12:02:07
 * @company SMTC
 */
@Entity
@Table(name = "Quality_Mau_Report")
@SequenceGenerator(name = "SEQ_Quality_Mau_Report", sequenceName = "SEQ_Quality_Mau_Report")
public class QualityMauReport implements BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = -8740047370010484725L;
	/** 未检测 */
	@Transient
	public static String STATE_NO_TEST = "未检测";
	/** 检测中 */
	@Transient
	public static String STATE_IS_STARTING = "检测中";
	/** 已检测 */
	@Transient
	public static String STATE_IS_END = "已检测";
	/** 来料质检 */
	@Transient
	public static String TYPE_IS_MATER = "来料质检";
	/** 制程质检 */
	@Transient
	public static String TYPE_IS_PROCESS = "制程质检";
	/** 电缆质检 */
	@Transient
	public static String TYPE_IS_PRODUCT = "电缆质检";
	/** 已下发至机台 */
	@Transient
	public static String SEND_IS_YES = "已发送";
	/** 未发送 */
	@Transient
	public static String SEND_IS_NO = "未发送";
	/** 合格 */
	@Transient
	public static String RESULT_IS_PASS = "合格";
	/** 不合格 */
	@Transient
	public static String RESULT_NO_PASS = "合格";
	/** 已入库 */
	@Transient
	public static String IS_COME = "已入库";
	/** 处理结果:通过 */
	@Transient
	public static String T_RESULT_PASS = "通过";
	/** 处理结果:机台自处理 */
	@Transient
	public static String T_RESULT_MAC_SELF = "机台自处理";
	/** 处理结果:单个工序返工 */
	@Transient
	public static String T_RESULT_SINGLE_AGAIN = "该工序返工";
	/** 处理结果:报废 */
	@Transient
	public static String T_RESULT_REJECT = "报废";

	/** 未入库 */
	@Transient
	public static String IS_NOT_COME = "未入库";
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

	@Column(name = "test_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date testDate;
	/** 系统生成单号 */
	@Column(name = "report_code")
	private String reportCode;
	/** 纸质报告单号(如果有) */
	@Column(name = "page_code")
	private String pageCode;
	/** 检测状态 */
	@Column(name = "test_state")
	private String testState;
	/** 检测结果 */
	@Column(name = "test_result")
	private String testResult;
	/** 质检人 */
	@Column(name = "test_by")
	private String testBy;
	/** 质检类型名称(quality_basic_type) */
	@Column(name = "type_name")
	private String typeName;
	/** 质检依据 */
	@Column(name = "test_according")
	private String testAccording;
	/** 轴名称 */
	@Column(name = "axis_name")
	private String axisName;
	/** 工序半制品轴号(rfid) */
	@Column(name = "semi_axis")
	private String semiAxis;
	/** 工序名称 */
	@Column(name = "seq_name")
	private String seqName;
	/** 机器编码 */
	@Column(name = "mac_code")
	private String macCode;
	/** 产品规格型号(成品检测才有) */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 质检类型 */
	@Column(name = "test_type")
	private String testType;
	/** 工单编号 */
	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "remark")
	private String remark;
	@Column(name = "batch_code")
	private String batchCode;
	/** 是否发送到机台 */
	@Column(name = "is_send")
	private String isSend;
	/** 电缆使用:是否入库 */
	@Column(name = "is_come")
	private String isCome;

	@Column(name = "weight")
	private Double weight;

	/** 处理结果 */
	@Column(name = "treatment_results")
	private String treatmentResults;

	/** 具体值 */
	@Transient
	private List<QualityBasicValue> vaList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getTestState() {
		return testState;
	}

	public void setTestState(String testState) {
		this.testState = testState;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTestAccording() {
		return testAccording;
	}

	public void setTestAccording(String testAccording) {
		this.testAccording = testAccording;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getSemiAxis() {
		return semiAxis;
	}

	public void setSemiAxis(String semiAxis) {
		this.semiAxis = semiAxis;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	/**
	 * @return courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode
	 *            要设置的 courseCode
	 * 
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            要设置的 remark
	 * 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return batchCode
	 */
	public String getBatchCode() {
		return batchCode;
	}

	/**
	 * @param batchCode
	 *            要设置的 batchCode
	 * 
	 */
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	/**
	 * @return isSend
	 */
	public String getIsSend() {
		return isSend;
	}

	/**
	 * @param isSend
	 *            要设置的 isSend
	 * 
	 */
	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	/**
	 * @return isCome
	 */
	public String getIsCome() {
		return isCome;
	}

	/**
	 * @param isCome
	 *            要设置的 isCome
	 * 
	 */
	public void setIsCome(String isCome) {
		this.isCome = isCome;
	}

	/**
	 * @return vaList
	 */
	public List<QualityBasicValue> getVaList() {
		return vaList;
	}

	/**
	 * @param vaList
	 *            要设置的 vaList
	 * 
	 */
	public void setVaList(List<QualityBasicValue> vaList) {
		this.vaList = vaList;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getTreatmentResults() {
		return treatmentResults;
	}

	public void setTreatmentResults(String treatmentResults) {
		this.treatmentResults = treatmentResults;
	}

}
