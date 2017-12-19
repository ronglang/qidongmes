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

import org.springframework.format.annotation.DateTimeFormat;

import com.css.commcon.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PLA_WEEK_SEQ_HOURS")
@SequenceGenerator(name = "SEQ_PLA_WEEK_SEQ_HOURS", sequenceName = "SEQ_PLA_WEEK_SEQ_HOURS")
public class PlaWeekSeqHours implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_by")
	private String create_by;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "seq_name")
	private String seqName;
	@Column(name = "time_unit")
	private String timeUnit;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "calc_time")
	private Timestamp calcTime;
	@Column(name = "seq_hours")
	private Double seqHours;
	@Column(name = "product_part_len")
	private Integer productPartLen;
	@Column(name = "pla_order_id")
	private Integer plaOrderId;

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

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public Timestamp getCalcTime() {
		return calcTime;
	}

	public void setCalcTime(Timestamp calcTime) {
		this.calcTime = calcTime;
	}

	public Double getSeqHours() {
		return seqHours;
	}

	public void setSeqHours(Double seqHours) {
		this.seqHours = seqHours;
	}

	public Integer getProductPartLen() {
		return productPartLen;
	}

	public void setProductPartLen(Integer productPartLen) {
		this.productPartLen = productPartLen;
	}

	public Integer getPlaOrderId() {
		return plaOrderId;
	}

	public void setPlaOrderId(Integer plaOrderId) {
		this.plaOrderId = plaOrderId;
	}
	
}
