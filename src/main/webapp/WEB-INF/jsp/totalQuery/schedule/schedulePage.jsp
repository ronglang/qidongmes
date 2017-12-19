<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>进度查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
	
	<script type="text/javascript">
		var basePath  = '<%=basePath%>';
		$(function(){
			
			var url = basePath + "rest/plaCourseManageAction/getScheduleGrid";
			ligerGridShow(url);
			
			clickShow();
			
		});
		
		function ligerGridShow(url){
			
			grid = $("#maingrid").ligerGrid(
					{
						url : url,
						//checkbox : true,
						columns : [
						        {hide : '序号',name : 'id',},
								//{display : '创建日期',name : 'createDate'},
								//{display : '创建人',name : 'createBy'},
								{display : '工作单编码',name : 'wsCode',},
								{display : '工作单类型',name : 'wsType'},
								{display : '描述信息',name : 'remark'},
								{display : '合同编号',name : 'scCode'},
								{display : '批次号',name : 'batCode'},
								{display : '型号规格',name : 'headGgxh'},
								{display : '颜色',name : 'color',},
								{display : '扎装段长',name : 'headZzdc'},
								{display : '扎装段数',name : 'headZzds'},
								{display : '总数量',name : 'totalAmount'},
								//{display : '生产通知单ID',name : 'manuNoticeId'},
								//{display : '客户id',name : 'cusId'},
								//{display : '开单日期',name : 'billDate'},
								//{display : '是否完成',name : 'isFinish'},
								//{display : '工艺id',name : 'craftId'},
								//{display : '审核标志',name : 'auditFlag'},
								//{display : '审核时间',name : 'auditTime'},
								//{display : '过程名称',name : 'courseName'},
								//{display : '启用标志，是否启用',name : 'useFlag'},
								//{display : '生产原则',name : 'manuTenet'},
								//{display : '过期标志，是否已过期',name : 'pastFlag'},
								//{display : '计划启用日期',name : 'planEnableDate'},
								
							],
						pageSize : 10,
						width : '99%',
						height : '60%',
						onSuccess : function(json, grid) {
							$("#pageloading").hide();
						},
						onError : function(json, grid) {
							$("#pageloading").hide();
						}
						,
						toolbar :{
							items : [
					          {text:"导出",click:exportExcel,icon:"print"},
					          { line: true },
							]
						}
					});
			
		}
		function exportExcel(){
			var wsCode = $("#sartch input[name='wsCode']").val();
			var xCode=$("#sartch input[name='xCode']").val();
			
			var data = {};
			if(wsCode!=null&&wsCode!=""){
				data.wsCode = wsCode;
			}
			if(xCode!=null&&xCode!=""){
				data.xCode=xCode;
			}
			var param = JSON.stringify(data);
			debugger;
			var url=basePath + "rest/plaCourseManageAction/exportExcel?param="+param;
			//window.open(url,"_self");
			window.location.href = basePath + "rest/plaCourseManageAction/exportExcels?param="+param;
			
		}
		function clickShow(){
			$("#btn").click(function(){
				var wsCode = $("#sartch input[name='wsCode']").val();
				var xCode=$("#sartch input[name='xCode']").val();
				
				var data = {};
				data.wsCode = wsCode;
				data.xCode=xCode;
				var param = JSON.stringify(data);
				
				
				
				
				var url = basePath + "rest/plaCourseManageAction/getScheduleGrid?param="+param;
				ligerGridShow(url);
				
			});
			$("#res").click(function(){
				var url = basePath + "rest/plaCourseManageAction/getScheduleGrid";
				ligerGridShow(url);
			});
			
		}
		

		
	</script>
	<style type="text/css">
		#sartch{
			height:50px;
			line-height:50px;
			width:100%;
		}
		#btn {
			width:80px;
			height:25px;
			border: none;
			background-color: #03A2DB;
			border-radius:5px;
			color:white;
			cursor: pointer;
			margin-left:20px;
		}
		#btn:hover{
			background-color: #74C1DB;
		}
		#res{
			width:80px;
			height:25px;
			border: none;
			background-color: #EDA71A;
			border-radius:5px;
			color:white;
			cursor: pointer;
			margin-left:20px;
		}
		#res:hover{
			background-color: #E0BF76;
		}
		#sartch label{
			margin-left:20px;
		}
	</style>
	
  </head>
  
  <body>
  
  	<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"></div>
		<div id="sartch">
		<form>
			<label>
				<span>工作单编码：</span>
				<input type="text" name="wsCode" />
			</label>
			<label>
				<span>型号规格：</span>
				<input type="text" name="xCode" />
			</label>
			<input type="button" name="sartch" id="btn" value="查  询" />
			<input type="reset" name="res" id="res" value="重  置" />
		</form>
		</div>
		<div style="width:100%;margin:0 auto;">
			<div id="maingrid"></div>
		</div>
		
		<div style="display:none;">
	</div>
  
  </body>
</html>
