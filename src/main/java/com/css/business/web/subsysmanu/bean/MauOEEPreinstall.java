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
 * @Title: MauOeePreinstall.java
 * @Package com.css.business.web.subsysmanu.bean
 * @Description: OEE 的月份预设
 * @author rb
 * @date 2017年9月29日 下午2:13:21
 * @company SMTC
 */
@Entity
@Table(name = "Mau_Oee_Preinstall")
public class MauOEEPreinstall implements BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = -4205701578308572069L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_Mau_Oee_Preinstall", allocationSize = 1, initialValue = 1, sequenceName = "seq_Mau_Oee_Preinstall")
	@GeneratedValue(generator = "seq_Mau_Oee_Preinstall", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "seq_name")
	private String seqName;
	/** 设定的月份 */
	@Column(name = "set_month")
	private Integer setMonth;
	/** 设定月份的oee */
	@Column(name = "set_month_oee")
	private Double setMonthOee;
	/** 设定月份的正品率 */
	@Column(name = "set_month_qr")
	private Double setMonthQR;
	/** 设定月份的开机率 */
	@Column(name = "set_month_or")
	private Double setMonthOR;
	/** 设定月份的速度满载率 */
	@Column(name = "set_month_pr")
	private Double setMonthPR;

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

	public Integer getSetMonth() {
		return setMonth;
	}

	public void setSetMonth(Integer setMonth) {
		this.setMonth = setMonth;
	}

	

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	/**
	 * @return setMonthOee
	 */
	public Double getSetMonthOee() {
		return setMonthOee;
	}

	/**
	 * @param setMonthOee
	 *            要设置的 setMonthOee
	 * 
	 */
	public void setSetMonthOee(Double setMonthOee) {
		this.setMonthOee = setMonthOee;
	}

	/**
	 * @return setMonthQR
	 */
	public Double getSetMonthQR() {
		return setMonthQR;
	}

	/**
	 * @param setMonthQR
	 *            要设置的 setMonthQR
	 * 
	 */
	public void setSetMonthQR(Double setMonthQR) {
		this.setMonthQR = setMonthQR;
	}

	/**
	 * @return setMonthOR
	 */
	public Double getSetMonthOR() {
		return setMonthOR;
	}

	/**
	 * @param setMonthOR
	 *            要设置的 setMonthOR
	 * 
	 */
	public void setSetMonthOR(Double setMonthOR) {
		this.setMonthOR = setMonthOR;
	}

	/**
	 * @return setMonthPR
	 */
	public Double getSetMonthPR() {
		return setMonthPR;
	}

	/**
	 * @param setMonthPR
	 *            要设置的 setMonthPR
	 * 
	 */
	public void setSetMonthPR(Double setMonthPR) {
		this.setMonthPR = setMonthPR;
	}

}
