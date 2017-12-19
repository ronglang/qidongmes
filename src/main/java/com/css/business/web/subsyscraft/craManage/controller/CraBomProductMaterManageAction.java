package com.css.business.web.subsyscraft.craManage.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraBomProductMater;
import com.css.business.web.subsyscraft.bean.CraBomProductMaterVo;
import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.craManage.service.CraBomProductMaterManageService;
import com.css.business.web.subsyscraft.craManage.service.CraCraftProductManageService;
import com.css.business.web.subsyscraft.craManage.service.CraProSeqRelationManageService;
import com.css.business.web.subsysplan.plaManage.utils.JsonUtil;
import com.css.business.web.subsysstore.storeManage.service.StoreMrecordManageService;
import com.css.business.web.sysManage.bean.SysDictionary;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.service.SysDictionaryManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;

@Controller
@RequestMapping("/craBomProductMaterManageAction")
public class CraBomProductMaterManageAction
		extends
		BaseSpringSupportAction<CraBomProductMater, CraBomProductMaterManageService> {

	// @Autowired
	@Resource(name = "craBomProductMaterManageService")
	private CraBomProductMaterManageService service;

	@Resource(name = "storeMrecordManageService")
	private StoreMrecordManageService smservice;

	@Resource(name = "craCraftProductManageService")
	private CraCraftProductManageService cpservice;

	@Resource(name = "craProSeqRelationManageService")
	private CraProSeqRelationManageService srservice;

	@Resource(name = "sysDictionaryManageService")
	private SysDictionaryManageService sysDictionaryManageService;

	@Resource(name = "craProSeqRelationManageService")
	private CraProSeqRelationManageService craProSeqRelationManageService;

	@Override
	public CraBomProductMaterManageService getEntityManager() {
		return service;
	}

	public CraBomProductMaterManageService getService() {
		return service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCmaterEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id,
			String data) {
		// String aa = data;
		if (data != null && !"".equals(data)) {
			request.setAttribute("data", data);
		} else {
			request.setAttribute("data", "false");
		}
		request.setAttribute("id", id);
		return "craManage/craCmaterForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCmaterList";
	}

	@RequestMapping({ "toCraCmaterTable" })
	public String toCraCmaterTable(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "craManage/craCmaterTable";
	}

	@RequestMapping({ "getCraBomProductMater" })
	@ResponseBody
	public Page getCraBomProductMater(Page p,String proGgxh) {
		Page page;
		try {
			page = service.getgetCraBomProductMaterGrid(p,proGgxh);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * 保存BOM材料明细
	 * 
	 * @param createDate
	 * @param mcpu
	 * @param unit
	 * @param materGgxh
	 * @param proCraftCode
	 * @param pcscRelaCode
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "saveCraBomProductMater" })
	@ResponseBody
	public HashMap<String, Object> saveCraBomProductMater(HttpServletRequest req, String li) {
		try {
			SysUser user = SessionUtils.getUser(req);
			Gson gson = new Gson();
			String lis = URLDecoder.decode(li, "UTF-8");
			CraBomProductMaterVo vo = gson.fromJson(lis, CraBomProductMaterVo.class);
			service.saveCraBomProductMater(vo,user);
			return JsonWrapper.successWrapper(vo, "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "数据错误!");
		}
	}

	@RequestMapping({ "updateCraBomProductMater" })
	@ResponseBody
	public HashMap<String, Object> updateCraBomProductMater(HttpServletRequest req, String li) {
		// TODO 修改方法实现
		try {
			SysUser user = SessionUtils.getUser(req);
			Gson gson = new Gson();
			String lis = URLDecoder.decode(li, "UTF-8");
			CraBomProductMaterVo vo = gson.fromJson(lis, CraBomProductMaterVo.class);
			service.updateCraBomProductMater(vo, user);
			return JsonWrapper.successWrapper(vo, "");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "数据出错了!");
		}
	}

	@RequestMapping({ "delCraBomProductMater" })
	@ResponseBody
	public String delCraBomProductMater(String data) {
		JSONArray ja = JSONArray.fromObject(data);
		List<Object> list = JsonUtil.jsonToList(ja);
		for (Object obj : list) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) obj;
			Integer id = Integer.parseInt(map.get("id").toString());
			service.delCraBomProductMater(id);
		}
		return null;
	}

	/**
	 * 封装MatgerGgxh的下拉框数据
	 * 
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getMaterGgxh" })
	@ResponseBody
	public Map[] getMaterGgxh() {
		Map[] materGgxh = service.getMaterGgxh();
		return materGgxh;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping({"getSeqName"})
	@ResponseBody
	public Map[] getSeqName(){
		Map[] seqName = service.getSeqName();
		return seqName;
	}


	@RequestMapping({ "toListPage1" })
	public String toListPage1(HttpServletRequest request, Integer relationId) {
		request.setAttribute("relationId", relationId);
		return "craManage/craBomProductMater";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping({ "craBomProductMaterList" })
	@ResponseBody
	public Page craBomProductMaterList(Page page, HttpServletRequest request,
			Integer relationId) {
		String init = request.getParameter("init");// 表示点击初始化按钮
		// relationId = 654;//表示生成哪一个relation的材料信息
		// 查询relation对象
		CraProSeqRelation relation = craProSeqRelationManageService
				.get(relationId);
		String mainCode = relation.getPcscRelaCode();// 主表code
		String seq_code = relation.getSeqCode();
		if (!(init == null || init.isEmpty()) && "add".equals(init)) {// 表示需要初始化参数
			// 查询sys_dictionary表，生成材料BOM表的数据。
			String hql = " from SysDictionary where pcode = ? and type like  ? ";
			List<SysDictionary> list = sysDictionaryManageService
					.getEntityDaoInf().getHibernateTemplate()
					.find(hql, new Object[] { seq_code, "%原材料参数%" });
			// 循环list生成
			List<CraBomProductMater> beanList = new ArrayList<CraBomProductMater>();
			// 生成材料数据之前，需要判断对应的记录是否已经存在
			hql = " from CraBomProductMater where  pcscRelaCode = ? ";
			List<CraBomProductMater> craBomProductMaterList = service
					.getEntityDaoInf().getHibernateTemplate()
					.find(hql, mainCode);// TODO 查询数据库看记录是否存在
			for (int i = 0; i < list.size(); i++) {
				SysDictionary sys = list.get(i);
				String code = sys.getCode();// 原材料id
				String pcode = sys.getPcode();// 工序编号
				for (CraBomProductMater bean : craBomProductMaterList) {
					String seqCode = bean.getSeqCode();
					String materId = bean.getMaterId() == null ? null : bean
							.getMaterId().toString();
					if (pcode.equals(seqCode) && code.equals(materId)) {
						list.remove(i);
						i = -1;
						break;
					}
				}
			}
			for (SysDictionary bb : list) {
				CraBomProductMater bean = new CraBomProductMater();

				bean.setBmCode(null);// 材料参数BOM编码 TODO 系统自动生成
				bean.setcCode(null);// 工艺编码，弃用字段
				bean.setCreateBy("创建人");//
				bean.setCreateDate(new Date());//
				bean.setMaterId(Integer.parseInt(bb.getCode()));// 物料id
				bean.setMcpu(null);// 产品单位长度，消耗原材料值,客户手动输入
				bean.setProCraftCode(relation.getProCraftCode());// 产品工艺编码
				bean.setSeqCode(relation.getSeqCode());// 工序编码
				bean.setUnit(null);// 客户手动输入，或者自动明确定义，有待商榷，暂定客户自己手动输入
				beanList.add(bean);
			}
			service.getEntityDaoInf().saveOrUpdateBatch(beanList);
			;
		}
		// 查询relation对应的材料记录
		service.getPage(page, request, mainCode);
		return page;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping({ "saveList" })
	@ResponseBody
	public HashMap<String, Object> saveList(HttpServletRequest request) {
		try {
			String jsonDataList = request.getParameter("data");
			// 转成对象之后，保存相应的数据
			List<CraBomProductMater> list2 = (List<CraBomProductMater>) JSONArray
					.toList(JSONArray.fromObject(jsonDataList),
							CraBomProductMater.class);
			service.getEntityDaoInf().saveOrUpdateBatch(list2);
			return JsonWrapper.successWrapper("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapper("保存失败");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getProGgxhFromCraBomProductMater"})
	@ResponseBody
	public Map[] getProGgxhFromCraBomProductMater(){
		Map[] proggxhs = service.getProggxh();
		return proggxhs;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getProColor"})
	@ResponseBody
	public Map[] getProColor(String proGgxh){
		Map[] data = service.getProColor(proGgxh);
		return data;
	}
	
	
}
