var grid;
$(function ()
    {
       /* $.metadata.setType("attr", "validate");
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
				NAME:{rangelength:[0,32]},REMARK:{rangelength:[0,512]}
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                submitSysRole();
            }
        });*/
        $("#"+routeName).ligerForm();
        $(".l-button-test").click(function ()
        {
            alert(v.element($("#txtName")));
        });
        
       if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
       }
       //列表加载end
       grid = $("#resourceList")	.ligerGrid({
    	   url : basePath + "rest/sysRoleMenuManageAction/queryRoleMenu?pcode=-1&roleId="+row_id,
    	   checkbox : true,
    	   usePager: false,
    	   /*detail:{
				url:basePath + "rest/sysRoleMenuManageAction/queryRoleMenu?roleId="+row_id
			},*/
    	   columns : [
    	              {display : '序号',name : 'id',hide:true,width : 1},
    	              {display : '名称',name : 'name',width : 320},
    	              {display : '是否根节点',name : 'isLeaf',width : 80,
    	            	  render:function(row,idx,value){
    	            		  if(value == '0')return '是';
    	            		  else if(value == '1')return '否';
    	            		  else return '';
    	            	  }
    	              },
    	              {display : '菜单类型',name : 'menuType',width : 90,
    	            	  render:function(row,idx,value){
    	            		  if(value == '1')return '菜单';
    	            		  else if(value == '2')return '按钮';
    	            		  else return '';
    	            	  }
    	              },
    	              {display : '编码',name : 'code',width : 120},
    	              {display : '父编码',name : 'pcode',width : 120},
    	              {display : '父菜单',name : 'pname',width : 120},
    	              {display : '排序',name : 'sort',width : 60},
    	              {display : 'type',name : 'type',width : 1,hide:true},
    	              {display : 'url',name : 'url',width : 260}
    	              ],
    	              pageSize : 10,
    	              isChecked : f_isChecked,
    	              onCheckRow : f_onCheckRow,
    	              onCheckAllRow : f_onCheckAllRow,
    	              width : '100%',
    	              height : '97%',
    	              onAfterAppend: function () {grid.collapseAll();},
    	              alternatingRow: true,
    	              tree: { columnName: 'name' },
    	              onAfterShowData: function() {  
    	            	  var l = $(".l-grid-tree-link-open").length;  
    	            	  for (var i = l - 1; i >= 0; i--)  
    	            		  $(".l-grid-tree-link-open")[i].click();  
    	              },
    	              onSuccess : function(json, grid) {
    	            	  $("#pageloading").hide();
    	              },
    	              onError : function(json, grid) {
    	            	  $("#pageloading").hide();
    	              }
       });
    });  
function f_isChecked(rowdata)
{
    if (!rowdata.roleId || rowdata.roleId == null || findChecked(rowdata.roleId) == null)
        return false;
    return true;
}

function initTree(colName,nodeTBName,treeType,queryName,queryValue){
	var url =  basePath +"rest/resourceManager/nodeTreesId";
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:true,
        data:"qcolName="+queryName+"&qcolValue="+queryValue+"&idlist="+row_id,
        success: function(json){
          	if(json.data!=null){
          		if(treeType =="checkbox"){
          			$.fn.zTree.init($("#"+colName+"ContentTree"), checkTreeSetting, json.data);
          			$.fn.zTree.getZTreeObj(colName+"ContentTree").setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
          			var ids="";
      				var names = "";
          			json.data.forEach(function(item){
          				if(item.checked){
          					ids+=item.id+",";
          					names += item.name+",";
          				}
          			});
          			ids = ids.substring(0, ids.length-1);
          			names = names.substring(0, names.length-1);
          			$("#IS_REPORTED").val(ids);
  					$("#IS_REPORTEDShow").val(names);
          		}else if(treeType =="radio"){
          			$.fn.zTree.init($("#"+colName+"ContentTree"), radioTreeSetting, json.data);
          		}else{
          			$.fn.zTree.init($("#"+colName+"ContentTree"), treeSetting, json.data);
          		}
        	}
          	
        },
        error: function(json) {
            alert('出错啦！');
        }
    });
}

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
				enable: true,
				idKey: "code",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback: {
			beforeClick: beforeTreeClick,
			onCheck: onTreeCheck
		}
};

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

function submitSysRole() {
	var url = basePath + "rest/sysRoleManageAction/saveRoleInfo";
	var name = $('#name').val();
	var remark = $('#remark').val();
	var p = grid.getCheckedRows();
	if(!p){
		alert("请为角色选择菜单及按钮权限"); return false;
	}
	var params = '';
	$.each(p,function(i,o){
		params += o.id+',';
	});
	if(params && params.length > 0){
		params = params.substring(0,params.length - 1);
	}
	
	//$('#btn_save').css('display',"none");
	//$('#btn_save').attr('disabled',true);
	
	$.ajax({
		url : url,
		type : "post",
		data : "roleName=" + name + "&roleRemark="+remark + "&datas=" +params+"&params="+row_id,
		dataType:'json',
		async:false,
		success : function(data) {
			//$('#btn_save').removeAttr('disabled');
			//$('#btn_save').css('display',"block");
			if(data.success){
				alert("操作成功");
				window.close();
				window.opener.reloadData();
			}
			else{
				dealErr(data);
			}
		},
		error : function(data) {
			//$('#btn_save').removeAttr('disabled');
			//$('#btn_save').css('display',"block");
			dealErr(data);
		}
	});
}

function dealErr(json){
	var msg = json.msg;
	if(msg){
		top.$.ligerDialog.error(msg);
	}
	else if(json.responseText){
		var result = jQuery.parseJSON(json.responseText);
		if(result)
			top.$.ligerDialog.error(result.msg);
		else
			top.$.ligerDialog.error(json.responseText);
	}
	else{
		alert("保存失败");
	}
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