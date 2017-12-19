var form;
var datas;
$(function(){
	debugger;
	loadSelectParamCode(relation_id);
	form = $("#form").ligerForm({
		inputWidth: 150, 
		labelWidth: 120, 
		space: 40,
		fields: [
			{ display: "relation_id", name: "relation_id",   newline :false,type: "hidden" },
			{ display: "工序编号", name: "seqCode",   newline :false,type: "text" },
			{ display: "参数编码", name: "paramCode",   newline :false,type: "select" ,id:"paramCode",comboboxName: "paramCode" ,options:{data: datas.paramCode}},
			{ display: "参数最小值", name: "paramMinValue",   newline :false,type: "text" },
			{ display: "参数最大值", name: "paramMaxValue",   newline :false,type: "text" },
			{ display: "单位", name: "uint",   newline :false,type: "text" },
			{ display: "产品规格型号", name: "ggxh",   newline :false,type: "text" },
			{ display: "参数设定值", name: "paramValue",   newline :false,type: "text" },
			{ display: "产品工艺编码", name: "proCraftCode",   newline :false,type: "text" },
			{ display: "参数名称", name: "paramName",   newline :false,type: "select" ,id:"paramName",comboboxName: "paramName" ,options:{data: datas.paramName}},
			{ display: "工艺编码（后台生成，不能填写）", name: "craftCode",   newline :false,type: "text" },
			{ display: "工艺名称", name: "craftName",   newline :false,type: "text" },
			{ display: "主表编码（自动带出来）", name: "pcscRelaCode",   newline :false,type: "text" }
			
		],
	}); 
	//TODO 初始化编辑的对象的值，暂时不提供编辑功能
//	if(id){
//		init(id);
//	}
	if(relation_id){//初始化部分参数，类似于
		loadSelectData(relation_id);
	}
});

function init(id){
	$.ajax({
		url: basePath+"rest/craSeqParamManageAction/getById",
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

function loadSelectData(objId){
	$.ajax({
		url: basePath+"rest/craSeqParamManageAction/loadSelectData",
		dataType: 'json',
		data: "relation_id="+objId,
		type: "post",
		success:function(data){
			if(data.success){
				var dat = data.data;
				form.setData(dat); 
			}
		}
	
	});
}

function loadSelectParamCode(objId){
	$.ajax({
		url: basePath+"rest/craSeqParamManageAction/loadSelectParamCode",
		dataType: 'json',
		data: "relation_id="+objId,
		type: "post",
		async:false, 
		success:function(data){
			if(data.success){
				datas = data.data;
			}
		}
	});
}