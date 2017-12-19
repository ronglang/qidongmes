package com.css.business.web.subsysmanu.mauManage.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMaterialDetail;
import com.css.business.web.subsysmanu.bean.MauMaterialRecord;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauMaterialDetailManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauMaterialRecordManageService;
import com.css.business.web.subsysplan.bean.PlaMachinePlan;
import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanMaterManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.CodedFormatParseUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/mauMaterialDetailManageAction")
public class MauMaterialDetailManageAction extends BaseSpringSupportAction<MauMaterialDetail, MauMaterialDetailManageService> {
	
	@Autowired
	private MauMaterialDetailManageService service;
	@Autowired
	private MauMaterialRecordManageService mauMaterialRecordManageService;
	@Autowired
	private PlaMachinePlanManageService plaMachinePlanManageService;
	
	
	@Autowired
	private PlaMachinePlanMaterManageService plaMachinePlanMaterManageService;
	
	@Autowired
	private MauMachineManageService mauMachineManageService;
	@Override
	public MauMaterialDetailManageService getEntityManager() {
		return service;
	}

	public MauMaterialDetailManageService getService() {
		return service;
	}

	@Resource(name="mauMaterialDetailManageService")
	public void setService(MauMaterialDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMaterialDetailedEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMaterialDetailedForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMaterialDetailedList";
	}
	/**
	 * 领料明细表格 本页第一个入口
	 * @param request
	 * @param id
	 * @param mainId
	 * @return
	 */
	
	@SuppressWarnings({  "unchecked" })
	@RequestMapping({ "toListGridPage" })
	public String toListGridPage(HttpServletRequest request, Integer id,Integer mainId){
		SessionUtils.setAttr(request, "mainId", mainId);
		//TODO 查询未领取的，属于mainId的领料单明细，有且只有一组
		String hql = " from MauMaterialDetail where isReceive = '0' and mainId = ? ";
//		if(mainId==null){
//			System.out.println();
//			mainId = 1;
//		}
		List<MauMaterialDetail> list = service.getEntityDaoInf().getHibernateTemplate().find(hql,mainId);
		for(MauMaterialDetail bean : list){
			if(bean.getMacCode()==null){
				Integer macId = bean.getMacId();
				MauMachine mauMachine = mauMachineManageService.get(macId);
				bean.setMacCode(mauMachine.getMacCode());
			}
		}
		
		request.setAttribute("list", list);
		
		return "mauManage/rec_material/mauMaterialDetailedList";
	}
	
	@RequestMapping("toListGridPageData")
	@ResponseBody
	public Page toListGridPageData(HttpServletRequest request,Page page,MauMaterialDetail bean,Integer mainId){
		
		try {
			String add = request.getParameter("init");
			if(add!=null&&!add.isEmpty()&&"add".equals(add)&&mainId!=null){
				//TODO 初始化领料明细单
				init(mainId);
			}
			if(mainId!=null){
				bean.setMainId(mainId);
			}
			bean.setIsReceive("1");
			page = super.dataGridPage(request, page, bean);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	/**
	 * 初始化领料明细
	 * @param mainId
	 */
	@SuppressWarnings("unchecked")
	private void init(Integer mainId) {
		//1.查询领料单主表
		MauMaterialRecord mainBean = mauMaterialRecordManageService.get(mainId);
		
		
		//TODO 判断mianId的领料明细是否已经存在，如果存在，便不再继续向下执行
		String hsql = " from MauMaterialDetail where mainId = ? ";
		List<MauMaterialDetail> mauMaterialDetailList = service.getEntityDaoInf().getHibernateTemplate().find(hsql,mainId);
		
		if(mauMaterialDetailList!=null&&mauMaterialDetailList.size()>0){
			throw new RuntimeException("该领料单已经存在领料明细，不需要再继续初始化，此处中断，不再向下执行");
		}
		
		//TODO 判断是否已经领料结束
		Timestamp endTime =  mainBean.getTimeEnd();
		if(endTime!=null){//已经结束
			return;
		}
		
		int workDay  = mainBean.getWorkDay();//工作时间,八位数
		//2.通过工作日，找到在这一天需要工作的机台集合--->机台计划集合，
		String hql = " from PlaMachinePlan where  workDay = ? ";
		List<PlaMachinePlan> list = plaMachinePlanManageService.getEntityDaoInf().getHibernateTemplate().find(hql,workDay);
		//查找机台对应的材料计划表
		List<Integer> machinPlanIds = new ArrayList<Integer>();
		for(PlaMachinePlan plan : list){
			machinPlanIds.add(plan.getId());
		}
		
		
		//3.查询机台材料计划表
		hql = " from PlaMachinePlanMater where machinePlanId in (:ids) ";
		List<PlaMachinePlanMater> plaMachinePlanMaterList =  plaMachinePlanMaterManageService.getEntityDaoInf().getHibernateTemplate().findByNamedParam(hql, "ids",machinPlanIds);
		List<MauMaterialDetail> resultList = new ArrayList<MauMaterialDetail>();
		
		//4.通过机台材料计划表生成领料单明细
		for(PlaMachinePlanMater bb: plaMachinePlanMaterList){
			MauMaterialDetail bean = new MauMaterialDetail();
			bean.setCreateBy("创建人");
			bean.setCreateDate(new Date());
			bean.setDocType(null);
			bean.setMaterCode(bb.getGgxh());//物料编号
			bean.setPlanCount(BigDecimal.valueOf(bb.getAmount()));//计划物料消耗数量
			bean.setMaterAmount(null);//物料数量,默认生成为空，由客户手动填写
			bean.setMmrCode(bb.getGgxh());//物料记录编码
			bean.setUnit(bb.getUnit());//单位
			bean.setRemark(null);//备注信息
			bean.setMainId(mainId);//主表id
			bean.setMaterName(bb.getMaterName());//物料名称
			bean.setIsReceive("0");//是否已领取，默认为未领取
			bean.setMacId(bb.getMachineId());//机台id
			resultList.add(bean);
		}
		
		
		service.getEntityDaoInf().saveOrUpdateBatch(resultList);
		
	}

	/**
	 * 新增领料明细
	 * @param request
	 * @param mainId
	 * @param bean
	 * @return
	 */
	@RequestMapping({"edit"})
	public String eidt(HttpServletRequest request,Integer mainId){
		return "mauManage/rec_material/mauMaterialDetailedEdit";
	}
	
	/**
	 * 保存明细数据，这个步骤，操作还比较多
	 * @param request
	 * @param mainId
	 * @param bean
	 * @return
	 */
	@RequestMapping({"add"})
	@ResponseBody
	public HashMap<String,Object> addEdit(HttpServletRequest request,Integer mainId,MauMaterialDetail bean){
		
		//1.查询领料单主表
		MauMaterialRecord mainBean = mauMaterialRecordManageService.get(mainId);
		int workDay  = mainBean.getWorkDay();//工作时间,八位数
//		//2.通过工作日，找到在这一天需要工作的机台集合--->机台计划集合，
//		String hql = " from PlaMachinePlan where  workDay = ? ";
//		List<PlaMachinePlan> list = plaMachinePlanManageService.getEntityDaoInf().getHibernateTemplate().find(hql,workDay);
		
		return JsonWrapper.successWrapper("新增明细数据成功");
	}
	
	/**
	 * 点击领料按钮
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping({"receiveMater"})
	@ResponseBody
	public HashMap<String,Object> receiveMater(HttpServletRequest request){
		
		String jsonDataList = request.getParameter("data");
		
		//转成对象之后，保存相应的数据
		List<MauMaterialDetail> list=(List<MauMaterialDetail>)JSONArray.toList(JSONArray.fromObject(jsonDataList), MauMaterialDetail.class);
		//领取物料，更新相应的操作，包括1.是否超领，2.生成新的领料明细记录，3.更新领料明细记录
		
		
		//过滤值为领取数量为0或者为空的参数
		for (int i = 0; i < list.size(); i++) {
			MauMaterialDetail bean = list.get(i);
			BigDecimal big =  bean.getMaterAmount();
			BigDecimal bb = new BigDecimal(0);
			if(big==null||bb.compareTo(big)==0){
				list.remove(i);
				i--;
			}
		}
		
		try{
			//此处应该是叉车点击确认领料之后，调用的接口，包括通知仓库备货
			service.updateByJdbc(list);//更新领取数量到数据库
			
			
			//TODO 此处直接发消息队列，不做后续操作的，
			List<Integer> idList = new ArrayList<Integer>();
			for(MauMaterialDetail bean : list){
				idList.add(bean.getId());
			}
			String hql = " from MauMaterialDetail where id in (:ids) ";
			List<MauMaterialDetail> ll = service.getEntityDaoInf().getHibernateTemplate().findByNamedParam(hql, "ids", idList);
			//通知叉车领料
			service.sendForklift(ll, "未领取");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		//封装领料条件
		String mainId = request.getParameter("mainId");
		String hql = " from MauMaterialDetail where isReceive = '0' and mainId = ? ";
		List<MauMaterialDetail> ll = service.getEntityDaoInf().getHibernateTemplate().find(hql,Integer.parseInt(mainId));
		for(MauMaterialDetail bean : ll){
			if(bean.getMacCode()==null){
				Integer macId = bean.getMacId();
				MauMachine mauMachine = mauMachineManageService.get(macId);
				bean.setMacCode(mauMachine.getMacCode());
			}
		}
		
//		request.setAttribute("list", ll);
		
		
		return JsonWrapper.successWrapper(ll,"领料成功");
		
	}
	@RequestMapping({"test"})
	@ResponseBody
	public String test() throws Exception{
		
		String s = null;
		// 测试叉车确认领料接口
		final List<MauMaterialDetail> list = new ArrayList<MauMaterialDetail>();
		MauMaterialDetail bean = new MauMaterialDetail();
		bean.setId(618);
		list.add(bean);
		service.confirmReceive(list);
		
		return s;
	}
	
	public static void main(String[] args) {
		BigDecimal d = new BigDecimal(0);
		d = d.add(BigDecimal.valueOf(10));
		d = d.add(BigDecimal.valueOf(20));
		System.out.println(d);
	}
	
	
	
}
