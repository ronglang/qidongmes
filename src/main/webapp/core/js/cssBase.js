var uplodifyFileType ='*.doc;*.docx;*.xls;*.xlxs;*.ppt;*.pptx;*.txt;*.bmp;*.png;*.gif;*.jpg;*.jpeg;*.rar;*.zip'; // 缺视频格式文件
var uplodifyFileTypeDesc = '请选择doc docx xls xlxs ppt pptx txt bmp png gif jpg jpeg rar zip格式文件';
/**
 * 
 * 
 * url base64加密
 */
function base64_encode(str) {
        var str = CryptoJS.enc.Utf8.parse(str);
        var base64 = CryptoJS.enc.Base64.stringify(str);
        return base64;

}

/**
 * url base64解密
 */
function base64_decode(base64) {
        var words = CryptoJS.enc.Base64.parse(base64);
        var str = words.toString(CryptoJS.enc.Utf8);
        return str;
}
/** ****************************************ajax提交方法************************************************************ */
/**
 * 公用提交方法
 * 
 * @param parm
 *            保存数据前的查询条件。 非空认为是update操作。 空认为是新增add操作。
 * @param routeName
 */
function pub_save(routeName,parm,isUpload){
	var url = "";
	if(isUpload)  url = basePath + "rest/"+ routeName + "Action/saveFormAndAttach";
	else url = basePath + "rest/"+ routeName + "Action/save";
	if(parm){
		// update
		url = url +"?"+parm;
	}
	//alert(url);
	// 根据routeName 获取传参
	var data =$("#"+routeName).serializeArray();
	// 获取上传文件原名称
	var uploadifyDiVs = $("div:[swfuploadify='true']");
	if(uploadifyDiVs.length>0){
		$.each(uploadifyDiVs,function(i,field){
			var name = this.id;
			var origName = this.innerText;		// 目前只支持div中只有一个文件操作
			var obj = {'name':name};
			obj['value'] = origName;
			data.push(obj);
		});
	}
	// 获取富文本编辑器
	var ueditorDiv = $("div:[ueditor='true']");
	if(ueditorDiv.length>0){
		var cols_name = ueditorDiv[0].id;
		var content = UE.getEditor(cols_name).getContent();
		var obj = {'name':cols_name};
		obj['value'] = content;
		data.push(obj);
	}
	if(isUpload){
		if(uploadurl.length > 0 && uploadurlorigname.length > 0){
			uploadurl = uploadurl.substring(0, uploadurl.length-1);
			uploadurlorigname = uploadurlorigname.substring(0, uploadurlorigname.length-1);
		}
		var url_obj = {'name':'uploadurl'};
		url_obj['value'] = uploadurl;
		data.push(url_obj);
		var origname_obj = {'name':'uploadurlorigname'};
		origname_obj['value'] = uploadurlorigname;
		data.push(origname_obj);
	}
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:$.param(data),
        success: function(json){
        	submitSuccess(json);
        },  
        error: function(json) {
            ajaxError(json);
        }
    });
}
/**
 * 默认的提交成功函数 各jsp页面可以自己重写
 */
function submitSuccess(json) {
	/*if(typeof saveSuccessCallback != 'undefined' &&  saveSuccessCallback instanceof Function){         
   		saveSuccessCallback(json);  
	}else{
		refreshDatagrid();
	} */
	
	/*if (json.success) {
		refreshDatagrid();
		top.$.ligerDialog.success("保存成功！");
	} else {
		var msg = "数据保存失败！";
		if(json.msg) {	
			msg = json.msg;
		}
		top.$.ligerDialog.error(msg);
	}*/
	if (json.success) {
		if(json.msg){
			refreshDatagrid();
			//top.$.ligerDialog.success(json.msg);
			$.ligerDialog.success(json.msg);
		}else{
			var result = jQuery.parseJSON(json.responseText);
			if(result){
				//top.$.ligerDialog.success(result.msg);
				$.ligerDialog.success(result.msg);
			}
			else{
				//top.$.ligerDialog.success(json.responseText);
				$.ligerDialog.success(json.responseText);
			}
		}
	}
	else{
		if(json.msg){
			refreshDatagrid();
			//top.$.ligerDialog.error(json.msg);
			$.ligerDialog.error(json.msg);
		}else{
			var result = jQuery.parseJSON(json.responseText);
			if(result){
				//top.$.ligerDialog.error(result.msg);
				$.ligerDialog.error(result.msg);
			}
			else
				//top.$.ligerDialog.error(json.responseText);
				$.ligerDialog.error(json.responseText);
		}
	}
}
function ajaxSuccess(json){
	if (json.success) {
		if(json.msg){
			//top.$.ligerDialog.success( json.msg );
			$.ligerDialog.success( json.msg );
		}else{
			var result = jQuery.parseJSON(json.responseText);
			if(result){
				//top.$.ligerDialog.success(result.msg);
				$.ligerDialog.success(result.msg);
			}
			else{
				//top.$.ligerDialog.success(json.responseText);
				$.ligerDialog.success(json.responseText);
			}
		}
	}
	else{
		//alert(json.msg+','+json.success+','+json.responseText);
		if(json.msg){
			//top.$.ligerDialog.error(json.msg);
			$.ligerDialog.error(json.msg);
		}else{
			var result = jQuery.parseJSON(json.responseText);
			if(result){
				//top.$.ligerDialog.error(result.msg);
				$.ligerDialog.error(result.msg);
			}
			else{
				//top.$.ligerDialog.error(json.responseText);
				$.ligerDialog.error(json.responseText);
			}
		}
	}
}
function ajaxError(json){
	if(json.msg){
		//top.$.ligerDialog.error(json.msg);
		//alert(json.msg);
		$.ligerDialog.error(json.msg);
	}else{
		/*if(json.responseText){
			top.$.ligerDialog.error(json.responseText);
			//alert(json.responseText);
		}else{
			top.$.ligerDialog.error("系统错误，请联系管理员！");
		}*/
		
		var result = jQuery.parseJSON(json.responseText);
		if(result){
			//top.$.ligerDialog.error(result.msg);
			$.ligerDialog.error(result.msg);
		}
		else{
			//top.$.ligerDialog.error(json.responseText);
			$.ligerDialog.error(json.responseText);
		}
	}
}

/**
 * 刷新数据列表
 */
function refreshDatagrid(){
//	var frames0 = window.parent.window.document.getElementsByTagName("iframe")[0];
//	var frames1 = window.parent.window.document.getElementsByTagName("iframe")[1];
//	var f1 = frames1.contentWindow.grid;
//	var f0 = frames0.contentWindow.grid;
//	if(typeof f1 != 'undefined'){         
   		//f1.loadServerData();
   		//if(f1.reload instanceof Function)f1.reload();
//		f1.reload();
//	}else if(typeof f0 != 'undefined'){
		//f0.loadServerData();
		//if(f0.reload instanceof Function)f0.reload();
//		f0.reload();
//	}
	parent.$.ligerDialog.close();
	var obj = parent.window["grid"];
	if(obj){
		parent.window["grid"].reload();
	}
	var m = parent.$(".l-dialog,.l-window-mask");
	if(m){
		parent.$(".l-dialog,.l-window-mask").remove(); 
	}
	//if(typeof frames.contentWindow.reloadData != 'undefined' && frames.contentWindow.reloadData instanceof Function)frames.contentWindow.reloadData();
}

/**
 * 公用的附件初始化方法
 * 
 * @param routeName
 * @param data
 */
function pub_initUpload(routeName,parm){
	var url = basePath + "rest/"+ routeName + "Action/attachment";
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
			var url = obj.uploadurl;
			var origname = obj.uploadurlorigname;
			uploadurl += url+ ",";
			uploadurlorigname += origname + ",";
			
			var type = obj.type ;
			obj.id;
			if(type == 'jpg'|| type=='png'||type=='bmp'||type=='gif'||type=='jpeg'){
				var a = $("<a style='margin-right:10px;float:left;' onclick='javascript:openImage(\""+encodeURI(url)+"\");return false;'>"+origname+"</a>");
				a.attr("href","#");
			}else{
				//var a = $("<a style='margin-right:10px'>"+origname+"</a>");
				//a.attr("href",basePath+url);
				
				var a = $("<a style='margin-right:10px;float:left;' onclick='javascript:downloadFile(\""+encodeURI(url)+"\");return false;'>"+origname+"</a>");
				a.attr("href","#");
			}
    		a.appendTo(uploadurl_div);
    		
    		if(!uploadurl_div.attr("edit")){
    			var div = $("<div onclick='javascript:del_attach(\""+obj.id+"\",\""+routeName+"\",\""+obj.bid+"\")'  " +
    				"class='uploadfile-show'></div>");
    			div.appendTo(uploadurl_div);
    		}
    	}
	}
}

/*******************************************************************************
 * 打开图片
 * 
 * @param url
 */
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
						height: 500,
						width: 700,
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


/**
 * 下载附件
 * @param {} url
 */
function downloadFile(filepath){
	//验证URL是否存在
	$.ajax({
        url: basePath+"rest/fileUploadAction/downloadValidate",
        type:"post",
        dataType:"json",
        async:true,
        data:"filePath="+filepath,
        success: function(json){
        	if(json.success){
				window.location.href= basePath+json.filePath;
        	}else{
        		top.$.ligerDialog.warn(json.msg);
        	}
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}

/*******************************************************************************
 * 删除附件
 * 
 * @param id
 *            附件id
 */
function del_attach(id,routeName,row_id){

    var url = basePath + "rest/sysAttachmentManageAction/deleteAttachment?ids="+id;
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
 * 公用的初始化方法
 * 
 * @param routeName
 * @param data
 */
function pub_initForm(routeName,parm){
	if(!isInit){
		return;
	}
	isInit =false;
	var url = basePath + "rest/"+ routeName + "Action/detail";
	// 提交
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:parm,
        success: function(json){
        	initSuccess(routeName,json);
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}
/**
 * 默认的初始化成功方法，给form对象赋值
 */
function initSuccess(routeName,json){
	// 根据routeName 获取页面对象
	var obj;
	if(json.data!=null){
		var resObj = json.data;
		$.each(resObj,function(i,field){
			//if(($("#"+i).length>0) && field!=null && field !=""){
			//number类型不能用0与''相比.
			if( ($("#"+i).length>0) && field!=null && ( (typeof(field)!='number' && field !='' ) || (typeof(field)=='number') ) ){
				obj = $("#"+i);
				if(typeof(field)=="boolean") field = Number(field);
				if(obj[0].tagName == "INPUT" || obj[0].tagName == "TEXTAREA"){
					if(obj.attr("ztree")){
						var zTree = $.fn.zTree.getZTreeObj(i+"ContentTree");
						field = ""+field;
						var va = field.split(",");
						var show = "";
						
						for(var j=0; j<va.length; j++){
							if(zTree)var node = zTree.getNodeByParam("id",va[j]);
							if(!node) return;	
							show += node.name + ",";
						}
						if (show.length > 0 ) show = show.substring(0, show.length-1);
						obj.val(field);	
						var showObj =  $("#"+i+"Show");
						showObj.val(show);
					}else if(obj.attr("ltree"))
					{
						window[i+"Sel"].setValue(field);
					}
					else{
						obj.val(field);
					}
				}else if(obj[0].tagName == "DIV"){
					if(obj.attr("ueditor")){
						 var r=decodeURIComponent(encodeURI (field));
						 if(r)
							UE.getEditor(""+i).execCommand('insertHtml',r);
						// 给ueditor赋值
		        		/*UE.getEditor(""+i).addListener("ready", function () {
		        	        // editor准备好之后才可以使用
		        			UE.getEditor(""+i).setContent(contentUediter);
		        		});*/
		        		
					}else if(typeof(liger.get(i))!="undefined"){
						liger.get(i).setValue(field);
					}
				}else if(obj[0].tagName == "LABEL"){
					obj.html(field);
				}
			}
			//判断无id有name的checkbox、radio.根据value设置选中
			else if($('input[name='+i+']').length>0 && field!=null && field !=""){
				obj = $('input[name='+i+']');
				if(obj[0].tagName == "INPUT" || obj[0].tagName == "TEXTAREA"){
					if(obj.attr("checkbox")){
						field = ""+field;
						var va = field.split(",");
						for(var j=0; j<va.length; j++){
							//$("input:checkbox[value='"+va[j]+"']").attr('checked','true');
							//$("input:checkbox[value='"+va[j]+"']").ligerComboBox().setValue(true);
							if(va[j] && $('input[name="'+i+'"]:checkbox[value="'+va[j]+'"]') ){
								var o = $('input[name="'+i+'"]:checkbox[value="'+va[j]+'"]').ligerCheckBox();
								if( o ){
									o.setValue(true);
								}
							}
						}
					}
					if(obj.attr("radio")){
						field = ""+field;
						//$("input:checkbox[value='"+va[j]+"']").attr('checked','true');
						//$("input:checkbox[value='"+va[j]+"']").ligerComboBox().setValue(true);
						//$('input[name="'+i+'"]:radio[value="'+field+'"]').click();
						//$('input[name="'+i+'"]:radio[value="'+field+'"]').ligerComboBox().updateStyle()
						//$('input[name="'+i+'"]:radio[value="'+field+'"]').ligerComboBox().setValue(true);
						//alert(i+','+field);
						if(field && $('input[name="'+i+'"]:radio[value="'+field+'"]').ligerRadio() ){
							$('input[name="'+i+'"]:radio[value="'+field+'"]').ligerRadio().setValue(true);
							//$('input[name="'+i+'"]:radio[value="'+field+'"]')[0].checked=true;
						}
					}
				}
			}
		});
	}
	
	if(typeof initSuccessCallback != 'undefined' &&  initSuccessCallback instanceof Function){         
   		initSuccessCallback(json);  
	}  
}

/**
 * 删除
 */
function pub_del(routeName) {
	var ids = f_getChecked();
	if (ids.length == 0) {
		//alert("请选择要删除的数据");
		//top.$.ligerDialog.warn("请选择要删除的数据！");
		$.ligerDialog.warn("请选择要删除的数据！");
		return;
	}
	var deleteCount=ids.split(",").length;
	// top.$.ligerDialog.confirm('确定要删除选定的数据吗？', function(yes) {
		var yes = window.confirm("确定要删除选定的 "+deleteCount+" 条数据吗?");
				if (yes) {
					var list_ck = $("input:[name='list_ck']");
					/*var ids = f_getChecked();
					if (ids.length == 0) {
						//alert("请选择要删除的数据");
						top.$.ligerDialog.warn("请选择要删除的数据！");
						return;
					}*/

					var url = basePath + "rest/" + routeName
							+ "Action/delete?ids=" + ids;
					// 提交
					$.ajax({
								url : url,
								type : "post",
								dataType : "json",
								async : true,
								success : function(json) {
									delSuccess(json);
									checkedData = [];//删除成功后清空选中数组
								},
								error : function(json) {
									ajaxError(json);
								}
							});
				 
				}
			// });
}

/**
 * 默认的提交成功函数 各jsp页面可以自己重写
 */
function delSuccess(json) {
	if(typeof grid != 'undefined'){
		//grid.set({data:[]});
   		//grid.loadServerData();
   		grid.reload();
	}
	if (json.success) {
		//top.$.ligerDialog.success("删除成功！");
		$.ligerDialog.success("删除成功！");
	} else {
		var msg = "删除失败！";
		if(json.msg){
			msg = json.msg;
		}
		//top.$.ligerDialog.error(msg);
		$.ligerDialog.error(msg);
	}
}

/**
 * 公用的初始化列表
 * 
 * @param routeName
 * @param currentPage
 *            存放在各自jsp页面上
 * @param pageSize
 *            存放在各自jsp页面上
 */
function pub_initList(routeName, orderName, orderRule){
	if("undefined"==typeof(orderName)) orderName = "";
	if("undefined"==typeof(orderRule)) orderRule = "";
	var url =  basePath + "rest/"+ routeName + "Action/page";
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
       /* data:{pageNo:pageNo,pageSize:pageSize}, */
        async:true,
        success: function(json){
        	if(typeof showTable != 'undefined' &&  showTable instanceof Function){         
   				showTable(json);		// 该方法各页面自己写
			} 
        },
        error: function(json) {
            ajaxError(json);
        }
    });
}

/** ****************************************ajax提交方法结束************************************************************ */
/** ***************************************list开始**************************************************** */

function f_isChecked(rowdata)
{
    if (findChecked(rowdata.id) == -1)
        return false;
    return true;
}

function f_onCheckRow(checked, data)
{
    if (checked) addChecked(data.id);
    else removeChecked(data.id);
}

function f_onCheckAllRow(checked)
{
    for (var rowid in this.records)
    {
        if(checked)
            addChecked(this.records[rowid]['id']);
        else
            removeChecked(this.records[rowid]['id']);
    }
}
/*
 * 利用onCheckRow将选中的行记忆下来，并利用isChecked将记忆下来的行初始化选中
 */
var checkedData = [];
function findChecked(id)
{
    for(var i =0;i<checkedData.length;i++)
    {
        if(checkedData[i] == id) return i;
    }
    return -1;
}
function addChecked(id)
{
    if(findChecked(id) == -1)
        checkedData.push(id);
}
function removeChecked(id)
{
    var i = findChecked(id);
    if(i==-1) return;
    checkedData.splice(i,1);
}
function f_getChecked()
{
    return checkedData.join(',');
}

/** ******************************************list结束**************************************************************** */
/** ****************************************ztree以及下拉菜单的通用方法************************************************************ */
// 普通树的配置
var treeSetting = {				
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeTreeClick,
			onClick: onTreeClick	
		}
};
// checkbox树的配置
var checkTreeSetting = {
		check: { // check
			enable: true,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeTreeClick,
			onCheck: onTreeCheck
		}
};
// radio树的配置
var radioTreeSetting = {
		check: { 
			enable: true,
			chkStyle: "radio",
			radioType: "all" 
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeTreeClick,
			onCheck: onTreeCheck
		}
};

/** ztree的方法* */
function beforeTreeClick(treeId, treeNode) {
	//var check = (treeNode && !treeNode.isParent);
	//if (!check) top.$.ligerDialog.warn("只能选择城市...！");//alert("只能选择城市...");
	
	//有些业务需要选择父节点
	var check = (treeNode);
	return check;
}
	
function onTreeClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId),
	nodes = zTree.getSelectedNodes(),
	v = "";
	h = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		h += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (h.length > 0 ) h = h.substring(0, h.length-1);
	if(treeId.indexOf("ContentTree")>-1){
		var inputId = treeId.split("ContentTree")[0];
		var cityObj = $("#"+inputId);
		cityObj.attr("value", h);
		var cityObjSHOW = $("#"+inputId+"Show");
		cityObjSHOW.attr("value", v);
		setName(v);
	}
}

function onTreeCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId),
	nodes = zTree.getCheckedNodes(true),
	v = "";
	h = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		h += nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (h.length > 0 ) h = h.substring(0, h.length-1);
	if(treeId.indexOf("ContentTree")>-1){
		var inputId = treeId.split("ContentTree")[0];
		var cityObj = $("#"+inputId);
		cityObj.attr("value", h);
		var cityObjSHOW = $("#"+inputId+"Show");
		cityObjSHOW.attr("value", v);
		setName(v,inputId);
	}
}

/* 赋值下拉框选中值的显示名称，在页面的方法中覆盖 */
function setName(v,inputId){
	$("#"+inputId+"Show").focus();//下拉框赋值后获取一次焦点
}

/*******************************************************************************
 * 创建装载tree的div
 * 
 * @colName 字段名 <div id="cityContent" style="display:none; position: absolute;">
 *          <ul id="cityContentTree" class="ztree" style="margin-top:0; width:160px;">
 *          </ul>
 *          </div>
 */
function creatTreeDiv(colName){
	
	var parentdiv=$('<div style="display:none; position: absolute;z-index:999;"></div>');        // 创建一个父div
	parentdiv.attr('id',colName+'Content');
	var childul = $('<ul  class="ztree" style="margin-top:0; width:160px;height:300px;"></ul>');
	childul.attr('id',colName+'ContentTree');
	childul.appendTo(parentdiv); 
	parentdiv.appendTo('body');
}
/**
 * 显示下拉树菜单（根据input的位置显示） tree的设置参数和节点存放在各自页面上
 * 
 * @inputId 存放下拉框值的input框id
 * @treeDivId 装载树的div Id
 * @buttonId 显示下拉树的按钮
 */
function showMenu(colName) {
	var input_Obj = $("#"+colName+"Show");
	var input_Offset = $("#"+colName+"Show").offset();
	
	// 给tree赋值
	var zTree = $.fn.zTree.getZTreeObj(colName+"ContentTree");
	var value = $("#"+colName).val();
	if(value!=""){
		var va = value.split(",");
		for(var j=0; j<va.length; j++){
			if(va[j]){
				var node = zTree.getNodeByParam("id",va[j]);
				if(node)node.checked = true;
				zTree.updateNode(node);
			}
		}
	}
	if(event && event.clientY<235)//判断弹出框位置是否太靠下，如果太靠下则减少下拉框高度，并向上弹出
	{
		$("#"+colName+"Content").css({left:input_Offset.left + "px", top:input_Offset.top + input_Obj.outerHeight() + "px"}).slideDown("fast");
	}else{
		$("#"+colName+"ContentTree").css("height","200px");
		$("#"+colName+"Content").css({left:input_Offset.left + "px", top:(input_Offset.top - 200) + "px"}).slideDown("fast");
	}
	

	$("body").bind("mousedown",{colName:colName}, onBodyDown);
	
}
/**
 * 给标题框赋值
 * @param colName
 * @returns
 */
function showMenuNew(colName) {
	var input_Obj = $("#"+colName+"Show");
	var input_Offset = $("#"+colName+"Show").offset();
	
	// 给tree赋值
	var zTree = $.fn.zTree.getZTreeObj(colName+"ContentTree");
	var value = $("#"+colName).val();
	if(value!=""){
		var va = value.split(",");
		for(var j=0; j<va.length; j++){
			var node = zTree.getNodeByParam("id",va[j]);
			if(node)node.checked = true;
			zTree.updateNode(node);
		}
	}
	$("#"+colName+"Content").css({left:input_Offset.left + "px", top:input_Offset.top + input_Obj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown",{colName:colName}, onBodyDown);
	var helpVillageWorkteamVal = "";
	$('#helpVillageWorkteamIdShow').bind('input propertychange', function() {
		helpVillageWorkteamVal = $("#helpVillageWorkteamIdShow").val();
		if(helpVillageWorkteamVal!=""){
			helpVillageWorkteamVal = helpVillageWorkteamVal.split("工作队").join("总体规划");
			$("#title").val(helpVillageWorkteamVal);
		 }
	});
	
//	if(){
//		
//	}
	
}


/**
 * 隐藏下拉树
 * 
 * @treeDivId 装载树的div Id
 */
function hideMenu(treeDivId) {
	$("#"+treeDivId).fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event){
	var btnId =  event.data.colName + "Btn";
	var treeDivId =  event.data.colName + "Content";
	if (!(event.target.id == btnId || event.target.id == treeDivId ||
			$(event.target).parents("#"+treeDivId).length>0)) {
		hideMenu(treeDivId);
	}
}

/**
 * 初始化加载树
 * 
 * @colName 装载树的div的id（创建方法参考creatTreeDiv）
 * @nodeTBName 被加载的路径名
 * @treeType 树的选择类型：checkbox ；radio ；normal
 * @queryName 查询条件
 * @queryValue 查询参数
 * @urlAndWhere 自定义请求url全路径（需加上条件）
 * @treeSettingObj 自定义树的配置
 */
// initTree("planDutyPerson","sysEmployee","checkbox","","");
function initTree(colName,nodeTBName,treeType,queryName,queryValue,urlAndWhere,treeSettingObj){
	selectFunNum++;
	isInit = false;
	var url,data='';
	if(typeof(urlAndWhere)!= 'undefined'&& urlAndWhere !=null && urlAndWhere.length >0){
		url = urlAndWhere;
	}else{  
		url =  basePath +"rest/"+ nodeTBName + "ManageAction/nodeTree";
		data = "qcolName="+queryName+"&qcolValue="+queryValue;
	}
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:data,
        success: function(json){
          	if(json.data!=null){
          		if(typeof(treeSettingObj)!= 'undefined' && treeSettingObj !=null && treeSettingObj.length>0){
          			$.fn.zTree.init($("#"+colName+"ContentTree"), treeSettingObj, json.data);
          		}else if(treeType =="checkbox"){
          			$.fn.zTree.init($("#"+colName+"ContentTree"), checkTreeSetting, json.data);
          		}else if(treeType =="radio"){
          			$.fn.zTree.init($("#"+colName+"ContentTree"), radioTreeSetting, json.data);
          		}else{
          			$.fn.zTree.init($("#"+colName+"ContentTree"), treeSetting, json.data);
          		}
        	}
          	selectFunNum--;
          	if(typeof(row_id) != "undefined"){
          		doMainAfterReady();
          	}
        },
        error: function(json) {
           // alert('出错啦！');
            top.$.ligerDialog.error("系统错误，请联系管理员！");
            selectFunNum--;
            if(typeof(row_id) != "undefined"){
            	doMainAfterReady();
            }
        }
    });
}
//相比于initTree,多了个data_str参数。乱码问题：包括中文的参数不能放在url中，要放在data. data_str格式：'&p1=a&p2=b'
function initTree_data(colName,nodeTBName,treeType,queryName,queryValue,urlAndWhere,treeSettingObj,data_str){
	selectFunNum++;
	isInit = false;
	var url,data='';
	if(typeof(urlAndWhere)!= 'undefined'&& urlAndWhere !=null && urlAndWhere.length >0){
		url = urlAndWhere;
	}else{  
		url =  basePath +"rest/"+ nodeTBName + "ManageAction/nodeTree";
		data = "qcolName="+queryName+"&qcolValue="+queryValue;
	}
	data = data+data_str;
	
	$.ajax({
		url: url,
		type:"post",
		dataType:"json",
		async:true,
		data:data,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success: function(json){
			if(json.data!=null){
				if(typeof(treeSettingObj)!= 'undefined' && treeSettingObj !=null && treeSettingObj.length>0){
					$.fn.zTree.init($("#"+colName+"ContentTree"), treeSettingObj, json.data);
				}else if(treeType =="checkbox"){
					$.fn.zTree.init($("#"+colName+"ContentTree"), checkTreeSetting, json.data);
				}else if(treeType =="radio"){
					$.fn.zTree.init($("#"+colName+"ContentTree"), radioTreeSetting, json.data);
				}else{
					$.fn.zTree.init($("#"+colName+"ContentTree"), treeSetting, json.data);
				}
			}
			selectFunNum--;
			if(typeof(row_id) != "undefined"){
				doMainAfterReady();
			}
		},
		error: function(json) {
			// alert('出错啦！');
			top.$.ligerDialog.error("系统错误，请联系管理员！");
			selectFunNum--;
			if(typeof(row_id) != "undefined"){
				doMainAfterReady();
			}
		}
	});
}

function doMainAfterReady(){
//	selectFunNum = selectFunNum-1;
	if(selectFunNum==0&&row_id!=null){
		// 执行表单赋值
		isInit = true;
		var parm = "id="+row_id;
		pub_initForm(routeName,parm);
		isInit = false;
	}
}
var isInit=true;//控制是否执行初始化方法
var selectFunNum = 0;//记录页面上下拉框的数量
//function countSelectNum(){
//	var obj = $("input[ztree='true']");		
//	if(obj.length >0){
//		isInit = false;
//		selectFunNum = obj.length/2;
//		//alert(selectFunNum);
//	}	
//}

/**
 * 自定义初始化树
 * 
 * @param {}
 *            colName
 * @param {}
 *            nodeTBName
 * @param {}
 *            treeType
 * @param {}
 *            queryName
 * @param {}
 *            queryValue
 * @param {}
 *            treeSettingObj
 * @param {}
 *            ignoreDoMainAfterReady 跳过doMainAfterReady方法,关联下拉框非首级下拉框调用时设为true，避免多次初始化页面
 */
function initTreeCustom(colName,nodeTBName,treeType,queryName,queryValue,treeSettingObj, ignoreDoMainAfterReady){
	isInit = false;
	selectFunNum++;
	var url =  basePath +"rest/"+ nodeTBName + "ManageAction/nodeTree";
	var deafauleTreeSetting ;
	if (treeSettingObj) {
		deafauleTreeSetting = treeSettingObj;
	} else if (treeType == "checkbox") {
		deafauleTreeSetting = checkTreeSetting;
	} else if (treeType == "radio") {
		deafauleTreeSetting = radioTreeSetting;
	} else {
		deafauleTreeSetting = treeSetting;
	}
	var treeDate = null;
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:false,
        data:"qcolName="+queryName+"&qcolValue="+queryValue,
        success: function(json){
          	if(json.data!=null){
          		$.fn.zTree.init($("#"+colName+"ContentTree"), deafauleTreeSetting, json.data);
        	}
          	selectFunNum--;
          	if(typeof(row_id) != "undefined"||!ignoreDoMainAfterReady){
          		doMainAfterReady();
          	}
          	treeDate = json.data;
        },
        error: function(json) {
           // alert('出错啦！');
        	 top.$.ligerDialog.error("系统错误，请联系管理员！");
        	 selectFunNum--;
        	 if(typeof(row_id) != "undefined"||!ignoreDoMainAfterReady){
        		 doMainAfterReady();
        	 }
        }
    });
	return treeDate;
}

function initTreeCustomMethod(colName,nodeTBName,treeType,queryName,queryValue,treeSettingObj,treeMethod,ignoreDoMainAfterReady){
	isInit = false;
	selectFunNum++;
	var url =  basePath +"rest/"+ nodeTBName + "ManageAction/"+treeMethod;
	var deafauleTreeSetting ;
	if (treeSettingObj) {
		deafauleTreeSetting = treeSettingObj;
	} else if (treeType == "checkbox") {
		deafauleTreeSetting = checkTreeSetting;
	} else if (treeType == "radio") {
		deafauleTreeSetting = radioTreeSetting;
	} else {
		deafauleTreeSetting = treeSetting;
	}
	
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:false,
        data:"qcolName="+queryName+"&qcolValue="+queryValue,
        success: function(json){
          	if(json.data!=null){
          		$.fn.zTree.init($("#"+colName+"ContentTree"), deafauleTreeSetting, json.data);
        	}
          	selectFunNum--;
          	if(typeof(row_id) != "undefined"||!ignoreDoMainAfterReady){
          		doMainAfterReady();
          	}
          	
        },
        error: function(json) {
           // alert('出错啦！');
        	 top.$.ligerDialog.error("系统错误，请联系管理员！");
        	 selectFunNum--;
        	 if(typeof(row_id) != "undefined"||!ignoreDoMainAfterReady){
        		 doMainAfterReady();
        	 }
        }
    });
}


/** ****************************************ztree以及下拉菜单的通用方法结束********************************************************* */
function isNullObject(obj){
	if(typeof(obj)!="undefined" && obj!=null && obj !="")
		return true;
	else
		return false;
	
}
/** ****************************************上传文件控件的通用方法结束********************************************************* */

function initUploadify(colName){
	
	// 初始化上传控件
	 $("#"+colName+"Sub").uploadify({
	 	auto :true,
	 	buttonText : '上传',
	 	buttonCursor	:'hand',
	 	fileSizeLimit : '500MB',
	 	button_image_url : basePath + 'core/img/upload.png',
	 	fileTypeExts : uplodifyFileType,	// 可自行定义限制上传文件格式
	 	fileTypeDesc : uplodifyFileType,  // 可自行定义限制上传文件格式提示
	 	requeueErrors : true,
        swf           : basePath+'core/js/uploadify/uploadify.swf',
        uploader      : basePath+'Upload/uploadFilePub',
        width         : 120,
        removeTimeout : 0,
        onUploadSuccess : function(file, data, response){
	       	if(response){
	       		// var dataObject = eval("("+data+")");
	       		var dataObject = JSON.parse(data);
	       		$("#"+colName).val(dataObject.uploadU);
	       		$("#"+colName+"origname").empty();// 路基
	       		var a = $("<a >"+dataObject.originalName+"</a>");
	       		if(row_id!=null){
	       			a.attr("href",basePath+routeName+"/"+colName+"OrigNamedownFile?id="+row_id);
	       		}
	       		a.appendTo($("#"+colName+"origname"));
	       	}
       }
   });
}

/*******************************************************************************
 * 下载文件
 * 
 * @param urlInput
 *            装载文件路径的input
 * @param originalNInput
 *            装载路径的input
 */
function downLoad(urlInput,originalNInput){

	var fileUrl = $("#"+urlInput).val();
	var original = $("#"+originalNInput).val();
	
	var url =  basePath +  "Upload/downFile";
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:{fileUrl: fileUrl,isOnLine:false},
        success: function(json){
        	
        }
    });
}


/*******************************************************************************
 * 清除数据库中对应路径记录
 * 
 * @param urlInput
 */
function deleteUploadFile(urlInput,originalNInput){
	$("#"+urlInput).val("");
	$("#"+originalNInput).empty();
}

/** ****************************************上传文件控件的通用方法结束********************************************************* */
/**
 * 根据传入时间来获取页面显示的时间数据
 * 
 * @param time
 * @return by yanqingwen
 */
function times(time)
{
	var newsYear = time.substring(0,4);// 年
	var newsMonth = time.substring(5 , 7);// 月
	var newsData = time.substring(8 , 10);// 日
	
	 var d = new Date();// 获取当前年月日
	 var year = d.getFullYear().toString();
	 var month = (d.getMonth() + 1).toString();
	 if(d.getMonth().toString().length == 1){
	 	month = "0"+month;
	 }
	 var data = d.getDate().toString();
	 if(d.getDate().toString().length == 1){
	 	data = "0"+data;
	 }
	 
	 if(newsYear == year && newsMonth == month && newsData == data){
	 	return time.substring(11,16);	// 当天
	 }else if(newsYear == year && newsMonth == month && newsData != data){
	 	return time.substring(5,16);	// 本月
	 }else if(newsYear == year && newsMonth != month && newsData != data){
	 	return time.substring(5,16);	// 今年
	 }else{
	 	return time.substring(0,16);	// 其他
	 }
}
/**
 * 表单验证
 * 
 * @param id
 * @param opt
 */
function subCheck(id,opt){
	pub_CheckForm(id,opt);
	for(var pro in errorMsg){
        for(p in errorMsg[pro]){
            $("#label"+pro).text(errorMsg[pro][p]).css("color","red");
            break;
        }
    }
	 var size = 0,key;
     for (key in errorMsg) {
         if (errorMsg.hasOwnProperty(key)) size++;
     }
     if(size>0){
         return false;
     }
     return true;
}
/**
 * 将两个时间串起来 如果同一天的时间 省去年月日，同一月 省去年
 * 
 * @param startTime
 *            开始时间 2014-10-11 09:12
 * @param endTime
 *            结束时间
 */
function StartAndEndTime(startTime,endTime){
    var rs="";
    if(startTime.substring(0,10)==endTime.substring(0,10)){// 同一天的
        rs = startTime.substring(0,16)+"~"+endTime.substring(11,16);
    }
    else if(startTime.substring(0,7)==endTime.substring(0,7)){// 同一月
        rs = startTime.substring(0,16)+"~"+endTime.substring(5,16);
    }
    else{
        rs = startTime.substring(0,16)+"~"+endTime.substring(0,16);
    }
    return rs;
}

/*
 * iframe高度自适应
 */
function parentiframesize(down) {
	var pTar = null; 
	if (parent.document.getElementById(down)){ 
		pTar = parent.document.getElementById(down); 
	}else{ 
		eval('pTar = ' + down + ';'); 
	} 
	if (pTar && !window.opera){ 
		var height = null;
		// var width = null;
		// begin resizing iframe
		pTar.style.display="block" ;
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){ 
			// ns6 syntax
			height = pTar.contentDocument.body.offsetHeight +20; 
		// width = pTar.contentDocument.body.scrollWidth+20;
		} else if (pTar.Document && pTar.Document.body.scrollHeight){
			// ie5+ syntax
			height = pTar.Document.body.scrollHeight; 
		// width = pTar.Document.body.scrollWidth;
		} 
		if(height<"1200"){
			height = 1200;
		}
		pTar.height = height;
	// pTar.width = width;
	} 
}

function  DateDiff(sDate1,  sDate2){    // sDate1和sDate2是2006-12-18格式
    if(sDate1.constructor==Date){
        sDate1 = sDate1.format("yyyy-MM-dd");
    }
    if(sDate2.constructor==Date){
        sDate2 = sDate2.format("yyyy-MM-dd");
    }
    var aDate  =  sDate1.split("-");
    var  oDate1  =  sDate2.split("-");
    return  parseInt(Math.abs(Date.UTC(aDate[0],aDate[1],aDate[2])  -Date.UTC(oDate1[0],oDate1[1],oDate1[2])  )  /  1000  /  60  /  60  /24) ;   // 把相差的毫秒数转换为天数
}

function setCookie(key,value){
    if(value==undefined || value==null || value=='null'){
        return;
    }
    var domain = document.domain;
    $.cookie(key,value,{expires:365,path:"/",domain:domain});

}


function read_only(){
	$("input[ztree='true']").focus(function(){
				$(this).blur();		
	})
}
function _jquery_initajax(){
	$.ajaxSetup({
		type : 'POST',
		beforeSend:function(evt, request, settings){
			var panel = $("#body");
			panel.css('position', 'relative');
			if (!panel.children('div.datagrid-mask').length){
			    var loadMsg="数据加载中。。。";
				var mask =$('<div class="datagrid-mask" style="display:block;  z-index: 9998;"></div>').appendTo(panel);
				var msg = $('<div class="datagrid-mask-msg" style="display:block;left:50%; z-index: 9999;"></div>').html(loadMsg).appendTo(panel);
				// msg._outerHeight(40);
				msg.css({
					marginLeft: (-msg.outerWidth()/2),
					lineHeight: (msg.height()+'px')
				});
			}
		},
		complete:function(data){
// if("503" == data.status){
// var result = jQuery.parseJSON(data.responseText);
// $.messager.show({title:'消息提示',msg:result.msg});
// }
			if("504" == data.status){
				window.location.href = basePath+'/login.jsp';
			}
			
			window.setTimeout(function() {
				try {
					var body = $("#body");
					body.children('div.datagrid-mask-msg').remove();
					body.children('div.datagrid-mask').remove();
				} catch (e) {
				}
			}, 500);
		}
		
	});
}

//列表页面查询和重置调用方法
function reloadData(colNameArr,isReset){
	var i=0;
	var pa;
	var value;
	if(!isReset){
		pa = $("#"+routeName+"ListForm").serialize();
		grid.loadServerData(decodeURIComponent(pa,true));
		for(i=0;i<colNameArr.length;i++)
		{
			grid.setParm(colNameArr[i],$("#"+colNameArr[i]).val());
		}
	}else{
		for(i=0;i<colNameArr.length;i++)
		{
			grid.setParm(colNameArr[i],'');
			$("#"+colNameArr[i]).val("");
			if($("#"+colNameArr[i]+"Show"))$("#"+colNameArr[i]+"Show").val("");
		}
		grid.loadServerData();
	}
	grid.changePage("first");
}

//列表页面高级查询
function reloadDataByHql(Hql,hql){
	window.parent.grid.loadServerData(decodeURIComponent(Hql,true));
	//分页查询加载列表
	window.parent.grid.setParm("hql",hql);
	window.parent.grid.changePage("first");
}


$(document).ready(function(){
	_jquery_initajax();
	read_only();
//	countSelectNum();
});

function openSearchPage(routeName,searchType){
	this.$.ligerDialog.open({ 
		url: basePath+"rest/sysUserManageAction/toSearchPage?routeName="+routeName+"&searchType="+searchType, 
		height: 450,
		width: 450,
		modal:false, 
		title:"搜索"
	});

}

/**
 * 获取浏览器种类
 * @returns {String}
 */
function getExplorer() {
	var explorer = window.navigator.userAgent ;
	//ie 
	if (explorer.indexOf("MSIE") >= 0) {
	return "ie";
	}
	//firefox 
	else if (explorer.indexOf("Firefox") >= 0) {
	return "Firefox";
	}
	//Chrome
	else if(explorer.indexOf("Chrome") >= 0){
	return "Chrome";
	}
	//Opera
	else if(explorer.indexOf("Opera") >= 0){
	return "Opera";
	}
	//Safari
	else if(explorer.indexOf("Safari") >= 0){
	return "Safari";
	}
}

//leitao 公用调用高级查询页面方法，没有特殊原因请不要更改
//弹出高级查询页面
//function advancedQueryPage(routeName,frameNumber){
//	this.$.ligerDialog.open({
//		url: basePath+"rest/"+routeName+"Action/toAdvancedQueryPage?actionName="+routeName+"&frameNumber="+frameNumber, 
//		height: 400,
//		width:600,
//		modal:true, 
//		title:"高级查询"
//		});
//}

//清空表单中部分类型的值。需要其它类型请扩展
function clearForm(routeName){
	$('#'+routeName+' input[type="text"]').val('');//一般input.不包括ckeditor,tree
	$('#'+routeName+' input[type="textarea"]').val('');//
	$('#'+routeName+' input[type="number"]').val('');//
	
	$.each($('#'+routeName+' input:checkbox'),function(i,f){//复选框
		var a = $(f);
		if(a && a.ligerCheckBox())
			a.ligerCheckBox().setValue(false);
	});
	
	$.each($('#'+routeName+' input:radio'),function(i,f){//单选框
		var a = $(f);
		if(a && a.ligerRadio())
			a.ligerRadio().setValue(false);
	});
}


/**
 * 显示当前时间的方法
 * 
 $("#times").showDate({ 
 	statics:true, //是否静态显示
	times:true, //是否显示时分秒
	weeks:true, //是否显示星期
	dmark:'/',   //年月日用/隔开
	tmark:':',   //时间用：隔开
	emptys:' ', //时间之间的符号
	sort:'dtw', //按顺序排，默认日期d，时间t，星期w（有6种，dtw、dwt、twd、tdw，wtd、wdt）
});
 *
 */
(function($){

$.fn.showDate = function(options){
	var opts = $.extend({},$.fn.showDate.defaults, options);
	var $showDate = $(this);//滚动元素容器
	
	$showDate.html(nowDate());
	if(!opts.statics){
		setInterval(function(){
			$showDate.html(nowDate());
		},1000);
	}
	
	function nowDate(){
		var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六'); 
	    var time=new Date(); 
	    var year=time.getFullYear();
	    var month=time.getMonth();
	    var date=time.getDate(); 
	    var day=time.getDay(); 
	    var hour=time.getHours(); 
	    var minutes=time.getMinutes(); 
	    var second=time.getSeconds(); 
	    month=parseInt(month)+1;
	    month<10?month='0'+month:month;
	    hour<10?hour='0'+hour:hour; 
	    minutes<10?minutes='0'+minutes:minutes; 
	    second<10?second='0'+second:second; 
	    var now_time= "";
	    switch(opts.sort){
	    	case 'dwt':
	    		if(opts.dates){
		    		now_time = now_time+year+opts.dmark+month+opts.dmark+date+opts.emptys;
			    }
	    		if(opts.weeks){
			    	now_time = now_time+show_day[day];
			    }
			    if(opts.times){
			    	now_time = now_time+hour+opts.tmark+minutes+opts.tmark+second+opts.emptys;
			    }
	    		break;
	    	case 'tdw':
	    		if(opts.times){
			    	now_time = now_time+hour+opts.tmark+minutes+opts.tmark+second+opts.emptys;
			    }
	    		if(opts.dates){
		    		now_time = now_time+year+opts.dmark+month+opts.dmark+date+opts.emptys;
			    }
	    		if(opts.weeks){
			    	now_time = now_time+show_day[day];
			    }
	    		break;
	    	case 'twd':
	    		if(opts.times){
			    	now_time = now_time+hour+opts.tmark+minutes+opts.tmark+second+opts.emptys;
			    }
	    		if(opts.weeks){
			    	now_time = now_time+show_day[day];
			    }
	    		if(opts.dates){
		    		now_time = now_time+year+opts.dmark+month+opts.dmark+date+opts.emptys;
			    }
	    		break;
	    	case 'wtd':
	    		if(opts.weeks){
			    	now_time = now_time+show_day[day];
			    }
	    		if(opts.times){
			    	now_time = now_time+hour+opts.tmark+minutes+opts.tmark+second+opts.emptys;
			    }
	    		if(opts.dates){
		    		now_time = now_time+year+opts.dmark+month+opts.dmark+date+opts.emptys;
			    }
	    		break;
	    	case 'wdt':
	    		if(opts.weeks){
			    	now_time = now_time+show_day[day];
			    }
	    		if(opts.dates){
		    		now_time = now_time+year+opts.dmark+month+opts.dmark+date+opts.emptys;
			    }
	    		if(opts.times){
			    	now_time = now_time+hour+opts.tmark+minutes+opts.tmark+second+opts.emptys;
			    }
	    		
	    		break;
	    	default:
	    		if(opts.dates){
		    		now_time = now_time+year+opts.dmark+month+opts.dmark+date+opts.emptys;
			    }
			    if(opts.times){
			    	now_time = now_time+hour+opts.tmark+minutes+opts.tmark+second+opts.emptys;
			    }
			    if(opts.weeks){
			    	now_time = now_time+show_day[day];
			    }
	    }
	    
	    return now_time;
	}
	
};

$.fn.showDate.defaults = {
		statics:false,//是否静态显示
		dates:true,  //显示年,月,日
		times:true, //显示时分秒
		weeks:true, //显示星期
		dmark:'/',   //年月日用-隔开
		tmark:':',   //时间用：隔开
		emptys:' ', //时间之间的空格
		sort:'dtw', //按顺序排，默认日期d，时间t，星期w（有6种，dtw、dwt、twd、tdw，wtd、wdt）
	};

})(jQuery);

/********************************************************/
/**
 * @classDescription 超级Marquee，可做图片导航，图片轮换，文字轮播
 * @date 2017-10-13
 * @dependence jQuery 1.3.2
 * @DOM
 *  	<div id="marquee">
 *  		<ul>
 *   			<li></li>
 *   			<li></li>
 *  		</ul>
 *  	</div>
 * @CSS
 *  	#marquee {width:200px;height:50px;overflow:hidden;}
 * @Usage
 *  	$('#marquee').marquee(options);
 * @options
 *		distance:200,//一次滚动的距离
 *		duration:20,//缓动效果，单次移动时间，越小速度越快，为0时无缓动效果
 *		time:5,//停顿时间，单位为秒
 *		direction: 'left',//滚动方向，'left','right','up','down'
 *		scrollAmount:1,//步长
 *		scrollDelay:20//时长，单位为毫秒
 *		isEqual:true,//所有滚动的元素长宽是否相等,true,false
 *		loop: 0,//循环滚动次数，0时无限
 *		btnGo:{left:'#goL',right:'#goR'},//控制方向的按钮ID，有四个属性left,right,up,down分别对应四个方向
 *		eventGo:'click',//鼠标事件
 *		controlBtn:{left:'#goL',right:'#goR'},//控制加速滚动的按钮ID，有四个属性left,right,up,down分别对应四个方向
 *		newAmount:4,//加速滚动的步长
 *		eventA:'mouseenter',//鼠标事件，加速
 *		eventB:'mouseleave',//鼠标事件，原速
 *		navId:'#marqueeNav', //导航容器ID，导航DOM:<ul><li>1</li><li>2</li><ul>,导航CSS:.navOn
 *		eventNav:'click' //导航事件
 */

$.fn.marquee = function(options){
	var opts = $.extend({},$.fn.marquee.defaults, options);
	
	return this.each(function(){
		var $marquee = $(this);//滚动元素容器
		var _scrollObj = $marquee.get(0);//滚动元素容器DOM
		var scrollW = $marquee.width();//滚动元素容器的宽度
		var scrollH = $marquee.height();//滚动元素容器的高度
		var $element = $marquee.children(); //滚动元素
		var $kids = $element.children();//滚动子元素
		var scrollSize=0;//滚动元素尺寸
		var _type = (opts.direction == 'left' || opts.direction == 'right') ? 1:0;//滚动类型，1左右，0上下
		var scrollId, rollId, isMove, marqueeId;
		var t,b,c,d,e; //滚动动画的参数,t:当前时间，b:开始的位置，c:改变的位置，d:持续的时间，e:结束的位置
		var _size, _len; //子元素的尺寸与个数
		var $nav,$navBtns;
		var arrPos = []; 
		var numView = 0; //当前所看子元素
		var numRoll=0; //轮换的次数
		var numMoved = 0;//已经移动的距离

		//防止滚动子元素比滚动元素宽而取不到实际滚动子元素宽度
		$element.css(_type?'width':'height',10000);
		//获取滚动元素的尺寸
		var navHtml = '<ul>';
		if (opts.isEqual) {
			_size = $kids[_type?'outerWidth':'outerHeight']();
			_len = $kids.length;
			scrollSize = _size * _len;
			for(var i=0;i<_len;i++){
				arrPos.push(i*_size);
				navHtml += '<li>'+ (i+1) +'</li>';
			}
		}else{
			$kids.each(function(i){
				arrPos.push(scrollSize);
				scrollSize += $(this)[_type?'outerWidth':'outerHeight']();
				navHtml += '<li>'+ (i+1) +'</li>';
			});
		}
		navHtml += '</ul>';
		
		//滚动元素总尺寸小于容器尺寸，不滚动
		if (scrollSize<(_type?scrollW:scrollH)) return; 
		//克隆滚动子元素将其插入到滚动元素后，并设定滚动元素宽度
		$element.append($kids.clone()).css(_type?'width':'height',scrollSize*2);
		
		//轮换导航
		if (opts.navId) {
			$nav = $(opts.navId).append(navHtml).hover( stop, start );
			$navBtns = $('li', $nav);
			$navBtns.each(function(i){
				$(this).bind(opts.eventNav,function(){
					if(isMove) return;
					if(numView==i) return;
					rollFunc(arrPos[i]);
					$navBtns.eq(numView).removeClass('navOn');
					numView = i;
					$(this).addClass('navOn');
				});
			});
			$navBtns.eq(numView).addClass('navOn');
		}
		
		//设定初始位置
		if (opts.direction == 'right' || opts.direction == 'down') {
			_scrollObj[_type?'scrollLeft':'scrollTop'] = scrollSize;
		}else{
			_scrollObj[_type?'scrollLeft':'scrollTop'] = 0;
		}
		
		if(opts.isMarquee){
			//滚动开始
			//marqueeId = setInterval(scrollFunc, opts.scrollDelay);
			marqueeId = setTimeout(scrollFunc, opts.scrollDelay);
			//鼠标划过停止滚动
			$marquee.hover(
				function(){
					clearInterval(marqueeId);
				},
				function(){
					//marqueeId = setInterval(scrollFunc, opts.scrollDelay);
					clearInterval(marqueeId);
					marqueeId = setTimeout(scrollFunc, opts.scrollDelay);
				}
			);
			
			//控制加速运动
			if(opts.controlBtn){
				$.each(opts.controlBtn, function(i,val){
					$(val).bind(opts.eventA,function(){
						opts.direction = i;
						opts.oldAmount = opts.scrollAmount;
						opts.scrollAmount = opts.newAmount;
					}).bind(opts.eventB,function(){
						opts.scrollAmount = opts.oldAmount;
					});
				});
			}
		}else{
			if(opts.isAuto){
				//轮换开始
				start();
				
				//鼠标划过停止轮换
				$marquee.hover( stop, start );
			}
		
			//控制前后走
			if(opts.btnGo){
				$.each(opts.btnGo, function(i,val){
					$(val).bind(opts.eventGo,function(){
						if(isMove == true) return;
						opts.direction = i;
						rollFunc();
						if (opts.isAuto) {
							stop();
							start();
						}
					});
				});
			}
		}
		
		function scrollFunc(){
			var _dir = (opts.direction == 'left' || opts.direction == 'right') ? 'scrollLeft':'scrollTop';
			
			if(opts.isMarquee){
				if (opts.loop > 0) {
					numMoved+=opts.scrollAmount;
					if(numMoved>scrollSize*opts.loop){
						_scrollObj[_dir] = 0;
						return clearInterval(marqueeId);
					} 
				}
				var newPos = _scrollObj[_dir]+(opts.direction == 'left' || opts.direction == 'up'?1:-1)*opts.scrollAmount;
			}else{
				if(opts.duration){
					if(t++<d){
						isMove = true;
						var newPos = Math.ceil(easeOutQuad(t,b,c,d));
						if(t==d){
							newPos = e;
						}
					}else{
						newPos = e;
						clearInterval(scrollId);
						isMove = false;
						return;
					}
				}else{
					var newPos = e;
					clearInterval(scrollId);
				}
			}
			
			if(opts.direction == 'left' || opts.direction == 'up'){
				if(newPos>=scrollSize){
					newPos-=scrollSize;
				}
			}else{
				if(newPos<=0){
					newPos+=scrollSize;
				}
			}
			_scrollObj[_dir] = newPos;
			
			if(opts.isMarquee){
				marqueeId = setTimeout(scrollFunc, opts.scrollDelay);
			}else if(t<d){
				if(scrollId) clearTimeout(scrollId);
				scrollId = setTimeout(scrollFunc, opts.scrollDelay);
			}else{
				isMove = false;
			}
			
		};
		
		function rollFunc(pPos){
			isMove = true;
			var _dir = (opts.direction == 'left' || opts.direction == 'right') ? 'scrollLeft':'scrollTop';
			var _neg = opts.direction == 'left' || opts.direction == 'up'?1:-1;

			numRoll = numRoll +_neg;
			//得到当前所看元素序号并改变导航CSS
			if(pPos == undefined&&opts.navId){
				$navBtns.eq(numView).removeClass('navOn');
				numView +=_neg;
				if(numView>=_len){
					numView = 0;
				}else if(numView<0){
					numView = _len-1;
				}
				$navBtns.eq(numView).addClass('navOn');
				numRoll = numView;
			}

			var _temp = numRoll<0?scrollSize:0;
			t=0;
			b=_scrollObj[_dir];
			//c=(pPos != undefined)?pPos:_neg*opts.distance;
			e=(pPos != undefined)?pPos:_temp+(opts.distance*numRoll)%scrollSize;
			if(_neg==1){
				if(e>b){
					c = e-b;
				}else{
					c = e+scrollSize -b;
				}
			}else{
				if(e>b){
					c =e-scrollSize-b;
				}else{
					c = e-b;
				}
			}
			d=opts.duration;
			
			//scrollId = setInterval(scrollFunc, opts.scrollDelay);
			if(scrollId) clearTimeout(scrollId);
			scrollId = setTimeout(scrollFunc, opts.scrollDelay);
		}
		
		function start(){
			rollId = setInterval(function(){
				rollFunc();
			}, opts.time*1000);
		}
		function stop(){
			clearInterval(rollId);
		}
		
		function easeOutQuad(t,b,c,d){
			return -c *(t/=d)*(t-2) + b;
		}
		
		function easeOutQuint(t,b,c,d){
			return c*((t=t/d-1)*t*t*t*t + 1) + b;
		}

	});
};
$.fn.marquee.defaults = {
	isMarquee:false,//是否为Marquee
	isEqual:true,//所有滚动的元素长宽是否相等,true,false
	loop: 0,//循环滚动次数，0时无限
	newAmount:3,//加速滚动的步长
	eventA:'mousedown',//鼠标事件，加速
	eventB:'mouseup',//鼠标事件，原速
	isAuto:true,//是否自动轮换
	time:5,//停顿时间，单位为秒
	duration:50,//缓动效果，单次移动时间，越小速度越快，为0时无缓动效果
	eventGo:'click', //鼠标事件，向前向后走
	direction: 'left',//滚动方向，'left','right','up','down'
	scrollAmount:1,//步长
	scrollDelay:10,//时长
	eventNav:'click'//导航事件
};

$.fn.marquee.setDefaults = function(settings) {
	$.extend( $.fn.marquee.defaults, settings );
};

/******************************************************************/
/**
 * 封装map和list
 */
(function(win) {
    var Map = function() {
        this.count = 0;
        this.entrySet = {};
    };

    var proto = Map.prototype;

    proto.size = function() {
        return this.count;
    };

    proto.isEmpty = function() {
        return this.count === 0;
    };

    proto.containsKey = function(key) {
        if (this.isEmpty()) {
            return false;
        }

        for ( var prop in this.entrySet) {
            if (prop === key) {
                return true;
            }
        }

        return false;
    };

    proto.containsValue = function(value) {
        if (this.isEmpty()) {
            return false;
        }

        for ( var key in this.entrySet) {
            if (this.entrySet[key] === value) {
                return true;
            }
        }

        return false;
    };

    proto.get = function(key) {
        if (this.isEmpty()) {
            return null;
        }

        if (this.containsKey(key)) {
            return this.entrySet[key];
        }

        return null;
    };

    proto.put = function(key, value) {
        this.entrySet[key] = value;
        this.count++;
    };

    proto.remove = function(key) {
        if (this.containsKey(key)) {
            delete this.entrySet[key];
            this.count--;
        }
    };

    proto.putAll = function(map) {
       if(!map instanceof Map){
    	   return;
       }

        for ( var key in map.entrySet) {
            this.put(key, map.entrySet[key]);
        }
    };

    proto.clear = function() {
        for ( var key in this.entrySet) {
            this.remove(key);
        }
    };

    proto.values = function() {
        var result = [];

        for ( var key in this.entrySet) {
            result.push(this.entrySet[key]);
        }

        return result;
    };

    proto.keySet = function() {
        var result = [];

        for ( var key in this.entrySet) {
            result.push(key);
        }

        return result;
    };

    proto.toString = function() {
        var result = [];
        for ( var key in this.entrySet) {
            result.push(key + ":" + this.entrySet[key]);
        }

        return "{" + result.join() + "}";
    };

    proto.valueOf = function() {
        return this.toString();
    };

    win.Map = Map;
    
    
    
    var List = function List(){  
	    this.init();  
	};  
	//初始化列表  
	List.prototype.init = function(){  
	    this.array = new Array();  
	};  
	//列表的大小  
	List.prototype.size = function(){  
	    return this.array.length;  
	};  
	//向列表添加一个元素  
	List.prototype.add = function(element){  
	    this.array.push(element);   
	};  
	//批量添加元素到列表  
	List.prototype.addAll = function(list){  
	    if(list && isList(list)){  
	        var size = list.size();  
	        var element;  
	        for(var i = 0;i < size;i++){  
	            element = list.get(i);  
	            this.array.push(element);  
	        }  
	    }  
	};  
	//转化成数组  
	List.prototype.toArray = function(){  
	    return this.array;  
	};  
	//移除数组（根据下标）  
	List.prototype.remove = function(index){  
	    if(index >= this.size()){  
	        index = this.size() - 1;  
	    }  
	    this.array.splice(index,1);  
	};  
	List.prototype.get = function(index){  
	    return this.array[index];  
	};
	//清除列表原色  
	List.prototype.clear = function(){  
	    this.init();  
	};  
	//是否为空列表  
	List.prototype.isEmpty = function(){  
	    return this.size() === 0;  
	};  
	//是否包含某个元素  
	List.prototype.contains = function(element){  
	    return this.indexOf(element) >= 0;  
	};  
	//判断列表的某个元素的第一个元素在列表的下标位置  
	//-1则表示没有这个元素  
	List.prototype.indexOf = function(element){  
	    var size = this.size();  
	    if(null === element){  
	        for(var i = 0;i < size;i++){  
	            if(this.array[i] === null){  
	                return i;  
	            }  
	        }  
	    }else{  
	        for(var i = 0;i < size;i++){  
	            if(this.array[i] === element){  
	                return i;  
	            }  
	        }  
	    }  
	    return -1;  
	};  
	var isList = function(list){  
	    return list instanceof List;  
	};
	
	win.List = List;
    
})(window);







