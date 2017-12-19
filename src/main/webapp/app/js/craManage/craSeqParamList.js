//定义全局变量
var ligerDialogOpen;
var grid;
var id;
$(function(){
	loadTree();
	
	grid = $("#myGrid1").ligerGrid({
		delayLoad:true,
		url : basePath+'rest/craSeqParamManageAction/craSeqParamList',
		width:'100%',
		height : '100%',
		checkbox :true,
		//parms :obj,
		enabledEdit: true,
		columns : [{
			display : '工序编码',
			name : 'seqCode',
			align : 'left',
			width : 100,
			minWidth : 10
		}, {
			display : '参数编码',
			name : 'paramCode',
			minWidth : 30
		}, {
			display : '参数最小值',
			name : 'paramMinValue',
			minWidth : 30,
			editor: { type: 'text' }
		}, {
			display : '参数最大值',
			name : 'paramMaxValue',
			minWidth : 30,
			editor: { type: 'text' }
		}, {
			display : '单位',
			name : 'uint',
			minWidth : 30,
			editor: { type: 'text' }
		}, {
			display : '产品规格型号',
			name : 'ggxh',
			minWidth : 30
		}, {
			display : '参数设定值 ',
			name : 'paramValue',
			minWidth : 30,
			editor: { type: 'text' }
		}, {
			display : '产品工艺编码',
			name : 'proCraftCode',
			minWidth : 30
		}, {
			display : '参数名称 ',
			name : 'paramName',
			minWidth : 30
		}, {
			display : '工艺编码（后台生成） ',
			name : 'craftCode',
			minWidth : 30
		}, {
			display : '工艺名称（客户手动填写） ',
			name : 'craftName',
			minWidth : 30
		}, {
			display : '主表编码 ',
			name : 'pcscRelaCode',
			minWidth : 30
		}],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {
			
		},
		//pageSize : 10,
		usePager:false,
//		rownumbers : true,
		toolbar : {
			items : [ 
			  //{text :'保存', click :mySave,icon :'add'},
//	          {text:"编辑",click:edit,icon:"edit"},
//	          {text:"删除",click:del,icon:"delete"},
	          {text:"初始化参数",click:loadGrid,icon:"delete"}
			]
		}
	});
	
	
	//loadGrid2(false);
});
//初始化
function loadGrid(){
	if(!id ){
		$.ligerDialog.warn("请选择产品工艺。", "提示内容");
		return;
	}
	var url = basePath+'rest/craSeqParamManageAction/craSeqParamList';
	grid.set({url:url});
	grid.loadServerData(decodeURIComponent("&init=add&relation_id="+id,true));
	grid.setParm('init','add');
	grid.setParm('relation_id',id);
	grid.changePage("first");
}
//只是查询
function queryReadyData(id){
	if(!id ){
		return;
	}
	var url =  basePath+'rest/craSeqParamManageAction/dataGridPage2?id='+id;
	grid.set({url:url});
	grid.reload();
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
		url: basePath+"rest/craSeqParamManageAction/saveList",
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
				$.ajax({
					url: basePath+"rest/craSeqParamManageAction/del",
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
			url:basePath+"rest/craSeqParamManageAction/toAddEditPage?id="+nowId,
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
	ligerDialogOpen = $.ligerDialog.open({
		url:basePath+"rest/craSeqParamManageAction/toAddEditPage?relation_id="+id,
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
		url: basePath+"rest/craSeqParamManageAction/saveBean",
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

var tree;

function loadTree(){
	$("#myTree").ligerTree({
		url: basePath+"rest/processModuleManageAction/toTree",  
		nodeWidth : 100,
//		onBeforeExpand: onBeforeExpand,
		isExpand :true,
		checkbox :false,
		parentIcon :'',
		onClick :function(data,target){
			//debugger;
//			alert(data.data.id);
//			alert("层级"+data.data.level);
			id = null;
			
			if(data.data.level==3){//层级为3  工序级
				//$("#f1").attr("src",basePath+"rest/craSeqParamManageAction/toListPage?id="+data.data.id);
			}else if(data.data.level == 2){  //产品工艺级
				id = data.data.id;
				queryReadyData(id);
				//loadGrid2(true);
				$("#f1").attr("src",basePath+"rest/craProSeqRelationManageAction/toListPageCore?proCraftCode="+data.data.id);
			}
		}
	});	
}
