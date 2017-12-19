<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>mauPowerList</title>
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />

<script src="<%=basePath%>core/js/jquery-1.7.2.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>

<script src="<%=basePath%>app/js/mauManage/mauPowerList.js"
	type="text/javascript"></script>
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "mauPowerManage";
</script>
<style>
* {
	margin: 0;
	padding: 0;
	font-size: 13px;
}

body {
	background-color: #3267AB;
}

ul {
	list-style-type: none;
}

#top {
	height: 60px;
	line-height: 60px;
	background-color: #3F77C1;
	border-bottom: 2px solid #1E4785;
}

#top .t_center {
	height: 60px;
	width: 56%;
	color: white;
	font-size: 25px;
	text-align: right;
	letter-spacing: 5px;
	float: left;
}

#top .t_time {
	width: 44%;
	height: 60px;
	float: left;
	color: white;
	text-align: right;
}

#top .t_time span {
	display: inline-block;
	margin-top: 10px;
	font-size: 16px;
	margin-right: 5%;
}

#head {
	width: 100%;
	margin: 0 auto;
}

#head ul {
	width: 95%;
	margin: 10px auto;
	text-align: center;
}

#head ul li {
	width: 30%;
	height: 40px;
	line-height: 40px;
	background-color: #4B84D1;
	float: left;
	margin: 10px 15px;
	color: #FFFA00;
}

#head ul li span {
	font-size: 18px;
}

#head ul li label {
	font-size: 18px;
}

#con {
	width: 95%;
	margin: 0 auto;
	position: relative;
	clear: both;
}

#con ul {
	padding-top: 30px;
	padding-bottom: 30px;
	width: 100%;
	height: auto;
	top: 10px;
	position: absolute;
	margin: 0 auto;
	border-radius: 10px;
	background-color: #224B81;
}

#con ul li {
	float: left;
	width: 220px;
	margin: 10px 10px;
	height: 40px;
	line-height: 40px;
	text-align: center;
	border: 1px solid #8ABDFF;
}

#con ul li span {
	display: inline-block;
	width: 50%;
	height: 40px;
	line-height: 40px;
	border-right: 1px solid #8ABDFF;
	overflow: hidden;
	color: white;
	font-size: 15px;
}

#con ul li label {
	display: inline-block;
	width: 49%;
	height: 40px;
	line-height: 40px;
	overflow: hidden;
	color: yellow;
	font-size: 15px;
}

#search {
	width: 100%;
	height: 50px; margin : 0 auto;
	position: absolute;
	background-color: #224B81;
	border-radius: 15px;
	clear: both;
	margin: 0 auto; position : absolute; background-color : #224B81;
	border-radius : 15px; clear : both;
	margin-top: 10px
}

#imgs {
	width: 100%;
	margin: 0 auto;
	position: absolute;
	background-color: #224B81;
	border-radius: 15px;
	clear: both;
	margin-top: 90px;
}

 #form2 .l-form-container .l-fieldcontainer li{
 	color:#FFFFFF;
 }
#mychart {
	margin: 0 auto;
}
</style>
</head>

<body style="padding:6px; overflow:hidden;">
	<div id="top">
		<div class="t_center">耗电情况</div>
		<div class="t_time">
			<span id="times"></span>
		</div>
	</div>
	<div id="head">
		<ul>
			<li><span>当前机台：</span><label id="macCode">ALL</label></li>
			<li><span>主操作员：</span><label id="employee">ALL</label></li>
			<li><span>当前工单：</span><label id="wsCode">ALL</label></li>
		</ul>
	</div>

	<div id="con">
		<div id="content"></div>
		<div id="search">
		</div>
		<div id="imgs">
			<div id="mychart" style="width: 90%;height: 400px;"></div>
		</div>
	</div>
	<div id="fo"
				style="text-align: center;width: 100%;position: relative;margin-top: 20px;margin-left: 50px">
				<form id="form2" action=""></form>
				<div class="liger-button" id='tj' onclick="search()"
					style="float: right;position: absolute;right: 300px;top: 2px; ">查询</div>
	</div>
</body>
<script type="text/javascript">
	var series,lendgs;

	$(function(){
		
		$("#times").showDate();
		$.ajax({
			url:'<%=basePath%>rest/mauPowerManageAction/getEchartsDate',
			type:'POST',
			dataType:'JSON',
			success:function(vo){
				
				//getDate(vo);
				myWebsocket();
				form();
			}
		});
		
		
		
		
		
		
	});
		
	function getDate(vo){
		series = vo.series;
		lendgs = vo.lengds;
		debugger;
		getChart(series,lendgs);
		
	}
	
	
	function myWebsocket(){
		var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		var url = "ws://" + urlPirfex + "mauPowerWebSocket";
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
			var jsonBean = eval('(' + event.data + ')');
			getDate(jsonBean);

		}
		//传的参数
		function onOpen(event) {
			webSocket.send(event);   
		}

		function onError(event) {
		}

	}
	
	function form(){
		form = $("#form2").ligerForm({
            inputWidth: 200, labelWidth: 80, 
            fields: [
            	{ display: "操作手", name: "employee", newline:true, type: "text"},
            	{ display: "机台编号", name: "macCode",  newline:false,type: "text" },
            	{ display: "工单号", name: "wsCode", newline:false,type: "text" },
            ],
            validate: true
        });
		
	}
	
	function search(){
		var employee = $("[name=employee]").val();
		var macCode = $("[name=macCode]").val();
		var wsCode = $("[name=wsCode]").val();
		//alert(employee+macCode+wsCode);
		//return;
		var label=document.getElementById("employee"); 
		var mac=document.getElementById("macCode"); 
		var ws=document.getElementById("wsCode"); 
		if(employee!="" && employee !=null){
			label.innerText=employee; 
			$("#employee").html(employee); 
		}else{
			label.innerText="ALL";
			$("#employee").html("ALL"); 
		}
		if(macCode!="" && macCode !=null){
			mac.innerText=macCode; 
			$("#macCode").html(macCode); 
		}else{
			mac.innerText="ALL"; 
			$("#macCode").html("ALL"); 
		}
		if(wsCode !="" && wsCode!=null){
			ws.innerText=wsCode; 
			$("#wsCode").html(wsCode);
		}else{
			ws.innerText="ALL"; 
			$("#wsCode").html("ALL");
		}
		
		$.ajax({
			url:'<%=basePath%>rest/mauPowerManageAction/getEchartsDate?employee='+employee+
					'&macCode='+macCode+'&wsCode='+wsCode,
			type:'POST',
			dataType:'JSON',
			success:function(vo){
				getDate(vo);
				//myWebsocket();
				//form();
			}
		});
		
	}

	function getChart(series, lendgs) {

		var myChart = echarts.init(document.getElementById('mychart'));
		option = {
			color : [ '#EE9A00' ],
			textStyle : {
				color : '#00CED1',
			},
			title : {
				text : '能耗情况统计',
				textStyle : {
					color : '#EE9A00',
				},
				left : 'center'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					animation : false,
					type : 'line' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			toolbox : {
				show : true,
				orient : 'vertical',
				left : 'right',
				top : 'center',
				feature : {
					dataZoom : {
						yAxisIndex : 'none'
					},
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			grid : {
				left : '3%',
				right : '10%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				name : '时间',
				boundaryGap : false,
				data : lendgs
			} ],
			yAxis : [ {
				name : 'KW/H',
				type : 'value'
			} ],
			series : [ {
				type : 'line',
				hoverAnimation : false,
				label : {
					normal : {
						show : true,
						position : 'top'
					}
				},
				// data:maps.get("dataList"),
				data : series
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}
</script>
</html>

