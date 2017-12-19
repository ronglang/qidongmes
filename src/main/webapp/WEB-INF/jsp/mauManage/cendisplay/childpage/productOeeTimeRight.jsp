<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>OEE值随时间波动情况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		
	<script type="text/javascript">
		
		$(function(){
			
			myCharts();
			
		});
		
		function myCharts(){
			var myChart = echarts.init(document.getElementById('oee'));
			option = {
				backgroundColor: '#FFFCF5',
				color: [ '#F20AD1','#05E836','#0F0F0F','#01B6ED','#F62D7B'],
			    title: {
			        text: 'OEE值随时间波动情况',
			        textStyle: {
			        	color: '#272822',
			      	},
			    },
			    legend: {
			    	x: 'right',
			        data:['铠装工序','集线工序','包带工序','挤护套工序','印字工序'],
			    },
			    xAxis:  {
			    	name: '时间',
			        type: 'category',
			        boundaryGap: false,
			        data: ['2.00','4.00','6.00','8.00','10.00','12.00','14.00','16.00','18.00','20.00','22.00','24.00'],
			        
			    },
			    yAxis: {
			    	name: '百分比',
			        type: 'value',
			        axisLabel: {
			            formatter: '{value}%'
			        }
			    },
			    series: [
			        {
			            name:'铠装工序',
			            type:'line',
			            data:[51, 67, 65, 56, 86, 67, 73,72,84,68,69,93],
			        },
			        {
			            name:'集线工序',
			            type:'line',
			            data:[71, 56, 60, 76, 56, 77, 63,74,74,68,65,83],
			        },
			        {
			            name:'包带工序',
			            type:'line',
			            data:[61, 64, 85, 86, 56, 47, 53,92,64,48,59,83],
			        },
			        {
			            name:'挤护套工序',
			            type:'line',
			            data:[76, 73, 69, 74, 76, 56, 43,92,64,48,56,63],
			        },
			        {
			            name:'印字工序',
			            type:'line',
			            data:[48, 77, 71, 80, 76, 57, 84,52,64,78,89,53],
			        }
			    ]
			};
			
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			
		}
		
	</script>
	
	<style type="text/css">
		#info{
			padding: 0;
			width: 99%;
			height: 500px;
			margin: 0 auto;
			background-color: #224B81;
			border-radius:8px;
		}
		.mr_top{
			width: 775px;
			height: 500px;
			border-radius:8px;
		}
	</style>
	

  </head>
  
  <body>
    
    <div id="info">
		<div class="mr_top" id="oee"></div>
	</div>
    
  </body>
</html>
