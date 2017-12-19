<%@ page language="java" import="java.util.*,java.net.*,com.xml.vo.BtUser,com.xml.service.BsPubWorkFlowService,com.xml.vo.WfNode,com.xml.vo.BsWorkflowYewu"
	pageEncoding="utf-8"%>
	
<%@ page import="java.net.URLDecoder" %>
	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	String v_sqlcId=(null==request.getParameter("sqlcId")?"":request.getParameter("sqlcId").toString());
	String v_osVer=(null==request.getParameter("osVer")?"":request.getParameter("osVer").toString());	
	String v_sqSqbh=(null==request.getParameter("sqSqbh")?"":request.getParameter("sqSqbh").toString());
	String v_psDm=(null==request.getParameter("psDm")?"":request.getParameter("psDm").toString());

	v_sqSqbh=URLDecoder.decode(v_sqSqbh,"utf-8");

    String v_ndId=(null==request.getParameter("ndId")?"":request.getParameter("ndId").toString());	
    BsPubWorkFlowService v_BsPubWorkFlowService=new BsPubWorkFlowService();
    List<BsWorkflowYewu> v_BsWorkflowYewu_list=v_BsPubWorkFlowService.wf_gethislog(v_sqSqbh);
    
    System.out.println("v_ndId"+v_ndId);
    
    WfNode v_WfNode=(WfNode)v_BsPubWorkFlowService.wf_getWfNode(v_ndId).get(0);
    String v_nodeUrl=v_WfNode.getNodeUrl();
    if ((v_nodeUrl==null)||("".equals(v_nodeUrl))){
    	v_nodeUrl="workflowyewu/wfyw_daibancheck.action?";
    };
	
	String v_mainUrl=v_nodeUrl;
	String v_viewPageUrl="";
	if (v_nodeUrl.indexOf("viewPage")>=0){
		v_viewPageUrl=v_nodeUrl.substring(v_nodeUrl.indexOf("viewPage")+9);
		v_mainUrl=v_nodeUrl.substring(0,v_nodeUrl.indexOf("viewPage")-1);
	}
	String v_buchongziliao_node="0";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">

		<title>添加</title>
		<link rel="stylesheet" href="../inc/css/page.css" type="text/css"></link>
		<jsp:include page="../inc_right.jsp"></jsp:include>
		
	<script type="text/javascript">
	
	  $(function(){
	      var v_local_viewPageUrl="<%=v_viewPageUrl%>";
	      var v_local_mainUrl="<%=v_mainUrl%>";
	      var v_local_sqlcId="<%=v_sqlcId%>";
	      var v_local_osVer="<%=v_osVer%>";
	      var v_local_sqSqbh="<%=v_sqSqbh%>";
	      var v_local_basePath="<%=basePath%>";
	      var v_local_psDm="<%=v_psDm%>";
	      v_local_sqSqbh=encodeURI(encodeURI(v_local_sqSqbh));
		  //v_local_sqSqbh=encodeURIComponent(encodeURIComponent(v_local_sqSqbh));
		  

	      if (v_local_mainUrl.indexOf("?")<0){
	    	  v_local_mainUrl=v_local_basePath+v_local_mainUrl+"?czlx=add&sqlcId="+v_local_sqlcId+"&osVer="+v_local_osVer+"&sp_slbh="+v_local_sqSqbh+"&logId=-1&currNdId=<%=v_ndId%>"+"&time="+new Date().getMilliseconds();
	      }else{
	    	 v_local_mainUrl=v_local_basePath+v_local_mainUrl+"&czlx=add&sqlcId="+v_local_sqlcId+"&osVer="+v_local_osVer+"&sp_slbh="+v_local_sqSqbh+"&logId=-1&currNdId=<%=v_ndId%>"+"&time="+new Date().getMilliseconds(); 
	      }
	      //v_local_mainUrl = encodeURI(encodeURI(v_local_mainUrl));
	      if (v_local_viewPageUrl==null || v_local_viewPageUrl==""){
	    	  document.getElementById("divChakan").style.display='none';
	    	  document.getElementById("iframe_chakanqu").src=""; 
	      }else{
		      if (v_local_viewPageUrl.indexOf("?")<0){
		    	  v_local_viewPageUrl=v_local_basePath+v_local_viewPageUrl+"?sp_slbh="+v_local_sqSqbh+"&time="+new Date().getMilliseconds();
		      }else{
		    	 v_local_viewPageUrl=v_local_basePath+v_local_viewPageUrl+"&sp_slbh="+v_local_sqSqbh+"&time="+new Date().getMilliseconds();
		      }
		      
	    	  document.getElementById("divChakan").style.display='block';
	    	  document.getElementById("iframe_chakanqu").src=v_local_viewPageUrl; 
	      }
	      if (v_local_mainUrl==null || v_local_mainUrl==""){
	    	  if (document.getElementById("iframe_caozuoqu")!=null){
	    		document.getElementById("iframe_caozuoqu").src="";   
	    	  }
	      }else{
	    	  if (document.getElementById("iframe_caozuoqu")!=null){
	    		document.getElementById("iframe_caozuoqu").src=v_local_mainUrl;  
	    	  }
	      }
	    // v_local_sqSqbh=encodeURIComponent(encodeURIComponent(v_local_sqSqbh));
		$('#tabLog').datagrid({
		    title:'业务经办日志',
		    iconCls:'icon-ok',
		    width:800,
		    height:300,
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
		    url:'<%=basePath%>workflowyewu/wfyw_wfshlogMainLoadData.action?psDm='+v_local_psDm+'&sqSqbh='+v_local_sqSqbh,
		    loadMsg:'数据加载中,请稍后...',   
		    //sortName:'code',
		    sortOrder:'desc',
		    remoteSort:false,
		    columns:[[
				{title:'日志表主键',field:'logId',align:'center',width:60,hidden:true},
				{title:'公司名称',field:'comMc',align:'center',width:150},
				{title:'公司代码',field:'comDm',align:'center',width:60,hidden:true},
				{title:'流程代码',field:'psDm',align:'center',width:60,hidden:true},
				{title:'流程名称',field:'psMc',align:'center',width:150},
				{title:'节点Id',field:'nodeId',align:'center',width:60,hidden:true},
				{title:'节点名称',field:'nodeName',align:'center',width:120},
				{title:'申请编号',field:'sqSqbh',align:'center',width:120},
				{title:'处理结果',field:'logShjg',align:'center',width:100,
					formatter:function(value,rec){
					 	if(value=="01"){ 
					 		return '<span style=color:blue>通过【是】</span>';
					 	}else if(value=="02"){
					 		return '<span style=color:red>未通过【否】</span>';
					 	}else if(value=="03"){
					 		return '<span style=color:red>回退</span>';
					 	}else if(value=="04"){
					 		return '<span style=color:red>补充资料</span>';
					 	}
					 }
				},
				{title:'备注',field:'logShbz',align:'center',width:200},
				{title:'审核人',field:'logShrxm',align:'center',width:60},
				{title:'审核时间',field:'logShsj',align:'center',width:120},
				{title:'补充资料情况',field:'logBczlflag',align:'center',width:100,
					formatter:function(value,rec){
					 	if(value=="0"){ 
					 		return '<span style=color:blue></span>';
					 	}else if(value=="1"){
					 		return '<span style=color:red>需要补充资料</span>';
					 	}else if(value=="2"){
					 		return '<span style=color:red>已补充资料</span>';
					 	}
					 }
				},
				{title:'补充资料人员',field:'logBczlczyxm',align:'center',width:90},
				{title:'补充资料时间',field:'logBczlczsj',align:'center',width:120}
			]]
		
		   });  


	  })////////////////////////////////////////////////////////////////////////////
	

	</SCRIPT>

	</head>



	<script language="javascript" for="document" event="onkeydown">
if (event.keyCode == 13 && event.srcElement.type != 'button'
		&& event.srcElement.type != 'submit'
		&& event.srcElement.type != 'reset'
		&& event.srcElement.type != 'textarea' && event.srcElement.type != '') {
	event.keyCode = 9;
}
</script>
	<body>
	 
	   <% if (v_viewPageUrl!=null || !"".equals(v_viewPageUrl)){ 
	   %>
	   <table width="100%"  style="background-color: #E6F2E8">
	   <tr>
	     <td width="5%"></td>
	     <td width="90%">
			  <div id="divChakan" class="content_wrap" style="width: 100%; height: 160px;display: none">
				<table style="width: 100%; border: none;">
					<tr>
						<td>
							<iframe id="iframe_chakanqu" name="iframe_chakanqu" src="" width="100%"
								height="160px;" style="border: none"></iframe>
						</td>
		
					</tr>
				</table>	    
			  </div>	     
	     </td>
	     <td width="5%"></td>	     
	   </tr>
	   <%} %>
	   </table>
	   <br/>
	   
	  <% 
	  for (BsWorkflowYewu v_loop_BsWorkflowYewu :v_BsWorkflowYewu_list){
	    String v_his_mainUrl=v_loop_BsWorkflowYewu.getNodeUrl();
	    String v_his_viewPageUrl="";
	    String v_log_id=String.valueOf(v_loop_BsWorkflowYewu.getLogId());
	    //如果当前节点为补充资料  则
	    if ("04".equals(v_loop_BsWorkflowYewu.getLogShjg())){
	    	v_buchongziliao_node="1";
	    }
	    
		if (v_his_mainUrl.indexOf("viewPage")>=0){
			v_his_viewPageUrl=v_his_mainUrl.substring(v_his_mainUrl.indexOf("viewPage")+9);
			v_his_mainUrl=v_his_mainUrl.substring(0,v_his_mainUrl.indexOf("viewPage")-1);
		}
		
		if (v_his_mainUrl.indexOf("?")<0){
			v_his_mainUrl=basePath+v_his_mainUrl+"?czlx=view&logId="+v_log_id;
		}else{
			v_his_mainUrl=basePath+v_his_mainUrl+"&czlx=view&logId="+v_log_id;
		}
		%>
		<table width="100%"  style="background-color: #E6F2E8">
		<tr>
		  <td width="5%"></td>
		  <td width="90%">
			<div class="content_wrap" style="width: 100%; height: 200px;border:1px solid;overflow:hidden;">
				<iframe  src="<%=v_his_mainUrl%>" width="100%"
					height="200px;" style="border: none;"></iframe>
			</div>			  
		  </td>
		  <td width="5%">
		  </td>
		</tr>
		</table>
        <br/>
		    
	  <%}%>
	  
	  <%if ("0".equals(v_buchongziliao_node)){ %>
	  <table width="100%"  style="background-color: #E6F2E8">
	   <tr>
	     <td width="5%"></td>
	     <td width="90%">
			<div class="content_wrap" style="width: 100%; height: 200px;border:1px solid;overflow:hidden;">
				<iframe id="iframe_caozuoqu" name="iframe_caozuoqu" src="" width="100%"
					height="200px;" style="border: none;"></iframe>
			</div>	     
	     </td>
	     <td width="5%"></td>	     
	   </tr>	
      </table>
      <br/>
      <%} %>
      
      <table width="100%"  style="background-color: #E6F2E8">	   
	   <tr>
	     <td width="5%"></td>
	     <td width="90%">
				<div class="content_wrap" style="width:100%;height:300px;">
				<table width="100%" height="100%">
				  <tr><td height="25px;" class="grouphead2">业务经办日志</td></tr>
				  <tr>
				  <td>
				  <table id="tabLog" style="width:100%;"></table>
				  </td></tr>
				</table>
				
				</div>    
	     </td>
	     <td width="5%"></td>	     
	   </tr>
	   <tr>
	     <td width="5%"></td>
	     <td width="90%">
			<div class="content_wrap" style="width: 100%; height: 50px;"
				align="center">
			</div>
	     </td>
	     <td width="5%"></td>	     
	   </tr>		      
	   
	      	   
	 </table>
	  

	</body>
</html>