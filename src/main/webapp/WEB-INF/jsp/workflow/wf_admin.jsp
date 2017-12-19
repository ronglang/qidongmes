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
	  <jsp:include page="/inf.jsp"></jsp:include>  

<script type="text/javascript">
$(function(){
$('#newstab').datagrid({
    title:'工作流管理',
    iconCls:'icon-ok',
    width:800,
    height:450,
    pageSize:25,
    pageList:[10,25,40],
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
    sortOrder:'desc',
    remoteSort:false,
    columns:[[
        {title:'流程编号',field:'psId',align:'center',hidden:'true',width:60},
		{title:'流程编号',field:'psDm',align:'center',width:160},
		{title:'流程名称',field:'psMc',align:'left',width:250},
		{title:'流程状态',field:'psSfzf',width:60,align:'center',
			formatter:function(value){
				if(value=="Y")
				  return '<font style="color:red">已作废</font>';
				else
				 return '<font style="color:green">正常</font>';
			}
		 },
		{title:'添加人姓名',field:'psTjrxm',align:'left',width:100},
		{title:'添加时间',field:'psTjsj',align:'left',width:90},
		{field:'opt',title:'操作',align:'center',width:200,
              formatter:function(value,rec){ //<a href="javascript:delData('+rec.userId+')" mce_href="#"><img src="../jquery-easyui/themes/icons/delete.gif" align="absmiddle">删除</a>  
                  if(rec.psSfzf=='N'){
                      return '<span style="color:blue" mce_style="color:blue"><a href="javascript:viewData(\''+rec.psMc+'\')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/view.png" align="absmiddle">查看</a> <a href="javascript:editData('+rec.psId+',\''+rec.psMc+'\')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/edit.gif" align="absmiddle">修改</a> <a href="javascript:zuofeiData(\''+rec.psDm+'\',1)" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/zuofei.png" align="absmiddle">作废</a> <a href="javascript:delData(\''+rec.psDm+'\')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/delete.gif" align="absmiddle">删除</a></span>';  
                  }else{
                      return '<span style="color:blue" mce_style="color:blue"><a href="javascript:viewData(\''+rec.psMc+'\')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/view.png" align="absmiddle">查看</a> <a href="javascript:editData('+rec.psId+',\''+rec.psMc+'\')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/edit.gif" align="absmiddle">修改</a> <a href="javascript:zuofeiData(\''+rec.psDm+'\',2)" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/doc2.png" align="absmiddle">取消作废</a>  <a href="javascript:delData(\''+rec.psDm+'\')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/delete.gif" align="absmiddle">删除</a></span>';  
                  }
             }   
        }  
	]],
     onDblClickRow:function(){//双击事件 查看、修改等操作
        var selected = $('#newstab').datagrid('getSelected');
			if(selected){
			   //window.open("DataView.action?Id="+selected.ID);
			  //alert("双击"+selected.id+":"+selected.name+":"+selected.age+":"+selected.className);
			  editData(selected.newsId);
			}
     },
     //工具栏
     toolbar:[{
          id:'bsnadd',
          text:'添加新的工作流',
          iconCls:'icon-add',
          handler:function(){
             $('#bsnadd').linkbutton('enable');
             addData();
           }
        }]
   });  
  	
});

//查看
function viewData(ps_mc){
   if(ps_mc==""){
		alert("请选中一条记录！");
	}else{
	 var params;
	 var style = "dialogWidth:980px;dialogHeight:1000px;status:no";   
	 window.showModalDialog("viewprocess.html?name="+ps_mc+"&time="+new Date().getMilliseconds(),params, style);   
	}
}

//添加
function addData(){
	 //window.open("sysuser.do?method=userAdd&region_id="+region_id);
	 var params;
	 var style = "dialogWidth:980px;dialogHeight:1000px;status:no";   
	 var k=window.showModalDialog("addprocess.html?time="+new Date().getMilliseconds(),params, style);   
	 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
		window.location.reload();
	}
}

//修改
function editData(ps_id,ps_mc){
   if(ps_id==""){
		alert("请选中一条记录！");
	}else{
	    //检查状态，正在使用中的时候，禁止修改
	    $.ajax({
				url:"../wf_checkState.action?name="+encodeURI(encodeURI(ps_mc))+'&time='+new Date().getMilliseconds(),
				type:'post',
				async:true,
				cache:false,
				timeout: 100000,
				//data:formData,
				error:function(){
					 alert("服务器繁忙，请稍后再试！");
				},
				success: function(result){
		           /*if(result=="1"){
		              alert("此流程正在使用，禁止修改！");
		              return;
		           }else{*/
		              var params;
					  var style = "dialogWidth:980px;dialogHeight:1000px;status:no";   
					  var k=window.showModalDialog("updateprocess.html?name="+ps_mc+"&ps_id="+ps_id+"&time="+new Date().getMilliseconds(),params, style);   
					 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
						window.location.reload();
					 }
		          // }
		        }
			});   
	 
	}
}

//删除确认
function delData(ps_dm){ 
	if(ps_dm==""){
		alert("请选中一条记录！");
	}else{
		var cfmMsg= "确定删除此条录吗?删除后不可恢复！";
		 $.messager.confirm('确认', cfmMsg, function (r) {
		    if(r){
		       $.ajax({
				url:"../wf_delete.action?ps_dm="+ps_dm+'&time='+new Date().getMilliseconds(),
				type:'post',
				async:true,
				cache:false,
				timeout: 100000,
				//data:formData,
				error:function(){
					 alert("服务器繁忙，请稍后再试！");
				},
				success: function(result){
		           if(result=="1"){
		              alert("删除成功！");
		              window.location.href='wf_admin.jsp';
		           }else if(result=="2"){
		               alert("此流程已经绑定业务菜单，禁止删除！");
		           }else if(result=="3"){
		               alert("此流程正在使用，禁止删除！");
		           }else{
		               alert("删除失败！");
		            }
		        }
			});   
		  }
		 });
	}
 
}

//作废
function zuofeiData(ps_dm,ps_sfzf){ 
	if(ps_dm=="" || ps_sfzf==""){
		alert("请选中一条记录！");
	}else{
		var cfmMsg;
		if(ps_sfzf=="1"){
		    cfmMsg="确定作废此条录吗?作废后不可恢复！";
		    ps_sfzf="Y";
		}else{
		   cfmMsg="确定取消作废此条录吗?";
		   ps_sfzf="N";
		}
		 $.messager.confirm('确认', cfmMsg, function (r) {
		    if(r){
		       $.ajax({
				url:"../wf_zuofei.action?ps_dm="+ps_dm+'&ps_sfzf='+ps_sfzf+'&time='+new Date().getMilliseconds(),
				type:'post',
				async:true,
				cache:false,
				timeout: 100000,
				//data:formData,
				error:function(){
					 alert("服务器繁忙，请稍后再试！");
				},
				success: function(result){
		           if(result=="1"){
		              alert("作废成功！");
		              window.location.href='wf_admin.jsp';
		           }else if(result=="2"){
		               alert("此流程正在使用，禁止作废！");
		           }else{
		               alert("作废失败！");
		            }
		        }
			});   
		  }
		 });
		
	}
}
</SCRIPT>
<body>
<div class="content_wrap" style="width:100%;height:95%">
<table id="newstab" style="width:100%;"></table>
</div>
  </body>
</html>