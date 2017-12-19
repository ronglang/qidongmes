$(document).ready(function(){
	
	$("#showtime").html(showTime);
	setInterval(function(){
		$("#showtime").html(showTime);
	},1000);
	
	webSocket();
});

/*计算当前时间*/
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
    var week = show_day[day];
    var now_time=year+'/'+month+'/'+date+'　'+hour+':'+minutes+':'+second+'　'+week; 
    
    return now_time;
} 

function webSocket(){
	
	var websocket = null;  
    //判断当前浏览器是否支持WebSocket  
    if ('WebSocket' in window) {
		var url = "ws://"+urlPath+"CLBoardWebSocket";
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
       for(var key in list){
    	   var a = "#"+key;
    	   $(a).html(list[key]);
       }
       
       
       
       //$("#head .h3").html(msg);
    };
		
    websocket.onclose = function()
    { 
       // 关闭 websocket
    };
	
}
