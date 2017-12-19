var tbdArray = new Array();
var grid;

$(function() {
	creatTreeDiv("areaCode");
	initTreeCustom("areaCode","sysArea","normal","","",villageCodeTreeSetting);
	
	creatTreeDiv("userType");
	initTree("userType","sysCommdic","common","clas","数据用户类型分类");

	if(areaCode){
		var treeObj = $.fn.zTree.getZTreeObj("areaCodeContentTree");
		if(treeObj){
			var node = treeObj.getNodeByParam("id",areaCode);
			if(node){
				treeObj.selectNode(node,false);
				//设置选中节点后右边编辑内容的载入
				myTreeClick(event,"areaCodeContentTree",node,true);
			}
		}
	}
	
	grid = $("#sysUserManageList")
			.ligerGrid(
					{
						url : basePath + "rest/" + routeName
								+ "Action/dataGridPage",
						checkbox : true,enabledSort:false,
						columns : [
								{
									display : '序号',
									name : 'id',
									hide : true,
									width : 1
								},
								{
									display : '用户名',
									name : 'name',
								//	hide : true,
									width : 190
								},
								{
									display : '账户类型',
									name : 'userType',
								//	hide : true,
									width : 150
								},
								//{ display : '账户区域权限', name : 'areaCode', width : 120 },
								{
									display : '组织机构',
									name : 'dept',
									hide : true,
									width : 120
								},
								{
									display : '账号',
									name : 'account',
									width : 120
								},
								{
									display : '状态',
									name : 'status',
									width : 120,
									render : function(rowdata, index, value) {
										if ('C10020001' == value) {
											return '启用';
										} else {
											return '禁用';
										}
									}
								},
								{
									display : '备注',
									name : 'remark',
									width : 120
								},
								{
									display : '操作',
									isAllowHide : false,
									width : 120,
									render : function(row) {
										var html = '<a href="#" onclick="javascript:edit('
												+ row.id
												+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
									
										var str = "启用";
										if ('C10020001' == row.status) {
											str = '禁用';
										}
										var statustr = "'" + row.status + "'";
										html += '<a href="#" id='
												+ row.id
												+ ' onclick="javascript:updateUserStatus('
												+ statustr + ',' + row.id
												+ ');return false;">' + str
												+ '&nbsp;&nbsp;&nbsp;</a>';

										html += '<a href="#" onclick="javascript:show('
										    	+ statustr + ',' + row.id
												+ ');return false;">查看</a>';
										return html;
									}
								} ],
						pageSize : 30,
						isChecked : f_isChecked,
						onCheckRow : f_onCheckRow,
						onCheckAllRow : f_onCheckAllRow,
						width : '100%',
						height : '97%',
						onSuccess : function(json, grid) {
							$("#pageloading").hide();
						},
						onError : function(json, grid) {
							$("#pageloading").hide();
						}
					});
	$("#add").click(function() {
		$.ligerDialog.open({
			url : basePath + "rest/sysUserManageAction/toAddEditPage",
			height : 600,
			width : 1000,
			modal : true,
			title : '添加用户'

		});
	});
});

/**
 * 密码重置
 */
function resetPassword(routeName) {
	var ids = f_getChecked();
	if (ids.length == 0) {
		top.$.ligerDialog.warn("请选择要重置密码的用户！");
		return;
	}
	var yes = window.confirm("确定要重置选定用户的密码?");
	if (yes) {
		var list_ck = $("input:[name='list_ck']");

		var url = basePath + "rest/" + routeName + "Action/resetPassword?ids="
				+ ids;
		// 提交数据
		$.ajax({
			url : url,
			type : "post",
			dataType : "json",
			async : true,
			success : function(json) {
				subSuccess(json);
			},
			error : function(json) {
				ajaxError(json);
			}
		});
	}
}
/**
 * 重写提交数据成功后的回调函数
 */
function subSuccess(json) {
	if (json.success) {
		$.ligerDialog.success(json.msg);
	} else {
		if (json.msg)
			$.ligerDialog.error(json.data);
	}
}
function edit(id) {
	var w = $.ligerDialog.open({
		url : basePath + "rest/sysUserManageAction/toAddEditPage?id=" + id,
		height : 600,
		width : 1000,
		modal : true,
		title : '编辑用户'
	});
	if(w && w.returnValue){
		grid.reload();
	}
}

function show(status, id) {
	$.ligerDialog.open({
		url : basePath + "rest/sysUserManageAction/toDetailPage?id=" + id+ "&status=" + status,
		height : 600,
		width : 1000,
		modal : true
	});
}

/**
 * 删除
 */
function pub_del_user(routeName) {
	var ids = f_getChecked();
	if (ids.length == 0) {
		//alert("请选择要删除的数据");
		top.$.ligerDialog.warn("请选择要删除的数据！");
		return;
	}
	var deleteCount=ids.split(",").length;
	var yes = window.confirm("确定要删除选定的 "+deleteCount+" 条数据吗?");
	if (yes) {
		var list_ck = $("input:[name='list_ck']");

		var url = basePath + "rest/" + routeName	+ "Action/delete2?ids=" + ids;
		// 提交
		$.ajax({
			url : url,
			type : "post",
			dataType : "json",
			async : true,
			success : function(json) {
				delSuccess(json);
				checkedData = [];//删除成功后清空选中数组
			},
			error : function(json) {
				ajaxError(json);
			}
		});
	}
}


/**
 * 更新帐户状态
 * 
 * @param {}
 *            status
 * @param {}
 *            id
 */
function updateUserStatus(status, id) {
	$.ajax({
		url : basePath + "rest/sysUserManageAction/updateUserStatus?id=" + id
				+ "&status=" + status,
		type : "post",
		dataType : "json",
		async : true,
		success : function(json) {
			if (json.msg)
				$.ligerDialog.success(json.msg);
			
			grid = $("#sysUserManageList").ligerGrid({
						url : basePath + "rest/" + routeName+ "Action/dataGridPage",
						checkbox : true,enabledSort:false,
						columns : [
											{
												display : '序号',
												name : 'id',
												hide : true,
												width : 1
											},
											{
												display : '用户名',
												name : 'name',
												hide : true,
												width : 120
											},
											{
												display : '账户类型',
												name : 'userType',
											//	hide : true,
												width : 120
											},
											//{display : '账户区域权限',name : 'areaCode',width : 120	},
											{
												display : '组织机构',
												name : 'dept',
												width : 120
											},
											{
												display : '账号',
												name : 'account',
												width : 120
											},
											{
												display : '状态',
												name : 'status',
												width : 120,
												render : function(rowdata, index, value) {
													if ('C10020001' == value) {
														return '启用';
													} else {
														return '禁用';
													}
												}
											},
											{
												display : '备注',
												name : 'remark',
												width : 120
											},
											{
												display : '操作',
												isAllowHide : false,
												width : 120,
												render : function(row) {
													var html = '<a href="#" onclick="javascript:edit('
															+ row.id
															+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
												
													var str = "启用";
													if ('C10020001' == row.status) {
														str = '禁用';
													}
													var statustr = "'" + row.status + "'";
													html += '<a href="#" id='
															+ row.id
															+ ' onclick="javascript:updateUserStatus('
															+ statustr + ',' + row.id
															+ ');return false;">' + str
															+ '&nbsp;&nbsp;&nbsp;</a>';

													html += '<a href="#" onclick="javascript:show('
															+ row.id
															+ ');return false;">查看</a>';
													return html;
												}
											} ],
						pageSize : grid.options.pageSize,
						isChecked : f_isChecked,
						onCheckRow : f_onCheckRow,
						onCheckAllRow : f_onCheckAllRow,
						onToNext : function() {
							if ($("#account").val())
								grid.setParm("account", $("#account").val());
						},
						onToFirst : function() {
							if ($("#account").val())
								grid.setParm("account", $("#account").val());
						},
						onToPrev : function() {
							if ($("#account").val())
								grid.setParm("account", $("#account").val());
						},
						onToLast : function() {
							if ($("#account").val())
								grid.setParm("account", $("#account").val());
						},
						width : '100%',
						height : '97%',
						onSuccess : function(json, grid) {
							$("#pageloading").hide();
						},
						onError : function(json, grid) {
							$("#pageloading").hide();
						}
					});
		},
		error : function(json) {
			if (json.msg)
				$.ligerDialog.error(json.msg);
		}
	});
}


var villageCodeTreeSetting={				
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeTreeClick,
			onClick:myTreeClick
		}
};

function myTreeClick (e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId),
	nodes = zTree.getSelectedNodes(),
	v = "";
	h = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		h += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (h.length > 0 ) h = h.substring(0, h.length-1);
	if(treeId.indexOf("ContentTree")>-1){
		var inputId = treeId.split("ContentTree")[0];
		var cityObj = $("#"+inputId);
		cityObj.attr("value", h);
		var cityObjSHOW = $("#"+inputId+"Show");
		cityObjSHOW.attr("value", v);
		setName(v);
	}
}	