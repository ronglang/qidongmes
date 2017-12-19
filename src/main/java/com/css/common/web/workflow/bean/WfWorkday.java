package com.css.common.web.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.css.common.web.syscommon.bean.BaseEntity;

@Entity
@Table(name = "wf_workday")
public class WfWorkday implements BaseEntity {
	private Integer id;
	@Column(name = "wd_year")
	private String wdYear;
	@Column(name = "wd_day")
	private String wdDay;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWdYear() {
		return wdYear;
	}

	public void setWdYear(String wdYear) {
		this.wdYear = wdYear;
	}

	public String getWdDay() {
		return wdDay;
	}

	public void setWdDay(String wdDay) {
		this.wdDay = wdDay;
	}

}
