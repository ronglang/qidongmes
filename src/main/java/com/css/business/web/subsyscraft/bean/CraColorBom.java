package com.css.business.web.subsyscraft.bean;

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
 * 
 * @Title:CraColorBom.java
 * @Description:绝缘，复绕等工序芯线颜色/成品颜色对照表
 * @author RB
 * @company SMTC
 * @date 2017年10月31日下午3:50:28
 */
@Entity
@Table(name = "Cra_Color_Bom")
@SequenceGenerator(name = "seq_Cra_Color_Bom", sequenceName = "seq_Cra_Color_Bom")
public class CraColorBom implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -3729262202658517696L;

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

	/** 产品规格型号(通过规格信号与工序BOM参数表关联) */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 参数所属工序 */
	@Column(name = "seq_code")
	private String seqCode;
	/** 每色重量 */
	@Column(name = "weight_per_color")
	private Double weightPerColor;
	/** 颜色 */
	@Column(name = "color")
	private String color;
	/** 轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	@Column(name = "seq_step")
	private Integer seqStep;

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

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public Double getWeightPerColor() {
		return weightPerColor;
	}

	public void setWeightPerColor(Double weightPerColor) {
		this.weightPerColor = weightPerColor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getAxisNum() {
		return axisNum;
	}

	public void setAxisNum(Integer axisNum) {
		this.axisNum = axisNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSeqStep() {
		return seqStep;
	}

	public void setSeqStep(Integer seqStep) {
		this.seqStep = seqStep;
	}

}
