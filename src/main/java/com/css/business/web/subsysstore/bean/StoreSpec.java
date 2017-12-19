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
@Table(name="sys_param_range")
public class StoreSpec implements BaseEntity{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@SequenceGenerator(name = "seq_sys_param_range", allocationSize = 1, initialValue = 1, sequenceName = "seq_sys_param_range")  
	@GeneratedValue(generator = "seq_sys_param_range", strategy = GenerationType.SEQUENCE)
	Integer id;
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
	@Column(name="para_type")
	String paraType;
	@Column(name="para_name")
	String paraName;
	@Column(name="unit")
	String unit;
	@Column(name="para_from")
	String paraFrom;
	@Column(name="isforeign")
	String isForeign;
	@Column(name="para_value_min")
	Float paraValueMin;
	@Column(name="para_value_max")
	Float paraValueMax;
	public String getParaType() {
		return paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getParaFrom() {
		return paraFrom;
	}

	public void setParaFrom(String paraFrom) {
		this.paraFrom = paraFrom;
	}

	public String getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}

	public Float getParaValueMin() {
		return paraValueMin;
	}

	public void setParaValueMin(Float paraValueMin) {
		this.paraValueMin = paraValueMin;
	}

	public Float getParaValueMax() {
		return paraValueMax;
	}

	public void setParaValueMax(Float paraValueMax) {
		this.paraValueMax = paraValueMax;
	}
	
	

}
