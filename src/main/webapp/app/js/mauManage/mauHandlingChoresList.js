var tbdArray = new Array();
var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
var form;
var names = "";
var url;
var mater;
var jsonObj={};

function f_save()
{
    alert('保存');
}
function f_close()
{
    alert('关闭');
}
parent._manuChoresListRefresh = function(){
	grid.reload();
};
function itemclick(item) {
	if (item.text == "增加") {
		winAdd = $.ligerDialog.open({
					// dialog中添加form表单
					url : basePath 
							+ "rest/mauHandlingChoresManageAction/toAddEditPage",
					height : 240,
					width : 850,
					modal : true,
					title : "添加",
					isResize : true,			
				});
		
	}
	if (item.text == "修改") {
		debugger;
		var selected = grid.getSelecteds();
		if(selected.length==0){
			$.ligerDialog.warn('您还没有选择，请选择一条修改');
			return;
		}if(selected.length>1){
			$.ligerDialog.warn('您选了多条，请选择一条修改');
		    return;
		}
		else{
			debugger;
			var id = selected[0].id;
			winEdit = $.ligerDialog.open({
				url : basePath
						+ "rest/mauHandlingChoresManageAction/toAddEditPage?id="
						+ id,
				height : 240,
				width : 850,
				modal : true,
				title : "编辑"
			});
		}
	}
	if (item.text == "删除") {
		var selected = grid.getSelecteds();
		if (selected.length == 0) { 
			$.ligerDialog.warn('请选择要删除的数据');
			return; 
		}
		var strIds = [];
		for (var i = 0; i < selected.length; i++) {
			strIds.push(selected[i].id);
		}
		// debugger;
		var id = strIds.join(",");
		// var id = selected.id;
		$.ligerDialog.confirm("您确认要删掉这<font color=red>" + selected.length+ "</font>条数据吗？",
						function(result) {
							if (result == true) {
								$
										.ajax({
											url : basePath+ "rest/mauHandlingChoresManageAction/toDelete?id="+ id,
											type : "post",
											dataType : "json",
											// data:data,
											success : function(data) {
											  parent._manuChoresListRefresh();
											},
											error : function(data) {
												alert(data);
											}
										});
							} else {
								return;
							}
						});
	}
}
$(function() {
	url = basePath + "rest/" + routeName + "Action/toDataGridPage";
	grids(url);
	getDates();
	
});

function grids(url){
	
	grid = $("#mauHandlingChoresManageList").ligerGrid({
		title : "杂物处理",
		url : url,
		checkbox : true,
		columns : [ {
			display : '序号',
			name : 'id'
		}, {
			display : '杂物名称',
			name : 'choresname'
		}, {
			display : '操作人', 
			name : 'agentby'
		}, {
			display : '物料编号',
			name : 'matercode'
		}, {
			display : '备注',
			name : 'remark'
		},
		],
		pageSize : 10,
		width : '100%',
		height : '97%',
		toolbar : {
			items : [ {
				text : '增加',
				click : itemclick,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : itemclick,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : itemclick,
				icon : 'delete'
			} ]
		},
		
	});
}



$("#pageloading").hide();
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	// js解码函数
	grid.loadServerData(decodeURIComponent(pa, true));
}
function show(id) {
	top.$.ligerDialog.open({
		url : basePath + "rest/mauHandlingChoresManageAction/toDetailPage?id="
				+ id,
		height : 600,
		width : 1080,
		modal : true,
		title : "详细信息"  
	});
}
function searchForm(){
	var agentby=$("[name=agentby]").val();
	var choresname=$("[name=choresname]").val();
	var matercode=$("[name=matercode]").val(); 
	jsonObj.agentBy=agentby;
	jsonObj.choresName=choresname;
	jsonObj.materCode=matercode;
	//var jsonStr= JSON.stringify(jsonObj); 
	var jsonQuery="agentBy="+agentby+"&choresName="+choresname+"&materCode="+matercode;
	//alert(jsonStr);
	url = basePath + "rest/" + routeName + "Action/toDataGridPage"+ "?" + jsonQuery;
	grids(url);
}

function resetForm(){
	grid = $("#mauHandlingChoresManageList").ligerGrid({
		title : "杂物处理",
		url : basePath + "rest/" + routeName + "Action/toDataGridPage",
		checkbox : true,
		columns : [ {
			display : '序号',
			name : 'id'
		}, {
			display : '杂务名称',
			name : 'choresname'
		}, {
			display : '操作人',
			name : 'agentby'
		}, {
			display : '物料编号',
			name : 'matercode'
		}, {
			display : '备注',
			name : 'remark'
		},

		],
		width : '100%',
		height : '97%',
		toolbar : {
			items : [ {
				text : '增加',
				click : itemclick,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : itemclick,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : itemclick,
				icon : 'delete'
			} ]
		}
	});
}

function form(){
	
	form=$("#queryForm").ligerForm({
		 inputWidth: 170, labelWidth: 70, space: 20,
		                  fields: [
		                  { name: "ProductID", type: "hidden" },
		                  { display: "操作人", name: "agentby", newline: false, type: "text"}, 
		                  { display: "杂物名称", name: "choresname", newline: false, type: "select", comboboxName: "choresname",editor: {valueField:'text',data:names}},
		                  { display: "物料编号 ", name: "matercode", newline: false,type: "select", comboboxName: "matercode", editor: {valueField:'text',data:mater}, width: 200,newline: false}
		                  ]
   });
	
}

//设置下拉框的值
function getDates(){
	$.ajax({
		url:basePath+'rest/mauHandlingChoresManageAction/getChoresname',
		type:'post',
		dataType:'json',
		success:function(choresname){
			debugger;
			names = choresname;
			//form(names);
			//debugger;
			$.ajax({
				url:basePath+'rest/mauHandlingChoresManageAction/getMaterCode',
				type:'post',
				dataType:'json',
				success:function(materCode){
					debugger;
					mater = materCode;
					form(names,mater);
					//debugger;
					
					
				},
				error:function(){
					alert("数据出错了");
				}
			});
			
		},
		error:function(){
			alert("数据出错了");
		}
	});
}


