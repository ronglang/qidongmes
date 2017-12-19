package com.css.common.web.apachemq.handle;

import java.io.Serializable;
import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MqJTSaveDataVO;

/**
 * @TODO  : 机台生产完毕后，打包上传到消息队列QUE_WEB_SAVE，的处理类
 * @author: 翟春磊
 * @DATE  : 2017年7月17日
 */
public class MesApacheMqReaderHandler_SaveData implements Serializable,MessageListener{
	private static final long serialVersionUID = 5419667323002729227L;

	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_SaveData.class);
	
	private PropertyFileUtil pfu = new PropertyFileUtil();
	
	//private CraCmaterManageDAO craCmaterManageDAO ;
	@Autowired
	private PlaCourseManageService plaCourseManageService;
	
	//private static final String BROKER_URL="failover://tcp://192.168.0.2:61615";
	//private Connection conn;
	//private Session session;
	
	public MesApacheMqReaderHandler_SaveData(){}
	
	/*public MesApacheMqReaderHandler_SaveData(Connection conn){
		try {
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
			log.info("MesApacheMqReaderHandler_SaveData Web端apacheMq监听启动");
			
			//craCmaterManageDAO = (CraCmaterManageDAO)wac.getBean("craCmaterManageDAO");
			//craCmaterManageDAO = (CraCmaterManageDAO)SpringContextHolder.getBean("craCmaterManageDAO");
			plaCourseManageService = (PlaCourseManageService)SpringContextHolder.getBean("plaCourseManageService");
			
			//String BROKER_URL = pfu.getProp("APACHE_MQ_URL");
			//创建连接工厂
			//ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
			//获得连接
			//conn = connectionFactory.createConnection();
			//conn.start();

			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			//session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			createWebSubSystemQueueReader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}*/
	
	/**
	 * @TODO:创建web系统接收机台HMI 打包数据保存
	 * @author: zhaichunlei
	 & @DATE : 2017年7月13日
	 */
	/*private void createWebSubSystemQueueReader(){
		try {
			String queue = pfu.getProp("QUE_WEB_SAVE");
			//创建队列
			Destination webSubSysDest;webSubSysDest = session.createQueue(queue);
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

	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage txtMessage = (TextMessage) msg;
		try {
			//System.out.println("get message:" + txtMessage.getText());
			log.info("机台打包提交一次保存");
			String str = txtMessage.getText();
			System.out.println(str);
			JSONObject jo = JSONObject.fromObject(str);
			MqJTSaveDataVO vo = (MqJTSaveDataVO)JSONObject.toBean(jo, MqJTSaveDataVO.class);
			saveData(vo);
			
			msg.acknowledge();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);;
		}
	}
	
	/**
	 * @TODO:保存机台打包提交的数据。 更同时更新参数
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param vo
	 */
	private void saveData(MqJTSaveDataVO vo){
		try {
			String macCode = vo.getMacCode();
			String workcode = vo.getCourseCode();
			String seqCode  = vo.getSeqCode();
			Long actualBeginTime = vo.getActualBeginTime();
			Long actualEndTime = vo.getActualEndTime();
			plaCourseManageService.autoUpdateCourse(vo);
			if(macCode == null  || seqCode == null || workcode == null || actualBeginTime == null || actualEndTime == null)
				throw new Exception("机台提交核心数据错误：参数 机台编码、工序编码、收线时间、工作单号。数据抛弃。");
			else{
				
			}
		

			
		} catch (Exception e) {
			// TODO Auto-generated catch block`
			e.printStackTrace();
			log.error(e);
			JSONObject o = JSONObject.fromObject(vo);
			log.error("抛弃的数据json如下："+o.toString());
		}
	}
}