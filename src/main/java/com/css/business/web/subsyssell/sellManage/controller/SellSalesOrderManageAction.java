/**
 * 在单系统控制器
 */
package com.css.business.web.subsyssell.sellManage.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.net.aso.d;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.print.resources.serviceui;

import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.service.CraCraftProductManageService;
import com.css.business.web.subsyscraft.craManage.service.CraRouteSeqManageService;
import com.css.business.web.subsyscraft.craManage.service.CraSeqManageService;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.mauManage.service.MauProductManageService;
import com.css.business.web.subsyssell.bean.SellSalesOrder;
import com.css.business.web.subsyssell.bean.SellSalesOrderDetails;
import com.css.business.web.subsyssell.sellManage.service.SellSalesOrderDetailsManageService;
import com.css.business.web.subsyssell.sellManage.service.SellSalesOrderManageService;
import com.css.business.web.subsyssell.vo.OrderProcessFlowVo;
import com.css.business.web.subsyssell.vo.CoordinateVo;
import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.service.SysCommdicManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/sellSalesOrderManageAction")
public class SellSalesOrderManageAction extends BaseSpringSupportAction<SellSalesOrder, SellSalesOrderManageService>{

	
	public static final String PRO_COLOR = "产品颜色";
	
	@Autowired
	private SellSalesOrderManageService sellSalesOrderManageService;
	
	@Autowired
	private SellSalesOrderDetailsManageService sellSalesOrderDetailsManageService;
	
	@Autowired
	private SysCommdicManageService sysCommdicManageService;
	
	@Autowired
	private MauProductManageService mauProductManageService;
	
	@Autowired
	private CraCraftProductManageService craCraftProductManageService;
	
	@Autowired
	private CraRouteSeqManageService craRouteSeqManageService;
	
	@Autowired
	private CraSeqManageService craSeqManageService;
	
	@Override
	public SellSalesOrderManageService getEntityManager() {
		// TODO Auto-generated method stub
		return sellSalesOrderManageService;
	}
	
	private Gson gson  = new Gson();
	/**
	 * 订单工艺流程图页面
	 * @return
	 */
	@RequestMapping({"orderCraftView"})
	public String getVoList(){
		return "sellManage/view/orderCraftView";
	}
	
	@RequestMapping({"toSellSalesOrderList"})
	public String toSellOrderList(){
		return "sellManage/sellSalesOrderList";
	}
	
	
	/**
	 * 
	 * @Description:详情页面
	 * @param request
	 * @param orderCode
	 * @return
	 */
	@RequestMapping("toDetailRecord")
	public String toDetailRecord(HttpServletRequest request,String orderCode){
		SellSalesOrder salesOrder = sellSalesOrderManageService.getByOrderCode(orderCode);
		request.setAttribute("orderCode", orderCode);
		return "sellManage/sellSalesOrderGenDetail";
	}
	
	@RequestMapping("toEditRecord")
	public String toEditRecord(HttpServletRequest request,String orderCode){
		request.setAttribute("orderCode", orderCode);
		return "sellManage/sellSalesOrderGenEdit";
	}
	/**
	 * 返回订单流程
	 * @param request
	 * @return
	 */
	@RequestMapping({"getVoList"})
	@ResponseBody
	public HashMap<String,Object> getVoList(HttpServletRequest request){
		String gg1 = "gg1";//TODO 传入的参数
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<OrderProcessFlowVo> list = craCraftProductManageService.getCraftView(gg1, 1,"0");
		//封装工序编号、工序名称
		for(OrderProcessFlowVo vo : list){
			String routeCode = vo.getRouteCode();//工艺路线编码
			String hql = " from CraRouteSeq where routeCode = ? order by sort ";
			List<CraRouteSeq> tempList = craRouteSeqManageService.getEntityDaoInf().getHibernateTemplate().find(hql,routeCode);
			for(CraRouteSeq bean : tempList){
				String seqCode = bean.getSeqCode();;
				vo.getSeqCodeList().add(seqCode);
				hql = " from CraSeq where seqCode = ? ";
				List<CraSeq> craSeqList = craSeqManageService.getEntityDaoInf().getHibernateTemplate().find(hql,seqCode);
				vo.getSeqNameList().add(craSeqList.get(0).getSeqName());
			}
			map.put(vo.getCraftCode(), vo);
		}
//		Collections.sort(list);
//		calcCoordinate(list);
		resultMap.put("list", list);
		resultMap.put("map", map);
		return JsonWrapper.successWrapper(calc(list));
	}
	
	
	
	/**
	 * 
	 * @param list
	 */
	@SuppressWarnings("unused")
	public List<CoordinateVo> calc(List<OrderProcessFlowVo> list){
		List<CoordinateVo> resultList = new ArrayList<CoordinateVo>();
		
		//顺序的List,用来依次循环产生坐标
//		List<OrderProcessFlowVo> voList = new ArrayList<OrderProcessFlowVo>();
		//级别--一组对象
		Map<Integer,List<OrderProcessFlowVo>> levelMap = new HashMap<Integer,List<OrderProcessFlowVo>>();
		//工艺--组
		Map<String,OrderProcessFlowVo> craMap = new HashMap<String,OrderProcessFlowVo>();
		
		int maxLevel = 0;//记录最大级别
		for(OrderProcessFlowVo vo : list){
			Integer level = vo.getLevel();
			String proCraftCode = vo.getCraftCode();
			if(levelMap.get(level)==null){
				levelMap.put(level, new ArrayList<OrderProcessFlowVo>());
			}
			levelMap.get(level).add(vo);
			
			craMap.put(proCraftCode, vo);
			if(level > maxLevel){
				maxLevel = level;
			}
		}
		//父级工艺，对应子工艺集合
		Map<String,List<OrderProcessFlowVo>> tempMap = new HashMap<String,List<OrderProcessFlowVo>>();
		
		Map<String,List<OrderProcessFlowVo>> dataMap = new HashMap<String,List<OrderProcessFlowVo>>();
		
		int level_x = 0,level_y=0;//每个级别完毕，都需要重新开始x,y
		for(int i=maxLevel;i >0;i--){
			tempMap.clear();
			List<OrderProcessFlowVo> tempList = levelMap.get(i);
			int maxSeqCount = 0;//记录该级别的最大工序数量
			
			for(OrderProcessFlowVo vo : tempList){
				int size = vo.getSeqCodeList().size();
				if(size > maxSeqCount){
					maxSeqCount = size;
				}
				String parentCraftCode = vo.getParentCraftCode();
				if(tempMap.get(parentCraftCode)==null){
					tempMap.put(parentCraftCode, new ArrayList<OrderProcessFlowVo>());
					dataMap.put(parentCraftCode, new ArrayList<OrderProcessFlowVo>());
				}
				tempMap.get(parentCraftCode).add(vo);
				dataMap.get(parentCraftCode).add(vo);
			}
			int levelMaxLength = (maxSeqCount*2)*OrderProcessFlowVo.RECTANGLE_WIDTH;//记录同一父级工艺最大的横轴长度
			int max_x = level_x + levelMaxLength;
			int parent_x = level_x,parent_y = 0;
			for(String key : tempMap.keySet()){//
				List<OrderProcessFlowVo> temp = tempMap.get(key);
				for(OrderProcessFlowVo v:temp){//表示同一个级别的同一父级工艺
					int size = v.getSeqCodeList().size();
					if(size > maxSeqCount){
						maxSeqCount = size;
					}
				}

				int x = parent_x,y = parent_y;
				int index = 0;
				for(OrderProcessFlowVo v: temp){//同一一父级工艺的几条记录
					List<String> seqNameList = v.getSeqNameList();
					int level = v.getLevel();
					int size = seqNameList.size();
					
					for (int j = 0; j < size; j++) {
						String seqName = seqNameList.get(j);
						CoordinateVo voBean = new CoordinateVo();
						voBean.setType("矩形");
						voBean.setStart_y(y);
						voBean.setStart_x(x);
						voBean.setSeqName(seqName);
						voBean.setHeight(OrderProcessFlowVo.RECTANGLE_HEIGHT);
						voBean.setWidth(OrderProcessFlowVo.RECTANGLE_WIDTH);
						resultList.add(voBean);
						//,每个矩形完毕之后，x变化
						x = x + OrderProcessFlowVo.RECTANGLE_WIDTH;
						
						if(level!=1||j!=size-1){//不是最后一条线或者不是最后一个元素，正常划横线
							CoordinateVo voBean1 = new CoordinateVo();
							voBean1.setType("横线");
							voBean1.setStart_x(x);
							voBean1.setStart_y(y+OrderProcessFlowVo.RECTANGLE_HEIGHT/2);
							voBean1.setLine_length(OrderProcessFlowVo.RECTANGLE_WIDTH);
							x = x + OrderProcessFlowVo.RECTANGLE_WIDTH;
							resultList.add(voBean1);
						}
						
						if(j==size-1){//如果是这条线的最后一个元素
							if(index!=temp.size()-1){//不是该级别最后一个元素，	
								//OrderProcessFlowVo.RECTANGLE_HEIGHT
								CoordinateVo voBean1 = new CoordinateVo();
								voBean1.setType("横线");
								voBean1.setStart_x(x);
								voBean1.setStart_y(y+OrderProcessFlowVo.RECTANGLE_HEIGHT/2);
								voBean1.setLine_length(OrderProcessFlowVo.RECTANGLE_WIDTH);
								x = x + OrderProcessFlowVo.RECTANGLE_WIDTH;
								resultList.add(voBean1);
								
								int height = 0;
								if(dataMap.get(v.getCraftCode()) == null||dataMap.get(v.getCraftCode()).isEmpty()){
									height = 1 * OrderProcessFlowVo.RECTANGLE_HEIGHT;
								}else{
									height = dataMap.get(v.getCraftCode()).size()*OrderProcessFlowVo.RECTANGLE_HEIGHT;
								}
								System.out.println("当前的工艺编码为"+v.getCraftCode()+"需要划线的高度为："+height);
								CoordinateVo voBean2 = new CoordinateVo();
								voBean2.setType("纵线");
								voBean2.setStart_x(max_x);
								voBean2.setStart_y(y+OrderProcessFlowVo.RECTANGLE_HEIGHT/2);
								//如何获取纵线长度？
								voBean2.setLine_length(height);
								resultList.add(voBean2);
								
							}
							if(index==0){
								
							}
							
							
							if(level!=1){
								while(x < max_x){
									CoordinateVo voBean1 = new CoordinateVo();
									voBean1.setType("横线");
									voBean1.setStart_x(x);
									voBean1.setStart_y(y+OrderProcessFlowVo.RECTANGLE_HEIGHT/2);
									voBean1.setLine_length(OrderProcessFlowVo.RECTANGLE_WIDTH);
									x = x + OrderProcessFlowVo.RECTANGLE_WIDTH;
									resultList.add(voBean1);
								}
							}
						}
					}
					//每条串行线路完毕之后，x为最初的值
					x = parent_x;
					String proCraftCode = v.getCraftCode();
					if(dataMap.get(proCraftCode)!=null&&dataMap.get(proCraftCode).size()!=0){
						int tmepSize = dataMap.get(proCraftCode).size();
						y = y + OrderProcessFlowVo.RECTANGLE_HEIGHT*tmepSize;
					}else{
						y = y + OrderProcessFlowVo.RECTANGLE_HEIGHT;
					}
					index++;
				}
				//每组父级工艺完毕之后的坐标
				parent_x = level_x;//x坐标，就是该级别的坐标
				parent_y += (temp.size())*OrderProcessFlowVo.RECTANGLE_HEIGHT;
			}
			//根据最大工序数，计算本级别最大长度
			int max_length = maxSeqCount*2*OrderProcessFlowVo.RECTANGLE_WIDTH;//本级别最大长度，包含右侧横线
			//下一个级别，将要从这个位置开始
			level_x = level_x + max_length + OrderProcessFlowVo.RECTANGLE_WIDTH;
			level_y = 0;//每个级别重新开始，纵坐标都初始为0；
		}
		return resultList;
	}
	



	@RequestMapping({"toListPage"})
	public String toListPage(HttpServletRequest request){
		
		return "sellManage/sellSalesOrderList";
	} 
	@RequestMapping({"toListPageData"})
	@ResponseBody
	public Page toListPageData(HttpServletRequest request,Page page ,String param){
		try {
			if(page == null){
				page = new Page();
			}
		//	page = super.dataGridPage(request, page, entity);
			page = sellSalesOrderManageService.getListPage(page,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping({"insertRecord"})
	public String insertRecord(HttpServletRequest request){
		
		return "sellManage/sellSalesOrderEdit";
	}
	@RequestMapping({"saveSellSalesOrderInfo"})
	@ResponseBody
	public HashMap<String,Object> saveSellSalesOrderInfo(){
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping({"saveSellSalesOrderData"})
	@ResponseBody
	public HashMap<String,Object> saveSellSalesOrderData(HttpServletRequest request){
		String mainData = request.getParameter("mainData");
		String sonDatas = request.getParameter("sonDatas");
		
		JSONObject jsonObject=JSONObject.fromObject(mainData);
		SellSalesOrder mainBean=(SellSalesOrder)JSONObject.toBean(jsonObject, SellSalesOrder.class);
		//封装部分系统生成参数
		mainBean.setCreateBy(SessionUtils.getUser(request).getAccount());
		mainBean.setGenFlag(SellSalesOrder.GEN_FLAG_NO);
		mainBean.setCreateTime(new Date());

		List<SellSalesOrderDetails> sonList = (List<SellSalesOrderDetails>)JSONArray.toList(JSONArray.fromObject(sonDatas), SellSalesOrderDetails.class);
		//TODO 部分数据转换
		for(SellSalesOrderDetails sonBean : sonList){
			sonBean.setAlreadyAxisNum(0);
			sonBean.setNowAxisNum(sonBean.getAxisNumber());
			sonBean.setCreateBy(SessionUtils.getUser(request).getAccount());
			sonBean.setCreateTime(new Date());
			sonBean.setOrderCode(mainBean.getOrderCode());
			try {
				sellSalesOrderDetailsManageService.save(sonBean);
			} catch (Exception e) {
				e.printStackTrace();
			};
		}
		sellSalesOrderManageService.getEntityDaoInf().save(mainBean);
		return JsonWrapper.successWrapper("保存成功");
	}
	
	/**
	 * 获取所有颜色
	 * @return
	 */
	@RequestMapping({"findProColor"})
	@ResponseBody
	public List<Map<String,String>> selectProColor(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		List<SysCommdic> dataList = sysCommdicManageService.findByClas(PRO_COLOR);
		Map<String,String> map = null;
		for(SysCommdic bean : dataList){
			map = new HashMap<String,String>();
			map.put("id", bean.getValue());
			map.put("text", bean.getValue());
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping({"selectProGgxh"})
	@ResponseBody
	public HashMap<String, Object> selectProGgxh(){
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		try {//{Rows:[{"proGgxh":"规格型号1"},{"proGgxh":"规格型号2"}],Total:100}
			//List<MauProduct> list = mauProductManageService.queryAll();
			List<String>pList= mauProductManageService.getAllProGgxh();
			Map<String,String> map = null;
			for(String proggxh : pList){
				map = new HashMap<String,String>();
				map.put("proGgxh", proggxh);
				resultList.add(map);
			}
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("Rows", resultList);
			resultMap.put("Total", pList.size());
			return JsonWrapper.successWrapper(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.failureWrapper("获取失败");
	}
	/**
	 * 校验订单编号是否存在
	 * @param orderCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({"checkOrderCode"})
	@ResponseBody
	public HashMap<String, Object> checkOrderCode(String orderCode) {
		String hql = " from SellSalesOrder where orderCode = ? ";
		List<SellSalesOrder> list = sellSalesOrderManageService.getEntityDaoInf().getHibernateTemplate().find(hql,orderCode);
		if(list==null||list.isEmpty()){
			return JsonWrapper.failureWrapper("订单编号不存在");
		}else{
			return JsonWrapper.successWrapper("订单编号已经存在");
		}

	}
	
	/**
	 * 根据订单编号集合分解订单到生产通知单
	 * @param request
	 * @param str
	 * @return
	 */
	@RequestMapping({"decomposeAdviceNote"})
	@ResponseBody
	public HashMap<String,Object> decomposeAdviceNote(HttpServletRequest request,@RequestBody String[] str){
		
		try{
			boolean booLock = false;
			String strInfo = str[str.length-1];
			if("lock=true".equals(strInfo)){//可以用锁库存
				String arr[] = new String[str.length-1];
				for(int i = 0;i < arr.length;i++){
					arr[i] = str[i];
				}
				str = arr;
				booLock = true;
			}
			List<String> list = Arrays.asList(str);
			for(String st : list){
				//sellSalesOrderManageService.decomposeOrder(st,booLock);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return JsonWrapper.successWrapper("分解成功");
	}
	/**
	 * 根据订单编号，查看该订单的完成率
	 * @param request
	 * @param orderCode
	 * @return
	 */
	@RequestMapping({"getCompletionRate"})
	@ResponseBody
	public HashMap<String,Object> getCompletionRate(HttpServletRequest request){
		try {
			String orderCode = request.getParameter("orderCode");
			String rate = getRate(orderCode);
			return JsonWrapper.successWrapper(rate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.failureWrapper("获取失败");
	}


	private String getRate(String orderCode) throws Exception {
		String rate = sellSalesOrderManageService.completionRate(orderCode);
		String rate1 = Double.parseDouble(rate)*100+"%";
		return rate1;
	}
	
	/**
	 * 
	 * @Description: app 获得已分解订单的信息   
	 * @return
	 */
	/*@RequestMapping("appGetSellOrders")
	@ResponseBody
	public HashMap<String, Object>appGetSellOrders(){
		//获得订单下面生产通知单已分解的订单
		List<SellSalesOrder> orderList = null;
		try {
			orderList = sellSalesOrderManageService.hasExplain();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("查询失败");
		}
		return JsonWrapper.successWrapper(orderList);
	}*/
	
	
	/**
	 * 
	 * @Description:订单分解成工单
	 * @param request
	 * @param data
	 * @return
	 */
	@RequestMapping("decomposePlaCourse")
	@ResponseBody
	public HashMap<String, Object>decomposePlaCourse(HttpServletRequest request,String param){
		List<String> datas  =gson.fromJson(param, new TypeToken<List<String>>(){}.getType()); 
		/*String lockData = datas.get(datas.size() - 1);
		boolean lock = Boolean.parseBoolean(lockData.split("=")[1]);
		datas.remove(datas.size() - 1);*/
		Integer sum = 0;
		for (String orderCode : datas) {
			//完成分解的数量
			try {
				sellSalesOrderManageService.decomposePlaCourse(orderCode,true);
				sum += 1;
			} catch (Exception e) {
				e.printStackTrace();continue;
			}
		}
		return JsonWrapper.successWrapper(null, "成功分解"+sum+"个订单");
	}
	
	@RequestMapping("getByOrderCode")
	@ResponseBody
	public SellSalesOrder getByOrderCode(HttpServletRequest request,String orderCode){
		SellSalesOrder sellSalesOrder = sellSalesOrderManageService.getByOrderCode(orderCode);
		return sellSalesOrder;
	}
	
	@RequestMapping("saveOrUpdateBean")
	@ResponseBody
	public HashMap<String,Object>saveOrUpdateBean(HttpServletRequest request){
		String mainData = request.getParameter("mainData");
		String sonDatas = request.getParameter("sonDatas");
		SellSalesOrder order = gson.fromJson(mainData, SellSalesOrder.class);
		List<SellSalesOrderDetails> sonList = gson.fromJson(sonDatas, new TypeToken<List<SellSalesOrderDetails>>(){}.getType());
		try {
			if (order.getId() ==null) {
				order.setCreateTime(new Date());
				order.setCreateBy(order.getOrderEntryClerk());
				order.setGenFlag(SellSalesOrder.GEN_FLAG_NO);
				sellSalesOrderManageService.save(order);
			}else{
				sellSalesOrderManageService.updateByCon(order, false);
			}
			for (SellSalesOrderDetails detail : sonList) {
				if(detail.getId() != null && !"".equals(detail.getId()) ){
					detail.setAlreadyAxisNum(0);
					detail.setNowAxisNum(detail.getAxisNumber());
					sellSalesOrderDetailsManageService.updateByCon(detail, false);
				}else {
					detail.setAlreadyAxisNum(0);
					detail.setNowAxisNum(detail.getAxisNumber());
					detail.setCreateBy(order.getOrderEntryClerk());
					detail.setCreateTime(new Date());
					sellSalesOrderDetailsManageService.save(detail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
		return JsonWrapper.successWrapper(null, "保存成功");
	}
	
	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object>clearBean(HttpServletRequest request,String orderCode){
		try {
			sellSalesOrderManageService.clearBean(orderCode);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		}
		return JsonWrapper.successWrapper(null, "删除成功");
	}
	
	/**
	 * 
	 * @Description:单个单进行批次分解
	 * @param request
	 * @param orderCode
	 */
	@RequestMapping("toBatchDecompose")
	public String toBatchDecompose(HttpServletRequest request,String orderCode){
		request.setAttribute("orderCode", orderCode);
		return "sellManage/sellSalesOrderBatch";
	}
	
	/**
	 * 
	 * @Description:分解单个批次订单
	 * @param request
	 * @param orderCode
	 * @param detailBeans
	 * @return
	 */
	@RequestMapping("batchDecompose")
	@ResponseBody
	public HashMap<String, Object>batchDecompose(HttpServletRequest request,String orderCode,String detailBeans){
		try {
			sellSalesOrderManageService.batchDecompose(orderCode,detailBeans);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("分解失败");
		}
		return JsonWrapper.successWrapper(null, "分解成功");
	}
	
	/**
	 * 
	 * @Description:.去修改订单类型页面,主要是 订单变插单
	 * @param request
	 * @param orderCode
	 * @return
	 */
	@RequestMapping("toChangeOrderType")
	public String toChangeOrderType(HttpServletRequest request,String orderCode){
		request.setAttribute("orderCode", orderCode);
		return "sellManage/sellSalesOrderChange";
	}
	
	/**
	 * 
	 * @Description:修改订单类型   ?-->插单.插单将未排产的都一起分解成工单
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping("changeOrderType")
	@ResponseBody
	public HashMap<String, Object>changeOrderType(HttpServletRequest request,String bean){
		Map<String,String>map = gson.fromJson(bean, new TypeToken<Map<String,String>>(){}.getType());
		if(SellSalesOrder.ORDER_TYPE_INSERT.equals(map.get("orderType"))){
			//插单
			try {
				sellSalesOrderManageService.changeOrderTypeNtoIn(map);
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg("修改失败");
			}
		}
		return JsonWrapper.successWrapper(null, "修改成功");
	}
	
	
}

