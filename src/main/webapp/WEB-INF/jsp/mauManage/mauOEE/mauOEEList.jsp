<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>OEE生产分析</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
	<script type="text/javascript">
	var grid;
		$(function(){
			$("#start").ligerDateEditor({  labelWidth: 80, labelAlign: 'left' });
			$("#end").ligerDateEditor({  labelWidth: 80, labelAlign: 'left' });
			queryTable();
		});
		
		function queryTable(){
			var param = initParam();
			grid= $("#maingrid").ligerGrid({
				height: '100%',
				url:'<%=basePath %>rest/mauOEEManageAction/getPageList?param='+param,
				checkbox: false,
				columns:[
							{display: '机台code', name: 'macCode'},
							{display: '工序名称', name: 'seqName'},
							{display: '统计时间', name: 'createDate'},
							{display: '实际产出', name: 'fact_output'},
							{display: '满载产出', name: 'fullyOutput'},
							{display: '不良品', name: 'rejects'},
							{display: '零碎品', name: 'bitsPieces'},
							{display: '过量消耗品', name: 'overdoes'},
							{display: '开机时间', name: 'startMin'},
							{display: '停机时间', name: 'endMin'},
							{display: '满载速度', name: 'fullySpeed'},
							{display: '主操作手', name: 'mainOperator'},
							{display: '副操作手', name: 'secondOperator'},
							{display: '速度满载率(PR)', name: 'rateSpeed'},
							{display: '开机率(OR)', name: 'rateStart'},
							{display: '正品率(QR', name: 'rateQuality'},
							{display: 'OEE', name: 'oee'},
							{hide: 'id', name: 'id',width:'1'}
					         ], rownumbers:true,pagesize:15,pageSizeOptions:[15,30,45,60]
				
			});
			$("#pageloading").hide();
		}
		
		function initParam(){
			var params= {};
			if ($("#start").val() != null && $("#start").val() != "") {
				params.start = $("#start").val();
			}
			if ($("#end").val() != null && $("#end").val() != "") {
				params.end = $("#end").val();
			}
			if ($("#seqName").val() != null && $("#seqName").val() != "") {
				params.seqName = $("#seqName").val();
			}
			if ($("#macCode").val() != null && $("#macCode").val() != "") {
				params.seqName = $("#macCode").val();
			}
			if ($("#oee").val() != null && $("#oee").val() != "") {
				params.oee = $("#oee").val();
			}
			//params.end = $("#end").val();
			return JSON.stringify(params);
		}
		
		function exportExcel(){
			var param = initParam();
			window.open("<%=basePath %>rest/mauOEEManageAction/exportExcel?param="+param,"_self");
			//window.open("_self","<%=basePath %>rest/mauOEEManageAction/exportExcel?param="+param);
		}
	</script>	
	<style type="text/css">
		*{
				margin: 0px;
				padding: 0px;
			}
			
		#tb table{
				width:100%;
				margin: 0 auto;
			}
		#tb table tr{
				height: 30px;
			}
		#tb table tr td{
			}
		#tb table tr td span{
				display: inline-block;
				width: 80px;
				text-align: right;
				font-size:14px;
			}
		#tb table tr td input{
			width:150px;
			height:25px;
		}
		#tb table tr td select{
				width: 120px;
			}
		#tb .search,#tb .export{
			width:80px;
			height:25px;
			text-align:center;
			line-height: 25px;
			display: inline-block;
			font-size:13px;
			
			border-radius:5px;
			border:none;
			cursor: pointer;
			margin-left: 10px;
			color: white;
		}
		#tb .search{
			background-color: blue;
		}
		#tb .export{
			background-color: green;
		}
	</style>
  </head>
  
  <body style="overflow-x:hidden; padding:2px;">
  <div id="tb"> 
 		<table border="0" cellspacing="0" cellpadding="0"  width="80%">
 		<tr>
 			<td>
	 			<span>检测时间:</span><input id="start" type="text" />
	 			<span style="width:20px; text-align: center;">到</span><input id="end" type="text" />
 			</td>
 			<td><span>工序名称:</span><input id="seqName" type="text" /></td>
 			<td><span>机台code:</span><input id="macCode" type="text" /></td>
 		</tr>
 		<tr>
			<td><span>oee :</span><input id="oee" type="text" placeholder="可填入&gt;0.85,&lt;0.95等条件" /></td>
			<td>&nbsp;</td>
 			<td>
 				<button class="search" onclick="queryTable()">搜  索</button>
 				<button class="export" onclick="exportExcel()">导  出</button>
 			</td>
 		</tr>
 		</table>
 	</div>
     <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div id="maingrid"></div>
	<div style="display:none;"></div>
  </body>
</html>
