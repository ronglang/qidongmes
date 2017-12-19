var tbdArray = new Array();
var groupicon = "../../../lib/ligerUI/skins/icons/communication.gif";
var form;
var jsonObj={};
function itemclick(item) { 
	if (item.text == "增加") { 
		winAdd = top.$.ligerDialog
				.open({
					// dialog中添加form表单
					url : basePath 
							+ "rest/storeManageAction/toAddEditPage",
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
			winEdit = top.$.ligerDialog.open({
				url : basePath
						+ "rest/storeManageAction/toAddEditPage?id="
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
													+ "rest/storeManageAction/toDelete?id="
													+ id,
											type : "post",
											dataType : "json",
											// data:data,
											success : function(data) {
												$(
														".l-bar-button.l-bar-btnload",
														window.parent.document)
														.click();
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
$(function() {
	grid = $("#storeManageList").ligerGrid({ 
		title : "叉车信息管理", 
		url : basePath + "rest/" + routeName + "Action/toListPage",
		checkbox : true,
		columns : [ {
			display : '序号',
			name : 'id'
		}, {
			display : '叉车编号',  
			name : 'fcode' 
		}, {
			display : '叉车类型',
			name : 'ftype'
		}, {
			display : '叉车驾驶员',
			name : 'fdriver'
		},{
			display:"创建时间",
			name:'createdate'
		}
		],
		pageSize : 10,
		width : '100%',
		height : '97%',
		onSuccess:function (json, grid){
			/*debugger;
			var data=json.Rows;*/
		    liger.get("ftype").setData(json.Rows);
	    },
		onError:function (json, grid){
			var data=json.Rows;
			alert(data);
			form.setData(json.Rows);
	    },
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
	form=$("#queryForm").ligerForm({
		 inputWidth: 170, labelWidth: 90, space: 40,
		                  fields: [
		                  { name: "id", type: "hidden" },
		                  { display: "叉车编号", name: "fcode", newline: false, type: "text"}, 
		                  { display: "叉车类型", name: "ftype", newline: false, type: "select", comboboxName: "ftype", options: {  textField: 'ftype' } },
		                  { display: "创建起始时间", name: "starttime", newline: false, type: "date"}, 
		                  { display: "创建结束时间", name: "endtime", newline: false, type: "date"}
		                  ]
    }); 
});
/*$("#pageloading").hide();
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	// js解码函数
	grid.loadServerData(decodeURIComponent(pa, true));
}
function show(id) {
	top.$.ligerDialog.open({
		url : basePath + "rest/mauHandlingChoresManageAction/toDetailPage?id="
				+ id,
		height : 600,
		width : 1080,
		modal : true,
		title : "详细信息"  
	});
}
function searchForm(){
	var agentby=$("[name=agentby]").val();
	var choresname=$("[name=choresname]").val();
	var matercode=$("[name=matercode]").val(); 
	var page=parseInt($('.pcontrol input').val());
	var pagesize=$(".l-bar-selectpagesize select").val();
	jsonObj.agentBy=agentby;
	jsonObj.choresName=choresname;
	jsonObj.materCode=matercode;
	jsonObj.page=parseInt($('.pcontrol input').val());
	jsonObj.pagesize=$(".l-bar-selectpagesize select").val();
	var jsonStr= JSON.stringify(jsonObj); 
	var jsonQuery="agentBy="+agentby+"&choresName="+choresname+"&materCode="+matercode+"&page="+page+"&pagesize="+pagesize;
	//alert(jsonStr);
		url = basePath + "rest/" + routeName + "Action/toDataGridPage"+ "?" + jsonQuery;
		grid = $("#mauForkliftManageList").ligerGrid({
			title : "叉车信息管理",
			url :url,
			checkbox : true,
			columns : [ {
				display : '序号',
				name : 'id'
			}, {
				display : '叉车编号',
				name : 'f_code'
			}, {
				display : '叉车类型',
				name : 'f_type'
			}, {
				display : '叉车驾驶员',
				name : 'matercode'
			},{
				display:"创建时间",
				name:'create_date'
			}
			],
			pageSize : 10,
			width : '100%',
			height : '97%',
			onSuccess:function (json, grid){
				debugger;
				var data=json.Rows;
			    liger.get("ftyp").setData(json.Rows);
				 $.ligerui.get("fdriver").setData(json.Rows);
		    },
			onError:function (json, grid){
				var data=json.Rows;
				alert(data);
				form.setData(json.Rows);
		    },
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
function resetForm(){
	grid = $("#mauForkliftManageList").ligerGrid({
		title : "杂物处理",
		url : basePath + "rest/" + routeName + "Action/toDataGridPage",
		checkbox : true,
		columns : [ {
			display : '序号',
			name : 'id'
		}, {
			display : '叉车编号',  
			name : 'f_code' 
		}, {
			display : '叉车类型',
			name : 'f_type'
		}, {
			display : '叉车驾驶员',
			name : 'f_driver'
		},{
			display:"创建时间",
			name:'create_date'
		}
		],
		pageSize : 10,  
		width : '100%',
		height : '97%',
		onSuccess:function (json, grid){
			debugger;
			var data=json.Rows;
			     liger.get("ftyp").setData(json.Rows);
				 $.ligerui.get("fdriver").setData(json.Rows);
	    },
		onError:function (json, grid){
			var data=json.Rows;
			alert(data);
			form.setData(json.Rows);
	    },
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
		}
	});
}
*/