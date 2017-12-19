package com.css.common.web.apachemq.handle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MqDisplayVo;
import com.google.gson.Gson;

/**
 * @Title: MesApacheMqReaderHandler_Display.java
 * @Package com.css.common.web.apachemq.handle
 * @Description: MQ对电子看板进行操作的处理类,处理机台进度和异常信息 .队列:QUE_TOPIC_P2P_KANBAN
 * @author rb
 * @date 2017年7月18日 下午2:21:24
 * @company SMTC
 */

public class MesApacheMqReaderHandler_Display implements Serializable,
		MessageListener {
	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_Display.class);
	private static final long serialVersionUID = 4586233702272887049L;
	private WebApplicationContext wac;
	// 工具类
	Gson gson = new Gson();
	private PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	//监听消息队列
	//private Connection conn;
	//private Session session;
	
	public MesApacheMqReaderHandler_Display(){
		
	}
	// 控制器类
	/*public MesApacheMqReaderHandler_Display(final WebApplicationContext wac Connection conn) {
		try {
			session = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.init();
	}*/

	/*private void init() {
		try {
			String queue = propertyFileUtil.getProp("QUE_P2P_KANBAN");
			//创建队列
			Destination webSubSysDest;webSubSysDest = session.createQueue(queue);
			//创建消息消费者
			MessageConsumer consumer = session.createConsumer(webSubSysDest);
			//初始化MessageListener
			//给消费者设定监听对象
			consumer.setMessageListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 监听消息,通过不同的消息类型进行不同的处理
	 */
	@Override
	public void onMessage(Message msg) {
		TextMessage txtMessage = (TextMessage) msg;
		try {
			String text = txtMessage.getText();
			writeLocal(text);
			// 获得消息发送的vo
			MqDisplayVo displayVo = gson.fromJson(text, MqDisplayVo.class);
			// MQ对应的类型 msg 时效性低 存缓存 exception params 时效性高 直接刷新,或者替换数据
			String type = displayVo.getType();
			if ("msg".equals(type)) {
				// 消息类型
				analysis_msg(displayVo);
			} else if ("exception".equals(type)) {
				// 异常类型
				analysis_exception(displayVo);
			}

			msg.acknowledge();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 解析出现的异常信息,实时性,所有可以不同存缓存,直接调用websocket
	 * @param displayVo
	 */
	private void analysis_exception(MqDisplayVo displayVo) {
		// exceptionid 具体使用看个人需求

		String json = gson.toJson(displayVo);
	
		/*CenExceptionWebS cenExceptionWebS = new CenExceptionWebS();
		cenExcep//tionWebS.flushPage();*/
		//将所有信息都存到缓存,根据个人需求去存
		/*cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常, json);
		cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_机台, "异常");*/
		String target = displayVo.getTarget();
		/*if ("拉丝".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_拉丝, displayVo.getExceptionId().toString());
		} else if("绞线".equals(target)){
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_绞线, displayVo.getExceptionId().toString());
		} else if("铠装".equals(target)){
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_铠装, displayVo.getExceptionId().toString());
		} else if("挤护套".equals(target)){
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_挤护套, displayVo.getExceptionId().toString());
		} else if("成缆".equals(target)){
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_成缆, displayVo.getExceptionId().toString());
		} else if("分盘".equals(target)){
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_分盘, displayVo.getExceptionId().toString());
		} else if("绝缘".equals(target)){
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_绝缘,  displayVo.getExceptionId().toString());
		} */
		
		cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_异常,  displayVo.getExceptionId().toString());


	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     

	/**
	 * @Description: 解析消息为msg的消息,将机台id和name存入缓存.
	 *               当是生产信息的时候,向该工序电子看板,车间电子看板,生产部电子看板同时存入信息
	 * 
	 * @param displayVo
	 */
	private void analysis_msg(MqDisplayVo displayVo) {
		// TODO Auto-generated method stub
		// 获取消息的目标
		String target = displayVo.getTarget();
		//具体信息
		String msg = displayVo.getMsg();
		// 机台信息 id
		String crewId = displayVo.getCrewId();
		// 根据不同的目标,向不同的ehcache对象存储
		if ("仓库".equals(target)) {
			// 仓库会有 出,入库 报废,缺料三种状态
			if ("出库".equals(msg)) {
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_库存, "出库");
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_出库, "出库");
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_统计, "统计");
			} else if ("入库".equals(msg)) {
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_库存, "入库");
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_入库, "入库");
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_统计, "统计");
			} else if ("缺料".equals(msg)) {
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_缺料, "缺料");
			} else if ("报废".equals(msg)) {
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_报废, "报废");
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_库存, "报废");
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_列表, "报废");
				cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_统计, "统计");
			}
		} else if ("叉车".equals(target)) {
			// 叉车信息,全部信息存入, 页面直接展示当前这一条
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_呼叫, msg);
		} else if ("工程部".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_工程部, "工程部");
		} else if ("质检".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_质检, "质检");
		} else if ("拉丝".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_拉丝_任务, crewId);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_进度, "车间");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_饼, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单, "通知单");
		} else if ("绞线".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_绞线_任务, crewId);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_进度, "车间");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_饼, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单, "通知单");
		} else if ("挤护套".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_挤护套_任务, crewId);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_进度, "车间");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_饼, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单, "通知单");
		} else if ("绝缘".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_绝缘_任务, crewId);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_进度, "车间");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_饼, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单, "通知单");
		} else if ("成缆".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_成缆_任务, crewId);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_进度, "车间");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_饼, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单, "通知单");
		} else if ("分盘".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_分盘_任务, crewId);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_进度, "车间");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_饼, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单, "通知单");
		} else if ("铠装".equals(target)) {
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_铠装_任务, crewId);
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_车间_进度, "车间");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_饼, "生产部");
			cacheFactory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单, "通知单");
		} 
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
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param text
	 */
	private void writeLocal(String text) {
		// TODO Auto-generated method stub
		// 把信息写到本地,看一下具体的信息是什么
		File file = new File("d:\\mq机台任务信息.txt");
		// 追加
		FileWriter fw;
		try {
			if(!file.exists()) file.createNewFile();
			fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	//	System.out.println("Done  "+text);
	}

}
