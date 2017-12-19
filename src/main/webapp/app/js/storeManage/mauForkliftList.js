var tbdArray = new Array();
var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
var form;
var url;
var ftypes;
var jsonObj={};
function itemclick(item) { 
	if (item.text == "增加") { 
		winAdd = $.ligerDialog
				.open({
					// dialog中添加form表单
					url : basePath 
							+ "rest/storeForkliftManageAction/toAddEditPage",
					height : 240,
					width : 850,
					modal : true,
					title : "添加",
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
			winEdit = $.ligerDialog.open({
				url : basePath
						+ "rest/storeForkliftManageAction/toAddEditPage?id="
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
													+ "rest/storeForkliftManageAction/toDelete?id="
													+ id,
											type : "post",
											dataType : "json",
											// data:data,
											success : function(data) {
											   parent.manuForkliftRefresh();
											},
											error : function(data) {
												alert(data);
											}
										});
							} else {
								return;
							}
						});
	} 
}
parent.manuForkliftRefresh = function(){
  grid.reload();	
};
$(function() {
	url = basePath + "rest/" + routeName + "Action/toListPage";
	grids(url);
	getDates();
});

function grids(url){
	grid = $("#storeManageList").ligerGrid({ 
		title : "叉车信息管理", 
		url : url,
		checkbox : true,
		columns : [ { display : '序号', name : 'id' }, 
		            { display : '叉车编号', name : 'fcode'  }, 
		            { display : '叉车类型', name : 'ftype' }, 
		            { display : '叉车驾驶员', name : 'fdriver' },
		            { display : '状态', name : 'status' },
		            { display : "创建时间", name:'createdate' }
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

function form(){
	form=$("#queryForm").ligerForm({
		 inputWidth: 150, labelWidth: 90, space: 20,
		                  fields: [
		                  { name: "id", type: "hidden" },
		                  { display: "叉车编号", name: "fcode", newline: false, type: "text"}, 
		                  { display: "叉车类型", name: "ftype", newline: false, type: "select", comboboxName: "ftype",  editor: {valueField:'text',data:ftypes}  },
		                  { display: "创建起始时间", name: "starttime", newline: false, type: "date"}, 
		                  { display: "创建结束时间", name: "endtime", newline: false, type: "date"}
		                  ]
   }); 
}

function searchForm(){
	var fcode=$("[name=fcode]").val();
	var ftype=$("[name=ftype]").val();
	var starttime=$("[name=starttime]").val();
	var endtime=$("[name=endtime]").val();
	jsonObj.fCode=fcode;
	jsonObj.fType=ftype;
	jsonObj.startTime=starttime;
	jsonObj.endTime=endtime;
	//var jsonStr= JSON.stringify(jsonObj); 
	var jsonQuery="fCode="+fcode+"&fType="+ftype+"&startTime="+starttime+"&startTime="+"&endTime="+endtime;
	//alert(jsonStr);
		url = basePath + "rest/" + routeName + "Action/toListPage"+ "?" + jsonQuery;
		grids(url);
}
function resetForm(){
	url = basePath + "rest/" + routeName + "Action/toListPage";
	grids(url);
}

//设置下拉框的值
function getDates(){
	$.ajax({
		url:basePath+'rest/storeForkliftManageAction/getFtype',
		type:'post',
		dataType:'json',
		success:function(ftype){
			debugger;
			ftypes = ftype;
			form(ftypes);
		},
		error:function(){
			alert("数据出错了");
		}
	});
}
