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

$(function(){
	var seqname = $('#seqname').val();
	var maccode = $('#maccode').val();
	$.ajax({
		url:"<%=basePath %>rest/mauProcessSamplingManageAction/toBackDateCraParam?seqname="+seqname+"&maccode="+maccode,
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
	var seqname = $('#seqname').val();
	var maccode = $('#maccode').val();
 	var length = list.length;
 	var str="";
 	str +="<tr>";
 		str +="<td colspan="+2+">工序："+seqname+"</td>";
		str +="<td colspan="+2+">机器编号："+maccode+"</td>";
		str +="<td colspan="+2+"></td>";
 	str +="</tr>";
 	str +="<tr>";
		str +="<td>参数名称</td>";
		str +="<td>参考值</td>";
		str +="<td>实测值</td>";
		str +="<td>参数名称</td>";
		str +="<td>参考值</td>";
		str +="<td>实测值</td>";
 	str +="</tr>";
 	for(var i=0;i<length;i++){
 	 	if(i%2==0){str +="<tr>";}
 		str +="<td style="+"'border-right: hidden;border-bottom: hidden;'"+">"+list[i].paramname+"</td>";
 		str +="<td>"+list[i].vaule+"</td>";
 		str +="<td>"+list[i].actvaule+"</td>";
 	 	if((i+1)%2==0&&i!==0){str +="</tr>";}
 	}
 	str +="<tr>";
 	str +="<td colspan="+9+"style:'text-align:left;'>备注：</td>";
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
    	<input id="seqname" type="text" value="${seqname}" hidden="hidden"/>
    	<input id="maccode" type="text" value="${maccode}" hidden="hidden"/>
    	<table id="ta">
			
    	</table>
    </div>
   
  <div style="display:none;">
  
</div>
</body>
</html>

