var tbdArray = new Array();
var grid;
function itemclick(item) {
	if (item.text == "编辑") {
		var selected = grid.getSelecteds();
		if (selected.length == 0) { 
			$.ligerDialog.warn('请选择需要编辑的数据！');
			return; 
		}
		if (selected.length > 1) { 
			$.ligerDialog.warn('不能选择多条数据');
			return; 
		}
		var outCode=selected[0].outboundOrderCode;
		winAdd =top.$.ligerDialog.open({ 
				url: basePath+"rest/storeDgCkManageAction/toAddEditPage?outCode="+outCode, 
				height: 550,
				width: 1080,
				modal:true,
				title:"编辑出库单"
				});
	}
	
	if (item.text == "保存") {
		save();
	}
	if (item.text == "删除") {
		pub_del(routeName);
		
	}
}


$(function ()
    {
		//pub_initList(routeName);
	var url =basePath + "rest/"+ routeName + "Action/getDataGridPage";
	getDataGrid(url);
	
		
		getSelectFunction();
		$("#pageloading").hide();
    });



 function getSelectFunction(){
	 $.ajax({
	        url: basePath + "rest/"+ routeName + "Action/getSelectOption",
	        type:"post",
	        dataType:"json",
	        async:false,
	        success: function(json){
	        	var matels=[];
	        	var obj=json.select;
	        	for (var int = 0; int < obj.length; int++) {
	        		var matel = new Object();
	        		/*selectEdName.append( "<option value='"+obj[int]+"'>'"+obj[int]+"'</option>" );*/
	        		matel.id=obj[int];
	        		matel.text=obj[int];
	        		matels.push(matel);
	        	
				}
	        	form=$("#queryForm").ligerForm({
	    			inputWidth: 150, labelWidth: 65,
	    			                  fields: [
	    			                  { name: "id", type: "hidden" },
	    			                  { display: "规格型号", name: "objGgxh", newline: false,width:150}, 
	    			                  { display: "领料单号", name: "pickListCode", newline: false,width:150},
	    			                  { display: "出库单号", name: "outboundOrderCode", newline: false,width:150},
	    			                  { display: "状态", name: "status", newline: false,type: "select",comboboxName: "status", options:{data: matels},width:100}, 
	    			                  { display: "操作时间", name: "starttime",editor:{showTime:true}, newline: true, type: "date",width:150}, 
	    			                  { display: "到", name: "endtime", editor:{showTime:true},newline: false, type: "date",width:150},
	    			                  
	    			                  ]
	    		}); 
	       
	        },  
	        error: function(json) {
	            return null;
	        }
	        
	    });
	
};
	

function save(){
	var manager = $("#storeDgCkManageList").ligerGetGridManager();
	var li = manager.getSelectedRows();
	var dataArray = [];
	if(li.length<1){
		$.ligerDialog.warn("请选择保存的行！");
		return;
	}
	for(var i = 0; i<li.length;i++){
		var bean = new Object();
		var beans = li[i];
		bean.pickListCode = beans.pickListCode;
		bean.objGgxh = beans.objGgxh;
		bean.sumamount = beans.sumamount;
		bean.unit = beans.unit;
		bean.alreadyReceiveAmount = beans.alreadyReceiveAmount;
		bean.leaveAmount = beans.leaveAmount;
		bean.createDate = beans.createDate;
		bean.outStorageDate = beans.outStorageDate;
		bean.createBy = beans.createBy;
		bean.handleIdea = beans.handleIdea;
		bean.status = beans.status;
		bean.id = beans.id;
		dataArray.push(bean);
	}
	var data = JSON.stringify(dataArray);
	//alert(data);
	$.ajax({ 
		url:  basePath+"rest/storeDgCkManageAction/saveProductData",
   		type:"post",
   		dataType:'json',
   		data:"li="+data,
   		success: function(map){
   			debugger;
   			if(map.success){
   				$.ligerDialog.success(map.msg);
   		 		location.reload();
   			}else{
   				$.ligerDialog.error(map.msg+"保存失败");
   			}
   	    },
   	    error : function() {
   	    	$.ligerDialog.error("保存失败");
		}
	});
	
}
function edit(id){
	top.$.ligerDialog.open({ 
	
		url: basePath+"rest/storeDgCkManageAction/toAddEditPage?id="+id, 
		height: 400,
		width: 800,
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


function update(){
	
	top.$.ligerDialog.open({ 
	
		url: basePath+"rest/storeDgCkManageAction/toAddEditPage?id="+id, 
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

function searchForm(){
	var pickListCode=$("[name=pickListCode]").val();
	var outboundOrderCode=$("[name=outboundOrderCode]").val();
	var objGgxh=$("[name=objGgxh]").val();
	var status=$("[name=status]").val();
	var starttime=$("[name=starttime]").val();
	var endtime=$("[name=endtime]").val();
	var jsonQuery="pickListCode="+pickListCode+"&objGgxh="+objGgxh+"&status="+status+"&starttime="+starttime+"&endtime="+endtime+"&outboundOrderCode="+outboundOrderCode;
	//alert(jsonStr);
	var	url = basePath + "rest/" + routeName + "Action/getDataGridPage"+ "?" + jsonQuery;
		getDataGrid(url);	
		
}




function getDataGrid(url){
	grid=$("#storeDgCkManageList").ligerGrid({
		url : url,
        checkbox: true,
        columns: [
            { display: '序号', name: 'id',  maxWidth:150 },
            { display: '出库单号', name: 'outboundOrderCode',  maxWidth: 250},
            { display: '领料单号', name: 'pickListCode',  maxWidth: 250},
            { display: '操作人', name: 'operator',  maxWidth: 240 },
            { display: '操作时间', name: 'operatTime',  maxWidth: 240 },
            { display: '领料人', name: 'picktor',  maxWidth: 240 },
            { display: '出库状态', name: 'status',  maxWidth: 240 },
	      { display: '操作', name: '',  maxWidth: 240,
                render: function (row)
                {
                	var html = '<a  href="javascript:void(0)" onclick="javascript:showTable(' + row.id + ');return false;">详情</a>&nbsp;&nbsp;&nbsp;';
                	//var html = '<a href="#" onclick="javascript:show(' + row.id + ');return false;">查看检查报告</a>';
                    return html;
                }
            }
        ],
        toolbar : {
			items : [{
				text : '编辑',
				click : itemclick,
				icon : 'modify'
			},
			    {
				line : true
			}, {
				line : true
			}, {
				text : '导出',
				click : exportExecl,
				icon : 'print'
			}, {
				line : true
			}, {
				line : true
			}, {
				text : '出库',
				click : outStore,
				icon : 'outbox'
			} ]
		},
        pageSize:10,
        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
        width: '100%',height:'97%',
        onSuccess:function (json, grid){
        	$("#pageloading").hide(); 
        },
		onError:function (json, grid){
        	$("#pageloading").hide(); 
        },
    });
	
	

}
//出库修改状态
function outStore(){
	var selected = grid.getSelecteds();
	if (selected.length == 0) { 
		$.ligerDialog.warn('请选择需要出库的行');
		return; 
	}
	
	var strIds = [];
	for (var i = 0; i < selected.length; i++) {
		strIds.push(selected[i].id);
		var status= selected[i].status;
		if(status=="已出库"){
			$.ligerDialog.warn('不能选择第'+i+'行,该物料已出库');
		    return;
		}
	}
	// debugger;
	var id = strIds.join(",");
	// var id = selected.id;
	$.ligerDialog
			.confirm(
					"您确认需要出库<font color=red>" + selected.length
							+ "</font>吗？",
					function(result) {
						if (result == true) {
							$
									.ajax({
										url :  basePath + "rest/" + routeName + "Action/outStore"+ "?id=" +id ,
										type : "post",
										dataType : "json",
										// data:data,
										success : function(json) {
											window.location.reload(true);
											$.ligerDialog.success(json.data);
										},
										error : function(json) {
											$.ligerDialog.error(json.data);
										}
									});
						} else {
							return;
						}
					});
}
//导出excel表
function exportExecl(){
	
	var pickListCode=$("[name=pickListCode]").val();
	var outboundOrderCode=$("[name=outboundOrderCode]").val();
	var objGgxh=$("[name=objGgxh]").val();
	var status=$("[name=status]").val();
	var starttime=$("[name=starttime]").val();
	var endtime=$("[name=endtime]").val();
	var jsonQuery="pickListCode="+pickListCode+"&objGgxh="+objGgxh+"&status="+status+"&starttime="+starttime+"&endtime="+endtime+"&outboundOrderCode="+outboundOrderCode;
	var	url = basePath + "rest/" + routeName + "Action/exportExecl"+ "?" + jsonQuery;
	window.open(url,"_self");
}

function resetForm(){
	$("[name=pickListCode]").val("");
	$("[name=outboundOrderCode]").val("");
	$("[name=objGgxh]").val("");
	$("[name=status]").val("");
	$("[name=starttime]").val("");
	$("[name=endtime]").val("");
	window.grid.reload();
}
/**
 * 查看详情
 * @param id
 */
function showTable(id){
	$.ligerDialog.open({ 
		url: basePath+"rest/storeDgCkManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"查看出库明细"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

