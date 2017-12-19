//以下是领料管理
	var g,g1,g2,li;
	$(function(){
		 urlStr = basePath+"rest/storeObjManageAction/loadStorePickingMaterialManage",
		 f_initGrid(urlStr);
		 f_initCharts1();
		 f_initCharts2();
		 f_initCharts3();
		 f_initCheckBox();
	}); 
	//初始化表格实现的是服务器端分页查询
    function f_initGrid(urlStr){ 
        g = $("#num21").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id',  width: 80 },
		            { display: '批次号', name: 'batchCode',  width: 80 },
		            { display: '材料', name: 'objSort',  width: 80 },
		            { display: 'RFID卡编码', name: 'rfidCode',  width: 120 },
		            { display: '型号', name: 'objGgxh',  width: 100 },
		            { display: '数量', name: 'acount',  width: 90 },
		            { display: '位置', name: 'address',  width: 90 },
		            { display: '操作人员', name: 'createBy',  width: 90 },
		            { display: '叉车编号', name: 'forkliftTruckNumber',  width: 90 },
		            { display: '送达机台', name: 'arriveMachine',  width: 90 },
		            { display: '班次', name: 'shift',  width: 90 },
		            { display: '调用班次', name: 'callFlight',  width: 80 },
		            { display: '出库时间', name: 'outDate',  width: 100 }
		        ], 
		        url:urlStr, 
		        pageSize: 10, 
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        width: '100%',
		        height:'50%',
		        enabledEdit: true, 
		        isScroll: true, 
		        checkbox:true,
		        onSuccess:function (json, grid){
		        	$("#pageloading").hide(); 
		        },
				onError:function (json, grid){
		        	$("#pageloading").hide(); 
		        },
	            onSelectRow: function (rowdata, rowindex){
	                $("#txtrowindex").val(rowindex);
	            }
	           
            });
    
        $(document).bind('keydown.grid', function (event){
	            if (event.keyCode == 13) { 
//		                g.endEditToNext();
		          }
            });
    } 
    //加载胶料echarts柱状图表
    function f_initCharts1(){
    	var myChart = echarts.init(document.getElementById('num11'));
    	var axis;
    	var series;
    	var objSort = '胶料';
    	var option;
    	myChart.showLoading({
    	    text: '正努在力的读取数据中...',
    	});
    	$.ajax({
			  url: basePath+"rest/storeObjManageAction/getChartDate?objSort="+objSort,
			  dataType: 'json',
			  success:function(map){
				    myChart.hideLoading();
				    axis = map.xAxis;
					series = [];
					var data = map.series;
					var obj = new Object();
					obj.name = '胶料';
					obj.type = 'bar';
					obj.barWidth = '45%';
					obj.itemStyle = {
						normal : {
						label : {
								show : true,
								position : 'inside'
							}
						}
					};
					obj.data =data;
					series.push(obj);
			    	option = {
			    		 color: ['#5F268B'],
						 title: {
					        text: '胶料入库',
					        subtext: '单位：千克(kg)'
					    },
			    	    legend: {                                   // 图例配置
			    	        padding: 5,                             // 图例内边距，单位px，默认上下左右内边距为5
			    	        itemGap: 10,                            // Legend各个item之间的间隔，横向布局时为水平间隔，纵向布局时为纵向间隔
			    	        data: ['胶料']
			    	    },
			    	    tooltip: {                                  // 气泡提示配置
			    	        trigger: 'axis',                        // 触发类型，默认数据触发，可选为：'axis'
			    	    },
			    	    xAxis:  {
						        type: 'category',
						        boundaryGap: true,
						        data: axis
						    },
						    yAxis: {
						        type: 'value'
						    },
			    	    series: series
			    	};
			    	myChart.setOption(option);
			  },
			  error: function() {
				  myChart.hideLoading();
				  alert("出错了，小兵兵");
			  }
		});
    }
    //加载铜线echarts柱状图表
    function f_initCharts2(){
    	var myChart = echarts.init(document.getElementById('num12'));
    	var axis;
    	var series;
    	var objSort = '铜料';
    	var option;
    	myChart.showLoading({
    	    text: '正努在力的读取数据中...',
    	});
    	$.ajax({
			  url: basePath+"rest/storeObjManageAction/getChartDate?objSort="+objSort,
			  dataType: 'json',
			  success:function(map){
				    myChart.hideLoading();
				    axis = map.xAxis;
					series = [];
					var data = map.series;
					var obj = new Object();
					obj.name = '铜料';
					obj.type = 'bar';
					obj.barWidth = '45%';
					obj.itemStyle = {
						normal : {
						label : {
								show : true,
								position : 'inside'
							}
						}
					};
					obj.data =data;
					series.push(obj);
			    	option = {
			    		 color: ['#CC3838'],
						 title: {
					        text: '铜料入库',
					        subtext: '单位：千克(kg)'
					    },
			    	    legend: {                                   // 图例配置
			    	        padding: 5,                             // 图例内边距，单位px，默认上下左右内边距为5
			    	        itemGap: 10,                            // Legend各个item之间的间隔，横向布局时为水平间隔，纵向布局时为纵向间隔
			    	        data: ['铜料']
			    	    },
			    	    tooltip: {                                  // 气泡提示配置
			    	        trigger: 'axis',                        // 触发类型，默认数据触发，可选为：'axis'
			    	    },
			    	    xAxis:  {
						        type: 'category',
						        boundaryGap: true,
						        data: axis
						    },
						    yAxis: {
						        type: 'value'
						    },
			    	    series: series
			    	};
			    	myChart.setOption(option);
			  },
			  error: function() {
				  myChart.hideLoading();
				  alert("出错了，小兵兵");
			  }
		});
    }
    //加载铝线echarts柱状图表
    function f_initCharts3(){
    	var myChart = echarts.init(document.getElementById('num13'));
    	var axis;
    	var series;
    	var objSort = '铝料';
    	var option;
    	myChart.showLoading({
    	    text: '正努在力的读取数据中...',
    	});
    	$.ajax({
			  url: basePath+"rest/storeObjManageAction/getChartDate?objSort="+objSort,
			  dataType: 'json',
			  success:function(map){
				    myChart.hideLoading();
				    axis = map.xAxis;
					series = [];
					var data = map.series;
					var obj = new Object();
					obj.name = '铝料';
					obj.type = 'bar';
					obj.barWidth = '45%';
					obj.itemStyle = {
						normal : {
						label : {
								show : true,
								position : 'inside'
							}
						}
					};
					obj.data =data;
					series.push(obj);
			    	option = {
			    		 color: ['#E6BB3A'],
						 title: {
					        text: '铝料入库',
					        subtext: '单位：千克(kg)'
					    },
			    	    legend: {                                   // 图例配置
			    	        padding: 5,                             // 图例内边距，单位px，默认上下左右内边距为5
			    	        itemGap: 10,                            // Legend各个item之间的间隔，横向布局时为水平间隔，纵向布局时为纵向间隔
			    	        data: ['铝料']
			    	    },
			    	    tooltip: {                                  // 气泡提示配置
			    	        trigger: 'axis',                        // 触发类型，默认数据触发，可选为：'axis'
			    	    },
			    	    xAxis:  {
						        type: 'category',
						        boundaryGap: true,
						        data: axis
						    },
						    yAxis: {
						        type: 'value'
						    },
			    	    series: series
			    	};
			    	myChart.setOption(option);
			  },
			  error: function() {
				  myChart.hideLoading();
				  alert("出错了，小兵兵");
			  }
		});
    }
    //初始化下拉列表框
    function f_initCheckBox(){
       var str = '';
       var array=[];
       var objSort ="原材料类型";
       $.ajax({
			  url: basePath+"rest/storeObjManageAction/getObjSortFromSysCommedic?objSort="+objSort,
			  dataType: 'json',
			  success:function(list){
				  var testList = list;
				  debugger;
				  for(var j =0; j<list.length;j++){
					  var objSortData = list[j].value;
					  debugger;
					  array.push(objSortData);
				  }
				  for(var j=0;j<array.length;j++){
			    	   str+="<option>"+array[j]+"</option>";
			       }
			      $("#flagOpt").after(str);
			  },
			  error: function() {
				  alert("出错了,小兵兵jjjj");
			  }
		});
    }
    //多条件查询
    function ComprehensiveSerch(){
    	var startTime= $("#startTime").val();
    	var endTime= $("#endTime").val();
		var objSort=$("#objSort").val();
		var objGgxh=$("#objGgxh").val();
		var rfidCode = $("#rfidCode").val();
		var urlStr = basePath+'rest/storeObjManageAction/getComprehensiveSerchFromStoreObj?startTime='
					+startTime+'&endTime='+endTime+'&objSort='+objSort+'&objGgxh='+objGgxh+'&rfidCode='+rfidCode;
		f_initGrid(urlStr);
    }
    //打印导出
    function ResultPrint(){
    	
    	var startTime= $("#startTime").val();
    	var endTime= $("#endTime").val();
		var objSort=$("#objSort").val();
		var objGgxh=$("#objGgxh").val();
		var rfidCode = $("#rfidCode").val();
    	//向后台发送请求
		var form=$("<form>");//定义一个form表单
		form.attr('style','display:none');
		form.attr('method','post');
		form.attr('action', basePath+'rest/storeObjManageAction/export?startTime='
				+startTime+'&endTime='+endTime+'&objSort='+objSort+'&objGgxh='+objGgxh+'&rfidCode='+rfidCode);
		//将表单放到body中
		$('body').append(form);
		debugger;
		form.submit();//提交表单
    } 
    
    
    
    
    
    
    
    