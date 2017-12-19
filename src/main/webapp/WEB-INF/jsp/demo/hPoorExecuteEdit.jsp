<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String hPoorExecute_id = request.getParameter("id");
	String idNumber = request.getParameter("idNumber");
	String dataYear = request.getParameter("dataYear");
%>
<%@ include file="/inf.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分年实施</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css"	rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<%-- <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-grid.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-form.css" rel="stylesheet" type="text/css" /> --%>
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "hPoorExecuteManage";
	var row_id = "";
	row_id =<%=hPoorExecute_id%>	;
	var idNumber = '<%=idNumber %>';
	var dataYear = '<%=dataYear %>';
</script>
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<%-- <!-- 菜单样式  start-->
 <link rel="stylesheet" type="text/css" href="<%=basePath%>/app/css/index.css">
 <link rel="stylesheet" type="text/css" href="<%=basePath%>/app/css/style.css">
 <!-- 菜单样式  end-->
<jsp:include page="../head.jsp"></jsp:include> --%>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/hManage/hPoorExecuteEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->
<style>
	/* form table{margin:40px auto;}
	form input{height:100%;width:80px;border:none;outline:none;}
	form td{width:70px;height:30px;padding:5px;line-height:16px;border:1px solid #aaa;} */
	form .title_top{text-align:center;font-size:18px;border:none;color:#000;font-family:"微软雅黑"}
	.ta{width:100%;}
	textarea{border:none;outline-style:none;}
</style>
<style type="text/css">
	.bfpart1
	{
		border:1px solid #333333;
	}
	.bfpart1 td
	{
		border:0px solid #333333;
		font-size:13px;
		height:35px;
		background-color:white;
	}
	.l-table-edit {
	    border:1px solid #9a9a9a;
	    width:99%;
	    table-layout:fixed;
	}
	.l-table-edit2 {
	    border:0px solid #9a9a9a;
	    width:99%;
	    table-layout:fixed;
	}
	.l-table-edit-td{
		border-bottom: 1px solid #6b6b6b;
		text-overflow: ellipsis;
		word-break: keep-all;
		overflow: hidden;
		color: #6b6b6b;
	}
	.table-edi {
		width: 99%;
	}
	.tdnowrap{
		text-overflow:ellipsis; 
		word-break:keep-all; 
		overflow:hidden;
		display:inline;
	}
	.tdcenter{
		align:center;
		text-align:center;
	}
	.ta{width:100%;}
</style>
</head>

<body style="padding-left:6px;width:99%;">
    <br>
	<h3 style="font-size:28px; font-weight:700; float: left;">分年实施</h3>
	<img src="<%=basePath%>app/images/line.png" style="height:1px; width:84%;padding-top:13px; padding-left:10px;  background-repeat: repeat-x; float:left; "></img>
	<p style="clear:both;"></p> 
	<!-- <hr> -->
	<br> 

    <button id="109003002_2_btn_add" type="button" class="btn-add"  onClick="javascript:scrollIntoView('hPoorExecuteManage');"></button>
		<!-- <button type="button" id="btn_modify" class="btn-mod" >修改</button> -->
		<button id="109003002_2_btn_delete" type="button" onclick="pub_del_execute(routeName)" class="btn-del" ></button>
		<div style="width:100%;height:100%;overflow:hidden;">
			<div id="hPoorExecuteManageList" style="margin:0; padding:0;height:200px;overflow:hidden;"></div>
		</div>
  	<div style="display:none;">
    </div>
<div id="div_input" style="width:99%;height:100%;display:none;">
		<form id="hPoorExecuteManage" method="post">
				<input type="hidden" id="id" name="id"/>
				<input type="hidden" id="buildYear" name="buildYear"/>
				<!-- <input type="hidden" id="endYear" name="endYear"/> -->
				<input id="idNumber" name="idNumber" type="hidden" value="<%=idNumber %>" />
				<input id="dataYear" name="dataYear" type="hidden" value="<%=dataYear %>" />
				<input id="mulltiYear" name="mulltiYear" type="hidden"/>
				<input type="hidden" id="idNumbers" name="idNumbers"/>
				<div></div>
				
				<br>
				<table cellpadding="0" cellspacing="0" class="l-table-edit" style="width:99%">
					<tr class="table-edit-tr">
						<td style="align:left;text-align:left;" class="table-edit-td">
							分年实施编辑
						</td>
					</tr>
					<tr>
						<td style="align:left;text-align:left;" class="table-edit-td">
							<table  cellpadding="0px" cellspacing="0px" class="l-table-edit2" >
								<tr >
									<td align="right" class="table-edit-label" height="35">实施年度：</td>
									<td align="left" class="table-edit-label">
										<!-- <input id="filingYear" name="filingYear" type="text" validate="{required:true}" /> -->
										<input id="filingYear" name="filingYear" type="hidden" ztree="true" value=""/>
										<div style="width:177px; height:22px;position:relative;">
											<input id="filingYearShow" name="filingYearShow" type="text" ztree="true"  validate="{required:true}"
												style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);"/>
												<img src="<%=basePath%>core/img/btn_03.png" style="position:absolute; right:0px; top:2px;"
													 id="filingYearBtn" href="#" onclick="showMenu('filingYear'); return false;"/>
										</div>
									</td>
									<td align="left"></td>
									<!-- <td align="right" class="table-edit-label">项目类型：</td> -->
									<td align="right" class="table-edit-label">五个一批：</td>
									<td align="left" class="table-edit-label">
									    <!-- <input id="proType" name="proType" type="text" readOnly="readOnly" validate="{required:true}"/> -->
									   <!--  <input id="fiveBatch" name="fiveBatch" type="text" validate="{required:true}"/> -->
										<input id="fiveBatch" name="fiveBatch" type="hidden" ztree="true" value=""/>
										<div style="width:177px; height:22px;position:relative;">
											<input id="fiveBatchShow" name="fiveBatchShow" type="text" ztree="true"   validate="{required:true}"
												style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);"/>
												<img src="<%=basePath%>core/img/btn_03.png" style="position:absolute; right:0px; top:2px;"
													 id="fiveBatchBtn" href="#" onclick="showMenu('fiveBatch'); return false;"/>
										</div> 
									</td>
									<td align="left"></td>
									<td id="span_oth1" name="span_oth1"  align="right" class="table-edit-label">其它项目：</td>
									<td id="span_oth2" name="span_oth2"  align="left" class="table-edit-label-input">
										<input id="othProject" name="othProject" type="text" ltype="text"   validate="{required:false}"/>
									</td>
									<td id="span_xm1" name="span_xm1" align="right" class="table-edit-label">项目：</td>
									<td id="span_xm2" name="span_xm2" align="left" class="table-edit-label-input">
										<!-- <input id="project" name="project" type="hidden" value=""/>
										<input id="projectName" name="projectName" type="text"  ltype="text" readOnly="readOnly"  validate="{required:false}"/> -->
										<input id="project" name="project" type="hidden" ztree="true" value=""/>
										<div style="width:177px; height:22px;position:relative;">
											<input id="projectShow" name="projectShow" type="text" ztree="true"  validate="{required:false}"
												style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);"/>
												<img src="<%=basePath%>core/img/btn_03.png" style="position:absolute; right:0px; top:2px;"
													 id="projectBtn" href="#" onclick="showMenu('project'); return false;"/>
										</div>
									</td>
									<td align="left"></td>
									<td align="right" class="table-edit-label">规模：</td>
									<td align="left" class="table-edit-label-input">
										<input id="scale" name="scale" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}" />
									</td>
									<td align="left"></td>
									<td align="right" class="table-edit-label">单位：</td>
									<td align="left" class="table-edit-label-input">
										 <!-- <input id="scaleUnit" name="scaleUnit" type="text"  validate="{required:true}" readOnly="readOnly" /> -->
										<input id="scaleUnit" name="scaleUnit" type="hidden" ztree="true" value="" />
										<div style="width:177px; height:22px;position:relative;">
												<input id="scaleUnitShow" name="scaleUnitShow" type="text" validate="{required:true}"
													ztree="true"
													style="margin: 0px;padding-left:5px; width: 170px; height: 20px;  color: rgb(34, 34, 34);" /><img
													src="<%=basePath%>core/img/btn_03.png"
													style="position:absolute; right:0px; top:2px;" id="scaleUnitBtn"
													href="#" onclick="showMenu('scaleUnit'); return false;" />
											</div>
									</td>
									<td align="left"></td>
								</tr>
					<!-- 	  </table>
						  <table cellpadding="0" cellspacing="0" class="l-table-edit"> -->
								<tr class="l-table-edit-tr">
									<td align="right" class="table-edit-label" height="75">实施措施：</td>
									<td align="left" class="table-edit-label-input" colspan="7" style="width:100%;">
										<textarea class="ta" cols="137" rows="4" id="step" name="step" maxlength="3000" style="border:1px solid gray;" ></textarea>
										<!-- <input id="step" name="step" /> -->
									</td>
									<td align="left"></td>
									<td align="right" class="table-edit-label">录入人：</td>
									<td align="left" class="table-edit-label-input">
										<input id="createBy" name="createBy" type="text" ltype="text" validate="{required:false,maxlength:32}"/>
									</td>
									<td align="left"></td>
									<td align="right" class="table-edit-label">时间：</td>
									<td align="left" class="table-edit-label-input">
										<input id="createDate" name="createDate" type="text" ltype="date" readonly="readonly" />
									</td>
									<td align="left"></td>
								</tr>
						  </table>
						  
								<!-- ------已投入------------- -->
						 <div class="cont1">
							  <h3>计划投入（元）</h3>
							  <table  cellpadding="0" cellspacing="0" class="l-table-edit2 tb2tb bfpart1">		 <!--  style="border:1px solid black;width:100%;" -->
							  		<!-- <tr class="l-table-edit-tr">
							  			<td align="left" class="table-edit-label" colspan="12">
							  				<div class="table-edit-label" style="width:99%;text-align:left; height:40px;valign:bottom;"> 
							  				</div>
							  			</td>
							  		</tr> -->
									<tr class="l-table-edit-tr">
										<!-- <td align="right" class="table-edit-label" rowspan="4" style="width:20px;">已投入</td> -->
										<td align="right" class="table-edit-label2" style="width:30px;height:35px;">总投资：</td>
										<td align="right" class="table-edit-label" style="width:30px;"><span style="color:red">*</span>计划数：</td>
										<td align="left" class="table-edit-label-input" style="width:120px;">
											<input id="totlePlan" name="totlePlan" type="text" ltype="text" readOnly="readonly"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left" style="width:5px;"></td>
										<td align="right" class="table-edit-label2" rowspan="1" style="width:30px;">政府补贴：</td>
										<td align="right" class="table-edit-label" style="width:30px;"><span style="color:red">*</span>计划数：</td>
										<td align="left" class="table-edit-label-input" style="width:120px;">
											<input id="govPlan" name="govPlan" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left" style="width:5px;"></td>
										<td align="right" class="table-edit-label2" rowspan="1"  style="width:30px;">社会帮扶：</td>
										<td align="right" class="table-edit-label" style="width:30px;"><span style="color:red">*</span>计划数：</td>
										<td align="left" class="table-edit-label-input" style="width:120px;">
											<input id="societyPlan" name="societyPlan" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left" style="width:5px;">
											<input id="totleComplete" name="totleComplete" type="hidden" ltype="text" />
											<input id="govComplete" name="govComplete" type="hidden" ltype="text" />
											<input id="societyComplete" name="societyComplete" type="hidden" ltype="text" />
										</td>
									</tr>
									<!-- <tr class="l-table-edit-tr">
										<td align="right" class="table-edit-label" height="35"><span style="color:red">*</span>完成数：</td>
										<td align="left" class="table-edit-label-input">
											<input id="totleComplete" name="totleComplete" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left"></td>
										<td align="right" class="table-edit-label"><span style="color:red">*</span>完成数：</td>
										<td align="left" class="table-edit-label-input">
											<input id="govComplete" name="govComplete" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left"></td>
										<td align="right" class="table-edit-label"><span style="color:red">*</span>完成数：</td>
										<td align="left" class="table-edit-label-input">
											<input id="societyComplete" name="societyComplete" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left"><span style="color:red">*</span></td>
									</tr> -->
									<tr class="l-table-edit-tr" height="35">
										<td align="right" class="table-edit-label2" colspan="4"></td>
										<td align="right" class="table-edit-label2" rowspan="1">扶贫信贷：</td>
										<td align="right" class="table-edit-label"><span style="color:red">*</span>计划数：</td>
										<td align="left" class="table-edit-label-input">
											<input id="creditPlan" name="creditPlan" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left"></td>
										<td align="right" class="table-edit-label2" rowspan="1">农户自筹：</td>
										<td align="right" class="table-edit-label"><span style="color:red">*</span>计划数：</td>
										<td align="left" class="table-edit-label-input">
											<input id="farmerPlan" name="farmerPlan" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left">
											<input id="creditComplete" name="creditComplete" type="hidden" ltype="text" />
											<input id="farmerComplete" name="farmerComplete" type="hidden" ltype="text" />
											<input id="incomeComplete" name="incomeComplete" type="hidden" ltype="text" />
										</td>
									</tr>
									<!-- <tr class="l-table-edit-tr">
										<td align="right" class="table-edit-label" height="35"><span style="color:red">*</span>完成数：</td>
										<td align="left" class="table-edit-label-input">
											<input id="creditComplete" name="creditComplete" type="text" ltype="text" validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left"></td>
										<td align="right" class="table-edit-label"><span style="color:red">*</span>完成数：</td>
										<td align="left" class="table-edit-label-input">
											<input id="farmerComplete" name="farmerComplete" type="text" ltype="text" validate="{required:true,number:true,maxlength:18}"/>
										</td>
										<td align="left"></td>
										<td align="left" colspan="4">&nbsp;</td>
									</tr> -->
								<tr>
									<td colspan="12" style="text-align:left;align:left;"><h3 class="tdnowrap">预计收入（元）</h3></td>
								</tr>
								<tr>
									<td align="right" class="table-edit-label"  >&nbsp;</td>
									<td align="right" class="table-edit-label" ><span style="color:red">*</span>计划数：</td>
									<td align="left" class="table-edit-label-input">
										<input id="incomePlan" name="incomePlan" type="text" ltype="text"  validate="{required:true,number:true,maxlength:18}"/>
									</td>
									<td align="left" colspan="9" >&nbsp;</td>
									<!-- <td align="left"></td>
									<td align="right" class="table-edit-label"><span style="color:red">*</span>完成数：</td>
									<td align="left" class="table-edit-label-input">
										<input id="incomeComplete" name="incomeComplete" type="text" ltype="text" validate="{required:true,number:true,maxlength:18}"/>
									</td>
									<td align="left" colspan="5"></td> -->
								</tr>
						     	</table>
						   <!--  <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收入（元</h3>
							<table cellpadding="0" cellspacing="0" class="l-table-edit2" style="width:99%;margin-top:10px;">
							</table> -->
						 </div>
						</td>
					</tr>
				</table>
				
			</form>
		<br>
		<!-- <br>
		<h3 style="font-size:24px; font-weight:700; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;五个一批:</h3> -->
		<p style="clear:both;"></p> 
	<!-- 	<hr> -->
		
		<table class="l-table-edit" style="width:99%;"> 
			<tr class="table-edit-tr">
				<td style="align:left;text-align:left;"  class="table-edit-td">
				   <!--  <button type="button" id="add" class="btn-add" onClick="javascript:scrollIntoView('hPoorPlanManage');"></button> -->
				    <button id="109003002_2_btn_proper_delete" type="button" onclick="pub_del3('bPoorProperManage')" class="btn-del" ></button>
				    <span >（请选择该“五个一批”落实到的成员）</span>
				</td>
			</tr>	
			<!-- <tr>
				<td class="tdcenter">
					<table cellpadding="0" cellspacing="0" border="0" class="tdcenter" width="100%;">
						<tr class="l-table-edit-tr">
							<td  style="align:center;text-align:center;"  class="table-edit-td tdcenter">
								<div style="width:100%;height:100%;overflow:hidden;border:0px;">
									<div id="bPoorProperManageList" style="margin:0; padding:0;height:200px;overflow:hidden;"></div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr> -->
		</table>
		<div style="width:99%;height:100%;overflow:hidden;border:0px;">
			<div id="bPoorProperManageList" style="margin:0; padding:0;height:200px;overflow:hidden;"></div>
		</div>
		
		<form id="bPoorProperManageForm" method="post">	
			<br>
			<div style="margin:auto;align:center;text-align:center;"><input id="109003002_2_btn_save" class="l-button" type="button" value="保存" /></div>
		</form>
	</div>
</body>
</html>

