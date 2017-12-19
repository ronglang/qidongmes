<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.css.sysManage.bean.SysUser,com.css.commcon.util.SessionUtils" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	SysUser user = SessionUtils.getUser(request);
	String account = user.getAccount();
	Integer userId = user.getId();
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>智慧巴中  · 精准扶贫数字管理系统</title>

<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=basePath %>core/js/jquery-1.7.2.js"></script>
 <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
 <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
 <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery.cookie.js" type="text/javascript"></script>
 <script src="<%=basePath %>core/plugin/LigerUI/lib/json2.js" type="text/javascript"></script>
 <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>

 <script type="text/javascript">
 	var basePath  = '<%=basePath%>';
 	var userId = <%=userId%>;
 	var isSimpleMap = false;
 </script>
 <script type="text/javascript" src="<%=basePath %>flexmap/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath %>flexmap/embedMap.js"></script>
<style type="text/css">
  body{ 
  	margin: 0px;padding: 0px;
  } 

  .headBackground{
 	 background-image: url("<%=basePath %>core/img/imgForLeader/topbg_03.png");
  	height:40px;
  }
</style>

</head>

<body style="width: 99.8%">


         <%--  <div position="top"  class="headBackground">
          <img style='position:absolute;top:0px;left:23px;' src='<%=basePath %>core/img/top-logo_01.png'/>
          <img style='position:absolute;top:0;right:0;' src='<%=basePath %>core/img/imgForLeader/login-show_02.png'/>  
           		<div style="position: absolute;top: 20px;right: 100px;font-size: 12;color: white;">
           			
           			
           			<table border="0" cellspacing="0" cellpadding="0">
           				<tr>
           					<td style="width: 300px;"></td>
           					<td>欢迎用户：<%=account%>&nbsp;&nbsp; &nbsp;</td>
           					<td><a style="color: white;" href="javascript:logout();">注销</a>&nbsp;&nbsp; &nbsp;</td>
           					<td><a style="color: white;" href="javascript:showUpdateUserPasswordDialog()">修改密码</a>&nbsp;&nbsp; &nbsp;</td>
           					<td><a style="color: white;" href="javascript:enterMain()">后台管理系统</a></td>
           					
           				</tr>
           			</table>
           		</div>
           </div> --%>
           <div id="layout1">
			<!--  <div position="left"  id="accordion1" class="l-accordion-panel"  style="height: 100%;">
				   
	         </div>   -->
	        <div position="center" style="margin-top:0px;"> 
	        	<!-- -->
				<!-- <div tabid="home" title="一张图" lselected="true"  style="height:100%"> -->
					<div id="flashContent" style="height:100%">
				        <p>
				            To view this page ensure that Adobe Flash Player version 
				            11.1.0 or greater is installed. 
				        </p>
				        <script type="text/javascript"> 
				            var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://"); 
				            $("#flashContent").html("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
				                            + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
				        </script> 
				     </div>
				<!-- </div>  -->
	        </div> 
       </div> 

     
<script type="text/javascript">
		var tab = null;
		var tabItems = [];
		var baseUrl='<%=basePath %>';
		var loginAccount = "<%=account%>";
		var timeid;
		var repTime = 600*1000;
		
       $(function () {
	       	initLayout();
	        var  height = $(".l-layout-center").height();
	       	initMenu(height);
	       	initTab(height);
	       	//timeid = window.setInterval(loadserverWarnData,repTime);
	       //	loadserverWarnData();
	       	//loadBacklog();
       });

       
       //加载待办提醒
       function loadBacklog(){
    	   $.ajax( {
      			type : 'post',
      			url : baseUrl+"rest/ondutyHandoverManageAction/listByEntityCon",
      			//data:"{\"id\":1}",
      			//contentType : 'application/json',
      			dataType : 'json',
      			error : function() {
      			},
      			success : function(res) {
      				if(res.success && res.data){
      					if(res.data.length > 0){
      						var con = "您有"+res.data.length+"条值班待办信息未处理！";
      						top.$.ligerDialog.tip({ title: '系统提醒信息',content:con,height:'260'});
      					}
      				}
      			}
      		});
       }
       
       function getFramecenterHeight(){
    	   return $("#framecenter").height();
       }
       
       function getFramecenterWidth(){
    	   return $("#framecenter").width();
       }
	   	
		function callTelNum(tel){
			showCallTelPage(tel);
		}
       
		function showCallTelPage(tel){
			top.$.ligerDialog.open({ 
				url: basePath+"rest/sysContactsManageAction/toCallTelPage?tel="+tel, 
				height: 600,
				width: 850,
				title:"电话呼叫",
				modal:true});
		}
		
       var isRep = true;
       //var  content = '周三省领导来我局检查，请大家作好准备工作！';
       var head = "<a href='javascript:repWarncOnclick();'>我知道了</a>&nbsp;&nbsp;<lable>提醒间隔时间：</lable><input type='number' style='width: 60px;' onchange='setRepTime(this.value);'/><label>秒</label></br>";
       var tipobj;
       function loadWarn(content){
    	   tipobj = top.$.ligerDialog.tip({ title: '系统提醒信息',content:content,height:'260'});
       }
       
       function repWarncOnclick(){
    	 window.clearInterval(timeid); 
       }
       
       function setRepTime(second){
    	   repTime = second*1000;
    	   window.clearInterval(timeid); 
    	   timeid = window.setInterval(loadserverWarnData,repTime);
       }
       
       function ref(content){
    	   if(tipobj)tipobj.close();
    	   loadWarn(head+content);
       }
       
       function loadserverWarnData(){
    	   $.ajax( {
   			type : 'post',
   			url : baseUrl+"rest/ondutySysWarnManageAction/querySysWarnByCurentUser",
   			//data:"{\"id\":1}",
   			//contentType : 'application/json',
   			dataType : 'json',
   			error : function() {
   			},
   			success : function(res) {
   				if(res.success && res.data){
   					if(res.data.length > 0){
   						var contentserver='';
   						for(var i = 0;i<res.data.length;i++){
   							contentserver += '<label style="color: red;font-size: 12px;">'+(i+1)+'、'+res.data[i].title+'</label></br>'+res.data[i].content+"</br>";
   						}
   						ref(contentserver);
   					}
   				}
   			}
   		});
       }
            
       function initLayout(){
       		 $("#layout1").ligerLayout({ leftWidth: 220, height: '100%'});
       		/*  alert( $('.l-layout-top').css('height'));
       		 $('.l-layout-top').css('height','40px');
       		 alert( $('.l-layout-top').css('height')); */
       }
       
       function initMenu(height){
    	   
    	   $.ajax({
    		   type: "POST",
    		   url: baseUrl+"rest/resourceManager/queryUserMenu",
    		   data: {},
    		   async:false,
    		   success: function(data){
    			   $.each(data, function(i,item){ 
	   	        		if(i==0){
	   	        			$("#accordion1").append("<div title='"+item.text+"'>"
	   	        					+"<ul id='tree"+item.id+"' style='margin-top:3px;'>"
	   	        					+"</div>").addClass("l-scroll");
	   	        		}
	   	        		else{
	   	        			$("#accordion1").append("<div title='"+item.text+"'>"
	   	        					+"<ul id='tree"+item.id+"' style='margin-top:3px;'>"
	   	        					+"</div>");
	   	        		}
	   	        		initTree("tree"+item.id+"",item.children);
   	        		});  	
    		   }
    		});
        	
    	
	       	$("#accordion1").ligerAccordion({
	       			height: height-24,
	       			width:200
	        });
       	
	       	function initTree(id,data){
		       	 $("#"+id).ligerTree({
			            data : data,
			            checkbox: false,
			            slide: true,
			            nodeWidth: 160,
			           // idFieldName:'code',
				       // parentIDFieldName:'pcode',
			            onSelect: function (node)
	                    {
	                     	 if (!node.data.url) return;
	                        var tabid = $(node.target).attr("tabid");
	                        if (!tabid)
	                        {
	                            tabid = node.data.id;//new Date().getTime();
	                            $(node.target).attr("tabid", tabid)
	                        } 
	                        addTab(tabid, node.data.text, node.data.url);
	                    }
		       		}); 
		    }
       	
       }
       
       function initTab(height){
    	   $("#framecenter").ligerTab({
    		  height: height,
               showSwitchInTab : true,
               showSwitch: false,
               onAfterAddTabItem: function (tabdata)
               {
                   tabItems.push(tabdata);
                   saveTabStatus();
               },
               onAfterRemoveTabItem: function (tabid)
               { 
                   for (var i = 0; i < tabItems.length; i++)
                   {
                       var o = tabItems[i];
                       if (o.tabid == tabid)
                       {
                           tabItems.splice(i, 1);
                           saveTabStatus();
                           break;
                       }
                   }
               },
               onReload: function (tabdata)
               {
                   var tabid = tabdata.tabid;
                   addFrameSkinLink(tabid);
               }
           });
    	   
    	   //tab = liger.get("framecenter");
    	   //tab.onClose = handleTabClose;
       }

       function handleTabClose(tabid){
    	   //alert(tabid);
       }

       function addTab(tabid, text, url){
    	   if(url == 'onemap'){
    		   tab.selectTabItem('home'); 
    	   }else{
    		   tab.addTabItem({
                   tabid: tabid,
                   text: text,
                   url: baseUrl+url,
                   callback: function ()
                   {
                       addFrameSkinLink(tabid); 
                   }
               });
    	   }
       }
         
       function selectOneMapTab(){
    	   tab.selectTabItem('home'); 
       }
       
       function saveTabStatus()
       { 
           $.cookie('liger-home-tab', JSON2.stringify(tabItems));
       }
       function addFrameSkinLink(tabid)
       {
           var prevHref = getLinkPrevHref(tabid) || "";
           var skin = getQueryString("skin");
           if (!skin) return;
           skin = skin.toLowerCase();
           attachLinkToFrame(tabid, prevHref + skin_links[skin]);
       }
       function addFrameSkinLink(tabid)
       {
           var prevHref = getLinkPrevHref(tabid) || "";
           var skin = getQueryString("skin");
           if (!skin) return;
           skin = skin.toLowerCase();
           attachLinkToFrame(tabid, prevHref + skin_links[skin]);
       }
       function attachLinkToFrame(iframeId, filename)
       { 
           if(!window.frames[iframeId]) return;
           var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
           var fileref = window.frames[iframeId].document.createElement("link");
           if (!fileref) return;
           fileref.setAttribute("rel", "stylesheet");
           fileref.setAttribute("type", "text/css");
           fileref.setAttribute("href", filename);
           head.appendChild(fileref);
       }
       function getLinkPrevHref(iframeId)
       {
           if (!window.frames[iframeId]) return;
           var head = window.frames[iframeId].document.getElementsByTagName('head').item(0);
           var links = $("link:first", head);
           for (var i = 0; links[i]; i++)
           {
               var href = $(links[i]).attr("href");
               if (href && href.toLowerCase().indexOf("ligerui") > 0)
               {
                   return href.substring(0, href.toLowerCase().indexOf("lib") );
               }
           }
       }
       function getQueryString(name)
       {
           var now_url = document.location.search.slice(1), q_array = now_url.split('&');
           for (var i = 0; i < q_array.length; i++)
           {
               var v_array = q_array[i].split('=');
               if (v_array[0] == name)
               {
                   return v_array[1];
               }
           }
           return false;
       }
         
       function logout(){
    	   window.location.href = baseUrl+'login.jsp';
       }
       
        function enterMain(){
    	   window.location.href =baseUrl+'rest/sysUserManageAction/main';
       }
       
       function showUpdateUserPasswordDialog(){
    	   window.parent.top.$.ligerDialog.open({
               height:320,
               width: 400,
               title : '用户密码修改',
               url: baseUrl+'rest/sysUserManageAction/toUpdateUserPasswordPage', 
               showMax: false,
               showToggle: true,
               showMin: false,
               isResize: true,
               slide: false
           });
       }
      
 </script>
</body>
</html>