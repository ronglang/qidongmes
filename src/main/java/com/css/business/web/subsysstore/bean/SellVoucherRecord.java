package com.css.business.web.subsysstore.bean;

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
 * 凭证记录，客户在材料不足的情况下，依然往下派发生产通知单
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sell_voucher_record")
public class SellVoucherRecord implements BaseEntity{
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_sell_voucher_record", sequenceName = "seq_sell_voucher_record")  
	@GeneratedValue(generator = "seq_sell_voucher_record", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "materiel_name")
	private String materielName;
	
	@Column(name = "materiel_ggxh")
	private String materielGgxh;
	
	@Column(name="stock_count")
	private Integer stock_count;
	
	@Column(name="need_count")
	private Integer need_count;
	
	@Column(name="unit")
	private String unit;
	
	@Column(name="produc_notice_code")
	private String producNoticeCode;
	
	@Column(name="order_code")
	private String orderCode;
	
	@Column(name="create_by")
	private String createBy;
	
	@Column(name="create_time")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getMaterielGgxh() {
		return materielGgxh;
	}

	public void setMaterielGgxh(String materielGgxh) {
		this.materielGgxh = materielGgxh;
	}

	public Integer getStock_count() {
		return stock_count;
	}

	public void setStock_count(Integer stock_count) {
		this.stock_count = stock_count;
	}

	public Integer getNeed_count() {
		return need_count;
	}

	public void setNeed_count(Integer need_count) {
		this.need_count = need_count;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProducNoticeCode() {
		return producNoticeCode;
	}

	public void setProducNoticeCode(String producNoticeCode) {
		this.producNoticeCode = producNoticeCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	
	
	
}
