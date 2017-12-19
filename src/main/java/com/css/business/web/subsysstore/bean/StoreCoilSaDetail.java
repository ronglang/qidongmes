package com.css.business.web.subsysstore.bean;

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
@Table(name = "STORE_COIL_SA_DETAIL")
@SequenceGenerator(name = "SEQ_STORE_COIL_SA_DETAIL", sequenceName = "SEQ_STORE_COIL_SA_DETAIL")
public class StoreCoilSaDetail implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 2004225939454100669L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 线盘存放区域表ID */
	@Column(name = "scsa_id")
	private Integer scsaId;
	/** 线盘RFID */
	@Column(name = "coil_rfid")
	private String coilRfid;
	/** 制品类型（原料、半制品、成品、异常品、数据字典 */
	@Column(name = "product_type")
	private String productType;
	/** 线盘规格型号（盘的大小） */
	@Column(name = "coil_specifications")
	private String coilSpecifications;
	/** 线规格型号（原料、半成品、成品的规格型号） */
	@Column(name = "cable_specifications")
	private String cableSpecifications;
	/** 线盘类型（铜盘、木盘、铁盘） */
	@Column(name = "coil_style")
	private String coilStyle;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScsaId() {
		return scsaId;
	}

	public void setScsaId(Integer scsaId) {
		this.scsaId = scsaId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return coilRfid
	 */
	public String getCoilRfid() {
		return coilRfid;
	}

	/**
	 * @param coilRfid
	 *            要设置的 coilRfid
	 * 
	 */
	public void setCoilRfid(String coilRfid) {
		this.coilRfid = coilRfid;
	}

	/**
	 * @return productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            要设置的 productType
	 * 
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return coilSpecifications
	 */
	public String getCoilSpecifications() {
		return coilSpecifications;
	}

	/**
	 * @param coilSpecifications
	 *            要设置的 coilSpecifications
	 * 
	 */
	public void setCoilSpecifications(String coilSpecifications) {
		this.coilSpecifications = coilSpecifications;
	}

	/**
	 * @return cableSpecifications
	 */
	public String getCableSpecifications() {
		return cableSpecifications;
	}

	/**
	 * @param cableSpecifications
	 *            要设置的 cableSpecifications
	 * 
	 */
	public void setCableSpecifications(String cableSpecifications) {
		this.cableSpecifications = cableSpecifications;
	}

	/**
	 * @return coilStyle
	 */
	public String getCoilStyle() {
		return coilStyle;
	}

	/**
	 * @param coilStyle
	 *            要设置的 coilStyle
	 * 
	 */
	public void setCoilStyle(String coilStyle) {
		this.coilStyle = coilStyle;
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
