package com.css.dubbointerface.subsysplan;

import java.util.List;

import com.css.business.web.subsysplan.bean.PlnPlan;

public interface PlanManagerInter {

	public List<PlnPlan> getPlanListByName(String name);
	
}
