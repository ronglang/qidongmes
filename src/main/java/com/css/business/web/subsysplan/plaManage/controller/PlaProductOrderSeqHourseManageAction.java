package com.css.business.web.subsysplan.plaManage.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderSeqHourseManageService;
import com.css.business.web.subsysplan.plaManage.utils.CalculaMaterUtil;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/plaProductOrderSeqHourseManageAction")
public class PlaProductOrderSeqHourseManageAction
		extends
		BaseSpringSupportAction<PlaProductOrderSeqHourse, PlaProductOrderSeqHourseManageService> {
	private PlaProductOrderSeqHourseManageService service;

	@Override
	public PlaProductOrderSeqHourseManageService getEntityManager() {
		return service;
	}

	public PlaProductOrderSeqHourseManageService getService() {
		return service;
	}

	@Resource(name = "plaProductOrderSeqHourseManageService")
	public void setService(PlaProductOrderSeqHourseManageService service) {
		this.service = service;
	}

	@RequestMapping("toListPage")
	public String toListPage(HttpServletRequest req) {

		return "plaManage/plaProductOrderSeqHourseList";
	}

	@RequestMapping("toFormPage")
	public String toFormPage(HttpServletRequest req, String date, String seqName) {
		req.setAttribute("date", date);
		req.setAttribute("seqName", seqName);
		return "plaManage/plaProductOrderSeqHourseForm";
	}

	@RequestMapping("toEditPage")
	public String toEditPage(HttpServletRequest req) {

		return "plaManage/plaProductOrderSeqHourseEdit";
	}

	@RequestMapping("toTablePage")
	public String toTablePage(HttpServletRequest req, String date,
			String seqName) {
		req.setAttribute("date", date);
		req.setAttribute("seqName", seqName);
		return "plaManage/plaProductOrderSeqHourseTable";
	}

	/**
	 * 获取GRID中的数据,并展示
	 * 
	 * @param p
	 * @param date
	 * @param seqName
	 * @param macCode
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "getPlaProductOrderSeqHourse" })
	@ResponseBody
	public Page getPlaProductOrderSeqHourse(Page p, String date,
			String seqName, String macCode) {
		Page page;
		String name = "";
		try {
			if (macCode != null && !"".equals(macCode)) {
				name = URLDecoder.decode(macCode, "UTF-8");
			}
			page = service.getPlaProductOrderSeqHourse(p, date, seqName, name);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到员工信息
	 * 
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getEmployee" })
	@ResponseBody
	public Map[] getEmployee() {
		Map[] str = service.getEmployee();
		return str;
	}

	/**
	 * 
	 * @param ids
	 *            //需要修改的目标id
	 * @param hours
	 *            //
	 * @param macName
	 * @param workDay
	 * @return
	 */
	@RequestMapping({ "savePlaProductOrderSeqHourse" })
	@ResponseBody
	public HashMap<String, Object> savePlaProductOrderSeqHourse(HttpServletRequest req ,String ids, String hours,
			String macCode, String workDay, String nowDate) {
		// 将前台得到的数据String类型转成List
		SysUser user = SessionUtils.getUser(req);
		JSONArray ja = JSONArray.fromObject(ids);
		List<Object> list = JsonUtil.jsonToList(ja);
		// 循环list,得到Map格式的数据
		HashMap<String, Object> map2  = new HashMap<String, Object>();
		try {
			String name = URLDecoder.decode(macCode, "UTF-8"); // 解码获得机台名称
			for (Object obj : list) {
				PlaProductOrderSeqHourse pos = new PlaProductOrderSeqHourse();
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				pos.setId(Integer.parseInt(map.get("id").toString()));
				pos.setMacCode(name);
				// 用PlaProductOrderSeqHourse的SeqHours暂存hours
				pos.setSeqHours(BigDecimal.valueOf(Double.parseDouble(hours)));
				pos.setWorkDay(Integer.parseInt(CalculaMaterUtil
						.strToDateFormat(workDay, "yyyyMMdd")));
				map2 = service.savePlan(user,pos, Integer.parseInt(CalculaMaterUtil
						.strToDateFormat(nowDate, "yyyyMMdd")));
				// TODO 对象换成VO，在service层进行封装数据并更新。
			}
			return map2;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "发生了未知错误");
		}
	}

	/**
	 * 删除功能(当前未完善)
	 * 
	 * @param data
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "delPlaProductOrderSeqHourse" })
	@ResponseBody
	public boolean delPlaProductOrderSeqHourse(String data) {
		JSONArray ja = JSONArray.fromObject(data);
		List<Object> list = JsonUtil.jsonToList(ja);
		try {
			for (Object obj : list) {
				// TODO 删除
				PlaProductOrderSeqHourse pos = new PlaProductOrderSeqHourse();
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) obj;
				pos.setId(Integer.parseInt(map.get("id").toString()));
				service.delPlaProductOrderSeqHourse(pos);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
