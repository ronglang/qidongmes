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
 * @Title:CraFrBomParam.java
 * @Description:复绕BOM
 * @author RB
 * @company SMTC
 * @date 2017年11月1日上午9:27:55
 */
@Entity
@Table(name = "Cra_Fr_Bom_Param")
@SequenceGenerator(name = "seq_Cra_FR_Bom_Param", sequenceName = "seq_Cra_FR_Bom_Param")
public class CraFrBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 1083717611920899873L;
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
	/** 工序编码 */
	@Column(name = "seq_code")
	private String seqCode;
	/** 工序步骤 */
	@Column(name = "seq_step")
	private Integer seqStep;
	/** 机台号 */
	@Column(name = "mac_code")
	private String macCode;
	/** 报表每小时平均产量(m) */
	@Column(name = "rhao")
	private Integer rhao;
	/** 预计每小时产量 */
	@Column(name = "estimated_output_h")
	private Integer estimatedOutputH;
	/** 预计复绕所需时间 */
	@Column(name = "estimated_fr_hour")
	private Integer estimatedFrHour;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 复绕长度(m) */
	@Column(name = "fr_length")
	private Float frLength;

	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;

	/** 轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;

	public Integer getAxisNum() {
		return axisNum;
	}

	public void setAxisNum(Integer axisNum) {
		this.axisNum = axisNum;
	}

	public Integer getDebugLength() {
		return debugLength;
	}

	public void setDebugLength(Integer debugLength) {
		this.debugLength = debugLength;
	}

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

	public Integer getSeqStep() {
		return seqStep;
	}

	public void setSeqStep(Integer seqStep) {
		this.seqStep = seqStep;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public Integer getRhao() {
		return rhao;
	}

	public void setRhao(Integer rhao) {
		this.rhao = rhao;
	}

	public Integer getEstimatedOutputH() {
		return estimatedOutputH;
	}

	public void setEstimatedOutputH(Integer estimatedOutputH) {
		this.estimatedOutputH = estimatedOutputH;
	}

	public Integer getEstimatedFrHour() {
		return estimatedFrHour;
	}

	public void setEstimatedFrHour(Integer estimatedFrHour) {
		this.estimatedFrHour = estimatedFrHour;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Float getFrLength() {
		return frLength;
	}

	public void setFrLength(Float frLength) {
		this.frLength = frLength;
	}

}
