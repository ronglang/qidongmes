package com.css.common.web.apachemq.handle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.json.JSON;
import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.mauManage.dao.MauCallForkliftRecordManageDAO;
import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanManageDAO;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.CommandVO;
import com.css.common.web.apachemq.handle.test.MqttBroker;
import com.google.gson.Gson;

/**
 * @TODO  : 目前用于呼叫叉车. 目前接收的是从机台即将生产完毕发来的呼叫，配往下一工序或半成品区
 *   并需要兼容： 仓库手持机上传出库时，呼叫叉车的功能。两功能数据格式要保持一致。叉车只有一个队列
 * @author: 翟春磊
 * @DATE  : 2017年7月21日
 */
public class MesApacheMqReaderHandler_ReceiveCommand  implements Serializable,MessageListener{
	
	private static final long serialVersionUID = -2472186684836845204L;

	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_ReceiveCommand.class);
	private Gson gson = new Gson();
	private PropertyFileUtil pfu = new PropertyFileUtil();
	
	//private CraCmaterManageDAO craCmaterManageDAO ;
	//private MauProcessDanyManageDAO mauProcessDanyManageDAO;
	@Autowired
	private PlaMachinePlanManageDAO plaMachinePlanManageDAO;
	@Autowired
	private MauCallForkliftRecordManageDAO mauCallForkliftRecordManageDAO;
	@Autowired
	private PlaCourseManageService plaCourseManageService;
	
	//private Connection conn;
	//private Session session;
	//发送消息
	private ApacheMqSender sender ;
	
	public MesApacheMqReaderHandler_ReceiveCommand(){}
	
	/*public MesApacheMqReaderHandler_ReceiveCommand(Connection conn){
		//String BROKER_URL = pfu.getProp("APACHE_MQ_URL");
		//创建连接工厂
		//ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("admin","admin",BROKER_URL);
		try {
			//获得连接
			//Connection conn = connectionFactory.createConnection();
			//conn.setClientID(YorkUtil.QUEUE_TOPIC_WEB_SYSTEM_ID);
			//start
			//conn.start();
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			session = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		this.init();
	}*/
	
	/*private void init(){
		try {
			log.info("Web端接收指令的apacheMq监听启动");
			
			//craCmaterManageDAO = (CraCmaterManageDAO)wac.getBean("craCmaterManageDAO");
			//craCmaterManageDAO = (CraCmaterManageDAO)SpringContextHolder.getBean("craCmaterManageDAO");
			//mauProcessDanyManageDAO = (MauProcessDanyManageDAO)SpringContextHolder.getBean("mauProcessDanyManageDAO");
			plaMachinePlanManageDAO = (PlaMachinePlanManageDAO)SpringContextHolder.getBean("plaMachinePlanManageDAO");
			mauCallForkliftRecordManageDAO = (MauCallForkliftRecordManageDAO)SpringContextHolder.getBean("mauCallForkliftRecordManageDAO");
			plaCourseManageService = (PlaCourseManageService)SpringContextHolder.getBean("plaCourseManageService");
			
			//String BROKER_URL = pfu.getProp("APACHE_MQ_URL");
			//创建连接工厂
			//ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("admin","admin",BROKER_URL);
			//获得连接
			//conn = connectionFactory.createConnection();
			//conn.setClientID(YorkUtil.QUEUE_TOPIC_WEB_SYSTEM_ID);
			//start
			//conn.start();
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			//session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//sender = ApacheMqSender.getInstance();
			createWebSubSystemQueueReader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}*/
	
	/**
	 * @TODO:创建web系统接收各种来源的命令
	 * @author: zhaichunlei
	 & @DATE : 2017年7月13日
	 */
	/*private void createWebSubSystemQueueReader(){
		try {
			String queue = pfu.getProp("QUE_WEB_COMMAND_RECEIVE_P2P");
			//创建队列
			Destination webSubSysDest = session.createQueue(queue);
			//创建消息消费者
			MessageConsumer consumer = session.createConsumer(webSubSysDest);
			//初始化MessageListener
			//给消费者设定监听对象
			//consumer.setMessageListener(this);
			
			String des = webSubSysDest.toString();
			System.out.println("command reader:" + des);
			
			//dealMsg(consumer);
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
	
	/*private void dealMsg(MessageConsumer consumer) throws JMSException{
		while(true){
			TextMessage msg = (TextMessage)consumer.receive(10*1000);
			if(msg != null)
				System.out.println("接收到了消息"+msg.getText());
			else{
				System.out.println("QUE_WEB_COMMAND_RECEIVE_P2P接收不到消息，10秒超时");
			}
		}
	}*/

	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage txtMessage = (TextMessage) msg;
		try {
			System.out.println("get message:" + txtMessage.getText());
			String str = txtMessage.getText();
			log.info("web消息队列接收到指令："+str);
			CommandVO vo = JSON.parse(str, CommandVO.class);
			dealCommand(vo); 
			
			msg.acknowledge();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	//判断命令，并作出反应
	private void dealCommand(CommandVO vo){
		//工单号
		String courseCode = vo.getCourseCode();
		//来源机台
		String fromMachineCode = vo.getFromMachineCode();
		//指令类型
		String commType  = vo.getCommType();
		
//		String nextMachine = plaMachinePlanManageDAO.getNextMachineNameByCourseCode_MachineCode(courseCode, fromMachineCode);
//			
//			//不存在下一工序，则生产完毕，送到待检区
//			if( nextMachine == null ){
//				vo.setToMachineName("入库待检区");
//			}
//			else{
//				vo.setToMachineName(nextMachine);
//			}
			
			//保存叉车呼叫记录
			MauCallForkliftRecord mf = new MauCallForkliftRecord();
			mf.setCreateBy("admin");
			mf.setCreateDate(new Date());
			mf.setCallAddress(fromMachineCode);
			SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
			mf.setCallTime(sf.format(new Date()));
			
			//此时还不知道哪个叉车
			mf.setReplyBy(null);  
			mf.setReplyTime(null);
			
//			String nestr = vo.getToMachineName();
//			if("入库待检区".equals(nestr)){
//				mf.setDestAddress("入库待检区");
//			}
//			else{
				mf.setDestAddress("机台:"+vo.getToMachineName());
//			}
			mf.setCourseCode(courseCode);
			mauCallForkliftRecordManageDAO.save(mf);
			
			vo.setId(mf.getId());
			String topicN = pfu.getProp("QEU_TOPIC_PERSIST_CHACHE");
			
			try {
				//呼叫叉车
				String msg = gson.toJson(vo);
				//ApacheMqSender_MQTT mqtt = ApacheMqSender_MQTT.getInstance();
				//mqtt.sendMsg_topic_persist(topicN, msg);
				MqttBroker br = MqttBroker.getInstance();
				br.sendMessage("master", msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("机台（"+fromMachineCode+"）呼叫叉车出现异常：" + e);
			}
		
	}
}
