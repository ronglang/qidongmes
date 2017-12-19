<%@ page language="java" import="java.util.*,com.xml.vo.BsDaima,com.xml.service.BsDaimaService" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dm_id=null==request.getParameter("dm_id")?"":request.getParameter("dm_id").toString();
BsDaima bean=null;
BsDaimaService service=null;
if(!"".equals(dm_id)){
	service=new BsDaimaService();
	bean=service.findBsDaimaById(dm_id);
}
request.setAttribute("bean",bean);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title></title>
  <jsp:include page="../inc_right_new.jsp"></jsp:include>
<script type="text/javascript">
var s = new Object();
s.type="";//设为空不刷新父页面
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

var dm_id='<%=dm_id%>';
if(dm_id=="")
  closeWindow();

$(function(){
	//工作流列表
	$('#mytab').datagrid({
	    title:'工作流列表',
	    iconCls:'icon-ok',
	    height:420,
	    pageSize:15,
	    pageList:[10,15,20],
	    nowrap:true,//True 就会把数据显示在一行里
	    striped:true,//奇偶行使用不同背景色
	    collapsible:true,
	    singleSelect:true,//True 就会只允许选中一行
	    fitColumns: true,
	    fit:true,//让DATAGRID自适应其父容器
	    fitColumns:false,//是否禁止出现水平滚动条：True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动
	    pagination:true,//底部显示分页栏
	    rownumbers:true,//是否显示行号
	    url:'../wf_admin.action',
	    loadMsg:'数据加载中,请稍后...',   
	    //sortName:'code',
	   // queryParams:{},
	    sortOrder:'desc',
	    remoteSort:false,
	    idField:'userId',
		frozenColumns:[[
		   {field:'ck',checkbox:true},
		   {title:'人员ID',field:'psId',width:80,align:'center',hidden:'true'}
		]],
	    columns:[[
			 {title:'流程编号',field:'psId',align:'center',hidden:'true',width:60},
			{title:'流程编号',field:'psDm',align:'center',width:160},
			{title:'流程名称',field:'psMc',align:'left',width:250}
		]],
	     onClickRow:function(){//双击事件 查看、修改等操作
	        var selected = $('#mytab').datagrid('getSelected');
				if(selected){
				  //alert("双击"+selected.schoolDm+":"+selected.schoolName);
				  var ps_dm=selected.psDm;
				  var condition={ps_dm:''+ps_dm+''};
				  $("#node_mytab").datagrid('options').queryParams=condition; //把查询条件赋值给datagrid内部变量
				  $("#node_mytab").datagrid('reload'); //重新加载
				}
	     }
	});
	
	//工作流节点列表
	$('#node_mytab').datagrid({
	    title:'节点列表',
	    iconCls:'icon-ok',
	    height:420,
	    pageSize:15,
	    pageList:[10,15,20],
	    nowrap:true,//True 就会把数据显示在一行里
	    striped:true,//奇偶行使用不同背景色
	    collapsible:true,
	    singleSelect:true,//True 就会只允许选中一行
	    fitColumns: true,
	    fit:true,//让DATAGRID自适应其父容器
	    fitColumns:false,//是否禁止出现水平滚动条：True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动
	    pagination:true,//底部显示分页栏
	    rownumbers:true,//是否显示行号
	    url:'../wf_all_node_by_ps_dm.action',
	    loadMsg:'数据加载中,请稍后...',   
	    //sortName:'code',
	   // queryParams:{},
	    sortOrder:'desc',
	    remoteSort:false,
	    idField:'ndId',
		frozenColumns:[[
		   {field:'ck',checkbox:true},
		   {title:'人员ID',field:'ndId',width:80,align:'center',hidden:'true'}
		]],
	    columns:[[
			{title:'节点编号',field:'ndId',align:'center',hidden:'true',width:60},
			{title:'节点名称',field:'nodeName',align:'left',width:160},
			{title:'节点名称拼音字母',field:'nodePyzm',align:'left',width:250}
		]],
	     onClickRow:function(){//双击事件 查看、修改等操作
	        var selected = $('#node_mytab').datagrid('getSelected');
				if(selected){
				  //alert("双击"+selected.schoolDm+":"+selected.schoolName);
				  $('#nodePyzm').val(selected.nodePyzm);
				}
	     }
	});
	
	
	
	
});


//更新
function updateData(){
  var dmDmz=$('#dmDmz').val();
  var dmDmmc=$('#dmDmmc').val();
  var nodePyzm=$('#nodePyzm').val();
  if(dmDmz==""){
     alert('请录入配置节点简写！');
     $('#dmDmz').focus();
     return;
  }
   if(dmDmmc==""){
     alert('请录入配置节点描述！');
     $('#dmDmmc').focus();
     return;
  }
   if(nodePyzm==""){
     alert('请先从左边工作流列表中选择一个工作流，然后在右边节点列表中选择一个节点！');
     return;
  }
   
      var formData=$("#myform").serialize();
      $.ajax({
			url:"wfcz_config_update.action?time="+new Date().getMilliseconds(),
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
	              alert("更新保存成功！");
	              closeAndRefreshWindow();
	           }else if(result=="2"){
	              alert("配置节点简写已经存在，请找用其他简写！");
	           }else{
	               alert("更新保存失败！");
	            }
	        }
	  });   
}
</SCRIPT>
  </head>
<body>
<p align=center><font style="color:red;font-weight:800">更新工作流业务节点配置</font></p>
 <form id="myform" method="post">
  <input type="hidden" id="nodePyzm" name="nodePyzm"/>
  <input type="hidden" id="dm_id" name="dm_id" value="${bean.dmId}"/>
	<table width="100%" height="40px" align="center" border="1" class=bbtable>
	 <tr>
     <td align="left" >
         配置节点简写:<input type="text" id="dmDmz" name="dmDmz" class="bbinput" value="${bean.dmDmz}" style="width:200px;"/>
      </td>
     <td align="left" >
         配置节点描述:<input type="text" id="dmDmmc" name="dmDmmc" class="bbinput" value="${bean.dmDmmc}" style="width:200px;"/>
      </td>
     </tr>
   <tr>
     <td colspan=2 align="center" >
         <a href="#" class="easyui-linkbutton" onClick="updateData()">更新保存业务节点配置</a>&nbsp;&nbsp;&nbsp;
         <a href="#" class="easyui-linkbutton" onClick="window.close()">关闭返回</a>
      </td>
     </tr>
</table>
</form>
<div style="width:49%;height:450px;float:left;">
<table id="mytab" style="width:100%;height:100%"></table>
</div>
<div style="width:49%;height:450px;float:right">
<table id="node_mytab" style="width:100%;height:100%"></table>
</div>
  </body>
</html>