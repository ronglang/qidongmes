var grid;
var queryform;
var panel;
var querydata;
function getColumns()
{
	var Columns=[{
		display:'序号',
		name:'id',
		width:'5%',
		isSort :true
	},{
		display:'参数名称',
		name:'paraName',
		width:'20%',
	},{
		display:'参数类型',
		name:'paraType',
		width:'15%',
	},{
		display:'参数来源',
		name:'paraFrom',
		width:'10%',
	},{
		display:'参数标准',
		name:'isForeign',
		width:'13%',
	},{
		display:'最小值',
		name:'paraValueMin',
		width:'13%',
	},{
		display:'最大值',
		name:'paraValueMax',
		width:'12%',
	},{
		display:'单位',
		name:'unit',
		width:'12%',
	}];
	return Columns;
}
function createGrid(){
	grid = $('#grid').ligerGrid(
			{
				width : '97%',
				height : '96.8%',
				method:'post',
				url: basePath + "rest/storeSpecManageAction/queryForm",
				checkbox : true,
				columns : getColumns(),
				usePager : true,
				async:true,
				pageSize : 15,
				pageSizeOptions:[15,30,45],//可指定每页页面大小
				//isChecked:InitialCheck,
				toolbar : {
					items : [ {
						text : '新增',
						click: Add,
						img : basePath + 'core/img/site_icon/add.gif'
					}, {
						text : '编辑',
						click:Edit,
						img : basePath + 'core/img/site_icon/edit.gif',
					}, {
						text : '删除',
						click : Delete,
						img : basePath + 'core/img/site_icon/delete.gif',
					}, {line:true},{line:true},{
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
				},
				onSelectRow :function(rowdata,rowid,rowobj){
					rowdata = grid.getSelected();
				}
			});
} 
function createQueryForm(){
	queryform = $('#queryform').ligerForm({
		validate: true,
		inputWidth : 202,
		labelWidth : 70,
		space : 40,
		fields : [ {
			name : "StoreSpec",
			type : "hidden"
		}, 
		{
			display : "参数名称",
			name : "paraName",
			newline : true,
			type : "text",
		}, 
		{
			display : "参数类型",
			name : "paraType",
			newline : false,
			type : "text",
		}, 
		{
			display : "参数来源",
			name : "paraFrom",
			newline : false,
			type : "combobox",
			comboboxName: "queryparaFrom",
			options: {data:[{text:'机台参数'},{text:'规格参数'}]},
		}, 
		{
			display : "参数标准",
			name : "isForeign",
			newline : false,
			type : "combobox",
			comboboxName: "paraStandard",
			options: {data:[{text:'国内'},{text:'国外'}]},
		}]
	});
	querydata = queryform.getData();
}
function createInputForm(){
	panel = $('#InputArea').ligerPanel({
		  title: '规格参数-基础信息录入',
        width: '97%',
        height : 220,
        data:[],
        frameName:'mypanel',
        url :basePath+'html/storeSpecForm.html',
	});
	panel.collapse();
}
//CRUD基本操作
function Add()
{
	//Panel下拉
	panel.expand();
}
function Edit()
{
	//panel下拉，并初始化数据
	var rows = grid.getSelecteds();
	if(rows.length<=0)
	{
		$.ligerDialog.question("请选择编辑行！","警告");
	}
	else if(rows.length>1)
	{
		$.ligerDialog.confirm("每次只能编辑一行数据，请重新选择！","提示",function(type){
			if(type)
			{
				grid.reload();
			}
		});
	}
	else
	{
		//设置数据
		try
		{
			document.getElementById('mypanel').contentWindow.setData(rows[0]); 
			panel.expand();
		}
		catch(err)
		{
			console.log(err);
		}
	}
}
function Delete()
{
	var delId = new Array();
	//选中要删除的行id
	var rows = grid.getSelecteds();
	for(var k in rows)
	{
		delId[k] = rows[k].id;
	}
	//发送删除请求
	$.ligerDialog.confirm("是否删除指定数据！","提示",function(type){
		if(type)
		{
			console.log({"array":delId});
			$.post(basePath + "rest/" + routeName + "Action/removeCollections",
					{
						"params":delId.join(',')
					},
					function(result){
						//如果删除成功，页面给出相应提示
						if(result!="Success")
						{
							$.ligerDialog.confirm(result, function (type){});
						}
						else{
							$.ligerDialog.tip({
								title : '提示信息',
								content : '已删除选定记录！'
							});
							//刷新表格
							setTimeout(grid.reload(),1000);
						}
					},
					'json');
		}
	});
}
function Query()
{
	//封装成JSON对象
	var formdata = queryform.getData();
	formdata.isForeign = liger.get('paraStandard').getText();
	formdata.paraFrom = liger.get('queryparaFrom').getText();
	var query ={};
	//删除值为为空的条件
	for(var x in formdata)
	{
		if(formdata[x]=="")
			continue;
		query[x]=formdata[x];
	}
	$.post(
			basePath + 'rest/'+routeName + 'Action/queryForm',
			{"params":JSON.stringify(query)},
			function(result){
				if(result!=null)
				{
					 if(result.Rows.length==0)
					  {
						  $.ligerDialog.warn("查询结果为空，请输入有效的查询条件！");
					  }
					grid.loadData(result);
				}
			},
			'json');
}
function Reload()
{
	//重置查询条件
	queryform.setData(querydata);
}
function Reflash()
{
	//刷新Grid
	grid.reload();
}
function InitialPage(){
	createInputForm();
	createQueryForm();
	createGrid();
}
Main = $(function(){
	InitialPage();
});