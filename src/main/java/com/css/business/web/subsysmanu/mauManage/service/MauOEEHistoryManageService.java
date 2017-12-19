package com.css.business.web.subsysmanu.mauManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.h;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauOEEHistory;
import com.css.business.web.subsysmanu.mauManage.dao.MauOEEHistoryManageDAO;
import com.css.business.web.syswebsoket.bean.EchartSeriesVo;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("mauOEEHistoryManageService")
public class MauOEEHistoryManageService extends BaseEntityManageImpl<MauOEEHistory, MauOEEHistoryManageDAO> {
	@Resource(name="mauOEEHistoryManageDAO")
	//@Autowired
	private MauOEEHistoryManageDAO dao;
	
	
	@Override
	public MauOEEHistoryManageDAO getEntityDaoInf() {
		return dao;
	}

	private Gson  gson =  new Gson();
	

	public Page getListPage(Page page, String param) {
		String hql = "from  MauOEEHistory where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		Map<String,String> paramMap = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		if (paramMap != null && paramMap.size() > 0) {
			if (paramMap.get("macCode") != null && !"".equals(paramMap.get("macCode"))) {
				sb.append(" and macCode = '"+paramMap.get("macCode")+"'");
			}
			
			if(paramMap.get("createDate") != null && !"".equals(paramMap.get("createDate")) ){
				sb.append(" and createDate = '"+paramMap.get("createDate").trim()+"'");
			} 
			
			if(paramMap.get("courseCode") != null && !"".equals(paramMap.get("courseCode")) ){
				sb.append(" and courseCode like '%"+paramMap.get("courseCode").trim()+"%'");
			} 
		}
		sb.append(" order by createDate DESC ");
		Page pageQuery = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		return pageQuery;
	}

	/**
	 * 
	 * @Description:多个记录做一个echarts
	 * @param ids
	 * @return
	 */
	public EchartsVo getChartsIds(String ids) {
		String[] split = ids.split(ids);
		String hql = "from MauOEEHistory where id in ("+ids+")";
		String repHql = hql.replace("\"", "");
		List<MauOEEHistory> listQuery = dao.listQuery(repHql);
		EchartsVo vo = new EchartsVo();
		List<String> legends = new ArrayList<>();
		List<String> allXAxis = new ArrayList<>();
		Map<String, EchartSeriesVo> seriesData = new HashMap<String, EchartSeriesVo>();
		Integer count = 0;
		String title = "";
		for (MauOEEHistory history : listQuery) {
			title += "-"+ history.getMacCode();
			String voJson = history.getEchartsVO();
			EchartsVo echartsVo = gson.fromJson(voJson, EchartsVo.class);
			if (count == 0) {
				legends = echartsVo.getLegends();
				count++;
			}
			allXAxis.addAll(echartsVo.getxAxis());
			Map<String, EchartSeriesVo> seriesData2 = echartsVo.getSeriesData();
			for (String ds : legends) {
				//当前循环的
				EchartSeriesVo nowESVo = seriesData2.get(ds);
				if (seriesData.get(ds) == null ) {
					seriesData.put(ds, nowESVo);
				}else {
					//最终的
					//已在,安排中的
					EchartSeriesVo echartSeriesVo2 = seriesData.get(ds);
					List<String> data = echartSeriesVo2.getData();
					data.addAll(nowESVo.getData());
					echartSeriesVo2.setData(data);
					seriesData.put(ds, echartSeriesVo2);
				}
				
			}

			
		}
		vo.setLegends(legends );
		vo.setTitle(title);
		vo.setxAxis(allXAxis );
		vo.setSeriesData(seriesData );
		return vo;
	}
	
	
	
}
