/**
 * 在单系统控制器
 */
package com.css.business.web.subsyssell.sellManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.bean.PlaWeekSeqHours;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaWeekSeqHoursManageService;
import com.css.business.web.subsysplan.plaManage.service.SchedulingManageService;
import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.sellManage.service.InSingleSystemManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/inSingleSystemManageAction")
public class InSingleSystemManageAction extends BaseSpringSupportAction<SellContractDetail, InSingleSystemManageService>{
	@Autowired
	private InSingleSystemManageService inSingleSystemManageService;
	
	@Autowired
	private PlaProductOrderManageService plaProductOrderManageService;//生产令服务接口
	
	@Autowired
	private PlaWeekSeqHoursManageService plaWeekSeqHoursManageService;
	
	@Autowired
	private SchedulingManageService schedulingManageService;
	
	
	
	@Override
	public InSingleSystemManageService getEntityManager() {
		return inSingleSystemManageService;
	}

	/**
	 * 在单系统页面跳转
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({"inSingleSystemPage"})
	public String inSingleSystemPage(HttpServletRequest request ,HttpServletResponse response){
		return "sellManage/inSingleSystem/inSingleSystemList";
	}
	/**
	 * 在单系统，数据请求路径,相当于预排产系统
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping({"inSingleSystemData"})
	@ResponseBody
	public Page inSingleSystemData(HttpServletRequest request,Page page){
		try {
			SysUser user = SessionUtils.getUser(request);
			//TODO 请求数据之前需要有一个预排产的动作
			/**
			 * TODO 预排产，在计算在单系统的时候，需要考虑预排产(这个预排产--->生产令已经在曾兵的哪个页面操作了)
			 */
			//这里本应该还有合同明细到生产令的一个步骤
			
			//1.查询数据库中，生产令状态为2的生产令，表示为预排产，严格来说，还需要从合同处生成预排产记录
			
			List<Integer> sclList = new ArrayList<Integer>();//生产令id集合
			String is_flag = "2";
			List<PlaProductOrder> orderList = plaProductOrderManageService.findByOrderState2(is_flag);//查询接口
			if(orderList==null||orderList.size()==0){
				return page;
			}
			//调用姜帅接口，先判断并生成工时记录,该处接口需要将list装换成map
			List<Object> paraList = new ArrayList<Object>();
			for(PlaProductOrder bean : orderList){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("proGgxh", bean.getProGgxh());
				map.put("productPartLen", bean.getProductPartLen());
				map.put("id", bean.getId());
				map.put("amount", bean.getAmount());
				paraList.add(map);
				sclList.add(bean.getId());
			}
			//姜帅返回的工时计算接口
			HashMap<String, Object> jsMap = plaWeekSeqHoursManageService.savePlaWeekSeqHours_3(user,null, paraList);
			
			
			if(Boolean.valueOf(jsMap.get("success").toString())){//表示成功或不成功，不成功将不进行后续操作
				//调用排产，接口生成is_flag = 2 的预排产计划
				List<Object> ll = (List<Object>) jsMap.get("data");
				List<PlaWeekSeqHours> plaWeekSeqHoursList = new ArrayList<PlaWeekSeqHours>();
				for(Object obj : ll){
					PlaWeekSeqHours hour = (PlaWeekSeqHours)obj;
					plaWeekSeqHoursList.add(hour);
				}
				//TODO 此处不必修改生产令状态，这部分工作应该是合同预排产接口生产生产令。
				
				//TODO 需要在形参部分加入一个is_flag状态表示预排产还是正常排产，还是已经正常排产0，表示正常排产，1表示修改为排产，2表示为预排产
				//schedulingManageService.scheduling(user,orderList, plaWeekSeqHoursList,is_flag);
			}else{//不具备预排产的条件，
				
			}
			
			//TODO 考虑之前的合同到生产令的
			inSingleSystemManageService.setData(request,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	
}
