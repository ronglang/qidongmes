$(function() {
	$("#" + routeName).ligerForm();
	$(".l-button-test").click(function() {
		alert(v.element($("#txtName")));
	});
	if (row_id) {
		var parm = "id=" + row_id;
		pub_initForm(routeName, parm);
	}
});
function saveFormData() {
	var parm = "";
	if (row_id) {
		parm = "id=" + row_id;
		url = basePath + "rest/" + routeName + "Action/toSaveData" + "?" + parm;
		var data = $("#" + routeName).serializeArray();
		$.ajax({
			url : url,
			type : "post",   
			dataType : "json",
			data : $.param(data),
			success : function(data) {
				parent.editRefresh();
				parent.$.ligerDialog.close();
				parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
			},
			error : function(data) {  
				alert("提交失败");
			}
		});
	}
	else{
		var data = $("#" + routeName).serializeArray();
		$.ajax({
			url :  basePath + "rest/" + routeName + "Action/toSaveData",
			type : "post",
			dataType : "json",
			data : $.param(data),
			success : function(data) {
				parent.editRefresh();
				parent.$.ligerDialog.close();
				parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
			},
			error : function(data) {  
				alert("提交失败");
			}
		});
	}
}
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa, true));
}



