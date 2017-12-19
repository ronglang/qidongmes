package com.css.business.web.subsysmanu.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.google.gson.Gson;

/**
 * 
 * @Title:MauOEEHistory.java
 * @Description:机台速度工单速度的历史
 * @author RB
 * @company SMTC
 * @date 2017年10月26日上午9:05:47
 */
@Entity
@Table(name = "mau_oee_history")
public class MauOEEHistory implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -2556799835758396007L;
	@Transient
	private Gson gson = new Gson();
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_mau_oee_history", allocationSize = 1, initialValue = 1, sequenceName = "seq_mau_oee_history")
	@GeneratedValue(generator = "seq_mau_oee_history", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;

	/** 工单号 */
	@Column(name = "course_code")
	private String courseCode;
	/** 工单号 */
	@Column(name = "mac_code")
	private String macCode;
	/** 就是EchartVo */
	@Column(name = "speed_json")
	private String echartsVO;
	/** 工单号 */
	@Column(name = "main_operator")
	private String mainOperator;
	/** 工单号 */
	@Column(name = "pro_ggxh")
	private String progxh;

	@Transient
	private HashMap<String, List<String>> data;

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

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getEchartsVO() {
		return echartsVO;
	}

	public void setEchartsVO(String echartsVO) {
		this.echartsVO = echartsVO;
	}

	public String getMainOperator() {
		return mainOperator;
	}

	public void setMainOperator(String mainOperator) {
		this.mainOperator = mainOperator;
	}

	public String getProgxh() {
		return progxh;
	}

	public void setProgxh(String progxh) {
		this.progxh = progxh;
	}

	public HashMap<String, List<String>> getData() {
		return data;
	}

	public void setData(HashMap<String, List<String>> data) {
		this.data = data;
	}
	

}
