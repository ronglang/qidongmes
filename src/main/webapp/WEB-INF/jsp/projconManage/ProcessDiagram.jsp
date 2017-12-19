<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>厂房布局示意图</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath %>app/js/projconManage/ProcessDiagram.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
    	</script>
    	<style>
			body {
				background: #0464D7;
			}
			#headtable {
				text-align: center;
				font-size: 30px;
    			color: #FFFFFF;
    			background: #299CFB;
    			height: 50px;
    			line-height: 50px;
    			width: 100%;
			}
			#headtitle{
				font-size: 34px;
			}
			#content{
				margin-top: 20px;
				background: #0464D7;
				padding: 20px 100px;
			}
			#time{
				text-align: left;
				font-size:18px ;
			}
			#contentlist{
				border: 1px solid white;
			}
			#ulist li{
				font-size: 30px;
				color: #FFFFFF;
				border: 1px solid cyan;
				width: 182px;
				height: 200px;
				text-align: center;
				display:inline-block;
			}
			#ulist li h3{
				font-size: 24px;
			}
			#ulist li img{
				width: 130px;
				height: 130px;
			}
		</style>
  </head>
  <!--
    	作者：zeng
    	时间：2017-07-19
    	描述：厂房布局图，展示所有的工序
    	工序：拉丝、绞线、绝缘、挤护套、成缆、铠装、分盘
   -->
 <body>
		<div id="main">
			<table id="headtable">
				<tr>
					<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td id="headtitle">东莞启动电力电缆有限公司第一厂房</td>
					<td id="time"></td>
				</tr>
			</table>
			<div id="content">
				<div id="contentlist">
					<ul id="ulist">
						<li>
							<h3>拉丝</h3>
							<img src="<%=basePath %>app/images/projconManage/工序示意图.jpg" onclick="JumpToMac('拉丝')">
						</li>
						<li>
							<h3>绞线</h3>
							<img src="<%=basePath %>app/images/projconManage/工序示意图.jpg" onclick="JumpToMac('绞线')"/>
						</li>
						<li>
							<h3>绝缘</h3>
							<img src="<%=basePath %>app/images/projconManage/工序示意图.jpg" onclick="JumpToMac('绝缘')"/>
						</li>
						<li>
							<h3>挤护套</h3>
							<img src="<%=basePath %>app/images/projconManage/工序示意图.jpg" onclick="JumpToMac('挤护套')"/>
						</li>
						<li>
							<h3>成缆</h3>
							<img src="<%=basePath %>app/images/projconManage/工序示意图.jpg" onclick="JumpToMac('成缆')"/>
						</li>
						<li>
							<h3>铠装</h3>
							<img src="<%=basePath %>app/images/projconManage/工序示意图.jpg" onclick="JumpToMac('铠装')">
						</li>
						<li>
							<h3>分盘</h3>
							<img src="<%=basePath %>app/images/projconManage/工序示意图.jpg" onclick="JumpToMac('分盘')"/>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
