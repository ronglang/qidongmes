<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String storeReturn_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机台故障列表</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeReturnManage"; 
	var row_id = "";
	row_id =<%=storeReturn_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<!-- 默认引用1end -->
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<!-- 默认引用end -->
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
		});
		
		function queryTable(){
	        	 {
	             	var maingid= $("#maingrid");
	                 window['g'] =
	                 $("#maingrid").ligerGrid({
	                     height: '98%',
	                     url:'<%=basePath%>rest/mauMachineFaultManageAction/getFaultPage?requestType=mapuser',
	                    // url : basePath + "rest/"+ routeName + "Action/getReturnTable?param="+para,
	                  //   title:'工单任务达成情况',
	                     columns: [
	                     { display: '机台编号', name: 'macCode' },
	                     { display: '故障类型', name: 'faultType' },
	                     { display: '操作人员', name: 'createBy' },
	                     { display: '维修人', name: 'repairBy' },
	                     { display: '故障时间', name: 'faultDate' },
	                     { display: '备注', name: 'remark' }
	                     ], rownumbers: true,
	                 });
	                $("#pageloading").hide();
	                $("#start").ligerDateEditor();
	                $("#end").ligerDateEditor();
	        	 }  
	        }
		
		//websocket
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
				webSocket.send();
			}
		
			function onError(event) {
		// 		alert(event.data);
			}
		}
	</script>
</head>

<body style="overflow-x:hidden; padding:2px;overflow:auto;">
		<b><div style=" width: 20%;height:30px;font-size:20px; ">机台故障情况</div></b>
		<div class="l-loading" style="display:none" id="pageloading"></div>
 		<div class="l-clear"></div>
    	<div id="maingrid" style="overflow: hidden;width: 100%"></div>
 		<div style="display:none;"></div>
</body>
</html>

