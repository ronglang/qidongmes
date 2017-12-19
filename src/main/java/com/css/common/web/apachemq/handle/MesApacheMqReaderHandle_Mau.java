
package com.css.common.web.apachemq.handle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.css.business.web.subsysquality.bean.MacQuaVo;
import com.css.business.web.subsysquality.quaManage.service.QualityMauReportManageService;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.UploadWebBoard;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**     
 * @Title: MesApacheMqReaderHandle_Mau.java   
 * @Package com.css.common.web.apachemq.handle   
 * @Description: 机台申请生产质检:制程质检可以的   
 * @author   rb
 * @date 2017年9月5日 下午6:07:17   
 * @company  SMTC   
 */

public class MesApacheMqReaderHandle_Mau implements Serializable,MessageListener{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	private static final long serialVersionUID = -5652587732529029071L;
	private Logger log = Logger.getLogger(MesApacheMqReaderHandle_Mau.class);
	
	private QualityMauReportManageService quaMauService = SpringContextHolder.getBean("qualityMauReportManageService");
	private PropertyFileUtil pfu = new PropertyFileUtil();
	Gson gson = new Gson();
	//发送消息
	private ApacheMqSender sender  ;
	/* 
	 * @param arg0
	 */
	@Override
	public void onMessage(Message msg) {
		TextMessage txtMessage = (TextMessage) msg;
		String text;
		try {
			text = txtMessage.getText();
			//把mq写到本地观察
			//writeLocal(text);
			MacQuaVo macQuaVo = gson.fromJson(text,MacQuaVo.class);
			if (macQuaVo != null) {
				//生产质检报告
				quaMauService.mqSaveBeanAndParams(macQuaVo);
			}
			
			msg.acknowledge();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param text
	 * @throws IOException 
	 */
	private void writeLocal(String text) throws IOException {
		// 把信息写到本地,看一下具体的信息是什么
		//File file = new File("j:\\mq.txt");
		String pp ="c:\\mq_mau.txt";
		File file = new File(pp);
		if (!file.exists()) {
			//file.mkdirs();
			file.createNewFile();
		}
		// 追加
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();

		}

	//	System.out.println("Done");
	}
	
	/*public void sendTomachine(MacQuaVo vo){
		String queue = pfu.getProp("QUE_P2P_QUALITY_MAU");
		try {
			sender.sendMsg_point_to_point(queue, gson.toJson(vo).toString());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


}
