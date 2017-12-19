package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauMachineSpeed;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineSpeedManageService;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/mauMachineSpeedManageAction")
public class MauMachineSpeedManageAction extends
		BaseSpringSupportAction<MauMachineSpeed, MauMachineSpeedManageService> {

	// @Autowired
	@Resource(name = "mauMachineSpeedManageService")
	private MauMachineSpeedManageService service;


	@RequestMapping({ "machinespeed" })
	public String toMauSpeedIndex() {

		return "mauManage/mauMachineSpeedList";
	}

	/**
	 * 根据产品规格型号查询【机台支持的产品规格型号与对应生产速度表数据】
	 */
	@RequestMapping({ "machinspeedlist" })
	public void getMauMachine() {
		String pro = "ASD0";
		List<MauMachineSpeed> list = service.getListService(pro);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getSpeed());
		}
	}


	@Override
	public MauMachineSpeedManageService getEntityManager() {
		return service;
	}

	public MauMachineSpeedManageService getService() {
		return service;
	}

	@Resource(name = "mauMachineSpeedManageService")
	public void setService(MauMachineSpeedManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineSpeedEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineSpeedForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineSpeedList";
	}
	
	@RequestMapping({ "toAddDataPage" })
	public String toAddEditPage(){
		
		return "mauManage/mauMachineSpeedAddForm";
	}
	
	@RequestMapping({ "getMauMachineSpeed" })
	@ResponseBody
	public Page getMauMachineSpeed(Page p, String proGgxh) {
		Page page = service.getMauMachineSpeedGrid(p, proGgxh);
		return page;
	}

	@RequestMapping({ "saveMauMachineSpeed" })
	@ResponseBody
	public Map<String,Object> saveOrUpdate(String li) {
		JSONArray ja = JSONArray.fromObject(li);
		List<Object> list = JsonUtil.jsonToList(ja);
		for (Object obj : list) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			MauMachineSpeed mms = new MauMachineSpeed();
			if (map.get("id") != null) {
				mms.setId(Integer.parseInt(map.get("id").toString()));
				mms.setDownTime(Double.parseDouble(map.get("downTime")
						.toString()));
				mms.setMachineId(Integer.parseInt(map.get("machineId")
						.toString()));
				mms.setProGgxh(map.get("proGgxh").toString());
				mms.setReadyTime(Double.parseDouble(map.get("readyTime")
						.toString()));
				mms.setUpTime(Double.parseDouble(map.get("upTime").toString()));
				mms.setSpeed(Double.parseDouble(map.get("speed").toString()));
				// TODO 执行update方法
				try {
					service.updateMauMachineSpeed(mms);
				} catch (Exception e) {
					e.printStackTrace();
					return JsonWrapper.failureWrapperMsg(mms, "false");
				}
				
			} else {
				mms.setDownTime(Double.parseDouble(map.get("downTime")
						.toString()));
				mms.setMachineId(Integer.parseInt(map.get("machineId")
						.toString()));
				mms.setProGgxh(map.get("proGgxh").toString());
				mms.setReadyTime(Double.parseDouble(map.get("readyTime")
						.toString()));
				mms.setUpTime(Double.parseDouble(map.get("upTime").toString()));
				mms.setSpeed(Double.parseDouble(map.get("speed").toString()));
				mms.setCreateBy("TG");
				mms.setCreateDate(new Date());
				// TODO 执行save方法
				service.saveMauMachineSpeed(mms);
			}

		}
		return JsonWrapper.successWrapper(null, "success");
	}

}
