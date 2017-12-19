<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>质检呼叫电子看板</title>
    
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
    
    <link href="<%=basePath %>core/css/public.css" rel="stylesheet" type="text/css" />

    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
    
    var grid;
    setInterval("queryTable();",60000);
    	$(function(){
    		
    		queryTable();
    		websockt();
    	});
    	
    	function queryTable(paramData){
    		 {
    			 grid = $("#maingrid").ligerGrid({
        			 height: '80%',
     				//url:'<%=basePath %>rest/qualityAskManageAction/toPageList?param='+param,
     				checkbox: false,
     				columns:[
     				{display: '质检类型', name: 'testType'},
    				{display: '质检编号', name: 'reportCode'},
    				{display: '呼叫人', name: 'createBy'},
    				{display: '呼叫时间', name: 'createDate' },
     				{hide: 'id', name: 'id',width:'1px'}
     				],
     				
          			 rownumbers:true,
          			data: paramData,     
         		});	
        		 $("#pageloading").hide();
        	 }
    	}
    	
    	function initSartch(){
    		var params={};
    		params.askState = "未处理";
    		return JSON.stringify(params);
    	}
    	
    	function websockt(){
			var basePath = "<%=basePath%>";
			var urlPirfex = basePath.substring(7, basePath.length);
			var url = "ws://"+urlPirfex+"QuaAskWebSocket";
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
				debugger;
				var josnList = eval('('+event.data+')');
				//paramList[dataIndex] =data;
				//var g = $("#maingrid").ligerGrid();
				grid.loadData(josnList);
	    		var paramData = { Rows: josnList };
	    		queryTable(paramData);
				
				/* 
				if ("true" == josnBean) {
					queryTable();
					//改变状态
					flag = 1;
					//只执行一次,一个小时还没有刷新,修改状态,使页面刷新
					setTimeout(function(){
						flag = 0;
					}, 60000*40);
				} */
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
  
  <body>
      <div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div id="maingrid"></div>
	<div style="display:none;"></div>
  </body>
</html>
