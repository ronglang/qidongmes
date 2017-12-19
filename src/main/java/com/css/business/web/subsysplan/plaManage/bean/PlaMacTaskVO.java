
package com.css.business.web.subsysplan.plaManage.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.css.common.util.DateUtil;

/**
 * @todo: 用于电子看析。展示每个机台生产的周边信息
 * @author : zhaichunlei
 * @date: 2017年12月13日
 */
public class PlaMacTaskVO implements Serializable{

	private static final long serialVersionUID = 6738186013588876301L;
	private Integer id;
	private String workcode;// 工作单号
	private String workcode_next;// 下一工作单号
	private String maccode;// 机台号
	private String macstate; //机台状态
	private String seqcode;// 工序代码
	private int axiscount;// 出轴数
	private String proggxh;//产品规格型号
	private BigDecimal schedule; //工单进度
	
	private String axisname; //轴名称
	private String rfidsbegin; //来料RFID
	private String rfidend; //收线RFID
	private String step;// 整个生产处于哪一步

	private Timestamp fstime;// 该工序实际结束时间
	private Timestamp fdtime;// 该工序实际结束时间
	private Integer useminute; //完成该工序的所需时间
	private Integer worktime; //工作时间
	private Integer testtime;// //调试时间（分钟）
	private BigDecimal testlen;// 调试长度
	private BigDecimal speed;// 本轴线平均生产速度
	private String status;  //预留字段.     //这里用作设备状态： 正常、故障
	private String emp1rfid; //打卡人员1
	private String emp2rfid; //打卡人员2
	private Timestamp emp1dtime;  //人员1打卡时间
	private Timestamp emp2dtime; //人员2打卡时间
	private Date createdate; 
	private String createby;
	
	private String hjtime; //候机时间。  计算出时间转换为 ？天？分
	//private String kjtime; //开机时间。  计算出时间转换为 ？天？分
	private String testtimestr;  //调试时间串
	private String worktimestr;  //工作时间串
	
	private String lyl; //利用率、 计算出百分比率，转换为string.   23%。  
	
	private String cc; //叉车需求。 叉车号、无
	
	private String fzr; // 负责人
	private Integer pnum; //团队人数
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkcode() {
		return workcode;
	}
	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}
	public String getWorkcode_next() {
		return workcode_next;
	}
	public void setWorkcode_next(String workcode_next) {
		this.workcode_next = workcode_next;
	}
	public String getMaccode() {
		return maccode;
	}
	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}
	public String getSeqcode() {
		return seqcode;
	}
	public void setSeqcode(String seqcode) {
		this.seqcode = seqcode;
	}
	public int getAxiscount() {
		return axiscount;
	}
	public void setAxiscount(int axiscount) {
		this.axiscount = axiscount;
	}
	public String getProggxh() {
		return proggxh;
	}
	public void setProggxh(String proggxh) {
		this.proggxh = proggxh;
	}
	public String getAxisname() {
		return axisname;
	}
	public void setAxisname(String axisname) {
		this.axisname = axisname;
	}
	public String getRfidsbegin() {
		return rfidsbegin;
	}
	public void setRfidsbegin(String rfidsbegin) {
		this.rfidsbegin = rfidsbegin;
	}
	public String getRfidend() {
		return rfidend;
	}
	public void setRfidend(String rfidend) {
		this.rfidend = rfidend;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public Timestamp getFstime() {
		return fstime;
	}
	public void setFstime(Timestamp fstime) {
		this.fstime = fstime;
	}
	public Timestamp getFdtime() {
		return fdtime;
	}
	public void setFdtime(Timestamp fdtime) {
		this.fdtime = fdtime;
	}
	public Integer getTesttime() {
		return testtime;
	}
	public void setTesttime(Integer testtime) {
		this.testtime = testtime;
	}
	public BigDecimal getTestlen() {
		return testlen;
	}
	public void setTestlen(BigDecimal testlen) {
		this.testlen = testlen;
	}
	public BigDecimal getSpeed() {
		return speed;
	}
	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmp1rfid() {
		return emp1rfid;
	}
	public void setEmp1rfid(String emp1rfid) {
		this.emp1rfid = emp1rfid;
	}
	public String getEmp2rfid() {
		return emp2rfid;
	}
	public void setEmp2rfid(String emp2rfid) {
		this.emp2rfid = emp2rfid;
	}
	public Timestamp getEmp1dtime() {
		return emp1dtime;
	}
	public void setEmp1dtime(Timestamp emp1dtime) {
		this.emp1dtime = emp1dtime;
	}
	public Timestamp getEmp2dtime() {
		return emp2dtime;
	}
	public void setEmp2dtime(Timestamp emp2dtime) {
		this.emp2dtime = emp2dtime;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getHjtime() {
		if(hjtime == null )
			return "";
		return DateUtil.getDayString(Integer.parseInt(hjtime));
	}
	public void setHjtime(String hjtime) {
		this.hjtime = hjtime;
	}
	public String getKjtime() {
		if(worktime != null && testtime != null){
			int m = worktime + testtime;
			String dstr = DateUtil.getDayString(m);
			return dstr;
		}
		return "";
	}
	/*public void setKjtime(String kjtime) {
		this.kjtime = kjtime;
	}*/
	public String getLyl() {
		if(lyl == null)
			return "";
		return lyl;
	}
	public void setLyl(String lyl) {
		this.lyl = lyl;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
	public String getMacstate() {
		return macstate;
	}
	public void setMacstate(String macstate) {
		this.macstate = macstate;
	}
	public Integer getWorktime() {
		return worktime;
	}
	public void setWorktime(Integer worktime) {
		this.worktime = worktime;
	}
	public String getTesttimestr() {
		return testtimestr;
	}
	public void setTesttimestr(String testtimestr) {
		this.testtimestr = testtimestr;
	}
	public String getWorktimestr() {
		return worktimestr;
	}
	public void setWorktimestr(String worktimestr) {
		this.worktimestr = worktimestr;
	}
	public Integer getUseminute() {
		return useminute;
	}
	public void setUseminute(Integer useminute) {
		this.useminute = useminute;
	}
	public String getSchedule() {
		if(schedule == null)
			return "0%";
		
		return schedule.doubleValue() + "%";
	}
	public void setSchedule(BigDecimal schedule) {
		this.schedule = schedule;
	}
}
