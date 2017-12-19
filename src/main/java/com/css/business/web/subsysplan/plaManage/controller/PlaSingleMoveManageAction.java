package com.css.business.web.subsysplan.plaManage.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaSingleMove;
import com.css.business.web.subsysplan.plaManage.bean.plaSingleMoveVo;
import com.css.business.web.subsysplan.plaManage.service.PlaSingleMoveManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/plaSingleMoveManageAction")
public class PlaSingleMoveManageAction extends
		BaseSpringSupportAction<PlaSingleMove, PlaSingleMoveManageService> {

	// @Autowired
	private PlaSingleMoveManageService service;

	@Override
	public PlaSingleMoveManageService getEntityManager() {
		return service;
	}

	public PlaSingleMoveManageService getService() {
		return service;
	}

	@Resource(name = "plaSingleMoveManageService")
	public void setService(PlaSingleMoveManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveList";
	}

	@RequestMapping({ "toTablePage" })
	public String toTablePage(HttpServletRequest request, Integer id,
			String batCode, Integer mainId, String proGgxh) {
		request.setAttribute("id", id);
		request.setAttribute("batCode", batCode);
		request.setAttribute("mainId", mainId);
		request.setAttribute("proGgxh", proGgxh);
		return "plaManage/plaSingleMoveTable";
	}

	/*@RequestMapping({ "getplaSingleMove" })
	@ResponseBody
	public Page getplaSingleMove(Page p, String wsCode) {
		try {
			Page page = service.getplaSingleMove(p, wsCode);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	/*@SuppressWarnings("rawtypes")
	@RequestMapping({ "getWsCode" })
	@ResponseBody
	public Map[] getWsCode() {
		Map[] wsCode = service.getWsCode();
		return wsCode;
	}
*/
	/*@RequestMapping({ "getSellContractPlanBatchById" })
	@ResponseBody
	public SellContractPlanBatch getSellContractPlanBatchById(Integer id) {
		SellContractPlanBatch sellContractPlanBatch = service
				.getSellContractPlanBatchById(id);
		return sellContractPlanBatch;
	}*/

	/**
	 * 挪单
	 * 
	 * @param datali
	 *            目标挪单数据 及 被挪单数据
	 * @param id
	 *            SellContractPlanBatch 的id
	 * @return
	 * @author JS
	 */
	/*@RequestMapping({ "singleMove" })
	@ResponseBody
	public HashMap<String, Object> singleMove(HttpServletRequest req,
			String datali, Integer id, String datali2) {
		try {
			SysUser user = SessionUtils.getUser(req);
			Gson gson = new Gson();
			String li = URLDecoder.decode(datali, "UTF-8");
			List<plaSingleMoveVo> voLi = gson.fromJson(li,
					new TypeToken<List<plaSingleMoveVo>>() {
					}.getType()); // 获取目标挪单数据及
			List<plaSingleMoveVo> voLi2 = null;
			if (datali2 != null && !"".equals(datali2)) {
				String li2 = URLDecoder.decode(datali2, "UTF-8");
				voLi2 = gson.fromJson(li2,
						new TypeToken<List<plaSingleMoveVo>>() {
						}.getType()); // 获取补单数据
			}
			HashMap<String, Object> hashMap = service.singleMove(user, voLi,
					id, voLi2);
			return hashMap;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e, null);
		}
	}*/

}
