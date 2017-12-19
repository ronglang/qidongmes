package com.css.business.web.subsysplan.bean;

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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @TODO : 生产令按工序、机台、工时拆分中间表
 * @author: 翟春磊
 * @DATE : 2017年8月4日
 */
@Entity
@Table(name = "pla_product_order_seq_hourse")
public class PlaProductOrderSeqHourse implements BaseEntity {
	/** 是否采样:是 */
	@Transient
	public static final String SAMPLE = "是";
	/** 是否采样:否 */
	@Transient
	public static final String NOT_SAMPLE = "否";
	@Transient
	private static final long serialVersionUID = -4674442249719918812L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_product_order", sequenceName = "seq_pla_product_order")
	@GeneratedValue(generator = "seq_pla_product_order", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "pla_order_id")
	private Integer plaOrderId;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "axis_name")
	private String axisName;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	@Column(name = "part_len")
	private Integer partLen;
	@Column(name = "len_unit")
	private String lenUnit;
	@Column(name = "employee_id_main")
	private Integer employeeIdMain;
	@Column(name = "employee_id_vice")
	private Integer employeeIdVice;
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endDate;
	@Column(name = "seq_hours")
	private BigDecimal seqHours;
	@Column(name = "work_day")
	private Integer workDay;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	/** 是否采样 */
	@Column(name = "is_sample")
	private String isSample;
	/** 采样长度 */
	@Column(name = "sample_length")
	private Double sample_length;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlaOrderId() {
		return plaOrderId;
	}

	public void setPlaOrderId(Integer plaOrderId) {
		this.plaOrderId = plaOrderId;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getProCraftCode() {
		return proCraftCode;
	}

	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	public Integer getPartLen() {
		return partLen;
	}

	public void setPartLen(Integer partLen) {
		this.partLen = partLen;
	}

	public Integer getEmployeeIdMain() {
		return employeeIdMain;
	}

	public void setEmployeeIdMain(Integer employeeIdMain) {
		this.employeeIdMain = employeeIdMain;
	}

	public Integer getEmployeeIdVice() {
		return employeeIdVice;
	}

	public void setEmployeeIdVice(Integer employeeIdVice) {
		this.employeeIdVice = employeeIdVice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getSeqHours() {
		return seqHours;
	}

	public void setSeqHours(BigDecimal seqHours) {
		this.seqHours = seqHours;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
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

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public void setLenUnit(String lenUnit) {
		this.lenUnit = lenUnit;
	}

	public String getLenUnit() {
		return lenUnit;
	}

	/**
	 * @return isSample
	 */
	public String getIsSample() {
		return isSample;
	}

	/**
	 * @param isSample
	 *            要设置的 isSample
	 * 
	 */
	public void setIsSample(String isSample) {
		this.isSample = isSample;
	}

	/**
	 * @return sample_length
	 */
	public Double getSample_length() {
		return sample_length;
	}

	/**
	 * @param sample_length
	 *            要设置的 sample_length
	 * 
	 */
	public void setSample_length(Double sample_length) {
		this.sample_length = sample_length;
	}

}
