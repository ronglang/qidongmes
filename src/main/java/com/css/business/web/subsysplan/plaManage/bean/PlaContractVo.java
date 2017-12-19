package com.css.business.web.subsysplan.plaManage.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PlaContractVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8509966353027559471L;

	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	private String creeateBy;
	private String scCode; // 合同编号
	private String scContent; // 合同内容
	private String remark; // 备注
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date scDate; // 合同日期
	private Double scMoney; // 合同金额
	private String firstParty; // 甲方
	private String secondParty; // 乙方
	private String agentBy; // 经办人
	private String cusCode; // 客户编号
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date deliveDate; // 交货日期
	private String RubberType; // 胶料类型
	private String MetalType; // 金属材料类型
	private Double length; // 交货长度
	private String proGgxh; // 产品规格型号
	private Integer crId; // 工艺路线Id
	private Integer crsID; // 工艺路线明细id

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

	public String getCreeateBy() {
		return creeateBy;
	}

	public void setCreeateBy(String creeateBy) {
		this.creeateBy = creeateBy;
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

	public Date getDeliveDate() {
		return deliveDate;
	}

	public void setDeliveDate(Date deliveDate) {
		this.deliveDate = deliveDate;
	}

	public String getRubberType() {
		return RubberType;
	}

	public void setRubberType(String rubberType) {
		RubberType = rubberType;
	}

	public String getMetalType() {
		return MetalType;
	}

	public void setMetalType(String metalType) {
		MetalType = metalType;
	}

	public Integer getCrId() {
		return crId;
	}

	public void setCrId(Integer crId) {
		this.crId = crId;
	}

	public Integer getCrsID() {
		return crsID;
	}

	public void setCrsID(Integer crsID) {
		this.crsID = crsID;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}
}
