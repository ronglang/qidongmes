package com.css.business.web.subsyssell.sellManage.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyssell.bean.SellContractPlanBatch;
import com.css.business.web.subsyssell.sellManage.service.SellContractPlanBatchManageService;
import com.css.common.annotation.Remark;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/sellContractPlanBatchManageAction")
public class SellContractPlanBatchManageAction extends BaseSpringSupportAction<SellContractPlanBatch, SellContractPlanBatchManageService> {
	
	//@Autowired
	private SellContractPlanBatchManageService service;
	
	@Override
	public SellContractPlanBatchManageService getEntityManager() {
		return service;
	}

	public SellContractPlanBatchManageService getService() {
		return service;
	}

	@Resource(name="sellContractPlanBatchManageService")
	public void setService(SellContractPlanBatchManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sellManage/sellContractPlanBatchEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sellManage/sellContractPlanBatchForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, String scCode){
		request.setAttribute("scCode", scCode);
		return "sellManage/sellContractPlanBatchList";
	}
	
	@Remark(toDo="删除数据")
	@RequestMapping({ "delete" })
	@ResponseBody
	//@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@Override
	public HashMap<String, Object> delete(String ids) throws Exception {
			if (StringUtil.isEmpty(ids)) {
				this.log.error("要删除的ID集合为空");
				return JsonWrapper.failureWrapperMsg("要删除的目标对象ID为空！");
			}
			
			try {
				service.deleteBat(ids);
				return JsonWrapper.successWrapper(ids,"删除成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg(e.getMessage());
			}
	}
}
