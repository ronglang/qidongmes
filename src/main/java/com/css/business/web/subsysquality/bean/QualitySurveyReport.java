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
 * @Title: QualitySurveyReport.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author rb
 * @date 2017年8月5日 下午3:07:24
 * @company SMTC
 */
@Entity
@Table(name = "Quality_Survey_Report")
@SequenceGenerator(name = "SEQ_Quality_Survey_Report", sequenceName = "SEQ_Quality_Survey_Report")
public class QualitySurveyReport implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 2997555159942449379L;

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
	/** 质检日期 */
	@Column(name = "survey_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date surveyDate;
	/** 物料类型 */
	@Column(name = "obj_sort")
	private String objSort;
	/** 数量 */
	@Column(name = "acount")
	private Double acount;
	/** 纸质质检报告单单号 */
	@Column(name = "survey_report_code")
	private String surveyReportCode;
	/** 单位 */
	@Column(name = "unit")
	private String unit;
	/** 供货商 */
	@Column(name = "supply_agent")
	private String supplyAgent;
	/** 批次号 */
	@Column(name = "batch_code")
	private String batchCode;
	/** 是否有出厂检验报告 */
	@Column(name = "is_supply_surey")
	private String isSupplySurey;
	/** 物料规格型号 */
	@Column(name = "obj_ggxh")
	private String objGgxh;
	/** 物料颜色 */
	@Column(name = "obj_color")
	private String objColor;
	/** RFID */
	@Column(name = "rfid_code")
	private String rfidCode;
	/** 检验结果 */
	@Column(name = "report_result")
	private String reportResult;
	/** 报告图片的url */
	@Column(name = "report_url")
	private String reportUrl;
	/** 检验状态 */
	@Column(name = "report_state")
	private String reportState;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 有效期 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "expiry_date")
	private Date expiryDate;
	/** 保质期 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "release_date")
	private Date releaseDate;
	/** 是否入库(已入库,未入库) */
	@Column(name = "is_come")
	private String isCome;
	/** 物料名称 */
	@Column(name = "obj_name")
	private String objName;
	/** 检测人 */
	@Column(name = "survey_by")
	private String surveyBy;
	/** 系统自动生产的系统单号 */
	@Column(name = "survey_code")
	private String surveyCode;

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
	 * @return surveyDate
	 */
	public Date getSurveyDate() {
		return surveyDate;
	}

	/**
	 * @param surveyDate
	 *            要设置的 surveyDate
	 * 
	 */
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	/**
	 * @return objSort
	 */
	public String getObjSort() {
		return objSort;
	}

	/**
	 * @param objSort
	 *            要设置的 objSort
	 * 
	 */
	public void setObjSort(String objSort) {
		this.objSort = objSort;
	}

	/**
	 * @return acount
	 */
	public Double getAcount() {
		return acount;
	}

	/**
	 * @param acount
	 *            要设置的 acount
	 * 
	 */
	public void setAcount(Double acount) {
		this.acount = acount;
	}

	/**
	 * @return surveyReportCode
	 */
	public String getSurveyReportCode() {
		return surveyReportCode;
	}

	/**
	 * @param surveyReportCode
	 *            要设置的 surveyReportCode
	 * 
	 */
	public void setSurveyReportCode(String surveyReportCode) {
		this.surveyReportCode = surveyReportCode;
	}

	/**
	 * @return unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            要设置的 unit
	 * 
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return supplyAgent
	 */
	public String getSupplyAgent() {
		return supplyAgent;
	}

	/**
	 * @param supplyAgent
	 *            要设置的 supplyAgent
	 * 
	 */
	public void setSupplyAgent(String supplyAgent) {
		this.supplyAgent = supplyAgent;
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
	 * @return isSupplySurey
	 */
	public String getIsSupplySurey() {
		return isSupplySurey;
	}

	/**
	 * @param isSupplySurey
	 *            要设置的 isSupplySurey
	 * 
	 */
	public void setIsSupplySurey(String isSupplySurey) {
		this.isSupplySurey = isSupplySurey;
	}

	/**
	 * @return objGgxh
	 */
	public String getObjGgxh() {
		return objGgxh;
	}

	/**
	 * @param objGgxh
	 *            要设置的 objGgxh
	 * 
	 */
	public void setObjGgxh(String objGgxh) {
		this.objGgxh = objGgxh;
	}

	/**
	 * @return objColor
	 */
	public String getObjColor() {
		return objColor;
	}

	/**
	 * @param objColor
	 *            要设置的 objColor
	 * 
	 */
	public void setObjColor(String objColor) {
		this.objColor = objColor;
	}

	/**
	 * @return rfidCode
	 */
	public String getRfidCode() {
		return rfidCode;
	}

	/**
	 * @param rfidCode
	 *            要设置的 rfidCode
	 * 
	 */
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	/**
	 * @return reportResult
	 */
	public String getReportResult() {
		return reportResult;
	}

	/**
	 * @param reportResult
	 *            要设置的 reportResult
	 * 
	 */
	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
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
	 * @return reportState
	 */
	public String getReportState() {
		return reportState;
	}

	/**
	 * @param reportState
	 *            要设置的 reportState
	 * 
	 */
	public void setReportState(String reportState) {
		this.reportState = reportState;
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
	 * @return objName
	 */
	public String getObjName() {
		return objName;
	}

	/**
	 * @param objName
	 *            要设置的 objName
	 * 
	 */
	public void setObjName(String objName) {
		this.objName = objName;
	}

	/**
	 * @return surveyBy
	 */
	public String getSurveyBy() {
		return surveyBy;
	}

	/**
	 * @param surveyBy
	 *            要设置的 surveyBy
	 * 
	 */
	public void setSurveyBy(String surveyBy) {
		this.surveyBy = surveyBy;
	}

	/**
	 * @return surveyCode
	 */
	public String getSurveyCode() {
		return surveyCode;
	}

	/**
	 * @param surveyCode
	 *            要设置的 surveyCode
	 * 
	 */
	public void setSurveyCode(String surveyCode) {
		this.surveyCode = surveyCode;
	}

}