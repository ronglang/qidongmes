<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
  //String sysRole_id = request.getParameter("id");
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色_权限维护</title>

<!-- 默认引用1 -->
<!-- 加的css文件begin -->
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css"  rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" /> 
<!-- 加的css文件end -->

 <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
 <link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Silvery/css/style.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" /> 
<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<%-- <script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>  --%>

<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysRoleEdit.js" type="text/javascript"> </script>
  	<style type="text/css">  	</style>
<script type="text/javascript">
	  var basePath  = '<%=basePath%>';
	  var routeName = "sysRoleManage";
	  var row_id = "${id}";
</script>
</head>

<body style="margin:0px;padding-top:50px;padding-left:10px;width:99%;">
  <form id="sysRoleManage" method="post">
  	<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin:0px auto;width:100%;">
	      <tr class="l-table-edit-tr">
		        <td align="right" class="l-table-edit-td" style="width:100px;">角色名称：<span style="color: red;">*</span></td>
		        <td align="left" class="l-table-edit-td-input" style="width:180px;">
		        	<input id="name" name="name" type="text" ltype="text" required="required" style="width:100%;"/>
		        </td>
		        <td align="right" class="l-table-edit-td" style="width:100px;">备注：</td>
		        <td align="left"  style="width:250px;">
		        	<input class="inp"  id="remark" name="remark" type="text" style="width:100%;"/>
		        </td>
		        <td align="left" style="width:100px;"></td>
		       <!--  <td>
		        	<div style="float:left;width:49%">
			        <td align="right" class="l-table-edit-td" style="width:100px;">权限设置：<span style="color: red;">*</span></td>
			        <td align="left" class="l-table-edit-td-input" style="width:100%;">
			        	<input id="params" name="params" type="hidden"  />
		        		<input id="IS_REPORTED" name="IS_REPORTED" type="hidden" ztree="hidden" value="" />
		          		<div style="width:181px; height:20px;position:relative;">
				              <textarea style="width:509px; margin-top:10px;" rows="6" id="IS_REPORTEDShow" name="IS_REPORTEDShow" class="l-textarea">
				              </textarea>
				        </div> 
			          </div>
		        </td> -->
	      </tr>
	</table>
</form>
<div style="width:99%;height:100%;width:100%;" id="resourceList"></div>
    <%-- <div style="width:99%;">
    	<div style="float:left;width:49%">
			        <td align="right" class="l-table-edit-td" style="width:100px;">权限设置：<span style="color: red;">*</span></td>
			        <td align="left" class="l-table-edit-td-input" style="width:100%;">
			        	<input id="params" name="params" type="hidden"  />
		        		<input id="IS_REPORTED" name="IS_REPORTED" type="hidden" ztree="hidden" value="" />
		          		<div style="width:181px; height:20px;position:relative;">
				            <input id="IS_REPORTEDShow" name="IS_REPORTEDShow" type="text" 
				              ztree="true" style="margin: 0px;padding-left:4px;  width:166px; height: 19px;  color: rgb(34, 34, 34);" ligerui="{width:177}" /><img
				              src="<%=basePath%>core/img/btn_03.png"
				              style="position:absolute; right:5px; top:2px;"
				              id="IS_REPORTEDBtn" href="#"
				              onclick="showMenu('IS_REPORTED'); return false;" />
				              <!-- onclick="HS(); return false;" />   value="${params}"-->
			          </div>
			        </td>
		        </tr>
		    </table>
    	</div>
    </div> --%>
 
    <div style="margin:0px auto;width:250px; clear:both; overflow:hidden; ">
        <input id="btn_save" type="button" style="float: left; display:inline-block;" class="l-button l-button-submit" onClick="submitSysRole()" value="提交" />
        <%--
        <input style="float: right; display:inline-block;" class="l-button l-button-test" type="reset" value="重置" />
         --%> 
    </div>
  <script>
	
		if(document.all){
			 var textArea=$("textarea");
			 for(var i=0;i<textArea.length;i++){
			 textArea[i].style.width="508px";  
			 }
			 
			 
		}else{
			 var textArea=$("textarea");
			 for(var i=0;i<textArea.length;i++){
			 textArea[i].style.width="505px";  
			 }
			 
		}
		
	</script>
</body>
</html>

