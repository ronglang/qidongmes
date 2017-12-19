package com.css.common.web.apachemq.bean;

import java.util.List;
import java.util.Map;

/**
 * @Title: MqTopicVo.java
 * @Package com.css.business.web.syswebsoket.vo
 * @Description: 消息队列与电子看板通信时发送格式的vo
 * @author rb
 * @date 2017年7月18日 上午10:20:45
 * @company SMTC
 */

public class MqDisplayVo {
	/** 消息的类型 msg,exception,params */
	private String type;
	/**
	 * 消息的目标 :仓库,叉车,工程部,质检,工位,拉丝,绞线,挤护套,绝缘,成缆,分盘,如有其他请告知荣波
	 */
	private String target;
	/** 消息内容 仓库:出库,入库,报废,缺料;除了仓库,可以没有*/
	private String msg;
	/** 机台信息 id  这个必须有 */
	private String crewId;
	/** 异常id */
	private Integer exceptionId;
	/** 实时数据的map */
	private Map<String, String> paMap;

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            要设置的 type
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            要设置的 target
	 * 
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            要设置的 msg
	 * 
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return exceptionId
	 */
	public Integer getExceptionId() {
		return exceptionId;
	}

	/**
	 * @param exceptionId
	 *            要设置的 exceptionId
	 * 
	 */
	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	/**
	 * @return paMap
	 */
	public Map<String, String> getPaMap() {
		return paMap;
	}

	/**
	 * @param paMap
	 *            要设置的 paMap
	 * 
	 */
	public void setPaMap(Map<String, String> paMap) {
		this.paMap = paMap;
	}

	/**
	 * @return  crewId
	 */
	public String getCrewId() {
		return crewId;
	}

	/**
	 *  @param crewId 要设置的 crewId 
	 *    
	 */
	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}

	

}
