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
		<title>plaCourseList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/plaManage/plaCourseList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "plaCourseManage";
			//debugger;
    	</script>
    	<style type="text/css">
    		#condition{
    			padding-top: 10px;
    		}
    		#condition input.btn{
    			margin-left: 20px;
    			width: 80px;
    			height:20px;
    			background-color: #0099CC;
    			color: white;
    			border:none;
    			cursor: pointer;
    			border-radius:3px;
    		}
    	</style>
	</head>

	<body style="padding:6px; overflow:hidden;">
		<div id="message" style="width:800px"></div>
		
		<div class="l-loading" style="display:block" id="pageloading"></div> 
		<div id="condition">
			<label>
				<span>工作单类型：</span>
				<select name="wsType">
				</select>
			</label>
			<label>
				<span>工作单编码：</span>
				<input type="text" name="workCode">
			</label>
			<label>
				<span>合同编号：</span>
				<input type="text" name="contractCode">
			</label>
			
			<label>
				<input type="button" class="btn" onclick="conditionSelect();" value="查询">
			</label>
		</div>
		
			
		
			<form id="plaCourseManageListForm">
				<fieldset class="l-fieldset">
			 		<legend class="l-legend">
						
					</legend>
				
				</fieldset>
				<br/>
				
				<div id="plaCourseManageList" style="margin:0; padding:0"></div>
			</form>
		  	<div style="display:none;">
		</div>
	</body>
</html>

