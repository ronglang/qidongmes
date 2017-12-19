<%@ page language="java" import="java.util.*,com.xml.workflow.WorkflowService,com.xml.vo.WfNodeTrans,com.xml.vo.WfForkset" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ps_dm=request.getParameter("ps_dm")==null?"":request.getParameter("ps_dm").toString();
String node_id=request.getParameter("node_id")==null?"":request.getParameter("node_id").toString();
String nextNodeSelect="";
List<WfForkset> dataList=null;
List<WfNodeTrans> transList=null;
if(!"".equals(ps_dm) && !"".equals(node_id)){
	WorkflowService service=new WorkflowService();
	nextNodeSelect=service.getNextNodeSelect(ps_dm,node_id);
	dataList=service.getNextNodeByPs_dmNode_id(ps_dm,node_id);
	transList=service.geAllNodeTransByPsdmNodeId(ps_dm,node_id);
}
String haveOldData="no";
if(null!=dataList && dataList.size()>0)
	haveOldData="yes";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>设置fork分支节点流向</title>
      <jsp:include page="../inc_right.jsp"></jsp:include>
<script type="text/javascript">
var s = new Object();   
s.type="";   //设为空不刷新父页面
window.returnValue=s; 
	
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


function addRow(){//添加表格的一行
    doAddRow();
}
//已添加到TABLE中的记录行数
var recordCount=0;

var nextNodeSelect='<%=nextNodeSelect%>';
function doAddRow(){
  oTR=myDataTab.insertRow(myDataTab.rows.length);
  oTR.align ="left";
  tmpNum=oTR.rowIndex;
  
 //描述
  oTD1=oTR.insertCell(0);
  oTD1.innerHTML="<input type='text' name='fork_desc' class=\"easyui-validatebox\" data-options=\"required:true\"/>";//text;
  //oTD1.innerHTML="<input class='easyui-datetimebox' name='birthday' value='3/4/2010 2:3:56' />";
  //$('.easyui-datetimebox').datetimebox();
  oTD1.style.align ="left";
  
  //描述值 
  oTD2=oTR.insertCell(1);
  oTD2.innerHTML="<input type='text' name='fork_desc_val' class=\"easyui-validatebox\" data-options=\"required:true,validType:'integer'\">";
  oTD2.style.align ="left";
 
  //流向节点ID
  oTD3=oTR.insertCell(2);
  oTD3.innerHTML=nextNodeSelect;
  oTD3.style.align ="left";
  
  //操作
  oTD4=oTR.insertCell(3);
  oTD4.innerHTML="<input type='button' value='删除此行' onclick=delRow()>";
  oTD4.style.align ="center";
  recordCount+=1;
  
  //动态添加easyui验证
  $('.easyui-validatebox').validatebox();
}
 
//删除一行
function delRow(){
  //删除一行时，删除xhstr中的保存的此值 
  var objTable= document.getElementById("myDataTab"); 
  var length= objTable.rows.length; 
  var currRowIndex=event.srcElement.parentNode.parentNode.rowIndex;
  document.all.myDataTab.deleteRow();
  recordCount-=1;
}


//更新时限
function save(){
	var status= $('#myform').form('validate'); 
	if(status){
		 var node_id=$('#node_id').val();
	     var ps_dm=$('#ps_dm').val();
	     var formData=$("#myform").serialize();
	     $.ajax({
			url:"wfcz_updateForkSet.action?node_id="+node_id+"&ps_dm="+ps_dm,
			type:'post',
			async:true,
			cache:false,
			timeout: 100000,
			data:formData,
			error:function(){
				alert("服务器繁忙，请稍后再试！");
			},
			success: function(result){
		        if(result=="1"){
		            alert("fork分支节点流向设置成功！");
		            closeAndRefreshWindow();
		         }else{
		             alert("fork分支节点流向设置失败！");
		         }
		     }
		  });	
	}
}

$(function(){
	var haveFlag='<%=haveOldData%>';
	if(haveFlag=="no"){
		doAddRow();
	}
});
</SCRIPT>
  </head>
<body>
<form id="myform" method="post" >
<input type="hidden" id="node_id" value="<%=node_id %>"/>
<input type="hidden" id="ps_dm" value="<%=ps_dm %>"/>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	 <tr height="40px" align="center">
       <td align="center" colspan="5" align="center">
       <a href="#" class="easyui-linkbutton" onclick="addRow()">添加一行</a>
       &nbsp;&nbsp;
       <a href="#" class="easyui-linkbutton" onclick="save()">保存设置</a>
       &nbsp;&nbsp;
       <a href="#" class="easyui-linkbutton" onClick="closeWindow()">关闭返回</a>
       </td>
    </tr>
   </table>
   
<!-- 实际列表 -->
<table id="myDataTab"  border="1" width="100%" style="margin-top:5px">
	<thead>
	 <tr>
	  <th class="title" width="20%">描述</th>
	  <th class="title" width="20%">描述值</th>
	  <th class="title" width="10%">下一节点流向</th>
	  <th class="title" width="10%">操作</th>
	 </tr>
	</thead>
	<tbody>
	<%
	if(null!=dataList && dataList.size()>0){
		for(WfForkset bean:dataList){
			out.print("<tr><td align=left><input type='text' name='fork_desc' value='"+bean.getFkDesc()+"' class=\"easyui-validatebox\" data-options=\"required:true\"/></td>");
			out.print("<td align=left><input type='text' name='fork_desc_val' value='"+bean.getFkDescVal()+"' class=\"easyui-validatebox\" data-options=\"required:true\"/></td>");
			
			out.println("<td align=left><select name=\"fork_to_node_id\" class=\"easyui-validatebox\" data-options=\"required:true\">");
			out.println("<option selected></option>");
			if(null!=transList && transList.size()>0){//wf_node_trans有数据
				for(WfNodeTrans bean2:transList){
						if(bean.getFkToNodeId().equals(bean2.getTsTo())){
							out.println("<option value=\"" +bean2.getTsTo()+ "\" selected>" + bean2.getTsName()+"</option>");
						}else{
							out.println("<option value=\"" +bean2.getTsTo()+ "\">" + bean2.getTsName()+"</option>");
						}
				}
			}
			out.println("</select></td>");
			out.print("<td align=center></td></tr>");
		}
	}
	 %>
	</tbody>
</table>
	</form>
  </body>
</html>