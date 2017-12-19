<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>APP 列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>core/css/bootstrap.css"  >
	<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.11.1.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			queryApps();
		});
	
		function queryApps(){
			var url="<%=basePath%>rest/aPPManageAction/getAllAPP";
			$.post(url,{},function(data){
				if(data.success){
					var html="";
					debugger;
					var apps = data.data;
					for(var i=0;i<apps.length;i++){
						html += "<tr><td>"+apps[i].appName+"</td>";
						html += "<td><button class=\"down\" onclick=appDown('"+apps[i].appName+"')>下载</button></td></tr>";
					}
					$("#appList").append(html);
				}
			},"json");
			
		}
		function appDown(fileName){
			fileName = fileName.replace(/\(/g,"%28");
			fileName = fileName.replace(/\)/g,"%29");
			window.open("<%=basePath%>rest/aPPManageAction/downAPP?fileName="+fileName,"_self");
		}
		
		function  saveE() {
			$.post("<%=basePath%>rest/aPPManageAction/saveE",{},function(data){},"json");
		}
	</script>
	
	<style type="text/css">
		h3{
			width: 100%;
			text-align: center;
		}
		
		.container{
			width: 100%;
		}
		.container table{
			width: 60%;
			margin: 0 auto;
			border: 1px solid #CCCCCC;
			padding-top: 50px;
			padding-bottom: 50px;
		}
		.container table tr{
			height: 35px;
			line-height: 35px;
		}
		.container table tbody tr:hover{
			background-color: #EADBCE;
		}
		.container table th,.container table td{
			width: 50%;
			text-align: center;
		}
		.container table td .down{
			border: none;
			background-color: #15428B;
			width: 55px;
			height: 27px;
			text-align: center;
			color: white;
			border-radius: 5px;
			cursor: pointer;
			margin-right: 10px;
		}
		.container table td .down:hover{
			color: yellow;
		}
	</style>
  </head>
  
  <body>
 <!--  <div class="container">
			<div class="row">
				<h1 class="col-md-6 col-md-offset-4">app下载</h1>
			</div>
	</div>
    <div class="container">
			<table class="table ">
				<thead>
					<tr>
						<th>名称</th>
						<th>操作</th>

					</tr>
				</thead>
				<tbody id="appList">
					
				</tbody>
			</table>
		</div> -->
		
		<h3>App下载</h3>
		<button onclick="saveE()"> 临时保存</button>
		<div class="container">
			<table class="table " cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="appList">
					<!-- <tr>
						<td>名称1</td>
						<td><button name="download" class="down">下载</button></td>
					</tr> -->
					
				</tbody>
			</table>
		</div>
  </body>
</html>
