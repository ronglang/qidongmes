<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生产异常列表</title>
    <script>
		var basePath  = '<%=basePath%>';
	</script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 默认引用1 -->
	<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    
    <link href="<%=basePath %>core/css/public.css" rel="stylesheet" type="text/css" />

    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
	<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>

	<!-- 默认引用1end -->
	<!-- 默认引用 -->
	<style type="text/css">
		.div1{
			text-align: center;
			font-size: 14px;
		}
		.word1{
			font-size: 14px;
		}
	</style>
	<script type="text/javascript">
	var flag = 0;
	//如果websocket一直不刷新,20分钟判断一次;websocket,onmessage会自动修改状态,具体时间现场确定
	setInterval("timeQuery();", 60000*20);
	function timeQuery(){
		if (flag == 0) {
			queryTable();
		}
	}
	
	$(function(){
		queryTable();
		websockt();
	});
	function queryTable(){
   	 {
        	var maingid= $("#maingrid");
            window['g'] =
            $("#maingrid").ligerGrid({
                height: '98%',
                url:'<%=basePath%>rest/mauExceptionManageAction/getPage?requestType=mapuser',
                columns: [
                { display: '机器编号', name: 'mac_code' },
                { display: '轴名称', name: 'axis_name' },
                { display: '异常信息', name: 'me_info' },
                { display: '异常参数', name: 'exception_param' },
                { display: '异常值', name: 'exception_value' },
                { display: '异常时间', name: 'me_time' },
                { display: '备注', name: 'remark' }
                ], rownumbers: true,usePager:false,
            });
           $("#pageloading").hide();
           $("#start").ligerDateEditor();
           $("#end").ligerDateEditor();
   	 }  
   }
	
	//websocket
	function websockt(){
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		var url = "ws://"+urlPirfex+"cenExceptionWebS";
		var webSocket = new WebSocket(url);
		webSocket.onerror = function(event) {
			onError(event);
		};
	
		webSocket.onopen = function(event) {
			onOpen(event);
		};
	
		webSocket.onmessage = function(event) {
			onMessage(event);
		};
		//这里获得反馈的值,进行参数的赋值
		function onMessage(event) {
			//在此处将数据封装到页面
			var josnBean = eval('('+event.data+')');
			if ("true" == josnBean) {
				queryTable();
				//改变状态
				flag = 1;
				//只执行一次,一个小时还没有刷新,修改状态,使页面刷新
				setTimeout(function(){
					flag = 0;
				}, 60000*40);
			}
		}
		//传的参数
		function onOpen(event) { 
			webSocket.send("1");
		}
	
		function onError(event) {
	// 		alert(event.data);
		}
	}

	</script>
  </head>
  
 <body style="overflow-x:hidden; padding:2px;overflow:auto;">
		<b><div style=" width: 20%;height:30px;font-size:20px; ">生产异常情况:</div></b>
		<div class="l-loading" style="display:none" id="pageloading"></div>
 		<div class="l-clear"></div>
    	<div id="maingrid" style="overflow: hidden;width: 100%"></div>
 		<div style="display:none;"></div>
</body>
</html>
