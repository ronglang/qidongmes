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
		<title>mauMachineList</title>
		
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/mauManage/mauMachineList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "mauMachineManage";    
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
			 #sort{
		  		border:none;
	    		margin-left:20px;
	    		background-color: #0099CC;
	    		color:white;
	    		width:80px;
	    		height:25px;
	    		border-radius:5px;
	    		cursor: pointer;
	    		font-size: 13px;
		  	}
		  #sort:hover{
    			background-color: #50C1E8;
	    	}
    	</style>
	</head>
	
	<body style="padding:6px; overflow:hidden;">
		<!-- <label class="l-icon-add " style="margin-left:30px;margin-right:10px;" onclick="addNewRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>添加
		<label class="l-icon-delete" style="margin-left:30px;margin-right:10px;" onclick="deleteRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>删除
		<label class="l-icon-modify" style="margin-left:30px;margin-right:10px;" onclick="updateRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>修改 -->
		<!-- 
		<input id="serchByMacCode" name="serchByMacCode" value="" placeholder="请输入机台编码" style="margin-left:24px;margin-right:10px;width:100px;"/>
		<label class="l-icon-search" onclick="serchByMacCode()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
		 -->
		<div>
			<span style="margin-left:20px;font-size:14px;">机台编码：</span>
			<input id="serchByMacCode" name="serchByMacCode" value="" placeholder="请输入机台编码" style="margin-left:10px;margin-right:10px;width:150px;height:23px;"/>
			<input type="button" name="" id="sort" value="查  询"  onclick="serchByMacCode()"/>
		</div>
		
		<div class="l-clear"></div>
		<!-- 这是展示页的div -->
		<div id="maingrid" style="margin-top:20px"></div><br/><br/>
		<!-- 这是修改页的form -->
		<form id="modifyform" name="modifyform" style="display:none" >
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
		            <td colspan=2><label for="macCode">机台编码:</label></td>
		            <td><input id="macCode" name="macCode" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="macName">机台名称:</label></td>
		            <td><input id="macName" name="macName" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="macState">机台状态:</label></td>
		            <td><input id="macState" name="macState" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="macType">机台类型:</label></td>
		            <td><input id="macType" name="macType" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="ggxhStart">规格型号开始值:</label></td>
		            <td><input id="ggxhStart" name="ggxhStart" type="text"/></td>
		        </tr>
		         <tr>
		            <td colspan=2><label for="ggxhEnd">规格型号结束值:</label></td>
		            <td><input id="ggxhEnd" name="ggxhEnd" type="text"/></td>
		        </tr>
		         <tr>
		            <td colspan=2><label for="macPrio">优先级:</label></td>
		            <td><input id="macPrio" name="macPrio" type="text"/></td>
		        </tr>
		  </table>
		</form>
		<!-- 这是新增页的form -->
		<form id="addform" name="addform" style="display:none" >
		    <table border="1" align="center" id="tb">
		        <tr>
		            <td colspan=2><label for="createDate1">创建时间:</label></td>
		            <td><input id="createDate1" name="createDate1" type="date" style="width:98%"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="createBy1">创建人:</label></td>
		            <td><input id="createBy1" name="createBy1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="macCode1">机台编码:</label></td>
		            <td><input id="macCode1" name="macCode1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="macName1">机台名称:</label></td>
		            <td><input id="macName1" name="macName1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="macState1">机台状态:</label></td>
		            <td><input id="macState1" name="macState1" type="text"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="macType1">机台类型:</label></td>
		            <td><input id="macType1" name="macType1" type="text" style="width:175px"/></td>
		        </tr>
		        <tr>
		            <td colspan=2><label for="ggxhStart1">规格型号开始值:</label></td>
		            <td><input id="ggxhStart1" name="ggxhStart1" type="text"/></td>
		        </tr>
		         <tr>
		            <td colspan=2><label for="ggxhEnd1">规格型号结束值:</label></td>
		            <td><input id="ggxhEnd1" name="ggxhEnd1" type="text"/></td>
		        </tr>
		         <tr>
		            <td colspan=2><label for="macPrio1">优先级:</label></td>
		            <td><input id="macPrio1" name="macPrio1" type="text" style="width:175px"/></td>
		        </tr>
		  </table>
		</form>
	</body>
</html>

