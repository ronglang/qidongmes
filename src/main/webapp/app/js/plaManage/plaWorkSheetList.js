var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#plaWorkSheetManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
	            { display: '序号', name: 'id',  width: 120 },{ display: '', name: 'createDate',  width: 120 },{ display: '', name: 'createBy',  width: 120 },{ display: '描述信息', name: 'remark',  width: 120 },{ display: '工作单类型', name: 'wsType',  width: 120 },{ display: '', name: 'wsCode',  width: 120 },{ display: '合同编号', name: 'scCode',  width: 120 },{ display: '批次号', name: 'batCode',  width: 120 },{ display: '型号规格', name: 'headGgxh',  width: 120 },{ display: '颜色', name: 'color',  width: 120 },{ display: '扎装段长', name: 'headZzdc',  width: 120 },{ display: '扎装段数', name: 'headZzds',  width: 120 },{ display: '总数量', name: 'totalAmount',  width: 120 },{ display: '生产通知单ID', name: 'manuNoticeId',  width: 120 },{ display: '客户id', name: 'cusId',  width: 120 },{ display: '', name: 'billDate',  width: 120 },{ display: '是否完成', name: 'isFinish',  width: 120 },{ display: '工艺id', name: 'craftId',  width: 120 },{ display: '审核标志', name: 'auditFlag',  width: 120 },{ display: '审核时间', name: 'auditTime',  width: 120 },
		        {
                    display: '操作', isAllowHide: false,  width:120,
                    render: function (row)
                    {
                    	var html = '<a href="#" onclick="javascript:edit(' + row.id + ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
                    	html += '<a href="#" onclick="javascript:show(' + row.id + ');return false;">查看</a>';
                        return html;
                    }
                }
	        ], 
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
				url: basePath+"rest/plaWorkSheetManageAction/toAddEditPage", 
				height: 600,
				width: 1080,
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

function edit(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/plaWorkSheetManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1080,
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

function reloadData(){
	var pa = $("#"+routeName+"ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa,true));
}

function show(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/plaWorkSheetManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"详细信息"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

