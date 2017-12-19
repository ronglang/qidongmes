package com.css.common.web.apachemq.bean;

import java.io.Serializable;
import java.util.List;

import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;

/**
 * @TODO  :  目前用于呼叫叉车. 目前接收的是从机台即将生产完毕发来的呼叫，配往下一工序或半成品区
 *   并需要兼容： 仓库手持机上传出库时，呼叫叉车的功能。两功能数据格式要保持一致。叉车只有一个队列
 *   
 *                     机台读取工单：由机台发给本队列一个指令：commType=取工单,fromMachineCode=机台编码.对应消息队列：QUE_WEB_Command_Receive_p2p
 * @author: 翟春磊
 * @DATE  : 2017年7月21日
 */
public class CommandVO implements Serializable{
	private static final long serialVersionUID = -4797429793062514718L;
	
	private Integer id;
	//保留字段，叉车端(app)用于发消息取消任务
	//机台读取工单发：取工单
	private String commType;
	//工单号
	//机台读取工单时不填
	private String courseCode;
	//仓库呼叫则不填
	//机台读取工单时要填
	private String fromMachineCode;
	//仓库呼叫的填（闻）   要求，根据工单、机台、物料名称、规格型号分组合计出数量与单位: 只包含这6个字段，再加机台名称共7字段
	//从仓库配料可能要配多种料到多个机台。所以用list （胶料的不同颜色可以一起配送）
	private List<PlaMachinePlanMater> materList;
	
	//机台呼叫的填.仓库的不填
	private String toMachineName;
	
	//叉车应答     叉车的编号。 
	//叉车回应时，只填两个字段：id与chaCheCode
	private String chaCheCode; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommType() {
		return commType;
	}

	public void setCommType(String commType) {
		this.commType = commType;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getFromMachineCode() {
		return fromMachineCode;
	}

	public void setFromMachineCode(String fromMachineCode) {
		this.fromMachineCode = fromMachineCode;
	}

	public List<PlaMachinePlanMater> getMaterList() {
		return materList;
	}

	public void setMaterList(List<PlaMachinePlanMater> materList) {
		this.materList = materList;
	}

	public String getToMachineName() {
		return toMachineName;
	}

	public void setToMachineName(String toMachineName) {
		this.toMachineName = toMachineName;
	}

	public String getChaCheCode() {
		return chaCheCode;
	}

	public void setChaCheCode(String chaCheCode) {
		this.chaCheCode = chaCheCode;
	}

}
