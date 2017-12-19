package com.css.common.web.apachemq.bean;

import java.io.Serializable;

/**
 * @TODO  : 生产过程实时参数采集表 
 * @author: 翟春磊
 * @DATE  : 2017年7月19日
 */
public class MauProcessDanyVo  implements Serializable{
	private static final long serialVersionUID = 8949569650059532090L;
	private Integer machineId; //机台ID
	private String axisName; //轴名称
	//private String paramCode; //参数编码
	//private String paramValue; //参数值
	//private String isException; //是否异常（是或或）
	//private Date cjTime; //采集时间点  Long
	private String courseCode; //工单编号
	private String machineCode; //机台编号
	
	private String seqCode; //工序编号
	
	private String gzipParamInfoList; //压缩后的参数
	
	/**
	 * {"machineId":0,"axisName":"GD20170908119_0308","machineCode":"LS01","gzipParamInfoList":"H4sI
	 * @return
	 */

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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public String getGzipParamInfoList() {
		return gzipParamInfoList;
	}

	public void setGzipParamInfoList(String gzipParamInfoList) {
		this.gzipParamInfoList = gzipParamInfoList;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

}
