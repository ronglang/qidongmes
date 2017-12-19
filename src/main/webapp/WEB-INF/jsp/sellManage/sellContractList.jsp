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
		<title>合同管理</title>
		
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script src="<%=basePath %>app/js/sellManage/sellContractList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "sellManage";    
    	</script>
    		<style>
			#head{
				/* border-top:2px solid blue;
				border-bottom:2px solid blue; */
				color:#0A754C;
				font-size:14px;
				width:100%;
				padding:10px auto;
		    } 
		    #headtitle{
		    	margin-left:15%;
		    	font-size:40px;
		    	color:blue;
		    }
	
		    #headwsCodediv,#headwsCodediv1{
		    	margin-left:10px;
		     	width:100%;
		     	font-size:30px;
		   		border-bottom:1px solid blue;
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
		  	margin-left:10px;
		  	font-size:18px;
		  }
		  .secondlable{
		  	margin-left:30%;
		  	font-size:18px;
		  }
		  #addNew1,#modifydiv1{
		  	border-bottom:1px solid blue;
		  }
		  #macCode,#macCode1{
		  	width:172px;
		  }
		  #adddiv,#modifydiv{
		  	width:950px;
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
		  .chagetext{
		  	font-size:18px;
		  }
		  #scDate,#scDate1,#scDate2{
		  	width:219px;	
		  }
		  #createDate,#createDate1,#createDate2{
		  	width:219px;
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
	    	#showall{
	    		border:none;
	    		margin-left:20px;
	    		background-color: #FFAB95;
	    		color:white;
	    		width:80px;
	    		height:25px;
	    		border-radius:5px;
	    		cursor: pointer;
	    		font-size: 13px;
	    	}
	    	#showall:hover{
	    		background-color: #F7C6B9;
	    	}
		</style>
	</head>
	<body style="padding:6px; overflow:hidden;">
	<!--
	  -->
		<div id="head">
			<!-- <label class="l-icon-add " style="width:100px;margin-left:10px;" onclick="addNewRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label ><label  class="chagetext" onclick="addNewRow()">添加</label>
			<label class="l-icon-modify" style="width:100px;margin-left:10px;" onclick="updateRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label class="chagetext" onclick="updateRow()">修改</label>
			<label class="l-icon-delete" style="width:120px;margin-left:10px;" onclick="deleteRow()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label class="chagetext" onclick="deleteRow()">删除</label> -->
			<!-- <label class="l-icon-view" style="width:100px;margin-left:10px;" onclick="goToList()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label class="chagetext" onclick="goToList()">详情</label> -->
			<!-- <label class="l-icon-down" style="width:100px;margin-left:10px;" onclick="decomposition()">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label><label class="chagetext" onclick="decomposition()">分解</label> -->
			
		</div>
		<div style="width:100%;height:100%;">
			<div id="serchdiv" style="height:40px;margin-bottom: 20px;">
				<lable>状态：</lable><select id="serchState" name="serchState"><option selected="selected">全部</option><option>未分解</option><option>已分解</option></select>
				<lable>合同编号：</lable><input id="serchscCode" class="inputlength" name="serchscCode"/>
				<lable>客户编号：</lable><input id="serchcusCode" class="inputlength" name="serchcusCode"/>
				<lable>经办人：</lable><input id="serchAgentBy" class="inputlength" name="serchAgentBy"/>
				<input type="button" name="" id="sort" value="查  询"  onclick="ComprehensiveSerch()"/>
				<input type="button" name="" id="showall" value="重  置" onclick="restload()"/>
				<!-- 
				<img id="serchbt" alt="" src="<%=basePath %>app/images/serc.png" onclick="ComprehensiveSerch()">
				<img id="serchbt" alt="" src="<%=basePath %>app/images/res.png" onclick="restload()">
				 -->
			</div>
			<!-- 这是展示页的div -->
			<div style="height:100%;height:100%;overflow-x:hidden;">
				<div id="maingrid" style="height:100%;height:100%;"></div>
			
			</div>
			
			<div style="display:none;height:0px;">
				<!-- 这是新增页的div -->
		    	<div id="adddiv" style="display:none">
		    		<div id="addNew1">
			    		 	<div id="headwsCodediv">
			    		 		合同录入
			    		 	</div>
			    		 	<label class="firstlable" for="scCode">合同编号:<input id="scCode" name="scCode" type="text" /></label>
			    			<label class="secondlable" for="scDate">签订日期:<input id="scDate" name="scDate" type="date" /></label><br/>
						    <label class="firstlable" for="scMoney">合同金额:<input id="scMoney" name="scMoney" type="text" /></label>
							<label class="secondlable" for="cusName">客户名称:<input id="cusName" name="cusName" type="text"/></label><br/>
						    <label class="firstlable" for="cusCode">客户编号:<input id="cusCode" name="cusCode" type="text"/></label>
					       	<label class="secondlable" for="agentBy">经办人员:<input  id="agentBy" name="agentBy" type="text"/></label><br/>
						    <label class="firstlable" for="createDate">录入时间:<input id="createDate" name="createDate" type="date"/></label> 
						    <label class="secondlable" for="createBy">录入人员:<input id="createBy" name="createBy" type="text"/></label> 
		    		</div>
		    		<div>
		    			段长格式要求说明：0.2*2+1.2*3 ,是0.2千米的2根，1.2千米的3根。 *代表乘，+是相加
		    		</div>
		    		<div id="addNew2"></div>
		    	</div>
		    	<!-- 这是修改页的div -->
		    	<div id="modifydiv" style="display:none">
		    		<div id="modifydiv1">
			    		 	<div id="headwsCodediv1">
			    		 	 	合同修改
			    		 	</div>
						    <label class="firstlable" for="scCode1">合同编号:<input id="scCode1" name="scCode1" type="text"/></label> 
			    			<label class="secondlable" for="scDate1">签订日期:<input id="scDate1" name="scDate1" type="date" /></label><br/>
						    <label class="firstlable" for="scMoney1">合同金额:<input id="scMoney1" name="scMoney1" type="text" /></label>
							<label class="secondlable" for="cusName1">客户名称:<input id="cusName1" name="cusName1" type="text"/></label><br/>
						    <label class="firstlable" for="agentBy1">经办人员:<input id="agentBy1" name="agentBy1" type="text"/></label>
					       	<label class="secondlable" for="cusCode1">客户编码:<input id="cusCode1" name="cusCode1" type="text"/></label><br/>
					       	<label class="firstlable" for="createDate1">录入时间:<input id="createDate1" name="createDate1" type="date"/></label>
					       	<label class="secondlable" for="createBy1">录入人员:<input id="createBy1" name="createBy1" type="text"/></label>
		    		</div>
		    		<div id="modifydiv2"></div>
		    	</div>
				<!-- 这是打印详情页的div -->
				<div id="detaildiv" style="display:none;" >
					<div id="headwsCodediv2">
			    		合同详情
			    	</div>
			    	<label class="firstlable" for="scCode2">合同编号:<input id="scCode2" name="scCode2" type="text"/></label> 
		   			<label class="secondlable" for="scDate2">签订日期:<input id="scDate2" name="scDate2" type="date" /></label><br/>
				    <label class="firstlable" for="scMoney2">合同金额:<input id="scMoney2" name="scMoney2" type="text" /></label>
					<label class="secondlable" for="cusName2">客户名称:<input id="cusName2" name="cusName2" type="text"/></label><br/>
				    <label class="firstlable" for="agentBy2">经办人员:<input id="agentBy2" name="agentBy2" type="text"/></label>
			       	<label class="secondlable" for="cusCode2">客户编码:<input id="cusCode2" name="cusCode2" type="text"/></label><br/>
			       	<label class="firstlable" for="createDate2">录入时间:<input id="createDate2" name="createDate2" type="date"/></label>
			       	<label class="secondlable" for="createBy2">录入人员:<input id="createBy2" name="createBy2" type="text"/></label>
			       	<div id="detaildiv2"></div>
		  		</div> 	
			</div>
		</div>
	</body>
</html>

