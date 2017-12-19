<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<base href="<%=basePath%>">
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>工序对应机台图</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath %>app/js/projconManage/MachineDiagram.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
    	</script>
    	<style>
			body {
				background: #0464D7;
			}
			#headtable {
				text-align: center;
				font-size: 40px;
    			color: #FFFFFF;
    			background: #299CFB;
    			height: 50px;
    			line-height: 50px;
    			width: 100%;
			}
			#content{
				margin-top: 20px;
				background: #0464D7;
				padding: 20px 100px;
			}
			#time{
				text-align: left;
				font-size:18px ;
			}
			#contentlist{
				border: 1px solid white;
				border-left: 0px;
				border-right: 0px;
			}
			#ulist li{
				color: #FFFFFF;
				border: 1px solid cyan;
				width: 140px;
				height: 200px;
				text-align: center;
				display:inline-block;
			}
			#ulist li img{
				width: 60px;
				height: 60px;
			}
			#contentSumary{
				color:#FFFFFF;
				font-size:28px ;
				width: 100%;
				margin-top:-30px;
			}
			#createButton{
				margin-left: 50px;
			}
			#addButton{
				margin-left: 150px;
			}
			#construcSelec{
				margin-left: 50px;
				background-color: #0464D7;
				color: #FFFFFF;
				font-size:24px ;
			}
			#proName{
				margin-left:10px;	
				font-size:34px ;
			}
			.valtextsize{
				font-size:28px ;
			}
			#ulist li h3{
				margin-top:10px;
				font-size:20px ;
			}
			#ulist li img{
				width: 130px;
				height: 130px;
			}
			
			#tb{
				text-align: center;
			}
			 #tb tr td{
    		 	border-bottom : 1px solid blue;
				height:20px;
				font-size: 15px;
			}
			#tb tr td input{
    		 	border-bottom : 1px solid blue;
				height:20px;
				font-size: 15px;
			}
			.imgbackground{
				background: red;
			}
		</style>
  </head>
  <body>
    	<div id="main">
			<table id="headtable">
				<tr>
					<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td id="proName"></td>
					<td id="time"></td>
				</tr>
			</table>
			<div id="content">
				<div id="contentSumary">
					机台总数：<label id="totalMacVal" class="valtextsize"></label>
					已施工数：<label id="conMacVal" class="valtextsize"></label>
					施工中数：<label id="comMacing" class="valtextsize"></label>
					<select id="construcSelec" class="valtextsize">
						<option selected="selected">---</option>
						<option>施工中</option>
						<option>已施工</option>
						<option>未施工</option>
					</select>
					<img src="<%=basePath %>app/images/projconManage/增加.png" id="addButton" onclick="addMac()"></img>
					<img src="<%=basePath %>app/images/projconManage/修改.png" id="updateButton" onclick="updateMac()"></img>
					<img src="<%=basePath %>app/images/projconManage/删除.png" id="deleteButton" onclick="deleteMac()"></img>
					<img src="<%=basePath %>app/images/projconManage/详情.png" id="detailButton" onclick="detailMac()"></img>
					<img src="<%=basePath %>app/images/projconManage/生成机台.png" id="createButton" onclick="showContentList()"></img>
				</div>
				<div id="contentlist">
					<ul id="ulist">
					</ul>
				</div>
			</div>
		</div>
		<!-- 这是新增页的div -->
		<div id="adddiv" style="display:none">
			<form>
			 	<label for="macCode">机台编码：</label><input id="macCode" name="macCode" type="text" placeholder="工序编码+时间+序号"/><br/><br/><br/>
			 	<label for="macState">机台状态：</label><input id="macState" name="macState" type="text" value="未施工"/><br/><br/><br/>
			 	<label for="processName">工序名称：</label><input id="processName" name="processName" type="text"/><br/><br/><br/>
			</form>
	   </div>
	   <!-- 这是修改页的div -->
		<div id="modifydiv" style="display:none">
			<form>
				<label for="id1">序列号：</label><input id="id1" name="id1" type="text"/><br/><br/><br/>
			 	<label for="macCode1">机台编码：</label><input id="macCode1" name="macCode1" type="text" placeholder="工序编码+时间+序号"/><br/><br/><br/>
			 	<label for="macState1">机台状态：</label><input id="macState1" name="macState1" type="text" value="未施工"/><br/><br/><br/>
			 	<label for="processName1">工序名称：</label><input id="processName1" name="processName1" type="text"/><br/><br/><br/>
			</form>
	   </div>
  </body>
</html>
