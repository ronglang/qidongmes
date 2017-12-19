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

@Entity
@Table(name = "Cra_Yz_Bom_Param")
@SequenceGenerator(name = "seq_Cra_Yz_Bom_Param", sequenceName = "seq_Cra_Yz_Bom_Param")
public class CraYzBomParam implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 3232592207495124564L;
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

	/** 规格型号 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 工序编码 */
	@Column(name = "seq_code")
	private String seqCode;
	/** 芯线印字内容 */
	@Column(name = "print_content")
	private String printContent;
	/** 印字须知(如:字高4mm,间隔小于30mm,须耐擦) */
	@Column(name = "print_notice")
	private String printNotice;
	/** 备注 */
	@Column(name = "remark")
	private String remark;

	/** 每轴最大米数 */
	@Column(name = "max_meter_per_axle")
	private Integer maxMeterPerAxle;
	/** 轴数 */
	@Column(name = "axis_num")
	private Integer axisNum;
	/** 工序步骤 */
	@Column(name = "seq_step")
	private Integer seqStep;
	/** 印字米数 */
	@Column(name = "print_meter")
	private Integer printMeter;

	/** 调试长度 */
	@Column(name = "debug_length")
	private Integer debugLength;
	
	/** 每公里用量(kg)*/
	@Column(name = "use_per_kilometer")
	private Float usePerKilometer;

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

	public String getPrintContent() {
		return printContent;
	}

	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}

	public String getPrintNotice() {
		return printNotice;
	}

	public void setPrintNotice(String printNotice) {
		this.printNotice = printNotice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMaxMeterPerAxle() {
		return maxMeterPerAxle;
	}

	public void setMaxMeterPerAxle(Integer maxMeterPerAxle) {
		this.maxMeterPerAxle = maxMeterPerAxle;
	}

	public Integer getAxisNum() {
		return axisNum;
	}

	public void setAxisNum(Integer axisNum) {
		this.axisNum = axisNum;
	}

	public Integer getSeqStep() {
		return seqStep;
	}

	public void setSeqStep(Integer seqStep) {
		this.seqStep = seqStep;
	}

	public Integer getPrintMeter() {
		return printMeter;
	}

	public void setPrintMeter(Integer printMeter) {
		this.printMeter = printMeter;
	}

	public Float getUsePerKilometer() {
		return usePerKilometer;
	}

	public void setUsePerKilometer(Float usePerKilometer) {
		this.usePerKilometer = usePerKilometer;
	}

}
