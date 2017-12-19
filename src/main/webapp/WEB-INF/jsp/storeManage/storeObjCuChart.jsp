<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Cu</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<%-- <link href="<%=basePath %>app/css/bootstrap.min1.css" rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript" src="<%=basePath%>core/js/echarts.min.js"></script>
<script src="<%=basePath%>core/js/jquery.js"></script>
<script>var basePath  = '<%=basePath%>';</script>
</head>
  
  <body>
    <div id="temp" style="height: 295px;width:100%;"></div>
  </body>
  <script type="text/javascript">
var legends = [];
var series = [];
var axis = [];
var option ;
var myChart ;
var objSort = '铜料';
$(function() {
   	$.ajax({
   		type : "post"  ,
        async : false, //同步执行
        url : basePath+"rest/storeObjManageAction/getChartDate?objSort="+objSort,
        dataType : "json", //返回数据形式为json
        success : function(map) {
        	if(map!=null){
        		load(map);
        	}
        },
        error : function() {
			alert("图表数据加载失败");
			myChart.hideLoading();
		}
   	});
   	//myLoad();
  });
  function myLoad(){
  	var basePath = "<%=basePath%>";
		var urlPirfex = basePath.substring(7, basePath.length);
		var url = "ws://"+urlPirfex+"wlyRoomChangeWebSocketServlet";
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
			//在此处将数据封装到页面
			var josnBean = eval('('+event.data+')');
			//将后台数据封装到echart图标中
			//1.横轴
			createAreas(josnBean);
			//2.图例
			createLegends(josnBean);
			//3.数据
			createData(josnBean);
			option.legend.data = legends;
			option.xAxis.data = axis;
			option.series = series;
			myChart.setOption(option);
		}
		function onOpen(event) {
			//TODO 发送房间号到后台去,获取房间号
			webSocket.send('${id}');
// 			document.getElementById('data').innerHTML = '<br />' + ""+"start";
		}
	
		function onError(event) {
	// 		alert(event.data);
		}
  }
  
  function load(vo){
		// 初始化参数
		//1.x轴
		createAreas(vo);
		//2.图例
		createLegends(vo);
		//3.数据
		createData(vo);
 		option = {
         		color: ['#B8860B'],

 			    title: {
 			        text: '铜线入库',
 			        subtext: 'smtc'
 			    },
 			    tooltip: {
 			        trigger: 'axis'
 			    },
 			    legend: {
 			        data:['铜线']
 			    },
 			    toolbox: {
 			        show: true,
 			        feature: {
 			            saveAsImage: {}
 			        }
 			    },
 			    xAxis:  {
 			        type: 'category',
 			        boundaryGap: true,
 			        data: axis
 			    },
 			    yAxis: {
 			        type: 'value'
 			    },
 			    series: series

        };
		myChart = echarts.init(document.getElementById('temp'));
		myChart.setOption(option);
  }
  
  function createLegends(map){
	  legends = ['铜线'];
	 
  }
  
  function createAreas(map){
	  axis = map.xAxis;
  }
  
  var index = 0;
	function createData(map) {
		series = [];
		var data = map.series;
		debugger;
		//初始化数据，全部设置为10;
		var obj = new Object();
		obj.name = legends;
		obj.type = 'bar';
		obj.barWidth = '45%';
		obj.itemStyle = {
			normal : {
			label : {
			show : true,
			position : 'inside'
				}
			}
		};
		obj.data =data;
		series.push(obj);
		debugger;

	}
</script>
</html>
