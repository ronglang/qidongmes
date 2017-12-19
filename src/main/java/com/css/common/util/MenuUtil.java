package com.css.common.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("all")
public class MenuUtil {
	
	public static boolean checkMenu(HttpServletRequest request){
		boolean isMenu ;
		ServletContext application = request.getSession().getServletContext();
		 List<Object> men = (List<Object>) application.getAttribute("menu");
		 if(men==null||men.size()==0){
			 isMenu =false;
		 }else{
			 isMenu = true;
		 }
		return isMenu;
	}
}
