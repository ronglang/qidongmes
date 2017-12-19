package com.css.common.web.apachemq.handle;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.css.business.web.subsyscraft.craManage.dao.CraCmaterManageDAO;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.syscommon.service.SpringContextHolder;

/**
 * @TODO  : web端读手持机的消息。不再使用 
 * @author: 翟春磊
 * @DATE  : 2017年7月18日
 */
@Deprecated
public class MesApacheMqReaderHandler_ShouChiJi implements Serializable,MessageListener{
	private static final long serialVersionUID = 4372634148548608382L;
	
	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_ShouChiJi.class);
	
	private PropertyFileUtil pfu = new PropertyFileUtil();
	//private WebApplicationContext wac;
	
	@SuppressWarnings("unused")
	private CraCmaterManageDAO craCmaterManageDAO ;
	
	private static final String BROKER_URL="failover://tcp://192.168.0.2:61615";
	private Connection conn;
	private Session session;
	
	public MesApacheMqReaderHandler_ShouChiJi(final WebApplicationContext wac){
		//this.wac = wac;
		this.init();
	}
	
	private void init(){
		try {
			log.info("MesApacheMqReaderHandler_ShouChiJi Web端apacheMq监听启动");
			
			//craCmaterManageDAO = (CraCmaterManageDAO)wac.getBean("craCmaterManageDAO");
			craCmaterManageDAO = (CraCmaterManageDAO)SpringContextHolder.getBean("craCmaterManageDAO");
			
			//创建连接工厂
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
			//获得连接
			conn = connectionFactory.createConnection();
			conn.setClientID(YorkUtil.QUEUE_TOPIC_WEB_SYSTEM_ID);
			//start
			conn.start();

			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			createWebSubSystemQueueReader_TOPIC_PERSIST();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * @TODO: 持久订阅
	 * @author: zhaichunlei
	 & @DATE : 2017年7月17日
	 */
	private void createWebSubSystemQueueReader_TOPIC_PERSIST(){
		// 创建destination
		Topic topic;
		try {
			String topic_name = pfu.getProp("QUE_TOPIC_PERSIST_SHOUCHIJI");//Topic名称
			topic = session.createTopic(topic_name);
			//MessageConsumer consumer = session.createConsumer(topic); //普通订阅
			MessageConsumer consumer = session.createDurableSubscriber(topic,YorkUtil.QUEUE_TOPIC_WEB_SYSTEM_ID); //持久订阅
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage txtMessage = (TextMessage) msg;
		try {
			System.out.println("get message:" + txtMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
