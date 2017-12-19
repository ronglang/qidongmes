package com.css.common.web.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "wf_node_trans")
public class WfNodeTrans implements BaseEntity {

	private Integer id;
	@Column(name = "ts_id")
	private Integer tsId;
	@Column(name = "ts_name")
	private String tsName;
	@Column(name = "ts_from")
	private Integer tsFrom;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "ts_to")
	private Integer tsTo;


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTsId() {
		return tsId;
	}

	public void setTsId(Integer tsId) {
		this.tsId = tsId;
	}

	public String getTsName() {
		return tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}

	public Integer getTsFrom() {
		return tsFrom;
	}

	public void setTsFrom(Integer tsFrom) {
		this.tsFrom = tsFrom;
	}

	public String getPsDm() {
		return psDm;
	}

	public void setPsDm(String psDm) {
		this.psDm = psDm;
	}

	public Integer getTsTo() {
		return tsTo;
	}

	public void setTsTo(Integer tsTo) {
		this.tsTo = tsTo;
	}

}
