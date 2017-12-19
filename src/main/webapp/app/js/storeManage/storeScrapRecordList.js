var tbdArray = new Array();
var grid;

function itemclick(item) {
	if (item.text == "增加") {
		winAdd = $.ligerDialog.open({ 
				url: basePath+"rest/storeScrapRecordManageAction/toAddEditPage", 
				height: 550,
				width: 850,
				modal:true,
				title:"添加报废记录"
				 });
		
	}
	
	
	if (item.text == "修改") {
		var selected = grid.getSelecteds();
		if(selected.length==0){
			$.ligerDialog.warn('您还没有选择，请选择一条修改');
			return;
		}if(selected.length>1){
			$.ligerDialog.warn('您选了多条，请选择一条修改');
		    return;
		}
		else{
			var id = selected[0].id;
			var status= selected[0].status;
			if(status=="报废"){
				$.ligerDialog.warn('不能选择已报废的原料');
			    return;
			}
			winEdit = top.$.ligerDialog.open({
				url : basePath
						+ "rest/storeScrapRecordManageAction/toAddEditPage?id="
						+ id,
				height : 550,
				width : 850,
				modal : true,
				title : "编辑"
			});
		}
	}
	
		
	if (item.text == "删除") {
		var selected = grid.getSelecteds();
		if (selected.length == 0) { 
			$.ligerDialog.warn('请选择要删除的数据');
			return; 
		}
		var status= selected[0].status;
		if(status=="报废"){
			$.ligerDialog.warn('不能删除已报废的原料');
		    return;
		}
		var strIds = [];
		for (var i = 0; i < selected.length; i++) {
			strIds.push(selected[i].id);
			var handleIdea= selected[i].handleIdea;
			if(handleIdea=="报废"){
				$.ligerDialog.warn('不能删除已报废的原料');
			    return;
			}
		}
		// debugger;
		var id = strIds.join(",");
		// var id = selected.id;
		$.ligerDialog
				.confirm(
						"您确认要删掉这<font color=red>" + selected.length
								+ "</font>条数据吗？",
						function(result) {
							if (result == true) {
								$
										.ajax({
											url : basePath
													+ "rest/storeScrapRecordManageAction/toDelete?ids="
													+ id,
											type : "post",
											dataType : "json",
											// data:data,
											success : function(json) {
												window.location.reload(true);
												alert(json.msg);
											},
											error : function(data) {
												alert(data.msg);
											}
										});
							} else {
								return;
							}
						});
	}
}



$(function ()
    {
		//pub_initList(routeName);
		var url=basePath + "rest/"+ routeName + "Action/getDataGridPage";
		getdataGrid(url);
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
	    			inputWidth: 150, labelWidth: 70,
	    			                  fields: [
	    			                  { name: "id", type: "hidden" },
	    			                  { display: "处理时间", name: "starttime",editor:{showTime:true}, newline: false, type: "date"}, 
	    			                  { display: "到", name: "endtime", editor:{showTime:true},newline: false, type: "date"},
	    			                  { display: "材料", name: "materialName", newline: false,type: "select",comboboxName: "materialName", options:{data: matels}}, 
	    			                  { display: "型号", name: "model", newline: false},
	    			                  { display: "颜色", name: "color", newline: false},
	    			                  ]
	    		}); 
	       
	        },  
	        error: function(json) {
	            return null;
	        }
	        
	    });
	
};

function getdataGrid(url){
	grid=$("#storeScrapRecordManageList").ligerGrid({
		url : url,
        checkbox: true,
        columns: [
            { hide: '序号', name: 'id',  width: 1 },
            { display: '批次号', name: 'batchCode',  maxWidth: 120,editor:{type:'text'} },
            { display: '材料', name: 'materialName',  maxWidth: 120 ,editor:{type:'text'}},
            { display: 'RFID卡编号', name: 'rfidCode',  maxWidth: 120,editor:{type:'text'} },
            { display: '型号', name: 'model',  maxWidth: 120,editor:{type:'text'} },
            { display: '颜色', name: 'color',  maxWidth: 120,editor:{type:'text'} },
            { display: '数量', name: 'amount',  maxWidth: 120,editor:{type:'text'} },
            { display: '单位', name: 'unit',  maxWidth: 80 ,editor:{type:'text'}},
            { display: '物料位置', name: 'materialRalation',  maxWidth: 120,editor:{type:'text'} },
            { display: '处理人', name: 'handler',  maxWidth: 120 ,editor:{type:'text'}},
            { display: '申请时间', name: 'applyDate',  maxWidth: 120 },
            { display: '处理时间', name: 'handleDate',  maxWidth: 120 },
            { display: '处理方式', name: 'handleIdea',  maxWidth: 120,editor:{type:'text'} },
            { display: '状态', name: 'status',  maxWidth: 120 },
            { display: '存放位置', name: 'handleAfterPosition',  maxWidth: 120,editor:{type:'text'} },
            { display: '备注', name: 'remark',  width:260,editor:{type:'text'} },
	        {
                display: '检测报告', isAllowHide: false,  width:120,
                render: function (row)
                {
                	//var html = '<a href="#" onclick="javascript:edit(' + row.id + ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
                	var html = '<a href="javascript:void(0)" onclick="javascript:show(' + row.id + ');return false;">检验报告</a>';
                    return html;
                }
            }
        ],
        toolbar : {
			items : [{
				text : '修改',
				click : itemclick,
				icon : 'save'
			}, {
				line : true
			}, {
				line : true
			}, {
				text : '增加',
				click : itemclick,
				icon : 'add'
			}, {
				line : true
			}, {
				line : true
			}, {
				text : '删除',
				click : itemclick,
				icon : 'delete'
			} ]
		},
        width: '100%',height:'97%',
        onSuccess:function (json, grid){
        	$("#pageloading").hide(); 
        },
		onError:function (json, grid){
        	$("#pageloading").hide(); 
        },
    });
}


/*
function save(){
	var manager = $("#storeScrapRecordManageList").ligerGetGridManager();
	var li = manager.getSelectedRows();
	var dataArray = [];
	if(li.length<1){
		alert("请选择保存的行！");
		return;
	}
	for(var i = 0; i<li.length;i++){
		var bean = new Object();
		var beans = li[i];
		debugger;
		bean.batchCode = beans.batchCode;
		bean.materialName = beans.materialName;
		bean.rfidCode = beans.rfidCode;
		bean.model = beans.model;
		bean.color = beans.color;
		bean.amount = beans.amount;
		bean.unit = beans.unit;
		bean.materialRalation = beans.materialRalation;
		bean.handler = beans.handler;
		bean.handleDate = beans.handleDate;
		bean.handleIdea = beans.handleIdea;
		bean.status = beans.status;
		bean.handleAfterPosition = beans.handleAfterPosition;
		bean.remark = beans.remark;
		bean.id = beans.id;
		dataArray.push(bean);
	}
	var data = JSON.stringify(dataArray);
	//alert(data);
	$.ajax({ 
		url:  basePath+"rest/storeScrapRecordManageAction/saveProductData",
   		type:"post",
   		dataType:'json',
   		data:"li="+data,
   		success: function(map){
   			debugger;
   			if(map.success){
   	    		alert(map.msg);
   		 		location.reload();
   			}else{
   				alert(map.msg+"保存失败");
   			}
   	    },
   	    error : function() {
			alert("保存失败");
		}
	});
	
}
*/



function reloadData(){
	var pa = $("#"+routeName+"ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa,true));
}

function searchForm(){
	var materialName=$("[name=materialName]").val();
	var model=$("[name=model]").val();
	var color=$("[name=color]").val();
	var starttime=$("[name=starttime]").val();
	var endtime=$("[name=endtime]").val();
	var jsonQuery="materialName="+materialName+"&model="+model+"&color="+color+"&starttime="+starttime+"&endtime="+endtime;
	//alert(jsonStr);
	var	url = basePath + "rest/" + routeName + "Action/getDataGridPage"+ "?" + jsonQuery;
	getdataGrid(url);
}

//导出excel表
function exportExecl(){
	
	var materialName=$("[name=materialName]").val();
	var model=$("[name=model]").val();
	var color=$("[name=color]").val();
	var starttime=$("[name=starttime]").val();
	var endtime=$("[name=endtime]").val();
	
	var jsonQuery="materialName="+materialName+"&model="+model+"&color="+color+"&starttime="+starttime+"&endtime="+endtime;
	var	url = basePath + "rest/" + routeName + "Action/exportExecl"+ "?" + jsonQuery;
	window.open(url,"_self");
}

function resetForm(){
	$("[name=materialName]").val("");
	$("[name=model]").val("");
	$("[name=color]").val("");
	$("[name=starttime]").val("");
	$("[name=endtime]").val("");
	window.grid.reload();
}

function show(id){
	$.ligerDialog.open({ 
		url: basePath+"rest/storeScrapRecordManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"检查报告展示"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

