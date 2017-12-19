<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mauAllOEEChart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath %>core/js/echarts.min.js"></script>
	<script src="<%=basePath%>core/js/jquery.js"></script>
	<script type="text/javascript">
	var option;
	var myChart;
	
	var app = {};
	var labelOption = {
		    normal: {
			        show: true,
			        position: 'insideBottom',
			        distance: 15,
			        align: 'left',
			        verticalAlign: 'middle',
			        rotate: 90,
			        formatter: '{c}  {name|{a}}',
			        fontSize: 16,
			        rich: {
			            name: {
			                textBorderColor: '#fff'
			            }
			        }
			    }
			};
	$(function(){
		search();
	});
	
	function search(){
		var createDate = '2017-9-29';
		var bean = {};
		bean.createDate = createDate;
		var param = JSON.stringify(bean);
		var url = "<%=basePath %>rest/mauOEEManageAction/getAllMacChart";
		$.post(url,{param:param},function(data){
			if(data.success){
				setChart(data.data);
			}else{
				alert(data.msg)
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
			    color: ['#003366', '#006699', '#4cabce', '#e5323e'],
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        }
			    },
			    legend: {
			        data: vo.legend
			    },
			    toolbox: {
			        show: true,
			        orient: 'vertical',
			        left: 'right',
			        top: 'center',
			        feature: {
			            mark: {show: true},
			            dataView: {show: true, readOnly: false},
			            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    calculable: true,
			    xAxis: [
			        {
			            type: 'category',
			            axisTick: {show: false},
			            data: vo.xlist
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value'
			        }
			    ],
			    series: [
			        {
				            name:vo.legend[0],
				            type:'bar',
				            label: labelOption,
				            data:vo.lineList[0]
				        },
			        {
				            name:vo.legend[1],
				            type:'bar',
				            label: labelOption,
				            data:vo.lineList[1]
				        },
			        {
				            name:vo.legend[2],
				            type:'bar',
				            label: labelOption,
				            data:vo.lineList[2]
				        },
			        {
				            name:vo.legend[3],
				            type:'bar',
				            label: labelOption,
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
  
  <body>
   	<div id="chart" style="height:600px;width:800px"></div>
  </body>
</html>
