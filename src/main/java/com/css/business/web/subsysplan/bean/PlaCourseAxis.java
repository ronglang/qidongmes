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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @Title:PlaCourseAxis.java
 * @Description:工单具体轴情况
 * @author RB
 * @company SMTC
 * @date 2017年11月4日上午9:05:49
 */
@Entity
@Table(name = "Pla_Course_Axis")
public class PlaCourseAxis implements BaseEntity{

	@Transient
	private static final long serialVersionUID = 7155197830681129130L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_Pla_Course_Axis", sequenceName = "seq_pla_course")
	@GeneratedValue(generator = "seq_Pla_Course_Axis", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	/** 工单编号 */
	@Column(name = "course_code")
	private String courseCode;
	/** 轴颜色 */
	@Column(name = "color")
	private String color;
	/** 产品规格型号 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	/** 轴数量 */
	@Column(name = "axis_num")
	private Integer axisNum;
	/** 每轴长度 */
	@Column(name = "axis_length")
	private Integer axisLength;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public Integer getAxisNum() {
		return axisNum;
	}

	public void setAxisNum(Integer axisNum) {
		this.axisNum = axisNum;
	}

	public Integer getAxisLength() {
		return axisLength;
	}

	public void setAxisLength(Integer axisLength) {
		this.axisLength = axisLength;
	}

}
