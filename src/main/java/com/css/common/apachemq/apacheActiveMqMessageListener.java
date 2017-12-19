package com.css.common.apachemq;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;


public class apacheActiveMqMessageListener implements SessionAwareMessageListener<TextMessage>{
	///private Destination destination;
	
	//接收到消息后，返回一个消息
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		// TODO Auto-generated method stub
		 System.out.println("收到一条消息");  
	        System.out.println("消息内容是：" + message.getText());  
	       // MessageProducer producer = session.createProducer(destination);  
	       // Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener。。。");  
	       // producer.send(textMessage);  
	}
	/*public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}*/

}
