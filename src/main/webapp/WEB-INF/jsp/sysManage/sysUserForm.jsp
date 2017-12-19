<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
  String sysUser_id = request.getParameter("id");
  String roleList = request.getParameter("roleList");
  String status = request.getParameter("status");
%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sysUserForm</title>
<link
	href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>core/css/public.css" />
<script type="text/javascript"
	src="<%=basePath%>core/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath%>core/js/cssBase.js"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script>
      var basePath  = '<%=basePath%>';
      </script>
    	<style>
    		table{
    			margin:30px auto;
    		}
    		textarea{
    		margin-top:15px ; 
    		margin-bottom:15px;
		resize:none;
    		}
    	</style>
</head>

<body style="padding:0px; margin:0px;">
	<form id="SysUser">
		<div></div>
		<!--   <a href="javascript:history.go(-1)" class="return">返&nbsp;回</a> -->
		<table style="margin:60px auto;">

			<tr class="l-table-edit-tr" style="display:none;">
				<td>：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="ID" readonly="readonly"></input>
					</div>
				</td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">账号：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="account" name="account" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">组织：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="dept" name="dept" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">用户：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="name" name="name" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">密码：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="password" name="password" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">状态：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
					<input id="status" name="status" type="hidden" ></input>  
					<input id="statuss" name="statuss" readonly="readonly" ></input>  
					</div>
					
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">维护人：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="createBy" name="createBy" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
			</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">维护时间：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="createDate" name="createDate" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
				<!-- <td align="right" class="l-table-edit-td">更新人：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="updateBy" name="updateBy" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td> -->
			</tr>
			<!-- <tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">更新时间：</td>
				<td align="left" class="l-table-edit-td-input">
					<div class="l-text-readonly">
						<input id="updateDate" name="updateDate" readonly="readonly"></input>
					</div>
				</td>
				<td align="left"></td>
			</tr> -->
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">角色：</td>
				<td align="left" class="l-table-edit-td-input" colspan="4"><textarea
						style="width:478px;" rows="6" class="l-textarea"
						readonly="readonly">${roleList }</textarea>
				</td>
				<td align="left"></td>			
				</tr>
			<tr class="l-table-edit-tr">
				<td align="right" class="l-table-edit-td">备注：</td>
				<td align="left" class="l-table-edit-td-input" colspan="4"><textarea
						style="width:478px;" rows="6" id="remark" name="remark"
						class="l-textarea" readonly="readonly"></textarea>
				</td>
				<td align="left"></td>
			</tr>
		</table>
	</form>
</body>

<script type="text/javascript">

if(document.all){
	 var textArea=$("textarea");
	 for(var i=0;i<textArea.length;i++){
	 textArea[i].style.width="798px";  
	 }
	  
	 
}else{
	 var textArea=$("textarea");
	 for(var i=0;i<textArea.length;i++){
	 textArea[i].style.width="500px";  
	 }
	 
}

  var routeName = "sysUserManage"; 
  var row_id = "";
  row_id = <%=sysUser_id%>;
  
  $(document).ready(function() {
    if (row_id != null) {
      var parm = "id="+row_id;
      pub_initForm(routeName,parm);
    }
  });
 
 
 
 var statuss = "";
  $(document).ready(function() {  
	  var  status = '<%=status%>';
	//  alert(status);
	  if(status != null){
		  if ('C10020001' == status) {
			  statuss = '启用';
			} else {
			  statuss = '禁用';
			}
		  $("#statuss").val(statuss); 
		  return ;
	  }
	  alert("账号状态异常，请联系管理员！！");
	  
	  });
 

   
    </script>
</html>

