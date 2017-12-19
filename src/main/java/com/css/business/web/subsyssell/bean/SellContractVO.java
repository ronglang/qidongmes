package com.css.business.web.subsyssell.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

public class SellContractVO  {

	private Integer id;
	private String createBy;
	private String scCode;
	private String scContent;
	private String remark;
	private Date scDate;
	private Double scMoney;
	private String firstParty;
	private String secondParty;
	private String agentBy;
	private String cusCode;
	private Date deliveDate;
	private String scState;
	private String cusName;
	private String orderCode;
	private String wsType;

	private String contractCode;// 生产通知单编号
	private String contractType;// 生产通知单类型；正常单、插单、
//	@Transient
//	private List<SellContractPlanBatch> sellContractPlanBatchLst = new ArrayList<SellContractPlanBatch>();
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

	/*@Transient
	public List<SellContractPlanBatch> getSellContractPlanBatchLst() {
		return sellContractPlanBatchLst;
	}

	public void setSellContractPlanBatchLst(
			List<SellContractPlanBatch> sellContractPlanBatchLst) {
		this.sellContractPlanBatchLst = sellContractPlanBatchLst;
	}
*/
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
