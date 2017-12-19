<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String routeName=(String)request.getAttribute("routeName")==null?"":(String)request.getAttribute("routeName");
	String searchType=request.getParameter("searchType");
%>

<!DOCTYPE HTML>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索</title>

<!-- 默认引用1 -->
<link rel="stylesheet" href="<%=basePath%>core/css/search/autocomplete.css">
<link rel="stylesheet" href="<%=basePath%>core/css/search/style.css">
<script type="text/javascript" src="<%=basePath%>core/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>core/js/autocomplete.js"></script>
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "<%=routeName%>"; 
	var searchType="<%=searchType%>";
</script>
<script type="text/javascript">
var proposals = [];

$(document).ready(function(){
	$("#search-form").autocomplete({
		hints: proposals,
		width: 250,
		height: 30,
		onSubmit: function(text){
			querydata(text);
		}
	});
});

function querydata(name){
	var url="";
	if(searchType=="1"){
		url = basePath + "rest/basicdataPoorhouseholdManageAction/listByEntity?holderName="+name;
	}
	else if(searchType=="2"){
		url = basePath + "rest/sysUserManageAction/listByEntity?name="+name;
	}
	// 提交
	$.ajax({
        url: url,
        type:"post",
        dataType:"json",
        success: function(json){
        	$("#message").html("");
        	var html="<div class='proposal-box' style='width: 95%; top: 150px;margin-left: 10px;'><ul class='proposal-list'>";
        		if(json.data.length>0){
        			for(var i=0;i<json.data.length;i++){
        				if(searchType=="1"){
        					html+="<li class='proposal' onclick='checkdata(this)'>"+json.data[i].holderName+" "+json.data[i].idNumber+" "+json.data[i].moneyYear+"</li>";
        				}else if(searchType=="2"){
        					html+="<li class='proposal' onclick='checkdata(this)'>"+json.data[i].name+" "+json.data[i].account+"</li>";
        				}
        			}
        		}else{
        			html+="未搜索到此人！&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='javascript:add();return false;'>新增</a>";
        		}
        	html+="</ul></div>";
        	$("#message").html(html);	
        },
        error: function(json) {
			$("#message").html("未搜索到此人！");
        }
        });
}
function checkdata(obj){
	var value=obj.innerHTML;
	window.parent.window.manageSearchData(value);
	parent.$.ligerDialog.close();
	parent.$(".l-dialog,.l-window-mask").remove(); 
}
function add(){
	var url="";
	if(searchType=="1"){
		url= basePath+"rest/basicdataPoorhouseholdManageAction/toAddEditPage";
	}else if(searchType=="2"){
		url= basePath+"rest/sysEmployeeManageAction/toAddEditPage";
	}
	//跳转到新增页面
	if(url!="")
	top.$.ligerDialog.open({ 
				url:url,
				height: 600,
				width: 1100,
				modal:true,
				title:"添加"
				});
	//关闭搜索窗口
	parent.$.ligerDialog.close();
	parent.$(".l-dialog,.l-window-mask").remove(); 
}
</script>
</head>
<body>

<div id="demo">
	<div class="wrapper">
		<h3>搜&nbsp;&nbsp;&nbsp;&nbsp;索</h3>
		<div id="search-form" ></div>
		<div id="message"></div>
	</div>
</div>

</body>
</html>

