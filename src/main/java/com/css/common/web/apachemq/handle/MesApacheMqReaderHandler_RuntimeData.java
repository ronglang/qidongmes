package com.css.common.web.apachemq.handle;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauProcessDany;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauProcessDanyManageDAO;
import com.css.common.util.BeanUtils;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MauProcessDanyVo;
import com.google.gson.Gson;

/**
 * @TODO  :  机台运行时，实时数据上传到消息队列QUE_WEB_PARAM，的处理类
 * @author: 翟春磊
 * @DATE  : 2017年7月17日
 */
public class MesApacheMqReaderHandler_RuntimeData implements Serializable,MessageListener{
	
	private static final long serialVersionUID = -2472186684836845204L;

	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_RuntimeData.class);
	private Gson gson = new Gson();
	private PropertyFileUtil pfu = new PropertyFileUtil();
	
	//private CraCmaterManageDAO craCmaterManageDAO ;
	@Resource(name = "mauProcessDanyManageDAO")
	private MauProcessDanyManageDAO mauProcessDanyManageDAO;
	@Resource(name = "mauMachineManageDAO")
	private MauMachineManageDAO mauMachineManageDAO;
	
	///private Connection conn;
	//private Session session;
	
	public MesApacheMqReaderHandler_RuntimeData(){}
	
	/*public MesApacheMqReaderHandler_RuntimeData(Connection conn){
		try {
			//this.session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.session = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		this.init();
	}*/
	
	/*private void init(){
		try {
			log.info("MesApacheMqReaderHandler_RuntimeData Web端apacheMq监听启动");
			
			//craCmaterManageDAO = (CraCmaterManageDAO)wac.getBean("craCmaterManageDAO");
			//craCmaterManageDAO = (CraCmaterManageDAO)SpringContextHolder.getBean("craCmaterManageDAO");
			mauProcessDanyManageDAO = (MauProcessDanyManageDAO)SpringContextHolder.getBean("mauProcessDanyManageDAO");
			
			String BROKER_URL = pfu.getProp("APACHE_MQ_URL");
			//创建连接工厂
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
			//获得连接
			conn = connectionFactory.createConnection();
			//conn.setClientID(YorkUtil.QUEUE_TOPIC_WEB_SYSTEM_ID);
			//start
			conn.start();

			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			//session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			createWebSubSystemQueueReader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}*/
	
	/**
	 * @TODO:创建web系统接收机台HMI
	 * @author: zhaichunlei
	 & @DATE : 2017年7月13日
	 */
	/*private void createWebSubSystemQueueReader(){
		try {
			String queue = pfu.getProp("QUE_WEB_PARAM");
			//创建队列
			Destination webSubSysDest = session.createQueue(queue);
			//创建消息消费者
			MessageConsumer consumer = session.createConsumer(webSubSysDest);
			//初始化MessageListener
			//给消费者设定监听对象
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void close(){
		/*try {
			session.close();
			conn.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage txtMessage = (TextMessage) msg;
		try {
			//System.out.println("get message:" + txtMessage.getText());
			String str = txtMessage.getText();
			//JSONArray ja = JSONArray.fromObject(str);
			//JSON.parse(str, List<MauProcessDany>);
			
			//saveBatchDynaData(ja);
			//log.info("机台打包提交一次运行时参数");
			//MauProcessDanyVo vo = gson.fromJson(str, MauProcessDanyVo.class);
			//saveBatchDynaData(vo);
			
			//msg.acknowledge();//确认成功接收
		} catch (JMSException e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	//批量保存生产过程中的实时数据
	private void saveBatchDynaData(MauProcessDanyVo vo){
		//数据来了就存。不校验 
		MauProcessDany md = new MauProcessDany();
		try {
			BeanUtils.copyProperties(md, vo);
			md.setCompressedParam(vo.getGzipParamInfoList());
			MauMachine mm = mauMachineManageDAO.getByMacCode(vo.getMachineCode());
			md.setMachineId(mm.getId());
			md.setSeqCode(mm.getSeqCode());
			String axisn = vo.getAxisName();
			Integer ind = axisn.indexOf('_');
			md.setCourseCode(axisn.substring(0,ind));
			md.setCreateDate(new Date(System.currentTimeMillis()));
			mauProcessDanyManageDAO.save(md);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		} 
	}
}
