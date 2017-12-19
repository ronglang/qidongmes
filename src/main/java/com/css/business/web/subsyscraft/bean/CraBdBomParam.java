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
 * @Title:CraBdBomParam.java
 * @Description:包带BOM参数
 * @author RB
 * @company SMTC
 * @date 2017年10月31日下午8:42:42
 */
@Entity
@Table(name = "Cra_Bd_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Bd_Bom_Param", sequenceName = "seq_Cra_Bd_Bom_Param")
public class CraBdBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 8289058586778703614L;
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

	/** 包带类型 */
	@Column(name = "bd_type")
	private String bdType;
	/** 包带材料 */
	@Column(name = "mater")
	private String mater;
	/** 包带规格类型 */
	@Column(name = "bd_mater_ggxh")
	private String bdMaterGgxh;
	/** 包带方向 */
	@Column(name = "bd_direction")
	private String bdDirection;

	/** 一层节距 */
	@Column(name = "once_pitch")
	private Float oncePitch;
	/** 包带长度 */
	@Column(name = "bd_length")
	private Float bdLength;
	/** 覆盖率 */
	@Column(name = "coverage_rate")
	private Float coverageRate;
	/** 最小覆盖率 */
	@Column(name = "min_coverage_rate")
	private Float minCoverageRate;
	/** 每千米耗用(kg) */
	@Column(name = "expend_km")
	private Float expendKm;
	/** 完成外径(mm) */
	@Column(name = "overall_diameter")
	private Float overallDiameter;
	/** 最大外径(mm) */
	@Column(name = "max_diameter")
	private Float maxDiameter;
	/** 材料总耗用量(kg) */
	@Column(name = "total_mater_expend")
	private Float totalMaterExpend;
	/** 二层绞距 */
	@Column(name = "twice_distance")
	private Float twiceDistance;
	/** 带重（煅烧云母带专用kg） */
	@Column(name = "bd_weight")
	private Float bdWeight;
	/** 带宽(麦拉带专用mm) */
	@Column(name = "bd_width")
	private Float bdWidth;
	/** 包带厚度(麦拉带专用mm) */
	@Column(name = "bd_thick")
	private Float bdThick;
	/** 层数 */
	@Column(name = "floor_num")
	private Integer floorNum;
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

	public String getInspectionItems() {
		return inspectionItems;
	}

	public void setInspectionItems(String inspectionItems) {
		this.inspectionItems = inspectionItems;
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

	public String getBdType() {
		return bdType;
	}

	public void setBdType(String bdType) {
		this.bdType = bdType;
	}

	public String getBdMater() {
		return mater;
	}

	public void setBdMater(String mater) {
		this.mater = mater;
	}

	public String getBdMaterGgxh() {
		return bdMaterGgxh;
	}

	public void setBdMaterGgxh(String bdMaterGgxh) {
		this.bdMaterGgxh = bdMaterGgxh;
	}

	public String getBdDirection() {
		return bdDirection;
	}

	public void setBdDirection(String bdDirection) {
		this.bdDirection = bdDirection;
	}

	public Float getOncePitch() {
		return oncePitch;
	}

	public void setOncePitch(Float oncePitch) {
		this.oncePitch = oncePitch;
	}

	public Float getBdLength() {
		return bdLength;
	}

	public void setBdLength(Float bdLength) {
		this.bdLength = bdLength;
	}

	public Float getCoverageRate() {
		return coverageRate;
	}

	public void setCoverageRate(Float coverageRate) {
		this.coverageRate = coverageRate;
	}

	public Float getMinCoverageRate() {
		return minCoverageRate;
	}

	public void setMinCoverageRate(Float minCoverageRate) {
		this.minCoverageRate = minCoverageRate;
	}

	public Float getExpendKm() {
		return expendKm;
	}

	public void setExpendKm(Float expendKm) {
		this.expendKm = expendKm;
	}

	public Float getOverallDiameter() {
		return overallDiameter;
	}

	public void setOverallDiameter(Float overallDiameter) {
		this.overallDiameter = overallDiameter;
	}

	public Float getMaxDiameter() {
		return maxDiameter;
	}

	public void setMaxDiameter(Float maxDiameter) {
		this.maxDiameter = maxDiameter;
	}

	public Float getTotalMaterExpend() {
		return totalMaterExpend;
	}

	public void setTotalMaterExpend(Float totalMaterExpend) {
		this.totalMaterExpend = totalMaterExpend;
	}

	public Float getTwiceDistance() {
		return twiceDistance;
	}

	public void setTwiceDistance(Float twiceDistance) {
		this.twiceDistance = twiceDistance;
	}

	public Float getBdWeight() {
		return bdWeight;
	}

	public void setBdWeight(Float bdWeight) {
		this.bdWeight = bdWeight;
	}

	public Float getBdWidth() {
		return bdWidth;
	}

	public void setBdWidth(Float bdWidth) {
		this.bdWidth = bdWidth;
	}

	public Float getBdThick() {
		return bdThick;
	}

	public void setBdThick(Float bdThick) {
		this.bdThick = bdThick;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

}
