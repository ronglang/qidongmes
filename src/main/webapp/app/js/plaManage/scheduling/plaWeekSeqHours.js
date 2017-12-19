var grid;
$(function(){
	debugger;
//	loadGrid(ids);
	loadGrid1(ids);
});
function loadGrid(ids){
//	var obj = eval('{'+ids+'}');
	debugger;
	alert("测试"+ids);
	alert(ids[0]);
	grid = $("#myGrid").ligerGrid({
		url : basePath+'rest/schedulingManageAction/plaWeekSeqHoursData',
		height : '100%',
		parms :{"ids":ids},
//		checkbox :true,
		columns : [{
			display : '工序编号',
			name : 'seqCode',
			align : 'left',
//			width : 100,
			minWidth : 10
		}, {
			display : '工序名称',
			name : 'seqName',
			minWidth : 30
		}, {
			display : '规格型号',
			name : 'proGgxh',
			minWidth : 30
		}, {
			display : '工序工时',
			name : 'seqHours',
			minWidth : 30
		}, {
			display : '计时单位',
			name : 'timeUnit',
			minWidth : 30
		}, {
			display : '创建人',
			name : 'create_by',
			minWidth : 30
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 10
//		,
////		rownumbers : true,
//		toolbar : {
//			items : [ {
//				text :'添加',
//	            click :add,
//	            icon :'add'
//	          },
//	          {text:"编辑",click:edit,icon:"edit"},
//	          {text:"删除",click:del,icon:"delete"}
//			]
//		}
	});
}

function loadGrid1(ids){
	grid = $("#myGrid").ligerGrid({
		url : basePath+'rest/schedulingManageAction/plaWeekSeqHoursData',
		height : '100%',
		parms :{"ids":ids},
//		checkbox :true,
		columns : [{
			display : '生产令编号',
			name : 'plaOrderId',
			align : 'left',
//			width : 100,
			minWidth : 10
		}, {
			display : '拉丝',
			name : 'laSi',
			minWidth : 30
		}, {
			display : '绞线',
			name : 'jiaoXian',
			minWidth : 30
		}, {
			display : '挤绝缘',
			name : 'jiJueYuan',
			minWidth : 30
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 30
	});
}
