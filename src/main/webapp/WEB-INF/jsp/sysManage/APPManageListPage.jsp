<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>app管理页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>core/css/bootstrap.css"  >
	<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.11.1.min.js"></script>
	
	<script type="text/javascript">
	//是否上传成功的标准
	var flag = '${uploadFlag}';
	
	$(function(){
		if(flag == "true"){
			$("#flagTrue").show();
		}else if(flag == "false"){
			$("#flagFalse").show();
		}
		queryApps();
	});
	
	setTimeout(function(){
		$("#flagTrue").hide();
		$("#flagFalse").hide();
	}, 6000);

	function queryApps(){
		var url="<%=basePath%>rest/aPPManageAction/getAllAPP";
		$.post(url,{},function(data){
			if(data.success){
				var html="";
				debugger;
				var apps = data.data;
				for(var i=0;i<apps.length;i++){
					html += "<tr><td>"+apps[i].appName+"</td>";
					html += "<td><button class=\"down\"onclick=appDown('"+apps[i].appName+"')>下载</button> || <button class=\"del\" onclick=clearBean('"+apps[i].appName+"')>删除</button> </td></tr>";
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
	
	function clearBean(fileName){
		fileName = fileName.replace(/\(/g,"%28");
		fileName = fileName.replace(/\)/g,"%29");
		var url = "<%=basePath%>rest/aPPManageAction/clearBean";
		$.post(url,{fileName:fileName},function(data){
			if(data.success){
				alert(data.msg);
				location.reload();
			}else{
				alert(data.msg);
			}
		},"json");
	}
	</script>
	
	<style type="text/css">
		h3{
			width: 100%;
			text-align: center;
		}
		#head{
			width: 100%;
			height: 80px;
			line-height: 80px;
			text-align: center;
		}
		#head form .indon{
			display: inline-block;
			margin-right: 50px;
		}
		#head form input.don{
			width: 250px;
			border: 1px solid gray;
		}
		#head form input.sub{
			width: 70px;
			height: 27px;
			line-height: 27px;
			cursor: pointer;
			border: 1px solid #035AA2;
			background-color: white;
			border-radius: 5px;
			color: #035AA2;
			font-weight: bold;
		}
		#head form input.sub:hover{
			background-color: #035AA2;
			color: white;
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
		.container table td .del{
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
		.container table td .del:hover{
			color: red;
		}
	</style>
  </head>
  
  <body>
  	<div id="flagTrue" style="display: none">上传成功</div>
  	<div id="flagFalse" style="display: none">上传失败</div>
		
	<h3>App管理</h3>
		<div id="head">
			<form action="<%=basePath%>rest/aPPManageAction/appUpload" method="post" enctype="multipart/form-data">
				<div class="indon">
					<input type="file" name="file" id="" value="" class="don"/>
				</div>
				<input type="submit" value="提交" class="sub"/>
			</form>
		</div>
		<div class="container">
			<table class="table " cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="appList">
					
				</tbody>
			</table>
		</div>	
  </body>
</html>
