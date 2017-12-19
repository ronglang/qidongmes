var groupicon = "../core/img/site_icon/communication.gif";
var form;
var dialog;
var Data;
var matels;
$(function() {
	getSupplier();
	//创建表单结构 
	
	//调用页面的dialog对象(ligerui对象)
//	dialog = frameElement.dialog; 
//	Data = dialog.get('data');
//	if(Data!=null)
//	{
//		//迫于现实设计，放弃自动装配
//		Data.createDate = Data.createDate.substring(0,10);
//		Data.matermanageTime = Data.matermanageTime.substring(0,10);
//		form.setData(Data);
//		
//	}
	$('#save').click(function(){
		alert('123');
		f_validate();
	});
	$('#cancel').click(function(){
		var area = $('#InputArea').ligerPanel();
		dialog = frameElement.dialog;
		//var area = $('#inputform').parent().parent('#InputArea');
		alert(JSON.stringify(dialog));
	});
});
function getSupplier(){
	var url=basePath+ 'rest/storeMaterialBasicInfoManageAction/getSupplier';
	//获取供应商的列表
	$.post(url,{},function(data){
		 matels=[];
		debugger;
    	var obj=data;
    	for (var int = 0; int < obj.length; int++) {
    		var matel = new Object();
    		/*selectEdName.append( "<option value='"+obj[int]+"'>'"+obj[int]+"'</option>" );*/
    		matel.text=obj[int];
    		
    		matels.push(matel);
    	
		}
    	
    	form = $("#inputform").ligerForm({
    		inputWidth: 202,
    		labelWidth: 70,
    		space: 40,
    		validate: [{
    			event:"blur"
    		}],
    		fields: [
    				{
    				display: "批次编号",
    				name: "batchNumber",
    				newline: true,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "RFID编码",
    				name: "rfid",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "材料类型",
    				name: "materialType",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "规格型号",
    				name: "spec",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "材料颜色",
    				name: "color",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},{
    				display: "材料数量",
    				name: "quantity",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "材料位置",
    				name: "location",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "操作人员",
    				name: "employee",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "库存时间",
    				name: "storageTime",
    				newline: true,
    				type: "date",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "合格证书",
    				name: "certificate",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "检验报告",
    				name: "inspectionReport",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    			{
    				display: "备注信息",
    				name: "remark",
    				newline: false,
    				newline:true,
    				type: "textarea",
    				height:30,
    				width:1138
    			},
    			{
    				display: "送货人员",
    				name: "deliveryPerson",
    				newline: true,
    				type: "text",
    				group: "送货信息",
    				groupicon: groupicon
    			},
    			{
    				display: "车牌号码",
    				name: "CarNumber",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},{
    				display: "托架号码",
    				name: "bracketNumber",
    				newline: false,
    				type: "text",
    				validate: {
    					required: true,
    				}
    			},
    					{
    				display: "供应商",
    				name: "supplier",
    				newline: false,
    				type: "select",
    				comboboxName:"doctype",
    				options: { valueFieldID:"docType",data: matels},
                    attr: { op: "equal" }, 
                    cssClass: "field",
    				validate: {
    					required: true,
    				}
    			}
    		]
    	});
    // dataMess=JSON.stringify(matels);
		
	},"json");
	
}
function f_validate() {
//	var param='';
//	if(null!=Data)
//	{
//		param = '?id='+Data.id;
//	}
	if(form.valid()) {
		$.ajax({
			load:'数据加载中...',
			type:'POST',
			dataType:'json',
			async:false, 
			data:$("#inputform").serialize(),
			url: basePath+ 'rest/storeMaterialBasicInfoManageAction/saveOrUpdateRecord',
			success:function(result){
				
				dialog.close();
			},
			error:function(result){
				$.ligerDialog.error("数据更新异常:"+result.status);
			}
		});
	} else {
		form.showInvalid();
	}
}