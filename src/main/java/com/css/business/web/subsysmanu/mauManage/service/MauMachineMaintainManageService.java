package com.css.business.web.subsysmanu.mauManage.service;


import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauForklift;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMachineMaintain;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineMaintainManageDAO;
import com.css.business.web.subsysplan.bean.PlaMacType;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.ExportExcel;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauMachineMaintainManageService")
public class MauMachineMaintainManageService extends BaseEntityManageImpl<MauMachineMaintain, MauMachineMaintainManageDAO> {
	@Resource(name="mauMachineMaintainManageDAO")
	//@Autowired
	private MauMachineMaintainManageDAO dao;
	
	
	@Override
	public MauMachineMaintainManageDAO getEntityDaoInf() {
		return dao;
	}


	


	public HashMap<String, Object> updateMachineMaintainRepairBy(
			String maintainBy, Integer id) {
		
			try {
				String hql="update MauMachineMaintain  set maintainBy=?,status=? where id=?  and status=?";
				dao.updateMachineMaintainRepairBy(hql,maintainBy,id);
				return JsonWrapper.successWrapper("成功"); 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return JsonWrapper.failureWrapperMsg(e.getMessage());
			}
	}


	

		/**
		 * 获取全部机台的编码
		 *@data:2017年8月7日
		@return
		@autor:wl
		 */
	
	public List<String> getMachineCode() {
		List<String>  list=null;
		try {
			String hql="select DISTINCT a.mac_code from mau_machine a,mau_machine_maintain b "
					+ "where   (a.mac_code is NOT null  and  b.status !='0' and b.status !='1') "
					+ "and (a.mac_code NOT in (select mac_code from mau_machine_maintain  where status !='2'))";
			list =dao.getMachineCode(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}
		
	
	/**
	 * 得到表格内容格式
	 * 
	 * @return
	 */
	/*
	private WritableCellFormat getBodyFomat() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					10, WritableFont.NO_BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(jxl.format.Border.TOP,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.LEFT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.RIGHT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.BOTTOM,
					jxl.format.BorderLineStyle.THIN);

			format.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}
	
	*/
	
	/**
		 * 页面导出excel
		 *@data:2017年8月8日
		@param response
		@param page
		@param macCode
		@param maintainBy
		@param status
		@param startMaintainDate
		@param endMaintainDate
	 * @throws Exception 
		@autor:wl
		 */
		/*
		public void exportExcel(HttpServletResponse response, Page page,
				String macCode, String maintainBy, String status,
				String startMaintainDate, String endMaintainDate) throws Exception {


			// 获取表格样式
			WritableCellFormat bodyFomat = this.getBodyFomat();
			response.reset();
			OutputStream outputStream = response.getOutputStream();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String format = sf.format(new Date());
			String titleName = format + "维护保养excel表";
			titleName = new String(titleName.getBytes(), "ISO8859-1");
			WritableWorkbook wwb = null;
			try {
				// 创建表
				wwb = Workbook.createWorkbook(outputStream);
				WritableSheet ws = wwb.createSheet(format, 0);
				String[] arrTitle = new String[] { "维护机台", "维护人", "开始维护时间", "结束维护时间",
						"状态", "备注" };
				// 写入标题
				for (int i = 0; i < arrTitle.length; i++) {
					ws.addCell(new Label(i, 0, arrTitle[i], bodyFomat));
				}

				Page pageList = this.queryPage(page, macCode, maintainBy, status, startMaintainDate, endMaintainDate);
				if (null != pageList) {
					@SuppressWarnings("unchecked")
					List<MauMachineMaintain> data = pageList.getData();
					int x = 1;
					for (int i = 0; i < data.size(); i++) {
						MauMachineMaintain vo = data.get(i);
						ws.addCell(new Label(0, x, null==vo.getMacCode()?"":vo.getMacCode(), bodyFomat));
						ws.addCell(new Label(1, x, null==vo.getMaintainBy()?"":vo.getMaintainBy(), bodyFomat));
						ws.addCell(new Label(2, x, null==vo.getStartMaintainDate()?"":vo.getStartMaintainDate()+"", bodyFomat));
						ws.addCell(new Label(3, x, null==vo.getEndMaintainDate()?"":vo.getEndMaintainDate()+"", bodyFomat));
						ws.addCell(new Label(4, x, null==vo.getStatus()?"":vo.getStatus(), bodyFomat));
						ws.addCell(new Label(5, x, null==vo.getRemark()?"":vo.getRemark(), bodyFomat));
						x++;
					}
				}

				response.setContentType("application/vnd.ms-excel;charset=GBK");
				response.addHeader("Content-Disposition", "attachment; filename=\""
						+ titleName + ".xls\"");
				wwb.write();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (null != wwb) {
					wwb.close();
				}
				if (null != outputStream) {

					outputStream.close();
				}
			}

		
			
		}
		*/
		/******************************************TG********************************************/
		
		@SuppressWarnings("unchecked")
		public List<String> getMachineInfoService(String macType){
			String hql = "";
			if("机台".equals(macType)){
				hql = "select DISTINCT macCode from MauMachine";
			}else if("叉车".equals(macType)){
				hql = "select DISTINCT fCode from MauForklift";
			}
			List<String> listStr = dao.createQuery(hql).list();
			
			return listStr;
		}
		
	
		/**   
		 * @Description: 查询保养设备
		 * @param page
		 * @return         
		 */ 
		public Page queryPage(Page page,Map<String,String> map) {
			
			StringBuilder bd=new StringBuilder("from MauMachineMaintain  where 1=1 ");
			if(map != null){
				if(null!=map.get("macname")&&!"".equals(map.get("macname"))){
					String macname = map.get("macname").trim();
					bd.append(" and macCode like '%"+macname+"%' ");
				}
				
				if(null!=map.get("state")&&!"".equals(map.get("state"))){
					bd.append(" and status ='"+map.get("state")+"' ");
				}
				
				if(null!=map.get("startTimeOne")&&!"".equals(map.get("startTimeOne"))){
					bd.append(" and startMaintainDate >= '"+map.get("startTimeOne")+"'");
					if(map.get("startTimeTwo") != null && !"".equals(map.get("startTimeTwo"))){
						bd.append(" and startMaintainDate <= '"+map.get("startTimeTwo")+"'");
					}
				}

				if(null!=map.get("endTimeOne")&&!"".equals(map.get("endTimeOne"))){
					bd.append(" and  endMaintainDate >= '"+map.get("endTimeOne")+"'");
					if(null!=map.get("endTimeTwo")&&!"".equals(map.get("endTimeTwo"))){
						bd.append(" and  endMaintainDate <= '"+map.get("endTimeTwo")+"'");
					}
				}
			}
			
			bd.append(" ORDER BY startMaintainDate,status desc");
			Page pageQuery = dao.pageQuery(bd.toString(), page.getPageNo(), page.getPagesize());

			return pageQuery;
		}
		
		
		/**
		 * 添加保养计划
		 * @return
		 */
		public Map<String,String> addMaintainDataService(HttpServletRequest req,Map<String,String> map){
			SysUser user = SessionUtils.getUser(req);
			MauMachineMaintain mmm = new MauMachineMaintain();
			Map<String,String> maps = new HashMap<>();
			if(map != null){
				mmm.setMacCode(map.get("macCode"));
				Timestamp ts = Timestamp.valueOf(map.get("startTime"));  
				mmm.setStartMaintainDate(ts);
				mmm.setMacType(map.get("macType"));
//				mmm.setMaintainBy(map.get("maintainBy"));
				mmm.setStatus("未开始");
				mmm.setCreateBy(user.getAccount());
				mmm.setCreateDate(new Date());
				
				try{
					dao.save(mmm);
					maps.put("success", "添加成功！");
				}catch(Exception e){
					e.printStackTrace();
					maps.put("error", "发生异常，添加失败！");
				}
				
			}else{
				maps.put("error", "发生异常，添加失败！");
			}
			return maps;
		}
		
		/**
		 * 更改保养计划
		 * @param map
		 * @return
		 */
		public Map<String,String> updateMachineMaintainRemark(Map<String,String> map) {
			MauMachineMaintain mmm = new MauMachineMaintain();
			Map<String,String> maps = new HashMap<>();
			if(map != null){
				mmm.setId(Integer.valueOf(map.get("id")));
				mmm.setMacCode(map.get("macCode"));
				Timestamp ts = Timestamp.valueOf(map.get("startTime"));  
				mmm.setStartMaintainDate(ts);
				//mmm.setMaintainBy(map.get("maintainBy"));
				mmm.setMacType(map.get("macType"));
				try{
					dao.updateByCon(mmm, false);
					maps.put("success", "修改成功！");
				}catch(Exception e){
					e.printStackTrace();
					maps.put("error", "发生异常，修改失败！");
				}
				
			}
			return maps;
		}

		/**
		 * 删除保养计划
		 * @param map
		 */
		public void clearMachineMaintain(Integer id) {
			MauMachineMaintain mmm = new MauMachineMaintain();
			mmm.setId(id);
			dao.remove(mmm);
		}

		/**
		 * 保养时间一到自动开始执行保养计划
		 */
//		public void startRunMachineMaintain(){
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//			String date= df.format(new Date());// new Date()为获取当前系统时间
//			
//			//获取保养计划开始时间
//			String hql = "from MauMachineMaintain where startMaintainDate='"+date+"'";
//			List<MauMachineMaintain> list = dao.createQuery(hql).list();
//			for(int i=0;i<list.size();i++){
//				String state = list.get(i).getStatus();
//			}
//			
//		}
		
		/**
		 * 保存保养完成后的数据
		 * @return
		 */
		public Map<String,String> saveOverDataService(Map<String,String> map){
			MauMachineMaintain mmm = new MauMachineMaintain();
			Map<String,String> maps = new HashMap<>();
			if(map != null){
				mmm.setId(Integer.valueOf(map.get("id")));
				mmm.setMaintainBy(map.get("maintainBy"));
				mmm.setRemark(map.get("remark"));
				mmm.setStatus("已完成");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date());
				mmm.setEndMaintainDate(Timestamp.valueOf(date));
				try{
					dao.updateByCon(mmm, false);
					maps.put("success", "保养完成，保存成功！");
				}catch(Exception e){
					maps.put("error", "提交失败！");
					e.printStackTrace();
				}
			}
			
			return maps;
		}
		
		/**
		 * 保存保养开始后的数据
		 * @return
		 */
		public Map<String,String> saveStartDataService(Map<String,String> map){
			MauMachineMaintain mmm = new MauMachineMaintain();
			Map<String,String> maps = new HashMap<>();
			if(map != null){
				mmm.setId(Integer.valueOf(map.get("id")));
				mmm.setStatus("保养中");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date());
				mmm.setFactMaintainDate(Timestamp.valueOf(date));
				try{
					dao.updateByCon(mmm, false);
					//更改设备状态
					MauMachineMaintain mmms = dao.get(Integer.valueOf(map.get("id")));
					String mac_code = mmms.getMacCode();
					String mac_type = mmms.getMacType();
					if("叉车".equals(mac_type)){
						MauForklift mf = new MauForklift();
						mf.setfCode(mac_code);
						String sql = "from MauForklift where fCode = '"+mac_code+"' ";
						MauForklift mfl = (MauForklift)dao.createQuery(sql).uniqueResult();
						mfl.setStatus("保养");
						try {
							dao.updateByCon(mfl, false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if("机台".equals(mac_type)){
						MauMachine mm = new MauMachine();
						mm.setMacCode(mac_code);
						String sql = "from MauMachine where macCode = '"+mac_code+"' ";
						MauMachine pmt = (MauMachine)dao.createQuery(sql).uniqueResult();
						pmt.setMacState("保养");
						try {
							dao.updateByCon(pmt, false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					maps.put("success", "保养开始！");
				}catch(Exception e){
					maps.put("error", "保养失败！");
					e.printStackTrace();
				}
			}
			
			return maps;
		}
		
		/**
		 * 导出
		 * @param response
		 * @param page
		 * @param param
		 * @throws Exception 
		 */
		public void exportExcel(HttpServletResponse response, Page page,Map<String,String> map) throws Exception {
			
			// 获取表格样式
			WritableCellFormat bodyFomat = ExportExcel.getBodyFormat();
			WritableCellFormat titleFomat = ExportExcel.getTitleFormat();
			response.reset();
			OutputStream outputStream = response.getOutputStream();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String format = sf.format(new Date());
			String titleName = "设备保养记录excel表".concat(format);
			titleName = new String(titleName.getBytes(), "ISO8859-1");
			WritableWorkbook wwb = null;
			try {
				// 创建表
				wwb = Workbook.createWorkbook(outputStream);
				WritableSheet ws = wwb.createSheet(format, 0);
				String[] arrTitle = new String[] { "保养设备", "设备类型", "计划开始保养时间", "实际开始保养时间",
						"结束保养时间", "保养人员", "状态", "保养备注" };
				// 写入标题
				for (int i = 0; i < arrTitle.length; i++) {
					ws.addCell(new Label(i, 0, arrTitle[i], titleFomat));
				}

				Page pageList = this.queryPage(page,map);
				if (null != pageList) {
					@SuppressWarnings("unchecked")
					List<MauMachineMaintain> data = pageList.getData();
					int x = 1;
					for (int i = 0; i < data.size(); i++) {
						MauMachineMaintain vo = data.get(i);
						ws.addCell(new Label(0, x, null==vo.getMacCode()?"":vo.getMacCode(), bodyFomat));
						ws.addCell(new Label(1, x, null==vo.getMacType()?"":vo.getMacType(), bodyFomat));
						ws.addCell(new Label(2, x, null==vo.getStartMaintainDate()?"":vo.getStartMaintainDate().toString(), bodyFomat));
						ws.addCell(new Label(3, x, null==vo.getFactMaintainDate()?"":vo.getFactMaintainDate()+"", bodyFomat));
						ws.addCell(new Label(4, x, null==vo.getEndMaintainDate()?"":vo.getEndMaintainDate()+"", bodyFomat));
						ws.addCell(new Label(5, x, null==vo.getMaintainBy()?"":vo.getMaintainBy(), bodyFomat));
						ws.addCell(new Label(6, x, null==vo.getStatus()?"":vo.getStatus(), bodyFomat));
						ws.addCell(new Label(7, x, null==vo.getRemark()?"":vo.getRemark(), bodyFomat));
						x++;
					}
				}

				response.setContentType("application/vnd.ms-excel;charset=GBK");
				response.addHeader("Content-Disposition", "attachment; filename=\""
						+ titleName + ".xls\"");
				wwb.write();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (null != wwb) {
					wwb.close();
				}
				if (null != outputStream) {

					outputStream.close();
				}
			}
			
		}

		
	/**
	 * 获取设备保养信息列表，用于现在在电子看板上面
	 * @return
	 */
	public List<MauMachineMaintain> getMaintainListService(){
		String hql = "from MauMachineMaintain where status = '未开始' or status = '保养中'";
		List<MauMachineMaintain> list = dao.listQuery(hql);
		return list;
	}
		

		
		


	
	
}
