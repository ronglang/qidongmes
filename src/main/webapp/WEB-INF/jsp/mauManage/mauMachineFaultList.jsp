<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>工程部电子看板</title>
			<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
	    <script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/mauManage/mauMachineFaultList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "mauMachineFaultManage";    
    	</script>


	<style type="text/css">
			
			#nowTime{
			font-size: 20px;
			color:white;
			}
			.nav-a{
				width: 33%;
				height: 60px;
				text-align: center;
				float: left;
				
			}
			.span-a{
				font-size:20px;
				font-family: "微软雅黑";
				font-weight: 300;
				color:white;
			}
	
	
	
			.tb{
				width: 30%;
				height: 80px;
				float: right;
				text-align: right;
				line-height: 150px;
			}
			 .newSpan{
			 	text-align: center;
			 	float: left;
			 	margin-top: 20px;
			 	margin-left: 40%;
			 }
		</style>
	
	<script type="text/javascript">

	setInterval(function(){
		setTime();
	},1000);
	
	function setTime(){
		var date = new Date();
		var dates=date.pattern("yyyy-MM-dd EEE hh:mm:ss");
		$("#nowTime").html(dates);
	}
		
			
		
			
		</script>
  </head>
  <body >
  		<div  style="width: 100%;height: 100px;background: rgba(33, 138, 217, 1);float:left;">
			<div class="newSpan">
				<span style="font-size: 30px;color: white;font-family: 'arial, helvetica, sans-serif';font-weight: 300;">
					工&nbsp;&nbsp;程&nbsp;&nbsp;部&nbsp;&nbsp;电&nbsp;&nbsp;子&nbsp;&nbsp;看&nbsp;&nbsp;板
				</span>
				
			</div>
			<div class="tb"><span class="span-a" >时间：<span id="nowTime"></span></span></div>
		</div>
		<div  style="width: 100%;height: 100px;background-color: #DEDEDE;float:left;">
		<iframe id="if" src="<%=basePath %>rest/mauMachineFaultManageAction/toMachineFaultHdmiCall?requestType=mapuser" style="height: 60px;width:99.9%;border:1px solid #DEDEDE;
				border-top-color: #F2F2F2;background-color: #DEDEDE;" scrolling="no">
		</iframe>
		</div>
		
		<div style="height: 700px;width:49%;float:left;">
		<div style=" width:100%;height:30px;float: left;text-align:center;background:gray; border-bottom: 1px solid gray;">
		<span style="font-size:18px;font-weight: 400;font-family: inherit;color: white;">
		&nbsp;设&nbsp;备&nbsp;保&nbsp;养&nbsp;计&nbsp;划</span>
		</div>
		<iframe id="if" src="<%=basePath %>rest/mauMachineFaultManageAction/toMachineFaultHdmiTable1?requestType=mapuser" style="height: 690px;width:100%;border:1px solid white;margin-top: 0px;" scrolling="no"
				border-top-color: #F2F2F2;background-color: #DEDEDE;" scrolling="no">
		</iframe>
		</div>
		
		<div style="height: 700px;width:49%;float:right;">
		<div style=" width:100%;height:30px;float: left;text-align:center;background:gray; border-bottom: 1px solid gray;">
		<span style="font-size:18px;font-weight: 400;font-family: inherit;color: white;">
		&nbsp;工&nbsp;程&nbsp;部&nbsp;维&nbsp;修&nbsp;记&nbsp;录</span>
		</div>
		<iframe id="if" src="<%=basePath %>rest/mauMachineFaultManageAction/toMachineFaultHdmiTable?requestType=mapuser" style="height: 690px;width:100%;border:1px solid white;margin-top: 0px;" scrolling="no"
				border-top-color: #F2F2F2;background-color: #DEDEDE;" scrolling="no">
		</iframe>
		</div>
  </body>
</html>
