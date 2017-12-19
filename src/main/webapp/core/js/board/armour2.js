
$(document).ready(function(){
	taskwebSocket();
});

function taskwebSocket(){
	
	var websocket = null;
    //判断当前浏览器是否支持WebSocket  
    if ('WebSocket' in window) {
		var url = "ws://"+urlPath+"KZTaskWebSocket";
        websocket = new WebSocket(url);
    }  
    else {
        alert('当前浏览器不支持websocket，请更换浏览器！');  
    }
    websocket.onopen = function()
    {
       // Web Socket 已连接上，使用 send() 方法发送数据
    	websocket.send("基础");
    };
		
    websocket.onmessage = function (evt) 
    { 
       var list = eval('('+evt.data+')');
       //为图标添加数据
       f_initCharts(list);
       
    };
		
    websocket.onclose = function()
    { 
       // 关闭 websocket
    };
	
}

function f_initCharts(list){
	var myChart = echarts.init(document.getElementById('main1'));
		myChart.showLoading({
	    	    text: '正努在力的读取数据中...',
	    });
	    
		option = {
				backgroundColor: 'white',
					title : {
					     text: '【'+list.machineName+'】任务完成情况',
					     x:'center',
					 },
				    color: ['#3398DB'],
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				        
				    },
				    toolbox: {
				        show: true,
				        right: '7%',
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '8%',
				        bottom: '1%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				        	name : '工单编号',
				            type : 'category',
				            //data : ['工单编号1', '工单编号2', '工单编号3', '工单编号4', '工单编号5', '工单编号6', '工单编号7'],
				            data : list.areas,
				            axisTick: {
				                alignWithLabel: true
				            }
				        }
				    ],
				    yAxis : [
				        {
				        	name:'百分比（%）',
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            type:'bar',
				            barWidth: '60%',
				            label: {
					            normal: {
					                show: true,
					                position: 'insideTop'
					            }
					        },
					        data:list.dataList
				            //data:[100, 100, 100, 60]
				        }
				    ]
				};
		
		
		myChart.hideLoading();
		myChart.setOption(option);
}
		/*
		option = {
			 title : {
			     text: '机台进度甘特表',
			     x:'center',
			 },
			 calculable:true,
			 tooltip : {
			     trigger: 'axis',
			     axisPointer:{
			  		type : 'line',
				    lineStyle : {
					    color: '#48b',
					    width: 2,
					    type: 'solid'
				  	}
			     }
			 },
			 legend: {
			     orient: 'vertical',
			     x:'left',
			     data:   ["最高气温"]
			 },
			 calculable : true,
			 yAxis : [{
			  type:"category",
			  data:["项目完成", "问卷设计", "试访", "问卷确定","实地执行","数据录入","数据分析","项目未完成"]
			 }],
			 xAxis : [{
			  type : 'value',
			  axisLabel : {
			   		formatter: function (value){
				         var end_time = 1418428800000;
				         var start_time = 1417392000000;
				         var date = new Array();
				         var i = 1;
				         while(end_time > start_time){
						    var date_formatter = new Date(start_time);
						    var date_time = (date_formatter.getFullYear()+"-"+(date_formatter.getMonth()+1)+"-"+date_formatter.getDate());
						    date.push(date_time);
						    start_time = start_time + i*24*60*60*1000;
				         }
				         return date[value*1];
			       }
			  },
			 }],
			 series : [{
			   name:"辅助",
			   type:"bar",
			   stack:"总",
			   itemStyle:{
			    normal:{
			     barBorderColor:'rgba(0,0,0,0)',
			     color:'rgba(0,0,0,0)'
			    },
			    emphasis:{
			     barBorderColor:'rgba(0,0,0,0)',
			     color:'rgba(0,0,0,0)'
			    }
			   },
			   data:[0,1,2,3,4,5,6]
			  },
			  {
			   name:"项目完成",
			   type:"bar",
			   stack:"abc",
			   data:[0.8,0,0,0,0,0,0]
			  },
			  {
			   name:"项目未完成",
			   type:"bar",
			   stack:"abc",
			   data:[0.2,0,0,0,0,0,0]
			  },
			  {
			   name:"问卷设计",
			   type:"bar",
			   stack:"总",
			   data:[0,1,0,0,0,0,0]
			  },
			  {
			   name:"试访",
			   type:"bar",
			   stack:"总",
			   data:[0,0,1,0,0,0,0]
			  },
			  {
			   name:"问卷确定",
			   type:"bar",
			   stack:"总",
			   data:[0,0,0,1,0,0,0]
			  },
			  {
			   name:"实地执行",
			   type:"bar",
			   stack:"总",
			   data:[0,0,0,0,1,0,0]
			  },
			  {
			   name:"数据录入",
			   type:"bar",
			   stack:"总",
			   data:[0,0,0,0,0,1,0]
			  },
			  {
			   name:"数据分析",
			   type:"bar",
			   stack:"总",
			   data:[0,0,0,0,0,0,1]
			  }
			 ]
		};
		*/

