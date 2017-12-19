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

		},
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler : function() {
			$("#" + routeName + " .l-text,.l-textarea").ligerHideTip();
			submitSysCityPerson();
		}
	});
	$("#" + routeName).ligerForm();
	$(".l-button-test").click(function() {
		alert(v.element($("#txtName")));
	});
	creatTreeDiv("gender");
	initTree("gender", "sysDictionary", "radio", "PID", "C1021");
	creatTreeDiv("education");
	initTree("education", "sysDictionary", "radio", "PID", "C015");
	creatTreeDiv("position");
	initTree("position", "sysDictionary", "radio", "PID", "C1010");
	creatTreeDiv("orgCode");
	initTree("orgCode", "sysOrg", "normal", "", "");
	creatTreeDiv("areaCode");
	initTree("areaCode", "sysArea", "normal", "", "");
	if (row_id) {
		var parm = "id=" + row_id;
		pub_initForm(routeName, parm);

	}
});

/**
 * 提交数据
 */
function submitSysCityPerson() {
	var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	}
	pub_save(routeName, parm, false);
}
