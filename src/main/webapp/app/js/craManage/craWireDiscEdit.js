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
	debugger;
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
				$.ligerDialog.success('保存成功');
				parent.editRefresh();
				parent.$.ligerDialog.close();
				parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
			},
			error : function(data) {  
				$.ligerDialog.error('保存失败,发生了未知错误！');
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
				$.ligerDialog.success('保存成功');
				parent.editRefresh();
				parent.$.ligerDialog.close();
				parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
			},
			error : function(data) {  
				$.ligerDialog.error('保存失败,发生了未知错误！');
			}
		});
	}
}

function resetMine(){
	window.location.reload();
}
function reloadData() {
	var pa = $("#" + routeName + "ListForm").serialize();
	grid.loadServerData(decodeURIComponent(pa, true));
}



