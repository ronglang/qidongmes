<%@ page language="java" import="java.util.*,com.xml.workflow.WorkflowService,com.xml.vo.WfNode" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ps_dm=request.getParameter("ps_dm")==null?"":request.getParameter("ps_dm").toString();
String node_id=request.getParameter("node_id")==null?"":request.getParameter("node_id").toString();
String now_url="";
Object obj=null;
WfNode bean=null;
if(!"".equals(ps_dm) && !"".equals(node_id)){
	WorkflowService service=new WorkflowService();
	obj=service.findWfNodeByNodeIdPsDm(node_id,ps_dm);
	if(null!=obj){
		bean=(WfNode)obj;
		now_url=null==bean.getNodeUrl()?"":bean.getNodeUrl().toString();
	}
}%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>添加或更新节点办理时限</title>
      <jsp:include page="../inc_right.jsp"></jsp:include>
<script type="text/javascript">
var s = new Object();   
s.type="";   //设为空不刷新父页面
window.returnValue=s; 
	
//更新操作URL
function updateUrl(){
  var node_url=$('#nodeUrl').val();
  if(node_url.legnth==0){
     alert('请输入操作URL！');
     $('#nodeUrl').focus();
     return;
  }else{
     var bind_node_id=$('#bind_node_id').val();
     var bind_ps_dm=$('#bind_ps_dm').val();
     $.ajax({
		url:"wfcz_updateNodeUrl.action?node_id="+bind_node_id+"&ps_dm="+bind_ps_dm+"&node_url="+node_url,
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
	            alert("操作URL保存成功！");
	            closeAndRefreshWindow();
	         }else{
	             alert("操作URL保存失败！");
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
<input type="hidden" id="bind_node_id" value="<%=node_id %>"/>
<input type="hidden" id="bind_ps_dm" value="<%=ps_dm %>"/>
<table width="100%" align="center" border="1">
<tr align="center" height="40px"><td colspan="2"><h2>添加或更新流程节点的操作URL</h2></td></tr>
   <tr height="40px">
   	   <td width="80px">操作URL</td>
       <td align="left">
       <input type="text" id="nodeUrl" style="width:600px" value="<%=now_url %>" class="easyui-validatebox" data-options="required:true"/>
       </td>
   </tr>
  <tr height="40px" align="center">
       <td align="center" colspan="2" align="center">
       <a href="#" class="easyui-linkbutton" onclick="updateUrl()">保存操作URL</a>
       &nbsp;&nbsp;
       <a href="#" class="easyui-linkbutton" onClick="closeWindow()">关闭返回</a>
       </td>
    </tr>
    </table>
  </body>
</html>