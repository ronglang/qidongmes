var form;
$(function() {
	form = $("#myForm").ligerForm({
		inputWidth : 200,
		labelWidth : 84,
		space : 20,
		fields : [ {
			dispaly:"id",
			name:"id",
			newline : false,
			type:"hidden",
			
		}
		,{
			display : "锁控操作时间",
			name : "lockControlTime",
			newline : false,
			type : "hidden",
		}
		,{
			display:"订单编号",
			name : "orderCode",
			newline : false,
			type : "text"
		}, {
			display : "产品名称",
			name : "proName",
			newline : false,
			type : "text",
		}, {
			display : "产品规格型号",
			name : "proGgxh",
			newline : false,
			type : "text",
		}
		, {
			display : "锁控结束时间",
			name : "lockEndTime",
			newline : false,
			type : "hidden",
			editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"}
		}
		, {
			display : "单轴长度",
			name : "als",
			newline : false,
			type : "number"
		}, {
			display : "轴数",
			name : "axisNumber",
			newline : false,
			type : "number"
		}, {
			display : "生产通知单编号",
			name : "producNoticeCode",
			newline : false,
			type : "hidden"
		}, {
			display : "订单明细id",
			name : "orderSonId",
			newline : false,
			type : "hidden"
		}, {
			display : "创建人",
			name : "createBy",
			newline : false,
			type : "hidden"
		}, {
			display : "创建时间",
			name : "createTime",
			newline : false,
			type : "hidden"
		}]
	});
	init();
});
function init(){
	$.ajax({
		url: basePath+"rest/storeLockControlManageAction/editData?",
		dataType: 'json',
		data: "id="+id,
		type: "post",
		success:function(data){
			var dat = data.data;
			form.setData(dat); 
			debugger;
		}
	});
}



