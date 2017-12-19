package com.css.business.web.subsysmanu.mauManage.service;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauForklift;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.bean.MauMachineFault;
import com.css.business.web.subsysmanu.bean.MauMachineMaintain;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineFaultManageDAO;
import com.css.business.web.subsysplan.bean.PlaMacType;
import com.css.common.util.ExportExcel;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauMachineFaultManageService")
public class MauMachineFaultManageService extends BaseEntityManageImpl<MauMachineFault, MauMachineFaultManageDAO> {
	@Resource(name="mauMachineFaultManageDAO")
	//@Autowired
	private MauMachineFaultManageDAO dao;
	
	
	@Override
	public MauMachineFaultManageDAO getEntityDaoInf() {
		return dao;
	}


	/**   
	 * @Description:查询维修记录信息
	 * @param page
	 * @return         
	 */ 
	public Page queryPage(Page page,Map<String,String> map) {
		// TODO Auto-generated method stub
		StringBuilder sql =new StringBuilder("from MauMachineFault where 1=1 ");
		if(map != null){
			if(null!=map.get("repairBy")&&!"".equals(map.get("repairBy"))){
				String repairBy=map.get("repairBy").trim();
				sql.append(" and repairBy like '%"+repairBy+"%' ");
			}
			if(null!=map.get("macCode")&&!"".equals(map.get("macCode"))){
				String macCode=map.get("macCode").trim();
				sql.append(" and macCode like '%"+macCode+"%' ");
			}
			if(null!=map.get("status")&&!"".equals(map.get("status"))){
				sql.append(" and status='"+map.get("status")+"' ");
			}
			
			if(null!=map.get("startTime")&&!"".equals(map.get("startTime"))){
				sql.append(" and faultDate>='"+map.get("startTime")+"' ");
			}

			if(null!=map.get("endTime")&&!"".equals(map.get("endTime"))){
				sql.append(" and  faultDate<='"+map.get("endTime")+"' ");
			}
		}
		
		sql.append("  ORDER BY faultDate,status");
		Page pageQuery = dao.pageQuery(sql.toString(), page.getPageNo(), page.getPagesize());
		
		return pageQuery;
	}

	/**
	 * 
	 *@data:2017年8月3日
	@param repairBy
	@param macCode
	@return
	@autor:wl
	 */
	public HashMap<String, Object> updateMauMachineFaultByMacCode(
			String repairBy, String macCode) {
		// TODO Auto-generated method stub
		try {
			String hql="update MauMachineFault set repairBy=?,status=?  where macCode=? and status=?";
			dao.updateMauMachineFaultByMacCode(hql,repairBy,macCode);
			return JsonWrapper.successWrapper("成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}

	/**
	 * 完成机台修理
	 *@data:2017年8月3日
	@param date
	@param macCode
	@return
	@autor:wl
	 */
	public HashMap<String, Object> finishRepairMachine(long date, String macCode) {
		try {
			Timestamp timestamp = new Timestamp(date);
			String hql="update MauMachineFault set repairDate=?,status=? where  macCode=?  and status=?";
			dao.finishRepairMachine(hql,macCode,timestamp);
			return JsonWrapper.successWrapper("成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 *@data:2017年8月3日
	@return
	@autor:wl
	 */
	public List<MauMachineFault> getListFault() {
		List<MauMachineFault> list=null;
		try {
			String hql="from MauMachineFault where status=? order by faultDate  desc";
			list=dao.getListFault(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * 添加工程部维修人
	 *@data:2017年8月5日
	@param repairBy
	@param id
	@return
	@autor:wl
	 */
	public HashMap<String, Object> updateMachineFaultRepairBy(String repairBy,
			Integer id) {
		try {
			String hql="update MauMachineFault  set repairBy=?,status=? where id=?  and status=?";
			dao.updateMachineFaultRepairBy(hql,repairBy,id);
			return JsonWrapper.successWrapper("成功"); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		// TODO Auto-generated method stub
	}

		/**
		 * 修改故障备注
		 *@data:2017年8月5日
		@param remark
		@param status
		@param id
		@return
		@autor:wl
		 */
	public HashMap<String, Object> updateMachineFaultRemark(String remark,
			String status, Integer id) {
		// TODO Auto-generated method stub
		Timestamp timestamp =null;
		String hql="";
		try {
			if("2".equals(status)){
				timestamp = new Timestamp(new Date().getTime());
				hql="update MauMachineFault set remark=?,status=?,repairDate='"+timestamp+"'  where id=? and status=?";
			}else if("0".equals(status)){
				hql="update MauMachineFault set remark=?,status=?  where id=? and status=?";
				dao.updateMachineFaultRemark1(hql,status,id);
				return JsonWrapper.successWrapper("成功"); 
			}else if("1".equals(status)){
				hql="update MauMachineFault set remark=?,status=?  where id=? and status=?";
			}
			dao.updateMachineFaultRemark(hql,remark,status,id);
			return JsonWrapper.successWrapper("成功"); 
		} catch (Exception e) {
			// TODO: handle exception
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
	}
	/**
	 * 得到表格内容格式
	 * 
	 * @return
	 */
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
	
	
	
		/**
		 * 维修管理页面导出excel
		 *@data:2017年8月8日
		@param response
		@param page
		@param macCode
		@param repairBy
		@param status
		@param startTime
		@param endTime
		@throws Exception
		@autor:wl
		 */

		public void exportExcel(HttpServletResponse response, Page page,Map<String,String> map) throws Exception {


			// 获取表格样式
			WritableCellFormat bodyFomat = ExportExcel.getBodyFormat();
			WritableCellFormat titleFomat = ExportExcel.getTitleFormat();
			response.reset();
			OutputStream outputStream = response.getOutputStream();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String format = sf.format(new Date());
			String titleName = "设备故障维修记录excel表".concat(format);
			titleName = new String(titleName.getBytes(), "ISO8859-1");
			WritableWorkbook wwb = null;
			try {
				// 创建表
				wwb = Workbook.createWorkbook(outputStream);
				WritableSheet ws = wwb.createSheet(format, 0);
				String[] arrTitle = new String[] { "故障设备","设备类型", "维修人员", "故障类型", "故障时间",
						"故障描述", "维修完成时间","状态"};
				// 写入标题
				for (int i = 0; i < arrTitle.length; i++) {
					ws.addCell(new Label(i, 0, arrTitle[i], titleFomat));
				}

				Page pageList = this.queryPage(page, map);
				if (null != pageList) {
					@SuppressWarnings("unchecked")
					List<MauMachineFault> data = pageList.getData();
					int x = 1;
					for (int i = 0; i < data.size(); i++) {
						MauMachineFault vo = data.get(i);
						ws.addCell(new Label(0, x, null==vo.getMacCode()?"":vo.getMacCode(), bodyFomat));
						ws.addCell(new Label(1, x, null==vo.getMacType()?"":vo.getMacType()+"", bodyFomat));
						ws.addCell(new Label(2, x, null==vo.getRepairBy()?"":vo.getRepairBy(), bodyFomat));
						ws.addCell(new Label(3, x, null==vo.getFaultType()?"":vo.getFaultType()+"", bodyFomat));
						ws.addCell(new Label(4, x, null==vo.getFaultDate()?"":vo.getFaultDate()+"", bodyFomat));
						ws.addCell(new Label(5, x, null==vo.getRemark()?"":vo.getRemark(), bodyFomat));
						ws.addCell(new Label(6, x, null==vo.getRepairDate()?"":vo.getRepairDate()+"", bodyFomat));
						ws.addCell(new Label(7, x, null==vo.getStatus()?"":vo.getStatus(), bodyFomat));
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

		
		
		/**TG
		 * 设备故障发生时，报告发生故障，向设备故障表中增加故障信息
		 * 获取（故障类型【机械类、电器类】、设备编码、设备类型）
		 * 生成故障编码和故障时间
		 * **********注：设备故障影响生产进度，生产机台调度。需要修改机台或叉车状态为故障中（没有表，预留）**************
		 * @return map故障报告信息，成功或失败
		 */
		public MauMachineFault reportRepair(MauMachineFault mmf){
			
			String hql = "select MAX(id)+1 from MauMachineFault";
			Integer mid = (Integer)dao.createQuery(hql).uniqueResult();
			//设置故障编码  name-id
			mmf.setMmfCode(mmf.getMacCode().concat("-").concat(mid.toString()));
			try{
				//保存故障信息
				dao.save(mmf);
				//更改设备状态
				String mac_code = mmf.getMacCode();
				String mac_type = mmf.getMacType();
				if("叉车".equals(mac_type)){
					MauForklift mf = new MauForklift();
					mf.setfCode(mac_code);
					String sql = "from MauForklift where fCode = '"+mac_code+"' ";
					MauForklift mfl = (MauForklift)dao.createQuery(sql).uniqueResult();
					mfl.setStatus("故障");
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
					pmt.setMacState("故障");
					try {
						dao.updateByCon(pmt, false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return mmf;
			
		}
		
		
		/**TG
		 * 确认到达维修地点
		 * 获取设备编码、维修人员名称
		 * @return 返回成功或失败
		 */
		public MauMachineFault arriveRepairAddrService(String mac_code,String repair_by){
			String hql = "from MauMachineFault where mac_code = ? and status = '未开始'";
			
			@SuppressWarnings("unchecked")
			List<MauMachineFault> list = dao.createQuery(hql, mac_code).list();
			MauMachineFault mmf = new MauMachineFault();
			mmf.setId(list.get(0).getId());
			mmf.setRepairBy(repair_by);
			mmf.setStatus("维修中");
			try {
				dao.updateByCon(mmf, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mmf;
			
		}
		
		
		/**TG
		 * 设备维修完成
		 * 获取设备编码、故障详情
		 * * **********注：设备故障影响生产进度，生产机台调度。需要修改机台或叉车状态为工作中（没有表，预留）**************
		 * @return 返回提交信息
		 */
		@RequestMapping({"repairOver"})
		public MauMachineFault repairOverService(String mac_code,String remark){
			String hql = "from MauMachineFault where mac_code = ? and status = '维修中'";
			
			@SuppressWarnings("unchecked")
			List<MauMachineFault> list = dao.createQuery(hql, mac_code).list();
			MauMachineFault mmf = new MauMachineFault();
			mmf.setId(list.get(0).getId());
			mmf.setRemark(remark);
			mmf.setStatus("已完成");
			
			try {
				//Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new Date().toString());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date da = new Date();
				String daStr = df.format(da);
				Date date = df.parse(daStr);
				mmf.setRepairDate(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				dao.updateByCon(mmf, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mmf;
		}
		
		
		/**
		 * 查询所有故障中的设备，包括未维修和维修中
		 * @return
		 */
		public List<MauMachineFault> repairAllService(){
			String hql = "from MauMachineFault where status = '未维修' or status = '维修中'";
			@SuppressWarnings("unchecked")
			List<MauMachineFault> list = dao.createQuery(hql).list();
			return list;
		}
		
		
		
		
		
		
	
}
