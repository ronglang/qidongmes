var tbdArray = new Array();
var grid;
$(function ()
    {
		pub_initList(routeName);
		    grid=$("#blogManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
		        { display: '主键', name: 'id',  width: 120 },
		        { display: '标题', name: 'title', width:400,
		        	render: function (row){
                    	var html = '<a href="#" onclick="javascript:show(' + row.id + ');return false;">'+row.title+'</a>';
                        return html;
                    }
		        },
		        { display: '添加时间', name: 'addDate', width: 120 },
		        { display: '添加人', name: 'own',width: 120 },
		        {
                    display: '操作', isAllowHide: false,  width:120,
                    render: function (row)
                    {
                    	var html = '<a href="#" onclick="javascript:edit(' + row.id + ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
                    	//html += '<a href="#" onclick="javascript:show(' + row.id + ');return false;">查看</a>';
                        return html;
                    }
                }
	        ], 
	        pageSize:20,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',height:'97%',
	       /* toolbar: { items: [
                               { text: '增加', click: function (){
                            	   addBlog();
                               },  icon: 'add'},
                               { line: true },
                               { text: '删除',  click: function (){
                            	   pub_del(routeName);
                               },  icon: 'delete' }
                               ]
            },*/
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
    }
);

function edit(id){
	//window.location.href = basePath+"app/jsp/userManage/blogEdit.jsp?id="+id;
	
	top.$.ligerDialog.open({ 
		url: basePath+"rest/blogManageAction/toAddEditBolgPage?id="+id, 
		height: 600,
		width: 900,
		modal:true/*, 
		buttons: [ { text: '确定', onclick: function (item, dialog) { 
				alert(item.text); 
			} 
		}, 
	                                                                                   
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		] */});
}

function addBlog(){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/blogManageAction/toAddEditBolgPage", 
		height: 600,
		width: 1000,
		modal:true,
		title:'添加模板DEMO'
		/*, 
		buttons: [ { text: '确定', onclick: function (item, dialog) { 
				alert(item.text); 
			} 
		}, 
	                                                                                   
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		] */});
}

function reloadData(){
	//grid.reload(); 
	var pa = $("#"+routeName+"ListForm").serialize();
	//var data =$("#blogManageFormList").serializeArray();
	//alert(decodeURIComponent(pa,true)+"  --" +decodeURIComponent($.param(data),true));
	grid.loadServerData(decodeURIComponent(pa,true));
}

function show(id){
	//window.location.href =  basePath+"app/jsp/userManage/blogForm.jsp?id="+id;
	
	top.$.ligerDialog.open({ 
		url: basePath+"rest/blogManageAction/toBlogDetailPage?id="+id, 
		height: 600,
		width: 900,
		modal:true/*, 
		buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

function video(name){
	//window.location.href =  basePath+"app/jsp/userManage/blogForm.jsp?id="+id;
	
	top.$.ligerDialog.open({ 
		//url: basePath+"video3.jsp?name="+name, 
		url: basePath+"rest/blogManageAction/toVideoPage?name="+name, 
		height: 520,
		width: 760,
		title:"视频监控-"+name,
		modal:true});
} 

function shiwuVideo(){
	top.$.ligerDialog.open({ 
		url: basePath+"jsp/video_hkws_ivms8800.jsp", 
		height: 630,
		width: 1000,
		title:"雅安水務视频监控DEMO",
		modal:true});
}

