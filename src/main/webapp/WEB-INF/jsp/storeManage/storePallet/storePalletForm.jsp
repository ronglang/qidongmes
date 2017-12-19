<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'storePalletForm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
		var routeName = "storePalletManage";
	</script>
	<link rel="icon" href="core/img/site_icon/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="core/img/site_icon/favicon.ico"/>
	<link rel="bookmark" href="<%=basePath%>core/img/site_icon/favicon.ico"/>
	<link rel="stylesheet" href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" />
	<link rel="stylesheet" href="<%=basePath%>app/css/storeSpec/storeSpec.css"/>
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>core/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>core/js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.min.js"></script><isindex>
	<script type="text/javascript" src="<%=basePath%>app/js/storeManage/storePallet/storePalletList.js"></script>
  </head>
  
  <body>
    <div id="Container">
    	<div id="InputArea"></div>
   		<div id="QueryArea">
    		<form id="queryform"></form>
   	    </div>
    	<div id="grid"></div>
    </div>
  </body>
</html>
