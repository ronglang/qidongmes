/**
 * @todo: 机台生产情况记录表
 * @author : zhaichunlei
 * @date: 2017年12月12日
 */
package com.css.business.web.subsysplan.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Administrator
 */
@Entity
@Table(name = "pla_mac_task_record")
public class PlaMacTaskRecord  implements BaseEntity{
	private static final long serialVersionUID = 7113007687553802522L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_mac_task_record", sequenceName = "seq_pla_mac_task_record")
	@GeneratedValue(generator = "seq_pla_mac_task_record", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "workcode")
	private String workcode;// 工作单号

	@Column(name = "maccode")
	private String maccode;// 机台号

	@Column(name = "seqcode")
	private String seqcode;// 工序代码
	
	@Column(name = "proggxh")
	private String proggxh;//产品规格型号
	
	@Column(name = "axisname")
	private String axisname; //轴名称
	
	@Column(name = "rfidsbegin")
	private String rfidsbegin; //来料RFID
	
	@Column(name = "rfidend")
	private String rfidend; //收线RFID
	
	@Column(name = "step")
	private String step;// 整个生产处于哪一步

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "fstime")
	private Timestamp fstime;// 该工序实际开始时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "fdtime")
	private Timestamp fdtime;// 该工序实际结束时间
	
	@Column(name = "testtime")
	private Integer testtime;// //调试时间（分钟）
	
	@Column(name = "testlen")
	private BigDecimal testlen;// 调试长度
	
	@Column(name = "speed")
	private BigDecimal speed;// 本轴线平均生产速度

	@Column(name = "status")
	private String status;  //预留字段
	
	@Column(name = "emp1rfid")
	private String emp1rfid; //打卡人员1
	
	@Column(name = "emp2rfid")
	private String emp2rfid; //打卡人员2
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "emp1dtime")
	private Timestamp emp1dtime;  //人员1打卡时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "emp2dtime")
	private Timestamp emp2dtime; //人员2打卡时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "createdate")
	private Date createdate; 
	
	@Column(name = "createby")
	private String createby;

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

}
