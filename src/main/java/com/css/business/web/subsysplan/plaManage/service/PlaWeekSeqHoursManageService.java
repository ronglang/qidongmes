package com.css.business.web.subsysplan.plaManage.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraCraftProductManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraProSeqRelationManageDao;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.subsyscraft.craManage.service.CraProSeqRelationManageService;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.business.web.subsysmanu.bean.MauTaskLoad;
import com.css.business.web.subsysmanu.mauManage.bean.MauMachineVO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineSpeedManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauTaskLoadDAO;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineSpeedManageService;
import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaProductOrderAxis;
import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.subsysplan.bean.PlaWeekSeqHours;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderAxisManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderSeqHourseManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaWeekSeqHoursManageDAO;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.subsysquality.quaManage.service.QualityProductPlanManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.dao.SysConfigManageDAO;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaWeekSeqHoursManageService")
public class PlaWeekSeqHoursManageService extends
		BaseEntityManageImpl<PlaWeekSeqHours, PlaWeekSeqHoursManageDAO> {

	Logger log = Logger.getLogger(PlaWeekSeqHoursManageService.class);

	@Resource(name = "plaWeekSeqHoursManageDAO")
	private PlaWeekSeqHoursManageDAO dao;

	@Resource(name = "mauMachineSpeedManageService")
	private MauMachineSpeedManageService service;

	@Resource(name = "plaProductOrderManageService")
	private PlaProductOrderManageService ser;
	@Resource(name = "plaProductOrderManageDAO")
	private PlaProductOrderManageDAO plaProductOrderManageDAO;

	@Resource(name = "plaProductOrderAxisManageDAO")
	private PlaProductOrderAxisManageDAO plaProductOrderAxisManageDAO;

	@Resource(name = "plaProductOrderSeqHourseManageDAO")
	private PlaProductOrderSeqHourseManageDAO plaProductOrderSeqHourseManageDAO;
	
	@Resource(name = "qualityProductPlanManageService")
	private QualityProductPlanManageService qualityservice;

	@Resource(name = "mauMachineManageService")
	private MauMachineManageService serv;
	@Resource(name = "mauMachineManageDAO")
	private MauMachineManageDAO mauMachineManageDAO;

	@Resource(name = "mauMachineSpeedManageDAO")
	private MauMachineSpeedManageDAO mauMachineSpeedManageDAO;

	@Resource(name = "craProSeqRelationManageService")
	private CraProSeqRelationManageService serCra;

	@Autowired
	private CraProSeqRelationManageDao craProSeqRelationManageDao;

	@Resource(name = "craCraftProductManageDAO")
	private CraCraftProductManageDAO craCraftProductManageDAO;

	@Resource(name = "sysConfigManageDAO")
	private SysConfigManageDAO sysConfigManageDAO;

	@Resource(name = "mauTaskLoadDAO")
	private MauTaskLoadDAO mauTaskLoadDAO;
	
	@Resource(name="craSeqManageDAO")
	private CraSeqManageDAO craSeqManageDAO;

	@Override
	public PlaWeekSeqHoursManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 计算每个工序所需要的时间(默认在分盘的工序才进行分盘)
	 * 
	 * @param li
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	@Deprecated
	public HashMap<String, Object> savePlaWeekSeqHours(SysUser user, String li,
			List<Object> list) {
		String msg = null;
		List<PlaWeekSeqHours> lli = new ArrayList<PlaWeekSeqHours>();
		try {
			if (li != null) {
				JSONArray ja = JSONArray.fromObject(li);
				list = JsonUtil.jsonToList(ja);
			}
			for (Object obj : list) {
				Map<String, Object> map = (Map<String, Object>) obj;
				String proGgxh = map.get("proGgxh").toString(); // 得到产品规格型号
				Integer po_id = map.get("id") == null ? 0 : Integer
						.parseInt(map.get("id").toString()); // 得到生产令id
				// String str
				// =map.get("productPartLen")==null?"0+1":map.get("productPartLen").toString();
				// //得到产品段长
				PlaProductOrder ppo = plaProductOrderManageDAO.get(po_id);
				String str = ppo.getProductPartLen();

				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine se = manager.getEngineByName("js");
				Integer length = Integer.parseInt(se.eval(str).toString()) * 1000; // 将要求的段长换算成以米为单位的总长度
				// Integer po_id = Integer.parseInt(map.get("id").toString());
				// //得到生产令id
				// Integer amount =
				// Integer.parseInt(map.get("amount").toString()); //得到轴数
				// Double length =
				// map.get("productPartLen")==null?0:Double.parseDouble(map.get("productPartLen").toString());
				// //得到产品段长
				Integer amount = map.get("amount") == null ? 0 : Integer
						.parseInt(map.get("amount").toString()); // 得到轴数

				String proCraCode = map.get("proCraftCode").toString();
				// String proCode = map.get("proCode").toString();

				if (proCraCode == null || proCraCode.length() == 0)
					throw new Exception("数据错误，传参产品工艺编码为空");

				CraCraftProduct ccp = craCraftProductManageDAO
						.getObjByProCraCode(proCraCode);
				if (proCraCode == null || proCraCode.length() == 0)
					throw new Exception("数据错误，传参产品工艺编码（" + proCraCode
							+ "）对应的数据不存在");

				// List<CraProSeqRelation> ltemp =
				// craProSeqRelationManageDao.getHibernateTemplate().find("from CraProSeqRelation where proCraftCode =?",proCraCode);
				List<CraProSeqRelation> ltemp = craProSeqRelationManageDao
						.getRouteSeqByRoute_code(proCraCode);

				// 下面逻辑不对
				/**
				 * 应当： 1. 循环List<CraProSeqRelation> ltemp，各工序。 2. 先找到适合生产的机台 3.
				 * 根据机台id找它的生产速度 4. 计算各工序的工时
				 */
				for (CraProSeqRelation cpsr : ltemp) {
					// 找到机台???
					// 怎么找机台：先找适合本工序的机台，再看在指定工作日是否还有空闲，没则继续下一个机台。
					// 若全没空，说明计划的时间不对。
					//
					// 取生产速度
					// 计算工时
					// 保存工时
				}

				Integer croe = ltemp.get(0).getCore(); // 得到芯线数
				List<MauMachineSpeed> lis = service.getMauMachineSpeed(proGgxh);
				for (int i = 0; i < lis.size(); i++) {
					PlaWeekSeqHours psh = new PlaWeekSeqHours();
					Integer mac_id = lis.get(i).getMachineId(); // 机台id

					List<MauMachine> ll = serv.getMauMachine(mac_id);
					Double h1 = 0.0;
					if (ll != null && ll.size() > 0) {
						List<MauMachine> counts = serv.getPlaMauMachine(ll.get(
								0).getMacType());
						int count = counts.size();
						if ("分盘".equals(ll.get(0).getMacType())) {
							h1 = (amount * length / lis.get(i).getSpeed()
									+ lis.get(i).getReadyTime() * amount
									+ lis.get(i).getUpTime() * amount + lis
									.get(i).getDownTime() * amount * croe)
									/ count;
						} else if ("绞线".equals(ll.get(0).getMacType())) {
							h1 = (amount * length / lis.get(i).getSpeed()
									+ lis.get(i).getReadyTime() * amount
									+ lis.get(i).getUpTime() * amount * croe + lis
									.get(i).getDownTime() * amount)
									/ count;
						} else if ("拉丝".equals(ll.get(0).getMacType())) {
							h1 = (amount * length / lis.get(i).getSpeed()
									+ lis.get(i).getReadyTime() * amount
									+ lis.get(i).getUpTime() * amount * croe + lis
									.get(i).getDownTime() * amount * croe)
									/ count;
						} else {
							h1 = (amount * length / lis.get(i).getSpeed()
									+ lis.get(i).getReadyTime() * amount
									+ lis.get(i).getUpTime() * amount + lis
									.get(i).getDownTime() * amount)
									/ count;
						}
						String seqName = ll.get(0).getMacType();
						psh.setSeqHours(h1);
						psh.setSeqName(seqName);
						psh.setProGgxh(proGgxh);
						psh.setProductPartLen(length);
						psh.setPlaOrderId(po_id);

						psh.setCreate_by(user.getAccount());
						psh.setCreateDate(new Date(System.currentTimeMillis()));

						List<PlaWeekSeqHours> ps = this.getPlaWeekSeqHours(
								po_id, seqName);
						lli.add(psh);
						if (ps == null || ps.size() == 0 || ps.get(0) == null) {
							dao.save(psh);
						}
					} else {
						msg = "空闲机台不足";
						return JsonWrapper.failureWrapperMsg(lli, msg);
					}

				}
			}
			msg = "保存成功";
			return JsonWrapper.successWrapper(lli, msg);
		} catch (Exception e) {
			msg = "发生了未知错误";
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(lli, msg);

		}
	}

	public List<PlaWeekSeqHours> getPlaWeekSeqHours(Integer plaOrderId,
			String seqName) {
		String hql = " from PlaWeekSeqHours p where p.plaOrderId = ? and p.seqName = ? ";
		@SuppressWarnings("unchecked")
		List<PlaWeekSeqHours> list = dao.createQuery(hql, plaOrderId, seqName)
				.list();
		return list;
	}

	public static void main(String[] args) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine se = manager.getEngineByName("js");
		try {
			Double length = (Double) se.eval("0+1");
			System.out.println(length);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @TODO: 分解生产令。生成各工序工时中间表数据 不再使用
	 * @author: zhaichunlei & @DATE : 2017年8月2日
	 * @param user
	 * @param li
	 * @param list
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	@Deprecated
	public HashMap<String, Object> savePlaWeekSeqHours_2(SysUser user,
			String li, List<Object> list) throws Exception {
		List<PlaWeekSeqHours> tlst = new ArrayList<PlaWeekSeqHours>();
		try {
			if (li != null) {
				JSONArray ja = JSONArray.fromObject(li);
				list = JsonUtil.jsonToList(ja);
			}

			for (Object obj : list) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				// String proGgxh = map.get("proGgxh").toString(); //得到产品规格型号
				Integer po_id = map.get("id") == null ? 0 : Integer
						.parseInt(map.get("id").toString()); // 得到生产令id
				// String str
				// =map.get("productPartLen")==null?"0+1":map.get("productPartLen").toString();
				// //得到产品段长
				PlaProductOrder ppo = plaProductOrderManageDAO.get(po_id);
				String str = ppo.getProductPartLen();

				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine se = manager.getEngineByName("js");
				Integer length = Integer.parseInt(se.eval(str).toString()) * 1000 ; // 将要求的段长换算成以米为单位的总长度
				Integer amount = map.get("amount") == null ? 0 : Integer
						.parseInt(map.get("amount").toString()); // 得到轴数

				String proCraCode = map.get("proCraftCode").toString();

				// 生产令编号
				// String productOrderCode = ppo.getProductOrderCode();
				// String proCode = map.get("proCode").toString();

				if (proCraCode == null || proCraCode.length() == 0)
					throw new Exception("数据错误，传参产品工艺编码为空");

				CraCraftProduct ccp = craCraftProductManageDAO
						.getObjByProCraCode(proCraCode);
				if (proCraCode == null || proCraCode.length() == 0)
					throw new Exception("数据错误，传参产品工艺编码（" + proCraCode
							+ "）对应的数据不存在");

				// 工艺路线
				// String routeCode = ccp.getRouteCode();
				List<CraProSeqRelation> clst = craProSeqRelationManageDao
						.getRouteSeqByRoute_code(proCraCode);
				if (clst == null || clst.size() == 0)
					throw new Exception("缺失产品工艺（" + proCraCode
							+ "）设置,请联系管理员添加。");

				// 分别操作各工序
				for (CraProSeqRelation csr : clst) {
					String seqCode = csr.getSeqCode();
					String proGgxh = csr.getProGgxh();
					Integer halfProNum = csr.getSeqHalfProNum(); // 半成品数

					if (seqCode == null || proGgxh == null
							|| halfProNum == null || seqCode.length() == 0
							|| proGgxh.length() == 0)
						throw new Exception("数据错误，产品工艺关系的工序、产品规格型号、半成品数不能为空");

					// fetchAndAddSeqIdleMachine(thisSeqTime_begin,miniTime,);
					// 取机台生产能力
					// mauMachineSpeedManageDAO.sf();
					// sf sf

					// 先不考虑机台的故障。 故障维护时间段需要排除在机台计划外。这里计算生产能力
					BigDecimal gs = serv.calcGongShi_LL(length, amount,
							seqCode, proGgxh, halfProNum);

					PlaWeekSeqHours psh = new PlaWeekSeqHours();
					psh.setSeqHours(gs.doubleValue());
					psh.setSeqName(csr.getSeqCode());
					psh.setSeqCode(csr.getSeqCode());
					psh.setProGgxh(proGgxh);
					psh.setProductPartLen(length);
					psh.setPlaOrderId(po_id);

					psh.setCreate_by(user.getAccount());
					psh.setCreateDate(new Date(System.currentTimeMillis()));

					tlst.add(psh);
				}

				// 先清除周计划工序工时表（周计划工序拆分表）
				dao.deleteByPlaProductId(po_id);
			}

			// 保存周计划工序工时表（周计划工序拆分表）
			if (tlst.size() > 0) {
				dao.saveOrUpdateBatch(tlst);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return JsonWrapper.failureWrapperMsg(tlst, e.getMessage());
		}
		return JsonWrapper.successWrapper(tlst, "保存成功");
	}

	/**
	 * @TODO: 计划第三版本.生成生产令的工序工时拆分表明细
	 * @author: zhaichunlei & @DATE : 2017年8月4日
	 * @param user
	 * @param li
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public HashMap<String, Object> savePlaWeekSeqHours_3(SysUser user,
			String li, List<Object> list) throws Exception {
		// List<PlaWeekSeqHours> tlst = new ArrayList<PlaWeekSeqHours>();
		try {
			if (li != null) {
				JSONArray ja = JSONArray.fromObject(li);
				list = JsonUtil.jsonToList(ja);
			}

			// 取当前工作日，从今天开始算.周末暂用最简单的方式排除掉周日
			Integer workDay = DateUtil.getCurrentDateInYyyyMMdd_intVal();

			for (Object obj : list) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				// String proGgxh = map.get("proGgxh").toString(); //得到产品规格型号
				Integer po_id = map.get("id") == null ? 0 : Integer
						.parseInt(map.get("id").toString()); // 得到生产令id
				// String str
				// =map.get("productPartLen")==null?"0+1":map.get("productPartLen").toString();
				// //得到产品段长
				PlaProductOrder ppo = plaProductOrderManageDAO.get(po_id);

				String proCraCode = map.get("proCraftCode").toString();

				// 生产令编号
				String productOrderCode = ppo.getProductOrderCode();
				// String proCode = map.get("proCode").toString();

				if (proCraCode == null || proCraCode.length() == 0)
					throw new Exception("数据错误，传参产品工艺编码为空");

				CraCraftProduct ccp = craCraftProductManageDAO
						.getObjByProCraCode(proCraCode);
				if (proCraCode == null || proCraCode.length() == 0)
					throw new Exception("数据错误，传参产品工艺编码（" + proCraCode
							+ "）对应的数据不存在");

				// 工艺路线
				// String routeCode = ccp.getRouteCode();
				List<CraProSeqRelation> clst = craProSeqRelationManageDao
						.getRouteSeqByRoute_code(proCraCode);
				if (clst == null || clst.size() == 0)
					throw new Exception("缺失产品工艺（" + proCraCode
							+ "）设置,请联系管理员添加。");

				// 分别操作各工序
				for (CraProSeqRelation csr : clst) {
					String seqCode = csr.getSeqCode();
					String proGgxh = csr.getProGgxh();
					Integer halfProNum = csr.getSeqHalfProNum(); // 半成品数

					if (seqCode == null || proGgxh == null
							|| halfProNum == null || seqCode.length() == 0
							|| proGgxh.length() == 0)
						throw new Exception("数据错误，产品工艺关系的工序、产品规格型号、半成品数不能为空");

					// 取生产令对应的轴列表
					List<PlaProductOrderAxis> pocLst = plaProductOrderAxisManageDAO
							.getListByProOrderCode(productOrderCode);
					if (pocLst == null || pocLst.size() == 0) {
						throw new Exception("生产令（" + productOrderCode
								+ "）不存在对应的轴信息。数据错误");
					}

					// 取上一工序的工序代码
					//String lastSeqCode = craCraftProductManageDAO.getLastSeq(seqCode, proCraCode);

					/*Timestamp thisSeqTime_begin = null;
					// 本工序是第一个工序
					if (lastSeqCode == null) {
						// 取本工序能开始生产的最早时间点（不早于上工序第一根线结束后30分钟）
						thisSeqTime_begin = plaProductOrderSeqHourseManageDAO
								.calcThisSeqFirstAxisEndTime(seqCode,
										ppo.getId());
						// 如果为空，说明还没开始，则时间重新安排。
					} else {
						// 取本工序能开始生产的最早时间点（不早于上工序第一根线结束后30分钟）
						// 上工序的开始最早时间。不可能为空
						thisSeqTime_begin = plaProductOrderSeqHourseManageDAO
								.calcThisSeqFirstAxisEndTime(lastSeqCode,
										ppo.getId());
					}*/

					// 下工序时间设置间隔（分钟）
					/*
					 * Integer miniTime = 0; try { String o =
					 * sysConfigManageDAO.getValueByItem("下工序开始时间间隔（分）");
					 * miniTime = Integer.parseInt(o); } catch (Exception e) {
					 * // TODO Auto-generated catch block e.printStackTrace();
					 * log.error(e); throw new
					 * Exception("请配置【下工序开始时间间隔（分）】到系统参数表中"); }
					 */

					fetchNextIdleMachine(user, pocLst, seqCode, workDay);

					// Map<String,List<PlaProductOrderAxis>> pmap = new
					// HashMap<String,List<PlaProductOrderAxis>>();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		return JsonWrapper.successWrapper("保存成功");
	}

	/**
	 * @TODO: 取下一个可用机台、或使用指定机台，直到当天工时排满再取下一个机台。同时生成生产令工序拆分表数据
	 * @author: zhaichunlei & @DATE : 2017年8月4日
	 * @param machineId
	 *            机台id
	 * @param productOrderCode
	 *            生产令
	 * @param thisSeqTime_begin
	 *            本工序可以开工的最早时间点。
	 * @param miniTime
	 *            第一根线：最早时间点要延迟的时间(两个工序间，第一根线需要延迟如30分钟,留给配送。)
	 * @return
	 * @throws Exception
	 */
	private void fetchNextIdleMachine(SysUser user,
			List<PlaProductOrderAxis> pocLst, String seqCode, Integer workDay)
			throws Exception {
		if(seqCode == null || seqCode.length() == 0 ||
				 workDay == null)
			throw new Exception("工序与工作日不能为空。 数据错误");
				
		// 未指定机台，则需要找一个
		// PlaProductOrderAxis ppoa = pocLst.get(0);
		// String productOrderCode = ppoa.getProductOrderCode();

		/**
		 * 根据时间点，轴名称，寻找当天有空闲机台列表 当天：从早上8点，到晚8点，半当天的半天，到第二天早8点，算当天一天。
		 * 目前不考虑换班的生产衔接问题，换班不中断生产
		 * 
		 * 找到当天的机台列表 1. 循环机台 2. 循环轴 3. 增加机台任务 4. 轴跳转、机台工时安排满后，机台跳转， 当天安排满后，工作日跳转
		 */
		// List<MauMachineVO> mlst =
		// plaProductOrderSeqHourseManageDAO.fetchSeqIdleMachine(seqCode,workDay);
		// //合计当前工作日工作量少的机台---当前工序(未加规格型号限制)
		CraSeq cs = craSeqManageDAO.getEntityByCode(seqCode);
		String cstr = cs.getChildCode();
		String seqTemp = seqCode;
		if(cstr != null && cstr.length() > 0){
			String temp[] = cstr.split(",");
			if(temp != null && temp.length > 1 ){
				seqTemp = temp[0].trim();
			}
		}
		//List<MauMachineVO> mlst = mauTaskLoadDAO.fetchSeqIdleMachine(seqCode,	workDay); // 合计当前工作日工作量少的机台---当前工序(未加规格型号限制)
		//考虑到合并工序的，要取第一个工序查询机台. 这里取到的是真实的机台id.
		List<MauMachineVO> mlst = mauTaskLoadDAO.fetchSeqIdleMachine(seqTemp,	workDay); // 合计当前工作日工作量少的机台---当前工序(未加规格型号限制)
		if (mlst == null || mlst.size() == 0) {
			workDay = DateUtil.increaseDateInYyyyMMdd_intVal(workDay);
			
			fetchNextIdleMachine(user,pocLst,seqCode,workDay);
		}
		//找到机台后，开始安排计划
		else{
			List<PlaProductOrderAxis> pocLst2 = new ArrayList<PlaProductOrderAxis>();

			c1: 
			for (MauMachineVO vo : mlst) {
				Integer mid = vo.getId();
				for (PlaProductOrderAxis ax : pocLst) {
					// 保存拆分表
					int bool = addTaskToMachine(user, seqCode, mid, ax, workDay);
					// 当前机台已满.成功添加
					if (bool == 1) {
						pocLst2.add(ax);

						// 满了，需要换机台继续生成
						pocLst.removeAll(pocLst2);
						pocLst2.clear();
						continue c1;
					}
					// 未满，可以继续添加
					else if (bool == 0) {
						pocLst2.add(ax);
					}
					//满了，且添加失败
					else if(bool == 2){
						pocLst.removeAll(pocLst2);
						pocLst2.clear();
						continue c1;
					}
				}
				pocLst.removeAll(pocLst2);
				pocLst2.clear();
			}
			
			//上面，当天机台已经全部安排任务了。继续下一天的
			
			// 当天的任务还没分完。需要计算第二天的
			if (pocLst.size() > 0) {
				workDay = DateUtil.increaseDateInYyyyMMdd_intVal(workDay);
				// 递归第二天的任务
				fetchNextIdleMachine(user, pocLst, seqCode, workDay);
			}
		}
	}

	/**
	 * @TODO: 增加一轴线的拆分任务; 0:未满；1：满了，本次添加成功；2；满了，本次添加失败
	 * @author: zhaichunlei & @DATE : 2017年8月8日
	 * @param seqCode
	 * @param ax
	 * @param workDay
	 * @throws Exception
	 */
	private int addTaskToMachine(SysUser user, String seqCode,
			Integer machineId, PlaProductOrderAxis ax, Integer workDay)
			throws Exception {
		// 取配置参数
		// 一天有多少工时用于工作
		String gs = sysConfigManageDAO.getValueByItem("一天工时数");
		if (gs == null || gs.trim().length() == 0)
			throw new Exception("请在SYSCONFIG配置【一天工时数】参数");

		// 机台余多少工时后，就视为排满
		String kxgs = sysConfigManageDAO.getValueByItem("排产机台允许空闲工时");
		if (kxgs == null || kxgs.trim().length() == 0)
			throw new Exception("请在SYSCONFIG配置【排产机台允许空闲工时】参数");

		// 取机台负荷的剩余工时
		MauTaskLoad mtl = mauTaskLoadDAO.getTaskByMachineAndWorkDay(machineId,
				workDay);
		BigDecimal hourse = new BigDecimal(0);
		// 还未安排任务。则增加
		if (mtl == null) {
			mtl = new MauTaskLoad();
			mtl.setObjId(machineId);
			mtl.setType("机台");
			mtl.setWorkDay(workDay);
			mtl.setCreateBy(user.getAccount());
			mtl.setCreateDate(new Date(System.currentTimeMillis()));
			mtl.setHours(hourse);
		} else {
			hourse = mtl.getHours();
		}
		// 计算本轴线工时
		BigDecimal bd = serv.calcGongShi_axis_machine(ax.getAxisName(),
				machineId,seqCode);
		
		//如果一轴线超出一天的工时，则报错。
		if(bd.compareTo(new BigDecimal(gs)) > 0) throw new Exception("线轴过长，一天内难已生产完毕。无法排产");

		// 校验是否可以生产
		BigDecimal yugs = new BigDecimal(gs);
		yugs = yugs.subtract(hourse);
		// 工时已经超了。先不报错
		if (yugs.compareTo(new BigDecimal(0)) <= 0) {
			mtl.setStatus("否"); // 本机台不能再增加任务了
			// 更新机台负荷状态
			if (mtl.getId() == null)
				mauTaskLoadDAO.save(mtl);
			else
				mauTaskLoadDAO.updateByCon(mtl, false);
			return 2; // 2；满了，本次添加失败
		}
		// 余的时间不多。可以忽略。
		if (yugs.compareTo(new BigDecimal(kxgs)) <= 0) {
			mtl.setStatus("否"); // 本机台不能再增加任务了
			// 更新机台负荷状态
			if (mtl.getId() == null)
				mauTaskLoadDAO.save(mtl);
			else
				mauTaskLoadDAO.updateByCon(mtl, false);
			return 2;
		}

		// 计算本轴线的工时
		BigDecimal mgs = serv.calcGongShi_axis_machine(ax.getAxisName(),
				machineId,seqCode);
		// 机台余的时间不够生产本轴线的。
		if (yugs.compareTo(mgs) < 0) {
			mtl.setStatus("否"); // 本机台不能再增加任务了
			// 更新机台负荷状态
			if (mtl.getId() == null)
				mauTaskLoadDAO.save(mtl);
			else
				mauTaskLoadDAO.updateByCon(mtl, false);
			return 2;
		}

		String an = ax.getAxisName();
		System.out.println("拆分线轴："+an+"*******************************************************");
		
		
		// 更新机台任务表
		// 保存生产令拆分表
		yugs = yugs.subtract(mgs);
		// 生产完本根线后，余的时间不多了
		if (yugs.compareTo(new BigDecimal(kxgs)) <= 0) {
			mtl.setStatus("否"); // 本机台不能再增加任务了
			mtl.setHours(hourse.add(mgs));
			// 更新机台负荷状态
			if (mtl.getId() == null)
				mauTaskLoadDAO.save(mtl);
			else
				mauTaskLoadDAO.updateByCon(mtl, false);

			saveProOrderSeqHourse(user, seqCode, machineId, ax, workDay, mgs);
			// 需要return 1;
			return 1;
		}
		// 还能继续生产。空闲时间大于设定
		else {
			mtl.setStatus("是");
			mtl.setHours(hourse.add(mgs));
			// 更新机台负荷状态
			if (mtl.getId() == null)
				mauTaskLoadDAO.save(mtl);
			else
				mauTaskLoadDAO.updateByCon(mtl, false);
			saveProOrderSeqHourse(user, seqCode, machineId, ax, workDay, mgs);
			// 需要return 0;

			return 0;
		}
	}

	/**
	 * @TODO: 保存生产令工序拆分表.
	 * @author: zhaichunlei & @DATE : 2017年8月8日
	 * @param user
	 * @param seqCode
	 * @param machineId
	 * @param ax
	 * @param workDay
	 * @param mgs
	 *            工时
	 * @throws ParseException
	 */
	private void saveProOrderSeqHourse(SysUser user, String seqCode,
			Integer machineId, PlaProductOrderAxis ax, Integer workDay,
			BigDecimal mgs) throws ParseException {
		MauMachine mac = mauMachineManageDAO.get(machineId);
		PlaProductOrder ppo = plaProductOrderManageDAO
				.getPlaProductOrderByCode(ax.getProductOrderCode());

		// 本根线的生产开始与结束时间
		Timestamp t_start = fetchNextAxisStartDate(mac.getMacCode(), workDay);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(t_start.getTime()
				+ new Double(mgs.doubleValue() * 60 * 60 * 1000).intValue());
		Timestamp t_end = new Timestamp(cal.getTimeInMillis());

		// 保存生产令拆分表
		PlaProductOrderSeqHourse pos = new PlaProductOrderSeqHourse();
		pos.setAxisName(ax.getAxisName());
		pos.setCreateBy(user.getAccount());
		pos.setCreateDate(new Date(System.currentTimeMillis()));
		pos.setEmployeeIdMain(null); // 未计划
		pos.setEmployeeIdVice(null);
		; // 未计划
		pos.setLenUnit("米"); // 整个系统统一定义长度为米。不再用千米
		pos.setMacCode(mac.getMacCode());
		pos.setPartLen(ax.getPartLen());
		pos.setPlaOrderId(ppo.getId()); // 生产令的id
		pos.setProCraftCode(ppo.getProCraftCode()); // 产品工艺
		pos.setProGgxh(ppo.getProGgxh()); // 产品规格型号
		pos.setSeqHours(mgs); // 生产工时
		pos.setStartDate(t_start); // 生产开始时间
		pos.setEndDate(t_end); // 生产结束时间
		pos.setWorkDay(workDay);
		pos.setSeqCode(seqCode); // 工序编码

		// TODO　随机生成需要质检
		
		//1 查询是否为最后一道工序
		String endSeq = this.getEndSeq(ppo.getId());
		
		Random random = new Random();
		int int1 = random.nextInt(100);
		if (endSeq.equals(seqCode)) { //最后道工序质检记录
			Double value = this.getEndValue();
			/*int aa = Integer.parseInt(value);
			if(int1<aa*100){
				pos.setIsSample(PlaProductOrderSeqHourse.SAMPLE);
			}else{
				pos.setIsSample(PlaProductOrderSeqHourse.NOT_SAMPLE);
			}*/
			if(int1 < value * 100) {
				pos.setIsSample(PlaProductOrderSeqHourse.SAMPLE);
			}else{
				pos.setIsSample(PlaProductOrderSeqHourse.NOT_SAMPLE);
			}
		}else{ //其他工序质检记录
			//String value = this.getValue(seqCode)+"";
			Double value = this.getValue(seqCode);
			//int aa = Integer.parseInt(value);
			if (int1 < value*100) {
				pos.setIsSample(PlaProductOrderSeqHourse.SAMPLE);
			} else {
				pos.setIsSample(PlaProductOrderSeqHourse.NOT_SAMPLE);
			}
		}
		plaProductOrderSeqHourseManageDAO.save(pos);  //生成拆分表
		qualityservice.initQualityProductPlan(ppo.getId()); //生成抽检计划
	}

	/**
	 * @TODO: 计算新的一轴线，在一个机台上，在一个工作日，可以开始生产的时间。
	 * @author: zhaichunlei & @DATE : 2017年8月8日
	 * @param machineId
	 * @param workDay
	 * @return
	 * @throws ParseException
	 */
	private Timestamp fetchNextAxisStartDate(String macCode, Integer workDay)
			throws ParseException {
		Timestamp t = plaProductOrderSeqHourseManageDAO
				.calcThisSeqLastAxisEndTime(macCode, workDay);
		// 当前还没开始生产，则从早8点开始
		if (t == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			java.util.Date d = sdf.parse(workDay + "080000");
			return new Timestamp(d.getTime());
		}
		return t;
	}

	public String getEndSeq(Integer plaOrderId) {
		String sql = " SELECT seq_code FROM	cra_route_seq WHERE	route_code = ("
				+ "	SELECT	cra_route_code	FROM	pla_product_order	WHERE	ID = "+plaOrderId+" ) "
				+ " order by  sort desc   limit 1 ";
		Object obj = dao.createSQLQuery(sql).list();
		String seq = obj.toString();
		return seq;
	}
	
	/**
	 * 获取最后一道工序的质检概率
	 * @return
	 * @author JS
	 */
	public Double getEndValue(){
		String hql = " SELECT value from SysConfig where item = 'SEQ_end' ";
		Double val= (Double) dao.createQuery(hql).list().get(0);
		return val;
	}
	
	public Double getValue(String seq){
		String hql = " SELECT value from SysConfig where item = ? ";
		//Double val= (Double) dao.createQuery(hql, seq).list().get(0);
		Double val = new BigDecimal(dao.createQuery(hql, seq).list().get(0).toString()).doubleValue();
		return val;
	}

}
