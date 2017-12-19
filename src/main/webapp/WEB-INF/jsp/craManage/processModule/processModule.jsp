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
		<title>craSeqRelationList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<%-- <script src="<%=basePath %>app/js/craManage/processModule/processModule.js" type="text/javascript"></script> --%>
		<script src="<%=basePath %>app/js/craManage/craSeqParamList.js" type="text/javascript"></script>
		<%-- <script src="<%=basePath %>app/js/craManage/craProSeqRelationCoreList.js" type="text/javascript"></script> --%>
		<script>
			var basePath  = '<%=basePath%>';
			var proCraftCode = null;
			var routeName = "craSeqRelationManage";    
    	</script>
	</head>

	<body style="padding:6px; ">
		<div style="width:100%;height:100%; overflow-y:none; ">
			<!-- <table  style="width:100%;height:100%; ">
				<tr>
					<td style="width:25%;">
						<div id="myTree" style="width:100%;height:100%;overflow-y:scroll;">
					</td>
					<td style="width:75%;">
						<div id="myGrid1" style="width:100%;height:100%;" ></div>
					</td>
				</tr>
			</table> -->
			<div id="myTree" style="width:30%;height:100%;float:left; overflow-x:auto;"></div>
			<div style="width:70%;height:100%;float:left;">
				<div id="myGrid1" style="width:100%;height:100%;" ></div>
			</div>
		</div>
		<!-- <div id="myGrid2" style="width:100%;height:100%;">		</div> -->
		<iframe id="f1" width="80%" height="500px" style="float:left;" src="<%=basePath%>rest/craSeqParamManageAction/toListPage?"
		frameborder="0">
		</iframe>
	</body>
</html>

