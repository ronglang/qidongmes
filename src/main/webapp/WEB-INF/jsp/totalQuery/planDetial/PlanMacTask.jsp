<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html style="height: 100%">

<head>
<meta charset="utf-8">
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>core/js/echarts.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>core/js/jquery-2.1.4.min.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
</head>

<body style="height: 99%; margin: 0;background-color: #F7F7F7;overflow: hidden;">
	<div
		style="text-align: center;position: absolute;left: 150px;z-index: 999;top: 9px;">
	</div>
	<div id="container" style="height: 100%;"></div>
	<script type="text/javascript">
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			//var app = {};
			option = null;

			var data = [];
			var aa,gds,form,start,end,type;
			var state = [{id:'未结束',text:'未结束'},{id:'结束',text:'结束'}];
			//var dataCount = 10;
			var startTime;
			var categories = [];
			var types = [];
			var gdsList;
			var getRandomColor = '#7b9ce1' ;
			
			$(function(){
				getCharts();
		});
			
			var gdColor =[];
			function getCharts(){
				$.ajax({
					type:'POST',
					url:'<%=basePath%>rest/plaMachinePlanManageAction/getTaskGanTe?start='+'${start}'+
							'&end='+'${end}'+'&mac='+'${mac}'+'&type='+'${type}',
						dataType : 'JSON',
						success : function(data) {
							debugger;
							categories = data.macs;
							aa = data.data;
							gds = data.gds;
							startTime = data.initTime;
							gdsList = data;
							getEach();
						},
						error : function() {
						}
					});
			}
		
			
		function getEach() {
			debugger;
			if(categories.length<1){
				data = [];
				data.push({
					name : "暂无数据",
					value : [0,0,0,0],
					itemStyle : {
						normal : {
						}
					}
				});
				if (getChart() && typeof getChart() === "object") {
					myChart.setOption(getChart(), true);
				}
			}else{
				echarts.util.each(categories, function(category, index) {
					var cc = aa[category];
					for (var i = 0; i < cc.length; i++) {
						var duration = (cc[i].end - cc[i].start) /60000;
						data.push({
							name : cc[i].name,
							value : [ index, cc[i].start, cc[i].end,
									duration.toFixed(2) ],
							itemStyle : {
								normal : {
									color : gdsList[cc[i].name]
								}
							}
						});
					}
				});

				if (getChart() && typeof getChart() === "object") {
					myChart.setOption(getChart(), true);
				}
				
			}
			
		}

		/* function getColor() {

			return '#'
					+ (function(color) {
						return (color += '0123456789abcdef'[Math.floor(Math
								.random() * 16)])
								&& (color.length == 6) ? color : arguments
								.callee(color);
					})('');
		} */

		function renderItem(params, api) {
			var categoryIndex = api.value(0);
			var start = api.coord([ api.value(1), categoryIndex ]);
			var end = api.coord([ api.value(2), categoryIndex ]);
			var height = api.size([ 0, 1 ])[1] * 0.6;
			return {
				type : 'rect',
				shape : echarts.graphic.clipRectByRect({
					x : start[0],
					y : start[1] - height / 2,
					width : end[0] - start[0],
					height : height
				}, {
					x : params.coordSys.x,
					y : params.coordSys.y,
					width : params.coordSys.width,
					height : params.coordSys.height
				}),
				style : api.style()
			};
		}

		function getChart() {

			option = {
				tooltip : {
					formatter : function(params) {
						return params.marker + params.name + ': '
								+ params.value[3] + 'min';
					}
				},
				title : {
					text : '机台负荷甘特图',
					subtext: '颜色相同的为同一工单',
					left : 'center'
				},
				toolbox : {
					show : true,
					right : '150',
					feature : {
						saveAsImage : {
							show : true
						}
					}
				},
				legend : {
					data : [ 'bar', 'error' ]
				},
				dataZoom : [ {
					type : 'slider',
					filterMode : 'weakFilter',
					showDataShadow : false,
					top : 300,
					height : 10,
					borderColor : 'transparent',
					backgroundColor : '#e2e2e2',
					handleIcon: 'M10.7,11.9H9.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4h1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7v-1.2h6.6z M13.3,22H6.7v-1.2h6.6z M13.3,19.6H6.7v-1.2h6.6z', // jshint ignore:line
					handleSize : 20,
					handleStyle : {
						shadowBlur : 6,
						shadowOffsetX : 1,
						shadowOffsetY : 2,
						shadowColor : '#aaa'
					},
					labelFormatter : ''
				}, {
					type : 'inside',
					filterMode : 'weakFilter'
				} ],
				grid : {
					height : 200
				},
				xAxis : {
					min : startTime,
					scale : true,
					axisLabel : {
						formatter : function(val) {
							return dateFtt("yyyy-MM-dd hh:mm:ss", new Date(val));
						}
					}
				},
				yAxis : {
					name : '机台编码',
					data : categories
				},
				series : [ {
					type : 'custom',
					renderItem : renderItem,
					itemStyle : {
						normal : {
							opacity : 0.8
						}
					},
					encode : {
						x : [ 1, 2 ],
						y : 0
					},
					data : data
				} ]
			};
			return option;
		}


		function dateFtt(fmt, date) { //author: meizz   
			var o = {
				"M+" : date.getMonth() + 1, //月份   
				"d+" : date.getDate(), //日   
				"h+" : date.getHours(), //小时   
				"m+" : date.getMinutes(), //分   
				"s+" : date.getSeconds(), //秒   
				"q+" : Math.floor((date.getMonth() + 3) / 3), //季度   
				"S" : date.getMilliseconds()
			//毫秒   
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
			return fmt;
		}
	</script>
</body>

</html>