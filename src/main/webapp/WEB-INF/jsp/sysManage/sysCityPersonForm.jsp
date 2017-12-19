<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String sysCityPerson_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
    <head>
        

        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>sysCityPersonForm</title>
        <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
        <script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
        <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
        <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

        <script>
            var basePath  = '<%=basePath%>';
        </script>
    </head>

    <body style="margin:0px padding:0px;">
        <form id="SysCityPerson">
        <table style="margin:20px auto">
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td" style="width:100px;">姓名：</td>
                <td align="left" class="l-table-edit-td-input" style="width:180px;"><input id="name"
                    type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left" style="width:20px;"></td>
                <td align="right" class="l-table-edit-td" style="width:100px;">性别：</td>
                <td align="left" class="l-table-edit-td-input" style="width:180px;"><input
                    id="gender" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left" style="width:20px;"></td>
                <td align="right" class="l-table-edit-td" style="width:100px;">学历：</td>
                <td align="left" class="l-table-edit-td-input" style="width:180px;"><input
                    id="education" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">学位：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="degree" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">英语等级：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="englishLevel" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">学校名称：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="schoolName" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">毕业时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="graduateTime" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">EMAIL：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="email" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">民族：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="nation" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">籍贯：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="nativePlace" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">出生地：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="birthPlace" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">祖籍：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="homePlace" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">现居地：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="currentPlace" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">工作时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="hiredDate" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">身份证号：</td>
                <td align="left" class="l-table-edit-td-input"><input id="idNo"
                    type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">婚姻状况：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="maritalStatus" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">邮政编码：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="postcode" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">出生日期：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="birthDate" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">家庭电话：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="homeTel" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">办公电话：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="officeTel" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">手机：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="mobileTel" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">血型：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="bloodtype" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">身高：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="height" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">体重：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="weight" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">健康状况：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="health" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">信仰：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="faith" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">入党时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="joinCommyTime" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">所在区域代码：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="areaCode" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">别名：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="otherName" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                  <td align="right" class="l-table-edit-td">工作性质：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="workType" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">职位：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="position" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">所在机构名称：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="orgCode" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">创建人：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="createBy" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td">创建时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="createDate" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                <td align="right" class="l-table-edit-td">更新人：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="updateBy" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
                 <td align="right" class="l-table-edit-td">更新时间：</td>
                <td align="left" class="l-table-edit-td-input"><input
                    id="updateDate" type="text" ltype="text" readonly="readonly"></input></td>
                <td align="left"></td>
            </tr>
            <tr class="l-table-edit-tr">
                <td align="right" class="l-table-edit-td ">备注：</td> 
                <td align="left" class="l-table-edit-td-input" colspan="7">
                <textarea style="margin-top:10px;height: 120px; width: 780px;" id="remark" name="remark" class="l-textarea" readonly="readonly"></textarea>
                </td>
                <td align="left" ></td>
            </tr>
        </table>
    </form>
    </body>

    <script type="text/javascript">
    var routeName = "sysCityPersonManage"; 
    var row_id = "";
    row_id = <%=sysCityPerson_id%>;
    
    $(document).ready(function() {
        if (row_id != null) {
            $("#SysCityPerson").ligerForm();
            var parm = "id="+row_id;
            pub_initForm(routeName,parm);
        }
    });
    </script>
</html>

