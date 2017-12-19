var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#orgYearplanRecordManageList").ligerGrid({
			url : basePath + "rest/orgYearplanRecordManageAction/dataGridPagevw?id="+yearplanId,
	        checkbox: true,
						        columns : [
								{
									display : '上报时间',
									name : 'recordYear',
									width : 300
								},
								{
									display : '操作',
									isAllowHide : false,
									width : 120,
									render : function(row) {
										var html = '<a href="#" onclick="javascript:edit('
												+ row.id
												+ ',\''+row.recordYear+'\');return false;">编辑进度</a>&nbsp;&nbsp;&nbsp;';
										return html;
									}
								} ], 
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
			top.$.ligerDialog.open({ 
				url: basePath+"rest/orgYearplanRecordManageAction/toAddEditPage?id="+yearplanId, 
				height: window.screen.availHeight-100,
				width: window.screen.availWidth-100,
				modal:true,
				title:"添加"
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

function edit(id,recordYear){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/orgYearplanRecordManageAction/toAddEditPage?id="+id+"&recordYear="+recordYear, 
		height: window.screen.availHeight-100,
		width: window.screen.availWidth-100,
		modal:true, 
		title:"编辑"
		/*buttons: [ { text: '确定', onclick: function (item, dialog) { 
				alert(item.text); 
			} 
		}, 
	                                                                                   
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		] */});
}

//function reloadData(){
//	var pa = $("#"+routeName+"ListForm").serialize();
//	grid.loadServerData(decodeURIComponent(pa,true));
//}

function show(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/orgYearplanRecordManageAction/toDetailPage?id="+id, 
		height: window.screen.availHeight-100,
		width: window.screen.availWidth-100,
		modal:true, 
		title:"详细信息"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

