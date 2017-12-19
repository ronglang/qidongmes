package com.css.common.web.apachemq.handle;

import java.io.Serializable;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import net.sf.json.JSONObject;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.css.business.web.subsysmanu.bean.MauException;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.mauManage.dao.MauExceptionManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MauExceptionVO;
import com.css.common.web.apachemq.bean.MqDisplayVo;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**
 * @TODO  : 机台上传异常信息到队列，的消息处理类 
 * @author: 翟春磊
 * @DATE  : 2017年7月17日
 */
public class MesApacheMqReaderHandler_Exception implements Serializable,MessageListener{
	private static final long serialVersionUID = 4372634148548608382L;
	
	private Logger log = Logger.getLogger(MesApacheMqReaderHandler_Exception.class);
	
	private Gson gson = new Gson();
	private PropertyFileUtil pfu = new PropertyFileUtil();
	
	//private CraCmaterManageDAO craCmaterManageDAO ;
	private MauExceptionManageDAO mauExceptionManageDAO = SpringContextHolder.getBean("mauExceptionManageDAO");
	private MauMachineManageDAO mauMachineManageDAO = SpringContextHolder.getBean("mauMachineManageDAO");
	//private PlaMachinePlanManageDAO plaMachinePlanManageDAO;
	//private CraSeqManageDAO craSeqManageDAO;
	
	//private static final String BROKER_URL="failover://tcp://192.168.0.2:61615";
	
	//监听消息队列
	//private Connection conn;
	//private Session session;
	
	//发送消息
	private ApacheMqSender sender ;
	
	public MesApacheMqReaderHandler_Exception(){}
	
	/*public MesApacheMqReaderHandler_Exception(Connection conn){
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
			log.info("MesApacheMqReaderHandler Web端apacheMq监听启动");
			
			mauExceptionManageDAO = (MauExceptionManageDAO)SpringContextHolder.getBean("mauExceptionManageDAO");
			mauMachineManageDAO   = (MauMachineManageDAO)SpringContextHolder.getBean("mauMachineManageDAO");
			//plaMachinePlanManageDAO = (PlaMachinePlanManageDAO)SpringContextHolder.getBean("plaMachinePlanManageDAO");
			//craSeqManageDAO = (CraSeqManageDAO)SpringContextHolder.getBean("craSeqManageDAO");
			
//			String BROKER_URL = pfu.getProp("APACHE_MQ_URL");
//			//创建连接工厂
//			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
//			//获得连接
//			conn = connectionFactory.createConnection();
//			//conn.setClientID(YorkUtil.QUEUE_TOPIC_WEB_SYSTEM_ID);
//			//start
//			conn.start();

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
/*	private void createWebSubSystemQueueReader(){
		try {
			String queue = pfu.getProp("QUE_WEB_EXCEPTION");
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

	private EhCacheFactory factory = EhCacheFactory.getInstance();
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage msg = (TextMessage) message;
		try {
			String str = msg.getText(); 
			log.info("消息队列收到机台异常信息："+str);
			//MauException me = gson.fromJson(str, MauException.class);
			MauExceptionVO mv = gson.fromJson(str, MauExceptionVO.class);
			
			MauException me = new MauException();
			me.setAgentBy(mv.getAgentBy());
			me.setAxisName(mv.getAxisName());
			me.setCode(mv.getCode());
			me.setCourseCode(mv.getCourseCode());
			me.setSeqCode(mv.getSeqCode());
			me.setProCraftCode(mv.getProCraftCode());
			me.setExceptionParam(mv.getExceptionParam());
			me.setExceptionValue(mv.getExceptionValue());
			me.setMacCode(mv.getMacCode());
			me.setMeInfo(mv.getMeInfo());
			me.setMeTime(new Date(mv.getMeTime()));
			
			factory.addWebsocketCmdCache(YorkUtil.Memcache_看板_生产_异常, str);
			saveException(me);
			
			//sendNotice_toKanBan(me);
			
			message.acknowledge();
		} catch (JMSException e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	/**
	 * @TODO: 保存异常信息到数据库
	 * @author: zhaichunlei
	 & @DATE : 2017年7月19日
	 * @return
	 */
	private MauException saveException(MauException me){
		try {
			String code       = me.getCode();
			String macCode  = me.getMacCode();
			String meInfo     = me.getMeInfo();
			Date meTime      = me.getMeTime();
			String axisName  = me.getAxisName();
			String excepPara = me.getExceptionParam();
			String excepValue= me.getExceptionValue();
			if(code == null){
				throw new Exception("异常编码不能为空");
			}
			if(macCode == null){
				throw new Exception("机台编码不能为空");
			}
			if(meInfo == null){
				throw new Exception("异常信息不能为空");
			}
			if(meTime == null){
				throw new Exception("异常发生时间不能为空");
			}
			if(axisName == null){
				throw new Exception("轴名称不能为空");
			}
			if(excepPara == null){
				throw new Exception("异常参数编码不能为空");
			}
			if(excepValue == null){
				throw new Exception("异常参数的值不能为空");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			//return null;
		}
		
		me.setCreateBy("auto");
		me.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		
		//参数提交少，但目前继续保存
		mauExceptionManageDAO.save(me);
		return me;
	}
	
	/**
	 * @TODO: 通知相关电子看板
	 * @author: zhaichunlei
	 & @DATE : 2017年7月19日
	 */
	private void sendNotice_toKanBan(MauException me){
		try {
			String macCode = me.getMacCode();
			MauMachine mm = mauMachineManageDAO.getByMacCode(macCode);
			if(mm == null){
				throw new Exception("机台编码（"+macCode+"）不存在");
			}
			Integer machineId = mm.getId();  //机台ID
			
			//封装看板消息队列需要的消息格式
			MqDisplayVo vo = new MqDisplayVo();
			vo.setType("exception");
			//String seqName = craSeqManageDAO.getNameByCode(seqCode);
			vo.setTarget(mm.getMacType());
			vo.setCrewId(String.valueOf(machineId));
			vo.setExceptionId(me.getId());
			
			JSONObject json = JSONObject.fromObject(vo);
			
			//工序看板
			String queue = pfu.getProp("QUE_P2P_KANBAN");
			
			//消息发送
			sender = ApacheMqSender.getInstance();
			
			//发送消息
			//sender.sendMsg_point_to_point_5秒不接丢弃(queue, json.toString()); //不用这种方式。 异常信息不允许丢弃
			sender.sendMsg_point_to_point(queue, json.toString());
			log.log(Level.INFO ,"生产发生了异常，已发送至电子看板。 异常ID："+me.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	} 
	
	/*public static void main(String[] args) {
		MauExceptionVO vo = new MauExceptionVO();
		vo.setAgentBy("操作人");
		vo.setAxisName("轴名称");
		vo.setCode("异常编码");
		vo.setMacCode("jitaocode");
		vo.setCourseCode("工单编码");
		vo.setMeInfo("异常信息");
		vo.setMeTime(1242544557884L);
		vo.setExceptionParam("异常参数");
		vo.setExceptionValue("异常值");
		
		Gson gson = new Gson();
		String json = gson.toJson(vo);
		System.out.println(json);
	}*/
}
