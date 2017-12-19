<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ShenPiMenuData=request.getAttribute("ShenPiMenuData")==null?"var zNodes =[];":request.getAttribute("ShenPiMenuData").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
  <title></title>
      <jsp:include page="../inc_right.jsp"></jsp:include>
	<link rel="stylesheet" href="../jslib/ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="../jslib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="../jslib/ztree/js/jquery.ztree.core-3.4.js"></script>
	
<script type="text/javascript">
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: onClick
	}
};
//var zNodes=[{id:0,pId:-1,name:"行政区域管理",isParent:true,open:true},{id:1,pId:0,name:"系统维护",isParent:true},{id:5,pId:1,name:"行政区域管理"},{id:3,pId:1,name:"用户管理"},{id:2,pId:1,name:"行政区域管理"},{id:6,pId:1,name:"查看日志"},{id:7,pId:0,name:"仓库业务管理",isParent:true},{id:4,pId:7,name:"果品物资管理"},{id:20,pId:7,name:"仓库管理"},{id:8,pId:7,name:"整体托盘入库"},{id:21,pId:7,name:"零星散货入库"},{id:9,pId:7,name:"果品预出库管理"},{id:22,pId:7,name:"整体托盘出库"},{id:33,pId:7,name:"零星散货出库"},{id:12,pId:7,name:"果品报损管理"},{id:37,pId:7,name:"零星散货报损"},{id:10,pId:0,name:"查询统计",isParent:true},{id:23,pId:10,name:"库存明细查询"},{id:25,pId:10,name:"库存汇总查询"},{id:11,pId:10,name:"库存年报查询"},{id:13,pId:0,name:"农户一卡通管理",isParent:true},{id:29,pId:13,name:"农户单位管理"},{id:24,pId:13,name:"农户管理"},{id:14,pId:13,name:"IC卡发放管理"},{id:15,pId:13,name:"农用物资发放管理"},{id:16,pId:13,name:"代缴果品管理"},{id:36,pId:13,name:"果品降级管理"},{id:17,pId:0,name:"RFID电子标签管理",isParent:true},{id:19,pId:17,name:"读卡器测试"},{id:18,pId:17,name:"RFID管理"},{id:26,pId:0,name:"手工仓库管理",isParent:true},{id:27,pId:26,name:"手工仓库入库"},{id:28,pId:26,name:"手工仓库出库"},{id:30,pId:0,name:"农户一卡通查询",isParent:true},{id:31,pId:30,name:"物资发放查询"},{id:34,pId:30,name:"物资汇总查询"},{id:32,pId:30,name:"代缴果品查询"},{id:35,pId:30,name:"代缴果品汇总查询"}];
var zNodes='<%=ShenPiMenuData%>';
zNodes=eval(zNodes);
		
$(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	
$('#mytab').datagrid({
    title:'绑定的工作流',
    iconCls:'icon-ok',
    width:400,
    height:300,
    pageSize:10,
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
    url:'',
    loadMsg:'数据加载中,请稍后...',   
    //sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    columns:[[
		{title:'绑定编号',field:'mlId',align:'center',hidden:'true',width:100},
		{title:'流程编号',field:'psDm',align:'center',width:150},
		{title:'流程名称',field:'psMc',align:'left',width:220},
		{title:'绑定时间',field:'mlBdsj',align:'center',width:100},
		{field:'opt',title:'操作',width:150,align:'center',
              formatter:function(value,rec){ //<a href="javascript:delData('+rec.userId+')" mce_href="#"><img src="../jslib/jquery-easyui/themes/icons/user_delete.gif" align="absmiddle">删除</a>  
                  return '<span style="color:red" mce_style="color:red"> <a href="javascript:unbind('+rec.menuId+',\''+rec.psDm+'\')" mce_href="#" data="btn_depart_qiyong"><img src="../jslib/jquery-easyui/themes/icons/unlock.gif" align="absmiddle">解除绑定</a></span>';  
             }   
        }  
	]],
     onDblClickRow:function(){//双击事件 查看、修改等操作
        var selected = $('#mytab').datagrid('getSelected');
			if(selected){
			   //window.open("DataView.action?Id="+selected.ID);
			  //alert("双击"+selected.id+":"+selected.name+":"+selected.age+":"+selected.className);
			  //editData(selected.aoId);
			}
     },
     //工具栏
     toolbar:[{
          id:'btn_bind_process',
          text:'绑定流程',
          iconCls:'icon-add',
          handler:function(){
             $('#btnadd').linkbutton('enable');
             addData();
           }
        }]
   });  
			
});
		
		
//单击节点事件 刷新GRID
function onClick(event, treeId, treeNode, clickFlag) {
	if(treeNode.nodedm=="0"){
		$("input[name='menu_id']").val("");
	}else{
	  	$("input[name='menu_id']").val(treeNode.nodedm);
		$('#mytab').datagrid({url:'wfcz_getProcessDataByMenuId.action',queryParams:{menu_id:''+treeNode.nodedm+''},method:'post'});
	
	}
}

//绑定工作流
function addData(){
   var menu_id=$("input[name='menu_id']").val();
   if(menu_id=="" || menu_id==null){
		alert("请先选择一个要绑定流程的申请业务！");
   }else{
   	   //检查当前业务是否已绑定流程
	   $.ajax({
			url:"wfcz_checkMenuHavedBindProcess.action?menu_id="+menu_id+"&time="+new Date().getMilliseconds(),
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
	              alert("此流程当前已经绑定过流程，不能重复绑定，要想绑定新流程，请先解除绑定！");
	              return;
	           }else{
				 var params;
				 var style = "dialogWidth:800px;dialogHeight:600px;status:no";   
				 var k=window.showModalDialog("wfcz_menu_bind_add.jsp?menu_id="+menu_id+"&time="+new Date().getMilliseconds(),params, style);   
				 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
					window.location.reload();
	            }
	        }
	      }
		});
	}
}

//解除绑定
function unbind(menu_id,ps_dm){
   if(menu_id==""){
	  alert("请选中一条记录！");
   }else{
   	   //检查状态，正在使用中的时候，禁止修改
	    $.ajax({
			url:"wfcz_checkStateByMenuIdPsDm.action?menu_id="+menu_id+"&ps_dm="+ps_dm+"&time="+new Date().getMilliseconds(),
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
	              alert("此流程正在使用，禁止解除绑定！");
	              return;
	           }else{
	    		  $.ajax({
					url:"wfcz_unbind.action?menu_id="+menu_id+"&ps_dm="+ps_dm+"&time="+new Date().getMilliseconds(),
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
					   }else{
					      alert("解除绑定失败！");
					   }
					}
				 });
	           }
	        }
		});
   }
}
</SCRIPT>
  </head>
  <body>
<div class="content_wrap" >
	<table width="100%" height="100%">	
	<tr>
	<td width="24%" height="100%">
		<div style="height:480px;*height:auto!important;*height:510px;min-height:510px;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</td>
	
	<td width="75%">
	<br/>
		<div class="right">
		<input type="hidden" name="menu_id" />
		<table id="mytab" style="width:60%"></table>
		</div>
	</td>
	</tr>
	</table>
</div>
  </body>
</html>