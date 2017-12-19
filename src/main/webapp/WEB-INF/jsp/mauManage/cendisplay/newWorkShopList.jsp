<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>车间电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>app/css/exception/Lobibox.min.css"/>
	<script src="<%=basePath%>app/js/exception/jquery.1.11.min.js"  type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js"  type="text/javascript"></script>
	<script src="<%=basePath%>core/js/cssBase.js"  type="text/javascript"></script>
	
	
	<script src="<%=basePath%>app/js/exception/lobibox.min.js"  type="text/javascript"></script>
	
	<script type="text/javascript">
			var basePath = '<%=basePath%>';
			$(function(){
				
				$("#times").showDate();
				
				initMacInfo(); //先初始化页面，后面由websocket刷新页面值
				//机台情况 
				//matInInfo();//这个是静态
				
				//$("#exception").hide();
				
				//var leng = $("#meInfo").html().toString().length;
				//var str = $("#meInfo").html();
				//if(leng >= 40){
					//$("#meInfo").html(str.substring(0,38)+"......");
				//}
				
				//var intDiff = parseInt(60);//倒计时总秒数量
				//timer(intDiff);
				
				//异常弹窗
				webSockets();
				
				//欢迎致辞websocket
				welcomeWSocket();
				
				//机台更新生产情况 websocket
				fn_macInfoWesocket();
			});
			
			function exceptionInfo(map){
				var html = "";
				var j = randomString(5);
				html += "<ul id='excInfo'>";
				html += "<li><span>机台编码:</span><b id='macCode"+j+"'>无</b></li>";
				html += "<li><span>工单编码:</span><b id='courseCode"+j+"'>无</b></li>";
				//html += "<li><span>轴名称:</span><b id='axisName'>无</b></li>";
				html += "<li><span>异常信息:</span><b id='meInfo"+j+"'>无</b></li>";
				html += "<li><span>异常时间:</span><b id='meTime"+j+"'>无</b></li>";
				html += "<li><span>操作人:</span><b id='agentBy"+j+"'>无</b></li>";
				html += "</ul>";
				 Lobibox.notify('error', {
	                	delay: 60000,
	                	//width: 500,
	                	//height: 500,
	                	//size: 'large',
	                	title: '异常消息',
	                	position: 'bottom right',
	                    msg: html
	                });
				 
				for(var key in map){
					var a = "#"+key+j;
			    	$(a).html(map[key]);
			    } 
			}
			
			//倒计时
			function timer(intDiff){
			    window.setInterval(function(){
			    var day=0,
			        hour=0,
			        minute=0,
			        second=0;//时间默认值        
			    if(intDiff > 0){
			        day = Math.floor(intDiff / (60 * 60 * 24));
			        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
			    }
			    if (minute <= 9) minute = '0' + minute;
			    if (second <= 9) second = '0' + second;
			    
			    $('#exception h3 span.t2').html(minute+":"+second);
			    if(minute == '00' && second == '00'){
			    	$("#exception").css("display","none");
			    }
			    intDiff--;
			    }, 1000);
			} 
			
			function webSockets(){
				var websocket = null;  
			    //判断当前浏览器是否支持WebSocket  
			    if ('WebSocket' in window) {
			    	var urlPath = basePath.substring(7, basePath.length);
					var url = "ws://"+urlPath+"LSExceptionWebSocket";
			        websocket = new WebSocket(url);
			    }  
			    else {
			        alert('当前浏览器不支持websocket，请更换浏览器！');  
			    }
			    websocket.onopen = function()
			    {
			       // Web Socket 已连接上，使用 send() 方法发送数据
			    	websocket.send("警告框");
			    };
					
			    websocket.onmessage = function (evt) 
			    { 
			       var map = eval('('+evt.data+')');
			       debugger;
			       if(map != "" && map != null){
			    	   exceptionInfo(map);
			       }
			    };
					
			    websocket.onclose = function()
			    { 
			    };
			}
			
			//欢迎致辞
			function welcomeWSocket(){
				var welcomeWS = null;  
			    //判断当前浏览器是否支持WebSocket  
			    if ('WebSocket' in window) {
			    	var urlPath = basePath.substring(7, basePath.length);
					var url = "ws://"+urlPath+"sysNoticeToWorkShopWebSocket";
					welcomeWS = new WebSocket(url);
			    }  
			    else {
			        alert('当前浏览器不支持websocket，请更换浏览器！');  
			    }
			    welcomeWS.onopen = function()
			    {
			       // Web Socket 已连接上，使用 send() 方法发送数据
			    	welcomeWS.send("警告框");
			    };
					
			    welcomeWS.onmessage = function (evt) 
			    { 
			       var map = eval('('+evt.data+')');
			       
			       //alert(map+","+msg);
				  //消息已过时
				  if(!map || map == "0" || !map.value){
					  $("#div_notice").css("display","none");
					  return true;
				  }
			       var msg = map.value;
			       //debugger;
			       /* if(map != "" && map != null){
			    	   exceptionInfo(map);
			       } */
			       
			      /*  <marquee loop=-1  behavior="alternate”direction="left" scrolldelay=400  style=" font-size:250% ; align:center;" scrollAmount="20">
					欢迎张三来厂考查2
				  </marquee> */
				  //$("#div_notice").html("");
				  
				  
				  var html = "";
				  html+="<div class='mlcl_ul'><ul><li class='lb'><span>";
				  //html+="<marquee loop=-1  behavior='alternate' direction='left' scrolldelay=400  style=' font-size:250% ; align:center;' scrollAmount='20'>";
				  html+=msg;
				 // html+=" </marquee>";
				  html+="</span></li></ul></div>";
				  
				   $("#div_notice").css("display","block");
			       //$("#div_notice").append(html);
			       $('#notice_text').html(msg);
			       //alert(html);
   				   //通知滚动
	   				/*  $("#div_notice").marquee({
	   					isMarquee:true,
	   					isEqual:false,
	   					scrollDelay:80,
	   					behavior:'scroll',
	   					//yScroll:'top',
	   					direction:'top'
	   				});  */
			    };
					
			    welcomeWS.onclose = function()
			    { 
			    };
			}
			
		/* 	function matInInfo(){
				
				$("#mm_con").html("");
				var html = "";
				html+="<div class='mlcl_ul'>";
				
				html+="<ul>";
				for(var i=0;i<12;i++){
					if(i%2 == 0){
						html+= "<li class='lb'><span>AD</span><span>白某某</span><span>12</span><span>12:12:12</span><span>13:13:13</span><span>45.6</span><span>87%</span><span>ADEQWQASAA</span><span>54%</span><span>ADEQWQASBB</span><span>正常</span><span>无</span></li>";
					}else{
						html+= "<li class='ls'><span>AC</span><span>李某某</span><span>8</span><span>11:13:13</span><span>12:15:12</span><span>65.2</span><span>90%</span><span>ADEQWQASDD</span><span>62%</span><span>ADEQWQASBB</span><span>正常</span><span>无</span></li>";
					}
					
				}
				html+="</ul>";
				html+="</div>";
				$("#mm_con").append(html);
				//通知滚动
				$("#mm_con .mlcl_ul").marquee({
					isMarquee:true,
					isEqual:false,
					scrollDelay:200,
					direction:'top'
				});
			} */
			
			function initMacInfo(){
				var url = basePath+"rest/plaMacTaskRecordManageAction/queryMachineTaskSummer?requestType=mapuser";
				$.ajax({
                    type: "POST",
                    dataType: "JSON",
                    url: url,
                    //data:"requestType=mapuser",
                    success: function (json) {
                    	$("#mm_con").html("");
                    	if(!json) return;
                    	
                    	var html = "";
                    	html+="<div class='mlcl_ul'>";
        				html+="<ul>";
        				
                    	$.each(json,function(i,e){
                    		    var c =  e.maccode;
                    		    var w = e.workcode;
	                    		if(i%2 == 0){
	        						html+= "<li class='lb'>";
	        					}else{
	        						html+= "<li class='ls'>";
	        					}
	                    		html+="<span>"+c+"</span><span><div id='\fzr_"+c+w+"\'>"+e.fzr+"</div></span><span><div id='pnum_"+c+w+"'>"+e.pnum+"</div></span>";
        						html+= "<span><div id=\'hjtime_"+c+w+"\'>"+e.hjtime+"</div></span><span><div id=\'kjtime_"+c+w+"\'>"+e.kjtime+"</div></span>";
        						html+="<span><div id=\'speed_"+c+w+"\'>"+e.speed+"</div></span><span><div id=\'lyl_"+c+w+"\'>"+e.lyl+"</div></span>";
        						html+="<span><div id=\'workcode_"+c+w+"\'>"+e.workcode+"</div></span><span><div id=\'schedule_"+c+w+"\'>"+e.schedule+"</div></span>";
        						html+="<span><div id=\'workcode_next_"+c+w+"\'>"+e.workcode_next+"</div></span><span><div id=\'macstate_"+c+w+"\'>"+e.macstate+"</div></span>";
        						html+="<span><div id=\'cc_"+c+w+"\'>"+e.cc+"</div></span>";
        						html+="</li>";
                    	});
        				html+="</ul>";
        				html+="</div>";
        				$("#mm_con").append(html);
        				//通知滚动
        				$("#mm_con .mlcl_ul").marquee({
        					isMarquee:true,
        					isEqual:false,
        					scrollDelay:80,
        					behavior:'scroll',
        					//yScroll:'top',
        					direction:'down'
        				});
                    },
                    error: function(data) {
                    	$.ligerDialog.error(data.msg);
                     }
	           });
			}
			
			// ajax更新机台生产情况，多个机台则以,分隔. 工单号不传,直接查机台
			function ajaxQueryMacInfo(maccodestr){
				var url = basePath+"rest/plaMacTaskRecordManageAction/queryMachineTaskSummer?requestType=mapuser";
				$.ajax({
                    type: "POST",
                    dataType: "JSON",
                    url: url,
                    data:"maccode="+maccodestr,
                    success: function (json) {
                    	if(!json) return;
        				
                    	$.each(json,function(i,e){
                   		    var c =  e.maccode;
                   		    var w = e.workcode;
                   		    
                   		    $('#fzr_'+c+w).html(e.fzr);
                   		    $('#pnum_'+c+w).html(e.pnum);
                   		    $('#hjtime_'+c+w).html(e.hjtime);
                   		    $('#kjtime_'+c+w).html(e.kjtime);
                   		    $('#speed_'+c+w).html(e.speed);
                   		    $('#lyl_'+c+w).html(e.lyl);
                   		    $('#workcode_'+c+w).html(e.workcode);
                   		    $('#schedule_'+c+w).html(e.schedule);
                   		    $('#workcode_next_'+c+w).html(e.workcode_next);
                   		    $('#macstate_'+c+w).html(e.macstate);
                   		    $('#cc_'+c+w ).html(e.cc); 
                    	});
                    },
                    error: function(data) {
                    	$.ligerDialog.error(data.msg);
                     }
	           });
			}
			
			//机台更新生产情况 websocket
			function fn_macInfoWesocket(){
					var macInfoWesocket = null;  
				    //判断当前浏览器是否支持WebSocket  
				    if ('WebSocket' in window) {
				    	var urlPath = basePath.substring(7, basePath.length);
						var url = "ws://"+urlPath+"plaMacTaskRecord_Save_WebSocket";
						macInfoWesocket = new WebSocket(url);
				    }  
				    else {
				        alert('当前浏览器不支持websocket，请更换浏览器！');  
				    }
				    macInfoWesocket.onopen = function()
				    {
				       // Web Socket 已连接上，使用 send() 方法发送数据
				    	macInfoWesocket.send("警告框");
				    };
						
				    macInfoWesocket.onmessage = function (evt) 
				    { 
				       var js = eval('('+evt.data+')');
				       
				       //alert(js + "," + js.length);
					  //消息已过时
					  if(!js || js.length == 0){
						  return true;
					  }
				      
				 	  var maccodes = '';
				 	  $.each(js, function( i , e ){
				 		    maccodes += e.maccode+",";
				 	  });
				 	  
				 	 ajaxQueryMacInfo(maccodes);
			    };
					
			    macInfoWesocket.onclose = function()
			    { 
			    };
			}
			
			function randomString(len) {
				len = len || 32;
				var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
				var maxPos = $chars.length;
				var pwd = '';
				for (var i = 0;  i < len; i++) {
					pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
					return pwd;
				}
			}
		</script>
		
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
				font-size: 13px;
				
			}
			body{
				background-color: #3267AB;
			}
			ul{
				list-style-type: none;
				padding: 0;
				margin: 0;
			}
			li{
				padding: 0;
				margin: 0;
			}
			#top{
				height: 50px;
				line-height: 50px;
				background-color: #3F77C1 ;
				border-bottom: 2px solid #1E4785;
			}
			#top .t_center{
				height: 50px;
				width: 56%;
				color: white;
				font-size: 25px;
				text-align: right;
				letter-spacing: 5px;
				float: left;
			}
			#top .t_time{
				width: 44%;
				height: 50px;
				float: left;
				color: white;
				text-align: right;
			}
			#top .t_time span{
				display: inline-block;
				margin-top: 10px;
				font-size: 16px;
				margin-right: 5%;
			}
			
			#head{
				width:98%;
				height:50px;
				background-color:#224B81;
				margin:10px auto;
				border-radius:10px;
			}
			#head .h_con{
				width:100%;
				height:50px;
				overflow:hidden;
				margin:0 auto;
			}
			#head .h_con ul{
				width:100%;
				height:50px;
				margin:0 auto;
			}
			#head .h_con ul li{
				height:50px;
				line-height: 50px;
				float: left;
				margin-left:50px;
			}
			#head .h_con ul li span{
				margin-left:20px;
				color:white;
				font-size:14px;
			}
			
			#main{
				width:96%;
				margin: 0 auto;
			}
			
			#main h3{
				height: 40px;
				line-height: 40px;
				border-top-left-radius:5px;
				border-top-right-radius:5px;
				background-color: #4C85D4;
				text-align: center;
				color: yellow;
				font-size: 16px;
				letter-spacing: 5px;
			}
			#main .ml_con{
				height: 625px;
				width: 96%;
				border-radius: 5px;
				background-color: #224B81;
				margin: 20px auto;
			}
			
			#main .ml_con .mm_u{
				width: 95%;
				margin: 0 auto;
				text-align: center;
			}
			#main .ml_con .mm_u li{
				height: 50px;
				line-height: 50px;
				padding: 0;
				margin:0;
				text-align: center;
			}
			#main .ml_con .mm_u li span{
				width:8.3%;
				display: inline-block;
				text-align: center;
	    		color: white;
	    		background-color: #2E3D57;
				font-size: 14px;
				font-weight: bold;
			}
			
			#mm_con .mlcl_ul{
				width:100%;
				height:500px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mm_con .mlcl_ul ul{
	    		height:500px;
				width: 95%;
				margin: 0 auto;
				padding: 0;
	    	}
	    	#mm_con .mlcl_ul ul li{
	    		height: 50px;
	    		text-align: center;
	    	}
	    	#mm_con .mlcl_ul ul li.ls span{
	    		background-color: #0367CE;
	    	}
	    	
	    	#mm_con .mlcl_ul ul li.lb span{
	    		background-color: #03244D;
	    	}
	    	#mm_con .mlcl_ul ul li span{
	    		display: inline-block;
	    		height: 50px;
	    		width:8.3%;
	    		line-height: 50px;
	    		text-align: center;
	    		color: white;
	    		overflow: hidden;
	    		font-size:13px;
	    	}
			
			
			#exception{
			  	width: 350px;
			  	height: 200px;
			  	background-color: #FCDDC8;
			  	position: fixed;
			  	z-index: 9999;
			  	bottom: 0;
			  	right: 0;
			  	border: 1px solid red;
			  }
			  #exception h3{
			  	margin: 0;
			  	padding: 0;
			  	height: 35px;
			  	line-height: 35px;
			  	background-color: #E65600;
			  	position: relative;
			  }
			  #exception h3 span.t1{
			    text-align: center;
			  	color: yellow;
			  	font-size: 25px;
			  	font-weight: bolder;
			  	width: 100%;
			  	display: inline-block;
			  }
			  #exception h3 span.t2{
			  	position: absolute;
			  	right:5px;
			  	top:0px;
			  	font-size: 16px;
			  }
			  #exception ul{
			  	list-style-type: none;
			  	margin: 0;
			  	padding: 0;
			  }
			  #exception ul li{
			  	text-align: center;
			  	border: 1px solid black;
			  	height: 35px;
			  	line-height: 35px;
			  	width: 100%;
			  	margin: 0;
			  	padding: 0;
			  }
			  #exception ul li span{
			  	width: 20%;
			  	display: block;
			  	background-color: #F0F0F0;
			  	float: left;
			  	color: blue;
			  	font-weight: bold;
			  	text-align: right;
			  }
			  #exception ul li b{
			  	width: 73%;
			  	float: left;
			  	display: block;
			  	text-align: left;
			  	text-indent: 10px;
			  }
			  #meInfo{
			  	font-size: 12px;
			  	line-height: 23px;
			  	text-align: left;
			  	margin-left: 5px;
			  }
			  #excInfo{
			  }
			  #excInfo li{
			  	margin-left:-50px;
			  }
			  #excInfo li span{
			  	font-size:18px;
			  	font-weight: bold;
			  	color:yellow;
			  }
		</style>
	    
		<style type="text/css">
			#div_notice {
				 background:#6495ED ;width:100%;height:90px;padding:5px; color:#FFFF00; 
								filter:alpha(Opacity=60);-moz-opacity:0.6;opacity: 0.6;
				      position:absolute; left:0 ;   top: 100;
				      font-size:250% ; font-weight:bold; font:italic  arial,sans-serif;
				      text-align:center;  padding:25px;  vertical-align:middle;    
			}
		</style>
  </head>
  
  <body>
    
    <div id="top">
			<div class="t_center">
				车间电子看板
			</div>
			<div class="t_time">
				<span id="times"></span>
			</div>
		</div>
		<div id="callCar">
			<iframe src="<%=basePath%>rest/mauCallForkliftRecordManageAction/toCallCar?requestType=mapuser" width="100%" height="60px" frameborder="0" scrolling="no" class=""></iframe>
		</div>
		<!-- 
		<div id="head">

		</div>
		 -->
		
		<div id="main"  style="position:relative; border:solid red 1px;">
			<div class="ml_con">
				<h3>机台情况</h3>
				<ul class="mm_u">
					<li>
						<span>机台编码</span><span>负责人</span><span>团队人数</span><span>候机时间</span><span>开机时间</span><span>平均速度</span><span>设备利用率</span><span>当前工单</span><span>当班完成率</span><span>下个工单</span><span>设备故障</span><span>叉车需求</span>
					</li>
				</ul>
				<div id="mm_con">
				</div>
				<!-- 飘浮DIV -->
				<div id="div_notice" style="display:none;">
					<marquee loop=-1  behavior="alternate”direction="left" scrolldelay=400  scrollAmount="20">
						<div id="notice_text" style=" font-size:350% ; align:center;" >ssss</div>
					</marquee>
				</div>
			</div>
			
		</div>
    	<!-- 
    	<div id="exception">
 				<h3><span class="t1">异常警告</span><span class="t2"></span></h3>
 				<div class="content">
 					<ul>
 						<li><span>异常时间:</span><b id="meTime"></b></li>
 						<li><span>机台名称:</span><b id="machineName"></b></li>
 						<li><span>轴名称:</span><b id="axisName"></b></li>
 						<li style="height: 52px;line-height: 52px;"><span>异常信息:</span>
 							<b id="meInfo"></b></li>
 					</ul>
 				</div>
 		</div>
    	 -->
  </body>
</html>
