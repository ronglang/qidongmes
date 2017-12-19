package com.css.business.web.subsysplan.plaManage.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.craManage.service.CraRouteSeqManageService;
import com.css.business.web.subsysplan.bean.PlaAbnormalInfor;
import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaWeekSeqHours;
import com.css.business.web.subsysplan.plaManage.service.PlaAbnormalInforManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaWeekSeqHoursManageService;
import com.css.business.web.subsysplan.plaManage.service.SchedulingManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/schedulingManageAction")
public class SchedulingManageAction extends BaseSpringSupportAction<PlaAbnormalInfor, PlaAbnormalInforManageService> {
	
	@Autowired
	private SchedulingManageService schedulingManageService;
	
	@Autowired
	private PlaAbnormalInforManageService service;
	@Autowired
	private PlaProductOrderManageService plaProductOrderManageService;
	@Autowired
	private CraRouteSeqManageService craRouteSeqManageService;
	@Autowired
	private PlaWeekSeqHoursManageService plaWeekSeqHoursManageService;
	/*@Autowired
	private SellContractDetailManageService sellContractDetailManageService;*/
	
	@Override
	public PlaAbnormalInforManageService getEntityManager() {
		return service;
	}
	
	/*@RequestMapping({ "plaWeekSeqHours" })
	public String plaWeekSeqHours(HttpServletRequest request, Integer id){
		String ids = request.getParameter("li");
		request.setAttribute("id", id);
		request.setAttribute("ids", ids);
		return "plaManage/scheduling/plaWeekSeqHours";
	}
	*//**
	 * 计算排产工时，并返回到页面上
	 * @param request
	 * @param page
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	@RequestMapping({"plaWeekSeqHoursData"})
	@ResponseBody
	public Page getPlaWeekSeqHours(HttpServletRequest request,Page page ){
		SysUser user = SessionUtils.getUser(request);
		
		try {
			//1.得到前端传入的参数
			String ids = request.getParameter("ids");
			String[] idArray = ids.split(",");
			String paramStr = "";
			List<Integer> idParamList = new ArrayList<Integer>();
			for(int i = 0;i < idArray.length;i++){
				Integer id = Integer.parseInt(idArray[i]);
				idParamList.add(id);
				paramStr += id+",";
			}
			paramStr = paramStr.substring(0,paramStr.length()-1);
			//2.查询数据库，得到生产令数据
			String hql = " from PlaProductOrder where id in ("+paramStr+")";
			List<PlaProductOrder> list = service.getEntityDaoInf().getHibernateTemplate().find(hql);
			List<Object> ll = new ArrayList<Object>();
			for(PlaProductOrder bean : list){
				Map<String,Object> mm = new HashMap<String,Object>();
				mm.put("proGgxh", bean.getProGgxh());
				mm.put("productPartLen", bean.getProductPartLen());
				mm.put("amount", bean.getAmount());
				mm.put("proCraftCode", bean.getProCraftCode());
				mm.put("proCode",bean.getProCode());
				mm.put("id", bean.getId());
				ll.add(mm);
			}
			HashMap<String, Object> map = plaWeekSeqHoursManageService.savePlaWeekSeqHours_3(user,null,ll);
			//TODO 这个地方不用姜帅的接口，使用纵表转横表，建立用来展示的数据
			if(Boolean.parseBoolean(map.get("success").toString())){//纵表转横表
				List<Object> proOrderVoList = schedulingManageService.getProOrderVoDataList(list);
				
				page.setData(proOrderVoList);
				page.setTotalCount(proOrderVoList.size());
			}
			return page;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	
	public void get(){
		//TODO 模拟数据，两条生产令，真是数据，是姜帅传输给我，这个位置有的时候，这批生产令的原材料肯定是够的，不用再判断了
		List<PlaProductOrder> list = new ArrayList<PlaProductOrder>();
		PlaProductOrder bean1 = plaProductOrderManageService.get(1);
		PlaProductOrder bean2 = plaProductOrderManageService.get(2);
		list.add(bean1);
		list.add(bean2);
		
		//根据生产令-->工艺路线-->工序-->机台-->工时
		//Integer craRouteId = bean1.getCra_route_id();//工艺路线id;
		String code = bean1.getCraRouteCode();
		//查询出工序列表
		//List<CraRouteSeq> routeSeqList = craRouteSeqManageService.getEntityDaoInf().getHibernateTemplate().find(" from CraRouteSeq where  ",craRouteId);
		//这是什么东东
		List<CraRouteSeq> routeSeqList = craRouteSeqManageService.getEntityDaoInf().getHibernateTemplate().find(" from CraRouteSeq where  ",code);
		
	}
	*//**
	 * 排产的所有信息到这里来,包括生成生产计划，回写生产令状态等等
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping({"scheduling"})
	@ResponseBody
	public HashMap<String, Object> scheduling(HttpServletRequest request,HttpServletResponse response){
		String sclIds = request.getParameter("orderIds");//生产令aids
//		String gsIds = request.getParameter("hoursIds");//工时ids
		SysUser user = SessionUtils.getUser(request);
		//TODO 修改生产令的状态，正式上线时，这里需要修改为真是数据
		
		List<Integer> scList = new ArrayList<Integer>();
		scList.add(5);
		scList.add(2);
		scList.add(6);
		scList.add(1);
		
		String[] arr = sclIds.split(",");
		scList.clear();
		for(int i = 0;i < arr.length;i++){
			scList.add(Integer.parseInt(arr[i]));
		}
		
		//TODO 获取生产令数据对象，获取生产工时数据对象
		List<PlaProductOrder> plaProductOrderList = plaProductOrderManageService.findByIds(scList);
		List<PlaWeekSeqHours> plaWeekSeqHoursList  = schedulingManageService.findByIdsForHours(scList);
		
		boolean boo = false;
		try {
			String is_flag = "1";
			//boo = schedulingManageService.scheduling(user,plaProductOrderList,plaWeekSeqHoursList,is_flag);
			//boo = schedulingManageService.scheduling_2(user, scList, is_flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(boo){
			String sql = " update pla_product_order set is_flag = ? where id = ? ";
			schedulingManageService.batchUpdate(scList,sql);
			return JsonWrapper.successWrapper("保存成功");
		}else{
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
	}
	@RequestMapping({"test"})
	public String test(){
//		SellContractDetail bean =  sellContractDetailManageService.get(13);
//		List<SellContractDetail> list = new ArrayList<SellContractDetail>();
//		list.add(bean);
//		schedulingManageService.schedulingOrder("2", list);
		schedulingManageService.test();
		return null;
	}
	@RequestMapping({"beforeScheduling"})
	@ResponseBody
	public HashMap<String,Object> beforeScheduling( HttpServletRequest request,HttpServletResponse response){
		String ids1 = request.getParameter("ids");
		String arr[] = ids1.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		
		for(int i = 0;i < arr.length;i++){
			idList.add(Integer.parseInt(arr[i]));
		}
		
//		for(int i = 0;i < ids.length;i++){
//			idList.add(Integer.parseInt(ids[i]));
//		}
		//TODO 根据id从数据库中查询出合同批次明细表记录
		List<SellContractDetail> list = sellContractDetailManageService.findByIds(idList);//根据id从数据中查询得出
		try{
			//schedulingManageService.schedulingOrder("2", list);
			return JsonWrapper.successWrapper(null, "预排产成功");
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "预排产失败");
		}
	}

	
	*//**
	 * 合同排产到生产令，正式排产
	 * @param request
	 *//*
	@RequestMapping({"formalScheduling"})
	@ResponseBody
	public HashMap<String,Object> scheduling(HttpServletRequest request,Integer ids[]){
		String ids1 = request.getParameter("ids");//合同明细的ids
		List<Integer> idList = new ArrayList<Integer>();
		if(ids1==null){
			return JsonWrapper.failureWrapperMsg(null, "没有要排产的合同明细id");
		}
		String arr[] = ids1.split(",");
		for(int i = 0;i < arr.length;i++ ){
			idList.add(Integer.parseInt(arr[i]));
		}
		//TODO 与上面取到的id选择一组来使用
		for (int i = 0; i < ids.length; i++) {
			idList.add(ids[i]);
		}
		
		//TODO 根据id从数据库中查询出合同批次明细表记录
		List<SellContractDetail> list = sellContractDetailManageService.findByIds(idList);//根据id从数据中查询得出
		try{
			//0，表示正式排产，2表示预排产，1表示已经生成计划
			//schedulingManageService.schedulingOrder("0", list);
			//完成之后，需要将合同明细的状态修改了，保证下次不会被查出来，保证该条记录只会生成一次的生产令。
			for(SellContractDetail bean : list ){
				bean.setPbatDetailState("已生成");
			}
			schedulingManageService.updateBatchSellContractDetail(list);
			return JsonWrapper.successWrapper(null, "预排产成功");
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "预排产失败");
		}
	}
	public static void main(String[] args) {
		for(int i = 0;i < 10;i++){
			System.out.println("start......");
			long startLong = System.currentTimeMillis();
			getString();
			long endLong = System.currentTimeMillis();
			System.out.println("方法执行时间"+(endLong-startLong));
			System.out.println("end......");
		}
	}
	
	public static String getString(){
		long nowLong = System.currentTimeMillis();
		Calendar c =  Calendar.getInstance();
		System.out.println(new Timestamp(nowLong));
		c.setTimeInMillis(nowLong);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int millisecond = c.get(Calendar.MILLISECOND);
		String result = ""+year+(month>=10?month:"0"+month)+(day>=10?day:"0"+day)+(hour>=10?hour:"0"+hour)+(minute>=10?minute:"0"+minute)+(second>=10?second:"0"+second)+millisecond;
		System.out.println(result);
		return result;
	}  */
}
