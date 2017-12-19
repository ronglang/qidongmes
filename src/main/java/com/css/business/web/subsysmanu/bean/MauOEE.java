package com.css.business.web.subsysmanu.bean;

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
 * @Title: MauOEE.java
 * @Package com.css.business.web.subsysmanu.bean
 * @Description: OEE分析
 * @author rb
 * @date 2017年8月29日 下午2:09:00
 * @company SMTC
 */
@Entity
@Table(name = "mau_oee")
public class MauOEE implements BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = 8923515576120559891L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_oee", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_oee")
	@GeneratedValue(generator = "seq_mau_oee", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;

	@Column(name = "start_time")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startTime;
	@Column(name = "end_time")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endTime;
	/** 机器编号 */
	@Column(name = "mac_code")
	private String macCode;
	/** 工序名称 */
	@Column(name = "seq_name")
	private String seqName;
	/** 产品规格 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 本次生产的计划ids */
	@Column(name = "pass_plan_ids")
	private String passPlanIds;
	/** 主操作手 */
	@Column(name = "main_operator")
	private String mainOperator;
	/** 副操作手 */
	@Column(name = "second_operator")
	private String secondOperator;
	/** 实际产出 */
	@Column(name = "fact_output")
	private Double factOutput;
	/** 不良品 */
	@Column(name = "rejects")
	private Integer rejects;
	/** 零碎品 */
	@Column(name = "bits_pieces")
	private Integer bitsPieces;
	/** 过量消耗 */
	@Column(name = "overdoes")
	private Integer overdoes;
	/** 开机分钟数 */
	@Column(name = "start_min")
	private Integer startMin;
	/** 停机分钟数 */
	@Column(name = "end_min")
	private Integer endMin;
	/** 满载速度 */
	@Column(name = "fully_speed")
	private Float fullySpeed;
	/** 满载输出 */
	@Column(name = "fully_output")
	private Double fullyOutput;
	/** 满速度满载率输出 PR */
	@Column(name = "rate_speed")
	private Double rateSpeed;
	/** 开机率 OR */
	@Column(name = "rate_start")
	private Double rateStart;
	/** 正品率 QR */
	@Column(name = "rate_quality")
	private Double rateQuality;
	/** OEE */
	@Column(name = "oee")
	private Double oee;
	/** 所属月份 （yyyyMM） */
	@Column(name = "in_month")
	private Integer inMonth;
	/** 工单编号 */
	@Column(name = "course_code")
	private String courseCode;

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
	 * @return startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            要设置的 startTime
	 * 
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            要设置的 endTime
	 * 
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * @return passPlanIds
	 */
	public String getPassPlanIds() {
		return passPlanIds;
	}

	/**
	 * @param passPlanIds
	 *            要设置的 passPlanIds
	 * 
	 */
	public void setPassPlanIds(String passPlanIds) {
		this.passPlanIds = passPlanIds;
	}

	/**
	 * @return mainOperator
	 */
	public String getMainOperator() {
		return mainOperator;
	}

	/**
	 * @param mainOperator
	 *            要设置的 mainOperator
	 * 
	 */
	public void setMainOperator(String mainOperator) {
		this.mainOperator = mainOperator;
	}

	/**
	 * @return secondOperator
	 */
	public String getSecondOperator() {
		return secondOperator;
	}

	/**
	 * @param secondOperator
	 *            要设置的 secondOperator
	 * 
	 */
	public void setSecondOperator(String secondOperator) {
		this.secondOperator = secondOperator;
	}

	public Double getFactOutput() {
		return factOutput;
	}

	public void setFactOutput(Double factOutput) {
		this.factOutput = factOutput;
	}

	/**
	 * @return rejects
	 */
	public Integer getRejects() {
		return rejects;
	}

	/**
	 * @param rejects
	 *            要设置的 rejects
	 * 
	 */
	public void setRejects(Integer rejects) {
		this.rejects = rejects;
	}

	/**
	 * @return bitsPieces
	 */
	public Integer getBitsPieces() {
		return bitsPieces;
	}

	/**
	 * @param bitsPieces
	 *            要设置的 bitsPieces
	 * 
	 */
	public void setBitsPieces(Integer bitsPieces) {
		this.bitsPieces = bitsPieces;
	}

	/**
	 * @return overdoes
	 */
	public Integer getOverdoes() {
		return overdoes;
	}

	/**
	 * @param overdoes
	 *            要设置的 overdoes
	 * 
	 */
	public void setOverdoes(Integer overdoes) {
		this.overdoes = overdoes;
	}

	/**
	 * @return startMin
	 */
	public Integer getStartMin() {
		return startMin;
	}

	/**
	 * @param startMin
	 *            要设置的 startMin
	 * 
	 */
	public void setStartMin(Integer startMin) {
		this.startMin = startMin;
	}

	/**
	 * @return endMin
	 */
	public Integer getEndMin() {
		return endMin;
	}

	/**
	 * @param endMin
	 *            要设置的 endMin
	 * 
	 */
	public void setEndMin(Integer endMin) {
		this.endMin = endMin;
	}

	public Float getFullySpeed() {
		return fullySpeed;
	}

	public void setFullySpeed(Float fullySpeed) {
		this.fullySpeed = fullySpeed;
	}

	public Double getFullyOutput() {
		return fullyOutput;
	}

	public void setFullyOutput(Double fullyOutput) {
		this.fullyOutput = fullyOutput;
	}

	/**
	 * @return rateSpeed
	 */
	public Double getRateSpeed() {
		return rateSpeed;
	}

	/**
	 * @param rateSpeed
	 *            要设置的 rateSpeed
	 * 
	 */
	public void setRateSpeed(Double rateSpeed) {
		this.rateSpeed = rateSpeed;
	}

	/**
	 * @return rateStart
	 */
	public Double getRateStart() {
		return rateStart;
	}

	/**
	 * @param rateStart
	 *            要设置的 rateStart
	 * 
	 */
	public void setRateStart(Double rateStart) {
		this.rateStart = rateStart;
	}

	/**
	 * @return rateQuality
	 */
	public Double getRateQuality() {
		return rateQuality;
	}

	/**
	 * @param rateQuality
	 *            要设置的 rateQuality
	 * 
	 */
	public void setRateQuality(Double rateQuality) {
		this.rateQuality = rateQuality;
	}

	/**
	 * @return oee
	 */
	public Double getOee() {
		return oee;
	}

	/**
	 * @param oee
	 *            要设置的 oee
	 * 
	 */
	public void setOee(Double oee) {
		this.oee = oee;
	}

	public Integer getInMonth() {
		return inMonth;
	}

	public void setInMonth(Integer inMonth) {
		this.inMonth = inMonth;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}
