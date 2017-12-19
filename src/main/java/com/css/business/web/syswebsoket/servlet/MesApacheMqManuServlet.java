/**     
 *
 */
package com.css.business.web.syswebsoket.servlet;

import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.css.business.web.syswebsoket.handler.MesApacheMqLaSiMessageListener;
import com.css.business.web.syswebsoket.handler.MesApacheMqManuMessageListener;
import com.css.business.web.syswebsoket.handler.MesApacheMqStoreMessageListener;

/**
 * @Title: CenterActiveMqServlet.java
 * @Package com.css.business.web.syswebsoket
 * @Description: 连接消息队列的中间类
 * @author rb
 * @date 2017年7月6日 下午5:11:58
 * @company SMTC
 * web.xml中已注掉
 */
@Deprecated
public class MesApacheMqManuServlet extends HttpServlet {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -4964789596626528508L;

	public MesApacheMqManuServlet() {

	}

	@Override
	public void init() {
		final WebApplicationContext wac = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		// new MesApacheMqManuMessageListener(wac);
		// new MesApacheMqStoreMessageListener(wac);
		// new MesApacheMqLaSiMessageListener(wac);
		// new MesApacheMqManuDepartmentMessageListener(wac);

	}
}
