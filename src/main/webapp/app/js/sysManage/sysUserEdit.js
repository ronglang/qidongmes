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
			ACCOUNT : {
				rangelength : [ 0, 64 ]
			},
			PASSWORD : {
				rangelength : [ 0, 128 ]
			},
			STATUS : {
				rangelength : [ 0, 8 ]
			},
			SORT : {
				rangelength : [ 0, 12 ]
			},
			ORG_ID : {
				rangelength : [ 0, 32 ]
			},
			EMPLOYEE_ID : {
				rangelength : [ 0, 32 ]
			},
			REMARK : {
				rangelength : [ 0, 512 ]
			}
		},
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler : function() {
			$("#" + routeName + " .l-text,.l-textarea").ligerHideTip();
			submitSysUser();
		}
	});
	$("#" + routeName).ligerForm();
	$(".l-button-test").click(function() {
		alert(v.element($("#txtName")));
	});
//	creatTreeDiv("orgCode");
	//initTree("orgCode", "sysOrg", "normal", "", "");

	//creatTreeDiv("hPersonId");
//	initTreeNoExit("hPersonId", "sysEmployee", "radio", "", "");
	//initTree("hPersonId", "sysEmployee", "radio", "", "");
	
	creatTreeDiv("roleId");
	//initTrees("roleId", "sysRole", "checkbox", "", "");
	 initTree("roleId", "sysRole", "checkbox", "", "");
	
	creatTreeDiv("userType");
	initTree("userType", "sysCommdic", "radio", "clas", "数据用户类型分类");
	/*
	creatTreeDiv("orgCodeAuth");
	initTree("orgCodeAuth", "sysOrg", "radio", "", "");
	*/
	creatTreeDiv("areaCode");
	initTree("areaCode", "sysArea", "normal", "", "");
	//initTree("areaCode", "sysArea", "radio", "pcode", "5119");
	//creatTreeDiv("areaCode");
	//initTreeCustom("areaCode","sysArea","normal","","",villageCodeTreeSetting);

	if (row_id) {
		var parm = "id=" + row_id;
		pub_initForm(routeName, parm);
	}
});
//
function initTrees(colName, nodeTBName, treeType, queryName, queryValue,
		urlAndWhere, treeSettingObj) {
	var url, data = "";
	if (row_id != null) {
		url = basePath + "rest/" + nodeTBName + "ManageAction/nodeTrees";
		data = "qcolName=" + queryName + "&qcolValue=" + queryValue + "&id="
				+ row_id;
	} else {
		url = basePath + "rest/" + nodeTBName + "ManageAction/nodeTree";
		data = "qcolName=" + queryName + "&qcolValue=" + queryValue;
	}

	$.ajax({
				url : url,
				type : "post",
				dataType : "json",
				async : false,
				data : data,
				success : function(json) {
					if (json.data != null) {
						if (typeof (treeSettingObj) != 'undefined'
								&& treeSettingObj != null
								&& treeSettingObj.length > 0) {
							$.fn.zTree.init($("#" + colName + "ContentTree"),
									treeSettingObj, json.data);
						} else if (treeType == "checkbox") {
							$.fn.zTree.init($("#" + colName + "ContentTree"),
									checkTreeSetting, json.data);
							var ids = "";
							var names = "";
							json.data.forEach(function(item) {
								if (item.checked) {
									ids = ids + item.id + ",";
									names = names + item.name + ",";
								}
							});
						ids = ids.substring(0, ids.length - 1);
							names = names.substring(0, names.length - 1);
							$("#roleId").val(ids);
							$("#roleIdShow").val(names);
						} else if (treeType == "radio") {
							$.fn.zTree.init($("#" + colName + "ContentTree"),
									radioTreeSetting, json.data);
						} else {
							$.fn.zTree.init($("#" + colName + "ContentTree"),
									treeSetting, json.data);
						}
					}

				},
				error : function(json) {
					top.$.ligerDialog.error("系统错误，请联系管理员！");
				}
			});
}


function initTreeNoExit(colName, nodeTBName, treeType, queryName, queryValue) {
	var url, datas = "";
	if (row_id == null) {
		url = basePath + "rest/sysEmployeeManageAction/nodeTreeAddUser";
		datas = "qcolName=" + queryName + "&qcolValue=" + queryValue;
	} else {
		url = basePath + "rest/sysEmployeeManageAction/nodeTreeNoExit";
		datas = "qcolName=" + queryName + "&qcolValue=" + queryValue + "&id="
				+ row_id;
	}
	$.ajax({
		url : url,
		type : "post",
		data : datas,
		success : function(json) {
			if (json.data != null) {
				if (treeType == "checkbox") {
					$.fn.zTree.init($("#" + colName + "ContentTree"),
							checkTreeSetting, json.data);
				} else if (treeType == "radio") {
					$.fn.zTree.init($("#" + colName + "ContentTree"),
							radioTreeSetting, json.data);
				} else {
					$.fn.zTree.init($("#" + colName + "ContentTree"),
							treeSetting, json.data);
				}
			}

		},
		error : function(json) {
			alert('出错啦！');
		}
	});
}

var villageCodeTreeSetting={				
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeTreeClick,
			onClick: function(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj(treeId),
				nodes = zTree.getSelectedNodes(),
				v = "";
				h = "";
				nodes.sort(function compare(a,b){return a.id-b.id;});
				for (var i=0, l=nodes.length; i<l; i++) {
					v += nodes[i].name + ",";
					h += nodes[i].id + ",";
				}
				if (v.length > 0 ) v = v.substring(0, v.length-1);
				if (h.length > 0 ) h = h.substring(0, h.length-1);
				if(treeId.indexOf("ContentTree")>-1){
					var inputId = treeId.split("ContentTree")[0];
					var cityObj = $("#"+inputId);
					cityObj.attr("value", h);
					var cityObjSHOW = $("#"+inputId+"Show");
					cityObjSHOW.attr("value", v);
					setName(v);
				}
			//	var len = (h+"").length;
			//	if(len != 12){
			//		top.$.ligerDialog.warn("请选择村");
			//	}
			//	else{
			//		var ac = (h+'').substring(0,6);
			//		creatTreeDiv("devFuBank");
			//		initTreeCustom("devFuBank","fBank","normal","areaCode",ac);
			//	}
				//initTreeCustom("poorIdnumber","basicdataPoorhousehold","normal","areaCode",h,idNumberTreeTreeSetting,true);
			}	
		}
};

//**
// * 提交数据
// */
function submitSysUser1() {
		var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} 
	pub_save(routeName,parm,false);
	
}


function submitSysUser() {
	
	if(row_id==null){
		var url = basePath + "rest/sysUserManageAction/saveUserInfo";
		$.ajax({
			url : url,
			type : "post",
			data :  "account=" + $("#account").val() 
	        + "&areaCode=" + $("#areaCode").val()
	        + "&userType=" + $("#userType").val()
		//	+ "&hPersonId=" + $("#hPersonId").val()
			+ "&roleIdList=" + $("#roleId").val() 
			+ "&name=" + $("#name").val()
			+ "&remark="+ $("#remark").val(),
			success : function(data) {
				var obj = jQuery.parseJSON(data);
				subSuccess(obj);
				
			},
		error : function(data) {
				ajaxError(data);
			}
		});
	}else{
		var url = basePath + "rest/sysUserManageAction/saveUserInfo";
		$.ajax({
			url : url,
			type : "post",
			data : "account=" + $("#account").val() 
			//	+ "&hPersonId=" + person
				+ "&roleIdList=" + $("#roleId").val()
				+ "&userType=" + $("#userType").val()
				+ "&remark="+ $("#remark").val()
				+ "&password=" + $("#password").val()
				+ "&id=" + row_id + "&createBy=" + $("#createBy").val()
				+ "&status=" + $("#status").val()
				+ "&name=" + $("#name").val()
				+ "&tel=" + $("#tel").val()
				+ "&createDate=" + $("#createDate").val()
				+ "&areaCode=" + $("#areaCode").val(),
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					subSuccess(obj);
			},
			error : function(data) {
				ajaxError(data);
                 //	alert("保存失败！");
			}
		});
	}
	
}
/* 这个方法也可。*/
//function submitSysUser() {
//	var url = basePath + "rest/sysUserManageAction/saveUserInfo";
//	
//	var datas = "";
//	var parmData = "";
//	if (row_id) {
////	parmData = "id=" + row_id
//	//	var data = queryValueToEdit(parmData);
//		//var person = data.person_id;
//	//	var code = data.org_code;
//		datas = "account=" + $("#account").val() 
//	//	+ "&orgCode="+ code
//	//	+ "&hPersonId=" + person
//		+ "&roleIdList=" + $("#roleId").val()
//		+ "&remark="+ $("#remark").val()
//		+ "&password=" + $("#password").val()
//		+ "&id=" + row_id + "&createBy=" + $("#createBy").val()
//		+ "&status=" + $("#status").val()
//	//	+ "&sort=" + $("#sort").val()
//		+ "&name=" + $("#name").val()
//		+ "&tel=" + $("#tel").val()
//		+ "&createDate=" + $("#createDate").val()
//		+ "&areaCode=" + $("#areaCode").val();
//		
//	} else {
//		datas = "account=" + $("#account").val() 
//			//	+ "&orgCode="+ $("#orgCode").val() 
//		        + "&areaCode=" + $("#areaCode").val()
//		        + "&userType=" + $("#userType").val()
//			//	+ "&hPersonId=" + $("#hPersonId").val()
//				+ "&roleIdList=" + $("#roleId").val() 
//				+ "&name=" + $("#name").val()
//				+ "&remark="+ $("#remark").val();
//	};
//	alert(11111);
//	$.ajax({
//				url : url,
//				type : "post",
//				data : datas,
//				success : function(data) {
//				alert(22222222);
//				var obj = jQuery.parseJSON(data);
//			//	alert(obj);
//					subSuccess(obj);
//				},
//				error : function(data) {
//					ajaxError(data);
//			}
//			});
//}

/**
 * 重写提交数据成功后的回调函数
 */
function subSuccess(data) {
//	alert(33333333);
//	alert(data.success);
	if (data.success) {
	//	top.$.ligerDialog.success(data.data);
			alert(data.data);
		//	refreshDatagrid();
	   saveSuccessCallback(data);
	} else {
		alert("该账号已存在！！");
		$("#account").val("");
		$("#roleId").val("");
		$("#roleIdShow").val("");
		$("#userType").val("");
		$("#userTypeShow").val("");
		$("#areaCode").val("");
		$("#areaCodeShow").val("");
		$("#remark").val("");
//		top.$.ligerDialog.error(data.data);
 	}
}

function initSuccessCallback(jsonobj) {
	$("#hPersonId").val(jsonobj.hPersonId);
}

//刷新父列表
function saveSuccessCallback(json){
	/*var frames0 = window.parent.window.document.getElementsByTagName("iframe")[0];
	var frames1 = window.parent.window.document.getElementsByTagName("iframe")[1];
	var f1 = frames1.contentWindow.grid;
	var f0 = frames0.contentWindow.grid;
	if(typeof f1 != 'undefined'){         
   		f1.loadServerData();
	}else if(typeof f0 != 'undefined'){
		f0.loadServerData();
	}
	parent.$.ligerDialog.close();
	parent.$(".l-dialog,.l-window-mask").remove();*/
	/*var obj = new Object();
	obj.success = true;
	window.returnValue = obj;
	window.close();*/
	refreshDatagrid();
}
