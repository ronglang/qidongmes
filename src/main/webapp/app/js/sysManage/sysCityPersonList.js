var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysCityPersonManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns : [{
							display : '序号',
							name : 'id',
							width : 120,
							hide:true
						}, {
							display : '姓名',
							name : 'name',
							width : 120
						}, {
							display : '民族',
							name : 'nation',
							width : 120
						}, {
							display : '身份证号',
							name : 'idNo',
							width : 120
						}, {
							display : '手机',
							name : 'mobileTel',
							width : 120
						}, {
							display : '所在区域代码',
							name : 'areaCode',
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
	        pageSize:10,
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
			top.$.ligerDialog.open({ 
				url: basePath+"rest/sysCityPersonManageAction/toAddEditPage", 
				height: 600,
				width: 1000,
				modal:true,
				title:"人口添加"
				/*buttons: [ { text: '确定', onclick: function (item, dialog) { 
						alert(item.text); 
					} 
				}, 
			                                                                                   
				{text: '取消', onclick: function (item, dialog) { 
					dialog.close(); 
				} } 
				]*/ });
		});
    }
);

function edit(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysCityPersonManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1000,
		modal:true, 
		title:"人口编辑"
		/*buttons: [ { text: '确定', onclick: function (item, dialog) { 
				alert(item.text); 
			} 
		}, 
	                                                                                   
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		] */});
}

function reloadData(){
	var pa = $("#"+routeName+"ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa,true));
}

function show(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysCityPersonManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1000,
		modal:true, 
		title:"人口详细信息"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

