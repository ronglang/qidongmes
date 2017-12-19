<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'macInformaction_StateWarmming.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
  	<!-- 为ECharts准备一个具备大小（宽高）的 -->
    <div id="main" style="height:400px;width:600px"></div>
    <!-- ECharts单文件引入 -->
    <script src="<%=basePath%>app/js/echarts.min.js"></script>
    
    <script type="text/javascript">  
        var myChart = echarts.init(document.getElementById('main'));
		option = {
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['报警状态统计']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : ['周一','周二','周三','周四','周五','周六','周日']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'报警状态统计',
				            type:'line',
				            stack: '总量',
				            data:[120, 132, 101, 134, 90, 230, 210]
				        }
				    ]
				};
        
                // 为echarts对象加载数据 
                myChart.setOption(option);
    </script>    
  </body>
</html>
