<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>mauForkliftList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/mauManage/mauForkliftList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "mauForkliftManage";    
    	</script>
	</head>
<body style="padding:6px; overflow:hidden;">
	<div style="width:100%;height:35px">
		<div id="queryForm" class="liger-form" style="float:left;"></div>
		<div style="margin-left:30px">
			<button type="button" onclick="searchForm()">搜索</button>
			&nbsp&nbsp&nbsp&nbsp&nbsp
			<button type="button" onclick="resetForm()">重置</button>
		</div>
	</div>
	<div id="mauForkliftManageList" style="margin:0; padding:0"></div>
</body>
</html>

