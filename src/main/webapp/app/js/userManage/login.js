$(document).ready(function(){
	$('#loading').hide();
})
if(!userLogin)
 var userLogin = {
	login:function(){
			 var loginurl = basePathUrl+'rest/sysUserManageAction/login'; 
			 /*account=?password=?checkcode=*/
			 var account=$('#account').val();
			 var password=$('#password').val();
			// var checkcode=$('#checkcode').val();
			 var remember = $('#remember')[0].checked;
			 if(!remember){
				 $.cookie('bzhp_cookie_login_user',null,{path: '/'});
				 $.cookie('bzhp_cookie_login_password',null,{path: '/'});
			 }
		   loginurl =loginurl + '?account='+account+'&password='+password;
		   //alert(loginurl);
			 $.ajax({
	                    type: "POST",
	                    dataType: "json",
	                    url: loginurl,
	                    //data: "&account="+account+"&password="+password/*+"&checkcode="+checkcode*/,
	                    success: function (result) {
	                       //result=jQuery.parseJSON(result);   
						   if(result.success){
							     if(remember){
							    	 $.cookie('bzhp_cookie_login_user',null,{path: '/'});
									 $.cookie('bzhp_cookie_login_password',null,{path: '/'});
									 $.cookie('bzhp_cookie_login_user',account,{expires:365,path: '/'});
									 $.cookie('bzhp_cookie_login_password',password,{expires:365,path: '/'});
								 }
								 else{
									 $.cookie('bzhp_cookie_login_user',null,{path: '/'});
									 $.cookie('bzhp_cookie_login_password',null,{path: '/'});
								 }
							   
							   if(!result.firstLogin){
								   userLogin.loginSuccessUrl(result.data);
							   }else{
								   userLogin.firstLoginUrl();
							   }
						   }else{
							   	/*document.getElementById('loginLoading').style.visibility='hidden';*/
						   		userLogin.refreshCheckCode();
						   		/*document.getElementById('password').innerHTML='';*/
						   		$('#loading').hide();
						   		
						   		if(result && result.msg){
						   			document.getElementById('pwdError').innerHTML=result.msg;
						   		}
						   		else{
						   			document.getElementById('pwdError').innerHTML='用户名或密码错误';
						   		}
						   		/*document.getElementById('userError').innerHTML='';
						   		document.getElementById('userError').innerHTML='用户名或密码错误';*/
						   		//$.ligerDialog.tip({  title: '错误提示',content:'密码错误！'});
						   		//$("#checkCode").val(result.msg);
						   		//$.ligerDialog.error(result.msg);
						   }
	                    },
	                    error: function(data) {
	                    	$.ligerDialog.error(data.msg);
	                     }
		
		           });
	},
	init : function() {
		var account =   $.cookie('bzhp_cookie_login_user');
		var password = $.cookie('bzhp_cookie_login_password');
		if(account){
			$('#account').val(account);
			$('#remember')[0].checked = true;
		}
		if(password){
			$("#password").val(password);
			$('#remember')[0].checked = true;
		}
		
				$("#loginbut").click(function(){
					if($("#account").val()==null || $("#account").val()==""){
						//top.$.ligerDialog.error("请输入用户名！");//弹出提示框
						// $.ligerDialog.tip({  title: '提示信息',content:'请输入用户名！'});//右下角弹出提示框
						document.getElementById('pwdError').innerHTML='请输入用户名';
						
					}else if($("#password").val()==null || $("#password").val()==""){
						//top.$.ligerDialog.error("请输入密码！");//弹出提示框
						//$.ligerDialog.tip({  title: '提示信息',content:'请输入密码！'});//右下角弹出提示框
					    document.getElementById('pwdError').innerHTML=' ';
						document.getElementById('pwdError').innerHTML='请输入密码'; 
					}else{
						document.getElementById('pwdError').innerHTML=' ';
						$('#loading').show();
					 /*document.getElementById('pwdError').innerHTML='正在登陆...';*/
					 
					/*	document.getElementById('pwdError').innerHTML=' '; */
						
						/*document.getElementById('loginLoading').innerHTML='';*/
						/*$('#loginLoading').show();*/
						/*document.getElementById('loginLoading').style.visibility='visible';*/
						
						userLogin.login();
					}
				});
				$("#checkCode").click(function(){
					$("#checkCode").val("");
				});
				
	},
	loginSuccessUrl:function(user){
		if(user.leader){
			window.location.href = basePathUrl+'rest/sysUserManageAction/leader';
		}else{
			if(!user.admin){
				window.location.href = basePathUrl+'rest/sysUserManageAction/showMain';//跳转到后台管理页面
			}else{
				window.location.href = basePathUrl+'rest/sysUserManageAction/showMain';//跳转到地图展示页面
			}
		}
		
	},
	//第一次登录，跳转到修改密码页面
	firstLoginUrl:function(){
//		window.location.href = basePathUrl+'rest/sysUserManageAction/toUpdateUserPasswordPage';//跳转到地图展示页面
		 window.parent.top.$.ligerDialog.open({
             height:320,
             width: 400,
             title : '第一次登录系统，请修改密码！',
             url: basePathUrl+'rest/sysUserManageAction/toUpdateUserPasswordPage', 
             showMax: false,
             showToggle: true,
             showMin: false,
             isResize: true,
             slide: false
         });
	},
	refreshCheckCode:function(){
		 var append = '?clearCache=' + new Date().getTime() + 'a' + Math.random();    
		 /*alert(append);*/
        $("#imageCode").attr("src", $("#imageCode").attr("src") + append); 
	}

 };
 
  
 
$(document).ready(function(){
	userLogin.init();
	
	$("#imageCode").click(function() {    
                userLogin.refreshCheckCode();    
    }); 
	$("#huanyizhang").click(function(){
		document.getElementById('imageCode').click();
	})
});




