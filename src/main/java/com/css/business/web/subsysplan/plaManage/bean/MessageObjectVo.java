package com.css.business.web.subsysplan.plaManage.bean;

import java.sql.Timestamp;

/**
 * @Title: PlaMachineDisplayVo.java
 * @Package com.css.business.web.subsysplan.plaManage.bean
 * @Description: 生产电子看板计划进度VO
 * @author rb
 * @date 2017年7月12日 下午5:01:56
 * @company SMTC
 */

public class MessageObjectVo {
	//接收手持机端用
	/**
	 * 接收手机传入的轴id
	 */
	private String axis_id;
	/**
	 * 接收消息时间
	 */
	private String date;
	/**
	 * 消息类型(axis_begin 代表上线   axis_end代表收线)
	 */
	private String msg_type;
	private String machine_id;

	
	
	
	//发送给机台的参数
	//多个以,号隔开
	private String rfids;
	//手持机扫描时间
	private Timestamp newDate;
	//取值：放线、收线
	private String msgType;
	private String macCode;


	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getAxis_id() {
		return axis_id;
	}

	public void setAxis_id(String axis_id) {
		this.axis_id = axis_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}

	public String getRfids() {
		return rfids;
	}

	public void setRfids(String rfids) {
		this.rfids = rfids;
	}

	public Timestamp getNewDate() {
		return newDate;
	}

	public void setNewDate(Timestamp newDate) {
		this.newDate = newDate;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


}
