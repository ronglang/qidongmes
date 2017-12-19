//var sonDataList = [];// 提交到后台的订单明细记录

var color;

var tempData = {
	Rows : [ {
		"contractCode" : "合同编号1"
	}, {
		"contractCode" : "合同编号2"
	} ],
	Total : 91
};

var tempData1 = {
	Rows : [ {
		"proGgxh" : "规格型号1"
	}, {
		"proGgxh" : "规格型号2"
	} ],
	Total : 100
};
var sonGrid;// 订单明细列表Grid对象
var sonForm;// 订单明细ligerForm对象
var mainForm;
$(function() {
	loadMainForm();// 加载主表grid

});
/**
 * 初始化规格型号
 */
function initProGgxh() {

	$.ajax({
		url : basePath + "rest/sellSalesOrderManageAction/selectProGgxh",
		dataType : 'json',
		data : "",
		type : "post",
		async : false,
		// contentType:"application/json",
		success : function(data) {
			if (data.success) {
				////debugger;
				// $.ligerDialog.success("保存成功", "提示内容", function(){});
				tempData1 = data.data;
			} else {
				// $.ligerDialog.success("保存失败", "提示内容", function(){});
			}
		}
	});
}
/**
 * 点击增加明细，添加明细记录
 */
function addSonData() {
	if (!sonGrid) {

		loadSonGrid();// 加载订单明细Grid
		// 初始化规格型号
		initProGgxh();
		loadSonForm();// 加载订单明细输入框

	} else {
		////debugger;
		addRowData();
	}
}
/**
 * 点击增加明细，添加一行记录
 */
function addRowData() {
	/*
	 * var va = sonForm.valid(); alert(va); if(va){
	 * $.ligerDialog.warn("请将数据填写完整"); return; }
	 */
	var axis = $("[name=axisNumber]").val();
	var color = $("[name=proColor]").val();
	if (axis == null || axis == "" || color == null || color == "") {
		////debugger;
		$.ligerDialog.warn("请将数据填写完整");
		return;
	}
	var axises = axis.split(";");
	var colors = color.split(";");
	if (axises.length != colors.length) {
		$.ligerDialog.warn("轴数与颜色需一一对应!并用';'隔开");
		return;
	}
	var sonDataTemp = sonForm.getData();
	// sonDataList.push(sonDataTemp);// 加入自定义缓存

	var oldData = sonGrid.get("data").Rows;
	// TODO 将日期类型转换成字符串类型
	////debugger;
	sonDataTemp.deliveryDate = parseDateToString(sonDataTemp.deliveryDate);
	sonDataTemp.orderCode = mainForm.getData().orderCode;
	// 计算总长度
	for(var i=0;i<axises.length;i++){
		if(i>0){
			sonDataTemp.totalLength = parseInt(axises[i] * sonDataTemp.als) + parseInt(sonDataTemp.totalLength) ;
		}else{
			sonDataTemp.totalLength = parseInt(axises[i] * sonDataTemp.als);
		}
	}
	oldData.push(sonDataTemp);
	sonGrid.reload();
	// sonForm.setData({"deliveryDate":null,proGgxh:null,als:null,axisNumber:null,proColor:null});
	// $("#sonForm").reset();
	// loadSonForm();
}
/**
 * 加载Grid
 */
function loadSonGrid() {
	sonGrid = $("#myGrid").ligerGrid({
		title : "订单明细列表",
		checkbox : true,
		allowAdjustColWidth : true,
		delayLoad : false,
		alternatingRow : false,
		// rownumbers:true,
		rownumbersColWidth : 60,
		height : 300,
		usePager : false,
		columns : [
		// { dispaly:"id",name:"id",type:"hidden"},
		{
			display : '订单编号',
			name : 'orderCode'
		}/*, {
			display : '交货日期',
			name : 'deliveryDate'
		}*/, {
			display : '产品规格型号',
			name : 'proGgxh'
		}, {
			display : '每轴长度',
			name : 'als'
		}/*, {
			display : '轴数',
			name : 'axisNumber'
		}*/, {
			display : '颜色',
			name : 'proColor'
		}, {
			display : '单位',
			name : 'unit'
		}, {
			display : '总长度',
			name : 'totalLength'
		} ],
		data : {
			Rows : []
		},
		toolbar : {
			items : [ {
				text : "删除",
				click : delBean,
				icon : "add"
			} ]
		}
	});
}
/**
 * 
 * 删除选中行
 */
function delBean() {
	var data = sonGrid.get("data").Rows;
	////debugger;
	var selectRowsBeans = sonGrid.getSelectedRows();
	for (var i = 0; i < selectRowsBeans.length; i++) {
		alert("下标：" + selectRowsBeans[i].__index);
		if (i == 0) {
			data.splice(selectRowsBeans[i].__index, 1);
		} else {
			data.splice(selectRowsBeans[i].__index - 1, 1);
		}
	}
	// alert(sonDataList.length);
	// ////debugger;
	// oldData = sonDataList;
	sonGrid.reload();
}

/**
 * 初始化订单主表页面
 */
function loadMainForm() {
	mainForm = $("#mainForm").ligerForm({
		inputWidth : 170,
		labelWidth : 90,
		space : 40,
		fields : [ {
			display : "订单编号",
			name : "orderCode",
			type : "text",
			newline : false,
			editor : {
				onChangeValue : function(value) {
					checkOrderCode(value);
				}
			}
		}, {
			display : '交货日期',
			name : 'deliveryDate',
			type : "date",
			newline : false,
			editor:{
				showTime:true,
				format:'yyyy-MM-dd'
			}
		}, {
			display : '销售部经办人',
			name : 'salesManager',
			type : "text",
			newline : false
		}, {
			display : '订单录入人员',
			name : 'orderEntryClerk',
			type : "text",
			newline : false
		}, {
			display : '合同编号',
			name : 'contractCode',
			type : "text",
			newline : false
			
		},{
			display : '订单类型',
			name : 'orderType',
			type : "select",
			newline : false,
			comboboxName: "orderType", options: { data: orderTypes},
			editor:{
				onSelected:function(value){
					if(value== "正常开单"){
						priLevels = [
							{ id: '5', text: '5' },
						];
					}else{
						priLevels = [
					                 { id: '1',text: '1' },
					                 { id: '2', text: '2' },
					                 { id: '3', text: '3' },
					                 { id: '4', text: '4' },
					                 { id: '5', text: '5' },
					                 ];
					}
					var priLevel = liger.get('priLevel');
					 priLevel.set('data',priLevels);
				}
			}
		},{
			display : '优先级',
			name : 'priLevel',
			type : "select",
			newline : false,
			comboboxName: "priLevel"/*, options: { data: priLevels}*/,
			 editor: { type: 'select',valueField:'id', textField:'text'}
		} ]
	});
}

var orderTypes = [
	                 { id: '正常开单',text: '正常开单' },
	                 { id: '插单', text: '插单' },
	                 ];
var priLevels = [
	                 { id: '1',text: '1' },
	                 { id: '2', text: '2' },
	                 { id: '3', text: '3' },
	                 { id: '4', text: '4' },
	                 { id: '5', text: '5' },
	                 ];
/**
 * 获取订单编号
 * 
 * @returns
 */
function getOrderCode() {
	return mainForm.getData().orderCode;
}
/**
 * 校验订单编号是否存在 true,表示存在，false表示不存在
 * 
 * @param value
 */
function checkOrderCode(value) {
	var boo = true;
	$.ajax({
		url : basePath + "rest/sellSalesOrderManageAction/checkOrderCode",
		dataType : 'json',
		data : "orderCode=" + value,
		type : "post",
		async : false,
		// contentType:"application/json",
		success : function(data) {
			if (data.success) {// 编号已经存在

			} else {// 编号不存在
				boo = false;
			}
		}
	});
	////debugger;
	if (boo) {
		$.ligerDialog.alert("订单编号已经存在，请重新输入");
	}
	return boo;
}
/**
 * 初始化订单从表页面
 */
function loadSonForm() {

	sonForm = $("#sonForm")
			.ligerForm(
					{
						inputWidth : 170,
						labelWidth : 90,
						space : 40,
						fields : [
								{
									display : '产品规格型号',
									name : 'proGgxh',
									newline : false,
									textField : "proGgxh",
									type : "popup",
									validate : {
										required : true,
										minlength : 2
									},
									editor : {
										selectBoxWidth : 600,
										selectBoxHeight : 300,
										textField : 'proGgxh',
										valeuField : 'proGgxh',
										condition : {
											fields : [ {
												label : '产品规格型号',
												name : 'proGgxh',
												type : 'text'
											} ]
										},
										grid : {
											columns : [ 
											   {
												display : '产品规格型号',
												name : 'proGgxh',
												align : 'left',
												width : 300,
												minWidth : 33
											   	},
											],
											sortName:'proGgxh',
											usePager : false,
											data : tempData1
										},
									}

								},
								{
									display : '每轴长度',
									name : 'als',
									type : "text",
									validate : {
										required : true,
										minlength : 2
									},
									newline : false
								// ,
								// options:{onChangeValue:function(value){calcTotalLength();}}
								},
								{
									display : '轴数',
									name : 'axisNumber',
									type : "text",
									validate : {
										required : true,
										minlength : 2
									},
									newline : false
								// ,
								// options:{onChangeValue:function(value){calcTotalLength();}}

								},
								{
									display : '颜色',
									name : 'proColor',
									type : "select",
									validate : {
										required : true,
										minlength : 2
									},
									newline : false,
									comboboxName : 'proColor',
									editor : {
										url : basePath
												+ 'rest/sellSalesOrderManageAction/findProColor',
										valueField : 'text',
										//isMultiSelect : true,
										//isShowCheckBox : true
									}

								},
								// { display: '创建人', name:
								// 'createBy',type:"text",newline: false},
								// { display: '创建时间', name:
								// 'createTime',newline: false},
								{
									display : '总长度',
									name : 'totalLength',
									type : "hidden",
									newline : false
								} ],
						validate : true
					});
	// $.ligerui.get("totalLength").set("disabled", true) ;
}
// function calcTotalLength(){
// ////debugger;
// var sonData = sonForm.getData();
// var totalLength = sonData.als * sonData.axisNumber;
// sonData.totalLength = 10000;
// sonForm.setData(sonData);
// alert("计算"+totalLength);
// }

/**
 * 将日期类型转换成字符串格式
 * 
 * @param date
 */
function parseDateToString(date) {
	////debugger;
	if (!date) {// 传入日期类型为空

	} else {// 不为空
		if (date instanceof Date) {// 匹配为日期类型
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			return year + "-" + (month < 10 ? "0" + month : month) + "-"
					+ (day < 10 ? "0" + day : day);
		} else {
		}
	}
	return date;
}

/**
 * 获取表头数据集
 */
function getMainBean() {
	// mainData = liger.get("mainForm");
	var mainData = mainForm.getData();
	mainData.orderType = $("[name=orderType]").val();
	mainData.deliveryDate = $("[name=deliveryDate]").val();
	////debugger;
	return mainData;
}
/**
 * 获取明细数据集
 */
function getSonBeans() {
	// sonData = sonForm.getData();
	return sonGrid.getData();
}

function getAllSaveDatas() {
	//debugger;
	var data = [];
	var mainData = getMainBean();
	var sonDatas = getSonBeans();
	// 转化日期格式
	mainData.deliveryDate = parseDateToString(mainData.deliveryDate);

	for (var i = 0; i < sonDatas.length; i++) {
		// alert("转换前的从表日期"+sonDatas[i].deliveryDate);
		sonDatas[i].deliveryDate = parseDateToString(sonDatas[i].deliveryDate);
		// alert("转换后的从表日期"+sonDatas[i].deliveryDate);
	}

	data.push(mainData);
	data.push(sonDatas);
	return data;
}
/**
 * 封装合同编号选择框数据
 */
function selectContractCode() {

}
