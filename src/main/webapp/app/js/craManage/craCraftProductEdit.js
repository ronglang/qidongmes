var form;
var manager ;
$(function(){
	debugger;
	var datas = loadSelect();
	debugger;
	
	form = $("#myForm").ligerForm({
		inputWidth: 200, 
		labelWidth: 100, 
		space: 10,
		fields: [
		    { display: "id", name: "id",   newline :true,type: "hidden" }, 
		    { display: "产品工艺编码", name: "proCraftCode",   newline :false,type: "hidden" },//后台自动生成产品工艺编码
		    { display: "创建人", name: "createBy",   newline :true,type: "hidden" },//创建人，目前自动生成
		    { display: "创建日期", name: "createDate",   newline :false,type: "hidden" },//创建日期 TODO 以上是隐藏字段，正式上线要注释掉
		    { display: "产品大类", name: "proId",   newline :true,type: "select", id:"proId",comboboxName: "proId" ,options:{data: datas.proId}}, //产品id,从产品表中选择
		    { display: "产品规格型号", name: "proGgxh",   newline :false,type: "select" ,id:"proGgxh",comboboxName:"proGgxh",options:{data:datas.proGgxh}},//产品规格型号，从规格表中选择数据
		    { display: "工艺路线编码", name: "routeCode",   newline :true,type: "select",id:"routeCode",comboboxName:"routeCode",options:{data:datas.routeCode} },//工艺路线编码，从工艺路线表中选择 
		    { display: "颜色", name: "proColor",   newline :false,type: "select",id:"proColor",comboboxName:"proColor",options:{data:datas.proColor} },//从数据字典中选择数据
		    { display: "产品工艺名称", name: "proCraftName",   newline :true,type: "text" },//工艺名称，由客户手动录入数据
		    { display: "集绞方式", name: "gatheringMode",   newline :false,type: "text" }//如 ： 3*150+2*20;值需要管最后一道集绞
		]
	,buttons:[
	          {text:"temp",click:test}
	          ]
	}); 
	//初始化编辑的对象的值
	if(id){
		init(id);
	}
//	manager = $("#myForm").ligerGetGridManager();
//	manager.get("gatheringMode").setDisabled();
	$.ligerui.get("gatheringMode").set('disabled', true) ; //设置该文本框不可编辑
});

function test(){
	var temp = $("#myForm").get("proId");
	temp.setParm("id","id1"); 
	debugger;
}
//ajax到后台请求数据
function loadSelect(){ 
	var datas = new Object();
	$.ajax({
		url: basePath+"rest/craCraftProductManageAction/getSelectData",
		dataType: 'json',
		data: "id="+id,
		type: "post",
		async:false, 
		success:function(data){
			debugger;
			var dat = data.data;
			datas.proId =  dat.proId;//产品id选择框
			datas.routeCode = dat.routeCode;//产品工艺路线
			
			datas.proGgxh = dat.proGgxh;//产品规格型号
			
			datas.proColor = dat.proColor;//产品颜色
		}
	});
	datas.setGround = [];//是否有集绞工序下拉框
	var obj = new Object();
	obj.id = "yes";
	obj.text = "是";
	var obj1 = new Object();
	obj1.id = "no";
	obj1.text = "否";
	
	datas.setGround.push(obj);
	datas.setGround.push(obj1);
	return datas;
}
function init(id){
	debugger;
	$.ajax({
		url: basePath+"rest/craCraftProductManageAction/getById",
		dataType: 'json',
		data: "id="+id,
		type: "post",
		success:function(data){
			debugger;
			var dat = data.data;
			form.setData(dat); 
		}
	});
}