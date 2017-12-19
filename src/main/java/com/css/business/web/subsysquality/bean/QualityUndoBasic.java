package com.css.business.web.subsysquality.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;


/**
 * 
 * @Title: QualityUndoBasic.java
 * @Package com.css.business.web.subsysquality.bean
 * @Description: 质量回溯主视图
 * @author ghx
 * @date 2017年10月13日 
 * @company SMTC
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "quality_undo_view")
public class QualityUndoBasic implements BaseEntity {
	
	public Integer getIndexid() {
		return indexid;
	}

	public void setIndexid(Integer indexid) {
		this.indexid = indexid;
	}

	public String getAxiscode() {
		return axiscode;
	}

	public void setAxiscode(String axiscode) {
		this.axiscode = axiscode;
	}

	public String getWscode() {
		return wscode;
	}

	public void setWscode(String wscode) {
		this.wscode = wscode;
	}



	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	@Id
	@Column(name = "indexid")
	private Integer indexid;
	
	@Column(name = "axiscode")
	private String axiscode;
	
	@Column(name = "workcode")
	private String wscode;
	
	
	@Column(name = "end_date")
	private Date end_date;

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	





}
