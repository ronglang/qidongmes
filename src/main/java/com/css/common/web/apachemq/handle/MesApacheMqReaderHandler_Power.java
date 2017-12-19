package com.css.common.web.apachemq.handle;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.css.business.web.subsysmanu.mauManage.service.MauPowerManageService;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MauPowerVo;
import com.google.gson.Gson;

/**
 * @Title: MesApacheMqReaderHandler_DisplayRT.java
 * @Package com.css.common.web.apachemq.handle
 * @Description: QUE_p2p_Power_elec
 * @author JS
 * @date 2017年7月19日 上午11:33:40
 * @company SMTC
 */

public class MesApacheMqReaderHandler_Power implements Serializable,
		MessageListener {

	private static final long serialVersionUID = -1357151588231534234L;
	private String path = null;
	
	@Resource(name="mauPowerManageService")
	private MauPowerManageService service;

	private WebApplicationContext wac;
	// 工具类
	Gson gson = new Gson();
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_Power.class);
	//监听消息队列
	public MesApacheMqReaderHandler_Power(){
		PropertyFileUtil pfu = new PropertyFileUtil();
		path = pfu.getProp("reportUpload");
	}	
	

	@Override
	public void onMessage(Message msg) {
		TextMessage txtMessage = (TextMessage) msg;
		String text = null;
		try {
			Gson gson = new Gson();
			text = txtMessage.getText();
			MauPowerVo vo = gson.fromJson(text, MauPowerVo.class);
			service.saveMauPower(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	

}
