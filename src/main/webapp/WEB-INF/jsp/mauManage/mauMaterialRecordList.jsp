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
		<title>mauMaterialRecordList</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath %>app/js/mauManage/mauMaterialRecordList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "mauMaterialRecordManage";    
    	</script>
    	<style>
			#head{
				border-top:2px solid blue;
				border-bottom:2px solid blue;
				color:#0A754C;
				font-size:14px;
				width:100%;
				padding:10px auto;
		    } 
		    #headtitle{
		    	margin-left:16%;
		    	font-size:40px;
		    	color:blue;
		    }
	
		    #headwsCodediv,#headwsCodediv1{
		    	margin-left:10px;
		     	width:100%;
		   		border-bottom:1px solid blue;
		   		font-size:20px;
		   		color:blue;
		    }
		    #wsCodelable,#wsCodelable1{
		    	font-size:20px;
		    }
		    #wsCode,#wsCode1{
		    	border:1px solid blue;
		    	margin-left:13px;
		    	width:175px;	
		    }
		  .firstlable{
		  	margin-top:15px;
		  	font-size:18px;
		  	float: left;
		  }
		  .secondlable{
		 	margin-top:15px;
		  	font-size:18px;
		  	float: right;
		  }
		  #addNew1,#modifydiv1{
		  	border-bottom:1px solid blue;
		  }
		  #macCode,#macCode1{
		  	width:172px;
		  }
		  #adddiv,#modifydiv{
		  	width:750px;
		  }
		  #modifydiv2{
		  	margin-top:10px;
		  }
		  #serchdiv{
		    width:100%;
		    font-size:18px;
		    vertical-align:middle
		   
		  }
		  .inputlength{
		  	margin-top:5px;
		  	width:10%;
		  }
		  #serchbt{
		  	margin-left:10px;
		  }
		  
		  #sort{
				background-color: #0099CC;
				color:white;
				border:none;
				width:80px;
				height:25px;
				cursor: pointer;
				border-radius:5px;
				margin-left:10px;
				font-size:13px;
			}
			#sort:hover{
				background-color: #64C4E5;
			}
			
			#res{
				background-color: #FFAB95;
				color:white;
				border:none;
				width:80px;
				height:25px;
				cursor: pointer;
				border-radius:5px;
				margin-left:10px;
				font-size:13px;
			}
			#res:hover{
				background-color: #F2BFB4;
			}
		  
		</style>

	</head>

	<body style="padding:6px; overflow:hidden;">
	<!-- 
		<div id="head">
			<label class="l-icon-add " style="width:100px;margin-left:10px;" onclick="addNewRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label ><label onclick="addNewRow()">添加</label>
			<label class="l-icon-modify" style="width:100px;margin-left:10px;" onclick="updateRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label onclick="updateRow()">修改</label>
			<label class="l-icon-delete" style="width:120px;margin-left:10px;" onclick="deleteRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label onclick="deleteRow()">删除</label>
			<label class="l-icon-view" style="width:100px;margin-left:10px;" onclick="detailsRowAndPrint()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label onclick="detailsRowAndPrint()">详情</label>
			<label class="l-icon-down" style="width:100px;margin-left:10px;" onclick="startPickging()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label onclick="startPickging()">开始领料</label>
			<label class="l-icon-right" style="width:100px;margin-left:10px;" onclick="endPickging()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label onclick="endPickging()">领料确认</label>
			<label id="headtitle">领料单管理</label>
			<label id="seplit"></label>
		</div>
	 -->
		<div id="serchdiv">
			<lable>状态：</lable><select id="pickingState" name="pickingState"><option selected="selected">全部</option><option>新增</option><option>待出库</option><option>已出库</option></select>
			<lable>工单号：</lable><input id="pickingwsCode" class="inputlength" name="pickingwsCode"/>
			<lable>单据号：</lable><input id="pickingmmrCode" class="inputlength" name="pickingmmrCode"/>
			<lable>机台编码：</lable><input id="pickingmacCode" class="inputlength" name="pickingmacCode"/>
			
			<input type="button" name="" id="sort" onclick="ComprehensiveSerch()" value="查  询" />
			<input type="reset" name="" id="res" onclick="restload()" value="重  置" />
			<!-- 
			<img id="serchbt" alt="" src="<%=basePath %>app/images/serc.png" onclick="ComprehensiveSerch()">
			<img id="serchbt" alt="" src="<%=basePath %>app/images/res.png" onclick="restload()">
			 -->
		</div>
		<!-- 这是展示页的div -->
		<div id="maingrid" style="margin-top:10px"></div><br/><br/>
		<!-- 这是新增页的div -->
    	<div id="adddiv" style="display:none">
    		<div id="addNew1">
	    		 	<div id="headwsCodediv" style="width:100%;">
	    		 		<label for="wsCode" id="wsCodelable">工作单:</label><input id="wsCode" name="wsCode" type="text"/>
	    		 	</div>
	    		 	<div style="width:100%;height:150px;">
	    			<label class="firstlable" for="mmrCode">单据号:<input id="mmrCode" name="mmrCode" type="text" /></label>
				    <label class="secondlable" for="macCode">机台编码:<input id="macCode" name="macCode" type="text" /></label><br/>
				      
					<label class="firstlable" for="matermanageBy">领料人:<input id="matermanageBy" name="matermanageBy" type="text"/></label>
				    <label class="secondlable" for="matermanageTime">领料时间:<input id="matermanageTime" name="matermanageTime" type="date"/></label><br/>
			       
			       	<label class="firstlable" for="createBy">创建人:<input type="text" id="createBy" name="createBy"/></label>
				    <label class="secondlable" for="createDate">创建时间:<input id="createDate" name="createDate" type="date"/></label> 
				    </div>
    		</div>
    		<div id="addNew2"></div>
    	</div>
    	<!-- 这是修改页的div -->
    	<div id="modifydiv" style="display:none">
    		<div id="modifydiv1">
	    		 	<div id="headwsCodediv1">
	    		 		<label for="wsCode1" id="wsCodelable1">工作单:</label><input id="wsCode1" name="wsCode1" type="text"/>
	    		 	</div>
	    		 	<div style="width:100%;height:150px;">
	    			<label class="firstlable"  for="mmrCode1">单据号:<input id="mmrCode1" name="mmrCode1" type="text" /></label>
				    <label class="secondlable" for="macCode1">机台编码:<input id="macCode1" name="macCode1" type="text" /></label><br/>
				      
					<label class="firstlable" for="matermanageBy1">领料人:<input id="matermanageBy1" name="matermanageBy1" type="text"/></label>
				    <label class="secondlable" for="matermanageTime1">领料时间:<input id="matermanageTime1" name="matermanageTime1" type="date"/></label><br/>
			       
			       	<label class="firstlable" for="createBy1">创建人:<input type="text" id="createBy1" name="createBy1"/></label>
				    <label class="secondlable" for="createDate1">创建时间:<input id="createDate1" name="createDate1" type="date"/></label> 
				    </div>
    		</div>
    		<div id="modifydiv2"></div>
    	</div>
    	
		<!-- 这是打印详情页的div -->
		<div id="printdetaildiv" align="center" style="margin-top:0px;display:none;" >
	    	<table id="tb" cellspacing="0" cellpadding="0" style="height: 450px;width: 600px;">
	    		<tr>
	    			<td colspan="8" align="center" style="height:50px;font-weight:bold;font-size:22px;border-width:0 0 3px 0;">东莞启东电线电缆有限公司<br>领料单</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="left" style="border-width:0;">领料部门： ${asdsa}</td>
	    			<td colspan="3" align="left" style="border-width:0;"></td>
	    			<td colspan="3" align="left" style="border-width:0;">单据日期：${asdsa}</td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="left" style="border-width:0 0 1px 0" >备注说明：${asdsa}</td>
	    			<td colspan="3" align="left" style="border-width:0 0 1px 0" >领料用途：</td>
	    			<td colspan="3" align="left" style="border-width:0 0 1px 0" >单据编号：${asdsa}</td>
	    		</tr>
	    		<tr id="firstrow">
	    			<td colspan="1">序号</td>
	    			<td colspan="1" >物料名称</td>
	    			<td colspan="1">型号规格</td>
	    			<td colspan="1">数量</td>
	    			<td colspan="1">单位</td>
	    			<td colspan="1">工作单号</td>
	    		</tr>
	    		<tr>
	    			<td align="left">合计数量：</td>
	    			<td colspan="7"></td>
	    		</tr>
	    		<tr>
	    			<td colspan="3" align="left" style="border-width:0">仓库发料：</td>
	    			<td colspan="2" style="border-width:0">领用人：</td>
	    			<td colspan="3" style="border-width:0">制单：</td>
	    		</tr>
	    		<tr>
	    			<td colspan="8" align="left" style="border-width:0">白色（仓库存根），红色（财务），黄色（车间领料），绿色（车间） </td>
	    		</tr>
	    	</table>
  		</div> 	
	</body>
</html>

