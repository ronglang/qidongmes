package com.css.business.web.subsysprojconstruct.bean;

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
@Table(name = "projcon_mac")
public class ProjconMac implements BaseEntity {
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_projcon_mac", allocationSize = 1, initialValue = 1, sequenceName = "seq_projcon_mac")  
	@GeneratedValue(generator = "seq_projcon_mac", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "process_name")
	private String processName;
	@Column(name = "mac_code")
	private String macCode;
	@Column(name = "mac_state")
	private String macState;
	@Column(name = "selec_state")
	private String selecState;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getMacState() {
		return macState;
	}

	public void setMacState(String macState) {
		this.macState = macState;
	}

	public String getSelecState() {
		return selecState;
	}

	public void setSelecState(String selecState) {
		this.selecState = selecState;
	}
	
	
}
