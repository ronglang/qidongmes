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
        <title>sysContactsForm</title>
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

    <body style="padding:0px; margin:0px;">
        <form id="SysContacts">
            <table style="margin:20px auto;">
                <tr class="l-table-edit-tr" >
                    <td align="right" class="l-table-edit-td" style="width:100px;">姓名：</td>
                    <td align="left" class="l-table-edit-td-input" style="width:180px;"><div class="l-text-readonly"><input id="name" type="text" readonly></div></td>
                    <td align="left" style="width:20px;"></td>
                    <td align="right" class="l-table-edit-td" style="width:100px;">拼音简码：</td>
                    <td align="left" class="l-table-edit-td-input" style="width:180px;"><div class="l-text-readonly"><input id="pinyin" type="text" readonly></div></td>
                    <td align="left" style="width:20px;"></td>
                    <td align="right" class="l-table-edit-td" style="width:100px;">所在机构：</td>
                    <td align="left" class="l-table-edit-td-input" style="width:180px;"><div class="l-text-readonly"><input id="orgIds" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" >
                    <td align="right" class="l-table-edit-td">性别：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="genders" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">学历：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="educations" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">学校名称：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="schoolName" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" >
                    <td align="right" class="l-table-edit-td">EMAIL：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="email" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">手机：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="mobileTel" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">家庭电话：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="homeTel" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" >
                    <td align="right" class="l-table-edit-td">办公电话：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="officeTel" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">工作性质：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="workTypes" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">职位：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="positions" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" >
                    <td align="right" class="l-table-edit-td">分组：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="contactGroupIds" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">维护人：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="createBys" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">维护时间：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="createDate" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <!-- <tr class="l-table-edit-tr" >
                    <td align="right" class="l-table-edit-td">更新人：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="updateBys" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">更新时间：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="updateDate" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr> -->
                <tr class="l-table-edit-tr" >
                    <td align="right" class="l-table-edit-td">备注：</td> 
                     <td align="left" class="l-table-edit-td-input" colspan="7"><textarea style="height: 120px;margin-top:10px; width:783px;" id="remark" name="remark" class="l-textarea" readonly="readonly"></textarea></td>
                     <td align="left"></td>
                </tr>
            </table>
        </form>
    </body>

    <script type="text/javascript">
    var routeName = "sysContactsManage"; 
    var row_id = "";
    row_id = <%=sysContacts_id%>;
    
    $(document).ready(function() {
        if (row_id != null) {
            var parm = "id="+row_id;
            pub_initForm(routeName,parm);
        }
    });
    </script>
</html>

