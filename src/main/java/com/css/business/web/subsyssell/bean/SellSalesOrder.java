package com.css.business.web.subsyssell.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.common.web.syscommon.bean.BaseEntity;

/**
 * 销售订单主表
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sell_sales_order")
// @SequenceGenerator(name = "seq_sell_sales_order", sequenceName =
// "seq_sell_sales_order")
public class SellSalesOrder implements BaseEntity {

	@Transient
	public static String GEN_FLAG_YES = "已分解";
	@Transient
	public static String GEN_FLAG_NO = "未分解";
	@Transient
	public static String GEN_FLAG_ING = "分解中";
	@Transient
	public static String ORDER_TYPE_NORMAL = "正常开单";
	@Transient
	public static String ORDER_TYPE_INSERT = "插单";

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_sell_sales_order", sequenceName = "seq_sell_sales_order")
	@GeneratedValue(generator = "seq_sell_sales_order", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "order_code")
	private String orderCode;// 订单编号

	@Column(name = "contract_code")
	private String contractCode;// 合同编号

	@Column(name = "delivery_date")
	private Date deliveryDate;// 交货日期

	@Column(name = "sales_manager")
	private String salesManager;// 销售部经办人

	@Column(name = "order_entry_clerk")
	private String orderEntryClerk;// 订单录入人员

	@Column(name = "create_by")
	private String createBy;// 创建人

	@Column(name = "create_time")
	private Date createTime;// 创建时间
	@Column(name = "gen_flag")
	private String genFlag;// 是否分解

	@Transient
	private String completionRate;// 订单完成率

	/**
	 * 优先级
	 */
	@Column(name = "pri_level")
	private Integer priLevel;
	/**
	 * 工单类型 :正常开单,插单
	 */
	@Column(name = "order_type")
	private String orderType;

	public String getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(String completionRate) {
		this.completionRate = completionRate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getSalesManager() {
		return salesManager;
	}

	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}

	public String getOrderEntryClerk() {
		return orderEntryClerk;
	}

	public void setOrderEntryClerk(String orderEntryClerk) {
		this.orderEntryClerk = orderEntryClerk;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGenFlag() {
		return genFlag;
	}

	public void setGenFlag(String genFlag) {
		this.genFlag = genFlag;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Integer getPriLevel() {
		return priLevel;
	}

	public void setPriLevel(Integer priLevel) {
		this.priLevel = priLevel;
	}

}
