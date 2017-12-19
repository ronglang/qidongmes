package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysContactGroup;
import com.css.business.web.sysManage.dao.SysContactGroupManageDAO;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysContactGroupManageService")
public class SysContactGroupManageService extends
		BaseEntityManageImpl<SysContactGroup, SysContactGroupManageDAO> {
	@Resource(name = "sysContactGroupManageDAO")
	// @Autowired
	private SysContactGroupManageDAO dao;

	@Override
	public SysContactGroupManageDAO getEntityDaoInf() {
		return dao;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> getTree(String code, Object dvalue) throws Exception {
		QueryCondition qc = new QueryCondition(SysContactGroup.class);
		if (!StringUtil.isEmpty(code)) {
			qc.addCondition(code, "=", dvalue);
		}
		List<SysContactGroup> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysContactGroup s : list) {
			TreeVO vo = new TreeVO(s.getId().toString(), s.getName().toString());
			tree.add(vo);
		}
		return tree;
	}
}