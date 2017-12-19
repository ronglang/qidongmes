<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
  String sysUser_id = request.getParameter("id");
 // String hPersonId = request.getAttribute("hPersonId") == null ? null:request.getAttribute("hPersonId").toString(); 
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysUserEdit</title>
<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
<!-- 默认引用1 -->
<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>core/plugin/swfupload/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
<script>
  var basePath  = '<%=basePath%>';
  var routeName = "sysUserManage";  
  var row_id = "";
  row_id =<%=sysUser_id%>;
 
</script>
<script src="<%=basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>

<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<!-- 默认引用1end -->
<script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<!-- 默认引用 -->
<script src="<%=basePath%>core/plugin/json.js" type="text/javascript"></script>
<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
<script src="<%=basePath%>app/js/sysManage/sysUserEdit.js" type="text/javascript"></script>
<!-- 默认引用end -->
    	<style>
    		
    		textarea{
    		margin-top:15px ; 
    		margin-bottom:15px;
		resize:none;
    		}
    		
    	table tr {
    	 height: 30px;
    	}	
    	</style>
</head>

<body style="padding:0px; margin:0px;">
  <form id="sysUserManage" style="margin-top:20px;" method="post">
    <div></div>
    
    <table cellpadding="0" cellspacing="0"  style="margin:20px auto;">
      <input type="hidden" id="updateBy" name = "updateBy"/>
      <input type="hidden" id="createBy" name = "createBy"/>
      <input type="hidden" id="createDate" name = "createDate"/>
      <input type="hidden" id="updateDate" name = "updateDate"/>
      <input type="hidden" id="status" name = "status"/>
      <input type="hidden" id="password" name = "password" />
      <input type="hidden" id="sort" name = "sort"/>
    
      <input type="hidden" id="tel" name = "tel"/>
      <input type="hidden" id="areaCodeName" name = "areaCodeName"/>
	  <input type="hidden" id="changePwDate" name = "changePwDate"/>	      
      <tr >
        <td style="width:100px;"  align="right">账号：<span style="color: red;">*</span></td>
        <td align="left"  style="width:180px;">
        <input id="account" name="account" type="text" ltype="text" required="required" /></td>
         <!-- 
        <td  align="right" style="width:80px;" >人员：</td>
        <td align="left"  style="width:180px;">
        <input id="hPersonId" name="hPersonId" type="hidden" value=""  ztree="true"/>
        <div style="width: 177px; height: 22px; position: relative;">
            <input id="hPersonIdShow" name="hPersonIdShow" type="text"
              ztree="true"
              style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
              src="<%=basePath%>core/img/btn_03.png"
              style="position: absolute; right: 0px; top: 2px;"
              id="personIdBtn" href="#"
              onclick="showMenu('hPersonId'); return false;" />
          </div ></td>
           -->
            <td style="width:100px;"  align="right">用户：<span style="color: red;">*</span></td>
        <td align="left"  style="width:180px;">
        <input id="name" name="name" type="text" ltype="text" required="required" /></td>
        
         <td align="right" style="width:100px;" >角色：</td>
        <td align="left" style="width:180px;">
        <input id="roleId" name="roleId" type="hidden" value="" ztree="true"/>
        <div style="width: 177px; height: 22px; position: relative;">
            <input id="roleIdShow" name="roleIdShow" type="text"
              ztree="true"
              style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
              src="<%=basePath%>core/img/btn_03.png"
              style="position: absolute; right: 0px; top: 2px;"
              id="roleIdBtn" href="#"
              onclick="showMenu('roleId'); return false;" />
        </div></td>
        <td   colspan="3"></td>      
        <!--
        <td align="right" class="l-table-edit-td" style="width:80px;">机构：</td>
        <td align="left" class="l-table-edit-td-input" style="width:180px;">
        <input id="orgCode" name="orgCode" type="hidden" value="" ztree="true"/>
        <div style="width: 177px; height: 22px; position: relative;">
            <input id="orgCodeShow" name="orgCodeShow" type="text" ztree="true"
              style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
              src="<%=basePath%>core/img/btn_03.png"
              style="position: absolute; right: 0px; top: 2px;" id="orgCodeBtn"
              href="#" onclick="showMenu('orgCode'); return false;" />
          </div></td>
          -->      
      </tr>
      
      <tr >
   
        <td align="right"  style="width:80px;" >账户类型：</td>
        <td  align="left"  style="width:180px;">
        <input id="userType" name="userType" type="hidden"  ztree="true"/>
        <div style="width: 177px; height: 22px; position: relative;">
            <input id="userTypeShow" name="userTypeShow" type="text"
              ztree="true"   validate="{required:true}"
              style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
              src="<%=basePath%>core/img/btn_03.png"
              style="position: absolute; right: 0px; top: 2px;"
              id="userTypeBtn" href="#"
              onclick="showMenu('userType'); return false;" />
          </div></td>
          
         <%--  <td align="right" style="width:100px;" >账户区域权限：</td>	      
	       <td align="left"  style="width:180px;">
	        <input id="areaCode" name="areaCode" type="hidden"   ztree="true"/>
	        <div style="width: 177px; height: 22px; position: relative;">
	            <input id="areaCodeShow" name="areaCodeShow" type="text"
	              ztree="true"  required="required"
	              style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
	              src="<%=basePath%>core/img/btn_03.png"
	              style="position: absolute; right: 0px; top: 2px;"
	              id="areaCodeBtn" href="#"
	              onclick="showMenu('areaCode'); return false;" />
	          </div></td> --%>
        <td colspan="6"></td>
        <!--  
        <td align="right" class="l-table-edit-td">账户机构权限：</td>
        <td align="left" class="l-table-edit-td-input">
        <input id="orgCodeAuth" name="orgCodeAuth" type="hidden"  ztree="true"/>
        <div style="width: 177px; height: 22px; position: relative;">
            <input id="orgCodeAuthShow" name="orgCodeAuthShow" type="text"
              ztree="true" 
              style="margin: 0px; padding-left: 4px; width: 170px; height: 19px; color: rgb(34, 34, 34);" /><img
              src="<%=basePath%>core/img/btn_03.png"
              style="position: absolute; right: 0px; top: 2px;"
              id="orgCodeAuthBtn" href="#"
              onclick="showMenu('orgCodeAuth'); return false;" />
          </div></td>
          -->
       
      </tr>
      <!--
      <tr >
	      
	        <td  colspan="6"></td> 
      </tr>
       <tr class="l-table-edit-tr">
        <td align="right" class="l-table-edit-td">排序：</td>
        <td align="left" class="l-table-edit-td-input">
        <input id="sort" name="sort" type="text" ltype="text" /></td>
        <td align="left"></td>
      </tr> -->
      <tr >
        <td align="right" style="width:100px;" >备注：</td>
        <td align="left"  colspan="6">
        <textarea style="width:478px;" rows="6" id="remark" name="remark" class="l-textarea"></textarea></td>
        <td style="width:100px;"></td>
      </tr>
    </table>
    <div style="margin:0px auto;width:250px; clear:both; overflow:hidden; margin-bottom:20px;">
		<input class="l-button l-button-test" type="reset" value="重置" />
		<input class="l-button l-button-submit" type="submit" value="提交" /> 
	</div>
  </form>
  	<script>
	
		if(document.all){
			 var textArea=$("textarea");
			 for(var i=0;i<textArea.length;i++){
			 textArea[i].style.width="833px";  
			 }
			 
			 
		}else{
			 var textArea=$("textarea");
			 for(var i=0;i<textArea.length;i++){
			 textArea[i].style.width="738px";  
			 }
			  
		}
		
	</script>
</body>
</html>

