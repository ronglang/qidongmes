package com.css.common.web.workflow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "wf_yewu_process")
public class WfYewuProcess implements BaseEntity {

	private Integer id;
	@Column(name = "yewu_mc")
	private String yewuMc;
	@Column(name = "yewu_pyzm")
	private String yewuPyzm;
	@Column(name = "yewu_sfqygzl")
	private String yewuSfqygzl;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "yp_bdsj")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date ypBdsj;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYewuMc() {
		return yewuMc;
	}

	public void setYewuMc(String yewuMc) {
		this.yewuMc = yewuMc;
	}

	public String getYewuPyzm() {
		return yewuPyzm;
	}

	public void setYewuPyzm(String yewuPyzm) {
		this.yewuPyzm = yewuPyzm;
	}

	public String getYewuSfqygzl() {
		return yewuSfqygzl;
	}

	public void setYewuSfqygzl(String yewuSfqygzl) {
		this.yewuSfqygzl = yewuSfqygzl;
	}

	public String getPsDm() {
		return psDm;
	}

	public void setPsDm(String psDm) {
		this.psDm = psDm;
	}

	public Date getYpBdsj() {
		return ypBdsj;
	}

	public void setYpBdsj(Date ypBdsj) {
		this.ypBdsj = ypBdsj;
	}

}
