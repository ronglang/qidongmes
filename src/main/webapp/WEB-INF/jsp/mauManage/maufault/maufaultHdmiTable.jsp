<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title></title>
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
		<style type="text/css">
		#nowTime{
			font-size: 20px;
			color:white;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
				color:white;
			}
	
	
	
			.tb{
				width: 30%;
				height: 80px;
				float: right;
				text-align: right;
				line-height: 70px;
			}
			 .newSpan{
			 	text-align: center;
			 	float: left;
			 	margin-top: 20px;
			 	margin-left: 40%;
			 }
		
		</style>
			<script>
			var basePath  = '<%=basePath%>';
			var routeName = "mauMachineFaultManage";    
    	</script>
		
		<script type="text/javascript">
		$(function(){
			var url=basePath + "rest/" + routeName + "Action/getFaultPage?requestType=mapuser";
				getDataGrid(url);
				//myload();
		});
		function getDataGrid(url){
			window['g'] =
		        $("#maingrid").ligerGrid({
		            url:url,
		            height:'99%',
		            columns: [
		              { display: '维修人员', name: 'repairBy',  width:  '15%' },
                      { display: '故障机台', name: 'macCode',  width: '15%'},
                      { display: '故障类型', name: 'faultType',  width:  '15%'},
                      { display: '故障时间', name: 'faultDate',  width:  '20%'},
                      { display: '维修时间', name: 'repairDate', width: '20%' },
                      { display: '状态', name: 'status',  width:  '15%', 
                      },
          	        
                 		 ],
          
            rownumbers: true,
           // autoFilter: true
        });
        $("#pageloading").hide();
};		


function myload(){
  	var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		var url = "ws://"+urlPirfex+"storeCrewWebS";
	
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
	
		function onMessage(event) {
			var jsonBean = eval('('+event.data+')');
			if(jsonBean){
				$("#maingrid").ligerGrid({}).reload();
			}
			
		}
		function onOpen(event) { 
			webSocket.send("true");
		}
	
		function onError(event) {
		}
  }
			
			
		</script>
	</head>

	<body style="overflow-x:hidden; padding:2px;">
		<div class="l-loading" style="display:block" id="pageloading"></div>
		<div class="l-clear"></div>
		<div id="maingrid"></div>
		<div style="display:none;">
		</div>
	</body>

</html>