package com.css.business.web.subsysstore.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "store_unlock")
public class StoreUnlock implements BaseEntity{
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_store_unlock", sequenceName = "seq_store_unlock")  
	@GeneratedValue(generator = "seq_store_unlock", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "materiel_name")
	private String materielName;
	
	@Column(name = "materiel_ggxh")
	private String materielGgxh;
	
	@Column(name = "amount")
	private Integer amount;
	
	
	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name="unlock_time")
	private Date unlockTime;
	
	@Column(name="lock_id")
	private Integer lockId;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getMaterielGgxh() {
		return materielGgxh;
	}

	public void setMaterielGgxh(String materielGgxh) {
		this.materielGgxh = materielGgxh;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
