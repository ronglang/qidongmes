//定义全局变量
var ligerDialogOpen;
var grid;
$(function() {
	loadGrid();

});

function loadGrid() {
	grid = $("#myGrid").ligerGrid({
		url : basePath + 'rest/craCraftProductManageAction/toListPageData',
		height : '100%',
		checkbox : true,
		columns : [ {
			display : '产品工艺编码',
			name : 'proCraftCode',
			align : 'left',
			minWidth : 10
		}, {
			display : '产品id',
			name : 'proId',
			minWidth : 30
		}, {
			display : '工艺路线编码',
			name : 'routeCode',
			minWidth : 30
		}, {
			display : '产品规格型号',
			name : 'proGgxh',
			minWidth : 30
		}, {
			display : '工艺名称',
			name : 'proCraftName',
			minWidth : 30
		} ],
		onDblClickRow : function(rowdata, rowindex, rowDomElement) {

		},
		pageSize : 10,
		toolbar : {
			items : [ {
				text : '添加',
				click : add,
				icon : 'add'
			}, {
				text : "删除",
				click : del,
				icon : "delete"
			} ]
		}
	});
}
function del() {
	var arr = grid.getSelecteds();
	var ids = [];

	if (arr.length == 0) {
		$.ligerDialog.warn("请选择你要删除的数据", "提示内容", function() {
		});
	} else {
		for (var i = 0; i < arr.length; i++) {
			ids.push(arr[i].id);
		}
		$.ligerDialog.confirm("数据删除后不可恢复，是否确认删除", "确认信息", function(confirm) {
			if (confirm) {
				alert(ids);
				$.ajax({
					url : basePath + "rest/craCraftProductManageAction/del",
					dataType : 'json',
					data : "ids=" + ids,
					type : "post",
					success : function(data) {
						if (data.success) {
							$.ligerDialog.success("删除成功", "提示内容", function() {
							});
							grid.reload();
						}
					}

				});
			}
		});
	}
}
function edit() {
	var arr = grid.getSelecteds();
	if (arr.length == 1) {
		var nowId = arr[0].id;
		ligerDialogOpen = $.ligerDialog.open({
			url : basePath
					+ "rest/craCraftProductManageAction/toAddEditPage?id="
					+ nowId,
			type : 'question',
			width : 1000,
			height : 500,
			buttons : [ {
				text : '保存',
				onclick : function(i, d) {
					save(i, d);
				}
			}, {
				text : "取消",
				onclick : function() {
					ligerDialogOpen.close();
				}
			} ]
		});
	} else {
		$.ligerDialog.warn("只能选择一条数据后编辑", "提示内容", function() {
		});
	}

}
function add() {
	ligerDialogOpen = $.ligerDialog.open({
		url : basePath + "rest/craCraftProductManageAction/toAddEditPage",
		type : 'question',
		width : 1000,
		height : 500,
		buttons : [ {
			text : '保存',
			onclick : function(i, d) {
				save(i, d);
			}
		}, {
			text : "取消",
			onclick : function() {
				ligerDialogOpen.close();
			}
		} ]
	});
}
// 保存数据
function save(i, d) {

	var bean = d.frame.form.getData();
	var data = bean;
	try {
		var manger = d.frame.$("#formGgxh").liger();
		var bean2 = manger.getData();
		// var bean2 = d.frame.formGgxh.getData();
		bean2 = JSON.stringify(bean2);
		data.materielGgxhs = bean2;
	} catch (e) {
		alert(e.name);
	}

	debugger;
	// return;
	$.ajax({
		url : basePath + "rest/craCraftProductManageAction/saveOrUpdate",
		dataType : 'json',
		data : data,
		type : "post",
		success : function(data) {
			if (data.success) {
				$.ligerDialog.success("保存成功", "提示内容", function() {
				});
				ligerDialogOpen.close();
				grid.reload();
			}
		}

	});

}