
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
 * @Title: CraBomProduct.java   
 * @Package com.css.business.web.subsyscraft.bean   
 * @Description: 产品BOM表，标识产品的理论模型   
 * @author   rb
 * @date 2017年7月26日 下午5:17:49   
 * @company  SMTC   
 */
@Entity
@Table(name = "cra_bom_product")
@SequenceGenerator(name = "seq_cra_bom_product", sequenceName = "seq_cra_bom_product")
public class CraBomProduct implements BaseEntity{
	
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	@Transient
	private static final long serialVersionUID = 2363723415171989409L;

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** 产品BOM编码 */
	@Column(name = "bom_product_code")
	private String bomProductCode;
	/** 产品BOM名称 */
	@Column(name = "bom_product_name")
	private String bomProductName;
	/** 属性编码 */
	@Column(name = "attribute_code")
	private String attributeCode;
	/** 产品BOM类型 */
	@Column(name = "type")
	private String type;
	/** 颜色，应该是一个枚举值 */
	@Column(name = "color")
	private String color;
	/** 产品编号 */
	@Column(name = "pro_code")
	private String proCode;
	/** 产品工艺表code */
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	/** 产品规格型号 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 关系表编码 */
	@Column(name = "rela_code")
	private String relaCode;
	
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
	 * @return  bomProductCode
	 */
	public String getBomProductCode() {
		return bomProductCode;
	}
	/**
	 *  @param bomProductCode 要设置的 bomProductCode 
	 *    
	 */
	public void setBomProductCode(String bomProductCode) {
		this.bomProductCode = bomProductCode;
	}
	/**
	 * @return  bomProductName
	 */
	public String getBomProductName() {
		return bomProductName;
	}
	/**
	 *  @param bomProductName 要设置的 bomProductName 
	 *    
	 */
	public void setBomProductName(String bomProductName) {
		this.bomProductName = bomProductName;
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
	 * @return  type
	 */
	public String getType() {
		return type;
	}
	/**
	 *  @param type 要设置的 type 
	 *    
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return  color
	 */
	public String getColor() {
		return color;
	}
	/**
	 *  @param color 要设置的 color 
	 *    
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return  proCode
	 */
	public String getProCode() {
		return proCode;
	}
	/**
	 *  @param proCode 要设置的 proCode 
	 *    
	 */
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	/**
	 * @return  proCraftCode
	 */
	public String getProCraftCode() {
		return proCraftCode;
	}
	/**
	 *  @param proCraftCode 要设置的 proCraftCode 
	 *    
	 */
	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}
	/**
	 * @return  proGgxh
	 */
	public String getProGgxh() {
		return proGgxh;
	}
	/**
	 *  @param proGgxh 要设置的 proGgxh 
	 *    
	 */
	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}
	/**
	 * @return  relaCode
	 */
	public String getRelaCode() {
		return relaCode;
	}
	/**
	 *  @param relaCode 要设置的 relaCode 
	 *    
	 */
	public void setRelaCode(String relaCode) {
		this.relaCode = relaCode;
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
