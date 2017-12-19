/**
 * @todo: 
 * @author : zhaichunlei
 * @date: 2017年12月13日
 */
package com.css.business.web.subsysplan.plaManage.dao;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaMacTaskRecord;
import com.css.business.web.subsysplan.plaManage.bean.PlaMacTaskVO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

/**
 * @author Administrator
 *
 */
@Repository("plaMacTaskRecordManageDAO")
public class PlaMacTaskRecordManageDAO  extends BaseEntityDaoImpl<PlaMacTaskRecord> {
	public List<PlaMacTaskVO> queryMachineTaskSummer(String maccode){
		StringBuffer sb = new StringBuffer();
		sb.append("select coalesce(m.workcode,'未生产') workcode, a .mac_code maccode,coalesce(m.useminute,0) useminute, coalesce(m.schedule,0) schedule,");//m.seqcode,
		sb.append(" coalesce(a.mac_state,'')  macstate, ");
		sb.append(" (select case when count(1) = 0 then '无' else '有' end from mau_call_forklift_record fr where fr.status='N' and fr.call_address like '%'|| a.mac_code ) cc,");
		sb.append("string_agg(coalesce(e.name,''),',') fzr, ");
		sb.append("cast(sum(case when r.emp1rfid is null then 0 else 1 end + case when r.emp2rfid is null then 0 else 1 end) as int4 ) pnum ,");
		sb.append(" cast(sum(COALESCE(r.testtime,0)) as int4) testtime,cast(sum(COALESCE(r.worktime,0)) as int4) worktime,  ");
		sb.append("avg(COALESCE(r.speed,0)) speed, ");
		//sb.append("coalesce((select m2.workcode from pla_mac_task m2 where m.maccode=m2.maccode and m2.pstime > m.pstime ");
       // sb.append("  and m2.productstate in ('已排产','生产中') LIMIT 1 ),'') workcode_next ");
        sb.append("COALESCE((select  coalesce(workcode,'') from  pla_mac_task where id in (select m2.id from pla_mac_task m2 where m.maccode=m2.maccode and m2.pstime > m.pstime ");
        sb.append("  and m2.productstate in ('已排产','生产中') limit 1 ) ) ,'')  workcode_next, ");
        sb.append(" cast(fun_calc_lyl_hjtime (m .workcode,a .mac_code)  as varchar) hjtime, ");
        sb.append(" cast(fun_calc_lyl (m .workcode, a.mac_code)  as varchar) || '%' lyl ");
		sb.append(" from mau_machine a left join	pla_mac_task m on a.mac_code=m.maccode and m.productstate='生产中' " );
		sb.append(" left join pla_mac_task_record r ");
		sb.append("on m.workcode=r.workcode and m.maccode=r.maccode ");
		sb.append(" left join  sys_employee e on e.emp_rfid=r.emp1rfid   and e.name is not null ");
		
		if(maccode != null && maccode.length() > 0){
			sb.append("where 1=1 ");
			//maccode,多个机台时，以,号隔开。 这里用like查询
			sb.append(" and \'"+maccode+"\' like '%'||a.mac_code||'%' ");
		}
		
		sb.append("group by m.workcode,a .mac_code, a.mac_state, m.maccode,m.useminute,m.schedule,m.pstime ");
		sb.append("order by a.mac_code ");
		@SuppressWarnings("unchecked")
		List<PlaMacTaskVO> lst = this.createSQLQuery(sb.toString()).setResultTransformer(Transformers.aliasToBean(PlaMacTaskVO.class)).list();

		return lst;
	}
}
