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
		<title>craSeqList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/craManage/craSeqList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "craSeqManage";    
    	</script>
    	<style>
    		 #tb tr td{
				border: 1px solid black;
				height:20px;
				font-size: 15px;
			}
			#tb{
				text-align: center;
			}
			 #tb1 tr td{
				border: 1px solid black;
				height:20px;
				font-size: 15px;
			}
			#tb1{
				text-align: center;
			}
    	</style>
	</head>

	<body style="padding:6px; overflow:hidden;">
		<label class="l-icon-add " style="margin-left:30px;margin-right:10px;" onclick="addNewRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>添加
		<label class="l-icon-delete" style="margin-left:30px;margin-right:10px;" onclick="deleteRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>删除
		<label class="l-icon-modify" style="margin-left:30px;margin-right:10px;" onclick="updateRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>修改
		<input id="serchBySeqCode" name="serchBySeqCode" value="请输入工序编号" style="margin-left:24px;margin-right:10px;width:100px;"/><label class="l-icon-search" onclick="serchBySeqCode()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
		<div class="l-clear"></div>
		<!-- 这是展示页的div -->
		<div id="maingrid" style="margin-top:20px"></div><br/><br/>
		<!-- 这是修改页的form -->
		<form id="editform" name="editform" style="display:none" >
		    <table border="1" align="center" id="tb">
		        <tr >
		            <td colspan=2><label for="id">序号:</label></td>
		            <td><input id="id" name="id" ltype="text" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="createDate">创建时间:</label></td>
		            <td><input id="createDate" name="createDate" type="date" style="width:98%"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="createBy">创建人:</label></td>
		            <td><input id="createBy" name="createBy" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="seqCode">工序编号:</label></td>
		            <td><input id="seqCode" name="seqCode" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="seqName">工序名称:</label></td>
		            <td><input id="seqName" name="seqName" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="seqType">工序类型:</label></td>
		            <td><input id="seqType" name="seqType" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="childCode">子工序的编码:</label></td>
		            <td><input id="childCode" name="childCode" type="text"/></td>
		        </tr>
		  </table>
		</form>
		<!-- 这是新增页的form -->
		<form id="addform" name="addform" style="display:none" >
			 <table border="1" align="center" id="tb1">
		        <tr>
		            <td colspan=2><label for="createDate1">创建时间:</label></td>
		            <td><input id="createDate1" name="createDate1" type="date" style="width:98%"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="createBy1">创建人:</label></td>
		            <td><input id="createBy1" name="createBy1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="seqCode1">工序编号:</label></td>
		            <td><input id="seqCode1" name="seqCode1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="seqName1">工序名称:</label></td>
		            <td><input id="seqName1" name="seqName1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="seqType1">工序类型:</label></td>
		            <td><input id="seqType1" name="seqType1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="childCode1">子工序的编码:</label></td>
		            <td><input id="childCode1" name="childCode1" type="text"/></td>
		        </tr>
		  </table>
		</form>
	</body>
</html>



















