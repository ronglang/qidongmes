var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysDbmetadataManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
	            { display: '序号', name: 'id',  width: 120 },{ display: '定义', name: 'definit',  width: 120 },{ display: '中文名', name: 'cnName',  width: 120 },{ display: '英文名', name: 'enName',  width: 120 },{ display: '短名', name: 'shName',  width: 120 },{ display: '类型', name: 'metadataType',  width: 120 },{ display: '字段类型', name: 'fieldType',  width: 120 },{ display: '长度', name: 'fieldLen',  width: 120 },{ display: '精度', name: 'precision',  width: 120 },{ display: '是否主键', name: 'isPk',  width: 120 },{ display: '父', name: 'parentId',  width: 120 },{ display: '机构', name: 'orgCode',  width: 120 },{ display: '数据库地址', name: 'dbUrl',  width: 120 },{ display: '数据库类型', name: 'dbType',  width: 120 },{ display: '数据库驱动', name: 'dbDriver',  width: 120 },{ display: '数据库用户名', name: 'dbUser',  width: 120 },{ display: '数据库密码', name: 'dbPsw',  width: 120 },
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
				url: basePath+"rest/sysDbmetadataManageAction/toAddEditPage", 
				height: 600,
				width: 750,
				modal:true, 
				title:'添加元数据'
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
		url: basePath+"rest/sysDbmetadataManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 750,
		modal:true, 
		title:'编辑元数据'
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
		url: basePath+"rest/sysDbmetadataManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 750,
		modal:true, 
		title:'查看元数据'
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

