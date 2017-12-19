package com.css.common.web.apachemq.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @TODO  : 机台数据提交VO 不包含实时数据 
 * @author: 翟春磊
 * @DATE  : 2017年7月20日
 */
public class MqJTSaveDataVO implements Serializable{

	private static final long serialVersionUID = -8917049499652237600L;




	//工作单
	private String courseCode;

	//机台编码
	private String macCode;
	//工序的step
	private int step;

	//轴号
	private String axisName;
	//工序编码
	private String seqCode;
	//收线时间
	//private Timestamp sxTime;
	private Long sxTime;
	//完成状态(正常结束；异常结束；) 
	private String status;
	
	//放线轴rfid,多个以英文逗号隔开
	private String rfids_begin;
	
	//消息类型：落轴，非落轴
	private String mqType;
	
	//private Timestamp actualBeginTime; //实际生产开始时间   从准备时间开始算起
	//private Timestamp actualEndTime ;  //实际生产结束时间   从落轴停机开始算起
	private Long actualBeginTime;
	private Long actualEndTime;
	
	//实际物料配送到位时间  叉车配料或人手推。 这个字段以后需要叉车或手持机告诉你。现在可以随意给个值
	//private Timestamp acutalDispatchTime; 
	private Long acutalDispatchTime; 
	
	//收线rfid. 收线只有一个轴
	private String rfid_end;
	
	//本根线实际生产速度——平均
	private float product_speed_avg;
	//人员RFID
	private String empRfid;
	//生产长度
	private Double axixLen;
	//调试时间
	//private Float testTime; 
	//产品规格型号
	private String proGgxh;
	//调试长度 米
	private BigDecimal testLen;
	//调试时间 传来的数据是毫秒，需转成分钟
	private Long testtime;
	//本轴线平均生产速度
	private BigDecimal speed;
	//打卡人员1
	private String emp1rfid;
	//人员1打卡时间
	private Timestamp emp1dtime;
	//打卡人员2
	private String emp2rfid;
	//人员2打卡时间
	private Timestamp emp2dtime; 
	
	//参数回填部分
	private List<ParamVo> lst = new ArrayList<ParamVo>();
	
	
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}



	public String getSeqCode() {
		return seqCode;
	}

	public void setSeqCode(String seqCode) {
		this.seqCode = seqCode;
	}

	public Long getSxTime() {
		return sxTime;
	}

	public void setSxTime(Long sxTime) {
		this.sxTime = sxTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRfids_begin() {
		return rfids_begin;
	}

	public void setRfids_begin(String rfids_begin) {
		this.rfids_begin = rfids_begin;
	}

	public Long getActualBeginTime() {
		return actualBeginTime;
	}

	public void setActualBeginTime(Long actualBeginTime) {
		this.actualBeginTime = actualBeginTime;
	}

	public Long getActualEndTime() {
		return actualEndTime;
	}

	public void setActualEndTime(Long actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	public Long getAcutalDispatchTime() {
		return acutalDispatchTime;
	}

	public void setAcutalDispatchTime(Long acutalDispatchTime) {
		this.acutalDispatchTime = acutalDispatchTime;
	}

	public String getRfid_end() {
		return rfid_end;
	}

	public void setRfid_end(String rfid_end) {
		this.rfid_end = rfid_end;
	}

	public float getProduct_speed_avg() {
		return product_speed_avg;
	}

	public void setProduct_speed_avg(float product_speed_avg) {
		this.product_speed_avg = product_speed_avg;
	}

	public List<ParamVo> getLst() {
		return lst;
	}

	public void setLst(List<ParamVo> lst) {
		this.lst = lst;
	}

	public String getEmpRfid() {
		return empRfid;
	}

	public void setEmpRfid(String empRfid) {
		this.empRfid = empRfid;
	}

	public Double getAxixLen() {
		return axixLen;
	}

	public void setAxixLen(Double axixLen) {
		this.axixLen = axixLen;
	}

/*	public Float getTestTime() {
		return testTime;
	}

	public void setTestTime(Float testTime) {
		this.testTime = testTime;
	}*/

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getMqType() {
		return mqType;
	}

	public void setMqType(String mqType) {
		this.mqType = mqType;
	}

	public BigDecimal getTestLen() {
		return testLen;
	}

	public void setTestLen(BigDecimal testLen) {
		this.testLen = testLen;
	}

	public Long getTesttime() {
		return testtime;
	}

	public void setTesttime(Long testtime) {
		this.testtime = testtime;
	}

	public BigDecimal getSpeed() {
		return speed;
	}

	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}

	public String getEmp1rfid() {
		return emp1rfid;
	}

	public void setEmp1rfid(String emp1rfid) {
		this.emp1rfid = emp1rfid;
	}

	public Timestamp getEmp1dtime() {
		return emp1dtime;
	}

	public void setEmp1dtime(Timestamp emp1dtime) {
		this.emp1dtime = emp1dtime;
	}

	public String getEmp2rfid() {
		return emp2rfid;
	}

	public void setEmp2rfid(String emp2rfid) {
		this.emp2rfid = emp2rfid;
	}

	public Timestamp getEmp2dtime() {
		return emp2dtime;
	}

	public void setEmp2dtime(Timestamp emp2dtime) {
		this.emp2dtime = emp2dtime;
	}
}