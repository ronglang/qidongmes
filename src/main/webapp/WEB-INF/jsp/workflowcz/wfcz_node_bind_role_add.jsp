<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ps_dm=request.getParameter("ps_dm")==null?"":request.getParameter("ps_dm").toString();
String node_id=request.getParameter("node_id")==null?"":request.getParameter("node_id").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>节点绑定权限</title>
      <jsp:include page="../inc_right.jsp"></jsp:include>
<script type="text/javascript">
var s = new Object();   
s.type="";   //设为空不刷新父页面
window.returnValue=s; 

$(function(){
	var node_id='<%=node_id%>';
	var ps_dm='<%=ps_dm%>';
	
	$('#mytab').datagrid({
	    title:'角色权限组列表',
	    iconCls:'icon-ok',
	    width:800,
	    height:420,
	    pageSize:15,
	    pageList:[10,15,20],
	    nowrap:true,//True 就会把数据显示在一行里
	    striped:true,//奇偶行使用不同背景色
	    collapsible:true,
	    singleSelect:false,//True 就会只允许选中一行
	    fitColumns: true,
	    fit:true,//让DATAGRID自适应其父容器
	    fitColumns:false,//是否禁止出现水平滚动条：True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动
	    pagination:true,//底部显示分页栏
	    rownumbers:true,//是否显示行号
	    url:'wfcz_queryAllRoleData.action?node_id='+node_id+'&ps_dm='+ps_dm,
	    loadMsg:'数据加载中,请稍后...',   
	    //sortName:'code',
	   // queryParams:{},
	    sortOrder:'desc',
	    remoteSort:false,
	    idField:'roleId',
		frozenColumns:[[
		   {field:'ck',checkbox:true},
		   {title:'角色编号',field:'roleId',width:200,align:'center',hidden:'true'}
		]],
	    columns:[[
			{title:'角色编号',field:'roleId',width:80,align:'center',hidden:'true'},
			{title:'角色名称',field:'roleName',width:400,align:'left',
				formatter:function(value,rec){
					  return rec.depName+'---'+rec.roleName;
				}
			}
		]]
	});
});


//获取用户勾选中的行的记录条数
function getSelectionsCount(){
  var rows = $('#mytab').datagrid('getSelections');
  return rows.length;
}

//获取用户勾选中的行ID
function getSelections(){
  var ids = [];
  var rows = $('#mytab').datagrid('getSelections');
  for(var i=0;i<rows.length;i++){
    ids.push(rows[i].roleId);//acId
  }
  return ids.join(':');
}

//绑定角色权限
function bindRoleData(){
 var selectCount=getSelectionsCount();
  if(selectCount==0){
     alert('请选择一个要绑定的流程！');
     return;
  }else{
     var role_ids=getSelections();
     var bind_node_id=$('#bind_node_id').val();
     var bind_ps_dm=$('#bind_ps_dm').val();
     $.ajax({
		url:"wfcz_nodeBindRole.action?node_id="+bind_node_id+"&ps_dm="+bind_ps_dm+"&bind_role_ids="+role_ids,
		type:'post',
		async:true,
		cache:false,
		timeout: 100000,
		data:null,
		error:function(){
			alert("服务器繁忙，请稍后再试！");
		},
		success: function(result){
	        if(result=="1"){
	            alert("绑定成功！");
	            closeAndRefreshWindow();
	         }else{
	             alert("绑定失败！");
	         }
	     }
	  });	
  }
}

//关闭并刷新父窗口
function closeAndRefreshWindow(){
	var s=new Object();      
	s.type="ok";//设置返回值为ok就可以。//这里返回刷新父页面。
	window.returnValue=s;   
	window.close();    
} 
  
//关闭并刷新父窗口  
function closeWindow(){
    window.close();
}
</SCRIPT>
  </head>
<body onunload="closeWindow()">
<p align=center><font style="color:red;font-weight:800">请勾选要绑定的角色权限</font></p>
<input type="hidden" id="bind_node_id" value="<%=node_id %>"/>
<input type="hidden" id="bind_ps_dm" value="<%=ps_dm %>"/>
<table width="100%" height="40px" align="center" border="1">
   <tr>
         <td align="left" >
         <a href="#" class="easyui-linkbutton" onclick="bindRoleData()">绑定角色权限</a>
         &nbsp;&nbsp;
         <a href="#" class="easyui-linkbutton" onClick="closeWindow()">关闭返回</a>
         </td>
    </tr>
    </table>
    
<hr size="1" style="width:100%;color:#99bbe8">

<div style="width:98%;height:500px;">
<table id="mytab" style="width:98%;height:100%"></table>
</div>
  </body>
</html>