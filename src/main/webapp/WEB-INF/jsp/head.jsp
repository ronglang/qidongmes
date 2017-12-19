<%@page import="com.css.commcon.util.SessionUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" language="javascript"
	src="<%=basePath%>app/js/jquery.dropdown.js"></script>
<!-- 菜单样式  start-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/app/css/index.css">
<link rel="shortcut icon" type="image/x-icon"
	href="<%=basePath%>app/images/cetc.png" media="screen" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/app/css/style.css">
<!-- 菜单样式  end-->
<style>
body,html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 2px;
	margin-right: 2px;
}

.l-layout-top {
	background: #102A49;
	color: White;
}

.l-layout-bottom {
	background: #E5EDEF;
	text-align: center;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 99999;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 16px;
	border: 1px solid white;
	margin: 4px;
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.l-winbar {
	background: #2B5A76;
	height: 30px;
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	z-index: 99999;
}

.space {
	color: #E7E7E7;
}
/* 顶部 */
.l-topmenu {
	margin: 0;
	padding: 0;
	height: 110px;
	font-size: -webkit-xxx-large;
	line-height: 50px;
	background-image:url("<%=basePath%>app/images/logo11.jpg");
	background-size:100% 100px;
	position: relative;
	border-top: 1px solid #1D438B;
}

.l-topmenu-logo {
	color: rgb(212, 211, 224);
	align-content: left;
	margin-top: 3px;
	padding-left: 3px;
	line-height: 26px;
}

.l-topmenu-welcome {
	position: absolute;
	/* margin-top: 30px; */
	font-size: large;
	height: 20px;
	line-height: 24px;
	right: 20px;
	top: 70px;
	color: #070A0C;
}

.l-topmenu-welcome a {
	color: rgb(212, 211, 224);
	text-decoration: underline
}

.body-gray2014 #framecenter {
	margin-top: 3px;
}

.viewsourcelink {
	background: #B3D9F7;
	display: block;
	position: absolute;
	right: 10px;
	top: 3px;
	padding: 6px 4px;
	color: #333;
	text-decoration: underline;
}

.viewsourcelink-over {
	background: #81C0F2;
}

#skinSelect {
	margin-right: 6px;
}
</style>
<div id="topmenu" class="l-topmenu">
	<div class="l-topmenu-logo">
		
	</div>
	<div class="l-topmenu-welcome">
		<c:if test="${empty session_user.name}">
			<a href="login.html" class="l-link2">登录</a>&nbsp;/
        	<a href="#" class="l-link2">联系主管</a>
		</c:if>
		<c:if test="${!empty session_user.name}">
			<a href="#" class="l-link2" style="text-decoration: none;"><img
				src="<%=basePath%>app/images/pes.png" alt="用户"
				style="height: 20px;ma" />&nbsp;&nbsp;<font style="color:black">${session_user.name}</font></a>&nbsp;/
  			<a href="javascript:void(0);" onclick="outLog();return false;" style="text-decoration: none;font-size: small;color:black">
				注销</a>
		</c:if>
	</div>
</div>
<script>
		var basePath  = '<%=basePath%>';
		function outLog() {
			$.ajax({
				type : "POST",
				url : basePath + "/rest/sysUserManageAction/logOutWebUser",
				data : "",
				dataType : "json",
				success : function(data) {
					if (data.success = true) {
						window.location.href = basePath + "login.jsp";
					}
				}
			});
		}
	</script>
