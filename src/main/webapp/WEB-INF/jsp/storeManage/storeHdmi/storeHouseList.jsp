<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>仓库电子看板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="<%=basePath%>core/img/logos.png">
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
			var basePath = '<%=basePath%>';
			$(function(){
				
				$("#times").showDate();
				
				getMatInInfoData();
				getProInInfoData();
				matOutInfo();
				proOutInfo();
				
			});
			
			function nation(){
				//在此处将数据封装到页面
				debugger;
				//var map = eval('('+event.data+')');
				var html = "";
				$("#head").html("");
				html += "<div class='h_con'>";
				html +="<ul>";
				for(var i=0;i<8;i++){
					html += "<li>";
					html +=	"<span>呼叫人：张三</span>";
					html +=	"<span>呼叫叉车：DA</span>";
					html +=	"<span>呼叫位置：的范围分为</span>";
					html +=	"<span>呼叫时间：2018-15-48 12:12:12</span>";
					html += "</li>";
				}
					
				html +="</ul>";
				html += "</div>";
				$("#head").append(html);
					//通知滚动
					$("#head .h_con").marquee({
						isMarquee:true,
						isEqual:false,
						scrollDelay:50,
						direction:'left',
					});
			}
			//获取原材料入库数据
			function getMatInInfoData(){
				$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getMaterQuality",
					type: "POST",
					dataType: "JSON",
					success: function(data){
						var lists = eval("("+data+")");
						var list = lists.data;
						matInInfo(list);
					}
					
				});
				
			}
			function matInInfo(list){
				debugger;
				$("#mm_con").html("");
				var html = "";
				html+="<div class='mlcl_ul'>";
				
				html+="<ul>";
				var color;
				var scolor;
				for(var i=0;i<list.length;i++){
					if(i%2 == 0){
						if(list[i].testState=='已检测'){
							color = "green";
						}else{
							color = "red";
						}
						if(list[i].testResult=='合格'){
							scolor = "green";
						}else{
							scolor = "red";
						}
						
						html+= "<li class='lb'><span>"+(i+1)+"</span><span>"+list[i].materName+"</span><span>"+list[i].materGgxh+"</span><span>"+list[i].materAmount+"</span><span>"+list[i].materFirm+"</span><span style='color:"+color+"'>"+((list[i].testState=='已检测')?'Y':'N')+"</span><span style='color:"+scolor+"'>"+((list[i].testResult=='合格')?'Y':'N')+"</span></li>";
						//html+= "<li class='lb'><span>"+(i+1)+"</span><span>"+list[i].materName+"</span><span>"+list[i].materGgxh+"</span><span>"+list[i].materAmount+"</span><span>"+list[i].materFirm+"</span><span tyle ='color:'"+scolor+"''>"+list[i].testState+"</span><span style ='color:'"+color+"''>"+((list[i].testResult==null)?'':list[i].testResult)+"</span></li>";
					}else{
						if(list[i].testState=='已检测'){
							color = "green";
						}else{
							color = "red";
						}
						if(list[i].testResult=='合格'){
							scolor = "green";
						}else{
							scolor = "red";
						}
						html+= "<li class='ls'><span>"+(i+1)+"</span><span>"+list[i].materName+"</span><span>"+list[i].materGgxh+"</span><span>"+list[i].materAmount+"</span><span>"+list[i].materFirm+"</span><span style='color:"+color+"'>"+((list[i].testState=='已检测')?'Y':'N')+"</span><span style='color:"+scolor+"'>"+((list[i].testResult=='合格')?'Y':'N')+"</span></li>";
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
				
			}
			//获取成品入库数据
			function getProInInfoData(){
				$.ajax({
					url: basePath + "rest/mauNewDislayManageAction/getMauQuality",
					type: "POST",
					dataType: "JSON",
					success: function(data){
						var lists = eval("("+data+")");
						var list = lists.data;
						proInInfo(list);
					}
					
				});
				
			}
			
			//成品质检
			function proInInfo(list){
				
				$("#mm_con2").html("");
				var html = "";
				html+="<div class='mlcl_ul'>";
				
				html+="<ul>";
				for(var i=0;i<list.length;i++){
					var color;
					var scolor;
					if(i%2 == 0){
						if(list[i].testState=='已检测'){
							color = "green";
						}else{
							color = "red";
						}
						if(list[i].testResult=='合格'){
							scolor = "green";
						}else{
							scolor = "red";
						}
						html+= "<li class='lb'><span>"+(i+1)+"</span><span>"+list[i].axisName+"</span><span>"+list[i].proGgxh+"</span><span>"+list[i].macCode+"</span><span>"+(list[i].testBy==null?"":list[i].testBy)+"</span><span style='color:"+color+"'>"+(list[i].testState=='已检测'?'Y':'N')+"</span><span style='color:"+scolor+"'>"+(list[i].testResult=='合格'?'Y':'N')+"</span></li>";
					}else{
						if(list[i].testState=='已检测'){
							color = "green";
						}else{
							color = "red";
						}
						if(list[i].testResult=='合格'){
							scolor = "green";
						}else{
							scolor = "red";
						}
						html+= "<li class='ls'><span>"+(i+1)+"</span><span>"+list[i].axisName+"</span><span>"+list[i].proGgxh+"</span><span>"+list[i].macCode+"</span><span>"+(list[i].testBy==null?"":list[i].testBy)+"</span><span style='color:"+color+"'>"+(list[i].testState=='已检测'?'Y':'N')+"</span><span style='color:"+scolor+"'>"+(list[i].testResult=='合格'?'Y':'N')+"</span></li>";
					}
					
				}
				html+="</ul>";
				html+="</div>";
				$("#mm_con2").append(html);
				//通知滚动
				$("#mm_con2 .mlcl_ul").marquee({
					isMarquee:true,
					isEqual:false,
					scrollDelay:200,
					direction:'top'
				});
				
			}
			
			//原材料领料记录--计划
			function matOutInfo(){
				var url = basePath + "rest/storeObjManageAction/getMaterOutDisplay";
				$.post(url,{},function(data){
					if(data.success){
						var list = data.data;
						$("#mm_con3").html("");
						var html = "";
						html+="<div class='mlcl_ul'>";
						html+="<ul>";
						for(var i=0;i<list.length;i++){
							var bean = list[i];
							if(i%2 == 0){
								html+= "<li class='lb'><span>"+(i+1)+"</span><span>"+bean.workcode+"</span><span>"+bean.materil+"</span><span>"+(bean.maccode==null?"":bean.maccode)+"</span><span>"+(bean.ptime==null?"":bean.ptime)+"</span></li>";
							}else{
								html+= "<li class='ls'><span>"+(i+1)+"</span><span>"+bean.workcode+"</span><span>"+bean.materil+"</span><span>"+(bean.maccode==null?"":bean.maccode)+"</span><span>"+(bean.ptime==null?"":bean.ptime)+"</span></li>";
							}
							
						}
						html+="</ul>";
						html+="</div>";
						$("#mm_con3").append(html);
						//通知滚动
						$("#mm_con3 .mlcl_ul").marquee({
							isMarquee:true,
							isEqual:false,
							scrollDelay:200,
							direction:'top'
						});
					}
				},"json");
				
				
				
			}
			function proOutInfo(){
				var url = basePath + "rest/storeObjManageAction/getProOutDisplay";
				$.post(url,{},function(data){
					if(data.success){
						var list = data.data;
						$("#mm_con4").html("");
						var html = "";
						html+="<div class='mlcl_ul'>";
						
						html+="<ul>";
						for(var i=0;i<list.length;i++){
							var bean = list[i];
							if(i%2 == 0){
								html+= "<li class='lb'><span>"+(i+1)+"</span><span>"+bean.objSort+"</span><span>"+bean.objGgxh+"</span><span>"+bean.weight+"kg</span><span>"+bean.inDate+"</span></li>";
							}else{
								html+= "<li class='ls'><span>"+(i+1)+"</span><span>"+bean.objSort+"</span><span>"+bean.objGgxh+"</span><span>"+bean.weight+"kg</span><span>"+bean.inDate+"</span></li>";
							}
							
						}
						html+="</ul>";
						html+="</div>";
						$("#mm_con4").append(html);
						//通知滚动
						$("#mm_con4 .mlcl_ul").marquee({
							isMarquee:true,
							isEqual:false,
							scrollDelay:200,
							direction:'top'
						});
					}
				},"json");
				
				
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
				height: 40px;
				line-height: 40px;
				background-color: #3F77C1 ;
				border-bottom: 2px solid #1E4785;
			}
			#top .t_center{
				height: 40px;
				width: 56%;
				color: white;
				font-size: 25px;
				text-align: right;
				letter-spacing: 5px;
				float: left;
			}
			#top .t_time{
				width: 44%;
				height: 40px;
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
				height: 30px;
				line-height: 30px;
				border-top-left-radius:5px;
				border-top-right-radius:5px;
				background-color: #4C85D4;
				text-align: center;
				color: yellow;
				font-size: 16px;
				letter-spacing: 5px;
			}
			#main .ml_con{
				height: 350px;
				width: 45%;
				border-radius: 5px;
				background-color: #224B81;
				margin: 10px;
				float: left;
			}
			
			#main .ml_con .mm_u{
				width: 100%;
				margin: 0 auto;
				text-align: center;
			}
			#main .ml_con .mm_u li{
				height: 35px;
				line-height: 35px;
				color: white;
				font-size: 14px;
				font-weight: bold;
				width: 100%;
			}
			#main .ml_con .mm_u li span{
				display: inline-block;
	    		height: 35px;
	    		width:14%;
	    		line-height: 35px;
	    		text-align: center;
	    		color: white;
	    		background-color: #2E3D57;
			}
			#main .ml_con .mm_u2 li span{
	    		width:19.7%;
			}
			
			#mm_con .mlcl_ul{
				width:100%;
				height:270px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mm_con .mlcl_ul ul{
	    		height:270px;
				width: 100%;
				margin: 0 auto;
				padding: 0;
	    	}
	    	#mm_con .mlcl_ul ul li{
	    		height: 30px;
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
	    		height: 30px;
	    		width:14%;
	    		line-height: 30px;
	    		text-align: center;
	    		color: white;
	    		overflow: hidden;
	    	}
			
			
			#mm_con2 .mlcl_ul{
				width:100%;
				height:270px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mm_con2 .mlcl_ul ul{
	    		height:270px;
				width: 100%;
				margin: 0 auto;
				padding: 0;
	    	}
	    	#mm_con2 .mlcl_ul ul li{
	    		height: 30px;
	    		text-align: center;
	    	}
	    	#mm_con2 .mlcl_ul ul li.ls span{
	    		background-color: #0367CE;
	    	}
	    	
	    	#mm_con2 .mlcl_ul ul li.lb span{
	    		background-color: #03244D;
	    	}
	    	#mm_con2 .mlcl_ul ul li span{
	    		display: inline-block;
	    		height: 30px;
	    		width:14%;
	    		line-height: 30px;
	    		text-align: center;
	    		color: white;
	    		overflow: hidden;
	    	}
			
			#mm_con3 .mlcl_ul{
				width:100%;
				height:270px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mm_con3 .mlcl_ul ul{
	    		height:270px;
				width: 100%;
				margin: 0 auto;
				padding: 0;
	    	}
	    	#mm_con3 .mlcl_ul ul li{
	    		height: 30px;
	    		text-align: center;
	    	}
	    	#mm_con3 .mlcl_ul ul li.ls span{
	    		background-color: #0367CE;
	    	}
	    	
	    	#mm_con3 .mlcl_ul ul li.lb span{
	    		background-color: #03244D;
	    	}
	    	#mm_con3 .mlcl_ul ul li span{
	    		display: inline-block;
	    		height: 30px;
	    		width:19.7%;
	    		line-height: 30px;
	    		text-align: center;
	    		color: white;
	    		overflow: hidden;
	    	}
			
			#mm_con4 .mlcl_ul{
				width:100%;
				height:270px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mm_con4 .mlcl_ul ul{
	    		height:270px;
				width: 100%;
				margin: 0 auto;
				padding: 0;
	    	}
	    	#mm_con4 .mlcl_ul ul li{
	    		height: 30px;
	    		text-align: center;
	    	}
	    	#mm_con4 .mlcl_ul ul li.ls span{
	    		background-color: #0367CE;
	    	}
	    	
	    	#mm_con4 .mlcl_ul ul li.lb span{
	    		background-color: #03244D;
	    	}
	    	#mm_con4 .mlcl_ul ul li span{
	    		display: inline-block;
	    		height: 30px;
	    		width:19.7%;
	    		line-height: 30px;
	    		text-align: center;
	    		color: white;
	    		overflow: hidden;
	    	}
			
		</style>

  </head>
  
  <body>
    
    <div id="top">
			<div class="t_center">
				仓库电子看板
			</div>
			<div class="t_time">
				<span id="times"></span>
			</div>
		</div>
		
		<!--<div id="head">

		</div>-->
		
		
		<div id="main">
			<div class="ml_con">
				<h3>原材料入库情况</h3>
				<ul class="mm_u">
					<li>
						<span>序号</span><span>名称</span><span>规格型号</span><span>入库数量</span><span>供应商</span><span>质检状态</span><span>质检结果</span>
					</li>
				</ul>
				<div id="mm_con">
					
				</div>
				
			</div>
			
			<div class="ml_con">
				<h3>原材料领料出库情况</h3>
				<ul class="mm_u mm_u2">
					<li><!-- <span>单据类型</span> -->
						<span>序号</span><span>工单号</span><span>材料名称</span><span>机台号</span><span>使用时间</span>
					</li>
				</ul>
				<div id="mm_con3">
					
				</div>
				
			</div>
			<div class="ml_con">
				<h3>成品入库情况</h3>
				<ul class="mm_u">
					<li>
						<span>序号</span><span>轴名称</span><span>规格型号</span><span>机台编码</span><span>质检人</span><span>质检状态</span><span>质检结果</span>
					</li>
				</ul>
				<div id="mm_con2">
					
				</div>
				
			</div>
			<div class="ml_con">
				<h3>成品出库情况</h3> <!-- 目前无成品出库信息，目前为假数据，因此无法知道显示字段，等有了数据再来修改成品出库信息的样式 -->
				<ul class="mm_u mm_u2">
					<li>
						<span>序号</span><span>名称</span><span>规格型号</span><span>出库数量</span><span>时间</span>
					</li>
				</ul>
				<div id="mm_con4">
					
				</div>
				
			</div>
		</div>
    
    
  </body>
</html>
