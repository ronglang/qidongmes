$(function() {
	$.metadata.setType("attr", "validate");
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
			clas : {
				rangelength : [ 0, 32 ]
			},

			VALUE : {
				rangelength : [ 0, 32 ]
			},
	
		},
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler : function() {
			$("#" + routeName + " .l-text,.l-textarea").ligerHideTip();
			submitSysDictionary();
		}
	});
	$("#" + routeName).ligerForm();
	$(".l-button-test").click(function() {
		alert(v.element($("#txtName")));
	});
	//creatTreeDiv("isSystem");
	//initTree("clas", "value");
	if (row_id) {
		var parm = "id=" + row_id;
		pub_initForm(routeName, parm);

	}
});

/**
 * 提交数据
 */
function submitSysDictionary() {
	var url = basePath + "rest/" + routeName + "Action/saveCommdic";
	if(row_id!=null){
		var datas =  "id=" + row_id +"&value=" + $("#value").val()
		+ "&clas=" + $("#clas").val() ;
		
	}
	else{
		var datas =  "value=" + $("#value").val()
		+ "&clas=" + $("#clas").val() ;
	}

	$.ajax({
		url : url,
		type : "post",
		data : datas,
		dataType:"json",
		success : function(data) {
			submitSuccess(data);
		},
		error : function(data) {
			dealErr(data);
		},
        failure : function(result){
        	alert('保存失败');
        }
	});
}


/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess(json) {
//	if (json.success) {
//		alert("保存成功");
//		refreshDatagrid();
//	} else {
//		dealErr(json);
//		alert("保存失败");
//	}
//	alert(json.success);
	if (json.success) {
		alert("保存成功");
		refreshDatagrid();
//		 top.$.ligerDialog.success(json.msg);
	} else {
		$(".l-button-submit").show();
		if(json.msg) top.$.ligerDialog.error(json.msg);
	}
}
function refreshDatagrid() {
	var frames0 = window.parent.window.document.getElementsByTagName("iframe")[0];
	var frames1 = window.parent.window.document.getElementsByTagName("iframe")[1];
	var f1 = frames1.contentWindow.grid;
	var f0 = frames0.contentWindow.grid;
	if (typeof f1 != 'undefined') {
		//f1.loadServerData();
		f1.reload();
	} else if (typeof f0 != 'undefined') {//f0.loadServerData();
		f0.reload();
	}
	parent.$.ligerDialog.close();
	parent.$(".l-dialog,.l-window-mask").remove();
}

function dealErr(json){
	var msg = json.msg;
	if(msg){
		top.$.ligerDialog.error(msg);
	}
	else if(json.responseText){
		var result = jQuery.parseJSON(json.responseText);
		if(result)
			top.$.ligerDialog.error(result.msg);
		else
			top.$.ligerDialog.error(json.responseText);
	}
	else{
		alert("保存失败");
	}
}