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
 * @Title:CraJtxBomParam.java
 * @Description:绞铁线BOM
 * @author RB
 * @company SMTC
 * @date 2017年11月1日上午8:42:12
 */
@Entity
@Table(name = "Cra_Jtx_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Jtx_Bom_Param", sequenceName = "seq_Cra_Jtx_Bom_Param")
public class CraJtxBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -4080191994081120676L;
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

	/** 铁线条数 */
	@Column(name = "wire_num")
	private Integer wireNum;
	/** 胶合长度(m) */
	@Column(name = "gather_length")
	private Integer gatherLength;
	/** 铁线总重量(kg) */
	@Column(name = "wire_weight")
	private Integer wireWeight;
	/** 铁线长度(m) */
	@Column(name = "wire_length")
	private Integer wireLength;

	/** 铁线直径(mm) */
	@Column(name = "wire_diameter")
	private Float wireDiameter;
	/** 绞距(mm) */
	@Column(name = "lay")
	private Float lay;
	/** 正常外径(mm) */
	@Column(name = "normal_diameter")
	private Float normalDiameter;
	/** 最大外径(mm) */
	@Column(name = "max_diameter")
	private Float maxDiameter;

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getWireNum() {
		return wireNum;
	}

	public void setWireNum(Integer wireNum) {
		this.wireNum = wireNum;
	}

	public Integer getGatherLength() {
		return gatherLength;
	}

	public void setGatherLength(Integer gatherLength) {
		this.gatherLength = gatherLength;
	}

	public Integer getWireWeight() {
		return wireWeight;
	}

	public void setWireWeight(Integer wireWeight) {
		this.wireWeight = wireWeight;
	}

	public Integer getWireLength() {
		return wireLength;
	}

	public void setWireLength(Integer wireLength) {
		this.wireLength = wireLength;
	}

	public Float getWireDiameter() {
		return wireDiameter;
	}

	public void setWireDiameter(Float wireDiameter) {
		this.wireDiameter = wireDiameter;
	}

	public Float getLay() {
		return lay;
	}

	public void setLay(Float lay) {
		this.lay = lay;
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

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

}
