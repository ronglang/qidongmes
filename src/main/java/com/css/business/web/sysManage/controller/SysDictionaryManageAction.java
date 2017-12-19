package com.css.business.web.sysManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysDictionary;
import com.css.business.web.sysManage.bean.SysMetaData;
import com.css.business.web.sysManage.bean.TreeVoR;
import com.css.business.web.sysManage.service.SysDictionaryManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.Constant;
import com.css.common.util.ReflectHelper;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/sysDictionaryManageAction")
public class SysDictionaryManageAction extends BaseSpringSupportAction<SysDictionary, SysDictionaryManageService> {
	
	//@Autowired
	private SysDictionaryManageService service;
	
	@Override
	public SysDictionaryManageService getEntityManager() {
		return service;
	}

	public SysDictionaryManageService getService() {
		return service;
	}

	@Resource(name="sysDictionaryManageService")
	public void setService(SysDictionaryManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id,String PID){
		request.setAttribute("id", id);
		request.setAttribute("PID", PID);
		if(id!=null){
			request.setAttribute("createBy", this.getEntityManager().get(id).getCreateBy());
			request.setAttribute("createDate", this.getEntityManager().get(id).getCreateDate());
		}
		return "sysManage/sysDictionaryEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysDictionaryForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysDictionaryList";
	}
	
	/**
	 * 查询数据字典的父类
	 * @param paramPage
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping({ "findAllParent" })
	@ResponseBody
	public Page findAllParent(Page paramPage) throws Exception {
		paramPage = service.findAllParent(paramPage);
		return paramPage;
	}
	
	/**
	 * 查询数据字典父类对应的子类
	 * @param paramPage
	 * @param PID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "findAllChildren" })
	@ResponseBody
	public Page findAllChildren(Page paramPage,String PID) throws Exception {
		paramPage = service.findAllChildren(paramPage,PID);
		return paramPage;
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
			list = this.getEntityManager().getTree(request,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTreeName" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeName(String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTreeName(qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
	}
	
	@RequestMapping({ "metaDataByTabname" })
	@ResponseBody
	public HashMap<String, Object> metaDataByTabname(String tabname){
		List<SysMetaData> list;
		try {
			list = this.getEntityManager().metaDataByTabname(tabname);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询表："+tabname+" 元数据错误",e);
			return JsonWrapper.failureWrapperMsg("查询表："+tabname+" 元数据错误");
		}
	}
	
	@RequestMapping({ "saveDictionary" })
	@ResponseBody
	public HashMap<String, Object> saveDictionary(HttpServletRequest request, SysDictionary entity){
		String msg = "添加成功";
		try {
			if(null != entity.getId()){
				msg ="编辑成功";
			}
			
			if(ReflectHelper.isContainAttr(entity, "createDate")){
				ReflectHelper.setValueByFieldName(entity, "createDate", new Date());
			}
			if(ReflectHelper.isContainAttr(entity, "createBy")){
				ReflectHelper.setValueByFieldName(entity, "createBy", SessionUtils.getUser(request).getAccount());
			}
			service.saveDictionary(entity);
			return JsonWrapper.successWrapper(entity,msg);
		} catch (Exception e) {
			String error = null;
			if(entity.getId()!=null){
				this.log.error("编辑失败",e);
				error ="编辑失败";
			}else{
				this.log.error("添加失败",e);
				error ="添加失败";
			}
			return JsonWrapper.failureWrapperMsg(entity,error);
		}
	}
	
	/**
	 * @TODO: 前台js请求数据字典
	 * @author: zhaichunlei
	 & @DATE : 2016年3月23日
	 * @param request
	 * @param code
	 * @return
	 */
	@RequestMapping({ "getDictionaryNameByCode" })
	@ResponseBody
	public HashMap<String, Object> getDictionaryNameByCode(HttpServletRequest request,String code){
		String name = Constant.DICS.get(code);
		return JsonWrapper.successWrapper(name);
	}
	
	/**
	 * 
	 * @Description: 去树页面   
	 * @return
	 */
	@RequestMapping("toTreeList")
	public String toTreeList(HttpServletRequest request,String id){
		return "sysManage/sysDictionaryTree";
	}
	
	/**
	 * 
	 * @Description: 获得所有的树形结构参数   
	 * @return
	 */
	@RequestMapping("getTree")
	@ResponseBody
	public List<TreeVoR> getTree(){
		List<TreeVoR> list=   service.getTreeList();
		return list;
	}
	
	@RequestMapping("addSysDictionary")
	@ResponseBody
	public Map<String ,String>addSysTreeDic(HttpServletRequest request,String pid,String name,String isParent){
		Map<String, String>map = new HashMap<>();
		SysDictionary entity = new SysDictionary();
		try {
			
			map.put("msg", "添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "添加失败");
		}
		return map;
		
	}

}
