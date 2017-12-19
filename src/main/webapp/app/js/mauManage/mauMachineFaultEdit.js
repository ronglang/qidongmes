
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
				id:{required:true},
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitMauMachineFault();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
    });  

/**
 * 提交数据
 */
function submitMauMachineFault() {
	var parm = "";
	
		//	加入查询参数，进行update参数
	var id=$("#hideId").val();
	var repairBy=$("#repairBy").val();
	var remark=$("#remark").val();
	var status=$("#status option:selected").val();
		if(repairBy){
			parm="id=" + id+"&repairBy="+repairBy;
		}else {
			parm = "id=" + id+"&remark="+remark+"&status="+status;
		}
		
	var url="rest/mauMachineFaultManageAction/updateMachineFault?"+parm;
	$.post(url,{},function(json){
		var url=basePath + "rest/" + routeName + "Action/getFaultPage";
		$.ligerDialog.success('保存成功');
		parent.$.ligerDialog.close(); //关闭弹出窗
		parent.window.getDataGrid(url);//调父窗口某个方法
		parent.$(".l-dialog,.l-window-mask").remove(); //关闭弹出层
	},"json");
}



/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess(json) {
	if (json.success) {
		alert("保存成功");
		if(json.data.id!=null)
			row_id = json.data.id;
	} else {
		alert("保存失败");
	}
}
