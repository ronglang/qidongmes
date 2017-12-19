//定义全局变量
var ligerDialogOpen;
var grid;
$(function(){
	loadGrid(false);
});

function loadGrid(boo){
	var obj = new Object();
//	obj.relation_id = id;
	if(boo){
		obj.init = "add";
	}
	grid = $("#myGrid1").ligerGrid({
		title:"领料单主表",
		url : basePath+'rest/mauMaterialRecordManageAction/getMainPageData',
		height : '80%',
		checkbox :true,
		parms :obj,
		enabledEdit: true,
		columns : [{
			display : '领料员',
			name : 'materManageBy',
			align : 'left',
			width : 100,
			minWidth : 10
		}, {
			display : '领料单号',
			name : 'mmrCode',
			minWidth : 30
		}, {
			display : '工作单号',
			name : 'wsCode',
			minWidth : 30
//			editor: { type: 'text' }
		}, {
			display : '单据状态',
			name : 'mmrState',
			minWidth : 30
//			editor: { type: 'text' }
		}, {
			display : '领料开始时间',
			name : 'timeStart',
			minWidth : 30
//			editor: { type: 'text' }
		}, {
			display : '产品规格型号',
			name : 'ggxh',
			minWidth : 30
		}, {
			display : '领料结束时间 ',
			name : 'timeEnd',
			minWidth : 30
//			editor: { type: 'text' }
		}, {
			display : '是否超出领取',
			name : 'isOver',
			minWidth : 30
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		pageSize : 10,
//		rownumbers : true,
		toolbar : {
			items : [ 
//			          {
//				text :'保存',
//	            click :mySave,
//	            icon :'add'
//	          },
//	          {text:"编辑",click:edit,icon:"edit"},
//	          {text:"删除",click:del,icon:"delete"},
//	          {text:"初始化参数",click:initParam,icon:"delete"},
	          
	          {text:"开始领料",click:start,icon:"right"},
	          {text:"结束领料",click:end,icon:"delete"}
			]
		},
		onDblClickRow :function (data,rowid,rowdata){
			console.log(data);
			console.log(rowid);
			console.log(rowdata);
			console.log(data.id);
			var para = "mainId="+data.id; 
			
			if(data.timeEnd){
				$.ligerDialog.success("该领料单已结束领料，不能编辑明细", "提示内容", function(){});
				return ;
			}
			debugger;
			window.open(basePath+"rest/mauMaterialDetailManageAction/toListGridPage?"+para);
		}
		
	});
}
/**
 * 结束领料，修改结束领料时间
 */
function end(){
	var data = grid.getSelectedRows() ;
	if(data.length==0){
		$.ligerDialog.success("请选择要领料的单据", "提示内容", function(){});
		return ;
	}
	for(var i = 0; i < data.length; i++){
		var b = data[i];
		if(b.timeEnd){
			$.ligerDialog.success("该单已经结束领料，请不要重复操作", "提示内容", function(){});
			return;
		}
	}
	var dd = [];
	for(var i = 0; i < data.length; i++){
		dd.push(data[i].id);
	}
	
	$.ajax({
		url: basePath+"rest/mauMaterialRecordManageAction/end",
		dataType: 'json',
		data:"data="+dd ,
		type: "post",
//		contentType:"application/json",
		success:function(data){
			if(data.success){
				$.ligerDialog.success("结束领料成功", "提示内容", function(){});
				grid.reload() ;
			}else{
				$.ligerDialog.success("结束领料失败", "提示内容", function(){});
			}
		}
	});
}
/**
 * 开始领料，修改开始领料时间
 */
function start(){
	var data = grid.getSelectedRows() ;
	var pk_id ;
	if(data.length==0){
		$.ligerDialog.success("请选择要领料的单据", "提示内容", function(){});
		return ;
	}else if(data.length>1){//只能选择一条记录
		$.ligerDialog.success("只能选择一条记录", "提示内容", function(){});
		return ;
	}else{
		pk_id = data[0].id;
	}
	
	
	for(var i = 0; i < data.length; i++){
		var b = data[i];
		if(b.timeStart){
			b.timeStart = null;
		}
		if(b.timeEnd){
			$.ligerDialog.success("改单已经结束领料，请不要重复操作", "提示内容", function(){});
			return;
		}
	}
	
	var dd = JSON.stringify(data);
	
	
	debugger;
	$.ajax({
		url: basePath+"rest/mauMaterialRecordManageAction/start",
		dataType: 'json',
		data:"id="+pk_id ,
		type: "post",
//		contentType:"application/json",
		success:function(data){
			if(data.success){
//				$.ligerDialog.success("领料成功", "提示内容", function(){});
				
				grid.reload() ;
				//TODO ,跳转到下一个页面
				window.open(basePath+"rest/mauMaterialDetailManageAction/toListGridPage?mainId="+pk_id);
				
			}else{
				$.ligerDialog.success("领料失败", "提示内容", function(){});
			}
			
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
		url: basePath+"rest/mauMaterialRecordManageAction/",
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
					url: basePath+"rest/mauMaterialRecordManageAction/",
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
			url:basePath+"rest/mauMaterialRecordManageAction/?id="+nowId,
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
		url:basePath+"rest/mauMaterialRecordManageAction/?relation_id="+id,
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
		url: basePath+"rest/mauMaterialRecordManageAction/",
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