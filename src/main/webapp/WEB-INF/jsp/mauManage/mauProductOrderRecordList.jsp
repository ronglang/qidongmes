<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>生产电子看板</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=basePath%>app/images/cetc.png" media="screen" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="mylink" />
<link href="<%=basePath%>core/css/bootstrap.css" />
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "mauProductOrderRecord";
	var row_id = "";
	setInterval(function(){
		setTime();
	},1000);
	
	function setTime(){
		var date = new Date();      
	 	var dateS =  date.pattern("yyyy-MM-dd EEE hh:mm:ss");
	 	 $("#nowdate").html(dateS);
	}
	
	//websocket刷新页面
	
	$(function(){
		
		//myLoad();
	});
	function myLoad(){
	  	var basePath = "<%=basePath%>";
			var urlPirfex = basePath.substring(7, basePath.length);
			var url = "ws://"+urlPirfex+"productDepartmentShowPlanWebS";
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
			/* 	//在此处将数据封装到页面
				var josnBean = eval('('+event.data+')');
				//将后台数据封装到echart图标中
				//1.横轴
				createAreas(josnBean);
				//2.图例
				createLegends(josnBean);
				//3.数据
				createData(josnBean);
				option.legend.data = legends;
				option.xAxis = axis;
				option.series = series;
				myChart.setOption(option); */
				
				var josnBean = eval('('+event.data+')');
				alert(josnBean);
				if(josnBean){
					window.location.reload();
				}
			}
			function onOpen(event) {
				//TODO 发送房间号到后台去,获取房间号
			
//	 			document.getElementById('data').innerHTML = '<br />' + ""+"start";
			}
		
			function onError(event) {
		// 		alert(event.data);
			}
	  }


</script>

<style type="text/css">
body,html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 2px;
	margin-right: 2px;
}

.l-layout-top {
	background: #102A49;
	color: White;
}

.l-layout-bottom {
	background: #E5EDEF;
	text-align: center;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 99999;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 16px;
	border: 1px solid white;
	margin: 4px;
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.l-winbar {
	background: #2B5A76;
	height: 30px;
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	z-index: 99999;
}

.space {
	color: #E7E7E7;
}
/* 顶部 */
.l-topmenu {
	margin: 0;
	padding: 0;
	height: 50px;
	font-size: -webkit-xxx-large;
	line-height: 100px;
	background-color: #6495ED;
	position: relative;
	border-top: 1px solid #1D438B;
}

.l-topmenu-logo {
	color: rgb(212, 211, 224);
	align-content: center;
	margin-top: 3px;
	padding-left: 35px;
	line-height: 26px;
}

.l-topmenu-welcome {
	position: absolute;
	margin-top: 30px;
	font-size: large;
	height: 24px;
	line-height: 24px;
	right: 30px;
	top: 2px;
	color: #070A0C;
}

.l-topmenu-welcome a {
	color: rgb(212, 211, 224);
	text-decoration: underline
}

.body-gray2014 #framecenter {
	margin-top: 3px;
}

.viewsourcelink {
	background: #B3D9F7;
	display: block;
	position: absolute;
	right: 10px;
	top: 3px;
	padding: 6px 4px;
	color: #333;
	text-decoration: underline;
}

.viewsourcelink-over {
	background: #81C0F2;
}

#skinSelect {
	margin-right: 6px;
}
</style>
</head>
<body style="padding:0px;width: 95%;margin: auto;overflow:scroll;  " >
<div style="position: relative;">
		 	<div class="call" style="width: 70%;height:80px;position: absolute; background:#1E90FF ;">
					<span style="font-size: 50px;font-family: 'LiSu';color: white;margin-left: 40%;">
					生&nbsp;&nbsp;产&nbsp;&nbsp;部&nbsp;&nbsp;电&nbsp;&nbsp;子&nbsp;&nbsp;看&nbsp;&nbsp;板</span>
		 	</div>
		 	</div>
 <div style="background-color:#1E90FF;height:80px;width:100%;float: right;"  >
	 			<div style="float: right;margin-top:40px; font-size:25px ;text-align: center;margin-right: 20px;">
					<span class="call" style="font-size: large;color: white;"> 时间 : </span><span class="call" id="nowdate" 
						style="font-size: large;color: white;"></span>
				</div>
			</div>
	<div id="layout1"
		style="width:99.2%; margin:0 auto; margin-top:1px; overflow :auto;overflow-x:hidden;">
		<div class="top"
			style="width: 100%;height:300px;margin:0 auto; margin-top:0;">
			<div class="top-a"
				style="float:left;width:40%;height:300px;margin:0 auto; margin-top:0;margin-left:1px;">
				<iframe style="width:100%;height:300px;border: 0;" scrolling="no"
				align="middle"
				src="<%=basePath%>rest/mauProductOrderRecordManageAction/toMauProductOrderRecordPineChart?requestType=mapuser">
			</iframe>
			</div>
		<div class="top-b" 
				style="float:left;width:55%;height: 290px;margin:0 auto; margin-top:0;margin-left:1px;">
				<iframe style="width:100%;height: 290px;border: 0;" scrolling="no"
				align="middle"
				src="<%=basePath%>rest/mauProductOrderRecordManageAction/toMauProductOrderRecordGlueChart?requestType=mapuser">
			</iframe>
			</div> 
			
		</div> 
		<div class="top"
			style="width: 100%;height:600px;margin:0 auto; margin-top:0;">
		<div class="two-a"
			style="width:100%;height: 590px;margin:0 auto; margin-top:0;margin-left:1px;margin-right:1px;">
			<iframe style="width:100%;height: 800px;border: 0;" scrolling="no"
				align="middle"
				src="<%=basePath%>rest/mauProductOrderRecordManageAction/toMauProductOrderRecordTable?requestType=mapuser">
			</iframe>
		</div>
		</div>
	</div>
	<script>
		function outLog() {
			$.ajax({
				type : "POST",
				url : basePath + "/rest/sysUserManageAction/logOutWebUser",
				data : "",
				dataType : "json",
				success : function(data) {
					if (data.success = true) {
						window.location.href = basePath + "login.jsp";
					}
				}
			});
		}
	</script>
</body>
</html>

