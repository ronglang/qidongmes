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
 * @Title:CraJjBomParam.java
 * @Description:集绞BOM
 * @author RB
 * @company SMTC
 * @date 2017年10月31日下午8:57:45
 */
/**
 * @Title:CraJjBomParam.java
 * @Description:TODO(用一句话描述)
 * @author RB
 * @company SMTC
 * @date 2017年11月1日下午2:58:14
 */
@Entity
@Table(name = "Cra_Jj_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Jj_Bom_Param", sequenceName = "seq_Cra_Jj_Bom_Param")
public class CraJjBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -6011172286867368579L;
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

	/** 绞线方向 */
	@Column(name = "direction")
	private String direction;
	/** 集绞方式:(蓝/棕/黑/灰 包一层pp带中间加填充) */
	@Column(name = "type")
	private String type;
	/** 材料 */
	@Column(name = "mater")
	private String mater;

	/** 芯线条数 */
	@Column(name = "core_num")
	private Integer coreNum;
	/** 集合长度 */
	@Column(name = "gather_length")
	private Integer gatherLength;
	/** 覆盖率(%) */
	@Column(name = "coverage_rate")
	private Integer coverageRate;
	/** 带重(kg) */
	@Column(name = "bag_weight")
	private Integer bagWeight;

	/** 正常外径(mm) */
	@Column(name = "normal_diameter")
	private Float normalDiameter;
	/** 最大外径(mm) */
	@Column(name = "max_diameter")
	private Float maxDiameter;
	/** 缠包带厚度(mm) */
	@Column(name = "bag_thickness")
	private Float bagThickness;
	/** 实际宽度(mm) */
	@Column(name = "fact_width")
	private Float factWidth;
	/** 计算宽度(mm) */
	@Column(name = "pla_width")
	private Float plaWidth;
	/** 绞入率( 非% 例如 1.005) */
	@Column(name = "in_rate")
	private Float inRate;
	/** 绞距(mm) */
	@Column(name = "lay")
	private Float lay;
	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;

	/** 轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;
	
	/** 每公里用量(kg)*/
	@Column(name = "use_per_kilometer")
	private Float usePerKilometer;

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

	public String getMater() {
		return mater;
	}

	public void setMater(String mater) {
		this.mater = mater;
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

	public Integer getCoverageRate() {
		return coverageRate;
	}

	public void setCoverageRate(Integer coverageRate) {
		this.coverageRate = coverageRate;
	}

	public Integer getBagWeight() {
		return bagWeight;
	}

	public void setBagWeight(Integer bagWeight) {
		this.bagWeight = bagWeight;
	}

	public Float getNormalDiameter() {
		return normalDiameter;
	}

	public void setNormalDiameter(Float normalDiameter) {
		this.normalDiameter = normalDiameter;
	}

	public Float getMaxDiameter() {
		return maxDiameter;
	}

	public void setMaxDiameter(Float maxDiameter) {
		this.maxDiameter = maxDiameter;
	}

	public Float getBagThickness() {
		return bagThickness;
	}

	public void setBagThickness(Float bagThickness) {
		this.bagThickness = bagThickness;
	}

	public Float getFactWidth() {
		return factWidth;
	}

	public void setFactWidth(Float factWidth) {
		this.factWidth = factWidth;
	}

	public Float getPlaWidth() {
		return plaWidth;
	}

	public void setPlaWidth(Float plaWidth) {
		this.plaWidth = plaWidth;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Float getLay() {
		return lay;
	}

	public void setLay(Float lay) {
		this.lay = lay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
