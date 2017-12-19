<%@ page language="java" 
 import="com.css.sysManage.bean.SysConfig,com.css.commcon.util.Constant,com.css.sysManage.bean.SysUser,com.css.commcon.util.SessionUtils"
 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String sysArea_id = request.getParameter("id");
	
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
<title>sysAreaEdit</title>

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "sysAreaManage"; 
	var row_id = "";
	row_id =<%=sysArea_id%>;
	var isSimpleMap=true;
	var userArea="5119";
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
 
 <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<script type="text/javascript" src="<%=basePath%>flexmap/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>flexmap/embedMap.js"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysAreaEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body>
	<form id="sysAreaManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">所属区县名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="county" name="county" type="text" ltype="text"  />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">所属乡镇名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="town" name="town" type="text" ltype="text"  />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">上级行政区划：</td>
				<td align="left" class="l-table-edit-td-input">
				    <!-- <input 	id="town" name="town" type="text" ltype="text" /> -->
					<input id="isPoor" name="isPoor" type="hidden" />
					<input id=pcode name="pcode" type="hidden" ztree="true" value="" />
					<div style="width: 177px; height: 22px; position: relative;">
							<input id="pcodeShow" name="pcodeShow" onfocus="this.blur()" type="text" ztree="true" 
								validate="{required:false}" placeholder="请选择行政区划" title="请选择行政区划"
								style="margin: 0px; padding-left: 5px; width: 170px; height: 20px; color: rgb(34, 34, 34);"/>
								<img
								src="<%=basePath%>core/img/btn_03.png"
								style="position: absolute; right: 0px; top: 2px;"
								id="pcodeBtn" href="#"
								onclick="showMenu('pcode'); return false;" />
					</div>
				</td>
				<td align="left"></td>
<!-- 				<td align="right" class="l-table-edit-td">创建人：</td> -->
<!-- 				<td align="left" class="l-table-edit-td-input"><input -->
<!-- 					id="createBy" name="createBy" type="text" ltype="text" /> -->
<!-- 				</td> -->
<!-- 				<td align="left"></td> -->
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">区域代码：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="areaCode" name="areaCode" type="text" ltype="text" />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">区域名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="aname" name="aname" type="text" ltype="text" />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">经度：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="latitude" name="latitude" type="text" ltype="text" />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">纬度：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="longitude" name="longitude" type="text" ltype="text" />
				</td>
				<td align="left"></td>
				
			</tr>
		</table>
		<div style="width: 800px;height:330px;margin: 20px;">
			<div id="flashContent" style="width: 100px;height:200px;">
				<p>To view this page ensure that Adobe Flash Player version
					11.1.0 or greater is installed.</p>
				<script type="text/javascript">
					var pageHost = ((document.location.protocol == "https:") ? "https://"
							: "http://");
					document
							.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
				                            + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>");
				</script>
			</div>
		</div>
	 <div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-bottom:20px;">
		<input class="l-button l-button-submit" type="submit" value="提交"/>
<!-- 		<input class="l-button l-button-test" type="reset" value="重置"/> -->
	</div>
	</form>
</body>
</html>

