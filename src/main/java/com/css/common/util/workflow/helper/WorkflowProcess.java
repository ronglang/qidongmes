package com.css.common.util.workflow.helper;

import java.util.List;

/** 
 * @author ������ E-mail:����kaioct@qq.com 
 * @version ����ʱ�䣺2014-11-3 ����02:46:42 
 * ��˵���� 
 */
public class WorkflowProcess {
	private List<Activity> activityList;
	private List<Transition> transitionList;
	
	
	public List<Activity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	public List<Transition> getTransitionList() {
		return transitionList;
	}
	public void setTransitionList(List<Transition> transitionList) {
		this.transitionList = transitionList;
	}
	
}
