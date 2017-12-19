package com.css.common.web.apachemq.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.common.web.apachemq.service.QueueSendService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;

/**
 * @TODO  : jms配置文件中先没配发送的配置项、 这里先注掉。需要时在配件文件中添加 
 * @author: 翟春磊
 * @DATE  : 2017年8月17日
 */
//@Controller
//@RequestMapping("/queueSendAction")
public class QueueSendAction extends BaseSpringSupportAction<SysMenu, QueueSendService>{

	@Resource(name="queueSendService")
	private QueueSendService service;
	
	@RequestMapping({ "toActiveMqWrite" })
	public String toActiveMqWrite(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "apache/activeMqWrite";
	}
	
	@RequestMapping({ "sendQueueMethod1" })
	@ResponseBody
	public HashMap<String, Object> sendQueueMethod1(HttpServletRequest request, SysMenu entity)
			throws Exception {
		this.log.debug("开始执行保存方法");
		try {
			if(entity == null) entity = new SysMenu();
			service.sendQueueMethod1(entity);
			this.log.debug("发送成功");
			return JsonWrapper.successWrapper(entity,"发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	@RequestMapping({ "sendQueueMethodSession" })
	@ResponseBody
	public HashMap<String, Object> sendQueueMethodSession(HttpServletRequest request, SysMenu entity)
			throws Exception {
		this.log.debug("开始执行保存方法");
		try {
			if(entity == null) entity = new SysMenu();
			service.sendSessionQueueMethod1(entity);
			this.log.debug("发送成功");
			return JsonWrapper.successWrapper(entity,"发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	
	@Override
	public QueueSendService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}

	public QueueSendService getService() {
		return service;
	}

	public void setService(QueueSendService service) {
		this.service = service;
	}

	
}
