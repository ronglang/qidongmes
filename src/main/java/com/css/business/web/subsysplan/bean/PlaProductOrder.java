package com.css.business.web.subsysplan.bean;

import java.sql.Timestamp;
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
@Table(name = "pla_product_order")
//@SequenceGenerator(name = "SEQ_PLA_PRODUCT_ORDER", sequenceName = "SEQ_PLA_PRODUCT_ORDER")
public class PlaProductOrder implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -4674442249719918818L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_product_order", sequenceName = "seq_pla_product_order")  
	@GeneratedValue(generator = "seq_pla_product_order", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "contract_code")
	private String contractCode;
	@Column(name = "product_order_code")
	private String productOrderCode;
	@Column(name = "bill_date")
	//@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp billDate;
	@Column(name = "demand_date")
	//@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp demandDate;
	@Column(name = "mater_type")
	private String materType;
	@Column(name = "amount")
	private String amount;
	@Column(name = "complete_amount")
	private Integer completeAmount;
	@Column(name = "demand_part_len")
	private String demandPartLen;
	@Column(name = "product_order_state")
	private String productOrderState;
	@Column(name = "priority")
	private Integer priority;
	@Column(name = "product_part_len")
	private String productPartLen;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name="cra_route_code")
	private String craRouteCode;//工艺路线编码
	@Column(name="is_flag")
	private String is_flag;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name="pbat_detail_code")
	private String pbatDetailCode;
	@Column(name="bat_code")
	private String batCode; //批次编码
	
	@Column(name="pro_code")
	private String proCode;
	@Column(name="pro_color")
	private String proColor;
	@Column(name="pro_craft_code")
	private String proCraftCode;
	@Column(name = "contract_detail_id")
	private Integer contractDetailId;
	
	public String getIs_flag() {
		return is_flag;
	}

	public void setIs_flag(String is_flag) {
		this.is_flag = is_flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getCompleteAmount() {
		return completeAmount;
	}

	public void setCompleteAmount(Integer completeAmount) {
		this.completeAmount = completeAmount;
	}

	public String getProductOrderCode() {
		return productOrderCode;
	}

	public void setProductOrderCode(String productOrderCode) {
		this.productOrderCode = productOrderCode;
	}

	public Timestamp getBillDate() {
		return billDate;
	}

	public void setBillDate(Timestamp billDate) {
		this.billDate = billDate;
	}

	public Timestamp getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Timestamp demandDate) {
		this.demandDate = demandDate;
	}

	public String getMaterType() {
		return materType;
	}

	public void setMaterType(String materType) {
		this.materType = materType;
	}

	public String getDemandPartLen() {
		return demandPartLen;
	}

	public void setDemandPartLen(String demandPartLen) {
		this.demandPartLen = demandPartLen;
	}

	public String getProductOrderState() {
		return productOrderState;
	}

	public void setProductOrderState(String productOrderState) {
		this.productOrderState = productOrderState;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getProductPartLen() {
		return productPartLen;
	}

	public void setProductPartLen(String productPartLen) {
		this.productPartLen = productPartLen;
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

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}
	

	public String getPbatDetailCode() {
		return pbatDetailCode;
	}

	public void setPbatDetailCode(String pbatDetailCode) {
		this.pbatDetailCode = pbatDetailCode;
	}

	public static void main(String[] args) {
		String s = "2.0000009";
		String arr[] = s.split("[.]");
		Double d = Double.valueOf(arr[1]);
		System.out.println(d);
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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

	public String getCraRouteCode() {
		return craRouteCode;
	}

	public void setCraRouteCode(String craRouteCode) {
		this.craRouteCode = craRouteCode;
	}

	/**
	 * @return  batCode
	 */
	public String getBatCode() {
		return batCode;
	}

	/**
	 *  @param batCode 要设置的 batCode 
	 *    
	 */
	public void setBatCode(String batCode) {
		this.batCode = batCode;
	}

	public Integer getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(Integer contractDetailId) {
		this.contractDetailId = contractDetailId;
	}

	
	
}
