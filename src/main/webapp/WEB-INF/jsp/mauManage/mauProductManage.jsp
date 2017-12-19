<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#t_select{
			margin:0 50px 20px 50px; 
		}
		#t_select h3{
			display:inline-block; 
			margin-right:50px;
			font-size:18px;
			color:#134683;
		}
		#t_select span{
			font-size:15px;
		}
		#t_select input{
			height:25px;
		}
		#t_select select{
			height:25px;
			width: 100px;
		}
		#sort{
			background-color: #0099CC;
			color:white;
			border:none;
			width:80px;
			height:25px;
			cursor: pointer;
			border-radius:5px;
			margin-left:20px;
		}
		#sort:hover{
			background-color: #50B9DE;
		}
	</style>
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    
    <script type="text/javascript">
    	var basePath = "<%=basePath%>";
		var urlPath = basePath.substring(7, basePath.length);
    </script>
    
    <style type="text/css">
    	#sort{
			background-color: #0099CC;
			color:white;
			border:none;
			width:80px;
			height:25px;
			cursor: pointer;
			border-radius:5px;
			margin-left:10px;
		}
		#sort:hover{
			background-color: #71CEED;
		}
    </style>
  </head>
  
  <body style="overflow-x:hidden; padding:2px;">
   <div class="l-loading" style="display:block" id="pageloading"></div>
	 <div class="l-clear"></div>
	<div id="t_select">
		<h3>条件查询</h3>
		<span>产品规格型号：</span><input type="text" name="" style="margin-right:20px" id="pro_name">
		<span>产品类型：</span><!-- <input type="text" name="" id="pro_type"/> -->
						<select id="pro_type">
							<option></option>
							<option>半成品</option>
							<option>成品</option>
						</select>
		<input type="button" name="" id="sort" value="查  询" />
	</div>
	<div id="maingrid"></div>
	   
	<div style="display:none;"></div>
	
    <div id="showKey" style="display:none;margin:10px 0 0 20px;">
        <b>请输入产品类型：</b>
        <input type="text" id="txtKey" style="width:150px;height:25px;"/>
    </div>
    <!-- 
    <div id="showType" style="display:none;">
        <table border="1" cellpadding="0" cellspacing="0">
        	<tr>
        		<th>编号</th>
        		<th>类型</th>
        	</tr>
        </table>
    </div>
	 -->

	<script src="<%=basePath %>app/js/mauManage/mauProductManage.js"></script>
</body>
</html>
