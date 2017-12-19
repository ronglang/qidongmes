var tbdArray = new Array();
var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
var form;
var jsonObj={};
function itemclick(item) {
	if (item.text == "增加") {
		
		winAdd = top.$.ligerDialog
				.open({
					url : basePath 
							+ "rest/craWireDiscManageAction/toAddEditPage",
					height : 300,
					width : 700,
					modal : true,
					title : "添加线盘",
					isResize : true,
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
			winEdit = top.$.ligerDialog.open({
				url : basePath
						+ "rest/craWireDiscManageAction/toAddEditPage?id="
						+ id,
				height : 300,
				width : 700,
				modal : true,
				title : "修改线盘"
			});
		}
	}
	if (item.text == "删除") {
		var selected = grid.getSelecteds();
		if (selected.length == 0) { 
			$.ligerDialog.warn('请选择要删除的数据');
			return; 
		}
		var strIds = [];
		for (var i = 0; i < selected.length; i++) {
			strIds.push(selected[i].id);
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
													+ "rest/craWireDiscManageAction/toDelete?id="
													+ id,
											type : "post",
											dataType : "json",
											// data:data,
											success : function(data) {
												$.ligerDialog.success('删除成功');
												var url =basePath + "rest/" + routeName + "Action/toDataGridPage";
												getDatagrid(url);
												
											},
											error : function(json) {
												$.ligerDialog.error('删除失败,发生了未知错误！');
												var url =basePath + "rest/" + routeName + "Action/toDataGridPage";
												getDatagrid(url);
											}
										});
							} else {
								return;
							}
						});
	}
}

parent.editRefresh = function(){
	grid.reload();
}


$(function() {
	var url =basePath + "rest/" + routeName + "Action/toDataGridPage";
	getDatagrid(url);
	getFormSelect();
});

function getFormSelect(){
	var url =basePath + "rest/" + routeName + "Action/getSelectOption";
	$.ajax({
		type: "GET",
		url:url,
		dataType:"json",
		success:function(json){
			var amatels=[];
			var bmatels=[];
			var cmatels=[];
        	var ma1=json.线盘规格型号;
        	var ma2=json.线盘质地;
        	var ma3=json.线盘状态;
        	var ma4=json.线盘使用状态;
        	for (var int = 0; int <ma1.length; int++) {
        		var matel = new Object();
        		matel.id=ma1[int].value;
        		matel.text=ma1[int].value;
        		amatels.push(matel);
        		}
        	
        	for (var int = 0; int < ma3.length; int++) {
        		var matel = new Object();
        		matel.id=ma3[int].value;
        		matel.text=ma3[int].value;
        		bmatels.push(matel);
        		}	
        	
        	for (var int = 0; int < ma4.length; int++) {
            		var matel = new Object();
            		matel.id=ma4[int].value;
            		matel.text=ma4[int].value;
            		cmatels.push(matel);
            		}
        	form=$("#queryForm").ligerForm({
       		 inputWidth: 170, labelWidth: 90, space: 40,
       		                  fields: [
       		                  { name: "id", type: "hidden" },
       		                  { display: "rfid编号", name: "rfidNumber", newline: false, type: "text"}, 
       		                  { display: "型号", name: "wireDiscPgxh", newline: false, type: "select", comboboxName: "wireDiscPgxh", options: {data: amatels } },
       		                  { display: "状态", name: "status", newline: false, type: "select", comboboxName: "status", options:{data: bmatels } },
       		                  { display: "使用状态", name: "useStatus", newline: false, type: "select", comboboxName: "useStatus", options: {data: cmatels } },
       		                  ] 
          }); 
		},
	error:function(){
		
	}
		
	});
	
	
}

function getDatagrid(url){
	grid = $("#craWireDiscManageList").ligerGrid({
		title : "线盘管理",
		url : url,
		checkbox : true,
		columns : [ {
			hide : '序号',
			name : 'id',width:1
		}, {
			display : 'rfid编号',
			name : 'rfidNumber',maxWidth:230
		}, {
			display : '规格',
			name : 'wireDiscPgxh',
			maxWidth:150
		},  {
			display : '材质',
			name : 'materialTexture',
			maxWidth:120
		},{
			display : '状态',
			name : 'status',
			maxWidth:130
		}, 
		{
			display : '使用状态', 
			name : 'useStatus',
			maxWidth:130
		},
		{
			display : '外径', 
			name : 'externalDiameter',
			maxWidth:180
		},
		{
			display : '内径', 
			name : 'boreDiameter',
			maxWidth:180
		},
		{
			display : '容量', 
			name : 'capacity',
			maxWidth:180
		},
		],
		enabledEdit:true,
		width : '100%',
		height : '97%',
		toolbar : {
			items : [{
				text : '修改',
				click : itemclick,
				icon : 'modify'
			}, {
				line : true
			},{
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
		
	});
}

function addRows(){
	var manager = $("#craWireDiscManageList").ligerGetGridManager();
	var row = manager.getSelectedRow();
	manager.addRow({
		rfidNumber:'',
		wireDiscPgxh:'',
		status:'',
		useStatus:'',
		externalDiameter:'',
		boreDiameter:'',
		capacity:'',
	});
}

function save(){

	var manager = $("#craWireDiscManageList").ligerGetGridManager();
	var li = manager.getSelectedRows();
	var dataArray = [];
	if(li.length<1){
		$.ligerDialog.warn("请选择保存的行！");
		return;
	}
	for(var i = 0; i<li.length;i++){
		var bean = new Object();
		var beans = li[i];
		bean.rfidNumber = beans.rfidNumber;
		bean.wireDiscPgxh = beans.wireDiscPgxh;
		bean.status = beans.status;
		bean.useStatus = beans.useStatus;
		bean.externalDiameter = beans.externalDiameter;
		bean.boreDiameter = beans.boreDiameter;
		bean.capacity = beans.capacity;
		bean.id = beans.id;
		dataArray.push(bean);
	}
	var data = JSON.stringify(dataArray);
	//alert(data);
	$.ajax({ 
		url:  basePath+"rest/craWireDiscManageAction/saveProductData",
   		type:"post",
   		dataType:'json',
   		data:"li="+data,
   		success: function(map){
   			if(map.success){
   				$.ligerDialog.success('保存成功');
   		 		window.location.reload();
   			}else{
   				$.ligerDialog.error('保存失败,发生了未知错误！');
   			}
   	    },
   	    error : function() {
   	    	$.ligerDialog.error('保存失败,发生了未知错误！');
		}
	});
	

}

$("#pageloading").hide();
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	// js解码函数
	grid.loadServerData(decodeURIComponent(pa, true));
}


function searchForm(){
	var rfidNumber=$("[name=rfidNumber]").val();
	var wireDiscPgxh=$("[name=wireDiscPgxh]").val();
	var status=$("[name=status]").val();
	var useStatus=$("[name=useStatus]").val();

	var jsonQuery="rfidNumber="+rfidNumber+"&wireDiscPgxh="+wireDiscPgxh+"&status="+status+"&useStatus="+useStatus;

		url = basePath + "rest/" + routeName + "Action/toDataGridPage"+ "?" + jsonQuery;
		getDatagrid(url);
}

function resetForm(){
	$("[name=rfidNumber]").val("");
	$("[name=wireDiscPgxh]").val("");
	$("[name=status]").val("");
	$("[name=useStatus]").val("");
	var url =basePath + "rest/" + routeName + "Action/toDataGridPage";
	getDatagrid(url);
	//window.location.reload(true);
	
}
