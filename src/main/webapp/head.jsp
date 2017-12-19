<%-- <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/app/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/app/css/initStyle.css">
	<script src="<%=basePath%>/core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script type="text/javascript" language="javascript" src="<%=basePath%>app/js/jquery.dropdown.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	.index_head{
		width: 100%;
		height: 75px;
		background:url('<%=basePath%>/app/images/main/u3.png')
	}
	.index_navs{
		width:100%;
		height:32px;
		background:url('<%=basePath%>/app/images/main/u136.png')
	}
	</style>
  </head>
  <script>
			var basePath  = '<%=path%>';
			var ctx = '<%=basePath%>';
	</script>
  <body>
  		<div class="index_head">
  			<img id="u5_img" class="img" src="<%=basePath%>/app/images/main/u5.png">
  			<img id="u5_img" class="img" src="<%=basePath%>/app/images/main/u7.png">
  			
  			<!-- menu -->
  			<div class="index_menu">
  			<c:forEach items="${menu}" var="item">
  			<div class="menu right">
  				<img id="${item.id}" name="${item.name}" onclick="showNav('${item.id}')" class="img1" src="<%=basePath%>${item.image}"> <br />
  				<div id="nav${item.id}" class="navs">
  				<!-- 二级菜单 -->
  					<c:forEach items="${item.children}" var="nav">
  					<dl id="${nav.id}" class="nav_dl ">
  					<a href="<%=basePath%>${nav.url}?id=${nav.id}"><dt onclick="hiddenNav(this);" id="${nav.id}" class="nav">${nav.name}<br /></dt></a>
  						<dd id="dd_${nav.id}" class="nav_dd">
  						<!-- 三级菜单 -->
  						 <c:forEach items="${nav.children}" var="nav1">
  								<a href="<%=basePath%>${nav1.url}?id=${nav1.id}"><div id="${nav.id}" class="child">${nav1.name}</div></a>
  						</c:forEach> 
  						</dd>
  					</dl>
  					</c:forEach>
  				</div>
  				</div>
  			</c:forEach>
  			</div>
  		</div>
  		<!-- nav -->
  		<div id="index_navs" class="index_navs">
  		</div>
  </body>
  <script>
  	//显示导航
  	function showNav1(pcode){
  		if(pcode==null||pcode==""){
  			alert("查询无效");
  			return 
  		}
  		
  		$.ajax({
  			type:"POST",
  			url:basePath +"/rest/sysUserManageAction/findMenuByPcode",
  			data:"pcode=" + pcode,
  			dataType:"json",
  			success: function(data){
  				$("#index_navs").html("");
  				$.each(data.data,function(i,obj){
  					var htmls ="<div id=" + obj.id + " class='index_nav left'><span>" + obj.name +"</span><img id='u103_line' class='img2' src='"+ ctx + "app/images/main/u147_line.png' alt='u103_line'></div>";
  					var div = $("#index_navs");
  						div.append(htmls);
  				});
  			}
  		});
  	}
  	
  	var pid = "";
  	function showNav(id){
  		$("#nav" + pid).css("display","none");
  		pid=id;
  		$("#nav" + id).css("display","block");
  	}
  </script>
  
</html>
 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>CETC-资源管理系统 --- 首页</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=basePath%>app/images/cetc.png" media="screen" />
</head>
<body>
<div id="pageloading"></div>
<div id="topmenu" class="l-topmenu">
		<div class="l-topmenu-logo">
			<a href="http://www.cetc.com.cn/"><img
				src="<%=basePath%>app/images/homelogo.jpg" style="height: 90px;" /></a>
		</div>
		<div class="l-topmenu-welcome">
			<c:if test="${empty session_user.name}">
				<a href="login.html" class="l-link2">登录</a>&nbsp;/
        	<a href="#" class="l-link2">联系主管</a>
			</c:if>
			<c:if test="${!empty session_user.name}">
				<a href="#" class="l-link2" style="text-decoration: none;"><img
					src="<%=basePath%>app/images/images/pes.png" alt="用户"
					style="height: 20px;ma" />&nbsp;&nbsp;${session_user.name}</a>&nbsp;/
  			<a href="javascript:void(0);" onclick="outLog();return false;">
					退出登录</a>
			</c:if>
		</div>
	</div>
</body>
</html>




