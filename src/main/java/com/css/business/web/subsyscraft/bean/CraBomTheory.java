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

/**
 * @Title: CraBomTheory.java
 * @Package com.css.business.web.subsyscraft.bean
 * @Description: 理论BOM
 * @author rb
 * @date 2017年7月26日 下午5:34:41
 * @company SMTC
 */
@Entity
@Table(name = "cra_bom_theory")
@SequenceGenerator(name = "seq_cra_bom_theory", sequenceName = "seq_cra_bom_theory")
public class CraBomTheory implements BaseEntity {

	@Transient
	private static final long serialVersionUID = 4213968148474195015L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 参数名称(关联sys_dictionary 也可是是机台名称) */
	@Column(name = "param_name")
	private String paramName;
	/** 第三方属性 */
	@Column(name = "third_prop")
	private String thirdProp;
	/** 单位 */
	@Column(name = "unit")
	private String unit;
	/** 参数值 */
	@Column(name = "param_value")
	private String paramValue;
	/** 描述 */
	@Column(name = "remark")
	private String remark;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	/**最小值 */
	@Column(name = "param_min")
	private String paramMin;
	/**最大值 */
	@Column(name = "param_max")
	private String paramMax;
	/**参数类型 */
	@Column(name = "param_type")
	private String paramType;
	/**主表编码*/
	@Column(name = "pcsc_rela_code")
	private String pcscRelaCode;
	
	

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getThirdProp() {
		return thirdProp;
	}

	public void setThirdProp(String thirdProp) {
		this.thirdProp = thirdProp;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
	/**
	 * @return  paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 *  @param paramValue 要设置的 paramValue 
	 *    
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParamMin() {
		return paramMin;
	}

	public void setParamMin(String paramMin) {
		this.paramMin = paramMin;
	}

	public String getParamMax() {
		return paramMax;
	}

	public void setParamMax(String paramMax) {
		this.paramMax = paramMax;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getPcscRelaCode() {
		return pcscRelaCode;
	}

	public void setPcscRelaCode(String pcscRelaCode) {
		this.pcscRelaCode = pcscRelaCode;
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            要设置的 createDate
	 * 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy
	 *            要设置的 createBy
	 * 
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
