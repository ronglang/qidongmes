$(function() {
	//重置按钮触发
	$("#" + routeName).ligerForm();
	$(".l-button-test").click(function() {
		alert(v.element($("#txtName")));
	});
  //判断是保存还是修改
	//var row_id=grid.getSelected().id;
	if (row_id) {
		var parm = "id=" + row_id;
		pub_initForm(routeName, parm);
	}
});
/**
 * 重写提交数据成功后的回调函数
 */
/*function submitSuccess(json) {
	if (json.success) {
		alert("保存成功");
		if (json.data.id != null)
			row_id = json.data.id;
	} else {
		alert("保存失败");  
	}
}*/
function saveFormData() {
	var parm = "";
	if (row_id) {
		parm = "id=" + row_id;
		//debugger;
		url = basePath + "rest/" + routeName + "Action/toSaveData" + "?" + parm; 
		//debugger;
		/*serializeArray()方法可以将表单数据序列化成一个键值对数组对象*/  
		var data = $("#" + routeName).serializeArray(); 
		$.ajax({
			url : url, 
			type : "post", 
			dataType : "json", 
			//$,param()可以将数组对象的数据处理成查询字符串的形式
			data : $.param(data), 
			success : function(data) { 
				debugger;
				parent.manuForkliftRefresh();
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
				debugger;
				parent.manuForkliftRefresh();
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
	//js解码函数  
	//alert(pa);
	grid.loadServerData(decodeURIComponent(pa, true));
}



