var genderObj ;
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
				ID:{required:true}
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitSysContacts();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        creatTreeDiv("orgId");
        initTree("orgId","sysOrg","normal","","");
        creatTreeDiv("workType");
        initTree("workType","sysDictionary","normal","PID","C1011");
        creatTreeDiv("position");
        initTree("position","sysDictionary","normal","PID","C32P");
        creatTreeDiv("contactGroupId");
        initTree("contactGroupId","sysContactGroup","normal","","");
        creatTreeDiv("education");
        initTree("education","sysDictionary","normal","pid","C015");
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
       
       var dataGrid = [
                       { id: 'C10210001', name: '男'},
                       { id: 'C10210002', name: '女'}
                       ]; 
       		genderObj = $("#gender").ligerRadioList({
       			data : dataGrid,
       			textField : 'name'
       		});
       		if (!row_id)genderObj.setValue("C10210001");
           });  

/**
 * 提交数据
 */
function submitSysContacts() {
	var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} 
	pub_save(routeName,parm,false);
}

