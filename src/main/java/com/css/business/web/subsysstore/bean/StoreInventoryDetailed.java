package com.css.business.web.subsysstore.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.css.common.web.syscommon.bean.BaseEntity;
@Entity
@Table(name = "store_inventory_detailed")
public class StoreInventoryDetailed implements BaseEntity{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1616737569120672493L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_store_inventory_detailed", sequenceName = "seq_store_inventory_detailed")  
	@GeneratedValue(generator = "seq_store_inventory_detailed", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="create_by")
	private String createBy;
	private Date create_date;
	private Timestamp inv_time;//盘点时间
	private String inv_materiel ;//盘点物料_
	
	private String inv_model;//盘点规格型号
	
	private String inv_color;//盘点颜色
	
	private Double inv_count;//盘点库存量
	
	private String inv_result;;//盘点结果
	
	private Double inv_lack_number;//缺料量
	
	private String unit;//计量单位

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Timestamp getInv_time() {
		return inv_time;
	}

	public void setInv_time(Timestamp inv_time) {
		this.inv_time = inv_time;
	}

	public String getInv_materiel() {
		return inv_materiel;
	}

	public void setInv_materiel(String inv_materiel) {
		this.inv_materiel = inv_materiel;
	}

	public String getInv_model() {
		return inv_model;
	}

	public void setInv_model(String inv_model) {
		this.inv_model = inv_model;
	}

	public String getInv_color() {
		return inv_color;
	}

	public void setInv_color(String inv_color) {
		this.inv_color = inv_color;
	}

	public Double getInv_count() {
		return inv_count;
	}

	public void setInv_count(Double inv_count) {
		this.inv_count = inv_count;
	}

	public String getInv_result() {
		return inv_result;
	}

	public void setInv_result(String inv_result) {
		this.inv_result = inv_result;
	}

	public Double getInv_lack_number() {
		return inv_lack_number;
	}

	public void setInv_lack_number(Double inv_lack_number) {
		this.inv_lack_number = inv_lack_number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
}
