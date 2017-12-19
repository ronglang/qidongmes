package com.css.business.web.subsysmanu.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @TODO  : 生产过程实时参数采集表 
 * @author: 翟春磊
 * @DATE  : 2017年7月19日
 */
@Entity
@Table(name = "mau_process_dany")
public class MauProcessDany  implements BaseEntity{
	private static final long serialVersionUID = 8956159698832944025L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "machine_id")
	private Integer machineId;
	@Column(name = "axis_name")
	private String axisName;
	//参数部分要压缩
	@Column(name = "param_code")
	private String paramCode; 
	@Column(name = "param_value")
	private String paramValue;
	@Column(name = "is_exception")
	private String isException;
	@Column(name = "cj_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date cjTime;
	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "seq_code")
	private String seqCode;
	
	@Column(name = "compressed_param")
	private String compressedParam; //压缩后的参数  
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	
	@Transient
	private String machineCode; //机台编号
	
	@Transient
	private String gzipParamInfoList; //压缩后的参数
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	public String getAxisName() {
		return axisName;
	}
	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getIsException() {
		return isException;
	}
	public void setIsException(String isException) {
		this.isException = isException;
	}
	public Date getCjTime() {
		return cjTime;
	}
	public void setCjTime(Date cjTime) {
		this.cjTime = cjTime;
	}
	public String getSeqCode() {
		return seqCode;
	}
	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}
	public String getCompressedParam() {
		return compressedParam;
	}
	public void setCompressedParam(String compressedParam) {
		this.compressedParam = compressedParam;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	@Transient
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	@Transient
	public String getGzipParamInfoList() {
		return gzipParamInfoList;
	}
	public void setGzipParamInfoList(String gzipParamInfoList) {
		this.gzipParamInfoList = gzipParamInfoList;
	}
}
