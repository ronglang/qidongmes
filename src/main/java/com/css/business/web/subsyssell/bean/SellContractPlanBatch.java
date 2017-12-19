package com.css.business.web.subsyssell.bean;

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
@Table(name = "SELL_CONTRACT_PLAN_BATCH")
@SequenceGenerator(name = "SEQ_SELL_CONTRACT_PLAN_BATCH", sequenceName = "SEQ_SELL_CONTRACT_PLAN_BATCH")
public class SellContractPlanBatch implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 4194044642691037015L;
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
	@Column(name = "sc_code")
	private String scCode;
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;
	@Column(name = "req_amount")
	private String reqAmount;
	@Column(name = "req_unit")
	private String reqUnit;
	@Column(name = "bat_code")
	private String batCode;
	@Column(name = "delive_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date deliveDate;
	@Column(name="pbat_detail_code")
	private String pbatDetailCode;
	@Column(name="req_period_length")
	private String reqPeriodLength;
	@Column(name="pro_ggxh")
	private String proGgxh;
	@Column(name="sc_planbat_state")
	private String scPlanbatState;
	@Column(name="pro_code")
	private String proCode;
	@Column(name="pro_color")
	private String proColor;
	@Column(name="pro_type")
	private String proType;
	@Column(name="pro_craft_code")
	private String proCraftCode;
	@Column(name = "pro_craft_name")
	private String proCraftName;
	@Column(name = "pro_period_length")
	private String proPeriodLength;
	@Column(name = "total_part_len")
	private Integer totalPartLen;
	
	@Column(name="req_sin_length")
	private Integer reqSinLength;//客户要求单轴长度
	
	
	
	public Integer getReqSinLength() {
		return reqSinLength;
	}

	public void setReqSinLength(Integer reqSinLength) {
		this.reqSinLength = reqSinLength;
	}

	@Column(name = "main_id")
	private Integer mainId;//主表主键
	
	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReqAmount() {
		return reqAmount;
	}

	public void setReqAmount(String reqAmount) {
		this.reqAmount = reqAmount;
	}

	public Date getDeliveDate() {
		return deliveDate;
	}

	public void setDeliveDate(Date deliveDate) {
		this.deliveDate = deliveDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getScCode() {
		return scCode;
	}

	public void setScCode(String scCode) {
		this.scCode = scCode;
	}

	public String getReqUnit() {
		return reqUnit;
	}

	public void setReqUnit(String reqUnit) {
		this.reqUnit = reqUnit;
	}

	public String getBatCode() {
		return batCode;
	}

	public void setBatCode(String batCode) {
		this.batCode = batCode;
	}

	public String getPbatDetailCode() {
		return pbatDetailCode;
	}

	public void setPbatDetailCode(String pbatDetailCode) {
		this.pbatDetailCode = pbatDetailCode;
	}

	public String getReqPeriodLength() {
		return reqPeriodLength;
	}

	public void setReqPeriodLength(String reqPeriodLength) {
		this.reqPeriodLength = reqPeriodLength;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getScPlanbatState() {
		return scPlanbatState;
	}

	public void setScPlanbatState(String scPlanbatState) {
		this.scPlanbatState = scPlanbatState;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public String getProCraftCode() {
		return proCraftCode;
	}

	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	public String getProCraftName() {
		return proCraftName;
	}

	public void setProCraftName(String proCraftName) {
		this.proCraftName = proCraftName;
	}

	public String getProPeriodLength() {
		return proPeriodLength;
	}

	public void setProPeriodLength(String proPeriodLength) {
		this.proPeriodLength = proPeriodLength;
	}

	public Integer getTotalPartLen() {
		return totalPartLen;
	}

	public void setTotalPartLen(Integer totalPartLen) {
		this.totalPartLen = totalPartLen;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}
	
}
