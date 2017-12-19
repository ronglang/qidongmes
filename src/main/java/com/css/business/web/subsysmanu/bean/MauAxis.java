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
@Table(name = "mau_axis")
public class MauAxis implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -1469962307382906300L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_course", sequenceName = "seq_pla_course")
	@GeneratedValue(generator = "seq_pla_course", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "rfid")
	private String rfid;
	@Column(name = "axis_color")
	private String axisColor;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "external_diameter")
	private Integer externalDiameter; // 外盘径
	@Column(name = "internal_diameter")
	private Integer internalDiameter; // 内盘径
	@Column(name = "axis_in_width")
	private Integer axisInWidth; // 轴内宽
	@Column(name = "axis_out_width")
	private Integer axisOutWidth; // 轴外宽
	@Column(name = "center_diameter")
	private Integer centerDiameter; // 中心孔直径
	@Column(name = "type")
	private String type;

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

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getAxisColor() {
		return axisColor;
	}

	public void setAxisColor(String axisColor) {
		this.axisColor = axisColor;
	}

	public Integer getExternalDiameter() {
		return externalDiameter;
	}

	public void setExternalDiameter(Integer externalDiameter) {
		this.externalDiameter = externalDiameter;
	}

	public Integer getInternalDiameter() {
		return internalDiameter;
	}

	public void setInternalDiameter(Integer internalDiameter) {
		this.internalDiameter = internalDiameter;
	}

	public Integer getAxisInWidth() {
		return axisInWidth;
	}

	public void setAxisInWidth(Integer axisInWidth) {
		this.axisInWidth = axisInWidth;
	}

	public Integer getAxisOutWidth() {
		return axisOutWidth;
	}

	public void setAxisOutWidth(Integer axisOutWidth) {
		this.axisOutWidth = axisOutWidth;
	}

	public Integer getCenterDiameter() {
		return centerDiameter;
	}

	public void setCenterDiameter(Integer centerDiameter) {
		this.centerDiameter = centerDiameter;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
