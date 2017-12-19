package com.css.common.web.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "wf_node")
public class WfNode implements BaseEntity {

	private Integer id;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "node_id")
	private Integer nodeId;
	@Column(name = "node_type")
	private String nodeType;
	@Column(name = "node_name")
	private String nodeName;
	@Column(name = "node_x")
	private String nodeX;
	@Column(name = "node_y")
	private String nodeY;
	@Column(name = "node_width")
	private String nodeWidth;
	@Column(name = "node_height")
	private String nodeHeight;
	@Column(name = "node_sx")
	private String nodeSx;
	@Column(name = "node_url")
	private String nodeUrl;
	@Column(name = "node_cl")
	private String nodeCl;
	@Column(name = "node_pyzm")
	private String nodePyzm;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
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

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeX() {
		return nodeX;
	}

	public void setNodeX(String nodeX) {
		this.nodeX = nodeX;
	}

	public String getNodeY() {
		return nodeY;
	}

	public void setNodeY(String nodeY) {
		this.nodeY = nodeY;
	}

	public String getNodeWidth() {
		return nodeWidth;
	}

	public void setNodeWidth(String nodeWidth) {
		this.nodeWidth = nodeWidth;
	}

	public String getNodeHeight() {
		return nodeHeight;
	}

	public void setNodeHeight(String nodeHeight) {
		this.nodeHeight = nodeHeight;
	}

	public String getNodeSx() {
		return nodeSx;
	}

	public void setNodeSx(String nodeSx) {
		this.nodeSx = nodeSx;
	}

	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public String getNodeCl() {
		return nodeCl;
	}

	public void setNodeCl(String nodeCl) {
		this.nodeCl = nodeCl;
	}

	public String getNodePyzm() {
		return nodePyzm;
	}

	public void setNodePyzm(String nodePyzm) {
		this.nodePyzm = nodePyzm;
	}


}
