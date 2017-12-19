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
function submitStoreScrapRecord() {
	var pickListCode= $("#first").find("option:selected").val();
	var objGgxh= $("#se").find("option:selected").val();
	$.ajax({
	       cache: true,
	       type: "POST",
	       url:basePath + "rest/" + routeName + "Action/getOutStorageFromApp?pickListCode="+pickListCode+"&objGgxh="+objGgxh,
	       async: false,
	       dataType:"json",
	       data: $('#storeOutOfStorageRecordManage').serialize(),  
	       error: function(data) {
	    	   $.ligerDialog.warn(data.msg);
	       },
	       success: function(data) {
	    	  
	    	   if(data.msg=="成功"){
	    		   
	    		   $.ligerDialog.success('保存成功');
	    	   }else{
	    		   $.ligerDialog.warn(data.msg); 
	    	   }
	    	
	       }
	       });
		
}


/*$("#storeOutOfStorageRecordManage").ligerForm({
	inputWidth: 150, 
	labelWidth: 60, 
	space: 50,
	fields: [
	    
	    { display: "领料单号", name: "pickListCode", newline: false, type: "select",
	          editor: { type: 'select', url: basePath + "rest/" + routeName + "Action/getPickListCode", parms :{id:0},valueField:'id', textField:'name',
	                 onSelected: function (value)
	                 {
	                       $.post(basePath + "rest/" + routeName + "Action/getGgxh?pickListCode="+pickListCode,{
	                            categorymain:value
	                       },function(data,textStatus){
	                              var combo = liger.get('categorysub2');
	                               if (!combo) return;
	                               combo.set('data', data);
	                        },"json");
	                 }
	           }
	  },
		{ display: "规格型号", name: "objGgxh",comboboxName:'objGgxh', newline: false, type: "select",
		        editor: { type: 'select',valueField:'id', textField:'name'}
		}
		   ,
	    { display: "经办人:", name: "createBy",   newline :false,
	    	type:"text"
	    },
	    { display: "领料数量:", name: "amount",   newline :false,
	    	type:"text"
	    }
	],
});
*/
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



