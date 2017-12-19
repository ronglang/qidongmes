package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauAxisMac;
import com.css.business.web.subsysmanu.mauManage.service.MauAxisMacManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/mauAxisMacManageAction")
public class MauAxisMacManageAction extends
		BaseSpringSupportAction<MauAxisMac, MauAxisMacManageService> {

	// @Autowired
	private MauAxisMacManageService service;

	@Override
	public MauAxisMacManageService getEntityManager() {
		return service;
	}

	public MauAxisMacManageService getService() {
		return service;
	}

	@Resource(name = "mauAxisMacManageService")
	public void setService(MauAxisMacManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauUnderShaftEdit";
	}

	@RequestMapping("toListPage")
	public String toListPage() {
		return "mauManage/mauaxis/mauAxisMac";
	}

	@RequestMapping("toFormPage")
	public String toFormPage(HttpServletRequest request, String id) {
		request.setAttribute("id", id);
		return "mauManage/mauaxis/mauAxisMacForm";
	}


	/**
	 * 获取线轴名称
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getAxisName" })
	@ResponseBody
	public Map[] getAxisName() {
		Map[] name = service.getAxisName();
		return name;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getMacCode" })
	@ResponseBody
	public Map[] getMacCode() {
		Map[] name = service.getMacCode();
		return name;
	}

	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request, Page page,
			String macCode, String axisName) {
		page = service.getListPage(page, macCode, axisName);
		return page;
	}

	private Gson gson = new Gson();

	@RequestMapping("saveBeans")
	@ResponseBody
	public HashMap<String, Object> saveBeans(HttpServletRequest request,
			String beans, String macCode) {
		Map<String, List<String>> map = gson.fromJson(beans,
				new TypeToken<Map<String, List<String>>>() {
				}.getType());
		// 放线轴
		List<String> inList = map.get(MauAxisMac.AXIS_TYPE_IN);
		MauAxisMac axisMac = new MauAxisMac();
		axisMac.setCreateBy(SessionUtils.getUser(request).getAccount());
		axisMac.setCreateDate(new Date());
		axisMac.setMacCode(macCode);
		try {
			for (String axisName : inList) {
				axisMac.setId(null);
				axisMac.setAxisName(axisName);
				axisMac.setAxisType(MauAxisMac.AXIS_TYPE_IN);
				service.save(axisMac);
			}
			// 出线轴
			List<String> outList = map.get(MauAxisMac.AXIS_TYPE_OUT);
			for (String axisName : outList) {
				axisMac.setId(null);
				axisMac.setAxisName(axisName);
				axisMac.setAxisType(MauAxisMac.AXIS_TYPE_OUT);
				service.save(axisMac);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
		return JsonWrapper.successWrapper(null, "保存成功");
	}

	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public HashMap<String, Object> saveOrUpdate(HttpServletRequest request,
			String bean) {
		MauAxisMac axisMac = gson.fromJson(bean, MauAxisMac.class);
		axisMac.setCreateBy(SessionUtils.getUser(request).getAccount());
		axisMac.setCreateDate(new Date());
		try {
			if (axisMac.getId() == null) {
				// 新建
				service.save(axisMac);

			} else {
				// 更新
				service.updateByCon(axisMac, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
		return JsonWrapper.successWrapper(null, "保存成功");

	}

	@RequestMapping("getById")
	@ResponseBody
	public HashMap<String, Object> getById(HttpServletRequest request, String id) {
		try {
			MauAxisMac mauAxisMac = service.get(Integer.parseInt(id));
			return JsonWrapper.successWrapper(mauAxisMac, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
	}

	@RequestMapping("clearBean")
	@ResponseBody
	public HashMap<String, Object> clearBean(HttpServletRequest request,
			String id) {
		try {
			service.deleteBusiness(Integer.parseInt(id));
			return JsonWrapper.successWrapper(null, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("保存失败");
		}
	}
}
