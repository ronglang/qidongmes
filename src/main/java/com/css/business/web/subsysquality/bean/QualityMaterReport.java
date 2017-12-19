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
 * @Title: QualityMaterReport.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: new原材料质检表
 * @author rb
 * @date 2017年8月30日 下午11:55:15
 * @company SMTC
 */
@Entity
@Table(name = "Quality_Mater_Report")
@SequenceGenerator(name = "SEQ_Quality_Mater_Report", sequenceName = "SEQ_Quality_Mater_Report")
public class QualityMaterReport implements BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = 949178174462772087L;
	@Transient
	public static String STATE_NO_START = "未检测";
	@Transient
	public static String STATE_STARTING = "检测中";
	@Transient
	public static String STATE_IS_END = "已检测";
	@Transient
	public static String IS_COME = "已入库";
	@Transient
	public static String IS_NOT_COME = "未入库";
	@Transient
	public static String RESULT_IS_PASS = "合格";
	@Transient
	public static String RESULT_NOT_PASS = "不合格";
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
	/** 保质期 */
	@Column(name = "release_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date releaseDate;
	/** 来料时间 */
	@Column(name = "come_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date comeDate;
	/** 有效期 */
	@Column(name = "expiry_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date expiryDate;
	/** 原料名称 */
	@Column(name = "mater_name")
	private String materName;
	/** 原料规格 */
	@Column(name = "mater_ggxh")
	private String materGgxh;
	/** 供应商 */
	@Column(name = "mater_firm")
	private String materFirm;
	/** 批次号 */
	@Column(name = "mater_batch")
	private String materBatch;
	/** 数量 */
	@Column(name = "mater_amount")
	private Integer materAmount;
	/** 单位 */
	@Column(name = "unit")
	private String unit;
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
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 是否入库 */
	@Column(name = "is_come")
	private String isCome;
	/** 物料类型：(如铜杆，金属箔，铁丝，胶料)物料类型：(如铜杆，金属箔，铁丝，胶料) */
	@Column(name = "mater_type")
	private String materType;

	/** 质检保存本批次的rfid */
	@Column(name = "rfid_code")
	private String rfidCode;

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

	public String getMaterGgxh() {
		return materGgxh;
	}

	public void setMaterGgxh(String materGgxh) {
		this.materGgxh = materGgxh;
	}

	public String getMaterFirm() {
		return materFirm;
	}

	public void setMaterFirm(String materFirm) {
		this.materFirm = materFirm;
	}

	public String getMaterBatch() {
		return materBatch;
	}

	public void setMaterBatch(String materBatch) {
		this.materBatch = materBatch;
	}

	public Integer getMaterAmount() {
		return materAmount;
	}

	public void setMaterAmount(Integer materAmount) {
		this.materAmount = materAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	/**
	 * @return materName
	 */
	public String getMaterName() {
		return materName;
	}

	/**
	 * @param materName
	 *            要设置的 materName
	 * 
	 */
	public void setMaterName(String materName) {
		this.materName = materName;
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
	 * @return releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 *            要设置的 releaseDate
	 * 
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            要设置的 expiryDate
	 * 
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getMaterType() {
		return materType;
	}

	public void setMaterType(String materType) {
		this.materType = materType;
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

	public Date getComeDate() {
		return comeDate;
	}

	public void setComeDate(Date comeDate) {
		this.comeDate = comeDate;
	}

	public String getRfidCode() {
		return rfidCode;
	}

	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

}
