<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderCraftView.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
	</script>
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>app/js/sellManage/view/orderCraftView.js" type="text/javascript"></script>
  </head>
  
  <body>
    <canvas id="canvas" width="5000" height="2000">hello</canvas> 
    <script type="text/javascript">
    	
    </script> 
  </body>
</html>
