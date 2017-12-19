var form;
var InitialData;
var paraType;
var paraFrom;
var paraStandard;//是否来自国外
var paraUnit;
var recordId=null;
Main = $(function() {
	getParams();
	//创建表单结构 
	CreateForm();
	//初始化表单
	InitialForm();
	panel = frameElement.panel;
	$('#save').click(function(){
		f_validate();
		InitialForm();	
	});
	$('#cancel').click(function(){
		InitialForm();
		panel.collapse();
	});
});
function CreateForm(){
	form = $("#inputform").ligerForm({
		inputWidth: 202,
		labelWidth: 70,
		space: 40,
		validate: true,
		fields: [{
				display: "参数名称",
				name: "paraName",
				newline: true,
				type: "text",
				validate: {
					required: true,
				}
			},
			{
				display: "参数类型",
				name: "paraType",
				newline: false,
				type: "combobox",
				comboboxName:"formParaType",
				options: {data:paraType},
				validate: {
					required: true,
				}
			},
			{
				display: "参数来源",
				name: "paraFrom",
				newline: false,
				type: "combobox",
				comboboxName:"formParaFrom",
				options: {data:paraFrom},
				validate: {
					required: true,
				}
			},
			{
				display: "参数标准",
				name: "isForeign",
				newline: false,
				type: "combobox",
				comboboxName: "isForeign",
				options: {data:paraStandard},
				validate: {
					required: true,
				}
			},
			{
				display: "最小值",
				name: "paraValueMin",
				newline: true,
				type: "text",
				validate: {
					required: true,
				}
			},{
				display: "最大值",
				name: "paraValueMax",
				newline: false,
				type: "text",
				validate: {
					required: true,
					digits:true
				}
			},
			{
				display: "参数单位",
				name: "unit",
				newline: false,
				type: "combobox",
				comboboxName: "paraUnit",
				options: {data:paraUnit},
				validate: {
					required: true,
				}
			},
		]
	});
	//获取原始数据
	InitialData = form.getData();
}
function InitialForm()
{
	form.setData(InitialData);
	recordId=null;
}
function setData(Data)
{
	if(Data!=null)
	{
		//自动装配
		form.setData(Data);
		liger.get('formParaType').setText(Data.paraType);
		liger.get('formParaFrom').setText(Data.paraFrom);
		liger.get('isForeign').setText(Data.isForeign);
		if(Data.id!=null)
		{
			recordId =Data.id;
		}
	}
}
function f_validate() {
	var url = "http://localhost:8080/mes/rest/storeSpecManageAction/saveOrUpdate";
	var data = form.getData();
	if(recordId!=null)
	{
		data.id = recordId;
	}
	data.paraType = liger.get('formParaType').getText();
	data.paraFrom = liger.get('formParaFrom').getText();
	data.isForeign = liger.get('isForeign').getText();
	data.unit = liger.get('paraUnit').getText();
	if(form.valid()) {
		$.post(url,{"data":JSON.stringify(data)},
				function(result){
					if(!result)
						$.ligerDialog.confirm("操作异常！","提示",function(){});
					else
					{
						panel.collapse();
						window.parent.grid.reload();	
					}
		},'json');
	}
	else
	{
		form.showInvalid();
	}
}
function getParams()
{
	//初始化参数类型下拉框
	/*静态设置*/
	paraType=[{text: '笼绞机' },{text: '弓绞机'},{ text: '管绞机'},{ text: '框绞机'}];
	paraStandard=[{text:'国内'},{text:'国外'}];
	paraFrom =[{text:'机台参数'},{text:'规格参数'}];
	paraUnit = [{text:'mm²'},{text:'℃'},{text:'㎡'},{text:'m'},{text:'㎏'}];
	//初始化参数来源下拉框
	//初始化参数标准下拉框
}