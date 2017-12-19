/**     
 *
 */
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

import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;

import com.css.business.web.syswebsoket.DisplayController.MauDisplayShowController;
import com.css.commcon.util.ActiveMQUtil;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;

/**
 * @Package com.css.business.web.syswebsoket
 * @Description: 针对 生产车间的电子看板做的activeMQ的监听
 * @author rb
 * @date 2017年7月6日 下午5:13:55
 * @company SMTC
 */
@Deprecated
public class MesApacheMqManuMessageListener /*implements Serializable,
		MessageListener*/ {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 452563188320006838L;

	private WebApplicationContext wac;
	// EhcheFactory
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	/*String cacheName = YorkUtil.Memcache_看板_生产;
	String cacheName_now = YorkUtil.Memcache_看板_生产_实时;*/
	// 生产电子看板控制器
	private MauDisplayShowController mauDisplayShowController = new MauDisplayShowController();
	private PropertyFileUtil propertyFileUtil = new PropertyFileUtil();

	public MesApacheMqManuMessageListener(final WebApplicationContext wac) {
		this.wac = wac;
		this.init();
	}

	private void init() {
		try {
			MessageConsumer messageConsumer = ActiveMQUtil
					.getMessageConsumer_topic(propertyFileUtil
							.getProp("QUE_TOPIC_NOPERSIST_KANBAN"));
			// 初始化MessageListener
			// 给消费者设定监听对象
		//	messageConsumer.setMessageListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Override
	public void onMessage(Message msg) {
		TextMessage txtMessage = (TextMessage) msg;
		try {
			System.out.println("get message:" + txtMessage.getText());
			String text = txtMessage.getText();
			// 假设:叉车呼叫是实时性数据,进度完成是非实时性数据
			/*if ("叉车呼叫".equals(text)) {
				// 实时性数据
				// 往ehcache存数据
				cacheFactory.addWebsocketCmdCache(cacheName_now, "叉车呼叫");
				// 调用控制器展示,实时调用websocket
				mauDisplayShowController.show(cacheName_now);
			} else if ("进度完成".equals(text)) {
				// 往ehcache存数据,不用实时调用websocket
				cacheFactory.addWebsocketCmdCache(cacheName, "进度完成");
			}*/
			// 往本地写信息,做测试工作
			writeLocal(text);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param text
	 */
	private void writeLocal(String text) {
		// TODO Auto-generated method stub
		// 把信息写到本地,看一下具体的信息是什么
		File file = new File("j:\\mq.txt");
		// 追加
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done");
	}

	/**
	 * @return wac
	 */
	public WebApplicationContext getWac() {
		return wac;
	}

	/**
	 * @param wac
	 *            要设置的 wac
	 * 
	 */
	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}

	/**
	 * @return mauDisplayShowController
	 */
	public MauDisplayShowController getMauDisplayShowController() {
		return mauDisplayShowController;
	}

	/**
	 * @param mauDisplayShowController
	 *            要设置的 mauDisplayShowController
	 * 
	 */
	public void setMauDisplayShowController(
			MauDisplayShowController mauDisplayShowController) {
		this.mauDisplayShowController = mauDisplayShowController;
	}

	/**
	 * @return propertyFileUtil
	 */
	public PropertyFileUtil getPropertyFileUtil() {
		return propertyFileUtil;
	}

	/**
	 * @param propertyFileUtil
	 *            要设置的 propertyFileUtil
	 * 
	 */
	public void setPropertyFileUtil(PropertyFileUtil propertyFileUtil) {
		this.propertyFileUtil = propertyFileUtil;
	}

}
