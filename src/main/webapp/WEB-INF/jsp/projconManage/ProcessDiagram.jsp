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
		<title>��������ʾ��ͼ</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--�ҵ� �����ļ� -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath %>app/js/projconManage/ProcessDiagram.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
    	</script>
    	<style>
			body {
				background: #0464D7;
			}
			#headtable {
				text-align: center;
				font-size: 30px;
    			color: #FFFFFF;
    			background: #299CFB;
    			height: 50px;
    			line-height: 50px;
    			width: 100%;
			}
			#headtitle{
				font-size: 34px;
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
			}
			#ulist li{
				font-size: 30px;
				color: #FFFFFF;
				border: 1px solid cyan;
				width: 182px;
				height: 200px;
				text-align: center;
				display:inline-block;
			}
			#ulist li h3{
				font-size: 24px;
			}
			#ulist li img{
				width: 130px;
				height: 130px;
			}
		</style>
  </head>
  <!--
    	���ߣ�zeng
    	ʱ�䣺2017-07-19
    	��������������ͼ��չʾ���еĹ���
    	������˿�����ߡ���Ե�������ס����¡���װ������
   -->
 <body>
		<div id="main">
			<table id="headtable">
				<tr>
					<td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td id="headtitle">��ݸ���������������޹�˾��һ����</td>
					<td id="time"></td>
				</tr>
			</table>
			<div id="content">
				<div id="contentlist">
					<ul id="ulist">
						<li>
							<h3>��˿</h3>
							<img src="<%=basePath %>app/images/projconManage/����ʾ��ͼ.jpg" onclick="JumpToMac('��˿')">
						</li>
						<li>
							<h3>����</h3>
							<img src="<%=basePath %>app/images/projconManage/����ʾ��ͼ.jpg" onclick="JumpToMac('����')"/>
						</li>
						<li>
							<h3>��Ե</h3>
							<img src="<%=basePath %>app/images/projconManage/����ʾ��ͼ.jpg" onclick="JumpToMac('��Ե')"/>
						</li>
						<li>
							<h3>������</h3>
							<img src="<%=basePath %>app/images/projconManage/����ʾ��ͼ.jpg" onclick="JumpToMac('������')"/>
						</li>
						<li>
							<h3>����</h3>
							<img src="<%=basePath %>app/images/projconManage/����ʾ��ͼ.jpg" onclick="JumpToMac('����')"/>
						</li>
						<li>
							<h3>��װ</h3>
							<img src="<%=basePath %>app/images/projconManage/����ʾ��ͼ.jpg" onclick="JumpToMac('��װ')">
						</li>
						<li>
							<h3>����</h3>
							<img src="<%=basePath %>app/images/projconManage/����ʾ��ͼ.jpg" onclick="JumpToMac('����')"/>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>
