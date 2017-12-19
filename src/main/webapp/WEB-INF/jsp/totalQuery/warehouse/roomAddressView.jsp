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
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"src="<%=basePath%>core/js/echarts-all-3.js"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
</head>
<style>
.shelf {
	width: 40px;
	height: 40px;
	border: 5px solid white;
	background-image: url(<%=basePath %>app/images/shelf.png);
}

.label {
	width: 50px;
	height: 5px;
	border: 5px solid white;
	background-color: white;
	text-align: center;
	color: red;
}

.mainshelf {
	margin-left: 90px;
	margin-top: 135px;
	float: left;
}

.img {
	display: none;
}

#inform {
				width: 250px;
				height: 200px;
				border: 1px solid #ccc;
				background: white;
				display: none;
				position: absolute;
			}
			
			#inform span {
				width: 0px;
				height: 0px;
				border-width: 10px;
				border-style: none solid solid none;
				position: absolute;
			}
			
			#inform .tb-border {
				left: -10px;
				border-color: transparent #ccc transparent transparent;
				top: -1px;
			}
			
			#inform .tb-background {
				left: -9px;
				border-color: transparent white transparent transparent;
			}

</style>
<script type="text/javascript">
	var materName;
	var id;
	$(function() {
		loadRoom();
	});

	function loadRoom() {
		//alert(1);
		var a = [ 'B1', 'B2', 'B3', 'B4'];
		var b = [ 'B5', 'B6', 'B7', 'B8'];
		var Room = '<table class="room" id="room" cellpadding="15" cellspacing="15" border="0">';
		for (var i = 0; i < 2; i++) {
			Room += '<tr class="rtr" >';
			if(i==0){
				for (var j = 0; j < 4; j++) {
					var shelf = '';
					for (var k = 0; k < 4; k++) {
						shelf += '<tr class="shtr" style="border: 1px solid white;">';
						if(k==3){
							shelf +='<td class="label" colspan="4"  >'+a[j]+'</td>';
						}else{
							for (var x = 0; x < 4; x++) {
								shelf += '<td class="shelf"><div class="img" id='+ a[j]+ k + x
								+ ' onmouseenter="show(this)" onclick="toGrid()"  ><img src="<%=basePath %>app/images/形状-1.png"/></div></td>';
								//debugger;
							}
						}
						shelf += '</tr>';
					}
					Room += '<td><table class="ta11" cellpadding="0" cellspacing="0" border="0">'
							+ shelf + '</table></td>';
				}
			}else{
				for (var j = 0; j < 4; j++) {
					var shelf = '';
					for (var k = 0; k < 4; k++) {
						shelf += '<tr class="asd" style="border: 1px solid white;">';
						if(k>0){
							for (var x = 0; x < 4; x++) {
								shelf += '<td class="shelf"><div class="img" id='+ b[j]+ k + x
								+ ' onmouseenter="show(this)" onclick="toGrid()" ><img src="<%=basePath %>app/images/形状-1.png"/></div></td>';
							}
						}else{
							shelf +='<td class="label" colspan="4"  >'+b[j]+'</td>';
						}
						shelf += '</tr>';
					}
					Room += '<td><table class="ta11" cellpadding="0" cellspacing="0" border="0">'
							+ shelf + '</table></td>';
				}
			}
			
			Room += '</tr>';

		}
		Room += '</table>';
		
		$("#mainshelf").append(Room);
		getaddress();
	}

	function getaddress() {
		materName = '胶料';
		$.ajax({
			url:'<%=basePath%>rest/statStoreObjVoManageAction/getAddress?materName=' + materName,
					type : 'POST',
					dataType : 'JSON',
					//data:
					success : function(str) {
						debugger;
						for (var i = 0; i < str.length; i++) {
							$("#" + str[i]).show(); //$("div").attr("id");
						}
					},
					error : function() {

					}

				});

	}
	
	function show(aa){
		id = $(aa).attr("id");
		//alert(id);
		var url ="<%=basePath%>rest/statStoreObjVoManageAction/toFormPage?address="+id;
		$("#ifr").show();
		$("#ifr").attr("src",url);
		var content = document.getElementById(id);
		debugger;
		var inform = document.getElementById("inform");
		content.onmouseover = function(ev) {
			var ev = ev || event;
			inform.style.display = "block";
			inform.style.left = (ev.clientX - this.offsetLeft + 20) + "px";
			inform.style.top = (ev.clientY - this.offsetTop - 20) + "px";
		};
		content.onmousemove = function(ev) {
			var ev = ev || event;
			inform.style.left = (ev.clientX - this.offsetLeft + 20) + "px";
			inform.style.top = (ev.clientY - this.offsetTop - 10) + "px";
		};
		content.onmouseout = function(ev) { inform.style.display = "none"; };
		
	}
	
	function toGrid(){
		var width = document.body.clientWidth;
		var heigth = document.body.clientHeight;
        window.open('<%=basePath %>rest/statStoreObjVoManageAction/overProduct', "机台负荷折线图",'height='+heigth+', width='+width);		
	}
	
	
</script>

<body>
	<div id="main"
		style="width: 1165px;height:600px ;margin: 0 auto;background-image: url(<%=basePath%>app/images/room.png);background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;">
		<div class="mainshelf" id="mainshelf"></div>
	</div>
	
	<div id="inform">
				<span class="tb-border"></span>
				<span class="tb-background"></span>
				<iframe id="ifr" style="width:75%;height: 100%;border:0;display: none;" scrolling="no" align="middle"></iframe>
	</div>
</body>

</html>