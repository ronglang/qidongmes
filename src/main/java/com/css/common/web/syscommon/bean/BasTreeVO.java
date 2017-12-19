package com.css.common.web.syscommon.bean;

public class BasTreeVO {
	private String idNumber;
	private String holder_name;
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getHolder_name() {
		return holder_name;
	}
	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}
	
	public BasTreeVO(String idNumber,String holder_name){
		this.idNumber = idNumber;
		this.holder_name = holder_name;
	}
}
