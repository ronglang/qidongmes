<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单修改类型和优先级</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <link href="<%= basePath%>app/js/sellManage/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
    <link rel="stylesheet" type="text/css" id="mylink"/>   
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%= basePath%>core/js/jquery-1.4.4.min.js" type="text/javascript"></script>    
    <script src="<%= basePath%>app/js/sellManage/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>  
    <script src="<%= basePath%>app/js/sellManage/lib/jquery.cookie.js"></script>
	<script type="text/javascript">
		var orderCode = '${orderCode}';
		var myForm;
		$(function(){
			initForm();
			setForm();
		});
		
		var orderTypes = [
			                 { id: '正常开单',text: '正常开单' },
			                 { id: '插单', text: '插单' },
			                 ];
		function initForm(){
			myForm = $("#myForm").ligerForm({
 		        inputWidth: 120, labelWidth: 80, space:10,
 		        fields: [
 		        	{ display: "订单编号", name: "orderCode", newline:false, type: "text"},
 		        	{
 		   			display : '订单类型',
 		   			name : 'orderType',
 		   			type : "select",
 		   			newline : false,
 		   			comboboxName: "orderType", options: { data: orderTypes},
 		   			editor:{
 		   				onSelected:function(value){
 		   					//debugger;
 		   					if(value== "正常开单"){
 		   						priLevels = [
 		   							{ id: '5', text: '5' },
 		   						];
 		   					}else{
 		   						priLevels = [
 		   					                 { id: '1',text: '1' },
 		   					                 { id: '2', text: '2' },
 		   					                 { id: '3', text: '3' },
 		   					                 { id: '4', text: '4' },
 		   					                 { id: '5', text: '5' },
 		   					                 ];
 		   					}
 		   					var priLevel = liger.get('priLevel');
 		   					 priLevel.set('data',priLevels);
 		   				}
 		   			}
 		   		},{
 		   			display : '优先级',
 		   			name : 'priLevel',
 		   			type : "select",
 		   			newline : false,
 		   			comboboxName: "priLevel"/*, options: { data: priLevels}*/,
 		   			 editor: { type: 'select',valueField:'id', textField:'text'}
 		   		},
 		       		{ display: "交货日期", name: "deliveryDate", newline:false,type: "date" ,editor:{showTime:true,format:"yyyy-MM-dd"}},
 		        ],
 		        
 		    });
			liger.get('orderCode').setDisabled();
		}
		
		function setForm(){
			var url ="<%= basePath%>rest/sellSalesOrderManageAction/getByOrderCode";
			$.post(url,{orderCode:orderCode},function(data){
				//debugger;
				myForm.setData(data);
			},"json");
		}
		
		function save(){
			//判断这个订单是否全部已经排产
			$.ligerDialog.confirm("是否确认修改订单类型,此操作将修改此订单下未下发的所有工单信息","提示信息",function(flag){
				debugger;
				if(!flag){
					return;
				}else if(flag){
				var bean = {};
				bean.orderCode = orderCode;
				bean.orderType = $("[name=orderType]").val();
				bean.deliveryDate = $("[name=deliveryDate]").val();
				bean.priLevel = $("[name=priLevel]").val();
				var param = JSON.stringify(bean);
				var url ="<%= basePath%>rest/sellSalesOrderManageAction/changeOrderType";
					$.post(url,{bean:param},function(data){
						if(data.success){
							$.ligerDialog.success("修改成功");
							setForm();
							return true;
						}else{
							$.ligerDialog.error("发生未知错误,请联系管理员");
							return false;
						}
					},"json");
					
					
				}else{
					$,ligerFialog.warn("出现位置错误,请练习管理员");
				}
			});			
		}
		
		//不用了,是不是排产wan,没关系啊,
		//判断这个订单是否全部已经排产
		function checkOrderCode(){
			var url ="<%=basePath %>rest/sellSalesOrderManageAction/checkOrderCode";
			$.post(url,{orderCode:oderCode},function(data){
				if(data.success){
					
				}else{
					
				}
			},"json");
		}
	</script>
	
  </head>
  
  <body>
    <div id="myForm"></div>
  </body>
</html>
