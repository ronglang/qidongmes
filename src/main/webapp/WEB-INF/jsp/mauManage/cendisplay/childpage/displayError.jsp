<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工单不合格轴数</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
		
<script type="text/javascript">
	$(function(){
		
		//工单生产进度
		errorNum();
		
	});
	
	//工单不合格轴数
	function errorNum(){
		
		var myChart = echarts.init(document.getElementById('errorNum'));
		option = {
			color: ['#FFFC01'],
			textStyle: {
		        	color: '#F2F2F2',
		      	},
		    title : {
		        text: '工单不合格轴数',
		        textStyle: {
		        	color: '#FFF300',
		      	},
		      	left: 'center'
		        //subtext: '纯属虚构'
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '10%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		        	name: '轴数(个)',
		            type : 'category',
		            data : ['ghsgh-001', 'ghsgh-001', 'ghsgh-001', 'ghsgh-001', 'ghsgh-001', 'ghsgh-001', 'ghsgh-001'],
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		        	name:'工单号',
		            type : 'value'
		        }
		    ],
		    series : [  
		        {
		            name : '轴数',
		            type:'bar',
		            barWidth: '40%',
		            label: {
			            normal: {
			                show: true,
			                position: 'top'
			            }
			        },
		            data:[10, 3, 12, 5, 3, 4, 7]
		        }
		    ]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

	}
	
</script>
<style type="text/css">
	.error{
		width: 100%;
		height: 280px;
		margin: 0 auto;
	}
</style>

  </head>
  
  <body>
    <div id="errorNum" class="error"></div>
  </body>
</html>
