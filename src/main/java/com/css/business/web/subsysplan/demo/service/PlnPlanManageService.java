package com.css.business.web.subsysplan.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlnPlan;
import com.css.business.web.subsysplan.demo.dao.PlnPlanManageDao;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.dubbointerface.subsysplan.PlanManagerInter;

@Service("plnPlanManagerService")
@Component  
public class PlnPlanManageService extends BaseEntityManageImpl<PlnPlan, PlnPlanManageDao> implements PlanManagerInter{
	@Resource(name="plnPlanDao")
	private PlnPlanManageDao dao;
	
	//@Reference
	//@Autowired
	//@Resource(name="plan_manuModule1ManageService")
	//private ManuManagerInter plan_manuModule1ManageService;

	@Override
	public PlnPlanManageDao getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public List<PlnPlan> getPlanListByName(String name) {
		// TODO Auto-generated method stub
		PlnPlan en = new PlnPlan();
		en.setId(1);
		en.setName("12333");
		
		List<PlnPlan> lst = new ArrayList<PlnPlan>();
		lst.add(en);
		return lst;
	}
	
	public String getDemoMsg(){
		//String str = plan_manuModule1ManageService.getStrDemao();
		String str = "sdfd";
		return str;
	}
	
	public static void main(String[] args){
		String tmp="0";

		String[] dia = tmp.split("x");
		System.out.println(dia.length);
	}

}
