<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String blog_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>bolgEdit</title>
<!-- 树引用 -->
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 树引用  end-->

<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />

<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<!-- 上传控件引用 -->
<script src="<%=basePath%>core/plugin/json.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfupload.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfupload.queue.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/fileprogress.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfuploadProperties.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/handlers.js" type="text/javascript"></script>
<!-- 上传控件引用 end-->
<!-- 树引用 -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<!-- 树引用 end-->
<!-- 富文本框 -->
<script src="<%=basePath%>core/js/ueditor/ueditor.config.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ueditor/ueditor.all.min.js" type="text/javascript"></script>
<!-- 富文本框end -->
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath %>app/js/userManage/blogEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "blogManage"; 
	var row_id = "";
	row_id =<%=blog_id%>;
</script>
</head>

<body>
	<form id="blogManage" method="post">
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
			<tr>
				<td align="right" class="l-table-edit-td">添加人：</td>
				<td align="left" class="l-table-edit-td"><input id="own" name="own" type="text" ltype="text"/></td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">添加时间：</td>
				<td align="left" class="l-table-edit-td"><input id="addDate" name="addDate" type="text" ltype="date"/></td>
				<td align="left"></td>
			</tr>
			<tr>
				
				<td align="right" class="l-table-edit-td">复选框：</td>
				<td align="left" class="l-table-edit-td">	<div id="ck"></div>
				</td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">单选框：</td>
				<td align="left" class="l-table-edit-td"><div id="ra"></div>
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">隐藏框：</td>
				<td align="left" class="l-table-edit-td">
					<input id="hi" name="hi" type="hidden" value="我是隐藏的"/>
				</td>
				<td align="left"></td>
				
				<td align="right" class="l-table-edit-td">下拉框：</td>
				<td align="left" class="l-table-edit-td">
					<input id="cityShow" name="cityShow" type="text" value="" readonly ztree="true"/>
					<input id="city" name="city" type="hidden" ztree="true"/>
							&nbsp;<a id="cityBtn" href="#" onclick="showMenu('city'); return false;">选择</a>
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">上传：</td>
				<td align="left" class="l-table-edit-td" colspan="4">
					<!-- 如下是上传控件写死 -->
					<div class="swfdiv" >
						<div id="uploadurlShow" style="overflow:hidden;"></div>
						
						<div class="fieldset flash" id="fsUploadProgress1" >
							<input type="hidden" name="saveDataId1" id="saveDataId1"/>
						</div>
						<div style="padding-left: 5px;">
							<span id="spanButtonPlaceholder1"></span>
							<input id="btnCancel1" type="button" value="取消上传" onclick="cancelQueue(upload1);"
							 disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;" />
							<br />
						</div>
					</div>
					<!-- 如下是上传控件写死 end -->
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">标题：</td>
				<td align="left" class="l-table-edit-td" colspan="4"><textarea cols="100" rows="4" id="title" name="title" class="l-textarea"></textarea></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">内容:</td>
				<td align="left" class="l-table-edit-td" colspan="4"><br>
					<div>
						<div id="blogContent"  style="width:750px;height:300px;" ueditor=true>
					    <script type="text/javascript">
					    	var contentEditor = UE.getEditor('blogContent');
					    </script>
					    </div>
					</div>
                 </td>
                 <td align="left"></td>
               </tr>
		</table>
		<input class="l-button l-button-submit" type="submit" value="提交"/>
		<input class="l-button l-button-test" type="reset" value="重置"/>
	</form>
</body>
</html>

