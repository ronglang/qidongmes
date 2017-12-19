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
 * @Title:CraJyBomParam.java
 * @Description:绝缘BOM参数
 * @author RB
 * @company SMTC
 * @date 2017年10月31日下午4:17:05
 */
@Entity
@Table(name = "Cra_Jy_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Jy_Bom_Param", sequenceName = "seq_Cra_Jy_Bom_Param")
public class CraJyBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 8631844683115752138L;
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
	/** 工作单上检查项目 1.外观,2.直径,3.绝缘厚度,最薄点,4.火花(AC7kv),抗张力延伸,5.印字 */
	@Column(name = "inspection_items")
	private String inspectionItems;
	/** 每轴可出货长度 */
	@Column(name = "oee_slps")
	private Integer oeeSlps;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 绝缘类型(如:导体内屏,绝缘层,绝缘外层) */
	@Column(name = "jy_type")
	private String jyType;
	/** 同心度(如:>70%) */
	@Column(name = "concentricity")
	private String concentricity;

	/** 工作步骤 */
	@Column(name = "seq_step")
	private Integer seqStep;
	/** 总长度 */
	@Column(name = "total_length")
	private Integer totalLength;

	/** 芯线每色长度(M) */
	@Column(name = "core_length_per_color")
	private Float coreLengthPerColor;
	/** 每公里用量(kg) */
	@Column(name = "use_per_kilometer")
	private Float usePerKilometer;
	/** 绝缘重量(kg) */
	@Column(name = "jy_weight")
	private Float jyWeight;
	/** 平均厚度(mm) */
	@Column(name = "ave_thickness")
	private Float aveThickness;
	/** 最薄点 */
	@Column(name = "thinnest_point")
	private Float thinnestPoint;
	/** 正常外径(mm) */
	@Column(name = "normal_diameter")
	private String normalDiameter;
	/** 最大外径(mm) */
	@Column(name = "max_diameter")
	private String maxDiameter;
	/** 标准重量 */
	@Column(name = "oee_stand_weight")
	private Float oeeStandWeight;

	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getJyType() {
		return jyType;
	}

	public void setJyType(String jyType) {
		this.jyType = jyType;
	}

	public String getConcentricity() {
		return concentricity;
	}

	public void setConcentricity(String concentricity) {
		this.concentricity = concentricity;
	}

	public Integer getSeqStep() {
		return seqStep;
	}

	public void setSeqStep(Integer seqStep) {
		this.seqStep = seqStep;
	}

	public Integer getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}

	public Float getCoreLengthPerColor() {
		return coreLengthPerColor;
	}

	public void setCoreLengthPerColor(Float coreLengthPerColor) {
		this.coreLengthPerColor = coreLengthPerColor;
	}

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

	public Float getJyWeight() {
		return jyWeight;
	}

	public void setJyWeight(Float jyWeight) {
		this.jyWeight = jyWeight;
	}

	public Float getAveThickness() {
		return aveThickness;
	}

	public void setAveThickness(Float aveThickness) {
		this.aveThickness = aveThickness;
	}

	public Float getThinnestPoint() {
		return thinnestPoint;
	}

	public void setThinnestPoint(Float thinnestPoint) {
		this.thinnestPoint = thinnestPoint;
	}

	public String getNormalDiameter() {
		return normalDiameter;
	}

	public void setNormalDiameter(String normalDiameter) {
		this.normalDiameter = normalDiameter;
	}

	public String getMaxDiameter() {
		return maxDiameter;
	}

	public void setMaxDiameter(String maxDiameter) {
		this.maxDiameter = maxDiameter;
	}

	public Float getOeeStandWeight() {
		return oeeStandWeight;
	}

	public void setOeeStandWeight(Float oeeStandWeight) {
		this.oeeStandWeight = oeeStandWeight;
	}

}
