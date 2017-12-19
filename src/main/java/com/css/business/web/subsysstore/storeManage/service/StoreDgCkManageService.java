package com.css.business.web.subsysstore.storeManage.service;

import java.io.OutputStream;
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
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreDgCk;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.storeManage.dao.StoreDgCkManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
//import com.css.business.web.sysManage.dao.SysAttachmentManageDAO;

@Service("storeDgCkManageService")
public class StoreDgCkManageService extends
		BaseEntityManageImpl<StoreDgCk, StoreDgCkManageDAO> {
	@Resource(name = "storeDgCkManageDAO")
	// @Autowired
	private StoreDgCkManageDAO dao;

	@Autowired
	private StoreObjManageService objService;


	@Override
	public StoreDgCkManageDAO getEntityDaoInf() {
		return dao;
	}

	public Page getStoreRecordPageList(Page param, String pickListCode,
			String objGgxh, String starttime, String endtime,String status,String outboundOrderCode) {
		StringBuilder sql=null;
		
			 sql = new StringBuilder(
					" from StoreDgCk o where 1=1");
			if (null != pickListCode && !"".equals(pickListCode)) {
				sql.append("  and  o.pickListCode  like  '%" + pickListCode + "%'");
			}
			if (null != outboundOrderCode && !"".equals(outboundOrderCode)) {
				sql.append("  and  o.outboundOrderCode  like  '%" + outboundOrderCode + "%'");
			}
			if (null != objGgxh && !"".equals(objGgxh)) {
				sql.append("  and  o.objGgxh='" + objGgxh + "'");
			}
		
			if (null != starttime && !"".equals(starttime)) {
				sql.append("  and  o.operatTime>='" + starttime + "'");
			}
			if (null != endtime && !"".equals(endtime)) {
				sql.append("  and  o.operatTime<='" + endtime + "'");
			}
			if(null!=status&&!"".equals(status)){
				sql.append("  and  o.status='" + status + "'");
			}
		sql.append("   order by  status,operatTime desc ");
		Page page =null;
		try {
			 page = dao.getRecordList(sql,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public void saveEnt(HttpServletRequest request, SysUser user,
			StoreDgCk ent) throws Exception {
		Integer id = ent.getId();
		ent.setCreateBy(user.getAccount());
		ent.setCreateDate(new Date());
		if (id == null)
			this.save(ent);
		else
			this.updateByCon(ent, false);
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

	private WritableCellFormat getTitleFomat() {

		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					13, WritableFont.BOLD);
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
	 * 
	 * 导出excel表
	 * 
	 * @throws Exception
	 * */
	public void exportExecl(HttpServletRequest request,
			HttpServletResponse response, Page param, String pickListCode,
			String objGgxh,  String starttime, String endtime,String status,String outboundOrderCode)
			throws Exception {

		// 获取表格样式
		WritableCellFormat bodyFomat = this.getBodyFomat();
		WritableCellFormat titleFomat = this.getTitleFomat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = format + "出库单管理excel表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
		try {
			
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] {"出库单号", "领料单号","规格型号","操作人","操作时间","领料人",  "出库状态"};
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i],titleFomat));
			}
	
			Page pageList = this.getStoreRecordPageList(param,pickListCode,objGgxh,  starttime,  endtime,status, outboundOrderCode);
			if (null != pageList) {
				List<StoreDgCk> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					StoreDgCk vo = data.get(i);
					ws.addCell(new Label(0, x, null==vo.getOutboundOrderCode()?"":vo.getOutboundOrderCode(), bodyFomat));
					ws.addCell(new Label(1, x, null==vo.getPickListCode()?"":vo.getPickListCode(), bodyFomat));
					ws.addCell(new Label(2, x, null==vo.getObjGgxh()?"":vo.getObjGgxh(), bodyFomat));
					ws.addCell(new Label(3, x, null==vo.getOperator()?"":vo.getOperator(), bodyFomat));
					ws.addCell(new Label(4, x, null==vo.getOperatTime()?"":vo.getOperatTime()+"", bodyFomat));
					ws.addCell(new Label(5, x, null==vo.getPicktor()?"":vo.getPicktor(), bodyFomat));
					ws.addCell(new Label(6, x, null==vo.getStatus()?"":vo.getStatus(), bodyFomat));
					
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
	 * 查询材料下拉框
	 * */
	public List<String> getSelectOption() {
		List<String> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"select value  from SysCommdic   where clas=?");

		list = dao.getSelectOption(sql.toString());
		return list;
	}

	

	// 将原材料入库信息对象转换成报废对象
	private StoreDgCk transformObject(StoreObj obj, String remark) {
		StoreDgCk StoreDgCk = new StoreDgCk();
		
		return StoreDgCk;
	}

	private Map<String, Object> getRfidCodeByToken(String token) {
		Map<String, Object> map = new HashMap<>();
		if (null != token && !"".equals(token)) {
			String[] rfid_code = token.split(",");
			for (String obj : rfid_code) {
				String[] rfid_code2 = obj.split("-");
				String rfid = rfid_code2[0];
				String remark = rfid_code2[1];
				map.put(rfid, remark);
			}
		}
		return map;
	}

	public List<StoreDgCk> getScrapStock() {
		String hql = " from StoreDgCk s order by s.handleDate DESC ";
		@SuppressWarnings("unchecked")
		List<StoreDgCk> list = dao.createQuery(hql).list();
		return list;
	}
/**
 * 编辑时回显数据
 *@data:2017年7月23日
@param id
@return
@autor:wl
 */
	public StoreDgCk getObjectById(Integer id) {
		// TODO Auto-generated method stub
		StoreDgCk obj=null;
		try {
			String hql="from StoreDgCk where id=?";
			obj=dao.getObjectById(hql,id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return obj;
	}

		public boolean updateProductManageService(Object obj) {
			
			return dao.updateProductManageDao(obj);
			
		}
		/**
		 * 出库操作
		 *@data:2017年7月27日
		@param amount
		@param objGgxh
		@param pickListCode
		@return
		@autor:wl
		 */
		public boolean updateAndsaveOut(String amount, String objGgxh,
				String pickListCode,String createBy) {
			boolean flag=false;
			try {
				List<StoreDgCk> list = dao.getRecordListByobjGgxh(objGgxh,pickListCode);
				if(null!=list&&list.size()>0){
					StoreDgCk record = list.get(0);
					//return changeOutRecord(amount, record, createBy);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return flag;
		}
		
		/*private boolean changeOutRecord(String amount,StoreDgCk record,String createBy) throws Exception {
			boolean flag=false;
			BigDecimal sumamount = record.getSumamount();
			BigDecimal amount1=new BigDecimal(amount);
			int compareTo = sumamount.compareTo(amount1);

			//表示是第一次出库
			if(compareTo==0){
				record.setAlreadyReceiveAmount(amount1);
				record.setStatus("已出库");
				record.setLeaveAmount(new BigDecimal("0"));
				record.setOutStorageDate(new Date());
				dao.updateByCon(record, true);
				flag=true;
				return flag;
			}
			if(compareTo==1){
				record.setAlreadyReceiveAmount(amount1);
				record.setStatus("已出库");
				record.setOutStorageDate(new Date());
				record.setLeaveAmount(sumamount.subtract(amount1));
				dao.updateByCon(record, true);
				//修改第一条纪律的状态和数量 并新增一条纪律
				StoreDgCk newRecord= new StoreDgCk();
				newRecord.setPickListCode(record.getPickListCode());
				newRecord.setCourseCode(record.getCourseCode());
				newRecord.setCreateDate(new Date());
				newRecord.setObjGgxh(record.getObjGgxh());
				newRecord.setStatus("待出库");
				newRecord.setSumamount(record.getLeaveAmount());
				newRecord.setUnit(record.getUnit());
				newRecord.setCreateBy(createBy);
				newRecord.setAlreadyReceiveAmount(new BigDecimal("0"));
				newRecord.setLeaveAmount(record.getLeaveAmount());
				newRecord.setOutStorageDate(null);
				dao.save(newRecord);
				flag=true;
				return flag;
			}
			if(compareTo==-1){
				return flag;
			}
			return flag;
		}*/
		/**
		 * 根据领料单号查询所有胡规格
		 *@data:2017年7月28日
		@param pickListCode
		@return
		@autor:wl
		 */
		public List<String> getPgxhByPickListCode(String pickListCode) {
			// TODO Auto-generated method stub
			 List<String> list =null;
			try {
				String hql="select objGgxh from StoreDgCk where pickListCode=?  and status=?  group by  objGgxh";
				list=dao.getPgxhByPickListCode(hql,pickListCode);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
		}

		public List<String> getPickListCode() {
			List<String> list = new ArrayList<>();
			StringBuilder sql = new StringBuilder(
					"select pickListCode  from StoreDgCk  where status=?  group by pickListCode");

			list = dao.getPickListCode(sql.toString());
			return list;
		}
		/********************************************/
		/**
		 * 点击出库修改出库状态
		 * @param idName
		 * @return
		 */
		public Boolean updateStatusById(String idName) {
			// TODO Auto-generated method stub
			Boolean flag=false;
			int id =0;
			try {
				if(null!=idName&&!"".equals(idName)){
					 id = Integer.parseInt(idName);
				}
				String hql="update StoreDgCk  set status=? where id=?";
				dao.updateStatusById(hql,id);
				flag=true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return flag;
		}
		/**
		 * 根据id获取出库单号
		 * @param id
		 * @return
		 */
		public StoreDgCk getOutCodeById(Integer id) {
			// TODO Auto-generated method stub
			StoreDgCk objCk=null;
			try {
				String hql="from StoreDgCk where id=?";
			List<StoreDgCk>	list=dao.getOutCodeById(hql,id);
			if(null!=list&&list.size()>0){
				objCk=list.get(0);
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return objCk;
		}
		
}
