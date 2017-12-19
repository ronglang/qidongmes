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
		<title>sysRoleList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/sysManage/sysRoleList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "sysRoleManage";    
    	</script>
    
	</head>

	<body style="padding:0px; overflow:hidden;">
		<div id="message" style="width:800px"></div>
		<div class="l-loading" style="display:block" id="pageloading"></div> 
			<form id="sysRoleManageListForm" style="margin:6px;">
				<fieldset class="l-fieldset">
			 		<legend class="l-legend">
						
					</legend>
				
				</fieldset>
				<br/>
				<button type="button" onclick="pub_del_role(routeName)" class="btn-del" ></button>
				<button type="button" id="add" class="btn-add" ></button>
				<!-- <button type="button" onclick="authorityManagement()" type="button" id="authority" class="btn-authority" ></button> -->
				<br/>
				<div id="sysRoleManageList" style="margin:0; padding:0"></div>
			</form>
		  	<div style="display:none;">
		</div>
	</body>
</html>
