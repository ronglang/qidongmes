package com.css.business.web.sysManage.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("exportDAO")
public class ExportDAO {

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getExportData(final String tableName) throws Exception {
		String sql = "select * from " + tableName;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
}
