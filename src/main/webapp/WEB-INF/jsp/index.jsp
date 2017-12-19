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
    
    <title>线缆生产精细化系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>app/report/css/top.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>app/report/css/index.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>app/report/css/data.css"/>
	<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	<style type="text/css">
	#top{
		background:url(<%=basePath%>app/report/images/bgtop.png) no-repeat;
	    background-size: 100% 100%;
	}
	.lefan{
		margin-top:2px;
	}
	.lefan li{
		border-bottom:1px solid #094e8f;
	}
	.lefan li:hover{
		background-image:url(<%=basePath%>app/report/images/ht1.png);
	}
	.lefan li a{
		color:white;
		text-decoration: none;
	}
	.m_left{
		width:200px;
		height:44px;
		background-image:url(<%=basePath%>app/report/images/ht1.png);
		
	}
	.rbt{
		width:101.5%;
	}
	.rt{
		width:400px;
	}
	.rt li{
		float: left;
	}
	.user{
		float: right;
		margin-right: 20px;
	}
	.user a{
		text-decoration: none;
		color: #C7617F;
		font-weight: bolder;
		font-size: 14px;
	}
	.user a:hover{
		text-decoration: underline;
		color: red;
	}
	.left_li{
	}
	.left_li a{
		width:200px;
		height:44px;
		display: inline-block;
		text-decoration: none;
	}
	</style>
	<script type="text/javascript">
	var basePath  = '<%=basePath%>';
		$(function(){
			//设置时间
			$(".tr_time").html(showTime());
			setInterval(function(){
				$(".tr_time").html(showTime());
			}, "1000");
			queryStore();
			
		});
		function getChild(id,my){
			//隐藏首页面
			$("#index").css("display","none");
			
			//显示身体部分
			$("#mains").css("display","inline-block");
			$(".dh").css("background-color","none");
			var parentsNode = $(my).parent().parent(); //ul
			var parentV = parentsNode.parent().children(); //全部ul
			for(var i =0;i<parentV.length;i++){
				parentV.eq(i).removeClass("ddhh");
			}
			parentsNode.addClass("ddhh");
			
			//获取一级菜单html
			one_html = $(my).html();
			
			var  url = "<%=basePath%>rest/sysUserManageAction/findMenuByPcode";
			$.post(url,{pcode:id},function(data){
				var child = data.data;
				var leftMenu = $("#leftMenu");
				leftMenu.html("");
				for(var i =0;i<child.length;i++){
					if(i == 0){
						var h="<li class='m_left left_li'><a href='javascript:' onclick=getPage('"+child[i].url+"',this,'"+child[i].name+"')  class='selected'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+child[i].name+"</a></li>";
						leftMenu.append(h);
						getPage(child[i].url);
						$("#mains .m_right .a_two").html(one_html);
						$("#mains .m_right .a_three").html(child[i].name);
					}else {
						var h = "";
						if(child[i].name == "车间看板" || child[i].name == "生产部看板" || child[i].name == "仓库看板" || child[i].name == "质检部看板" || child[i].name == "工程部看板"){
							var u = '<%=basePath%>'+child[i].url + "?requestType=portal";
							h ="<li class='left_li'><a href='"+u+"' onclick=getPage('"+child[i].url+"',this,'"+child[i].name+"') target='_blank'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+child[i].name+"</a></li>";
						}else{
							h="<li class='left_li'><a href='javascript:' onclick=getPage('"+child[i].url+"',this,'"+child[i].name+"')>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+child[i].name+"</a></li>";
						}
						leftMenu.append(h);
					}
					
				}
				
			},"json");
			
		}
		
		//点击二级目录展示页面
		function getPage(url,my,name){
			//显示右边菜单
			
			var u = '<%=basePath%>'+url + "?requestType=portal";
			
			$("#mains .m_right iframe").attr('src',u);
			if(name == "车间看板"){
				$("#mains .m_right iframe").attr('target','_blank');
			}
			var parentsNode = $(my).parent().parent().children();  //li
			for(var i =0;i<parentsNode.length;i++){
				parentsNode.eq(i).removeClass("m_left");
			}
			$(my).parent().addClass("m_left");
			
			$("#mains .m_right .a_one").html("主页");
			$("#mains .m_right .a_two").html(one_html);
			$("#mains .m_right .a_three").html(name);
			
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
						window.location.href= basePath + "mylogin.jsp";
					}
				},
				error:function(){
					alert("服务器出错！");
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
		
		function queryStore(){
			var url = basePath +"/rest/storeMrecordManageAction/getClassify";
			$.post(url,{materType:'原材料'},function(data){
				debugger;
				for(var i=0;i<data.length;i++){
					$("#"+data[i].key).html(data[i].value);
				}			
			},"json");
		}
	</script>
  </head>
  
  <body>
    
    
    <div id="man">

	<div id="top" style="background: url(<%=basePath%>app/report/images/bgtop.png) no-repeat">
		<div class="logon"><img src="<%=basePath%>app/report/images/logo.png" class="logo"></div>
		<div class="dht">
			<ul class="dh ddhh">
				<li class="fex"><img src="<%=basePath%>app/report/images/fg.png" /></li>
				<li class="dhtp1"><img src="<%=basePath%>app/report/images/dh0.png" class="tp1"></li>
				<li class="zy">
					<a class="ta1" href="<%=basePath %>rest/sysUserManageAction/showMain">主页</a>
				</li>
				<li class="fex1"><img src="<%=basePath%>app/report/images/fg.png" /></li>
			</ul>
			<c:forEach items="${menu}" var="item" varStatus="i"><!-- ${i.index+2} -->
				<c:if test="${item.pid=='-1' }">
					<ul class="dh1">
						<li class="dhtp1"><img src="<%=basePath%>app/report/images/dh${item.sort}.png" class="tp1"></li>
						<li class="jc" id="menu_${item.id}">
							<a class="ta" href="javaScript:void(0)" onclick="getChild('${item.id}',this)">${item.name}</a>
						</li>
						<li class="fex2"><img src="<%=basePath%>app/report/images/fg.png" /></li>
					</ul>
				</c:if>
			</c:forEach>
			<!-- 
			<ul class="dh1">
				<li class="dhtp1"><img src="<%=basePath%>app/report/images/dhout.png" class="tp1"></li>
				<li class="tc">
					<a href="javascript:void(0);" class="ta" style="cursor: pointer;" onclick="outLog();">退出</a>
				</li>
				<li class="fex2"><img src="<%=basePath%>app/report/images/fg.png" /></li>
			</ul>
			  -->
		</div>

	</div>

<div id="mains" style="display: none;width:100%">
	<div class="leftN" style="float: left;">
		<ul class="lefan" id="leftMenu">
		
		</ul>
	</div>
	<div class="m_right" style="float: left;width:86%;margin-left:2px;overflow:hidden;">
				
		<div class="rbt">
			<ul class="rt">
				<li><span style="font-size:12px;">当前位置：</span></li>
				<!-- <li><span class="a_one">主页</span>&nbsp;&gt;</li> -->
				<li><span class="a_two">基础数据</span>&nbsp;&gt;&nbsp;</li>
				<li><span class="a_three">基础数据</span></li>
			</ul>
			<div class="user">
				<span style="color:#034063;font-weight: bolder;font-size: 14px;">当前用户：${session_user.name}！</span>
				<span><a href="javascript:void(0);" onclick="outLog();">退出</a></span>
				<!-- 
				<span class="tr_time" style="margin-left:50px;"></span>
				 -->
			</div>
		</div>
		<iframe id="right_url" src="" width="100%" height="100%" frameborder="no"></iframe>
	</div>
</div>
	
<div id="index">
	<div id="wz">
		<ul class="dck">
			<li class="sy">首页</li>
			<!-- <li class="ck">出库单管理</li> -->
			<li class="outlogin"><a href="javascript:void(0);" onclick="outLog();">退出</a></li>
			<li class="yoers">当前用户：${session_user.name}！</li>
		</ul>
		<img src="<%=basePath%>app/report/images/cha.png" class="cha" />
	</div>

	<div id="mbody">

		<div class="kc">
			<ul class="ylkc">
				<li class="jl">胶料库存：<samp class="samp1" id="胶料">10.234T</samp></li>
				<li class="jl1">铜线库存：<samp class="samp1" id="铜料">21.200KM</samp></li>
				<li class="jl1">铝线库存：<samp class="samp1" id="铝料">15.200KM</samp></li>
				<li class="jl1">钢带库存：<samp class="samp1" id="钢带">8.500KM</samp></li>
				<li class="jl1">铁丝库存：<samp class="samp1" id="铁料">10.234KM</samp></li>
				<li class="jl1">包带库存：<samp class="samp1" id="包带">10.230KM</samp></li>
			</ul>
		</div>

		<div class="tb">
			<div class="gd">&nbsp;&nbsp;&nbsp;&nbsp;今日工单完成情况</div>
			<iframe id="right_url" src="<%=basePath%>rest/mauIndexManageAction/workOrder" width="100%" height="100%" frameborder="no"></iframe>
		</div>

		<div class="tb1">
			<div class="jt">&nbsp;&nbsp;&nbsp;&nbsp;全部机台运行状态</div>
			
			<iframe id="right_url" src="<%=basePath%>rest/mauIndexManageAction/machineStatusNum" width="100%" height="100%" frameborder="no"></iframe>
		</div>

		<div class="bg">
			<div class="he">&nbsp;&nbsp;&nbsp;&nbsp;生产通知单情况</div>
			<iframe id="right_url" src="<%=basePath%>rest/mauIndexManageAction/contractTime" width="100%" height="100%" frameborder="no"></iframe>
			<!-- 
			<div class="btable">
				<table class="table">
					<tr class="tr">
						<th class="th">序号</th>
						<th class="th">合同编号</th>
						<th class="th">生产令</th>
						<th class="th">批次号</th>
						<th class="th">完成情况</th>
						<th class="th">计划完工日期</th>
						<th class="th">交货日期</th>
					</tr>

					<tr class="tr1">
						<td class="td">01</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">02</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">03</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">04</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">05</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">06</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">07</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">08</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">09</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr">
						<td class="td">10</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

					<tr class="tr1">
						<td class="td">01</td>
						<td class="td">sc-2017-06-27-06</td>
						<td class="td">L1704255</td>
						<td class="td">PVCPU-001</td>
						<td class="td">100%</td>
						<td class="td">2017/09/06</td>
						<td class="td">2017/09/06</td>
					</tr>

				</table>
			</div>
			 -->
		</div>

	</div>

</div>
	
	
</div>
    
    
  </body>
</html>
