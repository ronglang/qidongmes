<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sysRole_id = request.getParameter("id");
	String idlist = request.getParameter("idlist");
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysAuthorityManagementEdit</title>

<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysRoleMenu";
	var row_id = "";
	row_id =<%=sysRole_id%>;
</script>
   <script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/js/toJson.js" type="text/javascript"></script> 

<!-- 默认引用end -->


<!-- JS文件end -->
<script type="text/javascript">
	 $(function(){
 	  	creatTreeDiv("IS_REPORTED");
     	initTree("IS_REPORTED","resource","checkbox","","");
     	if (row_id) {
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
       }
	 });
	
	function initTree(colName,nodeTBName,treeType,queryName,queryValue){
	var url =  basePath +"rest/resourceManager/nodeTrees";
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:false,
        data:"qcolName="+queryName+"&qcolValue="+queryValue+"&idlist="+$("#idlist").val(),
        success: function(json){
          	if(json.data!=null){
          		if(treeType =="checkbox"){
          			$.fn.zTree.init($("#"+colName+"ContentTree"), checkTreeSetting, json.data);
          			$.fn.zTree.getZTreeObj(colName+"ContentTree").setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
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
/*
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
*/
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
				pIdKey: "pId"
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

/**
 * 提交数据
 */
function submitSysRole() {
//	alert(1111111);
	var url = basePath + "rest/sysRoleMenuManageAction/setRoleRource";
	$.ajax({
		url : url,
		type : "post",
		data : "roleIdlist=" + $("#idlist").val()+"&newRoleList="+$("#IS_REPORTED").val(),
		success : function(json) {
			var obj = jQuery.parseJSON(json);
		//	alert(obj);
		//	alert(obj.data);
			submitSuccess(obj);
		},
		error : function(data) {
			ajaxError(data);
		}
	});
}

function submitSysRoles() {
	alert($("#idlist").val());
}
/* 
 * 重写提交数据成功后的回调函数
 */
 /*
function submitSuccess(data) {
	if (data.success) {
		top.$.ligerDialog.success(data.data);
	} else {
		if(json.msg) top.$.ligerDialog.error(data.data);
	}
} 
*/
function submitSuccess(json) {
	if (json.success) {
		alert(json.data);
//	top.$.ligerDialog.success(json.data);
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

</script>
</head>
<body>
	<form id="sysRoleMenu" method="post">
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">权限设置：</td>
				<td align="left" class="l-table-edit-td-input" style="width: 183px;">
				 <input id="IS_REPORTED" name="IS_REPORTED" type="hidden" ztree="true" value="" />
          <div style="width:180px; height:20px;position:relative;">
            <input id="IS_REPORTEDShow" name="IS_REPORTEDShow" type="text"   onfocus="this.blur()"  validate="{required:true}"
              ztree="true" style="margin: 0px;padding-left:4px;  width:178px; height: 19px;  color: rgb(34, 34, 34);"  /><img
              src="<%=basePath%>core/img/btn_03.png"
              style="position:absolute; right:0px; top:2px;"
              id="IS_REPORTEDBtn" href="#"
              onclick="showMenu('IS_REPORTED'); return false;" />
          </div></td>
				<td align="left"></td>
			</tr>
		</table>
		<input type="hidden" name="idlist" id="idlist" value="${idlist }"/>
		<input style="float:left;margin-left: 91px;margin-top: 47px;" class="l-button l-button-submit" type="button"  onclick="submitSysRole();" value="提交"/>
	</form>
</body>
</html>