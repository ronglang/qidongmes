package com.css.business.web.subsyscraft.bean;

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

@Entity
@Table(name = "cra_seq")
public class CraSeq implements BaseEntity {
	/**
	 * @author：曾斌
	 * @Date：2017-06-05 工序表
	 */
	@Transient
	private static final long serialVersionUID = -561673182872321317L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_cra_seq", allocationSize = 1, initialValue = 1, sequenceName = "seq_cra_seq")
	@GeneratedValue(generator = "seq_cra_seq", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "seq_code")
	private String seqCode;
	@Column(name = "seq_name")
	private String seqName;
	@Column(name = "seq_type")
	private String seqType;
	@Column(name = "child_code")
	private String childCode;
	/** 质检截取长度(单位m) */
	@Column(name = "test_wastage")
	private String testWastage;

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

	public String getSeqType() {
		return seqType;
	}

	public void setSeqType(String seqType) {
		this.seqType = seqType;
	}

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	/**
	 * @return testWastage
	 */
	public String getTestWastage() {
		return testWastage;
	}

	/**
	 * @param testWastage
	 *            要设置的 testWastage
	 * 
	 */
	public void setTestWastage(String testWastage) {
		this.testWastage = testWastage;
	}

}
