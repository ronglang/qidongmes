package com.css.business.web.subsyscraft.craManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.service.CraSeqManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/craSeqManageAction")
public class CraSeqManageAction extends BaseSpringSupportAction<CraSeq, CraSeqManageService> {
	
	//@Autowired
	private CraSeqManageService service;
	
	@Override
	public CraSeqManageService getEntityManager() {
		return service;
	}

	public CraSeqManageService getService() {
		return service;
	}

	@Resource(name="craSeqManageService")
	public void setService(CraSeqManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craSeqEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craSeqForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craSeqList";
	}
	
	@RequestMapping(value={"/loadCraSeqManage"})
	@ResponseBody
	public HashMap<String,Object> loadCraSeqManage(String page,String pagesize){
		return service.getCraSeqManageService(page,pagesize);
	
	}
	@RequestMapping(value={"/deleteCraSeqManageByIds"})
	@ResponseBody
	public String deleteCraSeqManageByIds(int[] ids){
		int [] a=ids;
		service.deleteCraSeqService(a);
		return "删除成功";
	}
	@RequestMapping(value={"/updateCraSeqManage"})
	@ResponseBody
	public String updateCraSeqManage(CraSeq craSeq){
		service.updateCraSeqService(craSeq);
		return "更新成功";
	}
	
	@RequestMapping(value={"/addCraSeqManage"})
	@ResponseBody
	public String addCraSeqManage(CraSeq row){
		service.addCraSeqService(row);
		return "新增成功";
	}
	
	@RequestMapping(value={"/serchBySeqCode"})
	@ResponseBody
	public HashMap<String,Object> serchBySeqCode(String page,String pagesize,String seqCode){
		return service.getserchBySeqCodeService(page,pagesize,seqCode);
	}
	

	/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTree" })
	@ResponseBody
	public HashMap<String, Object> nodeTree(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().nodeTree(SessionUtils.getUser(request) ,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getSeqCode"})
	@ResponseBody
	public Map[] getSeqCode(){
		Map[] str = service.getSeqCode();
		return str;
	}
	
	
}
