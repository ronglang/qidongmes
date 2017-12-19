package com.css.business.web.subsyscraft.bean;


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
@Table(name = "sys_parameter")
@SequenceGenerator(name = "seq_sys_parameter", sequenceName = "seq_sys_parameter")
public class SysParameter implements BaseEntity {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "seq_sys_parameter", strategy = GenerationType.SEQUENCE)
	private Integer id;
	/**
	 * 线规格
	 */
	@Column(name = "para_name")
	private String paraName;
	/**
	 * 线盘规格
	 */
	@Column(name = "para_type")
	private String paraType;
	/**
	 * 线盘容量
	 */
	@Column(name = "para_value")
	private String paraValue;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	
	

	
}
