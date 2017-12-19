package com.css.business.web.subsysplan.plaManage.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauTaskLoad;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysplan.bean.PlaMachinePlan;
import com.css.business.web.subsysplan.bean.PlaMachinePlanSchedule;
import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.subsysplan.bean.PlaWeekPlan;
import com.css.business.web.subsysplan.plaManage.bean.PlaProductOrderSeqHourseVo;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductOrderSeqHourseManageDAO;
import com.css.business.web.subsysplan.plaManage.utils.CalculaMaterUtil;
import com.css.business.web.sysManage.bean.SysEmployee;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaProductOrderSeqHourseManageService")
public class PlaProductOrderSeqHourseManageService
		extends
		BaseEntityManageImpl<PlaProductOrderSeqHourse, PlaProductOrderSeqHourseManageDAO> {
	@Resource(name = "plaProductOrderSeqHourseManageDAO")
	// @Autowired
	private PlaProductOrderSeqHourseManageDAO dao;

	@Resource(name = "mauMachineManageDAO")
	private MauMachineManageDAO machineDao;

	@Override
	public PlaProductOrderSeqHourseManageDAO getEntityDaoInf() {
		return dao;
	}

	public Page getPlaProductOrderSeqHourse(Page p, String date,
			String seqName, String macCode) throws Exception {
		/*
		 * String hql = " from PlaProductOrderSeqHourse "; Page page =
		 * dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		 */
		String sql = " SELECT p.ID ID,p.axis_name axisname,c.seq_name seqname,p.pro_ggxh proggxh,"
				+ " p.pro_craft_code procraftcode,p.part_len partlen,p.len_unit lenunit,P.mac_code maccode,"
				+ " s.name employeeidmain,m.name employeeidvice,p.start_date startdate,p.end_date enddate,"
				+ " p.seq_hours seqhours,p.work_day workday,t.hours hours FROM pla_product_order_seq_hourse p "
				+ " LEFT JOIN sys_employee s ON s.id = p.employee_id_main "
				+ " LEFT JOIN sys_employee m ON  m.id = p.employee_id_vice "
				+ " LEFT JOIN mau_task_load t ON t.mac_code = p.mac_code "
				+ " LEFT JOIN cra_seq c ON c.seq_code = p.seq_code where 1=1 ";
		if (date != null && !"".equals(date)) {
			String dateFormat = CalculaMaterUtil.strToDateFormat(date,
					"yyyyMMdd");
			sql += " AND P.work_day = '" + dateFormat + "' ";
		}
		if (seqName != null && !"".equals(seqName)) {
			sql += " AND c.seq_name = '" + seqName + "' ";
		}
		if (macCode != null && !"".equals(macCode)) {
			sql += " AND p.mac_code = '" + macCode + "' ";
		}
		Page page = dao.pageSQLQueryVONoneDesc(sql, p.getPageNo(),
				p.getPagesize(), new PlaProductOrderSeqHourseVo());
		return page;
	}

	public Integer getEmploueeId(String name) {
		String hql = "SELECT id from SysEmployee where name = ? ";
		int id = (int) dao.createQuery(hql, name).list().get(0);
		return id;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getEmployee() {
		String hql = " from SysEmployee where department = '生产部' ";
		List<SysEmployee> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).getName());
				} else {
					map.put("id", i + "");
				}

			}
			str[i] = map;
		}

		return str;
	}

	public void savePlaProductOrderSeqHourse(PlaProductOrderSeqHourse ppos) throws Exception {
		String hql = " from MauMachine where macCode = ? ";
		@SuppressWarnings("unchecked")
		List<MauMachine> list = dao.createQuery(hql, ppos.getMacCode()).list();
		int id = list.get(0).getId();
		BigDecimal seqHours = (BigDecimal) dao.exeFunction(
				"calc_gs_by_machine_axis_name", ppos.getAxisName(), id);// 通过数据库的函数计算工时
		ppos.setSeqHours(seqHours);
		dao.save(ppos);
	}

	public void updatePlaProductOrderSeqHourse(PlaProductOrderSeqHourse ppos)
			throws Exception {
		String hql = " from MauMachine where macCode = ? ";
		@SuppressWarnings("unchecked")
		List<MauMachine> list = dao.createQuery(hql, ppos.getMacCode()).list();
		int id = list.get(0).getId();
		BigDecimal seqHours = (BigDecimal) dao.exeFunction(
				"calc_gs_by_machine_axis_name", ppos.getAxisName(), id);// 通过数据库的函数计算工时
		ppos.setSeqHours(seqHours);
		dao.updateByCon(ppos, false);
	}

	public void delPlaProductOrderSeqHourse(PlaProductOrderSeqHourse ppos) {
		dao.remove(ppos);
	}

	/**
	 * @Description: 获得需要检测的数据
	 * @param proOrderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaProductOrderSeqHourse> getNeedTest(Integer proOrderId) {
		// TODO Auto-generated method stub
		String hql = " from PlaProductOrderSeqHourse where plaOrderId ='"
				+ proOrderId + "' and isSample ='是'";
		List<PlaProductOrderSeqHourse> list = dao.createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @Description: 通过轴名称和工序code 获取产品规格型号
	 * @param axisName
	 * @param seqCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PlaProductOrderSeqHourse getGGXHByAxisname(String axisName,
			String seqCode) {
		// TODO Auto-generated method stub
		String hql = "from PlaProductOrderSeqHourse where 1 = 1 and axisName = '"
				+ axisName + "' and seqCode ='" + seqCode + "'";
		List<PlaProductOrderSeqHourse> list = dao.createQuery(hql).list();
		if (list.size() > 0 && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public HashMap<String, Object> savePlan(SysUser user,PlaProductOrderSeqHourse pos,
			Integer nowDate) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		PlaProductOrderSeqHourse po = this.getHours(pos.getId()); // 当前计划数据
		MauTaskLoad mauTaskLoad = this.getMauTaskLoad(pos.getMacCode(),
				pos.getWorkDay()); //目标机台负荷
		MauTaskLoad taskLoad = this.getMauTaskLoad(po.getMacCode(), nowDate); // 获取当前机台负荷
		if(taskLoad==null){
			return JsonWrapper.failureWrapperMsg(null, "机台负荷不存在!");
		}else{
			boolean flag = true;
			boolean flag2 = false;
			int day = pos.getWorkDay();
			if (day != nowDate) {
				map = this.ChangeWeekPlan(pos, po);
				flag = (boolean) map.get("success");
				if (!(boolean) map.get("success")) {
					return map;
				}
			} 
			// 4.1机台计划改变 4.2机台计划进度改变
			map = this.ChangeMachiePlan(pos, po);
			flag2 = (boolean) map.get("success");
			if (!(boolean) map.get("success")) {
				return map;
			}
			// 3.周计划改变
			// 6.计划材料明细
			if (flag && flag2) {
				// 1.将负荷表的负荷改变(改变目标数据与当前数据)
				taskLoad.setHours(new BigDecimal(taskLoad.getHours().doubleValue()
						- po.getSeqHours().doubleValue() > 0 ? taskLoad.getHours()
						.doubleValue() - po.getSeqHours().doubleValue() : -1));
				if(mauTaskLoad==null){
					mauTaskLoad = new MauTaskLoad();
					mauTaskLoad.setHours(new BigDecimal(po.getSeqHours().doubleValue()));
					mauTaskLoad.setMacCode(pos.getMacCode());
					MauMachine machine = this.getMauMachineByCode(pos.getMacCode());
					mauTaskLoad.setObjId(machine.getId());
					mauTaskLoad.setWorkDay(pos.getWorkDay());
					mauTaskLoad.setCreateBy(user.getAccount());
					mauTaskLoad.setCreateDate(new Date());
					mauTaskLoad.setType("机台");
					mauTaskLoad.setStatus("是");
					dao.save(mauTaskLoad);
				}else{
					mauTaskLoad.setHours(new BigDecimal(po.getSeqHours().doubleValue()
							+ mauTaskLoad.getHours().doubleValue()));
					dao.updateByCon(mauTaskLoad, false);// 目标数据
				}
				dao.updateByCon(taskLoad, false); // 当前数据
				// 2.主表数据改变
				pos.setSeqHours(po.getSeqHours());
				dao.updateByCon(pos, false);
			}
			return map;
		}
		
	}
	
	
	public MauMachine getMauMachineByCode(String macCode){
		String hql = " from MauMachine where macCode = ? ";
		@SuppressWarnings("unchecked")
		List<MauMachine> list = dao.createQuery(hql, macCode).list();
		return list.get(0);
	}
	

	/**
	 * 机台计划调度与机台计划进度调度
	 * 
	 * @param pos
	 *            当前计划数据
	 * @param po
	 *            目标计划数据
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> ChangeMachiePlan(
			PlaProductOrderSeqHourse po, PlaProductOrderSeqHourse pos)
			throws Exception {
		// 1. 机台计划调度
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MauMachine nowMachine = machineDao.getByMacCode(pos.getMacCode()); // 获取当前机台信息
		MauMachine targetMachine = machineDao.getByMacCode(po.getMacCode()); // 获取目标机台信息
		String hql = " from PlaMachinePlan where machineId = ? and axisName = ? and seqCode = ? ";
		// 获取到当前线轴的机台计划
		PlaMachinePlan machinePlan = new PlaMachinePlan();
		List<PlaMachinePlan> list2 = dao.createQuery(hql, nowMachine.getId(),
				pos.getAxisName(), pos.getSeqCode()).list();
		if (list2 == null || list2.size() < 1) {
			return JsonWrapper.failureWrapperMsg(null, "当前线轴机台计划不存在");
		} else {
			machinePlan = list2.get(0);
			// 获取到目标机台计划
			String hql2 = " from PlaMachinePlan where machineId = "
					+ targetMachine.getId() + " and workDay = " + po.getWorkDay()
					+ " " + "order by planEndTime desc ";
			List<PlaMachinePlan> list = dao.createQuery(hql2).list();
			PlaMachinePlan macPlan = new PlaMachinePlan();
			Long time = (long) (po.getSeqHours().doubleValue() * 3600000);
			// 查询周计划目标id
			String hql4 = "  from PlaWeekPlan where workDay = ?  ";
			List<PlaWeekPlan> week = dao.createQuery(hql4, po.getWorkDay()).list();
			if (list != null && list.size() > 0) {
				macPlan = list.get(0);
				// 设置调度后的机台与日期
				machinePlan.setMacCode(targetMachine.getMacCode());
				machinePlan.setWorkDay(po.getWorkDay());
				machinePlan.setPlanStartTime(macPlan.getPlanEndTime()); // 调度线轴的开工时间为当前机台最后线轴的结束时间
				machinePlan.setPlanEndTime(new Timestamp(time
						+ macPlan.getPlanEndTime().getTime()));// 计算结束时间
				if (week != null && week.size() < 1) {
					machinePlan.setWeekPlanId(week.get(0).getId());
				} else {

				}
				dao.updateByCon(machinePlan, false);

			} else {
				// 设置调度后的机台与日期
				//machinePlan.setId(null);
				machinePlan.setMacCode(targetMachine.getMacCode());
				machinePlan.setWorkDay(po.getWorkDay());
				machinePlan.setPlanStartTime(Timestamp.valueOf(CalculaMaterUtil
						.strToDateFormat(po.getWorkDay().toString(), "yyyy-MM-dd")
						+ " 08:00:00")); // 调度线轴的开工时间为当前机台最后线轴的结束时间
				machinePlan.setPlanEndTime(new Timestamp(time
						+ machinePlan.getPlanEndTime().getTime()));// 计算结束时间
				dao.updateByCon(machinePlan, false);
			}

			// 2. 机台计划进度调度
			String hql3 = " from PlaMachinePlanSchedule where machinePlanId = ? ";
			PlaMachinePlanSchedule nowSchedule = new PlaMachinePlanSchedule();
			List<PlaMachinePlanSchedule> list3 = dao.createQuery(hql3,
					machinePlan.getId()).list(); // 获取当前机台计划进度数据
			if (list3 == null || list3.size() < 1) {
				return JsonWrapper.failureWrapperMsg(null, "当期机台计划进度数据不存在");
			} else {
				nowSchedule = list3.get(0);
				String da = CalculaMaterUtil.strToDateFormat(
						po.getWorkDay().toString(), "yyyy-MM-dd");
				Date date = sdf.parse(da);
				nowSchedule.setWorkDate(date);
				nowSchedule.setMachineId(targetMachine.getId());
				nowSchedule.setPlanStartTime(macPlan.getPlanEndTime());
				nowSchedule.setPlanEndTime(new Timestamp(time
						+ nowSchedule.getPlanEndTime().getTime()));

				if (week != null && week.size() > 0) {
					nowSchedule.setWeekPlanId(week.get(0).getId());
					dao.updateByCon(nowSchedule, false);
				} else {
					return JsonWrapper.failureWrapperMsg(null, "目标周计划不存在");
				}
				return JsonWrapper.successWrapper(null, "true");
			}

		}
		
		
	}

	public PlaProductOrderSeqHourse getHours(Integer id) {
		String hql = " from PlaProductOrderSeqHourse where id = ? ";
		PlaProductOrderSeqHourse pos = (PlaProductOrderSeqHourse) dao
				.createQuery(hql, id).list().get(0);
		return pos;
	}

	@SuppressWarnings("unchecked")
	public MauTaskLoad getMauTaskLoad(String macCode, Integer workDay) {
		String hql = " from MauMachine where macCode = ?  ";
		List<MauMachine> list = dao.createQuery(hql, macCode).list();
		List<MauTaskLoad> load = new ArrayList<MauTaskLoad>();
		for (MauMachine mauMachine : list) {
			String hql1 = " from MauTaskLoad where objId = ? and workDay = ? ";
			load = dao.createQuery(hql1, mauMachine.getId(), workDay).list();
		}
		if(load==null || load.size()<1){
			return null;
		}else{
			
			return load.get(0);
		}
	}

	/**
	 * 周计划调度
	 * 
	 * @param pos
	 *            调度计划数据
	 * @param po
	 *            当前计划数据
	 * @param nowDate
	 *            当前日期
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> ChangeWeekPlan(PlaProductOrderSeqHourse pos,
			PlaProductOrderSeqHourse po) throws Exception {
		String hql = " from PlaWeekPlan where workDay = ? ";
		PlaWeekPlan plaWeekPlan = new PlaWeekPlan();
		List<PlaWeekPlan> list = dao.createQuery(hql, po.getWorkDay()).list(); // 获取当前周计划数据
		if (list == null || list.size() < 1) {
			return JsonWrapper.failureWrapperMsg(null, "当前周计划数据链不存在");
		} else {
			plaWeekPlan = list.get(0);
			PlaWeekPlan pwp = new PlaWeekPlan();
			List<PlaWeekPlan> list2 = dao.createQuery(hql, pos.getWorkDay()).list();// 获取目标周计划数据
			
			if (list2 == null || list2.size() < 1) {
				//目标周计划不存在是，则新增
				if ("SEQ_001".equals(po.getSeqCode())) {
					plaWeekPlan.setDrawbenchTime(new BigDecimal(plaWeekPlan
							.getDrawbenchTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setDrawbenchTime(new BigDecimal(po.getSeqHours().doubleValue()));
				} else if ("SEQ_002".equals(po.getSeqCode())) {
					plaWeekPlan.setStrandTime(new BigDecimal(plaWeekPlan
							.getStrandTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setStrandTime(new BigDecimal(po.getSeqHours().doubleValue()));
				} else if ("SEQ_003".equals(po.getSeqCode())) {
					plaWeekPlan.setInsulationTime(new BigDecimal(plaWeekPlan
							.getInsulationTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setInsulationTime(new BigDecimal(po.getSeqHours().doubleValue()));
				} else if ("SEQ_005_01".equals(po.getSeqCode())
						|| "SEQ_005_02".equals(po.getSeqCode())) {
					plaWeekPlan.setArmoredTime(new BigDecimal(plaWeekPlan
							.getArmoredTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setArmoredTime(new BigDecimal(po.getSeqHours().doubleValue()));

				} else if ("SEQ_004".equals(po.getSeqCode())) {
					plaWeekPlan.setCableTime(new BigDecimal(plaWeekPlan.getCableTime()
							.doubleValue() - po.getSeqHours().doubleValue()));
					pwp.setCableTime(new BigDecimal(po.getSeqHours().doubleValue()));

				} else if ("SEQ_006".equals(po.getSeqCode())) {
					plaWeekPlan.setSheathTime(new BigDecimal(plaWeekPlan
							.getSheathTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setSheathTime(new BigDecimal(po.getSeqHours().doubleValue()));

				} else if ("SEQ_007".equals(po.getSeqCode())) {
					plaWeekPlan.setStrandSinsulationTime(new BigDecimal(plaWeekPlan
							.getStrandSinsulationTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setStrandSinsulationTime(new BigDecimal(po.getSeqHours().doubleValue()));
				}
				pwp.setCreateDate(new Date());
				pwp.setProductOrderId(plaWeekPlan.getId());
				pwp.setAxisAmount(plaWeekPlan.getAxisAmount());
				pwp.setPartLen(plaWeekPlan.getPartLen());
				pwp.setState(plaWeekPlan.getState());
				pwp.setIs_flag(plaWeekPlan.getIs_flag());
				pwp.setWorkDay(po.getWorkDay());
				pwp.setWorkStartDate(Timestamp.valueOf(CalculaMaterUtil
						.strToDateFormat(po.getWorkDay().toString(), "yyyy-MM-dd")
						+ " 08:00:00"));
				dao.save(pwp);
				dao.updateByCon(plaWeekPlan, false); // 当前周计划更新
				//return JsonWrapper.failureWrapperMsg(null, "目标周计划数据链不存在");
			} else {
				pwp = list2.get(0);
				if ("SEQ_001".equals(po.getSeqCode())) {
					plaWeekPlan.setDrawbenchTime(new BigDecimal(plaWeekPlan
							.getDrawbenchTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setDrawbenchTime(new BigDecimal(pwp.getDrawbenchTime()
							.doubleValue() + po.getSeqHours().doubleValue()));
				} else if ("SEQ_002".equals(po.getSeqCode())) {
					plaWeekPlan.setStrandTime(new BigDecimal(plaWeekPlan
							.getStrandTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setStrandTime(new BigDecimal(pwp.getStrandTime().doubleValue()
							+ po.getSeqHours().doubleValue()));
				} else if ("SEQ_003".equals(po.getSeqCode())) {
					plaWeekPlan.setInsulationTime(new BigDecimal(plaWeekPlan
							.getInsulationTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setInsulationTime(new BigDecimal(pwp.getInsulationTime()
							.doubleValue() + po.getSeqHours().doubleValue()));
				} else if ("SEQ_005_01".equals(po.getSeqCode())
						|| "SEQ_005_02".equals(po.getSeqCode())) {
					plaWeekPlan.setArmoredTime(new BigDecimal(plaWeekPlan
							.getArmoredTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setArmoredTime(new BigDecimal(pwp.getArmoredTime()
							.doubleValue() + po.getSeqHours().doubleValue()));

				} else if ("SEQ_004".equals(po.getSeqCode())) {
					plaWeekPlan.setCableTime(new BigDecimal(plaWeekPlan.getCableTime()
							.doubleValue() - po.getSeqHours().doubleValue()));
					pwp.setCableTime(new BigDecimal(pwp.getCableTime().doubleValue()
							+ po.getSeqHours().doubleValue()));

				} else if ("SEQ_006".equals(po.getSeqCode())) {
					plaWeekPlan.setSheathTime(new BigDecimal(plaWeekPlan
							.getSheathTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setSheathTime(new BigDecimal(pwp.getSheathTime().doubleValue()
							+ po.getSeqHours().doubleValue()));

				} else if ("SEQ_007".equals(po.getSeqCode())) {
					plaWeekPlan.setStrandSinsulationTime(new BigDecimal(plaWeekPlan
							.getStrandSinsulationTime().doubleValue()
							- po.getSeqHours().doubleValue()));
					pwp.setStrandSinsulationTime(new BigDecimal(pwp
							.getStrandSinsulationTime().doubleValue()
							+ po.getSeqHours().doubleValue()));
				}
				dao.updateByCon(plaWeekPlan, false); // 当前周计划更新
				dao.updateByCon(pwp, false); // 目标周计划更新
			}
			return JsonWrapper.successWrapper(null, "true");
		}
		

	}

}
