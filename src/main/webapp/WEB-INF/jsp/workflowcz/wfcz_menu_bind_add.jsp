<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String menu_id=request.getParameter("menu_id")==null?"":request.getParameter("menu_id").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>添加</title>
      <jsp:include page="../inc_right.jsp"></jsp:include>
<script type="text/javascript">
var s = new Object();   
s.type="";   //设为空不刷新父页面
window.returnValue=s; 
$(function(){
	var menu_id='<%=menu_id%>';
	
	$('#mytab').datagrid({
	    title:'商品息查询',
	    iconCls:'icon-ok',
	    width:800,
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
	    url:'wfcz_queryAllProcess.action',
	    loadMsg:'数据加载中,请稍后...',   
	    //sortName:'code',
	   // queryParams:{},
	    sortOrder:'desc',
	    remoteSort:false,
	    idField:'psId',
		frozenColumns:[[
		   {field:'ck',checkbox:true},
		   {title:'流程编号',field:'psDm',width:200,align:'center',hidden:'true'}
		]],
	    columns:[[
			{title:'流程ID',field:'psId',width:80,align:'center',hidden:'true'},
			{title:'流程编号',field:'psDm',width:200,align:'center'},
			{title:'流程名称',field:'psMc',width:300,align:'left'},
			{title:'添加人',field:'psTjrxm',width:150,align:'left'},
			{title:'添加日期',field:'psTjsj',width:130,align:'left'}
		]]
	});
});

//查询数据
function queryData(){
	var ps_mc=$('#ps_mc').val();
	//把查询条件拼接成JSON var condition={query_school_dm:'your keywords',id:'your id'};
	var condition={psMc:''+ps_mc+''};
	$("#mytab").datagrid('options').queryParams=condition; //把查询条件赋值给datagrid内部变量
	$("#mytab").datagrid('reload'); //重新加载
}


//获取用户勾选中的行的记录条数
function getSelectionsCount(){
  var rows = $('#mytab').datagrid('getSelections');
  return rows.length;
}

//获取用户勾选中的行ID
function getSelections(){
  //var ids = [];
  var rows = $('#mytab').datagrid('getSelections');
  //for(var i=0;i<rows.length;i++){
  //  ids.push(rows[i].acId);//acId
  //}
  //return ids.join(':');
  return rows[0].psDm;
 
}

//绑定
function bindData(){
 var selectCount=getSelectionsCount();
  if(selectCount==0){
     alert('请选择一个要绑定的流程！');
     return;
  }else if(selectCount>1){
     alert('最多只能选择一个流程！');
     return;
  }else{
     var ps_dm=getSelections();
     var menu_id=$('#bind_menu_id').val();
     $.ajax({
		url:"wfcz_bind.action?menu_id="+menu_id+"&ps_dm="+ps_dm,
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
	         }else if(result=="2"){
	            alert("绑定失败，此流程中定义的有fork分支节点，请先为每个fork分支节点进行设置后再进行此绑定操作！");
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
<p align=center><font style="color:red;font-weight:800">请勾选一个要绑定的流程</font></p>
<input type="hidden" id="bind_menu_id" value="<%=menu_id %>"/>
	<table width="100%" height="40px" align="center" border="1">
   <tr>
         <td>流程名称</td>
         <td align="left" >
         <input type="text" id="ps_mc" name="psMc" maxlength="100"/>
         </td>
         <td align="left" >
         <a href="#" class="easyui-linkbutton" onclick="queryData()">查询数据</a>
         &nbsp;&nbsp;
         <a href="#" class="easyui-linkbutton" onclick="bindData()">绑定流程</a>
         &nbsp;&nbsp;
         <a href="#" class="easyui-linkbutton" onClick="closeWindow()">关闭返回</a>
         </td>
    </tr>
    </table>
    
<hr size="1" style="width:100%;color:#99bbe8">
<div style="width:100%;height:500px;">
<table id="mytab" style="width:95%;height:100%"></table>
</div>
  </body>
</html>