var ligerDialogOpen;
var grid;
$(function(){
	loadGrid();
	seForm();
});

function loadGrid(){
	var param = initParam();
	grid = $("#myGrid").ligerGrid({
		title:"订单列表",
		url : basePath + "rest/sellSalesOrderManageAction/toListPageData?param="+param,
        checkbox: true,
        allowAdjustColWidth :true,
        delayLoad:false,
        alternatingRow:true,
        rownumbers:true,
        rownumbersColWidth:60,
        columns: [
            { display: 'id', name: 'id',width:1,  hide:true},
            { display: '订单编号', name: 'orderCode'},
            { display: '合同编号', name: 'contractCode'},
            { display: '交货日期', name: 'deliveryDate'},
            { display: '销售部经办人', name: 'salesManager'},
            { display: '订单录入人员', name: 'orderEntryClerk'},
            { display: '是否分解', name: 'genFlag'},
            { display: '创建人', name: 'createBy'},
            { display: '创建时间', name: 'createDate'},
            {
			    display: '操作',
			    render: function (row)
			    {
			    	var html="";
			    	if(row.genFlag == "已分解"){
			    		html += "<a href=\"javascript:void(0)\" onclick=\"detail('"+row.orderCode+"')\"> 查看详情</a>";
			    	}else {
			    		html += "<a href=\"javascript:void(0)\" onclick=\"edit('"+row.orderCode+"')\"> 编辑</a>";
			    		html += " || ";
				    	html += "<a href=\"javascript:void(0)\" onclick=\"deleteBean('"+row.orderCode+"')\"> 删除</a>";
			    	}
			    	
			    	html += " || ";
			    	html += "<a href=\"javascript:void(0)\" onclick=\"getCompletionRate('"+row.orderCode+"')\"> 完成率</a>";
			    	
		    		;
			        return html;
			    }
			}
        ],
        width:'99%',
        height:'60%',
        onSelectRow:function (rowdata, rowid, rowobj){
        	debugger;
//        	$.ligerDialog.alert("rowdata:"+rowdata+";rowid:"+rowid+";rowobj"+rowobj);
        	loadSonGrid(rowdata, rowid, rowobj);
        },
        toolbar : {
			items : [ 
	          {text:"新增订单明细",click:insert,icon:"add"},
	          {text:"分解为生产通知单",click:decomposeAdviceNote,icon:"add"},
	          {text:"分解为工单",click:decomposePlaCourse,icon:"add"}
	          
			]
		}
	});
}

/**
 * 分解成工单
 */
function decomposePlaCourse(){
	debugger;
	var orders = grid.getSelectedRows() ;
	var datas = [];
	if(orders.length==0){
		$.ligerDialog.alert("请选择要分解的订单");
		return;
	}
	for(var i =0;i < orders.length;i++){
		if (orders[i].genFlag == "已分解") {
			$.ligerDialog.warn("已分解的订单不能再次分解");
			return;
		}
		datas[i] = orders[i].orderCode;
	}
	$.ligerDialog.confirm("是否确认分解", "确认信息", function(boo){
		/*if(boo){//表示需要使用库存
			datas.push("lock=true");
		}*/
		if(!boo){
			return;
		}
		var dd = JSON.stringify(datas);
		$.post(basePath+"rest/sellSalesOrderManageAction/decomposePlaCourse",{param:dd},function(data){
			if(data.success){
				$.ligerDialog.success("分解成功", "提示内容", function(){});
				grid.reload() ;
				ligerDialogOpen.close();
			}else{
				$.ligerDialog.success("分解失败", "提示内容", function(){});
				ligerDialogOpen.close();
			}
		},"json");
	});
}


/**
 * 根据订单编号，查看
 * @param orderCode
 */
function getCompletionRate(orderCode){
	//alert(orderCode);
	$.ajax({
		url: basePath+"rest/sellSalesOrderManageAction/getCompletionRate",
		dataType: 'json',
		data:"orderCode="+orderCode,
		type: "post",
//		contentType:"application/json",
		success:function(data){
			if(data.success){
				$.ligerDialog.success(data.data, "提示内容", function(){});
				grid.reload() ;
				//ligerDialogOpen.close();
			}else{
				$.ligerDialog.success("获取失败", "提示内容", function(){});
				//ligerDialogOpen.close();
			}
		}
	});
}

/**
 * 分解为生产通知单
 * 可以传入一条或多条订单，分解为相应的生产通知单
 */
function decomposeAdviceNote(){
	var orders = grid.getSelectedRows() ;
	var datas = [];
	if(orders.length==0){
		$.ligerDialog.alert("请选择要分解的订单");
		return;
	}
	for(var i =0;i < orders.length;i++){
		$.ligerDialog.alert(orders[i].orderCode);
		datas.push(orders[i].orderCode);
	}
	
	debugger;
	$.ligerDialog.confirm("是否选择系统自由库存，锁定为备货", "确认信息", function(boo){
		if(boo){//表示需要使用库存
			datas.push("lock=true");
		}
		$.ajax({
			url: basePath+"rest/sellSalesOrderManageAction/decomposeAdviceNote",
			dataType: 'json',
			data:JSON.stringify(datas),
			type: "post",
			contentType:"application/json",
			success:function(data){
				if(data.success){
					$.ligerDialog.success("分解成功", "提示内容", function(){});
					grid.reload() ;
					ligerDialogOpen.close();
				}else{
					$.ligerDialog.success("分解失败", "提示内容", function(){});
					ligerDialogOpen.close();
				}
			}
		});
	});
	
}

/**
 * 去详情页面
 * @param orderCode 订单号
 * @param flag 是否分解  true已分解,false 未分解
 */
function detail(orderCode){
	//alert(orderCode);
	ligerDialogOpen = $.ligerDialog.open({
		title:"订单详情记录",
		url:basePath+"rest/sellSalesOrderManageAction/toDetailRecord?orderCode="+orderCode,
		type:'question' ,
		width :1000,
		height:500,
		buttons :[
		         {
		        	 text:"确定",onclick:function(){
		        		 ligerDialogOpen.close();
		        	 }
		         }
		]
	});
}

/**
 * 去修改页面
 * @param orderCode
 */
function edit(orderCode){
	ligerDialogOpen = $.ligerDialog.open({
		title:"修改订单记录",
		url:basePath+"rest/sellSalesOrderManageAction/toEditRecord?orderCode="+orderCode,
		type:'question' ,
		width :1000,
		height:500,
		buttons :[
		          {
			        	 text:"增加明细",onclick:function(i,d){
			        		 d.frame.addSonData();
			        	 }
			          },
			          {
			        	  text:"确定修改明细",onclick:function(i,d){
				        		 d.frame.editSonData();
				        	 }
			          },
			         { 
			        	 text: '保存', onclick: function(i,d){save(i,d);}
			         },
			         {
			        	 text:"取消",onclick:function(){
			        		 ligerDialogOpen.close();
			        	 }
			         }
		]
	});
}
/**
 * 新增订单,包括订单明细内容
 */
function insert(){
	ligerDialogOpen = $.ligerDialog.open({
		title:"新增订单记录",
		url:basePath+"rest/sellSalesOrderManageAction/insertRecord",
		type:'question' ,
		width :1000,
		height:500,
		buttons :[
		          {
		        	 text:"增加明细",onclick:function(i,d){
//		        		 alert("i-->"+i+";d-->"+d+d.frame.tempString);
		        		 debugger;
		        		 d.frame.addSonData();
		        	 }
		          },
		         { 
		        	 text: '保存', onclick: function(i,d){saveOrUpdate(i,d);}
		         },
		         {
		        	 text:"取消",onclick:function(){
		        		 ligerDialogOpen.close();
		        	 }
		         }
		]
	});
}
/**
 * 保存
 */
function save(i,d){
	debugger;
	var data = d.frame.getAllSaveDatas();
	
	if(d.frame.checkOrderCode(d.frame.getOrderCode())){//判断订单编号是否存在,
		return;
	}
	
//	return ;
	$.ajax({
		url: basePath+"rest/sellSalesOrderManageAction/saveSellSalesOrderData",
		dataType: 'json',
		data:"mainData="+JSON.stringify(data[0])+"&sonDatas="+JSON.stringify(data[1]) ,
		type: "post",
//		contentType:"application/json",
		success:function(data){
			if(data.success){
				$.ligerDialog.success("保存成功", "提示内容", function(){});
				grid.reload() ;
				ligerDialogOpen.close();
			}else{
				$.ligerDialog.success("保存失败", "提示内容", function(){});
				ligerDialogOpen.close();
			}
		}
	});
	
}

function saveOrUpdate(i,d){
	debugger;
	var data = d.frame.getAllSaveDatas();
	
//	return ;
	$.ajax({
		url: basePath+"rest/sellSalesOrderManageAction/saveOrUpdateBean",
		dataType: 'json',
		data:"mainData="+JSON.stringify(data[0])+"&sonDatas="+JSON.stringify(data[1]) ,
		type: "post",
//		contentType:"application/json",
		success:function(data){
			if(data.success){
				$.ligerDialog.success("保存成功", "提示内容", function(){});
				grid.reload() ;
				ligerDialogOpen.close();
			}else{
				$.ligerDialog.success("保存失败", "提示内容", function(){});
				ligerDialogOpen.close();
			}
		}
	});
	
}
/**
 * 根据订单id或者订单编号，查询订单明细
 * @param rowdata
 * @param rowid
 * @param rowobj
 */
function loadSonGrid(rowdata, rowid, rowobj){
	var data = new Object();
	data.orderCode = rowdata.orderCode;
//	alert("dataGrid");
	$("#sonGrid").ligerGrid({
		title:"订单明细",
		url : basePath + "rest/sellSalesOrderDetailsManageAction/getListPageData",
        checkbox: true,
        allowAdjustColWidth :true,
        delayLoad:false,
        alternatingRow:true,
        rownumbers:true,
        rownumbersColWidth:60,
        parms :data,
        width:'99%',
        height:'40%',
        columns: [
            { display: 'id', name: 'id',width:1,  hide:true},
            { display: '订单编号', name: 'orderCode'},
            { display: '交货日期', name: 'deliveryDate'},
            { display: '产品规格型号', name: 'proGgxh'},
            { display: '每轴长度', name: 'als'},
            { display: '轴数', name: 'axisNumber'},
            { display: '颜色', name: 'proColor'},
            { display: '创建人', name: 'createBy'},
            { display: '创建时间', name: 'createTime'},
            { display: '单位', name: 'unit'},
            { display: '总长度', name: 'totalLength'}/*,
            {
			    display: '操作',
			    render: function (row)
			    {
			    	var html = '';
		    		html += '<a href="#" onclick="javascript:edit(' + row.id + ',\''+row.idNumber+'\');return false;">编辑</a>&nbsp;&nbsp;';
			        return html;
			    }
			}*/
        ]
        
	});

}


function deleteBean(orderCode){
	$.ligerDialog.confirm("确定删除?","确认信息",function(flag){
		if(!flag){
			return;
		}
		$.post(basePath+"rest/sellSalesOrderManageAction/clearBean",{orderCode:orderCode},function(data){
			if(data.success){
				$.ligerDialog.success(data.msg);
				loadGrid();
			}else {
				$.ligerDialog.error(data.msg);
			}
		},"json");
		
	});
}

var seForm;
function initParam(){
	var data = seForm.getData();
	return JSON.stringify(data);
}
var flagData = [{id:"已分解",text:"已分解"},{id:"未分解",text:"未分解"}];
function seForm(){
	alert(11);
	seForm = $("#form2").ligerForm({
        inputWidth: 150, labelWidth: 90, space:10,
        fields: [
        	{ display: "是否已分解", name: "genFlag",  newline:true,type: "select" ,options: { data: flagData}},
        	{ display: "合同编号", name: "contractCode", newline:false, type: "text"},
        	{ display: "订单编号", name: "orderCode", newline:false, type: "text"},
        	{ display: "交货日期:从", name: "start",  newline:false,type: "date" },
        	{ display: "到", name: "end",  newline:false,type: "date" },
        ],	
    });
	//liger.get('scCode').setDisabled(); //设置只读
}
