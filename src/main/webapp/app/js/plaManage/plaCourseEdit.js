var form;
$(function(){
	debugger;
	form = $("#plaCourseManage").ligerForm({
		inputWidth: 150, 
		labelWidth: 120, 
		space: 40,
		fields: [
		    { display: "id", name: "id",   newline :false,type: "hidden" }, 
		    { display: "创建日期", name: "createDate",   newline :false,type: "text" }, 
		    { display: "创建人", name: "createBy",   newline :false,type: "text" }, 
		    { display: "备注", name: "remark",   newline :false,type: "text" }, 
		    { display: "工单类型", name: "wsType",   newline :false,type: "text" },
		    { display: "工单号", name: "wsCode",   newline :false,type: "text" },
		    { display: "合同编号", name: "scCode",   newline :false,type: "text" },
		    { display: "批次号", name: "batCode",   newline :false,type: "text" },
		    { display: "型号规格", name: "headGgxh",   newline :false,type: "text" },
		    { display: "颜色", name: "color",   newline :false,type: "text" },
		    { display: "扎装段长", name: "headZzdc",   newline :false,type: "text" },
		    { display: "扎装段数", name: "headZzds",   newline :false,type: "text" },
		    { display: "总数量", name: "totalAmount",   newline :false,type: "text" },
		    { display: "生产通知单ID", name: "manuNoticeId",   newline :false,type: "text" },
		    { display: "开单日期", name: "billDate",   newline :false,type: "text" },
		    { display: "工艺id", name: "cCode",   newline :false,type: "text" },
		    { display: "是否完成 ", name: "isFinish",   newline :false,type: "text" },
		    { display: "客户id", name: "cusId",   newline :false,type: "text" },
		    { display: "审核标志", name: "auditFlag",   newline :false,type: "text" },
		    { display: "审核时间", name: "auditTime",   newline :false,type: "text" },
		    { display: "过程名称", name: "courseName",   newline :false,type: "text" },
		    { display: "启用标志", name: "useFlag",   newline :false,type: "text" },
		    { display: "生产原则", name: "manuTenet",   newline :false,type: "text" },
		    { display: "过期标志", name: "pastFlag",   newline :false,type: "text" },
		    { display: "计划启用日期", name: "planEnableDate",   newline :false,type: "text" },
		    { display: "工艺路线编码", name: "routeCode",   newline :false,type: "text" },
		    { display: "产品工艺编码", name: "proCraftCode",   newline :false,type: "text" },
		    { display: "生产令编号", name: "productOrderCode",   newline :false,type: "text" }
		    ]
	}); 
	//初始化编辑的对象的值
	if(id){
		init(id);
	}
});

function init(id){
	$.ajax({
		url: basePath+"rest/plaCourseManageAction/detail",
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