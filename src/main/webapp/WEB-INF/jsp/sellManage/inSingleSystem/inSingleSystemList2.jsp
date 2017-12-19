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
    <title>在单系统</title>
    <link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>  
<!--     <script src="<%= basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>     -->
    
    <script src="<%= basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<!--     <script src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>   -->
<!--     <script src="<%= basePath%>app/js/sellManage/lib/jquery.cookie.js"></script> -->
<!--     <script src="<%= basePath%>app/js/sellManage/lib/json2.js"></script> -->
<!--     <script src="<%= basePath%>app/js/indexdata.js" type="text/javascript"></script> -->
    
    <script src="<%= basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="<%= basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
    <script src="<%= basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="<%= basePath%>app/js/sellManage/inSingleSystem/inSingleSystemList2.js" type="text/javascript"></script>
    
    <script type="text/javascript">
 		var basePath = '<%= basePath%>';
    </script> 
<style type="text/css"> 
</style>
</head>
<body style="padding:0px;background:#EAEEF5;">  
<div id="navtab1" style="width: 100%;  border: 1px solid #D3D3d3;" class="liger-tab">
	<div tabid="yesProduced" title="已生产" lselected="true" style="height: 100%">
		<form id="myForm1"></form>
		<div id="myGrid1"></div>
	</div>
	<div tabid="notProduced" title="未生产" showclose="true" >
		<form id="myForm2"></form>
		<div id="myGrid2"></div>
	</div>
</div>
	
</body>
</html>

