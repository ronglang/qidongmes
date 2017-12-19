/**
 * @todo: 
 * @author : zhaichunlei
 * @date: 2017年9月19日
 */
package com.css.business.web.subsyssell.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 翟春磊 vo
 */
public class SellContractVO implements Serializable {
	private static final long serialVersionUID = -2075036232154565381L;

	private Integer id;
	private Date create_date;
	private String create_by;
	private String sc_code;
	private String sc_content;
	private String remark;
	private Date sc_date;
	private Double sc_money;
	private String first_party;
	private String second_party;
	private String agent_by;
	private String cus_code;
	private Date delive_date;
	private String sc_state;
	private String cus_name;
	private String order_code;
	private String ws_type;

	private String contract_code;// 生产通知单编号
	private Integer order_detail_id;

	private String total_len;// 生产总长度
	private String complete_len;// 已下发到工单长度

	private String contract_type;// 生产通知单类型；正常单、插单、
	//private List<SellContractPlanBatch> sellContractPlanBatchLst = new ArrayList<SellContractPlanBatch>();

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

	public String getSc_code() {
		return sc_code;
	}

	public void setSc_code(String sc_code) {
		this.sc_code = sc_code;
	}

	public String getSc_content() {
		return sc_content;
	}

	public void setSc_content(String sc_content) {
		this.sc_content = sc_content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSc_date() {
		return sc_date;
	}

	public void setSc_date(Date sc_date) {
		this.sc_date = sc_date;
	}

	public Double getSc_money() {
		return sc_money;
	}

	public void setSc_money(Double sc_money) {
		this.sc_money = sc_money;
	}

	public String getFirst_party() {
		return first_party;
	}

	public void setFirst_party(String first_party) {
		this.first_party = first_party;
	}

	public String getSecond_party() {
		return second_party;
	}

	public void setSecond_party(String second_party) {
		this.second_party = second_party;
	}

	public String getAgent_by() {
		return agent_by;
	}

	public void setAgent_by(String agent_by) {
		this.agent_by = agent_by;
	}

	public String getCus_code() {
		return cus_code;
	}

	public void setCus_code(String cus_code) {
		this.cus_code = cus_code;
	}

	public Date getDelive_date() {
		return delive_date;
	}

	public void setDelive_date(Date delive_date) {
		this.delive_date = delive_date;
	}

	public String getSc_state() {
		return sc_state;
	}

	public void setSc_state(String sc_state) {
		this.sc_state = sc_state;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getContract_code() {
		return contract_code;
	}

	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}

	public String getTotal_len() {
		return total_len;
	}

	public void setTotal_len(String total_len) {
		this.total_len = total_len;
	}

	public String getComplete_len() {
		return complete_len;
	}

	public void setComplete_len(String complete_len) {
		this.complete_len = complete_len;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

/*	public List<SellContractPlanBatch> getSellContractPlanBatchLst() {
		return sellContractPlanBatchLst;
	}

	public void setSellContractPlanBatchLst(
			List<SellContractPlanBatch> sellContractPlanBatchLst) {
		this.sellContractPlanBatchLst = sellContractPlanBatchLst;
	}
*/
	public Integer getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public String getWs_type() {
		return ws_type;
	}

	public void setWs_type(String ws_type) {
		this.ws_type = ws_type;
	}

}
