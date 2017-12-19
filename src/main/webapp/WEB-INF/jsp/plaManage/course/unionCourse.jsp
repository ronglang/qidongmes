<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			var grid;
			var basePath  = '<%=basePath%>';
			var routeName = "plaCourseManage";
			$(function(){
				queryTable();
			});
			function queryTable(){
				grid = $("#grid").ligerGrid({
							url : basePath + "rest/" + routeName+ "Action/getNeedPartPlaCourse",
							checkbox : true,
							columns : [
									{display : '工作单编码',name : 'workcode'},
									{display : '规格型号',name : 'ggxh'},
									{display : '交货日期',name : 'demanddate'},
									{display : '生产长度',name : 'count'},
									],
							pageSize : 10,
							rownumbers:true,
							height : '80%',
							usePager:true,
							onSuccess : function(json, grid) {
								$("#pageloading").hide();
							},
							onError : function(json, grid) {
								$("#pageloading").hide();
							},
							toolbar :{
								items : [
						          {text:"合单",click:unionCourse,icon:'modify'},
						          { line: true }
								]
							}
						});
			}
			
			
			
			//合单
			function unionCourse(){
				var manager = $("#grid").ligerGetGridManager();
	        	var li = manager.getSelectedRows();
	        	if(li.length<1){
	        		$.ligerDialog.warn("请选择合单项");
	        		return;
	        	}
				$.ligerDialog.confirm('是否确认合单?', function (yes) {
					if(yes){
						var date = JSON.stringify(li);
						
						$.ajax({
							url:basePath + "rest/" + routeName+ "Action/getUnionCourseWsCode?bean="+date,
							type:'POST',
							dataType:'JSON',
							success:function(map){
								if(map.success){
				        			$.ligerDialog.success("合单成功");
				        			refreshDatagrid();
								}else{
									$.ligerDialog.error("合单失败");
								}
							}
						});
					}
				}); 
				
			}
			
			
		</script>
  </head>
  
  <body>
    <div id="grid"></div>
  </body>
</html>
