<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object status = request.getAttribute("status");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<script type="text/javascript">
		var basePathUrl = "<%=basePath%>";
		var status = "<%=status%>";
	</script>
<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/userManage/login.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
	
	<!–[if IE]>
	<script type="text/javascript" src="<%=basePath%>core/js/html5shiv.js"></script>
	<![endif]–>
	<script type="text/javascript">
		if (status == "504") {
			top.location.reload();
		}
	</script>
	
	<style type="text/css">
body{
	margin:auto;
	/*background-image:url(images/bg.jpg);*/
	filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/skin/v2011/images/body.png',sizingMethod='scale');
	background-repeat: no-repeat;
	background-positon: 100%, 100%;
	}
#all{
	margin:auto;
	margin-top:12%;
	width:682px;
	height:360px;
	border-radius:4px;
	background-color:#fbfbfb;
	}
.top{
	width:682px;
	height:121px;
	}
.mbody{
	margin-left:122px;
	margin-top:25px;
	}
.ling{
	list-style:none;
	}
.name{
	float:left;
	}
.txtname{
	margin-left:-1px;

	}
.tename{
	float:left;
	width:315px;
	height:33px;
	background-color:#fbfbfb;
	readonly:readonly;
	border:1px solid #d8d8d8;
	font-family:"微软雅黑";
	font-size:16px;
	}
.ling1{
	list-style:none;
	float:left;

	}
.pass{
	margin-top:4px;
	margin-left:-77px;
	}
.password{
	float:left;
	margin-top:4px;
	margin-left:-40px
	}
.zdlogin{
	margin-top:90px;
	margin-left:360px;
	list-style:none;
	font-family:"微软雅黑";
	font-size:16px;
	color:#7c7c7c;
	}
.zdf{
	float:left;
	margin-left:-358px;
	}
.fxk{
	width:14px;
	height:14px;
	border:1px solid #c8c6c6;
	border-radius:2px;
	background-color:#fbfbfb;
	}
.zddl{
	float:left;
	margin-top:-2px;
	margin-left:-338px;
	}
.wjma{
	float:left;
	margin-left:-74px;
	}

.tename:focus{
	border:1px solid #2b83d6;
	outline:none;
    -webkit-appearance:none;
	}
.tename:hover{
	border:1px solid #2b83d6;
	
	}
.zddl:hover{ color:#2b83d6;}
.wjma:hover{ color:#2b83d6;}
.dl:hover{ background-color:#1a72c4;}
.cz:hover{ background-color:#ef7e25;}
.an{
	margin-top:16px;
	float:left;
	list-style:none;
	font-family:"微软雅黑";
	font-size:18px;
	color:#FFFFFF;
	line-height:35px;
	text-align:center;
	}
.dl{
	float:left;
	width:140px;
	height:35px;
	background-color:#2b83d6;
	margin-right:74px;
	}
.cz{
	float:left;
	width:140px;
	height:35px;
	background-color:#f9994d;
	}
.delu{
	font-size:18px;
	color:#FFFFFF;
	}
</style>

  </head>
  
  <body style="background-image: url(<%=basePath%>app/report/images/bg.jpg)">

	<div id="all">

		<div class="top">
			<img src="<%=basePath%>app/report/images/top.png" />
		</div>

		<div class="mbody">
			<ul class="ling">
				<li class="name"><img src="<%=basePath%>app/report/images/name.png" class="tname" /></li>
				<li class="txtname"><input type="text" id="account" name="account" class="tename" placeholder="请输入用户名"></li>
			</ul>
			<br>
			<ul class="ling1">
				<li class="name"><img src="<%=basePath%>app/report/images/password.png" class="pass" /></li>
				<li class="password"><input type="password" id="password" name="password" class="tename" placeholder="请输入密码"></li>
			</ul>
			
			<ul class="zdlogin" style="position: relative;">
				<li class="zdf"><input type="checkbox" class="fxk" id="remember" name="remember" /></li>
				<li class="zddl">记住密码</li>
				<li id="pwdError" style="position: absolute;top:0px;left:-170px;color:red;display: none;">密码错误</li>
				<li class="wjma">忘记密码？</li>
			</ul>
			 <!-- 
			<ul style="background-color: red;width:100%;height:30px;">
				<li class="zdf"><input type="checkbox" class="fxk" /></li>
				<li class="zddl">自动登录</li>
				<li class="wjma">忘记密码？</li>
			</ul>
			-->
			<ul class="an">
				<a href="javascript:" class="delu" id="loginbut"><li class="dl" >登录</li></a>
				<a href="javascript:" class="delu" id="res"><li class="cz">重置</li></a>
			</ul>


		</div>


	</div>
	<script type="text/javascript">
		$(function(){
			$("#res").click(function(){
				$("#account").val("");
				$("#password").val("");
			});
		});
		
	</script>
</body>
</html>
