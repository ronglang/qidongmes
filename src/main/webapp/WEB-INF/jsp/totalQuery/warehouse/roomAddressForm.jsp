<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String address = request.getParameter("address");
	
	
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"src="<%=basePath%>core/js/echarts-all-3.js"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
</head>
<style>

</style>
<script type="text/javascript">
	var add = "<%=address%>";
	var form;
	$(function() {
		$.ajax({
			url:'<%=basePath%>rest/statStoreObjVoManageAction/getStoreMrecord?address='+add,
			type:'POST',
			dataType:'JSON',
			success:function(mrecord){
				forms();
				setDatas(mrecord);
			},
			error:function(){
				
			}
			
			
			
		});
		
	});
	
	function forms() {
		//创建表单结构 
		form = $("#form2").ligerForm({
			inputWidth: 100,
			labelWidth: 80,
			space: 10,
			fields: [
				{ display: "规格型号", name: "materGgxh",newline: true,type: "text",abelAlign:'right'},
				{ display: "颜色", name: "materColor",newline: true,type: "text",abelAlign:'right'},
				{ display: "位置", name: "address",newline: true,type: "text",abelAlign:'right'},
				{ display: "库存数量", name: "actStock",newline: true,type: "text",abelAlign:'right'},
				{ display: "入库时间", name: "createDate",newline: true,type: "text",abelAlign:'right'}
			],
			validate: true
		});

	}
	
	function setDatas(bean){
		form.setData(bean);
	}
	
	
</script>

<body>
	<div class="top"
		style="width: 95%;height:360px;margin:0 auto; margin-top:0">
		<div style="text-align: center;position: absolute;left:0px;top: 0px;">
				<form id="form2" action="">
				</form>
		</div>
		<div id="container"
			style="height: 100%;width: 96%;text-align: center;margin: 0 auto"></div>
	</div>
</body>

</html>