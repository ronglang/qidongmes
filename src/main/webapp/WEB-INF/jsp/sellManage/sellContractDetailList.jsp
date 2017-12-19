<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id = request.getAttribute("planBatchId") == null ? "" : request.getAttribute("planBatchId").toString();
		
	
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>生产通知单下发</title>
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<!--我的 导入文件 -->
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"	type="text/javascript"></script>
		<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"	type="text/javascript"></script>
		<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"	type="text/javascript"></script>
		
		<script src="<%=basePath %>app/js/sellManage/sellContractDetailList.js" type="text/javascript"></script>
		<script>
			var basePath  = '<%=basePath%>';
			var routeName = "sellContractDetailManage";    
			var row_id = '<%= id %>';
    	</script>
    	<style>
   		#head{
			border-bottom:2px solid blue;
			color:#0A754C;
			width:100%;
	    } 
    	#serch{
    			margin-top:10px;
			    width:100%;
			    font-size:20px;
			    border-bottom:2px solid blue;
		  }
		  .inputlength{
		  	width:100px;
		  }
		  #dowimg{
		  	margin-left:10px;
		  }
		  #headtitle{
		  	margin-left:30%;
		  	font-size:30px;
		  }
		  #rutur{
		  	margin-left:10px;
		  }
    	</style>
	</head>

	<body style="padding:10px;overflow:hidden;">
		<!-- overflow-y:scroll; -->
		<div style="width:100%;height:100%;">
			<div id="head">
				<%-- <label id="rutur"><img src="<%=basePath %>app/images/rutur.png" onclick="returnhome()"></label> --%>
				<%-- <label id="dowimg"><img src="<%=basePath %>app/images/dow.png" onclick="givePOrder()"></label> --%>
				<!-- york start -->
				<!-- <label><button  onclick="beforeSche()" style="margin-left:25px;" >预排产</button></label>
				<label><button  onclick="formalSche()" style="margin-left:25px;" >排产</button></label> -->
				<!-- york end -->
				<label id="headtitle">生产通知单->生成工单情况</label>
			</div>
			<%-- <div id="serch">
				状态：<select id="pbatDetailState" name="pbatDetailState"><option selected="selected">全部</option><option>已生成</option><option>未生成</option></select>
			           批次编码：<input id="batCode" name="batCode" class="inputlength"/>
				合同编码：<input id="scCode" name="scCode" class="inputlength" />
				生产通知单编码：<input id="pbatDetailCode" name="pbatDetailCode" class="inputlength"/>
				<img id="serchbt" alt="" src="<%=basePath %>app/images/serc.png" onclick="ComprehensiveSerch()">
			</div> --%>
			<div class="l-clear"></div> 
			<!-- 这是展示页的div -->
			<div style="width:100%;height:60%;">
				<div id="maingrid" style="margin-top:15px;height:100%;"></div>
			</div>
			<form id="sellContractDetailManage" method="post"> 	  	
				<div style="width:100%;height:200px;border:0px solid blue;">
					<table cellpadding="0" cellspacing="0" class=l-table-edit align="center" style="margin-top: 15px;padding-box:20px;">
						<!-- <tr class=table-edit-td> -->
						<tr class="l-table-edit-tr">
							<td class=table-edit-label>本次生产段长：</td>
							<!-- <td class=table-edit-td> -->
							<td class="table-edit-label-input">
								<input id="proPeriodLength" name="proPeriodLength" type="text" ltype="text"  validate="{required:true,maxlength:200}"/>
							</td>
							<td class=table-edit-label></td>
							<td class=table-edit-label>交货期：</td>
							<td class="l-table-edit-td-input">
								<input id="deliveDate" name="deliveDate" type="text"  ltype='date'  style="width:150px;" readonly="readonly"  />
							</td>
							<td class=table-edit-label >&nbsp;</td>
							<td class=table-edit-label >&nbsp;</td>
							<td>
								 <div style="width:10%; text-align:right;align:right;">
							   		<input  class="l-button" type="button" onClick="addRow_part()" value="生成工单" /> 
							   		<!-- <input id="111005_btn_save" name="111005_btn_save" class="l-button l-button-submit" type="submit" value="增加"  style="color: #FFF;"/> --> 
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>

