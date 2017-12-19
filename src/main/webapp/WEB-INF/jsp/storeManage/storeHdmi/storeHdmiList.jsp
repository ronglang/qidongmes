<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>仓库电子看板</title>
		<script src="<%=basePath %>core/js/jquery-2.1.4.min.js" type="text/javascript"></script>
		<script src="<%=basePath %>core/js/dateUtils.js" type="text/javascript"></script>
		<script type="text/javascript">
			var flag = true;
			setInterval(function() {
				setTime();

			}, 1000);

			function setTime() {
				var date = new Date();
				var dateS = date.pattern("yyyy-MM-dd EEE hh:mm:ss");
				$("#nowdate").html(dateS);
			}
			
			setInterval("pages();",10000);
			
			function pages(){
				if(flag) {
					$("#section0").show(1000);
					$("#section1").hide(1000);
					flag = false;
				} else {
					$("#section0").hide(1000);
					$("#section1").show(1000);
					flag = true;
				}
				
			}

			//setInterval("show();",10000);

			function show() {
				if(flag) {
					$("#if").show();
					flag = false;
				} else {
					$("#if").hide();
					flag = true;
				}

			}
		</script>
		<style>
			#container {
				width: 100%;
				height: 100%;
				overflow: hidden;
			}
			
			.sections,
			.section {
				height: 100%;
			}
			
			#container,
			.sections {
				position: relative;
			}
			
			.section {
				background-size: cover;
				background-position: 50% 50%;
				text-align: center;
			}
		</style>
	</head>

	<body>
		<div style="position: relative;margin-top: 1px;">
			<div class="call" style="width: 70%;height:80px;position: absolute; background:#5CACEE;">
				<span style="font-size: 50px;font-family: 'LiSu';color: white;margin-left: 40%;">
					仓&nbsp;&nbsp;库&nbsp;&nbsp;电&nbsp;&nbsp;子&nbsp;&nbsp;看&nbsp;&nbsp;板</span>
			</div>
			<div style="background-color:#5CACEE;height:80px;width:30%;float: right;">
				<div style="float: right;margin-top:40px; font-size:25px ;text-align: center;margin-right: 20px;">
					<span class="call" style="font-size: large;color: white;"> 时间 : </span><span class="call" id="nowdate" style="font-size: large;color: white;"></span>
				</div>
			</div>
			<iframe id="if" src="<%=basePath %>rest/storeObjManageAction/toStoreHdmiCall?requestType=mapuser" style="height: 60px;width:99.9%;border:1px solid #DEDEDE;
				border-top-color: #F2F2F2;background-color: #DEDEDE;" scrolling="no">
				</iframe>
		</div>
		<div id="container">
			<div class="sections">
				<div class="section" id="section0" style="height: 610px;">
					<iframe src="<%=basePath %>rest/storeObjManageAction/toStoreHdmiCrew?requestType=mapuser" style="height: 600px;width:100%;border:1px solid white;" scrolling="no">
					</iframe>
				</div>
				<div class="section" id="section1" style="height: 610px;">
					<iframe src="<%=basePath %>rest/storeObjManageAction/toStoreHdmiCrewB?requestType=mapuser" style="height: 600px;width:100%;border:1px solid white;" scrolling="no">
					</iframe>
				</div>
			</div>
		</div>
	</body>

</html>

