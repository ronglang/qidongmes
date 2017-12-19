
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
 * @Title: CraBomAttribute.java   
 * @Package com.css.business.web.subsyscraft.bean   
 * @Description: *BOM性能参数表
 * @author   rb
 * @date 2017年7月26日 下午5:10:03   
 * @company  SMTC   
 */

@Entity
@Table(name = "cra_bom_attribute")
@SequenceGenerator(name = "seq_cra_bom_attribute", sequenceName = "seq_cra_bom_attribute")
public class CraBomAttribute implements BaseEntity{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	@Transient
	private static final long serialVersionUID = 5674519936891644981L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 关系表编码 */
	@Column(name = "relation_code")
	private String relationCode;
	/** 属性参数值 */
	@Column(name = "attribute_param_value")
	private String attributeParamValue;
	/** 属性参数最小值 */
	@Column(name = "attribute_param_min")
	private String attributeParamMin;
	/** 属性参数最大值 */
	@Column(name = "attribute_param_max")
	private String attributeParamMax;
	/** 属性编码 */
	@Column(name = "attribute_code")
	private String attributeCode;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	/**
	 * @return  id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 *  @param id 要设置的 id 
	 *    
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return  relationCode
	 */
	public String getRelationCode() {
		return relationCode;
	}
	/**
	 *  @param relationCode 要设置的 relationCode 
	 *    
	 */
	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}
	/**
	 * @return  attributeParamValue
	 */
	public String getAttributeParamValue() {
		return attributeParamValue;
	}
	/**
	 *  @param attributeParamValue 要设置的 attributeParamValue 
	 *    
	 */
	public void setAttributeParamValue(String attributeParamValue) {
		this.attributeParamValue = attributeParamValue;
	}
	/**
	 * @return  attributeParamMin
	 */
	public String getAttributeParamMin() {
		return attributeParamMin;
	}
	/**
	 *  @param attributeParamMin 要设置的 attributeParamMin 
	 *    
	 */
	public void setAttributeParamMin(String attributeParamMin) {
		this.attributeParamMin = attributeParamMin;
	}
	/**
	 * @return  attributeParamMax
	 */
	public String getAttributeParamMax() {
		return attributeParamMax;
	}
	/**
	 *  @param attributeParamMax 要设置的 attributeParamMax 
	 *    
	 */
	public void setAttributeParamMax(String attributeParamMax) {
		this.attributeParamMax = attributeParamMax;
	}
	/**
	 * @return  attributeCode
	 */
	public String getAttributeCode() {
		return attributeCode;
	}
	/**
	 *  @param attributeCode 要设置的 attributeCode 
	 *    
	 */
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	/**
	 * @return  createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 *  @param createDate 要设置的 createDate 
	 *    
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return  createBy
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 *  @param createBy 要设置的 createBy 
	 *    
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	
}
