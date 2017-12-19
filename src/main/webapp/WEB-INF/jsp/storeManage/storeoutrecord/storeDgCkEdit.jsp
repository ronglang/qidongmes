<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String storeOutOfStorageRecord_id = request.getParameter("id");
	//storeScrapRecord_id = storeScrapRecord_id == null ? "":storeScrapRecord_id;
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>storeOutOfStorageRecordEdit</title>

<!-- 默认引用1 -->
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/plugin/swfupload/css/default.css"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
	var basePath  = '<%=basePath%>';
	var routeName = "storeOutOfStorageRecordManage";
	var row_id = <%=storeOutOfStorageRecord_id%>;
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


<!-- 默认引用 -->
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/storeManage/storeOutOfStorageRecordEdit.js"  type="text/javascript"></script>
	<style type="text/css">
	
	#mytable{
	 text-align: center;
	 margin-top: 60px;
	}
	#button-a{
	  margin-right: 260px;
	  margin-top:50px;
	}
	.l-table-edit-tr{
	height: 40px;
	width:100%;
	}
	.l-table-edit-td{
	padding-left: 100px;
	padding-top: 40px;
	}
	.l-table-edit-td-input{
	padding-top: 40px;
	}
	select{
	  width:180px;
	  height: 25px;
	}
	</style>
<!-- 默认引用end -->
<script>
var row_id = null;
var uploadurl = "";var uploadurlorigname = "";

/* $(function(){
	var optionAll=$(".optionAll");

		$($(optionAll).text).on("change",function(){
			var objGgxh =$("#objGgxh");
			  var pickListCode=$("#pickListCode").find("option:selected").val();
			  $.ajax({
			       cache: true,
			       type: "POST",
			       url:basePath + "rest/" + routeName + "Action/getPickListCode?pickListCode="+pickListCode,
			       async: false,
			       dataType:"json",
			       error: function(request) {
			       },
			       success: function(map) {
			    	   debugger;
			    	   	var matels=[];
			        	 var obj=map.pgxh;
			        	for (var int = 0; int < obj.length; int++) {
			        		var matel = new Object();
			        		matel.id=obj[int];
			        		matel.text=obj[int];
			        		matels.push(matel);
			      		 }
			        	var selectMsg="<select>";
			        	for (var i = 0; i< matels.length; i++) {
			        		selectMsg+="<option   value='"+matels[i].text+"'>"+matels[i].id+"</option>";
						}
			        	selectMsg+="</select>";
			        	objGgxh.remove();
			        	objGgxh.append(selectMsg);
			       }
				
			   });
		});
			
		
	
}); */
   
</script>

<script type="text/javascript">
	$(function(){
		queryFirst();
	});
	
	function queryFirst(){
	
		
		$.post(basePath + "rest/" + routeName + "Action/getPickListCode",{},function(data){
			var html="<select onchange='querySec()'> <option>--请选择--</option>";
			var html1="<select> <option>--请选择--</option></select>";
			//alert(data.pickListCode);
			for(var i = 0;i<data.pickListCode.length;i++){
				html +="<option value='"+data.pickListCode[i]+"' >"+data.pickListCode[i]+"</option>";
			}
			html+="</select>";
			$("#first").append(html);
			$("#se").append(html1);
		},"json");
	}
	
	function querySec(){
			//$("#se").empty(); 
		$("#se").find("select").remove(); //删除Select中索引值为0的Option(第一个) 
		var code = $("#first option:selected").val();
		
		$.post(basePath + "rest/" + routeName + "Action/getGgxh",{pickListCode:code},function(data){
			var html="<select> <option>--请选择--</option>";
			for(var i = 0;i<data.pgxh.length;i++){
				html +="<option >"+data.pgxh[i]+"</option>";
			}
			html+="</select>";
			$("#se").append(html);
		},"json");
	}
	
</script>
</head>

<body>
	<form id="storeOutOfStorageRecordManage" method="post"  >
		<div id="mytable">
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr class="l-table-edit-tr" >
				<td align="right" class="l-table-edit-td">领料单号:</td>
				<td align="left" class="l-table-edit-td-input" ><%-- <input
					id="pickListCode" name="pickListCode" type="text" ltype="text" validate="{required:true,maxlength:32}" value="${obj.pickListCode}" />  --%>
						<div  id="first" >
								
						</div>
					</td>
				 <td align="right" class="l-table-edit-td">规格型号：</td>
				<td align="left" class="l-table-edit-td-input"><%-- <input
					id="objGgxh" name="objGgxh" type="text" ltype="text" validate="{required:true,maxlength:32}" value="${obj.objGgxh}"  /> --%>
						<div id="se">
						</div>
					</td>
				
					
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">经办人：</td>
				<td align="left" class="l-table-edit-td-input"><input id="createBy"
					name="createBy" type="text" ltype="text" validate="{required:true,maxlength:32}"   value="${obj.createBy}"/></td>
						<td align="right" class="l-table-edit-td">出库数量：</td>
				<td align="left" class="l-table-edit-td-input"><input
					id="amount" name="amount" type="text" ltype="text"  validate="{required:true,maxlength:32,digits:true}"/></td>
			</tr>
			
		</table>
	
			<div id="button-a">
			<input class="l-button l-button-submit" type="submit" value="确定" /> 
			<input class="l-button l-button-test"  type="reset" value="重置" />
			</div>
			</div> 
	</form >
	
</body>


</html>

