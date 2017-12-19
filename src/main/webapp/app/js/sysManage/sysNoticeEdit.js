
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
				ID:{required:true},
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitSysNotice();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        
        creatTreeDiv("isShow");
   	    initTree("isShow","sysCommdic","common","clas","是否选项");
        
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
    });  

/**
 * 提交数据
 */
function submitSysNotice() {
	var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} 
	pub_save2(routeName,parm,false);
}

function pub_save2(routeName,parm,isUpload){
	var url = "";
	url = basePath + "rest/"+ routeName + "Action/save";
	if(parm){
		// update
		url = url +"?"+parm;
	}
	// 根据routeName 获取传参
	var data =$("#"+routeName).serializeArray();
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:$.param(data),
        success: function(json){
        	submitSuccess2(json);
        },  
        error: function(json) {
            ajaxError(json);
        }
    });
}

/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess2(json) {
	if (json.success) {
		alert("保存成功");
		if(json.data.id!=null)
			row_id = json.data.id;
		
		if(window.opener){
			window.returnValue = true;
			window.opener.reloadData();
		}
		window.close();
	} else {
		alert("保存失败");
	}
}

/*function closeit(){
    var browserName=navigator.appName;
    if (browserName=="Netscape"){
          window.open('', '_self', '');
          window.close();
    }
    if (browserName=="Microsoft Internet Explorer") { 
          window.parent.opener = "whocares"; 
          window.parent.close(); 
    }
}*/
