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

import com.css.commcon.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "SELL_CONTRACT_DETAIL")
@SequenceGenerator(name="SEQ_SELL_CONTRACT_DETAIL",sequenceName="SEQ_SELL_CONTRACT_DETAIL")
public class SellContractDetail implements BaseEntity{

	/**
	 * 合同批次表，记录的是合同和批次信息
	 */
	@Transient
	private static final long serialVersionUID = -2535566834148876409L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_sell_contract_detail", allocationSize = 1, initialValue = 1, sequenceName = "seq_sell_contract_detail")  
	@GeneratedValue(generator = "seq_sell_contract_detail", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "bat_code")
	private String batCode;
	
	@Column(name = "sc_code")
	private String scCode;
	
	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	
	@Column(name = "delive_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date deliveDate;
	
	@Column(name = "req_unit")
	private String reqUnit;
	
	@Column(name="pbat_detail_code")
	private String pbatDetailCode;
	
	@Column(name="pbat_detail_state")
	private String pbatDetailState;
	
	
	@Column(name = "req_period_length")
	private String reqPeriodLength;
	
	@Column(name = "pro_period_length")
	private String proPeriodLength;
	
	@Column(name = "req_amount")
	private String reqAmount;
	
	@Column(name="pro_ggxh")
	private String proGgxh;
	
	@Column(name = "pro_color")
	private String proColor;
	
	@Column(name = "mau_product_id")
	private Integer mauProductId;
	
	@Column(name = "plan_batch_id")
	private Integer planBatchId;
	
	@Column(name="main_id")
	private Integer mainId;//生产通知单主表主键
	@Column(name="total_len")
	private Integer totalLen;//生产总长度
	@Column(name="complete_len")
	private Integer completeLen;//已下发到工单长度
	
	
	
	
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
	
	public String getBatCode() {
		return batCode;
	}

	public void setBatCode(String batCode) {
		this.batCode = batCode;
	}

	public String getScCode() {
		return scCode;
	}

	public void setScCode(String scCode) {
		this.scCode = scCode;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeliveDate() {
		return deliveDate;
	}

	public void setDeliveDate(Date deliveDate) {
		this.deliveDate = deliveDate;
	}

	public String getReqAmount() {
		return reqAmount;
	}

	public void setReqAmount(String reqAmount) {
		this.reqAmount = reqAmount;
	}

	public String getReqUnit() {
		return reqUnit;
	}

	public void setReqUnit(String reqUnit) {
		this.reqUnit = reqUnit;
	}

	public String getReqPeriodLength() {
		return reqPeriodLength;
	}

	public void setReqPeriodLength(String reqPeriodLength) {
		this.reqPeriodLength = reqPeriodLength;
	}

	public String getProPeriodLength() {
		return proPeriodLength;
	}

	public void setProPeriodLength(String proPeriodLength) {
		this.proPeriodLength = proPeriodLength;
	}

	public String getPbatDetailCode() {
		return pbatDetailCode;
	}

	public void setPbatDetailCode(String pbatDetailCode) {
		this.pbatDetailCode = pbatDetailCode;
	}

	public String getPbatDetailState() {
		return pbatDetailState;
	}

	public void setPbatDetailState(String pbatDetailState) {
		this.pbatDetailState = pbatDetailState;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	/**
	 * @return  proColor
	 */
	public String getProColor() {
		return proColor;
	}

	/**
	 *  @param proColor 要设置的 proColor 
	 *    
	 */
	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public Integer getPlanBatchId() {
		return planBatchId;
	}

	public void setPlanBatchId(Integer planBatchId) {
		this.planBatchId = planBatchId;
	}

	public Integer getMauProductId() {
		return mauProductId;
	}

	public void setMauProductId(Integer mauProductId) {
		this.mauProductId = mauProductId;
	}

	public Integer getTotalLen() {
		return totalLen;
	}

	public void setTotalLen(Integer totalLen) {
		this.totalLen = totalLen;
	}

	public Integer getCompleteLen() {
		return completeLen;
	}

	public void setCompleteLen(Integer completeLen) {
		this.completeLen = completeLen;
	}
	
	
	
}
