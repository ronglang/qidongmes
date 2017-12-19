$(function() {

	//Validation插件
	/*$.metadata.setType("attr", "validate");
	var v = $("#" + routeName).validate({
		debug : true,
		errorPlacement : function(lable, element) {
			if (element.hasClass("l-textarea")) {
				element.ligerTip({
					content : lable.html(),
					target : element[0]
				});
			} else if (element.hasClass("l-text-field")) {
				element.parent().ligerTip({
					content : lable.html(),
					target : element[0]
				});
			} else {
				lable.appendTo(element.parents("td:first").next("td"));
			}
		},
		rules : {
			id : {
				required : true
			},
		},
		//当表单验证成功后执行，很多事件都会触发验证，该方法会被多次执行
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		//submit按钮点击后触发
	     
		submitHandler : function() {
			//$("#" + routeName + " .l-text,.l-textarea").ligerHideTip();
			//submitMauHandlingChores();
			//点击提交后将表单数据序列化，将实体传到后台。
			saveFormData();
		}
	});*/
	
	
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
				//alert("提交成功" + data);
	           /* $(".l-bar-button .l-bar-btnload",window.parent.opener).click();  */
				alert(data+",请刷新页面");
				//parent._manuChoresListRefresh();
				parent.$.ligerDialog.close();
				parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
				
			},
			error : function(data) {  
				alert("提交失败");
			}
		});
	}
	else{
		//debugger;
		var data = $("#" + routeName).serializeArray();
		$.ajax({
			url :  basePath + "rest/" + routeName + "Action/toSaveData",
			type : "post",
			dataType : "json",
			//$,param()可以将数组对象的数据处理成查询字符串的形式
			data : $.param(data),
			success : function(data) {
				//alert("提交成功" + data);
				/*$(".l-bar-button l-bar-btnload",window.parent.opener).click();*/
				//parent._manuChoresListRefresh();
				alert(data+",请刷新页面");
				parent.$.ligerDialog.close();
				parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
				
//				window.parent.grid.reload();
//				debugger;
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



