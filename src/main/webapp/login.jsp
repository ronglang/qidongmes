<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object status = request.getAttribute("status");
%>

<!DOCTYPE html>
<html>
<head>
<title>CETC-资源管理系统 --- 登陆</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=basePath%>app/images/cetc.png" media="screen" />


<script type="text/javascript">
		var basePathUrl = "<%=basePath%>";
		var status = "<%=status%>";
</script>
<script type="text/javascript"
	src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery.cookie.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/json2.js"
	type="text/javascript"></script>
<script src="<%=basePath%>app/js/userManage/login.js"
	type="text/javascript"></script>

<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<!–[if IE]>
<script type="text/javascript" src="<%=basePath%>core/js/html5shiv.js"></script>
<![endif]–>
<script type="text/javascript">
	if (status == "504") {
		top.location.reload();
	}
</script>
<%-- <link type="text/css" rel="stylesheet" href="<%=basePath%>app/css/loginThree.css">  --%>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>core/css/loginThree.css">
<style>
.noHH {
	white-space: nowrap;
}
</style>
</head>
<body
	style="background-image: url('<%=basePath%>core/img/login_img/logo_1.jpg');background-attachment:fixed;background-repeat:no-repeat;background-size:cover;-moz-background-size:cover;-webkit-background-size:cover; overflow-x:hidden;overflow-y:hidden;">
	<div id='loading'></div>
	<div
		style="width: 100%;height:550px;text-align: center;">
		<%-- <img alt="logo背景" src="<%=basePath%>core/img/login_img/logo_1.jpg" style="width: 100%;height: 700px"> --%>
		<div
			style="width: 100%;height: 400px;text-align: center;margin: auto;margin-top: 10%">
			<%-- <div style="filter: alpha(opacity=80); opacity: 0.8">
				<img alt="LOGO" src="<%=basePath%>core/img/login_img/logo.jpg">
			</div> --%>
			<div >
				<!-- <span style="font-size: 40px;color: #E6E6FA">二十九所资源管理系统</span> -->
				<span style="font-size: 40px;color: #E6E6FA">电科智联资源管理系统</span>
			</div>
			<div style="position:relative ;">
			<div style="filter: alpha(opacity=50); opacity: 0.5;background:#CAE1FF;width:100%;height: 200px;position: absolute;z-index: 1;">
				
			</div>
			<div style="width: 100%;position: absolute;z-index: 5;margin-top: 40px;">
					<ul id="login_area" class="clear" style="margin-left: 30%;">
						<li><input type="text" class="userInfo" id="account"
							name="account" placeholder="用户名" /></li>
						<li><input type="password" class="userInfo" id="password"
							name="password" placeholder="密码" /></li>

						<li class="remember"><input type="checkbox" name="remember"
							value="记住密码" id='remember' /><label>记住密码</label></li>
						<!-- 				<li><a href="#">忘记密码</a></li> -->
						<li class="doLogin" id="loginbut"></li>
						<li><span
							style="font-size:12px;margin-top:5px;display:inline-block;margin-bottom:5px;color:red"
							id="pwdError">&nbsp;</span></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div
		style="height:30px; line-height:32px; text-align:center;padding-top: 10px ">
		Copyright&nbsp; © 2017-2022 www.cniots.com
		&nbsp;&nbsp;&nbsp;&nbsp;成都电科智联科技有限公司</div>
	<div style="display:none"></div>
	<script>
		//进入默认用户名输入聚焦

		document.getElementById('account').focus();

		if (document.all) {

			$('.intro').css('top', '0px');
		}

		// 按回车登陆
		document.onkeydown = function(event) {
			var e = event || window.event
					|| arguments.callee.caller.arguments[0];

			if (e && e.keyCode == 13) { // enter 键
				document.getElementById("loginbut").click();
			}

		};
		$('#lbt .li_img').hide();
		$('#lbt .li_img').eq(0).show();
		setInterval("lbt()", 5000);
		var str = $('#lbt .li_img');
		var i = 0;
		function lbt() {
			$('#lbt .li_img').eq(i).fadeIn(1000).siblings('.li_img').fadeOut(
					1000);
			i++;
			if (i == str.length)
				i = 0;
		}
		$('#account').focus(function() {
			$('#account').attr('placeholder', '');
		});
		$('#account').blur(function() {
			$('#account').attr('placeholder', '用户名');
		});
		$('#password').focus(function() {
			$('#password').attr('placeholder', '');
		});
		$('#password').blur(function() {
			$('#password').attr('placeholder', '密码');
		});
	</script>


</body>

</html>
