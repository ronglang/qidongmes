package com.css.business.web.subsysplan.bean;

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

import org.springframework.format.annotation.DateTimeFormat;

import com.css.commcon.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PLA_UNIT_CON_MATER")
@SequenceGenerator(name = "SEQ_PLA_UNIT_CON_MATER", sequenceName = "SEQ_PLA_UNIT_CON_MATER")
public class PlaUnitConMater implements BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "obj_name")
	private String objName;
	@Column(name = "obj_ggxh")
	private String objGgxh;
	@Column(name = "obj_color")
	private String objColor;
	@Column(name = "obj_count")
	private Double objCount;
	@Column(name = "unit")
	private String unit;
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjGgxh() {
		return objGgxh;
	}

	public void setObjGgxh(String objGgxh) {
		this.objGgxh = objGgxh;
	}

	public String getObjColor() {
		return objColor;
	}

	public void setObjColor(String objColor) {
		this.objColor = objColor;
	}

	public Double getObjCount() {
		return objCount;
	}

	public void setObjCount(Double objCount) {
		this.objCount = objCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


}
