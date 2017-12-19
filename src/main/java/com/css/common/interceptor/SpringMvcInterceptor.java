package com.css.common.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.util.StringUtils;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.bean.SysUserOptionLog;
import com.css.business.web.sysManage.service.SysUserOptionLogManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.annotation.Auth;
import com.css.common.annotation.Remark;
import com.css.common.annotation.RemarkClass;
import com.css.common.enums.ServiceErrorCode;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.HtmlUtil;
import com.css.common.util.NetUtil;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 *TODO 处理异常，日志拦截器
 * @author huangaho
 *2015-4-17下午1:13:15
 */
public class SpringMvcInterceptor extends HandlerInterceptorAdapter {
    private final static Logger log = LoggerFactory.getLogger(SpringMvcInterceptor.class);
    private RedisService redisService;
    
    
	@Autowired
	private SysUserOptionLogManageService service;
    /**
     * 权限验证
     */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.info("方法调用前拦载，可实现权限验证！");
		 //Map<String, Object> result = new HashMap<String, Object>();
		 HandlerMethod method = (HandlerMethod) handler;
		 //Auth auth = method.getMethodAnnotation(Auth.class);
		//可实现权限验证
		  if (handler instanceof HandlerMethod) {
			  //验证TOKEN
//			  if(!validationToken(request,response,handler)){
//				  return false;
//			  }
			  //验证url权限
			  if(!validationUrl(request,response,handler)){
				  return false;
			  }
	      }
		  deleteDataPreRecord(request,method);
		return super.preHandle(request, response, handler);
	}

	@SuppressWarnings("rawtypes")
	private void deleteDataPreRecord(HttpServletRequest request,HandlerMethod method)throws Exception{
         Object ids = null;
         String pname = null;
         if(method.getMethod().getName().startsWith("delete")){
    		ParameterizedType parameterizedType = (ParameterizedType)method.getBean().getClass().getGenericSuperclass();
            Class obj =  (Class)(parameterizedType.getActualTypeArguments()[0]); 
             Class pcl = method.getBean().getClass();
    	     log.info("调用的ACTION类(删除数据前)："+pcl.getName()+"调用的方法："+method.getMethod().getName()+" 实体："+obj.getName());
     		 Enumeration enu =  request.getParameterNames();
     		 while(enu.hasMoreElements()){
     			 //leitao:当app调用公共删除接口时，传入参数不止ids一个，最少还有一个requestType，因此此处会出现参数名和值匹配错误
     			 if(enu.nextElement().toString()!="ids")
     				 break;
     			 pname = enu.nextElement().toString();
     		 }
     		 ids = request.getParameter(pname);
         	 StringBuilder deleteData = new StringBuilder();
     		 if(ids != null && !StringUtil.isEmpty(ids.toString())){
     			 String hql = " from " + obj.getName() + " where id in ("+ids+")" ;
     			 List list = this.service.getEntityDaoInf().createQuery(hql).list();
     			 if(list != null && list.size() > 0){
     				 Method [] methods = obj.getDeclaredMethods();
    				 boolean hastoString = false;
    				 for(Method  m : methods ){
    					 if("toString".equals(m.getName())){
    						 hastoString = true;
    						 break;
    					 }
    				 }
    				 if(hastoString){
    					 for(Object object : list){
    	    					Method m = object.getClass().getDeclaredMethod("toString");
    	    					Object val = m.invoke(object);
    	    					deleteData.append(val);
    	    				 }
    				 }else{
						 log.info(" 实体："+obj.getName()+" 没有重写toString方法！");
					 }
     			 }
     		 }
     		 
         	 Remark auth = method.getMethod().getAnnotation(Remark.class);
             RemarkClass reC2 =  method.getBean().getClass().getAnnotation(RemarkClass.class);
            if(auth != null){
         	   String con = auth.toDo();
         	   if(reC2 != null){
         		   con = reC2.toDo()+con;
         	   }
         	  con = "(前)"+con+deleteData.toString();
         	   if(SessionUtils.getUser(request) != null){
         		   SysUserOptionLog userlog =  new SysUserOptionLog(SessionUtils.getUser(request).getId(), con, NetUtil.getRemoteHost(request),
            				new Date(), SessionUtils.getUser(request).getAccount()) ;
         		   service.save(userlog);
         	   }
         	   log.info(con);
            }
         }
	}
	
	@SuppressWarnings("static-access")
	private boolean validationUrl(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		if (handler instanceof HandlerMethod) {
	           HandlerMethod method = (HandlerMethod) handler;
	          String requestType =  request.getParameter("requestType");//portal，mapuser，appuser
	          //取得用户请求地址
              String url = request.getRequestURI().replaceFirst(request.getContextPath(), "");
              log.info("request url : "+url);
	           if(requestType != null ){
	        	   if("mapuser".equals(requestType) || "portal".equals(requestType) ){
	        		   return true;
	        	   }
	           }
	           
	           //门户的请求URL
	           if(url.contains("portal")){
	        	   return true;
	           }
	        
	           Auth auth = method.getMethodAnnotation(Auth.class);
	          SysUser user= SessionUtils.getUser(request);
	          if(auth == null || auth.verifyLogin() || !(auth instanceof Auth)){
	        	  
	        	//手机登录
		           if("appuser".equals(requestType)){
		        	   String token = null;
			           token =  request.getParameter("token");
			           if(!StringUtil.isEmpty(token)){
			        		   SysUser usr = EhCacheFactory.getInstance().getTokenKeeperCache(token);
			        		   if(usr == null){
			        			   Map<String, Object> res = new HashMap<String, Object>();
			        			   res.put("success", false);
			        			   res.put("msg", "用户未登陆，请先登陆！");
			        			   log.warn("用户未登陆！");
			        			   HtmlUtil.writerJson(response, res);
			        			   return false;
			        		   }else{
			        			   return true;
			        		   }
			           }else{
			        	   Map<String, Object> res = new HashMap<String, Object>();
		                      res.put("success", false);
		                      res.put("msg", "用户未登陆，请先登陆！");
		                      log.warn("用户未登陆！");
		                      HtmlUtil.writerJson(response, res);
		        		   return false;
			           }
		           }
	        	  
		          if(user == null ){
		        	  if(isAjax(request)){
		        		  response.setStatus(response.SC_GATEWAY_TIMEOUT);
	                      Map<String, Object> res = new HashMap<String, Object>();
	                      res.put("success", false);
	                      res.put("logoutFlag", true);//登录标记 true 退出
	                      res.put("msg", "登录超时.");
	                      log.warn("用户超时！");
	                      HtmlUtil.writerJson(response, res);
		        	  }else{
		        		  Object num = request.getSession().getAttribute("num");
		        		  if(num == null){
		        			  request.getSession().setAttribute("num",1);
			        		  request.setAttribute("status", response.SC_GATEWAY_TIMEOUT);
		        		  }
		        		 request.getRequestDispatcher("/mylogin.jsp").forward(request, response); 
		        	  }
		        	  return false;
		          }
	          }
	           if(auth == null || auth.verifyURL() || !(auth instanceof Auth)){
                   return true;
	           }
	      }
		return true;
	}
	
	
	/**
	 * 验证用户TOKEN
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean validationToken(HttpServletRequest request,HttpServletResponse response, Object handler){
		  if (handler instanceof HandlerMethod) {
	           HandlerMethod method = (HandlerMethod) handler;
	           String token = null;
	           Map<String, Object> result = new HashMap<String, Object>();
	           Auth auth = method.getMethodAnnotation(Auth.class);
	           if(auth == null || auth.verifyToken()){
	        	   
		        		   //验证token
		        	   		token = getToken(request);
		        	   		String val = redisService.get(token);//account|password
		        	   		if(StringUtils.isEmpty(val)){//无token
		        	   			result.clear();
				                result.put("success", false);
				                result.put("msg", ServiceErrorCode.INVALID_TOKEN.getDesc());
				                result.put("stateCode", ServiceErrorCode.INVALID_TOKEN.getCode());
				                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
				                HtmlUtil.writerJson(response, result);
				                return false;
		        	   		}else{
		        	   			return true;
		        	   		}
		           
	           }
	      }
		return true;
	}
	
	/**
	 * 获取请求TOKEN
	 * @param request
	 * @param method
	 * @return
	 */
	private String getToken(HttpServletRequest request){
		String token = request.getParameter("token");
		return token;
	}
	
    /**
     * 集中处理操作日志
     */
    @Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
    	log.info("方法执行还未渲染到页面拦载，可日志记录！");
    	 //验证是否需要验证
        if (handler instanceof HandlerMethod) {
        	savelogtoDatabase(request,response,handler,"(删除数据成功)");
        }
		super.postHandle(request, response, handler, modelAndView);
	}
	
    
    private void savelogtoDatabase(HttpServletRequest request,HttpServletResponse response, Object handler,String msg)throws Exception{
    	HandlerMethod method = (HandlerMethod) handler;
        Remark auth = method.getMethod().getAnnotation(Remark.class);
        RemarkClass reC2 =  method.getBean().getClass().getAnnotation(RemarkClass.class);
       if(auth != null){
    	   String con = auth.toDo();
    	   if(reC2 != null){
    		   con = reC2.toDo()+con;
    	   }
    	   if(method.getMethod().getName().startsWith("delete")){
    		   con="(后)"+con+msg;
    	   }
    	   if(SessionUtils.getUser(request) != null){
    		   SysUserOptionLog userlog =  new SysUserOptionLog(SessionUtils.getUser(request).getId(), con+"!", NetUtil.getRemoteHost(request),
       				new Date(), SessionUtils.getUser(request).getAccount()) ;
    		   service.save(userlog);
    	   }
    	   log.info(con+"!");
       }
    }
	
	/**
	 * 集中处理异常
	 */
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        if (ex != null) {
            String msg = "系统异常！";
            if(ex instanceof RuntimeException){
            	msg = ex.getMessage();
            }
            //leitao : 判断错误类型是否为查询错误异常，主要用于高级查询的异常拦截
            if(ex instanceof org.springframework.orm.hibernate3.HibernateQueryException){
            	msg = "查询语句错误，请核实后查询";
            }
            logger(request, handler, ex);
            String uri = request.getHeader("X-Requested-With");
            savelogtoDatabase(request,response,handler,"(删除数据失败)");
            if (uri != null && uri.equalsIgnoreCase("xmlhttprequest")) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("success", false);
                result.put("msg", msg);
//                response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                HtmlUtil.writerJson(response, result);
            } else {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("success", false);
                result.put("msg", msg);
                ObjectMapper objectMapper = new ObjectMapper();
                HtmlUtil.writerHtml(response, objectMapper.writeValueAsString(result));
            }
            
        } else {
            super.afterCompletion(request, response, handler, ex);
        }
    }

    /**
     * 记录日志
     * 
     * @param request
     * @param handler
     * @param ex
     */
    public void logger(HttpServletRequest request, Object handler, Exception ex) {
        StringBuffer msg = new StringBuffer();
        msg.append("系统异常拦载：");
        msg.append("[uri＝").append(request.getRequestURI()).append("]");
        Enumeration<?> enumer = request.getParameterNames();
        while (enumer.hasMoreElements()) {
            String name = (String) enumer.nextElement();
            String[] values = request.getParameterValues(name);
            msg.append("[").append(name).append("=");
            if (values != null) {
                int i = 0;
                for (String value : values) {
                    i++;
                    msg.append(value);
                    if (i < values.length) {
                        msg.append("｜");
                    }

                }
            }
            msg.append("]");
        }
        log.error(msg.toString(), ex);
    }

    
    public boolean isAjax(HttpServletRequest request) {
        boolean flag = false;
        String isAjax = request.getHeader("x-requested-with");
        if (!StringUtils.isEmpty(isAjax)) {
            flag = true;
        }
        return flag;
    }


	public RedisService getRedisService() {
		return redisService;
	}


	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}
   
	
}
