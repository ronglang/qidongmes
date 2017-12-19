package com.css.business.web.subsyscraft.craManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsyscraft.craManage.service.CraProSeqRelationManageService;
import com.css.business.web.subsyscraft.craManage.service.CraSeqManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/craProSeqRelationManageAction")
public class CraProSeqRelationManageAction extends BaseSpringSupportAction<CraProSeqRelation, CraProSeqRelationManageService> {

	@Resource(name = "craProSeqRelationManageService")
	private CraProSeqRelationManageService service;
	
	@Autowired
	private CraSeqManageService craSeqManageService;
	
	@Override
	public CraProSeqRelationManageService getEntityManager() {
		return service;
	}
	
	public CraProSeqRelationManageService getService() {
		return service;
	}
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craProSeqRelationEdit";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craProSeqRelationList";
	}
	
	
	@RequestMapping({ "toListPageCore" })
	public String toListPageCore(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		String proCraftCode = request.getParameter("proCraftCode");
		request.setAttribute("proCraftCode",proCraftCode);
		return "craManage/craProSeqRelationCoreList";
	}
	
	
	@RequestMapping({"dataGrid"})
	@ResponseBody
	public Page dataGrid (HttpServletRequest request,Page page,CraProSeqRelation bean){
		try {
			Integer id = Integer.parseInt(bean.getProCraftCode()); //实际是id
			CraProSeqRelation c = service.get(id);
			String code = c.getProCraftCode();
			bean.setProCraftCode(code); //设置为真实code
			
			page = super.dataGridPage(request, page, bean);
			List<CraProSeqRelation> list = page.getData();
			for(CraProSeqRelation bb : list){
				String seq_code = bb.getSeqCode();
				String queryString = " from CraSeq where seqCode = ? ";
				
				List<CraSeq> ll = craSeqManageService.getEntityDaoInf().getHibernateTemplate().find(queryString,seq_code);
				if(ll==null||ll.isEmpty()||ll.size()>1){
					throw new RuntimeException("工序编码"+seq_code+"在表cra_seq中数据异常");
				}
				bb.setSeqName(ll.get(0).getSeqName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@RequestMapping({"saveList"})
	@ResponseBody
	public HashMap<String,Object> saveList(HttpServletRequest request){
		String jsonDataList = request.getParameter("dataList");
		//转成对象之后，保存相应的数据
		List<CraProSeqRelation> list2=(List<CraProSeqRelation>)JSONArray.toList(JSONArray.fromObject(jsonDataList), CraProSeqRelation.class);
		//需要干几件事，1：非绞线类工序，芯线数设为1，
		int laSiCount = 1;//记录总共需要生产多少拉丝半成品
		for(CraProSeqRelation bean : list2){
			String seq_name = bean.getSeqName();
			if(seq_name.indexOf("绞线")==-1){//不是绞线类
				bean.setCore(1);
			}else{//绞线类
				//保持客户输入
			}
			laSiCount *= bean.getCore();
		}
		//先将集合按照工艺路线排序
		List<CraProSeqRelation> list = new ArrayList<CraProSeqRelation>();
		int tempSort = 0;
		for (int i = 0; i < list2.size(); i++) {
			CraProSeqRelation bean = list2.get(i);
			if(bean.getSeqSort()==tempSort+1){
				list.add(bean);
				tempSort++;
				list2.remove(i);
				i = -1;
			}
		}
		
		//计算工序后半成品数量
		int finishCount = 0;
		for(CraProSeqRelation bean : list){
			String seqName = bean.getSeqName();
			Integer seqSort = bean.getSeqSort();
			Integer core = bean.getCore();
			if(seqSort==1){
				bean.setSeqHalfProNum(laSiCount);
				finishCount = laSiCount;
			}else{
				if(seqName.indexOf("绞线")!=-1){//绞线工序
					bean.setSeqHalfProNum(finishCount/core);
					finishCount = finishCount/core;
				}else{//非绞线工序
					bean.setSeqHalfProNum(finishCount);
				}
			}
		}
		service.getEntityDaoInf().saveOrUpdateBatch(list);
		
		
		return JsonWrapper.successWrapper("保存成功");
	}
}
