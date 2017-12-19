package com.css.business.web.subsysplan.plaManage.dao;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaWeekPlan;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaWeekPlanManageDAO")
public class PlaWeekPlanManageDAO extends BaseEntityDaoImpl<PlaWeekPlan>  {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void saveBatch(final List<PlaWeekPlan> list){
		super.getHibernateTemplate().execute(new HibernateCallback<String>() {
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				int count = 0;
				for(PlaWeekPlan plan : list){
					session.save(plan);
					if(count%100==0&&count!=0){
						session.flush();
					}
					count++;
				}
				return null;
			}
		});
	}
	/**
	 * 返回计划彪里边最大的日期
	 * @return
	 * @throws ParseException 
	 */
	public Timestamp getMaxDate () throws ParseException{
		String sql = " select max(work_start_date) from pla_week_plan  ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		String date = map.get("max") == null?null:map.get("max").toString();
		if(date == null){
			return null;
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp d = new Timestamp(df.parse(date).getTime());
			return d;
		}
	}
	
	/**
	 * @TODO: 根据工单号，更新周计划为完成状态
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param courseCode
	 */
	public void updateWeekPlanTo_finish_byCourseCode(String courseCode,Integer workDay){
		String sql = "update pla_week_plan set is_flag='已生产' where  id in (select week_plan_id from pla_machine_plan where course_code=?) and work_day=?";
		this.deleteBySql(sql, courseCode,workDay);
	}
}
