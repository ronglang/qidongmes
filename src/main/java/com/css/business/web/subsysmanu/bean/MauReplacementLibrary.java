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
 * 备件库表
 * @author Administrator
 *
 */
@Entity
@Table(name="mau_replacement_library")
public class MauReplacementLibrary implements BaseEntity{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 55402050336943061L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_replacement_library", sequenceName = "seq_mau_replacement_library")  
	@GeneratedValue(generator = "seq_mau_replacement_library", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "type")
	private String type;                     //备件类型
	@Column(name = "code")
	private String code;					//备件编码
	@Column(name = "ggxh")
	private String ggxh;                     //规格型号
	@Column(name = "number")
	private Integer number;                  //库存数量
	@Column(name = "replace_use")
	private String replaceUse;              //机器用途
	@Column(name = "supply")
	private String supply;                 //供应商
	@Column(name = "replace_line_use")
	private String replaceLineUse;              //电线用途
	
	@Column(name = "in_store_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date inStoreTime;                 //入库时间
	
	@Column(name = "reject_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date rejectTime;                   //报废时间
	
	@Column(name = "reject_number")
	private Integer rejectNumber;              //报废数量
	
	@Column(name = "reject_reason")
	private String rejectReason;              //报废原因
	
	@Column(name = "material_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date materialTime;              //领料时间
	
	@Column(name = "material_number")
	private Integer materialNumber;              //领料数量
	@Column(name = "material_by")
	private String materialBy;              //领料人
	@Column(name = "material_use")
	private String materialUse;              //领料用途
	
	@Column(name = "status")
	private String status;                  //状态：正常、报废、领料

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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(Integer materialNumber) {
		this.materialNumber = materialNumber;
	}

	public String getReplaceUse() {
		return replaceUse;
	}

	public void setReplaceUse(String replaceUse) {
		this.replaceUse = replaceUse;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	public String getReplaceLineUse() {
		return replaceLineUse;
	}

	public void setReplaceLineUse(String replaceLineUse) {
		this.replaceLineUse = replaceLineUse;
	}

	public Date getInStoreTime() {
		return inStoreTime;
	}

	public void setInStoreTime(Date inStoreTime) {
		this.inStoreTime = inStoreTime;
	}

	public Date getRejectTime() {
		return rejectTime;
	}

	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}

	public Integer getRejectNumber() {
		return rejectNumber;
	}

	public void setRejectNumber(Integer rejectNumber) {
		this.rejectNumber = rejectNumber;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Date getMaterialTime() {
		return materialTime;
	}

	public void setMaterialTime(Date materialTime) {
		this.materialTime = materialTime;
	}

	public String getMaterialBy() {
		return materialBy;
	}

	public void setMaterialBy(String materialBy) {
		this.materialBy = materialBy;
	}

	public String getMaterialUse() {
		return materialUse;
	}

	public void setMaterialUse(String materialUse) {
		this.materialUse = materialUse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
