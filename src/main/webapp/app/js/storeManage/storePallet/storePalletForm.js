var form;
var InitialData;
var palletType;
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
				display: "栈板名称",
				name: "palletName",
				newline: true,
				type: "text",
				validate: {
					required: true,
				}
			},
			{
				display: "栈板类型",
				name: "palletType",
				newline: false,
				type: "combobox",
				comboboxName:"formPalletType",
				options: {data:palletType},
				validate: {
					required: true,
				}
			},
			{
				display: "栈板RFID",
				name: "palletRfid",
				newline: false,
				type: "text"
			}
		]
	});
	liger.get('palletRfid').setDisabled();//禁用用ligeui生成的某个表单字段 
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
		liger.get('formPalletType').setText(Data.palletType);
		if(Data.id!=null)
		{
			recordId =Data.id;
		}
	}
}
function f_validate() {
	var url = "http://localhost:8080/mes/rest/storePalletManageAction/saveOrUpdate";
	var data = form.getData();
	if(recordId!=null)
	{
		data.id = recordId;
	}
	data.palletType = liger.get('formPalletType').getText();
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
	palletType=[{text:'原材料栈板'},{text:'半成品栈板'},{text:'成品栈板'}];
	//初始化参数来源下拉框
	//初始化参数标准下拉框
}