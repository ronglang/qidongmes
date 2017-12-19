<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html style="height: 100%">

<head>
<meta charset="utf-8">
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>core/js/echarts.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>core/js/jquery-2.1.4.min.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
</head>

<body style="height: 99%; margin: 0;background-color: #F7F7F7;overflow: hidden;">
	<div
		style="text-align: center;position: absolute;left: 150px;z-index: 999;top: 9px;">
		<form id="form2" action=""></form>
		<div class="liger-button" onclick="search()"
			style="float: right;position: absolute;right: -100px;top: 1px; ">搜索</div>
	</div>
	<iframe id="plan" style="width:99%;overflow: hidden;height: 340px;margin-top: 50px;border: 1px solid #CDC9C9; " src=""></iframe>
	<iframe id="end" style="width:99%;overflow: hidden;height: 340px;border: 1px solid #CDC9C9;" src=""></iframe>
	<script type="text/javascript">
	$(function (){
		forms();
	});
	function forms() {
		//创建表单结构 
		form = $("#form2").ligerForm({
			inputWidth : 120,
			labelWidth : 60,
			space : 10,
			fields : [ {
				display : "从",
				name : "start",
				newline : false,
				type : "date",
				editor : {
					format : "yyyy-MM-dd"
				}
			},{
				display : "到",
				name : "end",
				newline : false,
				type : "date",
				editor : {
					format : "yyyy-MM-dd"
				}
			},{
				display : "机台编码",
				name : "mac",
				newline : false,
				type : "select",
				editor : {
					url:'<%=basePath%>rest/mauMachineManageAction/getMachineCode',
					valueField:'text',
					isMultiSelect:true,
					isShowCheckBox:true
				},
			}
			],
			validate : true
		});

	}
	
	function search(){
		var start = $("[name=start]").val();
		var end = $("[name=end]").val();
		var mac = $("[name=mac]").val();
		var url = "<%=basePath%>rest/plaMachinePlanManageAction/toGanTe?start="
				+start+"&end="+end+"&mac="+mac+"&type=";
		$("#plan").attr("src", url+"计划");
		$("#end").attr("src", url+"结束");
	}
	
	</script>
</body>

</html>