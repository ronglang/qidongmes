<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
//原来此菜单绑定/workflowcz/wfcz_menu_process_bind.action
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>业务绑定列表</title>
    <jsp:include page="../inc_right_new.jsp"></jsp:include>
<script type="text/javascript">
		
$(function(){
$('#mytab').datagrid({
    title:'业务绑定列表',
    iconCls:'icon-ok',
    width:800,
    height:450,
    pageSize:20,
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
    url:'wfcz_wf_yewu_process_admin.action',
    loadMsg:'数据加载中,请稍后...',   
    //sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    columns:[[
		{title:'业务ID',field:'ypId',align:'center',hidden:'true',width:10},	
		{title:'业务名称',field:'yewuMc',align:'left',width:200},
		{title:'业务名称拼音字母缩写',field:'yewuPyzm',align:'left',width:120},
		{title:'是否启动工作流',field:'yewuSfqygzl',align:'center',width:120,
		  formatter:function(value,rec){
             	if(value=="Y")
                  return '<font style="color:green" >已经启用</font>';  
                else
                  return '<font style="color:red">暂未启用</font>';  
             }   
		},
		{title:'绑定的工作流代码',field:'psDm',align:'left',width:120,
			formatter:function(value,rec){
             	if(value!="")
                  return '<font style="color:green" >'+value+'</font>';  
                else
                  return '<font style="color:red">暂未绑定工作流程</font>';  
             }   
		},
		{title:'绑定的工作流名称',field:'psMc',align:'left',width:150},
		{title:'绑定时间',field:'ypBdsj',align:'left',width:110},
		{field:'opt',title:'操作',align:'center',width:250,
              formatter:function(value,rec){
                var czStr="";
                if(rec.yewuSfqygzl=="Y"){
                   czStr='<span style="color:blue" mce_style="color:blue"><a href="javascript:qiyongData('+rec.ypId+',\''+rec.psDm+'\',2)" mce_href="#" data=""><img src="../jslib/jquery-easyui/themes/icons/lock.gif" align="absmiddle">停用工作流</a></span>&nbsp;&nbsp;';
               }
               if(rec.yewuSfqygzl=="N" && rec.psDm!=""){
                    czStr+='<span style="color:blue" mce_style="color:blue"><a href="javascript:qiyongData('+rec.ypId+',\''+rec.psDm+'\',1)" mce_href="#" data=""><img src="../jslib/jquery-easyui/themes/icons/unlock.gif" align="absmiddle">启用工作流</a></span>&nbsp;&nbsp;';  
               }
                
             	if(rec.psDm==""){
                   czStr+='<span style="color:blue" mce_style="color:blue"><a href="javascript:bind('+rec.ypId+')" mce_href="#" data=""><img src="../jslib/jquery-easyui/themes/icons/link.png" align="absmiddle">绑定工作流程</a></span>';  
                }
                /*else{
                  czStr+='<span style="color:red" mce_style="color:red"> <a href="javascript:updatebind('+rec.ypId+',\''+rec.psDm+'\')" mce_href="#" data=""><img src="../jslib/jquery-easyui/themes/icons/updatelink.png" align="absmiddle">更新绑定</a></span>&nbsp;&nbsp;'; 
                }*/
                if(rec.yewuSfqygzl=="N" && rec.psDm!=""){
                	  czStr+='<span style="color:red" mce_style="color:red"> <a href="javascript:unbind('+rec.ypId+',\''+rec.psDm+'\')" mce_href="#" data=""><img src="../jslib/jquery-easyui/themes/icons/unlink.png" align="absmiddle">解除绑定</a></span>'; 
                }
             	return czStr;
             }   
        }  
	]],
     onDblClickRow:function(){//双击事件 查看、修改等操作
        var selected = $('#mytab').datagrid('getSelected');
			if(selected){
			   //window.open("DataView.action?Id="+selected.ID);
			  //alert("双击"+selected.id+":"+selected.name+":"+selected.age+":"+selected.className);
			  //editData(selected.comId);
			}
     },
     //工具栏
     toolbar:[{
          id:'btn_company_add',
          text:'添加新业务',
          iconCls:'icon-add',
          handler:function(){
             $('#btnadd').linkbutton('enable');
             addData();
           }
        }]
   });  
	
});


//添加新业务
function addData(){
	var params;
	var style = "dialogWidth:800px;dialogHeight:400px;status:no";   
	var k=window.showModalDialog("wfcz_yewu_process_add.jsp?time="+new Date().getMilliseconds(),params, style);   
	if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
		window.location.reload();
	}
}
		
//启用 禁用		
function qiyongData(id,ps_dm,state){
   if(id==""){
		alert("请选中一条记录！");
	}else{//1 启用 2禁用
	   var tipmsg="";
	   if(state=="2")
	     tipmsg="停用";
	   else
	     tipmsg="启用";
	     
	   if(confirm("确定要"+tipmsg+"此业务吗?")){
	       $.ajax({
			url:"wfcz_qiyong.action?yp_id="+id+"&state="+state+"&ps_dm="+ps_dm,
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
	              alert(tipmsg+"业务成功！");
	              window.location.reload();
	           }else if(result=="2"){
	              alert("当前流程正在使用中，不能停用！");
	           }else{
	               alert(tipmsg+"业务失败！");
	            }
	        }
		});   
	   
	   }
	 
	}
}



//绑定工作流
function bind(yp_id){
   if(yp_id=="" || yp_id==null){
		alert("请先选择一个要绑定流程的业务！");
   }else{
   	 var params;
	 var style = "dialogWidth:800px;dialogHeight:600px;status:no";   
	 var k=window.showModalDialog("wfcz_yewu_bind_add.jsp?yp_id="+yp_id+"&time="+new Date().getMilliseconds(),params, style);   
	 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
		window.location.reload();
          }
	}
}		


//解除绑定
function unbind(yp_id,ps_dm){
  if(yp_id=="" || yp_id==null){
		alert("请选择一条数据！");
   }else{
  	  $.ajax({
		url:"wfcz_unbindYewu.action?yp_id="+yp_id+"&ps_dm="+ps_dm+"&time="+new Date().getMilliseconds(),
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
		      alert("解除绑定成功！");
		      //刷新页面。   
		     window.location.reload();
		   }else if(result=="2"){
		     alert("此流程正在使用，禁止解除绑定！");
           }else{
		      alert("解除绑定失败！");
		   }
		}
	 });
   }
}
</SCRIPT>
  </head>
<body>
<div class="content_wrap" style="width:100%;height:100%">
<table id="mytab" style="width:100%"></table>
</div>
  </body>
</html>