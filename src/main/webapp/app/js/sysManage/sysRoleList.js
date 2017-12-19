var tbdArray = new Array();
var grid;
$(function() {
	grid = $("#sysRoleManageList")
			.ligerGrid(
					{
						url : basePath + "rest/" + routeName
								+ "Action/dataGridPage",
						checkbox : true,
						columns : [
								{
									display : '序号',
									name : 'id',
									hide:true,
									width : 120
								},
								{
									display : '角色名称',
									name : 'name',
									width : 120
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
										html += '<a href="#" onclick="javascript:show('
												+ row.id
												+ ');return false;">查看</a>';
										return html;
									}
								} ],
						pageSize : 10,
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
		/*top.$.ligerDialog.open({
			url : basePath + "rest/sysRoleManageAction/toAddEditPage",
			height : 400,
			width : 750,
			modal : true,
			title:'添加角色'});*/
		var url = basePath + "rest/sysRoleManageAction/toAddEditPage";
		window.open(url);
	});
});

function edit(id) {
	/*top.$.ligerDialog.open({
		url : basePath + "rest/sysRoleManageAction/toAddEditPage?id=" + id,
		height : 400,	  
		width : 750,
		modal : true,
		title:'编辑角色'
	});*/
	var url = basePath + "rest/sysRoleManageAction/toAddEditPage?id=" + id;
	window.open(url);
}

function authorityManagement() {
	if(f_getChecked()==""){
		top.$.ligerDialog.warn("请选择要操作的数据");
		return;
	}
	
	top.$.ligerDialog.open({
		url : basePath	
				+ "rest/sysRoleMenuManageAction/tosysAuthorityManagementEditPage?idlist="+f_getChecked(),
		height : 550,
		width : 450,
		modal : true,
		title:'权限设置'
	});
	//alert("77777777777");
}

function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa, true));
}

function show(id) {
	top.$.ligerDialog.open({
		url : basePath + "rest/sysRoleManageAction/toDetailPage?id="+id,
		height : 500,
		width : 750,
		modal : true});
}

/**
 * 删除
 */
function pub_del_role(routeName) {
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