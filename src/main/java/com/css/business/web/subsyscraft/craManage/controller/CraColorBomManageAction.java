package com.css.business.web.subsyscraft.craManage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraColorBom;
import com.css.business.web.subsyscraft.craManage.service.CraColorBomManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/craColorBomManageAction")
public class CraColorBomManageAction extends
		BaseSpringSupportAction<CraColorBom, CraColorBomManageService> {

	// @Autowired
	private CraColorBomManageService service;

	@Override
	public CraColorBomManageService getEntityManager() {
		return service;
	}

	public CraColorBomManageService getService() {
		return service;
	}

	@Resource(name = "craColorBomManageService")
	public void setService(CraColorBomManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCraftEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCraftForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCraftList";
	}

	@RequestMapping({ "toTablePage" })
	public String toTablePage(HttpServletRequest request, Integer id,
			String seqCode, String proGgxh, Integer flag, Integer seqStep) {
		request.setAttribute("id", id);
		request.setAttribute("seqCode", seqCode);
		request.setAttribute("seqStep", seqStep);
		String decode = null;
		try {
			if (proGgxh != null && !"".equals(proGgxh)) {
				decode = URLDecoder.decode(proGgxh, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("proGgxh", decode);
		request.setAttribute("flag", flag);
		return "craManage/seqBomParam/craColorBomTable";
	}

	private Gson gson = new Gson();

	@RequestMapping("saveOrUpdateBeans")
	@ResponseBody
	public HashMap<String, Object> saveOrUpdateBeans(
			HttpServletRequest request, String beans) {
		try {
			String decode = URLDecoder.decode(beans, "UTF-8");
			List<CraColorBom> list = gson.fromJson(decode,
					new TypeToken<List<CraColorBom>>() {
					}.getType());
			for (CraColorBom craColorBom : list) {
				if (craColorBom.getId() == null) {
					service.save(craColorBom);
				} else {
					service.updateByCon(craColorBom, false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(beans, "保存失败");
		}
		return JsonWrapper.successWrapper(null, "保存成功");
	}

	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request, Page page,
			String proGgxh, String seqCode, String seqStep) {
		try {
			String decode = URLDecoder.decode(proGgxh, "UTF-8");
			Page queryPage = service.getListPage(page, decode, seqCode,seqStep);
			return queryPage;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@RequestMapping("clearBeans")
	@ResponseBody
	public HashMap<String, Object> clearBeans(HttpServletRequest request,
			String ids) {
		List<String> allId = gson.fromJson(ids, new TypeToken<List<String>>() {
		}.getType());
		try {
			for (String id : allId) {
				service.deleteBusiness(Integer.parseInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("删除失败");
		}
		return JsonWrapper.successWrapper(null, "删除成功");
	}

}
