<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sysDbmetadata_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysDbmetadataEdit</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" /><link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<!-- <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysDbmetadataManage"; 
	var row_id = "";
	row_id =<%=sysDbmetadata_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script><script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysDbmetadataEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body style="padding:0px; margin:0px;">
	<form id="sysDbmetadataManage" method="post">
		<table cellpadding="0" cellspacing="0" class="l-table-edit" id="metadataTable" style="margin:30px auto;">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:100px;">定义：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="definit" name="definit" type="text" ltype="text" required="required"/></td>
				<td align="left" style="width:30px;"></td>
				<td align="right" class="l-table-edit-td" style="width:100px;">中文名：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="cnName" name="cnName" type="text" ltype="text" required="required"/></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">英文名：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="enName" name="enName" type="text" ltype="text" required="required"/></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">短名：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="shName" name="shName" type="text" ltype="text" required="required"/></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">类型：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="metadataType" name="metadataType" type="hidden" ztree="true"
					value="" />
				<div style="width: 177px; height: 22px; position: relative;">
						<input id="metadataTypeShow" name="metadataTypeShow" type="text"
							ztree="true" required="required"
							style="margin: 0px; padding-left:4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="metadataTypeBtn" href="#"
							onclick="showMenu('metadataType'); return false;" />
					</div></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">父：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="parentId" name="parentId" type="hidden" ztree="true" value="" />
				<div style="width: 177px; height: 22px; position: relative;">
						<input id="parentIdShow" name="parentIdShow" type="text"
							ztree="true" required="required"
							style="margin: 0px; padding-left:4px; width: 170px; height:19px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="parentIdBtn" href="#"
							onclick="showMenu('parentId'); return false;" />
					</div></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr" name="field" style="display:none">
				<td align="right" class="l-table-edit-td">字段类型：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="fieldType" name="fieldType" type="hidden" ztree="true" value="" />
				<div style="width: 177px; height: 22px; position: relative;">
						<input id="fieldTypeShow" name="fieldTypeShow" type="text"
							ztree="true" 
							style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="fieldTypeBtn" href="#"
							onclick="showMenu('fieldType'); return false;" />
					</div></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">长度：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="fieldLen" name="fieldLen" type="text" ltype="text" /></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr" name="field" style="display:none">
				<td align="right" class="l-table-edit-td">精度：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="precision" name="precision" type="text" ltype="text" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">是否主键：</td>
				<td align="left" class="l-table-edit-td-input"><div id="isPk"></div></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr" name="db" style="display:none">
				<td align="right" class="l-table-edit-td">数据库地址：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="dbUrl" name="dbUrl" type="text" ltype="text" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">数据库类型：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="dbType" name="dbType" type="hidden" ztree="true" value="" />
				<div style="width: 177px; height: 22px; position: relative;">
						<input id="dbTypeShow" name="dbTypeShow" type="text" ztree="true"
							style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;" id="dbTypeBtn"
							href="#" onclick="showMenu('dbType'); return false;" />
					</div></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr" name="db" style="display:none">
				<td align="right" class="l-table-edit-td">数据库驱动：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="dbDriver" name="dbDriver" type="text" ltype="text" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">数据库用户名：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="dbUser" name="dbUser" type="text" ltype="text" /></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr" name="db" style="display:none">
				<td align="right" class="l-table-edit-td">数据库密码：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="dbPsw" name="dbPsw" type="text" ltype="text" /></td>
				<td align="left"></td>
			</tr>
		</table>
		<div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-top:120px;">
		    <input style="float: left; display:inline-block;" class="l-button l-button-submit" type="submit" value="提交" /> 
		    <input style="float: right; display:inline-block;" class="l-button l-button-test" type="reset" value="重置" />
		</div>
	</form>
</body>
</html>
<script type="text/javascript">
var dbmetadataSetting = {				
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onTreeClick	
		}
};
var metadataTypeSetting = {				
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onMetadataTypeTreeClick	
		}
};

$(function() {
	creatTreeDiv("metadataType");
	initMetadataTypeTree("metadataType", "PID","C1013");
	
	creatTreeDiv("parentId");
	initDbmetadataTree("parentId", "sysDbmetadata");
	
	treeObj = $.fn.zTree.getZTreeObj("parentIdContentTree");
	treeObj.expandAll(true); 

});

function initMetadataTypeTree(colName,queryName,queryValue){
	var url =  basePath +"rest/sysDictionaryManageAction/nodeTree";
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:false,
        data:"qcolName="+queryName+"&qcolValue="+queryValue,
        success: function(json){
          	if(json.data!=null){
          		$.fn.zTree.init($("#"+colName+"ContentTree"), metadataTypeSetting, json.data);
        	}
        },
        error: function(json) {
            alert('出错啦！');
        }
    });
}

function onMetadataTypeTreeClick(e, treeId, treeNode) {
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
		//库
		if(h == 'C10130001'){
			$("#metadataTable tr[name='db']").each(function(i, e){
				$(this).css("display","");
			});
			$("#metadataTable tr[name='field']").each(function(i, e){
				$(this).css("display","none");
			});
		}
		else if(h == 'C10130003'){
			$("#metadataTable tr[name='db']").each(function(i, e){
				$(this).css("display","none");
			});
			$("#metadataTable tr[name='field']").each(function(i, e){
				$(this).css("display","");
			});
		}
		else{
			$("#metadataTable tr[name='db']").each(function(i, e){
				$(this).css("display","none");
			});
			$("#metadataTable tr[name='field']").each(function(i, e){
				$(this).css("display","none");
			});
		}
	}
}


function initDbmetadataTree(colName,nodeId){
	var url =  basePath +"rest/sysDbmetadataManageAction/getTree";
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        async:false,
        data:"excludeDataType=C10130003",
        success: function(json){
          	if(json.data!=null){
          		$.fn.zTree.init($("#"+colName+"ContentTree"), dbmetadataSetting, json.data);
        	}
        },
        error: function(json) {
            alert('出错啦！');
        }
    });
}

</script>
