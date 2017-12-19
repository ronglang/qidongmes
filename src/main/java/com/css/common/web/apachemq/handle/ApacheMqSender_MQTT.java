package com.css.common.web.apachemq.handle;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.css.common.util.PropertyFileUtil;

/**
 * @TODO  :只用于与手持机通讯，专用MQTT通讯协议替换TCP 
 * @author: 翟春磊
 * @DATE  : 2017年7月22日
 */
public class ApacheMqSender_MQTT {
	private Logger log = Logger.getLogger(ApacheMqSender_MQTT.class);
	
	private static ApacheMqSender_MQTT obj;
	private PropertyFileUtil pfu = new PropertyFileUtil();
	private Connection conn ;
	
	private ApacheMqSender_MQTT(){
		//String url = pfu.getProp("url");
		//String url = "failover\\://tcp\\://localhost\\:61616";
		//String url = "tcp://192.168.0.2:61615";
		//String url = pfu.getProp("APACHE_MQ_URL");
		String url = pfu.getProp("APACHE_MQ_URL_MQTT");
		//创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
		//获得连接
		try {
			conn = connectionFactory.createConnection();
			conn.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @TODO:发送点对点消息
	 * @author: zhaichunlei
	 & @DATE : 2017年7月14日
	 * @param queue 队列的名称
	 * @param msg
	 * @throws JMSException
	 */
	public void sendMsg_point_to_point(String queue,String msg) throws JMSException{
		//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		System.out.println("往消息队列中写入：" + msg);
		//创建队列
		Destination dest = session.createQueue(queue);
		//创建消息生产者
		 MessageProducer producer = session.createProducer(dest);
		TextMessage message = session.createTextMessage(msg);
		//发送消息
		producer.send(message);
		
		producer.close();
		session.close();
		
	}
	
	/**
	 * @TODO:发送点对点消息
	 * @author: zhaichunlei
	 & @DATE : 2017年7月14日
	 * @param queue 队列的名称
	 * @param msg
	 * @throws JMSException
	 */
	public void sendMsg_point_to_point_5秒不接丢弃(String queue,String msg) throws JMSException{
		//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		System.out.println("往消息队列中写入：" + msg);
		//创建队列
		Destination dest = session.createQueue(queue);
		//创建消息生产者
		MessageProducer producer = session.createProducer(dest);
		TextMessage message = session.createTextMessage(msg);
		message.setJMSRedelivered(false); //不重发
		long timeStamp = System.currentTimeMillis();  
	    message.setJMSTimestamp(timeStamp);  //当前时间
	    long expiration = 10 * 1000;  //10秒
	    message.setJMSExpiration(expiration + timeStamp);  //过期时间
		//发送消息
		producer.send(message);
		
		producer.close();
		session.close();
		
	}
	
	/**
	 * @TODO: 订阅模式，持久型。
	 * @author: zhaichunlei
	 & @DATE : 2017年7月14日
	 * @param setType 设备类型：电子看板、手持机、机台HMI. 用首字母大写： KB,SCJ,JTHMI   
	 *         目前只支持手持机.写死了主题名称
	 * @param setId, 设备编码或ID   
	 * @param msg
	 */
	public void sendMsg_topic_persist(String topic_que,String macCode, String msg){//String setId,
		 try {
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//String topic_que = pfu.getProp("QUE_TOPIC_PERSIST_SHOUCHIJI");
			Topic topic = session.createTopic(topic_que);
			MessageProducer producer = session.createProducer(topic);  
	        // NON_PERSISTENT 非持久化 PERSISTENT 持久化,发送消息时用使用持久模式  
	        producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
	        TextMessage message = session.createTextMessage();  
	        message.setText(msg);  
	        message.setJMSCorrelationID(macCode); //指定接收人
	        //message.setJMSCorrelationID(setType);//电子看板、手持机、机台HMI
	        message.setStringProperty("receiver", macCode);  //设备编码
	        // 发布主题消息  
	        producer.send(message);  
	        System.out.println("Sent message: " + message.getText());  
	        //session.commit();  
	        
	        producer.close();
	        session.close();  
	        
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}
	
	public void sendMsg_topic_persist(String topic_que, String msg){//String setId,
		 try {
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//String topic_que = pfu.getProp("QUE_TOPIC_PERSIST_SHOUCHIJI");
			Topic topic = session.createTopic(topic_que);
			MessageProducer producer = session.createProducer(topic);  
	        // NON_PERSISTENT 非持久化 PERSISTENT 持久化,发送消息时用使用持久模式  
	        producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
	        TextMessage message = session.createTextMessage();  
	        message.setText(msg);  
	        //message.setJMSCorrelationID(macCode); //指定接收人
	        //message.setJMSCorrelationID(setType);//电子看板、手持机、机台HMI
	        //message.setStringProperty("receiver", macCode);  //设备编码
	        // 发布主题消息  
	        producer.send(message);  
	        System.out.println("Sent message: " + message.getText());  
	        //session.commit();  
	        
	        producer.close();
	        session.close();  
	        
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}
	
	/**
	 * @TODO: 订阅模式，持久型。
	 * @author: zhaichunlei
	 & @DATE : 2017年7月14日
	 * @param setType 设备类型：电子看板、手持机、机台HMI. 用首字母大写： KB,SCJ,JTHMI   
	 *         目前只支持手持机.写死了主题名称
	 * @param setId, 设备编码或ID   
	 * @param msg
	 */
	public void sendMsg_topic_none_persist(String topic_que,String msg){//String setId,
		try {
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//String topic_que = pfu.getProp("QUE_TOPIC_PERSIST_SHOUCHIJI");
			Topic topic = session.createTopic(topic_que);
			MessageProducer producer = session.createProducer(topic);  
			// NON_PERSISTENT 非持久化 PERSISTENT 持久化,发送消息时用使用持久模式  
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
			TextMessage message = session.createTextMessage();  
			message.setText(msg);  
			//message.setJMSCorrelationID(setType);//电子看板、手持机、机台HMI
			//message.setStringProperty("property", setId);  //设备编码
			// 发布主题消息  
			producer.send(message);  
			System.out.println("Sent message: " + message.getText());  
			session.commit();  
			
			producer.close();
			session.close();  
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}
	
	public void close(){
	/*	try {
			conn.close();
			ApacheMqSender.obj = null;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	//用于点对点模式
	public static ApacheMqSender_MQTT getInstance(){
		if(obj == null){
			obj = new ApacheMqSender_MQTT();
		}
		
		return obj;
	}
}