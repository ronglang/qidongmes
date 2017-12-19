package com.css.common.web.apachemq.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.common.web.apachemq.dao.QueueSendDao;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * @TODO  :jsm配置文件中先没配发送的配置项、 这里先注掉。需要时在配件文件中添加 
 * @author: 翟春磊
 * @DATE  : 2017年8月17日
 */
//@Service("queueSendService")
public class QueueSendService extends BaseEntityManageImpl<SysMenu, QueueSendDao>{
	@Resource(name="queueSendDao")
	private QueueSendDao dao;
	
	@Autowired
	private JmsTemplate jmsQueueTemplate;
	
	//private ActiveMQQueue userServiceQueue;
	@Autowired
	@Qualifier("userServiceQueue")
	private Destination destination;  //user.service.queue 
	@Autowired
	@Qualifier("sessionUserServiceQueue")
	private Destination sesiondestination;  //user.service.queue 
	
	@Override
	public QueueSendDao getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}

	
	public void sendQueueMethod1(final SysMenu e){
		e.setCode("成都市" );
		if(jmsQueueTemplate != null){
			log.debug("jmsQueueTemplate成功注入。开始写入队列");
			ExecutorService es =  Executors.newFixedThreadPool(10);  
			es.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					jmsQueueTemplate.send(destination, new MessageCreator(){
						public Message createMessage(Session session) throws JMSException {  
	                        return session.createTextMessage(JSONObject.fromObject(e).toString());  
	                    }  
					});
				}
			});
		}
		else{
			log.debug("jmsQueueTemplate注入失败。");
		}
	}
	
	public void sendSessionQueueMethod1(final SysMenu e){
		e.setCode("成都市" );
		if(jmsQueueTemplate != null){
			log.debug("jmsQueueTemplate成功注入。开始写入队列");
			ExecutorService es =  Executors.newFixedThreadPool(10);  
			es.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					jmsQueueTemplate.send(sesiondestination, new MessageCreator(){
						public Message createMessage(Session session) throws JMSException {  
							return session.createTextMessage(JSONObject.fromObject(e).toString());  
						}  
					});
				}
			});
		}
		else{
			log.debug("jmsQueueTemplate注入失败。");
		}
	}
}
