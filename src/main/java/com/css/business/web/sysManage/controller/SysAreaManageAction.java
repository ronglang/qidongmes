package com.css.business.web.sysManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.AreaParentVO;
import com.css.business.web.sysManage.bean.SysArea;
import com.css.business.web.sysManage.service.SysAreaManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.BzhpUtil;
import com.css.common.util.Constant;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/sysAreaManageAction")
public class SysAreaManageAction extends BaseSpringSupportAction<SysArea, SysAreaManageService> {
	
	//@Autowired
	private SysAreaManageService service;
	
	@Override
	public SysAreaManageService getEntityManager() {
		return service;
	}

	public SysAreaManageService getService() {
		return service;
	}

	@Resource(name="sysAreaManageService")
	public void setService(SysAreaManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysAreaEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysAreaForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysAreaList";
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
			list = this.getEntityManager().getTree(SessionUtils.getUser(request) ,qcolName,qcolValue);
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
	public HashMap<String, Object> nodeTreeName(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().nodeTreeName(SessionUtils.getUser(request) ,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 根据条件生成树，无权限，查询条件是like
	 * @param request
	 * @param qcolName
	 * @param qcolValue
	 * @return
	 */
	@RequestMapping({ "nodeTreeByCon" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeByCon(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().nodeTreeByCon(SessionUtils.getUser(request) ,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 根据条件加载树，无权限，查询条件是等于
	*@param request
	*@param qcolName
	*@param qcolValue
	*@return 
	*@author leitao 
	*@date 2016-9-14 上午10:03:02
	 */
	@RequestMapping({ "nodeTreeByCon2" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeByCon2(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().nodeTreeByCon2(SessionUtils.getUser(request) ,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 根据用户权限取市、县级区域树
	*@param request
	*@return 
	*@author leitao 
	*@date 2016-7-1 上午9:47:37
	 */
	@RequestMapping({ "nodeSXJTree" })
	@ResponseBody
	public HashMap<String, Object> nodeSXJTree(HttpServletRequest request)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().nodeSXJTree(SessionUtils.getUser(request));
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 根据条件生成树
	 * @param request
	 * @param qcolName
	 * @param qcolValue
	 * @return
	 */
	@RequestMapping({ "nodeTreeByConToEquals" })
	@ResponseBody
	public HashMap<String, Object> nodeTreeByConToEquals(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().nodeTreeByConToEquals(SessionUtils.getUser(request) ,qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 轮流根据每条村名搜索天地图村坐标并写入数据库
	 * @param request
	 * @return
	 * @author fudian
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "setVillageLngLat" })
	public Map setVillageLngLat(HttpServletRequest request){
		try {
			service.setVillageLngLat();
			return JsonWrapper.successWrapper(null, "导入成功");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("导入失败");
		}
		
	}
	
	@RequestMapping({ "getAreaList" })
	@ResponseBody
	public HashMap<String, Object> getAreaList(HttpServletRequest request)  {
//		List<SysArea> findList;
//		findList = service.findPoorVillage();
		return JsonWrapper.successWrapper(Constant.AREA_LIST);
//		return JsonWrapper.successWrapper(findList);
	}
	
	@RequestMapping({"ListByPidForApp"})
	@ResponseBody
	public HashMap<String, Object> ListByPidForApp(String pid){
		return JsonWrapper.successWrapper(this.service.ListByPidForApp(pid));
	}
	
	/**
	 * @TODO: 根据行政区划代码取名称
	 * @author: zhaichunlei
	 & @DATE : 2016年3月10日
	 * @param areaCode
	 * @return
	 */
	@RequestMapping({"getAreaNameByCode"})
	@ResponseBody
	public String getAreaNameByCode(String areaCode){
		String name = service.getAreaNameByCode(areaCode);
		return name;
	}
	
	/**
	 * 获取参数区域的各个父级名称
	 * @param areaCode
	 * @return
	 */
	@RequestMapping({"getParentsNames"})
	@ResponseBody
	public Map<String, Object> getParentsNames(String areaCode){
		AreaParentVO areaParent = service.getParentsNames(areaCode);
		if(areaParent!=null){
			return JsonWrapper.successWrapper(areaParent);
		}else{
			return JsonWrapper.failureWrapperMsg("区域代码错误，无法获取父级名称");
		}
	}
	
	/**
	 * @TODO: 获取行政区划根节点
	 * @author: zhaichunlei
	 & @DATE : 2016年7月14日
	 * @param request
	 * @return
	 */
	@RequestMapping({ "getRootArea" })
	@ResponseBody
	public HashMap<String, Object> getRootArea(HttpServletRequest request)  {
		String rootArea = BzhpUtil.ROOT_AREA;
		return JsonWrapper.successWrapper(rootArea);
	}
	
	/**
	 * 通过递归算法求树形结构，列表显示
	*@return 
	*@author leitao 
	 */
	@RequestMapping("/queryelecTree")
	@ResponseBody
	public Map<String, Object> queryResourceAll(String areaCode,String aname){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			java.util.List<SysArea>  menus=service.getHprojectTree(areaCode,aname);
			map.put("Rows", menus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public HashMap<String, Object> save(HttpServletRequest request,SysArea ent){
		try {
			String areaCode = ent.getAreaCode();
			String aname = service.getAreaNameByCode(areaCode);
			if(aname != null && aname.length()  > 0){
				throw new Exception("该行政区划已存在");
			}
			Boolean isPoor = ent.getIsPoor();
			if(isPoor == null ) ent.setIsPoor(false);
			String pcode = ent.getPcode();
			if(pcode == null || pcode.length() == 0 )
				throw new Exception("父行政区划不能为空");
			return super.save(request, ent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
}