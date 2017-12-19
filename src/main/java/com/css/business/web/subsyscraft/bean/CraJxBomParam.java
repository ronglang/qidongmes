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
 * @Title:CraJxBomParam.java
 * @Description:绞线BOM参数
 * @author RB
 * @company SMTC
 * @date 2017年10月31日下午3:59:56
 */
@Entity
@Table(name = "Cra_Jx_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Jx_Bom_Param", sequenceName = "seq_Cra_Jx_Bom_Param")
public class CraJxBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -1039313897936608547L;
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
	/** 一层绞线方向(左，右) */
	@Column(name = "once_direction")
	private String onceDirection;
	/** 二层绞线方向(左，右) */
	@Column(name = "twice_direction")
	private String twiceDirection;
	/** 三层绞线方向(左，右) */
	@Column(name = "third_direction")
	private String thirdDirection;
	/** 四层绞线方向(左，右) */
	@Column(name = "fourth_direction")
	private String fourthDirection;
	/** 工作单上检查项目 */
	@Column(name = "inspection_items")
	private String inspectionItems;
	/** OEE项目：每轴可出货长度 */
	@Column(name = "oee_slps")
	private Integer oeeSlps;
	/** 一层绞线线数 */
	@Column(name = "once_line_num")
	private String onceLineNum;
	/** 二层绞线线数 */
	@Column(name = "twice_line_num")
	private String twiceLineNum;
	/** 三层绞线线数 */
	@Column(name = "third_line_num")
	private String thridLineNum;
	/** 四层绞线线数 */
	@Column(name = "fourth_line_num")
	private String fourthLineNum;
	/** 备注 */
	@Column(name = "remark")
	private String remark;
	/** 工序步骤 */
	@Column(name = "seq_step")
	private Integer seqStep;

	/** 一层绞距 */
	@Column(name = "once_lay")
	private Float onceLay;
	/** 一层完成直径(mm) */
	@Column(name = "once_fdiameter")
	private String onceFdiameter;
	/** 一层最大线径(mm) */
	@Column(name = "once_max_diameter")
	private String onceMaxDiameter;
	/** 二层绞距 */
	@Column(name = "twice_lay")
	private Float twiceLay;
	/** 二层完成直径(mm) */
	@Column(name = "twice_fdiameter")
	private String twiceFdiameter;
	/** 二层最大线径(mm) */
	@Column(name = "twice_max_diameter")
	private String twiceMaxDiameter;
	/** 三层绞距 */
	@Column(name = "third_lay")
	private Float thirdLay;
	/** 三层完成直径(mm) */
	@Column(name = "third_fdiameter")
	private String thirdFdiameter;
	/** 三层最大线径(mm) */
	@Column(name = "third_max_diameter")
	private String thirdMaxDiameter;
	/** 四层绞距 */
	@Column(name = "fourth_lay")
	private Float fourthLay;
	/** 四层完成直径(mm) */
	@Column(name = "fourth_fdiameter")
	private String fourthFdiameter;
	/** 四层最大线径(mm) */
	@Column(name = "fourth_max_diameter")
	private String fourthMaxDiameter;
	/** 预绞距 */
	@Column(name = "lay_distance")
	private Float layDistance;
	/** 标准重量(KG/m) */
	@Column(name = "oee_stand_weight")
	private Float oeeStandWeight;
	/** 压缩率% */
	@Column(name = "once_compression_ratio")
	private Float oncecompressionRatio;
	/** 压缩率% */
	@Column(name = "twice_compression_ratio")
	private Float twicecompressionRatio;
	/** 压缩率% */
	@Column(name = "thrid_compression_ratio")
	private Float thridcompressionRatio;
	/** 压缩率% */
	@Column(name = "fourth_compression_ratio")
	private Float fourthcompressionRatio;

	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;
	/** 轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;

	/** 每公里用量(kg) */
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

	public String getOnceDirection() {
		return onceDirection;
	}

	public void setOnceDirection(String onceDirection) {
		this.onceDirection = onceDirection;
	}

	public String getTwiceDirection() {
		return twiceDirection;
	}

	public void setTwiceDirection(String twiceDirection) {
		this.twiceDirection = twiceDirection;
	}

	public String getThirdDirection() {
		return thirdDirection;
	}

	public void setThirdDirection(String thirdDirection) {
		this.thirdDirection = thirdDirection;
	}

	public String getFourthDirection() {
		return fourthDirection;
	}

	public void setFourthDirection(String fourthDirection) {
		this.fourthDirection = fourthDirection;
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

	public String getOnceLineNum() {
		return onceLineNum;
	}

	public void setOnceLineNum(String onceLineNum) {
		this.onceLineNum = onceLineNum;
	}

	public String getTwiceLineNum() {
		return twiceLineNum;
	}

	public void setTwiceLineNum(String twiceLineNum) {
		this.twiceLineNum = twiceLineNum;
	}

	public String getThridLineNum() {
		return thridLineNum;
	}

	public void setThridLineNum(String thridLineNum) {
		this.thridLineNum = thridLineNum;
	}

	public String getFourthLineNum() {
		return fourthLineNum;
	}

	public void setFourthLineNum(String fourthLineNum) {
		this.fourthLineNum = fourthLineNum;
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

	public Float getOnceLay() {
		return onceLay;
	}

	public void setOnceLay(Float onceLay) {
		this.onceLay = onceLay;
	}

	public String getOnceFdiameter() {
		return onceFdiameter;
	}

	public void setOnceFdiameter(String onceFdiameter) {
		this.onceFdiameter = onceFdiameter;
	}

	public String getOnceMaxDiameter() {
		return onceMaxDiameter;
	}

	public void setOnceMaxDiameter(String onceMaxDiameter) {
		this.onceMaxDiameter = onceMaxDiameter;
	}

	public Float getTwiceLay() {
		return twiceLay;
	}

	public void setTwiceLay(Float twiceLay) {
		this.twiceLay = twiceLay;
	}

	public String getTwiceFdiameter() {
		return twiceFdiameter;
	}

	public void setTwiceFdiameter(String twiceFdiameter) {
		this.twiceFdiameter = twiceFdiameter;
	}

	public String getTwiceMaxDiameter() {
		return twiceMaxDiameter;
	}

	public void setTwiceMaxDiameter(String twiceMaxDiameter) {
		this.twiceMaxDiameter = twiceMaxDiameter;
	}

	public Float getThirdLay() {
		return thirdLay;
	}

	public void setThirdLay(Float thirdLay) {
		this.thirdLay = thirdLay;
	}

	public String getThirdFdiameter() {
		return thirdFdiameter;
	}

	public void setThirdFdiameter(String thirdFdiameter) {
		this.thirdFdiameter = thirdFdiameter;
	}

	public String getThirdMaxDiameter() {
		return thirdMaxDiameter;
	}

	public void setThirdMaxDiameter(String thirdMaxDiameter) {
		this.thirdMaxDiameter = thirdMaxDiameter;
	}

	public Float getFourthLay() {
		return fourthLay;
	}

	public void setFourthLay(Float fourthLay) {
		this.fourthLay = fourthLay;
	}

	public String getFourthFdiameter() {
		return fourthFdiameter;
	}

	public void setFourthFdiameter(String fourthFdiameter) {
		this.fourthFdiameter = fourthFdiameter;
	}

	public String getFourthMaxDiameter() {
		return fourthMaxDiameter;
	}

	public void setFourthMaxDiameter(String fourthMaxDiameter) {
		this.fourthMaxDiameter = fourthMaxDiameter;
	}

	public Float getLayDistance() {
		return layDistance;
	}

	public void setLayDistance(Float layDistance) {
		this.layDistance = layDistance;
	}

	public Float getOeeStandWeight() {
		return oeeStandWeight;
	}

	public void setOeeStandWeight(Float oeeStandWeight) {
		this.oeeStandWeight = oeeStandWeight;
	}

	public Float getOncecompressionRatio() {
		return oncecompressionRatio;
	}

	public void setOncecompressionRatio(Float oncecompressionRatio) {
		this.oncecompressionRatio = oncecompressionRatio;
	}

	public Float getTwicecompressionRatio() {
		return twicecompressionRatio;
	}

	public void setTwicecompressionRatio(Float twicecompressionRatio) {
		this.twicecompressionRatio = twicecompressionRatio;
	}

	public Float getThridcompressionRatio() {
		return thridcompressionRatio;
	}

	public void setThridcompressionRatio(Float thridcompressionRatio) {
		this.thridcompressionRatio = thridcompressionRatio;
	}

	public Float getFourthcompressionRatio() {
		return fourthcompressionRatio;
	}

	public void setFourthcompressionRatio(Float fourthcompressionRatio) {
		this.fourthcompressionRatio = fourthcompressionRatio;
	}

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

	public void setSeqStep(Integer seqStep) {
		this.seqStep = seqStep;
	}

}
