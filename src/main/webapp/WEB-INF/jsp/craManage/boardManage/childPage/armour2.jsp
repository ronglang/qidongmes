<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>铠装工序电子看板</title>
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	
	
	<style>
		#main1{
			width: 100%;
			height: 280px;
		}
	</style>
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
		
	</script>
  </head>
  <body>
  		<div id="main1"></div>
  		
  		
  	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	
	<script src="<%=basePath%>core/js/board/armour2.js" type="text/javascript"></script>
  </body>
</html>
