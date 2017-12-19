var xAxis;//X轴
var series; //Y轴

$(function(){
	getInit();
	 webSokect();
	// f_initCharts();	
});
function getInit(){
	//var url =basePath+"rest/plaMachinePlanScheduleManageAction/initDisplayTask";
	var machineCode = "LS01";
	var seqCode = "SEQ_001";
	$.ajax({
		url: basePath+"rest/plaMachinePlanScheduleManageAction/getTaskByMachineId?requestType=portal",
		dataType: 'json',
		type: 'POST',
		data: "machineCode="+machineCode+"&seqCode="+seqCode,
		success: function(data){
			data = eval('('+data+')');
			if (data.dataList == null) {
				data.dataList = [];
			}
			if(data.areas == null){
				data.areas=[];
			}
			f_initCharts(data);
		},
		error: function(){
			alert("服务器出错!");
		}
		
	});
	//var url =basePath+"rest/plaMachinePlanScheduleManageAction/getTaskByMachineId?requestType=portal";
//	$.post(url,{machineCode:'LS01',seqCode:'SEQ_001'},function(data){
//		debugger;
//		data = eval('('+data+')');
//		if (data.dataList == null) {
//			data.dataList = [];
//		}
//		if(data.areas == null){
//			data.areas=[];
//		}
//		f_initCharts(data);
//	},"json");
}

function f_initCharts(list){
	
	debugger;
	var myChart = echarts.init(document.getElementById('main1'));
		myChart.showLoading({
	    	    text: '正努在力的读取数据中...',
	    });
	    
		option = {
				//backgroundColor: 'white',
					title : {
					     //text: list.mauMachine.macName+'当前机台任务完成情况',
						text: '拉丝工序任务完成情况',
					     x:'center',
					 },
				    //color: ['#3398DB'],
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
				        	name : '机台编号',
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
				            barWidth: '20%',
				            label: {
					            normal: {
					                show: true,
					                position: 'insideTop'
					            }
					        },
				            data: list.dataList,
				        }
				    ]
				};
	
		myChart.hideLoading();
		myChart.setOption(option);
}

function webSokect(){
	//var basePath = "<%=basePath%>";
	var urlPirfex = basePath.substring(7, basePath.length);
	var url = "ws://"+urlPirfex+"LSTaskWebSocket";
	var webSocket = new WebSocket(url);
	webSocket.onerror = function(event) {
		onError(event);
	};

	webSocket.onopen = function(event) {
		onOpen(event);
	};

	webSocket.onmessage = function(event) {
		onMessage(event);
	};
	//这里获得反馈的值,进行参数的赋值
	function onMessage(event) {
		//在此处将数据封装到页面
		debugger;
		var josnBean = eval('('+event.data+')');
		if (josnBean == null ) {
			var list = {};
			list.areas = [];
			list.dataList = [];
			f_initCharts(list);
		}else{
			f_initCharts(josnBean);
		}
		/*if ("true" == josnBean) {
			queryTable();
			//改变状态
			flag = 1;
			//只执行一次,一个小时还没有刷新,修改状态,使页面刷新
			setTimeout(function(){
				flag = 0;
			}, 60000*40);
		}*/
		//X轴 data.areas
		//数据 data.dataList
		
	}
	//传的参数
	function onOpen(event) { 
		webSocket.send("1");
	}

	function onError(event) {
// 		alert(event.data);
	}
}
