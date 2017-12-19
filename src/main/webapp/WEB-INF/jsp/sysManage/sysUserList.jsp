<%@ page language="java" import="com.css.business.web.sysManage.bean.SysConfig,com.css.common.util.Constant,com.css.business.web.sysManage.bean.SysUser,com.css.commcon.util.SessionUtils" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SysUser user = SessionUtils.getUser(request);
	String areaCode = "";
	if(user != null){
		areaCode = user.getAreaCode();
	}
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysUserList</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />

<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

   <script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
   <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
	    
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>

<script src="<%=basePath %>app/js/sysManage/sysUserList.js"
	type="text/javascript"></script>
<script>
			var basePath  = '<%=basePath%>';
			var areaCode  = '<%=areaCode %>';
			var routeName = "sysUserManage";    
    	</script>
</head>

<body style="padding:6px; overflow:hidden;">
	<div id="message" style="width:800px"></div>
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<form id="sysUserManageListForm">
		<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin:0px;width:99%;padding-left:5px;">
					<tr class="table-edit-tr">
  						<td align="right" class="l-table-edit-td">
  							行政区划：
  						</td>
  						<td style="width:13%" align="left" class="l-table-edit-td-input">
							<input id="areaCode" name="areaCode" type="hidden" ztree="true" value="" />
							<div style="width: 99%; height: 22px; position: relative;">
									<input id="areaCodeShow" name="areaCodeShow" onfocus="this.blur()" type="text" ztree="true" placeholder="行政区域" validate="{required:true}"
										style="margin: 0px; padding-left: 5px; width: 99%; height: 20px; color: rgb(34, 34, 34);"/><img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="areaCodeBtn" href="#"
										onclick="showMenu('areaCode'); return false;" />
							</div>
						</td>
  						<td align="right" class="l-table-edit-td">
  							帐户类型：
  						</td>
  						<td style="width:13%" align="left" class="l-table-edit-td-input">
							<input id="userType" name="userType" type="hidden" ztree="true" value="" />
							<div style="width: 99%; height: 22px; position: relative;">
									<input id="userTypeShow" name="userTypeShow" onfocus="this.blur()" type="text" ztree="true" placeholder="用户类型 " validate="{required:false}"
										style="margin: 0px; padding-left: 5px; width: 99%; height: 20px; color: rgb(34, 34, 34);"/><img
										src="<%=basePath%>core/img/btn_03.png"
										style="position: absolute; right: 0px; top: 2px;"
										id="userTypeBtn" href="#"
										onclick="showMenu('userType'); return false;" />
							</div>
						</td>
  						<td align="right" class="l-table-edit-td">
  							账号:
  						</td>
  						<td style="width:13%" align="left" class="l-table-edit-td-input">
							<input name="account" id="account" type="text" ltype="text">
						</td>
  						<td width="20%">&nbsp;</td>
						<td>
							<input type="button" class="btn-search" onclick="reloadData(['account'])" />
							<input type="button" class="btn-reset" onclick="reloadData(['account'],true)" />
						</td>
  					</tr>
  				</table>
		<br />
		<button type="button" onclick="pub_del_user(routeName);" class="btn-del"></button>
		<button type="button" id="add" class="btn-add"></button>
		<button type="button" id="reset" class="btn-reset-password" onclick="resetPassword(routeName);"></button>
		<div id="sysUserManageList" style="margin:0; padding:0"></div>
	</form>
	<div style="display:none;"></div>
</body>
</html>

