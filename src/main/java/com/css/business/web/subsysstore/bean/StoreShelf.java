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
@Table(name = "store_shelf")
public class StoreShelf implements BaseEntity {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_store_shelf", allocationSize = 1, initialValue = 1, sequenceName = "seq_store_shelf")  
	@GeneratedValue(generator = "seq_store_shelf", strategy = GenerationType.SEQUENCE)
	private Integer id;
	/** 货架名称 */
	@Column(name = "shelf_name")
	private String shelfName;
	/** 每格容量 */
	@Column(name = "capacity_each")
	private String capacityEach;
	/** 余量 */
	@Column(name = "remain")
	private String remain;
	/** 创建人 */
	@Column(name = "create_by")
	private String createBy;
	/** 创建时间 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	/** 货架区段位 */
	@Column(name = "columns")
	private String column;
	/** 货架层号 */
	@Column(name = "floor")
	private String floor;
	/** 货架上货物包装规格 */
	@Column(name = "package_type")
	private String packageType;

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

	/**
	 * @return shelfName
	 */
	public String getShelfName() {
		return shelfName;
	}

	/**
	 * @param shelfName
	 *            要设置的 shelfName
	 * 
	 */
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	/**
	 * @return capacityEach
	 */
	public String getCapacityEach() {
		return capacityEach;
	}

	/**
	 * @param capacityEach
	 *            要设置的 capacityEach
	 * 
	 */
	public void setCapacityEach(String capacityEach) {
		this.capacityEach = capacityEach;
	}

	/**
	 * @return remain
	 */
	public String getRemain() {
		return remain;
	}

	/**
	 * @param remain
	 *            要设置的 remain
	 * 
	 */
	public void setRemain(String remain) {
		this.remain = remain;
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

	/**
	 * @return column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            要设置的 column
	 * 
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * @return floor
	 */
	public String getFloor() {
		return floor;
	}

	/**
	 * @param floor
	 *            要设置的 floor
	 * 
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
}
