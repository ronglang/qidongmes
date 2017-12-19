package com.css.business.web.subsysstatistics.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产统计报表
 * 
 * @author candy
 * 
 */
public class ProductStatReportVo {
	// 工单编号
	private String course_code;
	// 机台code
	private String mac_code;
	// 机台名称
	private String mac_name;
	// 轴名称
	private String axis_name;
	// 物料规格
	private String mater_type;
	// 工序名称
	private String seq_name;
	// 计划长度
	private String part_len;
	// 半成品长度
	private BigDecimal semi_product_len;
	// 计划开始时间
	private Date plan_start_time;
	// 实际开始时间
	private Date fact_start_time;
	// 计划结束时间
	private Date plan_end_time;
	// 实际结束时间
	private Date fact_end_time;
	// 计划来料时间
	private Date plan_incoming_time;
	// 实际来料时间
	private Date fact_incoming_time;
	// 工作时间 (分钟)
//	private String work_date;
	// 完成率
	private Double comp_rate;
	// 颜色
	private String color;
	// 产品规格
	private String head_ggxh;
	// 生产状态
	private String product_state;
	// 原料规格型号
	private String mater_ggxh;

	public ProductStatReportVo() {
		super();
	}

	/*public ProductStatReportVo(String course_cose, String mac_name,
			String axis_name, String mater_type, String seq_name,
			String part_len, Double semi_product_len, Date plan_start_time,
			Date fact_start_time, Date plan_end_time, Date fact_end_time,
			Date plan_incoming_time, Date fact_incoming_time, Date work_date,
			Double comp_rate, String color, String head_ggxh) {
		super();
		this.course_code = course_cose;
		this.mac_name = mac_name;
		this.axis_name = axis_name;
		this.mater_type = mater_type;
		this.seq_name = seq_name;
		this.part_len = part_len;
		this.plan_start_time = plan_start_time;
		this.fact_start_time = fact_start_time;
		this.plan_end_time = plan_end_time;
		this.fact_end_time = fact_end_time;
		this.plan_incoming_time = plan_incoming_time;
		this.fact_incoming_time = fact_incoming_time;
		this.comp_rate = comp_rate;
		this.color = color;
		this.head_ggxh = head_ggxh;
	}*/
	
	

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String getMac_name() {
		if (mac_name == null || mac_name == "") {
			return mac_code;
		}
		return mac_name;
	}

	public void setMac_name(String mac_name) {
		this.mac_name = mac_name;
	}

	public String getAxis_name() {
		return axis_name;
	}

	public void setAxis_name(String axis_name) {
		this.axis_name = axis_name;
	}

	public String getMater_type() {
		return mater_type;
	}

	public void setMater_type(String mater_type) {
		this.mater_type = mater_type;
	}

	public String getSeq_name() {
		return seq_name;
	}

	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	public String getPart_len() {
		return part_len;
	}

	public void setPart_len(String part_len) {
		this.part_len = part_len;
	}



	public BigDecimal getSemi_product_len() {
		return semi_product_len;
	}

	public void setSemi_product_len(BigDecimal semi_product_len) {
		this.semi_product_len = semi_product_len;
	}

	public Date getPlan_start_time() {
		return plan_start_time;
	}

	public void setPlan_start_time(Date plan_start_time) {
		this.plan_start_time = plan_start_time;
	}

	public Date getFact_start_time() {
		return fact_start_time;
	}

	public void setFact_start_time(Date fact_start_time) {
		this.fact_start_time = fact_start_time;
	}

	public Date getPlan_end_time() {
		return plan_end_time;
	}

	public void setPlan_end_time(Date plan_end_time) {
		this.plan_end_time = plan_end_time;
	}

	public Date getFact_end_time() {
		return fact_end_time;
	}

	public void setFact_end_time(Date fact_end_time) {
		this.fact_end_time = fact_end_time;
	}

	public Date getPlan_incoming_time() {
		return plan_incoming_time;
	}

	public void setPlan_incoming_time(Date plan_incoming_time) {
		this.plan_incoming_time = plan_incoming_time;
	}

	public Date getFact_incoming_time() {
		return fact_incoming_time;
	}

	public void setFact_incoming_time(Date fact_incoming_time) {
		this.fact_incoming_time = fact_incoming_time;
	}

	/**
	 * 工作时间
	 * @return  work_date
	 */
	public String getWork_date() {
		if (fact_end_time == null) {
			return  "还未结束";
		} else if(fact_end_time != null  && fact_start_time != null ){
			long end = fact_end_time.getTime();
			long start = fact_start_time.getTime();
			return  String.valueOf( (end-start)/60000);
		} else {
			return "还未结束";
		}
	}

	/**
	 *  @param work_date 要设置的 work_date 
	 *    
	 */
	/*public void setWork_date(String work_date) {
		this.work_date = work_date;
	}*/
	

	public Double getComp_rate() {
		if (semi_product_len != null && part_len != null) {
			BigDecimal divide = semi_product_len.divide(new BigDecimal(part_len),4,BigDecimal.ROUND_HALF_UP);
			comp_rate = divide.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue()*100;
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
			comp_rate = Double.parseDouble(df.format(comp_rate));
		}
		return comp_rate;
	}

	public void setComp_rate(Double comp_rate) {
		this.comp_rate = comp_rate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getHead_ggxh() {
		return head_ggxh;
	}

	public void setHead_ggxh(String head_ggxh) {
		this.head_ggxh = head_ggxh;
	}

	/**
	 * @return product_state
	 */
	public String getProduct_state() {
		return product_state;
	}

	/**
	 * @param product_state
	 *            要设置的 product_state
	 * 
	 */
	public void setProduct_state(String product_state) {
		this.product_state = product_state;
	}

	/**
	 * @return mater_ggxh
	 */
	public String getMater_ggxh() {
		return mater_ggxh;
	}

	/**
	 * @param mater_ggxh
	 *            要设置的 mater_ggxh
	 * 
	 */
	public void setMater_ggxh(String mater_ggxh) {
		this.mater_ggxh = mater_ggxh;
	}

	/**
	 * @return mac_code
	 */
	public String getMac_code() {
		return mac_code;
	}

	/**
	 * @param mac_code
	 *            要设置的 mac_code
	 * 
	 */
	public void setMac_code(String mac_code) {

		this.mac_code = mac_code;
	}
	
	
}
