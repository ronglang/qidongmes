<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysOrg_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
	<head>
		

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>sysOrgForm</title>
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

		<script>
			var basePath  = '<%=basePath%>';
			var isSimpleMap = true;
    	</script>
    	
    	<script type="text/javascript" src="<%=basePath%>flexmap/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>flexmap/embedMap.js"></script>
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
		<form id="SysOrg">
				<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin:10px auto;">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:120px;">上级机构：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;">
					<input id="parentId" name="parentId" readonly="readonly"/>
					</td>
				<td align="left" style="width:30px;"></td>
				<td align="right" class="l-table-edit-td" style="width:120px;">机构名称：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="orgName" name="orgName" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">区划代码：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="areaCode" name="areaCode" readonly="readonly"/>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">值班人姓名：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="dutyName" name="dutyName" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">值班电话：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="telephone" name="telephone" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">单位性质：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="property" name="property" readonly="readonly"/>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">地址：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="address" name="address" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">机构经度：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="longitude" name="longitude" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">机构纬度：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="latitude" name="latitude" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
			</tr>
			
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">负责人姓名：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="leaderName" name="leaderName" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">机构负责人手机号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="leaderMobile" name="leaderMobile" type="text" ltype="text" readonly="readonly"/></td>
				<td align="left"></td>
			</tr>
		
			<tr class="l-table-edit-tr">
					<td align="right" class="l-table-edit-td">排序：</td>
					<td align="left" class="l-table-edit-td-input"><input id="orders" type="text" ltype="text" readonly="readonly"></input></td>
					<td align="left"></td>
					<td align="right" class="l-table-edit-td">维护人：</td>
					<td align="left" class="l-table-edit-td-input">
					<input id="createBy" type="text" ltype="text" readonly="readonly"></input></td>
					<td align="left"></td>
					<td align="right" class="l-table-edit-td">维护时间：</td>
					<td align="left" class="l-table-edit-td-input">
					<input id="createDate" type="text" ltype="text" readonly="readonly"></input></td>
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
					<div id="flashContent" style="margin:10px auto" >
						<iframe frameborder="0" style="height:450px;width: 100%"  name="showmessage" src="<%=basePath %>rest/sysUserManageAction/oneMap"></iframe>
					</div>
				</td>
				
			</tr>
		</table>
		</form>
	</body>

	<script type="text/javascript">
	
	if(document.all){
		 var textArea=$("textarea");
		 for(var i=0;i<textArea.length;i++){
		 textArea[i].style.width="778px";  
		 }
		 
		 
	}else{
		 var textArea=$("textarea");
		 for(var i=0;i<textArea.length;i++){
		 textArea[i].style.width="780x";  
		 }
		  
	}
	
	
	var routeName = "sysOrgManage"; 
	var row_id = "";
	row_id = <%=sysOrg_id%>;
	
	$(document).ready(function() {
		if (row_id != null) {
			$("#SysOrg").ligerForm();
			var parm = "id="+row_id;
			pub_initForm(routeName,parm);
		}
	});
  	</script>
</html>

