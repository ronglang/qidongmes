package com.css.business.web.subsysmanu.mauManage.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class MauWorkStationVo implements Serializable {

	private static final long serialVersionUID = -7264106398322348437L;

	private String  maupart;//生产的米数
	private String  completeRate;//完成情况（比例）
	private String  remainTime;//剩余完成时间
	private String  ppgx;//规格型号
	private String  color;//颜色
	private String  amount;//数量
	private String  courseCode;//工单号
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date endTime;//交货日期
	
	/*******************************/
	
	
	private String tensionNum;//放线张力
	private String sinkTem;//水槽温度
	private String oneSectionTem;//一段温度
	private String twoSectionTem;//二段温度
	private String threeSectionTem;//三段温度
	private String fourSectionTem;//四段温度
	private String fiveSectionTem;//五段温度
	private String sixSectionTem;//六段温度
	private String sevenSectionTem;//七段温度
	private String eightSectionTem;//八段温度
	private String wireDiameter;//线径
	private String storageLineNum;//储线张力
	private String oeeValue;//oee值
	private String pressureNum;//挤出压力
	private String requestAxis;//请求轴号
	
	public String getMaupart() {
		return maupart;
	}
	public void setMaupart(String maupart) {
		this.maupart = maupart;
	}
	public String getCompleteRate() {
		return completeRate;
	}
	public void setCompleteRate(String completeRate) {
		this.completeRate = completeRate;
	}
	public String getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}
	public String getPpgx() {
		return ppgx;
	}
	public void setPpgx(String ppgx) {
		this.ppgx = ppgx;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getTensionNum() {
		return tensionNum;
	}
	public void setTensionNum(String tensionNum) {
		this.tensionNum = tensionNum;
	}
	public String getSinkTem() {
		return sinkTem;
	}
	public void setSinkTem(String sinkTem) {
		this.sinkTem = sinkTem;
	}
	public String getOneSectionTem() {
		return oneSectionTem;
	}
	public void setOneSectionTem(String oneSectionTem) {
		this.oneSectionTem = oneSectionTem;
	}
	public String getTwoSectionTem() {
		return twoSectionTem;
	}
	public void setTwoSectionTem(String twoSectionTem) {
		this.twoSectionTem = twoSectionTem;
	}
	public String getThreeSectionTem() {
		return threeSectionTem;
	}
	public void setThreeSectionTem(String threeSectionTem) {
		this.threeSectionTem = threeSectionTem;
	}
	public String getFourSectionTem() {
		return fourSectionTem;
	}
	public void setFourSectionTem(String fourSectionTem) {
		this.fourSectionTem = fourSectionTem;
	}
	public String getFiveSectionTem() {
		return fiveSectionTem;
	}
	public void setFiveSectionTem(String fiveSectionTem) {
		this.fiveSectionTem = fiveSectionTem;
	}
	public String getSixSectionTem() {
		return sixSectionTem;
	}
	public void setSixSectionTem(String sixSectionTem) {
		this.sixSectionTem = sixSectionTem;
	}
	public String getSevenSectionTem() {
		return sevenSectionTem;
	}
	public void setSevenSectionTem(String sevenSectionTem) {
		this.sevenSectionTem = sevenSectionTem;
	}
	public String getEightSectionTem() {
		return eightSectionTem;
	}
	public void setEightSectionTem(String eightSectionTem) {
		this.eightSectionTem = eightSectionTem;
	}
	public String getWireDiameter() {
		return wireDiameter;
	}
	public void setWireDiameter(String wireDiameter) {
		this.wireDiameter = wireDiameter;
	}
	public String getStorageLineNum() {
		return storageLineNum;
	}
	public void setStorageLineNum(String storageLineNum) {
		this.storageLineNum = storageLineNum;
	}
	public String getOeeValue() {
		return oeeValue;
	}
	public void setOeeValue(String oeeValue) {
		this.oeeValue = oeeValue;
	}
	public String getPressureNum() {
		return pressureNum;
	}
	public void setPressureNum(String pressureNum) {
		this.pressureNum = pressureNum;
	}
	public String getRequestAxis() {
		return requestAxis;
	}
	public void setRequestAxis(String requestAxis) {
		this.requestAxis = requestAxis;
	}
	
	
	
}
	
