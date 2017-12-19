	var g,g1,g2;	
	$(function(){
		 urlStr = basePath+"rest/sellContractManageAction/loadSellContractManage",
		 f_initGrid(urlStr);
	}); 
	var flag = false;
   //表格展示列表0
    function f_initGrid(urlStr)
    { 
    	var head = $('#head');
        g = $("#maingrid").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id'},
		            { display: '合同编号', name: 'scCode'},
		            { display: '签订日期', name: 'scDate'},
		            { display: '合同金额', name: 'scMoney'},
		            { display: '客户名称', name: 'cusName'},
		            { display: '经办人', name: 'agentBy'},
		            { display: '客户编号', name: 'cusCode'},
		            { display: '状态', name: 'scState'}
		        ], 
		        url:urlStr, 
		        pageSize: 10, 
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        width: '94%',
		        height:'99%',
		        enabledEdit: true, 
		        //isScroll: false, 
		        checkbox:true,
	            width: '100%',
	            //toolbar:head,
		        onSuccess:function (json, grid){
		        	$("#pageloading").hide(); 
		        },
				onError:function (json, grid){
		        	$("#pageloading").hide(); 
		        },
	            onSelectRow: function (rowdata, rowindex){
	                $("#txtrowindex").val(rowindex);
	            }
	           
            });
    }    //多条件查询
    function ComprehensiveSerch(){
    	var serchState= $("#serchState").val();
    	debugger;
    	var serchscCode= $("#serchscCode").val();
		var serchcusCode=$("#serchcusCode").val();
		var serchAgentBy=$("#serchAgentBy").val();
		var urlStr = basePath+'rest/sellContractManageAction/getComSerchSellContract?serchState='
					+serchState+'&serchscCode='+serchscCode+'&serchcusCode='+serchcusCode+'&serchAgentBy='+serchAgentBy;
		f_initGrid(urlStr);
    }
    //重置，为了解决小的bug，采用重新加载
    function restload(){
    	window.location.reload(true);
    }
    //新增一行
    function addNewRow(){
    	var adddiv=$('#adddiv');
    	//var adddiv=$('#aaaa');
    	f_initGrid1();
        $.ligerDialog.open({
            height:400,
            width:1000,
            height:'100%',
            title : '新增合同',
            showMax: true,
            showToggle: false,
            showMin: true,
            isResize: true,
            slide: false,
            target:adddiv,
            buttons: [{
						text: '删除行', 
						onclick: function (i, d) {
							var row = g1.getSelectedRows();
							if (row.length>0){
								g1.deleteSelectedRow();
							}else{
								$.ligerDialog.warn('请选择行！');
								return;
							}
						}
		    		  },
                      {
    					text: '新增行', 
    					onclick: function (i, d) {
    						g1.addRow();
    					}
            		  },
                      { 
            			text: '保存', 
            			onclick: function (i, d) {
            				
            				var row={};
            				row["scCode"] = $("#scCode").val();
            				row["scDate"] = Todate1($("#scDate").val());
            				row["scMoney"] = $("#scMoney").val();
            				row["cusName"] =$("#cusName").val();
            				row["cusCode"]= $("#cusCode").val();
            				row["agentBy"]= $("#agentBy").val();
            				row["createDate"]=Todate1($("#createDate").val());
            				row["createBy"]= $("#createBy").val();
            				
            				var rows = g1.getSelectedRows();
            				if(!rows || rows == null || rows.length == 0) alert("请选择批次");
            				
            				if(!flag || row["scCode"] == "" || row["scDate"] == "" 
            					||row["scMoney"] == ""||row["cusName"]==""
            						||row["cusCode"]=="" || row["agentBy"] =="" 
            							|| row["createDate"] =="" || row["createBy"] == ""){
            					$.ligerDialog.warn('字段不能为空！');
            					return;
            				}
            				
            				for(var j = 0;j < rows.length;j++){
	            				var noEmpty = rows[j];
	            				if(noEmpty.batCode == "" || noEmpty.pbatDetailCode == "" || noEmpty.proGgxh == ""
	            					|| noEmpty.reqPeriodLength == "" || noEmpty.reqAmount == "" || noEmpty.reqUnit == ""
	            						|| noEmpty.createBy == ""){
	            					$.ligerDialog.warn('字段不能为空！');
	            					$.ligerDialog.warn(Todate(noEmpty.createDate));
	            					return;
	            				}
            				}
            				
            				//debugger;
            				if(rows.length<=0){
            					$.ligerDialog.warn("请选择全部的明细");
            					return;
            				}
            				var dataArr = [];
            				for(var j = 0;j < rows.length;j++){
            					var dataBean = new Object();
            					var bean = rows[j];
            					dataBean.scCode = $("#scCode").val();
            					dataBean.batCode = bean.batCode;
            					dataBean.pbatDetailCode =bean.pbatDetailCode;
            					dataBean.proGgxh =bean.proGgxh;
            					dataBean.reqPeriodLength =bean.reqPeriodLength;
            					dataBean.reqAmount =bean.reqAmount;
            					dataBean.reqUnit =bean.reqUnit;
            					//var test = bean.deliveDate;Tue Jul 25 2017 00:00:00 GMT+0800
            					//var test2 = "2017-07-22 00:00:00";
            					//dataBean.deliveDate ="2017-07-22 00:00:00";
            					dataBean.deliveDate =Todate(bean.deliveDate);
            					dataBean.createBy =bean.createBy;
            					dataBean.createDate =Todate(bean.createDate);
            					dataBean.proCraftName =bean.proCraftName;
            					dataBean.proCraftCode =bean.proCraftCode;
            					dataBean.proColor =bean.color;
            					dataArr.push(dataBean);
            				}
            				var data = JSON.stringify(dataArr);
            				
            				//new
            				row["sellContractPlanBatchLstStr"] = data;
            				alert(row["sellContractPlanBatchLstStr"]);
            				$.ajax({
            					  url: basePath+"rest/sellContractManageAction/addSellContractManage",
            					  dataType: 'json',
            					  data:row,
            					  success:function(json){
            						  ajaxError(json);
            						  window.location.reload(true);
            					  },
            					  error: function(json) {
            						  ajaxError(json);
            					      d.hide();
            					   }
            					});
            				//debugger;
            			/*	$.ajax({
          					  url: basePath+"rest/sellContractManageAction/addSellContractManage",
          					  dataType: 'json',
          					  data:row,
          					  success:function(msg){
          						$.ajax({
                  				  url:basePath+"rest/sellContractManageAction/addSellContractPlanBatch", 
                  				  contentType: "application/json",
                				  dataType: 'json',
                				  data: data,
                				  type: "post",
            					  success:function(msg){
            						 window.location.reload(true);
            					  },
            					  error: function(msg) {
            					       d.hide();
            					   }
            					});
          					  },
          					  error: function(msg) {
          					       d.hide();
          					   }
          					});*/
        					
            				}
            		   },
                       { 
            			  text: '关闭', 
            			  onclick: function (i, d) {d.hide();}
            		   }]
        });
    }
    //初始化表格1
    function f_initGrid1(){ 
    	var index = 1;
    	 var ggxh="";
    	 var colour="";
    	 var proCraftName = "";
    	 var proCraftCode = "";
        g1 = $("#addNew2").ligerGrid({
		        columns: [
		            { display: '批次号', name: 'batCode',editor: { type: 'text' },width:90},
		            { display: '批次规格型号编码', name: 'pbatDetailCode', editor: {type: 'text'},width:110},
		            { display: '规格型号', name: 'proGgxh',type: "select",  editor: { type: 'select',valueField:'text',
	                       ext:function(){
                            var options =  {
                                  url:basePath+'rest/sellContractDetailManageAction/getAllProGGXH',
                                  parms:{}
                              }; 
                          return options;
			                      },
					            onSelected:function(value){
				            		//debugger;
					            	ggxh=value;
			                }
			           },width:90, },
		            { display: "颜色", name: "color", width:50, type: "select",
		                editor: { type: 'select',valueField:'text',
		                       ext:function(){
		                    	  // debugger;
		                               var options =  {
		                                     url:basePath+'rest/sellContractDetailManageAction/getGGXHColor',
		                                     parms:{proGgxh:ggxh}
		                                 }; 
		                             return options;
		                         },
		                         onSelected:function(value,rowdata){
		     	            		//debugger;
		                        	 colour=value;
		                        	
		                        },
		                }
			           
		          },
		          { display: '产品工艺名称', name: 'proCraftName', type: "text",/*editor: {type: 'text'}, *//*render: function(rowdata){
		        		 //debugger;
		        		 $.post(basePath+'rest/sellContractDetailManageAction/getProCraftName',{proGgxh:rowdata.proGgxh,color:rowdata.color},function(data){
		        			// debugger;
		        			 if (data==null) {
							}else{
								debugger;
								rowdata.proCraftName = data.proCraftName;
								rowdata.proCraftCode = data.proCraftCode;
							}
		        		 },"json");
		        		 
		        	 } ,*/width:110},
		            { display: '段长', name: 'reqPeriodLength',editor: { type: 'text' },
		            	render:function(rowdata){
		            		
		            		var reg=/^\d+(\.*\d{0,2})(\*\d+)(\+)\d+(\.*\d{0,2})(\*\d+)$/;
		            		var str = rowdata.reqPeriodLength;
		            		//debugger;
		            		if(str == "" || str == null){
		            			return "";
		            		}else{
		            			if(reg.test(str)){
			    					var arr = str.split("+");
			    					var num1 = arr[0].split("*")[1];
			    					var num2 = arr[1].split("*")[1];
			    					rowdata.reqAmount = parseInt(num1) + parseInt(num2);
			    					flag = true;
			    					index = true;
			    					return str;
			    				}else{
			    					if(index == 1){
			    						alert("段长格式不正确，请重新设置！格式例：0.2*2+1.2*3");
			    						index++;
			    						flag = false;
			    						debugger;
			    						return "";
			    					}
			    					
			    				}
		            			
		            		}
		    				
		            	}
		            	,width:90},
		            
//		            { display: '数量', name: 'reqAmount',editor: { type: 'text' },width:90},
		            { display: '数量', name: 'reqAmount',width:90},
		            { display: '单位', name: 'reqUnit',editor: { type: 'text' },width:90},
		            { display: '交货时间', name: 'deliveDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:90},
		            { display: '录入人员', name: 'createBy',editor: { type: 'text' },width:90},
		            { display: '录入时间', name: 'createDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:90},
		            { hide: '生产工艺编码', name: 'proCraftCode',width:1,render : function(rowdata){
			        	   $.post(basePath+'rest/sellContractDetailManageAction/getProCraftName',{proGgxh:rowdata.proGgxh,color:rowdata.color},function(data){
 		        			// debugger;
 		        			 if (data==null) {
 							}else{
 								 debugger;
 								//$("#proCraftName").val(data.proCraftName);
 								rowdata.proCraftName = data.proCraftName;
 								rowdata.proCraftCode = data.proCraftCode;
 								return rowdata;
 							}
 		        		 },"json");
			           }}
		        ],  
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        width: '100%',
		        height:'94%',
		        enabledEdit: true, 
		        isScroll: false, 
		        checkbox:true,
	            usePager:false,
		        onSuccess:function (json, grid){
		        	$("#pageloading").hide(); 
		        },
				onError:function (json, grid){
		        	$("#pageloading").hide(); 
		        },
	            onSelectRow: function (rowdata, rowindex){
	                $("#txtrowindex").val(rowindex);
	            }
	           
            });     
    }
   //删除一行
    function deleteRow()
    { 
    	var rows = g.getSelectedRows();
    	if (rows.length<=0) { alert('未选中合同'); return; }
    	for(var i = 0; i<rows.length;i++){
    		if(rows[i].scState!="未分解"){
    			$.ligerDialog.warn('温馨提示：第'+(i+1)+'个选中的合同状态错误,只能删除未分解的合同');
    			return;
    		};
    	} 
    	if(window.confirm('温馨提示：确定要删除？')){
    		var ids = []; 
            for (var i in rows) { 
            	ids.push(rows[i].id);
            };
    	  	$.ajax({
        		url:basePath+"rest/sellContractManageAction/deleteSellContractManageByIds?ids="+ids, //请求的url地址
        	    dataType: "json",   //返回格式为json
        	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        	    type: "post",   //请求方式
        	    success: function(msg) {
        	    	window.location.reload(true);
        	    },
        	    error: function() {
        	    	  window.location.reload(true);
        	    }
        	});
    	}
    }
    
    //修改选择的行
    function updateRow(){
    	var row = g.getSelectedRow();
    	if (!row) { $.ligerDialog.warn('请选择行'); return; }
    	var modifydiv = $("#modifydiv");
    	var scCode = row.scCode;
    	urlStr = basePath+"rest/sellContractManageAction/loadSellContractPlanBatchManage?scCode="+scCode,
    	f_initGrid2(urlStr);
    	var id = row.id;
    	var scState = row.scState;
    	$("#scCode1").val(row.scCode);
    	$("#scDate1").val(Todate2(row.scDate));
    	$("#scMoney1").val(row.scMoney);
    	$("#cusName1").val(row.cusName);
    	$("#agentBy1").val(row.agentBy);
    	$("#cusCode1").val(row.cusCode);
    	$("#createDate1").val(Todate2(row.createDate));
    	$("#createBy1").val(row.createBy);
        $.ligerDialog.open({
            height:500,
            width: 1000,
            title : '修改合同',
            showMax: true,
            showToggle: false,
            showMin: true,
            isResize: true,
            slide: false,
            target:modifydiv,
            buttons: [{ 
    			text: '选中修改', 
    			onclick: function (i, d) {
    				var row={};
    				row["id"] = id;
    				row["scCode"] = $("#scCode1").val();
    				row["scDate"] = Todate1($("#scDate1").val());
    				row["scMoney"] = $("#scMoney1").val();
    				row["cusName"] =$("#cusName1").val();
    				row["agentBy"] =$("#agentBy1").val();
    				row["cusCode"]= $("#cusCode1").val();
    				row["createDate"]=Todate1($("#createDate1").val());
    				row["createBy"]= $("#createBy1").val();
    				row["scState"]= scState;
    				var rows = g2.getSelectedRows();
    				if(rows.length<=0){
    					$.ligerDialog.warn("请选择全部的批次规格型号");
    					return;
    				}
    				var dataArr = [];
    				for(var j = 0;j < rows.length;j++){
    					var dataBean = new Object();
    					var bean = rows[j];
    					var stateCheck = bean.scPlanbatState;
    					if(stateCheck =="已下发"){
    						$.ligerDialog.warn('温馨提示：第'+(i+1)+'已经在生产了,不能修改，只能修改状态为未下发的');
    		    			return;
    		    		};
    					dataBean.id = bean.id;
    					dataBean.createDate =bean.createDate;
    					dataBean.createBy = bean.createBy;
    					dataBean.scCode = $("#scCode1").val();
    					dataBean.reqAmount = bean.reqAmount;
    					dataBean.reqUnit = bean.reqUnit;
    					dataBean.batCode = bean.batCode;
    					dataBean.deliveDate = bean.deliveDate;
    					dataBean.pbatDetailCode = bean.pbatDetailCode;
    					dataBean.reqPeriodLength = bean.reqPeriodLength;
    					dataBean.proGgxh = bean.proGgxh;
    					dataBean.scPlanbatState = bean.scPlanbatState;
    					dataArr.push(dataBean);
    				}
    				var data = JSON.stringify(dataArr);
    				$.ajax({
  					  url: basePath+"rest/sellContractManageAction/updateSellContractManage",
  					  dataType: 'json',
  					  data:row,
  					  success:function(msg){
  						$.ajax({
          				  url:basePath+"rest/sellContractManageAction/updateSellContractPlanBatchManage", 
          				  contentType: "application/json",
        				  dataType: 'json',
        				  data: data,
        				  type: "post",
    					  success:function(msg){
    						  window.location.reload(true);
    					  },
    					  error: function(msg) {
    						  window.location.reload(true);
    					   }
    					});
  					  },
  					  error: function(msg) {
  					       d.hide();
  					   }
  					});
					
    				}
    		   },
               { 
    			  text: '关闭', 
    			  onclick: function (i, d) {
    				  window.location.reload(true);
    			  }
    		   }],  
        });
    }
    //展示表格2 
    function f_initGrid2(urlStr){ 
    g2 = $("#modifydiv2").ligerGrid({
        columns: [
            { display: '序号', name: 'id',editor: { type: 'text' },width:90},
            { display: '批次号', name: 'batCode',editor: { type: 'text' },width:90},
            { display: '批次规格型号编码', name: 'pbatDetailCode', editor: {type: 'text'},width:110},
            { display: '规格型号', name: 'proGgxh', editor: { type: 'text' },width:90},
            { display: '段长', name: 'reqPeriodLength',editor: { type: 'text' },width:90},
            { display: '数量', name: 'reqAmount',editor: { type: 'int' },width:90},
            { display: '单位', name: 'reqUnit',editor: { type: 'text' },width:90},
            { display: '交货时间', name: 'deliveDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:90},
            { display: '录入人员', name: 'createBy',editor: { type: 'text' },width:90},
            { display: '录入时间', name: 'createDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:90},
            { display: '状态', name: 'scPlanbatState',type: 'text' ,width:90}
        ],
        url:urlStr,
        rownumbers: true,
        isChecked: f_isChecked, 
        onCheckRow: f_onCheckRow, 
        onCheckAllRow: f_onCheckAllRow,
        width: '100%',
        height:'94%',
        enabledEdit: true, 
        isScroll: false, 
        checkbox:true,
        usePager:false,
        onSuccess:function (json, grid){
        	$("#pageloading").hide(); 
        },
		onError:function (json, grid){
        	$("#pageloading").hide(); 
        },
        onSelectRow: function (rowdata, rowindex){
            $("#txtrowindex").val(rowindex);
        }
       
    });     
}
    
    function detailsRow(){
    	var row = g.getSelectedRow();
    	if (!row) { $.ligerDialog.warn('请选择行'); return; }
    	var detaildiv = $("#detaildiv");
    	var scCode = row.scCode;
    	urlStr = basePath+"rest/sellContractManageAction/loadSellContractPlanBatchManage?scCode="+scCode,
    	f_initGrid3(urlStr);
    	var id = row.id;
    	var scState = row.scState;
    	$("#scCode2").val(row.scCode);
    	$("#scDate2").val(Todate2(row.scDate));
    	$("#scMoney2").val(row.scMoney);
    	$("#cusName2").val(row.cusName);
    	$("#agentBy2").val(row.agentBy);
    	$("#cusCode2").val(row.cusCode);
    	$("#createDate2").val(Todate2(row.createDate));
    	$("#createBy2").val(row.createBy);
    	  $.ligerDialog.open({
              height:500,
              width: 1000,
              title : '合同详情展示',
              showMax: true,
              showToggle: false,
              showMin: true,
              isResize: true,
              slide: false,
              target:detaildiv,
              buttons: [{ 
      			text: '关闭', 
	      			onclick: function (i, d) {
	      				d.hide();
	      			}
      		   }],  
          });
    }
  
    
    //展示表格3
    function f_initGrid3(urlStr){ 
    g3 = $("#detaildiv2").ligerGrid({
        columns: [
            { display: '序号', name: 'id',editor: { type: 'text' },width:40},
            { display: '批次号', name: 'batCode',editor: { type: 'text' },width:90},
            { display: '批次规格型号编码', name: 'pbatDetailCode', editor: {type: 'text'},width:110},
            { display: '规格型号', name: 'proGgxh', editor: { type: 'text' },width:90},
            { display: '段长', name: 'reqPeriodLength',editor: { type: 'text' },width:90},
            { display: '数量', name: 'reqAmount',editor: { type: 'int' },width:70},
            { display: '单位', name: 'reqUnit',editor: { type: 'text' },width:40},
            { display: '交货时间', name: 'deliveDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:90},
            { display: '录入人员', name: 'createBy',editor: { type: 'text' },width:90},
            { display: '录入时间', name: 'createDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:90},
            { display: '状态', name: 'scPlanbatState',type: 'text' ,width:90}
        ],
        url:urlStr,
        rownumbers: true,
        isChecked: f_isChecked, 
        onCheckRow: f_onCheckRow, 
        onCheckAllRow: f_onCheckAllRow,
        width: '100%',
        height:'94%',
        enabledEdit: true, 
        isScroll: false, 
        checkbox:true,
        usePager:false,
        onSuccess:function (json, grid){
        	$("#pageloading").hide(); 
        },
		onError:function (json, grid){
        	$("#pageloading").hide(); 
        },
        onSelectRow: function (rowdata, rowindex){
            $("#txtrowindex").val(rowindex);
        }
       
    });     
}
    //分解合同
    function decomposition(){
    	var rows = g.getSelectedRows();
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
  				  submitSuccess(json);
  				  //ajaxError(json);
  				  g.reload();
  			  },
  			  error: function(msg) {
  				$.ligerDialog.error("温馨提示：分解失败，请联系管理员！");
  			   }
  			});
    	}
    }
    //window.location.href = basePath+"rest/projconMacManageAction/toMachineDiagram?processName="+encodeURI(processName);
   //跳转到生产通知单列表
    function goToList(){
    	window.location.href = basePath+"rest/sellContractDetailManageAction/toListPage";
    }
    
  //日期格式转换Sun Jul 23 2017 00:00:00 GMT+0800 转换为yyyy-mm-dd hh:mm:ss
    function Todate(num) {
        num = num + ""; //给字符串后就一个空格
        if(num.length<=0 || num=="undefined"){return;}
        var date = "";
        var month = new Array();
        month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4;
        month["May"] = 5; month["Jan"] = 6; month["Jul"] = 7; month["Aug"] = 8;
        month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
        var week = new Array();
        week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四";
        week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
        str = num.split(" "); //根据空格组成数组
        //通过修改这里可以得到你想要的格式
        date = str[3] + "-" + month[str[1]] + "-" + str[2] + " " + "00:00:00"; 
        //date=date+" 周"+week[str[0]];
        return date;
    }
    //日期格式转换
    function Todate1(num){
    	var str=num+"";
    	if(str.length<=0){return;}
    	var reg=new RegExp("/","g"); //创建正则RegExp对象 
    	 str = str.replace(reg, "-");
    	 str+=" 00:00:00";
    	 return str;
    }
    //日期转化  2017-07-22 00:00:00 到 2017-07-22
    function Todate2(num){
    	var str = num +"";
    	if(str.length<=0){return;}
    	str = str.split(" ");
    	return str[0];
    }
    