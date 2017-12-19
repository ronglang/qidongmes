package com.css.business.web.subsysplan.plaManage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.css.business.web.subsyscraft.bean.CraRoute;
import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteSeqManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysplan.bean.PlaAbnormalInfor;
import com.css.business.web.subsysplan.bean.PlaMachinePlan;
import com.css.business.web.subsysplan.plaManage.bean.PlaContractVo;
import com.css.business.web.subsysplan.plaManage.dao.PlaAbnormalInforManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanManageDAO;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

public class SchedulingServiceImpl extends BaseEntityManageImpl<PlaAbnormalInfor, PlaAbnormalInforManageDAO> implements SchedulingInterface{

	@Autowired
	private PlaAbnormalInforManageDAO plaAbnormalInforManageDAO;//没有实际意义
	
	@Autowired
	private CraRouteSeqManageDAO craRouteSeqManageDAO;//工艺路线明细-对应的工艺、工序
	
	@Autowired
	private CraRouteManageDAO craRouteManageDAO;//工艺路线表
	
	@Autowired
	private MauMachineManageDAO mauMachineManageDAO;//机台信息表
	
	@Autowired
	private PlaMachinePlanManageDAO plaMachinePlanManageDAO;//机台计划信息表
	
	@Resource(name="craSeqManageDAO")
	private CraSeqManageDAO craSeqManageDAO;
	
	
	@Override
	public PlaAbnormalInforManageDAO getEntityDaoInf() {
		return plaAbnormalInforManageDAO;
	}
	
	@Override
	public HashMap<String, Object> getMachineRoute(PlaContractVo bean) {
		//得到交货时间
		Date nowDate = new Date();
		Date deliveDate = bean.getDeliveDate();
		
		// TODO 取得该合同这个产品的工艺路线
		Integer crsId = bean.getCrsID();//工艺路线id
		CraRoute craRoute = craRouteManageDAO.get(crsId);
		String routeCode = craRoute.getProGgxh();
		
		//获取工艺路线明细内容
		List<CraRouteSeq> craRouteSeqList = craRouteSeqManageDAO.getHibernateTemplate().find(" from CraRouteSeq where routeCode = ? ",routeCode);
		
		List<String> seqNameList = new ArrayList<String>();
		//TODO 取得工序名称集合
		for(CraRouteSeq seq : craRouteSeqList){
			String seqName = craSeqManageDAO.getNameByCode(seq.getSeqCode());
			seqNameList.add(seqName);
		}
		//TODO 根据工序名称集合取得可用的理论机台信息集合
		List<MauMachine> macList = mauMachineManageDAO.getHibernateTemplate().find(" from MauMachine where macType in (?) ",seqNameList);
//		List<Integer> macIdList = new ArrayList<Integer>();
//		for(MauMachine m : macList){
//			macIdList.add(m.getId());
//		}
		//TODO 从已有的机台计划中筛选出空闲的机台,如何判断哪些机台可用？或者在哪一天可用？
		//可用机台分为两种情况：1.完全空闲，来的这个合同，可以从当前日期，任意排布机台计划，2.以后才空闲，在当前日期与交货日期之间的也可以排产，
		//1.TODO 只考虑完全空闲的机台，查询出计划完成日期在当前日期与交货日期之间的机台计划信息
		List<PlaMachinePlan> plaMachinePlanList = plaMachinePlanManageDAO.getHibernateTemplate().find(" from PlaMachinePlan where planEndTime >= ? and planEndTime <= ? ",new Object[]{nowDate,deliveDate});
		//筛选出完全空闲的机台
		//该集合记录完全空闲的机台
		List<MauMachine> macfreeList = new ArrayList<MauMachine>();
		//该集合记录在当前日期与交货日期之间空闲的机台
//		List<MauMachine> macfreeList_2 = new ArrayList<MauMachine>();
		Map<Long,MauMachine> macfreeMap = new HashMap<Long,MauMachine>();
		
		for (int i = 0; i < macList.size(); i++) {
			MauMachine mac = macList.get(i);
			Integer macId = mac.getId();
			boolean boo = false;
			long longdate = nowDate.getTime();//当前日期毫秒数
			for(PlaMachinePlan plan : plaMachinePlanList){
				Integer machineId = plan.getMachineId();
				if(macId.intValue() == machineId.intValue()){
					boo = true;
					long planFinishTime = plan.getPlanEndTime().getTime();//计划完成时间
					if(planFinishTime >= longdate && planFinishTime < deliveDate.getTime()){
						longdate = planFinishTime;
					}
				}
			}
			
			if(!boo){//完全空闲的机台
				macfreeList.add(mac);
			}else{//当前日期到交货日期，部分空闲的机台
				macfreeMap.put(longdate, mac);
			}
		}
		Map<Long,MauMachine> resultMap = new HashMap<Long,MauMachine>();
		resultMap.putAll(macfreeMap);
		long index = -1;
		for(MauMachine mac :macfreeList){
			resultMap.put(index--, mac);
		}
		return JsonWrapper.successWrapper(resultMap, "得到空闲机台的信息");
	}

	@Override
	public HashMap<String, Object> getConsumeMaterial(PlaContractVo bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getConsumeWorkHours(PlaContractVo bean,
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
