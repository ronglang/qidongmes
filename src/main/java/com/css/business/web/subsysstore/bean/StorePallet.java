package com.css.business.web.subsysstore.bean;

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
@Table(name ="store_pallet")
public class StorePallet implements BaseEntity{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@SequenceGenerator(name = "seq_store_pallet", allocationSize = 1, initialValue = 1, sequenceName = "seq_store_pallet") 
	@GeneratedValue(generator = "seq_store_pallet", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="pallet_name")
	private String palletName;
	@Column(name="pallet_type")
	private String palletType;
	@Column(name="pallet_rfid")
	private String palletRfid;
	@Column(name="remark")
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPalletName() {
		return palletName;
	}
	public void setPalletName(String palletName) {
		this.palletName = palletName;
	}
	public String getPalletType() {
		return palletType;
	}
	public void setPalletType(String palletType) {
		this.palletType = palletType;
	}
	public String getPalletRfid() {
		return palletRfid;
	}
	public void setPalletRfid(String palletRfid) {
		this.palletRfid = palletRfid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
