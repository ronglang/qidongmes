package com.css.common.web.syscommon.controller.support;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.css.common.util.StringUtil;

public class QueryCondition {
	@SuppressWarnings("rawtypes")
	private Class className;
	private String orderBy;
	private List<Object[]> condition = new ArrayList<Object[]>();
	private String hql;
	private Object[] hqlValue;

	@SuppressWarnings("rawtypes")
	public QueryCondition(Class className) {
		this.className = className;
	}

	public void addCondition(String column, String condition, Object value) {
		Object[] c = { column, condition, value };
		this.condition.add(c);
	}

	public void addCondition(String jsonStr) {
		if (StringUtil.isEmpty(jsonStr)) {
			return;
		}
		JSONArray ja = JSONArray.fromObject(jsonStr);
		for (int i = 0; i < ja.size(); i++) {
			JSONObject obj = ja.getJSONObject(i);
			Object[] c = { obj.getString("queryField"),
					obj.getString("queryType"), obj.get("queryValue") };
			this.condition.add(c);
		}
	}

	public void addCondition(List<QueryObject> queryObjects) {
		if (queryObjects == null) {
			return;
		}
		for (QueryObject queryObject : queryObjects) {
			if (!StringUtil.isEmpty(queryObject.getQueryValue())) {
				if (("like".equals(queryObject.getQueryType().trim()))
						|| ("not like"
								.equals(queryObject.getQueryType().trim()))) {
					Object[] c = { queryObject.getQueryField(),
							queryObject.getQueryType(),
							"%" + queryObject.getQueryValue() + "%" };
					this.condition.add(c);
				} else {
					Object[] c = { queryObject.getQueryField(),
							queryObject.getQueryType(),
							queryObject.getQueryValue() };
					this.condition.add(c);
				}
			}
		}
	}

	public void addCondition(String hql, Object[] hqlValue) {
		if (StringUtil.isEmpty(hql)) {
			return;
		}
		this.hql = hql;
		this.hqlValue = hqlValue;
	}

	public Object[] getHql() {
		String hql = " from " + getClassName().toString();
		List<Object> qValue = new ArrayList<Object>();
		String chql = "";
		Object[] temp;
		String test;
		Object[] parameterList;
		for (int i = 0; i < this.condition.size(); i++) {
			temp = (Object[]) this.condition.get(i);
			if (i != 0) {
				chql = chql + " and ";
			}
			if (temp[1].toString().equals("in")) {
				test = "";
				parameterList = (Object[]) temp[2];
				for (Object tempParameter : parameterList) {
					test = test + "?,";
					qValue.add(tempParameter);
				}
				if (!StringUtil.isEmpty(test)) {
					test = test.substring(0, test.length() - 1);
				}
				chql = chql + temp[0] + " " + temp[1] + "(" + test + ")";
			} else {
				chql = chql + temp[0] + " " + temp[1] + " ?";
				qValue.add(temp[2]);
			}
		}
		if (!StringUtil.isEmpty(this.hql)) {
			if (chql.length() > 1) {
				hql = hql + " where " + chql + " and " + this.hql;
			} else {
				hql = hql + " where " + this.hql;
			}
			test = (parameterList = this.hqlValue).length + "";
			for (int a = 0; a < test.length(); a++) {
				Object obj = parameterList[a];
				qValue.add(obj);
			}
		} else if (chql.length() > 1) {
			hql = hql + " where " + chql;
		}
		if (!StringUtil.isEmpty(this.orderBy)) {
			hql = hql + " order by " + this.orderBy;
		}
		Object[] ret = new Object[2];
		ret[0] = hql;
		ret[1] = qValue;
		return ret;
	}

	public void setOrderBy(String column, String sort) {
		if (StringUtil.isEmpty(column)) {
			return;
		}
		if (StringUtil.isEmpty(column)) {
			setOrderBy(column);
		} else {
			this.orderBy = (column + "  " + sort);
		}
	}

	public void setOrderBy(String column) {
		this.orderBy = (column + " asc");
	}

	private String getClassName() {
		String classString = this.className.toString().substring(
				this.className.toString().lastIndexOf(".") + 1,
				this.className.toString().length());
		return classString;
	}
}
