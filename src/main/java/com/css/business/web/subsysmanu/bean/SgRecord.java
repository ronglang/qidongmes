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

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "sg_record")
public class SgRecord implements BaseEntity{
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_sg_record", allocationSize = 1, initialValue = 1, sequenceName = "seq_sg_record")
	@GeneratedValue(generator = "seq_sg_record", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "sg_types")
	private String sgtypes;
	@Column(name = "rfid_num")
	private String rfidnum;
	@Column(name = "sg_state")
	private String sgstate;
	@Column(name = "name")
	private String name;
	@Column(name = "create_by")
	private String createby;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSgtypes() {
		return sgtypes;
	}

	public void setSgtypes(String sgtypes) {
		this.sgtypes = sgtypes;
	}

	public String getRfidnum() {
		return rfidnum;
	}

	public void setRfidnum(String rfidnum) {
		this.rfidnum = rfidnum;
	}

	public String getSgstate() {
		return sgstate;
	}

	public void setSgstate(String sgstate) {
		this.sgstate = sgstate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
