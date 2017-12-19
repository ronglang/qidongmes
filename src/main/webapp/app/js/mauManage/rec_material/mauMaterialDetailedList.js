//定义全局变量
var ligerDialogOpen;
var grid;
var tempMainId;//本页存储mainId
var basePathUrl ;//所有的页面刷新，全部用这个地址
$(function(){
	basePathUrl = basePath+'rest/mauMaterialDetailManageAction/toListGridPage?mainId='+mainId;
	loadGrid(false);
});

function loadGrid(boo,mainId_param){
	var obj = new Object();
	obj.mainId = mainId;
	debugger;
	if(boo){
		if(!obj.mainId){
			obj.mainId = mainId_param;
		}
		obj.init = "add";
	}
	grid = $("#myGrid1").ligerGrid({
		title:"领料单明细",
		url : basePath+'rest/mauMaterialDetailManageAction/toListGridPageData',
		height : '50%',
		checkbox :true,
		parms :obj,
		enabledEdit: true,
		columns : [{
			display : '物料编号',
			name : 'materCode',
			align : 'left',
			width : 100,
			minWidth : 10
		}, {
			display : '物料名称',
			name : 'materName',
			minWidth : 30
		}, {
			display : '物料数量',
			name : 'materAmount',
			minWidth : 30
		}, {
			display : '物料记录编码',
			name : 'mmrCode',
			minWidth : 30
		}, {
			display : '计划领取数量',
			name : 'planCount',
			minWidth : 30
		}, {
			display : '单位',
			name : 'unit',
			minWidth : 30
		}, {
			display : '是否已领取',
			name : 'isReceive',
			minWidth : 30
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 10,
		toolbar : {
			items : [
	          {text:"初始化领料明细",click:initParam,icon:"delete"},
	          {text:"刷新",click:refresh,icon:"delete"}
			]
		},
		onDblClickRow :function (data,rowid,rowdata){
		}
		
	});
}
function refresh(){
	location.href = basePathUrl;
}



function initParam(){
	loadGrid(true);
//	location.href = basePathUrl;
}


/**
 * 领料按钮
 */
function receiveMater(){
	var data = $("#f1").serializeArray();
	var dataArr = []; 
	for(var i = 0 ;i < data.length;i++){
		var obj = new Object();
		obj.id = data[i].name;
		obj.materAmount = data[i].value;
		dataArr.push(obj);
	}
	data = JSON.stringify(dataArr);
	debugger;
	$.ajax({
		url: basePath+"rest/mauMaterialDetailManageAction/receiveMater",
		dataType: 'json',
		data: "data="+data+"&mainId="+mainId,
		type: "post",
		async: false,
		success:function(data1){
			alert("ajax"+mainId);
			debugger;
			//TODO 这里做两件事情
			//1.更新grid
			grid.reload();
			//2.封装下面参数
			$("#tab tr:not(:first):not(:last)").remove(); 
			var html = "";
			for(var i = 0;i < data1.data.length;i++){
				var bean = data1.data[i];
				html += "<tr><td>"+bean.materCode+","+bean.id+"</td><td>"+bean.materName+"</td><td>"+bean.macCode+"</td><td><input name=\""+bean.id+"\" id=\""+bean.id+"\" /></td></tr>";
			}
			
			$("#tab tr:eq(0)").after(html);
		}
	});
	debugger;
}






