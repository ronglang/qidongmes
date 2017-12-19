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
 * @Title:CraDjBomParam.java
 * @Description:对绞BOM
 * @author RB
 * @company SMTC
 * @date 2017年11月1日下午4:14:01
 */
@Entity
@Table(name = "Cra_dj_Bom_Param")
@SequenceGenerator(name = "seq_Cra_dj_Bom_Param", sequenceName = "seq_Cra_dj_Bom_Param")
public class CraDjBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 5869962553277229240L;
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
	/** 标准重量(KG/m) */
	@Column(name = "oee_stand_weight")
	private Float oeeStandWeight;
	/** OEE项目：每轴可出货长度 */
	@Column(name = "oee_slps")
	private Integer oeeSlps;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 工作单上检查项目 */
	@Column(name = "inspection_items")
	private String inspectionItems;

	/** 对绞类型 */
	@Column(name = "type")
	private String type;
	/** 材料 */
	@Column(name = "mater")
	private String mater;
	/** 绞向 */
	@Column(name = "direction")
	private String direction;

	/** 芯线条数 */
	@Column(name = "core_num")
	private Integer coreNum;
	/** 集合长度 */
	@Column(name = "gather_length")
	private Integer gatherLength;
	/** 轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;
	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;

	/** 绞距 */
	@Column(name = "lay")
	private Float lay;
	/** 完成直径 */
	@Column(name = "diameter")
	private Float diameter;

	/** 最大外径 */
	@Column(name = "max_diameter")
	private Float maxDiameter;
	/** 正常外径 */
	@Column(name = "normal_diameter")
	private Float normalDiameter;
	/** 绞入率( 非% 例如 1.005) */
	@Column(name = "in_rate")
	private Float inRate;
	/** 每公里用量(kg)*/
	@Column(name = "use_per_kilometer")
	private Float usePerKilometer;

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

	public Float getOeeStandWeight() {
		return oeeStandWeight;
	}

	public void setOeeStandWeight(Float oeeStandWeight) {
		this.oeeStandWeight = oeeStandWeight;
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

	public String getInspectionItems() {
		return inspectionItems;
	}

	public void setInspectionItems(String inspectionItems) {
		this.inspectionItems = inspectionItems;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMater() {
		return mater;
	}

	public void setMater(String mater) {
		this.mater = mater;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getCoreNum() {
		return coreNum;
	}

	public void setCoreNum(Integer coreNum) {
		this.coreNum = coreNum;
	}

	public Integer getGatherLength() {
		return gatherLength;
	}

	public void setGatherLength(Integer gatherLength) {
		this.gatherLength = gatherLength;
	}

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

	public Float getLay() {
		return lay;
	}

	public void setLay(Float lay) {
		this.lay = lay;
	}

	public Float getDiameter() {
		return diameter;
	}

	public void setDiameter(Float diameter) {
		this.diameter = diameter;
	}

	public Float getMaxDiameter() {
		return maxDiameter;
	}

	public void setMaxDiameter(Float maxDiameter) {
		this.maxDiameter = maxDiameter;
	}

	public Float getNormalDiameter() {
		return normalDiameter;
	}

	public void setNormalDiameter(Float normalDiameter) {
		this.normalDiameter = normalDiameter;
	}

	public Float getInRate() {
		return inRate;
	}

	public void setInRate(Float inRate) {
		this.inRate = inRate;
	}

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

}
