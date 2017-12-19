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
		<title>bolgList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/userManage/blogList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "blogManage";    
    	</script>
	</head>

	<body style="padding:6px; overflow:hidden;">
		<div id="message" style="width:800px"></div>
		<div class="l-loading" style="display:block" id="pageloading"></div> 
			<form>
				<button type="button" onclick="pub_del(routeName)" class="btn-del" >删除</button>
				<button type="button" id="add" onclick="addBlog()" class="btn-add" >新增</button>
				<button type="button" id="add" onclick="video('天河湾11-1号')" class="btn-add" >视频监控</button>
				<button type="button" id="add" onclick="shiwuVideo();" class="btn-add" >水務视频监控</button>
				<div id="blogManageList" style="margin:0; padding:0"></div>
			</form>
		  	<div style="display:none;">
		</div>
	</body>
</html>

