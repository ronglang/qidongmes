<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sysOrg_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysOrgEdit</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" /><link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<!-- <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysOrgManage"; 
	var row_id = "";
	var isSimpleMap = true;
	row_id =<%=sysOrg_id%>;
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
<script type="text/javascript" src="<%=basePath%>flexmap/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>flexmap/embedMap.js"></script>
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysOrgEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->
    	<style>
    		table{
    			margin:30px auto;
    		}
    		textarea{
    		margin-top:15px ; 
    		margin-bottom:15px;
		resize:none;
    		}
    	</style>
</head>

<body style="padding:0px; margin:0px;">
	<form id="sysOrgManage" method="post">
		<input type="hidden" name="id" id="id" />
		<input type="hidden" id="orgCode" name = "orgCode"/>
		<input type="hidden" id="updateBy" name = "updateBy"/>
        <input type="hidden" id="createBy" name = "createBy"/>
        <input type="hidden" id="createDate" name = "createDate"/>
        <input type="hidden" id="updateDate" name = "updateDate"/>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin:10px auto;">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:120px;">上级机构：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;">
					<input id="parentId" name="parentId" type="hidden" ztree="true" value="" ztree="true"/>
					<div style="width: 177px; height: 22px; position: relative;">
						<input id="parentIdShow" name="parentIdShow" type="text"
							ztree="true" required="required"
							style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="parentIdBtn" href="#"
							onclick="showMenu('parentId'); return false;" />
					</div></td>
				<td align="left" style="width:30px;"></td>
				<td align="right" class="l-table-edit-td" style="width:120px;">机构名称：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="orgName" name="orgName" type="text" ltype="text" required="required"/></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">区划代码：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="areaCode" name="areaCode" type="hidden" ztree="true" value="" ztree="true"/>
				<div style="width: 177px; height: 22px; position: relative;">
						<input id="areaCodeShow" name="areaCodeShow" type="text"
							ztree="true" required="required"
							style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="areaCodeBtn" href="#"
							onclick="showMenu('areaCode'); return false;" />
					</div></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">值班人姓名：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="dutyName" name="dutyName" type="text" ltype="text" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">值班电话：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="telephone" name="telephone" type="text" ltype="text" /></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">单位性质：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="property" name="property" type="hidden" ztree="true" value="" ztree="true"/>
				<div style="width: 177px; height: 22px; position: relative;">
						<input id="propertyShow" name="propertyShow" type="text"
							ztree="true" required="required"
							style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="propertyBtn" href="#"
							onclick="showMenu('property'); return false;" />
					</div></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">地址：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="address" name="address" type="text" ltype="text" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">机构经度：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="longitude" name="longitude" type="text" ltype="text" required="required"/></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">机构纬度：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="latitude" name="latitude" type="text" ltype="text" required="required"/></td>
				<td align="left"></td>
			</tr>
			
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">负责人姓名：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="leaderName" name="leaderName" type="text" ltype="text" /></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">机构负责人手机号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="leaderMobile" name="leaderMobile" type="text" ltype="text" validate="{number:true,minlength:11}"/></td>
				<td align="left"></td>

		        <td align="right" class="l-table-edit-td">职位：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="position" name="position" type="hidden" ztree="true" value="" ztree="true"/>
				<div style="width: 177px; height: 22px; position: relative;">
						<input id="positionShow" name="positionShow" type="text"
							ztree="true" 
							style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="positionBtn" href="#"
							onclick="showMenu('position'); return false;" />
					</div></td>
				<td align="left"></td>
			</tr>
		
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">简介：</td>
				<td align="left" class="l-table-edit-td-input" colspan="8"><textarea  style="width:770px; height: 120px;margin-top: 5px;margin-bottom: 10px;" rows="4" id="intro" name="intro" class="l-textarea"></textarea></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">备注：</td>
				<td align="left" class="l-table-edit-td-input" colspan="8"><textarea  style="width:770px; " rows="6" id="remarks" name="remarks" class="l-textarea"></textarea></td>
				<td align="left"></td>
			</tr>
			
			<tr class="l-table-edit-tr">
				<td align="left" class="l-table-edit-td-input"  colspan="8" style="height: 450px;">
					<div id="flashContent" style="margin:20px auto">
						<iframe frameborder="0" style="height:450px;width: 100%"  name="showmessage" src="<%=basePath %>rest/sysUserManageAction/oneMap"></iframe>
					</div>
				</td>
				
			</tr>
		</table>
		<div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-bottom:20px;">
		    <input style="float: left; display:inline-block;" class="l-button l-button-submit" type="submit" value="提交" /> 
		    <input style="float: right; display:inline-block;" class="l-button l-button-test" type="reset" value="重置" />
		</div>
	</form>
			
	<script>
	
		if(document.all){
			 var textArea=$("textarea");
			 for(var i=0;i<textArea.length;i++){
			 textArea[i].style.width="770px";  
			 }
			 $("#title").attr("ligerui",'{width:770}');
			 
		}else{
			 var textArea=$("textarea");
			 for(var i=0;i<textArea.length;i++){
			 textArea[i].style.width="780px";  
			 }
			 $("#title").attr("ligerui",'{width:780}');
		}
		
	</script>
</body>
</html>

