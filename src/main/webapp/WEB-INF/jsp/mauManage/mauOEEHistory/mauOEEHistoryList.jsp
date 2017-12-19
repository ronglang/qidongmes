<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台历史速度表格</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>core/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/css/public.css" rel="stylesheet" type="text/css" />
    
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/js/cssBase.js"></script>
   	<script type="text/javascript">
   		var grid;
   		$(function(){
   			setGrid();
   		});
   		
   		function setGrid(){
   			var param = initParam();
   			grid = $("#maingrid").ligerGrid({
		        height: '85%',
		        width: '100%',
		        url: '<%=basePath %>rest/mauOEEHistoryManageAction/getListPage?param='+param,
		        columns: [
				{ display: '工单编号', name: 'courseCode'},
				{ display: '机台编码', name: 'macCode'},
		        { display: '主操作人', name: 'mainOperator'},
		        { display: '规格型号', name: 'progxh'},
		        { display: '时间', name: ''},
		        {
				    display: '操作',
				    render: function (row)
				    {
				    	var html = '';
			    		html += '<a href="javascript:" onclick="javascript:toDetail(' + row.id + ',\''+row.idNumber+'\');return false;">查看详情</a>&nbsp;&nbsp;'
			    		;
				        return html;
				    }
				},
		        { hide:'id',name:'id',width:1}
		        ],
		       /*  toolbar: {
		            items: [
		            { text: '增加', click: addRows, icon: 'add'},
		            { text: '修改', click: updateRows, icon: 'modify'},
		            { text: '删除', click: deleteRows, icon: 'delete'},
		            { line: true },
		            ]
		        }, */
		        rownumbers: true,
		        enabledEdit: true,
		        checkbox:true
		    });
		    $("#pageloading").hide();
		}
   		
   		//初始化查询数据
   		function initParam(){
   			var param = {};
   			var macCode = $("#macCode").val();
   			var courseCode = $("#courseCode").val();
   			 if(macCode != "" && macCode != null ){
   				param.macCode = macCode;
   			 }
   			if(courseCode != ""  && courseCode != null){
   				param.courseCode = courseCode;
  			 }
   			var bean = JSON.stringify(param);
   			return bean;
   		}
   		
   		function toDetail(id){
   			window.open("<%=basePath %>rest/mauOEEHistoryManageAction/toHistoryChart?id="+id);
   		}
   		
   		/**
   		* 多条数据展示
   		*/
   		function setManyGrid(){
   			var beans = grid.getSelectedRows();
   			if(!beans.length  > 0 ){
   				$.ligerDialog.warn("请选择数据");
   				return;
   			}
   			var maccode = beans[0].macCode;
   			var ids = "";
   			for(var i = 0;i<beans.length;i++ ){
   				debugger;
   				var aa = beans[i];
   				if(maccode != aa.macCode){
   					$.ligerDialog.warn('请选择一个机台');
   					return;
   				}
   				if( i == 0){
   					ids  = aa.id;
   				}else{
	   				ids  = ids +","+aa.id
   				}
   			}
   			//var params = {};
   			//param.ids = ids;
   		//	param.macCode = maccode;
   			var param = JSON.stringify(ids);
   			//var url ="<%=basePath %>rest/mauOEEHistoryManageAction/toHistoryChart";
   			window.open("<%=basePath %>rest/mauOEEHistoryManageAction/toHistoryChart?id="+param);
   		}
   	</script>
    
  </head>
  	
  	
  <body style="width:95%">
    <div class="container">
			<div class="row">
				<h1 class="col-md-6 col-md-offset-4">机台历史速度查询</h1>
			</div>

	</div >
		<div class="container">
			<form class="form-inline">
				<div class="form-group">
					<label for="macCode">机台code</label>
					<input type="text" class="form-control" id="macCode" placeholder="">
				</div>
				<div class="form-group">
					<label for="courseCode">工单号</label>
					<input type="text" class="form-control" id="courseCode" placeholder="">
				</div>
				<button type="button" class="btn btn-primary" onclick="setGrid()">查询</button>
				<button type="button" class="btn btn-primary" onclick="setManyGrid()">多条数据展示</button>
			</form>
		</div>
		<div class="container">
  		<h1></h1>
  	</div>
  	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	
	<div id="maingrid"></div>
  </body>
</html>
