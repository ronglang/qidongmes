var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysNoticeManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
	            { display: 'id', name: 'id',  width: 1,hide:true },
	            { display: 'key', name: 'key',  width: 1,hide:true},
	            { display: '内容', name: 'value',  width: 450 },
	            { display: '是否显示', name: 'isShow',  width: 120 },
	            { display: '类型', name: 'type',  width: 1,hide:true},
	            { display: '创建时间', name: 'createDate',  width: 180 },
		        {
                    display: '操作', isAllowHide: false,  width:120,
                    render: function (row)
                    {
                    	var html = '<a href="#" onclick="javascript:edit(' + row.id + ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
                    	/*html += '<a href="#" onclick="javascript:show(' + row.id + ');return false;">查看</a>';*/
                        return html;
                    }
                }
	        ], 
	        pageSize:10,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',
	        height:'97%',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		$("#add").click(function(){
			/*top.$.ligerDialog.open({ 
				url: basePath+"rest/sysNoticeManageAction/toAddEditPage", 
				height: 600,
				width: 1080,
				modal:true,
				title:"添加"
				});*/
			var url = basePath+"rest/sysNoticeManageAction/toAddEditPage";
			window.open(url,'管理通知','width=600,height=200');
		});
		
		
    }
);

function edit(id){
	/*top.$.ligerDialog.open({ 
		url: basePath+"rest/sysNoticeManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"编辑"
	});*/
	/*var g = $.ligerDialog.open({ 
		url: basePath+"rest/sysNoticeManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"编辑"
	});
	if(g){
		reloadData();
	}*/
	
	var url = basePath+"rest/sysNoticeManageAction/toAddEditPage?id="+id;
	window.open(url,'管理通知','width=600,height=200');
}

function reloadData(){
	var pa = $("#"+routeName+"ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa,true));
}

function show(id){
	/*top.$.ligerDialog.open({ 
		url: basePath+"rest/sysNoticeManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"详细信息"
		});*/
	$.ligerDialog.open({ 
		url: basePath+"rest/sysNoticeManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"详细信息"
		});
} 

