package com.css.business.web.subsysstatistics.bean;


/**
 * @Title: StatOrderDisplayVo.java
 * @Package com.css.business.web.subsysstatistics.bean
 * @Description: 统计,生产令生产情况的vo
 * @author rb
 * @date 2017年7月25日 下午1:52:08
 * @company SMTC
 */

public class StatOrderDisplayVo {

	/** 合同id */
	private Integer contractId;
	/** 生产令编号 */
	private String productOrderCode;
	/** 开单日期 */
	private String billDate;
	/** 要求日期 */
	private String demandDate;
	/** 计划轴数 */
	private Integer amount;
	/** 完成轴数 */
	private Integer completeAmount;
	/** 客户要求长度 */
	private Double demandPartLen;
	/** 实际生产长度 */
	private Double productPartLen;
	/** 产品型号 */
	private String proGgxh;
	/** 生产状态 */
	private String productOrderState;
	/** 是否加入生产 */
	private String is_flag;
	/** 异常个数 */
	private Integer ex_count;

	/**
	 * @return contractId
	 */
	public Integer getContractId() {
		return contractId;
	}

	/**
	 * @param contractId
	 *            要设置的 contractId
	 * 
	 */
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return productOrderCode
	 */
	public String getProductOrderCode() {
		return productOrderCode;
	}

	/**
	 * @param productOrderCode
	 *            要设置的 productOrderCode
	 * 
	 */
	public void setProductOrderCode(String productOrderCode) {
		this.productOrderCode = productOrderCode;
	}

	

	/**
	 * @return  billDate
	 */
	public String getBillDate() {
		return billDate;
	}

	/**
	 *  @param billDate 要设置的 billDate 
	 *    
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	/**
	 * @return  demandDate
	 */
	public String getDemandDate() {
		return demandDate;
	}

	/**
	 *  @param demandDate 要设置的 demandDate 
	 *    
	 */
	public void setDemandDate(String demandDate) {
		this.demandDate = demandDate;
	}

	/**
	 * @return amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            要设置的 amount
	 * 
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * @return completeAmount
	 */
	public Integer getCompleteAmount() {
		return completeAmount;
	}

	/**
	 * @param completeAmount
	 *            要设置的 completeAmount
	 * 
	 */
	public void setCompleteAmount(Integer completeAmount) {
		this.completeAmount = completeAmount;
	}

	/**
	 * @return demandPartLen
	 */
	public Double getDemandPartLen() {
		return demandPartLen;
	}

	/**
	 * @param demandPartLen
	 *            要设置的 demandPartLen
	 * 
	 */
	public void setDemandPartLen(Double demandPartLen) {
		this.demandPartLen = demandPartLen;
	}

	/**
	 * @return productPartLen
	 */
	public Double getProductPartLen() {
		return productPartLen;
	}

	/**
	 * @param productPartLen
	 *            要设置的 productPartLen
	 * 
	 */
	public void setProductPartLen(Double productPartLen) {
		this.productPartLen = productPartLen;
	}

	/**
	 * @return proGgxh
	 */
	public String getProGgxh() {
		return proGgxh;
	}

	/**
	 * @param proGgxh
	 *            要设置的 proGgxh
	 * 
	 */
	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	/**
	 * @return productOrderState
	 */
	public String getProductOrderState() {
		return productOrderState;
	}

	/**
	 * @param productOrderState
	 *            要设置的 productOrderState
	 * 
	 */
	public void setProductOrderState(String productOrderState) {
		this.productOrderState = productOrderState;
	}

	/**
	 * @return is_flag
	 */
	public String getIs_flag() {
		return is_flag;
	}

	/**
	 * @param is_flag
	 *            要设置的 is_flag
	 * 
	 */
	public void setIs_flag(String is_flag) {
		this.is_flag = is_flag;
	}

	/**
	 * @return ex_count
	 */
	public Integer getEx_count() {
		return ex_count;
	}

	/**
	 * @param ex_count
	 *            要设置的 ex_count
	 * 
	 */
	public void setEx_count(Integer ex_count) {
		this.ex_count = ex_count;
	}

}
