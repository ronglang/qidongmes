
$(function ()
    {
        $.metadata.setType("attr", "validate");
        var v = $("#"+routeName).validate({
            debug: true,
            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.ligerTip({ content: lable.html(), target: element[0] }); 
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().ligerTip({ content: lable.html(), target: element[0] });
                }
                else
                {
                    lable.appendTo(element.parents("td:first").next("td"));
                }
            },
            rules: {
				
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitSysArea();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        var swfVersionStr = "11.1.0";
	 var xiSwfUrlStr = "playerProductInstall.swf";
	 var params = {};
	
	params.quality = "high";
	
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	 params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = "Comand_Common";
	attributes.name = "Comand_Common";
	attributes.align = "middle";
	swfobject.embedSWF(
			basePath + "flexmap/Comand_Common.swf", "flashContent", 
			"100%", "100%", 
			swfVersionStr, xiSwfUrlStr, 
			flashvars, params, attributes);
		swfobject.createCSS("#flashContent", "display:block;text-align:left;");
            
		
 
	    creatTreeDiv("pcode");
		initTreeCustom("pcode","sysArea","normal","","",villageCodeTreeSetting);
		
		if(areaCode){
			var treeObj = $.fn.zTree.getZTreeObj("pcodeContentTree");
			if(treeObj){
				var node = treeObj.getNodeByParam("id",areaCode);
				if(node){
					treeObj.selectNode(node,false);
					//设置选中节点后右边编辑内容的载入
					myTreeClick(event,"pcodeContentTree",node,true);
				}
			}
		}
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
    });  

function saveGra(str){
	if(str.split(",").length>1){
		alert(str.split(",")[0]);
		$("#latitude").val(str.split(",")[0]);$("#longitude").val(str.split(",")[1]);
	}
}

/**
 * 提交数据
 */
function submitSysArea() {
	var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} 
	pub_save(routeName,parm,false);
}

/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess(json) {
	if (json.success) {
		//	top.$.ligerDialog.error("保存成功");
			alert("保存成功");
			refreshDatagrid();
		} else {
			var msg = "数据保存失败！";
			if(json.msg) {	
				msg = json.msg;
			}
			top.$.ligerDialog.error(msg);
		}
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
			onClick: myTreeClick
		}
};

function myTreeClick(e, treeId, treeNode) {
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
	
	var len = (h+"").length;
	/*if(len != 9){
		top.$.ligerDialog.warn("请选择乡镇");
	}*/
	//initTreeCustom("poorIdnumber","basicdataPoorhousehold","normal","areaCode",h,idNumberTreeTreeSetting,true);
}
