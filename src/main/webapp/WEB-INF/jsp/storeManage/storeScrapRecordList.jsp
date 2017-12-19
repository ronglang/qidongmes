<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<title>报废统计记录</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/storeManage/storeScrapRecordList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "storeScrapRecordManage";    
    	</script>
	</head>

	<body style="padding:6px; overflow:hidden;">
	<div style="height:50px;">
		<span style="color:gray;font-size:20px;display:inline-block;float:left;line-height: 50px;">废料处理统计：</span><br>
		<div id="getsum" style="color:red;margin-left:10px;font-size:20px;float: left;">${msg}</div>
	</div>
	
	<div style="width:100%;height:80px;">
		<div id="queryForm" class="liger-form" style="float:left;font-weight: bold;"></div>
		<div style="height:60px;margin-left:50px;padding-top:30px;">
			<button type="button" onclick="searchForm()">搜索</button>
			&nbsp&nbsp
			<button type="button" onclick="resetForm()">重置</button>
			&nbsp&nbsp
			<button type="button" onclick="exportExecl()">导出</button>
			
		</div>
	</div>
	
		<div id="message" style="width:800px"></div>
		<div class="l-loading" style="display:none" id="pageloading"></div> 
			<form id="storeScrapRecordManageListForm">
				<fieldset class="l-fieldset">
			 		<legend class="l-legend">
					</legend>
				
				</fieldset>
				<br/>
				<!-- <button type="button" id="add" class="btn-add" ></button>
				<button type="button" onclick="pub_del(routeName)" class="btn-del" ></button> -->
				<div id="storeScrapRecordManageList" style="margin:0; padding:0"></div>
			</form>
		  	<div style="display:none;">
		</div>
	</body>
</html>

