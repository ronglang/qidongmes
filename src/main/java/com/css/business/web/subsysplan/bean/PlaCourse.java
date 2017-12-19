package com.css.business.web.subsysplan.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "PLA_COURSE")
public class PlaCourse implements BaseEntity {

	@Transient
	private static final long serialVersionUID = -8806231744513355829L;
	@Transient
	public static final String wsType_NORMAL = "正常开单";
	@Transient
	public static final String wsType_BACK = "返工单";
	@Transient
	public static final String wsType_MOVE = "挪单";
	@Transient
	public static final String wsType_BEMOVE = "被挪单补单";
	@Transient
	public static final String wsType_INSERT = "插单";
	/** 是否已完成 : 是 */
	@Transient
	public static final String IS_FINISH_YES = "是";
	/** 是否已完成 : 否 */
	@Transient
	public static final String IS_FINISH_NO = "否";
	/** 是否已完成 : 已审核 */
	@Transient
	public static final String AUDIT_FLAG_YES = "已审核";
	/** 是否已完成 : 未审核 */
	@Transient
	public static final String AUDIT_FLAG_NO = "未审核";
	/** 是否启用 : 是 */
	@Transient
	public static final String USE_FLAG_YES = "是";
	/** 是否启用 : 否 */
	@Transient
	public static final String USE_FLAG_NO = "否";
	/** 计划是否生成 : 是 */
	@Transient
	public static final String PLAN_FLAG_YES = "是";
	/** 计划是否生成 : 否 */
	@Transient
	public static final String PLAN_FLAG_NO = "否";
	/** 分解标志: 是 */
	@Transient
	public static final String DECOMPOSE_FLAG_YES = "是";
	/** 分解标志 : 否 */
	@Transient
	public static final String DECOMPOSE_FLAG_NO = "否";

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_course", sequenceName = "seq_pla_course")
	@GeneratedValue(generator = "seq_pla_course", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createDate;
	@Column(name = "create_by")
	private String createBy;
	@Column(name = "remark")
	private String remark;
	/** 工单类型 */
	@Column(name = "ws_type")
	private String wsType;
	/** 工单编号 */
	@Column(name = "ws_code")
	private String wsCode;
	/** 产品编号 */
	@Column(name = "product_order_code")
	private String productOrderCode;
	/** 合同编号 */
	@Column(name = "sc_code")
	private String scCode;
	/** 生产通知单批次号 */
	@Column(name = "bat_code")
	private String batCode;
	/** 型号规格(还不确定是不是产品的。可能是扎装有关的） */
	@Column(name = "head_ggxh")
	private String headGgxh;
	/** 颜色 */
	@Column(name = "color")
	private String color;
	/** 扎装段长 */
	@Column(name = "head_zzdc")
	private String headZzdc;
	/** 扎装段数 */
	@Column(name = "head_zzds")
	private String headZzds;
	/** total_amount */
	@Column(name = "total_amount")
	private Integer totalAmount;
	@Column(name = "manu_notice_id")
	private Integer manuNoticeId;
	@Column(name = "cus_id")
	private String cusId;
	/** 开单日期 */
	@Column(name = "bill_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date billDate;
	/** 交货时间 */
	@Column(name = "demand_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date demandDate;
	/** 是否完成 */
	@Column(name = "is_finish")
	private String isFinish;
	/** 是否审核 */
	@Column(name = "audit_flag")
	private String auditFlag;
	/** 审核时间 */
	@Column(name = "audit_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date auditTime;
	@Column(name = "course_name")
	private String courseName;
	/** 启用标志，是否启用 (是、否) */
	@Column(name = "use_flag")
	private String useFlag;
	/** 生产原则 ????? */
	@Column(name = "manu_tenet")
	private String manuTenet;
	/** 过期标志，是否已过期 */
	@Column(name = "past_flag")
	private String pastFlag;
	/** 计划启用日期 */
	@Column(name = "plan_enable_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date planEnableDate;
	/** 工艺路线编码 */
	@Column(name = "route_code")
	private String routeCode;
	/** 产品工艺编码(核心字段) */
	@Column(name = "pro_craft_code")
	private String proCraftCode;
	@Column(name = "contract_detail_id")
	private Integer contractDetailId;
	/** 是否已生成计划(是、否) */
	@Column(name = "plan_flag")
	private String planFlag;
	/** 优先方式（产能优先、节能优先、自定义） */
	@Column(name = "priority_way")
	private String priorityWay;
	/** 挪单表code */
	@Column(name = "move_code")
	private String moveCode;
	/** 分解标志（是，否） */
	@Column(name = "decompose_flag")
	private String decomposeFlag;
	/** 产品规格型号 */
	@Column(name = "pro_ggxh")
	private String proGgxh;
	@Column(name = "pro_color")
	private String proColor;
	@Column(name = "order_code")
	private String orderCode;// 订单编号

	@Column(name = "ws_schedule")
	private Float wsSchedule;

	// 机台名称. 下发机台任务用
	@Transient
	private String machineName;
	// 开单日期
	@Transient
	private Long billDate_Long;

	// 工单的机台计划
	@Transient
	private List<PlaMacTask> planLst = new ArrayList<PlaMacTask>();

	// 计划材料
	@Transient
	private List<PlaMacTaskMateril> materList = new ArrayList<PlaMacTaskMateril>();

	// 产品工艺。内包含各工序的工艺参数
	@Transient
	private CraCraftProduct craft = new CraCraftProduct();

	@Transient
	private CraProSeqRelation craProSeqRelation;

	@Column(name = "ws_pstime")
	private Timestamp ws_pstime;
	@Column(name = "ws_pdtime")
	private Timestamp ws_pdtime;
	@Column(name = "ws_fstime")
	private Timestamp ws_fstime;
	@Column(name = "ws_fdtime")
	private Timestamp ws_fdtime;
	/**
	 * 优先级
	 */
	@Column(name = "pri_level")
	private Integer priLevel;

	/**
	 * 当前工序
	 */
	@Transient
	private String nowSeq;

	public String getNowSeq() {
		return nowSeq;
	}

	public void setNowSeq(String nowSeq) {
		this.nowSeq = nowSeq;
	}

	public Timestamp getWs_pstime() {
		return ws_pstime;
	}

	public void setWs_pstime(Timestamp ws_pstime) {
		this.ws_pstime = ws_pstime;
	}

	public Timestamp getWs_pdtime() {
		return ws_pdtime;
	}

	public void setWs_pdtime(Timestamp ws_pdtime) {
		this.ws_pdtime = ws_pdtime;
	}

	public Timestamp getWs_fstime() {
		return ws_fstime;
	}

	public void setWs_fstime(Timestamp ws_fstime) {
		this.ws_fstime = ws_fstime;
	}

	public Timestamp getWs_fdtime() {
		return ws_fdtime;
	}

	public void setWs_fdtime(Timestamp ws_fdtime) {
		this.ws_fdtime = ws_fdtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getManuNoticeId() {
		return manuNoticeId;
	}

	public void setManuNoticeId(Integer manuNoticeId) {
		this.manuNoticeId = manuNoticeId;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getPlanEnableDate() {
		return planEnableDate;
	}

	public void setPlanEnableDate(Date planEnableDate) {
		this.planEnableDate = planEnableDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWsType() {
		return wsType;
	}

	public void setWsType(String wsType) {
		this.wsType = wsType;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getScCode() {
		return scCode;
	}

	public void setScCode(String scCode) {
		this.scCode = scCode;
	}

	public String getBatCode() {
		return batCode;
	}

	public void setBatCode(String batCode) {
		this.batCode = batCode;
	}

	public String getHeadGgxh() {
		return headGgxh;
	}

	public void setHeadGgxh(String headGgxh) {
		this.headGgxh = headGgxh;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getHeadZzdc() {
		return headZzdc;
	}

	public void setHeadZzdc(String headZzdc) {
		this.headZzdc = headZzdc;
	}

	public String getHeadZzds() {
		return headZzds;
	}

	public void setHeadZzds(String headZzds) {
		this.headZzds = headZzds;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public String getManuTenet() {
		return manuTenet;
	}

	public void setManuTenet(String manuTenet) {
		this.manuTenet = manuTenet;
	}

	public String getPastFlag() {
		return pastFlag;
	}

	public void setPastFlag(String pastFlag) {
		this.pastFlag = pastFlag;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public String getProCraftCode() {
		return proCraftCode;
	}

	public void setProCraftCode(String proCraftCode) {
		this.proCraftCode = proCraftCode;
	}

	public List<PlaMacTask> getPlanLst() {
		return planLst;
	}

	public void setPlanLst(List<PlaMacTask> planLst) {
		this.planLst = planLst;
	}

	public List<PlaMacTaskMateril> getMaterList() {
		return materList;
	}

	public void setMaterList(List<PlaMacTaskMateril> materList) {
		this.materList = materList;
	}

	@Transient
	public CraCraftProduct getCraft() {
		return craft;
	}

	public void setCraft(CraCraftProduct craft) {
		this.craft = craft;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getProductOrderCode() {
		return productOrderCode;
	}

	public void setProductOrderCode(String productOrderCode) {
		this.productOrderCode = productOrderCode;
	}

	public Long getBillDate_Long() {
		if (this.billDate == null)
			return null;
		else
			return billDate.getTime();
	}

	public void setBillDate_Long(Long billDate_Long) {
		this.billDate_Long = billDate_Long;
	}

	@Transient
	public CraProSeqRelation getCraProSeqRelation() {
		return craProSeqRelation;
	}

	public void setCraProSeqRelation(CraProSeqRelation craProSeqRelation) {
		this.craProSeqRelation = craProSeqRelation;
	}

	public Integer getContractDetailId() {
		return contractDetailId;
	}

	public void setContractDetailId(Integer contractDetailId) {
		this.contractDetailId = contractDetailId;
	}

	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	public String getPlanFlag() {
		return planFlag;
	}

	public void setPlanFlag(String planFlag) {
		this.planFlag = planFlag;
	}

	public String getPriorityWay() {
		return priorityWay;
	}

	public void setPriorityWay(String priorityWay) {
		this.priorityWay = priorityWay;
	}

	public String getMoveCode() {
		return moveCode;
	}

	public void setMoveCode(String moveCode) {
		this.moveCode = moveCode;
	}

	public String getDecomposeFlag() {
		return decomposeFlag;
	}

	public void setDecomposeFlag(String decomposeFlag) {
		this.decomposeFlag = decomposeFlag;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public Float getWsSchedule() {

		return wsSchedule;
	}

	public void setWsSchedule(Float wsSchedule) {

		this.wsSchedule = wsSchedule;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getPriLevel() {
		return priLevel;
	}

	public void setPriLevel(Integer priLevel) {
		this.priLevel = priLevel;
	}
	
	public PlaCourse(){
		this.wsSchedule=0.0f;
	}

}
