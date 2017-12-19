package com.css.business.web.subsysstore.storeManage.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraBomTheory;
import com.css.business.web.subsyscraft.craManage.service.CraBomTheoryManageService;
import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.bean.MauProduct;
import com.css.business.web.subsysmanu.bean.MauRfid;
import com.css.business.web.subsysmanu.mauManage.service.MauCallForkliftRecordManageService;
import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanMaterManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaUnitConMaterManageService;
import com.css.business.web.subsysquality.bean.QualityMaterReport;
import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.storeManage.bean.StoreApp;
import com.css.business.web.subsysstore.storeManage.dao.StoreObjManageDAO;
import com.css.business.web.subsysstore.storeManage.service.StoreCoilSaDetailManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreCoilSaManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreMrecordManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreObjManageService;
import com.css.business.web.sysManage.service.SysNoticeManageService;
import com.css.common.util.PropertyFileUtil;
import com.css.common.web.apachemq.bean.CommandVO;
import com.css.common.web.apachemq.handle.test.MqttBroker;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;

@Controller
@RequestMapping("/storeObjManageAction")
public class StoreObjManageAction extends
		BaseSpringSupportAction<StoreObj, StoreObjManageService> {

	// @Autowired
	private StoreObjManageService service;

	@Resource(name = "storeCoilSaManageService")
	private StoreCoilSaManageService servicesa;

	@Resource(name = "storeObjManageDAO")
	private StoreObjManageDAO dao;

	@Resource(name = "storeCoilSaDetailManageService")
	private StoreCoilSaDetailManageService ser;

	@Resource(name = "plaCourseManageService")
	private PlaCourseManageService pcservice;

	@Resource(name = "sysNoticeManageService")
	private SysNoticeManageService snservice;

	@Resource(name = "plaUnitConMaterManageService")
	private PlaUnitConMaterManageService puservice;

	@Resource(name = "craBomTheoryManageService")
	private CraBomTheoryManageService cbservice;

	@Resource(name = "storeMrecordManageService")
	private StoreMrecordManageService smsser;
	@Resource(name = "plaMachinePlanMaterManageService")
	private PlaMachinePlanMaterManageService plaMachinePlanMaterManageService;
	@Resource(name = "mauCallForkliftRecordManageService")
	private MauCallForkliftRecordManageService mauCallForkliftRecordManageService;

	private PropertyFileUtil pfu = new PropertyFileUtil();

	@Override
	public StoreObjManageService getEntityManager() {
		return service;
	}

	public StoreObjManageService getService() {
		return service;
	}

	@Resource(name = "storeObjManageService")
	public void setService(StoreObjManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeObjEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeObjForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeObjList";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreObjCuChart" })
	public String toStoreObjCuChart(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeObjCuChart";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreObjGlueChart" })
	public String toStoreObjGlueChart(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeObjGlueChart";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreObjAlChart" })
	public String toStoreObjAlChart(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeObjAlChart";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "getChartDate" })
	@ResponseBody
	public Map<String, List<String>> getChartDate(String objSort) {
		Map<String, List<String>> map = service.getChart(objSort);
		return map;
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "getStoreObjDate" })
	@ResponseBody
	public Page getStoreObjDate(HttpServletRequest request, Integer id, Page p,
			String time1, String time2, String batchCode, String objSort,
			String objGgxh, String address, String rfidCode) {
		try {
			Page page = service.queryStoreObj(time1, time2, batchCode, objSort,
					objGgxh, address, rfidCode, p);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreObjTable" })
	public String toStoreObjTable(HttpServletRequest request, Integer id,
			String time1, String time2, String batchCode, String objSort,
			String objGgxh, String address) {
		request.setAttribute("time1", time1);
		request.setAttribute("time2", time2);
		request.setAttribute("batchCode", batchCode);
		request.setAttribute("objSort", objSort);
		request.setAttribute("objGgxh", objGgxh);
		request.setAttribute("address", address);
		return "storeManage/storeObjTable";
	}

	/**
	 * 
	 * @Description: app传上来的各类参数
	 * @Todo 入库
	 * @param request
	 * @param id
	 * @param token
	 * @param rfidCode
	 * @param qualityReportCode
	 *            质检报告单号
	 * @param objSort
	 *            物料类型
	 * @param objGgxh
	 *            规格
	 * @param batchCode
	 *            批次
	 * @param acount
	 *            总重量
	 * @return
	 * 
	 * @author JS
	 */
	@RequestMapping({ "getStoreObj" })
	@ResponseBody
	public HashMap<String, Object> getStoreObj(HttpServletRequest request,
			Integer id, String token, String rfidCode,
			String qualityReportCode, String materialType) {
		// SysUser user = SessionUtils.getUser(request);
		StoreObj obj = null;
		String msg = "";
		String ggxh_a = "";
		String unit = "";
		String name = ""; 
		Double stock = 0.0;
		try {
				if (rfidCode != null && rfidCode.length() > 0) {

					if ("原材料".equals(materialType)) {
						String[] rfid = rfidCode.split(",");
						List<MauCallForkliftRecord> call = new ArrayList<MauCallForkliftRecord>();
						MauCallForkliftRecord callfork = new MauCallForkliftRecord();
						for (int i = 0; i < rfid.length; i++) {
							List<StoreObj> ll = service.getStoreObjDate(rfid[i]);
							if(ll!=null&&ll.size()>0){
								return JsonWrapper.failureWrapperMsg(ll, "该RFID已使用!");
							}
							obj = new StoreObj();
							Date createDate = new Date();
							List<QualityMaterReport> materReport = service
									.getQualityMaterReport(qualityReportCode);
							if (materReport == null || materReport.size() < 1) {
								// 根据检验报告编号查询到的产品没有对应的报告则视为不能入库
								msg = "该产品不合格，或者还没有质检";
								return JsonWrapper.failureWrapperMsg(obj, msg);
							}
							for (QualityMaterReport qualityMaterReport : materReport) {
								// TODO
								Double weight = (double) (qualityMaterReport
										.getMaterAmount() / rfid.length);
								obj.setWeight(weight);
								obj.setObjName(qualityMaterReport
										.getMaterName());
								obj.setBatchCode(qualityMaterReport
										.getMaterBatch());
								obj.setObjSort(qualityMaterReport
										.getMaterType());
								obj.setObjGgxh(qualityMaterReport
										.getMaterGgxh());
								obj.setQualityReportCode(qualityMaterReport
										.getReportCode());
								obj.setMaterialType(materialType);
								obj.setInoutType("入库");
								obj.setUnit("km");
								obj.setRfidCode(rfid[i]);
								obj.setCreateDate(createDate);
								obj.setInDate(createDate);
								obj.setSupplyAgent(qualityMaterReport
										.getMaterFirm());
								// TODO 当材料类型为铜杆或者铝杆时，需要计算库存中 的长度
								if ("铜杆".equals(qualityMaterReport
										.getMaterType())
										|| "铝杆".equals(qualityMaterReport
												.getMaterType())) {
									List<CraBomTheory> list = cbservice
											.getCraBomTheory(qualityMaterReport
													.getMaterGgxh());
									obj.setAcount(weight
											* Double.parseDouble(list.get(0)
													.getParamValue()));
								}
								qualityMaterReport
										.setIsCome(QualityMaterReport.IS_COME);
								service.SaveStoreObj(obj);
								service.updateQualityMaterReport(qualityMaterReport);

								StoreMrecord sm = new StoreMrecord();
								sm.setActStock((double) qualityMaterReport
										.getMaterAmount());
								sm.setCreateDate(createDate);
								sm.setMaterName(qualityMaterReport
										.getMaterName());
								sm.setBatchCode(qualityMaterReport
										.getMaterBatch());
								sm.setMaterGgxh(qualityMaterReport
										.getMaterGgxh());
								sm.setMaterType(materialType);
								sm.setRfid(rfid[i]);
								sm.setUnit("kg");
								smsser.save(sm);

								// 形成叉车呼叫记录
								if (ggxh_a == "") {
									ggxh_a = sm.getMaterGgxh();
									stock += sm.getActStock();
									unit = sm.getUnit();
									name = qualityMaterReport.getMaterName();
								} else if (ggxh_a.equals(sm.getMaterGgxh())) {
									stock += sm.getActStock();
								} else {
									callfork.setProGgxh(name + "(" + ggxh_a
											+ ")");
									callfork.setAccount(new BigDecimal(stock));
									SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
									callfork.setCallTime(sf.format(new Date()));
									callfork.setUnit(unit);
									callfork.setStatus(MauCallForkliftRecord.NO_status);
									callfork.setCallAddress("仓库(原材料入库)");
									callfork.setDestAddress("");
									callfork.setRfidCode(rfidCode);
									callfork.setCreateDate(new Date());
									dao.save(callfork);
									call.add(callfork);
									// TODO 将呼叫叉车消息放在消息队列中
									mauCallForkliftRecordManageService
											.sendMessageTo(call);
									stock = sm.getActStock();
									unit = sm.getUnit();
									ggxh_a = sm.getMaterGgxh();
									name = qualityMaterReport.getMaterName();
								}

								if (i == rfid.length - 1) {
									callfork.setProGgxh(name + "(" + ggxh_a
											+ ")");
									callfork.setAccount(new BigDecimal(stock));
									SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
									callfork.setCallTime(sf.format(new Date()));
									callfork.setUnit(unit);
									callfork.setStatus(MauCallForkliftRecord.NO_status);
									callfork.setCallAddress("仓库(原材料入库)");
									callfork.setDestAddress("");
									callfork.setRfidCode(rfidCode);
									callfork.setCreateDate(new Date());
									dao.save(callfork);
									call.add(callfork);
									// TODO 将呼叫叉车消息放在消息队列中
									mauCallForkliftRecordManageService
											.sendMessageTo(call);
								}

							}
						}

					} else if ("成品".equals(materialType)) {
						String[] rfid = rfidCode.split(",");
						// Double weight = (double) rfid.length;
						List<MauCallForkliftRecord> call = new ArrayList<MauCallForkliftRecord>();
						MauCallForkliftRecord callfork = new MauCallForkliftRecord();
						for (int i = 0; i < rfid.length; i++) {
							obj = new StoreObj();
							List<QualityMauReport> mauReport = service
									.getQualityMauReport(rfid[i]);
							// TODO 赋值
							if (mauReport == null || mauReport.size() < 1) {
								// 根据检验报告编号查询到的产品没有对应的报告则视为不能入库
								msg = "该产品不合格，或者还没有质检";
								return JsonWrapper.failureWrapperMsg(obj, msg);
							}
							if (mauReport != null && mauReport.size() > 0) {
								for (QualityMauReport qualityMauReport : mauReport) {
									Double weight = qualityMauReport
											.getWeight();
									Date createDate = new Date();
									// obj.setColor(color);
									obj.setAcount(weight);
									obj.setRfidCode(rfid[i]);
									obj.setBatchCode(qualityMauReport
											.getBatchCode());
									obj.setQualityReportCode(qualityMauReport
											.getReportCode());
									obj.setMaterialType(materialType);
									// TODO 查询成品 得到成品属性    
									List<MauProduct> mauList = service
											.getMauProduct(qualityMauReport
													.getProGgxh());
									if (mauList == null || mauList.size() < 1) {
										msg = "产品规格型号不正确!(如需新增规格型号,请至产品管理)";
										return JsonWrapper.failureWrapperMsg(
												obj, msg);
									}
									obj.setObjSort(mauList.get(0).getPro_code());
									obj.setObjName(mauList.get(0).getPro_name());
									obj.setObjGgxh(mauList.get(0).getPro_ggxh());
									obj.setInoutType("入库");
									obj.setUnit("轴");
									obj.setCreateDate(createDate);
									obj.setInDate(createDate);
									qualityMauReport
											.setIsCome(QualityMauReport.IS_COME);
									service.SaveStoreObj(obj);
									service.saveStoreCoilSaDetail(
											obj.getAddress(), rfid[i],
											materialType);
									service.updateQualityMauReport(qualityMauReport);
									StoreMrecord sm = new StoreMrecord();
									sm.setActStock(weight);
									sm.setCreateDate(createDate);
									sm.setMaterName(qualityMauReport
											.getProGgxh());
									sm.setMaterGgxh(qualityMauReport
											.getProGgxh());
									sm.setMaterType(materialType);
									sm.setUnit("kg");
									sm.setRfid(rfid[i]);
									smsser.save(sm);
									
									// 形成叉车呼叫记录
									if (ggxh_a == "") {
										ggxh_a = sm.getMaterGgxh();
										stock += sm.getActStock();
										unit = sm.getUnit();
										name = qualityMauReport.getProGgxh();
									} else if (ggxh_a.equals(sm.getMaterGgxh())) {
										stock += sm.getActStock();
									} else {
										callfork.setProGgxh(name + "(" + ggxh_a
												+ ")");
										callfork.setAccount(new BigDecimal(
												stock));
										SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
										callfork.setCallTime(sf.format(new Date()));
										callfork.setUnit(unit);
										callfork.setStatus(MauCallForkliftRecord.NO_status);
										callfork.setCallAddress("仓库(成品入库)");
										callfork.setDestAddress("");
										callfork.setRfidCode(rfidCode);
										callfork.setCallRfid("");
										callfork.setDestRfid("");
										callfork.setCreateDate(new Date());
										dao.save(callfork);
										call.add(callfork);
										// TODO 将呼叫叉车消息放在消息队列中
										mauCallForkliftRecordManageService
												.sendMessageTo(call);
										stock = sm.getActStock();
										unit = sm.getUnit();
										ggxh_a = sm.getMaterGgxh();
										name = qualityMauReport.getProGgxh();
									}

									if (i == rfid.length - 1) {
										callfork.setProGgxh(name + "(" + ggxh_a
												+ ")");
										callfork.setAccount(new BigDecimal(
												stock));
										SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
										callfork.setCallTime(sf.format(new Date()));
										callfork.setUnit(unit);
										callfork.setStatus(MauCallForkliftRecord.NO_status);
										callfork.setCallAddress("仓库(成品入库)");
										callfork.setDestAddress("");
										callfork.setRfidCode(rfidCode);
										callfork.setCallRfid("");
										callfork.setDestRfid("");
										callfork.setCreateDate(new Date());
										dao.save(callfork);
										call.add(callfork);
										// TODO 将呼叫叉车消息放在消息队列中
										mauCallForkliftRecordManageService
												.sendMessageTo(call);
									}
								}
							}

						}

					}
					msg = "";
					StoreApp app = new StoreApp();
					app.setObjName(obj.getObjName() + "(" + obj.getObjGgxh()
							+ ")");
					return JsonWrapper.successWrapper(obj, msg);
				} else {
					msg = "RFID不能为空";
					return JsonWrapper.failureWrapperMsg(obj, msg);
				}

		} catch (Exception e) {
			e.printStackTrace();
			msg = e.toString();
			return JsonWrapper.failureWrapperMsg(null, msg);
		}
	}

	/**
	 * @author zeng bin
	 * @param request
	 * @param id
	 * @return 返回到领料管理
	 * @date:2017-06-28
	 */
	@RequestMapping({ "toStorePickingMaterial" })
	public String toStorePickingMaterial(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storePickingMaterial";
	}

	@RequestMapping(value = { "/loadStorePickingMaterialManage" })
	@ResponseBody
	public HashMap<String, Object> loadStorePickingMaterialManage(String page,
			String pagesize) {
		return service.getStorePickingMaterialManageService(page, pagesize);
	}

	@RequestMapping(value = { "/getObjSortFromSysCommedic" })
	@ResponseBody
	public List<?> getObjSortFromSysCommedic(String objSort) {
		return service.getObjSortFromSysCommedicManageService(objSort);
	}

	@RequestMapping(value = { "/getComprehensiveSerchFromStoreObj" })
	@ResponseBody
	public HashMap<String, Object> getComprehensiveSerchFromStoreObj(
			String page, String pagesize, String startTime, String endTime,
			String objSort, String objGgxh, String rfidCode) {
		return service.getComprehensiveSerchFromStoreObjManageService(page,
				pagesize, startTime, endTime, objSort, objGgxh, rfidCode);
	}

	@RequestMapping(value = "/export")
	public void export(HttpServletRequest request,
			HttpServletResponse response, String startTime, String endTime,
			String objSort, String objGgxh, String rfidCode) {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=export.xls");
		// 创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		service.exportExcel(wb, startTime, endTime, objSort, objGgxh, rfidCode);
		// 获取文件输入流，写入excel并关闭
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
	 * EXCEL报表导出
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param time1
	 * @param time2
	 * @param batchCode
	 * @param objSort
	 * @param objGgxh
	 * @param address
	 * @author JS
	 */
	@RequestMapping(value = "/exportInStore")
	public void exportInStore(HttpServletRequest request,
			HttpServletResponse response, Integer id, String time1,
			String time2, String batchCode, String objSort, String objGgxh,
			String address) {
		response.setContentType("applicationnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=export.xls");
		// 创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		service.exportInStoreExcel(wb, time1, time2, batchCode, objSort,
				objGgxh, address);
		// 获取文件输入流，写入excel并关闭
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
	 * APP出库管理接口
	 * 
	 * @param request
	 * @param id
	 * @param token
	 * @param rfidCode
	 *            出库物料RFID卡号
	 * @Param workCode 工作单号
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "getOutStoreObj" })
	@ResponseBody
	public HashMap<String, Object> getOutStoreObj(HttpServletRequest request,
			Integer id, String token, String rfidCode,String workCode, String inoutType) {
		String msg = null;
		StoreObj obj = null;
		try {
			// TODO 执行出库方法 1.成品出库2.领料出库3.废料出库
			return service.ProductOutStore(rfidCode, workCode, inoutType);
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.toString();
			return JsonWrapper.failureWrapperMsg(obj, msg);
		}

	}

	/**
	 * 将出库信息发送给叉车的消息队列
	 * 
	 * @data:2017年7月21日
	 * @param obj
	 * @autor:wl
	 */
	private void sendEixtStoreToforklift(CommandVO vo) {
		MqttBroker sender;
		sender = MqttBroker.getInstance();
		String prop = pfu.getProp("QEU_TOPIC_PERSIST_CHACHE");
		Gson gson = new Gson();
		String message = gson.toJson(vo);
		sender.sendMessage(prop, message);
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	// @RequestMapping({ "toStoreHdmiList" })
	// public String toStoreHdmiList(HttpServletRequest request, Integer id) {
	// request.setAttribute("id", id);
	// return "storeManage/storeHdmi/storeHdmiList";
	// }
	// 去仓库电子看板
	@RequestMapping({ "toStoreHdmiList" })
	public String toStoreHdmiList(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeHdmi/storeKanbanList";
	}

	// 去仓库电子看板出库列表
	@RequestMapping({ "toOutStoreList" })
	public String toOutStoreList() {
		return "storeManage/storeHdmi/childPage/outStoreList";
	}

	// 去仓库电子看板出库列表
	@RequestMapping({ "toInStoreList" })
	public String toInStoreList() {
		return "storeManage/storeHdmi/childPage/inStoreList";
	}

	// 去仓库电子看板出库列表
	@RequestMapping({ "toStoresList" })
	public String toStoresList() {
		return "storeManage/storeHdmi/childPage/storesList";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreHdmiCrew" })
	public String toStoreHdmiCrew(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeHdmi/storeHdmiCrew";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreHdmiCrewB" })
	public String toStoreHdmiCrewB(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeHdmi/storeHdmiCrewB";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreHdmiTable" })
	public String toStoreHdmiTable(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeHdmi/storeHdmiTable";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreHdmiTableB" })
	public String toStoreHdmiTableB(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeHdmi/storeHdmiTableB";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "toStoreHdmiCall" })
	public String toStoreHdmiCall(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "storeManage/storeHdmi/storeHdmiCall";
	}

	/**
	 * 
	 * @return
	 * @author JS
	 */
	@RequestMapping({ "getOutStock" })
	@ResponseBody
	public Page getOutStock(Page p) {
		try {
			Page page = service.getOutStock(p);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取下拉框的值
	 * 
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getBatchCode" })
	@ResponseBody
	public Map[] getBatchCode() {
		Map[] code = service.getBatchCode();
		return code;
	}

	/**
	 * 获取下拉框的值
	 * 
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getObjSort" })
	@ResponseBody
	public Map[] getObjSort() {
		Map[] sort = service.getObjSort();
		return sort;
	}

	/**
	 * 获取下拉框的值
	 * 
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getObjGgxh" })
	@ResponseBody
	public Map[] getObjGgxh() {
		Map[] ggxh = service.getObjGgxh();
		return ggxh;
	}

	/**
	 * 获取下拉框的值
	 * 
	 * @return
	 * @author JS
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "getAddress" })
	@ResponseBody
	public Map[] getAddress() {
		Map[] addre = service.getAddress();
		return addre;
	}

	/**
	 * 根据RIFD，返回数据
	 * 
	 * @param rfidCode
	 * @return
	 */
	@RequestMapping({ "getGgxhToFork" })
	@ResponseBody
	public HashMap<String, Object> getGgxhToFork(String rfidCode) {
		MauRfid mauRfid = service.getMauRfid(rfidCode);
		if(mauRfid==null){
			return JsonWrapper.failureWrapperMsg(null, "该RIFD不存在");
		}
		if ("地标卡".equals(mauRfid.getMaterialType())||"机台卡".equals(mauRfid.getMaterialType())) {

			StoreCoilSa obj = service.getGgxhToFork(rfidCode);
			if (obj == null) {
				return JsonWrapper.failureWrapperMsg(obj, "该RIFD不存在");
			}
			StoreApp app = new StoreApp();
			app.setObjName(obj.getArea_type()+"("+obj.getArea_name()+")");
			return JsonWrapper.successWrapper(app, app.getObjName());
		}else if("物料卡".equals(mauRfid.getMaterialType())){
			StoreObj obj = service.getStoreObj(rfidCode);
			if (obj == null) {
				return JsonWrapper.failureWrapperMsg(obj, "该RIFD不存在");
			}
			StoreApp app = new StoreApp();
			//app.setAreaAddress(obj.getArea_name());
			app.setObjName(obj.getObjName()+"("+obj.getObjGgxh()+")");
			return JsonWrapper.successWrapper(app, app.getObjName());
		}
		return JsonWrapper.failureWrapperMsg(null, "该RIFD不在校验范围内");
	}

	/**
	 * @Description: 根据出入库 类型获得当天的记录
	 * @param request
	 * @param inoutType
	 * @return
	 */
	@RequestMapping("getTodayObj")
	@ResponseBody
	public List<StoreObj> getTodayObj(HttpServletRequest request,
			String inoutType) {
		try {
			List<StoreObj> todayList = service.getTodayList(inoutType);
			return todayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/***************************** 仓库电子看板 ************************************/

	// 去仓库电子看板
	@RequestMapping("toStoreList")
	public String toStoreList() {
		return "storeManage/storeHdmi/storeHouseList";
	}
	
	/**
	 *  当天原材料领料情况
	 * @return
	 */
	@RequestMapping("getMaterOutDisplay")
	@ResponseBody
	public HashMap<String , Object>getMaterOutDisplay(){
		try {
			List<PlaMacTaskMateril> displayList = service.getDisplayList();
			return JsonWrapper.successWrapper(displayList);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未查到相关数据");
		}
		
	}
	
	/**
	 * 当天成品出库情况
	 * @return
	 */
	@RequestMapping({"getProOutDisplay"})
	@ResponseBody
	public HashMap<String , Object> getProOutDisplay(){
		try {
			List<StoreObj> list = service.getProDisplayList();
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("未查到相关数据");
		}
	}

}
