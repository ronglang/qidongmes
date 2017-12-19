package com.css.common.web.syscommon.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * TODO 临时表
 * @author huanghao
 * 2015年11月5日下午12:59:49
 */
@Entity
@Table(name = "TEMP")
public class Temp implements BaseEntity {
	
	@Transient
	private static final long serialVersionUID = -5220428748445696476L;
	private Integer id;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
