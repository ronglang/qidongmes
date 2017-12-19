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
 * @Title:CraLsBomParam.java
 * @Description:拉丝BOM参数
 * @author RB
 * @company SMTC
 * @date 2017年10月31日下午4:29:05
 */
@Entity
@Table(name = "Cra_Ls_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Ls_Bom_Param", sequenceName = "seq_Cra_Ls_Bom_Param")
public class CraLsBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -8952631977649603545L;
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

	/** 产品规格 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 工序编码 */
	@Column(name = "seq_code")
	private String seqCode;
	/** 材料 */
	@Column(name = "mater")
	private String mater;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 工作单上检查项目 '软铜:1.外观,2.直径,3.延伸率': */
	@Column(name = "inspection_items")
	private String inspectionItems;
	/** 每轴可出货长度 */
	@Column(name = "oee_slps")
	private Integer oeeSlps;
	/** 延伸率%(如：>25) */
	@Column(name = "elongation_rate")
	private String elongationRate;
	/** 报表每小时平均产量 */
	@Column(name = "apph")
	private String apph;
	/** 预计每小时产量 */
	@Column(name = "ehap")
	private String ehap;

	/** 工作步骤 */
	@Column(name = "seq_step")
	private Integer seqStep;
	/** 总轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;

	/** 目标直径 */
	@Column(name = "target_diameter")
	private Float targetDiameter;
	/** 最小直径 */
	@Column(name = "min_diameter")
	private Float minDiameter;
	/** 最大直径 */
	@Column(name = "max_diameter")
	private Float maxDiameter;
	/** 标准线径 */
	@Column(name = "stand_diameter")
	private Float standDiameter;
	/** 标准重量 */
	@Column(name = "oee_stand_weight")
	private Float oeeStandWeight;

	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;

	/** 每公里用量(kg) */
	@Column(name = "use_per_kilometer")
	private Float usePerKilometer;

	/** 铝芯形状(圆形/扇形) */
	@Column(name = "al_shape")
	private Float alShape;

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

	public String getMater() {
		return mater;
	}

	public void setMater(String mater) {
		this.mater = mater;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInspectionItems() {
		return inspectionItems;
	}

	public void setInspectionItems(String inspectionItems) {
		this.inspectionItems = inspectionItems;
	}

	public Integer getOeeSlps() {
		return oeeSlps;
	}

	public void setOeeSlps(Integer oeeSlps) {
		this.oeeSlps = oeeSlps;
	}

	public String getElongationRate() {
		return elongationRate;
	}

	public void setElongationRate(String elongationRate) {
		this.elongationRate = elongationRate;
	}

	public String getApph() {
		return apph;
	}

	public void setApph(String apph) {
		this.apph = apph;
	}

	public String getEhap() {
		return ehap;
	}

	public void setEhap(String ehap) {
		this.ehap = ehap;
	}

	public Integer getSeqStep() {
		return seqStep;
	}

	public void setSeqStep(Integer seqStep) {
		this.seqStep = seqStep;
	}

	public Integer getAxisNum() {
		return axisNum;
	}

	public void setAxisNum(Integer axisNum) {
		this.axisNum = axisNum;
	}

	public Float getTargetDiameter() {
		return targetDiameter;
	}

	public void setTargetDiameter(Float targetDiameter) {
		this.targetDiameter = targetDiameter;
	}

	public Float getMinDiameter() {
		return minDiameter;
	}

	public void setMinDiameter(Float minDiameter) {
		this.minDiameter = minDiameter;
	}

	public Float getMaxDiameter() {
		return maxDiameter;
	}

	public void setMaxDiameter(Float maxDiameter) {
		this.maxDiameter = maxDiameter;
	}

	public Float getStandDiameter() {
		return standDiameter;
	}

	public void setStandDiameter(Float standDiameter) {
		this.standDiameter = standDiameter;
	}

	public Float getOeeStandWeight() {
		return oeeStandWeight;
	}

	public void setOeeStandWeight(Float oeeStandWeight) {
		this.oeeStandWeight = oeeStandWeight;
	}

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

	public Float getAlShape() {
		return alShape;
	}

	public void setAlShape(Float alShape) {
		this.alShape = alShape;
	}

}
