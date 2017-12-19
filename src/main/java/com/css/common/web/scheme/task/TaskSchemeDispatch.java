package com.css.common.web.scheme.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import net.sf.json.JSONObject;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.bean.CraRoute;
import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.bean.CraSeqParam;
import com.css.business.web.subsyscraft.craManage.dao.CraCraftProductManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraRouteSeqManageDAO;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqParamManageDAO;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMaterialDetail;
import com.css.business.web.subsysmanu.bean.MauMaterialRecord;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMaterialDetailManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauMaterialRecordManageDAO;
import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.bean.PlaMachinePlan;
import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysplan.plaManage.dao.PlaCourseManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanManageDAO;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanMaterManageDAO;
import com.css.common.util.BeanUtils;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.MqDisplayVo;
import com.css.common.web.apachemq.handle.ApacheMqSender;
import com.css.common.web.syscommon.service.SpringContextHolder;


/**
 * @TODO  :定时任务 .定时在spring配置文件中。
 * 
 *              已废弃。 不再使用订时计划。机台主动查询工单
 * @author: 翟春磊
 * @DATE  : 2017年7月7日
 */
//@Component("taskSchemeDispatch")  
@Deprecated
public class TaskSchemeDispatch  implements Serializable{
	Logger log = Logger.getLogger(TaskSchemeDispatch.class);
	//private Gson gson = new Gson();
	private PropertyFileUtil pfu = new PropertyFileUtil();
	private static final long serialVersionUID = 3334321628997602825L;
	
	//@Resource(name="craRouteManageDAO")
	private CraRouteManageDAO craRouteManageDAO ;
	//机台计划
	//@Resource(name="plaMachinePlanManageDAO")
	private PlaMachinePlanManageDAO plaMachinePlanManageDAO;
	private MauMachineManageDAO mauMachineManageDAO;
	private PlaMachinePlanMaterManageDAO plaMachinePlanMaterManageDAO;
	private PlaCourseManageDAO plaCourseManageDAO;
	private CraCraftProductManageDAO craCraftProductManageDAO;
	private CraSeqParamManageDAO craSeqParamManageDAO;
	private CraRouteSeqManageDAO craRouteSeqManageDAO;
	private MauMaterialRecordManageDAO mauMaterialRecordManageDAO;//各种工单  这里领料单
	private  MauMaterialDetailManageDAO mauMaterialDetailManageDAO;//这里是领料单明细
	
	private Connection conn ;
	private Session session;
	
	//发送消息
	private ApacheMqSender sender ;
	//private Destination dest;
	//private MessageProducer producer;
	
	public TaskSchemeDispatch(){
		this.init();
	}
	
	private void init(){
		try {
			//消息发送
			sender = ApacheMqSender.getInstance();
			
			//com.css.common.web.syscommon.service.SpringContextHolder
			//craCmaterManageDAO = (CraCmaterManageDAO)wac.getBean("craCmaterManageDAO");
			//WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			craRouteManageDAO = (CraRouteManageDAO)SpringContextHolder.getBean("craRouteManageDAO");
			plaMachinePlanManageDAO = (PlaMachinePlanManageDAO)SpringContextHolder.getBean("plaMachinePlanManageDAO");
			mauMachineManageDAO =  (MauMachineManageDAO)SpringContextHolder.getBean("mauMachineManageDAO");
			plaMachinePlanMaterManageDAO = (PlaMachinePlanMaterManageDAO)SpringContextHolder.getBean("plaMachinePlanMaterManageDAO");
			plaCourseManageDAO = (PlaCourseManageDAO)SpringContextHolder.getBean("plaCourseManageDAO");
			craCraftProductManageDAO = (CraCraftProductManageDAO)SpringContextHolder.getBean("craCraftProductManageDAO");
			craSeqParamManageDAO = (CraSeqParamManageDAO)SpringContextHolder.getBean("craSeqParamManageDAO");
			craRouteSeqManageDAO = (CraRouteSeqManageDAO)SpringContextHolder.getBean("craRouteSeqManageDAO");
			mauMaterialRecordManageDAO = (MauMaterialRecordManageDAO)SpringContextHolder.getBean("mauMaterialRecordManageDAO");
			mauMaterialDetailManageDAO = (MauMaterialDetailManageDAO)SpringContextHolder.getBean("mauMaterialDetailManageDAO");
			
			
			String BROKER_URL = pfu.getProp("APACHE_MQ_URL");
			//创建连接工厂
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
			//获得连接
			conn = connectionFactory.createConnection();
			conn.start();
			
			//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			/*//创建队列
			dest = session.createQueue("test-queue");
			//创建消息生产者
			producer = session.createProducer(dest);*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * @TODO: 发送信息
	 * @author: zhaichunlei
	 & @DATE : 2017年7月11日
	 * @param macCode  机台编码。用于取消息队列名称
	 * @param msg
	 * @throws Exception
	 */
	private void sendMsg(String queue,String msg) throws Exception{
		Destination dest = session.createQueue(queue);
		MessageProducer producer =  session.createProducer(dest);
		TextMessage message = session.createTextMessage(msg);
		//发送消息
		producer.send(message);
		
		//System.out.println("向队列（"+queue+"）发送任务：" + msg);
		//log.log(Level.INFO, "向队列（"+queue+"）发送任务：" + msg);
		producer.close();
	}
	
	//定时任务
	//秒 分 时 date month dayofweek 
	//@Scheduled(cron = "1-59 * * * * ?")  
	public void doJob(){
//		System.out.println("定时任务");
//		//工单、工艺->机台任务->材料
//		dealData_GD();
//		//叉车任务(不直接发出，由底层发出的命令驱动)
//		//库房领料任务---当天的所有领料工单需求
//		dealData_LL();
	}
	
	/**
	 * @TODO: 查询数据。并包装为JSON --工单（ 工艺）-》机台计划-》计划对应的材料。
	 * @author: zhaichunlei
	 & @DATE : 2017年7月11日
	 */
//	private void dealData_GD(){
//		//符合条件的工单列表
//		try {
//			List<PlaCourse> clst = plaCourseManageDAO.getCourseDaily();
//			if(clst == null || clst.size() == 0) return; 
//			
//			for(PlaCourse p : clst){
//				//工单号
//				String wsCode = p.getWsCode();
//				//查询出当天所有的机台计划 ； 昨天未完成的如何处理？ 条件改为工作日期<=今天
//				List<PlaMacTask> lst = plaMachinePlanManageDAO.queryMachinePlanDailyByCourse(wsCode);
//				if(lst == null || lst.size() == 0) return;
//				p.setPlanLst(lst);
//				
//				for(PlaMacTask pl : lst){
//					Integer id = pl.getId();
//					List<PlaMachinePlanMater> mlst = plaMachinePlanMaterManageDAO.queryMachinePlanMaterByPid(id);
//					if(mlst != null && mlst.size() > 0){
//						System.out.println(mlst.size());
//					}
//					pl.setMaterList(mlst);
//				}
//				//把材料放进机台计划中。工单中不再存放
//				//List<PlaMachinePlanMater> mlst = plaMachinePlanMaterManageDAO.queryMachinePlanMaterByCourseCode(wsCode);
//				//p.setMaterList(mlst);
//				
//				//工艺参数
//				//产品工艺
//				String proCraftCode = p.getProCraftCode();
//				if(proCraftCode == null || proCraftCode.length() == 0) continue;  //错误数据，抛弃
//				CraCraftProduct ccp = craCraftProductManageDAO.getObjByProCraCode(proCraftCode);
//				if(ccp == null) break;
//				
//				p.setCraft(ccp);
//				
//				String route =  ccp.getRouteCode();
//				if(route != null && route.length() > 0){
//					CraRoute cr = craRouteManageDAO.getObjByCode(route);
//					ccp.setCraRoute(cr);
//					
//					List<CraRouteSeq> rlst = craRouteSeqManageDAO.getRouteSeqList(route);
//					ccp.setSeqLst(rlst);
//					
//					if(rlst != null && rlst.size() > 0){
//						for(CraRouteSeq crs : rlst){
//							String seqCode = crs.getSeqCode();
//							List<CraSeqParam> rslst = craSeqParamManageDAO.getSeqParamListByProCraCode(proCraftCode,seqCode);
//							crs.setCspLst(rslst);
//						}
//					}
//				}
//			}
//			
//			//发送机台任务
//			for(PlaCourse p : clst){
//				PlaCourse temp = new PlaCourse();
//				BeanUtils.copyProperties(temp, p);
//				temp.setPlanLst(null);
//				
//				//按机台把计划分组存放到map中
//				List<PlaMachinePlan> plst = p.getPlanLst();
//				Map<String,List<PlaMachinePlan>> map =  new HashMap<String,List<PlaMachinePlan>>();
//				//各机台领料计划合计
//				Map<String,Map<String,PlaMachinePlanMater>> macMater = new HashMap<String,Map<String,PlaMachinePlanMater>>();
//				
//				for(PlaMachinePlan plan : plst){
//					Integer mid = plan.getMachineId();
//					MauMachine mm = mauMachineManageDAO.get(mid);
//					if(mm == null) continue;
//					p.setMachineName(mm.getMacName()); //把机台名称放到工单中
//					
//					if(map.containsKey(mm.getMacCode())){
//						List<PlaMachinePlan> tt = map.get(mm.getMacCode());
//						tt.add(plan);
//					}
//					else{
//						List<PlaMachinePlan> tt = new ArrayList<PlaMachinePlan>();
//						tt.add(plan);
//						map.put(mm.getMacCode(), tt);
//					}
//					
//					
//					//把原料需求，按机台编码、规格，数量合计
//					if(macMater.containsKey(mm.getMacCode())){
//						Map<String,PlaMachinePlanMater> te = macMater.get(mm.getMacCode());
//						List<PlaMachinePlanMater> mlst = plan.getMaterList();
//						if(mlst != null && mlst.size() > 0){
//							for(PlaMachinePlanMater pp : mlst){
//								String ggxh = pp.getGgxh();
//								//判断map中有无。 无则加新对象，有则累加值
//								if(te.containsKey(ggxh)){
//									PlaMachinePlanMater m = te.get(ggxh);
//									m.setAmount(m.getAmount() + pp.getAmount());
//								}
//								else{
//									PlaMachinePlanMater m = new PlaMachinePlanMater();
//									//生成一个新对象
//									BeanUtils.copyProperties(m, pp);
//									te.put(pp.getGgxh(), m);
//								}
//							}
//						}
//					}
//					else{
//						List<PlaMachinePlanMater> mlst = plan.getMaterList();
//						if(mlst != null && mlst.size() > 0){
//							Map<String,PlaMachinePlanMater> te= new HashMap<String,PlaMachinePlanMater>();
//							macMater.put(mm.getMacCode(), te);
//							
//							for(PlaMachinePlanMater pp : mlst){
//								String ggxh = pp.getGgxh();
//								//判断map中有无。 无则加新对象，有则累加值
//								if(te.containsKey(ggxh)){
//									PlaMachinePlanMater m = te.get(ggxh);
//									m.setAmount(m.getAmount() + pp.getAmount());
//								}
//								else{
//									PlaMachinePlanMater m = new PlaMachinePlanMater();
//									//生成一个新对象
//									BeanUtils.copyProperties(m, pp);
//									te.put(pp.getGgxh(), m);
//								}
//							}
//						}
//					}
//				}
//				
//				//根据机台合并
//				
//				//产生领料的机台才生成领料单
//				Set<String> set = macMater.keySet();
//				for(String macCode : set){
//					//按机台，生成领料单
//					MauMaterialRecord r = new MauMaterialRecord();
//					r.setCreateBy("admin");
//					r.setCreateDate(new Date(System.currentTimeMillis()));
//					//生成领料单号
//					String lld = mauMaterialRecordManageDAO.exeFunction("maumaterialrecordmanagedao", "LL").toString();
//					r.setMmrCode(lld);
//					r.setDocType("领料单");
//					r.setWsCode(p.getWsCode());
//					r.setRemark("新增");
////					r.setMacCode(macCode);
//					//r.setMacCode(macCode);
//					r.setMmrState("未开始");
//					r.setWorkDay(null); //要从机台计划取
//					r.setMatermanageTime(new Date(System.currentTimeMillis()));
//					mauMaterialRecordManageDAO.save(r);
//					
//					//下面生成领料明细
//					Map<String,PlaMachinePlanMater> te = macMater.get(macCode);
//					Set<String> ggxhSet = te.keySet();
//					for(String ggxh : ggxhSet){
//						PlaMachinePlanMater ppm = te.get(ggxh);
//						
//						Double amount = ppm.getAmount();
//						MauMaterialDetail d = new MauMaterialDetail();
//						d.setMaterAmount(new BigDecimal(amount));
//						d.setMmrCode(r.getMmrCode());
//						d.setDocType("领料单");
//						d.setUnit(ppm.getUnit());
//						d.setCreateBy(r.getCreateBy());
//						d.setCreateDate(r.getCreateDate());
//						
//						mauMaterialDetailManageDAO.save(d);
//					}
//				}
//				/*if(pst != null && pst.size() > 0){
//					//按机台，生成领料单
//					MauMaterialRecord r = new MauMaterialRecord();
//					r.setCreateBy("admin");
//					r.setCreateDate(new Date(System.currentTimeMillis()));
//					//生成领料单
//					String lld = mauMaterialRecordManageDAO.exeFunction("maumaterialrecordmanagedao", "LL").toString();
//					r.setMmrCode(lld);
//					r.setDocType("领料单");
//					r.setWsCode(p.getWsCode());
//					r.setRemark("新增");
//					r.setMacCode(macCode);
//					mauMaterialRecordManageDAO.save(r);
//					
//					//生成领料单明细
//					MauMaterialDetail d = new MauMaterialDetail();
//					mauMaterialDetailManageDAO.save(d);
//				}*/
//				
//				set = map.keySet();
//				for(String macCode : set){
//					List<PlaMachinePlan> pst = map.get(macCode);
//					
//					//工单，拆分到工序中各机台。分别发送
//					temp.setPlanLst(pst);
//					if(macCode == null || macCode.length() == 0 ) 
//						continue;
//					
//					String queue = pfu.getProp("MACHINE_"+macCode.toUpperCase());
//					
//					if(queue == null)
//						continue;
//					
//					//把整个工单(包括本机台自己的计划)，发到指定机台上
//					//JSONArray js = JSONArray.fromObject(pst);
//					JSONObject js = JSONObject.fromObject(p);
//					sendMsg(queue, js.toString());
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.error(e);
//		} 
//	}
//	
//	/**
//	 * @TODO:当天的领料任务,全部发送到仓库看板。 
//	 * 后续优化： 判断生产半小时时，触发领料。再发于仓库看板
//	 * @author: zhaichunlei
//	 & @DATE : 2017年7月11日
//	 * @throws Exception 
//	 */
//	private void dealData_LL() {
//		try {
//			//早上发送一次对仓库/叉车的通知，让仓库与叉车页面自动查询
//			//封装看板消息队列需要的消息格式
//			MqDisplayVo vo = new MqDisplayVo();
//			vo.setType("msg");
//			vo.setTarget("仓库");
//			vo.setMsg("出库");
//			
//			JSONObject json = JSONObject.fromObject(vo);
//			//工序看板
//			String queue = pfu.getProp("QUE_P2P_KANBAN");
//			//发送消息
//			sender.sendMsg_point_to_point(queue, json.toString());
//			
//			
//			//呼叫叉车
//			vo = new MqDisplayVo();
//			vo.setType("msg");
//			vo.setTarget("叉车");
//			json = JSONObject.fromObject(vo);
//			//发送消息
//			sender.sendMsg_point_to_point(queue, json.toString());
//		} catch (JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		/*List<PlaMachinePlanMater> lst = plaMachinePlanMaterManageDAO.queryMachinePlanMaterDaily();
//		if(lst == null || lst.size() == 0) return;*/
//		
//		/*//仓库看板的队列名称
//		String queue = pfu.getProp("KB_DEP_STORE");
//		
//		try {
//			if(queue == null){
//				log.error("请设置仓库看板(KB_DEP_STORE)的专用消息队列");
//				throw new Exception("请设置仓库看板(KB_DEP_STORE)的专用消息队列");
//			}
//			JSONArray js = JSONArray.fromObject(lst);
//			sendMsg(queue, js.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.error(e);
//		}*/
//	}
}
