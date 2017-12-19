	$(function(){
		
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
	       f_initGrid(list);
	       
	    };
			
	    websocket.onclose = function()
	    { 
	       // 关闭 websocket
	    };
		
	}
	
	function f_initGrid(list){
		var myChart = echarts.init(document.getElementById('main'));
		myChart.showLoading({
	    	    text: '正努在力的读取数据中...',
	    });
	    
		option = {
			title : {
			     text: '【'+list.machineName+'】任务完成情况',
			     x:'center',
			 },
		    tooltip : {
		        trigger: 'axis'
		    },
//		    legend: {
//		        data:['主机速度检测表']
//		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : list.areas,
		            //data : ['周一','周二','周三','周四','周五','周六','周日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'主机速度检测表',
		            type:'line',
		            stack: '总量',
		            data:list.dataList
		            //data:[20, 32, 31, 34, 19, 23, 21]
		        }
		    ]
		};
		myChart.hideLoading();
		myChart.setOption(option);
	}
