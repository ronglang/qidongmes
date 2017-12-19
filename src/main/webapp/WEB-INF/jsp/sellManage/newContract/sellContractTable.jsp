<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath%>core/css/public.css" rel="stylesheet" />
		<link href="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>core/js/jquery-1.7.2.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
	    <script src="<%=basePath%>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	    
		<script src="<%=basePath%>core/js/cssBase.js" type="text/javascript"></script>
    <script type="text/javascript">
   		var basePath = '<%=basePath%>';
    var url,scCode,cusCode,agentBy;
    var grid;
    var state = [
                 {id:'1',text:'已分解' },
                 {id:'2',text:'未分解'}
                ];
        $(function ()
        {
        	var url = '<%=basePath %>rest/sellContractManageAction/loadSellContractManage';
        	//window['g'] =
           grid =    $("#maingrid").ligerGrid({
                    height: '99%',
                    title:'生产通知管理',
                    url: url,
                    checkbox: true,
                    columns: [
                    { display: '合同编号', name: 'sc_code', align: 'left', width:'10%' },
                    { display: '签订日期', name: 'sc_date', width:'8%' },
                    { display: '客户名称', name: 'cus_name', width:'10%' },
                    { display: '经办人', name: 'agent_by' , width:'8%'},
                    { display: '客户编号', name: 'cus_code', width:'9%' },
                    { display: '订单编号', name: 'order_code' , width:'8%'},
                    { display: '分解状态', name: 'sc_state', width:'8%' },
                    { display: '分解总长度', name: 'total_len' , width:'8%'},
                    { display: '已生成工单长度', name: 'complete_len', width:'8%' },
                    { display: '生产通知单类型', name: 'ws_type' , width:'8%'},
                    { display: '工单状态', width:'8%',
                    	render :function(row,idx,val){
    						var t = row.total_len;
    						var c = row.complete_len;
    						if(Number(t) > Number(c)){
    							return '可以生成工单';
    						}
    						else if(Number(t) == 0 && Number(c) == 0){
    							return '';
    						}
    						else{
    							return '工单已全部下发';
    						}
                    	}	
                    },
                    { hide:true,name:'id',width:0.1},
                    { display:'操作列',width:85,
                    	render:function(row,idx){
                    		/* var sta = row.scState;
                    		if(sta == '已分解'){
    	                		var ur='<a href="void(0)" onClick=\"javascript:detailInfo(\''+row.scCode+'\'); return false;\">详情'+'</a>';
    	               			return ur;
                    		}
                    		return ''; */
                    		//不管有没分解，都可以分解
                    		return '<a href="void(0)" onClick=\"javascript:detailInfo(\''+row.sc_code+'\'); return false;\">详情'+'</a>';
                    	}	
                    }
                    ],
                    toolbar: {
                        items: [
                        { text: '增加', click: addContract, icon: 'add' },
                        { line: true },
                        { text: '修改', click: updateContract, icon: 'modify' },
                        { line: true },
                        { text: '删除', click: del, icon:'delete' },
                        { line: true },
                        /* { text: '详情', click: toDetail, icon:'view' },
                        { text: '详情', click: detailInfo, icon:'view' }, 
                        { line: true }, */
                        { text: '分解', click: decomposition, icon:'down' }
                        ]
                    },
                    rownumbers: true
                   // autoFilter: true
                });
                $("#pageloading").hide();
                
        	
            //grid(url);
            getSelect();
           // form();
           
        });

	/* 	function grid(url){
			window['g'] =
            $("#maingrid").ligerGrid({
                height: '90%',
                title:'生产通知管理',
                url: url,
                checkbox: true,
                columns: [
                { display: '合同编号', name: 'sc_code', align: 'left', width:'10%' },
                { display: '签订日期', name: 'sc_date', width:'8%' },
                { display: '客户名称', name: 'cus_name', width:'10%' },
                { display: '经办人', name: 'agent_by' , width:'8%'},
                { display: '客户编号', name: 'cus_code', width:'9%' },
                { display: '订单编号', name: 'order_code' , width:'8%'},
                { display: '分解状态', name: 'sc_state', width:'8%' },
                { display: '分解总长度', name: 'total_len' , width:'8%'},
                { display: '已生成工单长度', name: 'complete_len', width:'8%' },
                { display: '生产通知单类型', name: 'ws_type' , width:'8%'},
                { display: '工单状态', width:'8%',
                	render :function(row,idx,val){
						var t = row.total_len;
						var c = row.complete_len;
						if(Number(t) > Number(c)){
							return '可以生成工单';
						}
						else{
							return '工单已全部下发';
						}
                	}	
                },
                { hide:true,name:'id',width:0.1},
                { display:'操作列',width:85,
                	render:function(row,idx){
                		return '<a href="void(0)" onClick=\"javascript:detailInfo(\''+row.sc_code+'\'); return false;\">详情'+'</a>';
                	}	
                }
                ],
                toolbar: {
                    items: [
                    { text: '增加', click: addContract, icon: 'add' },
                    { line: true },
                    { text: '修改', click: updateContract, icon: 'modify' },
                    { line: true },
                    { text: '删除', click: del, icon:'delete' },
                    { line: true },
                    { text: '分解', click: decomposition, icon:'down' },
                    { line: true }
                    ]
                },
                rownumbers: true
               // autoFilter: true
            });
            $("#pageloading").hide();
		} */

		
		function itemclick(item)
        {
            alert("对不起，你没有‘"+item.text+"’权限");
        }
		
		//挪单处理
		function singleMove(){
			// TODO 跳转到挪单页面
		    var url = basePath+"rest/sellContractPlanBatchManageAction/toListPage?scCode="+scCode;
	    	   //var p = 'target=_blank';
	    	   var w = window.screen.width * 0.9;
	    	   var h = window.screen.height * 0.8;
	    	   var p = 'width='+w+',height='+h;
			   //window.open(url,'订单下发',p);    	
			   //$.ligerDialog.open(url);
	    	   $.ligerDialog.open({ 
					url: decodeURI(url), 
					height: 500,
					width: w,
					modal:true, 
					buttons: [ 
					{text: '关闭', onclick: function (item, dialog) { 
						dialog.close(); 
					} } 
					] });
		}
		
		
		
		//删除合同
		function del(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	var dataArray = [];
        	//////debugger;
        	if(li.length<1 ){
        		$.ligerDialog.warn("请选择删除行");
        		return;
        	}
        	for(var i = 0; i<li.length;i++){
        		var bean = new Object();
        		var beans = li[i];
        		bean.id = beans.id;
        		var scState = beans.scState;
        		if(scState == '已分解'){
        			//已分解的可以删除，但已下发的不能删
        			//alert('勾选删除内容包含已分解内容，不能删除');
        		}
        		dataArray.push(bean);
        	}
        	var date = JSON.stringify(dataArray);
        	////debugger;
        	$.ligerDialog.confirm('确定删除选择内容?', function (yes) {
        		if(yes){
        			$.ajax({
                		url:'<%=basePath %>rest/sellContractManageAction/delContracts?data='+date,
                		type:'POST',
                		dataType:'json',
                		success:function(json){
                			if(json.success){
                				ajaxSuccess(json);
	                			//url = '<%=basePath %>rest/sellContractManageAction/loadSellContractManage';
	                			//grid(url);
	                			grid.reload();
                			}
                			else{
                				ajaxError(json);
                			}
                		},
                		error:function(json){
                			ajaxError(json);
                			//grid();
                		}
                		
                	});
        		}else{
        			return;
        		}
        		
        	});
        	
		}
		
		//增加合同
		function addContract(){
			var width = document.body.clientWidth * 0.9;
			var heigth = document.body.clientHeight * 0.9;
        		$.ligerDialog.open({

    		        height: heigth,

    		        width: width,

    		        title: "增加生产通知('<font style='color: red'>*</font>'号为必填)",

    		        url: "<%=basePath %>rest/sellContractManageAction/toNewFormPage",

    		        showMax: false,

    		        showMin: false,

    		        isResize: true,

    		        slide: false,

    		        name:'repairAgain',

    		        buttons: [
    		            { text: '保存', onclick: function (item, dialog) {
    		        	var manger = dialog.frame.$("#maingrid").liger();
    		        	var li = manger.getData();
						////debugger;
						if(li.length<1){
							$.ligerDialog.warn("请先点击'提交'按钮");
							return;
						}
    		        	var data =JSON.stringify(li);
    		        	var scCode=dialog.frame.$("[name=scCode]").val();
    		        	var scDate=dialog.frame.$("[name=scDate]").val();
    		        	var cusName=dialog.frame.$("[name=cusName]").val();
    		        	var cusCode=dialog.frame.$("[name=cusCode]").val();
    		        	var agentBy=dialog.frame.$("[name=agentBy]").val();
    		        	var orderCode=dialog.frame.$("[name=orderCode]").val();
    		        	var wsType=dialog.frame.$("[name=wsType]").val();
    		        	if(scCode==""||scDate==""||cusName==""||cusCode==""||agentBy==""||orderCode==""||wsType==""){
    		        		$.ligerDialog.warn("请选择将数据填写完整");
    		        		return;
    		        	}
    		        	var bean = new Object();
    		        	bean.scCode = scCode;
    		        	bean.scDate = scDate;
    		        	bean.cusName = cusName;
    		        	bean.cusCode = cusCode;
    		        	bean.agentBy = agentBy;
    		        	bean.orderCode = orderCode;
    		        	bean.wsType = wsType;
    		        	var be = JSON.stringify(bean);
    		        	$.ajax({
    		        		url:"<%=basePath %>rest/sellContractManageAction/saveContract",
    		        		type:'POST',
    		        		dataType: 'json',
    		        		data:"data="+encodeURIComponent(encodeURIComponent(data))+"&bean="+be,
    		        		success:function(msg){
    		        			if(msg.success){
	    		        			dialog.close();
	    		        			$.ligerDialog.success("保存成功");
	    		        			//url = '<%=basePath %>rest/sellContractManageAction/loadSellContractManage';
	    	    		        	//grid(url);
	    	    		        	grid.reload();
    		        			}else{
    		        				$.ligerDialog.error("保存失败,发生了未知错误");
    		        			}
    		        			
    		        		},
    		        		error:function(){
    		        			$.ligerDialog.error("保存失败,发生了未知错误");
    		        		}
    		        	});
    		        }

    		        },
    		        
    		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

    		    });
        		
		}
		
		//修改合同
		function updateContract(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	if(li.length>1 || li.length<1 ){
        		$.ligerDialog.warn("请选择你需要修改的一行");
        		return;
        	}
        	for(var i = 0; i<li.length;i++){
        		//var beans = new Object();
        		var width = document.body.clientWidth * 0.9;
				var heigth = document.body.clientHeight * 0.9;

        		var id = li[i].id;
        		$.ligerDialog.open({

    		        height: heigth,

    		        width: width,

    		        title: "修改生产通知('<font style='color: red'>*</font>'号为必填)",

    		        url: "<%=basePath %>rest/sellContractManageAction/toNewFormPage?id="+id,

    		        showMax: false,

    		        showMin: false,

    		        isResize: true,

    		        slide: false,

    		        name:'repairAgain',

    		        buttons: [
    		                  { text: '保存', onclick: function (item, dialog) {
    		        	var manger = dialog.frame.$("#maingrid").liger();
    		        	var li = manger.getData();
						if(li.length<1){
							$.ligerDialog.warn("请先点击'提交'按钮");
							return;
						}
    		        	var data =JSON.stringify(li);
    		        	var da = encodeURIComponent(encodeURIComponent(data));
						debugger;
    		        	var scCode=dialog.frame.$("[name=scCode]").val();
    		        	var scDate=dialog.frame.$("[name=scDate]").val();
    		        	var cusName=dialog.frame.$("[name=cusName]").val();
    		        	var cusCode=dialog.frame.$("[name=cusCode]").val();
    		        	var agentBy=dialog.frame.$("[name=agentBy]").val();
    		        	var orderCode=dialog.frame.$("[name=orderCode]").val();
    		        	//var proType = dialog.frame.$("[name=proType]").val();
    		        	if(scCode==""||scDate==""||cusName==""||cusCode==""||agentBy==""||orderCode==""){
    		        		$.ligerDialog.warn("请选择将数据填写完整");
    		        		return;
    		        	}
    		        	var bean = new Object();
    		        	bean.scCode = scCode;
    		        	bean.scDate = scDate;
    		        	bean.cusName = cusName;
    		        	bean.cusCode = cusCode;
    		        	bean.agentBy = agentBy;
    		        	bean.orderCode = orderCode;
    		        	
    		        	//bean.serialize();
    		        	var be = JSON.stringify(bean);
    		        	debugger;
    		        	$.ajax({
    		        		url:"<%=basePath %>rest/sellContractManageAction/saveContract?id="+id,
    		        		type:'POST',
    		        		dataType: 'json',
    		        		data:"&data="+da+"&bean="+be,
    		        		success:function(msg){
    		        			if(msg.success){
	    		        			dialog.close();
	    		        			$.ligerDialog.success("保存成功");
	    		        			//url = '<%=basePath %>rest/sellContractManageAction/loadSellContractManage';
	    	    		        	//grid(url);
	    	    		        	grid.reload();
    		        			}else{
    		        				$.ligerDialog.error("保存失败,发生了未知错误");
    		        			}
    		        			
    		        		},
    		        		error:function(){
    		        			$.ligerDialog.error("保存失败,发生了未知错误");
    		        			//dialog.close();
    		        		}
    		        	});
    		        }

    		        },
    		        
    		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

    		    });
        		
        		
        	}
			
		}
		
		// 合同详情
		function toDetail(){
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	if(li.length>1 || li.length<1 ){
        		$.ligerDialog.warn("请选择你需要修改的一行");
        		return;
        	}
        	for(var i = 0; i<li.length;i++){
        		//var beans = new Object();
        		var width = document.body.clientWidth * 0.9;
				var heigth = document.body.clientHeight * 0.9;
				var flag = 1;
        		var id = li[i].id;
        		$.ligerDialog.open({

    		        height: heigth,

    		        width: width,

    		        title: '修改生产通知',

    		        url: "<%=basePath %>rest/sellContractManageAction/toNewFormPage?id="+id+"&flag="+flag,

    		        showMax: false,

    		        showMin: false,

    		        isResize: true,

    		        slide: false,

    		        name:'repairAgain',

    		        buttons: [
    		        
    		            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }]

    		    });
        		
        		
        	}
			
			
		}
		
		
		//分解合同
	    function decomposition(){
	    	var rows = grid.getSelectedRows();
	    	if (rows.length<=0) { $.ligerDialog.warn('未选中领料单'); return; }
	    	for(var i = 0; i<rows.length;i++){
	    		if(rows[i].scState=="已分解"){
	    			$.ligerDialog.warn('温馨提示：第'+(i+1)+'状态错误，该合同已经分解了');
	    			return;
	    		};
	    	}
	    	if(window.confirm('温馨提示：确认分解？分解后为生产令后合同不可以再编辑')){
	    		var ids = []; 
	            for (var i in rows) { 
	            	ids.push(rows[i].id);
	            };
	    		$.ajax({
	  			  url:basePath+"rest/sellContractManageAction/decSellContractDetail?ids="+ids, 
	  			  dataType: 'json',
	  			  type: "post",
	  			  success:function(json){
	  				  //submitSuccess(json);
	  				  //ajaxError(json);
	  				  ajaxSuccess(json);
	  				  grid.reload();
	  			  },
	  			  error: function(json) {
	  				  ajaxError(json);
	  				//$.ligerDialog.error("温馨提示：分解失败，请联系管理员！");
	  			   }
	  			});
	    	}
	    }
		
		
		function form(){
        	
       	 //创建表单结构 
           form = $("#form2").ligerForm({
               inputWidth: 200, labelWidth: 65, space: 100,
               fields: [
               	{ display: "状态 ", name: "state", newline: false, type: "select",editor: {valueField:'text',data:state}},
               	{ display: "合同编号", name: "scCode", newline: false, type: "text"},
             	{ display: "订单编号", name: "orderCode", newline: false,type: "text"},
               ],
               validate  : true
           });
       	
       }
		
		function getSelect(){
			$.ajax({
	    		url:'<%=basePath %>rest/sellContractManageAction/getscCode',
	    		type:'POST',
	    		dataType:'json',
	    		success:function(str){
	    			scCode = str;
	    			$.ajax({
	    	    		url:'<%=basePath %>rest/sellContractManageAction/getcusCode',
	    	    		type:'POST',
	    	    		dataType:'json',
	    	    		success:function(str){
	    	    			cusCode = str;
	    	    			$.ajax({
	    	    	    		url:'<%=basePath %>rest/sellContractManageAction/getagentBy',
	    	    	    		type:'POST',
	    	    	    		dataType:'json',
	    	    	    		success:function(str){
	    	    	    			agentBy = str;
	    	    	    			form(scCode,cusCode,agentBy);
	    	    	    		}
	    	    	    	});
	    	    		}
	    	    	});
	    		}
	    	});
			
		}
		
       
       function search(){
       	//form.valid();
	       	var serchState= $("[name=state]").val();
	       	var serchscCode= $("[name=scCode]").val();
			var orderCode=$("[name=orderCode]").val();
			//var serchAgentBy=$("[name=agentBy]").val();
			//url = '<%=basePath %>rest/sellContractManageAction/getComSerchSellContract?serchState='
			url = '<%=basePath %>rest/sellContractManageAction/loadSellContractManage?scState='
					+serchState+'&scCode='+serchscCode+'&orderCode='+orderCode;
			//grid(url);
			grid.set({'url':url});
			grid.loadServerData();
        }
       
       //1.打开详情页面，显示批次信息。 
       //2.在批次列表显示生产段长，已下发段长。点详情，进入下发页面。
       //3.下发页面，显示detail已下发列表，在form中列出未下发数量 ，录入本次下发段长，保存；
       //目前只能用三级页面
       function detailInfo(scCode){
    	   //var url = basePath+"rest/sellContractDetailManageAction/toListPage?id="+id;
    	   	    var url = basePath+"rest/sellContractPlanBatchManageAction/toListPage?scCode="+scCode;
    	   //var p = 'target=_blank';
    	   var w = window.screen.width * 0.8;
    	   var h = window.screen.height * 0.5;
    	   var p = 'width='+w+',height='+h;
		   //window.open(url,'订单下发',p);    	
		   //$.ligerDialog.open(url);
    	   $.ligerDialog.open({ 
				url: decodeURI(url), 
				height: 500,
				width: w,
				modal:true, 
				buttons: [ 
				{text: '关闭', onclick: function (item, dialog) { 
					dialog.close(); 
				} } 
				] });
       }
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
	<div style="height:100%;width:100%;">
		 <div class="l-loading" style="display:block" id="pageloading"></div>
			<div style="text-align: center;width: 100%;position: relative;;"  >
				<form id="form2" action="" >
				</form>
				<div class="liger-button" onclick="search()" style="float: right;position: absolute;right: 180px;top: 3px; ">查询</div>
			</div>
		    <div id="maingrid"   style="height:100%;width:100%;"></div>
	</div>
   
</body>
</html>


