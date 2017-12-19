<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String storeScrapRecord_id = request.getParameter("id");
	//storeScrapRecord_id = storeScrapRecord_id == null ? "":storeScrapRecord_id;
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>storeScrapRecordEdit</title>

<!-- 默认引用1 -->
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeScrapRecordManage";
	var row_id = <%=storeScrapRecord_id%>;
</script>
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"	type="text/javascript"></script>

<!-- 默认引用1end -->
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"	type="text/javascript"></script>
<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"	type="text/javascript"></script>

<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js"	type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js"	type="text/javascript"></script>
			
<script src="<%=basePath%>core/plugin/json.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfupload.js" type="text/javascript">
</script><script src="<%=basePath%>core/plugin/swfupload/swfupload.queue.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/fileprogress.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/swfuploadProperties.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/swfupload/handlers.js" type="text/javascript"></script>

<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/storeManage/storeScrapRecordEdit.js"
	type="text/javascript"></script>
<!-- 默认引用end -->
<script>
var row_id = null;
var uploadurl = "";var uploadurlorigname = "";
function addUploadData(path, origName,saveDataId){
	if (isNullObject(path) && isNullObject(origName)) {
		uploadurl += path + ",";
		uploadurlorigname += origName + ",";
	}
	switch(saveDataId){
		case "saveDataId1":
			$("#examinReportUrl").val(path);//上传成功后给户主照片input框赋值
			$("#examinReportUrlImg").attr('src',basePath+path);//上传成功后给户主照片input框赋值
			break;
	}
}
	function btnPrintClick() {
		window.print();
	}
	var basePath = "<%=basePath %>";
	
	function savePic(){
		//debugger;
		var id = $("#id").val();
		var picUrl = $("#examinReportUrl").val();
		var url = "<%=basePath%>rest/storeScrapRecordManageAction/saveFormAndAttach?";
		
		var data = "id="+id+"&picUrl="+picUrl;
		alert("url:"+url+"\ndata:"+data+"\nid:"+id);
		$.ajax({
			type : "POST",
			async : true,
			url : url,
			data : data,
			dataType : "json",
			success : function(result) {
				
				if(result=="error"){
					alert("保存失败");
				}else{
					alert("保存成功");
				}
			},
			error : function() {
				alert("图表数据加载失败11");
				myChart.hideLoading();
			}
		});

		
	}
</script>
</head>

<body>
	<form id="storeScrapRecordManage" method="post"  >
		<div></div>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
		<input type="hidden" value="${storeScrapRecord.id}" name="id"  id="myid"/>
		<tr class="l-table-edit-tr" style="height:30px;width:100%">
			</tr>
				<tr class="l-table-edit-tr" style="height:30px;width:100%">
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">批次号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="batchCode" name="batchCode" type="text" ltype="text" validate="{required:true,maxlength:32}"  value="${storeScrapRecord.batchCode}"/></td>
				<td align="right" class="l-table-edit-td">RFID号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="rfidCode" name="rfidCode" type="text" ltype="text" validate="{required:true,maxlength:32}" value="${storeScrapRecord.rfidCode}" /></td>
				 <td align="right" class="l-table-edit-td">材料：</td> 
				<td align="left" class="l-table-edit-td-input">
					<div id="materialNameSelect" >
					</div>
					</td>
				
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">颜色：</td>
				<td align="left" class="l-table-edit-td-input"><input id="color"
					name="color" type="text" ltype="text"  validate="{required:true,maxlength:32}" value="${storeScrapRecord.color}"/>
					</td>
				<td align="right" class="l-table-edit-td">数量：</td>
				<td align="left" class="l-table-edit-td-input"><input id="amount"
					name="amount" type="text" ltype="text" validate="{required:true,maxlength:32}"   value="${storeScrapRecord.amount}"/></td>
					
			<td align="right" class="l-table-edit-td">单位：</td>
				<td align="left" class="l-table-edit-td-input"><!-- <input id="unit"
					name="unit" type="text" ltype="text" validate="{required:true,maxlength:32}" /> -->
					<select name="unit" >
					<option >--请选择--</option>
					<option value="m" <c:if test="${storeScrapRecord.unit=='m' }">selected</c:if>>m</option>
					<option value="kg" <c:if test="${storeScrapRecord.unit=='kg' }">selected</c:if>>kg</option>
					<option value="km" <c:if test="${storeScrapRecord.unit=='km' }">selected</c:if>>km</option>
					<option value="T" <c:if test="${storeScrapRecord.unit=='T' }">selected</c:if>>T</option>
					</select>
					</td>
				
					
			</tr>
			<tr class="l-table-edit-tr">
			<td align="right" class="l-table-edit-td">型号：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="model" name="model" type="text" ltype="text"  validate="{required:true,maxlength:32}" value="${storeScrapRecord.model}"/></td>
				<td align="right" class="l-table-edit-td">处理人：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="handler" name="handler" type="text" ltype="text"  validate="{required:true,maxlength:32}" value="${storeScrapRecord.handler}"/></td>
		
			<td align="right" class="l-table-edit-td">申请时间：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="applyDate" name="applyDate" type="text" ltype="date"  validate="{required:false,maxlength:32}" value="${storeScrapRecord.applyDate}"/></td>
			</tr>
			
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">物料位置：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="materialRalation" name="materialRalation" type="text" ltype="text"  validate="{required:true,maxlength:32}" value="${storeScrapRecord.materialRalation}"/></td>
			
				<td align="right" class="l-table-edit-td">存放位置：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="handleAfterPosition" name="handleAfterPosition" type="text" ltype="text" validate="{required:true,maxlength:32}" value="${storeScrapRecord.handleAfterPosition}"/></td>
				<td align="right" class="l-table-edit-td">备注：</td>
				<td align="left" class="l-table-edit-td-input">
				<input
					id="remark"  name="remark" type="text" ltype="text" validate="{required:true,maxlength:64}"  value="${storeScrapRecord.remark}"/>
					</td>
			</tr>
			
		</table>
	<br>
	<br>
	<br>
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">
					<input id="id" name="id" type="hidden" ltype="text" />
					<input id="examinReportUrl" name="examinReportUrl" type="hidden" ltype="text" />
					<img id="examinReportUrlpic" alt="请上传机台照片" title="请上传机台照片" src="<%=basePath %>${examinReportUrl}" style="width:165px;height:80%;text-align:center;border:none;">
					<div class="swfdiv" >
						<div id="uploadurlShow1" style="overflow: hidden;" style="float:left;"></div>
						<div style="padding-left: 20px;" style="float:left;">
							<span id="spanButtonPlaceholder1"></span>
								<input id="btnCancel1" type="hidden"  value="取消上传" onclick="cancelQueue(upload1);"
								disabled="disabled"
								style="margin-left: 2px; height: 22px; font-size: 8pt;" />
						</div>
						<div id="fsUploadProgress1" style="width:50px" style="float:left;">
							<input type="hidden" id="saveDataId1" name="saveDataId1"  />
						</div>
					</div>
				</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		<br><br>
			<input style="margin-right: 250px;" class="l-button l-button-submit" type="submit" value="确定" /> 
			<input style="margin-right: 50px;" class="l-button l-button-test"  type="reset" value="重置" />
	</form >
	
</body>
</html>

