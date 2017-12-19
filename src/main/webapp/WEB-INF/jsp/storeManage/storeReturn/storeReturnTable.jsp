<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String storeReturn_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退料列表</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeReturnManage"; 
	var row_id = "";
	row_id =<%=storeReturn_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/storeManage/storeReturn/storeReturnTable.js" type="text/javascript"></script>
<!-- 默认引用end -->
<style type="text/css">
	.div1{
		text-align: center;
		font-size: 14px;
	}
	.word1{
		font-size: 14px;
	}
</style>
</head>

<body>
	<body style="overflow-x:hidden; padding:2px;overflow:auto;">
	<div class="div1">
		<!-- <lable>开始时间：</lable><input id="start"  type="text"/>
		<lable style="margin-left:15px;">结束时间：</lable><input id="end" name="end" type="text"/>
					<lable>材料名称：</lable>
							<select id="material">
								<option value="">请选择材料</option>	
								<option value="胶料">胶料</option>	
								<option value="铝料">铝料</option>	
								<option value="铜料">铜料</option>	
							</select>
					<lable>型号：</lable><input id="model" name="model"/>
					<button  value="查询" onclick="doSearch()" style="margin-left:25px;" >查询&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
					<button value="导出" onclick="getExcel()" style="margin-left:120px;" >导出&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button> -->
	
		<span class="word1">时间:<input id="start" type="text"></span class="word1">到<span><input id="end" type="text"></span>
		<span class="word1">材料:
			<select id="material">
				<option value="">请选择材料</option>	
				<option value="胶料">胶料</option>	
				<option value="铝料">铝料</option>	
				<option value="铜料">铜料</option>	
			</select></span>
		型号:<span><input id="model"></span>
		颜色:<span><input id="corlour"></span>
		<span><button  id="doSearch" onclick="doSearch()"> 查询</button></span>
		<span><button id="getExcel" onclick="getExcel()">导出</button></span>
	</div>
		<div class="l-loading" style="display:none" id="pageloading"></div>
 		<div class="l-clear"></div>
    	<div id="maingrid" style="overflow: hidden;"></div>
 		<div style="display:none;">
</div>
</body>
</html>

