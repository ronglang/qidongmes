package com.css.business.web.subsysmanu.bean;

import java.math.BigDecimal;
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
@Table(name = "mau_machine")
public class MauMachine implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -6360467332465830784L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_machine", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_machine")
	@GeneratedValue(generator = "seq_mau_machine", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "mac_name")
	private String macName;
	@Column(name = "mac_state")
	private String macState;
	@Column(name = "mac_type")
	private String macType;
	@Column(name = "ggxh_start")
	private String ggxhStart;
	@Column(name = "ggxh_end")
	private String ggxhEnd;
	@Column(name = "mac_prio")
	private String macPrio;

	@Column(name = "mac_mark")
	private String macMark;// 代号 如101
	@Column(name = "is_flag")
	private String isFlag;// 0未排产 1已排产
	@Column(name = "remark")
	private String remark;// 备注
	@Column(name = "main_by")
	private String mainBy;
	@Column(name = "vice_by")
	private String viceBy;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "diameter_min")
	private BigDecimal diameterMin; // 最小直径
	@Column(name = "diameter_max")
	private BigDecimal diameterMax; // 最大直径
	@Column(name = "outlet_count")
	private Integer outletCount;  	// 出轴数
	@Column(name="use_mater")
	private String usrMater;

	public String getGgxhStart() {
		return ggxhStart;
	}

	public void setGgxhStart(String ggxhStart) {
		this.ggxhStart = ggxhStart;
	}

	public String getGgxhEnd() {
		return ggxhEnd;
	}

	public void setGgxhEnd(String ggxhEnd) {
		this.ggxhEnd = ggxhEnd;
	}

	public String getMacPrio() {
		return macPrio;
	}

	public void setMacPrio(String macPrio) {
		this.macPrio = macPrio;
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

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public String getMacState() {
		return macState;
	}

	public void setMacState(String macState) {
		this.macState = macState;
	}

	public String getMacType() {
		return macType;
	}

	public void setMacType(String macType) {
		this.macType = macType;
	}

	public String getMacMark() {
		return macMark;
	}

	public void setMacMark(String macMark) {
		this.macMark = macMark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}

	public String getMainBy() {
		return mainBy;
	}

	public void setMainBy(String mainBy) {
		this.mainBy = mainBy;
	}

	public String getViceBy() {
		return viceBy;
	}

	public void setViceBy(String viceBy) {
		this.viceBy = viceBy;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public BigDecimal getDiameterMin() {
		return diameterMin;
	}

	public void setDiameterMin(BigDecimal diameterMin) {
		this.diameterMin = diameterMin;
	}

	public BigDecimal getDiameterMax() {
		return diameterMax;
	}

	public void setDiameterMax(BigDecimal diameterMax) {
		this.diameterMax = diameterMax;
	}

	public Integer getOutletCount() {
		return outletCount;
	}

	public void setOutletCount(Integer outletCount) {
		this.outletCount = outletCount;
	}

	public String getUsrMater() {
		return usrMater;
	}

	public void setUsrMater(String usrMater) {
		this.usrMater = usrMater;
	}

}
