<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String materGgxh = request.getParameter("materGgxh");
	String areaType = request.getParameter("areaType");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript">
    var materGgxh = '<%=materGgxh%>';
    var areaType = '<%=areaType%>';
        $(function ()
        {
        	var url = '<%=basePath%>rest/statStoreObjVoManageAction/getStoreAddress?materGgxh='+materGgxh+'&areaType='+areaType;
            getTable(url);
            form();
        });
        
        function getTable(url){
        	window['g'] =
                $("#maingrid").ligerGrid({
                	url:url,
                    height: '93%',
                    columns: [
                    { display: '物料名称', name: 'materName'},
                    { display: '物料规格', name: 'materGgxh'},
                    { display: '物料颜色', name: 'materColor'},
                    { display: '物料批次', name: 'batchCode'},
                    { display: '<span style="color:#EE2C2C">*位置信息</span>', name: 'address',render:function(row){
                    	var html = "";
                    	html = "<span style='color:#EE2C2C'>"+row.address+"</span>";
                    	return html;
                    }},
                    ],  
                    rownumbers: true
                });
                $("#pageloading").hide();
        }

        function deleteRow()
        {
            g.deleteSelectedRow();
        }
        
        var groupicon = "";
        var form;
       
        
        function form(){
        	
        	 //创建表单结构 
            form = $("#form2").ligerForm({
                inputWidth: 200, labelWidth: 65, space: 10,
                fields: [
              	{ display: "物料规格", name: "materGgxh", newline: false,type: "select",editor: {url:'<%=basePath%>rest/statStoreObjVoManageAction/getMaterGgxh',valueField:'text'}},
              	{ display: "物料颜色", name: "materColor", newline: false,type: "select",editor: {url:'<%=basePath%>rest/statStoreObjVoManageAction/getMaterColor',valueField:'text'}},
                ],
                validate  : true
            });
        	
        }
        
        function search(){
        	//form.valid();
        	var materGgxh = $("[name=materGgxh]").val();
        	var materColor = $("[name=materColor]").val();
			url = '<%=basePath%>rest/statStoreObjVoManageAction/getStoreAddress?materGgxh='
				+ materGgxh + '&materColor=' + materColor;
		getTable(url);
	}
</script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div class="l-loading" style="display:block" id="pageloading"></div>
	<div class="l-clear"></div>
	<div style="text-align: center;width: 100%;position: relative;;">
		<form id="form2" action=""></form>
		<div class="liger-button" onclick="search()"
			style="float: right;position: absolute;right: 20px;top: 3px; ">搜索</div>
	</div>
	<div id="maingrid" style="overflow: hidden;"></div>

	<div style="display:none;"></div>
</body>
</html>

