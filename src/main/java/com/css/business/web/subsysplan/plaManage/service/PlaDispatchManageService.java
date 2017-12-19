package com.css.business.web.subsysplan.plaManage.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaDispatch;
import com.css.business.web.subsysplan.plaManage.dao.PlaDispatchManageDAO;
import com.css.business.web.subsysplan.plaManage.utils.CalculaMaterUtil;
import com.css.business.web.subsysplan.vo.PlaDisPatchVo;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaDispatchManageService")
public class PlaDispatchManageService extends BaseEntityManageImpl<PlaDispatch, PlaDispatchManageDAO> {
	@Resource(name="plaDispatchManageDAO")
	//@Autowired
	private PlaDispatchManageDAO dao;
	
	
	@Override
	public PlaDispatchManageDAO getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * 封装日历饼图的数据
	 * @return
	 * @throws ParseException
	 * @author JS
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PlaDisPatchVo getPlanDisPatchCanlenderPie() throws ParseException{
		String sql = " SELECT DISTINCT work_day FROM pla_product_order_seq_hourse ORDER BY work_day ";
		List<Object> list = dao.createSQLQuery(sql).list(); // 查询出所有排产日期
		PlaDisPatchVo vo = new PlaDisPatchVo();
		List<String> li = new ArrayList<String>();
		Map<String,Map[]> series = new HashMap<String,Map[]>();
		for (Object obj : list) { //封装图数据
			String sq = "   SELECT  c.seq_name   ,count(*)  FROM pla_product_order_seq_hourse p "
					+ " LEFT JOIN cra_seq c ON p.seq_code = c.seq_code WHERE work_day = '"+obj.toString()+"' "
					+ " GROUP BY  c.seq_name ,work_day ORDER BY work_day ";
			List<Object> lis = dao.createSQLQuery(sq).list(); // 根据日期查询到对应当天的工序，工序需要生成数量
			Iterator<Object> its = lis.iterator();
			Map[] ser = new Map[lis.size()];
			int i = 0;
			while(its.hasNext()){ //迭代lis的数据
				Object[] it = (Object[]) its.next();
				Map<String, String> map = new HashMap<String, String>();
				for(int j = 0 ;j < 3 ; j++){
					if(j==0){
						map.put("name", it[0].toString());
					}else if(j==1){
						map.put("value", it[1].toString());
					}else{
						map.put("date", CalculaMaterUtil.strToDateFormat(obj.toString(),"yyyy-MM-dd"));
					}
					
				}
				ser[i] = map;
				i++;
			}
			series.put(CalculaMaterUtil.strToDateFormat(obj.toString(),"yyyy-MM-dd"), ser);
			li.add(CalculaMaterUtil.strToDateFormat(obj.toString(),"yyyy-MM-dd"));
		}
		String sql1 = " SELECT  c.seq_name FROM pla_product_order_seq_hourse p LEFT JOIN cra_seq c ON p.seq_code = c.seq_code  GROUP BY  c.seq_name  ";
		List<String> seqNames = dao.createSQLQuery(sql1).list();
		vo.setLegend(seqNames); //得到图例
		vo.setWorkdates(li);
		vo.setSeries(series);
		return vo;
	}
	
	/**
	 * 封装柱状图数据
	 * @param date
	 * @param seqName
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public PlaDisPatchVo getPlanDisPatchCanBar(String date,String seqName) throws ParseException{
		String workDay = CalculaMaterUtil.strToDateFormat(date, "yyyyMMdd");
		String sql = " SELECT m .mac_code,M .mac_name,sum(coalesce(T .hours,-1)  ) hours "
				+ " FROM cra_seq C ,mau_machine M LEFT JOIN mau_task_load T ON M .mac_code = T .mac_code "
				+ " AND work_day = "+workDay+" WHERE C .seq_code = M .seq_code AND C .seq_name = '"+seqName+"' "
				+ " GROUP BY m .mac_code,M .mac_name";
		List<Object> list = dao.createSQLQuery(sql).list();
		PlaDisPatchVo vo = new PlaDisPatchVo();
		Iterator<Object> it = list.iterator();
		List<String> barY = new ArrayList<String>();
		List<String> barX = new ArrayList<String>();
		while(it.hasNext()){
			Object[] obj = (Object[]) it.next();
			barX.add(obj[0].toString());
			barY.add(obj[2].toString());
		}
		vo.setBarX(barX);
		vo.setBarY(barY);
		return vo;
	}
	
	
	
}
