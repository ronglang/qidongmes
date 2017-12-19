<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台—[${map.macCode }]电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js"  type="text/javascript"></script>
	<script src="<%=basePath%>core/js/cssBase.js"  type="text/javascript"></script>
	<style type="text/css">
			*{
				margin: 0;
				padding: 0;
				font-size: 13px;
			}
			body{
				background-color: #3267AB;
			}
			ul{
				list-style-type: none;
			}
			#top{
				height: 60px;
				line-height: 60px;
				background-color: #3F77C1 ;
				border-bottom: 2px solid #1E4785;
			}
			#top .t_center{
				height: 60px;
				width: 56%;
				color: white;
				font-size: 25px;
				text-align: right;
				letter-spacing: 5px;
				float: left;
			}
			#top .t_time{
				width: 44%;
				height: 60px;
				float: left;
				color: white;
				text-align: right;
			}
			#top .t_time span{
				display: inline-block;
				margin-top: 10px;
				font-size: 16px;
				margin-right: 5%;
			}
			#head{
				width: 100%;
				margin: 0 auto;
			}
			#head ul{
				width: 95%;
				margin: 10px auto;
				text-align: center;
			}
			#head ul li{
				width:20%;
				height: 40px;
				line-height: 40px;
				background-color: #4B84D1;
				float: left;
				margin:10px 15px;
				color: #FFFA00;
			}
			#head ul li span{
				font-size: 18px;
			}
			#head ul li label{
				font-size: 18px;
			}
			#con{
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
			#con ul li{
				float: left;
				width: 220px;
				margin:10px 10px;
				height: 40px;
				line-height: 40px;
				text-align: center;
				border: 1px solid #8ABDFF;
			}
			#con ul li span{
				display: inline-block;
				width: 50%;
				height: 40px;
				line-height: 40px;
				border-right: 1px solid #8ABDFF;
				overflow: hidden;
				color: white;
				font-size: 15px;
			}
			
			#con ul li label{
				display: inline-block;
				width: 49%;
				height: 40px;
				line-height: 40px;
				overflow: hidden;
				color: yellow;
				font-size: 15px;
			}
			
			#imgs{
				width:100%;
				margin:0 auto;
				position:absolute;
				background-color: #224B81;
				border-radius:15px;
				clear: both;
				margin-bottom:100px;
			}
			#mychart{
				margin:0 auto;
			}
		</style>
		
		<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var macCode = '${map.macCode }';
		//押出机code
		var YACHU = ["EA120","EB","EC","ED","EQ","EK","EJ"];
		var fa  ; 
		
		setInterval(function(){
			getEchartsData();
			getDataParam();
			getTaskSc();
		}, 60000);
			$(function(){
				
				$("#times").showDate();
				
				//myWebsocket();
				
				initShowParams();
				getEchartsData();
				changeHieght();
				$(window).resize(function(){
					changeHieght();
				});
				
				//获取参数数据
				getDataParam();
				
				 fa = YACHU.indexOf(macCode);
				 //测试押出机
				/*  alert(fa);
				fa = 1;
				 var maps = {};
				 maps.dataList = [1,2,3,4,5,6,7,8,9];
				 maps.dataList2 = [3,4,5,6,7,8,9,8,7];
				 maps.timeList = [1,2,3,4,5,6,7,8,9];
				 getChart(maps);  */
				 
				 getTaskSc();
				
			});
			
			//设置机台基本参数
			function getTaskSc(){
				var url = "<%=basePath%>rest/plaMachinePlanManageAction/getNowTask";
				$.post(url,{macCode:macCode},function(data){
					if(data.success){
						var bean = data.data;
						if (bean  == null) {
							return ;
						}
						$("#mainBy").text(bean.main_by);
						$("#courseCode").text(text.workcode);
						$("#schel").text(bean.schedule);
					}
				},"json");
			}
			//折线图参数
			function getEchartsData(){
				
				var url = basePath + "rest/mauNewDislayManageAction/getSeqMacSpeed";
				$.post(url,{macCode:macCode},function(data){
					if(data.success){
						getChart(data.data);
					}else{
						var vo = {};
						vo.title="";
						getChart(vo);
					}
				},"json");
			}
			
			function getDataParam(){
			 	$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getSeqMacParam?macCode="+macCode,
					type: "post",
					dataType: "JSON",
					success:function(data){
						debugger;
						var map = eval("("+data+")");
						showParams(map);
					}
				}); 
				/* var url = basePath + "rest/mauNewDislayManageAction/getSeqMacParam?macCode="+macCode;
				$.post(url,{macCode:macCode},function(data){
					showParams(data);
				},"json"); */
			}
			
			function changeHieght(){
				var height = $("#content ul").css("height");
				height = parseInt(height)+90+"px";
				$("#imgs").css("margin-top",height);
			}
			
			function myWebsocket(){
				////debugger;
				var macCode = '${map.macCode }';
				var basePath = "<%=basePath%>";
				var urlPirfex = basePath.substring(7, basePath.length);
				var url = "ws://"+urlPirfex+"newProceWebSocket";
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
					var jsonBean = eval('('+event.data+')');
					debugger;
					showParams(jsonBean);
					
					
				}
				//传的参数
				function onOpen(event) { 
					webSocket.send(macCode);
				}
			
				function onError(event) {
				}

			}
			
			function showParams(maps){
				//debugger;
				
				//var mapData = maps.data;
				var mapData = eval("("+maps.data+")");
				var data = '${map.params}';
				
				
				for(var key in mapData){
					debugger;
					var value= mapData[key];
					//$("#"+key).text(value);
					var obj= document.getElementById(key);
					if(obj ){
						obj.innerText =  value;
					}
				}
			}
			
			function initShowParams(){
				var data = '${map.params}';
				var list = eval("("+data+")");
				$("#content").html("");
				var html = "";
				html += "<ul>";
				for(var i=0;i<list.length;i++){
					html += "<li><span title='"+list[i]+"' >"+list[i]+"</span><label id='"+list[i]+"'></label></li>";
				}
				html += "</ul>";
				$("#content").append(html);
			}
			
			//var mychart;
			var option;
			var series = [];
	function getChart(vo){
		createSeries(vo);
		var myChart = echarts.init(document.getElementById('mychart'));
		option = {
			color: ['#FFF300', '#FF00FF'],
				textStyle: {
					color: '#B0C4DE',
				},
				title: {
					text: vo.title,
				},
				tooltip: {
					trigger: 'axis'
				},
				legend: {
					data: vo.legends
				},
				toolbox: {
					show: true,
					feature: {
						dataZoom: {
							yAxisIndex: 'none'
						},
						dataView: {
							readOnly: false
						},
							// magicType: {type: ['line', 'bar']},
						restore: {},
						saveAsImage: {}
					}
				},
				grid: {
					left: '3%',
					right: '10%',
					bottom: '3%',
					containLabel: true
				},

				calculable: true,
				xAxis: {
					name: '时间',
					boundaryGap: false,
					data: vo.xAxis,
				},
				 yAxis: [
						   	{
			                     type: 'value',
			                     name: '速度',
			                     position: 'left',
			                         axisLabel: {
			                             formatter: '{value} '
			                          }
			                }/* ,
			                 {
			                    type: 'value',
			                    name: '螺杆速度',
			                    position: 'right',
			                    axisLabel: {
			                        formatter: '{value} '
			                    }
			                }, */ 
						   ],
						series: series
					};
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			}
	
	function createSeries(vo) {
		if(vo.seriesData == null){
			return ;
		}
		var legend = vo.legends;
		var seriesData = vo.seriesData;
		for(var i = 0 ; i<legend.length;i++){
			var obj = {};
			var name = legend[i];
			obj.name = name;
			obj.type = "line";
			obj.label = {normal: {
				                show: true,
				                position: 'top'
				            }
						};
			var data = seriesData[name];
			if(data.type == "left"){
				obj.data = data.data;
			}else if(data.type == "right"){
				obj.yAxisIndex = 1;
				obj.data = data.data;
			}
			series[i] = obj;
		}
	}
		</script>
		
  </head>
  
  <body>
    
    <div id="top">
			<div class="t_center">
				机台实时数据看板
			</div>
			<div class="t_time">
				<span id="times"></span>
			</div>
		</div>
		<div id="head">
			<ul>
				<li><span>机台编码：</span><label>${map.macCode }</label></li>
				<li><span>主操作员：</span><label id="mainBy">暂无</label></li>
				<li><span>当前工单：</span><label id="courseCode">暂无</label></li>
				<li><span>完成量：</span><label id="schel">暂无</label></li>
			</ul>
		</div>
		<div id="con">
			<div id="content">
			
			</div>
			<div id="imgs">
				<div id="mychart" style="width: 90%;height: 400px;">
			
				</div>
			</div>
		</div>
		
		
    
  </body>
</html>
