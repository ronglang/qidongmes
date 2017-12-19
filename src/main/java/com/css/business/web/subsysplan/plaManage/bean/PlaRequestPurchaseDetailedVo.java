package com.css.business.web.subsysplan.plaManage.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PlaRequestPurchaseDetailedVo implements Serializable{
	
	private static final long serialVersionUID = 5534105444783529618L;
	private Integer id;
	private Integer week_plan_id;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date work_date;
	private Integer machine_id;
	private Integer employee_id;
	private String axis_name;
	private String product_craft;
	private String mater_type;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp plan_start_time;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp fact_start_time;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp plan_end_time;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp fact_end_time;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp plan_incoming_time;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp fact_incoming_time;
	private String incoming_axis;
	private String seq_code;
	private String seq_semi_product_axis;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp plan_send_next_seq_date;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Timestamp fact_send_next_seq_date;
	private String up_seq_code;
	private String next_seq_code;
	private String next_seq_machine_code;
	private BigDecimal semi_product_len;
	private Integer ready_time;
	private Integer product_time;
	private BigDecimal product_speed;
	private Integer sort;
	private String create_by;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date create_date;
	private String param_name;
	private String param_value;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWeek_plan_id() {
		return week_plan_id;
	}
	public void setWeek_plan_id(Integer week_plan_id) {
		this.week_plan_id = week_plan_id;
	}
	public Date getWork_date() {
		return work_date;
	}
	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}
	public Integer getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(Integer machine_id) {
		this.machine_id = machine_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getAxis_name() {
		return axis_name;
	}
	public void setAxis_name(String axis_name) {
		this.axis_name = axis_name;
	}
	public String getProduct_craft() {
		return product_craft;
	}
	public void setProduct_craft(String product_craft) {
		this.product_craft = product_craft;
	}
	public String getMater_type() {
		return mater_type;
	}
	public void setMater_type(String mater_type) {
		this.mater_type = mater_type;
	}
	public Timestamp getPlan_start_time() {
		return plan_start_time;
	}
	public void setPlan_start_time(Timestamp plan_start_time) {
		this.plan_start_time = plan_start_time;
	}
	public Timestamp getFact_start_time() {
		return fact_start_time;
	}
	public void setFact_start_time(Timestamp fact_start_time) {
		this.fact_start_time = fact_start_time;
	}
	public Timestamp getPlan_end_time() {
		return plan_end_time;
	}
	public void setPlan_end_time(Timestamp plan_end_time) {
		this.plan_end_time = plan_end_time;
	}
	public Timestamp getFact_end_time() {
		return fact_end_time;
	}
	public void setFact_end_time(Timestamp fact_end_time) {
		this.fact_end_time = fact_end_time;
	}
	public Timestamp getPlan_incoming_time() {
		return plan_incoming_time;
	}
	public void setPlan_incoming_time(Timestamp plan_incoming_time) {
		this.plan_incoming_time = plan_incoming_time;
	}
	public Timestamp getFact_incoming_time() {
		return fact_incoming_time;
	}
	public void setFact_incoming_time(Timestamp fact_incoming_time) {
		this.fact_incoming_time = fact_incoming_time;
	}
	public String getIncoming_axis() {
		return incoming_axis;
	}
	public void setIncoming_axis(String incoming_axis) {
		this.incoming_axis = incoming_axis;
	}
	public String getSeq_code() {
		return seq_code;
	}
	public void setSeq_code(String seq_code) {
		this.seq_code = seq_code;
	}
	public String getSeq_semi_product_axis() {
		return seq_semi_product_axis;
	}
	public void setSeq_semi_product_axis(String seq_semi_product_axis) {
		this.seq_semi_product_axis = seq_semi_product_axis;
	}
	public Timestamp getPlan_send_next_seq_date() {
		return plan_send_next_seq_date;
	}
	public void setPlan_send_next_seq_date(Timestamp plan_send_next_seq_date) {
		this.plan_send_next_seq_date = plan_send_next_seq_date;
	}
	public Timestamp getFact_send_next_seq_date() {
		return fact_send_next_seq_date;
	}
	public void setFact_send_next_seq_date(Timestamp fact_send_next_seq_date) {
		this.fact_send_next_seq_date = fact_send_next_seq_date;
	}
	public String getUp_seq_code() {
		return up_seq_code;
	}
	public void setUp_seq_code(String up_seq_code) {
		this.up_seq_code = up_seq_code;
	}
	public String getNext_seq_code() {
		return next_seq_code;
	}
	public void setNext_seq_code(String next_seq_code) {
		this.next_seq_code = next_seq_code;
	}
	public String getNext_seq_machine_code() {
		return next_seq_machine_code;
	}
	public void setNext_seq_machine_code(String next_seq_machine_code) {
		this.next_seq_machine_code = next_seq_machine_code;
	}
	public BigDecimal getSemi_product_len() {
		return semi_product_len;
	}
	public void setSemi_product_len(BigDecimal semi_product_len) {
		this.semi_product_len = semi_product_len;
	}
	public Integer getReady_time() {
		return ready_time;
	}
	public void setReady_time(Integer ready_time) {
		this.ready_time = ready_time;
	}
	public Integer getProduct_time() {
		return product_time;
	}
	public void setProduct_time(Integer product_time) {
		this.product_time = product_time;
	}
	public BigDecimal getProduct_speed() {
		return product_speed;
	}
	public void setProduct_speed(BigDecimal product_speed) {
		this.product_speed = product_speed;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getParam_name() {
		return param_name;
	}
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	public String getParam_value() {
		return param_value;
	}
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}
	
}
