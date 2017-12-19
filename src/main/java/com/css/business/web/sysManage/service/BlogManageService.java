package com.css.business.web.sysManage.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.Blog;
import com.css.business.web.sysManage.dao.BlogManageDAO;
import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * 
 *TODO 博客业务处理类
 * @author huangaho
 *2015-4-24上午11:36:36
 */
@Service("blogManageService")
public class BlogManageService extends BaseEntityManageImpl<Blog, BlogManageDAO> {
	@Resource(name="blogManageDAO")
	//@Autowired
	private BlogManageDAO dao;
	
	
	@Override
	public BlogManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 保存博客并保存博客附件
	 */
	@Transactional(readOnly = false)
	public void saveBlogAndAttach(Blog blog,Sys_attachment atta) throws Exception{
		if(blog.getAddDate() == null){
			blog.setAddDate(new Date());
		}
		this.save(blog);
		atta.setBid(blog.getId());
		this.saveAttachment(atta);
	}
}
