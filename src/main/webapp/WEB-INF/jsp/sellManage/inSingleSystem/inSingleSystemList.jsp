<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>合同管理</title>
    <link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>  
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
    <script src="<%= basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>    
    <script src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  
    <script src="<%= basePath%>app/js/sellManage/lib/jquery.cookie.js"></script>
    <script src="<%= basePath%>app/js/sellManage/lib/json2.js"></script>
    <script src="<%= basePath%>app/js/indexdata.js" type="text/javascript"></script>
    <script src="<%= basePath%>app/js/sellManage/inSingleSystem/inSingleSystemList.js" type="text/javascript" ></script>
    <script type="text/javascript">
 		var basePath = '<%= basePath%>';
    </script> 
<style type="text/css"> 
</style>
</head>
<body style="padding:0px;background:#EAEEF5;">  
	<!-- 查询条件 -->
	<form id="myForm"></form>
	<div id="myGrid"></div>
</body>
</html>

