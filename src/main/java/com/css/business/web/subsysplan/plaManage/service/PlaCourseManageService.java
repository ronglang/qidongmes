package com.css.business.web.subsysplan.plaManage.service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.craManage.dao.CraCraftProductManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraProSeqRelationManageDao;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteSeqManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqParamManageDAO;
import com.css.business.web.subsyscraft.craManage.service.CraWireDiscManageService;
import com.css.business.web.subsysmanu.bean.MauAxis;
import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.bean.MauMachineEmployee;
import com.css.business.web.subsysmanu.mauManage.dao.MauAxisMacManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauAxisManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauCallForkliftRecordManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineSpeedManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMaterialDetailManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMaterialRecordManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauTaskLoadDAO;
import com.css.business.web.subsysmanu.vo.SendForkliftVo;
import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaCourseAxis;
import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.bean.PlaMacTaskAxisParam;
import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.business.web.subsysplan.bean.PlaMacTaskRecord;
import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.subsysplan.plaManage.bean.PlaContractVo;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskAxisParamDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskMaterilDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskRecordManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanMaterManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanScheduleManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderAxisManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderSeqHourseManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaTakeupManageDao;
import com.css.business.web.subsysplan.plaManage.dao.PlaWeekPlanManageDAO;
import com.css.business.web.subsysplan.vo.NeedPartPlaCourseVo;
import com.css.business.web.subsysplan.vo.PlaDisPatchVo;
import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysquality.quaManage.dao.QualityMauReportManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.syswebsoket.bean.EchartSeriesVo;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.CaluLength;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.ExportExcel;
import com.css.common.util.PropertyFileUtil;
import com.css.common.util.TimeUtil;
import com.css.common.web.apachemq.bean.MqDisplayVo;
import com.css.common.web.apachemq.bean.MqJTSaveDataVO;
import com.css.common.web.apachemq.bean.ParamVo;
import com.css.common.web.apachemq.handle.ApacheMqSender;
import com.css.common.web.apachemq.handle.test.MqttBroker;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("plaCourseManageService")
public class PlaCourseManageService extends
		BaseEntityManageImpl<PlaCourse, PlaCourseManageDAO> {

	private Gson gson = new Gson();
	private EhCacheFactory ecFactory = EhCacheFactory.getInstance();

	@Resource(name = "plaCourseManageDAO")
	// @Autowired
	private PlaCourseManageDAO dao;

	@Resource(name = "plaMachinePlanManageDAO")
	private PlaMachinePlanManageDAO plaMachinePlanManageDAO;

	@Resource(name = "plaMacTaskAxisParamDAO")
	private PlaMacTaskAxisParamDAO plaMacTaskAxisParamDAO;

	@Resource(name = "plaMacTaskMaterilDAO")
	private PlaMacTaskMaterilDAO plaMacTaskMaterilDAO;

	@Resource(name = "mauMachineManageDAO")
	private MauMachineManageDAO mauMachineManageDAO;

	@Resource(name = "plaMachinePlanScheduleManageDAO")
	private PlaMachinePlanScheduleManageDAO plaMachinePlanScheduleManageDAO;// 进度
	@Resource(name = "plaProductOrderManageDAO")
	private PlaProductOrderManageDAO plaProductOrderManageDAO; // 生产令
	@Resource(name = "plaWeekPlanManageDAO")
	private PlaWeekPlanManageDAO plaWeekPlanManageDAO;

	@Resource(name = "craWireDiscManageService")
	private CraWireDiscManageService craWireDiscManageService;
	@Resource(name = "plaMachinePlanMaterManageDAO")
	private PlaMachinePlanMaterManageDAO plaMachinePlanMaterManageDAO;
	@Resource(name = "craCraftProductManageDAO")
	private CraCraftProductManageDAO craCraftProductManageDAO;
	@Resource(name = "craSeqParamManageDAO")
	private CraSeqParamManageDAO craSeqParamManageDAO;
	@Resource(name = "craRouteSeqManageDAO")
	private CraRouteSeqManageDAO craRouteSeqManageDAO;
	@Resource(name = "mauMaterialRecordManageDAO")
	private MauMaterialRecordManageDAO mauMaterialRecordManageDAO;// 各种工单 这里领料单
	@Resource(name = "mauMaterialDetailManageDAO")
	private MauMaterialDetailManageDAO mauMaterialDetailManageDAO;// 这里是领料单明细
	@Resource(name = "craRouteManageDAO")
	private CraRouteManageDAO craRouteManageDAO;
	@Resource(name = "craProSeqRelationManageDao")
	private CraProSeqRelationManageDao craProSeqRelationManageDao;
	@Resource(name = "craSeqManageDAO")
	// private CraSeqManageService seqService;
	private CraSeqManageDAO craSeqManageDAO;

	@Resource(name = "plaProductOrderSeqHourseManageDAO")
	private PlaProductOrderSeqHourseManageDAO plaProductOrderSeqHourseManageDAO;
	@Resource(name = "mauCallForkliftRecordManageDAO")
	private MauCallForkliftRecordManageDAO mauCallForkliftRecordManageDAO;
	@Resource(name = "qualityMauReportManageDAO")
	private QualityMauReportManageDAO qualityMauReportManageDAO;

	@Resource(name = "plaProductOrderAxisManageDAO")
	private PlaProductOrderAxisManageDAO plaProductOrderAxisManageDAO;
	/*
	 * @Resource(name = "sellContractDetailManageDAO") private
	 * SellContractDetailManageDAO sellContractDetailManageDAO;
	 */
	@Resource(name = "mauMachineSpeedManageDAO")
	private MauMachineSpeedManageDAO mauMachineSpeedManageDAO;
	@Resource(name = "mauTaskLoadDAO")
	private MauTaskLoadDAO mauTaskLoadDAO;
	@Resource(name = "mauAxisMacManageDAO")
	private MauAxisMacManageDAO mauAxisMacManageDAO;
	@Resource(name = "plaTakeupManageDao")
	private PlaTakeupManageDao plaDao;
	@Resource(name = "mauAxisManageDAO")
	private MauAxisManageDAO mauAxisDao;
	@Resource(name = "mauCallForkliftRecordManageDAO")
	private MauCallForkliftRecordManageDAO mauCallDao;

	@Resource(name = "plaMacTaskRecordManageDAO")
	private PlaMacTaskRecordManageDAO plaMacTaskRecordDAO;

	@Override
	public PlaCourseManageDAO getEntityDaoInf() {
		return dao;
	}

	private PropertyFileUtil pfu = new PropertyFileUtil();

	/**
	 * 抓取合同（假数据）
	 * 
	 * @param vo
	 * @return
	 */
	public List<PlaContractVo> getContractVo() {
		PlaContractVo vo = null;
		List<PlaContractVo> list = new ArrayList<PlaContractVo>();
		for (int i = 0; i < 2; i++) {
			vo = new PlaContractVo();
			Date date = new Date();
			vo.setId(i + 1);
			vo.setCreateDate(date);
			vo.setCreeateBy("JS");
			vo.setAgentBy("gg");
			vo.setCrId(i + 1);
			vo.setCrsID(i + 1);
			vo.setCusCode(i + "AIFD");
			vo.setDeliveDate(date);
			vo.setFirstParty("甲方");
			vo.setSecondParty("乙方");
			vo.setLength(500.1 * (i + 1));
			vo.setMetalType("Cu");
			vo.setProGgxh("ASD" + i);
			vo.setRemark("测试使用Vo");
			vo.setRubberType("JL");
			vo.setScCode("20170702-A" + i);
			vo.setScContent("按时交货");
			vo.setScDate(date);
			vo.setScMoney(8000000.0);
			list.add(vo);
		}
		return list;
	}

	/**
	 * @TODO: 队列监听端调用。 接收到机台消息后，调用本方法，维护生产进度相关一系列表 机台生产完毕后， 打包提交的数据。
	 * @author: zhaichunlei & @DATE : 2017年7月20日
	 * @param vo
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void autoUpdateCourse(MqJTSaveDataVO vo) throws Exception {
		String workCode = vo.getCourseCode();// 工单
		int step = vo.getStep();// 步骤
		String macCode = vo.getMacCode(); // 机台编码
		String axisCode = vo.getAxisName(); // 轴号
		String seqCode = vo.getSeqCode(); // 工序
		String status = vo.getStatus(); // 状态 预留字段
		String rfids_begin = vo.getRfids_begin(); // 来料rfid
		String rfid_end = vo.getRfid_end(); // 收线rfid
		String mqType = vo.getMqType();

		Long testtime = vo.getTesttime();// //调试时间（传来的是毫秒）
		BigDecimal testlen = vo.getTestLen();// 调试长度
		BigDecimal speed = vo.getSpeed();// 本轴线平均生产速度
		String emp1rfid = vo.getEmp1rfid(); // 打卡人员1
		String emp2rfid = vo.getEmp2rfid(); // 打卡人员2
		Timestamp emp1dtime = vo.getEmp1dtime(); // 人员1打卡时间
		Timestamp emp2dtime = vo.getEmp2dtime(); // 人员2打卡时间

		if (mqType.equalsIgnoreCase("yes")) {

			Timestamp actualBeginTime = vo.getActualBeginTime() == null ? null
					: new Timestamp(vo.getActualBeginTime()); // 实际开工时间
			Timestamp actualEndTime = vo.getActualEndTime() == null ? null
					: new Timestamp(vo.getActualEndTime());
			; // 实际结束时间
			Timestamp acutalDispatchTime = vo.getAcutalDispatchTime() == null ? null
					: new Timestamp(vo.getAcutalDispatchTime());

			if (macCode == null || seqCode == null || workCode == null
					|| actualBeginTime == null || actualEndTime == null) {
				throw new Exception("机台提交核心数据错误：参数 机台编码、工序编码、收线时间、工作单号。数据抛弃。");
			} else {
				// 保存本轴线在本机台的生产信息--》到记录表
				PlaMacTaskRecord ptr = new PlaMacTaskRecord();
				ptr.setWorkcode(workCode);
				ptr.setMaccode(macCode);
				ptr.setSeqcode(seqCode);
				ptr.setAxisname(axisCode);
				ptr.setRfidsbegin(rfids_begin);
				ptr.setRfidend(rfid_end);
				ptr.setStep(step + "");
				ptr.setFstime(actualBeginTime);
				ptr.setFdtime(actualEndTime);
				ptr.setTesttime(testtime == null ? null
						: (int) (testtime / (1000 * 60)));
				ptr.setTestlen(testlen);
				ptr.setSpeed(speed);
				ptr.setStatus(status);
				ptr.setEmp1rfid(emp1rfid);
				ptr.setEmp2rfid(emp2rfid);
				ptr.setEmp1dtime(emp1dtime);
				ptr.setEmp2dtime(emp2dtime);
				ptr.setCreateby("auto");
				ptr.setCreatedate(new Date(System.currentTimeMillis()));
				// 保存放后面。

				float product_speed_avg = vo.getProduct_speed_avg(); // 实际生产速度

				List<ParamVo> lst = vo.getLst(); // 需要回写的参数

				PlaMacTask pmp = plaMachinePlanManageDAO.queryMacTask(workCode,
						macCode, step);

				if (pmp == null)
					throw new Exception("机台（" + macCode + "）未生产轴（" + axisCode
							+ "）。数据被抛弃。 请核查");

				// 更新实际speed
				if (product_speed_avg > 0) {
					pmp.setFspeed(product_speed_avg);
				}

				// 更新机台计划
				/**
				 * 1)如果axisCount = 1 status="结束" schedule=100 修改fstime and
				 * fdtime 2)如果axisCount > 1 scheldule = scheldule+100/axisCount
				 * if schelduel < 100 status="生产中" 修改 fstime else status="结束" 修改
				 * fdtime
				 */
				if (pmp.getAxiscount() == 1) {
					pmp.setProductstate("结束");
					pmp.setSchedule((float) 100);
					pmp.setFstime(actualBeginTime);
					pmp.setFdtime(actualEndTime);
					pmp.setPdtime(actualEndTime);
				} else {
					float sch = pmp.getSchedule();

					if (sch == 0) {
						pmp.setFstime(actualBeginTime);
					}
					sch = sch + 100 / pmp.getAxiscount();
					if (sch >= 99) {
						pmp.setProductstate("结束");
						pmp.setSchedule((float) 100);
						pmp.setFdtime(actualEndTime);
						pmp.setPdtime(actualEndTime);
					} else {
						pmp.setProductstate("生产中");
						pmp.setSchedule(sch);
					}

				}

				// String reaxis = pmp.getReaxistype(); //收线轴型号。
				// 呼叫叉车操作 ---start
				Integer externalDiameter = mauAxisDao
						.getExternalDiameterByAxisName(pmp.getReaxistype());
				PlaMacTask pmt = plaMachinePlanManageDAO.queryMacTask(workCode,
						macCode, step); // 查找数据库进度与当前进度对比，当前进度大于数据库进度，则表示需要换盘
				List<MauCallForkliftRecord> call = new ArrayList<MauCallForkliftRecord>();
				if (pmp.getSchedule() >= pmt.getSchedule()
						&& externalDiameter != 0) {
					// 形成叉车呼叫记录
					MauCallForkliftRecord callfork = new MauCallForkliftRecord();
					SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
					callfork.setCallTime(sf.format(new Date()));
					// callfork.setUnit(unit);
					callfork.setStatus(MauCallForkliftRecord.NO_status);
					callfork.setCallAddress("车间:" + pmp.getMaccode()
							+ "--收线盘--需要换盘");
					dao.save(callfork);
					call.add(callfork);
					mauCallDao.sendMessageTo(call);
				} else if ("fr".equals(seqCode) && pmp.getSchedule() == 100) {
					// 形成叉车呼叫记录
					MauCallForkliftRecord callfork = new MauCallForkliftRecord();
					SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
					callfork.setCallTime(sf.format(new Date()));
					// callfork.setUnit(unit);
					callfork.setStatus(MauCallForkliftRecord.NO_status);
					callfork.setCallAddress("车间:" + pmp.getMaccode()
							+ "--工单完成,成品需移至成品待检区!");
					dao.save(callfork);
					call.add(callfork);
					mauCallDao.sendMessageTo(call);
				}
				// 呼叫叉车操作 ---end
				plaMachinePlanManageDAO.updateByCon(pmp, false);

				// 计算工作单的进度
				// 根据工作单号获取List<plaMacTask>
				List<PlaMacTask> pmts = plaMachinePlanManageDAO
						.queryMachinePlanByWorkCode(pmp.getWorkcode());
				float workSch = 0;
				Timestamp gdfstime = null;
				Timestamp gdfdtime = null;
				for (int i = 0; i < pmts.size(); i++) {
					if (i == 0) {
						// 第一步工序的实际开始时间设置为工作单的实际开始时间
						gdfstime = pmts.get(i).getFstime();
					}
					if (i == pmts.size() - 1) {
						// 最后一步工序的结束时间设置为工作单的实际结束时间。
						gdfdtime = pmts.get(i).getFdtime();
					}
					workSch = workSch + pmts.get(i).getSchedule() / pmts.size();
				}
				PlaCourse pc = dao.getByCourseCode(pmp.getWorkcode());
				pc.setWs_fstime(gdfstime);
				pc.setWs_fdtime(gdfdtime);
				pc.setWsSchedule(workSch);
				dao.updateByCon(pc, false);

				ptr.setProggxh(pc.getProGgxh());

				// ***************成品质检质检判断***********************
				// 1.判断当前的步骤是否是该工单的最后一步 step
				// 查询该工单最大 step
				Integer PlaMacTask = dao.getMaxStepByWorkCode(workCode);
				if (PlaMacTask != step) {
					// 不做任何操作
				} else {
					// 2.如果是最后一步就是成品,自动生成质检报告
					QualityMauReport mau = new QualityMauReport();
					mau.setCourseCode(workCode);
					mau.setAxisName(axisCode);
					// mau.setBatchCode(batchCode);
					mau.setCreateBy(seqCode);
					mau.setCreateDate(new Date());
					mau.setIsCome(QualityMauReport.IS_NOT_COME);
					mau.setMacCode(macCode);
					mau.setProGgxh(dao.getProGGXH(workCode));
					mau.setReportCode((String) dao
							.exeFunction("fun_get_mau_report_code"));
					mau.setSeqName(seqCode);
					mau.setTestState(QualityMauReport.STATE_NO_TEST);
					mau.setTestType(QualityMauReport.TYPE_IS_PRODUCT);
					mau.setSemiAxis(rfid_end);
					// mau.setWeight(weight);
					qualityMauReportManageDAO.save(mau);
				}

				if (lst != null) {
					// 回写参数'
					for (ParamVo v : lst) {

					}
				}

				/**********************************************************************/
				/**
				 * 关于MauMachineEmployee， 这里暂记录以后的思路改变 1.
				 * 建立机台、人员操作能力参数表（人员在机台操作的主副手） 2. 这里根据机台、人员（1，2）查询主副操手。
				 * 把emp1定义为主操手。如果emp2存在，则是副操手 3.
				 * 数据存入PlaMacTaskRecord_生产记录，但不保存MauMachineEmployee_参数表 4.
				 * 使用数据库定时任务，把生产记录汇总，写入参数表（或保存完生产记录直接更新参数表。）
				 */

				// TODO 将机台与人员信息写入。
				if (vo.getEmpRfid() != null || !"".equals(vo.getEmpRfid())) {
					MauMachineEmployee employee = new MauMachineEmployee();
					employee.setCreateDate(new Date());
					employee.setEmpRfid(vo.getEmpRfid());
					employee.setMacCode(vo.getMacCode());
					PlaCourse course = dao.getPlaCourseByWsCode(vo
							.getCourseCode());
					employee.setProGgxh(course.getProGgxh());
					// employee.setTestTime(vo.getTestTime());
					employee.setTestTime(vo.getTesttime() == null ? null
							: new Float(vo.getTesttime() / (60 * 1000)));
					float diameter = plaMachinePlanManageDAO.getDiameterVaule2(
							course.getProGgxh(), step, seqCode);
					employee.setDiameter((float) (Math.round(diameter * 100) / 100));
					// employee.setTestLen((float)(Math.round(vo.getTestLen()*100)/100));
					employee.setTestLen(vo.getTestLen() == null ? null : vo
							.getTestLen().floatValue());
					float aveSpeed = 0.0f;
					if (vo.getActualEndTime() > vo.getActualBeginTime()) {
						aveSpeed = (float) (vo.getAxixLen() / ((int) (vo
								.getActualEndTime() - vo.getActualBeginTime()) / 60000));
					}
					employee.setAveSpeed(aveSpeed);
					dao.save(employee);
				}

				// 保存生产记录. 同时发送数据到缓存，websocket通知车间看板更新进度
				savePla_mac_task_record(ptr);
			}
		} else {
			Double curLen = vo.getAxixLen();
			Timestamp actualBeginTime = vo.getActualBeginTime() == null ? null
					: new Timestamp(vo.getActualBeginTime()); // 实际开工时间
			Timestamp actualEndTime = vo.getActualEndTime() == null ? null
					: new Timestamp(vo.getActualEndTime());
			if (macCode == null || seqCode == null || workCode == null
					|| curLen == null) {
				throw new Exception("机台提交核心数据错误：参数 机台编码、工序编码、收线时间、工作单号。数据抛弃。");
			} else {
				PlaMacTask pmp = plaMachinePlanManageDAO.queryMacTask(workCode,
						macCode, step);

				if (pmp == null)
					throw new Exception("机台（" + macCode + "）未生产轴（" + axisCode
							+ "）。数据被抛弃。 请核查");

				if (pmp.getAxiscount() == 1) {
					float sch = (float) (curLen / pmp.getAxisParam().get(0)
							.getLength());
					if (sch < 0.99) {
						pmp.setProductstate("生产中");
						pmp.setSchedule(sch);
					} else {
						pmp.setProductstate("结束");
						pmp.setSchedule(100f);
					}
					pmp.setSchedule(sch);
					pmp.setFstime(actualBeginTime);

				}
			}

		}

	}

	// 保存生产记录。 同时发送数据到缓存，websocket通知车间看板更新进度
	private void savePla_mac_task_record(PlaMacTaskRecord ptr) {
		plaMacTaskRecordDAO.save(ptr);

		// 车间看板的每轴线生产完毕需要时间，websocket设置时间是1分钟左右更新一次。 其中若多条线生产完毕，则需要缓存起来. list
		String jsstr = ecFactory.getMacShowCaChe(YorkUtil.Memcache_车间动态);
		List<PlaMacTaskRecord> lst = gson.fromJson(jsstr,
				new TypeToken<List<PlaMacTaskRecord>>() {
				}.getType());
		if (lst == null)
			lst = new ArrayList<PlaMacTaskRecord>();
		lst.add(ptr);

		String jm = gson.toJson(lst);
		ecFactory.addMacShowCache(YorkUtil.Memcache_车间动态, jm);
	}

	/**
	 * 发送消息到叉车消息队列
	 * 
	 * @data:2017年8月10日
	 * @param vo
	 * @autor:wl
	 */
	private void sendEixtStoreToforklift(MqJTSaveDataVO vo) {
		MqttBroker sender;
		sender = MqttBroker.getInstance();
		String prop = pfu.getProp("QEU_TOPIC_PERSIST_CHACHE");
		String message = gson.toJson(vo);
		sender.sendMessage(prop, message);
	}

	/**
	 * @TODO: 可以发送到指定位置看板; 车间看板、车间大看板、生产部、工程部、质检部等
	 * @author: zhaichunlei & @DATE : 2017年7月20日
	 * @param kanbanName
	 * @throws JMSException
	 */
	private void sendGXKanBan(String kanbanName) throws JMSException {
		ApacheMqSender sender = ApacheMqSender.getInstance();
		PropertyFileUtil pfu = new PropertyFileUtil();
		String queue = pfu.getProp("QUE_P2P_KANBAN");

		// 封装看板消息队列需要的消息格式
		MqDisplayVo mdv = new MqDisplayVo();
		mdv.setType("msg");
		mdv.setTarget(kanbanName);

		JSONObject json = JSONObject.fromObject(mdv);
		String msg = json.toString();
		sender.sendMsg_point_to_point(queue, msg);
	}

	/**
	 * @TODO 保存工作单
	 * @author JS
	 * @param plaCourse
	 */
	public void savePlaCourse(PlaCourse plaCourse) {
		dao.save(plaCourse);
	}

	/**
	 * 根据条件查工作单
	 * 
	 * @author JS
	 * @param proGgxh
	 * @return
	 */
	public List<PlaCourse> getPlaCourse(String proGgxh) {
		String hql = " from PlaCourse where useFlag = '未启用' ";
		if (proGgxh != null) {
			hql += " AND headGgxh = ?  ";
		}
		hql += " order by id ";
		@SuppressWarnings("unchecked")
		List<PlaCourse> list = dao.createQuery(hql, proGgxh).list();
		return list;
	}

	/**
	 * 获取可合单的工作单
	 * 
	 * @return vo:NeedPartPlaCourseVo
	 * @throws Exception
	 */
	public Page getNeedPartPlaCourse(Page p) throws Exception {

		return dao.getUnProductPlaCourseList(p);
	}

	public List<PlaCourse> findPlaCourseByWsCode(String wsCode) {
		String hql = " from PlaCourse where useFlag = '未启用' ";
		if (wsCode != null) {
			hql += " AND wsCode = ?  ";
		}
		hql += " order by id ";
		@SuppressWarnings("unchecked")
		List<PlaCourse> list = dao.createQuery(hql, wsCode).list();
		return list;
	}

	public void updatePlaMacTaskState(PlaMacTask pmt) throws Exception {
		pmt.setProductstate("已排产");
		plaMachinePlanManageDAO.updateByCon(pmt, false);
	}

	/**
	 * @author JS
	 * @param p
	 * @param wsCode
	 * @param planEnableDate
	 * @param useFlag
	 * @param proCraftCode
	 * @return 工作单统计查询
	 */
	public Page getPlaCourseGrid(Page p, String wsCode, String planEnableDate,
			String isFinish, String proCraftCode) {
		String hql = " from PlaCourse where pastFlag='0'  ";
		// 条件查询判断
		if (wsCode != null && !"".equals(wsCode)) {
			hql += " AND wsCode = '" + wsCode + "' ";
		}
		if (planEnableDate != null && !"".equals(planEnableDate)) {
			hql += " AND planEnableDate = '" + planEnableDate + "' ";
		}
		if (isFinish != null && !"".equals(isFinish)) {
			hql += " AND isFinish = '" + isFinish + "' ";
		}
		if (proCraftCode != null && !"".equals(proCraftCode)) {
			hql += " AND proCraftCode = '" + proCraftCode + "' ";
		}
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getWsCode() {
		String hql = "SELECT DISTINCT wsCode from PlaCourse ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;

		}
		return str;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getProCraftCode() {
		String hql = "SELECT DISTINCT proCraftCode from CraCraftProduct ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;

		}
		return str;
	}

	/**
	 * 发送叉车任务到队列中,可以加入形参作为数据源，发送到叉车。
	 * 
	 * @param ll
	 * 
	 */
	public void send(List<PlaProductOrderSeqHourse> ll) {

		List<SendForkliftVo> voList = new ArrayList<SendForkliftVo>();
		for (PlaProductOrderSeqHourse bb : ll) {

			// 这个对象是从本机台到半成品区
			SendForkliftVo voBean = new SendForkliftVo();
			voBean.setTaskId(bb.getId().toString());
			voBean.setIsReceive("未领取");
			voBean.setDestination("半成品区");// 目的地
			voBean.setDestType("半成品区");// 目的地类型
			voBean.setCount(new BigDecimal(1));// 数量
			voBean.setMaterialGgxh(bb.getProGgxh());// 规格型号
			voBean.setStartPlace("机台：" + bb.getMacCode() + "收线处");// 出发地
			voBean.setUnit("轴");
			voBean.setMacCode(bb.getMacCode());
			voList.add(voBean);

			String axis_name = bb.getAxisName();
			if (axis_name != null && axis_name.length() > 13) {// GD20170908118_0001
				voBean.setCourseCode(axis_name.substring(0, 13));
			}
			MauCallForkliftRecord mfr = saveCallForkifitRecord(voBean);
			voBean.setTaskId(mfr.getId().toString());

			String proCraft = bb.getProCraftCode();
			String seqCode = bb.getSeqCode();
			CraProSeqRelation cpsr = craProSeqRelationManageDao
					.getRelaByCCP_code_and_seqCode(proCraft, seqCode);
			// 这个是从半成品区到本机台
			SendForkliftVo voBean1 = new SendForkliftVo();
			voBean1.setCount(new BigDecimal(cpsr.getSeqHalfProNum()));// 原料
																		// 数量（上工序的半成品）
			voBean1.setDestination("机台:" + bb.getMacCode() + "放线处");// 目的地，本机台
			voBean.setDestType("机台放线处");// 目的地类型
			voBean1.setIsReceive("未领取");
			voBean1.setMaterialGgxh("本工序生产之前所需原料盘");
			voBean1.setStartPlace("半成品区域");// 出发地点
			voBean1.setUnit("轴");
			voBean1.setMaterialGgxh(bb.getProGgxh()); // 产品规格型号
			voBean1.setMacCode(bb.getMacCode()); // 机台编号
			voList.add(voBean1);

			if (axis_name != null && axis_name.length() > 13) {// GD20170908118_0001
				voBean1.setCourseCode(axis_name.substring(0, 13));
			}
			mfr = saveCallForkifitRecord(voBean1);
			voBean1.setTaskId(mfr.getId().toString());
		}

		String msg = gson.toJson(voList);
		MqttBroker br = MqttBroker.getInstance();
		br.sendMessage("cc", msg);
	}

	/**
	 * @todo: 保存呼叫叉车记录
	 * @author : zhaichunlei
	 * @date: 2017年9月16日
	 */
	private MauCallForkliftRecord saveCallForkifitRecord(SendForkliftVo vo) {
		MauCallForkliftRecord mfr = new MauCallForkliftRecord();
		mfr.setCreateBy("System");
		mfr.setCreateDate(new Date(System.currentTimeMillis()));
		mfr.setCreateBy(vo.getStartPlace());
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
		mfr.setCallTime(sf.format(new Date()));
		/*
		 * mfr.setDestMachineOrPlace(vo.getDestination());
		 * mfr.setDestType(vo.getDestType());
		 */
		mfr.setDestAddress(vo.getDestType());
		mfr.setStatus("未领取");

		mauCallForkliftRecordManageDAO.save(mfr);
		return mfr;
	}

	/**
	 * 统计查询工作单进度
	 * 
	 * @param p
	 * @param map
	 * @return
	 */
	public Page getScheduleGrid(Page p, Map<String, String> map) {
		String hql = " from PlaCourse where 1=1  ";
		if (map != null) {
			// 条件查询判断
			if (map.get("wsCode") != null && !"".equals(map.get("wsCode"))) {
				hql += " AND wsCode like '%" + map.get("wsCode") + "%' ";
			}
			if (map.get("xCode") != null && !"".equals(map.get("xCode"))) {
				hql += " AND headGgxh like '%" + map.get("xCode") + "%' ";
			}

		}

		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	/**
	 * @Description: 查询今日工单完成情况,以最后一道工序为准
	 * @return
	 */
	public PlaMachineDisplayVo getTodayComplete() {
		// TODO Auto-generated method stub
		PlaMachineDisplayVo vo = new PlaMachineDisplayVo();
		/*
		 * List<String> X = new ArrayList<>(); List<String> Y = new
		 * ArrayList<>(); // 1.查询机台任务表今日有哪些工单在生产,获得工单编号 String workDate =
		 * DateUtil.format(new Date(), "yyyy-MM-dd"); // 思路一:当天的生产单 //
		 * List<Object[]> courseList = //
		 * plaMachinePlanManageDAO.getCourseCode(workDate); // 另一种思路,查询正在生产的
		 * List<Object[]> courseList = plaMachinePlanManageDAO
		 * .getWorkingCourseCode(); if (courseList == null || courseList.size()
		 * == 0) { return null; } // 2.根据工单编号查询工艺路线,获得最后一道工序编号 for (Object[]
		 * strs : courseList) { String courseCode = strs[0] == null ? null :
		 * strs[0].toString(); String routeCode = strs[1] == null ? null :
		 * strs[1].toString(); String seqCode =
		 * craRouteSeqManageDAO.getLastSeqCode(routeCode); //
		 * 3.查询机台任务计划和机台进度,担心一个工单 一个工序 有多个机台所以使用object[] List<Object[]> list =
		 * plaMachinePlanScheduleManageDAO .getPlanAndFactPro(courseCode,
		 * seqCode); BigDecimal allPro = new BigDecimal(0); BigDecimal allsemi =
		 * new BigDecimal(0); for (Object[] objs : list) { String proLen =
		 * objs[0] == null ? "0" : objs[0].toString(); String semiLen = objs[1]
		 * == null ? "0" : objs[1].toString(); BigDecimal pro = new
		 * BigDecimal(proLen); BigDecimal semi = new BigDecimal(semiLen); allPro
		 * = allPro.add(pro); allsemi = allsemi.add(semi); } // 计算完成率 ,保存两位小数 //
		 * BigDecimal divide = allsemi.divide(allPro); BigDecimal rate =
		 * allsemi.divide(allPro) .setScale(2, BigDecimal.ROUND_HALF_UP)
		 * .multiply(new BigDecimal(100)); X.add(courseCode);
		 * Y.add(rate.toString()); } vo.setAreas(X); vo.setDataList(Y);
		 */
		String today = DateUtil.format(new Date(), "yyyy-MM-dd");
		String start = today + " 00:00:00";
		String end = today + " 23:59:59";
		String hql = "from PlaCourse where ws_pstime > '" + start
				+ "'and ws_pstime < '" + end + "'";
		List<PlaCourse> list = dao.createQuery(hql).list();
		List<String> X = new ArrayList<>();
		List<String> Y = new ArrayList<>();
		for (PlaCourse plaCourse : list) {
			X.add(plaCourse.getWsCode());
			Y.add(plaCourse.getWsSchedule().toString());
		}
		vo.setAreas(X);
		vo.setDataList(Y);
		return vo;
	}

	/**
	 * @todo:单管理，查询工单相信信息
	 * @author : zhaichunlei
	 * @throws Exception
	 * @date: 2017年9月21日
	 */
	public Page getCourseInfoList(Page p, PlaCourse ent) throws Exception {
		return dao.getCourseInfoList(p, ent);
	}

	/**
	 * 
	 * @todo: 生成计划
	 * @author : zhaichunlei
	 * @date: 2017年9月28日
	 */
	@Transactional(readOnly = false)
	public void genPlanByGd_step2(SysUser user, String ids) throws Exception {
		if (ids == null || ids.length() == 0)
			throw new Exception("请选择工单");
		String idst[] = ids.split(",");

		// 校验是否能再次生成计划。 校验生产令的生产状态
		for (String id : idst) {
			if (id == null || (id = id.trim()).length() == 0) {
				continue;
			}
			PlaCourse pc = dao.get(Integer.parseInt(id));
			PlaProductOrder ppo = plaProductOrderManageDAO
					.getPlaProductOrderByCode(pc.getWsCode());
			String isFlag = ppo.getIs_flag();
			if (!"0".equals(isFlag)) {
				throw new Exception("工单(" + pc.getWsCode() + ")已开始生产，不能重新生成计划");
			}
		}

		for (String id : idst) {
			if (id == null || (id = id.trim()).length() == 0) {
				continue;
			}
			PlaCourse pc = dao.get(Integer.parseInt(id));
			// 调用过程
			dao.exeFunction("proc_gen_plan_by_course_code", pc.getWsCode());
		}
	}

	/**
	 * @todo 删除工单
	 * @param ids
	 * @throws Exception
	 */
	/*
	 * public void deleteCourse(String ids) throws Exception { if (ids == null
	 * || ids.length() == 0) throw new Exception("请选择记录");
	 * 
	 * String arr[] = ids.split(","); for (String idstr : arr) { if (idstr !=
	 * null && idstr.length() > 0) { Integer id = Integer.parseInt(idstr);
	 * PlaCourse pc = dao.get(id); String wscode = pc.getWsCode();
	 * PlaProductOrder po = plaProductOrderManageDAO
	 * .getPlaProductOrderByCode(wscode); Integer contract_detail_id =
	 * po.getContractDetailId(); Integer partlen =
	 * Integer.parseInt(po.getProductPartLen())
	 * Integer.parseInt(po.getAmount().toString());
	 * 
	 * // 删除轴名称 // String sql = //
	 * "delete from PlaProductOrderAxis where productOrderCode=?";
	 * PlaProductOrderAxis poa = new PlaProductOrderAxis();
	 * poa.setProductOrderCode(wscode);
	 * plaProductOrderAxisManageDAO.removeByCon(poa); // 删除工单 dao.remove(pc); //
	 * 删除生产令表 PlaProductOrder ppo = new PlaProductOrder();
	 * ppo.setProductOrderCode(wscode);
	 * plaProductOrderManageDAO.removeByCon(ppo);
	 * 
	 * // 重计划生产通知单已下发数量 SellContractDetail scd = sellContractDetailManageDAO
	 * .get(contract_detail_id); Integer rem = scd.getCompleteLen() - partlen;
	 * // 这里无法考虑小于0，数据错误的情况。也不能抛出异常。 按正常的逻辑处理 if (rem > 0)
	 * scd.setPbatDetailState("未全部生成"); else scd.setPbatDetailState("未生成");
	 * scd.setCompleteLen(rem); sellContractDetailManageDAO.updateByCon(scd,
	 * false); } } }
	 */

	/**
	 * @ todo:查询工音信息
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PlaDisPatchVo getGDInfo(Integer courseId, String seqCode,
			Integer machineId, String proGgxh, String proColor)
			throws Exception {
		if (seqCode != null && seqCode.length() > 0 && machineId == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("select a.seq_code,m.work_day,sum(m.hours) hours from mau_task_load m,mau_machine a  where m.obj_id=a.id ");
			sb.append("and exists(select 1 from mau_machine a where m.obj_id=a.id and a.seq_code=?) ");
			sb.append("and m.work_day > cast(to_char(now(),'yyyyMMdd') as int4)");
			sb.append("group by a.seq_code,m.work_day ");
			sb.append(" order by m.work_day ");

			List<Object[]> lst = dao.createSQLQuery(sb.toString(), seqCode)
					.list();

			PlaDisPatchVo vo = new PlaDisPatchVo();
			List<String> barY = new ArrayList<String>();
			List<String> barX = new ArrayList<String>();
			for (Object o[] : lst) {
				barX.add(o[1].toString());
				barY.add(o[2].toString());
			}
			vo.setBarX(barX);
			vo.setBarY(barY);

			return vo;
		} else if (machineId != null
				&& (seqCode == null || seqCode.length() == 0)) {
			StringBuffer sb = new StringBuffer();
			sb.append("select a.seq_code,a.mac_code,m.work_day,sum(m.hours) hours from mau_task_load m,mau_machine a  where m.obj_id=a.id ");
			sb.append("and m.obj_id=? ");
			sb.append("and m.work_day > cast(to_char(now(),'yyyyMMdd') as int4) ");
			sb.append("group by a.seq_code,a.mac_code,m.work_day ");
			sb.append(" order by m.work_day ");
			List<Object[]> lst = dao.createSQLQuery(sb.toString(), machineId)
					.list();

			PlaDisPatchVo vo = new PlaDisPatchVo();
			List<String> barY = new ArrayList<String>();
			List<String> barX = new ArrayList<String>();
			for (Object o[] : lst) {
				barX.add(o[2].toString());
				barY.add(o[3].toString());
			}
			vo.setBarX(barX);
			vo.setBarY(barY);

			return vo;
		} else {
			throw new Exception("传参错误，工序编码与机台，只能一个有值。");
		}

		// String sql = "from MauMachineSpeed m where ";
		// sql +=
		// "exists(SELECT 1 from MauMachine a where a.id=m.machineId and m.proGgxh=? and a.seqCode=?)";

		/*
		 * //本工序对应的，支持生产本产品的机台列表 List<MauMachineSpeed> mlst =
		 * mauMachineSpeedManageDAO.listQuery(sql, ppo.getProGgxh(),seqCode);
		 * if(mlst == null || mlst.size() == 0) throw new
		 * Exception("本产品在工序("+seqCode+")不存在支持的机台。请联系管理员设置机台生产参数");
		 * 
		 * 
		 * PlaCourse pc = dao.get(id); String pro_craft_code =
		 * pc.getProCraftCode(); if(pro_craft_code == null ||
		 * pro_craft_code.length() == 0) throw new Exception("产品工艺编码不能为空");
		 * CraCraftProduct ccp =
		 * craCraftProductManageDAO.getObjByProCraCode(pro_craft_code); if( ccp
		 * == null) throw new Exception("数据错误，产品工艺("+pro_craft_code+")不存在");
		 * 
		 * PlaProductOrder ppo =
		 * plaProductOrderManageDAO.getPlaProductOrderByCode(pc.getWsCode());
		 * if( ppo == null) throw new
		 * Exception("数据错误，工单("+pro_craft_code+")对应的生产令不存在");
		 * 
		 * String rc = ccp.getRouteCode(); if(rc == null || rc.length() == 0)
		 * throw new Exception("数据错误，产品工艺（"+pro_craft_code+"）的工艺路线不能为空");
		 * 
		 * String hql = "from CraRouteSeq where routeCode=? order by sort ";
		 * List<CraRouteSeq> lst = craRouteSeqManageDAO.listQuery(hql, rc);
		 * PlaCourseVO vo = new PlaCourseVO(); vo.setCraRouteSeqList(lst);
		 * vo.setPro_color(ppo.getProColor()); vo.setProGgxh(ppo.getProGgxh());
		 * >>>>>>> .theirs /* //本工序对应的，支持生产本产品的机台列表 List<MauMachineSpeed> mlst =
		 * mauMachineSpeedManageDAO.listQuery(sql, ppo.getProGgxh(),seqCode);
		 * if(mlst == null || mlst.size() == 0) throw new
		 * Exception("本产品在工序("+seqCode+")不存在支持的机台。请联系管理员设置机台生产参数");
		 * 
		 * 
		 * PlaCourse pc = dao.get(id); String pro_craft_code =
		 * pc.getProCraftCode(); if(pro_craft_code == null ||
		 * pro_craft_code.length() == 0) throw new Exception("产品工艺编码不能为空");
		 * CraCraftProduct ccp =
		 * craCraftProductManageDAO.getObjByProCraCode(pro_craft_code); if( ccp
		 * == null) throw new Exception("数据错误，产品工艺("+pro_craft_code+")不存在");
		 * 
		 * PlaProductOrder ppo =
		 * plaProductOrderManageDAO.getPlaProductOrderByCode(pc.getWsCode());
		 * if( ppo == null) throw new
		 * Exception("数据错误，工单("+pro_craft_code+")对应的生产令不存在");
		 * 
		 * String rc = ccp.getRouteCode(); if(rc == null || rc.length() == 0)
		 * throw new Exception("数据错误，产品工艺（"+pro_craft_code+"）的工艺路线不能为空");
		 * 
		 * String hql = "from CraRouteSeq where routeCode=? order by sort ";
		 * List<CraRouteSeq> lst = craRouteSeqManageDAO.listQuery(hql, rc);
		 * PlaCourseVO vo = new PlaCourseVO(); vo.setCraRouteSeqList(lst);
		 * vo.setPro_color(ppo.getProColor()); vo.setProGgxh(ppo.getProGgxh());
		 * 
		 * for(CraRouteSeq crs : lst){ String seqCode = crs.getSeqCode(); String
		 * sql = "from MauMachineSpeed m where "; sql +=
		 * "exists(SELECT 1 from MauMachine a where a.id=m.machineId and m.proGgxh=? and a.seqCode=?)"
		 * ;
		 * 
		 * //本工序对应的，支持生产本产品的机台列表 List<MauMachineSpeed> mlst =
		 * mauMachineSpeedManageDAO.listQuery(sql, ppo.getProGgxh(),seqCode);
		 * if(mlst == null || mlst.size() == 0) throw new
		 * Exception("本产品在工序("+seqCode+")不存在支持的机台。请联系管理员设置机台生产参数");
		 * 
		 * crs.setMauMachineSpeedLst(mlst);
		 * 
		 * for(MauMachineSpeed mms : mlst){ //取本机台的负荷
		 * 
		 * } }
		 */
	}

	/**
	 * @param ids
	 * @return
	 */
	public String getGDInfoStr(String ids) {
		if (ids != null && ids.length() > 0) {
			String idst[] = ids.split(",");
			StringBuffer sb = new StringBuffer();
			for (String id : idst) {
				PlaCourse pc = dao.get(Integer.parseInt(id));
				sb.append(pc.getWsCode() + ",");
			}
			return sb.substring(0, sb.length() - 1);
		}
		return "";
	}

	public void exportScheduleExcels(HttpServletResponse response, Page page,
			Map<String, String> map) throws Exception {
		// 获取表格样式
		WritableCellFormat titleFormat = ExportExcel.getTitleFormat();
		WritableCellFormat bodyFormat = ExportExcel.getBodyFormat();

		response.reset();

		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		String titleName = "进度查询Excel".concat(format);
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;

		try {
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);

			String[] arrTitle = new String[] { "工作单编码", "工作单类型", "描述信息",
					"合同编号", "批次号", "型号规格", "颜色", "扎装段长", "扎装段数", "总数量" };

			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new jxl.write.Label(i, 0, arrTitle[i], titleFormat));
			}
			Page pageList = this.getScheduleGrid(page, map);
			if (null != pageList) {
				List<PlaCourse> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					PlaCourse plac = data.get(i);
					ws.addCell(new jxl.write.Label(0, x, null == plac
							.getWsCode() ? "" : plac.getWsCode(), bodyFormat));
					ws.addCell(new jxl.write.Label(1, x, null == plac
							.getWsType() ? "" : plac.getWsType(), bodyFormat));
					ws.addCell(new jxl.write.Label(2, x, null == plac
							.getRemark() ? "" : plac.getRemark(), bodyFormat));
					ws.addCell(new jxl.write.Label(3, x, null == plac
							.getScCode() ? "" : plac.getScCode(), bodyFormat));
					ws.addCell(new jxl.write.Label(4, x, null == plac
							.getBatCode() ? "" : plac.getBatCode(), bodyFormat));
					ws.addCell(new jxl.write.Label(5, x, null == plac
							.getHeadGgxh() ? "" : plac.getHeadGgxh(),
							bodyFormat));
					ws.addCell(new jxl.write.Label(6, x, null == plac
							.getColor() ? "" : plac.getColor(), bodyFormat));
					ws.addCell(new jxl.write.Label(7, x, null == plac
							.getHeadZzdc() ? "" : plac.getHeadZzdc(),
							bodyFormat));
					ws.addCell(new jxl.write.Label(8, x, null == plac
							.getHeadZzds() ? "" : plac.getHeadZzds(),
							bodyFormat));
					ws.addCell(new jxl.write.Label(9, x, null == plac
							.getTotalAmount().toString() ? "" : plac
							.getTotalAmount().toString(), bodyFormat));
					x++;
				}

			}
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ titleName + ".xls\"");
			wwb.write();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			wwb.close();
			outputStream.close();
		}

	}

	/**
	 * 通过工作单列表生成计划
	 * 
	 * @param pcs
	 * @throws Exception
	 */
	public void setPlaMacTaskByWorkList(List<PlaCourse> pcs) throws Exception {
		try {
			for (PlaCourse pc : pcs) {
				setPlaMacTaskByWork(pc);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * 生产所有planflag=否的开单的工单计划
	 */
	public void setAllPlaCoursePlan() throws Exception {
		List<PlaCourse> pcs = dao.getNoPlanNormalPlaCourseList();
		for (PlaCourse pc : pcs) {
			try {
				setPlaMacTaskByWork(pc);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

	}

	/**
	 * 插单生产计划
	 */
	public void setInsertPlaCoursePlan() throws Exception {
		// 获取正常开单planflag=是 useflag=否的列表
		List<PlaCourse> pcs = dao.getNormalPlanPlaCourseList();
		// 删除对应的pla_Mac_task pla_Mac_task_color pla_Mac_task_marerial
		try {
			for (PlaCourse pc : pcs) {
				plaMachinePlanManageDAO.deleteByworkCode(pc.getWsCode());
				pc.setPlanFlag(PlaCourse.PLAN_FLAG_NO);
				pc.setWs_pstime(null);
				pc.setWs_pdtime(null);
				dao.updateByCon(pc, false);
			}
		} catch (Exception e) {
			throw new Exception("删除计划失败");
		}
		// 先生成插单的工单
		pcs.clear();
		pcs = dao.getInsertNoPlanPlaCourseList();
		try {
			for (PlaCourse pc : pcs) {
				setPlaMacTaskByWork(pc);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		// 再生成正常开单的工单
		pcs.clear();
		pcs = dao.getNoPlanNormalPlaCourseList();
		try {
			for (PlaCourse pc : pcs) {
				setPlaMacTaskByWork(pc);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 通过工单形成工作单 1 取工单信息 2获取产品型号的工艺路线 3 遍历工序
	 * 
	 * @throws Exception
	 */
	public void setPlaMacTask(String ids) throws Exception {
		PlaCourse pc = new PlaCourse();
		pc = dao.get(Integer.parseInt(ids));
		String plan_falg = pc.getPlanFlag();
		if (plan_falg != null && "是".equals(plan_falg)) {
			throw new Exception("已单(" + pc.getWsCode() + ")已生成计划");
		} else {
			try {
				setPlaMacTaskByWork(pc);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}

	/**
	 * 生产一个工作单的计划
	 * 
	 * @param pc
	 * @throws Exception
	 */
	public void setPlaMacTaskByWork(PlaCourse pc) throws Exception {
		// 取工单
		String ggxh = "";
		ggxh = pc.getProGgxh();
		// 取工作单颜色、轴数、单轴长度
		ArrayList<WorkAxisColor> wacs = new ArrayList<WorkAxisColor>();
		String sql = "select color,axis_num,axis_length from pla_course_axis"
				+ " where course_code='" + pc.getWsCode() + "' and pro_ggxh='"
				+ ggxh + "'";
		List<Object> list = dao.createSQLQuery(sql).list();
		Iterator<Object> its = list.iterator();
		while (its.hasNext()) {
			WorkAxisColor wac = new WorkAxisColor();
			Object[] obj = (Object[]) its.next();
			wac.color = (String) obj[0];
			wac.axisnum = (int) obj[1];
			wac.axislength = (int) obj[2];
			wacs.add(wac);
		}

		// 从产品型号中获取线缆每根芯的导体根数
		sql = "select in_core  from mau_product where pro_ggxh='" + ggxh + "'";
		list.clear();
		String[] cores = null;
		String[] areas = null;
		list = dao.createSQLQuery(sql).list();
		for (Object ob : list) {
			if (ob != null) {
				cores = ((String) ob).split(";");
			}
		}
		// 如果芯线的根数一样，则说明集绞的芯线一样或者是单芯线，如果不一样则说明一个工作单会有相同工序（ls、jy、等）
		ArrayList<Integer> corenumbers = new ArrayList<Integer>();
		for (int i = 0; i < cores.length; i++) {
			corenumbers.add(Integer.valueOf(cores[i]));
		}
		ArrayList<String> seqs = new ArrayList<String>();
		ArrayList<Integer> steps = new ArrayList<Integer>();
		ArrayList<PlaMacTaskMateril> pmtms = new ArrayList<PlaMacTaskMateril>();
		plaMachinePlanManageDAO.getSeqs(ggxh, seqs, steps);
		Map<String, Float> maxLength = new HashMap<>();
		ArrayList<PlaMacTask> plaMacTasks = new ArrayList<PlaMacTask>();
		try {
			for (int i = 0; i < seqs.size(); i++) {
				String befseq = "";
				if (i > 0) {
					befseq = seqs.get(i - 1);
				}
				String seq = seqs.get(i);// 工序代码
				int step = steps.get(i);// 工序的步骤
				setSeqMaxLength(maxLength, ggxh, seq, step);
				// SEQ获取该工序表的
				PlaMacTask pmt = new PlaMacTask();
				// 放工单号
				pmt.setWorkcode(pc.getWsCode());
				// 设置seqCode
				pmt.setSeqcode(seq);
				// 设置step
				pmt.setStep(step);
				// state
				pmt.setProductstate("计划");

				// 放交货日期
				pmt.setDelidate(pc.getDemandDate());

				/*
				 * if (i > 0 && step == steps.get(i - 1)) { //
				 * 说明该工序与上工序是同步完成（及使用同一个机台） // 选机台
				 * 
				 * String macCode = plaMacTasks.get(plaMacTasks.size() - 1)
				 * .getMaccode(); pmt.setMaccode(macCode);
				 * 
				 * } else { // 选机台 String macCode = getMacCode(ggxh,
				 * steps,seqs,i); pmt.setMaccode(macCode);
				 * 
				 * }
				 */

				// axisParam(因为要算长度,所以在算线盘前一定要有长度,要有长度必须要有axis_color)
				if (i < seqs.size() - 1) {
					pmt.setAxisParam(getAxisParam(corenumbers, seq, ggxh, pc,
							step, pmt.getMaccode(), wacs));
				}
				if (i == seqs.size() - 1) {
					// 计算最后一个工序的长度，颜色，分配轴名称，即设置最后一个工序的AxisParam
					setLastPlaMacTaskAxaisParam(pc, pmt, wacs);
				}
				plaMacTasks.add(pmt);
			}

			// 计算长度
			setAxisLength(maxLength, pc, plaMacTasks, ggxh, false);
			// 设置机台
			// setMacCode( plaMacTasks, ggxh, steps, seqs);
			for (int i = 0; i < seqs.size(); i++) {
				String seq = seqs.get(i);// 工序代码
				int step = steps.get(i);// 工序的步骤
				PlaMacTask plaMacTask = plaMacTasks.get(i);
				float length = plaMacTask.getAxisParam().get(0).getLength();
				if (i > 0 && step == steps.get(i - 1)) {
					// 说明该工序与上工序是同步完成（及使用同一个机台） 同样的收线盘,放线盘
					// 选机台
					String macCode = plaMacTasks.get(i - 1).getMaccode();
					plaMacTask.setMaccode(macCode);
					plaMacTask.setPuttype(plaMacTasks.get(i - 1).getPuttype());
					plaMacTask.setReaxistype(plaMacTasks.get(i - 1)
							.getReaxistype());

				} else {
					// 选机台
					setNewMacCode(plaMacTasks, ggxh, steps, seqs, i);
				}

				// 将maccode 设置进axisparam
				setAxisParamMacCode(plaMacTasks);
			}
			// 重新计算长度，选择机台后，加上调试长度
			setAxisLength(maxLength, pc, plaMacTasks, ggxh, true);
			// 出轴数
			setAxisNumber(plaMacTasks);

			// 计算机台的生产速度
			setMacSpeed(plaMacTasks, seqs, steps, ggxh);

			// 计算每个工序到所用时间
			setUseMinute(plaMacTasks, ggxh);

			// 计算每个工序的开始与结束时间
			setTime(plaMacTasks);

			// 添加操作人员

			// 计算用料
			setPlaMacTaskMateril(plaMacTasks, ggxh);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		// 存储数据库
		for (PlaMacTask pmt : plaMacTasks) {
			plaMachinePlanManageDAO.save(pmt);

			for (PlaMacTaskAxisParam pmtap : pmt.getAxisParam()) {
				// 增加步骤,荣添加
				pmtap.setStep(pmtap.getStep());
				plaMacTaskAxisParamDAO.save(pmtap);
			}

			for (PlaMacTaskMateril pmtm : pmt.getPmtms()) {
				plaMacTaskMaterilDAO.save(pmtm);
			}

		}

		// 设置工单的计划开始时间和结束时间
		pc.setWs_pstime(plaMacTasks.get(0).getPstime());
		pc.setWs_pdtime(plaMacTasks.get(plaMacTasks.size() - 1).getPdtime());
		pc.setPlanFlag(PlaCourse.PLAN_FLAG_YES);
		this.updateByCon(pc, false);

	}

	private void setAxisParamMacCode(ArrayList<PlaMacTask> plaMacTasks) {
		for (int i = 0; i < plaMacTasks.size(); i++) {
			PlaMacTask plaMacTask = plaMacTasks.get(i);
			ArrayList<PlaMacTaskAxisParam> axisParam = plaMacTask
					.getAxisParam();
			for (PlaMacTaskAxisParam plaMacTaskAxisParam : axisParam) {
				plaMacTaskAxisParam.setMaccode(plaMacTask.getMaccode());
			}
		}
	}

	/**
	 * 选择机台
	 * 
	 * @param seq
	 * @param ggxh
	 * @return
	 */
	public String getMacCode(String ggxh, ArrayList<Integer> steps,
			ArrayList<String> seqs, int index) throws Exception {

		String macCode = "";
		String mater = "";
		String sql = "";
		String seq = seqs.get(index);
		int step = steps.get(index);
		// 根据bom表取目标线径，
		float diameter = 0;

		diameter = plaMachinePlanManageDAO.getDiameterVaule(ggxh, steps, seqs,
				index);

		if (diameter == 0) {
			throw new Exception("请检查" + seq + "对应的bom表，未能获取线缆正常直径值");
		}
		if (seq.equalsIgnoreCase("ls")) {
			mater = plaMachinePlanManageDAO.getMaterValue(ggxh, seq, step);
			sql = "select mac_code from mau_machine a ,store_material_basic_info c "
					+ "where   a.seq_code='ls' and mac_state in('运行','关闭') "
					+ "and diameter_min <"
					+ diameter
					+ " and diameter_max >="
					+ diameter
					+ " and  c.material_type = a.use_mater and c.mater_ggxh='"
					+ mater + "'";
		} else {
			sql = "select mac_code from mau_machine a " + "where a.seq_code='"
					+ seq + "' and  mac_state in('运行','关闭') "
					+ "and diameter_min <" + diameter + " and diameter_max >="
					+ diameter;
		}
		ArrayList<String> macal = new ArrayList<String>();
		// 该工序的所有机台 条件：与工序（机台类型）相关，线径大小，机台状态
		List<Object> list = dao.createSQLQuery(sql).list();
		for (Object ob : list) {
			if (ob != null) {
				macal.add((String) ob);
			}
		}

		if (macal.size() == 0) {
			throw new Exception("没有找到工序：" + seq + "对应的机台");
		}
		// 取oee 该类型中最大值的mac_code
		sql = "SELECT mac_code FROM (select row_number() OVER () AS indexid, mac_code,oee from mau_oee a "
				+ " join  cra_seq b on a.seq_name = b.seq_name and b.seq_code='"
				+ seq + "' order by oee desc) c where c.indexid = 1 ";
		list.clear();
		list = dao.createSQLQuery(sql).list();
		String maxOee_macCode = "";
		for (Object ob : list) {
			if (ob != null) {
				maxOee_macCode = (String) ob;
			}
		}
		for (int i = 0; i < macal.size(); i++) {
			if (macal.get(i).equalsIgnoreCase(maxOee_macCode)) {
				macCode = macal.get(i);
			}
		}
		if (macCode == "") {
			macCode = macal.get(0);
		}
		if (macCode == "" || macCode == null) {
			throw new Exception("工序：" + seq + "没有找到可用的机台");
		}
		return macCode;
	}

	public void setMacCode(ArrayList<PlaMacTask> tasks, String ggxh,
			ArrayList<Integer> steps, ArrayList<String> seqs) throws Exception {

		for (int index = 0; index < tasks.size(); index++) {

			String macCode = "";
			String mater = "";
			String sql = "";
			String seq = seqs.get(index);
			int step = steps.get(index);
			PlaMacTask task = tasks.get(index);
			float length = task.getAxisParam().get(0).getLength();
			if (index > 0 && step == steps.get(index - 1)) {
				// 说明该工序与上工序是同步完成（及使用同一个机台） 同样的收线盘,放线盘
				// 选机台
				macCode = tasks.get(index - 1).getMaccode();
				task.setMaccode(macCode);
				task.setPuttype(tasks.get(index - 1).getPuttype());
				task.setReaxistype(tasks.get(index - 1).getReaxistype());
				continue;

			}
			// 根据bom表取目标线径，
			float diameter = 0;
			diameter = plaMachinePlanManageDAO.getDiameterVaule(ggxh, steps,
					seqs, index);
			// 最小弯曲半径
			if (seq.equalsIgnoreCase("ls")) {
				mater = plaMachinePlanManageDAO.getMaterValue(ggxh, seq, step);
				sql = "select mac_code from mau_machine a ,store_material_basic_info c "
						+ "where   a.seq_code='ls' and mac_state in('运行','关闭') "
						+ "and diameter_min <"
						+ diameter
						+ " and diameter_max >="
						+ diameter
						+ " and  c.material_type = a.use_mater and c.mater_ggxh='"
						+ mater + "'";
			} else {
				sql = "select mac_code from mau_machine a "
						+ "where a.seq_code='" + seq
						+ "' and  mac_state in('运行','关闭') "
						+ "and diameter_min <" + diameter
						+ " and diameter_max >=" + diameter;
			}
			// 该工序的所有机台 条件：与工序（机台类型）相关，线径大小，机台状态
			List<String> macList = dao.createSQLQuery(sql).list();
			if (seq.equalsIgnoreCase("ls")) {
				// 拉丝 直接用oee 确定机台
				// 取oee 该类型中最大值的mac_code
				sql = "SELECT mac_code FROM (select row_number() OVER () AS indexid, mac_code,oee from mau_oee a "
						+ " join  cra_seq b on a.seq_name = b.seq_name and b.seq_code='"
						+ seq + "' order by oee desc) c where c.indexid = 1 ";
				List<String> oeeMacList = dao.createSQLQuery(sql).list();
				// 如果oee最大在其中 就选oee 或者随机
				if (macList.contains(oeeMacList)) {
					macCode = oeeMacList.get(0);
				} else {
					macCode = macList.get(0);
				}
				task.setMaccode(macCode);
				// 拉丝 只有收线盘
				task.setReaxistype(getMauAxisName(macCode, "收线轴", diameter,
						length));
			} else {
				// 如果不是拉丝就要确定,前后的盘的关系
				// 前一个步骤的收线盘 --本步骤的 放线盘
				String reaxistype = tasks.get(index - 1).getReaxistype();
				// 查询 该工序下 可以将此收线盘,作为放线盘的 机台code
				sql = "select mac_code  from  mau_machine seq_code = '"
						+ task.getSeqcode()
						+ "' and mac_code in(select mac_code from mau_axis_mac where axis_type ='"
						+ reaxistype + "') ";
				List<String> axisList = dao.createSQLQuery(sql).list();
				// 取交集
				axisList.retainAll(macList);
				if (axisList.size() == 0) {
					throw new Exception("没有找到合适的机台,线盘判断有误");
				} else if (axisList.size() == 1) {
					macCode = axisList.get(0);
				} else {
					// 多个机台,查询oee
					String dSql = "";
					for (String mac : axisList) {
						dSql = " mac_code = '" + mac + "' or";
					}
					sql = "select mac_code from mau_oee where ("
							+ dSql.substring(dSql.length() - 2)
							+ ")  order by oee DESC ";
					List<String> oeeList = dao.createSQLQuery(sql).list();
					macCode = oeeList.get(0);
				}

				// 设置本任务的出线轴
				// 判断 本任务可使用的出线轴
				sql = "select axis_name from mau_axis_mac where mac_code = '"
						+ macCode + "' and axis_type ='出线轴'";
				List<String> outList = dao.createSQLQuery(sql).list();
				// 判断下一任务能使用的 放线轴
				Integer nextSeq = steps.get(index + 1);

				if (index != 0 && index != steps.size() - 1) {
					// 不是最后一步
					sql = "select axis_name from mau_axis_mac where mac_code  in (select mac_code from mac_machine where seq_code = '"
							+ nextSeq + "') and axis_type ='放线轴'";
					List<String> canOutList = dao.createSQLQuery(sql).list();
					outList.retainAll(canOutList);
					if (outList.size() == 0) {
						throw new Exception("判断线盘有误,查看线盘的数据录入");
					} else if (outList.size() == 1) {
						task.setPuttype(outList.get(0));
					} else {
						task.setPuttype(getMauAxisName(outList, diameter,
								length));
					}
				} else {
					// 最后一步
					task.setPuttype(getMauAxisName(outList, diameter, length));
				}

			}
		}
	}

	public void setNewMacCode(ArrayList<PlaMacTask> tasks, String ggxh,
			ArrayList<Integer> steps, ArrayList<String> seqs, int index)
			throws Exception {
		String macCode = "";
		String mater = "";
		String sql = "";
		String seq = seqs.get(index);
		int step = steps.get(index);
		PlaMacTask task = tasks.get(index);
		float length = task.getAxisParam().get(0).getLength();
		// 根据bom表取目标线径，
		float diameter = 0;
		diameter = plaMachinePlanManageDAO.getDiameterVaule(ggxh, steps, seqs,
				index);
		if (diameter == 0) {
			throw new Exception("没有获取到该工序" + seq + "对应bom表的目标线径");
		}

		if (seq.equalsIgnoreCase("ls")) {
			mater = plaMachinePlanManageDAO.getMaterValue(ggxh, seq, step);
			sql = "select mac_code from mau_machine a ,store_material_basic_info c "
					+ "where   a.seq_code='ls' and mac_state in('运行','关闭') "
					+ "and diameter_min <"
					+ diameter
					+ " and diameter_max >="
					+ diameter
					+ " and  c.material_type = a.use_mater and c.mater_ggxh='"
					+ mater + "' order by mac_code";
		} else {
			sql = "select mac_code from mau_machine a " + "where a.seq_code='"
					+ seq + "' and  mac_state in('运行','关闭') "
					+ "and diameter_min <" + diameter + " and diameter_max >="
					+ diameter + " order by mac_code";
		}
		// 该工序的所有机台 条件：与工序（机台类型）相关，线径大小，机台状态
		List<String> macList = dao.createSQLQuery(sql).list();

		if (seq.equalsIgnoreCase("ls")) {
			// 拉丝 直接用oee 确定机台
			// 取oee 该类型中最大值的mac_code
			sql = "SELECT mac_code FROM (select row_number() OVER () AS indexid, mac_code,oee from mau_oee a "
					+ " join  cra_seq b on a.seq_name = b.seq_name and b.seq_code='"
					+ seq
					+ "' order by oee desc) c where c.indexid = 1 order by mac_code";
			List<String> oeeMacList = dao.createSQLQuery(sql).list();
			// 如果oee最大在其中 就选oee 或者随机
			if (macList.contains(oeeMacList)) {
				macCode = oeeMacList.get(0);
			} else {
				macCode = macList.get(0);
			}
			task.setMaccode(macCode);
			// 拉丝 只有收线盘
			task.setReaxistype(getMauAxisName(macCode, "收线轴", diameter, length));
		} else {
			// 1.可以确定防线盘
			// 1.1 集绞有可能有多个放线盘,只要是绝缘后的都可以
			// 放线盘
			List<Integer> list = new ArrayList<>();
			String puttype = "";
			// 如果上一个收线轴没有,本工序的放线轴就没有
			if (tasks.get(index - 1).getReaxistype() == null
					|| "".equals(tasks.get(index - 1).getReaxistype())) {
				// 不设置放线轴
			} else {
				if (!"jj".equalsIgnoreCase(seq)) {
					puttype = tasks.get(index - 1).getReaxistype();
				} else {
					// 集绞的防线盘有可能有多个规格,找出绝缘的收线盘 就可以了
					for (int a = 0; a < seqs.size(); a++) {
						if ("jy".equals(seqs.get(a))) {
							list.add(a);
						}
					}
					for (Integer i : list) {
						String str = tasks.get(i).getReaxistype();
						puttype += str + " ";
					}
				}
			}
			task.setPuttype(puttype);
			// 2. 找出能放这种盘的 机台
			if (list.size() == 0) {
				if ("".equals(puttype) || puttype == null) {
					// 没有上个工序的收线盘
					sql = "select mac_code  from  mau_machine where seq_code = '"
							+ task.getSeqcode() + "' order by mac_code";
				} else {
					sql = "select mac_code  from  mau_machine where seq_code = '"
							+ task.getSeqcode()
							+ "' and mac_code in (select mac_code from mau_axis_mac where axis_name ='"
							+ puttype
							+ "' group by mac_code) order by mac_code";
				}
			} else {
				String dSql = "";
				for (Integer i : list) {
					dSql += " axis_name = '" + tasks.get(i).getReaxistype()
							+ "' or";
				}
				sql = "select mac_code  from  mau_machine where seq_code = '"
						+ task.getSeqcode()
						+ "' "
						+ "and mac_code in (select mac_code from mau_axis_mac where "
						+ "("
						+ dSql.substring(0, dSql.length() - 2)
						+ ") and axis_type='放线轴' group by mac_code order by mac_code) ";
			}
			List<String> axisList = dao.createSQLQuery(sql).list();
			// 3. 取机台
			// 两种算法的机台List取交集
			axisList.retainAll(macList);
			if (axisList.size() == 0) {
				axisList = macList;
			}
			if (axisList.size() == 0) {
				// throw new Exception("没有找到合适的机台,线盘判断有误");
			} else if (axisList.size() == 1) {
				macCode = axisList.get(0);
			} else {
				// 多个机台,查询oee
				String dSql = "";
				for (String mac : axisList) {
					dSql = dSql + "'" + mac + "',";
				}
				sql = "select mac_code from mau_oee where  mac_code in ("
						+ dSql.substring(0, dSql.length() - 1)
						+ ")  order by oee DESC ";
				List<String> oeeList = dao.createSQLQuery(sql).list();
				if (oeeList.size() > 0) {
					macCode = oeeList.get(0);
				} else {
					macCode = axisList.get(0);
				}

			}

			task.setMaccode(macCode);

			// 4.算收线盘
			// 判断 本任务可使用的出线轴
			String sql2 = "select axis_name from mau_axis_mac where mac_code = '"
					+ macCode + "' and axis_type ='收线轴'";
			List<String> outList = dao.createSQLQuery(sql2).list();
			// 本机台能的收线盘 和下任务的防线盘进行比较
			// 5.设置收线盘,需要判断是否是最后一个步骤,最后一个步骤就找最优的如果不是就找下个工序
			if (index != 0 && index == steps.size() - 1) {
				// 最后一步
				// 复绕不计算 收线盘
				if ("fr".equalsIgnoreCase(seq)) {

				} else {
					if (outList.size() == 0) {
						task.setReaxistype(null);
					} else {
						task.setReaxistype(getMauAxisName(outList, diameter,
								length));
					}

				}
			} else {
				// 不是最后一步,判断下一工序
				String nextSeq = seqs.get(index + 1);
				// 如果下一工序是拉丝,则直接去找集绞
				if ("ls".equalsIgnoreCase(nextSeq)) {
					// 如果是拉丝 ,和直接去找集绞
					sql = "select axis_name from mau_axis_mac where mac_code  in (select mac_code from mau_machine where seq_code = 'jj') and axis_type ='放线轴' group by axis_name";
					List<String> canOutList = dao.createSQLQuery(sql).list();
					outList.retainAll(canOutList);
					if (outList.size() == 0) {
						// throw new Exception("判断线盘有误,查看线盘的数据录入");
						// 不设置放线盘
					} else if (outList.size() == 1) {
						task.setReaxistype(outList.get(0));
					} else {
						task.setReaxistype(getMauAxisName(outList, diameter,
								length));
					}
				} else {
					// 不是最后一步
					sql = "select axis_name from mau_axis_mac where mac_code  in (select mac_code from mau_machine where seq_code = '"
							+ nextSeq
							+ "') and axis_type ='放线轴' group by axis_name";
					List<String> canOutList = dao.createSQLQuery(sql).list();
					List<String> reList = outList;
					outList.retainAll(canOutList);
					if (outList.size() == 0) {
						// throw new Exception("判断线盘有误,查看线盘的数据录入");
					} else if (outList.size() == 1) {
						task.setReaxistype(outList.get(0));
					} else {
						task.setReaxistype(getMauAxisName(outList, diameter,
								length));
					}
				}
			}

		}
	}

	/**
	 * 除拉丝 算线盘
	 * 
	 * @param outList
	 * @param diameter
	 * @param length
	 * @return
	 */
	private String getMauAxisName(List<String> outList, float diameter,
			float length) {
		String dSql = "";
		for (String mac : outList) {
			dSql += " axisName = '" + mac + "' or";
		}
		String hql = "from MauAxis where  ("
				+ dSql.substring(0, dSql.length() - 2) + ")";
		List<MauAxis> axisList = dao.createQuery(hql).list();
		Map<Float, String> map = new HashMap<>();
		Float min = 0.0F;
		Float result = 0.0F;
		// 算出线盘的安装长度,与length 比较 比length小最少的 获取
		for (MauAxis mauAxis : axisList) {
			// 内宽
			Integer axisInWidth = mauAxis.getAxisInWidth();
			// 每层线数 N
			int N = (int) (axisInWidth / (diameter * 1.07));
			// 装线缆层数
			int n = (int) (((mauAxis.getExternalDiameter() - mauAxis
					.getInternalDiameter()) / 2 - 50) / diameter);

			float totalLenght = 0;
			float interd = (float) mauAxis.getInternalDiameter().intValue();
			for (int i = 0; i < n; i++) {
				totalLenght = totalLenght + (float) Math.PI
						* (interd + diameter) * N / 1000;
				interd = interd + 2 * diameter;
			}
			map.put(totalLenght, mauAxis.getAxisName());
			Float re = totalLenght - length;
			if (min == 0.0 && re > 0) {
				min = re;
				result = totalLenght;
			}
			if (re < min && re > 0 && min != 0.0) {
				min = re;
				result = totalLenght;
			}
		}
		return map.get(result);
	}

	/**
	 * 拉丝 算线盘 获取最接近的
	 * 
	 * @param macCode
	 * @param axisType
	 * @param diameter
	 * @return
	 */
	public String getMauAxisName(String macCode, String axisType,
			float diameter, float length) {
		Map<Float, String> map = new HashMap<>();
		Float min = 0.0F;
		Float result = 0.0F;
		String sql = "select ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter from Mau_Axis ma , Mau_Axis_Mac mm where  ma.axis_Name = mm.axis_Name and mm.mac_Code ='"
				+ macCode
				+ "' and mm.axis_Type = '"
				+ axisType
				+ "' group by ma.axis_name,ma.external_diameter,ma.internal_diameter,ma.axis_in_width,ma.axis_out_width,ma.center_diameter";
		// List<MauAxis> axisList = dao.createQuery(sql).list();
		List<Object[]> axisList2 = dao.createSQLQuery(sql).list();
		for (Object[] objects : axisList2) {
			String axisName = objects[0].toString();
			// 外盘径
			Integer externalDiameter = (Integer) objects[1];
			// 内盘径
			Integer internalDiameter = (Integer) objects[2];
			// 轴内宽
			Integer axisInWidth = (Integer) objects[3];
			// 轴外宽
			Integer axisOutWidth = (Integer) objects[4];
			// 中心孔直径
			Integer centerDiameter = (Integer) objects[5];
			// 每层线数 N
			int N = (int) (axisInWidth / (diameter * 1.07));
			// 装线缆层数
			int n = (int) (((externalDiameter - internalDiameter) / 2 - 50) / diameter);
			float totalLenght = 0;
			float interd = (float) internalDiameter.intValue();
			for (int i = 0; i < n; i++) {
				totalLenght = totalLenght + (float) Math.PI
						* (interd + diameter) * N / 1000;
				interd = interd + 2 * diameter;
			}

			map.put(totalLenght, axisName);
			Float re = totalLenght - length;
			if (min == 0.0 && re > 0) {
				min = re;
				result = totalLenght;
			}
			if (re < min && re > 0 && min != 0.0) {
				min = re;
				result = totalLenght;
			}
		}

		return map.get(result);
	}

	/**
	 * 设置最后工序的长度以及轴名称
	 * 
	 * @param pc
	 * @param pmt
	 * @param wacs
	 */
	public void setLastPlaMacTaskAxaisParam(PlaCourse pc, PlaMacTask pmt,
			ArrayList<WorkAxisColor> wacs) {
		ArrayList<PlaMacTaskAxisParam> aps = new ArrayList<PlaMacTaskAxisParam>();
		int begin = 0;
		for (int i = 0; i < wacs.size(); i++) {
			for (int j = 0; j < wacs.get(i).axisnum; j++) {
				PlaMacTaskAxisParam ap = new PlaMacTaskAxisParam();
				ap.setWorkcode(pc.getWsCode());
				ap.setSeqcode(pmt.getSeqcode());
				ap.setMaccode(pmt.getMaccode());
				ap.setColor(wacs.get(i).color);
				ap.setLength(wacs.get(i).axislength);
				ap.setAxiscount(1);
				ap.setProduct("成品");
				begin = begin + 1;
				String mcode = String.valueOf(begin);
				for (int h = mcode.length(); h < 4; h++) {
					mcode = String.valueOf(0) + mcode;
				}
				ap.setAxiscode(pc.getWsCode() + "_" + mcode);
				aps.add(ap);
			}
		}
		pmt.setAxisParam(aps);
	}

	/**
	 * 获取出轴号、颜色、每轴长度(不算）
	 * 
	 * @param seq
	 * @param ggxh
	 * @param pc
	 * @return
	 */
	public ArrayList<PlaMacTaskAxisParam> getAxisParam(
			ArrayList<Integer> cores, String seq, String ggxh, PlaCourse pc,
			int step, String macCode, ArrayList<WorkAxisColor> wacs) {
		ArrayList<PlaMacTaskAxisParam> aps = new ArrayList<PlaMacTaskAxisParam>();
		if (seq.equalsIgnoreCase("ls")) {
			String sql = "select axis_num from cra_ls_bom_param where pro_ggxh='"
					+ ggxh + "' and seq_step=" + step;
			int axisCount = 0;
			List<Object> list = dao.createSQLQuery(sql).list();
			for (Object ob : list) {
				if (ob != null) {
					axisCount = (int) ob;
				}
			}

			if (axisCount == 0) {
				if (step < 2) {
					axisCount = cores.get(0);
				} else {
					// 还有拉丝步骤
					axisCount = cores.get(1);
				}

			}

			for (int i = 0; i < axisCount; i++) {
				PlaMacTaskAxisParam ap = new PlaMacTaskAxisParam();
				ap.setWorkcode(pc.getWsCode());
				ap.setMaccode(macCode);
				ap.setSeqcode(seq);
				ap.setStep(step);
				ap.setAxiscode(pc.getWsCode() + step + seq + i);
				aps.add(ap);
			}
		} else if (seq.equalsIgnoreCase("jy")) {
			// 先判读订单颜色的个数
			// 判断订单颜色的个数
			ArrayList<String> colors = new ArrayList<String>();
			String sql = "select  color from pla_course_axis where course_code='"
					+ pc.getWsCode()
					+ "' and  pro_ggxh='"
					+ ggxh
					+ "' group by color ";
			List<Object> list = dao.createSQLQuery(sql).list();
			for (Object ob : list) {
				if (ob != null) {
					colors.add((String) ob);
				}
			}

			if (colors.size() == 1) {
				// 获取该型号线的线芯颜色
				ArrayList<String> jycolors = new ArrayList<String>();
				ArrayList<Integer> axisnums = new ArrayList<Integer>();
				sql = "select color,axis_num from cra_color_bom where pro_ggxh='"
						+ ggxh + "' and seq_code='jy' and seq_step=" + step;
				list.clear();
				list = dao.createSQLQuery(sql).list();
				if (list.size() == 0) {
					jycolors.add(colors.get(0));
					axisnums.add(1);
				}
				for (Object ob : list) {
					if (ob != null) {
						Object[] obj = (Object[]) ob;
						jycolors.add((String) obj[0]);
						axisnums.add((int) obj[1]);
					}
				}

				for (int i = 0; i < jycolors.size(); i++) {
					for (int j = 0; j < axisnums.get(i); j++) {
						PlaMacTaskAxisParam ap = new PlaMacTaskAxisParam();
						ap.setWorkcode(pc.getWsCode());
						ap.setMaccode(macCode);
						ap.setSeqcode(seq);
						ap.setAxiscode(pc.getWsCode() + step + seq + i + j);
						ap.setAxiscount(1);
						ap.setStep(step);
						ap.setColor(jycolors.get(i));
						aps.add(ap);
					}
				}
			}

			else {// 1.订单有不同颜色； 2.订单只有一个颜色

				for (int i = 0; i < colors.size(); i++) {

					PlaMacTaskAxisParam ap = new PlaMacTaskAxisParam();
					ap.setWorkcode(pc.getWsCode());
					ap.setMaccode(macCode);
					ap.setSeqcode(seq);
					ap.setAxiscode(pc.getWsCode() + step + seq + i);
					ap.setAxiscount(1);
					ap.setStep(step);
					ap.setColor(colors.get(i));
					aps.add(ap);
				}
			}
		} else if (seq.equalsIgnoreCase("dj")) {
			// 获取该型号线的线芯颜色
			int axisnum = 0;
			String sql = "select axis_num from cra_color_bom where pro_ggxh='"
					+ ggxh + "' and seq_code='DJ' and seq_step=" + step;

			List<Object> list = dao.createSQLQuery(sql).list();
			for (Object ob : list) {
				if (ob != null) {
					axisnum = (int) ob;
				}
			}

			for (int j = 0; j < axisnum; j++) {
				PlaMacTaskAxisParam ap = new PlaMacTaskAxisParam();
				ap.setWorkcode(pc.getWsCode());
				ap.setMaccode(macCode);
				ap.setSeqcode(seq);
				ap.setAxiscode(pc.getWsCode() + step + seq + j);
				ap.setAxiscount(1);
				ap.setStep(step);
				ap.setColor("");
				aps.add(ap);
			}

		} else {
			PlaMacTaskAxisParam ap = new PlaMacTaskAxisParam();
			ap.setWorkcode(pc.getWsCode());
			ap.setMaccode(macCode);
			ap.setSeqcode(seq);
			ap.setAxiscode(pc.getWsCode() + step + seq);
			ap.setAxiscount(1);
			ap.setStep(step);
			ap.setColor("黑");
			aps.add(ap);
		}
		return aps;

	}

	/**
	 * 获取机台速度
	 * 
	 * @param macCode
	 * @return
	 */
	public void setMacSpeed(ArrayList<PlaMacTask> pmts, ArrayList<String> seqs,
			ArrayList<Integer> steps, String ggxh) throws Exception {
		for (int i = 0; i < pmts.size(); i++) {
			String macCode = pmts.get(i).getMaccode();
			String fAlphabet = macCode.substring(0, 1);
			String seq = pmts.get(i).getSeqcode();
			int step = pmts.get(i).getStep();
			float speed = 0;
			// 首先从mau_machine_employee里查询速度
			float dia = 0.0f;
			float tmpdia = plaMachinePlanManageDAO.getDiameterVaule2(ggxh,
					step, seq);
			dia = (float) (Math.round(tmpdia * 100)) / 100;
			String sql = "";
			if (dia > 0) {
				sql = "select max(ave_speed) from mau_machine_employee where mac_code='"
						+ macCode + "' and diameter=" + dia;
			} else {
				sql = "select max(ave_speed) from mau_machine_employee where mac_code='"
						+ macCode + "'";
			}
			List<Object> list = dao.createSQLQuery(sql).list();
			if (list.size() > 0 && list.get(0) != null) {
				speed = (float) list.get(0);
			} else {
				// 首先从mau_param里查询速度
				sql = "select max(speed) from mau_param where mac_code='"
						+ macCode + "' and diameter=" + dia;
				list = dao.createSQLQuery(sql).list();
				if (list.size() > 0 && list.get(0) != null) {
					speed = (float) list.get(0);
				} else {
					ArrayList<MacSpeedParam> msps = new ArrayList<MacSpeedParam>();
					sql = "Select speed,mater,quotiety,target_diameter  "
							+ "from mau_speed_quotiety where mac_code='"
							+ macCode + "'  order by target_diameter";
					list = dao.createSQLQuery(sql).list();
					for (Object ob : list) {
						MacSpeedParam msp = new MacSpeedParam();
						Object[] obj = (Object[]) ob;
						if (obj[0] != null) {
							msp.speed = (float) obj[0];
						} else {
							msp.speed = 0;
						}
						if (obj[1] != null) {
							msp.mater = (String) obj[1];
						} else {
							msp.mater = "";
						}
						if (obj[2] != null) {
							msp.quotiety = (float) obj[2];
						} else {
							msp.quotiety = 0;
						}
						if (obj[3] != null) {
							msp.diameter = (float) obj[3];
						} else {
							msp.diameter = 0;
						}
						msps.add(msp);
					}

					if (msps.size() == 1 && msps.get(0).speed > 0) {
						speed = msps.get(0).speed;
					} else {
						// 获取机台生产的满速

						if (fAlphabet.equalsIgnoreCase("D")) {
							if (macCode.equalsIgnoreCase("DB")) {
								// 取ls bom 表的芯型
								String shape = "";
								sql = "select al_shape from cra_ls_bom_param "
										+ "where pro_ggxh='" + ggxh
										+ "' and seq_step=" + step;
								list = dao.createSQLQuery(sql).list();
								for (Object ob : list) {
									if (ob != null) {
										shape = (String) ob;
									}
								}
								if (shape.equalsIgnoreCase("圆形")) {
									speed = 800;
								} else {
									speed = 400;
								}
							}
							if (macCode.equalsIgnoreCase("DM")) {
								// 获取标准重量
								float stand_weight = 0;
								sql = "select oee_stand_weight from cra_ls_bom_param "
										+ "where pro_ggxh='"
										+ ggxh
										+ "' and seq_step=" + step;
								list = dao.createSQLQuery(sql).list();
								for (Object ob : list) {
									if (ob != null) {
										stand_weight = (float) ob;
									}
								}

								if (stand_weight > 0) {
									for (int j = 0; j < msps.size(); j++) {
										if (plaMachinePlanManageDAO
												.getMaterValue(ggxh, seq, step)
												.equalsIgnoreCase(
														msps.get(j).mater)) {
											speed = (int) (msps.get(j).quotiety / stand_weight);
										}
									}
								}

							}
							if (macCode.equalsIgnoreCase("DE")) {
								// 获取目标线径
								dia = plaMachinePlanManageDAO.getDiameterVaule(
										ggxh, steps, seqs, i);
								if (dia == 0) {
									throw new Exception(ggxh + "对应的工序" + seq
											+ ",bom表缺少直径值");
								}
								for (int j = 0; j < msps.size(); j++) {
									if (dia == msps.get(i).diameter) {
										speed = msps.get(i).speed;
									}
								}
							}

						}

						if (fAlphabet.equalsIgnoreCase("S")
								|| fAlphabet.equalsIgnoreCase("C")
								|| fAlphabet.equalsIgnoreCase("T")) {
							// 获取绞距
							float lay = plaMachinePlanManageDAO.getLayValue(
									ggxh, seq, step);
							if (lay == 0) {
								throw new Exception(ggxh + "对应工序" + seq
										+ "bom表缺少绞距值");
							}
							if (macCode.equalsIgnoreCase("SA")
									|| macCode.equalsIgnoreCase("SC")) {
								float d = 0;
								sql = "select fourth_fdiameter, third_fdiameter, twice_fdiameter, "
										+ "once_fdiameter from cra_jx_bom_param where pro_ggxh='"
										+ ggxh + "' and seq_step=" + step;
								list = dao.createSQLQuery(sql).list();

								for (Object ob : list) {
									Object[] obj = (Object[]) ob;
									for (int j = 0; j < obj.length; j++) {
										String tmp = (String) obj[j];
										if (tmp != null && tmp.length() > 1) {
											String[] dia1 = tmp.split("x");
											if (dia1.length > 1) {
												float d1 = Float
														.valueOf(dia1[0]);
												float d2 = Float
														.valueOf(dia1[1]);
												if (d1 > d2) {
													d = d2;
												} else {
													d = d1;
												}
											} else {
												d = Float.valueOf(dia1[0]);
											}
											break;
										} else {
											continue;
										}
									}
								}

								if (msps.size() > 1) {
									if (d < msps.get(0).diameter) { // 直径<判断值
										speed = msps.get(0).quotiety * lay;
									} else {
										speed = msps.get(1).quotiety * lay;
									}
								}
							}

							else if (macCode.equalsIgnoreCase("CC")
									|| macCode.equalsIgnoreCase("CB")) {
								String nextseq = pmts.get(i + 1).getSeqcode();
								int nextstep = pmts.get(i + 1).getStep();
								if (nextseq.equalsIgnoreCase("bd")
										&& step == nextstep) {
									String mater = "有包带";
									for (int j = 0; j < msps.size(); j++) {
										if (mater
												.equalsIgnoreCase(msps.get(j).mater)) {
											speed = msps.get(j).quotiety * lay;
										}
									}
								} else {
									String mater = "无包带";
									for (int j = 0; j < msps.size(); j++) {
										if (mater
												.equalsIgnoreCase(msps.get(j).mater)) {
											speed = msps.get(j).quotiety * lay;
										}
									}
								}

							} else {
								speed = msps.get(0).quotiety * lay;

							}

						}
						if (fAlphabet.equalsIgnoreCase("V")) {
							float once_pitch = 0;
							sql = "select once_pitch from cra_bd_bom_param "
									+ "where pro_ggxh='" + ggxh
									+ "' and seq_step=" + step;
							list.clear();
							list = dao.createSQLQuery(sql).list();
							for (Object ob : list) {
								if (ob != null) {
									once_pitch = (float) ob;
								}
							}
							if (once_pitch == 0) {
								throw new Exception(ggxh + "对应工序bd,bom表缺少绞距值");
							}
							speed = msps.get(0).quotiety * once_pitch;
						}
						if (fAlphabet.equalsIgnoreCase("E")) {
							String mater = "";
							float use_per_kilometer = 0;
							sql = "SELECT b.material_type ,a.use_per_kilometer "
									+ "FROM cra_"
									+ seq
									+ "_bom_param a, store_material_basic_info b "
									+ "where a. pro_ggxh='"
									+ ggxh
									+ "' and a.seq_step="
									+ step
									+ " and a.mater = b.mater_ggxh";
							list.clear();
							list = dao.createSQLQuery(sql).list();
							for (Object ob : list) {
								if (ob != null) {
									Object[] obj = (Object[]) ob;
									if (obj[0] != null) {
										mater = (String) obj[0];
									} else {
										throw new Exception(
												ggxh
														+ "对应工序"
														+ seq
														+ ",bom表缺少材料信息或者该材料在基础表中没有找到对应的类型");
									}
									if (obj[1] != null) {
										use_per_kilometer = (float) obj[1];
									} else {
										throw new Exception(ggxh + "对应工序" + seq
												+ ",bom表缺少材料的KG/KM值");
									}
								}
							}

							if (use_per_kilometer > 0) {
								for (int j = 0; j < msps.size(); j++) {
									if (mater
											.equalsIgnoreCase(msps.get(j).mater)) {
										speed = msps.get(j).quotiety
												/ (use_per_kilometer / 1000);
									}
								}
							} else {
								throw new Exception(ggxh + "对应工序" + seq
										+ ",bom表的材料KG/KM值不能为0");
							}

						}

					}
				}
			}

			if (speed > 0) {
				pmts.get(i).setSpeed(speed);
			} else {
				throw new Exception("机台-" + pmts.get(i).getMaccode()
						+ "没有速度参数，请检查机台的基础数据");
			}
		}
	}

	/**
	 * 根据该机台到生产速度和出抽长度得到生产使用时间 计算轴长度才调用
	 * 
	 * @param axisParam
	 * @param macCode
	 * @return
	 */
	public boolean setUseMinute(ArrayList<PlaMacTask> plaMacTasks, String ggxh) {
		boolean bool = true;
		float useMinute = 0;
		for (int i = 0; i < plaMacTasks.size(); i++) {
			String macCode = plaMacTasks.get(i).getMaccode();
			if (i > 0
					&& plaMacTasks.get(i).getStep() == plaMacTasks.get(i - 1)
							.getStep()) {
				plaMacTasks.get(i).setUseminute((int) useMinute);
			} else {
				// 获取AxisParam
				List<PlaMacTaskAxisParam> AxisParams = plaMacTasks.get(i)
						.getAxisParam();
				// 获取该工序的总长度
				float lengths = 0;
				for (int j = 0; j < AxisParams.size(); j++) {
					lengths = lengths + AxisParams.get(j).getLength()
							* AxisParams.get(j).getAxiscount();
				}
				if (plaMacTasks.get(i).getMaccode().equalsIgnoreCase("DE")) {
					// 一次拉8根
					lengths = lengths / 8;
				}

				// 取oee值开机率
				String sql = "select max(rate_start) from mau_oee where mac_code = '"
						+ macCode + "'";
				List<Object> list = dao.createSQLQuery(sql).list();
				float rate_start = 1.0f;
				for (Object ob : list) {
					if (ob != null)
						rate_start = (float) ob;
				}
				useMinute = lengths / plaMacTasks.get(i).getSpeed()
						/ rate_start;
				plaMacTasks.get(i).setUseminute((int) useMinute);
			}
		}

		return bool;
	}

	/**
	 * 设置每个工序的出轴数
	 * 
	 * @param pmt
	 */
	public void setAxisNumber(ArrayList<PlaMacTask> pmt) {
		for (int i = 0; i < pmt.size(); i++) {
			pmt.get(i).setAxiscount(pmt.get(i).getAxisParam().size());
		}
	}

	/**
	 * 计算每个工序的轴长度-反推
	 * 
	 * @param seq
	 * @param ggxh
	 * @return
	 */
	public void setAxisLength(Map<String, Float> maxLength, PlaCourse pc,
			ArrayList<PlaMacTask> plaMacTask, String ggxh, boolean bool)
			throws Exception {
		// 从最后一步开始往前推算
		float dLength = 0;
		float jjLength = 0;
		for (int i = plaMacTask.size() - 1; i >= 0; i--) {
			List<PlaMacTaskAxisParam> aps = plaMacTask.get(i).getAxisParam();
			String seq = plaMacTask.get(i).getSeqcode();
			int step = plaMacTask.get(i).getStep();
			float debugLength = 0.0f;
			// bool = true 要加长调试长度
			if (bool) {
				String macCode = plaMacTask.get(i).getMaccode();
				debugLength = plaMachinePlanManageDAO.getDebugLenght(ggxh, seq,
						step, macCode);
			}
			if (i == plaMacTask.size() - 1) {
				// 最后一步已通过颜色设置将成品的颜色，轴名称分配了，计算总长度 + 加上调试长度即为前工序的出产长度
				float tmplength = 0;
				for (int j = 0; j < aps.size(); j++) {
					tmplength = tmplength + aps.get(j).getLength();
				}
				dLength = tmplength + debugLength;
			} else {
				dLength = setSeqLengths(maxLength, dLength, seq, step, aps,
						ggxh, plaMacTask.get(i + 1), debugLength, pc, jjLength);
				if (seq.equalsIgnoreCase("jj")) {
					jjLength = dLength;
				}
			}
		}

	}

	public float setSeqLengths(Map<String, Float> maxLength, float dLength,
			String seq, int step, List<PlaMacTaskAxisParam> aps, String ggxh,
			PlaMacTask pmt, float debug_length, PlaCourse pc, float jjLength)
			throws Exception {
		float seqLenght = 0;
		if (seq.equalsIgnoreCase("jj")) {
			// 获取该工序的绞距
			float d = 0;
			float h = 0;
			String sql = "select normal_diameter,lay from cra_jj_bom_param where pro_ggxh ='"
					+ ggxh + "'" + " and seq_step =" + step;
			List<Object> list = dao.createSQLQuery(sql).list();
			for (Object ob : list) {
				Object[] obs = (Object[]) ob;
				if (obs[0] != null) {
					d = (float) obs[0];
				}
				if (obs[1] != null) {
					h = (float) obs[1];
				}
			}
			if (d == 0 || h == 0) {
				throw new Exception(ggxh + "集绞bom 缺少绞距或正常直径值");
			}
			float needLength = CaluLength.getFactLength(dLength, h, d, 0);
			aps.get(0).setLength(needLength);
			seqLenght = needLength + debug_length;
		} else if (seq.equalsIgnoreCase("jx")) {
			// 如果上一步还是绞线，这步就不计算，只加上调试长度
			if (pmt.getSeqcode().equalsIgnoreCase("jx")) {
				for (int i = 0; i < aps.size(); i++) {
					aps.get(i).setLength(dLength);
				}
				seqLenght = dLength + debug_length;
			} else {
				float d = 0;
				String sql = "select fourth_fdiameter, third_fdiameter, twice_fdiameter, "
						+ "once_fdiameter from cra_jx_bom_param where pro_ggxh='"
						+ ggxh + "' and seq_step=" + step;
				List<Object> list = dao.createSQLQuery(sql).list();

				for (Object ob : list) {
					Object[] obj = (Object[]) ob;
					for (int j = 0; j < obj.length; j++) {
						String tmp = (String) obj[j];
						if (tmp != null && tmp.length() > 1) {
							String[] dia = tmp.split("x");
							if (dia.length > 1) {
								float d1 = Float.valueOf(dia[0]);
								float d2 = Float.valueOf(dia[1]);
								if (d1 > d2) {
									d = d2;
								} else {
									d = d1;
								}
							} else {
								d = Float.valueOf(dia[0]);
							}
							break;
						} else {
							continue;
						}
					}
				}
				sql = "select fourth_lay,third_lay,"
						+ " twice_lay,once_lay ,compression_ratio "
						+ " from cra_jx_bom_param where pro_ggxh ='" + ggxh
						+ "'" + " and seq_step =" + step;
				ArrayList<Float> hs = new ArrayList<Float>();
				float compress = 0;
				list.clear();
				list = dao.createSQLQuery(sql).list();
				for (int i = 0; i < list.size(); i++) {
					Object[] obs = (Object[]) list.get(i);
					for (int j = 0; j < obs.length; j++) {
						if (obs[j] == null) {
							obs[j] = (float) 0;
						}
						if (j < obs.length - 1) {
							hs.add((float) obs[j]);
						} else {
							compress = (float) obs[obs.length - 1];
						}
					}

				}

				boolean bool = false;
				for (int i = 0; i < hs.size(); i++) {
					if (hs.get(i) == 0) {
						continue;
					}
					if (hs.get(i) > 0) {
						// 如果大于0，取最大绞距计算
						bool = true;
						float needLength = CaluLength.getFactLength(dLength,
								hs.get(i), d, compress);
						aps.get(0).setLength(needLength);
						seqLenght = needLength + debug_length;
						break;
					}
				}
				if (!bool) {
					throw new Exception(ggxh + "绝缘bom  缺少绞距值");
				}

			}

		} else if (seq.equalsIgnoreCase("ls")) {
			for (int i = 0; i < aps.size(); i++) {
				aps.get(i).setLength(dLength);
			}
			seqLenght = dLength;
		} else if (seq.equalsIgnoreCase("jy")) {
			// 判断订单颜色的个数
			ArrayList<String> colors = new ArrayList<String>();
			String sql = "select  color from pla_course_axis where course_code='"
					+ pc.getWsCode()
					+ "' and  pro_ggxh='"
					+ ggxh
					+ "' group by color ";
			List<Object> list = dao.createSQLQuery(sql).list();
			for (Object ob : list) {
				if (ob != null) {
					colors.add((String) ob);
				}
			}
			if (colors.size() > 1) {// 订单有不同颜色,那么绝缘要知道每个颜色的总长度和订单长度的比

				ArrayList<String> jycolors = new ArrayList<String>();
				ArrayList<Integer> axis_lengths = new ArrayList<Integer>();
				int total = 0;
				sql = "select color, sum(axis_num*axis_length) from pla_course_axis"
						+ " where pro_ggxh='" + ggxh + "' group by color";
				list.clear();
				list = dao.createSQLQuery(sql).list();
				for (Object ob : list) {
					if (ob != null) {
						Object[] obj = (Object[]) ob;
						if (obj[0] != null) {
							jycolors.add((String) obj[0]);
						}
						if (obj[1] != null) {
							axis_lengths.add(new BigInteger(obj[1].toString())
									.intValue());
							total = total
									+ new BigInteger(obj[1].toString())
											.intValue();
						}
					}
				}

				for (int i = 0; i < aps.size(); i++) {
					String color = aps.get(i).getColor();
					for (int j = 0; j < jycolors.size(); j++) {
						if (color.equalsIgnoreCase(jycolors.get(j))) {
							aps.get(i).setLength(axis_lengths.get(j));
						}
					}
				}
				seqLenght = total + debug_length;
			} else {// 订单只有一种颜色，jy有多种颜色，那么颜色是根据芯线颜色定，bom里要设定，每种颜色长度一样
				float total = 0;
				if (aps.size() == 1) {

					total = dLength;
					aps.get(0).setLength(total);
				} else {

					for (int i = 0; i < aps.size(); i++) {
						aps.get(i).setLength(jjLength);
						total = total + aps.get(i).getAxiscount() * jjLength;

					}
				}

				seqLenght = total + debug_length;

			}

		} else if (seq.equalsIgnoreCase("dj")) {
			// 获取该工序的绞距
			float d = 0;
			float h = 0;

			String sql = "select normal_diameter,lay from cra_dj_bom_param where pro_ggxh ='"
					+ ggxh + "'" + " and seq_step =" + step;
			List<Object> list = dao.createSQLQuery(sql).list();
			for (Object ob : list) {
				if (ob != null) {
					Object[] obs = (Object[]) ob;
					if (obs[0] != null) {
						d = (float) obs[0];
					}
					if (obs[1] != null) {
						h = (float) obs[1];
					}
				}
			}
			if (d == 0 || h == 0) {
				throw new Exception(ggxh + "对绞bom  缺少绞距或正常直径值");
			}

			float needLength = CaluLength.getFactLength(dLength, h, d, 0);
			for (int i = 0; i < aps.size(); i++) {
				aps.get(i).setLength(dLength);
			}
			seqLenght = needLength + debug_length;
		} else {

			for (int i = 0; i < aps.size(); i++) {
				aps.get(i).setLength(dLength);
			}
			seqLenght = dLength + debug_length;
		}
		// for(int i = 0 ; i<aps.size();i++){
		// float len = aps.get(i).getLength();
		// if(len>0){
		// //
		// String key = seq+";"+String.valueOf(step);
		// Object value = maxLength.get(key);
		// if(value!=null){
		// float maxLen = (float) value;
		// if(maxLen<len){
		// partAxis( seq, step , pc.getWsCode(), pmt.getMaccode(),
		// plaMachinePlanManageDAO.getDiameterVaule2(ggxh, step, seq));
		// //throw new
		// Exception(seq+";"+String.valueOf(len)+";"+String.valueOf(maxLen));
		// }
		// }
		// //
		// }
		// }

		return seqLenght;
	}

	/**
	 * 计算每个工序的开始时间和结束时间
	 * 
	 * @param plaMacTask
	 * @return
	 * @throws ParseException
	 */
	public boolean setTime(ArrayList<PlaMacTask> plaMacTasks)
			throws ParseException {
		boolean bool = true;
		// 开始排
		for (int i = 0; i < plaMacTasks.size(); i++) {
			if (i == 0) {// 如果是第一步
				setFirstSeqTime(plaMacTasks, i);
			} else {
				// 第二步开始，如果该工序的step= before step 说明是同机台完成，时间与上工序一致
				if (i > 0
						&& plaMacTasks.get(i).getStep() == plaMacTasks.get(
								i - 1).getStep()) {
					plaMacTasks.get(i).setPstime(
							plaMacTasks.get(i - 1).getPstime());
					plaMacTasks.get(i).setPdtime(
							plaMacTasks.get(i - 1).getPdtime());
				} else {
					// 右出现ls，按照首步骤处理
					if (plaMacTasks.get(i).getSeqcode().equalsIgnoreCase("ls")) {
						setFirstSeqTime(plaMacTasks, i);
					} else {
						setBeginEnd((Timestamp) plaMacTasks.get(i - 1)
								.getPdtime(), plaMacTasks, i);
					}

				}
			}

		}
		// 取机台到最后到成时间

		return bool;
	}

	/**
	 * 设置第二步开始工序的开始和结束时间。
	 * 
	 * @param lastSeqEndTime
	 * @param pmt
	 */
	public void setBeginEnd(Timestamp lastSeqEndTime,
			ArrayList<PlaMacTask> pmts, int index) {
		// 找出前工序相同机台的计划完成时间
		String macCode = pmts.get(index).getMaccode();
		Timestamp sameMacPdtime = null;
		for (int i = index - 1; i >= 0; i--) {
			if (pmts.get(i).getMaccode().equalsIgnoreCase(macCode)) {
				sameMacPdtime = pmts.get(i).getPdtime();
				break;
			}
		}
		// 从数据库中找出该机台最大的完成时间
		Timestamp sTime = getMaxDate(pmts.get(index));
		// 先比较sameMacPdtim 与 sTime的大小，赋值给一个临时tempTime
		Timestamp tempTime = null;
		if (sameMacPdtime != null) {
			tempTime = TimeUtil.compTime(sameMacPdtime, sTime);
		} else {
			tempTime = sTime;
		}
		// tempTime 再与上工序的结束时间比较
		int useMin = pmts.get(index).getUseminute();
		Calendar ca = Calendar.getInstance();
		ca.setTime(tempTime);
		// 比较的时间
		Calendar compca = Calendar.getInstance();
		compca.setTime(lastSeqEndTime);
		int comp = ca.compareTo(compca);
		if (comp < 0) {// 上工序的结束时间>该机台可排的最大时间 该工序的开始时间为上工序的结束时间
			pmts.get(index).setPstime(lastSeqEndTime);
			compca.add(Calendar.MINUTE, useMin);
			pmts.get(index).setPdtime(TimeUtil.calendarToTimestamp(compca));
		} else {
			pmts.get(index).setPstime(TimeUtil.calendarToTimestamp(ca));
			ca.add(Calendar.MINUTE, useMin);
			pmts.get(index).setPdtime(TimeUtil.calendarToTimestamp(ca));
		}

	}

	/**
	 * 设置第一道工序的开始和结束时间
	 * 
	 * @param pmt
	 */
	public void setFirstSeqTime(ArrayList<PlaMacTask> pmts, int index) {
		// 通过开始时间和工序所需时间计算结束时间
		PlaMacTask pmt = pmts.get(index);
		String macCode = pmts.get(index).getMaccode();
		Timestamp tempTime = null;
		if (index != 0 && pmts.get(0).getMaccode().equalsIgnoreCase(macCode)) {
			tempTime = pmts.get(0).getPdtime();
		}

		int useMin = pmt.getUseminute();
		// 获取机台的计划最大时间
		Timestamp sTime = getMaxDate(pmt);
		if (tempTime != null) {
			sTime = TimeUtil.compTime(sTime, tempTime);
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(sTime);
		pmt.setPstime(sTime);
		ca.add(Calendar.MINUTE, useMin);
		pmt.setPdtime(TimeUtil.calendarToTimestamp(ca));

	}

	/**
	 * 获取该机台的最大的结束时间
	 * 
	 * @param pmt
	 * @return
	 */
	public Timestamp getMaxDate(PlaMacTask pmt) {
		String macCode = pmt.getMaccode();
		// 获取当前系统时间
		Timestamp currerTime = new Timestamp(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		String compDate = calendar.get(GregorianCalendar.YEAR) + "-"
				+ (calendar.get(GregorianCalendar.MONTH) + 1) + "-"
				+ calendar.get(GregorianCalendar.DAY_OF_MONTH) + " "
				+ "00:00:01";

		String compDate0 = calendar.get(GregorianCalendar.YEAR) + "-"
				+ (calendar.get(GregorianCalendar.MONTH) + 1) + "-"
				+ calendar.get(GregorianCalendar.DAY_OF_MONTH) + " "
				+ "8:00:00";
		String compDate1 = calendar.get(GregorianCalendar.YEAR) + "-"
				+ (calendar.get(GregorianCalendar.MONTH) + 1) + "-"
				+ calendar.get(GregorianCalendar.DAY_OF_MONTH) + " "
				+ "20:00:00";
		String compDate2 = calendar.get(GregorianCalendar.YEAR) + "-"
				+ (calendar.get(GregorianCalendar.MONTH) + 1) + "-"
				+ calendar.get(GregorianCalendar.DAY_OF_MONTH) + " "
				+ "23:59:59";
		Calendar tomorrow = Calendar.getInstance();
		int day = tomorrow.get(Calendar.DATE);
		tomorrow.set(Calendar.DATE, day + 1);
		String compDate3 = tomorrow.get(GregorianCalendar.YEAR) + "-"
				+ (tomorrow.get(GregorianCalendar.MONTH) + 1) + "-"
				+ tomorrow.get(GregorianCalendar.DAY_OF_MONTH) + " "
				+ "8:00:00";
		// 当天00：00：00
		Timestamp comperTime = TimeUtil.stringToTimestamp(compDate);
		// 获取早上8点时间值
		Timestamp comperTime0 = TimeUtil.stringToTimestamp(compDate0);
		// 获取当前晚上20：00时间值
		Timestamp comperTime1 = TimeUtil.stringToTimestamp(compDate1);
		// 获取晚上23：59：59的时间值
		Timestamp comperTime2 = TimeUtil.stringToTimestamp(compDate2);
		// 获取第二天早上8：00的时间值
		Timestamp comperTime3 = TimeUtil.stringToTimestamp(compDate3);
		/**
		 * 如果当前时间小于晚上20：00，则20：00与机台最后使用时间比较
		 */
		String sql = "";

		if (currerTime.compareTo(comperTime) == 1
				&& currerTime.compareTo(comperTime0) == -1) {
			sql = "select   coalesce( to_char(max(pdtime),'YYYY-MM-DD HH24:MI:SS'), to_char(current_date,'YYYY-MM-DD')||' 08:00:00')"
					+ " from pla_mac_task where maccode='" + macCode + "'";
		}
		if (currerTime.compareTo(comperTime0) == 1
				&& currerTime.compareTo(comperTime1) == -1) {
			sql = "select   coalesce( to_char(max(pdtime),'YYYY-MM-DD HH24:MI:SS'), to_char(current_date,'YYYY-MM-DD')||' 20:00:00')"
					+ " from pla_mac_task where maccode='" + macCode + "'";
		}
		if (currerTime.compareTo(comperTime1) == 1
				&& currerTime.compareTo(comperTime2) == -1) {
			sql = "select   coalesce( to_char(max(pdtime),'YYYY-MM-DD HH24:MI:SS'), to_char(current_date+1,'YYYY-MM-DD')||' 08:00:00')"
					+ " from pla_mac_task where maccode='" + macCode + "'";
		}

		String maxTime = "";

		// 最后时间作为此工作单的开始时间
		List<Object> list = dao.createSQLQuery(sql).list();
		for (Object ob : list) {
			if (ob != null) {
				maxTime = (String) ob;
			}
		}
		Timestamp reTime = TimeUtil.stringToTimestamp(maxTime);
		if ((reTime.after(comperTime) && reTime.before(comperTime0))
				|| (reTime.compareTo(comperTime0) == 0)) {
			reTime = comperTime0;
		} else if ((reTime.compareTo(comperTime1) == 0)
				|| (reTime.after(comperTime0) && reTime.before(comperTime1))) {
			reTime = comperTime1;
		} else if (reTime.compareTo(comperTime3) == 0
				|| (reTime.after(comperTime1) && reTime.before(comperTime3))) {
			reTime = comperTime3;
		}

		return reTime;
	}

	public void setPlaMacTaskMateril(ArrayList<PlaMacTask> pmt, String ggxh)
			throws Exception {
		for (int i = 0; i < pmt.size(); i++) {
			ArrayList<PlaMacTaskMateril> pmtms = new ArrayList<PlaMacTaskMateril>();
			if (pmt.get(i).getSeqcode().equalsIgnoreCase("fr")
					|| pmt.get(i).getSeqcode().equalsIgnoreCase("jx")
					|| pmt.get(i).getSeqcode().equalsIgnoreCase("yz")) {
				continue;
			} else {
				if (i > 1
						&& pmt.get(i).getSeqcode() == pmt.get(i - 1)
								.getSeqcode()) {
					continue;
				}
				String sql = "select mater,use_per_kilometer from cra_"
						+ pmt.get(i).getSeqcode()
						+ "_bom_param where pro_ggxh='" + ggxh
						+ "' and seq_step=" + pmt.get(i).getStep();
				String mater = "";
				float use_per_kilometer = 0;
				List<Object> list = dao.createSQLQuery(sql).list();
				for (Object ob : list) {
					if (ob != null) {
						Object[] obj = (Object[]) ob;
						if (obj[0] != null) {
							mater = (String) obj[0];
							PlaMacTaskMateril pmtm = new PlaMacTaskMateril();
							if (obj[1] == null) {
								throw new Exception(ggxh + "对应的cra_"
										+ pmt.get(i).getSeqcode()
										+ "_bom_param表缺少KG/KM");
							}
							if (obj[1] != null) {
								use_per_kilometer = (float) obj[1];
							}
							ArrayList<PlaMacTaskAxisParam> aps = pmt.get(i)
									.getAxisParam();
							float length = 0;
							for (int j = 0; j < aps.size(); j++) {
								length = length + aps.get(j).getLength();
							}
							pmtm.setMaccode(pmt.get(i).getMaccode());
							pmtm.setWorkcode(pmt.get(i).getWorkcode());
							pmtm.setSeqcode(pmt.get(i).getSeqcode());
							pmtm.setMateril(mater);
							pmtm.setMatecount(use_per_kilometer * length / 1000);
							pmtm.setPtime(pmt.get(i).getPstime());
							pmtm.setState("未领取");

							pmtm.setType("领料出库");
							pmtms.add(pmtm);
						}
					}
				}
			}
			pmt.get(i).setPmtms(pmtms);
		}

	}

	public void partAxis(String seq, int step, String workcode, String maccode,
			float diameter) {
		String sql = "select max(axis_length) from pla_course_axis where course_code='"
				+ workcode + "'";
		List<Integer> list = dao.createSQLQuery(sql).list();
		int maxAxisLen = list.get(0).intValue();
		Map<Float, String> map = new HashMap<>();
		ArrayList<String> typelen = plaMachinePlanManageDAO.getPeAxisLength(
				diameter, true);
		List<Float> lens = new ArrayList<Float>();
		for (int i = 0; i < typelen.size(); i++) {
			String[] tmps = typelen.get(i).split("=");
			lens.add(Float.valueOf(tmps[1]));
			map.put(Float.valueOf(tmps[1]), tmps[0]);
		}
		Collections.sort(lens);
		String axisType = "";
		for (int i = 0; i < lens.size(); i++) {
			if (maxAxisLen < lens.get(i)) {
				axisType = map.get(lens.get(i));
				break;
			}
		}
		if (seq.equalsIgnoreCase("jj")) {

		}

	}

	public static void main(String[] args) {
		List<Float> list = new ArrayList<Float>();
		list.add(12.00f);
		list.add(2.33f);
		list.add(27.22f);
		Collections.sort(list);
		System.out.println(list);

		// float d1 = 2.39f;
		// float d2 = 2.390f;
		// System.out.println(d1==d2);
		//
		// System.out.println(String.valueOf(Math.sqrt(240/Math.PI)*2));
		// String mcode = String.valueOf(20);
		// for (int h = mcode.length(); h < 4; h++) {
		// mcode = String.valueOf(0) + mcode;
		// }
		// System.out.println(mcode);
		// double length = 0;
		// length = (1202 * 100 / 392)
		// * Math.sqrt((Math.PI * 20.2) * (Math.PI * 20.2) + 392 * 392);
		// System.out.println(length / 100);
		// System.out.println((int) length / 100);

		// Date date = null;
		// try {
		// date = (new SimpleDateFormat("yyyy-MM-dd H:m"))
		// .parse("2017-10-26 20:15");
		// } catch (ParseException e) {
		// // Auto-generated catch block
		// e.printStackTrace();
		// }
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(date);
		// String str = new SimpleDateFormat("yyyy-MM-dd H:m").format(
		// cal.getTime()).toString();
		// System.out.println(str);
		//
		// cal.add(Calendar.MINUTE, 66);
		// System.out.println(new SimpleDateFormat("yyyy-MM-dd H:m").format(
		// cal.getTime()).toString());
		// int ut = 50;
		// cal.add(Calendar.MINUTE, -ut);
		// Date date1 = cal.getTime();
		//
		// System.out.println((new SimpleDateFormat("yyyy-MM-dd H:m"))
		// .format(date));
		// System.out.println((new SimpleDateFormat("yyyy-MM-dd H:m"))
		// .format(date1));

	}

	class WorkAxisColor {
		public String color;
		public int axisnum;
		public int axislength;

		public WorkAxisColor() {
			color = "";
			axisnum = 0;
			axislength = 0;
		}
	}

	class MacSpeedParam {
		public String mater;
		public float quotiety;
		public float speed;
		public float diameter;

		public MacSpeedParam() {
			mater = "";
			quotiety = 0;
			speed = 0;
			diameter = 0;
		}
	}

	public Page getListPage(Page page, String param) {
		Map<String, String> map = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());
		String hql = "from PlaCourse where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null && map.size() > 0) {
			if (map.get("planFlag") != null && !"".equals(map.get("planFlag"))) {
				sb.append(" and planFlag ='" + map.get("planFlag") + "' ");
			}
			if (map.get("orderCode") != null
					&& !"".equals(map.get("orderCode"))) {
				sb.append(" and orderCode like'%" + map.get("orderCode")
						+ "%' ");
			}
			if (map.get("courseCode") != null
					&& !"".equals(map.get("courseCode"))) {
				sb.append(" and wsCode like'%" + map.get("courseCode") + "%' ");
			}
			if (map.get("proGgxh") != null && !"".equals(map.get("proGgxh"))) {
				sb.append(" and proGgxh like '%" + map.get("proGgxh") + "%' ");
			}
			// 计划时间段
			if (map.get("psstart") != null && !"".equals(map.get("psstart"))) {
				sb.append(" and ws_pstime > '" + map.get("psstart") + "' ");
			}
			if (map.get("psend") != null && !"".equals(map.get("psend"))) {
				sb.append(" and ws_pstime < '" + map.get("psend") + "' ");
			}

		}
		sb.append(" order by planFlag ,ws_pstime,demandDate ");
		Page pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(),
				page.getPagesize());
		return pageQuery;
	}

	/**
	 * 
	 * @Description:echart展示工单完成情况
	 * @param courseCode
	 * @return
	 */
	public EchartsVo getRateEchart(String courseCode) {
		EchartsVo vo = new EchartsVo();
		vo.setTitle("工单:" + courseCode + "进度情况");
		List<String> legends = new ArrayList<>();
		List<String> xAxis = new ArrayList<>();
		Map<String, EchartSeriesVo> seriesData = new HashMap<>();
		List<PlaMacTask> list = this.getRate(courseCode);
		EchartSeriesVo es = new EchartSeriesVo();
		List<String> data = new ArrayList<>();
		es.setType("left");
		legends.add("机台进度");
		for (PlaMacTask plaMacTask : list) {
			String workcode = plaMacTask.getWorkcode();
			String maccode = plaMacTask.getMaccode();
			String seqcode = plaMacTask.getSeqcode();
			float schedule = plaMacTask.getSchedule() == null ? 0.0F
					: plaMacTask.getSchedule();
			xAxis.add(maccode);
			data.add(String.valueOf(schedule));
		}
		es.setData(data);
		seriesData.put("机台进度", es);
		vo.setLegends(legends);
		vo.setSeriesData(seriesData);
		vo.setxAxis(xAxis);
		return vo;
	}

	/**
	 * 
	 * @Description:获得当日的工单的完成情况
	 * @return
	 */
	public EchartsVo getCourseRateEchart() {
		String today = DateUtil.format(new Date(), "yyyy-MM-dd");
		String start = today + " 00:00:00";
		String end = today + " 23:59:59";
		String hql = "from PlaCourse where ws_pstime > '" + start
				+ "'and ws_pstime < '" + end + "'";
		List<PlaCourse> list = dao.createQuery(hql).list();
		EchartsVo vo = new EchartsVo();
		List<String> legends = new ArrayList<>();
		legends.add("完成量");
		List<String> xAxis = new ArrayList<>();
		List<String> data = new ArrayList<>();// 具体的值
		for (PlaCourse plaCourse : list) {
			xAxis.add(plaCourse.getWsCode());
			data.add(plaCourse.getWsSchedule().toString());
		}
		EchartSeriesVo esVo = new EchartSeriesVo();
		esVo.setType("left");
		esVo.setData(data);
		Map<String, EchartSeriesVo> map = new HashMap<>();
		map.put(legends.get(0), esVo);
		vo.setTitle("今日工单完成情况");
		vo.setxAxis(xAxis);
		vo.setLegends(legends);
		vo.setSeriesData(map);
		return vo;
	}

	private List<PlaMacTask> getRate(String courseCode) {
		String hql = "from  PlaMacTask where workcode = '" + courseCode
				+ "' order by step";
		List<PlaMacTask> list = dao.createQuery(hql).list();
		return list;
	}

	/**
	 * 
	 * @Description:在单系统
	 * @param param
	 * @return
	 */
	public EchartsVo getInSystemEchartVo(String param) {
		Map<String, String> map = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());
		String hql = "from PlaCourse where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		// 默认三天前
		c.add(Calendar.DAY_OF_YEAR, -3);
		Date time = c.getTime();
		String psStart = DateUtil.format(time, "yyyy-MM-dd");
		// String psStart = "";
		String psEnd = "现在";
		if (map != null && map.size() > 0) {
			if (map.get("orderCode") != null
					&& !"".equals(map.get("orderCode"))) {
				sb.append(" and orderCode like'%" + map.get("orderCode")
						+ "%' ");
			}
			if (map.get("courseCode") != null
					&& !"".equals(map.get("courseCode"))) {
				sb.append(" and wsCode like'%" + map.get("courseCode") + "%' ");
			}
			if (map.get("proGgxh") != null && !"".equals(map.get("proGgxh"))) {
				sb.append(" and proGgxh like '%" + map.get("proGgxh") + "%' ");
			}
			if (map.get("planFlag") != null && !"".equals(map.get("planFlag"))) {
				sb.append(" and planFlag = '" + map.get("planFlag") + "' ");
			}
			// 计划时间段
			if (map.get("psstart") != null && !"".equals(map.get("psstart"))) {
				sb.append(" and ws_pstime > '" + map.get("psstart") + "'");
				psStart = map.get("psstart");
			} else {
				sb.append(" and ws_pstime >'" + psStart + "'");
			}
			if (map.get("psend") != null && !"".equals(map.get("psend"))) {
				sb.append(" and ws_pstime < '" + map.get("psend") + "' ");
				psEnd = map.get("psend");
			}

		} else {
			sb.append(" and ws_pstime >'" + psStart + "'");
		}

		sb.append(" order by planFlag ,demandDate ");
		List<PlaCourse> listQuery = dao.listQuery(sb.toString());

		EchartsVo vo = new EchartsVo();

		vo.setTitle("时间:从" + psStart + "到" + psEnd);
		List<String> legends = new ArrayList<>();
		;
		List<String> xAxis = new ArrayList<>();
		legends.add("工单进度");
		List<String> data = new ArrayList<>();
		for (PlaCourse course : listQuery) {
			xAxis.add(course.getWsCode());
			data.add(String.valueOf(course.getWsSchedule()));
		}
		Map<String, EchartSeriesVo> seriesData = new HashMap<>();
		EchartSeriesVo es = new EchartSeriesVo();
		es.setType("left");
		es.setData(data);
		seriesData.put("工单进度", es);
		vo.setLegends(legends);
		vo.setSeriesData(seriesData);
		vo.setxAxis(xAxis);

		return vo;
	}

	public Page getPageList(String orderCode, Page page) {
		String hql = "from PlaCourse where orderCode ='" + orderCode + "'";
		Page pageQuery = dao.pageQuery(hql, page.getPageNo(),
				page.getPagesize());
		return pageQuery;
	}

	public void clearBean(String wsCode) {
		String sql = " delete from pla_course where wsCode='" + wsCode + "'";
		dao.deleteBySql(sql);
	}

	/**
	 * 获取当前机台名
	 * 
	 * @param wsCode
	 * @return
	 */
	public String getNowSeq(String wsCode) {
		String hql = "from PlaMacTask where workcode='" + wsCode + "' ";
		String deHql = " and productstate='生产中'";
		String macCode = "";
		List<PlaMacTask> list = dao.createQuery(hql + deHql).list();
		if (list != null && list.size() != 0) {
			macCode = list.get(0).getMaccode();
		} else {
			hql = "select max(step) from pla_mac_task where workcode='"
					+ wsCode + "'  and productstate='结束'";
			Integer step = (Integer) dao.createSQLQuery(hql).uniqueResult();
			if (step == null) {
				return "未生产";
			} else {
				step += 1;
				hql = " from PlaMacTask where workcode='" + wsCode
						+ "'  and step =" + step;
				list = dao.createQuery(hql).list();
				if (list == null || list.size() == 0) {
					return "未查到";
				}
				macCode = list.get(0).getMaccode();

			}
		}
		// 查询 工序名称
		hql = "select cs.seq_name from mau_machine mm ,cra_seq cs where mm.seq_code = cs.seq_code and mm.mac_code = '"
				+ macCode + "'";
		String seqName = (String) dao.createSQLQuery(hql).uniqueResult();
		return seqName;
	}

	/**
	 * 
	 * @param map
	 * @param gghx
	 * @param seq
	 * @param step
	 */
	public void setSeqMaxLength(Map<String, Float> map, String ggxh,
			String seq, int step) {
		float diameter = plaMachinePlanManageDAO.getDiameterVaule2(ggxh, step,
				seq);
		if (diameter > 0) {
			float length = plaMachinePlanManageDAO.getOneMaxPeAxisLength(
					diameter, true);
			String key = seq + ";" + String.valueOf(step);
			map.put(key, length);
		}

	}

	/**
	 * 合单
	 * 
	 * @param listVo
	 * @return
	 * @author JS
	 */
	public HashMap<String, Object> getUnionCourseWsCode(
			List<NeedPartPlaCourseVo> listVo) {
		String wsCode = "";
		String scCode = "";
		String orderCode = "";
		int a = 0;
		PlaCourse plaCourse = new PlaCourse();
		for (int i = 0; i < listVo.size(); i++) {
			if (i < listVo.size() - 1) {
				wsCode += listVo.get(i).getWorkcode() + ",";
			} else {
				wsCode += listVo.get(i).getWorkcode();
			}
		}
		for (NeedPartPlaCourseVo vo : listVo) {
			List<PlaCourseAxis> courseAxis = dao.getPlaCourseAxisByWsCode(vo
					.getWorkcode());
			plaCourse = dao.getPlaCourseByWsCode(vo.getWorkcode());
			if (a < listVo.size() - 1) {
				scCode += plaCourse.getScCode() + ",";
				orderCode += plaCourse.getOrderCode() + ",";
				dao.remove(plaCourse);
			} else {
				scCode += plaCourse.getScCode();
				orderCode += plaCourse.getOrderCode();
			}

			// plaCourse
			for (PlaCourseAxis plaCourseAxis : courseAxis) {
				plaCourseAxis.setCourseCode(wsCode);
				try {
					// 合工单详情
					dao.updateByCon(plaCourseAxis, false);
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(plaCourseAxis, "");
				}
			}
			a++;
		}
		// 合工单
		plaCourse.setScCode(scCode);
		plaCourse.setWsCode(wsCode);
		plaCourse.setOrderCode(orderCode);
		// plaCourse.setId(null);
		try {
			dao.updateByCon(plaCourse, false);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(plaCourse, "");
		}

		return JsonWrapper.successWrapper(plaCourse, "");
	}

}
