package com.css.business.web.subsysplan.plaManage.bean;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

/**
 * 生产令工时数据展示对象，数据库纵表转横表
 * @author Administrator
 *
 */
public class ProOrderVo {

	private String plaOrderId;
	
	private String laSi;//拉丝
	
	private String jiaoXian;//绞线
	
	private String jiJueYuan;//挤绝缘
	
	private String fenPan;//分盘
	
	

	public String getFenPan() {
		return fenPan;
	}

	public void setFenPan(String fenPan) {
		this.fenPan = fenPan;
	}

	public String getPlaOrderId() {
		return plaOrderId;
	}

	public void setPlaOrderId(String plaOrderId) {
		this.plaOrderId = plaOrderId;
	}

	public String getLaSi() {
		return laSi;
	}

	public void setLaSi(String laSi) {
		this.laSi = laSi;
	}

	public String getJiaoXian() {
		return jiaoXian;
	}

	public void setJiaoXian(String jiaoXian) {
		this.jiaoXian = jiaoXian;
	}

	public String getJiJueYuan() {
		return jiJueYuan;
	}

	public void setJiJueYuan(String jiJueYuan) {
		this.jiJueYuan = jiJueYuan;
	}
	
}
