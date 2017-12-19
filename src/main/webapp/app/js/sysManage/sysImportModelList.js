var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysImportModelManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
								            {
									display : '序号',
									name : 'id',
									width : 120
								},
								{
									display : '模板名称',
									name : 'modelname',
									width : 180
								},
								{
									display : '标题行数',
									name : 'ignorecols',
									width : 120
								},
								{
									display : '跳过列数',
									name : 'ignorerows',
									width : 120
								},
								{
									display : '表名',
									name : 'tablename',
									width : 120
								},
								{
									display : '对应列名称',
									name : 'colnames',
									width : 120
								},
		        {
                    display: '操作', isAllowHide: false,  width:120,
                    render: function (row)
                    {
                    	var html = '<a href="#" onclick="javascript:edit(' + row.id + ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
                    	html += '<a href="#" onclick="javascript:show(' + row.id + ');return false;">查看</a>&nbsp;&nbsp;&nbsp';
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
				url: basePath+"rest/sysImportModelManageAction/toAddEditPage", 
				height: 600,
				width: 750,
				modal:true, 
				title:'添加模板'
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
		url: basePath+"rest/sysImportModelManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 750,
		modal:true, 
		title:'编辑模板'
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
		url: basePath+"rest/sysImportModelManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 750,
		modal:true,
		title:'模板详情'
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
}

function importData(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysImportModelManageAction/toImportPage?id="+id, 
		height: 600,
		width: 750,
		modal:true, 
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
}

