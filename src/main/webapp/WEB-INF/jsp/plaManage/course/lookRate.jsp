<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工单进度详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js"  type="text/javascript"></script>
	<script src="<%=basePath%>core/js/cssBase.js"  type="text/javascript"></script>
	<style type="text/css">
		*{
				margin: 0;
				padding: 0;
				font-size: 13px;
			}
	</style>
	
	<script type="text/javascript">
		var id = '${id}';
		var courseCode = '${courseCode}';
		$(function(){
			queryRate(courseCode);	
		});
		
		//查询进度
		function queryRate(courseCode){
			var url = "<%=basePath%>rest/plaCourseManageAction/getRateEchart";
			$.post(url,{workCode:courseCode},function(data){
				if(data.success){
					createChart(data.data);
				}else{
					var vo = {};
					vo.title = "";
					vo.xAxis = [];
					createChart(vo);
				}				
			},"json");
		}
		
		var option;
		var mychart;
		var series = [];
		function createChart(vo){
			createSeria(vo);
			
			option={
					title: {
				        text: vo.title,
				        left: 'center'
				    },
					 color: ['#3398DB'],
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					        	name : '机台编号',
					            type : 'category',
					            data : vo.xAxis,
					            axisTick: {
					                alignWithLabel: true
					            }
					        }
					    ],
					    yAxis : [
					        {
					        	//min :100,
					        	name : '进度',
					            type : 'value'
					        }
					    ],
					    
					    series :series
					
			};
			debugger;
			var myChart = echarts.init(document.getElementById('mychart'));
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
		
		function createSeria(vo){
			debugger;
			var objs = vo.seriesData;
			if(objs == null ){
				return;
			}
			var legends = vo.legends;
			for(var i = 0; i<legends.length;i++){
				var obj = {};
				var name = legends[i];
				obj.name=name;
				obj.type='bar';
				var datas = objs[name];
				obj.data = datas.data;
				series[i] = obj;
			}
			
		}
	</script>
	
  </head>
  
  <body>
    <div id="mychart" style="width:98%;height:450px"></div>
  </body>
</html>
