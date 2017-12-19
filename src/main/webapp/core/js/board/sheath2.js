$(document).ready(function(){
	
	$("#showtime").html(showTime);
	setInterval(function(){
		$("#showtime").html(showTime);
	},1000);
	
	//图
//	$("#two .left").css("width",winWidth/2+'px');
//	$("#two .right").css("width",winWidth/2+'px');
	
//	getCheckSpeed();
//	getCheckOD();
	taskwebSocket();
	
	//websocket影射名称：JHTTaskWebSocket
	
});

function showTime(){ 
    var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六'); 
    var time=new Date(); 
    var year=time.getFullYear();
    var month=time.getMonth();
    var date=time.getDate(); 
    var day=time.getDay(); 
    var hour=time.getHours(); 
    var minutes=time.getMinutes(); 
    var second=time.getSeconds(); 
    month=parseInt(month)+1;
    month<10?month='0'+month:month;
    hour<10?hour='0'+hour:hour; 
    minutes<10?minutes='0'+minutes:minutes; 
    second<10?second='0'+second:second; 
    var now_time=year+'/'+month+'/'+date+'　'+hour+':'+minutes+':'+second+'　'+show_day[day]; 
    
    return now_time;
} 
function taskwebSocket(){
	
	var websocket = null;  
    //判断当前浏览器是否支持WebSocket  
    if ('WebSocket' in window) {
		var url = "ws://"+urlPath+"JHTTaskWebSocket";
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
       getTackPoint(list);
       
    };
		
    websocket.onclose = function()
    { 
       // 关闭 websocket
    };
	
}

function getTackPoint(list){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('rightImg'));
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
	        	name : '轴数',
	            type : 'category',
	            //data : ['轴1', '轴2', '轴3', '轴4', '轴5', '轴6', '轴7', '轴8', '轴9', '轴10'],
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
		 // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        
}



function getCheckSpeed(){
	var myChart = echarts.init(document.getElementById('leftTop'));
	option = {
		backgroundColor: 'white',
	    title: {
	        text: '当前工单主机变化情况',
	        left: 'center'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '3%',
	        right: '8%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	    	right: '6%',
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	    	name: '轴数',
	        type: 'category',
	        boundaryGap: false,
	        data : ['轴1', '轴2', '轴3', '轴4', '轴5', '轴6', '轴7', '轴8', '轴9', '轴10'],
	    },
	    yAxis: {
	    	name: '转速（m/s）',
	        type: 'value'
	    },
	    series: [
	        {
//	            name:'邮件营销',
	            type:'line',
	            stack: '总量',
	            label: {
	            	
		            normal: {
		                show: true,
		                color : 'blue',
		                position: 'insideTop'
		            }
		        },
	            data:[16, 23, 14, 44, 35, 25, 32, 25, 55, 58],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            }
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function getCheckOD(){
	var myChart = echarts.init(document.getElementById('leftBottom'));
	option = {
		backgroundColor: 'white',
	    title: {
	        text: 'OD值参数检测表',
	        left: 'center'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '3%',
	        right: '8%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	    	right: '6%',
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	    	name: '时间',
	        type: 'category',
	        boundaryGap: false,
	        data : ['0:00', '2:00', '4:00', '6:00', '8:00', '10:00', '12:00', '14:00', '16:00', '18:00','20:00','22:00','24:00'],
	    },
	    yAxis: {
	    	name: '单位（mm）',
	        type: 'value'
	    },
	    series: [
	        {
//	            name:'邮件营销',
	            type:'line',
	            stack: '总量',
	            label: {
	            	
		            normal: {
		                show: true,
		                color : 'blue',
		                position: 'insideTop'
		            }
		        },
	            data:[5.5, 4.2, 5.3, 5, 4.8, 6.8, 6.7, 7.5, 5.9, 6.6,5.8,6.9,6],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            }
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

