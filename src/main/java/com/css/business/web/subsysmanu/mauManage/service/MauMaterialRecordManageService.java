package com.css.business.web.subsysmanu.mauManage.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauMaterialDetail;
import com.css.business.web.subsysmanu.bean.MauMaterialRecord;
import com.css.business.web.subsysmanu.mauManage.bean.MaterialRequsitionFromVo;
import com.css.business.web.subsysmanu.mauManage.bean.Student;
import com.css.business.web.subsysmanu.mauManage.dao.MauMaterialRecordManageDAO;
import com.css.business.web.subsysstore.bean.StoreOutOfStorageRecord;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauMaterialRecordManageService")
public class MauMaterialRecordManageService extends BaseEntityManageImpl<MauMaterialRecord, MauMaterialRecordManageDAO> {
	@Resource(name="mauMaterialRecordManageDAO")
	//@Autowired
	private MauMaterialRecordManageDAO dao;
	@Override
	public MauMaterialRecordManageDAO getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * method：查询MauMaterialRecord表中的领料部分
	 * @return 返回一个map对象
	 * author:曾斌
	 * date:2017-04-26
	*/
	 public HashMap<String,Object> getMauMaterialRecordsService(String pageStr,String pagesizeStr){
		 String hql = "from MauMaterialRecord where docType =? order by id ";
		 HashMap<String,Object> map = getPagingQueryToolService(hql,pageStr,pagesizeStr,"领料单");
		 return map;
	 }
	 
	 /**
	 * @method：根据id删除MauMaterialRecord表中的一条或者多条记录
	 * @param: ids存放id的数组
	 * @return void
	 * author:曾斌
	 * date:2017-04-26
	*/
	 public void deleteMauMaterialRecordsService(int [] ids){
		 String hql0 = "delete from mau_material_detail USING mau_material_record where mau_material_record.id =? and mau_material_detail.mmr_code = mau_material_record.mmr_code";
		 String hql1 = "delete from MauMaterialRecord where id=?";
		 for (int i = 0; i < ids.length; i++) {
			 dao.createSQLQuery(hql0, ids[i]).executeUpdate();
			 dao.createQuery(hql1, ids[i]).executeUpdate();
		}
	 }
	 
	 /**
	  *@method:跟新 MauMaterialRecord表中的一条记录
	  *@param:Object表所对应的一个实例对象
	  */
	 public void updateMauMaterialRecordService(MauMaterialRecord MauMaterialRecord){
			dao.updateMauMaterialRecordDao(MauMaterialRecord);
	 }
	 /** 
	 * @method: 新增MauMaterialRecord表中的一条记录
	 * author:曾斌
	 * time:2017-05-4
	 */
	 public void addMauMaterialRecordService(MauMaterialRecord MauMaterialRecord) {
		 dao.addMauMaterialRecordDao(MauMaterialRecord);
	 }
	 
	 public HashMap<String, Object> getMauMaterialDetailService(String pageStr,String pagesizeStr, String mmrCode) {
		 String hql = "from MauMaterialDetail where docType =? and mmrCode=?  order by id ";
		 HashMap<String,Object> map = getPagingQueryToolService(hql,pageStr,pagesizeStr,"领料单",mmrCode);
		 return map;
	}
	 /** 
	 * @throws ParseException 
	 * @method: 新增MauMaterialDetail表中的多条记录
	 * author:曾斌
	 * time:2017-05-10
	 */
	 public void addMauMaterialDetailService(List<MauMaterialDetail> list){
		 for (int i = 0; i < list.size(); i++) {
			 dao.addMauMaterialDetailDao(list.get(i));
		}	
	  }
	 /**
	  * 新增MauMaterialDetail表中的多条记录
	  * @param mauMaterialDetaillist
	  */
	 public void updateMauMaterialDetailService(List<MauMaterialDetail> mauMaterialDetaillist) {
		 for (int i = 0; i < mauMaterialDetaillist.size(); i++) {
			dao.updateMauMaterialDetailDao(mauMaterialDetaillist.get(i));
		}
			
	 }
	 
	 /**
	  * 开始领料
	  * @param ids
	  */
	public void startPickgingMaterialService(int[] ids) {
		StoreOutOfStorageRecord sos = new StoreOutOfStorageRecord();
		for (int i = 0; i < ids.length; i++) {
			String hql = "update MauMaterialRecord m set m.remark=? where m.id=?";
			dao.createQuery(hql, "待出库",ids[i]).executeUpdate();
			String hql2 ="from MauMaterialRecord where id=?";
			MauMaterialRecord mauMaterialRecord = (MauMaterialRecord) dao.createQuery(hql2, ids[i]).list().get(0);
			String hql1 = "SELECT d.* FROM mau_material_record r,mau_material_detail d WHERE r.id = ? AND r.mmr_code = d.mmr_code";
			@SuppressWarnings({ "unchecked", "unused" })
			List<Object[]> mauMaterialDetailList = dao.createSQLQuery(hql1, ids[i]).list();
			for (int j = 0; j < mauMaterialDetailList.size(); j++) {
				//工单编号（x）、领料单编号、物料规格型号、数量、单位（x）、领料人（x）、时间（x）、状态（待出库）
				/*sos.setCourseCode(mauMaterialRecord.getWsCode());
				sos.setPickListCode(mauMaterialDetailList.get(j)[6].toString());
				sos.setUnit(mauMaterialDetailList.get(j)[7].toString());
				sos.setObjGgxh(mauMaterialDetailList.get(j)[3].toString());
				sos.setSumamount((BigDecimal)mauMaterialDetailList.get(j)[4]);
				sos.setCreateBy(mauMaterialRecord.getMatermanageBy());
				sos.setCreateDate(mauMaterialRecord.getMatermanageTime());
				sos.setStatus("待出库");
				dao.save(sos);*/
			}
			
		}
		
	}
	/**
	 * 完成领料后的确认
	 * @param ids
	 */
	public void endPickgingMaterialService(int[] ids) {
		for (int i = 0; i < ids.length; i++) {
			String hql = "update MauMaterialRecord m set m.remark=? where m.id=?";
			dao.createQuery(hql, "已出库",ids[i]).executeUpdate();
		}
	}
	/**
	 * 多条件查询
	 * @param page
	 * @param pagesize
	 * @param pickingState
	 * @param pickingwsCode
	 * @param pickingmmrCode
	 * @param pickingmacCode
	 * @return
	 */
	public HashMap<String, Object> getComSerchMauMaterialRecordService(String page, String pagesize, String pickingState,String pickingwsCode, String pickingmmrCode, String pickingmacCode) {
		String hql="from MauMaterialRecord where docType=?";
		List<String> list = new ArrayList<>();
		list.add("领料单");
		if(!pickingState.startsWith("全部")){
			hql+=" and remark =?";
			list.add(pickingState);
		}
		if(pickingwsCode!=""){
			hql+=" and wsCode =?";
			list.add(pickingwsCode);
		}
		if(pickingmmrCode!=""){
			hql+=" and mmrCode =?";
			list.add(pickingmmrCode);
		}
		if(pickingmacCode!=""){
			hql+=" and macCode =?";
			list.add(pickingmacCode);
		}
		hql+= " order by id";
		String[] arrays = new String[list.size()];
		for (int i = 0;i<arrays.length;i++) {
			arrays[i] = list.get(i);
		}
		HashMap<String,Object> map = getPagingQueryToolService(hql,page,pagesize,arrays);
		return map;
	}


	 /** 
	 * @method: 根据mmdCode查询MauMaterialDetail表中的多条记录
	 * author:曾斌
	 * time:2017-05-16
	 */
	//不再使用
	@SuppressWarnings("null")
	@Deprecated
	public List<MaterialRequsitionFromVo> quringMmdBymmrCodeServices(String mmrCode) {
		 String hql = "from MauMaterialDetail where mmrCode=? order by id";
		 String hql1 = "from MauMaterialRecord where mmrCode=? order by id";
		 @SuppressWarnings("unchecked")
		List<MauMaterialDetail> list = dao.createQuery(hql, mmrCode).list();
		MauMaterialRecord mauMaterialRecord = (MauMaterialRecord) dao.createQuery(hql1, mmrCode).list().get(0);
		List<MaterialRequsitionFromVo> listvo = new ArrayList<>();
		MaterialRequsitionFromVo materialRequsitionFromVo = new MaterialRequsitionFromVo();
		for (int i = 0; i < list.size(); i++) {
			materialRequsitionFromVo.setWsCode(mauMaterialRecord.getWsCode());
//			materialRequsitionFromVo.setMacCode(mauMaterialRecord.getMacCode());
			//materialRequsitionFromVo.setMacCode(mauMaterialRecord.getMacCode());
			materialRequsitionFromVo.setMmrCode(mauMaterialRecord.getMmrCode());
			materialRequsitionFromVo.setMaterCode(list.get(i).getMaterCode());
			materialRequsitionFromVo.setMaterAmount(list.get(i).getMaterAmount());
			materialRequsitionFromVo.setUnit(list.get(i).getUnit());
			materialRequsitionFromVo.setMaterManageBy(mauMaterialRecord.getMatermanageBy());
			materialRequsitionFromVo.setMaterManageTime(mauMaterialRecord.getMatermanageTime());
			materialRequsitionFromVo.setDocType(mauMaterialRecord.getDocType());
			listvo.add(materialRequsitionFromVo);
		}
		return listvo;
	}
	 /** 
	 * @method: 根据mmdCode查询MauMaterialDetail表中的多条记录并且生产excel文件
	 * author:曾斌
	 * time:2017-05-25
	 */
	public void exportExcel(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("Sheet0");
		sheet.setColumnWidth((short)0,(short)3000);
		sheet.setColumnWidth((short)1,(short)3000);
		sheet.setColumnWidth((short)2,(short)3000);
		sheet.setColumnWidth((short)3,(short)3000);
		sheet.setColumnWidth((short)4,(short)3000);
		sheet.setColumnWidth((short)5,(short)3000);
		sheet.setColumnWidth((short)6,(short)3000);
		sheet.setColumnWidth((short)7,(short)3000);
		HSSFRow row0 = sheet.createRow(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < 8; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue("东莞启东电线电缆有限公司");
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short)16);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		}
		HSSFRow row1 = sheet.createRow(1);
		HSSFCellStyle cellStyle1 = wb.createCellStyle();
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		for (int i = 0; i < 8; i++) {
			HSSFCell cell = row1.createCell(i);
			cell.setCellValue("领料单");
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
			font.setFontHeightInPoints((short)16);
			cellStyle1.setFont(font);
			cell.setCellStyle(cellStyle1);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
		}
		HSSFRow row2 = sheet.createRow(2);
		HSSFCellStyle cellStyle2 = wb.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		for (int i = 0; i < 8; i++) {
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setFontHeightInPoints((short)12);
			cellStyle2.setFont(font);
			HSSFCell cell;
			if(i==0){
				cell = row2.createCell(i);
				cell.setCellValue("领料部门：");
				cell.setCellStyle(cellStyle2);
			}else if(i<5){
				cell = row2.createCell(i);
				cell.setCellValue("123");
				cell.setCellStyle(cellStyle2);
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));
			}else if(i==5){
				cell = row2.createCell(i);
				cell.setCellValue("单据日期：");
				cell.setCellStyle(cellStyle2);
			}else{
				cell = row2.createCell(i);
				cell.setCellValue("123");
				cell.setCellStyle(cellStyle2);
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 7));
			}
		}
		HSSFRow row3 = sheet.createRow(3);
		HSSFCellStyle cellStyle3 = wb.createCellStyle();
		cellStyle3.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		for (int i = 0; i < 8; i++) {
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setFontHeightInPoints((short)12);
			cellStyle3.setFont(font);
			HSSFCell cell;
			if(i==0){
				cell = row3.createCell(i);
				cell.setCellValue("备注说明：");
				cell.setCellStyle(cellStyle3);
			}else if(i==1){
				cell = row3.createCell(i);
				cell.setCellValue("123");
				cell.setCellStyle(cellStyle3);
			}else if(i==2){
				cell = row3.createCell(i);
				cell.setCellValue("领料用途：");
				cell.setCellStyle(cellStyle3);
			}else if(i<5){
				cell = row3.createCell(i);
				cell.setCellValue("123");
				cell.setCellStyle(cellStyle3);
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 4));
			}else if(i==5){
				cell = row3.createCell(i);
				cell.setCellValue("单据编号：");
				cell.setCellStyle(cellStyle3);
			}else{
				cell = row3.createCell(i);
				cell.setCellValue("123");
				cell.setCellStyle(cellStyle3);
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 7));
			}
		}
		HSSFRow row4 = sheet.createRow(4);
		HSSFCellStyle cellStyle4 = wb.createCellStyle();
		cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		for (int i = 0; i < 8; i++) {
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setFontHeightInPoints((short)12);
			cellStyle4.setFont(font);
			HSSFCell cell;
			cell = row4.createCell(i); 
			cell.setCellStyle(cellStyle4);
			switch(i) 
			{ 
			   case 0: 
				   cell.setCellValue("序号");
			       break; 
			   case 1: 
				   cell.setCellValue("物料名称"); 
			       break; 
			   case 2: 
				   cell.setCellValue("型号规格");
			       break; 
			   case 3: 
				   cell.setCellValue("颜色");
			       break; 
			   case 4: 
				   cell.setCellValue("批次号");
			       break; 
			   case 5: 
				   cell.setCellValue("数量"); 
			       break;
			   case 6: 
				   cell.setCellValue("单位");
			       break; 
			   default: 
				   cell.setCellValue("工作单号"); 
			       break; 
			} 
		}
		HSSFCellStyle cellStyle5 = wb.createCellStyle();
		cellStyle5.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle5.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle5.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle5.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle5.setBorderTop(HSSFCellStyle.BORDER_THIN);
		//模拟一个list泛型集合
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "张三", "男"));
		//把list集合中的数据填入到表格中，空白行不设置值
		for (int i = 5; i < 15; i++) {
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setFontHeightInPoints((short)12);
			cellStyle5.setFont(font);
			HSSFCell cell;
			if(i<(5+list.size())){
				HSSFRow row = sheet.createRow(i);
				for (int j = 0; j < 8; j++) {
					cell = row.createCell(j); 
					cell.setCellStyle(cellStyle5);
					/*if(j==0){
						cell.setCellValue(list.get(0).getName());
					}else if(j==1){
						cell.setCellValue(list.get(0).getSex());
					}else{
						cell.setCellValue(list.get(0).getAge());
					}*/
					
				}
			}else{
				HSSFRow row = sheet.createRow(i);
				for (int j = 0; j < 8; j++) {
					cell = row.createCell(j); 
					cell.setCellStyle(cellStyle5);
				}
			}
		}
		HSSFRow row15 = sheet.createRow(15);
		HSSFCellStyle cellStyle6 = wb.createCellStyle();
		cellStyle6.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle6.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle6.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle6.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle6.setBorderTop(HSSFCellStyle.BORDER_THIN);
		for (int i = 0; i < 8; i++) {
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setFontHeightInPoints((short)12);
			cellStyle6.setFont(font);
			HSSFCell cell;
			if(i==0){
				cell = row15.createCell(i);
				cell.setCellValue("合计数量");
				cell.setCellStyle(cellStyle6);
			}else{
				cell = row15.createCell(i);
				cell.setCellStyle(cellStyle6);
				sheet.addMergedRegion(new CellRangeAddress(15, 15, 1, 7));
			}
		}
		//第17行
		HSSFRow row16 = sheet.createRow(16);
		HSSFCellStyle cellStyle7 = wb.createCellStyle();
		cellStyle7.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		for (int i = 0; i < 8; i++) {
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setFontHeightInPoints((short)12);
			cellStyle7.setFont(font);
			HSSFCell cell;
			if(i==0){
				cell = row16.createCell(i);
				cell.setCellValue("仓库发料：");
				cell.setCellStyle(cellStyle7);
			}else if(i<3){
				cell = row16.createCell(i);
				cell.setCellStyle(cellStyle7);
				sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 2));
			}else if(i==3){
				cell = row16.createCell(i);
				cell.setCellValue("领用人：");
				cell.setCellStyle(cellStyle7);
			}else if(i<6){
				cell = row16.createCell(i);
				cell.setCellValue("123");
				cell.setCellStyle(cellStyle7);
				sheet.addMergedRegion(new CellRangeAddress(16, 16, 4, 5));
			}else if(i==6){
				cell = row16.createCell(i);
				cell.setCellValue("制单：");
				cell.setCellStyle(cellStyle7);
			}else{
				cell = row16.createCell(i);
				cell.setCellValue("123");
				cell.setCellStyle(cellStyle7);
			}
		}
		HSSFRow row17 = sheet.createRow(17);
		HSSFCellStyle cellStyle8 = wb.createCellStyle();
		cellStyle8.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		for (int i = 0; i < 8; i++) {
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setFontHeightInPoints((short)12);
			cellStyle8.setFont(font);
			HSSFCell cell;
			cell = row17.createCell(i);
			cell.setCellValue("白色(仓库存根),红色(财务),黄色(车间领料),绿色(车间)");
			cell.setCellStyle(cellStyle7);
			//合并单元格居中
			sheet.addMergedRegion(new CellRangeAddress(17, 17, 0, 7));
		}
		
		
	}
	 /**
	  * @method:封装分页查询的一个重要方法
	  * @param hql 需要查询的一个hql语句
	  * @param pageStr 当前第几页，从liguerui获取得到的
	  * @param pagesizeStr 每页记录数，从liguerui获取得到的
	  * @param values 需要的参数，记住hql语句的顺序，参考getPagingQueryToolDao;
	  * @return HashMap<String,Object>
	  * author:曾斌
	  * date:2017-04-26
	  */
	 public HashMap<String,Object> getPagingQueryToolService(String hql,String pageStr,String pagesizeStr,final Object...values){
		 HashMap<String,Object> map=new HashMap<String,Object>();
		 List<?> list;
		 double total;
		 int page=1;
		 int pagesize=10;
		 if(pageStr!=null){
			 page =Integer.parseInt(pageStr);
		  }
		 if(pagesizeStr!=null){
			 pagesize=Integer.parseInt(pagesizeStr);
		 }
		 total = dao.getPagingQueryToolDao(hql, true, page, pagesize, values).size();
		 list = dao.getPagingQueryToolDao(hql, false, page, pagesize, values);
		 map.put("Total", total);
		 map.put("Rows", list);
		 return map;
	 }

	 /**
	  * 
	  * @Description:电子看板显示
	  * @return
	  */
	public List<MauMaterialRecord> getTodayList() {
		int workDay = Integer.parseInt(DateUtil.format(new Date(), "yyyyMMdd"));
		String hql = " from MauMaterialRecord where docType='领料单'  and ( workDay="+workDay+" or (workDay < "+workDay+" and mmrState !='已结束'))";
		List<MauMaterialRecord> list = dao.listQuery(hql);
		List<MauMaterialRecord>newList = new ArrayList<>();
		for (MauMaterialRecord record : list) {
			MauMaterialRecord newRecord = new MauMaterialRecord();
			newRecord.setMmrCode(record.getMmrCode());
			newRecord.setWsCode(record.getWsCode());
			newRecord.setIsOver(record.getIsOver()==null?"":record.getIsOver());
			newRecord.setStartDateVo(record.getTimeStart()==null?"":record.getTimeStart().toString().substring(11, 16));
			newRecord.setEndDateVo(record.getTimeEnd()==null?"":record.getTimeEnd().toString().substring(11, 16));
			newRecord.setMmrState(record.getMmrState());
			newList.add(newRecord);
		}
		return newList;
	}



	

	
}
