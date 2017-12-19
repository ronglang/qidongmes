<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>机台历史速度图表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath %>core/css/bootstrap.css" rel="stylesheet" type="text/css">
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js"  type="text/javascript"></script>
	
	<script type="text/javascript">
		var id = '${id}';
		var bean;
		//押出机code
		var YACHU = ["EA120","EB","EC","ED","EQ","EK","EJ"];
		var option = {};
		var macCode ;
		$(function(){
			setChart();
		});
		
		function setChart(){
			if(id.indexOf(",") > 0 ){
					var url ="<%=basePath%>rest/mauOEEHistoryManageAction/getChartsIds";
					$.post(url,{ids:id},function(data){
						if(data.success){
							var vojson = data.data;
							//var vo = JSON.parse(vojson);
							getChart(vojson);
						}else{
							var vo = {};
							vo.title="";
							getChart(vo);
						}
					},"json");
			}else{
				var url = "<%=basePath%>rest/mauOEEHistoryManageAction/getById";
				$.post(url,{id:id},function(data){
					if(data.success){
						var datas  = data.data;
						var echarts = datas.echartsVO;
						 var vo  = JSON.parse(echarts);
						getChart(vo);
					}else{
						var vo = {};
						vo.title="";
						getChart(vo);
					}
				},"json");
			}
			
		}
		
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
	debugger;
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
  	 <div class="container">
			<div class="row">
				<h1 class="col-md-6 col-md-offset-4">机台历史速度查询</h1>
			</div>
	</div>
	<div class="container">
		<div class="row">
			
		</div>
	</div>
    <div id="imgs">
		<div id="mychart" style="width: 90%;height: 400px;">
		</div>
	</div>
  </body>
</html>
