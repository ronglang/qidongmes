<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
<%--     <link href="<%=basePath %>app/js/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>app/js/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>app/js/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>app/js/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>app/js/lib/jquery-validation/jquery.validate.min.js"></script> --%>
    
    	<link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script> 
		
		<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"	type="text/javascript"></script>
		<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js"	type="text/javascript"></script>
		<script	src="<%=basePath%>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js"	type="text/javascript"></script>
		<style type="text/css">
		
		#span-a{
		 font-size:20px;
		 font-weight: 600;
		 font-family: inherit;
		 color:purple;
		}
		#number{
		width:30px;
		height:21px;
		margin-top:5px;
		background: red;
		float: left;
		text-align: center;
		font-size:18px;
		font-weight: bold;
		}
		</style>
    	<script type="text/javascript">
    	
        var flag = 0;
		//如果websocket一直不刷新,20分钟判断一次;websocket,onmessage会自动修改状态,具体时间现场确定
		setInterval("timeQuery();", 60000*20);
		function timeQuery(){
			if (flag == 0) {
				queryTable();
			}
		}
        function itemclick(item)
        {
            if(item.text=="增加"){
            	alert("执行增加");
            }
            if(item.text=="修改"){
            	alert("修改了");
            }
            if(item.text=="删除"){
            	alert("删除掉了");
            }
            if(item.text=="查询"){
            	$.ligerDialog.open({url: 'search.html', height: 400,width:400 ,isResize: true }).show();
            }
        }
        $(function ()
        {
        	queryTable();
            myLoad();
        });
      //定时刷新表格去数据库查询数据
		function queryTable(){
			var url = '<%=basePath %>rest/mauProductOrderRecordManageAction/getMauProductOrderRecordDate?requestType=mapuser';
            getTable(url);
		}
		
        
        function myLoad(){
          	var basePath = "<%=basePath%>";
        		var urlPirfex = basePath.substring(7, basePath.length);
        		var url = "ws://"+urlPirfex+"productDepartmentShowPlanWebS";
        		var webSocket = new WebSocket(url);
        		webSocket.onerror = function(event) {
        			onError(event);
        		};
        	
        		webSocket.onopen = function(event) {
        			onOpen(event);
        		};
        	
        		webSocket.onmessage = function(event) {
        			onMessage(event);
        		};
        	
        		//这里获得反馈的值,进行参数的赋值
    			function onMessage(event) {
    				//在此处将数据封装到页面
    				var josnBean = eval('('+event.data+')');
    				if ("true" == josnBean) {
    					queryTable();
    				}
    				//改变状态
    				flag = 1;
    				//只执行一次,一个小时还没有刷新,修改状态,使页面刷新
    				setTimeout(function(){
    					flag = 0;
    				}, 60000*40);
    			}
        		
        		function onOpen(event) {
        			//TODO 发送房间号到后台去,获取房间号
        			webSocket.send('${id}');
//         			document.getElementById('data').innerHTML = '<br />' + ""+"start";
        		}
        	
        		function onError(event) {
        	// 		alert(event.data);
        		}
          }
          
          
         
          var index = 0;
        	function createData(map) {
        		series = [];
        		var data = map.dataList;
        		//初始化数据，全部设置为10;
        		var obj = new Object();
        		obj.name = legends;
        		obj.type = 'bar';
        		obj.barWidth = '20%';
        		obj.itemStyle = {
        			normal : {
        			label : {
        			show : true,
        			position : 'inside'
        				}
        			}
        		};
        		obj.data =data;
        		series.push(obj);

        	}
        function getTable(url){
        	window['g'] =
                $("#maingrid").ligerGrid({
                	url:url,
                    height: '100%',
                    columns: [
                   /*  { display: '生产令编号', name: 'productOrderCode', align: 'left'},
                    { display: '规格型号', name: 'materType'},
                   { display: '数量', name: 'amount'},
                   { display: '段长',name:'productPartLen'},
                   { display: '合同编号',name:'contractId'},
                   { display: '计划开工日期',name:'billDate'},
                   { display: '计划交单日期',name:'demandDate'},
                   { display: '实际生产日期',name:'trueProductDate'},
                   { display: '实际交单日期',name:'trueEndDate'} */
                    { display: '工单编号', name: 'courseCode', align: 'left'},
                    { display: '规格型号', name: 'proGgxh'},
                    { display: '数量', name: 'axisAmount'},
                    { display: '段长',name:'partLen'},
                    { display: '计划开工日期',name:'workStartDate'},
                    { display: '计划交单日期',name:'workEndDate'},
                    { display: '实际生产日期',name:'factStartTime'},
                    { display: '实际交单日期',name:'factEndTime'} ,
                    { display: '工序工时',name:'seqHours'} 
                  
                    ],  
                	onDblClickRow:function(rowdata, rowindex, rowDomElement){
                		var address = rowdata.address;
                		alert(address);
                		//window.open("<%=basePath %>rest/mauProcessMonitoringManageAction/toListDetailPage?productid="+productid);
                	},
                    rownumbers: true
                });
                $("#pageloading").hide();
        }

        function deleteRow()
        {
            g.deleteSelectedRow();
        }
        
        var groupicon = "";
        var form;
        var area_data = [
            { id: '11', value: '11', text: '北京市' },
            { id: '22', value: '22', text: '广州市' },
            { id: '33', value: '33', text: '苏州市' },
            { id: '44', value: '44', text: '杜洲市' }
        ];
        $(function ()
        {
            //创建表单结构 
            form = $("#form2").ligerForm({
                <%-- inputWidth: 170, labelWidth: 90, space: 40,
                
                fields: [
                { display: "实际开工日期 ", name: "time1", newline: false, type: "date", validate: {required: true,minlength: 5 }},
                { display: "到 ", name: "time2", newline: false, type: "date", validate: {required: true,minlength: 5 } },
              	{ display: "生产令编号", name: "productOrderCode", newline: false, type: "text"},
              	{ display: "规格型号", name: "materType", newline: false, type: "text"},
              	{ display: "合同编号", name: "contractId", newline: false, type: "text"},
                ],
                buttons:[
                         {text:"搜索",click:function(){
                        	//form.valid();
                        	var time1= $("[name=time1]").val();
                        	var time2= $("[name=time2]").val();
							var productOrderCode=$("[name=productOrderCode]").val();
							var materType=$("[name=materType]").val();
							var contractId=$("[name=contractId]").val();
							//alert(time1+"+"+time2+"+"+batchCode+"+"+objSort+"+"+objGgxh+"+"+address);
							//grid;
							//window.open('<%=basePath %>rest/mauProductOrderRecordManageAction/tomauProductOrderRecordTable?time1='+time1+'&time2='+time2+'&batchCode='+batchCode+'&objSort='+objSort+'&objGgxh='+objGgxh+'&address='+address);
							url = '<%=basePath %>rest/mauProductOrderRecordManageAction/getMauProductOrderRecordDate?time1='+time1+'&time2='+time2+'&productOrderCode='+productOrderCode+'&materType='+materType+'&contractId='+contractId;
							getTable(url);
                         }}
                         ],
                validate  : true --%>
            });
        });
        
        function search(){
        	var start = $("#start").val();
        	
        	
        }
        
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a>

 
 <div class="l-clear"></div>
	<div style="text-align: center;width: 100%" >
		<form id="form2" action="">
		</form>
		<%-- <input type="button" id="search" value=" " onclick="search()" style="cursor: pointer;padding-left: 26px;width: 73px;height:27px;
			background-color: transparent;background-image: url(<%=basePath %>app/js/lib/images/ListSearch.png);background-repeat: no-repeat;border: 0px;color: #0e6fbd;" /> --%>
	</div>
	<div ><div id="number">3</div><span id="span-a">生产令延迟情况</span></div>
    <div id="maingrid" style="overflow: hidden;">
    	
    </div>
   
  <div style="display:none;">
  
</div>
</body>
</html>

