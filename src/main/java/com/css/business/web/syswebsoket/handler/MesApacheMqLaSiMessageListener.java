package com.css.business.web.syswebsoket.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.web.context.WebApplicationContext;

import com.css.commcon.util.ActiveMQUtil;
import com.css.common.util.PropertyFileUtil;

/**     
 * @package com.css.business.web.syswebsoket.handler   
 * @Description: 针对 生产车间拉丝工序的电子看板做的activeMQ的监听
 * @author   zb
 * @date 2017年7月6日 下午3:00:00   
 * @company  SMTC
 * 不再使用   
 */

@Deprecated
public class MesApacheMqLaSiMessageListener {//implements Serializable,MessageListener{
	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	private static final long serialVersionUID = 452563189320006838L;
	private WebApplicationContext wac;
	/** 生产拉丝电子看板的异常websocket */
	private  PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
	public MesApacheMqLaSiMessageListener(final WebApplicationContext wac){
		this.wac = wac;
		this.init();
	}
	private void init(){
		try {
			MessageConsumer messageConsumer = ActiveMQUtil.getMessageConsumer_topic(propertyFileUtil.getProp("KB_SEQ_LASHI"));
			//初始化MessageListener
			//给消费者设定监听对象

			//messageConsumer.setMessageListener(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage txtMessage = (TextMessage) msg;
		try {
			System.out.println("get message:" + txtMessage.getText());
			String text = txtMessage.getText();
			// 通过消息的条件,对不同的websocket进行开启操作
			// if ("条件".equals(text)) {
			// }
			// cenExceptionWebS.flag = true;
			// 把信息写到本地,看一下具体的信息是什么
			File file = new File("e:/mq.txt");
			// 追加
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();

			System.out.println("Done");
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public WebApplicationContext getWac() {
		return wac;
	}
	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}
	
	
}
