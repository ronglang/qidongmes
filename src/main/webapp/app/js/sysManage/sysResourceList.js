var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysResourceManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns : [/*{
							display : '序号',
							name : 'id',
							width : 120
						},*/ {
							display : '',
							name : 'click',
							width : 120
						}, {
							display : '',
							name : 'desktopUrl',
							width : 120
						}, {
							display : '',
							name : 'display',
							width : 120
						}, {
							display : '',
							name : 'iconCss',
							width : 120
						}, {
							display : '',
							name : 'image',
							width : 120
						}, {
							display : '',
							name : 'image128',
							width : 120
						}, {
							display : '',
							name : 'image256',
							width : 120
						}, {
							display : '',
							name : 'image32',
							width : 120
						}, {
							display : '',
							name : 'image48',
							width : 120
						}, {
							display : '',
							name : 'image64',
							width : 120
						}, {
							display : '',
							name : 'isref',
							width : 120
						}, {
							display : '',
							name : 'istext',
							width : 120
						}, {
							display : '',
							name : 'name',
							width : 120
						}, {
							display : '',
							name : 'pid',
							width : 120
						}, {
							display : '',
							name : 'rCode',
							width : 120
						}, {
							display : '',
							name : 'refid',
							width : 120
						}, {
							display : '',
							name : 'remark',
							width : 120
						}, {
							display : '',
							name : 'sort',
							width : 120
						}, {
							display : '',
							name : 'type',
							width : 120
						}, {
							display : '',
							name : 'url',
							width : 120
						}, {
							display : '',
							name : 'width',
							width : 120
						}, {
							display : '',
							name : 'winWh',
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
				url: basePath+"rest/sysResourceManageAction/toAddEditPage", 
				height: 600,
				width: 1000,
				modal:true, 
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
		url: basePath+"rest/sysResourceManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 900,
		modal:true, 
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
		url: basePath+"rest/sysResourceManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 900,
		modal:true, 
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

