<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PlanMaterDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
  </head>
  <script type="text/javascript">
  var data = ${data};
  	$(function(){
  		grid();
  	});
  	function grid(){
  		var json = JSON.stringify(data); 
  		window['g'] =
			$("#maingrid").ligerGrid({
				height: '99%',
				url:'<%=basePath%>rest/planDetialManageAction/getMaterDetailPageList?param='+json,
				columns: [
				    { display: '轴名称', name: 'axisname',   width:'20%'},
				    { display: '原料型号', name: 'materggxh',   width:'20%'},
					{ display: '原料名称', name: 'matername',   width:'20%'},
					{ display: '数量', name: 'materamount',  width:'20%'},
					{ display: '单位', name: 'unit',  width:'10%'},
					{ display: '是否领料', name: 'flag',  width:'10%'},
					{ hide: '生产令id', name: 'orderid',  width:1}
				],
				
				rownumbers: true,
			});

		$("#pageloading").hide();
  	}
  
  </script>
  <body style="overflow-x:hidden; padding:2px;">
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"></div>
		<div id="maingrid"></div>
		<div style="display:none;">
		</div>
	</body>
</html>
