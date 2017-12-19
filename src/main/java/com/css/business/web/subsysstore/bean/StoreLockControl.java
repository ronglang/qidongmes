package com.css.business.web.subsysstore.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.web.syscommon.bean.BaseEntity;
/**
 * 成品库存锁控表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "store_lock_control")
public class StoreLockControl implements BaseEntity{
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_store_lock_control", sequenceName = "seq_store_lock_control")  
	@GeneratedValue(generator = "seq_store_lock_control", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "pro_name")
	private String proName;
	
	@Column(name = "pro_ggxh")
	private String proGgxh;
	
	@Column(name = "amount")
	private Integer amount;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")   
	@Column(name = "lock_control_time")
	private Date lockControlTime;//锁控时间
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")   
	@Column(name = "lock_end_time")
	private Date lockEndTime;
	
	@Column(name = "create_by")
	private String createBy;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")    
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "produc_notice_code")
	private String producNoticeCode;//生产通知单编号
	
	@Column(name = "order_code")
	private String orderCode ;
	
	@Column(name = "als")
	private Integer als;
	
	@Column(name = "axis_number")
	private Integer axisNumber;
	
	@Column(name = "order_son_id")
	private Integer orderSonId;
	
	@Column(name="pro_color")
	private String proColor;
	
	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public Integer getOrderSonId() {
		return orderSonId;
	}

	public void setOrderSonId(Integer orderSonId) {
		this.orderSonId = orderSonId;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getLockControlTime() {
		return lockControlTime;
	}

	public void setLockControlTime(Date lockControlTime) {
		this.lockControlTime = lockControlTime;
	}

	public Date getLockEndTime() {
		return lockEndTime;
	}

	public void setLockEndTime(Date lockEndTime) {
		this.lockEndTime = lockEndTime;
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
	
}
