package com.css.business.web.subsysplan.plaManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaCourseAxis;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaCourseAxisManageDAO")
public class PlaCourseAxisManageDAO extends BaseEntityDaoImpl<PlaCourseAxis>  {

	public List<Object[]> getNumByOrderCode(String orderCode) {
		String sql = "select pro_ggxh ,color,axis_length,sum(axis_num) from Pla_Course_Axis where  course_code in (select ws_code from pla_course where order_code ='"+orderCode+"') group by pro_ggxh,color,axis_length";
		List<Object[]> queryBySql = this.queryBySql(sql);
		return queryBySql;
	}

}
