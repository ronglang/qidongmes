<%@ page language="java" import="java.util.*,com.xml.vo.BtUser" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>工作日管理</title>
  <jsp:include page="../inc_right.jsp" flush="true"></jsp:include>
  <script type="text/javascript">
$(function(){

 $('#mytab').datagrid({
    title:'工作日管理',
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
    url:'wfcz_workday_admin.action',
    loadMsg:'数据加载中,请稍后...',   
    //sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    columns:[[
		{title:'工作日年份',field:'wdYear',width:100,align:'left'},
		{field:'opt',title:'操作',width:300,align:'center',
              formatter:function(value,rec){ 
                 // return '<span style="color:red" mce_style="color:red"><a href="javascript:viewData('+rec.wdYear+')" mce_href="#" data="btn_user_delete"><img src="../jslib/jquery-easyui/themes/icons/view.gif" align="absmiddle">查看</a> <a href="javascript:editData('+rec.wdYear+')" mce_href="#" data="btn_user_edit"><img src="../jslib/jquery-easyui/themes/icons/edit.gif" align="absmiddle">修改</a> </span>';  
             return '<span style="color:red" mce_style="color:red"><a href="javascript:viewData('+rec.wdYear+')" mce_href="#" data="btn_user_delete"><img src="../jslib/jquery-easyui/themes/icons/view.gif" align="absmiddle">查看</a>  </span>';  
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
          id:'btn_user_add',
          text:'添加新的年工作日或者重新设置年工作日',
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
		 var params;
		 var style = "dialogWidth:1000px;dialogHeight:800px;status:no";   
		 var k=window.showModalDialog("wfcz_work_day_add.jsp?time="+new Date().getMilliseconds(),params, style);   
		 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
			window.location.reload();
		}
}

//查看
function viewData(wdYear){
   if(wdYear==""){
		alert("请选中一条记录！");
	}else{
		var params;
		var style = "dialogWidth:1000px;dialogHeight:800px;center:yes;resizable:no;status:no;scroll:yes;help:no";   
		var k=window.showModalDialog("wfcz_work_day_view.action?getCurYear="+wdYear+"&time="+new Date().getMilliseconds(),params, style);   
	}
}

//修改
function editData(wdYear){
	if(wdYear==""){
		alert("请选中一条记录！");
	}else{
		 var params;
		 var style = "dialogWidth:1000px;dialogHeight:800px;status:no";   
		 var k=window.showModalDialog("wfcz_work_day_update.action?getCurYear="+wdYear+"&time="+new Date().getMilliseconds(),params, style);   
		 //if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
		//	window.location.reload();
		//}
	}
}
</script>
  </head>
  <body>
	<table id="mytab"></table>
  </body>
</html>