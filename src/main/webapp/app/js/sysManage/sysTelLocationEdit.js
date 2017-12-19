
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
				CODE:{rangelength:[0,20]},NAME:{rangelength:[0,50]},GENDER:{rangelength:[0,20]},NATION:{rangelength:[0,20]},ID_NO:{rangelength:[0,20]},TEL:{rangelength:[0,200]},ADDRESS:{rangelength:[0,64]},LONGITUDE:{rangelength:[0,32]},LATITUDE:{rangelength:[0,32]},
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitSysTelLocation();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        var GENDER_dataGrid = [{ id: '1', name: '男'},{ id: '0', name: '女'},]; $("#GENDER").ligerRadioList({data:GENDER_dataGrid,textField: 'name'});
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
    });  

/**
 * 提交数据
 */
function submitSysTelLocation() {
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
