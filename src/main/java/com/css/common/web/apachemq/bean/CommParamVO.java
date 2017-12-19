package com.css.common.web.apachemq.bean;

import java.io.Serializable;

import com.css.business.web.subsysmanu.bean.MauException;

/**
 * @TODO  : 机台HMI、叉车HMI、工序端，对WEB系统发来的指令要求VO。 
 * @author: 翟春磊
 * @DATE  : 2017年7月13日
 */
public class CommParamVO implements Serializable{

	private static final long serialVersionUID = 8750829038158967945L;

	//机台、叉车编码
	private String macCode ;
	
	/**
	 * 指令
	 * 消除异常警报: comm=clearExecption, execptionId=1
	 *      目的：异常信息在系统有记录，同时记录的有异常的报警状态；消除警报时，需要把警报状态改为已处理、已清除等状态。同
	 *  显示警报：
	 *       comm=showEexception,exceptionId=1    
	 *   警报上报(并且服务器决定是否显示，在哪里显示)
	 *      comm=uploadException,content=[{}]  json串.异常内容以异常类实体    MauException
	 *   
	 *   
	 */
	private String comm;
	
	//目标设备编码
	private String descMachineCode;
	
	//异常内容
	private MauException exceptioin;
	
	
	public String getMacCode() {
		return macCode;
	}
	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public String getDescMachineCode() {
		return descMachineCode;
	}
	public void setDescMachineCode(String descMachineCode) {
		this.descMachineCode = descMachineCode;
	}
	public MauException getExceptioin() {
		return exceptioin;
	}
	public void setExceptioin(MauException exceptioin) {
		this.exceptioin = exceptioin;
	}
}
