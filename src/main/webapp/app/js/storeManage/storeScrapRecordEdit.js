var form;
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
				//id:{required:true},
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitStoreScrapRecord();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
        	$.ligerDialog.warn(v.element($("#txtName")));
        });
        
        
       $('#applyDate').ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',showTime:true}); 
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
       }
       
        
    var selectEdName= $("#materialNameSelect");
    var	url = basePath + "rest/"+ routeName + "Action/getSelectOption";
    $.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:false,
        success: function(json){
        	var matels=[];
        	var obj=json.select;
        	for (var int = 0; int < obj.length; int++) {
        		var matel = new Object();
        		matel.id=obj[int];
        		matel.text=obj[int];
        		matels.push(matel);
			}
        form=selectEdName.ligerForm({
        	inputWidth: 150, 
			labelWidth: 150, 
			space: 70,
			fields: [
			    { display: "", name: "materialName",width:180, 
			    	newline :false,type:"select",comboboxName: "materialName" ,
			    	options:{data:matels}
			    }]
        	});
        },  
        error: function(json) {
            ajaxError(json);
        }
    });

    });  


/**
 * 提交数据
 */
function submitStoreScrapRecord() {
	var url=basePath + "rest/"+ routeName + "Action/getDataGridPage";
	var parm = "";
	var id=$("#myid").val();
	parm = "id=" + id;
	pub_save(routeName,parm,true);
	parent.$.dialog.close();
}



/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess(json) {
	if (json.success) {
		$.ligerDialog.success('保存成功');
		if(json.data.id!=null)
			row_id = json.data.id;
	} else {
		$.ligerDialog.error('保存失败,发生了未知错误！');
	}
}

var uploadurl = "";var uploadurlorigname = "";
function addUploadData(path, origName,saveDataId){
	alert('afds');
	if (isNullObject(path) && isNullObject(origName)) {
		uploadurl += path + ",";
		uploadurlorigname += origName + ",";
	}
	switch(saveDataId){
		case "saveDataId1":
			$("#examinReportUrl").val(path);//上传成功后给户主照片input框赋值
			$("#examinReportUrlpic").attr('src',basePath+path);//上传成功后给户主照片input框赋值
			break;
	}
}
