var grid;
var menu;
var form;
var dialog;
var panel;
var griddata;
var row=null;//设定初始值
var rows;
var array = [];
var Col = [{
	display : '序号',
	name : 'id',
	width : '3%',
	isSort :true
}, {
	display : '批次号',
	name : 'batchNumber',
	width : '6%',
},{
	display : 'RFID',
	name : 'rfid',
	width : '11%'
},{
	display : '材料类型',
	name : 'materialType',
	width : '6%'
}, {
	display : '规格型号',
	name : 'spec',
	width : '5%'
}, {
	display : '颜色',
	name : 'color',
	width : '5%',
}, {
	display : '数量',
	name : 'quantity',
	width : '5%',
},{
	display : '位置',
	name : 'location',
	width : '5%',
} ,{
	display : '操作员',
	name : 'employee',
	width : '6%',
} ,{
	display : '库存时间',
	name : 'storageTime',
	width : '10%',
},{
	display : '合格证书',
	name : 'certificate',
	width : '8%',
} ,{
	display : '检验报告',
	name : 'inspectionReport',
	width : '8%',
} ,{
	display : '送货信息',
	name : 'deliveryInfo',
	width : '10%',
} ,{
	display : '备注',
	name : 'remark',
	width : '12%',
} 
];
function InitialInputArea(){
	panel = $('#InputArea').ligerPanel({
		  title: '基础信息录入',
          width: '98.5%',
          height : 340,
          frameName:'mypanel',
          data:[],
          showRefresh:true,
          url :basePath+'html/Material.jsp'
	});
	panel.collapse();
}
function MenuHide() {
	menu1.hide();
}
function InitialCheck()
{
	return false;
}
function InitialQueryArea() {
	form = $('#queryform').ligerForm({
		inputWidth : 202,
		labelWidth : 70,
		space : 40,
		validate : true,
		fields : [ {
			name : "MaterialReturn",
			type : "hidden"
		}, {
			display : "创建人员",
			name : "createBy",
			newline : true,
			type : "text",
			validate : {
				required : true,
			}
		}, {
			display : "创建日期",
			name : "createDate",
			newline : false,
			type : "date",
			validate : {
				required : true,
			}
		}, {
			display : "单据编号",
			lablewidth : 120,
			name : "mmrCode",
			newline : false,
			type : "text",
			validate : {
				required : true,
			}
		}, {
			display : "单据类型",
			name : "docType",
			newline : false,
			type : "select",
			validate : {
				required : true,
			}
		} ]
	});
	// 
}

function CreateMenu() {
	menu = $.ligerMenu({
		top : 100,
		left : 100,
		width : 120,
		items : [ {
			text : '新增',
			click : Add,
			img : basePath + 'core/img/site_icon/add.gif'
		}, {
			text : '编辑',
			click : Edit,
			img : basePath + 'core/img/site_icon/edit.gif',
		},{
			text : '删除',
			click : Delete,
			img : basePath + 'core/img/site_icon/delete.gif',
		},{
			line : true
		}, {
			text : '查看',
			click : Query,
			img : basePath + 'core/img/site_icon/view.gif',
			children : [ {
				text : '报表',
				click : Add,
				img : basePath + 'core/img/site_icon/pager.gif'
			}, {
				text : '导出',
				img : basePath + 'core/img/site_icon/logout.gif',
				children : [ {
					text : 'Excel',
					click : Add
				}, {
					text : 'Word'
				} ]
			} ]
		}, {
			text : '关闭',
			click : MenuHide,
			img : basePath + 'core/img/site_icon/candle.gif'
		},
		{
			text : '刷新',
			click : function(){
				//setTimeout($('#'));
				grid.reload();
			},
			img : basePath + 'core/img/site_icon/refresh.gif'
		},
		{
			text : '重新加载',
			click : function(){
				//setTimeout($('#'));
				location.reload();
			},
			img : basePath + 'core/img/site_icon/back.gif'
		}]
	});
	$('#DataList').bind("contextmenu", function(e) {
		menu.show({
			top : e.pageY,
			left : e.pageX
		});
		return false;
	});

}
function OpenDialog(rowdata,newtitle){
	$.ligerDialog.open({
		height : 380,
		width : 700,
		title : newtitle,
		url : basePath + 'rest/mauMaterielRecordManageAction/toReturnRecordEditPage',
		showMax : false,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data:rowdata,
		onClosed:function(){
			grid.reload();
		}
		});
}
function Add(){
	panel.expand();
}
function Edit() 
{
	panel.expand();
}
// 删除信息
function Delete() {
	if(null!=rows)
	{
		for(var i=0;i<rows.length;i++)
		{
			var idCollection = {};
			idCollection.id=rows[i].id;		
			array[i]=idCollection;
		}
		$.ligerDialog.confirm("是否删除指定数据！","提示",function(type){
			if(type)
			{
				//发出删除请求R
				$.ajax({
					load:"删除数据中...",
					type:'POST',
					dataType:'json',
					url: basePath + "rest/" + routeName + "Action/deleteRecord",
					data:{"params":JSON.stringify(array)},
					async:true,  
				    cache:false, 
					success:function(data){
						grid.reload();
						$.ligerDialog.tip({
							title : '提示信息',
							content : '选定的数据已删除！'
						});
					},
					 error: function(json){  
						 $.ligerDialog.error('保存失败,发生了未知错误！');  
				    }  
				});
			}
			
		});
	}
	else
	{
		$.ligerDialog.question("请选择要删除的行！","警告");
	}
}
function Reload()
{
	location.reload();
}
$(function() {
	InitialInputArea();
	InitialQueryArea();
	// 加载查询区域
	CreateMenu();
	grid = $("#DataList").ligerGrid({
		width : '98.5%',
		height : '95.5%',
		method:'post',
		url : basePath + "rest/" + routeName + "Action/queryForm",
		checkbox : true,
		columns : Col,
		usePager : true,
		async:true,
		sortName:'id',
		pageSize : 8,
		pageSizeOptions:[8,16,32],//可指定每页页面大小
		isChecked:InitialCheck,
		toolbar : {
			items : [ {
				text : '新增',
				click: Add,
				img : basePath + 'core/img/site_icon/add.gif'
			}, {
				text : '编辑',
				click:function(){
					var rows = grid.getSelecteds();
					if(rows.length>1)
					{
						$.ligerDialog.confirm("只能编辑一行数据，请重新选择！","提示",function(type){
							debugger;
							if(type)
							{
								grid.reload();
							}
						}); 
					}
					else if(rows.length==0)
					{
						$.ligerDialog.question("请选择编辑行！","警告");
					}
					else
					{
						OpenDialog(row,'退料记录-编辑');
					}
					
				},
				img : basePath + 'core/img/site_icon/edit.gif',
			}, {
				text : '删除',
				click : Delete,
				img : basePath + 'core/img/site_icon/delete.gif',
			}, {
				text : '查询',
				click : Query,
				img : basePath + 'core/img/site_icon/search.gif'
			}, {
				text : '重置',
				click : Reload,
				img : basePath + 'core/img/site_icon/back.gif'
			} ]
		},
		onSuccess : function(data) {
			//alert(JSON.stringify(data));
		},
		onSelectRow :function(rowdata,rowid,rowobj){
			row = rowdata;
			rows = grid.getSelecteds();
		},
		onDblClickRow : function(rowdata, rowindex, rowobj) {
		},
	});
});
function Query(){
	if (form.valid())// 校验通过
	{
		$.ajax({
					load : "正在加载中...",
					type : "post",
					dataType:'json',
					data:$('#queryform').serialize(),
					url : basePath + 'rest/mauMaterielRecordManageAction/queryRecordList',
					success : function(result) {
						  if (result.Rows.length!=0) {
							  $.ligerDialog.success(JSON.stringify(result));
							  grid.loadData(result);
						  }
						  else
						  {
							  $.ligerDialog.warn("查询结果为空，请重新选择查询条件！");
						  }		  
					},
					error:function(){
						$.ligerDialog.error("error");
					}
				});
	}
}