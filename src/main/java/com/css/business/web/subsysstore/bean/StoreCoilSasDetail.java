package com.css.business.web.subsysstore.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "STORE_COIL_SAS_DETAIL")
@SequenceGenerator(name = "SEQ_STORE_COIL_SAS_DETAIL", sequenceName = "SEQ_STORE_COIL_SAS_DETAIL")
public class StoreCoilSasDetail implements BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 所属存放区域表ID */
	@Column(name = "sysa_id")
	private Integer sysaId;
	/** 制品类型（原料、半成品、工序、成品） */
	@Column(name = "product_stype")
	private String productStype;
	/** 盘规格 */
	@Column(name = "coil_specification")
	private String coilSpecification;
	/** 余量 */
	@Column(name = "remain")
	private Integer remain;
	/** 容量 */
	@Column(name = "capacity")
	private Integer capacity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysaId() {
		return sysaId;
	}

	public void setSysaId(Integer sysaId) {
		this.sysaId = sysaId;
	}

	/**
	 * @return productStype
	 */
	public String getProductStype() {
		return productStype;
	}

	/**
	 * @param productStype
	 *            要设置的 productStype
	 * 
	 */
	public void setProductStype(String productStype) {
		this.productStype = productStype;
	}

	/**
	 * @return coilSpecification
	 */
	public String getCoilSpecification() {
		return coilSpecification;
	}

	/**
	 * @param coilSpecification
	 *            要设置的 coilSpecification
	 * 
	 */
	public void setCoilSpecification(String coilSpecification) {
		this.coilSpecification = coilSpecification;
	}

	/**
	 * @return remain
	 */
	public Integer getRemain() {
		return remain;
	}

	/**
	 * @param remain
	 *            要设置的 remain
	 * 
	 */
	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	/**
	 * @return capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            要设置的 capacity
	 * 
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

}
