var form;
$(function(){
	loadForm();
	loadGrid("proState=2");
});

function loadForm(){
//	 { display: "材料", name: "materie",  
//	    	newline :false,icon:'search',type:"select",comboboxName: "materie" ,
//	    	options:{data:materies}
//	    },
	var states = [];
	var obj = new Object();
	obj.id = "2";
	obj.text = "未生产";
	states.push(obj);
	debugger;
	var obj1 = new Object();
	obj1.id = "1";
	obj1.text = "已生产";
	states.push(obj1);
	form = $("#myForm").ligerForm({
		inputWidth: 150, 
		labelWidth: 120, 
		space: 40,
		fields: [
	         {display: "生产状态", name: "proState",  newline:false,type:"select",comboboxName:"proState",options:{data:states}},
	         {display: "计划开始日期", name: "startDate",  newline:false,type:"date"},
	         {display: "计划完成日期", name: "endDate",  newline:false,type:"date"}
		],
		buttons:[
		         {text:"搜索",click:function(){search();}}
        ]
	}); 
}
function search(){
	var data = form.getData();
	if(!data.startDate){
		alert("开始日期不能为空");
		return;
	}else if(!data.endDate){
		alert("结束日期不能为空");
		return;
	}
	var startDate = parse(data.startDate);
	var endDate = parse(data.endDate);
	data.startDate = startDate;
	data.endDate = endDate;
//	alert(parms);
	loadGrid(data);
}

function parse(date){
	var year = date.getFullYear();//年
	var month = date.getMonth()+1;//月
	var day = date.getDate();//日
	var result = year+"-"+(month>=10?month:"0"+month)+"-"+(day>=10?day:"0"+day);
	return result;
}

/**
 * 加载数据
 */
function loadGrid(param){
	debugger;
	grid = $("#myGrid").ligerGrid({
		url : basePath+'rest/inSingleSystemManageAction/inSingleSystemData',
		height : '80%',
		checkbox :true,
		parms :param,
		columns : [{
			display : '生产令',
			name : 'orderCode',
			align : 'left',
//			width : 100,
			minWidth : 10
		}, {
			display : '计划开工日期',
			name : 'planStartTime',
			minWidth : 30
		}, {
			display : '计划完成日期',
			name : 'planEndTime',
			minWidth : 30
		}, {
			display : '所需工时',
			name : 'hours',
			minWidth : 30
		}
//		, {
//			display : '物料编码',
//			name : 'materielCode',
//			minWidth : 30
//		}, {
//			display : '物料规格',
//			name : 'materielGgxh',
//			minWidth : 30
//		}, {
//			display : '产品型号',
//			name : 'proGgxh',
//			minWidth : 30
//		}, {
//			display : '颜色',
//			name : 'color',
//			minWidth : 30
//		}
		, {
			display : '数量',
			name : 'count',
			minWidth : 30
		}, {
			display : '交货日期',
			name : 'deliveryTime',
			minWidth : 30
		}, {
			display : '合同编号',
			name : 'contractCode',
			minWidth : 30
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 10
//		rownumbers : true,
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