package com.css.business.web.subsysmanu.bean;

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

/**
 * @Title: MauMould.java
 * @Package com.css.business.web.subsysmanu.bean
 * @Description: 模具实体类
 * @author TG
 * @date 2017年9月08日 上午11
 * @company SMTC
 */
@Entity
@Table(name = "mau_mould")
public class MauMould implements BaseEntity {
	
	@Transient
	private static final long serialVersionUID = -8782590906387459744L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_mould", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_mould")
	@GeneratedValue(generator = "seq_mau_mould", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "mou_ggxh")
	private String mouGgxh;            //规格
	@Column(name = "mou_code")
	private String mouCode;            //编码   
	@Column(name = "number")
	private Integer number;			  //数量,不用
	@Column(name = "unit")
	private String unit;           //单位,不用
	@Column(name = "mou_type")
	private String mouType;           //类型
	@Column(name = "mou_size")
	private String mouSize;           //尺寸
	@Column(name = "mou_supply")
	private String mouSupply;           //供应商
	
	@Column(name = "in_store_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date inStoreTime;        //入库时间
	
	@Column(name = "mou_use")
	private String mouUse;           //机器用途
	@Column(name = "mou_line_use")
	private String mouLineUse;           //电线用途
	@Column(name = "is_reject")
	private String isReject;           //是否报废
	
	@Column(name = "reject_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date rejectTime;           //报废时间
	
	@Column(name = "reject_reason")
	private String rejectReason;           //报废理由
	@Column(name = "reject_number")
	private Integer rejectNumber;           //报废数量
	
	
	/*****************维修字段*******************/
	
	@Column(name = "maintain_vender")
	private String maintainVender;           //维修厂家
	@Column(name = "maintain_result")
	private String maintainResult;           //维修结果
	@Column(name = "change_record")
	private String changeRecord;           //变化记录
	
	@Column(name = "maintain_time")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date maintainTime;             //维修时间
	
	@Column(name = "result_time")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date resultTime;             //返厂时间
	
	@Column(name = "status")
	private String status;           //状态：使用中、维修中、库中
	@Column(name = "is_parent")
	private String isParent;         //0为模具基础信息，1为维修记录
	
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMouGgxh() {
		return mouGgxh;
	}
	public void setMouGgxh(String mouGgxh) {
		this.mouGgxh = mouGgxh;
	}
	public String getMouCode() {
		return mouCode;
	}
	public void setMouCode(String mouCode) {
		this.mouCode = mouCode;
	}
	
	public String getMouType() {
		return mouType;
	}
	public void setMouType(String mouType) {
		this.mouType = mouType;
	}
	public String getMouSize() {
		return mouSize;
	}
	public void setMouSize(String mouSize) {
		this.mouSize = mouSize;
	}
	public String getMouSupply() {
		return mouSupply;
	}
	public void setMouSupply(String mouSupply) {
		this.mouSupply = mouSupply;
	}
	public Date getInStoreTime() {
		return inStoreTime;
	}
	public void setInStoreTime(Date inStoreTime) {
		this.inStoreTime = inStoreTime;
	}
	public String getMouUse() {
		return mouUse;
	}
	public void setMouUse(String mouUse) {
		this.mouUse = mouUse;
	}
	public String getMouLineUse() {
		return mouLineUse;
	}
	public void setMouLineUse(String mouLineUse) {
		this.mouLineUse = mouLineUse;
	}
	public String getIsReject() {
		return isReject;
	}
	public void setIsReject(String isReject) {
		this.isReject = isReject;
	}
	public Date getRejectTime() {
		return rejectTime;
	}
	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Integer getRejectNumber() {
		return rejectNumber;
	}
	public void setRejectNumber(Integer rejectNumber) {
		this.rejectNumber = rejectNumber;
	}
	
	public Date getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}
	public Date getResultTime() {
		return resultTime;
	}
	public void setResultTime(Date resultTime) {
		this.resultTime = resultTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getMaintainVender() {
		return maintainVender;
	}
	public void setMaintainVender(String maintainVender) {
		this.maintainVender = maintainVender;
	}
	public String getMaintainResult() {
		return maintainResult;
	}
	public void setMaintainResult(String maintainResult) {
		this.maintainResult = maintainResult;
	}
	public String getChangeRecord() {
		return changeRecord;
	}
	public void setChangeRecord(String changeRecord) {
		this.changeRecord = changeRecord;
	}
	
	
	
	
	
}
