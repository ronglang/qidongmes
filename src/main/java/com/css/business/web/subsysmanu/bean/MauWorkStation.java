package com.css.business.web.subsysmanu.bean;

import java.math.BigDecimal;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "mau_work_order_record")
@SequenceGenerator(name = "seq_mau_product_order_record", sequenceName = "seq_mau_product_order_record")
public class MauWorkStation implements BaseEntity {

	/**
	 * 生产部电子看板   生产令完成情况表
	 */
	private static final long serialVersionUID = -8553506536906670208L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	@Column(name = "product_order_code")
	private String productOrderCode;
	@Column(name = "mater_type")
	private String materType;
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="product_part_len")
	private BigDecimal productPartLen;
	/** 仓库签收人 */
	@Column(name = "contract_id")
	private String contractId;

	@Column(name = "bill_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date billDate;

	@Column(name = "demand_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date demandDate;

	@Column(name = "true_product_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date trueProductDate;

	@Column(name = "true_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date trueEndDate;

	@Column(name = "color")
	private String color;
	
	/*a.course_code,c.pro_ggxh,b.axis_amount,b.part_len,b.work_start_date,b.work_end_date,a.fact_start_time,a.fact_end_time,c.seq_hours*/
	/**
	 * 生产延期情况表展示
	 */
	private String courseCode;//工单号
	private String proGgxh;//规格型号
	private String axisAmount;//数量
	private String partLen;//段长
	private Date workStartDate;//计划开单日期
	private Date workEndDate;//计划结单日期
	private Date factStartTime;//实际开单日期
	private Date factEndTime;//实际交单日期
	private String seqHours;//生产工序工时
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getProductOrderCode() {
		return productOrderCode;
	}

	public void setProductOrderCode(String productOrderCode) {
		this.productOrderCode = productOrderCode;
	}

	public String getMaterType() {
		return materType;
	}

	public void setMaterType(String materType) {
		this.materType = materType;
	}

	


	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getProductPartLen() {
		return productPartLen;
	}

	public void setProductPartLen(BigDecimal productPartLen) {
		this.productPartLen = productPartLen;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	public Date getTrueProductDate() {
		return trueProductDate;
	}

	public void setTrueProductDate(Date trueProductDate) {
		this.trueProductDate = trueProductDate;
	}

	public Date getTrueEndDate() {
		return trueEndDate;
	}

	public void setTrueEndDate(Date trueEndDate) {
		this.trueEndDate = trueEndDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getAxisAmount() {
		return axisAmount;
	}

	public void setAxisAmount(String axisAmount) {
		this.axisAmount = axisAmount;
	}

	public String getPartLen() {
		return partLen;
	}

	public void setPartLen(String partLen) {
		this.partLen = partLen;
	}

	public Date getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}

	public Date getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(Date workEndDate) {
		this.workEndDate = workEndDate;
	}

	public Date getFactStartTime() {
		return factStartTime;
	}

	public void setFactStartTime(Date factStartTime) {
		this.factStartTime = factStartTime;
	}

	public Date getFactEndTime() {
		return factEndTime;
	}

	public void setFactEndTime(Date factEndTime) {
		this.factEndTime = factEndTime;
	}

	public String getSeqHours() {
		return seqHours;
	}

	public void setSeqHours(String seqHours) {
		this.seqHours = seqHours;
	}

	

	
}
