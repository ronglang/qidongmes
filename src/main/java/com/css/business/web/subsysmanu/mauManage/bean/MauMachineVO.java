package com.css.business.web.subsysmanu.mauManage.bean;

import java.util.Date;

public class MauMachineVO {
	
	private Integer id;
	private Date create_date;
	private String create_by;
	private String mac_code;
	private String mac_name;
	private String mac_state;
	private String mac_type;
	private String ggxh_start;
	private String ggxh_end;
	private String mac_prio;

	private String mac_mark;//代号 如101
	private String is_flag;//0未排产  1已排产
	private String remark;//备注
	private String main_by;
	private String vice_by;
	private String seq_code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public String getMac_code() {
		return mac_code;
	}
	public void setMac_code(String mac_code) {
		this.mac_code = mac_code;
	}
	public String getMac_name() {
		return mac_name;
	}
	public void setMac_name(String mac_name) {
		this.mac_name = mac_name;
	}
	public String getMac_state() {
		return mac_state;
	}
	public void setMac_state(String mac_state) {
		this.mac_state = mac_state;
	}
	public String getMac_type() {
		return mac_type;
	}
	public void setMac_type(String mac_type) {
		this.mac_type = mac_type;
	}
	public String getGgxh_start() {
		return ggxh_start;
	}
	public void setGgxh_start(String ggxh_start) {
		this.ggxh_start = ggxh_start;
	}
	public String getGgxh_end() {
		return ggxh_end;
	}
	public void setGgxh_end(String ggxh_end) {
		this.ggxh_end = ggxh_end;
	}
	public String getMac_prio() {
		return mac_prio;
	}
	public void setMac_prio(String mac_prio) {
		this.mac_prio = mac_prio;
	}
	public String getMac_mark() {
		return mac_mark;
	}
	public void setMac_mark(String mac_mark) {
		this.mac_mark = mac_mark;
	}
	public String getIs_flag() {
		return is_flag;
	}
	public void setIs_flag(String is_flag) {
		this.is_flag = is_flag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMain_by() {
		return main_by;
	}
	public void setMain_by(String main_by) {
		this.main_by = main_by;
	}
	public String getVice_by() {
		return vice_by;
	}
	public void setVice_by(String vice_by) {
		this.vice_by = vice_by;
	}
	public String getSeq_code() {
		return seq_code;
	}
	public void setSeq_code(String seq_code) {
		this.seq_code = seq_code;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
