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
		<title>craRouteList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/craManage/craRouteList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "craRouteManage";    
    	</script>
    	<style>
    		 #tb tr td{
    		 	border-bottom : 1px solid blue;
				height:20px;
				font-size: 15px;
			}
			#tb{
				text-align: center;
			}
			#tb tr td input{
    		 	border-bottom : 1px solid blue;
				height:20px;
				font-size: 15px;
			}
			 #tb1 tr td{
    		 	border-bottom : 1px solid blue;
				height:20px;
				font-size: 15px;
			}
			#tb1{
				text-align: center;
			}
			#tb1 tr td input{
    		 	border-bottom : 1px solid blue;
				height:20px;
				font-size: 15px;
			}
			
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
				background-color: #64C4E5;
			}
    	</style>
	</head>

	<body style="padding:6px; overflow:hidden;">
		<!-- 
		<label class="l-icon-add " style="margin-left:30px;margin-right:8px;" onclick="addNewRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>添加
		<label class="l-icon-delete" style="margin-left:30px;margin-right:8px;" onclick="deleteRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>删除
		<label class="l-icon-modify" style="margin-left:30px;margin-right:8px;" onclick="updateRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>修改
		 -->
		 <span>工序路线编码：</span>
		<input id="serchByRouteCode" name="serchByRouteCode" value="" placeholder="请输入工序路线编码" style="margin-left:10px;margin-right:10px;width:150px;height:25px;"/>
		<!--
		<label class="l-icon-search" onclick="serchByRouteCode()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
		<label class="l-icon-view " style="margin-left:60px;margin-right:8px;" onclick="detailsRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>详情
		 -->
		 
		 <input type="button" name="" id="sort" onclick="serchByRouteCode()" value="查  询" />
		 
		<div class="l-clear"></div>
		<!-- 这是展示页的div -->
		<div id="maingrid" style="margin-top:20px"></div>
		<!-- 这是详情页的div -->
		<div id="detailRowDiv"  style="margin-top:0px;display:none"></div><br/><br/>
		<!-- 这是修改页的div -->
    	<div id="modifydiv" style="margin-top:5px;display:none">
    		<div id="modifydiv1">
	    		<form id="modifyform" name="modifyform">
				    <table align="center" id="tb">
				        <tr>
				            <td colspan=2><label for="routeCode">工序路线编码:</label></td>
				            <td><input id="routeCode" name="routeCode" type="text"/></td>
				             <td colspan=2><label for="routeName">工序线路名称:</label></td>
				            <td><input id="routeName" name="routeName" type="text"/></td>
				            <td colspan=2><label for="createBy">创建人:</label></td>
				            <td><input id="createBy" name="createBy" type="text"/></td>
				            <td colspan=2><label for="createDate">创建时间:</label></td>
				            <td><input id="createDate" name="createDate" type="date"/></td>
				        </tr>
				  </table>
				</form>
    		</div>
    		<div id="modifydiv2" style="margin-top:10px"></div><br/><br/>
    	</div>
    	<!-- 这是新增页的div -->
    	<div id="adddiv" style="margin-top:5px;display:none">
    		<div id="adddiv1">
	    		<form id="addform" name="addform">
				    <table align="center" id="tb1">
				        <tr>
				            <td colspan=2><label for="routeCode1">工序路线编码:</label></td>
				            <td><input id="routeCode1" name="routeCode1" type="text"/></td>
				             <td colspan=2><label for="routeName1">工序线路名称:</label></td>
				            <td><input id="routeName1" name="routeName1" type="text"/></td>
				            <td colspan=2><label for="createBy1">创建人:</label></td>
				            <td><input id="createBy1" name="createBy1" type="text"/></td>
				            <td colspan=2><label for="createDate1">创建时间:</label></td>
				            <td><input id="createDate1" name="createDate1" type="date"/></td>
				        </tr>
				  </table>
				</form>
    		</div>
    		<div id="adddiv2" style="margin-top:0px"></div><br/><br/>
    	</div>
			
	</body>
</html>

