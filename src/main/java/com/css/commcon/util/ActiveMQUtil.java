/**     
*
*/   
package com.css.commcon.util;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.css.common.util.PropertyFileUtil;

/**     
 * @Title: ActiveMQUtil.java   
 * @Package com.css.business.web.syswebsoket   
 * @Description: activeMQ基本的一些方法   
 * @author   rb
 * @date 2017年7月6日 下午3:56:20   
 * @company  SMTC   
 */

public class ActiveMQUtil {
//	private static final  String BROKER_URL = null;
	private static  PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
	/**
	 * 
	 * @Description: 获取消费者   点对点的
	 * @param queueName 队列名称
	 * @return
	 */
	public static MessageConsumer getMessageConsumer_p2p( String queueName){
		MessageConsumer messageConsumer = null;
		try {
			//创建连接工厂
		//	ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory( propertyFileUtil.getProp("APACHE_MQ_URL"));
			//获得连接
			Connection conn = connectionFactory.createConnection();
			//start
			conn.start();
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//创建队列
			Destination dest = session.createQueue(queueName);
			//创建消息生产者
			messageConsumer = session.createConsumer(dest);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return messageConsumer;
	}
	
	/**
	 * @Description: 获取消费者   topic非持久
	 * @param queueName 队列名称
	 * @return
	 */
	public static MessageConsumer getMessageConsumer_topic( String topicName){
		MessageConsumer messageConsumer = null;
		try {
			//创建连接工厂
			//	ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory( propertyFileUtil.getProp("APACHE_MQ_URL"));
			//获得连接
			Connection conn = connectionFactory.createConnection();
			//start
			conn.start();
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//创建队列
			//Destination dest = session.createQueue(queueName);
			//创建消息生产者
			//messageConsumer = session.createConsumer(dest);
			Topic topic = session.createTopic(topicName);
			messageConsumer = session.createConsumer(topic);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return messageConsumer;
	}
	
	public static MessageConsumer getMessageConsumer_topic_Myself(String 订阅ID, String topicName){
		MessageConsumer messageConsumer = null;
		try {
			//创建连接工厂
			//	ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory( propertyFileUtil.getProp("APACHE_MQ_URL"));
			//获得连接
			Connection conn = connectionFactory.createConnection();
			//start
			conn.start();
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//创建队列
			//Destination dest = session.createQueue(queueName);
			//创建消息生产者
			//messageConsumer = session.createConsumer(dest);
			Topic topic = session.createTopic(topicName);
			session.createDurableSubscriber(topic, 订阅ID);
			messageConsumer = session.createConsumer(topic);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return messageConsumer;
	}
	
	
	
}
