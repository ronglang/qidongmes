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
<title>雅安市综合应急指挥平台</title>

<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />

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
  	height: 50px;
  }
  .leftBtn{
  color:#727272;
  width:100%;
  height:127px;
	cursor:pointer;
  text-align:center;
  border-top:1px solid #F9F9F9;
  border-bottom:1px solid #DEDEDE;
  }
.leftBtn:hover{
    background-color:#F5F5F5;
    color:#18836C;
    }
  .leftBtn img{

  margin-top:25px;
  }
.leftBtn span{
  
  position:relative;
  top:9px;
  font-weight:600;
  }
</style>
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
	       	//initMenu(height);
	       	initTab(height);
	       	oneMap();
       });
       
       function oneMap(){
    	   addTab("tabid", "一张图", "rest/sysUserManageAction/oneMap");
       }
       
       function proCount(){
    	   addTab("tabid", "贫困户属性统计", "rest/basicdataPoorhouseholdManageAction/toBTCountPage");
       }
       
       function getFramecenterHeight(){
    	   return $("#framecenter").height();
       }
       
       function getFramecenterWidth(){
    	   return $("#framecenter").width();
       }
            
       function initLayout(){
       		$("#layout1").ligerLayout({ leftWidth: 74, height: '100%',allowLeftResize:false,allowLeftCollapse:false});
       		//$("#layout1").ligerLayout({ leftWidth: 200 ,allowLeftCollapse:false}); 
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
       	
	      
       	
       }
       
    	function initTree(id,data){
	       	 $("#"+id).ligerTree({
		            data : data,
		            checkbox: false,
		            slide: true,
		            nodeWidth: 110,
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
    	   
    	   tab = liger.get("framecenter");
    	   tab.onClose = handleTabClose;
       }

       function handleTabClose(tabid){
    	   //alert(tabid);
       }

       function addTab(tabid, text, url){
    	   tab.removeAll();
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
</head>

<body style="width: 99.8%">
        <div id="layout1">
           <div position="top" class="headBackground">
          <img style='position:absolute;top:0px;left:23px;' src='<%=basePath %>core/img/logo_01.png'/> 
           		<img style='position:absolute;top:0;right:0;' src='<%=basePath %>core/img/imgForLeader/login-show_02.png'/> 
           		<div style="position: absolute;top: 20px;right: 100px;font-size: 12;color: white;">
           			
           			
           			<table border="0" cellspacing="0" cellpadding="0">
           				<tr>
           					<td style="width: 300px;"></td>
           					<td>欢迎用户：<%=account%>&nbsp;&nbsp; &nbsp;</td>
           					<td><a style="color: white;" href="javascript:logout();">注销</a>&nbsp;&nbsp; &nbsp;</td>
           					<td><a style="color: white;" href="javascript:showUpdateUserPasswordDialog()">修改密码</a></td>
           				</tr>
           			</table>
           		</div>
           </div>
           
			 <div position="left"  id="accordion1" class="l-accordion-panel"  style="height: 100%;  background-color:#E6E6E6;">
				   <!-- <input type="button" value="一张图" onclick="oneMap();"/></br>
				   <input type="button" value="贫困户属性统计" onclick="proCount();"/></br>
				   <input type="button" value="项目进度" /></br>
				    <input type="button" value="建设成效" /></br> -->
				    <div class='leftBtn' onclick="oneMap()"><img src='<%=basePath%>core/img/imgForLeader/icon1_03.png'/><br/><span>一张图</span></div>
				    <div class='leftBtn' onclick="proCount()"><img src='<%=basePath%>core/img/imgForLeader/icon2_06.png'/><br/><span>进度</span></div>
				    <div class='leftBtn'><img src='<%=basePath%>core/img/imgForLeader/icon3_08.png'/><br/><span>统计</span></div>
				    <div class='leftBtn'><img src='<%=basePath%>core/img/imgForLeader/icon4_10.png'/><br/><span>成效</span></div>
	         </div>  
	           
	        <div position="center" id="framecenter" > 
	        	
	        </div> 
       </div> 
</body>
</html>