var cdate = "<%=date%>";
   var cseqName = "<%=seqName%>";
var dom = null;//document.getElementById("container");
//alert('dom:'+dom);
var myChart = null;//echarts.init(dom);
var app = {};
option = null;
var dataAxis;
var data,form;
var yMax = 24;
var dataShadow = [];

$(function(){
	if(gdh)
		$('#gdh').html(gdh);
	initLayout();
	loadTree();
	dom = document.getElementById("container");
	myChart = echarts.init(dom);
});

function initLayout(){
	$("#divpar").ligerLayout({inWindow:true, leftWidth: 200, height: '100%',allowLeftResize:true,allowLeftCollapse:true});
	// var  height = $("#treediv").height();
	 //$("#rightdiv").ligerLayout({ centerWidth: 700, height: '100%',allowLeftResize:true,allowLeftCollapse:true});
	/*$("#rightdiv").ligerAccordion({
			height: height,
			width:200
	});*/
	//$("#layout1").ligerLayout({ leftWidth: 200 ,allowLeftCollapse:false}); 
}

function item(vo){
	dataAxis = vo.barX; //获取X轴数据
	data = vo.barY; //获取Y轴数据
	getChart();
}

function getBar() {

	option = {
		title : {
			left : 'center',
			text : '工序/机台负荷柱状图',
			subtext : 'SMTC'
		},
		tooltip : {
			trigger : 'axis',
		},
		grid : {
			top : '25%',
			containLabel : true
		},
		xAxis : {
			name:'工作日',
			data : dataAxis,
			type : 'category',
			boundaryGap : true,
		},
		yAxis : {
			name:'工作时间(h)',
			axisLabel : {
				textStyle : {
					color : '#999'
				}
			}
		},
		toolbox : {
			show : true,
			right:'5%',
			feature : {
				saveAsImage : {
					show : true
				}
			}
		},
		dataZoom : [ {
			type : 'inside'
		} ],
		series : [ { // For shadow
			type : 'bar',
			barGap : '-100%',
			barCategoryGap : '40%',
			data : dataShadow,
			animation : false,
		}, {
			type : 'bar',
			itemStyle : {
				normal : {
					label:{show:true},
					color : function(params) {
						var index_color = params.value;
						if (index_color >= 24) {
							return '#EE9A49';
						} else if(index_color == -1){
							return '#8A2BE2';
						}
						else {
							return '#6495ED';
						}

					}
				}
			},
			data : data
		} ]
	};
	return option;
}

// Enable data zoom when user click bar.

function getChart() {
	myChart.on('click', function(params) {
		//alert(params.name);
		grids.set({url:"<%=basePath %>rest/plaProductOrderSeqHourseManageAction/getPlaProductOrderSeqHourse?date="+cdate+"&seqName="+cseqName+"&macCode="+encodeURIComponent(encodeURIComponent(params.name))});
	});
	;
	if (getBar() && typeof getBar() === "object") {
		myChart.setOption(getBar(), true);
	}

}

function queryChart(level,nodeId){
	var seqCode='' ,machineId='';
	if(level == '1')
		seqCode = nodeId;
	
	if(level == '2')
		machineId = nodeId;
	var url = basePath+'rest/plaCourseManageAction/getGDInfo?courseId='+row_id+'&seqCode='+ seqCode + '&machineId=' + machineId+"&proGgxh="+proGgxh+"&proColor="+proColor;
	
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		success : function(json) {
			if(json.success){
				var obj = json.data;
				item(obj);
			}
			else{
				ajaxError(json);
			}
		},
		error : function(json) {
			ajaxError(json);
		}
	});
}

var tree;
function loadTree(){
	tree = $("#treediv").ligerTree({
		url: basePath+"rest/craRouteSeqManageAction/toTree?courseCode="+row_id,  
		nodeWidth : 100,
//		onBeforeExpand: onBeforeExpand,
		isExpand :true,
		dataType:'json',
		checkbox :true,
		//parentIcon :'',
		onClick :function(data,target){
			//debugger;
//			alert(data.data.id);
//			alert("层级"+data.data.level);
			id = null;
			
			if(data.data.level=='1'){//层级为3  工序级
				//alert(1);
				//$("#f1").attr("src",basePath+"rest/craSeqParamManageAction/toListPage?id="+data.data.id);
				queryChart('1',data.data.id);
			}else if(data.data.level == '2'){  //产品工艺级
				//alert(2);
				//id = data.data.id;
				//queryReadyData(id);
				//$("#f1").attr("src",basePath+"rest/craProSeqRelationManageAction/toListPageCore?proCraftCode="+data.data.id);
				queryChart('2',data.data.id);
			}
		}
	});	
}


function save(){
	//var row = tree.getSelected();
	var row = tree.getChecked();
	if(row.length == 0){
		var yes = window.confirm("不选择机台系统将自动计算。并各工序只选择一个机台。 确认执行?");
		if(!yes) return;
	}
	
	//开始执行分解。
	var arr = new Array();
	if(row.length > 0){
		for(var i = 0 ;i < row.length ; i ++){
			var js = {"seqCode":row[i].data.parentId,"level":row[i].data.level,"id":row[i].data.id}; 
			arr.push(js);
		}
	}
	
	genGD(arr);
}

function genGD(row){
	var j = JSON.stringify(row);
	var jj = $.param(j);
	var url = basePath + "rest/" + routeName + "Action/genPlanByGd?ids=" + row_id+"&macstr="+j;
	
	$('#btn_kd').attr('disabled','true');
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		async : true,
		success : function(json) {
			$('#save_btn').removeAttr('disabled');
			if(json.success){
				ajaxSuccess(json);
				var obj = new Object();
				obj.data = true;
				window.returnValue=obj;
				window.close();
			}
			else{
				$('#save_btn').removeAttr('disabled');
				ajaxError(json);
			}
			//grid.reload();
		},
		error : function(json) {
			$('#save_btn').removeAttr('disabled');
			ajaxError(json);
		}
	});
}

//提示框后要关闭窗口。 只能用alert.
function ajaxSuccess(json){
	if (json.success) {
		if(json.msg){
			//top.$.ligerDialog.success( json.msg );
			//$.ligerDialog.success( json.msg );
			alert(json.msg );
		}else{
			var result = jQuery.parseJSON(json.responseText);
			if(result){
				//top.$.ligerDialog.success(result.msg);
				//$.ligerDialog.success(result.msg);
				alert(result.ms);
			}
			else{
				//top.$.ligerDialog.success(json.responseText);
				//$.ligerDialog.success(json.responseText);
				alert(json.responseText);
			}
		}
	}
	else{
		//alert(json.msg+','+json.success+','+json.responseText);
		if(json.msg){
			//top.$.ligerDialog.error(json.msg);
			$.ligerDialog.error(json.msg);
		}else{
			var result = jQuery.parseJSON(json.responseText);
			if(result){
				//top.$.ligerDialog.error(result.msg);
				$.ligerDialog.error(result.msg);
			}
			else{
				//top.$.ligerDialog.error(json.responseText);
				$.ligerDialog.error(json.responseText);
			}
		}
	}
}