$(document).ready(function(){
	
	$("#showtime").html(showTime);
	setInterval(function(){
		$("#showtime").html(showTime);
	},1000);
	//getCheckSpeed();
	//getCheckWendu();
	
	taskwebSocket();
	

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
		var url = "ws://"+urlPath+"CLTaskWebSocket";
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
	$("#machineName").html(list.machineName);
	debugger;
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('bottomimg'));
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
		        }
		    ]
		};
		 // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        
}


/*得到速度检测表*/
function getCheckSpeed(){
	var myChart = echarts.init(document.getElementById('topimg'));
	option = {
		backgroundColor: 'white',
	    title: {
	        text: '工单主机平均转速变化情况',
	        left: 'center'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
//	    legend: {
//	        data:['邮件营销'],
//	        left: 'center',
//	        top: '10%'
//	    },
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
	        data : ['轴1', '轴2', '轴3', '轴4', '轴5', '轴6', '轴7'],
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
	            data:[16, 23, 14, 44, 35, 25, 32],
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

/*得到八段温度检测表*/
function getCheckWendu(){
	// 基于准备好的dom，初始化echarts实例2
    var myChart = echarts.init(document.getElementById('right'));
	var colors = ['#FF0000', '#00B050', '#02CEEA','#9E480E','#70AD47','#D47B40','#D7B420','#DCB71D'];
    // 指定图表的配置项和数据
    option = {
	    backgroundColor: 'white',
	    title : {
	        text: '八段温度检测表',
	        left: 'center',
	        top: 30,
	        textStyle: {
	            color: 'black'
	        }
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        left: '3%',
	        right: '7%',
	        bottom: '3%',
	        containLabel: true
	    },
	    legend: {
	        data:['一段温度','二段温度','三段温度','四段温度','五段温度','六段温度','七段温度','八段温度']
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis:  {
	        type: 'category',
	        boundaryGap: false,
	        name:'时间',
	        data: ['0.00','2.00','4.00','6.00','8.00','12.00','14.00','16.00','18.00','20.00','22.00','24.00']
	    },
	    yAxis: {
	        type: 'value',
	        name:'温度(摄氏度)',
	        axisLabel: {
	            formatter: '{value}'
	        }
	    },
	    series: [
	        {
	            name:'一段温度',
	            type:'line',
	            data:[32, 31, 30, 28, 28.5, 31, 31.5, 32, 32.5,33,33.5,34.5]
	        },{
	            name:'二段温度',
	            type:'line',
	            data:[29, 28, 27, 26, 25.5, 25, 24.5, 24, 23.5,33,33.5,34.5]
	        },{
	            name:'三段温度',
	            type:'line',
	            data:[32.5, 32, 31, 30, 29, 31, 31.5, 32, 32.5,33.5,32,32.5]
	        },{
	            name:'四段温度',
	            type:'line',
	            data:[33, 31, 30, 29, 28.5, 27, 29, 30, 31.5,33,34,33.5]
	        },{
	            name:'五段温度',
	            type:'line',
	            data:[25.3, 24, 23.5, 23, 24.5, 25, 26.5, 27, 26.5,25,26,26.5]
	        },{
	            name:'六段温度',
	            type:'line',
	            data:[21, 23, 24, 25, 26, 25.5, 24.5, 23, 24,24.5,26,27]
	        },{
	            name:'七段温度',
	            type:'line',
	            data:[33, 31, 30, 28.5, 28, 29, 30, 31.5, 32.5,30,32,31.5]
	        },{
	            name:'八段温度',
	            type:'line',
	            data:[18, 18.5, 19, 19.5, 20,20.5, 21.5, 22, 23,24,25,26]
	        }
	    ]
	};
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
