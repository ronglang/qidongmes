package com.css.business.web.subsyssell.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "SELL_CONTRACT")
@SequenceGenerator(name = "SEQ_SELL_CONTRACT", sequenceName = "SEQ_SELL_CONTRACT")
public class SellContract implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 424239036305029579L;
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
	@Column(name = "sc_content")
	private String scContent;
	@Column(name = "remark")
	private String remark;
	@Column(name = "sc_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date scDate;
	@Column(name = "sc_money")
	private Double scMoney;
	@Column(name = "first_party")
	private String firstParty;
	@Column(name = "second_party")
	private String secondParty;
	@Column(name = "agent_by")
	private String agentBy;
	@Column(name = "cus_code")
	private String cusCode;
	@Column(name = "delive_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date deliveDate;
	@Column(name = "sc_state")
	private String scState;
	@Column(name = "cus_name")
	private String cusName;
	@Column(name = "order_code")
	private String orderCode;
	@Column(name = "ws_type")
	private String wsType;

	@Column(name = "contract_code")
	private String contractCode;// 生产通知单编号

	@Column(name = "contract_type")
	private String contractType;// 生产通知单类型；正常单、插单、
	@Transient
	private List<SellContractPlanBatch> sellContractPlanBatchLst = new ArrayList<SellContractPlanBatch>();
	@Transient
	private String sellContractPlanBatchLstStr;

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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

	public Date getScDate() {
		return scDate;
	}

	public void setScDate(Date scDate) {
		this.scDate = scDate;
	}

	public Double getScMoney() {
		return scMoney;
	}

	public void setScMoney(Double scMoney) {
		this.scMoney = scMoney;
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

	public String getScContent() {
		return scContent;
	}

	public void setScContent(String scContent) {
		this.scContent = scContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}

	public String getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
	}

	public String getAgentBy() {
		return agentBy;
	}

	public void setAgentBy(String agentBy) {
		this.agentBy = agentBy;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getScState() {
		return scState;
	}

	public void setScState(String scState) {
		this.scState = scState;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Transient
	public List<SellContractPlanBatch> getSellContractPlanBatchLst() {
		return sellContractPlanBatchLst;
	}

	public void setSellContractPlanBatchLst(
			List<SellContractPlanBatch> sellContractPlanBatchLst) {
		this.sellContractPlanBatchLst = sellContractPlanBatchLst;
	}

	public String getSellContractPlanBatchLstStr() {
		return sellContractPlanBatchLstStr;
	}

	public void setSellContractPlanBatchLstStr(
			String sellContractPlanBatchLstStr) {
		this.sellContractPlanBatchLstStr = sellContractPlanBatchLstStr;
	}

	public String getWsType() {
		return wsType;
	}

	public void setWsType(String wsType) {
		this.wsType = wsType;
	}

}
