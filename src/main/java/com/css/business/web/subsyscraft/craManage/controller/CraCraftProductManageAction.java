package com.css.business.web.subsyscraft.craManage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.business.web.subsyscraft.bean.CraRoute;
import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.bean.SysParameter;
import com.css.business.web.subsyscraft.craManage.service.CraCraftProductManageService;
import com.css.business.web.subsyscraft.craManage.service.CraRouteManageService;
import com.css.business.web.subsyscraft.craManage.service.CraRouteSeqManageService;
import com.css.business.web.subsyscraft.craManage.service.CraSeqManageService;
import com.css.business.web.subsyscraft.craManage.service.SysParameterManageService;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.mauManage.service.MauProductManageService;
import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.business.web.sysManage.service.SysCommdicManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/craCraftProductManageAction")
public class CraCraftProductManageAction extends
		BaseSpringSupportAction<CraCraftProduct, CraCraftProductManageService> {

	@Autowired
	private CraCraftProductManageService service;

	@Autowired
	private MauProductManageService mauProductManageService;// 产品表

	@Autowired
	private CraRouteManageService craRouteManageService;

	@Autowired
	private SysCommdicManageService sysCommdicManageService;

	@Autowired
	private SysParameterManageService sysParameterManageService;

	@Autowired
	private CraRouteSeqManageService craRouteSeqManageService;

	@Autowired
	private CraSeqManageService craSeqManageService;

	@Override
	public CraCraftProductManageService getEntityManager() {
		return service;
	}

	public CraCraftProductManageService getService() {
		return service;
	}

	@Resource(name = "craCraftProductManageService")
	public void setService(CraCraftProductManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCraftProductEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCraftProductForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCraftProductList";
	}

	

	@RequestMapping({ "toSetGatherPage" })
	public String toSetGatherPage(HttpServletRequest request) {
		request.setAttribute("p_code",request.getParameter("p_code"));
		return "craManage/craGatherSetForm";
	}
	
	@RequestMapping({ "toListPageData" })
	@ResponseBody
	public Page toListPageData(HttpServletRequest request, Page page,
			CraCraftProduct bean) {
		try {

			page = super.dataGridPage(request, page, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@RequestMapping({ "getById" })
	@ResponseBody
	public HashMap<String, Object> getById(HttpServletRequest request,
			Integer id) {
		try {
			CraCraftProduct bean = service.get(id);
			return JsonWrapper.successWrapper(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("数据异常");
		}

	}

	@SuppressWarnings({})
	@RequestMapping({ "saveOrUpdate" })
	@ResponseBody
	public HashMap<String, Object> saveOrUpdate(HttpServletRequest request,
			CraCraftProduct bean) {
		try {
			String jsonDataMap = bean.getMaterielGgxhs();
			Gson gson = new Gson();
		   List<Map<String,String>> list =	 gson.fromJson(jsonDataMap, new TypeToken<List<Map<String,String>>>(){}.getType());
		
	
//			Map<String,String> map = null;
//			if(jsonDataMap!=null&&!jsonDataMap.isEmpty()){
//				JSONObject jsonObject = JSONObject.fromObject(jsonDataMap);
//				map = new HashMap<String,String>();
//				for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
//					String key = (String) iter.next();
//					map.put(key, jsonObject.get(key).toString());
//				}
//			}
			
			String proGgxh = bean.getProGgxh();
			Integer proId = bean.getProId();
			if(proGgxh != null && proId == null ){ //要把pro_id写入
				MauProduct mp = mauProductManageService.getByProductGgxh(proGgxh);
				proId = mp.getId();
				bean.setProId(proId);
			}

			if (bean.getId() == null) {
				bean.setCreateBy("创建人");
				bean.setCreateDate(new Date());
				String code = service.getEntityDaoInf().exeCode(
						"fun_pro_craft_code");
				bean.setProCraftCode(code);// 产品工艺编码，调用生成方法
				
				
			}
			// service.save(bean);
			// TODO 这里需要为产品工艺新增方法单独写一个service，因为涉及到其他的关联表的数据的保存。
			//将bean的产品规格型号封装一下产品id，
			/*String proGgxh = bean.getProGgxh();
			String hql = " from MauProduct where pro_ggxh = ? ";
			List<MauProduct> mauProductList = mauProductManageService.getEntityDaoInf().getHibernateTemplate().find(hql,proGgxh);
			if(mauProductList==null||mauProductList.isEmpty()){
				throw new RuntimeException("规格型号"+proGgxh+"不存在");
			}
			if(mauProductList.size()>1){
				throw new RuntimeException("规格型号"+proGgxh+"存在多条记录，请优化");
			}
			MauProduct proBean = mauProductList.get(0);
			bean.setProId(proBean.getId());*/
			service.saveBean(bean,list);
			// 此处重新构造产品工艺新建代码

			return JsonWrapper.successWrapper("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.successWrapper("保存失败");
		}
	}

	/**
	 * 获取下拉框数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "getSelectData" })
	@ResponseBody
	public HashMap<String, Object> getSelectData(HttpServletRequest request) {
		// List<Object> list = new ArrayList<Object>();

		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		map.put("proId", new ArrayList<Map<String, Object>>());
		map.put("routeCode", new ArrayList<Map<String, Object>>());
		map.put("proGgxh", new ArrayList<Map<String, Object>>());
		map.put("proColor", new ArrayList<Map<String, Object>>());
		// TODO 封装产品工艺选择器
		getProductSelect(map);
		// TODO 封装工艺路线选择器
		getCraRouteSelect(map);
		// TODO 封装规格型号，现在是产品与规格之间的关系，是一对一？一对多？多对多？
		getProGgxhSelect(map, request);
		// TODO 封装产品颜色选择器
		getColorSelect(map);

		return JsonWrapper.successWrapper(map);
	}

	@SuppressWarnings("unchecked")
	private void getProGgxhSelect(Map<String, List<Map<String, Object>>> map,
			HttpServletRequest request) {
		String proId = request.getParameter("proId");// 产品id
		if (proId != null && proId.length() != 0) {// 表示查询选择产品之后的
			String queryString = " from SysParameter where paraType = ? and paraName = ? ";
			List<SysParameter> ggxhList = sysParameterManageService
					.getEntityDaoInf().getHibernateTemplate()
					.find(queryString, "产品规格型号", proId);
			for (SysParameter bean : ggxhList) {
				Map<String, Object> mm = new HashMap<String, Object>();
				mm.put("id", bean.getParaValue());
				mm.put("text", bean.getParaValue());
				map.get("proGgxh").add(mm);
			}

		} else {
			String queryString = " from SysParameter where paraType = ? ";
			List<SysParameter> ggxhList = sysParameterManageService
					.getEntityDaoInf().getHibernateTemplate()
					.find(queryString, "产品规格型号");

			for (SysParameter bean : ggxhList) {
				Map<String, Object> mm = new HashMap<String, Object>();
				mm.put("id", bean.getParaValue());
				mm.put("text", bean.getParaValue());
				map.get("proGgxh").add(mm);
			}
		}

	}

	private void getColorSelect(Map<String, List<Map<String, Object>>> map) {
		List<SysCommdic> list = sysCommdicManageService.findByClas("产品颜色");
		for (SysCommdic bean : list) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("id", bean.getValue());
			mm.put("text", bean.getValue());
			map.get("proColor").add(mm);
		}
	}

	private void getCraRouteSelect(Map<String, List<Map<String, Object>>> map) {
		List<CraRoute> craRouteList = null;
		try {
			craRouteList = craRouteManageService.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (CraRoute bean : craRouteList) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("id", bean.getProGgxh());// 工艺编码
			mm.put("text", bean.getProGgxh());// 这里都可以存放工艺编码
			map.get("routeCode").add(mm);
		}
	}

	private void getProductSelect(Map<String, List<Map<String, Object>>> map) {
		// TODO 查询所有的产品信息集合，封装到集合里边, 产品选择框
		List<MauProduct> mauProductList = null;
		try {
			mauProductList = mauProductManageService.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (MauProduct bean : mauProductList) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("id", bean.getId());
			mm.put("text", bean.getPro_name());
			map.get("proId").add(mm);
		}
	}

	/**
	 * 通过jdbc的方式来进行批量删除，这样，效率会高很多
	 * 
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping({ "del" })
	@ResponseBody
	public HashMap<String, Object> delBean(HttpServletRequest request,
			Integer ids[]) {
		try {
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < ids.length; i++) {
				list.add(ids[i]);
			}
			service.delList(list);
			return JsonWrapper.successWrapper("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper("删除失败");
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getProGgxh" })
	@ResponseBody
	public Map[] getProGgxh() {
		Map[] str = service.getProGgxh();
		return str;
	}

	/**
	 * 产品大类选择框数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "getSelectData1" })
	@ResponseBody
	public HashMap<String, Object> getSelectData1() {
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		map.put("proBig", new ArrayList<Map<String, Object>>());
		map.put("proColor", new ArrayList<Map<String, Object>>());

		// TODO 封装产品颜色选择器
		getColorSelect(map);
		// TODO 封装产品大类选择器
		for (int i = 0; i < 3; i++) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("id", i + "");
			mm.put("text", "产品大类" + i);
			map.get("proBig").add(mm);
		}
		map.get("proBig").clear();
		String hql = " from MauProduct where p_code = ? ";
		List<MauProduct> list = mauProductManageService.getEntityDaoInf()
				.getHibernateTemplate().find(hql, "0");
		for (MauProduct bean : list) {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("id", bean.getId());
			mm.put("text", bean.getPro_name());
			map.get("proBig").add(mm);
		}

		return JsonWrapper.successWrapper(map);
	}

	/**
	 * 具体产品规格型号
	 * 
	 * @param request
	 * @param proType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "getProGgxhFromProType" })
	@ResponseBody
	public HashMap<String, Object> getProGgxhFromProType(
			HttpServletRequest request, String proType) {

		// TODO 模拟数据
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		/*List<MauProduct> list = new ArrayList<MauProduct>();
		MauProduct mauProduct = null;
		mauProduct = mauProductManageService.get(4);
		list.add(mauProduct);
		mauProductManageService.get(5);
		list.add(mauProduct);

		for (MauProduct bean : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", bean.getPro_ggxh());// id就是产品的规格型号
			map.put("text", bean.getPro_name());// 显示值是产品名称
			resultList.add(map);
		}*/
		// 根据产品类型获取产品规格。
		resultList.clear();
		//proType实际是大类的id，需要取它的proCode
		MauProduct mp = mauProductManageService.getEntityDaoInf().get(Integer.parseInt(proType));
		String pc = mp.getPro_code();
		String hql = " from MauProduct where p_code = ? ";
		List<MauProduct> list1 = mauProductManageService.getEntityDaoInf()
				//.getHibernateTemplate().find(hql, proType);
				.getHibernateTemplate().find(hql, pc);  
		for (MauProduct bean : list1) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", bean.getPro_ggxh());// id就是产品的规格型号
			map.put("text", bean.getPro_ggxh());// 显示值是产品名称
			resultList.add(map);
		}
		return JsonWrapper.successWrapper(resultList);
	}

	
	/**
	 * 具体产品名称
	 * 
	 * @param request
	 * @param proType
	 * @return
	 */
	@RequestMapping(value="/getProNameFromProType",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public HashMap<String, Object> getProNameFromProType(HttpServletRequest request, String proType) {
	    String pro_Type=request.getParameter("proType");
	    return service.getProNameFromProType(pro_Type);
	
	}
	

	/**
	 * 产品颜色
	 * 
	 * @param request
	 * @param proName
	 * @return
	 */
	@RequestMapping(value="/getColorFromProName",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public HashMap<String, Object> getColorFromProName(HttpServletRequest request, String proName) {
	    String pro_name=request.getParameter("proName");
	    return service.getColorFromProName(pro_name);
	}
	
	/**
	 * 参与集绞的线缆的规格型号
	 * 
	 * @param request
	 * @param proName
	 * @return
	 */
	@RequestMapping(value="/getGatherGGXH",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public HashMap<String, Object> getgetGatherGGXH(HttpServletRequest request) {
	    String p_code=request.getParameter("p_code");
	    return service.getGatherGGXH(p_code);
	}
	
	
	/**
	 * 参与集绞的线缆的规格型号的颜色
	 * 
	 * @param request
	 * @param proName
	 * @return
	 */
	@RequestMapping(value="/getGatherColor",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public HashMap<String, Object> getGatherColor(HttpServletRequest request) {
	    String pro_code=request.getParameter("pro_code");
	    return service.getGatherColor(pro_code);
	}
	
	/**
	 * 获取所有的工艺路线列表
	 * 
	 * @param request
	 * @param response
	 * @param proGgxh
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "getProCraftRoute" })
	@ResponseBody
	public HashMap<String, Object> getProCraftRoute(HttpServletRequest request,
			HttpServletResponse response, String proGgxh) {
		String hql = " from CraRoute ";
		List<CraRoute> list = craRouteManageService.getEntityDaoInf()
				.getHibernateTemplate().find(hql);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (CraRoute bean : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", bean.getProGgxh());// 工艺路线编码
			map.put("text", bean.getProGgxh());// 工艺路线名称
			resultList.add(map);
		}
		return JsonWrapper.successWrapper(resultList);
	}

	/**
	 * 根据工艺路线编码判断工序里是否包含集绞工序
	 * 
	 * @param routeCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "getSetGroudByYesOrNo" })
	@ResponseBody
	public HashMap<String, Object> getSetGroudByYesOrNo(String routeCode) {
		String hql = " from CraRouteSeq where routeCode = ? ";
		List<CraRouteSeq> lsit = craRouteSeqManageService.getEntityDaoInf()
				.getHibernateTemplate().find(hql, routeCode);
		List<String> seqCodeList = new ArrayList<String>();
		for (CraRouteSeq bean : lsit) {
			String seq_code = bean.getSeqCode();
			seqCodeList.add(seq_code);
		}

		hql = " from CraSeq where seqCode in (:codes) ";
		List<CraSeq> list = craSeqManageService.getEntityDaoInf()
				.getHibernateTemplate()
				.findByNamedParam(hql, "codes", seqCodeList);
		for (CraSeq bean : list) {
			if ("集绞".equals(bean.getSeqName())) {
				return JsonWrapper.successWrapper("包含集绞");
			}
		}
		return JsonWrapper.failureWrapper("不包含集绞");
	}
}
