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
 * @Title: QualityBasicValue.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: 质检参数数值表 (具体的参数与质检报告关联)
 * @author rb
 * @date 2017年8月30日 下午11:45:47
 * @company SMTC
 */
/**
 * @Title: QualityBasicValue.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author rb
 * @date 2017年9月5日 下午3:44:33
 * @company SMTC
 */

@Entity
@Table(name = "Quality_Basic_Value")
@SequenceGenerator(name = "SEQ_Quality_Basic_Value", sequenceName = "SEQ_Quality_Basic_Value")
public class QualityBasicValue implements BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@Transient
	private static final long serialVersionUID = 4612109346780500945L;
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
	/** 实际值 */
	@Column(name = "param_value")
	private String paramValue;
	/** 所属类型名称 */
	@Column(name = "basic_type_name")
	private String basicTypeName;
	/** 报告id */
	@Column(name = "qua_code")
	private String quaCode;
	/** 第二次的值 */
	@Column(name = "second_value")
	private String secondValue;

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

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * @return quaCode
	 */
	public String getQuaCode() {
		return quaCode;
	}

	/**
	 * @param quaCode
	 *            要设置的 quaCode
	 * 
	 */
	public void setQuaCode(String quaCode) {
		this.quaCode = quaCode;
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
	 * @return secondValue
	 */
	public String getSecondValue() {
		return secondValue;
	}

	/**
	 * @param secondValue
	 *            要设置的 secondValue
	 * 
	 */
	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}

}
