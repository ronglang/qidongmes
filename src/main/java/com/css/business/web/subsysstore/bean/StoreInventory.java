package com.css.business.web.subsysstore.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
@Table(name = "store_inventory")
public class StoreInventory implements BaseEntity{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1616737569120672493L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_store_inventory", sequenceName = "seq_store_inventory")  
	@GeneratedValue(generator = "seq_store_inventory", strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String create_by;
	private Timestamp create_date;
	private Timestamp inv_time;//盘点时间
	private String inv_materiel ;//盘点物料_

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public String getInv_timeFormat() {
		if(inv_time!=null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = df.format(inv_time);
			return dateString;
		}
		return "";
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
	
	
}
