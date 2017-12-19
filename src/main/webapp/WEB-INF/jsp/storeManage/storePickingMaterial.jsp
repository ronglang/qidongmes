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
		<title>领料管理</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath %>app/js/storeManage/storePickingMaterial.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
    	</script>
   		<style>
			#num1{
				width: 96%;
				margin-left: 2%;
				margin:0 auto;
			}
			#num1>div{
				border: 1px solid deepskyblue;
				float: left;
				width: 33%;
				height: 200px;
				margin:0 auto;
			}
			#num2{
				width: 96%;
				margin-left: 2%;
				min-height:400px; 
			}
			#num21{
				height:300px;
				margin-top:6px;
			}
		</style>
	</head>
	<body style="padding:6px;background:#E0EEE0;overflow:hidden;">
			<div id="total">
				<div id="num1">
					<h2>今日领料记录：</h2>
					<div id="num11" ></div>
					<div id="num12"></div>
					<div id="num13"></div>
				</div>
				<div id="num2">
					<h2>历史领料记录：</h2>
					<lable>开始时间：</lable><input id="startTime" name="startTime" type="date"/>
					<lable style="margin-left:15px;">结束时间：</lable><input id="endTime" name="endTime" type="date"/>
					<lable>材料名称：</lable><select id="objSort" name="objSort"><option selected="selected" id="flagOpt">---&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</option></select>
					<lable>型号：</lable><input id="objGgxh" name="objGgxh"/>
					<lable >RFID卡编号：</lable><input id="rfidCode" name="rfidCode" style="margin-left:20px;"/>
					<button class="l-icon-search" onclick="ComprehensiveSerch()" style="margin-left:25px;" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
					<button class="l-icon-print" onclick="ResultPrint()" style="margin-left:120px;" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
					<div id="num21"></div>
				</div>
			</div>	
	</body>
</html>

