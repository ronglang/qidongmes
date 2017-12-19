<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品类型</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>app/js/mauManage/mauProductTypeManage.js"></script>
    <script type="text/javascript">
    	var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
    </script>
  </head>
  
  <body>
  
  	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
    
    <div id="maingrid">
    </div>
    <div id="showKey" style="display:none;margin:10px 0 0 20px;">
    	<b>类型：</b>
        <select id="sel_type" name="sel" style="width:150px;height:25px;">
        	<option value="成品类型">成品类型</option>
        	<option value="半成品类型">半成品类型</option>
        </select>
        <b>产品类型：</b>
        <input type="text" id="txtKey" style="width:150px;height:25px;"/>
    </div>
    
  </body>
  
</html>
