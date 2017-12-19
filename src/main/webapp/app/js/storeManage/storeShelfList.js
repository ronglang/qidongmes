var tbdArray = new Array();
var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
var form;
var url;
var floors;
var jsonObj={};
function itemclick(item) {
	if (item.text == "增加") {
		winAdd = top.$.ligerDialog
				.open({
					url : basePath 
							+ "rest/storeShelfManageAction/toAddEditPage",
					height : 240,
					width : 850,
					modal : true,
					title : "添加",
					isResize : true,
				});
	}
	if (item.text == "修改") {
		debugger;
		var selected = grid.getSelecteds();
		if(selected.length==0){
			$.ligerDialog.warn('您还没有选择，请选择一条修改');
			return;
		}if(selected.length>1){
			$.ligerDialog.warn('您选了多条，请选择一条修改');
		    return;
		}
		else{
			debugger;
			var id = selected[0].id;
			winEdit = top.$.ligerDialog.open({
				url : basePath
						+ "rest/storeShelfManageAction/toAddEditPage?id="
						+ id,
				height : 240,
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
													+ "rest/storeShelfManageAction/toDelete?id="
													+ id,
											type : "post",
											dataType : "json",
											// data:data,
											success : function(data) {
												parent.editRefresh();
											},
											error : function(data) {
												$.ligerDialog.error(data);
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
};


$(function() {
	url =  basePath + "rest/" + routeName + "Action/toDataGridPage";
	grids(url);
	getDates();
});

function grids(url){
	grid = $("#storeShelfManageList").ligerGrid({
		title : "货位管理",
		url :url,
		checkbox : true,
		columns : [ {
			display : '序号',
			name : 'id'
		}, {
			display : '货架名称',
			name : 'shelfname'
		}, {
			display : '货架层号',
			name : 'floor'
		}, {
			display : '货架区段位',
			name : 'columns'
		}, 
		{
			display : '每格容量', 
			name : 'capacityeach'
		}, {
			display : '余量',
			name : 'remain'
		}, {
			display : '包装规格',
			name : 'packagetype'
		},
		],
		width : '100%',
		height : '97%',
		toolbar : {
			items : [ {
				text : '增加',
				click : itemclick,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : itemclick,
				icon : 'modify'
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

$("#pageloading").hide();
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	// js解码函数
	grid.loadServerData(decodeURIComponent(pa, true));
}

function searchForm(){
	var shelfname=$("[name=shelfname]").val();
	var floor=$("[name=floor]").val();
	jsonObj.shelfName=shelfname;
	jsonObj.floor=floor;
	//var jsonStr= JSON.stringify(jsonObj); 
	var jsonQuery="shelfName="+shelfname+"&floor="+floor;
		url = basePath + "rest/" + routeName + "Action/toDataGridPage"+ "?" + jsonQuery;
		grids(url);
}
function resetForm(){
	$("[name=shelfname]").val("");
	$("[name=floor]").val("");
	grids();
}

function form(){
	form=$("#queryForm").ligerForm({
		 inputWidth: 170, labelWidth: 90, space: 40,
		                  fields: [
		                  { name: "id", type: "hidden" },
		                  { display: "货架名称", name: "shelfname", newline: false, type: "text"}, 
		                  { display: "层号", name: "floor", newline: false, type: "select", comboboxName: "floor", editor: {valueField:'text',data:floors} },
		                  ] 
   }); 
}

//设置下拉框的值
function getDates(){
	$.ajax({
		url:basePath+'rest/storeShelfManageAction/getFloor',
		type:'post',
		dataType:'json',
		success:function(floor){
			debugger;
			floors = floor;
			form(floors);
			
		},
		error:function(){
			$.ligerDialog.error("数据出错了");
		}
	});
}


