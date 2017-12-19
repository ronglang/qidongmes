<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String sysContacts_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysContactsEdit</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css"
	rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<!-- <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
    var basePath  = '<%=basePath%>';
    var routeName = "sysContactsManage";
    var row_id = "";
    row_id =<%=sysContacts_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js"
	type="text/javascript"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysContactsEdit.js"
	type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body style="padding:0px; margin:0px;">
	<form id="sysContactsManage" method="post" >
		<input type="hidden" id="updateBy" name = "updateBy"/>
        <input type="hidden" id="createBy" name = "createBy"/>
        <input type="hidden" id="createDate" name = "createDate"/>
        <input type="hidden" id="updateDate" name = "updateDate"/>
		<table cellpadding="0" cellspacing="0" class="l-table-edit"
			style="margin:30px auto;">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td" style="width:100px;">姓名：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;">
				<input id="name" name="name" type="text" ltype="text" validate="{required:true,maxlength:32}"
				/></td>
				<td align="left" style="width:20px;">
				<td align="right" class="l-table-edit-td" style="width:100px;">拼音简码：</td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="pinyin" name="pinyin" type="text" ltype="text" /></td>
				<td align="left" style="width:20px;"></td>
				<td align="right" class="l-table-edit-td" style="width:100px;">所在机构：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input" style="width:180px;"><input
					id="orgId" name="orgId" type="hidden" ztree="true" value="" />
					<div style="width:177px; height:22px;position:relative;">
						<input id="orgIdShow" name="orgIdShow" type="text" ztree="true" validate="{required:true} " 
							style="margin: 0px;padding-left:4px; width: 170px; height: 19px;  color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="orgIdBtn"
							href="#" onclick="showMenu('orgId'); return false;" />
					</div></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">性别：</td>
				<td align="left" class="l-table-edit-td-input">
					<div id="gender" name='gender' radio='true'></div></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">学历：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="education" name="education" type="hidden" value="" ztree="true" />
					<div style="width: 177px; height: 21px; position: relative;">
						<input id="educationShow" name="educationShow" type="text" validate="{required:true}" 
							ztree="true"
							style="margin: 0px; padding-left:4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position: absolute; right: 0px; top: 2px;"
							id="educationBtn" href="#"
							onclick="showMenu('education'); return false;" />
					</div>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">学校名称：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="schoolName" name="schoolName" type="text" ltype="text" /></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">EMAIL：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="email" name="email" type="text" ltype="text"  validate="{required:true,email:true}"/></td>
				<td align="left"></td>
				<!--  <td align="right" class="l-table-edit-td">电话：</td>
                <td align="left" class="l-table-edit-td-input"><input id="tel"
                    name="tel" type="text" ltype="text" />
                </td>
                <td align="left"></td> -->
				<td align="right" class="l-table-edit-td">手机：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="mobileTel" name="mobileTel" type="text" ltype="text" validate="{required:true,number:true,}"  maxlength="11" minlength="11"/></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">家庭电话：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="homeTel" name="homeTel" type="text" ltype="text" number="true"/></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">

				<td align="right" class="l-table-edit-td">办公电话：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="officeTel" name="officeTel" type="text" ltype="text" number="true"/></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">工作性质：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="workType" name="workType" type="hidden" ztree="true" value="" />
					<div style="width:177px; height:21px;position:relative;">
						<input id="workTypeShow" name="workTypeShow" type="text" validate="{required:true}" 
							ztree="true"
							style="margin: 0px;padding-left:4px; width: 170px; height: 19px;  color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="workTypeBtn"
							href="#" onclick="showMenu('workType'); return false;" />
					</div></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">职位：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="position" name="position" type="hidden" ztree="true" value="" />
					<div style="width:177px; height:22px;position:relative;">
						<input id="positionShow" name="positionShow" type="text" validate="{required:true}" 
							ztree="true"
							style="margin: 0px;padding-left:4px; width: 170px; height: 19px;  color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;" id="positionBtn"
							href="#" onclick="showMenu('position'); return false;" />
					</div></td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">分组ID：<span style="color: red;">*</span></td>
				<td align="left" class="l-table-edit-td-input"><input
					id="contactGroupId" name="contactGroupId" type="hidden"
					ztree="true" value="" />
					<div style="width:177px; height:22px;position:relative;">
						<input id="contactGroupIdShow" name="contactGroupIdShow"
							type="text" ztree="true" validate="{required:true}" 
							style="margin: 0px;padding-left:4px; width: 170px; height: 19px;  color: rgb(34, 34, 34);" /><img
							src="<%=basePath%>core/img/btn_03.png"
							style="position:absolute; right:0px; top:2px;"
							id="contactGroupIdBtn" href="#"
							onclick="showMenu('contactGroupId'); return false;" />
					</div></td>
				<td align="left"></td>

			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">教育背景：</td>
				<td align="left" class="l-table-edit-td-input" colspan="7"><textarea
						style="height: 120px;margin-top:10px; width:783px;"
						id="educationbackground" name="educationbackground"
						class="l-textarea"></textarea>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">备注：</td>
				<td align="left" class="l-table-edit-td-input" colspan="7"><textarea
						style="height: 120px;margin-top:10px; width:783px;" id="remark"
						name="remark" class="l-textarea"></textarea>
				</td>
				<td align="left"></td>
			</tr>
		</table>
		<div>
			<input style="float: left; margin-left: 350px;margin-right: 70px;"
				class="l-button l-button-submit" type="submit" value="提交" /> 
				<input style="float: left; "
				class="l-button l-button-test" type="reset" value="重置" />
		</div>
	</form>
</body>
</html>

