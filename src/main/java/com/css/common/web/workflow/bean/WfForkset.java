package com.css.common.web.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "wf_forkset")
public class WfForkset implements BaseEntity {
	private Integer id;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "node_id")
	private Integer nodeId;
	@Column(name = "fk_desc")
	private String fkDesc;
	@Column(name = "fk_desc_val")
	private String fkDescVal;
	@Column(name = "fk_to_node_id")
	private Integer fkToNodeId;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPsDm() {
		return psDm;
	}

	public void setPsDm(String psDm) {
		this.psDm = psDm;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getFkDesc() {
		return fkDesc;
	}

	public void setFkDesc(String fkDesc) {
		this.fkDesc = fkDesc;
	}

	public String getFkDescVal() {
		return fkDescVal;
	}

	public void setFkDescVal(String fkDescVal) {
		this.fkDescVal = fkDescVal;
	}

	public Integer getFkToNodeId() {
		return fkToNodeId;
	}

	public void setFkToNodeId(Integer fkToNodeId) {
		this.fkToNodeId = fkToNodeId;
	}

}
