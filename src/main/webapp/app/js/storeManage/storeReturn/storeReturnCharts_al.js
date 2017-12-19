var legends = [];
var series = [];
var axis = [];
var option ;
var myChart ;

$(function(){
	queryCharts();
});

function queryCharts(){
	$.post(basePath + 'rest/'+ routeName + 'Action/queryCharts',{material:'铝杆'},function(data){
		setChart(data);
	},"json");
}
function setChart(data){
	 createXAxis(data);
	 createSeries(data);

	option = {
			color: ['#C1CDC1'],
			tooltip : {
				trigger: 'axis',
				showDelay : 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
				}
    },
    title : {
		text : '铝料退料',
		subtext : 'SWTC',
	},
	 toolbox: {
	        show: true,
	        feature: {
	       //     saveAsImage: {}
			mark: {show: true},
			dataView: {show: true, readOnly: false},
			//magicType: {show: true, type: ['line', 'bar']},
			//restore: {show: true},
			saveAsImage: {show: true}
	        }
	    },
	legend : {
	    	data: ['铝料']
	    },
    xAxis : axis,
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : series,
};
	//debugger;
		myChart = echarts.init(document.getElementById('mainBar'));
		myChart.setOption(option);
}
function createXAxis(data){
	debugger;
	axis = [];
	var da = data.xAxis;
	var obj = new Object();
	obj.type= 'category';
	obj.boundaryGap = true;		//是否从0开始,还是离0有一段距离
	obj.axisTick = {alignWithLabel: true};
	obj.data = da;
	axis.push(obj);
 }
 
function createSeries(data){
	debugger;
	series = [];
	var da = data.series;
	var obj = new Object();
	obj.name='铝料';
	obj.itemStyle = {
			normal : {
			label : {
			show : true,
			position : 'inside'
				}
			}
		};
	obj.type = 'bar';
	obj.barWidth = '60%';
	obj.data = da;
	//设置柱的宽度，要是数据太少，柱子太宽不美观~
	obj.barWidth  = '10%' ;
	series.push(obj);
 }
