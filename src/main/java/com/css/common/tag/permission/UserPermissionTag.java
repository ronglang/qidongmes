package com.css.common.tag.permission;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.util.StringUtil;

public class UserPermissionTag extends TagSupport implements Serializable{

	private static final long serialVersionUID = 8068152903297965288L;
	//具体权限url
	private String authurl;

	public String getAuthurl() {
		return authurl;
	}

	public void setAuthurl(String authurl) {
		this.authurl = authurl;
	}

	@Override
	public int doStartTag() throws JspException {
		boolean result = false;
		SysUser user = SessionUtils.getUser((HttpServletRequest) pageContext.getRequest());
		List<SysMenu> auths = user.getUserAllResource();
		for(SysMenu s : auths){
			if(!StringUtil.isEmpty(s.getUrl()) && !StringUtil.isEmpty(this.authurl)){
				if(this.authurl.equals(s.getUrl())){
					result = true;
					break;
				}
			}
		}
		if(StringUtil.isEmpty(this.authurl) || user.isAdmin()){
			result = true;
		}
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
}
