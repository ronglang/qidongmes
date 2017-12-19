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
		<title></title>
		<script src="<%=basePath %>core/js/jquery-2.1.4.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div style="width: 50%; height: 30px;">
			<div style="background-color: red;color: white;width: 50px;text-align: center;float: left;">1</div>
			<div style=" width: 200px;height:auto;font-size:20px;color: #4682B4; ">&nbsp;&nbsp;物料库存情况</div>
		</div>
		<div style="width: 100%; height: auto;">
			<ul>
				<li class="aa"><div class="dd" id="d1"></div><div class="cc">胶料库存量</div></li>
				<li class="aa"><div class="dd" id="d2"></div><div class="cc">铜线库存量</div></li>
				<li class="aa"><div class="dd" id="d3"></div><div class="cc">铝线库存量</div></li>
				<li class="aa"><div class="dd" id="d4"></div><div class="cc">铁丝库存量</div></li>
				<li class="aa"><div class="dd" id="d5"></div><div class="cc">钢带库存量</div></li>
				<li class="aa"><div class="dd" id="d6"></div><div class="cc">包带库存量</div></li>
				<li class="aa"><div class="dd" id="d7"></div><div class="cc">编织带库存量</div></li>
			</ul>
		</div>
		<div style="background-color: red;color: white;width: 50px;text-align: center;float: left;">2</div>
		<div style=" width: 200px;height:auto;font-size:20px;color: #4682B4; ">&nbsp;&nbsp;物料出库情况</div>
		<iframe style="width: 100%;height: 410px;border: 1px solid #F2F2F2;" scrolling="no" 
		src="<%=basePath %>rest/storeObjManageAction/toStoreHdmiTable">
		</iframe>
		<script>
		$(function(){
			/* $("#d1").text("21KG"); */
			$.ajax({
				url:'<%=basePath %>rest/storeScrapRecordManageAction/getScrapStock',
				type:'POST',
				async:false,
				dataType:'json',
				success:function(list){
					load(list);
					
				}
			});
			myload();
		});
		
		function load(list){
			var amountjl = 0;
			var amountcu = 0;
			var amountal = 0;
			var amountfe = 0;
			var amountfepro = 0;
			var amountbd = 0;
			var amountbz = 0;
			for(var i = 0;i<list.length;i++){
				//debugger;
				if("铜料"==list[i].materName){
					//alert(list[i].amount);
					amountcu = amountcu+list[i].actStock;
				}else if ("胶料"==list[i].materName){
					//alert(list[i].amount);
					amountjl = amountjl+list[i].actStock;
				}else if ("铝料"==list[i].materName){
					amountal = amountal+list[i].actStock;
				}else if ("铁丝"==list[i].materName){
					amountfe = amountfe+list[i].actStock;
				}else if ("钢带"==list[i].materName){
					amountfepro = amountfepro+list[i].actStock;
				}else if ("包带"==list[i].materName){
					amountbd = amountbd+list[i].actStock;
				}else if ("编织带"==list[i].materName){
					amountbz = amountbz+list[i].actStock;
				}
			}
			if(amountjl < 10000){$("#d1").text(amountjl+"KG");}else{$("#d1").text(amountjl/1000+"T");}
			if(amountcu < 10000){$("#d2").text(amountcu+"KG");}else{$("#d2").text(amountcu/1000+"T");}
			if(amountal < 10000){$("#d3").text(amountal+"KG");}else{$("#d3").text(amountal/1000+"T");}
			if(amountfe < 10000){$("#d4").text(amountfe+"KG");}else{$("#d4").text(amountfe/1000+"T");}
			if(amountfepro < 10000){$("#d5").text(amountfepro+"KG");}else{$("#d5").text(amountfepro/1000+"T");}
			if(amountbd < 10000){$("#d6").text(amountbd+"KG");}else{$("#d6").text(amountbd/1000+"T");}
			if(amountbz < 10000){$("#d7").text(amountbz+"KG");}else{$("#d7").text(amountbz/1000+"T");}
			
			
		}
		
		 function myload(){
			  	var basePath = "<%=basePath%>";
					var urlPirfex = basePath.substring(7, basePath.length);
					var url = "ws://"+urlPirfex+"storeCrewWebSocket";
				
					var webSocket = new WebSocket(url);
					webSocket.onerror = function(event) {
						onError(event);
					};
				
					webSocket.onopen = function(event) {
						onOpen(event);
					};
				
					webSocket.onmessage = function(event) {
						onMessage(event);
					};
				
					function onMessage(event) {
						var jsonBean = eval('('+event.data+')');
						//debugger;
						load(jsonBean);
						
					}
					function onOpen(event) { 
						webSocket.send("true");
					}
				
					function onError(event) {
					}
			  }
		
		
		</script>
	</body>
	<style>
		ul{
			height: 100px;
		}
		ul .aa {
			position: relative;
			float: left;
			overflow: hidden;
			height: 100%;
			width: 12%;
			margin-left: 2%;
			padding-top: 1px;
			font-family: YouYuan;
			font-weight: 900;
		}
		.dd{
			float: right;
			margin-right: 34%;
			position: absolute;
			width: 50%;
			font-family: "宋体";
			font-size: 50px;
			color: #4682B4;
			
		}
		.cc{
			margin-top:45px;
			margin-left:20px;
			width: 100%;
			font-size: xx-small;
			font-family:'STKaiti';
			color:black;
		}
	</style>

</html>
