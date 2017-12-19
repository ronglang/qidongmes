package com.css.business.web.subsysquality.quaManage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.util.facade.FacadeTaskHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.print.resources.serviceui;

import com.css.business.web.subsysquality.bean.QualityBasicValue;
import com.css.business.web.subsysquality.bean.QualityMaterReport;
import com.css.business.web.subsysquality.quaManage.dao.QualityMaterReportManageDAO;
import com.css.business.web.sysManage.bean.SysNotice;
import com.css.business.web.sysManage.service.SysNoticeManageService;
import com.css.commcon.util.SessionUtils;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service("qualityMaterReportManageService")
public class QualityMaterReportManageService extends
		BaseEntityManageImpl<QualityMaterReport, QualityMaterReportManageDAO> {
	@Resource(name = "qualityMaterReportManageDAO")
	// @Autowired
	private QualityMaterReportManageDAO dao;

	/** 实际参数值service */
	@Resource(name="qualityBasicValueManageService")
	private QualityBasicValueManageService vaService;
	@Override
	public QualityMaterReportManageDAO getEntityDaoInf() {
		return dao;
	}
	private Gson gson = new Gson();
	@Resource(name="sysNoticeManageService")
	private SysNoticeManageService noticeService ;
	
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param map
	 * @param page
	 * @return         
	 */ 
	public Page getPageList(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String hql = "from QualityMaterReport where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (map != null && map.size() > 0) {
			if (map.get("testResult") != null && map.get("testResult") !="" ) {
				sb.append(" and testResult = '"+map.get("testResult")+"'");
			}
			if (map.get("testState") != null && map.get("testState") !="" ) {
				sb.append(" and testState = '"+map.get("testState")+"'");
			}
			if (map.get("isCome") != null && map.get("isCome") !="" ) {
				sb.append(" and isCome = '"+map.get("isCome")+"'");
			}
			if (map.get("start") != null && map.get("start") !="" ) {
				sb.append(" and testDate >= '"+map.get("start")+"'");
			}
			if (map.get("end") != null && map.get("end") !="" ) {
				sb.append(" and end >= '"+map.get("end")+"'");
			}
		}
		sb.append("  order By testState ");
		Page pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return pageQuery;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param bean
	 * @param paramBeans         
	 * @param useName 
	 */ 
	public String saveBeanAndParams(String bean, String paramBeans, String useName) {
		// TODO Auto-generated method stub
		//bean.replaceAll("", null);
		QualityMaterReport report = gson.fromJson(bean, QualityMaterReport.class);
		
		//如果状态修改成已检测,处理质检呼叫
		/*if(report.STATE_IS_END.equals(report.getTestState()) ){
			
		}*/
		List<QualityBasicValue> list = gson.fromJson(paramBeans, new TypeToken<List<QualityBasicValue>>(){}.getType());
		try {
			String macShowCaChe = factory.getMacShowCaChe(YorkUtil.来料质检RFID);
			if (macShowCaChe != null  ) {
				factory.removeMacShowCache(YorkUtil.来料质检RFID);
			}
			this.updateByCon(report, false);
			for (QualityBasicValue value : list) {
				if (value.getId() != null) {
					//修改
					vaService.updateByCon(value, false);
				} else {
					//新建保存
					value.setQuaCode(report.getReportCode());
					value.setCreateDate(new Date());
					value.setCreateBy(useName);
					vaService.save(value);
				}
			}
			//页面一行提示
			/*if (QualityMaterReport.RESULT_IS_PASS.equals(report.getTestResult())) {
				SysNotice entity = new SysNotice();
				//合格
				entity.setIsShow("是");
				entity.setKey(report.getReportCode());
				entity.setType("入库");
				entity.setValue("来料入库:有来料入库,质检编号"+report.getReportCode());
				noticeService.save(entity );
			}*/
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param id         
	 */ 
	public void clearBean(String id) {
		// TODO Auto-generated method stub
		try {
			vaService.clearBeansByReportId(id);
			String hql = " delete from QualityMaterReport where id = '"+id+"'";
			dao.createQuery(hql).executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**   
	 * @Description: 调用数据库函数   
	 * @param string
	 * @return         
	 * @throws Exception 
	 */ 
	public Object getFun(String funName,Object ...obj) throws Exception {
		// TODO Auto-generated method stub
		Object result = dao.exeFunction(funName,obj);
		return result;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param sTATE_NO_START
	 * @return         
	 */ 
	public List<QualityMaterReport> getListByState(String state) {
		// TODO Auto-generated method stub
		String hql = "from QualityMaterReport where testState = '"+state+"'";
		List<QualityMaterReport> listQuery = dao.listQuery(hql);
		return listQuery;
	}

	/**   
	 * @Description: 打印   
	 * @param reportCode
	 * @return         
	 */ 
	public QualityMaterReport getPrintMaterReport(String reportCode) {
		// TODO Auto-generated method stub
		String hql = " from QualityMaterReport where reportCode = '"+reportCode+"'";
		List<QualityMaterReport> listQuery = dao.listQuery(hql);
		QualityMaterReport report = null;
		if (listQuery != null && listQuery.size() > 0) {
			report =  listQuery.get(0);
			List<QualityBasicValue> vaList = vaService.getValueByTypeName(null, report.getReportCode());
			report.setVaList(vaList);
			return report;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description:仓库电子看版，原材料入数。查询当天的，查询以前 没有质检的 
	 * @param @return
	 * @return List<QualityMaterReport>
	 * @throws
	 */
	public List<QualityMaterReport> getDepotShow(){
		String day = DateUtil.format(new Date(), "yyyy-MM-dd");
		String start = day + " 00:00:00";
		String end = day + " 23:59:59";
		String hql = "from QualityMaterReport where (comeDate >= '"+start+"' and comeDate <= '"+end+"') or (testState = '"+QualityMaterReport.STATE_NO_START+"' )  order by comeDate";
		List<QualityMaterReport> list = dao.listQuery(hql);
		return  list;
	}
	
	

	
}
