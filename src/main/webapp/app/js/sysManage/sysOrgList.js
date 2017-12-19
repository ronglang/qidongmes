var tbdArray = new Array();
var grid;
$(function ()
    {
		grid=$("#sysOrgManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns : [{
							display : '序号',
							name : 'id',
							width : 1,
							hide:true
						}, {
							display : '机构名称',
							name : 'orgName',
							width : 120
						}, {
							display : '值班人姓名',
							name : 'dutyName',
							width : 120
						}, {
							display : '值班电话',
							name : 'telephone',
							width : 120
						}, {
							display : '地址',
							name : 'address',
							width : 120
						}, {
							display : '机构负责人姓名',
							name : 'leaderName',
							width : 120
						}, {
							display : '机构负责人手机号',
							name : 'leaderMobile',
							width : 120
						}, {
							display : '操作',
							isAllowHide : false,
							width : 120,
							render : function(row) {
								var html = '<a href="#" onclick="javascript:edit('
										+ row.id
										+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
								html += '<a href="#" onclick="javascript:show('
										+ row.id + ');return false;">查看</a>';
								return html;
							}
						}], 
	        pageSize:30,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',height:'97%',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		$("#add").click(function(){
			$.ligerDialog.open({ 
				url: basePath+"rest/sysOrgManageAction/toAddEditPage", 
				height: 600,
				width: 1000,
				title:"机构添加",
				modal:true});
		});
    }
);

function edit(id){
	$.ligerDialog.open({ 
		url: basePath+"rest/sysOrgManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1000,
		title:"机构编辑",
		modal:true});
}

function show(id){
	$.ligerDialog.open({ 
		url: basePath+"rest/sysOrgManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1000,
		title:"机构详情",
		modal:true});
} 

