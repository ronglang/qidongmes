a<%@ page language="java" 
    import="java.util.*,com.xml.vo.BtUser,com.xml.service.BsCanshuService,com.xml.vo.BsDaima,com.xml.vo.WfBean,com.xml.vo.WfNode,com.xml.util.StringUtil"
	pageEncoding="utf-8"%>
	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
    String v_sqlcId=(null==request.getAttribute("sqlcId")?"":request.getAttribute("sqlcId").toString());
    String v_osVer=(null==request.getAttribute("osVer")?"":request.getAttribute("osVer").toString());
    BsCanshuService v_BsCanshuService=new BsCanshuService();
    List<BsDaima> v_bsdaima_list=(List<BsDaima>)v_BsCanshuService.getBsDaimaFromDmlb("ZZCHJDM");
    
    String v_czlx=(null==request.getAttribute("czlx")?"":request.getAttribute("czlx").toString()); 
    String v_viewReadOnly=("view".equals(v_czlx)?"readonly='readonly'":"");
    String v_viewDisabled=("view".equals(v_czlx)?"disabled='disabled'":"");
    String v_viewHeadClass=("view".equals(v_czlx)?"class='grouphead2'":"class='grouphead3'");
    String v_viewHeadtext=("view".equals(v_czlx)?"处理环节":"当前处理环节");
    String v_viewReadonlycolor=("view".equals(v_czlx)?"background-color: #cccccc":"");
    	
    
    //System.out.println("v_czlxv_czlxv_czlxv_czlxv_czlx:"+v_czlx+" v_viewBoolean"+v_viewBoolean);
    WfBean v_local_WfBean=(WfBean)request.getAttribute("WfBean");
    if (v_local_WfBean!=null){
    	if ("04".equals(v_local_WfBean.getLogShjg())){
    		v_viewHeadtext="当前处理环节--"+v_local_WfBean.getNodeName();
    		//v_viewReadOnly="";
    		//v_viewDisabled="";
    		//v_viewHeadClass="";
    		//v_viewReadonlycolor="";
    	}else{
    		v_viewHeadtext=v_viewHeadtext+"--"+v_local_WfBean.getNodeName();
    	}
    	
    }
    WfNode v_CurrWfNode=(WfNode)request.getAttribute("CurrWfNode");
    if (v_CurrWfNode!=null){
    	v_viewHeadtext=v_viewHeadtext+"--"+v_CurrWfNode.getNodeName();
    }    
    
    
    
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
	
	//$("#logShjg").combobox({ disabled: true });
	
 	//if(v_local_czlx=='view' ){
 	//    $("form input").prop("readonly", true);
 	//    $("form textarea").prop("readonly", true);
 	//    $("form input[type='radio']").attr("disabled",true);
 	//}

  $('#btn_shenhe').click(
	function(){
		var v_logShjg=$('#logShjg').combobox('getValue');
		if (v_logShjg==null || v_logShjg==""){
			alert("请选择审核结果");
			return;
		}
		
		 var cfmMsg= "确定要审核吗?";	
		 $.messager.confirm('确认', cfmMsg, function (r) {
				if(r){
					var status=$("#myform").form('validate');
					if(status){//表单验证通过
						var formData=$("#myform").serialize();
						$('#btn_shenhe').disabled=true;
						$.ajax({
							url:'wfyw_daibancheckSave.action?sqlcId='+<%=v_sqlcId%>+'&osVer='+<%=v_osVer%>,
							type:'post',
							async:true,
							cache:false,
							timeout: 100000,
							data:formData,
							error:function(){
								alert("服务器繁忙，请稍后再试！");
							},
							success: function(result){
								var data=new Array();
								data=result.split("★");
						        if(data[0]=="-1"){
						            alert(data[1]);
						         }else{
					             	alert(data[1]);
					             	fanhui(); 
						         }
						     }
						});	
						$('#btn_shenhe').disabled=false;
						
					}
				
				} 
			 })
	
	}
  );
  
});/////////////////////////////////////////////////////////
	



function fanhui(){
  var v_retObj=new Object();
  v_retObj.daibanResult="refresh";
  
  
  if (window.top!=window.self){
	window.parent.returnValue=v_retObj;
	window.parent.close();  
  }else{
	window.returnValue=v_retObj;
	window.close();  
  }
  
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

		<form id="myform" name="myform" method="post"
			action="">
		       <table width="100%" height="100%"  align="center" border="1px solid" style="<%=v_viewReadonlycolor %>">
		       	    <tr><td colspan="4" height="25px;" <%=v_viewHeadClass %>><%=v_viewHeadtext %></td></tr>
					<tr>
					   <td>
							<DIV align="right">
								处理结论
								<span class="red">*</span>
							</DIV>
					   </td>				
						<td colspan="2">
							<select id="logShjg" name="logShjg" class="easyui-combobox" 
								style="width: 190px;" 
								data-options="required:true" <%=v_viewDisabled %>
								
								>
							  <option value="">==请选择==</option>
							  <%
							  
							  for (BsDaima v_BsDaima :v_bsdaima_list){
							    String v_dm_dmz=v_BsDaima.getDmDmz();
							    String v_dm_dmmc=v_BsDaima.getDmDmmc();
							    String v_tmp_logShjg="abc";
							    if (v_local_WfBean!=null){
							    	v_tmp_logShjg=StringUtil.dowithNull(v_local_WfBean.getLogShjg().toString());
							    }
							    if (v_tmp_logShjg.equals(v_dm_dmz)){
							  %>
							   <option value="<%=v_dm_dmz %>" selected="selected"><%=v_dm_dmmc%></option>
							  <%
							    }else{
							   %>
							   <option value="<%=v_dm_dmz %>" ><%=v_dm_dmmc%></option>
							   <% 	
							    }}
							  %>
							</select>
						</td>
						<td>
						</td>
					</tr>    
					<tr>
					   <td>
							<DIV align="right">
							处理意见 &nbsp;&nbsp;
							</DIV>
					   </td>				
						<td colspan="2" >
							<textarea class="easyui-validatebox" id="logShbz" name="logShbz"
								 style="height: 90px; width: 600px;<%=v_viewReadonlycolor %>" <%=v_viewReadOnly %> >${WfBean.logShbz}</textarea>
						</td>
						<td>
						</td>
					</tr>    
					<% if ("view".equals(v_czlx)){ %>
					<tr>
					   <td>
							<DIV align="right">
								处理人
							</DIV>
					   </td>				
						<td>
							<input class="easyui-validatebox" id="logShrxm" name="logShrxm" readonly="readonly" value="${WfBean.logShrxm}" style="<%=v_viewReadonlycolor %>"/>
						</td>
						<td>
						    处理日期
						</td>
						<td>
						   <input class="easyui-validatebox" id="logShsj" name="logShsj" readonly="readonly" value="${WfBean.logShsj}" style="<%=v_viewReadonlycolor %>"/>
						</td>
					</tr>  
					<%} %> 
					
					<% if ("add".equals(v_czlx)){ %>
					<tr >
						<td colspan="9" style="height:25px;">
							<DIV align="center">
		                        <a href="javascript:void(0);" id="btn_shenhe" class="easyui-linkbutton" 
		                           onclick=""
							       data-options="iconCls:'icon-save'">审核</a>
							       
						      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
		                        <a href="javascript:void(0);" id="btn_fanhui" class="easyui-linkbutton" 
		                           onclick="fanhui();"
								   data-options="iconCls:'icon-cancel'">返  回</a>								
							</DIV>
						</td>
		
					</tr>	
					<%} %> 
								
								     
		       </table> 				
		</form>		

	</body>
</html>