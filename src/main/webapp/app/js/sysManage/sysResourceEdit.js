
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
				click:{rangelength:[0,255]},desktop_url:{rangelength:[0,255]},display:{rangelength:[0,255]},icon_css:{rangelength:[0,255]},image:{rangelength:[0,255]},image128:{rangelength:[0,255]},image256:{rangelength:[0,255]},image32:{rangelength:[0,255]},image48:{rangelength:[0,255]},image64:{rangelength:[0,255]},isref:{rangelength:[0,255]},istext:{rangelength:[0,255]},name:{rangelength:[0,255]},pid:{digits:true},r_code:{rangelength:[0,255]},refid:{rangelength:[0,255]},remark:{rangelength:[0,255]},sort:{number:true},type:{digits:true},url:{rangelength:[0,255]},width:{rangelength:[0,255]},win_wh:{rangelength:[0,255]},
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitSysResource();
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
function submitSysResource() {
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
		alert("保存成功");
		if(json.data.id!=null)
			row_id = json.data.id;
	} else {
		alert("保存失败");
	}
}
