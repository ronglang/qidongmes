<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wfProcessData=null==request.getAttribute("wfProcessData")?"var zNodes =[];":request.getAttribute("wfProcessData").toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>工作流节点权限管理</title>
  <jsp:include page="../inc_right.jsp" flush="true"></jsp:include>
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
		//beforeClick: beforeClick,getDepartTreeDatabyComId&com_id=
		onClick: onWfProcessClick
	}
};
var zNodes='<%=wfProcessData%>';
zNodes=eval(zNodes);

$(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);

 $('#mytab').datagrid({
    title:'节点角色权限管理',
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
    url:'wfcz_getRoleDataByNodeId.action?node_id=',
    loadMsg:'数据加载中,请稍后...',   
    //sortName:'code',
    sortOrder:'desc',
    remoteSort:false,
    idField:'nrId',
    columns:[[
		{title:'编码',field:'nrId',width:80,align:'center',hidden:'true'},
		{title:'nodeid',field:'nodeId',width:80,align:'center',hidden:'true'},
		{title:'流程编号',field:'psDm',width:120,align:'left'},
		{title:'角色id',field:'roleId',width:80,align:'center',hidden:'true'},
		{title:'绑定角色',field:'roleName',width:200,align:'left',
			formatter:function(value,rec){
				  return rec.depName+'---'+rec.roleName;
			}
		 },
		 {title:'节点办理时限',field:'nodeSx',width:80,align:'left',
		   formatter:function(value,rec){
				  return value+'工作日';
			}
		},
		{title:'节点操作URL',field:'nodeUrl',width:120,align:'left'},
		{field:'opt',title:'操作',width:100,align:'center',
              formatter:function(value,rec){ 
                  return '<span style="color:red" mce_style="color:red"><a href="javascript:unbindRole(\''+rec.psDm+'\','+rec.nodeId+','+rec.roleId+')" mce_href="#" data=""><img src="../jslib/jquery-easyui/themes/icons/delete.gif" align="absmiddle">解除角色绑定</a> </span>';  
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
          text:'绑定新角色权限',
          iconCls:'icon-add',
          handler:function(){
             //$('#btnadd').linkbutton('enable');
             bindRoleData();
           }
        },{
          id:'btn_sx_add_update',
          text:'添加或更新办理时限',
          iconCls:'icon-clock',
          handler:function(){
             //$('#btnSx').linkbutton('enable');
             addOrUpdateSxData();
           }
        },{
          id:'btn_url_add_update',
          text:'添加或更新操作URL',
          iconCls:'icon-url',
          handler:function(){
             //$('#btnUrl').linkbutton('enable');
             addOrUpdateUrlData();
           }
        },{
          id:'btn_fork_add_update',
          text:'设置fork分支节点流向',
          iconCls:'icon-url',
          handler:function(){
             //$('#btnUrl').linkbutton('enable');
             addOrUpdateForkData();
           }
        }]
   });  
});

//单击流程tree节点事件 ，加载此流程下的所有节点
function onWfProcessClick(event, treeId, treeNode, clickFlag) {
	$("input[name='ps_id']").val(treeNode.nodedm);
	//alert('当前点击的单位ID是'+treeNode.id);
	//加载此流程下的所有环节节点
	loadNodeTreeData(treeNode.id);
}	
	
///////////////////第二个树的设置/////////////////////////
//用来保存用户点击的单位ID，方便在查询此单位部门时使用
var tmp_ps_id;	
var setting_dep = {
			async: {
				enable: true,//异步处理
				//contentType: "application/json",//提交参数方式，这里 JSON 格式，默认form格式
				url:getUrl// 'role.do?method=getDepartTreeDatabyComId',//异步获取json格式数据的路径
				//autoParam: ["com_id",""+tmp_com_id+""],//异步加载时需要提交的参数，多个用逗号分隔,//autoParam: ["id=b"], 
    			//otherParam: {"com_id":""+tmp_com_id+""} 
			},
			callback: {//回调函数，在这里可做一些回调处理
				//beforeAsync: zTreeBeforeAsync
				onClick: onNodeClick
			},
			check: {//设置 zTree 的节点上是否显示 checkbox / radio ,默认为false
				enable: false
			},
			data: {
				simpleData: {
					//如果设置为 true，请务必设置 setting.data.simpleData 内的其他参数: idKey / pIdKey / rootPId,并且让数据满足父子关系。				
					enable: true,//true / false 分别表示 使用 / 不使用 简单数据模式
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0							
				}
			}
		};

var zNodes_dep =[];//树节点，json格式，异步加载可设置为null或[]	
var zTreeObj_dep;//树对象		
function loadNodeTreeData(node_id){
	//获取并传递用户点击的单位ID
	//alert('传递过来的当前点击的单位ID是'+node_id);
	tmp_ps_id=node_id;
	zTreeObj_dep =$.fn.zTree.init($("#treeDemo2"), setting_dep, zNodes_dep);//实例化后直接返回树对象
}

function getUrl() {
     var param = "ps_id=" +tmp_ps_id;
     return "wfcz_getNodeTreeDatabyPsId.action?" + param;
}

	
//单击流程环节 节点事件 刷新 右边的角色GRID
function onNodeClick(event, treeId, treeNode, clickFlag) {
	$("input[name='node_id']").val(treeNode.id);
	$("input[name='ps_dm']").val(treeNode.psDm);
	$("input[name='node_type']").val(treeNode.nodeType);
	//alert(treeNode.id);
	//alert(treeNode.psDm);
	$('#mytab').datagrid({url:'wfcz_getRoleDataByNodeIdPsDm.action?a=1',queryParams:{ps_dm:''+treeNode.psDm+'',node_id:''+treeNode.id+''},method:'post'});
}

//绑定角色
function bindRoleData(){
	var node_id=$("input[name='node_id']").val();
	var ps_dm=$("input[name='ps_dm']").val();
   if(node_id=="" || node_id==null || node_id=="0" || ps_dm=="" || ps_dm==null || ps_dm=="0"){
		alert("请先选择一个要添加角色权限的流程节点！");
	}else{
		 //window.open("user_add.jsp");
		 var params;
		 var style = "dialogWidth:800px;dialogHeight:600px;status:no";   
		 var k=window.showModalDialog("wfcz_node_bind_role_add.jsp?ps_dm="+ps_dm+"&node_id="+node_id+"&time="+new Date().getMilliseconds(),params, style);   
		 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
			//gu20141208 window.location.reload();
		     $('#mytab').datagrid('reload');
		}
	}
}

//删除绑定角色
function unbindRole(ps_dm,node_id,role_id){
   if(ps_dm=="" || ps_dm==null || node_id=="" || node_id==null || role_id==null || role_id==""){
		alert("请先选择一个要删除的角色权限！");
	}else{
	 	$.ajax({
			url:"wfcz_unbindNodeRole.action?role_id="+role_id+"&ps_dm="+ps_dm+"&node_id="+node_id+"&time="+new Date().getMilliseconds(),
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
			//gu20141208 window.location.reload();
		     $('#mytab').datagrid('reload');
			   }else if(result=="2"){
			      alert("当前选中节点至少需要绑定1个角色，如果确实要解除此角色权限，请添加一个新角色权限后再进行解除操作！");
			   }else{
			      alert("解除绑定失败！");
			   }
			}
		 });
	}
}

//添加或更新办理时限
function addOrUpdateSxData(){
   var node_id=$("input[name='node_id']").val();
   var ps_dm=$("input[name='ps_dm']").val();
   if(node_id=="" || node_id==null || node_id=="0" || ps_dm=="" || ps_dm==null || ps_dm=="0"){
		alert("请先选择一个要添加或更新办理时限的流程节点！");
	}else{
	 	var params;
		 var style = "dialogWidth:800px;dialogHeight:600px;status:no";   
		 var k=window.showModalDialog("wfcz_node_sx_add.jsp?ps_dm="+ps_dm+"&node_id="+node_id+"&time="+new Date().getMilliseconds(),params, style);   
		 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
			//gu20141208 window.location.reload();
		     $('#mytab').datagrid('reload');
		}
	} 
}
//添加或更新操作URL
function addOrUpdateUrlData(){
   var node_id=$("input[name='node_id']").val();
   var ps_dm=$("input[name='ps_dm']").val();
   if(node_id=="" || node_id==null || node_id=="0" || ps_dm=="" || ps_dm==null || ps_dm=="0"){
		alert("请先选择一个要添加或更新操作URL的流程节点！");
	}else{
	    var params;
		 var style = "dialogWidth:800px;dialogHeight:600px;status:no";   
		 var k=window.showModalDialog("wfcz_node_url_add.jsp?ps_dm="+ps_dm+"&node_id="+node_id+"&time="+new Date().getMilliseconds(),params, style);   
		 if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
			//gu20141208 window.location.reload();
		     $('#mytab').datagrid('reload');
		}
	}
}
//设置或者更新fork节点流向
function addOrUpdateForkData(){
	var node_id=$("input[name='node_id']").val();
	var ps_dm=$("input[name='ps_dm']").val();
	var node_type=$("input[name='node_type']").val();
	if(node_id=="" || node_id==null || node_id=="0" || ps_dm=="" || ps_dm==null || ps_dm=="0"){
		alert("请先选择一个要设置fork分支节点流向的节点！");
	}else{
		if(node_type=="FORK_NODE"){
		 var params;
		 var style = "dialogWidth:800px;dialogHeight:600px;status:no";   
		 var k=window.showModalDialog("wfcz_node_forkset_add.jsp?ps_dm="+ps_dm+"&node_id="+node_id+"&time="+new Date().getMilliseconds(),params, style);   
		 //if(k.type=="ok"){ //传递回的type为ok的时候才刷新页面。   
		 //	window.location.reload();
		 //}
		}else{
		    alert("此节点不是fork分支节点，不能进行设置！");
		}
	}
}
</SCRIPT>
<style type="text/css">
.ztree{
    min-height:650px;
    height:auto !important;
    height:650px;
}
</style>
  </head>
  <body>
 <table width="100%" height="100%" border="1">
	<tr>
	<td width="20%" height="90%" valign=top>
		<div id="treeDemo" class="ztree" style="border: 1px solid #8db3e3"></div>
	</td>
	
	<td width="20%" height="90%" valign=top>
		<div id="treeDemo2" class="ztree"  style="border: 1px solid #8db3e3"></div>
	</td>
	
	<td width="50%" height="100%" valign=top>
	    <input type="hidden" name="node_type" />
		<input type="hidden" name="ps_id" />
		<input type="hidden" name="node_id" />
		<input type="hidden" name="ps_dm" />
		<table id="mytab"></table>
	</td>
	</tr>
	</table>
  </body>
</html>