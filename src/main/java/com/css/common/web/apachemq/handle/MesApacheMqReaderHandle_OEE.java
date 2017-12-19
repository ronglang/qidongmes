
package com.css.common.web.apachemq.handle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.css.business.web.subsysmanu.mauManage.service.MauOEEManageService;
import com.css.business.web.subsysquality.bean.MacQuaVo;
import com.css.business.web.subsysquality.quaManage.service.QualityMauReportManageService;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MqMauOEEVo;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**     
 * @Title: MesApacheMqReaderHandle_OEE.java   
 * @Package com.css.common.web.apachemq.handle   
 * @Description: 机台关闭是上传本次的基本信息   
 * @author   rb
 * @date 2017年9月6日 下午3:21:25   
 * @company  SMTC   
 */

public class MesApacheMqReaderHandle_OEE implements Serializable,MessageListener{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	private static final long serialVersionUID = -2900290110635796419L;
	private Logger log = Logger.getLogger(MesApacheMqReaderHandle_OEE.class);
	
	private MauOEEManageService oeeService = SpringContextHolder.getBean("mauOEEManageService");
	private PropertyFileUtil pfu = new PropertyFileUtil();
	Gson gson = new Gson();

	/* 
	 * @param arg0
	 */
	//@Override
	public void onMessage(Message msg) {
		TextMessage txtMessage = (TextMessage) msg;
		String text;
		try {
			text = txtMessage.getText();
			//把mq写到本地观察
			//writeLocal(text);
			MqMauOEEVo vo = gson.fromJson(text, MqMauOEEVo.class);
			//计算写入数据库
			oeeService.saveByMqVo(vo);
			
			msg.acknowledge();
		} catch (Exception e) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		//System.out.println("Done");
	}
	
	
}
