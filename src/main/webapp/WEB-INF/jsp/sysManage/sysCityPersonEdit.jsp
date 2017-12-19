<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    String sysCityPerson_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysCityPersonEdit</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" /><link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
    var basePath  = '<%=basePath%>';
    var routeName = "sysCityPersonManage"; 
    var row_id = "";
    row_id =<%=sysCityPerson_id%>;
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
<script src="<%=basePath%>app/js/sysManage/sysCityPersonEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->

</head>

<body style="padding:0px; margin:0px;">
    <form id="sysCityPersonManage" method="post">
       	<input type="hidden" id="updateBy" name = "updateBy"/>
        <input type="hidden" id="createBy" name = "createBy"/>
        <input type="hidden" id="createDate" name = "createDate"/>
        <input type="hidden" id="updateDate" name = "updateDate"/>
        <table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin:20px auto;">
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td" style="width:100px;">姓名：<span style="color: red;">*</span></td>
                <td align="left" class="l-table-edit-td-input" style="width:180px;"><input id="name"
                    name="name" type="text" ltype="text" required="required"/></td>
                <td align="left" style="width:20px;"></td>
                <td align="right" class="l-table-edit-td" style="width:100px;">性别：</td>
                <td align="left" class="l-table-edit-td-input" style="width:180px;"><input
                    id="gender" name="gender" type="hidden" ztree="true" value="" />
                <div style="width: 177px; height: 22px; position: relative;">
                        <input id="genderShow" name="genderShow" type="text"
                            ztree="true"
                            style="margin: 0px; padding-left:4px; width: 170px; height:19px; color: rgb(34, 34, 34);" /><img
                            src="<%=basePath%>core/img/btn_03.png"
                            style="position: absolute; right: 0px; top: 2px;"
                            id="genderBtn" href="#"
                            onclick="showMenu('gender'); return false;" />
                    </div></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">出生日期：<span style="color: red;">*</span></td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="birthDate" name="birthDate" type="text" ltype="date" required="required"/></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
            	<td align="right" class="l-table-edit-td">民族：<span style="color: red;">*</span></td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="nation" name="nation" type="text" ltype="text" required="required"/></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">籍贯：<span style="color: red;">*</span></td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="nativePlace" name="nativePlace" type="text" ltype="text" required="required"/></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">出生地：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="birthPlace" name="birthPlace" type="text" ltype="text" /></td>
                <td align="left"></td>
            </tr>
             <tr class="l-table-edit-tr">
             	<td align="right" class="l-table-edit-td">所在区域：<span style="color: red;">*</span></td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="areaCode" name="areaCode" type="hidden" ztree="true" value="" />
                <div style="width: 177px; height: 22px; position: relative;">
                        <input id="areaCodeShow" name="areaCodeShow" type="text"
                            ztree="true" required="required"
                            style="margin: 0px; padding-left:4px; width: 170px; height:19px; color: rgb(34, 34, 34);" /><img
                            src="<%=basePath%>core/img/btn_03.png"
                            style="position: absolute; right: 0px; top: 2px;"
                            id="areaCodeBtn" href="#"
                            onclick="showMenu('areaCode'); return false;" />
                    </div></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">所在机构：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="orgCode" name="orgCode" type="hidden" ztree="true" value="" />
                <div style="width: 177px; height: 22px; position: relative;">
                        <input id="orgCodeShow" name="orgCodeShow" type="text"
                            ztree="true"
                            style="margin: 0px; padding-left:4px; width: 170px; height:19px; color: rgb(34, 34, 34);" /><img
                            src="<%=basePath%>core/img/btn_03.png"
                            style="position: absolute; right: 0px; top: 2px;" id="orgCodeBtn"
                            href="#" onclick="showMenu('orgCode'); return false;" />
                    </div></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">职位：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="position" name="position" type="hidden" ztree="true" value="" />
                <div style="width: 177px; height: 22px; position: relative;">
                        <input id="positionShow" name="positionShow" type="text"
                            ztree="true"
                            style="margin: 0px; padding-left:4px; width: 170px; height:19px; color: rgb(34, 34, 34);" /><img
                            src="<%=basePath%>core/img/btn_03.png"
                            style="position: absolute; right: 0px; top: 2px;"
                            id="positionBtn" href="#"
                            onclick="showMenu('position'); return false;" />
                    </div></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
            	<td align="right" class="l-table-edit-td" style="width:100px;">学历：</td>
                <td align="left" class="l-table-edit-td-input" style="width:180px;"><input
                    id="education" name="education" type="hidden" ztree="true" value="" />
                <div style="width: 177px; height: 22px; position: relative;">
                        <input id="educationShow" name="educationShow" type="text"
                            ztree="true"
                            style="margin: 0px; padding-left:4px; width: 170px; height:19px; color: rgb(34, 34, 34);" /><img
                            src="<%=basePath%>core/img/btn_03.png"
                            style="position: absolute; right: 0px; top: 2px;"
                            id="educationBtn" href="#"
                            onclick="showMenu('education'); return false;" />
                    </div></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">学位：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="degree" name="degree" type="text" ltype="text" /></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">学校名称：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="schoolName" name="schoolName" type="text" ltype="text" /></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">毕业时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="graduateTime" name="graduateTime" type="text" ltype="date" /></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">英语等级：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="englishLevel" name="englishLevel" type="text" ltype="text" /></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">EMAIL：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="email" name="email" type="text" ltype="text" /></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
             	<td align="right" class="l-table-edit-td">祖籍：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="homePlace" name="homePlace" type="text" ltype="text" /></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">现居地：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="currentPlace" name="currentPlace" type="text" ltype="text" /></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">邮政编码：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="postcode" name="postcode" type="text" ltype="text" /></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
            	<td align="right" class="l-table-edit-td">工作时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="hiredDate" name="hiredDate" type="text" ltype="date" /></td>
                <td align="left"></td>
             	<td align="right" class="l-table-edit-td">身份证号：<span style="color: red;">*</span></td>
                <td align="left" class="l-table-edit-td-input"><input id="idNo"
                    name="idNo" type="text" ltype="text" required="required" validate="{minlength:18,maxlength:18}"/></td>
                <td align="left"></td>
             	<td align="right" class="l-table-edit-td">入党时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="joinCommyTime" name="joinCommyTime" type="text" ltype="date" /></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">手机：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="mobileTel" name="mobileTel" type="text" ltype="text" validate="{number:true,minlength:11,maxlength:11}"/></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">家庭电话：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="homeTel" name="homeTel" type="text" ltype="text" /></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">办公电话：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="officeTel" name="officeTel" type="text" ltype="text" /></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">血型：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="bloodtype" name="bloodtype" type="text" ltype="text" /></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">身高(cm)：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="height" name="height" type="text" ltype="text" validate="{number:true}"/></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">体重(kg)：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="weight" name="weight" type="text" ltype="text" validate="{number:true}"/></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">健康状况：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="health" name="health" type="text" ltype="text" /></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">信仰：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="faith" name="faith" type="text" ltype="text" /></td>
                <td align="left"></td>
                  <td align="right" class="l-table-edit-td">婚姻状况：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="maritalStatus" name="maritalStatus" type="text" ltype="text" /></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
               
                  <td align="right" class="l-table-edit-td">别名：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="otherName" name="otherName" type="text" ltype="text" /></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">工作性质：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="workType" name="workType" type="text" ltype="text" /></td>
                <td align="left"></td>
            </tr>
           
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td ">备注：</td> 
                <td align="left" class="l-table-edit-td-input" colspan="7">
                <textarea style="margin-top:10px;height: 120px; width: 780px;"
                 id="remark" name="remark" class="l-textarea" ></textarea>
                </td>
                <td align="left" ></td>
            </tr>
        </table>
        <div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-bottom:20px;">
            <input style="float: left; display:inline-block;" class="l-button l-button-submit" type="submit" value="提交" /> 
            <input style="float: right; display:inline-block;" class="l-button l-button-test" type="reset" value="重置" />
        </div>
    </form>
</body>
</html>

