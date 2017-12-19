var form;
var datas;
var fieldList;
var formGgxh;
var list = [];

var CustomersData = {
	Rows : list
};
function initField() {
	var a = [ {
		display : "id",
		name : "id",
		newline : false,
		type : "hidden"
	}, {
		display : "产品工艺编码",
		name : "proCraftCode",
		newline : false,
		type : "hidden"
	},// 后台自动生成产品工艺编码
	{
		display : "创建人",
		name : "createBy",
		newline : false,
		type : "hidden"
	},// 创建人，目前自动生成
	{
		display : "创建日期",
		name : "createDate",
		newline : false,
		type : "hidden"
	},// 创建日期 TODO 以上是隐藏字段，正式上线要注释掉

	{
		display : "产品大类",
		name : "proBig",
		newline : true,
		type : "select",
		id : "proId",
		comboboxName : "proBig",
		options : {
			data : datas.proBig,
			onSelected : function(value, text) {
				selectProBig(value, text);
			}
		},
	},// 初始化加载产品大类

	{
		display : "产品名称",
		name : "proGgxh",
		newline : false,
		type : "select",
		id : "proGgxh",
		comboboxName : "proGgxh",
		options : {
			onSelected : function(value, text) {
				selectProName(value, text);
			}
		}
	},// 加载对应的产品名称,id就是产品规格

	{
		display : "工艺路线编码",
		name : "routeCode",
		newline : true,
		type : "select",
		id : "routeCode",
		comboboxName : "routeCode",
		options : {
			data : datas.routeCode,
			onSelected : function(value, text) {
				selectRouteCode(value, text);
			}
		}
	},// 这个也应该是产品名称选定之后，自动初始化该产品对应的产品工艺

	{
		display : "产品工艺名称",
		name : "proCraftName",
		newline : false,
		type : "text"
	},// 工艺名称，由客户手动录入数据

	{
		display : "集绞方式",
		name : "gatheringMode",
		newline : true,
		type : "text",
	// options:{onChangeValue:function(value){gatheringModeChange(value);}}
	}];
	return a;
}

$(function() {
	datas = loadSelect();
	fieldList = initField();

	form = $("#myForm").ligerForm({
		inputWidth : 200,
		labelWidth : 120,
		space : 100,
		// readonly :false,
		fields : fieldList,
		// buttons:[
		// {text:"temp",click:test}
		// ],
		onAfterSetFields : function() {
			// alert("safds");
		}

	});
	// 初始化编辑的对象的值
	if (id) {
		init(id);
	}
	// manager = $("#myForm").ligerGetGridManager();
	// manager.get("gatheringMode").setDisabled();
	$.ligerui.get("gatheringMode").set('disabled', true); // 设置该文本框不可编辑
	gatherGird();
});
/**
 * 集绞方式输入框，输入之后，弹出规格型号选择框，选择框个数，与输入的格式有关。
 * 
 * @param value
 */
function gatheringModeChange(value) {
	if (!value) {
		alert("请输入集绞格式");
	} else {
		if (value.indexOf("+") == -1) {// 不包含+
			alert("集绞方式至少包含一个加号");
		} else if (value.indexOf(" ") != -1) {
			alert("输入不能包含空格");
		} else {
			alert("处理集绞方式");
			var arr = value.split("+");
			alert(arr.length);
			// TODO 生成固定的ligerForm ,根据集绞工序的数量，加载表单框

			// loadGgxhForm(arr.length);
			return;
			// var html = "";
			// if(arr.length>0){
			// html = "<tr><td>原材料规格</td></tr>";
			// }
			// for(var i = 0;i < arr.length; i ++){//下标很重要，表示对应的规格型号
			// html += "<tr><td><input type=\"text\" name='"+i+"'
			// id='"+i+"'/></td></tr>";
			// }
			// $("#myTable").html(html);
		}
	}
}

function gatherGird() {
	window['g'] = $("#formGgxh").ligerGrid({
		title : '设置集绞方式',
		checkbox : false,
		width : '100%',
		height : '60%',
		columns : [ {
			display : '序号',
			name : 'indexid'
		}, {
			display : '规格型号',
			name : 'ggxh'
		}, {
			display : '颜色',
			name : 'color'
		}, {
			display : '数量',
			name : 'count'
		} ],
		toolbar : {
			items : [ {
				text : '增加',
				click : addgather,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改',
				click : updategather,
				icon : 'modify'
			}, {
				line : true
			}, {
				text : '删除',
				click : delgather,
				icon : 'delete'
			}, {
				line : true
			} ]
		},
		data : CustomersData,
		rownumbers : true,
		usePager : false
	});
}

function getMaxId() {
	var manger = $("#formGgxh").liger();
	var li = manger.getData();
	console.log(li);
	var maxId = 0;
	for (var i = 0; i < li.length; i++) {
		if (li[i].indexid > maxId) {
			maxId = li[i].indexid;
		}
	}
	maxId = maxId + 1;
	// alert("maxId="+maxId);
	return maxId;

}

function setGatherMode(index, count) {
	var curValue = $.ligerui.get("gatheringMode").getValue();
	if (index == 1) {
		$.ligerui.get("gatheringMode").setValue(count);
	} else {
		$.ligerui.get("gatheringMode").setValue(curValue + "+" + count);
	}

}

function addgather() {
	var p_code = $.ligerui.get("proGgxh").getValue();
	var indexid = getMaxId();
	ligerDialogOpen = $.ligerDialog.open({
		url : basePath
				+ "rest/craCraftProductManageAction/toSetGatherPage?&p_code="
				+ p_code,
		height : 300,
		width : 300,
		title : '集绞参数设置',
		buttons : [ {
			text : '保存',
			onclick : function(item, dialog) {
				var ggxh = dialog.frame.$("[name=ggxh]").val();
				var color = dialog.frame.$("[name=color]").val();
				var count = dialog.frame.$("[name=count]").val();

				if (ggxh == "" || color == "" || count == "") {
					$.ligerDialog.warn("请选择将数据填写完整");
					return;
				}
				var bean = new Object();
				bean.indexid = indexid;
				bean.ggxh = ggxh;
				bean.color = color;
				bean.count = count;
				list.push(bean);
				// debugger;
				gatherGird();
				setGatherMode(indexid, count);
				dialog.close();
			}

		}, {
			text : '取消',
			onclick : function(item, dialog) {
				dialog.close();
			}
		} ]

	});
}
function updategather() {
}
function delgather() {
}

/**
 * 加载规格型号明细
 * 
 * @param obj
 */
function loadGgxhForm(len) {
	var fields = [];
	for (var i = 0; i < len; i++) {
		var obj = new Object();
		obj.display = "规格型号" + i;
		obj.name = "" + i;
		obj.newline = false;
		obj.type = "text";
		fields.push(obj);
	}
	debugger;
	formGgxh = $("#formGgxh").ligerForm({
		inputWidth : 150,
		labelWidth : 120,
		space : 40,
		// readonly :false,
		fields : fields
	});
}

/**
 * 选择产品大类，初始化产品名称
 * 
 * @param value
 * @param key
 */
function selectProBig(value, key) {
	if (value && key) {
		$.get(basePath
				+ "rest/craCraftProductManageAction/getProGgxhFromProType", {
			proType : value
		}, function(data, textStatus) {
			var dataList = data.data;
			var combo = liger.get("proGgxh");
			combo.set('data', dataList);
		}, "json");
	}
}
/**
 * 选择产品名称，初始化工艺路线
 * 
 * @param value
 * @param text
 */
function selectProName(value, text) {
	// TODO ,根据产品规格型号，查找对应的工艺路线（一般情况只有一个，也可能有多少个）。
	if (value && text) {
		// alert("选择产品规格，初始化工艺路线"+value+"----->"+text);
		var combo = liger.get("routeCode");
		combo.set("data", [ {
			"id" : "id1",
			"text" : "route1"
		}, {
			"id" : "tesxt1",
			"text" : "route2"
		} ]);
		$.get(basePath + "rest/craCraftProductManageAction/getProCraftRoute", {
			proGgxh : text
		}, function(data, textStatus) {
			debugger;
			var dataList = data.data;
			var combo = liger.get("routeCode");
			combo.set('data', dataList);
		}, "json");
	}
}
/**
 * 选择工艺路线，决定集绞选择框，访问后台，判断是否具有集绞工序。 是否允许编辑
 */
function selectRouteCode(value, text) {
	debugger;
	if (value && text) {
		// alert("决定是否允许集绞文本框编辑"+value+"----->"+text);
		$.get(basePath
				+ "rest/craCraftProductManageAction/getSetGroudByYesOrNo", {
			routeCode : value
		}, function(data, textStatus) {
			debugger;
			// var dataList = data.data;
			// var combo = liger.get("routeCode");
			// combo.set('data', dataList);
			if (data.success) {// true，表示包含集绞工序
				// 放开集绞方式文本输入框
				$("#formGgxh").css("display", "block");
				$.ligerui.get("gatheringMode").set('disabled', true); // 设置该文本框不可编辑
			} else {
				// 没有集绞工序，什么都不做,设置文本框不可编辑
				$.ligerui.get("gatheringMode").set('disabled', true); // 设置该文本框不可编辑
			}

		}, "json");

	}

}

/**
 * 首次访问页面，初始化加载下拉框选择结构，本页刚进入页面的时候，只需要初始化产品大类和颜色，其他的，应该在选择下拉框之后设置。
 */
function loadSelect() {
	var datas = new Object();
	// data.proBig =
	// [{"id":"tesxt","text":"value1"},{"id":"tesxt1","text":"value2"}];
	$.ajax({
		url : basePath + "rest/craCraftProductManageAction/getSelectData1",
		dataType : 'json',
		data : "id=" + id,
		type : "post",
		async : false,
		success : function(data) {
			debugger;
			var dat = data.data;
			datas.proBig = dat.proBig;// 产品大类选择框
			datas.proColor = dat.proColor;// 产品颜色选择框
		}
	});
	return datas;
}