/**     
*
*/   
package com.css.business.web.syswebsoket.handler;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.web.context.WebApplicationContext;

import com.css.business.web.syswebsoket.storewebsocket.storeCallWebSocket;
import com.css.business.web.syswebsoket.storewebsocket.storeCrewsWebSocket;
import com.css.commcon.util.ActiveMQUtil;
import com.css.common.util.PropertyFileUtil;

/**     
 * @Package com.css.business.web.syswebsoket   
 * @Description: 针对仓库的电子看板做的activeMQ的监听
 * @author   JS
 * @date 2017年7月11日 下午5:13:55   
 * @company  SMTC   
 */
@Deprecated
public class MesApacheMqStoreMessageListener /*implements Serializable,MessageListener*/{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	private static final long serialVersionUID = 452563188320006838L;

private WebApplicationContext wac;
	/** 生产电子看板的异常websocket */
	private  PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
	public MesApacheMqStoreMessageListener(final WebApplicationContext wac){
		//System.out.println("仓库消息。。。。。。。。。。");
		this.wac = wac;
		this.init();
	}
	private void init(){
		try {
			MessageConsumer messageConsumer = ActiveMQUtil.getMessageConsumer_topic(propertyFileUtil.getProp("KB_DEP_STORE"));
			//初始化MessageListener
			//给消费者设定监听对象
		//	messageConsumer.setMessageListener(this);
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
			storeCrewsWebSocket store = new storeCrewsWebSocket();
			storeCallWebSocket call = new storeCallWebSocket();


			//store.getStoreSeq(text);
		//	call.getStoreCall(text);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return  wac
	 */
	public WebApplicationContext getWac() {
		return wac;
	}
	/**
	 *  @param wac 要设置的 wac 
	 *    
	 */
	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}
	
	
	 
}
