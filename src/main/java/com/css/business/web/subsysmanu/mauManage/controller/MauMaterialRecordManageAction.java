package com.css.business.web.subsysmanu.mauManage.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauMaterialDetail;
import com.css.business.web.subsysmanu.bean.MauMaterialRecord;
import com.css.business.web.subsysmanu.mauManage.bean.MaterialRequsitionFromVo;
import com.css.business.web.subsysmanu.mauManage.service.MauMaterialRecordManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/mauMaterialRecordManageAction")
public class MauMaterialRecordManageAction extends BaseSpringSupportAction<MauMaterialRecord, MauMaterialRecordManageService> {
	
	@Autowired
	private MauMaterialRecordManageService service;
	
	@Override
	public MauMaterialRecordManageService getEntityManager() {
		return service;
	}

	public MauMaterialRecordManageService getService() {
		return service;
	}

	@Resource(name="mauMaterialRecordManageService")
	public void setService(MauMaterialRecordManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMaterialRecordEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMaterialRecordForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauMaterialRecordList";
	}
	@RequestMapping(value={"/loadPickingMauMaterialRecordManage"})
	@ResponseBody
	public HashMap<String,Object> loadPickingMauMaterialRecordManage(String page,String pagesize){
		return service.getMauMaterialRecordsService(page,pagesize);
	}
	
	@RequestMapping(value={"/deleteickingMauMaterialRecordManageByIds"})
	@ResponseBody
	public String deleteickingMauMaterialRecordManageByIds(int[] ids){
		int [] a=ids;
		service.deleteMauMaterialRecordsService(a);
		return "删除成功";
	}
	
	@RequestMapping(value={"/updateMauMaterialRecordManage"})
	@ResponseBody
	public String updateMauMaterialRecordManage(MauMaterialRecord row){
		row.setDocType("领料单");
		service.updateMauMaterialRecordService(row);
		return "更新成功";
	}
	
	@RequestMapping(value={"/addMauMaterialRecordManage"})
	@ResponseBody
	public String addMauMaterialRecordManage(MauMaterialRecord row){
		row.setDocType("领料单");
		row.setRemark("新增");
		service.addMauMaterialRecordService(row);
		return "新增成功";
	}
	@RequestMapping(value={"/loadPickingMauMaterialDetailManage"})
	@ResponseBody
	public HashMap<String,Object> loadPickingMauMaterialDetailManage(String page,String pagesize,String mmrCode){
		return service.getMauMaterialDetailService(page,pagesize,mmrCode);
	}
	@RequestMapping(value = "/addMauMaterialDetail", method = RequestMethod.POST)  
	@ResponseBody
	public String addMauMaterialDetail(@RequestBody MauMaterialDetail[] data ){
		List<MauMaterialDetail> MauMaterialDetail = new ArrayList<MauMaterialDetail>() ;
		for (int i = 0; i < data.length; i++) {
			data[i].setDocType("领料单");
			MauMaterialDetail.add(data[i]);
		}
		service.addMauMaterialDetailService(MauMaterialDetail);
		return "新增成功111";
	}
	@RequestMapping(value = "/updateMauMaterialDetail", method = RequestMethod.POST)  
	@ResponseBody
	public String updateMauMaterialDetail(@RequestBody MauMaterialDetail[] data ){
		List<MauMaterialDetail> MauMaterialDetail = new ArrayList<MauMaterialDetail>() ;
		for (int i = 0; i < data.length; i++) {
			data[i].setDocType("领料单");
			MauMaterialDetail.add(data[i]);
		}
		service.updateMauMaterialDetailService(MauMaterialDetail);
		return "跟新成功";
	}
	
	@RequestMapping(value="/quringMmdBymmrCode")
	@ResponseBody
	public List<MaterialRequsitionFromVo> quringMmdBymmrCode(HttpServletRequest request, String mmrCode){
		List<MaterialRequsitionFromVo> list = service.quringMmdBymmrCodeServices(mmrCode);
		return list;
	}
	
	@RequestMapping(value={"/startPickgingMaterial"})
	@ResponseBody
	public String startPickgingMaterial(int[] ids){
		service.startPickgingMaterialService(ids);
		return "领料中...";
	}
	
	@RequestMapping(value={"/endPickgingMaterial"})
	@ResponseBody
	public String endPickgingMaterial(int[] ids){
		service.endPickgingMaterialService(ids);
		return "领料完成确认";
	}
	@RequestMapping(value={"/getComSerchMauMaterialRecord"})
	@ResponseBody
	public HashMap<String,Object> getComSerchMauMaterialRecord(String page,String pagesize,String pickingState,String pickingwsCode,String pickingmmrCode,String pickingmacCode){
		return service.getComSerchMauMaterialRecordService(page,pagesize,pickingState,pickingwsCode,pickingmmrCode,pickingmacCode);
	}
	@RequestMapping(value="/export")
	public void export(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=export.xls");
		//创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		service.exportExcel(wb);
		//获取文件输入流，写入excel并关闭
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 从此处以下，重写领料单功能
	 */
	@RequestMapping({"toListGridPage"})
	public String toListGridPage(HttpServletRequest request){
		
		return "mauManage/rec_material/mauMaterialRecord";
	}
	@RequestMapping({"getMainPageData"})
	@ResponseBody
	public Page getMainPageData(HttpServletRequest request,Page page,MauMaterialRecord bean){
		
		try {
			page = super.dataGridPage(request, page, bean);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	/**
	 * 开始领料
	 * @param request
	 * @return
	 */
	@RequestMapping({"start"})
	@ResponseBody
	public HashMap<String,Object> start(HttpServletRequest request,Integer id){
		try{
			MauMaterialRecord bean = service.get(id);
			if(bean!=null&&bean.getTimeStart()==null){
				bean.setTimeStart(new Timestamp(System.currentTimeMillis()));
				service.save(bean);//新增或修改，后台是saveOrupdate
			}
			return JsonWrapper.successWrapper("开始领料");
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("领料失败");
		}
	}
	/**
	 * 结束领料
	 * @param request
	 * @return
	 */
	@RequestMapping({"end"})
	@ResponseBody
	public HashMap<String,Object> end(HttpServletRequest request){
		try{
			List<Integer> list = new ArrayList<Integer>();
			String ids = request.getParameter("data");
			String arr[] = ids.split(",");
			for(String s : arr){
				list.add(Integer.parseInt(s));
			}
			
			String hql = " from MauMaterialRecord where id in (:ids) ";
			@SuppressWarnings("unchecked")
			List<MauMaterialRecord> beanList = service.getEntityDaoInf().getHibernateTemplate().findByNamedParam(hql, "ids", list);
			for(MauMaterialRecord bean : beanList){
				bean.setTimeEnd(new Timestamp(System.currentTimeMillis()));
			}
			service.getEntityDaoInf().saveOrUpdateBatch(beanList);
			return JsonWrapper.successWrapper("开始领料");
		}catch(Exception e){
			e.printStackTrace();
			return JsonWrapper.failureWrapper("领料失败");
		}
	}
	
	/**
	 * 
	 * @Description:电子看板，仓库的领料关系，当天计划 和未领料的计划
	 * @param request
	 * @return
	 */
	@RequestMapping("getTodayList")
	@ResponseBody
	public HashMap<String, Object>getTodayList(HttpServletRequest request){
		try {
			List<MauMaterialRecord>list = service.getTodayList();
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("查询失败");
		}
		
	}
}
