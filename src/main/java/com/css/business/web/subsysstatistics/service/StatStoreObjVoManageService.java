package com.css.business.web.subsysstatistics.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.css.business.web.subsysstatistics.bean.StatCourse;
import com.css.business.web.subsysstatistics.bean.StatStoreObjVo;
import com.css.business.web.subsysstatistics.dao.StatCourseManageDAO;
import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * 
 * @Title: StatStoreObjVoManageService.java
 * @Package com.css.business.web.subsysstatistics.service
 * @Description: 统计仓库 只是需要一个容器 ,dao是不能用的,hql是不能用的
 * @author rb
 * @date 2017年8月2日 上午10:29:30
 * @company SMTC
 */
@Service("statStoreObjVoManageServide")
public class StatStoreObjVoManageService extends
		BaseEntityManageImpl<StatCourse, StatCourseManageDAO> {

	@Resource(name = "statCourseManageDAO")
	// @Autowired
	private StatCourseManageDAO dao;

	@Override
	public StatCourseManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * @Description: 获得所有的库存,出入库情况 ,原材料,成品,半成品
	 * @param map
	 *            条件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page getAllStore(Map<String, String> map, Page page) {
		// 获得
		String newSql = getNewSql(map);
		Page sqlQuery = page;
		try {
			sqlQuery = dao.pageSQLQueryVONoneDesc(newSql, page.getPageNo(),
					page.getPagesize(), new StatStoreObjVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlQuery;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param map
	 * @return
	 */
	public Page getActStore(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String sql = "SELECT " + " store_mrecord.mater_ggxh ggxh,"
				+ " store_mrecord.mater_name name2, "
				+ " store_mrecord.mater_color color, "
				+ " store_mrecord.unit unit, "
				+ " store_mrecord.act_stock allcount "
				+ " FROM store_mrecord where 1 = 1 ";
		StringBuilder sb = new StringBuilder(sql);
		if (map.get("type") != null && map.get("type") != "") {
			sb.append("and mater_type ='" + map.get("type") + "' ");
		}
		if (map.get("ggxh") != null && map.get("ggxh") != "") {
			sb.append("and mater_ggxh ='" + map.get("ggxh") + "' ");
		}
		if (map.get("color") != null && map.get("color") != "") {
			sb.append("and mater_color ='" + map.get("color") + "' ");
		}
		sb.append(" order by create_date DESC");
		@SuppressWarnings("unchecked")
		List<Object[]> list = dao.createSQLQuery(sql).list();
		Page pageSQLQuery = page;
		try {
			pageSQLQuery = dao.pageSQLQueryVONoneDesc(sb.toString(),
					page.getPageNo(), page.getPagesize(), new StatStoreObjVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageSQLQuery;
	}

	/**
	 * 
	 * @Description: 出入库销量
	 * @param map
	 * @param page
	 * @return
	 */
	private String getNewSql(Map<String, String> map) {
		// 基本查询 原材料,半成品,成品.默认原材料
		String type = map.get("type");
		if (type == null || type == "")
			type = "原材料";
		String outSql = "SELECT " + "obj.color color,"

		+ "obj.obj_name oname," + "obj.unit unit," + "obj.obj_ggxh ggxh,"
				+ "\"sum\" (obj.acount) su " + "FROM store_obj obj " + "WHERE "
				+ "obj.material_type = '" + type + "' "
				+ "AND obj.inout_type = '出库' ";
		String inSql = "SELECT " + "obj.color color," + "obj.obj_name iname,"
				+ "obj.obj_ggxh ggxh," + "obj.unit unit,"
				+ "\"sum\" (obj.acount) su " + "FROM store_obj obj " + "WHERE "
				+ "obj.material_type = '" + type + "' "
				+ "AND obj.inout_type = '入库' ";
		String inOutGroup = "GROUP BY obj.obj_ggxh, obj.unit ,obj_name, obj.color";

		StringBuilder insb = new StringBuilder(inSql);
		StringBuilder outsb = new StringBuilder(outSql);

		// 总库存没有时间选择
		if (map.get("start") != null && !"".equals(map.get("start"))) {
			insb.append("AND obj.create_date >= '" + map.get("start") + "' ");
			outsb.append("AND obj.create_date >= '" + map.get("start") + "' ");
		}
		if (map.get("end") != null && !"".equals(map.get("end"))) {
			insb.append("AND obj.create_date <= '" + map.get("end") + "' ");
			outsb.append("AND obj.create_date <= '" + map.get("end") + "' ");
		}
		insb.append(inOutGroup);
		outsb.append(inOutGroup);
		outSql = outsb.toString();
		inSql = insb.toString();

		String sql = "SELECT " + "obj.obj_ggxh ggxh," + "obj.obj_name name2,"
				+ "obj.color color," + "obj.unit unit," + "o.su  outcount,"
				+ "i.su  incount " + "FROM  " + " store_obj obj "
				+ " left join (" + outSql
				+ ") o on obj.obj_ggxh = o.ggxh AND obj.color = o.color "
				+ " left join (" + inSql
				+ ") i on obj.obj_ggxh = i.ggxh AND obj.color = i.color "
				+ " WHERE 1= 1  "

		;
		StringBuilder sb = new StringBuilder(sql);
		if (map.get("ggxh") != null && map.get("ggxh") != "") {
			sb.append("AND obj.obj_ggxh= '" + map.get("ggxh") + "' ");
		}
		if (map.get("color") != null && map.get("color") != "") {
			sb.append("AND obj.color = '" + map.get("color") + "' ");
		}
		if (map.get("name") != null && map.get("name") != "") {
			sb.append("AND obj_name = '" + map.get("name") + "' ");
		}
		if (map.get("type") != null && map.get("type") != "") {
			sb.append("AND material_type = '" + map.get("type") + "' ");
		}
		if (map.get("start") != null && !"".equals(map.get("start"))) {
			sb.append("AND obj.create_date >= '" + map.get("start") + "' ");
		}
		if (map.get("end") != null && !"".equals(map.get("end"))) {
			sb.append("AND obj.create_date <= '" + map.get("end") + "' ");
		}
		String group = "GROUP BY obj.obj_ggxh,obj.obj_name,obj.unit,o.su,i.su,obj.color";
		String newSql = sb.append(group).toString();

		return newSql;
	}

	/**
	 * 
	 * @Description: 截取页面展示的list
	 * @param list
	 *            初始list
	 * @param page
	 * @return
	 */
	private List<Object[]> getNewList(List<Object[]> list, Page page) {
		// 总页数
		page.setTotalCount(list.size() / page.getPagesize() + 1);
		// 上一次次从多少取
		Integer startSize = (page.getPageNo() - 1) * page.getPagesize();
		// 如果开始取的大于总数量,直接返回
		if (startSize > list.size()) {
			return null;
		}
		// 本次做多能取到什么位置
		Integer endSize = page.getPageNo() * page.getPagesize();
		// 需要的值
		List<Object[]> newList = new ArrayList<>();
		if (endSize > list.size()) {
			// 最后一条大于listsize,循环到list最后
			int count = 0;
			for (int i = startSize; i < list.size(); i++) {
				// newList.set(count, list.get(i));
				newList.add(count, list.get(i));
				count++;
			}
		} else {
			// 最后一条小于 listSize ,循环到endsize
			int count = 0;
			for (int i = startSize; i < endSize; i++) {
				newList.add(count, list.get(i));
				count++;
			}
		}
		return newList;
	}

	/**
	 * @Description: 查询某个材料的出入库详情
	 * @param page
	 * @param map
	 *            ggxh color start end
	 * @return
	 */
	public Page queryInOrOutDetail(Page page, Map<String, String> map,
			String signner) {
		// TODO Auto-generated method stub
		String sql = "SELECT " + "od.obj_ggxh ggxh," + "od.obj_name name2,"
				+ "od.acount count," + "od.color color," + "od.unit unit,"
				+ "od.quality_report_code report_code," + "od.address address,"
				+ "od.depot_sign signer," + "od.create_date createdate "
				+ "FROM " + "store_obj AS od where ";
		StringBuilder sb = new StringBuilder(sql);
		if (map.get("type") != null && !"".equals(map.get("type"))) {
			sb.append(" od.material_type= '" + map.get("type") + "' ");
		}
		if (map.get("start") != null && !"".equals(map.get("start"))) {
			sb.append(" and od.create_date >= '" + map.get("start") + "'");
		}
		if (map.get("end") != null && !"".equals(map.get("end"))) {
			sb.append(" and od.create_date <= '" + map.get("end") + "'");
		}
		if (map.get("inout") != null && !"".equals(map.get("inout"))) {
			sb.append(" and od.inout_type = '" + map.get("inout") + "'");
		}
		if (map.get("ggxh") != null && map.get("ggxh") != "") {
			sb.append(" and od.obj_ggxh='" + map.get("ggxh") + "'");
		}
		if (signner != null && !"".equals(signner)) {
			sb.append(" and od.depot_sign='" + signner + "'");
		}
		if (map.get("color") != null && map.get("color") != "") {
			sb.append(" and od.color='" + map.get("color") + "'");
		}

		sb.append(" order by create_date DESC");
		Page queryPage = page;
		try {
			queryPage = dao.pageSQLQueryVONoneDesc(sb.toString(),
					page.getPageNo(), page.getPagesize(), new StatStoreObjVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
	}

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

	private WritableCellFormat getTitalFomat() {
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
	 * 库存导出
	 * 
	 * @data:2017年8月11日
	 * @param map
	 * @param page
	 * @param response
	 * @param ggxh
	 * @param color
	 * @throws Exception
	 * @autor:wl
	 */
	public void exportExeclActStore(Map<String, String> map, Page page,
			HttpServletResponse response, String ggxh, String color)
			throws Exception {

		if (null != ggxh && !"".equals(ggxh)) {
			map.put("ggxh", ggxh);
		}
		if (null != color && !"".equals(color)) {
			map.put("color", color);
		}

		String type = map.get("type");
		// 获取表格样式
		WritableCellFormat bodyFomat = this.getBodyFomat();
		WritableCellFormat titlFomat = this.getTitalFomat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = format + type + "excel表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] { type + "规格", type + "名称",
					type + "颜色", "库存数量", "单位" };
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], titlFomat));
			}

			Page pageList = this.getActStore(map, page);
			if (null != pageList) {
				List<StatStoreObjVo> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					StatStoreObjVo vo = data.get(i);
					ws.addCell(new Label(0, x, null == vo.getGgxh() ? "" : vo
							.getGgxh(), bodyFomat));
					ws.addCell(new Label(1, x, null == vo.getName2() ? "" : vo
							.getName2(), bodyFomat));
					ws.addCell(new Label(2, x, null == vo.getColor() ? "" : vo
							.getColor(), bodyFomat));
					ws.addCell(new Label(3, x, null == vo.getAllcount() ? ""
							: vo.getAllcount() + "", bodyFomat));
					ws.addCell(new Label(4, x, null == vo.getUnit() ? "" : vo
							.getUnit(), bodyFomat));
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
	 * 出入库导出
	 * 
	 * @data:2017年8月11日
	 * @param map
	 * @param page
	 * @param response
	 * @param ggxh
	 * @param color
	 * @throws IOException
	 * @autor:wl
	 */
	public void exportExeclAllStore(Map<String, String> map, Page page,
			HttpServletResponse response, String ggxh, String color,
			String start, String end, String matName) throws Exception {

		map.put("ggxh", ggxh);
		map.put("color", color);
		map.put("start", start);
		map.put("end", end);
		map.put("name", matName);
		String type = map.get("type");

		// 获取表格样式
		WritableCellFormat bodyFomat = this.getBodyFomat();
		WritableCellFormat titlFomat = this.getTitalFomat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = format + type + "excel表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] { type + "规格", type + "名称",
					"入库数量", "出库数量", "单位" };
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], titlFomat));
			}

			Page pageList = this.getAllStore(map, page);
			if (null != pageList) {
				List<StatStoreObjVo> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					StatStoreObjVo vo = data.get(i);
					ws.addCell(new Label(0, x, null == vo.getGgxh() ? "" : vo
							.getGgxh(), bodyFomat));
					ws.addCell(new Label(1, x, null == vo.getName2() ? "" : vo
							.getName2(), bodyFomat));
					ws.addCell(new Label(2, x, null == vo.getIncount() ? ""
							: vo.getIncount() + "", bodyFomat));
					ws.addCell(new Label(3, x, null == vo.getOutcount() ? ""
							: vo.getOutcount() + "", bodyFomat));
					ws.addCell(new Label(4, x, null == vo.getUnit() ? "" : vo
							.getUnit(), bodyFomat));
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

	public List exportExcel(Map<String, String> map) {
		// TODO Auto-generated method stub
		String sql = "SELECT " + " store_mrecord.mater_ggxh ggxh,"
				+ " store_mrecord.mater_name name2, "
				+ " store_mrecord.mater_color color, "
				+ " store_mrecord.unit unit, "
				+ " store_mrecord.act_stock allcount "
				+ " FROM store_mrecord where 1 = 1 ";
		StringBuilder sb = new StringBuilder(sql);
		if (map.get("type") != null && map.get("type") != "") {
			sb.append("and mater_type ='" + map.get("type") + "' ");
		}
		if (map.get("ggxh") != null && map.get("ggxh") != "") {
			sb.append("and mater_ggxh ='" + map.get("ggxh") + "' ");
		}
		if (map.get("color") != null && map.get("color") != "") {
			sb.append("and mater_color ='" + map.get("color") + "' ");
		}
		sb.append(" order by create_date DESC");
		@SuppressWarnings("unchecked")
		List<Object[]> list = dao.createSQLQuery(sql).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAddress(String materName) {
		String hql = " from StoreMrecord where materName = ? ";
		List<StoreMrecord> list = dao.createQuery(hql, materName).list();
		List<String> str = new ArrayList<String>();
		for (StoreMrecord storeMrecord : list) {
			str.add(storeMrecord.getAddress());
		}
		return str;
	}

	public StoreMrecord getStoreMrecord(String address) {
		String hql = " from StoreMrecord where address = ? ";
		StoreMrecord sm = (StoreMrecord) dao.createQuery(hql, address).list()
				.get(0);
		return sm;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAreaType(String materGgxh) {
		String hql = " from StoreMrecord where materGgxh = ? ";
		List<StoreMrecord> list = dao.createQuery(hql, materGgxh).list();
		List<String> ls = new ArrayList<String>();
		String area = "";
		for(StoreMrecord sm : list){
			area = sm.getAddress();
			ls.add(area);
		}
		return ls;
	}

	/**
	 * 查询仓库位置信息
	 * 
	 * @param materGgxh
	 * @param areaType
	 * @param p
	 * @return
	 * @author JS
	 */
	public Page getStoreMrecord(String materGgxh, String areaType,String materColor, Page p) {
		String hql = " from StoreMrecord where 1=1 ";
			if (materGgxh != null && !"".equals(materGgxh)) {
				hql += "and materGgxh = '" + materGgxh + "' ";
			}
			if (areaType != null && !"".equals(areaType)) {
				String[] areas = areaType.split("-");
				if(areas.length==1){
					hql += "and address = '" + areas[0] + "' ";
				}else{
					hql += "and address between '" + areas[0] + "' and '"+areas[1]+"' ";
				}
			}
			if(materColor != null && !"".equals(materColor)){
				hql +=" and materColor = '"+materColor+"' ";
			}
		
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getMaterGgxh() {
		String hql = " SELECT DISTINCT materGgxh from StoreMrecord ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map[] getMaterColor() {
		String hql = " SELECT DISTINCT materColor from StoreMrecord where materColor is NOT NULL ";
		List<Object> list = dao.createQuery(hql).list();
		Map[] str = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					map.put("text", list.get(i).toString());
				} else {
					map.put("id", i + "");
				}
			}
			str[i] = map;
		}
		return str;
	}

}
