<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>core/js/kxbdSuperMarquee.js" type="text/javascript" charset="utf-8"></script>
	
	<style type="text/css">
	
		*{
				margin: 0;
				padding: 0;
				font-size: 13px;
			}
			body{
				background-color: #3267AB;
			}
			#top{
				height: 60px;
				line-height: 60px;
				background-color: #3F77C1 ;
				border-bottom: 2px solid #1E4785;
			}
			#top .t_center{
				height: 60px;
				width: 56%;
				color: white;
				font-size: 25px;
				text-align: right;
				letter-spacing: 5px;
				float: left;
			}
			#top .t_time{
				width: 44%;
				height: 60px;
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
			
			ul{
				list-style-type: none;
			}
			#main{
				width:100%;
			}
			#main .m_left{
				width: 50%;
				float: left;
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
				height: 500px;
				width: 95%;
				border-radius: 5px;
				background-color: #224B81;
				margin: 0 auto;
			}
			#main .m_right{
				width: 50%;
				float: left;
			}
			#mlc_list_o .mlcl_ul,#mlc_list_t .mlcl_ul{
				width:90%;
				height:350px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mlc_list_o .mlcl_ul ul,#mlc_list_t .mlcl_ul ul{
	    		height:350px;
				width: 100%;
				margin: 0 auto;
				border-bottom: 1px solid white;
				padding: 0;
	    	}
	    	#mlc_list_o .mlcl_ul ul li,#mlc_list_t .mlcl_ul ul li{
	    		height: 50px;
	    	}
	    	#mlc_list_o .mlcl_ul ul li.ls span,#mlc_list_t .mlcl_ul ul li.ls span{
	    		background-color: #0367CE;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span,#mlc_list_t .mlcl_ul ul li span{
	    		display: inline-block;
	    		height: 50px;
	    		line-height: 50px;
	    		border-top: 1px solid white;
	    		border-left: 1px solid white;
	    		text-align: center;
	    		color: white;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span.s1,#mlc_list_t .mlcl_ul ul li span.s1{
	    		width: 80px;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span.s2,#mlc_list_t .mlcl_ul ul li span.s2{
	    		width: 160px;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span.s3,#mlc_list_t .mlcl_ul ul li span.s3{
	    		border-right: 1px solid white;
	    	}
	    	.title{
	    		width: 90%;
	    		margin: 0 auto;
	    	}
	    	.title span{
	    		display: inline-block;
	    		height: 50px;
	    		line-height: 50px;
	    		border-top: 1px solid white;
	    		border-left: 1px solid white;
	    		text-align: center;
	    		color: yellow;
	    		margin: 0;
	    		padding: 0;
	    		margin-right: -4px;
	    	}
	    	
	    	
	
	</style>
		
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var urlPirfex = basePath.substring(7, basePath.length);
			$(function(){
				
				$("#times").html(showTime());
				setInterval(function(){
					$("#times").html(showTime());
				},1000);
				
			});
			
			
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
			    var now_time=year+'/'+month+'/'+date+'　'+hour+':'+minutes+':'+second+'　'+show_day[day]; 
			    
			    return now_time;
			} 
	</script>

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
		<div id="callCar">
			<iframe src="<%=basePath%>rest/mauCallForkliftRecordManageAction/toCallCar?requestType=mapuser" width="100%" height="60px" frameborder="0" scrolling="no" class=""></iframe>
		</div>
		<div id="storeDivs" style="height:100px;">
			<iframe src="<%=basePath%>rest/storeObjManageAction/toStoresList?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
		</div>
		
		<div id="main">
			<div class="m_left">
				<iframe src="<%=basePath%>rest/storeObjManageAction/toOutStoreList?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
			<div class="m_right">
				<iframe src="<%=basePath%>rest/storeObjManageAction/toInStoreList?requestType=mapuser" width="100%" height="100%" frameborder="0" scrolling="no" class=""></iframe>
			</div>
		</div>
    
    
  </body>
</html>
