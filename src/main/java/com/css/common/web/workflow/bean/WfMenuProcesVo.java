package com.css.common.web.workflow.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WfMenuProcesVo implements Serializable{
	private static final long serialVersionUID = 2449232490350474555L;
	private Integer id;
	private Integer menuid;
	private String psdm;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date mlbdsj;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public String getPsdm() {
		return psdm;
	}
	public void setPsdm(String psdm) {
		this.psdm = psdm;
	}
	public Date getMlbdsj() {
		return mlbdsj;
	}
	public void setMlbdsj(Date mlbdsj) {
		this.mlbdsj = mlbdsj;
	}
}
