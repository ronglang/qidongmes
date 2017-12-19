
<%@page import="com.css.commcon.util.SessionUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" language="javascript"
	src="<%=basePath%>app/js/jquery.dropdown.js"></script>
<!-- 菜单样式  start-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/app/css/index.css">
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
	height: 100px;
	font-size: -webkit-xxx-large;
	line-height: 100px;
	background-color: #6495ED;
	position: relative;
	border-top: 1px solid #1D438B;
}

.l-topmenu-logo {
	color: rgb(212, 211, 224);
	align-content: center;
	margin-top: 3px;
	padding-left: 35px;
	line-height: 26px;
}

.l-topmenu-welcome {
	position: absolute;
	margin-top: 30px;
	font-size: large;
	height: 24px;
	line-height: 24px;
	right: 30px;
	top: 2px;
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
<div style="height:50px; line-height:32px; text-align:center;">
	Copyright&nbsp; © 2017-2022 www.cniots.com&nbsp;&nbsp;&nbsp;&nbsp; 成都电科智联科技有限公司
	<iframe style="width:100%;height: 100%;border: 0;"> </iframe>
</div>
<div style="display:none"></div>
