var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#mauMaterielDetailedManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
	            { display: '序号', name: 'id',  width: 120 },{ display: '', name: 'createDate',  width: 120 },{ display: '', name: 'createBy',  width: 120 },{ display: '物料编号', name: 'materCode',  width: 120 },{ display: '', name: 'materAmount',  width: 120 },{ display: '明细来源（如：退料单，补料单，领料单）', name: 'docType',  width: 120 },{ display: '', name: 'mmdCode',  width: 120 },
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
				url: basePath+"rest/mauMaterielDetailedManageAction/toAddEditPage", 
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
		url: basePath+"rest/mauMaterielDetailedManageAction/toAddEditPage?id="+id, 
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
		url: basePath+"rest/mauMaterielDetailedManageAction/toDetailPage?id="+id, 
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

