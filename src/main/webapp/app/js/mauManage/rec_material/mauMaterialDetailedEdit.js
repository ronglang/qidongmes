$(function(){
	loadForm();
});

function loadForm(){
	form = $("#form").ligerForm({
		inputWidth: 150, 
		labelWidth: 120, 
		space: 40,
		fields: [
			{ display: "id", name: "id",   newline :false,type: "hidden" },
			{ display: "物料编号", name: "materCode",   newline :false,type: "text" },
			{ display: "物料数量", name: "materAmount",   newline :false,type: "text" },
			{ display: "物料记录编码", name: "mmrCode",   newline :false,type: "text" },
			{ display: "单位", name: "unit",   newline :false,type: "text" },
			{ display: "备注", name: "remark",   newline :false,type: "text" },
			{ display: "计划数量", name: "planCount",   newline :false,type: "text" },
			{ display: "物料名称", name: "materName",   newline :false,type: "text" }
		],
	}); 
}