var tbdArray = new Array();
var grid;
$(function ()
    {	
		//pub_initList(routeName);
		grid=$("#sellContractPlanBatchManageList").ligerGrid({
			url : basePath + "rest/"+ routeName + "Action/dataGridPage?1=1&scCode="+scCode,
	        checkbox: true,
	        columns: [
	            { display: 'iD', name: 'id',  width: 1,hide:true },
	            { display: '合同编号', name: 'scCode',  width: 120 },
	            { display: '产品名称', name: 'proName',  width: 1,hide:true},
	            { display: '产品类型', name: 'proType',  width: 150 },
	            { display: '产品编码', name: 'proCode',  width: 1,hide:true },
	            { display: '产品颜色', name: 'proColor',  width: 120 },
	            { display: '产品工艺编码', name: 'proCraftCode',  width: 150,hide:false},
	            { display: '产品工艺名称', name: 'proCraftName',  width: 1,hide:true},
	            { display: '产品规格型号', name: 'proGgxh',  width: 150 },
	            { display: '状态', name: 'scPlanbatState',  width: 120 },
	            { display: '批次编码', name: 'batCode',  width: 120 },
	            { display: '交货时间', name: 'deliveDate',  width: 120 },
	            { display: '要求段长', name: 'reqPeriodLength',  width: 120 },
	            { display: '生产段长', name: 'proPeriodLength',  width: 120 },
	            { display: '轴数', name: 'reqAmount',  width: 120 },
	            { display: '总长度', name: 'totalPartLen',  width: 120 },
	            { display: '单位', name: 'reqUnit',  width: 120 },
	            { display: '创建人', name: 'createBy',  width: 120 },
	            { display: '创建时间', name: 'createDate',  width: 120 },
		        {
                    display: '操作', isAllowHide: false,  width:120,frozen:true,
                    render: function (row)
                    {
                    	debugger;
                    	var html = '<a href="void(0)" onClick=\"javascript:toDetailList(\'' + row.id + '\');return false;\">生成工单</a>&nbsp;&nbsp;&nbsp;';
                    	html += '<a href="void(0)" onclick="javascript:singleMove(\'' +row.id+'\');return false;">挪单</a>';
                        return html;
                    }
                }
	        ], 
	        pageSize:10,
	        isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow,
	        width: '100%',height:'97%',
	        onSuccess:function (json, grid){
	        	$("#pageloading").hide(); 
	        },
			onError:function (json, grid){
	        	$("#pageloading").hide(); 
	        }
	    });
		$("#add").click(function(){
			top.$.ligerDialog.open({ 
				url: basePath+"rest/sellContractPlanBatchManageAction/toAddEditPage", 
				height: 600,
				width: 1080,
				modal:true,
				title:"添加"
				/*buttons: [ { text: '确定', onclick: function (item, dialog) { 
						alert(item.text); 
					} 
				}, 
			                                                                                   
				{text: '取消', onclick: function (item, dialog) { 
					dialog.close(); 
				} } 
				]*/ });
		});
    }
);

//挪单
function singleMove(id){
	// TODO 跳转到挪单页面batCode,mainId,proGgxh
	//alert(proGgxh);
	debugger;
    var url = basePath+"rest/plaSingleMoveManageAction/toTablePage?id="+id;
	   var w = window.screen.width * 1;
	   var h = window.screen.height * 0.9;
	   window.open(url, "挪单",'height='+h+', width='+w);
}


//打开明细列表，下发
function toDetailList(batId){
	var url = basePath+"rest/sellContractDetailManageAction/toListPage?planBatchId="+batId+"&p="+Math.random();
	//alert(url);
	/*top.$.ligerDialog.open({
		url: url, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"编辑"});*/
	var w = window.screen.width * 0.8;
	var h = window.screen.height * 0.5;
	var p = 'width='+w+',height='+h;
	window.open(url,"生产通知单下发",p);
}

function edit(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sellContractPlanBatchManageAction/toAddEditPage?id="+id, 
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

function show(id){
	top.$.ligerDialog.open({ 
		url: basePath+"rest/sellContractPlanBatchManageAction/toDetailPage?id="+id, 
		height: 600,
		width: 1080,
		modal:true, 
		title:"详细信息"
		/*buttons: [
		{text: '取消', onclick: function (item, dialog) { 
			dialog.close(); 
		} } 
		]*/ });
} 

function delrow(routeName){
	pub_del(routeName);
}