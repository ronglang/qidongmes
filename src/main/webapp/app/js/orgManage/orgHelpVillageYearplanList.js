var tbdArray = new Array();
var grid;
$(function() {
	// pub_initList(routeName);
	grid = $("#orgHelpVillageYearplanManageList").ligerGrid({
			url : basePath + "rest/" + routeName+ "Action/dataGridPage",
			checkbox : true,
			columns : [
					{
						display : '标题',
						name : 'title',
						width : 300
					},
					{
						display : '操作',
						isAllowHide : false,
						width : 120,
						render : function(row) {
							var html = '<a href="#" onclick="javascript:edit('
									+ row.id
									+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
//							html += '<a href="#" onclick="javascript:show('
//									+ row.id
//									+ ');return false;">查看</a>';
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
	$("#add").click(
			function() {
				top.$.ligerDialog.open({
					url : basePath
							+ "rest/orgHelpVillageYearplanManageAction/toAddEditPage",
							height: window.screen.availHeight-100,
							width: window.screen.availWidth-100,
					modal : true,
					title : "添加"
				/*
				 * buttons: [ { text: '确定', onclick: function
				 * (item, dialog) { alert(item.text); } },
				 * 
				 * {text: '取消', onclick: function (item, dialog) {
				 * dialog.close(); } } ]
				 */});
			});
});

function edit(id) {
	top.$.ligerDialog.open({
		url : basePath
				+ "rest/orgHelpVillageYearplanManageAction/toAddEditPage?id="
				+ id,
				height: window.screen.availHeight-100,
				width: window.screen.availWidth-100,
		modal : true,
		title : "上报进度"
	/*
	 * buttons: [ { text: '确定', onclick: function (item, dialog) {
	 * alert(item.text); } },
	 * 
	 * {text: '取消', onclick: function (item, dialog) { dialog.close(); } } ]
	 */});
}

//function reloadData() {
//	var pa = $("#" + routeName + "ListForm").serialize();
//	grid.loadServerData(decodeURIComponent(pa, true));
//}

function show(id) {
	top.$.ligerDialog.open({
		url : basePath
				+ "rest/orgHelpVillageYearplanManageAction/toDetailPage?id="
				+ id,
				height: window.screen.availHeight-100,
				width: window.screen.availWidth-100,
		modal : true,
		title : "详细信息"
	/*buttons: [
	{text: '取消', onclick: function (item, dialog) { 
		dialog.close(); 
	} } 
	]*/});
}
