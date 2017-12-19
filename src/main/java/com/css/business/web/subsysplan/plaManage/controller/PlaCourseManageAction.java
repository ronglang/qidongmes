package com.css.business.web.subsysplan.plaManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.plaManage.bean.PlaContractVo;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.business.web.subsysplan.vo.NeedPartPlaCourseVo;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.commcon.util.SessionUtils;
import com.css.common.annotation.Remark;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/plaCourseManageAction")
public class PlaCourseManageAction extends
		BaseSpringSupportAction<PlaCourse, PlaCourseManageService> {

	// @Autowired
	private PlaCourseManageService service;

	private Gson gson = new Gson();

	@Override
	public PlaCourseManageService getEntityManager() {
		return service;
	}

	public PlaCourseManageService getService() {
		return service;
	}

	@Resource(name = "plaCourseManageService")
	public void setService(PlaCourseManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaCourseEdit";
	}

	/**
	 * 去统计查询中的进度查询主页面
	 * 
	 * @return
	 */
	@RequestMapping("toSchedulePage")
	public String toSchedulePage() {
		return "totalQuery/schedule/schedulePage";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaCourseForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaCourseList";
	}

	@RequestMapping({ "toQueryList" })
	public String toQueryList(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/statisticalQuery/planCourseStatisticalList";
	}

	@RequestMapping({ "toQueryForm" })
	public String toQueryForm(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/statisticalQuery/planCourseStatisticalForm";
	}

	@RequestMapping({ "toQueryTable" })
	public String toQueryTable(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/statisticalQuery/planCourseStatisticalTable";
	}

	@RequestMapping({ "toQueryEdit" })
	public String toQueryEdit(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/statisticalQuery/planCourseStatisticalEdit";
	}

	@RequestMapping({ "toGenPlanListPage" })
	public String toGenPlanListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/course/plaCourseGenPlan";
	}

	@RequestMapping({ "gotoGenDetail" })
	public String gotoGenDetail(HttpServletRequest request, String ids,
			String proCraftCode, String proGgxh, String proColor) {
		request.setAttribute("id", ids);
		request.setAttribute("proCraftCode", proCraftCode);
		String gds = service.getGDInfoStr(ids);
		request.setAttribute("gdh", gds);
		request.setAttribute("proGgxh", proGgxh);
		request.setAttribute("proColor", proColor);
		return "plaManage/course/plaCourseSelectMachine";
	}

	@RequestMapping("toPageList")
	public String toPageList() {
		return "plaManage/course/plaCourseList";
	}

	@RequestMapping({ "getTest" })
	public List<PlaContractVo> getTest() {

		return null;
	}

	@RequestMapping({ "saveOrUpdate" })
	@ResponseBody
	public HashMap<String, Object> saveOrUpdate(PlaCourse bean) {
		try {
			service.save(bean);
			return JsonWrapper.successWrapper(null, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "失败");
		}
	}

	@RequestMapping({ "getPlaCourseGrid" })
	@ResponseBody
	public Page getPlaCourseGrid(Page p, String wsCode, String planEnableDate,
			String isFinish, String proCraftCode) {
		Page page = service.getPlaCourseGrid(p, wsCode, planEnableDate,
				isFinish, proCraftCode);
		return page;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getWsCode" })
	@ResponseBody
	public Map[] getWsCode() {
		Map[] str = service.getWsCode();
		return str;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getProCraftCode" })
	@ResponseBody
	public Map[] getProCraftCode() {
		Map[] str = service.getProCraftCode();
		return str;
	}

	/**
	 * 统计查询工作单条件查询
	 * 
	 * @param p
	 * @param param
	 * @return
	 */
	@RequestMapping({ "getScheduleGrid" })
	@ResponseBody
	public Page getScheduleGrid(Page p, String param) {
		Map<String, String> map = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());
		Page page = service.getScheduleGrid(p, map);
		return page;
	}

	/**
	 * @todo: 工单管理，查询工单详细信息
	 * @author : zhaichunlei
	 * @throws Exception
	 * @date: 2017年9月21日
	 */
	@RequestMapping({ "getCourseInfoList" })
	@ResponseBody
	public Page getCourseInfoList(HttpServletRequest req, Page p, PlaCourse ent)
			throws Exception {
		Page page = service.getCourseInfoList(p, ent);
		return page;
	}

	/**
	 * @todo: 工单计划生成
	 * @author : zhaichunlei
	 * @date: 2017年9月21日
	 */
	@RequestMapping({ "genPlanByGd_step2" })
	@ResponseBody
	public HashMap<String, Object> genPlanByGd_step2(HttpServletRequest req,
			String ids) {
		try {
			SysUser user = SessionUtils.getUser(req);
			service.genPlanByGd_step2(user, ids);
			return JsonWrapper.successWrapper("", "成功生成计划");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	/**
	 * 
	 * @Description: 获得当日的工单生产情况
	 * @param req
	 * @return
	 */
	@RequestMapping("getTodayComplete")
	@ResponseBody
	public PlaMachineDisplayVo getTodayComplete(HttpServletRequest req) {
		PlaMachineDisplayVo vo = service.getTodayComplete();
		return vo;
	}

	@Remark(toDo = "删除工单，及周边数据")
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
		if (StringUtil.isEmpty(ids)) {
			this.log.error("要删除的ID集合为空");
			return JsonWrapper.failureWrapperMsg("要删除的目标对象ID为空！");
		}
		try {
			// this.getEntityManager().deleteCourse(ids);
			HashMap<String, Object> map = JsonWrapper.successWrapper(ids,
					"删除成功");
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	/**
	 * TODO
	 * 
	 * @param id
	 * @return
	 */
	// @RequestMapping("getGDInfo")
	// @ResponseBody
	// public HashMap<String,Object> getGDInfo(Integer courseId,String seqCode ,
	// Integer machineId,String proGgxh,String proColor){
	// try {
	// //if(true) throw new Exception("tttttt");
	// PlaDisPatchVo vo = service.getGDInfo(courseId,seqCode,
	// machineId,proGgxh,proColor);
	// return JsonWrapper.successWrapper(vo);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return JsonWrapper.failureWrapperMsg(e.getMessage());
	// }
	// }
	//
	@RequestMapping("exportExcels")
	@ResponseBody
	public HashMap<String, Object> exportScheduleExcel(
			HttpServletResponse response, Page page, String param) {
		Map<String, String> map = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());
		try {
			service.exportScheduleExcels(response, page, map);
			return JsonWrapper.successWrapper("导出成功");
		} catch (Exception e) {

			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}

	}

	/**
	 * 工作单模糊查询
	 * 
	 * @param request
	 * @param paramPage
	 * @param entity
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "plaCourseDataGridPage" })
	@ResponseBody
	// @Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	public Page plaCourseDataGridPage(HttpServletRequest request,
			Page paramPage, String param) throws Exception {
		Gson gson = new Gson();
		Map<String, String> map = gson.fromJson(param,
				new TypeToken<Map<String, String>>() {
				}.getType());

		PlaCourse p = new PlaCourse();
		p.setWsCode(map.get("wsCode"));
		p.setScCode(map.get("scCode"));
		p.setWsType(map.get("wsType"));
		return super.dataGridPage(request, paramPage, p);
	}

	// /**
	// * 查询所有的工作单类型
	// * @param param
	// */
	// @SuppressWarnings("unchecked")
	// @RequestMapping("plaCourseWsType")
	// @ResponseBody
	// public List<String> plaCourseWsType(String param){
	// List<String> list=new ArrayList<String>();
	// list=service.plaCourseWsType();
	// return list;
	// }

	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request, String param, Page page) {
		page = service.getListPage(page, param);
		return page;
	}

	@RequestMapping("toLookRate")
	public String toLookRate(HttpServletRequest request, String id) {
		request.setAttribute("id", id);
		PlaCourse plaCourse = service.get(Integer.parseInt(id));
		request.setAttribute("courseCode", plaCourse.getWsCode());
		return "plaManage/course/lookRate";
	}

	@RequestMapping("getRateEchart")
	@ResponseBody
	public HashMap<String, Object> getRateEchart(HttpServletRequest request,
			String workCode) {
		try {
			EchartsVo vo = service.getRateEchart(workCode);
			return JsonWrapper.successWrapper(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("查询失败");
		}
	}

	/**
	 * 
	 * @Description:生成计划
	 * @param request
	 * @param id
	 *            工单id
	 * @return
	 */
	@RequestMapping("setPlaMacTask")
	@ResponseBody
	public HashMap<String, Object> setPlaMacTask(HttpServletRequest request,
			String id) {
		try {
			service.setPlaMacTask(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("生成计划失败:" + e.getMessage());
		}
		return JsonWrapper.successWrapper(null, "生成计划成功");
	}

	/**
	 * 
	 * @Description:查看在单系统
	 * @return
	 */
	@RequestMapping("toInSystemCourse")
	public String toInSystemCourse() {
		return "plaManage/course/inSystemCourseList";
	}

	@RequestMapping("getInSystemEchartVo")
	@ResponseBody
	public HashMap<String, Object> getInSystemEchartVo(
			HttpServletRequest request, String param) {
		try {
			EchartsVo vo = service.getInSystemEchartVo(param);
			return JsonWrapper.successWrapper(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("查询失败");
		}
	}

	/**
	 * 
	 * @Description:生产所有planflag=否的开单的工单计划
	 * @return
	 */
	@RequestMapping("setAllPlaCoursePlan")
	@ResponseBody
	public HashMap<String, Object> setAllPlaCoursePlan() {

		try {
			service.setAllPlaCoursePlan();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("生成失败");
		}
		return JsonWrapper.successWrapper(null, "生成成功");
	}

	/**
	 * 
	 * @Description:生产所有planflag=否的插单
	 * @return
	 */
	@RequestMapping("setInsertPlaCoursePlan")
	@ResponseBody
	public HashMap<String, Object> setInsertPlaCoursePlan() {
		try {
			service.setInsertPlaCoursePlan();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("生成失败");
		}
		return JsonWrapper.successWrapper(null, "生成成功");
	}

	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request, String orderCode,
			Page page) {
		page = service.getPageList(orderCode, page);
		return page;
	}

	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object> clearBean(HttpServletRequest request,
			String wsCode) {
		try {
			service.clearBean(wsCode);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		}
		return JsonWrapper.successWrapper(null, "删除成功");
	}

	@RequestMapping("getNowSeq")
	@ResponseBody
	public HashMap<String, Object> getNowSeq(HttpServletRequest request,
			String wsCode) {
		try {
			String seqName = service.getNowSeq(wsCode);
			return JsonWrapper.successWrapper(seqName);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.successWrapper("未查到");
		}
	}

	@RequestMapping("getMorePageList")
	@ResponseBody
	public Page getMorePageList(HttpServletRequest request, String param,
			Page page) {
		page = service.getListPage(page, param);
		List<PlaCourse> data = page.getData();
		List<Object> list = new ArrayList<>();
		for (PlaCourse plaCourse : data) {
			PlaCourse pc = new PlaCourse();
			String seqName = service.getNowSeq(plaCourse.getWsCode());
			plaCourse.setNowSeq(seqName);
			list.add(plaCourse);
		}
		page.setData(list);
		return page;
	}

	/**
	 * 获取可以合单的工作单
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping({ "getNeedPartPlaCourse" })
	@ResponseBody
	public Page getNeedPartPlaCourse(Page p) {
		try {
			return service.getNeedPartPlaCourse(p);
		} catch (Exception e) {
			e.printStackTrace();
			return p;
		}
	}

	/**
	 * 合单页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping({ "tounionCourse" })
	public String tounionCourse(HttpServletRequest request, Integer id) {
		return "plaManage/course/unionCourse";
	}

	/**
	 * 得到合单的工作单并进行合单
	 * 
	 * @param bean
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "getUnionCourseWsCode" })
	@ResponseBody
	public HashMap<String, Object> getUnionCourseWsCode(String bean) {
		Gson gson = new Gson();
		List<NeedPartPlaCourseVo> listVo = gson.fromJson(bean,
				new TypeToken<List<NeedPartPlaCourseVo>>() {}.getType());
		return service.getUnionCourseWsCode(listVo);
	}

}
