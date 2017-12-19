package com.css.business.web.subsysplan.plaManage.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraBdBomParam;
import com.css.business.web.subsyscraft.bean.CraDjBomParam;
import com.css.business.web.subsyscraft.bean.CraFrBomParam;
import com.css.business.web.subsyscraft.bean.CraHtBomParam;
import com.css.business.web.subsyscraft.bean.CraJjBomParam;
import com.css.business.web.subsyscraft.bean.CraJtxBomParam;
import com.css.business.web.subsyscraft.bean.CraJxBomParam;
import com.css.business.web.subsyscraft.bean.CraJyBomParam;
import com.css.business.web.subsyscraft.bean.CraLsBomParam;
import com.css.business.web.subsyscraft.bean.CraYzBomParam;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.bean.PlaMacTaskAxisParam;
import com.css.business.web.subsysplan.plaManage.bean.MessageObjectVo;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskAxisParamDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskMaterilDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanManageDAO;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService.MacSpeedParam;
import com.css.business.web.subsysstore.storeManage.dao.StoreCoilSaManageDAO;
import com.css.business.web.syswebsoket.bean.EchartSeriesVo;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.common.util.DateUtil;
import com.css.common.util.PropertyFileUtil;
import com.css.common.util.TimeUtil;
import com.css.common.web.apachemq.handle.ApacheMqSender;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("plaMachinePlanManageService")
public class PlaMachinePlanManageService extends
		BaseEntityManageImpl<PlaMacTask, PlaMachinePlanManageDAO> {

	@Resource(name = "plaMachinePlanManageDAO")
	private PlaMachinePlanManageDAO dao;

	@Resource(name = "plaCourseManageDAO")
	private PlaCourseManageDAO plaCourseManageDAO;

	@Resource(name = "plaMacTaskAxisParamDAO")
	private PlaMacTaskAxisParamDAO plaMacTaskAxisParamDAO;

	@Resource(name = "plaMacTaskMaterilDAO")
	private PlaMacTaskMaterilDAO plaMacTaskMaterilDAO;

	@Resource(name = "storeCoilSaManageDAO")
	// @Autowired
	private StoreCoilSaManageDAO Smdao;

	private MauMachineManageDAO mauMachineManageDAO;

	@Override
	public PlaMachinePlanManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * @Description: 查询所有计划
	 * @param page
	 * @return
	 */
	public Page queryPage(Page page, String workcode, String maccode,
			String seqcode, String pstime, String pdtime) throws Exception {
		String sql = " from PlaMacTask where 1=1  ";
		if (maccode != null && !"".equals(maccode)) {
			sql += " AND maccode = '" + maccode + "' ";
		}
		if (seqcode != null && !"".equals(seqcode)) {
			sql += " AND seqcode = '" + seqcode + "' ";
		}
		if (workcode != null && !"".equals(workcode)) {
			sql += " AND workcode = '" + workcode + "' ";
		}
		if (pstime != null && !"".equals(pstime)) {
			sql += " AND  pstime >= '" + pstime + "' ";
		}
		if (pdtime != null && !"".equals(pdtime)) {
			sql += " AND pstime <= '" + pdtime + "' ";
		}
		sql += "order by pstime ";
		Page pageQuery = dao.pageQuery(sql.toString(), page.getPageNo(),
				page.getPagesize());
		return pageQuery;
	}

	public Page queryChilePage(Page p, String workCode, String macCode) {
		String hql = " from PlaMacTaskAxisParam where 1=1 ";
		if (workCode != null && !"".equals(workCode)) {
			hql += " AND workcode = '" + workCode + "' ";
		}
		if (macCode != null && !"".equals(macCode)) {
			hql += " AND maccode = '" + macCode + "' ";
		}
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	public Page getPlanGrid(Page p, String startTime, String endTime,
			String productstate) {
		StringBuffer sql = new StringBuffer(" from PlaMacTask r  where 1=1   ");
		if (null != productstate && !"".equals(productstate)) {
			sql.append("  and productstate ='" + productstate + "'");
		}
		if (null != startTime && !"".equals(startTime)) {
			sql.append("  and r.pstime>='" + startTime + "'");
		}
		if (null != endTime && !"".equals(endTime)) {
			sql.append("  and r.pstime<='" + endTime + "'");
		}
		sql.append("    order by  r.pstime ");
		Page page = dao.pageQuery(sql.toString(), p.getPageNo(),
				p.getPagesize());
		return page;
	}

	@SuppressWarnings("unchecked")
	public List<PlaCourse> getPlaCourse(String startTime, String endTime) {
		String hql = " from PlaCourse where 1=1 ";
		if (null != startTime && !"".equals(startTime)) {
			hql += "  and r.pstime>='" + startTime + "'";
		}
		if (null != endTime && !"".equals(endTime)) {
			hql += "  and r.pstime<='" + endTime + "'";
		}
		List<PlaCourse> list = dao.createQuery(hql).list();
		// TODO
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getMacCode() {
		String hql = " SELECT DISTINCT maccode from PlaMacTask ";
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getWorkCode() {
		String hql = " SELECT DISTINCT workcode from PlaMacTask ";
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
	 * 发送任务到机台
	 * 
	 * @param stime
	 * @param dtime
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void sendMacTask(String stime, String dtime) throws Exception {
		PropertyFileUtil pfu = new PropertyFileUtil();
		ApacheMqSender sender = ApacheMqSender.getInstance();


		String sql = "from PlaMacTask   where productstate='计划' "
				+ "AND  pstime >= '" + stime + "'  AND pstime <= '" + dtime
				+ "' order by pstime";
		List<PlaMacTask> pmts = dao.createQuery(sql).list();
		if (pmts != null && pmts.size() > 0) {
			for (PlaMacTask pmt : pmts) {
				String queue = pfu.getProp("MACHINE_"
						+ pmt.getMaccode().toUpperCase());


				if (queue == null)
					continue;
				String workCode = pmt.getWorkcode();
				String macCode = pmt.getMaccode();
				// 把每个机台生产的轴长度、颜色、轴号加入到pmt
				pmt.setAxisParam(plaMacTaskAxisParamDAO.queryByParam(workCode,
						macCode));
				// 用料加入到pmt
				pmt.setPmtms(plaMacTaskMaterilDAO.queryByParam(workCode,
						macCode));
				// 把整个工单(包括本机台自己的计划)，发到指定机台上

				/** 将BOM参数取出来放入消息队列 */
				// 1.根据工单编号获取产品规格型号
				String proGgxh = this.getProGgxhByWorkCode(workCode);
				// 2.查询出BOM参数
				pmt.setBom(this.getBomParam(pmt.getSeqcode(), proGgxh));
				JSONObject js = JSONObject.fromObject(pmt);
				// 把工单内容输出到log日志中
				log.info(js.toString());

				log.info("向机台返回工单内容：" + js.toString());
				sender.sendMsg_point_to_point(queue, js.toString());
				pmt.setProductstate("已排产");
				dao.updateByCon(pmt, false);
			}
		}

		// 更改工作单的状态
		ArrayList<String> works = new ArrayList<String>();
		sql = "select workcode from pla_mac_task a "
				+ "where a.productstate='已排产' and a.pstime >='" + stime
				+ "' and a.pstime <='" + dtime + "' group by workcode";

		List<Object> list = dao.createSQLQuery(sql).list();
		for (int i = 0; i < list.size(); i++) {
			works.add((String) list.get(i));
		}
		ArrayList<PlaCourse> pcs = new ArrayList<PlaCourse>();
		for (int i = 0; i < works.size(); i++) {
			PlaCourse pc = new PlaCourse();
			pc = plaCourseManageDAO.getByCourseCode(works.get(i));
			pc.setUseFlag(PlaCourse.USE_FLAG_YES);
			pcs.add(pc);
		}
		plaCourseManageDAO.saveOrUpdateBatch(pcs);
	}

	public PlaMacTask getMacReal(String macCode) {
		String hql = "from  PlaMacTask where maccode = '" + macCode
				+ "' and productstate = '生产中'";
		List<PlaMacTask> list = dao.createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public EchartsVo getTodayMacProduce() {
		String today = DateUtil.format(new Date(), "yyyy-MM-dd");
		String start = today + " 00:00:00";
		String end = today + " 23:59:59";
		String hql = "from PlaMacTask where pstime > '" + start
				+ "' and pstime <'" + end + "'";
		List<PlaMacTask> list = dao.createQuery(hql).list();
		EchartsVo vo = new EchartsVo();
		List<String> legends = new ArrayList<>();
		legends.add("已完成");
		legends.add("未完成");
		Map<String, EchartSeriesVo> seriesData = new HashMap<>();
		String title = "机台生产情况";
		List<String> xAxis = new ArrayList<>();
		// 已完成
		List<String> finiList = new ArrayList<>();
		// 未完成
		List<String> finiNoList = new ArrayList<>();
		// 该机台当日总长度
		float totolSum = 0;
		// 该机台当日已生产长度
		Integer finiSum = 0;
		// 正在循环的maccode
		String macCode = "";
		Integer count = 0;
		for (PlaMacTask task : list) {
			if (count == 0) {
				// 如果count== 0 第一次
				macCode = task.getMaccode();
				ArrayList<PlaMacTaskAxisParam> axisParam = task.getAxisParam();

				float paramSum = 0;
				;
				for (PlaMacTaskAxisParam param : axisParam) {
					paramSum += param.getAxiscount() * param.getLength();
				}
				// 每个任务完成的长度的 ,丢失精度 保留个位
				Integer paramFini = (int) (task.getSchedule() * paramSum / 100);
				totolSum += (int)paramSum;
				finiSum += paramFini;
				count++;
			} else if (macCode.equals(task.getMaccode())) {
				ArrayList<PlaMacTaskAxisParam> axisParam = task.getAxisParam();
				// 每个任务的长度
				float paramSum = 0;
				for (PlaMacTaskAxisParam param : axisParam) {
					paramSum += param.getAxiscount() * param.getLength();
				}
				// 每个任务完成的长度的 ,丢失精度 保留个位
				Integer paramFini = (int) (task.getSchedule() * paramSum / 100);
				totolSum += paramSum;
				finiSum += paramFini;
				count++;
			} else {
				// 当macCode 不一致的时候 进行保存
				xAxis.add(macCode);
				String fini = new java.text.DecimalFormat("#.00")
						.format((double) (finiSum / totolSum) * 100);
				// 已完成
				finiList.add(fini);
				// 未完成
				finiNoList
						.add(String.valueOf((100 - Double.parseDouble(fini))));
				// 初始化
				// 如果count== 0 第一次
				macCode = task.getMaccode();
				// 该机台当日总长度
				totolSum = 0;
				// 该机台当日已生产长度
				finiSum = 0;
				ArrayList<PlaMacTaskAxisParam> axisParam = task.getAxisParam();

				float paramSum = 0;
				;
				for (PlaMacTaskAxisParam param : axisParam) {
					paramSum += param.getAxiscount() * param.getLength();
				}
				// 每个任务完成的长度的 ,丢失精度 保留个位
				Integer paramFini = (int) (task.getSchedule() * paramSum / 100);
				totolSum += paramSum;
				finiSum += paramFini;
				count++;
			}

			// 最后一次
			if (count == list.size()) {
				// 当macCode 不一致的时候 进行保存
				xAxis.add(macCode);
				String fini = new java.text.DecimalFormat("#.00")
						.format((double) (finiSum / totolSum) * 100);
				// 已完成
				finiList.add(fini);
				// 未完成
				finiNoList
						.add(String.valueOf((100 - Double.parseDouble(fini))));
			}
		}
		EchartSeriesVo esFini = new EchartSeriesVo();
		EchartSeriesVo esNoFini = new EchartSeriesVo();
		esFini.setType("left");
		esNoFini.setType("left");
		esFini.setData(finiList);
		esNoFini.setData(finiNoList);
		seriesData.put("已完成", esFini);
		seriesData.put("未完成", esNoFini);

		vo.setxAxis(xAxis);
		vo.setTitle(title);
		vo.setSeriesData(seriesData);
		vo.setLegends(legends);

		return vo;
	}

	@SuppressWarnings("unchecked")
	public String getProGgxhByWorkCode(String workCode) {
		String hql = " from PlaCourse where wsCode = ? ";
		List<PlaCourse> list = dao.createQuery(hql, workCode).list();
		if (list.size() < 1 || list == null) {
			return null;
		}
		return list.get(0).getProGgxh();
	}

	/**
	 * 获取BOM参数 数据
	 * 
	 * @param seqCode
	 * @param proGgxh
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("unchecked")
	public List<String> getBomParam(String seqCode, String proGgxh) {
		String sql = " SELECT * FROM cra_" + seqCode
				+ "_bom_param WHERE pro_ggxh = '" + proGgxh + "' ";
		List<String> ls = new ArrayList<String>();
		Gson gson = new Gson();
		if ("ls".equals(seqCode)) {
			List<CraLsBomParam> list = dao.createSQLQuery(sql,
					CraLsBomParam.class).list();
			for (CraLsBomParam craLsBomParam : list) {
				ls.add(gson.toJson(craLsBomParam));
			}
		} else if ("jx".equals(seqCode)) {
			List<CraJxBomParam> list = dao.createSQLQuery(sql,
					CraJxBomParam.class).list();
			for (CraJxBomParam craJxBomParam : list) {
				ls.add(gson.toJson(craJxBomParam));
			}
		} else if ("jy".equals(seqCode)) {
			List<CraJyBomParam> list = dao.createSQLQuery(sql,
					CraJyBomParam.class).list();
			for (CraJyBomParam craJyBomParam : list) {
				ls.add(gson.toJson(craJyBomParam));
			}
		} else if ("bd".equals(seqCode)) {
			List<CraBdBomParam> list = dao.createSQLQuery(sql,
					CraBdBomParam.class).list();
			for (CraBdBomParam craBdBomParam : list) {
				ls.add(gson.toJson(craBdBomParam));
			}
		} else if ("jtx".equals(seqCode)) {
			List<CraJtxBomParam> list = dao.createSQLQuery(sql,
					CraJtxBomParam.class).list();
			for (CraJtxBomParam craJtxBomParam : list) {
				ls.add(gson.toJson(craJtxBomParam));
			}
		} else if ("yz".equals(seqCode)) {
			List<CraYzBomParam> list = dao.createSQLQuery(sql,
					CraYzBomParam.class).list();
			for (CraYzBomParam craYzBomParam : list) {
				ls.add(gson.toJson(craYzBomParam));
			}
		} else if ("ht".equals(seqCode)) {
			List<CraHtBomParam> list = dao.createSQLQuery(sql,
					CraHtBomParam.class).list();
			for (CraHtBomParam craHtBomParam : list) {
				ls.add(gson.toJson(craHtBomParam));
			}
		} else if ("jj".equals(seqCode)) {
			List<CraJjBomParam> list = dao.createSQLQuery(sql,
					CraJjBomParam.class).list();
			for (CraJjBomParam craJjBomParam : list) {
				ls.add(gson.toJson(craJjBomParam));
			}
		} else if ("fr".equals(seqCode)) {
			List<CraFrBomParam> list = dao.createSQLQuery(sql,
					CraFrBomParam.class).list();
			for (CraFrBomParam craFrBomParam : list) {
				ls.add(gson.toJson(craFrBomParam));
			}
		} else if ("dj".equals(seqCode)) {
			List<CraDjBomParam> list = dao.createSQLQuery(sql,
					CraDjBomParam.class).list();
			for (CraDjBomParam craDjBomParam : list) {
				ls.add(gson.toJson(craDjBomParam));
			}
		}
		return ls;
	}

	/**
	 * 
	 * @Description:TODO(用一句话描述)
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param type
	 *            计划 : 结束
	 * @param mac
	 *            机台code 多台 以 ";"号隔开
	 * @return
	 */
	public Map<String, Object> getTaskGanTe(String start, String end,
			String type, String mac) {
		// 所有
		String hql = " from  PlaMacTask where  1 = 1  ";
		// 机台
		String macHql = "select DISTINCT maccode  from PlaMacTask where 1 = 1 ";
		// 工单
		String gdHql = "select DISTINCT workcode  from PlaMacTask where 1 = 1 ";
		Long initTime = 0L;
		if ("".equals(start) || start == null) {
			initTime = System.currentTimeMillis();
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date parse = null;
			try {
				parse = sdf.parse(start);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			initTime = parse.getTime();
		}
		if (start != null && !"".equals(start)) {
			hql += " and  pstime  >= '" + start + "'";
			macHql += " and  pstime  >= '" + start + "'";
			gdHql += " and  pstime  >= '" + start + "'";
		}
		if (end != null && !"".equals(end)) {
			hql += " and  pstime <= '" + end + "'";
			macHql += " and  pstime  <= '" + end + "'";
			gdHql += " and  pstime  <= '" + end + "'";
		}
		if ("结束".equals(type)) {
			hql += " and  productstate = '结束' ";
			macHql += " and  productstate ='结束'";
			gdHql += " and  productstate ='结束'";
		} else {
			hql += " and (productstate = '计划' or productstate = '已排产') ";
			macHql += " and  (productstate = '计划' or productstate = '已排产') ";
			gdHql += "  and (productstate = '计划' or productstate = '已排产') ";
		}
		String inCode = "";
		if (mac != null && !"".equals(mac)) {
			String[] split = mac.split(";");
			for (int i = 0; i < split.length; i++) {
				if (i + 1 == split.length) {
					inCode += split[i];
				} else {
					inCode += split[i] + ",";
				}
			}
			hql += " and maccode in('" + inCode + "') ";
			macHql += " and  maccode in('" + inCode + "')";
			gdHql += "  and maccode in('" + inCode + "') ";
		}
		// 查处机台List
		List<String> macList = dao.createQuery(macHql).list();
		// 最后返回的Map
		Map<String, Object> map = new HashMap<>();
		// 查询所有的工单号，，做颜色
		List<String> gdsList = dao.createQuery(gdHql).list();
		// 所有机台的情况 macCode: 任务情况
		Map<String, List<Map<String, Object>>> macMap = new HashMap<>();
		// 所有机台的工单情况 macCode :list<工单>
		Map<String, List<String>> gdMap = new HashMap<>();
		// 循环机台
		int count = 0;
		for (int i = 0; i < macList.size(); i++) {
			String gssHql = gdHql;
			// 具体机台的工单任务
			gssHql += " and maccode = '" + macList.get(i) + "'";
			@SuppressWarnings("unchecked")
			List<String> gdList = dao.createQuery(gssHql).list();
			if (gdList == null || gdList.size() == 0) {
				continue;
			}
			// 单个机台所有的任务情况
			List<Map<String, Object>> singList = new ArrayList<>();
			// 循环工单
			for (String gd : gdList) {
				String detalHql = "";
				if ("结束".equals(type)) {
					// 查处具体任务
					detalHql = "from  PlaMacTask where workcode='" + gd
							+ "' and maccode ='" + macList.get(i)
							+ "' and productstate ='结束'";
				} else {
					// 查处具体任务
					detalHql = "from  PlaMacTask where workcode='"
							+ gd
							+ "' and maccode ='"
							+ macList.get(i)
							+ "' and (productstate = '计划' or productstate = '已排产')";
				}
				List<PlaMacTask> taskList = dao.createQuery(detalHql).list();
				if (taskList == null) {
					continue;
				} else {
					for (PlaMacTask task : taskList) {
						Long startTime = 0L;
						Long endTime = 0L;
						Date startDate = null;
						if ("结束".equals(type)) {
							// 获取实际开始时间,实际结束时间
							startDate = task.getFstime();
							startTime = task.getFstime().getTime();
							endTime = task.getFdtime().getTime();
						} else {
							// 获得计划开始时间,实际开始时间
							startDate = task.getPstime();
							startTime = task.getPstime().getTime();
							endTime = task.getPdtime().getTime();
						}
						// 封装数据
						Map<String, Object> singMap = new HashMap<>();
						singMap.put("index", count);
						singMap.put("start", startTime);
						singMap.put("end", endTime);
						singMap.put("startDate", startDate);
						singMap.put("name", gd);
						singList.add(singMap);
						count++;
					}
				}
			}
			// 每个机台完成后保存该机台所有的数据
			macMap.put(macList.get(i), singList);
			gdMap.put(macList.get(i), gdList);
		}
		// 封装完全数据
		map.put("macs", macList);
		map.put("gds", gdMap);
		map.put("startDate", new Date());
		map.put("data", macMap);
		map.put("initTime", initTime);
		for (int j = 0; j < gdsList.size(); j++) {
			map.put(gdsList.get(j), this.getColor());
		}
		return map;
	}

	public String getColor() {
		String color = "#";
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();
		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;
		return color + r + g + b;
	}

	/**
	 * 获取任务 OEE 使用
	 * 
	 * @param macCode
	 *            机台code
	 * @param courseCode
	 *            工单code
	 * @param step
	 *            步骤
	 * @return
	 */
	public PlaMacTask getPlaMacTask(String macCode, String courseCode,
			Integer step) {
		String hql = "from PlaMacTask where workcode = '" + courseCode
				+ "' and maccode = '" + macCode + "' and step = " + step;
		List<PlaMacTask> list = dao.createQuery(hql).list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 计算每个任务计划完成的总长度
	 * 
	 * @param macCode
	 * @param courseCode
	 * @param step
	 * @return
	 */
	public Double getLength(String macCode, String courseCode, Integer step) {
		String sql = "select axiscount ,length from pla_mac_task_color where maccode='"
				+ macCode
				+ "' and workcode = '"
				+ courseCode
				+ "' "
				+ " and step = " + step;
		List<Object[]> list = dao.createSQLQuery(sql).list();
		Double result = 0.0;
		for (Object[] objs : list) {
			result = result + Double.parseDouble(objs[0].toString())
					* Integer.parseInt(objs[1].toString());
		}
		return result;
	}

	/**
	 * 获取当前机台的任务
	 * 
	 * @param macCode
	 * @return
	 */
	public PlaMacTask getNowTask(String macCode) {
		String now = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String hql = "from PlaMacTask where fstime <='" + now
				+ "' and fdtime >= '" + now + "'";
		List<PlaMacTask> list = dao.createQuery(hql).list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 重新设置机台计划的时间
	 * 
	 * @return
	 */
	public void reSetPlanTime() throws Exception {

		// 删掉还没有生产机台计划的开始和结束时间时间
		// String
		// sql="udpate pla_mac_task set pstime=null,pdtime=null where productstate in('计划','已排产') ";
		// 获取PlaMacTask列表 条件：还没有生产机台计划的开始和结束时间时间
		try {
			String hql = "from PlaMacTask where productstate in('计划','已排产') ";
			List<PlaMacTask> list = dao.createQuery(hql).list();
			for (PlaMacTask pmt : list) {
				pmt.setPdtime(null);
				pmt.setPstime(null);
				dao.updateByCon(pmt, false);
			}

			// 取还还没有结束的所有工作单
			// 1是插单的数据
			List<PlaCourse> pcInsert = plaCourseManageDAO
					.getPlaCourseInsertUnfinishedList();
			for (PlaCourse pc : pcInsert) {
				List<PlaMacTask> pmts = dao.queryMachinePlanByMacCode(pc
						.getWsCode());
				// 重新算时间
				setTime(pmts);
				// 修改数据库值
				for (PlaMacTask pmt : pmts) {
					dao.updateByCon(pmt, false);
				}
			}
			// 2是正常开单的数据
			List<PlaCourse> pcNomal = plaCourseManageDAO
					.getNormalPlaCourseUnfinishedList();
			for (PlaCourse pc : pcNomal) {
				List<PlaMacTask> pmts = dao.queryMachinePlanByMacCode(pc
						.getWsCode());
				// 重新算时间
				setTime(pmts);
				// 修改数据库值
				for (PlaMacTask pmt : pmts) {
					dao.updateByCon(pmt, false);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * 计算每个工序的开始时间和结束时间
	 * 
	 * @param plaMacTask
	 * @return
	 * @throws ParseException
	 */
	public void setTime(List<PlaMacTask> pmts) throws ParseException {

		// 开始排
		for (int i = 0; i < pmts.size(); i++) {
			if (pmts.get(i).getPstime() != null) {
				continue;
			}
			if (i == 0) {// 如果是第一步
				setFirstSeqTime(pmts, i);
			} else {
				// 第二步开始，如果该工序的step= before step 说明是同机台完成，时间与上工序一致
				if (i > 0 && pmts.get(i).getStep() == pmts.get(i - 1).getStep()) {
					pmts.get(i).setPstime(pmts.get(i - 1).getPstime());
					pmts.get(i).setPdtime(pmts.get(i - 1).getPdtime());
				} else {
					// 右出现ls，按照首步骤处理
					if (pmts.get(i).getSeqcode().equalsIgnoreCase("ls")) {
						setFirstSeqTime(pmts, i);
					} else {
						setBeginEnd((Timestamp) pmts.get(i - 1).getPdtime(),
								pmts, i);
					}

				}
			}

		}
	}

	/**
	 * 设置第二步开始工序的开始和结束时间。
	 * 
	 * @param lastSeqEndTime
	 * @param pmt
	 */
	public void setBeginEnd(Timestamp lastSeqEndTime, List<PlaMacTask> pmts,
			int index) {
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
	public void setFirstSeqTime(List<PlaMacTask> pmts, int index) {
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
		if (currerTime.compareTo(comperTime1) == -1) {
			sql = "select   coalesce( to_char(max(pdtime),'YYYY-MM-DD HH24:MI:SS'), to_char(current_date,'YYYY-MM-DD')||' 20:00:00')"
					+ " from pla_mac_task where maccode='" + macCode + "'";
		}
		if (currerTime.compareTo(comperTime1) == 1
				&& currerTime.compareTo(comperTime2) == -1) {
			sql = "select   coalesce( to_char(max(pdtime),'YYYY-MM-DD HH24:MI:SS'), to_char(current_date+1,'YYYY-MM-DD')||' 08:00:00')"
					+ " from pla_mac_task where maccode='" + macCode + "'";
		}
		if (currerTime.compareTo(comperTime0) == -1) {
			sql = "select   coalesce( to_char(max(pdtime),'YYYY-MM-DD HH24:MI:SS'), to_char(current_date,'YYYY-MM-DD')||' 08:00:00')"
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
		if (reTime.before(comperTime0)) {
			reTime = comperTime0;
		}
		if (reTime.after(comperTime0) && reTime.before(comperTime2)) {
			reTime = comperTime3;
		}
		return reTime;
	}

	public String saveWorkData(HttpServletRequest request, String data) throws Exception {
		ApacheMqSender sender;
		PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
		String topic = propertyFileUtil.getProp("JT_TOPIC_PERSISTER");
		String msg = "失败";
		Gson gson = new Gson();
		List<MessageObjectVo> fromJson = gson.fromJson(data,
				new TypeToken<List<MessageObjectVo>>() {
				}.getType());
		List<MessageObjectVo> list = new ArrayList<>();
		if (fromJson.size() > 0) {
			String macCode = null;
			for (MessageObjectVo messageObjectVo : fromJson) {
				if (null != messageObjectVo) {
					// MauProcessTemp temp = new MauProcessTemp();
					String axis_id = messageObjectVo.getAxis_id();
					String machine_id = messageObjectVo.getMachine_id();
					String date = messageObjectVo.getDate();

					String msg_type = messageObjectVo.getMsg_type();
					if (null != axis_id && !"".equals(axis_id)) {
						messageObjectVo.setRfids(axis_id);
						// temp.setRfidnumber(axis_id);
					}
					if (null != machine_id && !"".equals(machine_id)) {

						// macCode = machine_id.substring(1, 3);

						String areaName = Smdao.getAreaName(machine_id);
						if (areaName == null) {
							return "卡号未初始化";
						}
						macCode = areaName;
						messageObjectVo.setMacCode(macCode);
					}

					// temp.setMac_remark(macRemark);
					if (msg_type.equals("axis_begin")) {
						// temp.setType("放线");
						/*
						 * dao.updatePlaSeqMaterDetail(axis_id, macRemark);
						 * dao.updateFactTimeFromPlan( new
						 * Timestamp(Long.parseLong(date)), macRemark);
						 */
						messageObjectVo.setMsgType("放线");

					} else if (msg_type.equals("axis_end")) {
						// temp.setType("收线");
						messageObjectVo.setMsgType("收线");
						/*
						 * dao.updateFactEndTime( new
						 * Timestamp(Long.parseLong(date)), macRemark);
						 */
						// dao.updatePlaSeqMaterDetail(str);
					}
					// 根据机台代号去mau_machine机台表查询出机台的编码 名称 状态 类型
					/*
					 * List<MauMachine>
					 * listMachine=dao.getMachineById(substring);
					 * if(null!=listMachine&&listMachine.size()>0){ MauMachine
					 * mauMachine = listMachine.get(0);
					 * messageObjectVo.setMacCode(mauMachine.getMacCode());
					 * messageObjectVo.setMacName(mauMachine.getMacName());
					 * messageObjectVo .setMacState(mauMachine.getMacState());
					 * messageObjectVo.setMacType(mauMachine.getMacType()); }
					 */
					if (null != date && !"".equals(date)) {
						messageObjectVo.setNewDate(new Timestamp(Long
								.parseLong(date)));
						// temp.setSavedate(new
						// Timestamp(Long.parseLong(date)));
					}
					// 将传入的信息保存到临时表中
					// dao.save(temp);
					list.add(messageObjectVo);

					//  将线盘的RFID与系统线轴编码绑定 JS
					PlaMacTask macTask = plaMacTaskAxisParamDAO
							.getPlaMacTask(macCode);
					if(macTask==null){
						return msg;
					}
					PlaMacTaskAxisParam axisParam = plaMacTaskAxisParamDAO
							.getPlaMacTaskAxisParamToRfid(
									macTask.getWorkcode(), macTask.getMaccode());
					if(axisParam==null){
						return msg;
					}
					axisParam.setRfid(axis_id);
					dao.updateByCon(axisParam, false);
					msg = "成功";

				}
			}

			sender = ApacheMqSender.getInstance();
			// 将转换的VO发个消息队列
			for (MessageObjectVo vo : list) {
				String mac = vo.getMacCode();
				String voStr = gson.toJson(vo);
				// String voStr=vo.toString();
				sender.sendMsg_topic_persist(topic, mac, voStr);
			}
			sender.close();
		}
		return msg;
	}

	public void reSetMac(List<PlaMacTask> pmts, String ggxh, PlaMacTask pmt,
			String newMacCode) throws Exception {
		// 获取上一步的收线轴以及下部的放线轴
		boolean bool = true;
		String putAxis = "";// 放线轴等于前步骤的收线轴
		String reAxis = "";// 收线轴等于后步骤的放线轴

		for (int i = 0; i < pmts.size(); i++) {
			if (pmts.get(i).equals(pmt)) {
				putAxis = pmts.get(i - 1).getReaxistype();
				reAxis = pmts.get(i + 1).getPuttype();
			}
		}
		// 新机台是否满足轴类型的要求
		String sql1 = "select COUNT(*) from mau_axis_mac where "
				+ "axis_name='" + putAxis
				+ "' and axis_type='放线轴' and mac_code='" + newMacCode + "'";

		String sql2 = "select COUNT(*) from mau_axis_mac where "
				+ "axis_name='" + reAxis
				+ "' and axis_type='收线轴' and mac_code='" + newMacCode + "'";

		List<Object> list1 = dao.createSQLQuery(sql1).list();
		List<Object> list2 = dao.createSQLQuery(sql2).list();
		if (list1.size() == 0 || list2.size() == 0) {
			bool = false;
			throw new Exception("机台" + newMacCode + "不符合线轴要求，需要满足放线轴为："
					+ putAxis + "并且收线轴为：" + reAxis + "的机台");
		}

		// 判断该机台符合线径要求
		ArrayList<String> seqs = new ArrayList<String>();
		ArrayList<Integer> steps = new ArrayList<Integer>();
		dao.getSeqs(ggxh, seqs, steps);
		float diameter = dao.getDiameterVaule(ggxh, steps, seqs, pmt.getStep());

		String sql = "select mac_code from mau_machine a "
				+ "where a.seq_code='" + pmt.getSeqcode()
				+ "' and mac_code = '" + newMacCode + "' "
				+ "and  mac_state in('运行','关闭') " + "and diameter_min <"
				+ diameter + " and diameter_max >=" + diameter;

		// 该工序的所有机台 条件：与工序（机台类型）相关，线径大小，机台状态
		List<String> macList = dao.createSQLQuery(sql).list();
		if (macList.size() == 0) {
			bool = false;
			throw new Exception("机台" + newMacCode + "不符合线径大小");
		}
		if (bool) {
			pmt.setMaccode(newMacCode);
		}
	}

/**
 * 获取某机台的理论满速度
 * @param macCode
 * @param seq
 * @param step
 * @param nextseq 下道工序
 * @param ggxh
 * @throws Exception
 */
	public void getMacFullSpeed(String macCode,String seq,int step ,String nextseq,  String ggxh) throws Exception {

			
			String fAlphabet = macCode.substring(0, 1);
			
			float speed = 0;
			//首先从mau_machine_employee里查询速度
			float dia = 0.0f;
			float  tmpdia=		dao.getDiameterVaule2(
					ggxh, step, seq);
			dia = (float)(Math.round(tmpdia*100))/100;
			String sql="";
		
					ArrayList<MacSpeedParam> msps = new ArrayList<MacSpeedParam>();
					sql = "Select speed,mater,quotiety,target_diameter  "
							+ "from mau_speed_quotiety where mac_code='" + macCode
							+ "'  order by target_diameter";
					 List<Object> list = dao.createSQLQuery(sql).list();
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
										+ "where pro_ggxh='" + ggxh + "' and seq_step="
										+ step;
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
										+ "where pro_ggxh='" + ggxh + "' and seq_step="
										+ step;
								list = dao.createSQLQuery(sql).list();
								for (Object ob : list) {
									if (ob != null) {
										stand_weight = (float) ob;
									}
								}

								if (stand_weight > 0) {
									for (int j = 0; j < msps.size(); j++) {
										if (dao.getMaterValue(ggxh,
												seq, step).equalsIgnoreCase(
												msps.get(j).mater)) {
											speed = (int) (msps.get(j).quotiety / stand_weight);
										}
									}
								}

							}
							if (macCode.equalsIgnoreCase("DE")) {
								// 获取目标线径
								 dia = dao.getDiameterVaule2(
										ggxh, step, seq);
								if (dia == 0) {
									throw new Exception(ggxh + "对应的工序" + seq
											+ ",bom表缺少直径值");
								}
								
							}

						}

						if (fAlphabet.equalsIgnoreCase("S")
								|| fAlphabet.equalsIgnoreCase("C")
								|| fAlphabet.equalsIgnoreCase("T")) {
							// 获取绞距
							float lay = dao.getLayValue(ggxh, seq,
									step);
							if (lay == 0) {
								throw new Exception(ggxh + "对应工序" + seq + "bom表缺少绞距值");
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
												float d1 = Float.valueOf(dia1[0]);
												float d2 = Float.valueOf(dia1[1]);
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

							}

							else if (macCode.equalsIgnoreCase("CC")
									|| macCode.equalsIgnoreCase("CB")) {
								
								if (nextseq.equalsIgnoreCase("bd") ) {
									String mater = "有包带";
									for (int j = 0; j < msps.size(); j++) {
										if (mater.equalsIgnoreCase(msps.get(j).mater)) {
											speed = msps.get(j).quotiety * lay;
										}
									}
								} else {
									String mater = "无包带";
									for (int j = 0; j < msps.size(); j++) {
										if (mater.equalsIgnoreCase(msps.get(j).mater)) {
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
									+ "where pro_ggxh='" + ggxh + "' and seq_step="
									+ step;
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
									+ "FROM cra_" + seq
									+ "_bom_param a, store_material_basic_info b "
									+ "where a. pro_ggxh='" + ggxh
									+ "' and a.seq_step=" + step
									+ " and a.mater = b.mater_ggxh";
							list.clear();
							list = dao.createSQLQuery(sql).list();
							for (Object ob : list) {
								if (ob != null) {
									Object[] obj = (Object[]) ob;
									if (obj[0] != null) {
										mater = (String) obj[0];
									} else {
										throw new Exception(ggxh + "对应工序" + seq
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
									if (mater.equalsIgnoreCase(msps.get(j).mater)) {
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
	
}
