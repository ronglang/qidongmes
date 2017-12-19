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
 * @Title:CraHtBomParam.java
 * @Description:护套(内外护套)BOM
 * @author RB
 * @company SMTC
 * @date 2017年11月1日上午8:54:32
 */
@Entity
@Table(name = "Cra_Ht_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Ht_Bom_Param", sequenceName = "seq_Cra_Ht_Bom_Param")
public class CraHtBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 3177601807951936899L;
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

	/** 护套颜色 */
	@Column(name = "color")
	private String color;
	/** 外护套类型(如:喷工作单号+轴号+米数) */
	@Column(name = "ht_type")
	private String htType;
	/** 护套层(内/外) */
	@Column(name = "ht_floor")
	private String htFloor;

	/** 平均厚度(mm) */
	@Column(name = "ave_thick")
	private Float aveThick;
	/** 最薄点(mm) */
	@Column(name = "min_thick")
	private Float minThick;
	/** 正常外径(mm) */
	@Column(name = "normal_diameter")
	private Float normalDiameter;
	/** 最大外径(mm) */
	@Column(name = "max_diameter")
	private Float maxDiameter;
	/**  */
	@Column(name = "expendKm")
	private Float expend_km;
	/** 外护套总耗用量(kg) */
	@Column(name = "totalMaterExpend")
	private Float total_mater_expend;
	/** 每公里重量(kg) */
	@Column(name = "per_km_weight")
	private Float perKmWeight;

	/** 轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;

	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;
	
	/** 每公里用量(kg)*/
	@Column(name = "use_per_kilometer")
	private Float usePerKilometer;
	/** 护套材料*/
	@Column(name="mater")
	private String mater;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getHtType() {
		return htType;
	}

	public void setHtType(String htType) {
		this.htType = htType;
	}

	public String getHtFloor() {
		return htFloor;
	}

	public void setHtFloor(String htFloor) {
		this.htFloor = htFloor;
	}

	public Float getAveThick() {
		return aveThick;
	}

	public void setAveThick(Float aveThick) {
		this.aveThick = aveThick;
	}

	public Float getMinThick() {
		return minThick;
	}

	public void setMinThick(Float minThick) {
		this.minThick = minThick;
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

	public Float getExpend_km() {
		return expend_km;
	}

	public void setExpend_km(Float expend_km) {
		this.expend_km = expend_km;
	}

	public Float getTotal_mater_expend() {
		return total_mater_expend;
	}

	public void setTotal_mater_expend(Float total_mater_expend) {
		this.total_mater_expend = total_mater_expend;
	}

	public Float getPerKmWeight() {
		return perKmWeight;
	}

	public void setPerKmWeight(Float perKmWeight) {
		this.perKmWeight = perKmWeight;
	}

	public Integer getAxisNum() {
		return axisNum;
	}

	public void setAxisNum(Integer axisNum) {
		this.axisNum = axisNum;
	}

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

	public String getMater() {
		return mater;
	}

	public void setMater(String mater) {
		this.mater = mater;
	}

}
