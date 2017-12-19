package com.css.business.web.subsyssell.sellManage;

import java.io.Serializable;
import java.util.Date;

public class SellContractDetailVO implements Serializable{
	private static final long serialVersionUID = -2814658755036732213L;

	private Integer id;
	
	private String bat_code;
	private String pro_name;
	
	private String sc_code;
	
	private String createBy;
	
	private Date createDate;
	
	private Date delive_date;
	
	private String req_unit;
	
	private String pbat_detail_code;
	
	private String pbat_detail_state;
	
	private String req_period_length;

	private String pro_period_length;  //detail段长   
	private String pro_period_length_bat; //批次段长
	private Integer req_amount;
	
	private String pro_ggxh;
	
	private String pro_color;
	
	private Integer mau_product_id;
	
	private Integer plan_batch_id;
	
	private String pro_period_length_gd; //已下发到工单的
	private String course_code;
	
	private Integer total_len;
	private Integer complete_len;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBat_code() {
		return bat_code;
	}

	public void setBat_code(String bat_code) {
		this.bat_code = bat_code;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getSc_code() {
		return sc_code;
	}

	public void setSc_code(String sc_code) {
		this.sc_code = sc_code;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDelive_date() {
		return delive_date;
	}

	public void setDelive_date(Date delive_date) {
		this.delive_date = delive_date;
	}

	public String getReq_unit() {
		return req_unit;
	}

	public void setReq_unit(String req_unit) {
		this.req_unit = req_unit;
	}

	public String getPbat_detail_code() {
		return pbat_detail_code;
	}

	public void setPbat_detail_code(String pbat_detail_code) {
		this.pbat_detail_code = pbat_detail_code;
	}

	public String getPbat_detail_state() {
		return pbat_detail_state;
	}

	public void setPbat_detail_state(String pbat_detail_state) {
		this.pbat_detail_state = pbat_detail_state;
	}

	public String getReq_period_length() {
		return req_period_length;
	}

	public void setReq_period_length(String req_period_length) {
		this.req_period_length = req_period_length;
	}

	public String getPro_period_length() {
		return pro_period_length;
	}

	public void setPro_period_length(String pro_period_length) {
		this.pro_period_length = pro_period_length;
	}

	public String getPro_period_length_bat() {
		return pro_period_length_bat;
	}

	public void setPro_period_length_bat(String pro_period_length_bat) {
		this.pro_period_length_bat = pro_period_length_bat;
	}

	public Integer getReq_amount() {
		return req_amount;
	}

	public void setReq_amount(Integer req_amount) {
		this.req_amount = req_amount;
	}

	public String getPro_ggxh() {
		return pro_ggxh;
	}

	public void setPro_ggxh(String pro_ggxh) {
		this.pro_ggxh = pro_ggxh;
	}

	public String getPro_color() {
		return pro_color;
	}

	public void setPro_color(String pro_color) {
		this.pro_color = pro_color;
	}

	public Integer getMau_product_id() {
		return mau_product_id;
	}

	public void setMau_product_id(Integer mau_product_id) {
		this.mau_product_id = mau_product_id;
	}

	public Integer getPlan_batch_id() {
		return plan_batch_id;
	}

	public void setPlan_batch_id(Integer plan_batch_id) {
		this.plan_batch_id = plan_batch_id;
	}

	public String getPro_period_length_gd() {
		return pro_period_length_gd;
	}

	public void setPro_period_length_gd(String pro_period_length_gd) {
		this.pro_period_length_gd = pro_period_length_gd;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public Integer getTotal_len() {
		return total_len;
	}

	public void setTotal_len(Integer total_len) {
		this.total_len = total_len;
	}

	public Integer getComplete_len() {
		return complete_len;
	}

	public void setComplete_len(Integer complete_len) {
		this.complete_len = complete_len;
	}
}
