<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>OEE图表统计查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath %>core/js/echarts.min.js"></script>
	<script src="<%=basePath%>core/js/jquery.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var type; //chart 展示的风格,line是折线,bar 柱状图	
		var typeName="";
		var option;
		var  myChart;
		$(function(){
			search();
		});
		
		function search(){
			var start = "2017-9-20";
			var end = "2017-10-20";
			var macCode = "LS_01";
			var bean = {};
			bean.start = start;
			bean.end = end;
			bean.macCode = macCode;
			var param = JSON.stringify(bean);
			var url = "<%=basePath %>rest/mauOEEManageAction/getSingleMacChart";
			$.post( url,{param:param},function(data){
				
				if (data.success) {
					var vo = data.data;
					setChart(vo);
				}else{
					alert(data.msg);
				}
			},"json");
		}
		
		function setChart(vo){
			debugger;
			option = {
				    title: {
				        text: "时间段"
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:vo.legend
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    toolbox: {
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: vo.xlist
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:vo.legend[0],
				            type:'line',
				            stack: '总量',
				            data:vo.lineList[0]
				        },
				        {
				            name:vo.legend[1],
				            type:'line',
				            stack: '总量',
				            data:vo.lineList[1]
				        },
				        {
				            name:vo.legend[2],
				            type:'line',
				            stack: '总量',
				            data:vo.lineList[2]
				        },
				        {
				            name:vo.legend[3],
				            type:'line',
				            stack: '总量',
				            data:vo.lineList[3]
				        }
				    ]
				};
			var dom = document.getElementById("chart");
		 	myChart = echarts.init(dom);
			myChart.setOption(option);
		}
	
	</script>
  </head>
  	
  	<div id="chart" style="height:600px;width:800px"></div>
  <body>
    
  </body>
</html>
