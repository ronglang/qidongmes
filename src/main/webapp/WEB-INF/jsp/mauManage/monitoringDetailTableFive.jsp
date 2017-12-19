<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>app/js/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>app/js/lib/ligerUI/js/ligerui.all.js"></script>
<script type="text/javascript">
var seqname="铠装";
$(function(){
	$.ajax({
		url:"<%=basePath %>rest/mauProcessMonitoringManageAction/getList?productid=${productid}&seqname="+seqname,
		type:"POST",
		async: false,
		dataType:"json",
		success:function(list){
			if(list!=null){
				load(list);
			}
		}
	});

});

function load(list){
/* 	alert(list.length); */
 	var length = list.length;
 	var str="";
 	str +="<tr>";
 	str +="<td colspan="+2+">"+seqname+"</td>";
		str +="<td>机台</td>";
		str +="<td>"+list[1].mactype+"</td>";
		str +="<td>出线轴号：</td>";
		str +="<td>"+list[1].sourceaxisnumber+"</td>";
 	str +="</tr>";
 	debugger;
 	for(var i=0;i<length;i++){
 	 	if(i%3==0){str +="<tr>";}
 		str +="<td style="+"'border-right: hidden;border-bottom: hidden;'"+">"+list[i].paramname+"</td>";
 		str +="<td>"+list[i].vaule+"</td>";
 	 	if((i+1)%3==0&&i!==0){str +="</tr>";}
 	}
  	str +="<tr>";
 		str +="<td>工作单上检查项目：</td>";
 		str +="<td colspan="+8+"></td>";
 	str +="</tr>";
 	str +="<tr>";
 	str +="<td colspan="+9+"style:'text-align:left;'>OEE项目：</td>";
 	str +="</tr>";
 	$("#ta").append(str);
	
}

    
</script>
<style type="text/css">
body,html {
	height: 99%;
}
#ta{
	width: 99%;
	height: 100%;
}
#ta tr td{
	border:1px solid black;
	font-size: x-small;
	text-align: center;
	height: 4%;
	width: 10%;
	background-color: #EBEBEB;
}
</style>
</head>

<body style="overflow:hidden; padding:2px">
 <div class="l-clear"></div>
    <div id="maingrid" style="overflow: hidden;">
    	<table id="ta">
			
    	</table>
    </div>
   
  <div style="display:none;">
  
</div>
</body>
</html>

