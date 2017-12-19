package com.css.business.web.subsysstore.storeManage.vo;

import java.util.List;
import java.util.Map;

public class JudgeMaterVo {

	// 最终结果
	private boolean type;
	// 最后的原因
	private String msg;
	//rfid 扫描的材料信息   规格型号:重量
	private Map<String, Double> materMap;
	//任务所需材料  规格型号 : 用量
	private Map<String, Double> taskMaterMap;
	//中途出现的 小问题原因
	private List<String> msgList;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Double> getMaterMap() {
		return materMap;
	}

	public void setMaterMap(Map<String, Double> materMap) {
		this.materMap = materMap;
	}

	public Map<String, Double> getTaskMaterMap() {
		return taskMaterMap;
	}

	public void setTaskMaterMap(Map<String, Double> taskMaterMap) {
		this.taskMaterMap = taskMaterMap;
	}

	public List<String> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

}
