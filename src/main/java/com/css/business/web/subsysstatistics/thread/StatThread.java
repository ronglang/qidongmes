
package com.css.business.web.subsysstatistics.thread;

import com.css.business.web.subsysstatistics.service.StatCourseManageService;
import com.css.common.web.syscommon.service.SpringContextHolder;

/**     
 * @Title: StatThread.java   
 * @Package com.css.business.web.subsysstatistics.thread   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author   rb
 * @date 2017年7月25日 下午5:34:53   
 * @company  SMTC   
 */

public class StatThread extends Thread{
	/** 工单编号 */
	private String courseCode;
	/** 机台id */
	private String machineId;
	SpringContextHolder holder = new SpringContextHolder();
	private StatCourseManageService service =  holder.getBean("statCourseManageService");
	
	/**
	 *  @param courseCode 要设置的 courseCode 
	 *    
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 *  @param machineId 要设置的 machineId 
	 *    
	 */
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		saveStatCourse();
	}

	/**   
	 * @Description: 通过工单编号和机台id去查询记录            
	 */ 
	private void saveStatCourse() {
		// TODO Auto-generated method stub
		service.saveStatCourse(courseCode,machineId);
	}

}
