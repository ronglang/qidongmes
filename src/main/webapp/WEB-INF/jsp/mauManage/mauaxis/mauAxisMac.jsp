<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>轴</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%= basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>    
    <script src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  

	<script type="text/javascript">
		var grid;
		
		$(function(){
			queryTable();
			setForm();
		});
	
		function queryTable(){
			var param = initParam();
			debugger;
			grid = $("#myGrid").ligerGrid({
				title : "订单明细列表",
				url : "<%= basePath%>rest/mauAxisMacManageAction/getListPage?param="+param,
				height : '99%',
				rownumbers: true,
				columns : [
				{
					display : '机台编码',
					name : 'macCode'
				}, {
					display : '轴名称',
					name : 'axisName'
				}, {
					display : '线轴类型',
					name : 'axisType'
				}, {
					hide : 'id',
					name : 'id',
					width:1
				} ],
				 groupColumnName: 'axisName', 
	                groupRender: function (macCode,groupdata)
	                {
	                    return '机台名称 ' + macCode + '(: 数目=' + groupdata.length + ')';
	                },
				toolbar : {
					items : [ {
						text : "添加",
						click : insert,
						icon : "add"
					} ,{
						text : "修改",
						click : update,
						icon : "add"
					} ,{
						text : "删除",
						click : delBean,
						icon : "add"
					}]
				}
			});
		}
		var ligerDialogOpen ;
		function insert(){
			 ligerDialogOpen = $.ligerDialog.open({
	 				title:"添加",
	 				url:"<%=basePath %>rest/mauAxisMacManageAction/toFormPage",
	 				type:'question' ,
	 				width :1000,
	 				height:250,
	 				buttons :[
	 				          {
	 					        	 text:"保存",onclick:function(i,d){
	 					        		d.frame.save();
	 					        		grid.reload() ;
	 					        	 }
	 					          },
	 					         { 
	 					        	 text: '取消', onclick: function(i,d){ ligerDialogOpen.close();;}
	 					         }
	 					        
	 				]
	 			});
		}
		
		function update(){
			var li = grid.getSelectedRow();
			debugger;
			ligerDialogOpen = $.ligerDialog.open({
 				title:"添加",
 				url:"<%=basePath %>rest/mauAxisMacManageAction/toFormPage?id="+li.id,
 				type:'question' ,
 				width :1000,
 				height:250,
 				buttons :[
 				          {
 					        	 text:"保存",onclick:function(i,d){
 					        		d.frame.save();
 					        		grid.reload() ;
 					        	 }
 					          },
 					         { 
 					        	 text: '取消', onclick: function(i,d){ ligerDialogOpen.close();;}
 					         }
 					        
 				]
 			});
		}
		
		function delBean(){
			var data = grid.getSelectedRow();
			if (data.length < 1) {
				$.ligerDialog.warn("请选择一行进行删除");
				return;
			}
			
			//var rowId = data[0].id;
			var url = "<%=basePath %>rest/mauAxisMacManageAction/clearBean";
			$.post(url,{id:data.id},function(data){
				if(data.success){
					$.ligerDialog.success(data.msg);
					grid.reload();
				}else{
					$.ligerDialog.error(data.msg);
				}
			},"json");
		}
		
		function initParam(){
			var bean = {};
			var axisName = $("[name=axisName]").val();
			if (axisName != null || axisName != "") {
				bean.axisName = axisName;
			}
			var se = JSON.stringify(bean);
			return se;
		}
		
		function setForm(){
			seForm = $("#myForm").ligerForm({
 		        inputWidth: 120, labelWidth: 80, space:10,
 		        fields: [
 		        	{ display: "机台编号", name: "macCode", newline:false, type: "select",editor:{url:'<%=basePath %>rest/mauAxisMacManageAction/getMacCode',valueField:'text'}},
 		        ],	
 		    });	
		}
		
</script>
  </head>
  
  <body>
  	<!-- 查询条件,此处暂时不写代码 -->
	<div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<form id="myForm" action="" >
		</form>
		<div class="liger-button" id = 'tj' onclick="queryTable()" style="float: right;position: absolute;left: 400px;top: 1px; ">提交</div>
	</div>
    <div id="myGrid"></div>
  </body>
</html>
