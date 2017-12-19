//定义全局变量
var ligerDialogOpen;
var grid;
$(function(){
	loadGrid(false);
});

function loadGrid(boo){
	var obj = new Object();
	obj.relationId = relationId;
	if(boo){
		obj.init = "add";
	}
	grid = $("#myGrid").ligerGrid({
		url : basePath+'rest/craGatheringModeManageAction/dataGrid',
		height : '100%',
		checkbox :true,
		parms :obj,
		enabledEdit: true,
		columns : [{
			display : '产品工艺编码',
			name : 'proCraftCode',
			align : 'left',
			width : 100,
			minWidth : 10
		}, {
			display : '集绞方式',
			name : 'gatheringMode',
			minWidth : 30
		}, {
			display : '来料规格',
			name : 'proGgxh',
			minWidth : 30,
			editor: { type: 'text' }
		}, {
			display : '来料数量',
			name : 'amount',
			minWidth : 30
//			editor: { type: 'text' }
		}, {
			display : '来料颜色',
			name : 'proColor',
			minWidth : 30
//			editor: { type: 'text' }
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 10,
//		rownumbers : true,
		toolbar : {
			items : [ {
				text :'保存',
	            click :mySave,
	            icon :'add'
	          },
//	          {text:"编辑",click:edit,icon:"edit"},
//	          {text:"删除",click:del,icon:"delete"},
//	          {text:"初始化参数",click:initParam,icon:"delete"}
			]
		}
	});
}
/**
 * 保存编辑之后的grid数据
 */
function mySave(){
	var data = grid.getSelectedRows() ;
	if(data.length==0){
		$.ligerDialog.success("请选择要保存的数据", "提示内容", function(){});
		return ;
	}
	var dd = JSON.stringify(data);
	debugger;
	$.ajax({
		url: basePath+"rest/craGatheringModeManageAction/saveOrUpdateList",
		dataType: 'json',
		data:"data="+dd ,
		type: "post",
//		contentType:"application/json",
		success:function(data){
			if(data.success){
				$.ligerDialog.success("保存成功", "提示内容", function(){});
				grid.reload() ;
			}else{
				$.ligerDialog.success("保存失败", "提示内容", function(){});
			}
			
		}
	
	});
}
/**
 * 点击左侧树之后，根据数据库sys_dictionary表，初始化参数
 */
function initParam(){
	loadGrid(true);
}

function del(){
	var arr = grid.getSelecteds() ;
	var ids = [];
	
	if(arr.length==0){
		$.ligerDialog.warn("请选择你要删除的数据", "提示内容", function(){});
	}else{
		for(var i = 0;i < arr.length;i++){
			ids.push(arr[i].id);
		}
		$.ligerDialog.confirm("数据删除后不可恢复，是否确认删除", "确认信息", function(confirm){
			if(confirm){
				alert(ids);
				$.ajax({
					url: basePath+"rest/craGatheringModeManageAction/del",
					dataType: 'json',
					data: "ids="+ids,
					type: "post",
					success:function(data){
						if(data.success){
							$.ligerDialog.success("删除成功", "提示内容", function(){});
							grid.reload() ;
						}
					}
				
				});
			}
		}) ;
	}
}
function edit(){
	var arr = grid.getSelecteds() ;
	if(arr.length==1){
		var nowId = arr[0].id;
		ligerDialogOpen = $.ligerDialog.open({
			url:basePath+"rest/craGatheringModeManageAction/?id="+nowId,
			type:'question' ,
			width :1000,
			height:500,
			buttons :[
			         { 
			        	 text: '保存', onclick: function(i,d){save(i,d);}
			         },
			         {
			        	 text:"取消",onclick:function(){
			        		 ligerDialogOpen.close();
			        	 }
			         }
			]
		}) ;
	}else{
		$.ligerDialog.warn("只能选择一条数据后编辑", "提示内容", function(){});
	}
	
}
function add(){
	alert("添加"+id);
	ligerDialogOpen = $.ligerDialog.open({
		url:basePath+"rest/craGatheringModeManageAction/?relation_id="+id,
		type:'question' ,
		width :1000,
		height:500,
		buttons :[
		         { 
		        	 text: '保存', onclick: function(i,d){save(i,d);}
		         },
		         {
		        	 text:"取消",onclick:function(){
		        		 ligerDialogOpen.close();
		        	 }
		         }
		]
	}) ;
}
//保存数据
function save(i,d){
	var bean = d.frame.form.getData() ;
	var data = bean;
	data.relationId = id;
	$.ajax({
		url: basePath+"rest/craGatheringModeManageAction/saveBean",
		dataType: 'json',
		data: data,
		type: "post",
		success:function(data){
			if(data.success){
				$.ligerDialog.success("保存成功", "提示内容", function(){});
				ligerDialogOpen.close();
				grid.reload() ;
			}else{
				$.ligerDialog.success(data.msg, "提示内容", function(){});
			}
		}
	
	});
	
}