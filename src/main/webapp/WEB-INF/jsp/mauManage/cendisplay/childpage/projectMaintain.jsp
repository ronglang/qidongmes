<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设备保养</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath %>core/js/jquery-1.4.4.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>core/js/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath %>core/js/cssBase.js" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
	var basePath = '<%=basePath %>';
		$(function(){
			
			ajaxData();
			
		});
		
		function ajaxData(){
			
			$.ajax({
				url: basePath+"rest/mauMachineFaultManageAction/getMaintainList",
				dataType: 'json',
				type: 'POST',
				success:function(list){
					maintain(list);
				},
				
			});
			
		}
		
		function maintain(list){
			$("#mlc_list_o").html("");
			var html = "";
			html+="<div class='mlcl_ul'>";
			html+="<ul>";
			for(var i=0;i<list.length;i++){
				if(i%2 == 0){
					html+= "<li class='rs'><span>"+(i+1)+"</span><span>"+list[i].macCode+"</span><span>"+list[i].startMaintainDate+"</span><span>"+(list[i].factMaintainDate==null?"&nbsp;":list[i].factMaintainDate)+"</span><span>"+list[i].status+"</span></li>";
					//html+= "<li><span class='s1'>"+(i+1)+"</span><span class='s1'>"+list[i].macCode+"</span> <span class='s1'>"+list[i].macType+"</span> <span class='s2'>"+list[i].startMaintainDate+"</span><span class='s2'>"+(list[i].factMaintainDate==null?"&nbsp;":list[i].factMaintainDate)+"</span><span class='s1 s3'>"+list[i].status+"</span></li>";
				}else{
					html+= "<li class='ls'><span>"+(i+1)+"</span><span>"+list[i].macCode+"</span><span>"+list[i].startMaintainDate+"</span><span>"+(list[i].factMaintainDate==null?"&nbsp;":list[i].factMaintainDate)+"</span><span>"+list[i].status+"</span></li>";
				}
			}
			html+="</ul>";
			html+="</div>";
			$("#mlc_list_o").append(html);
			//通知滚动
			$("#mlc_list_o .mlcl_ul").marquee({
				isMarquee:true,
				isEqual:false,
				scrollDelay:150,
				direction:'top'
			});
			
		}
		
	</script>
	
	<style type="text/css">
			ul{
				list-style-type: none;
			}
			.ml_con h3{
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
			.ml_con{
				height: 600px;
				width: 95%;
				border-radius: 5px;
				background-color: #224B81;
				margin: 0 auto;
			}
		    #mlc_list_o .mlcl_ul,#mlc_list_t .mlcl_ul{
				width:95%;
				height:450px;
				overflow:hidden;
				margin:0 auto;
	    	}
	    	#mlc_list_o .mlcl_ul ul,#mlc_list_t .mlcl_ul ul{
	    		height:450px;
				width: 100%;
				margin: 0 auto;
				padding: 0;
	    	}
	    	#mlc_list_o .mlcl_ul ul li,#mlc_list_t .mlcl_ul ul li{
	    		height: 50px;
	    	}
	    	#mlc_list_o .mlcl_ul ul li.ls span,#mlc_list_t .mlcl_ul ul li.ls span{
	    		background-color: #0367CE;
	    		width:20%;
	    	}
	    	#mlc_list_o .mlcl_ul ul li.rs span,#mlc_list_t .mlcl_ul ul li.rs span{
	    		background-color: #03244D;
	    		width:20%;
	    	}
	    	#mlc_list_o .mlcl_ul ul li span,#mlc_list_t .mlcl_ul ul li span{
	    		display: inline-block;
	    		height: 50px;
	    		line-height: 50px;
	    		text-align: center;
	    		color: white;
	    		font-size: 14px;
	    		overflow: hidden;
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
	    		width: 95%;
	    		margin: 0 auto;
	    	}
	    	.title span{
	    		display: inline-block;
	    		height: 40px;
	    		line-height: 40px;
	    		width:20%;
	    		text-align: center;
	    		background:#2E3D57;
	    		color: white;
	    		font-weight:bold;
	    		margin: 0;
	    		padding: 0;
	    	}
	
	</style>

  </head>
  
  <body>
    
    <div class="ml_con">
		<h3>设备保养</h3>
		<div class="title" style="margin-top: 10px;">
			<span>序号</span><span>设备编码</span><span>计划时间</span><span>开始时间</span><span>状态</span>
		</div>
		<div id="mlc_list_o">
			
		</div>
		
	</div>
    
  </body>
</html>
