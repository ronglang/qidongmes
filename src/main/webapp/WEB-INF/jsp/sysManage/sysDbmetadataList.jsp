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
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysDbmetadataList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/sysManage/sysDbmetadataList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "sysDbmetadataManage";    
    	</script>
	</head>

	<body style="padding:6px; overflow:hidden;">
		<div id="message" style="width:800px"></div>
		<div class="l-loading" style="display:block" id="pageloading"></div> 
			<form id="sysDbmetadataManageListForm">
				<fieldset class="l-fieldset">
			 		<legend class="l-legend">
						
					</legend>
				
				</fieldset>
				<br/>
				<button type="button" onclick="pub_del(routeName)" class="btn-del" >删除</button>
				<button type="button" id="add" class="btn-add" >新增</button>
				<!-- <button type="button" id="query" class="btn-add" onclick="javascript:advQuery()">高级查询</button> -->
				<button type="button" id="export" class="btn-add" onclick="javascript:exportExcel()">导出excel</button>
				<div id="sysDbmetadataManageList" style="margin:0; padding:0"></div>
			</form>
		  	<div style="display:none;">
		</div>
	</body>
</html>
<script type="text/javascript">
function advQuery(){
	top.$.ligerDialog.open({
		name: "advQueryDialog",
		url : basePath + "rest/"+routeName+"Action/toAdvQueryPage",//根据元数据表获得元数据
		//url : basePath + "rest/"+routeName+"Action/toAdvQueryPageEx",//通过hibernate注解获得元数据
		height : 500,
		width : 520,
		modal: true
	});
}

function exportExcel(){
	window.location.href =  basePath + "rest/"+routeName+"Action/exportExcel";
}
</script>
