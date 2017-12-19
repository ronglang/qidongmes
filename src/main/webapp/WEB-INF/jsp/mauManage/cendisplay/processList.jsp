<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工序列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		 <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/dateUtils.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
  <style>
			body {
			}
			#headtable {
				text-align: center;
				font-size: 30px;
    			color: #FFFFFF;
    			height: 50px;
    			line-height: 50px;
    			width: 100%;
			}
			#headtitle{
				font-size: 34px;
			}
			#content{
				margin-top: 20px;
				padding: 20px 100px;
			}
			#time{
				text-align: left;
				font-size:18px ;
			}
			#contentlist{
				
			}
			#ulist li{
				font-size: 30px;
				color: #FFFFFF;
				width: 230px;
				height: 260px;
				text-align: center;
				margin-left:10px ;
				display:inline-block;
			}
			#ulist li h3{
				font-size: 24px;
				margin:0 auto;
				margin-top:10px;
				color:#F04D7E;
			}
			#ulist li img{
				width: 130px;
				height: 130px;
			}
		</style>
		
		<script type="text/javascript">
			setInterval("setTime();",1000);
			function setTime(){
				var date = new Date();
				var datas = date.pattern("yyyy-MM-dd EEE hh:mm:ss");
				$("#time").html(datas);
			}
		</script>
  </head>
 
 <body>
		<div id="main">
			
			<div id="content">
				<div id="contentlist">
					<ul id="ulist">
						<li>
							<a href="<%=basePath%>rest/testWireDrawingAction/toListPageTwo?requestType=portal" target="_blank"> 
								<img src="<%=basePath%>app/images/process/1.png"  title="拉丝">
							</a>
							<h3>拉丝(八头拉、大拉机)</h3>
						</li>
						<li>
							<a href="<%=basePath%>rest/lineBoard/toboard?requestType=portal" target="_blank"> 
							<img src="<%=basePath%>app/images/process/2.png"  title="绞线"></a>
							<h3>绞线</h3>
						</li>
						<li>
							
							<a href="<%=basePath%>rest/insulation/toindex?requestType=portal" target="_blank">
							 <img src="<%=basePath%>app/images/process/3.png"  title="绝缘"></a>
							<h3>(押出机)绝缘</h3>
						</li>
						<li>
							<a href="<%=basePath%>rest/lineBoard/toCent?requestType=portal" target="_blank"> 
							<img src="<%=basePath%>app/images/process/7.png"  title="分盘"></a>
							<h3>(复绕)分盘</h3>
						</li>
						<li>
							<a href="<%=basePath%>rest/lineBoard/toPacketBind?requestType=portal" target="_blank"> 
							<img src="<%=basePath%>app/images/process/7.png"  title="包带"></a>
							<h3>包带</h3>
						</li>
						<!-- 
						<li>
							<a href="<%=basePath%>rest/testWireDrawingAction/toListPageTwo?requestType=portal" target="_blank"> 
								<img src="<%=basePath%>app/images/process/1.png"  title="拉丝">
							</a>
							<h3>(大拉机)拉丝</h3>
						</li>
						
						<li>
							<a href="<%=basePath%>rest/sheathboard/tosheath?requestType=portal" target="_blank"> 
							<img src="<%=basePath%>app/images/process/4.png"  title="挤护套"></a>
							<h3>挤护套</h3>
						</li>
						<li>
							<a href="<%=basePath%>rest/overLine/toindex?requestType=portal" target="_blank"> 
							<img src="<%=basePath%>app/images/process/5.png"  title="成缆"></a>
							<h3>成缆</h3>
						</li>
						<li>
							<a href="<%=basePath%>rest/armourboard/toarmour?requestType=portal" target="_blank"> 
							<img src="<%=basePath%>app/images/process/6.png"  title="铠装"></a>
							<h3>铠装</h3>
						</li>
						 -->
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>