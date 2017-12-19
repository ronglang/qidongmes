
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
                submitSysDbmetadata();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
	    creatTreeDiv("fieldType");
		initTree("fieldType", "sysDictionary", "normal", "PID", "C1014");

		creatTreeDiv("dbType");
		initTree("dbType", "sysDictionary", "normal", "PID", "C1015");
		var isPk_dataGrid = [ {
			id : '1',
			name : '是'
		}, {
			id : '0',
			name : '否'
		}, ];
		$("#isPk").ligerRadioList({
			data : isPk_dataGrid,
			textField : 'name'
		});
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
    });  

/**
 * 提交数据
 */
function submitSysDbmetadata() {
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
		top.$.ligerDialog.success(json.msg);
	} else {
	if(json.msg) 
		top.$.ligerDialog.error(json.msg);
	}
}
