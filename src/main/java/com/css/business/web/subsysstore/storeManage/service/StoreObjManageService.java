package com.css.business.web.subsysstore.storeManage.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.bean.MauRfid;
import com.css.business.web.subsysmanu.mauManage.service.MauCallForkliftRecordManageService;
import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.business.web.subsysquality.bean.QualityMaterReport;
import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.bean.StoreCoilSaDetail;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.storeManage.bean.StoreHdmiVo;
import com.css.business.web.subsysstore.storeManage.dao.StoreMrecordManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreObjManageDAO;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeObjManageService")
public class StoreObjManageService extends
		BaseEntityManageImpl<StoreObj, StoreObjManageDAO> {
	@Resource(name = "storeObjManageDAO")
	// @Autowired
	private StoreObjManageDAO dao;
	
	@Resource(name = "storeMrecordManageDAO")
	private StoreMrecordManageDAO smDao;
	
	@Resource(name = "mauCallForkliftRecordManageService")
	private MauCallForkliftRecordManageService mauCallForklift;

	@Override
	public StoreObjManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 条件查询原料入库
	 * 
	 * @param time1
	 * @param time2
	 * @param batchCode
	 * @param objSort
	 * @param objGgxh
	 * @param address
	 * @param p
	 * @return
	 * @throws Exception
	 * @author JS
	 */
	public Page queryStoreObj(String time1, String time2, String batchCode,
			String objSort, String objGgxh, String address, String rfidCode,
			Page p) throws Exception {
		StringBuffer hql = new StringBuffer(
				" from StoreObj s where s.inoutType='入库' ");
		if (time1 != null && !"".equals(time1) && time2 != null
				&& !"".equals(time2)) {
			hql.append(" and s.inDate >= '" + time1 + "' and s.inDate <= '"
					+ time2 + "' ");
		}
		if (batchCode != null && !"".equals(batchCode)) {
			hql.append(" and s.batchCode = '" + batchCode + "' ");
		}
		if (objSort != null && !"".equals(objSort)) {
			hql.append(" and s.objName= '" + objSort + "' ");
		}
		if (objGgxh != null && !"".equals(objGgxh)) {
			hql.append(" and s.objGgxh= '" + objGgxh + "' ");
		}
		if (address != null && !"".equals(address)) {
			hql.append(" and s.address= '" + address + "' ");
		}
		if (rfidCode != null && !"".equals(rfidCode)) {
			hql.append(" and s.rfidCode= '" + rfidCode + "' ");
		}
		hql.append(" order by s.inDate desc ");
		Page page = dao.queryStoreObjDao(hql.toString(), p);
		return page;
	}

	public List<StoreObj> queryStoreRfid(String rfid) {
		String hql = " from StoreObj s where s.inoutType='入库' and s.rfidCode = ? ";
		@SuppressWarnings("unchecked")
		List<StoreObj> list = dao.createQuery(hql, rfid).list();
		return list;
	}

	/**
	 * 查询柱状图数据
	 * 
	 * @param objSort
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getChart(String objSort) {
		String hql = " from StoreObj s where s.inoutType='入库' and s.objSort = ? order by objGgxh ";
		List<StoreObj> list = dao.getChartDao(hql, objSort);
		Map<String, List<String>> chartsMap = new HashMap<String, List<String>>();
		if (list.size() > 0 && list != null) {
			// 柱状图的X轴
			List<String> xAxis = new ArrayList<>();
			// 柱状图的Y轴
			List<String> series = new ArrayList<>();
			String sort = "";
			for (StoreObj storeReturn : list) {
				Double acount = 0.0;
				String hql1 = " SELECT sum(acount) from StoreObj where inoutType='入库' and objGgxh = ? ";
				List<Object> list2 = dao.createQuery(hql1,
						storeReturn.getObjGgxh()).list();
				if (!sort.equals(storeReturn.getObjGgxh())) {
					acount = Double.parseDouble(list2.get(0).toString());
					xAxis.add(storeReturn.getObjGgxh());
					series.add(acount + "");
				}
				sort = storeReturn.getObjGgxh();

			}
			chartsMap.put("xAxis", xAxis);
			chartsMap.put("series", series);
		}
		return chartsMap;
	}

	/**
	 * @method：查询StoreOBJ
	 * @return 返回一个map对象
	 * @author:曾斌
	 * @date:2017-06-28
	 */
	public HashMap<String, Object> getStorePickingMaterialManageService(
			String pageStr, String pagesizeStr) {
		String hql = "from StoreObj where materialType=? order by id";
		HashMap<String, Object> map = getPagingQueryToolService(hql, pageStr,
				pagesizeStr, "原材料");
		return map;
	}

	/**
	 * @method：查询SysCommdic,查询参数列表，初始化下拉列表框
	 * @return 返回一个list对象
	 * @author:曾斌
	 * @date:2017-06-28
	 */
	public List<?> getObjSortFromSysCommedicManageService(String objSort) {
		String hql = "from SysCommdic where clas=?";
		List<?> list = new ArrayList<>();
		list = dao.getObjSortFromSysCommedicManageDao(hql, objSort);
		return list;
	}

	/**
	 * @method：查询StoreObj,综合查询
	 * @return 返回一个map对象
	 * @author:曾斌
	 * @date:2017-06-30
	 */
	public HashMap<String, Object> getComprehensiveSerchFromStoreObjManageService(
			String page, String pagesize, String startTime, String endTime,
			String objSort, String objGgxh, String rfidCode) {
		String hql = "from StoreObj where materialType=?";
		List<String> list = new ArrayList<>();
		list.add("原材料");
		if (startTime != "") {
			hql += " and outDate >='" + startTime + "'";
		}
		if (endTime != "") {
			hql += " and outDate <='" + endTime + "'";
		}
		if (!objSort.startsWith("---")) {
			hql += " and objSort =?";
			list.add(objSort);
		}
		if (objGgxh != "") {
			hql += " and objGgxh =?";
			list.add(objGgxh);
		}
		if (rfidCode != "") {
			hql += " and rfidCode =?";
			list.add(rfidCode);
		}
		hql += " order by id";
		String[] arrays = new String[list.size()];
		for (int i = 0; i < arrays.length; i++) {
			arrays[i] = list.get(i);
		}
		HashMap<String, Object> map = getPagingQueryToolService(hql, page,
				pagesize, arrays);
		return map;
	}

	/**
	 * @method：查询StoreObj,综合查询，将查询到的数据导出到EXCEL
	 * @return 返回一个map对象
	 * @author:曾斌
	 * @date:2017-06-30
	 */
	public void exportExcel(HSSFWorkbook wb, String startTime, String endTime,
			String objSort, String objGgxh, String rfidCode) {
		HSSFSheet sheet = wb.createSheet("Sheet0");
		sheet.setColumnWidth((short) 0, (short) 3000);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 3, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 3000);
		sheet.setColumnWidth((short) 5, (short) 3000);
		sheet.setColumnWidth((short) 6, (short) 3000);
		sheet.setColumnWidth((short) 7, (short) 3000);
		sheet.setColumnWidth((short) 8, (short) 3000);
		sheet.setColumnWidth((short) 9, (short) 3000);
		sheet.setColumnWidth((short) 10, (short) 3000);
		sheet.setColumnWidth((short) 11, (short) 3000);
		sheet.setColumnWidth((short) 12, (short) 3000);

		HSSFRow row0 = sheet.createRow(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < 13; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue("东莞启东电线电缆有限公司");
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short) 16);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
		}

		HSSFRow row1 = sheet.createRow(1);
		HSSFCellStyle cellStyle1 = wb.createCellStyle();
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		HSSFFont font = wb.createFont();
		font.setFontName("仿宋_GB2312");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
		font.setFontHeightInPoints((short) 16);
		cellStyle1.setFont(font);
		for (int i = 0; i < 13; i++) {
			HSSFCell cell = row1.createCell(i);
			cell.setCellValue("领料记录");
			cell.setCellStyle(cellStyle1);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 12));
		}

		HSSFCellStyle cellStyle2 = wb.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HSSFFont font2 = wb.createFont();
		font2.setFontName("仿宋_GB2312");
		font2.setFontHeightInPoints((short) 12);
		cellStyle2.setFont(font2);
		HSSFRow row2 = sheet.createRow(2);
		for (int i = 0; i < 13; i++) {
			HSSFCell cell = row2.createCell(i);
			switch (i) {
			case 0:
				cell.setCellValue("序号");
				break;
			case 1:
				cell.setCellValue("批次号");
				break;
			case 2:
				cell.setCellValue("材料");
				break;
			case 3:
				cell.setCellValue("RFID卡编码");
				break;
			case 4:
				cell.setCellValue("型号");
				break;
			case 5:
				cell.setCellValue("数量");
				break;
			case 6:
				cell.setCellValue("位置");
				break;
			case 7:
				cell.setCellValue("操作人员");
				break;
			case 8:
				cell.setCellValue("叉车编号");
				break;
			case 9:
				cell.setCellValue("送达机台");
				break;
			case 10:
				cell.setCellValue("班次");
				break;
			case 11:
				cell.setCellValue("调用班次");
				break;
			default:
				cell.setCellValue("出库时间");
				break;
			}
			cell.setCellStyle(cellStyle2);
		}

		String hql = "from StoreObj where materialType=?";
		List<String> list = new ArrayList<>();
		list.add("原材料");
		if (startTime != "") {
			hql += " and outDate >='" + startTime + "'";
		}
		if (endTime != "") {
			hql += " and outDate <='" + endTime + "'";
		}
		if (!objSort.startsWith("---")) {
			hql += " and objSort =?";
			list.add(objSort);
		}
		if (objGgxh != "") {
			hql += " and objGgxh =?";
			list.add(objGgxh);
		}
		if (rfidCode != "") {
			hql += " and rfidCode =?";
			list.add(rfidCode);
		}
		hql += " order by id";
		String[] arrays = new String[list.size()];
		for (int i = 0; i < arrays.length; i++) {
			arrays[i] = list.get(i);
		}

		List<?> list1 = dao.getObjSortForExcel(hql, arrays);
		for (int i = 0; i < list1.size(); i++) {
			HSSFRow row = sheet.createRow(3 + i);
			StoreObj storeObj = (StoreObj) list1.get(i);
			for (int j = 0; j < 13; j++) {
				HSSFCell cell = row.createCell(j);
				switch (j) {
				case 0:
					cell.setCellValue(storeObj.getId());
					break;
				case 1:
					cell.setCellValue(storeObj.getBatchCode());
					break;
				case 2:
					cell.setCellValue(storeObj.getObjSort());
					break;
				case 3:
					cell.setCellValue(storeObj.getRfidCode());
					break;
				case 4:
					cell.setCellValue(storeObj.getObjGgxh());
					break;
				case 5:
					cell.setCellValue(storeObj.getAcount());
					break;
				case 6:
					cell.setCellValue(storeObj.getAddress());
					break;
				case 7:
					cell.setCellValue(storeObj.getCreateBy());
					break;
				case 8:
					cell.setCellValue(storeObj.getForkliftTruckNumber());
					break;
				case 9:
					cell.setCellValue(storeObj.getArriveMachine());
					break;
				case 10:
					cell.setCellValue(storeObj.getShift());
					break;
				case 11:
					cell.setCellValue(storeObj.getCallFlight());
					break;
				default:
					Date date2 = storeObj.getOutDate();
					if (date2 != null) {
						SimpleDateFormat dff = new SimpleDateFormat(
								"yyyy-MM-dd");
						String str = dff.format(date2);// 日期转化
						cell.setCellValue(str);
					}
					break;
				}
				cell.setCellStyle(cellStyle2);
			}

		}

	}

	/**
	 * @method:封装分页查询的一个重要方法
	 * @param hql
	 *            需要查询的一个hql语句
	 * @param pageStr
	 *            当前第几页，从liguerui获取得到的
	 * @param pagesizeStr
	 *            每页记录数，从liguerui获取得到的
	 * @param values
	 *            需要的参数，记住hql语句的顺序，参考getPagingQueryToolDao;
	 * @return HashMap<String,Object> author:曾斌 date:2017-6-28
	 */
	public HashMap<String, Object> getPagingQueryToolService(String hql,
			String pageStr, String pagesizeStr, final Object... values) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<?> list;
		double total;
		int page = 1;
		int pagesize = 10;
		if (pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		if (pagesizeStr != null) {
			pagesize = Integer.parseInt(pagesizeStr);
		}
		total = dao.getPagingQueryToolDao(hql, true, page, pagesize, values)
				.size();
		list = dao.getPagingQueryToolDao(hql, false, page, pagesize, values);
		map.put("Total", total);
		map.put("Rows", list);
		return map;
	}

	/**
	 * 保存入库信息
	 * 
	 * @param obj
	 * @author JS
	 */
	public void SaveStoreObj(StoreObj obj) {
		dao.save(obj);
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void updateQualityMauReport(QualityMauReport qualityMauReport)
			throws Exception {
		dao.updateByCon(qualityMauReport, false);
	}

	/**
	 * 
	 * @param rfidCode
	 * @return
	 * @author JS
	 */
	public List<StoreObj> getStoreObjDate(String rfidCode) {
		String hql = " from StoreObj s where s.rfidCode = ? and inoutType = '入库' ";
		@SuppressWarnings("unchecked")
		List<StoreObj> list = dao.createQuery(hql, rfidCode).list();
		return list;
	}

	public void exportInStoreExcel(HSSFWorkbook wb, String time1, String time2,
			String batchCode, String objSort, String objGgxh, String address) {
		HSSFSheet sheet = wb.createSheet("Sheet0");
		sheet.setColumnWidth((short) 0, (short) 3000);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 3, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 3000);
		sheet.setColumnWidth((short) 5, (short) 3000);
		sheet.setColumnWidth((short) 6, (short) 3000);
		sheet.setColumnWidth((short) 7, (short) 3000);
		sheet.setColumnWidth((short) 8, (short) 3000);
		sheet.setColumnWidth((short) 9, (short) 3000);
		sheet.setColumnWidth((short) 10, (short) 3000);
		sheet.setColumnWidth((short) 11, (short) 3000);
		sheet.setColumnWidth((short) 12, (short) 3000);

		HSSFRow row0 = sheet.createRow(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		for (int i = 0; i < 13; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue("东莞启东电线电缆有限公司");
			HSSFFont font = wb.createFont();
			font.setFontName("仿宋_GB2312");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short) 16);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
		}

		HSSFRow row1 = sheet.createRow(1);
		HSSFCellStyle cellStyle1 = wb.createCellStyle();
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		HSSFFont font = wb.createFont();
		font.setFontName("仿宋_GB2312");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
		font.setFontHeightInPoints((short) 16);
		cellStyle1.setFont(font);
		for (int i = 0; i < 13; i++) {
			HSSFCell cell = row1.createCell(i);
			cell.setCellValue("入库记录表");
			cell.setCellStyle(cellStyle1);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 12));
		}

		HSSFCellStyle cellStyle2 = wb.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HSSFFont font2 = wb.createFont();
		font2.setFontName("仿宋_GB2312");
		font2.setFontHeightInPoints((short) 12);
		cellStyle2.setFont(font2);
		HSSFRow row2 = sheet.createRow(2);
		for (int i = 0; i < 13; i++) {
			HSSFCell cell = row2.createCell(i);
			switch (i) {
			case 0:
				cell.setCellValue("序号");
				break;
			case 1:
				cell.setCellValue("批次号");
				break;
			case 2:
				cell.setCellValue("材料");
				break;
			case 3:
				cell.setCellValue("RFID卡编码");
				break;
			case 4:
				cell.setCellValue("型号");
				break;
			case 5:
				cell.setCellValue("数量");
				break;
			case 6:
				cell.setCellValue("位置");
				break;
			case 7:
				cell.setCellValue("操作人员");
				break;
			case 8:
				cell.setCellValue("叉车编号");
				break;
			case 9:
				cell.setCellValue("送达机台");
				break;
			case 10:
				cell.setCellValue("班次");
				break;
			case 11:
				cell.setCellValue("调用班次");
				break;
			default:
				cell.setCellValue("出库时间");
				break;
			}
			cell.setCellStyle(cellStyle2);
		}

		String hql = "from StoreObj where materialType=?";
		List<String> list = new ArrayList<>();
		list.add("原材料");
		if (time1 != "") {
			hql += " and outDate >='" + time1 + "'";
		}
		if (time2 != "") {
			hql += " and outDate <='" + time2 + "'";
		}
		if (objSort != "") {
			hql += " and objSort =?";
			list.add(objSort);
		}
		if (objGgxh != "") {
			hql += " and objGgxh =?";
			list.add(objGgxh);
		}
		if (batchCode != "") {
			hql += " and batchCode=? ";
		}
		if (address != "") {
			hql += " and rfidCode =?";
			list.add(address);
		}
		hql += " order by id";
		String[] arrays = new String[list.size()];
		for (int i = 0; i < arrays.length; i++) {
			arrays[i] = list.get(i);
		}

		List<?> list1 = dao.createQuery(hql, arrays).list();
		for (int i = 0; i < list1.size(); i++) {
			HSSFRow row = sheet.createRow(3 + i);
			StoreObj storeObj = (StoreObj) list1.get(i);
			for (int j = 0; j < 13; j++) {
				HSSFCell cell = row.createCell(j);
				switch (j) {
				case 0:
					cell.setCellValue(storeObj.getId());
					break;
				case 1:
					cell.setCellValue(storeObj.getBatchCode());
					break;
				case 2:
					cell.setCellValue(storeObj.getObjSort());
					break;
				case 3:
					cell.setCellValue(storeObj.getRfidCode());
					break;
				case 4:
					cell.setCellValue(storeObj.getObjGgxh());
					break;
				case 5:
					cell.setCellValue(storeObj.getAcount());
					break;
				case 6:
					cell.setCellValue(storeObj.getAddress());
					break;
				case 7:
					cell.setCellValue(storeObj.getCreateBy());
					break;
				case 8:
					cell.setCellValue(storeObj.getForkliftTruckNumber());
					break;
				case 9:
					cell.setCellValue(storeObj.getArriveMachine());
					break;
				case 10:
					cell.setCellValue(storeObj.getShift());
					break;
				case 11:
					cell.setCellValue(storeObj.getCallFlight());
					break;
				default:
					Date date2 = storeObj.getOutDate();
					if (date2 != null) {
						SimpleDateFormat dff = new SimpleDateFormat(
								"yyyy-MM-dd");
						String str = dff.format(date2);// 日期转化
						cell.setCellValue(str);
					}
					break;
				}
				cell.setCellStyle(cellStyle2);
			}

		}

	}

	public Page getInStock(Page p) {
		String hql = " from StoreObj s where s.inoutType='入库' order by s.inDate DESC ";
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	public Page getOutStock(Page p) throws Exception {
		String sql = " SELECT o.acount AS acount, o.obj_sort AS sort,o.obj_ggxh AS ggxh,o.unit AS unit, "
				+ " r.count AS amount FROM store_obj o LEFT JOIN store_return r ON  o.obj_ggxh = r.model "
				+ " WHERE  o.inout_type LIKE '%出库' ORDER BY o.out_date DESC ";
		Page page = dao.pageSQLQueryVONoneDesc(sql, p.getPageNo(),
				p.getPagesize(), new StoreHdmiVo());
		return page;
	}

	@SuppressWarnings("rawtypes")
	public Map[] getBatchCode() {
		String hql = " SELECT DISTINCT batchCode FROM StoreObj WHERE batchCode != '' and inoutType='入库' ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { // 根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("key", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@SuppressWarnings("rawtypes")
	public Map[] getObjSort() {
		String hql = " SELECT DISTINCT objName FROM StoreObj WHERE objName != ''  and inoutType='入库' ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { // 根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("key", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@SuppressWarnings("rawtypes")
	public Map[] getObjGgxh() {
		String hql = " SELECT DISTINCT objGgxh FROM StoreObj WHERE objGgxh != ''  and inoutType='入库' ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { // 根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("key", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@SuppressWarnings("rawtypes")
	public Map[] getAddress() {
		String hql = " SELECT DISTINCT address FROM StoreObj WHERE address != ''  and inoutType='入库' ";
		@SuppressWarnings("unchecked")
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) { // 根据ligerForm下拉框数据格式，封装数据
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("key", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	// 查询成品属性
	@SuppressWarnings("unchecked")
	public List<MauProduct> getMauProduct(String proGgxh) {
		String hql = " FROM MauProduct where pro_ggxh = ? ";
		List<MauProduct> mau = dao.createQuery(hql, proGgxh).list();
		return mau;
	}

	// 获取保存叉车放置物品的实际位置
	@Deprecated
	public void saveAddress(String objrfid, String areaRfid) throws Exception {
		StoreObj obj = new StoreObj();
		String hl = " from StoreObj where rfidCode = ? ";
		obj = (StoreObj) dao.createQuery(hl, objrfid).list().get(0);
		String hql = " from StoreCoilSa where areaRfid = ? ";
		StoreCoilSa scs = (StoreCoilSa) dao.createQuery(hql, areaRfid).list()
				.get(0);
		obj.setAddress(scs.getArea_name());
		dao.updateByCon(obj, false);
	}

	/**
	 * 查询空闲的区域
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAreaAddress(String areaMaterType) throws Exception {
		String hql = "  from StoreCoilSa s where s.allowance >= 1 AND s.areaMaterType = ? AND s.areaRfid !='' AND s.areaRfid IS NOT NULL ";
		StoreCoilSa scs = (StoreCoilSa) dao.createQuery(hql, areaMaterType)
				.list().get(0);
		scs.setAllowance(scs.getAllowance() - 1);
		this.changeAllowance(scs);
		return scs.getArea_name();
	}

	/**
	 * 当出入库以后需要改变区域的余量
	 * 
	 * @throws Exception
	 */
	public void changeAllowance(StoreCoilSa scs) throws Exception {
		dao.updateByCon(scs, false);
	}

	/**
	 * 保存位置明细
	 * 
	 * @param address
	 *            区域位置
	 * @param coilRfid
	 *            线盘RFID
	 */
	public void saveStoreCoilSaDetail(String address, String coilRfid,
			String materialType) {
		StoreCoilSaDetail sd = new StoreCoilSaDetail();
		sd.setCoilRfid(coilRfid);
		sd.setProductType(materialType);
		// sd.setCreateBy(createBy);
		sd.setCreateDate(new Date());
		String hql = " from StoreCoilSa where area_name = ? ";
		StoreCoilSa scs = (StoreCoilSa) dao.createQuery(hql, address).list()
				.get(0);
		sd.setScsaId(scs.getId());
		dao.save(sd);
	}

	/**
	 * 根据APP扫描的RFID，查找带成品质检的数据
	 * 
	 * @param rfid
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("unchecked")
	public List<QualityMauReport> getQualityMauReport(String rfid) {
		String hql = " from QualityMauReport where semiAxis = ? ";
		List<QualityMauReport> list = dao.createQuery(hql, rfid).list();
		return list;

	}

	/**
	 * 根据检验报告编号，生产入库信息
	 * 
	 * @param reportCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<QualityMaterReport> getQualityMaterReport(String reportCode) {
		String hql = " from QualityMaterReport where reportCode = ? and testResult = '合格' ";
		List<QualityMaterReport> list = dao.createQuery(hql, reportCode).list();
		return list;
	}

	public void updateQualityMaterReport(QualityMaterReport mater)
			throws Exception {
		dao.updateByCon(mater, false);
	}

	/**
	 * 根据RFID查询，返回对象
	 * 
	 * @param rfidCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public StoreCoilSa getGgxhToFork(String rfidCode) {
		String hql = " from StoreCoilSa where areaRfid = ? ";
		List<StoreCoilSa> obj = dao.createQuery(hql, rfidCode).list();
		if (obj.size() < 1) {
			return null;
		}
		return obj.get(0);
	}

	@SuppressWarnings("unchecked")
	public StoreObj getStoreObj(String rfidCode) {
		String hql = " from StoreObj where rfidCode = ? ";
		List<StoreObj> obj = dao.createQuery(hql, rfidCode).list();
		if (obj.size() < 1) {
			return null;
		}
		return obj.get(0);
	}

	/**
	 * 查询RFID类型
	 * 
	 * @param rfidCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public MauRfid getMauRfid(String rfidCode) {
		String hql = " from MauRfid where appId = ? ";
		List<MauRfid> list = dao.createQuery(hql, rfidCode).list();
		if (list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * @Description: 当天的出入库记录
	 * @param inoutType
	 *            出入库类型
	 * @return
	 */
	public List<StoreObj> getTodayList(String inoutType) {
		String date = DateUtil.format(new Date(), "yyyy-MM-dd");
		String start = date + " 00:00:00";
		String end = date + " 23:59:59";
		String hql = "";
		if ("出库".equals(inoutType)) {
			hql = "from StoreObj where inoutType='" + inoutType
					+ "' and outDate>='" + start + "' and outDate<='" + end
					+ "'";
		} else if ("入库".equals(inoutType)) {
			hql = "from StoreObj where inoutType='" + inoutType
					+ "' and inDate>='" + start + "' and inDate<='" + end + "'";
		}
		// TODO Auto-generated method stub
		List<StoreObj> list = dao.createQuery(hql).list();
		return list;
	}

	/**
	 * 查詢当天的领料计划 --电子看板使用
	 * 
	 * @return
	 */
	public List<PlaMacTaskMateril>getDisplayList(){
		String time = DateUtil.format(new Date(), "yyyy-MM-dd")+" 23:59:59";
		String hql = "from PlaMacTaskMateril where  ptime <= '"+time+"' and state = '未领取' ";
		List<PlaMacTaskMateril> list = dao.createQuery(hql).list();
		return list;
	}
	
	/**
	 * 当天成品出库情况
	 * @return
	 */
	public List<StoreObj> getProDisplayList(){
		String time = DateUtil.format(new Date(), "yyyy-MM-dd")+" 23:59:59";
		String hql = " from StoreObj where createDate <= '"+time+"' and inoutType = '成品出库' ";
		@SuppressWarnings("unchecked")
		List<StoreObj> list = dao.createQuery(hql).list();
		return list;
	}

	/**
	 * 成品出库
	 * 
	 * @author JS
	 * @throws Exception 
	 */
	@Rollback
	public HashMap<String, Object> ProductOutStore(String rfidCode,
			String workCode, String inoutType) throws Exception {
		String msg = "";
		String ggxh_a = "";
		String unit = "";
		String name = ""; 
		String address="";
		Double actstock = 0.0;
		String[] rfid = rfidCode.split(",");
		for (int i = 0; i < rfid.length; i++) {
			List<MauCallForkliftRecord> call = new ArrayList<MauCallForkliftRecord>();
			MauCallForkliftRecord callfork = new MauCallForkliftRecord();
			List<StoreObj> store = this.getStoreObjDate(rfid[i]);
			if (store == null || store.size() == 0) {
				msg = "RFID不存在";
				return JsonWrapper.failureWrapperMsg("", msg);
			}
			for (StoreObj storeObj : store) {
				storeObj.setInoutType(inoutType);
				// 出库后，改变仓库库存总量
				List<StoreMrecord> stock = smDao.getStock(rfid[i]);
				//StoreMrecord sm = new StoreMrecord();
				if (stock == null || stock.size() == 0) {
					msg = "无效出库";
					return JsonWrapper.failureWrapperMsg(stock, msg);
				} else {
					this.removeByCon(stock.get(0));   //删除库存
					this.updateByCon(storeObj, false);; // 修改库存记录
					
					// TODO 呼叫叉车
					if (ggxh_a == "") {
						ggxh_a = stock.get(0).getMaterGgxh();
						
						if("领料出库".equals(inoutType)){
							PlaMacTaskMateril ptm = this.getMacAddress(workCode, ggxh_a);
							address = ptm.getMaccode();
							ptm.setState(PlaMacTaskMateril.IS_STATE);
							dao.updateByCon(ptm, false);
						}
						
						actstock += stock.get(0).getActStock();
						unit = stock.get(0).getUnit();
						name = stock.get(0).getMaterName();
					} else if (ggxh_a.equals(stock.get(0).getMaterGgxh())) {
						actstock += stock.get(0).getActStock();
					} else {
						callfork.setProGgxh(name + "(" + ggxh_a
								+ ")");
						callfork.setAccount(new BigDecimal(actstock));
						SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
						callfork.setCallTime(sf.format(new Date()));
						callfork.setUnit(unit);
						callfork.setStatus(MauCallForkliftRecord.NO_status);
						callfork.setCallAddress("仓库("+storeObj.getAddress()+")");
						callfork.setDestAddress(address);
						callfork.setRfidCode(rfidCode);
						callfork.setCallRfid(this.getAddressRfidByAddress(storeObj.getAddress()));
						callfork.setDestRfid(this.getAddressRfidByAddress(address));
						callfork.setCreateDate(new Date());
						dao.save(callfork);
						call.add(callfork);
						// TODO 将呼叫叉车消息放在消息队列中
						mauCallForklift
								.sendMessageTo(call);
						actstock = stock.get(0).getActStock();
						unit = stock.get(0).getUnit();
						ggxh_a = stock.get(0).getMaterGgxh();
						name = stock.get(0).getMaterName();
					}

					if (i == rfid.length - 1) {
						callfork.setProGgxh(name + "(" + ggxh_a
								+ ")");
						callfork.setAccount(new BigDecimal(actstock));
						SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
						callfork.setCallTime(sf.format(new Date()));
						callfork.setUnit(unit);
						callfork.setStatus(MauCallForkliftRecord.NO_status);
						callfork.setCallAddress("仓库("+storeObj.getAddress()+")");
						callfork.setDestAddress("目的地:"+address);
						callfork.setRfidCode(rfidCode);
						callfork.setCallRfid(this.getAddressRfidByAddress(storeObj.getAddress()));
						callfork.setDestRfid(this.getAddressRfidByAddress(address));
						callfork.setCreateDate(new Date());
						dao.save(callfork);
						call.add(callfork);
						// TODO 将呼叫叉车消息放在消息队列中
						mauCallForklift.sendMessageTo(call);
					}
					
				}
			}
			
		}
		return JsonWrapper.successWrapper(null, "备货完成,已呼叫叉车");
	}
	
	/**
	 * 废料出库
	 * 
	 * @author JS
	 */
	public HashMap<String, Object> WasteOutStore(String rfidCode,
			String workCode, String inoutType) {

		return null;
	}
	
	/**
	 * 领料出库
	 * @param rfidCode
	 * @param workCode
	 * @param inoutType
	 * @return
	 */
	public HashMap<String, Object> MaterOutStore(String rfidCode,
			String workCode, String inoutType) {
		String[] rfidSplit = rfidCode.split(",");
		String detailHql = "";
		if (rfidSplit.length > 0  ) {
			for (int i = 0;i<rfidSplit.length ;i++) {
				String rfidHql = "from StoreMrecord where rfid = '"+rfidSplit[i]+"'";
				List<StoreMrecord> storeList = dao.createQuery(rfidHql).list();
				if (storeList == null || storeList.size() == 0) {
					
				}else{
					//
					if("出库".equals(inoutType)){
					}
				}
				
			}
		}
		//查询物料情况
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public PlaMacTaskMateril getMacAddress(String wsCode,String ggxh){
		String hql = " from PlaMacTaskMateril where workcode = ? and materil = ? ";
		List<PlaMacTaskMateril> list = dao.createQuery(hql, wsCode,ggxh).list();
		if(list.size()==0 || list==null){
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public String getAddressRfidByAddress(String address){
		String hql = " from StoreCoilSa where area_name = ? ";
		List<StoreCoilSa> list = dao.createQuery(hql, address).list();
		if(list.size()<1 || list ==null){
			return "";
		}
		return list.get(0).getAreaRfid();
	}
	
	
	
}
