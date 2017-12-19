var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#mauWasteManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
	            { display: '序号', name: 'id',  width: 120 },{ display: '', name: 'createDate',  width: 120 },{ display: '', name: 'createBy',  width: 120 },{ display: '', name: 'code',  width: 120 },{ display: '物料编号，标识该废品是什么物料', name: 'materCode',  width: 120 },{ display: '规格', name: 'wasteType',  width: 120 },{ display: '生产部物料单（领料、退料、补料）单据编号', name: 'mmdCode',  width: 120 },{ display: '重量', name: 'wasteWeight',  width: 120 },{ display: '单位，比如重量的KG，长度的M，KM，', name: 'unit',  width: 120 },{ display: '工作单号', name: 'wsCode',  width: 120 },
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
				url: basePath+"rest/mauWasteManageAction/toAddEditPage", 
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
		url: basePath+"rest/mauWasteManageAction/toAddEditPage?id="+id, 
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
		url: basePath+"rest/mauWasteManageAction/toDetailPage?id="+id, 
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

