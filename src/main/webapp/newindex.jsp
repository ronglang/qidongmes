<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <script>
			var basePath  = '<%=basePath%>';
	</script>
    <title>主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
  	body,div,ul,li,p,table,img,form,dl,ol,h1,h2,h3,h4,h5,h6,dd,dt{
	margin: 0;
	padding: 0;
}
ul{
	list-style-type: none;
}
a{
	text-decoration: none;
}
/*头部菜单位置*/
#top{
	width: 98%;
	margin: 0 auto;
	height: 56px;
	border-bottom: 2px solid #FF0000;
}
#top .t_left{
	width: 15%;
	float: left;
	height: 56px;
	
}
#top .t_left .tl_img{
	display: block;
	float: left;
	width: 40px;
	height: 56px;
}
#top .t_left img{
	width: 33px;
	height: 29px;
	margin-top: 15px;
}
#top .t_left .tl_text{
	display: block;
	float: left;
	height: 56px;
	line-height: 56px;
}
#top .t_left .tl_text a{
	text-decoration: none;
	font-weight: bolder;
	word-spacing:8px;
	letter-spacing: 2px;
	color: black;
}
#top ul{
	width: 73%;
	float: left;
	height: 56px;
	line-height: 56px;
}
#top ul li{
	float: left;
	margin-left: 5px;
	width: 110px;
}
#top ul li a{
	display: block;
	width: 110px;
	margin-top: 6px;
	text-decoration: none;
	height: 50px;
	line-height: 50px;
	color: #333333;
	text-align: center;
}
#top ul li a:hover{
	color: white;
	background-color: #CC3300;
}
#top ul li a.selected{
	color: white;
	background-color: #CC3300;
}

#top .t_right{
	width: 12%;
	float: left;
	height: 56px;
	font-size:13px;
	text-align:right;
	text-align: center;
	position: relative;
}
#top .t_right .tr_time{
	position: absolute;
	top:10px;
	left:1px;
}
#top .t_right .tr_text{
	position: absolute;
	top:35px;
	left:1px;
}
#top .t_right .tr_2 a{
	display:inline-block;
	text-decoration: none;
	color: #0099CC;
}
#top .t_right .tr_2 a:hover{
	text-decoration: underline;
	color: #00B9FC;
}
#mid{
	width:98%;
	margin:10px auto;
}
#mid .m_left{
	width: 11%;
	height: 700px;
	/*height: 100%;*/
	background-color: #CC3300;
	border-top: 1px solid #CC3300;
	border-left: 1px solid #CC3300;
	float: left;
}
#mid .m_left iframe{
	height: 100%;
}
#mid .m_left ul{
	width: 100%;
}
#mid .m_left ul li{
	width: 100%;
	height: 40px;
	border-bottom: 1px solid #A70000;
}
#mid .m_left ul li a{
	display: inline-block;
	width: 100%;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: white;
	text-decoration: none;
	font-size: 13px;
	letter-spacing: 1px;
}
#mid .m_left ul li a.selected{
	background-color: white;
	color: black;
}
#mid .m_left ul li a:hover{
	background-color: white;
	color: black;
}
/*右边部分*/
#mid .m_right{
	float: left;
	margin-left: 20px;
	width: 87%;
}
#mid .m_right span{
	font-size: 12px;
}

/**********首页面样式*************/
/*顶部div*/
#mid_top{
	width: 100%;
	margin: 10px auto;
}
/*顶部div中第一个div*/
#mid_top .mt_one{
	width: 31%;
	border: 1px solid #E0E0E0;
	float: left;
}
#mid_top .mt_one .mto_top{
	width: 100%;
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #E0E0E0;
}
#mid_top .mt_one .mto_top img{
	margin: 0 10px 0 20px;
}
#mid_top .mt_one .mto_top span{
	margin: 5px 0;
}
#mid_top .mt_one .mto_con{
	width: 100%;
	margin: 20px auto;
}
#mid_top .mt_one .mto_con .mtoc_one{
	background-color: #36A9E0;
	border: 1px solid #36A9E0;
}
#mid_top .mt_one .mto_con .mtoc_two{
	background-color: #28C5CA;
	border: 1px solid #28C5CA;
}
#mid_top .mt_one .mto_con .mtoc_three{
	background-color: #FB9A39;
	border: 1px solid #FB9A39;
}
#mid_top .mt_one .mto_con .mtoc_foure{
	background-color: #F45D32;
	border: 1px solid #F45D32;
}
#mid_top .mt_one .mto_con .mtoc_five{
	background-color: #36A9DF;
	border: 1px solid #36A9DF;
}
#mid_top .mt_one .mto_con div{
	width: 100px;
	height: 70px;
	float: left;
	margin: 20px 0 0 40px;
}
#mid_top .mt_one .mto_con .num{
	display: block;
	width: 100px;
	text-align: center;
	height: 45px;
	line-height: 45px;
	font-weight: bold;
	background-color: white;
}
#mid_top .mt_one .mto_con .text{
	display: block;
	width: 100px;
	text-align: center;
	font-size: 13px;
	height: 25px;
	line-height: 25px;
	color: white;
}
#mid_top .mto_emp{
	width: 100%;
	clear: both;
	height: 50px;
}
/*顶部div中第二个div*/
#mid_top .mt_two{
	width: 31%;
	border: 1px solid #E0E0E0;
	float: left;
	margin-left: 20px;
}
#mid_top .mt_two .mto_top{
	width: 100%;
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #E0E0E0;
}
#mid_top .mt_two .mto_top img{
	margin: 0 10px 0 20px;
}
#mid_top .mt_two .mto_top span{
	display: inline-block;
	height: 30px;
	line-height: 30px;
}
#mid_top .mt_two .mto_con{
	margin-bottom: 20px;
	margin-top: 20px;
}
#mid_top .mt_two .mto_con div{
	width: 130px;
	float: left;
	margin-left: 20px;
	margin-top: 15px;
}
#mid_top .mt_two .mto_con span.top{
	display: inline-block;
	width: 130px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	color: white;
}
#mid_top .mt_two .mto_con span.num{
	display: inline-block;
	width: 130px;
	height: 60px;
	margin-top: 20px;
	line-height: 60px;
	font-size: 50px;
	text-align: center;
}
#mid_top .mt_two .mto_con span.unit{
	display: inline-block;
	width: 130px;
	text-align: center;
	margin-bottom: 36px;
}
#mid_top .mt_two .mto_con .mtoc_one{
	border: 1px solid #29C6CB;
}
#mid_top .mt_two .mto_con .mtoc_one span.top{
	background-color: #29C6CB;
}
#mid_top .mt_two .mto_con .mtoc_one span.num{
	color: #29C6CB;
}
#mid_top .mt_two .mto_con .mtoc_one span.unit{
	color: #29C6CB;
}
#mid_top .mt_two .mto_con .mtoc_two{
	border: 1px solid #36A9E0;
}
#mid_top .mt_two .mto_con .mtoc_two span.num{
	color: #36A9E0;
}
#mid_top .mt_two .mto_con .mtoc_two span.unit{
	color: #36A9E0;
}
#mid_top .mt_two .mto_con .mtoc_two span.top{
	background-color: #36A9E0;
}
#mid_top .mt_two .mto_con .mtoc_three{
	border: 1px solid #F45D32;
}
#mid_top .mt_two .mto_con .mtoc_three span.top{
	background-color: #F45D32;
}
#mid_top .mt_two .mto_con .mtoc_three span.num{
	color: #F45D32;
}
#mid_top .mt_two .mto_con .mtoc_three span.unit{
	color: #F45D32;
}
/*顶部div中第三个div*/
#mid_top .mt_three{
	width: 31%;
	border: 1px solid #E0E0E0;
	float: left;
	margin-left: 20px;
}
#mid_top .mt_three .mtt_top{
	width: 100%;
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #E0E0E0;
}
#mid_top .mt_three .mtt_top img{
	margin: 0 10px 0 20px;
}
#mid_top .mt_three .mtt_top span{
	display: inline-block;
	height: 30px;
	line-height: 30px;
}
#mid_top .mt_three .mtt_con{
	margin-bottom: 20px;
}
#mid_top .mt_three .mtt_con ul{
	width: 100%;
	margin-top: 10px;
}
#mid_top .mt_three .mtt_con ul li{
	border-bottom: 1px dashed #E3D3D3;
	height: 36px;
	line-height: 36px;
	text-indent: 5px;
	font-size: 15px;
	color: #5D5D5D;
}
/*中间图标部分*/
#now_task{
	width: 100%;
	margin: 0 auto;
	clear: both;
	border: 1px solid #E0E0E0;
}

#now_task .nt_top{
	width: 100%;
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #E0E0E0;
}
#now_task .nt_top img{
	margin: 0 10px 0 20px;
}
#now_task .nt_top span{
	display: inline-block;
	height: 30px;
	line-height: 30px;
}
#now_task .nt_con{
	height: 400px;
	width: 100%;
}

/*底部表格*/
#foot_tab{
	width: 100%;
	margin: 10px auto;
	clear: both;
}
#foot_tab .ft_left{
	width: 48%;
	float: left;
	border: 1px solid #E0E0E0;
}
#foot_tab .ft_left .ft_top{
	width: 100%;
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #E0E0E0;
}
#foot_tab .ft_left .ft_top img{
	margin: 0 10px 0 20px;
}
#foot_tab .ft_left .ft_top span{
	display: inline-block;
	height: 30px;
	line-height: 30px;
}

#foot_tab .ft_right{
	width: 48%;
	margin-left: 10px;
	float: left;
	border: 1px solid #E0E0E0;
}
#foot_tab .ft_right .ft_top{
	width: 100%;
	height: 30px;
	line-height: 30px;
	border-bottom: 1px solid #E0E0E0;
}
#foot_tab .ft_right .ft_top img{
	margin: 0 10px 0 20px;
}
#foot_tab .ft_right .ft_top span{
	display: inline-block;
	height: 30px;
	line-height: 30px;
}


  </style>
  
	<script type="text/javascript">
	$(function(){
		//设置时间
		$("#top .t_right .tr_time").html(showTime());
		setInterval(function(){
			$("#top .t_right .tr_time").html(showTime());
		}, "1000");
		
	});
	//var menu = ${menu};
	//获取二级导航条
	var one_html = "";
		function getChild(id,my){
			//隐藏首页面
			$("#hiddens").css("display","none");
			//显示左边菜单
			$("#mid .m_left").css("display","inline-block");
			
			var parentsNode = $(my).parent().parent().children();  //li
			for(var i =0;i<parentsNode.length;i++){
				parentsNode.eq(i).children().eq(0).removeClass("selected");
			}
			$(my).addClass("selected");
			
			//获取一级菜单html
			one_html = $(my).html();
			
			var  url = "<%=basePath%>rest/sysUserManageAction/findMenuByPcode";
			$.post(url,{pcode:id},function(data){
				var child = data.data;
				var leftMenu = $("#leftMenu");
				leftMenu.html("");
				for(var i =0;i<child.length;i++){
					if(i == 0){
						var h="<li><a href='javascript:' onclick=getPage('"+child[i].url+"',this,'"+child[i].name+"')  class='selected'>"+child[i].name+"</a></li>";
						leftMenu.append(h);
						getPage(child[i].url);
					}else {
						if(child[i].name == "车间看板"){
							
							var h="<li><a href='javascript:' onclick=getPage('"+child[i].url+"',this,'"+child[i].name+"') target='_blank'> "+child[i].name+"</a></li>";
						}else{
							var h="<li><a href='javascript:' onclick=getPage('"+child[i].url+"',this,'"+child[i].name+"')> "+child[i].name+"</a></li>";
						}
						
						leftMenu.append(h);
					}
				}
				
			},"json");
			
		}
		//点击二级目录展示页面
		function getPage(url,my,name){
			//显示有边菜单
			
			$("#mid .m_right").css("display","inline-block");
			
			var u = '<%=basePath%>'+url + "?requestType=portal";
			$("#mid .m_right iframe").attr('src',u);
			
			var parentsNode = $(my).parent().parent().children();  //li
			for(var i =0;i<parentsNode.length;i++){
				parentsNode.eq(i).children().eq(0).removeClass("selected");
			}
			$(my).addClass("selected");
			
			$("#mid .m_right .a_one").html("主页");
			$("#mid .m_right .a_two").html(one_html);
			$("#mid .m_right .a_three").html(name);
			
		}
		//退出方法
		function outLog(){
			$.ajax({
				type:"POST",
				url:basePath +"/rest/sysUserManageAction/logOutWebUser",
				data:"",
				dataType:"json",
				success: function(data){
					if(data.success=true){
						window.location.href= basePath + "newlogin.jsp";
					}
				}
			});
		}
		//时间
		function showTime(){ 
		    var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六'); 
		    var time=new Date(); 
		    var year=time.getFullYear();
		    var month=time.getMonth();
		    var date=time.getDate(); 
		    var day=time.getDay(); 
		    var hour=time.getHours(); 
		    var minutes=time.getMinutes(); 
		    var second=time.getSeconds(); 
		    month=parseInt(month)+1;
		    month<10?month='0'+month:month;
		    hour<10?hour='0'+hour:hour; 
		    minutes<10?minutes='0'+minutes:minutes; 
		    second<10?second='0'+second:second; 
		    var now_time=year+'/'+month+'/'+date+' '+hour+':'+minutes+':'+second+' '+show_day[day]; 
		    
		    return now_time;
		} 
	</script>
  </head>
  
  <body>
    <div id="top">
		<div class="t_left">
			<span class="tl_img">
				<img src="<%=basePath %>app/images/newlogo.png"/>
			</span>
			<span class="tl_text">
				<a href="<%=basePath %>rest/sysUserManageAction/showMain" title="去首页">智能线缆管理系统</a>
			</span>
			
		</div>
		
		
		<ul>
			<!--<li><a href="#" class="selected">基础数据</a>-->
			<c:forEach items="${menu}" var="item">
				<c:if test="${item.pid=='-1' }">
					<li id="menu_${item.id}"><a href="javaScript:void(0)"  onclick="getChild('${item.id}',this)">${item.name}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		<div class="t_right">
			<div class="tr_time">2017/8/24&nbsp;14:50:10&nbsp;星期四</div>
			<div class="tr_text">
				<span class="tr_1">
					您好,<b>${session_user.name}</b>！
				</span>
				<span class="tr_2">
					<a href="javascript:void(0);" onclick="outLog();">退出系统</a>
				</span>
			</div>
			
		</div>
	</div>
		
	
	<div id="mid">
		<!-- 左边菜单 -->
		<div class="m_left" style="display:none">
			<ul id="leftMenu">
			</ul>
		</div>
		
		<!-- 右边内容 -->
		<div class="m_right" id="m_right" style="display:none">
			<div style="background-color: gray;margin-bottom:40px;">
				<div style="float: left;width:27%;">
					<span style="font-size:12px;">当前位置：</span>
					<span class="a_one">主页</span>&nbsp;&gt;
					<span class="a_two">基础数据</span>&nbsp;&gt;
					<span class="a_three">机台管理</span>
				</div>
				<div style="float: left;width:72%;height:30px;">
					<img alt="" src="<%=basePath %>app/images/newindex/message.png" style="display:inline-block;float: left;">
					<marquee style="float: left;background-color: #FFFFB6;width:95%;height:30px;line-height: 30px;margin-left:10px;color:#CF3327;">此处显示消息</marquee>
				</div>
			</div>
			<hr style="background-color: #CCCCCC; margin: 10px auto;clear: both;" />
			<iframe id="right_url" src="" width="100%" height="100%" frameborder="no"></iframe>
		</div>
		
		
		<!-- 主页部分 -->
		<div id="hiddens">
		<div id="mid_top">
				<div class="mt_one">
					<div class="mto_top">
						<img src="<%=basePath %>app/images/newindex/index_img1.png"/>
						<span>原料库存情况</span>
					</div>
					<div class="mto_con">
						<div class="mtoc_one">
							<span class="num">20.123T</span>
							<span class="text">胶料库存量</span>
						</div>
						<div class="mtoc_two">
							<span class="num">20.123T</span>
							<span class="text">胶料库存量</span>
						</div>
						<div class="mtoc_three">
							<span class="num">20.123T</span>
							<span class="text">胶料库存量</span>
						</div>
						<div class="mtoc_foure">
							<span class="num">20.123T</span>
							<span class="text">胶料库存量</span>
						</div>
						<div class="mtoc_five">
							<span class="num">20.123T</span>
							<span class="text">胶料库存量</span>
						</div>
					</div>
					<div class="mto_emp">
						&nbsp;
					</div>
				</div>
				
				<div class="mt_two">
					<div class="mto_top">
						<img src="<%=basePath %>app/images/newindex/index_img2.png"/>
						<span>今日机台状态</span>
					</div>
					<div class="mto_con">
						<div class="mtoc_one">
							<span class="top">开机台数</span>
							<span class="num">30</span>
							<span class="unit">单位：台</span>
						</div>
						<div class="mtoc_two">
							<span class="top">关机台数</span>
							<span class="num">3</span>
							<span class="unit">单位：台</span>
						</div>
						<div class="mtoc_three">
							<span class="top">故障台数</span>
							<span class="num">2</span>
							<span class="unit">单位：台</span>
						</div>
					</div>
					<div class="mto_emp">
						&nbsp;
					</div>
				</div>
				
				
				<div class="mt_three">
					<div class="mtt_top">
						<img src="<%=basePath %>app/images/newindex/index_img3.png"/>
						<span>系统消息</span>
					</div>
					<div class="mtt_con">
						<ul>
							<li>机台BA所需铝料低于10kg，请及时补料！</li>
							<li>机台CA出现故障，需要维修！</li>
							<li>机台DA温度过高，请采取急求措施！</li>
							<li>机台DC所需铜料低于10kg，请及时补料！</li>
							<li>机台AD所需铜料低于10kg，请及时补料！</li>
							<li>机台BG所需铜料低于10kg，请及时补料！</li>
						</ul>
					</div>
				</div>
				
			</div>
			
			<div style="width: 100%; clear: both;">&nbsp;</div>
			
			<!-- 中间图标部分 -->
			<div id="now_task">
				<div class="nt_top">
					<img src="<%=basePath %>app/images/newindex/index_img4.png"/>
					<span>今日工单任务完成情况</span>
				</div>
				<div class="nt_con">
					
					
				</div>
				
			</div>
			
			<div style="width: 100%;height: 1px; clear: both;">&nbsp;</div>
			
			<!-- 底部表格 -->
			<div id="foot_tab">
				
				<div class="ft_left">
					<div class="ft_top">
						<img src="<%=basePath %>app/images/newindex/index_img5.png"/>
						<span>合同到期预警</span>
					</div>
					<div class="ft_con">
						
					</div>
				</div>
				<div class="ft_right">
					<div class="ft_top">
						<img src="<%=basePath %>app/images/newindex/index_img6.png"/>
						<span>机台报警情况</span>
					</div>
					<div class="ft_con">
						
					</div>
				</div>
				
			</div>
			
			<div style="width: 100%;height: 200px; clear: both;">&nbsp;</div>
			</div>
		
	</div>
	
	
	
  </body>
  
  
</html>
