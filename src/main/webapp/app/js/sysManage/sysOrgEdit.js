
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
                submitSysOrg();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        creatTreeDiv("parentId");
        initTree("parentId","sysOrg","radio","","");
        //initTreeCustomMethod("parentId","sysOrg","normal","","",treeSetting,"nodeTreeList");
        
        creatTreeDiv("areaCode");initTree("areaCode","sysArea","radio","","");
        creatTreeDiv("property");
        initTree("property","sysDictionary","radio","PID","C036");
        creatTreeDiv("position");
        initTree("position","sysDictionary","radio","PID","C1055");
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
			
       }
    });  

    
 var treeSetting = {				
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "orgCode",
				pIdKey: "parentId"
			},
			key: {
			name: "orgName"
			}

		},
		callback: {
			beforeClick: beforeTreeClick,
			onClick: onTreeClick	
		}
};   
    
/**
 * 提交数据
 */
function submitSysOrg() {
	var url = basePath + "rest/" + routeName + "Action/saveOrgInfo";
	$.ajax({
		url : url,
		type : "post",
		data : $("#"+routeName).serializeArray(),
		success : function(data) {
			submitSuccess(data);
			refreshDatagrid();
		},
		error : function(data) {
			ajaxError(data);
		}
	});
	
}


