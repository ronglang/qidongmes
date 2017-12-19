var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysTelLocationManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
	            { display: '序号', name: 'id',  width: 120 },{ display: '编码', name: 'code',  width: 120 },{ display: '姓名', name: 'name',  width: 120 },{ display: '性别', name: 'gender',  width: 120 },{ display: '民族', name: 'nation',  width: 120 },{ display: '身份证号', name: 'idNo',  width: 120 },{ display: '电话', name: 'tel',  width: 120 },{ display: '地址', name: 'address',  width: 120 },{ display: '经度', name: 'longitude',  width: 120 },{ display: '纬度', name: 'latitude',  width: 120 },{ display: '创建人ID', name: 'createBy',  width: 120 },{ display: '记录时间', name: 'createDate',  width: 120 },{ display: '更新人', name: 'updateBy',  width: 120 },{ display: '更新时间', name: 'updateDate',  width: 120 },
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
				url: basePath+"rest/sysTelLocationManageAction/toAddEditPage", 
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
		url: basePath+"rest/sysTelLocationManageAction/toAddEditPage?id="+id, 
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
		url: basePath+"rest/sysTelLocationManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 900,
		modal:true, 
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

