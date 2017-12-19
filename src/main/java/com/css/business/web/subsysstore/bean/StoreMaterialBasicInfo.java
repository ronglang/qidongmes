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
@Table(name = "store_material_basic_info")
public class StoreMaterialBasicInfo implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@SequenceGenerator(name = "seq_store_material_basic_info", allocationSize = 1, initialValue = 1, sequenceName = "seq_store_material_basic_info")
	@GeneratedValue(generator = "seq_store_material_basic_info", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "material_type")
	private String material_type;
	@Column(name = "mater_name")
	private String mater_name;
	@Column(name = "mater_ggxh")
	private String mater_ggxh;
	@Column(name = "color")
	private String color;
	@Column(name = "delivery_info")
	private String delivery_info; //供应商
	@Column(name = "remark")
	private String remark;
	@Column(name = "create_by")
	private String create_by;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date create_date;
	
	@Column(name = "p_code")
	private String pCode;
	@Column(name="unit")
	private String unit;
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
	 * @return material_type
	 */
	public String getMaterial_type() {
		return material_type;
	}

	/**
	 * @param material_type
	 *            要设置的 material_type
	 * 
	 */
	public void setMaterial_type(String material_type) {
		this.material_type = material_type;
	}

	/**
	 * @return mater_name
	 */
	public String getMater_name() {
		return mater_name;
	}

	/**
	 * @param mater_name
	 *            要设置的 mater_name
	 * 
	 */
	public void setMater_name(String mater_name) {
		this.mater_name = mater_name;
	}

	/**
	 * @return mater_ggxh
	 */
	public String getMater_ggxh() {
		return mater_ggxh;
	}

	/**
	 * @param mater_ggxh
	 *            要设置的 mater_ggxh
	 * 
	 */
	public void setMater_ggxh(String mater_ggxh) {
		this.mater_ggxh = mater_ggxh;
	}

	/**
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            要设置的 color
	 * 
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return delivery_info
	 */
	public String getDelivery_info() {
		return delivery_info;
	}

	/**
	 * @param delivery_info
	 *            要设置的 delivery_info
	 * 
	 */
	public void setDelivery_info(String delivery_info) {
		this.delivery_info = delivery_info;
	}

	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            要设置的 remark
	 * 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @return create_date
	 */
	public Date getCreate_date() {
		return create_date;
	}

	/**
	 * @param create_date
	 *            要设置的 create_date
	 * 
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
	
}
