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
 * @Description: 质量回溯详细信息
 * @author ghx
 * @date 2017年10月13日 
 * @company SMTC
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "quality_undo_detail_view")
public class QualityUndoDetail implements BaseEntity {
	


	

	public Integer getIndexid() {
		return indexid;
	}

	public void setIndexid(Integer indexid) {
		this.indexid = indexid;
	}


	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getMaccode() {
		return maccode;
	}

	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

	public String getSeqcode() {
		return seqcode;
	}

	public void setSeqcode(String seqcode) {
		this.seqcode = seqcode;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getFdtime() {
		return fdtime;
	}

	public void setFdtime(String fdtime) {
		this.fdtime = fdtime;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getMaterial() {
		return materil;
	}

	public void setMaterial(String materil) {
		this.materil = materil;
	}

	public String getMain_by() {
		return main_by;
	}

	public void setMain_by(String main_by) {
		this.main_by = main_by;
	}


	@Id
	@Column(name = "indexid")
	private Integer indexid;
	
	@Column(name = "workcode")
	private String workcode;
	
	@Column(name = "maccode")
	private String maccode;
	
	@Column(name = "seqcode")
	private String seqcode;
	
	@Column(name = "step")
	private String step;
	
	@Column(name = "fdtime")
	private String fdtime;
	
	@Column(name = "speed")
	private String speed;
	
	@Column(name = "materil")
	private String materil;
	
	@Column(name = "main_by")
	private String main_by;
	
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
