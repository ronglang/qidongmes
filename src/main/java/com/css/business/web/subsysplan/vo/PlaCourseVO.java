/**
 * @todo: 
 * @author : zhaichunlei
 * @date: 2017年9月21日
 */
package com.css.business.web.subsysplan.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.css.business.web.subsyscraft.bean.CraRouteSeq;

/**
 * @author Administrator
 *
 */
public class PlaCourseVO implements Serializable{
	private static final long serialVersionUID = -8684786600348931925L;
	private Integer id;
	private Date create_date;
	private String create_by;
	private String ws_type;
	private String ws_code;
	private String product_order_code;
	private String sc_code;
	private String bat_code;
	private String head_ggxh;
	private String color;
	private String head_zzdc;
	private String head_zzds;
	private Integer total_amount;
	private Integer manu_notice_id;
	private Date bill_date;
	private Date demand_date;
	private String is_finish;
	private Date course_name;
	private String use_flag;
	private String past_flag;
	private Date plan_enable_date;
	private String route_code;
	private String pro_craft_code;
	private Integer contract_detail_id;
	private String plan_flag;
	private String priority_way;
	private Integer amount;
	private Integer complete_amount;
	private String demand_part_len;
	private String product_part_len;
	private String pro_name;
	private String p_code;
	private String product_order_state;
	private String priority;
	private String pro_code;
	private String  is_flag;
	private String  decompose_flag;
	private String proGgxh;
	private String pro_ggxh;
	private String pro_color;
	
	private List<CraRouteSeq> craRouteSeqList = new ArrayList<CraRouteSeq>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getWs_type() {
		return ws_type;
	}
	public void setWs_type(String ws_type) {
		this.ws_type = ws_type;
	}
	public String getWs_code() {
		return ws_code;
	}
	public void setWs_code(String ws_code) {
		this.ws_code = ws_code;
	}
	public String getProduct_order_code() {
		return product_order_code;
	}
	public void setProduct_order_code(String product_order_code) {
		this.product_order_code = product_order_code;
	}
	public String getSc_code() {
		return sc_code;
	}
	public void setSc_code(String sc_code) {
		this.sc_code = sc_code;
	}
	public String getBat_code() {
		return bat_code;
	}
	public void setBat_code(String bat_code) {
		this.bat_code = bat_code;
	}
	public String getHead_ggxh() {
		return head_ggxh;
	}
	public void setHead_ggxh(String head_ggxh) {
		this.head_ggxh = head_ggxh;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getHead_zzdc() {
		return head_zzdc;
	}
	public void setHead_zzdc(String head_zzdc) {
		this.head_zzdc = head_zzdc;
	}
	public String getHead_zzds() {
		return head_zzds;
	}
	public void setHead_zzds(String head_zzds) {
		this.head_zzds = head_zzds;
	}
	public Integer getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	public Integer getManu_notice_id() {
		return manu_notice_id;
	}
	public void setManu_notice_id(Integer manu_notice_id) {
		this.manu_notice_id = manu_notice_id;
	}
	public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	public Date getDemand_date() {
		return demand_date;
	}
	public void setDemand_date(Date demand_date) {
		this.demand_date = demand_date;
	}
	public String getIs_finish() {
		return is_finish;
	}
	public void setIs_finish(String is_finish) {
		this.is_finish = is_finish;
	}
	public Date getCourse_name() {
		return course_name;
	}
	public void setCourse_name(Date course_name) {
		this.course_name = course_name;
	}
	public String getUse_flag() {
		return use_flag;
	}
	public void setUse_flag(String use_flag) {
		this.use_flag = use_flag;
	}
	public String getPast_flag() {
		return past_flag;
	}
	public void setPast_flag(String past_flag) {
		this.past_flag = past_flag;
	}
	public Date getPlan_enable_date() {
		return plan_enable_date;
	}
	public void setPlan_enable_date(Date plan_enable_date) {
		this.plan_enable_date = plan_enable_date;
	}
	public String getRoute_code() {
		return route_code;
	}
	public void setRoute_code(String route_code) {
		this.route_code = route_code;
	}
	public Integer getContract_detail_id() {
		return contract_detail_id;
	}
	public void setContract_detail_id(Integer contract_detail_id) {
		this.contract_detail_id = contract_detail_id;
	}
	public String getPlan_flag() {
		return plan_flag;
	}
	public void setPlan_flag(String plan_flag) {
		this.plan_flag = plan_flag;
	}
	public String getPriority_way() {
		return priority_way;
	}
	public void setPriority_way(String priority_way) {
		this.priority_way = priority_way;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getComplete_amount() {
		return complete_amount;
	}
	public void setComplete_amount(Integer complete_amount) {
		this.complete_amount = complete_amount;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_craft_code() {
		return pro_craft_code;
	}
	public void setPro_craft_code(String pro_craft_code) {
		this.pro_craft_code = pro_craft_code;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getProduct_order_state() {
		return product_order_state;
	}
	public void setProduct_order_state(String product_order_state) {
		this.product_order_state = product_order_state;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIs_flag() {
		return is_flag;
	}
	public void setIs_flag(String is_flag) {
		this.is_flag = is_flag;
	}
	public String getPro_color() {
		return pro_color;
	}
	public void setPro_color(String pro_color) {
		this.pro_color = pro_color;
	}
	public String getPro_code() {
		return pro_code;
	}
	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}
	public String getDemand_part_len() {
		return demand_part_len;
	}
	public void setDemand_part_len(String demand_part_len) {
		this.demand_part_len = demand_part_len;
	}
	public String getProduct_part_len() {
		return product_part_len;
	}
	public void setProduct_part_len(String product_part_len) {
		this.product_part_len = product_part_len;
	}
	public String getDecompose_flag() {
		return decompose_flag;
	}
	public void setDecompose_flag(String decompose_flag) {
		this.decompose_flag = decompose_flag;
	}
	public List<CraRouteSeq> getCraRouteSeqList() {
		return craRouteSeqList;
	}
	public void setCraRouteSeqList(List<CraRouteSeq> craRouteSeqList) {
		this.craRouteSeqList = craRouteSeqList;
	}
	public String getProGgxh() {
		return proGgxh;
	}
	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}
	public String getPro_ggxh() {
		return pro_ggxh;
	}
	public void setPro_ggxh(String pro_ggxh) {
		this.pro_ggxh = pro_ggxh;
	}
}
