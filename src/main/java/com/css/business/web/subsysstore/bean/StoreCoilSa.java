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
@Table(name = "STORE_COIL_SA")
@SequenceGenerator(name = "SEQ_STORE_COIL_SA", sequenceName = "SEQ_STORE_COIL_SA")
public class StoreCoilSa implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -5817031078671730493L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 区域名称 */
	@Column(name = "area_name")
	private String area_name;
	/** 区段位（线盘数据字典） */
	@Column(name = "area_position")
	private String area_position;
	/** 区域类型 */
	@Column(name = "area_type")
	private String area_type;
	/**  */
	@Column(name = "create_by")
	private String create_by;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "area_mater_type")
	private String areaMaterType;
	@Column(name = "area_rfid")
	private String areaRfid;
	
	@Column(name = "allowance")
	private Integer allowance;  // 余量
	@Column(name = "capacity")
	private Integer capacity;  //  容量
	

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

	/**
	 * @return area_name
	 */
	public String getArea_name() {
		return area_name;
	}

	/**
	 * @param area_name
	 *            要设置的 area_name
	 * 
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	/**
	 * @return area_position
	 */
	public String getArea_position() {
		return area_position;
	}

	/**
	 * @param area_position
	 *            要设置的 area_position
	 * 
	 */
	public void setArea_position(String area_position) {
		this.area_position = area_position;
	}

	/**
	 * @return area_type
	 */
	public String getArea_type() {
		return area_type;
	}

	/**
	 * @param area_type
	 *            要设置的 area_type
	 * 
	 */
	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}

	/**
	 * @return create_by
	 */
	public String getCreate_by() {
		return create_by;
	}

	/**
	 * @param create_by
	 *            要设置的 create_by
	 * 
	 */
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
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

	public String getAreaMaterType() {
		return areaMaterType;
	}

	public void setAreaMaterType(String areaMaterType) {
		this.areaMaterType = areaMaterType;
	}

	public String getAreaRfid() {
		return areaRfid;
	}

	public void setAreaRfid(String areaRfid) {
		this.areaRfid = areaRfid;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
}
