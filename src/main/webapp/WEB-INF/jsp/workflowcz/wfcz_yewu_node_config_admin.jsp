<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title></title>
  <jsp:include page="../inc_right_new.jsp" flush="true"></jsp:include>
  <script type="text/javascript">
$(function(){
 $('#mytab').datagrid({
    title:'工作流节点配置管理',
    iconCls:'icon-ok',
    width:600,
    height:300,
    pageSize:10,
    pageList:[10,15,20],
    nowrap:true,//True 就会把数据显示在一行里
    striped:true,//奇偶行使用不同背景色
    collapsible:true,
    singleSelect:false,//True 就会只允许选中一行;此处要设为FALSE,否则不能多选
    fitColumns: true,
    fit:true,//让DATAGRID自适应其父容器
    fitColumns:false,//是否禁止出现水平滚动条：True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动
    pagination:true,//底部显示分页栏
    rownumbers:true,//是否显示行号
    url:'wfcz_config_admin.action',
    loadMsg:'数据加载中,请稍后...',   
    //sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    idField:'userId',
	frozenColumns:[[
	   //{field:'ck',checkbox:true},
	   //{title:'档案ID',field:'userId',width:80,align:'center',hidden:'true'}
	]],
    
    columns:[[
		{title:'ID',field:'dmId',width:80,align:'center',hidden:'true'},
		{title:'配置节点简写',field:'dmDmz',width:200,align:'left'},
		{title:'配置节点描述',field:'dmDmmc',width:250,align:'left'},
		{title:'绑定节点拼音字母',field:'dmDmmcjx',width:250,align:'left'},
		{field:'opt',title:'操作',width:300,align:'center',
              formatter:function(value,rec){
                  return '<span><a href="javascript:editData(\''+rec.dmId+'\')" mce_href="#" data=""><img src="../jslib/jquery-easyui/themes/icons/edit.gif" align="absmiddle">修改更新</a>&nbsp;&nbsp;  </span>';  
             } 
        } 
	]],
     onDblClickRow:function(){//双击事件 查看、修改等操作
        var selected = $('#mytab').datagrid('getSelected');
			if(selected){
			   //window.open("DataView.action?Id="+selected.ID);
			  //alert("双击"+selected.id+":"+selected.name+":"+selected.age+":"+selected.className);
			  //editData(selected.userId);
			}
     },
     //工具栏
     toolbar:[{
          id:'btnadd',
          text:'添加业务节点配置',
          iconCls:'icon-add',
          handler:function(){
             $('#btnadd').linkbutton('enable');
             addData();
           }
        }]
   });  
});


//添加
function addData(){
	 //window.open("user_add.jsp");
	 var params;
	 var style = "dialogWidth:850px;dialogHeight:600px;status:no";   
	 var k=window.showModalDialog("wfcz_yewu_node_config_add.jsp?time="+new Date().getMilliseconds(),params, style);   
	 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
	 	shuaxin();
	}
}

//更新
function editData(id){
	 if(id==""){
		alert("请选中一条记录！");
	}else{
		 var params;
		 var style = "dialogWidth:850px;dialogHeight:600px;status:no";   
		 var k=window.showModalDialog("wfcz_yewu_node_config_edit.jsp?dm_id="+id+"&time="+new Date().getMilliseconds(),params, style);   
		 if(k.type=="ok")//传递回的type为ok的时候才刷新页面。   
		 	shuaxin();
	}
}

function shuaxin(){
	$('#mytab').datagrid('reload');
}

</SCRIPT>
  </head>
  <body>
		<table id="mytab"></table>
  </body>
</html>