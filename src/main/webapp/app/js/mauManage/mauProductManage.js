var param = "";
var url ;

$(document).ready(function() {
	url = basePath + 'rest/mauProductManageAction/getProductList';
	grid(url);

	// debugger;
	// 点击搜索功能
	

});

$("#sort").click(function() {
	var pro_name = $("#pro_name").val();
	var pro_type = $("#pro_type option:selected").text();
	debugger;
	url = basePath+ 'rest/mauProductManageAction/getProductList?pro_name='
	+ pro_name+'&pro_type='+pro_type;
	grid(url);
});

function grid() {

	window['g'] = $("#maingrid").ligerGrid(
			{
				height : '90%',
				title : '产品列表管理',
				url : url,
				checkbox : true,
				columns : [ {
					display : '产品规格型号',
					name : 'pro_ggxh',
					width:180
				}, {
					display : '产品名称',
					name : 'pro_name',
					width:180
				}, {
					display : '产品类型',
					name : 'pro_type'
				}, {
					display : '产品颜色',
					name : 'pro_color'
				}, {
					display : '芯线数',
					name : 'core',
					width : 50
				}, {
					display : '大芯线颜色',
					name : 'colorCore',
					width : 150
				}, {
					display : '大芯线内芯数',
					name : 'inCore',
					width : 150
				}, {
					display : '截面积',
					name : 'area',
					width : 50
				},{
					display : '备注',
					name : 'remark'
				}, {
					hide : 'id',
					name : 'id',
					width : 1
				} ],
				toolbar : {
					items : [
					// { text: '保存', click: save, icon: 'save' },
					{
						text : '显示所有',
						click : gridTwo,
						icon : 'down'
					}, {
						text : '增加',
						click : addRows,
						icon : 'add'
					}, {
						text : '修改',
						click : updateRows,
						icon : 'modify'
					}, {
						text : '删除',
						click : deleteRows,
						icon : 'delete'
					}, {
						line : true
					},
					// { text: '增加产品类型', click: addProductType, icon: 'add'},
					// { text: '产品类型', click: productType, icon: 'logout'},

					]
				},
				rownumbers : true,
				enabledEdit : true,
				pageSize : 14
			});
	$("#pageloading").hide();
}
/* 搜索后显示所有数据 */
function gridTwo() {
	param = null;
	grid();
}

/* 添加数据 */
function addRows() {

	$.ligerDialog
			.open({
				height : 300,
				width : 900,
				title : '产品添加',
				url : basePath + "rest/mauProductManageAction/productform",
				showMax : false,
				showMin : false,
				isResize : true,
				slide : false,
				name : 'repairAgain',
				buttons : [
						{
							text : '确认增加',
							onclick : function(item, dialog) {
								var pro_ggxh = dialog.frame.$("[name=pro_ggxh]").val();
								$.post(basePath+ "rest/mauProductManageAction/checkProCode",{
									procode : pro_ggxh}, function(data) {
										if (data.flag == "false") {
											alert(data.msg);
											return;
										}
								}, "json");
								var datas = dialog.frame.getDatas();
								var va = dialog.frame.valids();
								datas.inCore = datas.inCore+";"+datas.inCore2;
								if (!va) {
									$.ligerDialog.error("请将数据填写完整");
									return;
								}
								var data = JSON.stringify(datas);
								debugger;
								$
										.ajax({
											url : basePath
													+ "rest/mauProductManageAction/saveProductData",
											type : 'POST',
											data : "mauproduct=" + encodeURIComponent(encodeURIComponent(data)),
											success : function(map) {
												debugger;
												if(map.success){
													$.ligerDialog.success("增加成功");
													location.reload();
													dialog.close();
												}else{
													$.ligerDialog.success("增加失败");
												}
											},
											error : function() {
												$.ligerDialog.error("增加失败");
											}
										});
								grid();
							}

						}, {
							text : '取消',
							onclick : function(item, dialog) {
								dialog.close();
							}
						} ]

			});

}

// 产品类型列表
function productType() {
	debugger;
	$.ligerDialog.open({
		// dialog中添加form表单
		url : basePath + "rest/mauProductManageAction/toTypePage",
		height : 500,
		width : 650,
		modal : true,
		title : "产品类型",
		isResize : true,
		buttons : [ {
			text : '关闭',
			onclick : function(item, dialog) {
				dialog.close();
			}
		}

		]
	});

}
// 添加产品类型
function addProductType() {
	$.ligerDialog.open({
		target : $("#showKey"),
		title : '增加产品类型',
		width : 380,
		height : 120,
		isResize : true,
		modal : true,
		buttons : [ {
			text : '添加',
			onclick : function(i, d) {
				saveType(d);
			}
		}, {
			text : '关闭',
			onclick : function(i, d) {
				$("input").ligerHideTip();
				d.hide();
			}
		} ]
	});

}
/* 修改行数据 */
function updateRows() {
	var manager = $("#maingrid").ligerGetGridManager();
	var li = manager.getSelectedRows();
	//var dataArray = [];
	debugger;
	if (li.length > 1 || li.length < 1) {
		$.ligerDialog.error("请选择一行修改");
		return;
	}
	for (var i = 0; i < li.length; i++) {
		var bean = new Object();
		var beans = li[i];
		bean.pro_ggxh = beans.pro_ggxh;
		bean.pro_name = beans.pro_name;
		bean.pro_type = beans.pro_type;
		bean.route_code = beans.route_code;
		bean.pro_color = beans.pro_color;
		bean.core  = beans.core;
		bean.remark = beans.remark;
		bean.colorCore = beans.colorCore;
		bean.inCore = beans.inCore;
		bean.area = beans.area;
		bean.id = beans.id;
		var data = JSON.stringify(bean);
		var id = beans.id;
		$.ligerDialog
				.open({
					height : 300,
					width : 850,
					title : '产品修改',
					url : basePath
							+ "rest/mauProductManageAction/productform?data="
							+ encodeURIComponent(encodeURIComponent(data)) + "&id=" + bean.id,
					showMax : false,
					showMin : false,
					isResize : true,
					slide : false,
					name : 'repairAgain',
					buttons : [
							{
								text : '确认修改',
								onclick : function(item, dialog) {
									var datas = dialog.frame.getDatas();
									datas.id = id;
									var va = dialog.frame.valids();
									datas.inCore = datas.inCore+";"+datas.inCore2;
									if (!va) {
										$.ligerDialog.error("请将数据填写完整");
										return;
									}
									debugger;
									var data = JSON.stringify(datas);
									$
											.ajax({
												url : basePath
														+ "rest/mauProductManageAction/saveProductData",
												type : 'POST',
												data : "mauproduct=" + encodeURIComponent(encodeURIComponent(data)),
												success : function() {
													$.ligerDialog
															.success("修改成功");
													dialog.close();
												},
												error : function() {
													$.ligerDialog.error("修改失败");
													// dialog.close();
												}
											});
									grid();
								}

							}, {
								text : '取消',
								onclick : function(item, dialog) {
									dialog.close();
								}
							} ]

				});

	}

}

// 保存类型
function saveType(d) {
	var typeName = $("#txtKey").val();
	if (typeName == "" || typeName == "") {
		$.ligerDialog.error("请填写产品类型！");
		return;
	}
	$.ajax({
		url : basePath + 'rest/mauProductManageAction/saveProType',
		type : "post",
		dataType : 'json',
		data : "typeName=" + typeName,
		success : function(map) {
			// debugger;
			if (map.msg == "添加成功") {
				$.ligerDialog.success("添加成功");
				d.hide();
				grid();
			} else {
				$.ligerDialog.error("添加失败！【" + map.msg + "】");
			}
		},
		error : function() {
			$.ligerDialog.error("添加失败，发生未知错误！");
		}
	});
}

// 删除行
function deleteRows() {
	var manager = $("#maingrid").ligerGetGridManager();
	var li = manager.getSelectedRows();
	var dataArray = [];
	if (li.length < 1) {
		$.ligerDialog.error("请选择删除的行！");
		return;
	}
	for (var i = 0; i < li.length; i++) {
		var bean = new Object();
		var beans = li[i];
		bean.id = beans.id;
		dataArray.push(bean);
	}
	var data = JSON.stringify(dataArray);
	// debugger;
	$.ligerDialog
			.confirm(
					"温馨提示：是否确定删除？",
					function(yes) {
						if (yes) {
							$
									.ajax({
										url : basePath
												+ 'rest/mauProductManageAction/clearProductData',
										type : "post",
										dataType : 'json',
										data : "ids=" + data,
										success : function(map) {
											// debugger;
											if (map.success) {
												// alert(map.msg);
												$.ligerDialog.success("删除成功！");
												grid();
											} else {
												$.ligerDialog.error("删除失败!");
											}
										},
										error : function() {
											$.ligerDialog.error("删除失败，发生未知错误！");
											grid();
										}
									});
						} else {
							return;
						}
					});

}

/*
 * //添加一行数据 function addRows(){ var manager =
 * $("#maingrid").ligerGetGridManager(); var row = manager.getSelectedRow();
 * manager.addRow({ pro_code:'', pro_name:'', pro_type:'', // pro_color:'', //
 * pro_length:'', // unit:'m', // packing_type:'', // packing_mater:'',
 * route_code:'', remark:'' });
 * 
 * 
 *  }
 */

// 保存数据
function save() {
	var manager = $("#maingrid").ligerGetGridManager();
	var li = manager.getSelectedRows();
	var dataArray = [];
	if (li.length < 1) {
		$.ligerDialog.error("请选择保存的行！");
		return;
	}
	for (var i = 0; i < li.length; i++) {
		var bean = new Object();
		var beans = li[i];
		// debugger;
		bean.pro_code = beans.pro_code;
		bean.pro_name = beans.pro_name;
		bean.pro_type = beans.pro_type;
		// bean.pro_color = beans.pro_color;
		// bean.pro_length = beans.pro_length;
		// bean.unit = beans.unit;
		// bean.packing_type = beans.packing_type;
		// bean.packing_mater = beans.packing_mater;
		bean.route_code = beans.route_code;
		bean.remark = beans.remark;
		bean.id = beans.id;
		dataArray.push(bean);
	}
	debugger;
	var data = JSON.stringify(dataArray);
	// alert(data);
	$.ajax({
		url : basePath + 'rest/mauProductManageAction/saveProductData',
		type : "post",
		dataType : 'json',
		data : "li=" + data,
		success : function(map) {
			// debugger;
			if (map.success) {
				$.ligerDialog.success("保存成功");
				location.reload();
			} else {
				$.ligerDialog.error("保存失败");
			}
		},
		error : function() {
			$.ligerDialog.error("保存失败");
		}
	});

}
/*
 * function addForm(){ var basePath = '<%=basePath%>'; top.$.ligerDialog.open({
 * 
 * url: basePath+"rest/mauMachineSpeedManageAction/toAddDataPage", height: 600,
 * width: 1080, modal:true, title:"添加" });
 *  }
 */

/*
 * 
 * <div class="l-text-wrapper"><div class="l-text l-text-combobox"><input
 * type="text" id="test1" readonly="" data-comboboxid="" />
 * 
 * var dialog = null, combobox; $(function () { combobox =
 * $("#test1").ligerComboBox({ data: [ { text: '张三', id: '1' }, { text: '李四',
 * id: '2' }, { text: '赵武', id: '44' } ], emptyText: '（空）', //空行 addRowButton:
 * '新增', //新增按钮 addRowButtonClick: function () //新增事件 { combobox.clear(); dialog =
 * $.ligerDialog.open({ height: 300, width: 400, title: '打开窗口提示', url:
 * 'addNew.htm', showMax: false, showToggle: true, showMin: false, isResize:
 * true, slide: false, data: { callback: afterSave } }); } }); }); function
 * afterSave(data) { dialog.close(); combobox.addItem(data);
 * combobox.setValue(data.id, data.text); }
 * 
 */

