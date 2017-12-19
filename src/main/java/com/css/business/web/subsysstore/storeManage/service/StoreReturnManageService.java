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

import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.bean.StoreReturn;
import com.css.business.web.subsysstore.storeManage.dao.StoreObjManageDAO;
import com.css.business.web.subsysstore.storeManage.dao.StoreReturnManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeReturnManageService")
public class StoreReturnManageService extends
		BaseEntityManageImpl<StoreReturn, StoreReturnManageDAO> {
	@Resource(name = "storeReturnManageDAO")
	private StoreReturnManageDAO dao;
	
	@Resource(name = "storeObjManageDAO")
	private StoreObjManageDAO storeObjManageDAO;

	@Override
	public StoreReturnManageDAO getEntityDaoInf() {
		return dao;
	}
	
	//@Autowired
	@Resource(name = "storeCoilSaDetailManageService")
	private StoreCoilSaDetailManageService ser;
	
	/**
	 * @return  ser
	 */
	public StoreCoilSaDetailManageService getSer() {
		return ser;
	}

	@Autowired
	private StoreCoilSaManageService servicesa;
	/**
	 * @return  servicesa
	 */
	public StoreCoilSaManageService getServicesa() {
		return servicesa;
	}

	/**
	 * @Description: 退料列表查询
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public Page queryPageTable(Page page, Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(" from StoreReturn where 1 = 1");
		if (paramMap.get("start") != null && paramMap.get("start") != "") {
			sb.append(" and create_date >='" + paramMap.get("start") + "' ");
		}
		if (paramMap.get("end") != null && paramMap.get("end") != "") {
			sb.append(" and create_date <='" + paramMap.get("end") + "' ");

		}
		if (paramMap.get("material") != null && paramMap.get("material") != "") {
			sb.append(" and material = '" + paramMap.get("material") + "' ");
		}
		if (paramMap.get("model") != null && paramMap.get("model") != "") {
			sb.append(" and model ='" + paramMap.get("model") + "' ");
		}
		if (paramMap.get("corlour") != null && paramMap.get("corlour") != "") {
			sb.append(" and corlour ='" + paramMap.get("corlour") + "' ");
		}
		sb.append("  order by create_date DESC");
		Page pageQuery = null;
		pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(),
				page.getPagesize());

		return pageQuery;
	}

	/**
	 * @Description: 无条件分页查询
	 * @param page
	 */
	public Page pageQuery(Page page) {
		// TODO Auto-generated method stub
		String hql = " from StoreReturn ";
		Page pageQuery = dao.pageQuery(hql, page.getPageNo(),
				page.getPagesize());

		return pageQuery;

	}

	/**
	 * @Description:
	 * @param material
	 * @return
	 */
	public Map<String, List<String>> queryByMaterial(String material) {
		try {
			Map<String, String> paramMap = new HashMap<>();
		//	paramMap.put("material", material);
			queryByCondition(paramMap);
			String hql = "from StoreReturn where material = '"+material+"'";
		//	Query createQuery = dao.createQuery(hql, material);
			//	List<StoreReturn> list = createQuery.list();
			List<StoreReturn> listQuery = dao.listQuery(hql);
			Map<String, List<String>> chartsMap = new HashMap<String, List<String>>();
			List<String> xAxis = new ArrayList<>();
			List<String> series = new ArrayList<>();
			if (listQuery.size() > 0 && listQuery != null) {
				// 柱状图的X轴
				xAxis = new ArrayList<>();
				// 柱状图的Y轴
				series = new ArrayList<>();
				for (StoreReturn storeReturn : listQuery) {
					xAxis.add(storeReturn.getModel());
					series.add(storeReturn.getCount());
				}
			}
			chartsMap.put("xAxis", xAxis);
			chartsMap.put("series", series);
			return chartsMap;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description: 通过条件查询,为导出excel做准备
	 * @param paramMap
	 * @return
	 */
	public List<StoreReturn> queryByCondition(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(" from StoreReturn where 1 = 1");

		if (paramMap.get("start") != null && paramMap.get("start") != "") {
			sb.append(" and create_date >='" + paramMap.get("start") + "' ");
		}
		if (paramMap.get("end") != null && paramMap.get("end") != "") {
			sb.append(" and create_date <='" + paramMap.get("end") + "' ");
		}
		if (paramMap.get("material") != null && paramMap.get("material") != "") {
			sb.append(" and material = " + paramMap.get("material") + " ");
		}
		if (paramMap.get("model") != null && paramMap.get("model") != "") {
			sb.append(" and model =" + paramMap.get("model") + " ");
		}
		if (paramMap.get("corlour") != null && paramMap.get("corlour") != "") {
			sb.append(" and corlour =" + paramMap.get("corlour") + " ");
		}
		sb.append("  order by create_date DESC");
		List<StoreReturn> resultList = null;
		resultList = dao.queryByCondition(sb.toString());
	
		return resultList;
	}
	
	/**
	 * 
	 * @Description: 机台完成后,进行是否退料进行操作
	 * @param id
	 */
	@Autowired(required = false)
	public void borad(Integer id){
		//这些判断应该在几台管理那里就完成,余料这边只需要拉走余料就可以了
		//1.获得机台信息,查看是否有余料,
		//2.如果有余料,查询生产计划,判断下一生产计划是否使用当前余料
		//3,如果不使用,调用叉车将余料拉回库房
		String objGgxh = null; //规格
		String objSort = null;	//物料类型
		HttpServletRequest request = null;
		String token = null;
		String rfidCode = "sdsdf";	//rfid
		String qualityReportCode = null;	
		String batchCode = null;	//批次
		String acount = null;	//数量
		String msg = null;
		StoreObj obj = null;
		StoreReturn storeReturn = new StoreReturn();
		if (rfidCode != null && rfidCode.length() > 0) {
			List<StoreCoilSa> list = servicesa.queryAddress(objGgxh,
					objSort);
			String[] rfid = rfidCode.split(",");
			Double weight = Double.parseDouble(acount) / rfid.length;
			for (int i = 0; i < rfid.length; i++) {
				obj = new StoreObj();
				obj.setAcount(weight);
				obj.setBatchCode(batchCode);
				obj.setObjSort(objSort);
				obj.setObjGgxh(objGgxh);
				obj.setQualityReportCode(qualityReportCode);
				obj.setMaterialType("原材料");
				obj.setInoutType("入库");
				obj.setRfidCode(rfid[i]);
				obj.setCreateBy("JS");
				obj.setCreateDate(new Date());
				if (objSort != null && "胶料".equals(objSort)) {
					String address = list.get(i).getArea_name() + "-"
							+ list.get(i).getArea_position() + "-"
							+ list.get(i).getArea_type();
					obj.setAddress(address);
				} else {
					String address = list.get(i).getArea_name() + "-"
							+ list.get(i).getArea_position();
					obj.setAddress(address);
				}
				storeObjManageDAO.save(obj);
				ser.saveStoreColiSaD(list.get(i).getId(),
						obj.getRfidCode(), obj.getMaterialType());
			}
		}
	}
	
	
	/**
	 * 
	 * @Description: excel导出样式   
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
	 * 
	 * 导出excel表
	 * @throws Exception 
	 * */
	
	 public void exportExecl(
			HttpServletResponse response, Map<String, String> paramMap, String fileName
			) throws Exception {
		 //获得产寻结果
		 List<StoreReturn> pageList = queryByCondition(paramMap);
		//获取表格样式
		WritableCellFormat bodyFomat = this.getBodyFomat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format=sf.format(new Date());
		String titleName=fileName+"excel表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb=null;
		try {
			//创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[]{"序号","批次号", "材料", "RFID编号", "型号", 
					"颜色", "数量", "单位", "机台","叉车编号","存放位置","处理人","处理时间","备注"};
			//写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i,0,arrTitle[i],bodyFomat));
			}
			
			//Page pageList = this.getStoreRecordPageList(param,materialName,model,color,starttime,endtime);
			if(null!=pageList){
				//List<StoreReturn> data = pageList;
				int x=1;
				for (int i = 0; i < pageList.size(); i++) {
					StoreReturn vo = pageList.get(i);
					ws.addCell(new Label(0,x,vo.getId()+"",bodyFomat));
					ws.addCell(new Label(1,x,vo.getBatchcode(),bodyFomat));
					ws.addCell(new Label(2,x,vo.getMaterial(),bodyFomat));
					ws.addCell(new Label(3,x,vo.getRfidCode(),bodyFomat));
					ws.addCell(new Label(4,x,vo.getModel(),bodyFomat));
					ws.addCell(new Label(5,x,vo.getColour(),bodyFomat));
					ws.addCell(new Label(6,x,vo.getCount()+"",bodyFomat));
					ws.addCell(new Label(7,x,vo.getUnit(),bodyFomat));
					ws.addCell(new Label(8,x,vo.getBoard(),bodyFomat));
					ws.addCell(new Label(9,x,vo.getForklift_code(),bodyFomat));
					ws.addCell(new Label(10,x,vo.getPoistion().toString(),bodyFomat));
					ws.addCell(new Label(11,x,vo.getRemark(),bodyFomat));
					x++;
				}
			}
			wwb.write();

			response.setContentType("applicationnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ titleName + ".xls\"");
			outputStream.flush();  
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(null!=wwb){
				wwb.close();
			}
			if(null!=outputStream){
				
				outputStream.close();
			}
		}
		
	}

	


}
