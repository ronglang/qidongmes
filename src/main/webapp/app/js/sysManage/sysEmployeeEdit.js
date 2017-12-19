var uploadurl = "";var uploadurlorigname = "";
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
                submitSysEmployee();
            }
        });
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
//    countSelectNum();
	//creatTreeDiv("orgCode");
//	initTree("orgCode", "sysOrg", "normal", "", "");
     //   creatTreeDiv("buildYear");
	//	initTree("buildYear","sysCommdic","radio","clas","年度");

	creatTreeDiv("zzmm");
	initTree("zzmm", "sysCommdic", "common", "clas", "政治面貌");
	
	creatTreeDiv("health");
	initTree("health", "sysCommdic", "common", "clas", "健康状况");
	
	creatTreeDiv("sex");
	initTree("sex", "sysCommdic", "common", "clas", "性别");
	
	creatTreeDiv("education");
	initTree("education", "sysCommdic", "common", "clas", "学历");
	
//	creatTreeDiv("degree");
//	initTree("degree", "sysDictionary", "radio", "PID", "C1024");
	
	creatTreeDiv("nation");
	initTree("nation", "sysCommdic", "common", "clas", "民族");
	
	creatTreeDiv("userType");
	initTree("userType", "sysCommdic", "common", "clas", "人员类型");
	
	//creatTreeDiv("maritalStatus");
	//initTree("maritalStatus", "sysDictionary", "radio", "PID", "C1023");
	
	//creatTreeDiv("level");
	//initTree("level", "sysDictionary", "radio", "PID", "C1025");
	//creatTreeDiv("training");
//	initTree("training", "sysDictionary", "radio", "PID", "C1001");
	//creatTreeDiv("oneducation");
	//initTree("oneducation", "sysDictionary", "radio", "PID", "C015");

       if (row_id) {
			var parm = "id="+row_id;
			//doMainAfterReady();
		//	pub_initForm(routeName,parm);
			pub_initUpload(routeName,parm);
       }
    });  

/**
 ** 每加载完一个下拉框，总计数减1，加载完成后，执行表单赋值
**/
//function doMainAfterReady(){
//	selectFunNum = selectFunNum-1;
//	if(selectFunNum==0&&row_id!=null){
//		// 执行表单赋值
//		var parm = "id="+row_id;
//		pub_initForm(routeName,parm);
//	}
//}

/**
 * 提交数据
 */
function submitSysEmployee() {
	var parm = "";
	if (row_id) {
		//	加入查询参数，进行update参数
		parm = "id=" + row_id;
	} 
	pub_save(routeName,parm,true);
}
/**
 * 重写提交数据成功后的回调函数
	 */
function submitSuccess(json) {
	if (json.success) {
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

function refreshDatagrid() {
	var frames0 = window.parent.window.document.getElementsByTagName("iframe")[0];
	var frames1 = window.parent.window.document.getElementsByTagName("iframe")[1];
	var f1 = frames1.contentWindow.grid;
	var f0 = frames0.contentWindow.grid;
	if (typeof f1 != 'undefined') {
		//f1.loadServerData();
		f1.reload();
	} else if (typeof f0 != 'undefined') {//f0.loadServerData();
		f0.reload();
	}
	parent.$.ligerDialog.close();
	parent.$(".l-dialog,.l-window-mask").remove();
}
function addUploadData(path, origName) {
	if (isNullObject(path) && isNullObject(origName)) {
		uploadurl += path + ",";
		uploadurlorigname += origName + ",";
	}
}


/**
 * 公用的附件初始化方法重写
 * 
 * @param routeName
 * @param data
 */
function pub_initUpload(routeName,parm){
	var url = basePath + "rest/"+ routeName + "Action/attachmentBy";
	// 提交
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:parm,
        success: function(json){
        	initUploadSuccess(routeName,json);
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}

function initSuccessCallback(json){
	if(json){
		$("#emphotoImg").attr("src",basePath+json.data.picture);
	}
}

/**
 * 删除附件
 * @param id
 * @param routeName
 * @param row_id
 */
function del_attach(id,routeName,row_id){

    var url = basePath + "rest/sysEmployeeManageAction/deletePictureUrl?id="+row_id;
	// 提交
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        success: function(json){
        	delSuccess(json);
        	var parm = "id="+row_id;
        	pub_initUpload(routeName,parm);
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}

/**
 * 默认的初始化成功方法，给附件赋值
 */
function initUploadSuccess(routeName,json){
	// 根据routeName 获取页面对象
	var obj;
	if(json.data!=null){
		// 加载显示附件
		var length = json.data.length;
		var uploadurl_div = $("#uploadurlShow");
		uploadurl_div.empty();
		//清除初始值
		uploadurl = "";
		uploadurlorigname = "";
		for(var i=0; i < length ; i++){
			var obj = json.data[i];
			var url = obj.picture;
			var str=url.split("\\");
			var str2=str[5];
			var origname = str2.substring(21,str2.length);
			uploadurl += url+ ",";
			uploadurlorigname += origname + ",";
			var str3=origname.split(".");
			var type =str3[1];
			obj.id;
			if(type == 'JPG'||type == 'jpg'||type=='PNG'|| type=='png'||type=='BMP'||type=='bmp'||type=='GIF'||type=='gif'||type=='jpeg'||type=='JPEG'){
				var a = $("<a style='margin-right:10px;float:left;' onclick='javascript:openImage(\""+encodeURI(url)+"\");return false;'>"+origname+"</a>");
				a.attr("href","#");
			}else{
				var a = $("<a style='margin-right:10px;float:left;' onclick='javascript:downloadFile(\""+encodeURI(url)+"\");return false;'>"+origname+"</a>");
				a.attr("href","#");
			}
    		a.appendTo(uploadurl_div);
    		
    		if(!uploadurl_div.attr("edit")){
    			var div = $("<div onclick='javascript:del_attach(\""+obj.id+"\",\""+routeName+"\",\""+obj.id+"\")'  " +
    				"class='uploadfile-show'></div>");
    			div.appendTo(uploadurl_div);
    		}
    	}
	}
}

function openImage(url){
	//验证URL是否存在
	var filePath = url;
	$.ajax({
        url: basePath+"rest/fileUploadAction/downloadValidate",
        type:"post",
        dataType:"json",
        async:true,
        data:"filePath="+filePath,
        success: function(json){
        	if(json.success){
        			$.ligerDialog.open({ 
						url: decodeURI(basePath+json.filePath), 
						height: 400,
						width: 600,
						modal:true, 
						buttons: [ 
					                                                                                   
						{text: '关闭', onclick: function (item, dialog) { 
							dialog.close(); 
						} } 
						] });
        	}else{
        		top.$.ligerDialog.warn(json.msg);
        	}
	        
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}
