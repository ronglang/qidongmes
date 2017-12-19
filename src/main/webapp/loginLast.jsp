<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object status = request.getAttribute("status");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>巴中市精准扶贫信息系统 --- 登陆</title>
		
	 <link type="text/css" rel="stylesheet" href="<%=basePath%>app/css/login.css">
		<script>
		 var _IE = (function(){
		        var v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');
		        while (
		                div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
		                        all[0]
		                );
		        return v > 4 ? v : false ;
		    }());
		
		if(_IE<=9&&_IE!=0){
			var ieLink=document.createElement('link');
			ieLink.type="text/css";ieLink.rel="stylesheet";ieLink.href="http://localhost:8080/bzhp/app/css/ieLogin.css";
			document.getElementsByTagName("head")[0].appendChild(ieLink);
		}
		</script>
		<script type="text/javascript">
		var basePathUrl = "<%=basePath%>";
		var status = "<%=status%>";
		</script>
		<script type="text/javascript" src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
		
	   <%--  <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />   --%>
    	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery.cookie.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
		<script src="<%=basePath%>app/js/userManage/login.js" type="text/javascript"></script>
		<script type="text/javascript">
		
		if(status == "504"){
			top.location.reload(); 
		}
				 
		</script>
	</head>
	<body>

<div class='rightBg'>
</div>	

<div class="outDiv">


<div class="mainDiv">


<img src="<%=basePath%>app/images/imgLogin/login-tit_15.png" class="Logo"/>

			<div class="loginArea">
				<div class="loginInfo">
					<h3>登录</h3>
					<br /> <span><img
						src="<%=basePath%>app/images/imgLogin/userPic_03.png" alt="" /> </span><input
						class="userName" type="text" id="account" placeholder="用户名" /><br />
					<span id='userError' class='tip'
						style='background-color:#f5f5f5;border:none;width:100px'></span> <br />
					<span><img
						src="<%=basePath%>app/images/imgLogin/mima_03.png" alt="" /> </span><input
						class="passWord" type="password" id="password" placeholder="密码" />
					<br /> <span id='pwdError' class='tip'
						style='background-color:#f5f5f5;border:none;width:100px'></span> <br />
					<!--  <input type="text" class="yanzhengma" id='checkcode' placeholder="请输入验证码"/> -->
					<%-- <img src="<%=basePath%>app/images/imgLogin/login_07.png" class="yanzhengmaArea"/> --%>
					<ul class="yanzhengmaArea">
						<li class="veri-input"><input id="checkCode" name="checkcode"
							class='yanzhengma' placeholder="请输入验证码" /></li>
						<li class="veri-code"><img id="imageCode"
							src="<%=basePath%>rest/sysUserManageAction/getImage"
							title="看不清，点击换一张" style="margin-top:5px;cursor: pointer;" /></li>
					</ul>
					<a href="javascript:void(0)" id='huanyizhang'>换一张</a> <img
						src="<%=basePath%>app/images/imgLogin/5-121204193943.gif"
						id='loginLoading'
						style="position:relative;left:-30px;top:30px;display:none;" />
					<div class="loginBtn" id="loginbut">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</div>
				</div>

			</div>
			<%--  <img src="<%=basePath%>app/images/imgLogin/login_18.png" style="width:370px;position: relative;top: 50%"/> --%>

</div>
</div>
<script>

// 按回车登陆
document.onkeydown=function(event){
	           var e = event || window.event || arguments.callee.caller.arguments[0];
        
            if(e && e.keyCode==13){ // enter 键
               document.getElementById("loginbut").click();
           }
          
       }; 


</script>
</body>
	
</html>
