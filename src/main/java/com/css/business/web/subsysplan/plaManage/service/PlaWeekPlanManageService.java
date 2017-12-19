package com.css.business.web.subsysplan.plaManage.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaWeekPlan;
import com.css.business.web.subsysplan.plaManage.dao.PlaWeekPlanManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaWeekPlanManageService")
public class PlaWeekPlanManageService extends BaseEntityManageImpl<PlaWeekPlan, PlaWeekPlanManageDAO> {
	@Resource(name="plaWeekPlanManageDAO")
	//@Autowired
	private PlaWeekPlanManageDAO dao;
	
	
	@Override
	public PlaWeekPlanManageDAO getEntityDaoInf() {
		return dao;
	}
	
	public Page getPalWeekPlan(Page p,Map<String,String> map){
		String hql = " from PlaWeekPlan where 1=1 ";
		if(map!=null){
				if(map.get("workDay")!=null&&!"".equals(map.get("workDay"))){
					
					hql+=" and workDay ||'' like '%"+map.get("workDay")+"%'";
				}
				if(map.get("state")!=null&&!"".equals(map.get("state"))){
					hql+=" and state like '%"+map.get("state")+"%'";
				}
			
		}
		hql+=" order by workDay desc";
		
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}
	
	public List<PlaWeekPlan> getWeekPlanList(Integer id){
		String hql = " from PlaWeekPlan where id = ? ";
		@SuppressWarnings("unchecked")
		List<PlaWeekPlan> list = dao.createQuery(hql, id).list();
		return list;       
	}
	
	public void updatePlaWeekPlan(PlaWeekPlan plaWeekPlan) throws Exception{
		plaWeekPlan.setState("1");
		dao.updateByCon(plaWeekPlan, false);
	}
	
	public List<String> getStatusListService(){
		String hql = "select state from PlaWeekPlan GROUP BY state";
		@SuppressWarnings("unchecked")
		List<String> list = dao.createQuery(hql).list();
		return list;
	}
	
}
