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
    
    <title>智能线缆管理系统后台登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		var basePathUrl = "<%=basePath%>";
		var status = "<%=status%>";
	</script>
<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
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
		body,h1,h2,h3,h4,h5,h6,div,img,ul,li,dl,dd,dt,ol,p{
			padding: 0;
			margin: 0;
		}
		body{
			background: url("<%=basePath%>app/images/newindex/login_bg.jpg");
			/* 背景图垂直、水平均居中 */
			background-position: center center;
			/* 背景图不平铺 */
			background-repeat: no-repeat;
			/* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
			background-attachment: fixed;
			/* 让背景图基于容器大小伸缩 */
			background-size: cover;
			/*当内容的高度大于viewport的高度时，会出现滚动条。希望背景图始终相对于viewport固定，即使用户滚动时也是一样。*/
			background-attachment: fixed;
		}
		#login{
			width: 679px;
			height: 560px;
			margin: 100px auto;
		}
		#login .l_top{
			width: 679px;
			height: 121px;
			background: url("<%=basePath%>app/images/newindex/l_top_bg.png");
		}
		#login .l_con {
			padding-top: 50px;
		}
		#login .l_con label{
			display: inline-block;
			text-align: center;
			height: 70px;
			line-height: 70px;
			width: 100%;
			position: relative;
		}
		#login .l_con label span{
			background-color: #CD3301;
			display: inline-block;
			height: 40px;
			width: 40px;
			position: relative;
			position: absolute;
			left: 30%;
		}
		#login .l_con label span img{
			position: absolute;
		    top: 50%;
		    left: 50%;
		    transform: translate(-50%, -50%);
			width: 18px;
			height: 22px;
		}
		#login .l_con label input{
			height: 30px;
			width: 200px;
			position: absolute;
			top: 0;
			left: 39%;
			border: 1px solid #D4D4D4;
		}
		#login form{
			height: 339px;
			background-color: white;
			width: 100%;
		}
		#login .pass_error{
			width: 50%;
			height: 20px;
			margin: 0 auto;
			text-align: center;
			font-size: 14px;
			color: red;
		}
		#login .l_nopass{
			width: 50%;
			height: 50px;
			margin: 0 auto;
			text-align: center;
			font-size: 14px;
		}
		#login .l_nopass .ln_1{
			float: left;
			width: 50%;
		}
		#login .l_nopass .ln_1 input{
			cursor: pointer;
		}
		#login .l_nopass .ln_text{
			float: left;
			width: 50%;
		}
		#login .l_nopass .ln_text a{
			text-decoration: none;
			color: #D45229;
		}
		#login .l_nopass .ln_text a:hover{
			text-decoration:underline;
		}
		#login .l_foot{
			text-align: center;
			
		}
		#login .l_foot .btn{
			width: 100px;
			height: 30px;
			border: none;
			background-color: #CD3301;
			color: white;
			border-radius: 5px;
			cursor: pointer;
			font-size: 15px;
		}
		#login .l_foot .btn:hover{
			background-color: #DB623B;
		}
		#login .l_foot .res{
			width: 100px;
			height: 30px;
			border: none;
			background-color: #EC87EC;
			color: white;
			border-radius: 5px;
			cursor: pointer;
			font-size: 15px;
			margin-left: 20px;
		}
		#login .l_foot .res:hover{
			background-color: #EDAAED;
		}
		#footer{
			width: 100%;
			position: fixed;
			bottom: 0;
			height: 20px;
			font-size: 14px;
			text-align: center;
		}
	
	</style>

  </head>
  
  <body>
    	
    	<div id='loading'></div>
    	<div id="login">
			<div class="l_top"></div>
			<form action="" method="post">
					
				<div class="l_con">
					
					<label>
						<span><img src="<%=basePath%>app/images/newindex/user_bg.png"/></span>
						<input type="text" class="userInfo" id="account" name="account" value="" placeholder="请输入登录账号" />
					</label>
					<label>
						<span><img src="<%=basePath%>app/images/newindex/pass_bg.png"/></span>
						<input type="password" class="userInfo" id="password" name="password" value="" placeholder="请输入登录密码"/>
					</label>
				</div>
				<div class="pass_error">
					<span id="pwdError" ></span>
				</div>
				<div class="l_nopass">
					<div class="ln_1 remember">
						<input type="checkbox" name="" id="remember" name="remember" value="记住密码" /><span>记住密码</span>
					</div>
					<div class="ln_text">
						<a href="javascript:">忘记密码？</a>
					</div>
					
				</div>
				<div class="l_foot">
					<input type="button" name="" class="btn doLogin" id="loginbut" value="登  录" />
					<input type="reset" name="" class="res" value="重  置" />
				</div>
				
			</form>
			
			
		</div>
		<div id="footer">
			Copyright&nbsp;&nbsp;©&nbsp;2017-2022&nbsp;www.cniots.com&nbsp;&nbsp;&nbsp;&nbsp;成都电科智联科技有限公司
		</div>
    	<div style="display:none"></div>
    	
    	<script type="text/javascript">
    			$(function(){
    				
    				//按回车登录
    				document.onkeydown = function(event) {
    					var e = event || window.event || arguments.callee.caller.arguments[0];
    					if (e && e.keyCode == 13) { // enter 键
    						document.getElementById("loginbut").click();
    					}

    				};
    				
    			});
    		
    	
    	</script>
  </body>
</html>
