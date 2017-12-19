<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>高级查询</title>
<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<!-- 导入echart插件 -->
<script src="<%=basePath%>core/js/echarts.min.js"></script>
<script type="text/javascript">
$(function(){
	var myChart = echarts.init(document.getElementById('main'));
	option = {
			title: {
				text: '某站点用户访问来源',
				subtext: '纯属虚构',
				x: 'center'
			},
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
			},
			series: [{
				name: '访问来源',
				type: 'pie',
				radius: '55%',
				center: ['50%', '60%'],
				data: [
					{ value: 335, name: '直接访问' },
					{ value: 310, name: '邮件营销' },
					{ value: 234, name: '联盟广告' },
					{ value: 135, name: '视频广告' },
					{ value: 1548, name: '搜索引擎' }
				],
				itemStyle: {
					emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}]
		};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	
});
</script>
</head>
<body>
	<h1>欢迎光临echartdemo</h1>
	<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>
