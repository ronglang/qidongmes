package com.css.business.web.subsyssell.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;

/**
 * 销售订单主表
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sell_sales_order_details")
// @SequenceGenerator(name = "seq_sell_sales_order_details", sequenceName =
// "seq_sell_sales_order_details")
public class SellSalesOrderDetails implements BaseEntity {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_sell_sales_order_details", sequenceName = "seq_sell_sales_order_details")
	@GeneratedValue(generator = "seq_sell_sales_order_details", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "order_code")
	private String orderCode;// 订单编号

	@Column(name = "delivery_date")
	private Date deliveryDate;// 交货日期

	@Column(name = "pro_ggxh")
	private String proGgxh;// 产品规格想好

	@Column(name = "als")
	private Integer als;// 轴长度

	@Column(name = "axis_number")
	private Integer axisNumber;// 轴数量

	@Column(name = "pro_color")
	private String proColor;// 产品颜色

	@Column(name = "create_by")
	private String createBy;// 创建人

	@Column(name = "create_time")
	private Date createTime;// 创建时间

	@Column(name = "unit")
	private String unit;// 单位

	@Column(name = "total_length")
	private Integer totalLength;// 总长度

	@Column(name = "now_axis_num")
	private Integer nowAxisNum;// 本次分解轴数
	@Column(name = "already_axis_num")
	private Integer alreadyAxisNum;// 已经分解轴数

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

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public Integer getAls() {
		return als;
	}

	public void setAls(Integer als) {
		this.als = als;
	}

	public Integer getAxisNumber() {
		return axisNumber;
	}

	public void setAxisNumber(Integer axisNumber) {
		this.axisNumber = axisNumber;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}

	public Integer getNowAxisNum() {
		return nowAxisNum;
	}

	public void setNowAxisNum(Integer nowAxisNum) {
		this.nowAxisNum = nowAxisNum;
	}

	public Integer getAlreadyAxisNum() {
		return alreadyAxisNum;
	}

	public void setAlreadyAxisNum(Integer alreadyAxisNum) {
		this.alreadyAxisNum = alreadyAxisNum;
	}

}
