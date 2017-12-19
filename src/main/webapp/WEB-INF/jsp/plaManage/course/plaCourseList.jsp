<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>core/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" />
		<link href="<%=basePath%>core/css/zTreeStyle/demo.css" rel="stylesheet" />
		<link href="<%=basePath%>app/css/newpage.css" rel="stylesheet" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.core-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/ztree/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
		
		<script type="text/javascript">
			var grid;
			var basePath  = '<%=basePath%>';
			var routeName = "plaCourseManage";
			var form;
			$(function(){
				form();
				queryTable();
			});
			function queryTable(){
				var param = initPatam();
				grid = $("#grid").ligerGrid({
							url : basePath + "rest/" + routeName+ "Action/getListPage?param="+param,
							//checkbox : true,
							columns : [
									{display : '序号',name : 'id',width : 1,hide:true},
									{display : '工作单类型',name : 'wsType',minWidth : 90,frozen:true},
									{display : '订单编号',name : 'orderCode',minWidth : 90,frozen:true},
									{display : '工作单编码',name : 'wsCode',minWidth : 120,frozen:true},
									{display : '计划是否已生成',name : 'planFlag',minWidth : 90},
									{display : '计划是否已下发',name : 'useFlag',minWidth : 90},
									{display : '型号规格',name : 'proGgxh',minWidth : 150},
									{display : '交货日期',name : 'demandDate',minWidth : 100},
									//{display : '总长度',name : 'totalAmount',minWidth : 90},
									{display : '开单日期',name : 'billDate',minWidth : 120},
									{display : '计划开始时间',name : 'ws_pstime',minWidth : 120},
									{display : '计划结束时间',name : 'ws_pdtime',minWidth : 120},
									{display : '是否完成',name : 'isFinish',minWidth : 90},
									{display : '工单进度',name : 'wsSchedule',minWidth : 100},
									{display : '操作',isAllowHide : false,minWidth : 150,frozen:true,

										render : function(row) {
											var html = ''; //<a href="#" onclick="javascript:edit('+ row.id+ ');return false;">编辑</a>&nbsp;&nbsp;&nbsp;';
											//html += '<a href="void(0);" onClick=\"javascript:genGD('+ row.id +',\''+ row.plan_flag + '\');return false;\">分解</a>&nbsp;&nbsp;&nbsp;';
											//html += '<a href="void(0);" onClick=\"javascript:genGD_detail('+ row.id +',\''+ row.pro_craft_code + '\',\''+row.pro_ggxh+'\',\''+row.pro_color+'\');return false;\">分解明细</a>&nbsp;&nbsp;&nbsp;';
											if(row.planFlag == "否"){
												html += '<a href="void(0);" onClick=\"javascript:genPla('+ row.id +');return false;\">生成计划</a>';
											}else if(row.planFlag == "是"){
												html += '<a href="void(0);" onClick=\"javascript:toLookrate('+ row.id +');return false;\">进度详情</a>';
											}
											return html;
										}
									},
									{hide : 'id',name : 'isFinish',width : 1},
									],
							pageSize : 10,
							rownumbers:true,
							onSelectRow:function (data, rowindex, rowobj){
								 getSonList(data.wsCode);
							},
						//	isChecked : f_isChecked,
						//	onCheckRow : f_onCheckRow,
						//	onCheckAllRow : f_onCheckAllRow,
							//width : '99%',
							height : 430,
							usePager:true,
							onSuccess : function(json, grid) {
								$("#pageloading").hide();
							},
							onError : function(json, grid) {
								$("#pageloading").hide();
							},
							toolbar :{
								items : [
						          {text:"插单生成",click:insertCourse,icon:'edit'},
						          { line: true } ,
						          {text:"生成所有",click:allCourse,icon:'add'},
						          { line: true } ,
						          {text:"合单",click:unionCourse,icon:'modify'}
								]
							}
						});
			}
			
			// 插单生成
			function insertCourse(){
				$.ligerDialog.confirm("是否生成所有插单",function(flag){
					if(flag){
						var url ="<%=basePath%>rest/plaCourseManageAction/setInsertPlaCoursePlan";
						$.post(url,{},function(data){
							if (data.success) {
								$.ligerDialog.success(data.msg);
								grid.reload();
							}else{
								$.ligerDialog.error(data.msg);
							}
							
						},"json");
					}
				});
			}
			
			// 生成所有工单
			function allCourse(){
				$.ligerDialog.confirm("是否生成所有工单",function(flag){
					if(flag){
						var url ="<%=basePath%>rest/plaCourseManageAction/setAllPlaCoursePlan";
						$.post(url,{},function(data){
							if (data.success) {
								$.ligerDialog.success(data.msg);
								grid.reload();
								
							}else{
								$.ligerDialog.error(data.msg);
							}
							
						},"json");
					}
				});
			}
			
			
			//合单
			function unionCourse(){
				ligerDialogOpen = $.ligerDialog.open({
					title:"合单",
					url:basePath+"rest/" + routeName+ "Action/tounionCourse",
					width :1000,
					height:500,
					showMax: false,
    		        showMin: false,
    		        isResize: true,
    		        slide: false,
    		        name:'repairAgain',
					buttons :[]
				});
				
			}
			
			var ligerDialogOpen;
			function toLookrate(id){
				ligerDialogOpen = $.ligerDialog.open({
					title:"工单任务进度",
					url:basePath+"rest/plaCourseManageAction/toLookRate?id="+id,
					type:'question' ,
					width :1000,
					height:500,
					buttons :[
					         {
					        	 text:"确定",onclick:function(){
					        		 ligerDialogOpen.close();
					        	 }
					         }
					]
				});
			}
			
			//查询条件
			function initPatam(){
				var param = {};
				var courseCode = $("[name=courseCode]").val();
				var orderCode = $("[name=orderCode]").val();
				var planFlag = $("[name=planFlag]").val();
				var proGgxh = $("[name=proGgxh]").val();
				if(courseCode != null  && courseCode !=""){
					param.courseCode = courseCode;
				}
				if(proGgxh != null  && proGgxh !=""){
					param.proGgxh = proGgxh;
				}
				if(orderCode != null  && orderCode !=""){
					param.orderCode = orderCode;
				}
				if(planFlag != null  && planFlag !=""){
					param.planFlag = planFlag;
				}
			//	var sear = form.getData();
				var result = JSON.stringify(param);
				return result;
			}
			
			//生成计划
			function genPla(id){
				var url =basePath +"rest/plaCourseManageAction/setPlaMacTask";
				$.post(url,{id:id},function(data){
					if(data.success){
						$.ligerDialog.success(data.msg);
						grid.reload();
					}else{
						$.ligerDialog.error(data.msg);
					}
				},"json");
			}
			
			//查看工单详情
			function getSonList(wsCode){
				var title = "工单号详情";
				var bean = {};
				bean.courseCode = wsCode;
				var param = JSON.stringify(bean);
				debugger;
				var sonGrid = $("#sonGrid").ligerGrid({
					title:title,
					url:basePath + "rest/plaCourseAxisManageAction/getList?param="+param,
					columns : [
								{display : '工作单编码',name : 'courseCode'},
								{display : '规格型号',name : 'proGgxh'},
								{display : '颜色',name : 'color'},
								{display : '轴数量',name : 'axisNum'},
								{display : '每轴长度',name : 'axisLength'},
					           ],
					//pageSize : 10,
					rownumbers:true,
					height : 250,
					usePager:false,
					onSuccess : function(json, grid) {
						$("#pageloading").hide();
					},
					onError : function(json, grid) {
						$("#pageloading").hide();
					}
				});
			}
			
			var flagData = [{id:"是",text:"是"},{id:"否",text:"否"}];
			
			function form(){
		    	form = $("#form2").ligerForm({
		            inputWidth: 150, labelWidth: 90, space:10,
		            fields: [
		            	{ display: "是否生成计划", name: "planFlag",  newline:true,type: "select" ,options: { data: flagData}},
		            	{ display: "订单编号", name: "orderCode", newline:false, type: "text"},
		            	{ display: "工单编号", name: "courseCode", newline:false, type: "text"},
		            	{ display: "规格型号", name: "proGgxh",  newline:false,type: "text" },
		            ],	
		        });
		    	//liger.get('scCode').setDisabled(); //设置只读
		    }
		</script>
  </head>
  
  <body>
  
	<div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<form id="form2" action="" >
		</form>
		<div class="liger-button" id = 'tj' onclick="queryTable()" style="float: right;position: absolute;right: 200px;top: 1px; ">提交</div>
	</div>
    <div id="grid" style="margin:0; padding:0;overflow:auto;height:432px">
    	
    </div>
    <div id="sonGrid" style="margin:0; padding:0;overflow:auto;height:250px"></div>
  </body>
</html>
