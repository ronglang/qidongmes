package com.css.business.web.subsysmanu.bean;

import java.math.BigDecimal;
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
 * @Title: MauOEEMonth.java
 * @Package com.css.business.web.subsysmanu.bean
 * @Description: oee 月份实际表
 * @author rb
 * @date 2017年9月29日 下午2:34:46
 * @company SMTC
 */
@Entity
@Table(name = "Mau_OEE_Month")
public class MauOEEMonth implements BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = 1490653488360471045L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_Mau_OEE_Month", allocationSize = 1, initialValue = 1, sequenceName = "seq_Mau_OEE_Month")
	@GeneratedValue(generator = "seq_Mau_OEE_Month", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;

	/** 机器编号 */
	@Column(name = "mac_code")
	private String macCode;
	/** 工序名称 */
	@Column(name = "seq_name")
	private String seqName;
	/** 实际产出 */
	@Column(name = "fact_output")
	private BigDecimal factOutput;
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
	/** 满载输出 */
	@Column(name = "fully_output")
	private BigDecimal fullyOutput;
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
	/** 预设OEE */
	@Column(name = "pre_oee")
	private Double preOee;
	/** 月份 */
	@Column(name = "in_month")
	private String inMonth;

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
	 * @return factOutput
	 */
	public BigDecimal getFactOutput() {
		return factOutput;
	}

	/**
	 * @param factOutput
	 *            要设置的 factOutput
	 * 
	 */
	public void setFactOutput(BigDecimal factOutput) {
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

	/**
	 * @return fullyOutput
	 */
	public BigDecimal getFullyOutput() {
		return fullyOutput;
	}

	/**
	 * @param fullyOutput
	 *            要设置的 fullyOutput
	 * 
	 */
	public void setFullyOutput(BigDecimal fullyOutput) {
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

	/**
	 * @return preOee
	 */
	public Double getPreOee() {
		return preOee;
	}

	/**
	 * @param preOee
	 *            要设置的 preOee
	 * 
	 */
	public void setPreOee(Double preOee) {
		this.preOee = preOee;
	}

	public String getInMonth() {
		return inMonth;
	}

	public void setInMonth(String inMonth) {
		this.inMonth = inMonth;
	}

}
