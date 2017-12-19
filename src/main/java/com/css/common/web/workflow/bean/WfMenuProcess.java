package com.css.common.web.workflow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "wf_menu_process")
public class WfMenuProcess implements BaseEntity {

	private Integer id;
	@Column(name = "menu_id")
	private Integer menuId;
	@Column(name = "ps_dm")
	private String psDm;
	@Column(name = "ml_bdsj")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date mlBdsj;


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getPsDm() {
		return psDm;
	}

	public void setPsDm(String psDm) {
		this.psDm = psDm;
	}

	public Date getMlBdsj() {
		return mlBdsj;
	}

	public void setMlBdsj(Date mlBdsj) {
		this.mlBdsj = mlBdsj;
	}


}
