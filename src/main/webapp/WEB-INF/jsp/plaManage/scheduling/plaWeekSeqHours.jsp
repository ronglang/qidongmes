<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String plaAbnormalInfor_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>plaAbnormalInforForm</title>
		
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>app/js/plaManage/scheduling/plaWeekSeqHours.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var ids = "${ids}";
			debugger;
    	</script>
	</head>

	<body>
		<form id="myForm">
		</form>
		<div id="myGrid"></div>
	</body>
	<script type="text/javascript">
// 	var routeName = "plaAbnormalInforManage"; 
// 	var row_id = "";
// 	row_id = ;
	
// 	$(document).ready(function() {
// 		if (row_id != null) {
// 			$("#PlaAbnormalInfor").ligerForm();
// 			var parm = "id="+row_id;
// 			pub_initForm(routeName,parm);
// 		}
// 	});
  	</script>
</html>

