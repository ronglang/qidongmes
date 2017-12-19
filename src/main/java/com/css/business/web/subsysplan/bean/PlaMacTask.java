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

import com.css.common.util.JsonDateSerializer;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @ClassName: PlaMacTask
 * @Description: XXX(这里用一句话描述这个类的作用)
 * @author RB
 * @date 2017年11月29日 下午2:46:30
 * 
 */
@Entity
@Table(name = "pla_mac_task")
public class PlaMacTask implements BaseEntity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seqcode == null) ? 0 : seqcode.hashCode());
		result = prime * result + ((step == null) ? 0 : step.hashCode());
		result = prime * result
				+ ((workcode == null) ? 0 : workcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaMacTask other = (PlaMacTask) obj;
		if (seqcode == null) {
			if (other.seqcode != null)
				return false;
		} else if (!seqcode.equals(other.seqcode))
			return false;
		if (step == null) {
			if (other.step != null)
				return false;
		} else if (!step.equals(other.step))
			return false;
		if (workcode == null) {
			if (other.workcode != null)
				return false;
		} else if (!workcode.equals(other.workcode))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 6739394537700498797L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_pla_mac_task", sequenceName = "seq_pla_mac_task")
	@GeneratedValue(generator = "seq_pla_mac_task", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "workcode")
	private String workcode;// 工作单号

	@Column(name = "maccode")
	private String maccode;// 机台号

	@Column(name = "seqcode")
	private String seqcode;// 工序代码

	@Column(name = "axiscount")
	private int axiscount;// 出轴数
	/**
	 * axisParam color 颜色 length 单轴长度 axisCount 轴数 axisCode 轴号
	 */
	@Transient
	private ArrayList<PlaMacTaskAxisParam> axisParam;// 出轴号、颜色、每轴长度

	@Transient
	private List<String> bom;

	@Transient
	private ArrayList<PlaMacTaskMateril> pmtms;// 该机台的用料情况

	public ArrayList<PlaMacTaskMateril> getPmtms() {
		return pmtms;
	}

	public void setPmtms(ArrayList<PlaMacTaskMateril> pmtms) {
		this.pmtms = pmtms;
	}

	@Column(name = "useminute")
	private Integer useminute;// 该工序所用时间

	@Column(name = "delidate")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date delidate;// 交货日期

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "pstime")
	private Timestamp pstime;// 该工序计划开始时间

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "pdtime")
	private Timestamp pdtime;// 该工序计划结束时间

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "fstime")
	private Timestamp fstime;// 该工序时间开始时间

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "fdtime")
	private Timestamp fdtime;// 该工序实际结束时间

	@Column(name = "priority")
	private Integer priority;// 优先级别

	@Column(name = "schedule")
	private Float schedule;// 进度

	@Column(name = "step")
	private Integer step;// 整个生产处于哪一步

	@Column(name = "productstate")
	private String productstate;// 生产状态（计划、已排产、生产中、结束）

	@Column(name = "main_by")
	private String main_by;

	@Column(name = "vice_by")
	private String vice_by;

	@Column(name = "reaxistype")
	private String reaxistype;// 收线轴的类型

	@Column(name = "puttype")
	private String puttype;// 放线轴的类型

	@Column(name = "speed")
	private Float speed;

	@Column(name = "fspeed")
	private Float fspeed;

	public Float getFspeed() {
		return fspeed;
	}

	public void setFspeed(Float fspeed) {
		this.fspeed = fspeed;
	}

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

	public int getAxiscount() {
		return axiscount;
	}

	public void setAxiscount(int axiscount) {
		this.axiscount = axiscount;
	}

	public ArrayList<PlaMacTaskAxisParam> getAxisParam() {
		return axisParam;
	}

	public void setAxisParam(ArrayList<PlaMacTaskAxisParam> axisParam) {
		this.axisParam = axisParam;
	}

	public Integer getUseminute() {
		return useminute;
	}

	public void setUseminute(Integer useminute) {
		this.useminute = useminute;
	}

	public Date getDelidate() {
		return delidate;
	}

	public void setDelidate(Date delidate) {
		this.delidate = delidate;
	}

	public Timestamp getPstime() {
		return pstime;
	}

	public void setPstime(Timestamp pstime) {
		this.pstime = pstime;
	}

	public Timestamp getPdtime() {
		return pdtime;
	}

	public void setPdtime(Timestamp pdtime) {
		this.pdtime = pdtime;
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Float getSchedule() {
		return schedule;
	}

	public void setSchedule(Float schedule) {
		this.schedule = schedule;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getProductstate() {
		return productstate;
	}

	public void setProductstate(String productstate) {
		this.productstate = productstate;
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

	public String getReaxistype() {
		return reaxistype;
	}

	public void setReaxistype(String reaxistype) {
		this.reaxistype = reaxistype;
	}

	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public List<String> getBom() {
		return bom;
	}

	public void setBom(List<String> bom) {
		this.bom = bom;
	}

	public PlaMacTask() {
		axisParam = new ArrayList<PlaMacTaskAxisParam>();// 出轴号、颜色、每轴长度
		pmtms = new ArrayList<PlaMacTaskMateril>();// 该机台的用料情况
		axisParam.clear();
		pmtms.clear();
		speed = 0.0f;
	    fspeed = 0.0f;
	    schedule = 0.0f;
	}

	public String getPuttype() {
		return puttype;
	}

	public void setPuttype(String puttype) {
		this.puttype = puttype;
	}

}
