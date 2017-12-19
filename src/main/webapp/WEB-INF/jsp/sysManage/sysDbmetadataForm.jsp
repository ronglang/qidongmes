<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String sysDbmetadata_id = request.getParameter("id");
%>

<!DOCTYPE HTML>
<html>
    <head>
        

        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>sysDbmetadataForm</title>
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

    <body style="paddding:0px; margin:0px;">
        <form id="SysDbmetadata">
            <table style="margin:20px auto;">
                <tr class="l-table-edit-tr">
                    <td align="right" class="l-table-edit-td">定义：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="definit" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">中文名：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="cnName" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr">
                    <td align="right" class="l-table-edit-td">英文名：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="enName" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">短名：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="shName" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr">
                    <td align="right" class="l-table-edit-td">类型：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="metadataType" type="text" readonly></div></td>
                    <td align="left"></td>
                   <td align="right" class="l-table-edit-td">父：</td>
                   <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="parentId" type="text" readonly></div></td>
                   <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" style="display:none;">
                    <td align="right" class="l-table-edit-td">长度：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="fieldLen" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">精度：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="precision" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" style="display:none;">
                    <td align="right" class="l-table-edit-td">是否主键：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="isPk" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">字段类型：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="fieldType" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" style="display:none;">
                    <td align="right" class="l-table-edit-td">机构：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="orgCode" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">数据库地址：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="dbUrl" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" style="display:none;">
                    <td align="right" class="l-table-edit-td">数据库类型：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="dbType" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">数据库驱动：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="dbDriver" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
                <tr class="l-table-edit-tr" style="display:none;">
                    <td align="right" class="l-table-edit-td">数据库用户名：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="dbUser" type="text" readonly></div></td>
                    <td align="left"></td>
                    <td align="right" class="l-table-edit-td">数据库密码：</td>
                    <td align="left" class="l-table-edit-td-input"><div class="l-text-readonly"><input id="dbPsw" type="text" readonly></div></td>
                    <td align="left"></td>
                </tr>
            </table>
        </form>
    </body>

    <script type="text/javascript">
    var routeName = "sysDbmetadataManage"; 
    var row_id = "";
    row_id = <%=sysDbmetadata_id%>;
    
    $(document).ready(function() {
        if (row_id != null) {
            var parm = "id="+row_id;
            pub_initForm(routeName,parm);
        }
    });
    </script>
</html>

