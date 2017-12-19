<!-- 按钮权限控制 公共jsp. 引入本jsp,com.css.business.web.sysManage.bean.SysMenu-->
<%@ page language="java" pageEncoding="UTF-8" import="java.util.List"%>
<%
   String btn_menu_str = session.getAttribute("menu_btn").toString();
   String btn_menu_not_str = session.getAttribute("menu_btn_not").toString();
   String basePath_inf_b = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/";
%>

<!DOCTYPE html >
<html> 
<head>
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath_inf_b%>app/images/fupin.ico" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<script src="<%=basePath_inf_b%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
 <script type="text/javascript">
       $(function () {  
          //不能直接隐藏所有按钮。不受权限控制的如查看按钮，是默认显示，不必控制。
           //$('input[type="button"]').css('display','none');
           //$('input[type="submit"]').css('display','none');
             var role_btn_attr_not = [<%=btn_menu_not_str %>];
           //无权限的隐藏
           $.each(role_btn_attr_not,function(i,code){
        	   button_controlHide(code);//按钮隐藏
/*         	         if(code == '109003002_btn_toolbar_bfjg'){
        	         var o =$('#109003002_btn_toolbar_bfjg');
        	   		alert(o);     	
        	   		o.css("display","none");
        	   }
 */        	  
 			   //href_controlHide(code);//grid href
    	   });
           //有权限的控制显示
             var role_btn_attr = [<%=btn_menu_str %>];
    	   $.each(role_btn_attr,function(i,code){
    		   button_controlShow(code);//按钮显示
    	   });
       });
       
       function button_controlShow(code){//true:显示，false:隐藏
    	   	    var o = $("#"+code);
		   if(o && o[0] && o.prop instanceof Function && o.prop("display") == "none"){
			  	o.css("display","block");
		   }
       }
       function button_controlHide(code){//true:显示，false:隐藏
    	   	    var o = $("#"+code);
		   if(o){
			  	o.css("display","none");
			  	//alert('ttttttt:'+','+code+','+o+','+o[0]);
			  	//o.css("border","1px blue solid");
		   }
       }
       
       function grid_button_controlShow(code){//true:显示，false:隐藏
    	   
       }
       
       function href_controlHide(code){//true:显示，false:隐藏
	       	  if(grid && grid != 'undefined'){
	       		//构建表格（$('#list-grid').ligerGrid(options); ）的时候在options中添加事件
	       		var p = {"onAfterShowData":function(){this.find('#'+code).css("display","none");}};
	       		alert(grid+','+p); 
	       		grid.setOptions(p);
	       	  }
       }
 </script>
</head>

<body style="width: 99.8%">
     
</body>
</html>