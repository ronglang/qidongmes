package com.css.common.web.apachemq.bean;

import java.util.List;

/**
 * @Title: UploadWebBoard.java
 * @Package com.css.common.web.apachemq.bean
 * @Description: 机台给web端电子看板发送的模型
 * @author rb
 * @date 2017年7月20日 下午3:49:51
 * @company SMTC
 */

public class UploadWebBoard {
	@Override
	public String toString() {
		return "UploadWebBoard [  machineCode=" + machineCode + ",  paramInfoList=" + paramInfoList
				+ ", macSpeed=" + macSpeed + ", totalPower=" + totalPower + "]";
	}

	/** 工序名称 */
	//public String seqName;
	/** 机台id */
	//public Integer machineId;
	/** 机台code */
	public String machineCode;
	/** 机台名称 */
	//public String machineName;
	/** 参数信息 */
	public List<ParamInfo> paramInfoList;
	/** 机台速度 */
	public String macSpeed;
	/** 总有功率 */
	public String totalPower;


	/**
	 * @return paramInfoList
	 */
	public List<ParamInfo> getParamInfoList() {
		return paramInfoList;
	}

	/**
	 * @param paramInfoList
	 *            要设置的 paramInfoList
	 * 
	 */
	public void setParamInfoList(List<ParamInfo> paramInfoList) {
		this.paramInfoList = paramInfoList;
	}

	/**
	 * @return machineCode
	 */
	public String getMachineCode() {
		return machineCode;
	}

	/**
	 * @param machineCode
	 *            要设置的 machineCode
	 * 
	 */
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getMacSpeed() {
		return macSpeed;
	}

	public void setMacSpeed(String macSpeed) {
		this.macSpeed = macSpeed;
	}

	public String getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(String totalPower) {
		this.totalPower = totalPower;
	}

}
