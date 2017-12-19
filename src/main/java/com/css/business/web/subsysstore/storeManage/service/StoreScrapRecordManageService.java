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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.bean.StoreScrapRecord;
import com.css.business.web.subsysstore.storeManage.dao.StoreScrapRecordManageDAO;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.dao.SysAttachmentManageDAO;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.controller.FileUploadAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeScrapRecordManageService")
public class StoreScrapRecordManageService extends
		BaseEntityManageImpl<StoreScrapRecord, StoreScrapRecordManageDAO> {
	@Resource(name = "storeScrapRecordManageDAO")
	private StoreScrapRecordManageDAO dao;

	//@Autowired
	@Resource(name="storeObjManageService")
	private StoreObjManageService objService;

	@Resource(name = "sysAttachmentManageDAO")
	private SysAttachmentManageDAO adao;

	@Override
	public StoreScrapRecordManageDAO getEntityDaoInf() {
		return dao;
	}

	public Page getStoreRecordPageList(Page param, String materialName,
			String model, String color, String starttime, String endtime) {

		long total = 0L;
		List<StoreScrapRecord> list = new ArrayList<>();
		Page page =new Page();
		StringBuilder sql = new StringBuilder(
				" from StoreScrapRecord o where 1=1");
/*		StringBuilder hql = new StringBuilder(
				"select count(o.id) from StoreScrapRecord o where 1=1 ");
*/		if (null != materialName && !"".equals(materialName)) {
			sql.append("  and  o.materialName='" + materialName + "'");
			//hql.append("  and  o.materialName='" + materialName + "'");
		}
		if (null != model && !"".equals(model)) {
			sql.append("  and  o.model like '%" + model + "%'");
		}
		if (null != color && !"".equals(color)) {
			sql.append("  and  o.color like '%" + color + "%'");
		}
		if (null != starttime && !"".equals(starttime)) {
			sql.append("  and  o.handleDate>='" + starttime + "'");
			//hql.append("  and  o.handleDate>='" + starttime + "'");
		}
		if (null != endtime && !"".equals(endtime)) {
			sql.append("  and  o.handleDate<='" + endtime + "'");
			//hql.append("  and  o.handleDate<='" + endtime + "'");
		}
		sql.append("   order by  id desc");
		try {
			//total = dao.getRecordTotalCount(hql);
			page = dao.getRecordList(sql,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Page page = new Page(param.getPageNo(), total, param.getPagesize(),
				list);*/
		return page;
	}

	public String getTotalMsg(HttpServletRequest request) {
		String msg = "";
		StringBuilder sql = new StringBuilder(
				"select o.materialName,sum(o.amount) as amount,o.unit    from StoreScrapRecord  o where o.status=?   GROUP BY materialName,unit");
		try {
			msg = dao.getTotalMsg(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/*
	 * public String uploadReportService(MultipartFile[] files,
	 * HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * String uri="";
	 * 
	 * try { String path=request.getServletContext().getContextPath();
	 * SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd"); String format
	 * =sf.format(new Date()); File fileFolder = new
	 * File(path+File.separator+format);
	 * 
	 * if(!fileFolder.exists()){ fileFolder.mkdir(); } if(files.length>0){
	 * List<String> type =new ArrayList<>(); type.add(".jpg");
	 * type.add(".jpeg"); type.add(".png"); type.add(".bmp");
	 * 
	 * for (MultipartFile multipartFile : files) { String originalFilename =
	 * multipartFile.getOriginalFilename(); int lastIndex =
	 * originalFilename.indexOf("."); String fileType
	 * =originalFilename.substring(lastIndex); String realName =
	 * format+fileType; if(!"".equals(fileType)){ if(type.contains(fileType)){
	 * 
	 * File secondFile = new File(fileFolder,realName);
	 * if(!secondFile.exists()){ secondFile.mkdir(); } //获取uri存入到数据库 String
	 * UrlPath = secondFile.getPath(); String uriPath =
	 * UrlPath.substring(UrlPath .indexOf("\\picstrorage")); uriPath =
	 * uriPath.substring(1); uri=UrlPath; multipartFile.transferTo(secondFile);
	 * } }
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return uri; }
	 */

	public void saveEnt(HttpServletRequest request, SysUser user,
			StoreScrapRecord ent) throws Exception {
		Integer id = ent.getId();
		ent.setCreateBy(user.getAccount());
		ent.setCreateDate(new Date());
		if (id == null)
			this.save(ent);
		else
			this.updateByCon(ent, false);
	}

	@Transactional(readOnly = false)
	public void saveFormAndAttach(HttpServletRequest request, SysUser user,
			StoreScrapRecord ent, String uploadurl, String uploadurlorigname)
			throws Exception {

		// 保存实体
		saveEnt(request, user, ent);

		String[] uploadurlarr = null;
		String[] uploadurlorignamearr = null;
		if (!StringUtil.isEmpty(user.getToken())) {
			List<String> uploadurlList = new ArrayList<String>();
			List<String> uploadurlorignameList = new ArrayList<String>();
			FileUploadAction upload = new FileUploadAction();
			HashMap<String, Object> map = upload.uploadForApp(request);// 调用上传文件方法，返回上传文件的地址和文件名
			Integer fileNumber = (Integer) map.get("fileNumber");// 获取上传文件的个数
			fileNumber = fileNumber == null ? 0 : fileNumber;
			for (int i = 0; i < fileNumber; i++) {
				int x = i + 1;
				boolean success = (boolean) map.get("success_" + x);
				uploadurlorigname = (String) map.get("filename_" + x);
				uploadurl = (String) map.get("filenameindisk_" + x);
				if (success == true) {
					if (!StringUtil.isEmpty(uploadurl)
							&& !StringUtil.isEmpty(uploadurlorigname)) {
						uploadurlList.add(uploadurl);
						// 手机上传多图的时候只调用一次上传接口，所以跟web端不一样，不会返回两个带‘，’分割的字符串；
						uploadurlorignameList.add(uploadurlorigname);
					}
				}
			}
			if (uploadurlList.size() > 0) {
				uploadurlarr = uploadurlList.toArray(new String[1]);// 返回一个包含所有对象的指定类型的数组

				// 手机端上传照片（不是图片），需要更新到贫困户头像中
				String hzphoto = uploadurlarr[0];
				if (hzphoto != null && hzphoto.length() > 0) {
					ent.setExaminReportUrl(hzphoto);
				}
			}
			if (uploadurlorignameList.size() > 0)
				uploadurlorignamearr = uploadurlorignameList
						.toArray(new String[1]);
		} else {
			if (!StringUtil.isEmpty(uploadurl)
					&& !StringUtil.isEmpty(uploadurlorigname)) {
				uploadurlarr = uploadurl.split(",");
				uploadurlorignamearr = uploadurlorigname.split(",");
			}
		}
		if (uploadurlarr != null) {
			Integer id = ent.getId();
			if (id != null)
				this.adao.deleteByBid(ent.getId(), this.getClassName());

			for (int i = 0; i < uploadurlarr.length; i++) {
				Sys_attachment att = new Sys_attachment(
						uploadurlorignamearr[i], uploadurlarr[i]);
				att.setBid(ent.getId());
				att.setCreate_by(user.getAccount());
				att.setCreate_date(new Date());
				this.saveAttachment(att);
			}
		}

	}

	/**
	 * 查询当前序号的图片
	 * */
	public String getReportUrlById(Integer id) {
		String url = "";
		StoreScrapRecord record = dao.findUniqueBy("id", id);
		if (null != record) {
			url = record.getExaminReportUrl();
		}

		return url;
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
	 * 
	 * 导出excel表
	 * 
	 * @throws Exception
	 * */
	public void exportExecl(HttpServletRequest request,
			HttpServletResponse response, Page param, String materialName,
			String model, String color, String starttime, String endtime)
			throws Exception {

		// 获取表格样式
		WritableCellFormat bodyFomat = this.getBodyFomat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = format + "报废统计excel表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] { "批次号", "材料", "RFID编号", "型号",
					"颜色", "数量", "单位", "物料位置", "处理人", "申请时间", "处理时间", "处理方式", "存放位置" };
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], bodyFomat));
			}

			Page pageList = this.getStoreRecordPageList(param, materialName,
					model, color, starttime, endtime);
			if (null != pageList) {
				List<StoreScrapRecord> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					StoreScrapRecord vo = data.get(i);
					ws.addCell(new Label(0, x, null==vo.getBatchCode()?"":vo.getBatchCode(), bodyFomat));
					ws.addCell(new Label(1, x, null==vo.getMaterialName()?"":vo.getMaterialName(), bodyFomat));
					ws.addCell(new Label(2, x, null==vo.getRfidCode()?"":vo.getRfidCode(), bodyFomat));
					ws.addCell(new Label(3, x, null==vo.getModel()?"":vo.getModel(), bodyFomat));
					ws.addCell(new Label(4, x, null==vo.getColor()?"":vo.getColor(), bodyFomat));
					ws.addCell(new Label(5, x, null==vo.getAmount()?"":vo.getAmount()+"", bodyFomat));
					ws.addCell(new Label(6, x, null==vo.getUnit()?"":vo.getUnit(), bodyFomat));
					ws.addCell(new Label(7, x, null==vo.getMaterialRalation()?"":vo.getMaterialRalation(),
							bodyFomat));
					ws.addCell(new Label(8, x, null==vo.getHandler()?"":vo.getHandler(), bodyFomat));
					ws.addCell(new Label(9, x, null==vo.getApplyDate()?"":vo.getApplyDate()+"",bodyFomat));
					ws.addCell(new Label(10, x, null==vo.getHandleDate()?"":vo.getHandleDate()+"",
							bodyFomat));
					ws.addCell(new Label(11, x, null==vo.getHandleIdea()?"":vo.getHandleIdea(), bodyFomat));
					ws.addCell(new Label(12, x, null==vo.getHandleAfterPosition()?"":vo.getHandleAfterPosition(),
							bodyFomat));
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
				"select value  from SysCommdic where clas=?");

		list = dao.getSelectOption(sql.toString());
		return list;
	}

	/**
	 * 
	 * 接收手机APP传入的消息 并根据RFID号查询出材料的入库信息
	 * */
	public List<StoreScrapRecord> saveFromApp(HttpServletRequest request,
			SysUser user, String rfidAndRemark) {
		boolean flag = false;
		List<StoreScrapRecord> list = new ArrayList<>();
		try {
			Map<String, Object> map = this.getRfidCodeByToken(rfidAndRemark);
			for (String rfidcode : map.keySet()) {
				// 根据RFID编号调用入库信息查询对应原材料的入库信息
				String str = (String) map.get(rfidcode);// 备注信息
				List<StoreObj> data = objService.queryStoreRfid(rfidcode);
				if (data.size() > 0) {
					StoreObj obj = data.get(0);
					// 通知质检员 备注信息 如果质检员同意该备注就以该备注为准，否则就更改
					StoreScrapRecord storeScrapRecord = this.transformObject(
							obj, str);
					list.add(storeScrapRecord);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	// 将原材料入库信息对象转换成报废对象
	private StoreScrapRecord transformObject(StoreObj obj, String remark) {
		StoreScrapRecord storeScrapRecord = new StoreScrapRecord();
		storeScrapRecord.setAmount(obj.getAcount());
		storeScrapRecord.setBatchCode(obj.getBatchCode());
		storeScrapRecord.setColor(obj.getColor());
		storeScrapRecord.setCreateDate(obj.getInDate());
		storeScrapRecord.setRfidCode(obj.getRfidCode());
		storeScrapRecord.setMaterialName(obj.getObjSort());
		storeScrapRecord.setModel(obj.getObjGgxh());
		storeScrapRecord.setUnit(obj.getUnit());
		storeScrapRecord.setMaterialRalation(obj.getDepotSign());
		storeScrapRecord.setRemark(remark);
		return storeScrapRecord;
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

	public List<StoreScrapRecord> getScrapStock() {
		String hql = " from StoreScrapRecord s order by s.handleDate DESC ";
		@SuppressWarnings("unchecked")
		List<StoreScrapRecord> list = dao.createQuery(hql).list();
		return list;
	}
	/**
	 * 批量删除
	 *@data:2017年7月26日
		@param ids
		@return
		@autor:wl
	 */
	public boolean deleteRecordByids(String ids) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			String sql ="delete from StoreScrapRecord  where id=?";
			if(null!=ids&&!"".equals(ids)){
				String[] idTotal = ids.split(",");
				for (String id : idTotal) {
					flag=dao.deleteById(sql,Integer.parseInt(id));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return flag;
	}

/*	public boolean saveProductManageService(StoreScrapRecord mpm) {
		return dao.saveProductManageDao(mpm);
	}

	public boolean updateProductManageService(Object obj) {
		return dao.updateProductManageDao(obj);
		
	}*/
		/**
		 * 根据id获取需要编辑的值
		 *@data:2017年8月2日
		@param id
		@return
		@autor:wl
		 */
	public StoreScrapRecord getStoreScrapRecordById(Integer id) {
		// TODO Auto-generated method stub
		StoreScrapRecord storeScrapRecord=null;
		try {
			String hql="from StoreScrapRecord where id=?";
			List<StoreScrapRecord> list=dao.getStoreScrapRecordById(hql,id);
			if(null!=list&&list.size()>0){
				storeScrapRecord=list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return storeScrapRecord;
	}

		/**   
		 * @Description: TODO(这里用一句话描述这个方法的作用)   
		 * @return         
		 */ 
		public List<Map<String, String>> getClassify(String state) {
			// TODO Auto-generated method stub
			String hql = "select materialName,sum(amount) from StoreScrapRecord  where status ='"+state+"' group by materialName";
			@SuppressWarnings("unchecked")
			List<Object[]> list = dao.createQuery(hql).list();
			List<Map<String, String>>resultList = new ArrayList<>();
			for (Object[] objs : list) {
				Map<String,String>map = new HashMap<String, String>();
			//	map.put(objs[0].toString(), objs[1]==null?"0":objs[2].toString());
				map.put("type", "报废");
				map.put("key", objs[0].toString());
				map.put("value", objs[1]==null?"0":objs[1].toString());
				resultList.add(map);
			}
			return resultList;
		}

}
