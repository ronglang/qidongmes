<%@ page language="java" import="java.util.*,com.xml.vo.BtUser"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	           
   	BtUser v_BtUser=new BtUser();		
   	String v_userType="";//用户类型1系统用户 2虚拟企业用户
   	String v_comDm="";
   	String v_comMc="";
   	if (request.getSession().getAttribute("loginUser") != null) {
   		v_BtUser=(BtUser) request.getSession().getAttribute("loginUser");
   		
   		v_userType = (null==v_BtUser.getUserType()?"":v_BtUser.getUserType().toString());
   		v_comDm=(null==v_BtUser.getComDm()?"":v_BtUser.getComDm().toString());
   		v_comMc=(null==v_BtUser.getComName()?"":v_BtUser.getComName().toString());
   	}
   	String v_mmid=(null==request.getParameter("mmid")?"":request.getParameter("mmid").toString());
	//String v_shenQingKind=(null==request.getParameter("shenQingKind")?"":request.getParameter("shenQingKind").toString());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		
		<title>添加</title>
		<jsp:include page="../inc_right.jsp"></jsp:include>

		
	</head>
	
<script type="text/javascript">	


$(function(){
$('#tabDaibanYewu').datagrid({
    title:'待办业务',
    iconCls:'icon-ok',
    width:800,
    height:500,
    pageSize:20,
    pageList:[20,40,60],
    nowrap:true,//True 就会把数据显示在一行里
    striped:true,//奇偶行使用不同背景色
    collapsible:true,
    singleSelect:true,//True 就会只允许选中一行
    fitColumns: true,
    fit:true,//让DATAGRID自适应其父容器
    fitColumns:false,//是否禁止出现水平滚动条：True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动
    pagination:true,//底部显示分页栏
    rownumbers:true,//是否显示行号
    url:'wfyw_daiBanMainLoadData.action',
    loadMsg:'数据加载中,请稍后...',   
    //sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    columns:[[
		{title:'sqlcId',field:'sqlcId',align:'center',width:60,hidden:true},
		{title:'节点主键',field:'ndId',align:'center',width:60,hidden:true},
		{title:'节点所属的流程代码',field:'psDm',align:'center',width:60,hidden:true},
		{title:'当前流程所在节点',field:'slCurrPostion',align:'center',width:60,hidden:true},
		{title:'节点URL',field:'nodeUrl',align:'center',width:60,hidden:true},
		{title:'公司代码',field:'comDm',align:'center',width:60,hidden:true},
		{title:'申请编号',field:'sqSqbh',align:'center',width:150},
		{title:'公司名称',field:'comMc',align:'center',width:200},
		{title:'操作版本号',field:'osVer',align:'center',width:60,hidden:true},
		{title:'流程名称',field:'psMc',align:'center',width:200},
		{title:'待办事项',field:'nodeName',align:'center',width:200},
		{field:'opt',title:'操作',align:'center',width:260,
              formatter:function(value,rec){   
                  return '<span style="color:blue" mce_style="color:blue">&nbsp;<a href="javascript:caozuo('+rec.ndId+','+rec.sqlcId+','+rec.osVer+',\''+rec.sqSqbh+'\',\''+rec.nodeName+'\',\''+rec.psDm+'\',\''+rec.nodeUrl+'\')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/view.gif" align="absmiddle">'+rec.nodeName+'</a></span>';  
             }   
        } 		
	]],
     //工具栏
     toolbar:['-',{
          id:'btn_refresh',
          text:'刷新',
          iconCls:'icon-refresh',
          handler:function(){
             $('#tabDaibanYewu').datagrid('reload');
           }
        }]

   });  
   
}); //////////////////////////////////////////////////////////////////////



//
function caozuo(v_ndId,v_sqlcId,v_osVer,v_sqSqbh,v_nodeName,v_psDm,v_nodeUrl){
	
  if (v_sqlcId==null || v_sqlcId==""){
	  alert("请选择要操作的记录");
	  return;
  }
  
  if (v_ndId!=null && v_ndId==""){
	  alert("数据有问题，节点ID为空了");
	  return;
  }
  
  if (v_osVer!=null && v_osVer==""){
	  alert("数据有问题，操作版本号为空了");
	  return;
  }
  
 var cfmMsg= "确定要【"+v_nodeName+"】申请编号为【"+v_sqSqbh+"】的记录吗?";

  //var v_sqSqbhtemp=encodeURIComponent(encodeURIComponent(v_sqSqbh));
<%--  window.location.href=v_nodeUrl+"?sqlcId="+v_sqlcId+"&osVer="+v_osVer+"&sqSqbh="+v_sqSqbh+"&time="+new Date().getMilliseconds();  --%>
  var v_width=screen.availWidth-100;
  var v_height=screen.availHeight-60;
  var v_left=50;
  var v_top=30;
  
  var v_sqSqbhtemp=v_sqSqbh;//encodeURI(encodeURI(v_sqSqbh));
  
  var style = "help:no;status:no;dialogWidth:"+v_width+"px;dialogHeight:"+v_height+"px;dialogLeft:"+v_left+";dialogTop:"+v_top+"center:yes;edge:Raised";   
  var v_url="wfyw_daibanChuli.jsp?ndId="+v_ndId+"&sqlcId="+v_sqlcId+"&osVer="+v_osVer+"&sqSqbh="+v_sqSqbhtemp+"&psDm="+v_psDm+"&time="+new Date().getMilliseconds();
  v_nodeUrl='<%=basePath%>yewu/spsc/spsc_clhz_admin.jsp';
  alert(v_nodeUrl);
  if (v_nodeUrl!=null && v_nodeUrl!=""){
	  v_url=v_nodeUrl+"?ndId="+v_ndId+"&sqlcId="+v_sqlcId+"&osVer="+v_osVer+"&sqSqbh="+v_sqSqbhtemp+"&psDm="+v_psDm+"&time="+new Date().getMilliseconds();
	  window.location.href=v_url;
  }else{
	  var k=window.showModalDialog(encodeURI(encodeURI(v_url)),obj, style);  
	  //if (k.daibanResult='refresh'){
		  $('#tabDaibanYewu').datagrid('reload');
	  //}
  }


  
}




function chakan(v_sqSqbh){
  if (v_sqSqbh==null || v_sqSqbh==""){
	  alert("请选择要查看的记录");
	  return false;
  }
	  
  $("#addBtn").linkbutton('disable');
  var v_czlx="view";//操作类型    查看
  var v_sqSqbhtemp=encodeURIComponent(encodeURIComponent(v_sqSqbh));
  window.location.href="spscbufa_bufaView.action?czlx="+v_czlx+"&sqSqbh="+v_sqSqbhtemp+"&time="+new Date().getMilliseconds();
}



</SCRIPT>
    
	<script language="javascript" for="document" event="onkeydown">
if (event.keyCode == 13 && event.srcElement.type != 'button'
		&& event.srcElement.type != 'submit'
		&& event.srcElement.type != 'reset'
		&& event.srcElement.type != 'textarea' && event.srcElement.type != '') {
	event.keyCode = 9;
}
</script>
	<body >
		<div class="content_wrap" style="width:100%;height:90%">
		<table id="tabDaibanYewu" style="width:100%;"></table>
		</div>
		<br/>
		<div class="content_wrap" style="width:100%;height:10%" align="center">
		</div>		
	</body>
</html>