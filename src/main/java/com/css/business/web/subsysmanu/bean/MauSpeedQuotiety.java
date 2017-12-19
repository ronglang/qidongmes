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

@Entity
@Table(name = "mau_speed_quotiety")
public class MauSpeedQuotiety implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -8049302633639566095L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_speed_quotiety", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_speed_quotiety")
	@GeneratedValue(generator = "seq_mau_speed_quotiety", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "quotiety")
	private Float quotiety;
	@Column(name = "speed")
	private Float speed;
	@Column(name = "mater")
	private String mater;
	@Column(name = "target_diameter")
	private Float targetDiameter;

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

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public Float getQuotiety() {
		return quotiety;
	}

	public void setQuotiety(Float quotiety) {
		this.quotiety = quotiety;
	}

	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public String getMater() {
		return mater;
	}

	public void setMater(String mater) {
		this.mater = mater;
	}

	public Float getTargetDiameter() {
		return targetDiameter;
	}

	public void setTargetDiameter(Float targetDiameter) {
		this.targetDiameter = targetDiameter;
	}

}
