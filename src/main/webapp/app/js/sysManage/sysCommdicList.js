var tbdArray = new Array();
var grid;
$(function ()
    {
		//pub_initList(routeName);
		grid=$("#sysCommdicManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage",
	        checkbox: true,
	        columns: [
	            { display: '序号', name: 'id',  width: 120 },
	            { display: '类别名称', name: 'clas',  width: 120 },
	            { display: '值', name: 'value',  width: 190 },
	            //{ display: '是否系统字典', name: 'isSystem',  width: 120 },
	           
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
	         pageSize:30,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
//	        onToFirst: setParms,
//			onToNext: setParms,
//			onToPrev:setParms,
//			onToLast: setParms,
//			onReload:setParms,
	        width: '98%',height:'98%',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		
		/* 新增*/
		$("#add").click(function(){
		
			var clas = $("#hiddenValue").val();
		//	var clas ;
			if(clas==""){
				top.$.ligerDialog.warn("请选择数据字典类别");
				return;
			}else{
				top.$.ligerDialog.open({ 
					url: basePath+"rest/sysCommdicManageAction/toAddEditPage?clas="+clas, 
					height: 400,
					width: 650,
					modal:true, 
					title:'添加数据字典'
					 });
			}
			
		});
    }

	
);

//function setParms() {
//	grid.setParm("clas",$("#clas").val());
//	grid.setParm("value",$("#value").val());
//}

function edit(id){
//	alert(id);
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysCommdicManageAction/toAddEditPage?id="+id, 
		height: 600,
		width: 750,
		modal:true,
		title:'数据字典编辑'});
}

/*function reloadData(){
	var curpage = $(".pcontrol").find("input").val();
	if(curpage == 1){
		var pa = $("#"+routeName+"ListForm").serialize()+"&page=1&pagesize="+$("select[name='rp']").val();
		grid.loadServerData(decodeURIComponent(pa,true));
	}else{
		$(".l-bar-btnfirst").trigger('click');
	}
}*/

function show(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sysDictionaryManageAction/toDetailPage?id="+id, 
		height: 400,
		width: 700,
		modal:true,
		title:'数据字典详细信息'
		 });
} 

function filter(node) {
	//node.level == 2 &&
	var w = $('#word').val();
    return ( node.name.indexOf(w)>-1);
}

function searchIt(){
	//var treeObj = $.fn.zTree.getZTreeObj("treeEventInfoAuditOrg");
	//显示之前被隐藏的
	/*var nodes = treeObj.getNodesByParam("isHidden", true);
	if(nodes)
		treeObj.showNodes(nodes);*/
	
	//var node = treeObj.getNodesByFilter(filter, true); // 仅查找一个节点
    //var nodes = treeObj.getNodesByFilter(filter); // 查找节点集合
	//alert(node.name);
    /*$.each(nodes,function(i,o){
    	treeObj.hideNode(o);
    });*/
    //if(nodes)
	//	treeObj.hideNodes(nodes);
	var w = $('#word').val();
	$.ajax( {
	      type : 'post',
	       url : basePath+'rest/sysCommdicManageAction/nodeTreeClas',
	       data:'&qcolValue='+w,
	      //url : basePath+'rest/sysCommdicManageAction/nodeTree?qcolName=isleaf&qcolValue=0',
	      dataType : 'json',
	      error : function(res) {
	        alert("error");
	      },
	      success : function(res) {
	        $.fn.zTree.init($("#treeEventInfoAuditOrg"), setting, res.data);
	      }
	    });
}