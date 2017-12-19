package com.css.business.web.subsysquality.bean;

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
 * 
 * @Title: QualityBasicParam.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: 参数基础表(所有的参数在这个表里)
 * @author rb
 * @date 2017年8月30日 下午11:33:47
 * @company SMTC
 */
@Entity
@Table(name = "Quality_Basic_Param")
@SequenceGenerator(name = "SEQ_Quality_Basic_Param", sequenceName = "SEQ_Quality_Basic_Param")
public class QualityBasicParam implements BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = -3527340286359685909L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;

	/** 参数名称 */
	@Column(name = "param_name")
	private String paramName;
	/** 参考值 */
	@Column(name = "refer_value")
	private String referValue;
	/** 关联type的名称 */
	@Column(name = "basic_type_name")
	private String basicTypeName;
	/** 关联type的id */
	@Column(name = "basic_type_id")
	private Integer basicTypeId;

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

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getReferValue() {
		return referValue;
	}

	public void setReferValue(String referValue) {
		this.referValue = referValue;
	}

	/**
	 * @return basicTypeName
	 */
	public String getBasicTypeName() {
		return basicTypeName;
	}

	/**
	 * @param basicTypeName
	 *            要设置的 basicTypeName
	 * 
	 */
	public void setBasicTypeName(String basicTypeName) {
		this.basicTypeName = basicTypeName;
	}

	/**
	 * @return basicTypeId
	 */
	public Integer getBasicTypeId() {
		return basicTypeId;
	}

	/**
	 * @param basicTypeId
	 *            要设置的 basicTypeId
	 * 
	 */
	public void setBasicTypeId(Integer basicTypeId) {
		this.basicTypeId = basicTypeId;
	}

}
