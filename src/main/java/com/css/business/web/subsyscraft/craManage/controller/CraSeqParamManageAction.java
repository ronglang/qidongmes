package com.css.business.web.subsyscraft.craManage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.bean.CraSeqParam;
import com.css.business.web.subsyscraft.craManage.service.CraProSeqRelationManageService;
import com.css.business.web.subsyscraft.craManage.service.CraSeqManageService;
import com.css.business.web.subsyscraft.craManage.service.CraSeqMiddleManageService;
import com.css.business.web.subsyscraft.craManage.service.CraSeqParamManageService;
import com.css.business.web.sysManage.bean.SysDictionary;
import com.css.business.web.sysManage.bean.SysMacDictionary;
import com.css.business.web.sysManage.service.SysDictionaryManageService;
import com.css.business.web.sysManage.service.SysMacDictionaryManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/craSeqParamManageAction")
public class CraSeqParamManageAction extends BaseSpringSupportAction<CraSeqParam, CraSeqParamManageService> {
	
	@Resource(name="craSeqParamManageService")
	private CraSeqParamManageService service;
	@Resource(name = "craProSeqRelationManageService")
	private CraProSeqRelationManageService craProSeqRelationManageService;
	
	@Resource(name="sysDictionaryManageService")
	private SysDictionaryManageService sysDictionaryManageService;
	
	@Resource(name="craSeqManageService")
	private CraSeqManageService craSeqManageService;
	
	@Resource(name="craSeqMiddleManageService")
	private CraSeqMiddleManageService craSeqMiddleManageService;
	
	@Resource(name="sysMacDictionaryManageService")
	private SysMacDictionaryManageService sysMacDictionaryManageService;
	
	
	@Override
	public CraSeqParamManageService getEntityManager() {
		return service;
	}

	public CraSeqParamManageService getService() {
		return service;
	}

	@Resource(name="craSeqParamManageService")
	public void setService(CraSeqParamManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		String relation_id = request.getParameter("relation_id");
		System.out.println("relation_id"+relation_id);
		request.setAttribute("relation_id", relation_id);
		return "craManage/craSeqParamEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craSeqParamForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craSeqParamList";
	}
	/**
	 * 获取grid数据
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping({ "craSeqParamList" })
	@ResponseBody
	public Page craSeqParamList(HttpServletRequest request,Page page,Integer relation_id) throws Exception{
		//查询参数封装在map里边
		Map<String ,Object> map = new HashMap<String,Object>();
//		service.findByQueryPage(page,map);
		//TODO 封装可编辑的grid
		//String relation_id = request.getParameter("relation_id");
		//Integer id = Integer.parseInt(relation_id);
		if(relation_id == null ) throw new Exception("参数relation_id为空。");
		
		CraProSeqRelation bean = craProSeqRelationManageService.get(relation_id);
		
		String pcscRelaCode = bean.getPcscRelaCode();//主表code
		String craftCode = bean.getcCode();//工艺编码
		String seq_code = bean.getSeqCode();//工序编码
		String proGgxh = bean.getProGgxh();//产品规格型号
		String proCraftCode = bean.getProCraftCode();//产品工艺编码
//		map.put("craftCode", craftCode);
//		map.put("seqCode", seq_code);
		map.put("pcscRelaCode", pcscRelaCode);
		
		
		
		String init = request.getParameter("init");
		//service.findByQueryPage(page,map);
		
		//List<CraSeqParam> dataList = page.getData();
		
		
		List<CraSeq> craseqList = craSeqManageService.getEntityDaoInf().getHibernateTemplate().find(" from CraSeq where seqCode = ? ",seq_code);
		String seq_name = craseqList.get(0).getSeqName();
		
		//目前有个问题：SysMacDictionary是区分机台的，这里查询时未区分机台。会造成查出相同的参数来。
		if(init!=null&&"add".equals(init)){
			//String hql = " from SysMacDictionary where pcode = ? and type like ? ";
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT d	 FROM	SysMacDictionary d,MauMachine m ");
			sb.append("WHERE	d.macCode = m .macCode ");
			sb.append("AND NOT EXISTS ( SELECT 1 FROM CraSeqParam P where P .macCode = m .macCode ");
			sb.append("AND P .seqCode = m .seqCode AND P .paramCode = d.code and pcscRelaCode=?) ");
			sb.append(" and d.pcode = ? and d.type like ?");
			List<SysMacDictionary> list = sysMacDictionaryManageService.getEntityDaoInf().getHibernateTemplate().find(sb.toString(),new Object[]{pcscRelaCode,seq_code,"%采集参数"});
			//拿到参数的集合，可以生成具体的值了
			String craftName =seq_name+"工艺";
			List<CraSeqParam> craSeqParamList = new ArrayList<CraSeqParam>();
			for(SysMacDictionary b : list){
				CraSeqParam paramBean = new CraSeqParam();
				paramBean.setCreateBy("创建人");
				paramBean.setCreateDate(new Date());
				paramBean.setSeqCode(seq_code);
				paramBean.setParamCode(b.getCode());
				paramBean.setParamMinValue(null);//手动输入
				paramBean.setParamMaxValue(null);//手动输入
				paramBean.setUint(null);//单位
				paramBean.setGgxh(proGgxh);//产品规格型号
				paramBean.setParamValue(null);//参数设定值
				paramBean.setProCraftCode(proCraftCode);//产品工艺编码
				paramBean.setParamName(b.getValue());//参数名称
//				paramBean.setCraftCode(craftCode);//工艺编码
//				paramBean.setCraftName(craftName);//工艺名称
				paramBean.setPcscRelaCode(bean.getPcscRelaCode());//主表code
				paramBean.setMacCode(b.getMacCode());
				craSeqParamList.add(paramBean);
			}
			updateData(relation_id, bean, craftCode, craftName, craSeqParamList);
		}
		service.findByQueryPage(page,map);
		return page;
	}

	private void updateData(Integer id, CraProSeqRelation bean,
			String craftCode, String craftName,
			List<CraSeqParam> craSeqParamList) {
		service.updatedata(id, bean, craftCode, craftName, craSeqParamList);
	}
	/**
	 * 编辑或新增ajax访问
	 * @param request
	 * @param bean
	 * @return
	 */
	@RequestMapping({ "saveOrUpdateBean" })
	@ResponseBody
	public HashMap<String,Object> saveOrUpdateBean(HttpServletRequest request,CraSeqParam bean){
		try {
//			bean.setId(10000);
			service.save(bean);
			return JsonWrapper.successWrapper(null, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "保存失败");
		}
	}
	/**
	 * 根据id查询对象
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping({ "getById" })
	@ResponseBody
	public HashMap<String,Object> getById(HttpServletRequest request, Integer id){
		try{
			CraSeqParam bean = service.get(id);
			return JsonWrapper.successWrapper(bean, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(null, "查询失败");
		}
		
		
	}
	@RequestMapping({ "del" })
	@ResponseBody
	public HashMap<String,Object> delById(HttpServletRequest request,Integer[] ids){
		try{
			service.deleteBatch(ids);
			return JsonWrapper.successWrapper(null, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.successWrapper(e, "删除失败");
		}
	}
	
	
	public HashMap<String,Object> initEdit(){
		
		return null;
	}
	/**
	 * 从产品工艺树结构处，点击工序，添加工艺，此处获取初始化参数。
	 * @param request
	 * @param relation_id
	 * @return
	 */
	@RequestMapping({"loadSelectData"})
	@ResponseBody
	public HashMap<String,Object> loadSelectData(HttpServletRequest request,Integer relation_id){
//		relation_id = 350;
		//TODO 得到工序、工艺关系表的一条数据
		try{
			CraProSeqRelation bean = craProSeqRelationManageService.get(relation_id);
			
			CraSeqParam voBean = new CraSeqParam();
			voBean.setSeqCode(bean.getSeqCode());//工序编号
			voBean.setGgxh(bean.getProGgxh());//产品规格型号
			voBean.setProCraftCode(bean.getProCraftCode());//产品工艺编码
			voBean.setPcscRelaCode(bean.getPcscRelaCode());//主表code
			return JsonWrapper.successWrapper(voBean, "获取数据成功");
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e,"获取数据异常");
		}
	}
	@RequestMapping({"loadSelectParamCode"})
	@ResponseBody
	public HashMap<String,Object> loadSelectParamCode(HttpServletRequest request,Integer relation_id){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map<String,Object>> codeList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> nameList = new ArrayList<Map<String,Object>>();
		resultMap.put("paramCode", codeList);
		resultMap.put("paramName", nameList);
		
		CraProSeqRelation bean = craProSeqRelationManageService.get(relation_id);
		String seq_code = bean.getSeqCode();//工序编码
		//查询定义表里边的参数类型
		String hql = " from SysDictionary where pcode = ? ";
		List<SysDictionary> list = sysDictionaryManageService.getEntityDaoInf().getHibernateTemplate().find(hql,seq_code);
		
		for(SysDictionary sd : list){
			//参数编号集合，
			Map<String,Object> tempMap = new HashMap<String,Object>();
			String code = sd.getCode();
			tempMap.put("id", code);
			tempMap.put("text", code);
			codeList.add(tempMap);
			
			Map<String,Object> tempMap1 = new HashMap<String,Object>();
			// 参数名称集合
			String value = sd.getValue();
			tempMap1.put("id", value);
			tempMap1.put("text", value);
			nameList.add(tempMap1);
			
		}
		return JsonWrapper.successWrapper(resultMap,"获取数据成功");
	}
	
	@RequestMapping({"saveBean"})
	@ResponseBody
	public HashMap<String,Object> saveBean(HttpServletRequest request,CraSeqParam bean){
		HashMap<String,Object> boo = checkSave(bean);
		if(Boolean.valueOf(boo.get("success").toString())){//校验通过，才允许保存
			service.saveBean(bean);
		}else{
			
		}
		return boo;
	}

	private HashMap<String,Object> checkSave(CraSeqParam bean) {
		String seq_code = bean.getSeqCode();
		String paramCode = bean.getParamCode();
		String paramName = bean.getParamName();
		String proCraftCode = bean.getProCraftCode();//产品工艺编码
		String mainCode = bean.getPcscRelaCode();//主表编码
		if(seq_code==null||seq_code.length()==0){//工序编码不能为空
			return JsonWrapper.failureWrapperMsg("工序编码不能为空");
		}
		if(paramCode==null||paramCode.isEmpty()){
			return JsonWrapper.failureWrapperMsg("参数编号不能为空");
		}
		if(paramName==null||paramName.isEmpty())
			return JsonWrapper.failureWrapperMsg("参数名称不能为空");
		if(proCraftCode == null || proCraftCode.isEmpty())
			return JsonWrapper.failureWrapperMsg("产品工艺编码不能为空，此处是数据异常，请联系开发人员");
		if(mainCode == null||mainCode.isEmpty())
			return JsonWrapper.failureWrapperMsg("主表编码不能为空，此处是数据异常，请联系开发人员");
		
		//TODO 校验参数名、参数编号、工序编号，是否能对应上
		String hql = " from SysDictionary where code = ? and value = ? and pcode = ? ";
		List<SysDictionary> ll = sysDictionaryManageService.getEntityDaoInf().getHibernateTemplate().find(hql,new Object[]{paramCode,paramName,seq_code});
		if(ll==null||ll.isEmpty()){
			return JsonWrapper.failureWrapperMsg("参数编号、参数名称、工序编码三者之间对应关系错乱，请重新输入");
		}
		return JsonWrapper.successWrapper("校验通过");
		
	}
	
	@RequestMapping(value = {"saveList"},method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> saveList(HttpServletRequest request){
		String ok  =request.getParameter("data");
		
		List<CraSeqParam> list2=(List<CraSeqParam>)JSONArray.toList(JSONArray.fromObject(ok), CraSeqParam.class);
		service.getEntityDaoInf().saveOrUpdateBatch(list2);
		return JsonWrapper.successWrapper("");
	}
	
	@RequestMapping(value = {"dataGridPage2"})
	@ResponseBody
	public Page dataGridPage(HttpServletRequest request,HttpServletResponse res,Page page ,CraSeqParam entity ) throws Exception{
		Integer id = entity.getId();
		CraProSeqRelation c = craProSeqRelationManageService.get(id);
		String code = c.getPcscRelaCode();
		
		entity.setPcscRelaCode(code);
		entity.setId(null);
		return super.dataGridPage(request,page ,entity);
	}
	
}
