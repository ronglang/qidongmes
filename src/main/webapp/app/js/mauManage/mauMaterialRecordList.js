	var g,g1;	
	$(function(){
		 urlStr = basePath+"rest/mauMaterialRecordManageAction/loadPickingMauMaterialRecordManage",
		 f_initGrid(urlStr);
	}); 
	
   //表格展示列表
    function f_initGrid(urlStr)
    { 
        g = $("#maingrid").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id',  width:50},
		            { display: '工作单号', name: 'wsCode',   minwidth: 50,maxwidth:200 },
		            { display: '单据号', name: 'mmrCode',   minwidth: 50,maxwidth:200 },
		            { display: '机台编码', name: 'macCode',minwidth: 50,maxwidth:200 },
		            { display: '领料人', name: 'matermanageBy',   minwidth: 50,maxwidth:200 },
		            { display: '领料时间', name: 'matermanageTime',   minwidth: 50,maxwidth:200 },
		            { display: '创建时间', name: 'createDate',  minwidth: 50,maxwidth:200 },
		            { display: '创建人', name: 'createBy',   minwidth: 50,maxwidth:200 },
		            { display: '状态', name: 'remark',   minwidth: 50,maxwidth:200 }
		        ], 
		        toolbar:{
		        	items:[
		        	       {text:'添加',click:addNewRow,icon:'add'},
		        	       {text:'修改',click:updateRow,icon:'modify'},
		        	       {text:'删除',click:deleteRow,icon:'delete'},
		        	       { line: true },
		        	       {text:'详情',click:detailsRowAndPrint,icon:'view'},
		        	       {text:'开始领料',click:startPickging,icon:'down'},
		        	       {text:'领料确认',click:endPickging,icon:'right'},
		        	       
		        	]
		        },
		        url:urlStr, 
		        pageSize: 10, 
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        width: '100%',
		        height:'80%',
		        enabledEdit: true, 
		        isScroll: false, 
		        checkbox:true,
	            
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
    //新增一行
    function addNewRow(){
    	var adddiv=$('#adddiv');
    	f_initGrid1();
        $.ligerDialog.open({
            height:500,
            width:800,
            title : '新增领料单',
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
								$.ligerDialog.warn('请选择行'); 
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
            				row["wsCode"] = $("#wsCode").val();
            				row["mmrCode"] = $("#mmrCode").val();
            				row["macCode"] = $("#macCode").val();
            				row["matermanageBy"] =$("#matermanageBy").val();
            				row["matermanageTime"] =Todate1($("#matermanageTime").val());
            				row["createBy"]= $("#createBy").val();
            				row["createDate"]=Todate1($("#createDate").val());
            				var rows = g1.getSelectedRows();
            				if(rows.length<=0){
            					$.ligerDialog.warn("请选择全部的明细");
            					return;
            				}
            				var dataArr = [];
            				for(var j = 0;j < rows.length;j++){
            					var dataBean = new Object();
            					var bean = rows[j];
            					dataBean.materCode = bean.materCode;
            					dataBean.materAmount = bean.materAmount;
            					dataBean.mmrCode = $("#mmrCode").val();
            					dataBean.unit = bean.unit;
            					dataBean.createBy = bean.createBy;
            					//dataBean.createDate ="2017-07-22 00:00:00";
            					dataBean.createDate =bean.createDate;
            					dataArr.push(dataBean);
            				}
            				var data = JSON.stringify(dataArr);
            				debugger;
            				$.ajax({
          					  url: basePath+"rest/mauMaterialRecordManageAction/addMauMaterialRecordManage",
          					  dataType: 'json',
          					  data:row,
          					  success:function(msg){
          						$.ajax({
                  				  url:basePath+"rest/mauMaterialRecordManageAction/addMauMaterialDetail", 
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
          					});
        					
            				}
            		   },
                       { 
            			  text: '关闭', 
            			  onclick: function (i, d) {d.hide();}
            		   }], 
        });
    }
    //初始化表格2
    function f_initGrid1(){ 
        g1 = $("#addNew2").ligerGrid({
		        columns: [
		            { display: '材料编码', name: 'materCode',editor: { type: 'text' },width:127},
		            { display: '材料数量', name: 'materAmount', editor: {type: 'int'},width:127},
		            { display: '单位', name: 'unit', editor: { type: 'text' },width:127},
		            { display: '创建人', name: 'createBy',editor: { type: 'text' },width:127},
		            { display: '创建时间', name: 'createDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:127},
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
   
    //修改选择的行
    function updateRow(){
    	var row = g.getSelectedRow();
    	if (!row) { $.ligerDialog.warn('请选择行'); return; }
    	f_open(row);
    }
    function f_open(row){
    	var modifydiv = $("#modifydiv");
    	var mmrCode = row.mmrCode;
    	urlStr = basePath+"rest/mauMaterialRecordManageAction/loadPickingMauMaterialDetailManage?mmrCode="+mmrCode,
    	f_initGrid2(urlStr);
    	var id = row.id;
    	var remark = row.remark;
    	$("#wsCode1").val(row.wsCode);
    	$("#mmrCode1").val(row.mmrCode);
    	$("#macCode1").val(row.macCode);
    	$("#matermanageBy1").val(row.matermanageBy);
    	//2017-07-22 00:00:00
    	$("#matermanageTime1").val(Todate2(row.matermanageTime));
    	$("#createDate1").val(Todate2(row.createDate));
    	$("#createBy1").val(row.createBy);
        $.ligerDialog.open({
            height:500,
            width: 800,
            title : '修改领料单',
            showMax: true,
            showToggle: false,
            showMin: true,
            isResize: true,
            slide: false,
            target:modifydiv,
            buttons: [{ 
    			text: '选中修改', 
    			onclick: function (i, d) {
    				debugger;
    				var row={};
    				row["id"] = id;
    				row["remark"] = remark;
    				row["wsCode"] = $("#wsCode1").val();
    				row["mmrCode"] = $("#mmrCode1").val();
    				row["macCode"] = $("#macCode1").val();
    				row["matermanageBy"] =$("#matermanageBy1").val();
    				row["matermanageTime"] =Todate1($("#matermanageTime1").val());
    				row["createBy"]= $("#createBy1").val();
    				row["createDate"]=Todate1($("#createDate1").val());
    				var rows = g1.getSelectedRows();
    				debugger;
    				if(rows.length<=0){
    					$.ligerDialog.warn("请选择全部的明细");
    					return;
    				}
    				var dataArr = [];
    				for(var j = 0;j < rows.length;j++){
    					var dataBean = new Object();
    					var bean = rows[j];
    					dataBean.id = bean.id;
    					dataBean.materCode = bean.materCode;
    					dataBean.materAmount = bean.materAmount;
    					dataBean.mmrCode = $("#mmrCode1").val();
    					dataBean.unit = bean.unit;
    					dataBean.createBy = bean.createBy;
    					//2017-07-22 00:00:00
    					dataBean.createDate =bean.createDate;
    					dataArr.push(dataBean);
    				}
    				var data = JSON.stringify(dataArr);
    				$.ajax({
  					  url: basePath+"rest/mauMaterialRecordManageAction/updateMauMaterialRecordManage",
  					  dataType: 'json',
  					  data:row,
  					  success:function(msg){
  						$.ajax({
          				  url:basePath+"rest/mauMaterialRecordManageAction/updateMauMaterialDetail", 
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
    //初始化表格3
    function f_initGrid2(urlStr){ 
        g1 = $("#modifydiv2").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id',editor: { type: 'text' },width:100},
		            { display: '材料编码', name: 'materCode',editor: { type: 'text' },width:100},
		            { display: '材料数量', name: 'materAmount', editor: {type: 'int'},width:100},
		            { display: '单位', name: 'unit', editor: { type: 'text' },width:100},
		            { display: '创建人', name: 'createBy',editor: { type: 'text' },width:100},
		            { display: '创建时间', name: 'createDate',type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'},width:150},
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
   
    //删除选择的行集合
    function deleteRow()
    { 
    	var ids = []; 
        var rows = g.getSelecteds();
        if (rows.length<=0) { $.ligerDialog.warn('请选择行'); return; }
        debugger;
        for (var i in rows) {   
        	ids.push(rows[i].id);
        }; 
    	$.ajax({
    		url:basePath+"rest/mauMaterialRecordManageAction/deleteickingMauMaterialRecordManageByIds?ids="+ids, //请求的url地址
    	    dataType: "json",   //返回格式为json
    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    	    type: "post",   //请求方式
    	    success: function(msg) {
    	    	$.ligerDialog.success(msg);
    	    	window.location.reload(true);
    	    },
    	    error: function() {
    	    	$.ligerDialog.error("出错啦，小兵");
    	    }
    	});
    }
    //打印详情页
    function detailsRowAndPrint(){
    	var row = g.getSelectedRow();
    	if (!row) { $.ligerDialog.warn('请选择行'); return; }
    	var mmrCode = row.mmrCode;
    	var printdetaildiv = $("#printdetaildiv");
    	$.ajax({
			 url:basePath+"rest/mauMaterialRecordManageAction/quringMmdBymmrCode?mmrCode="+mmrCode,
			  dataType: 'json',
			  success:function(list){
				  	//intialTable(list);
			    	$.ligerDialog.open({
			            height:520,
			            width:800,
			            title : '领料单详情打印页',
			            showMax: true,
			            showToggle: false,
			            showMin: true,
			            isResize: true,
			            slide: false,
			            target:printdetaildiv,
			            buttons: [{ 
									text: '打印', 
									onclick: function (i, d) {
										//向后台发送请求
										var form=$("<form>");//定义一个form表单
										form.attr('style','display:none');
										form.attr('method','post');
										form.attr('action',basePath+'rest/mauMaterialRecordManageAction/export');
										//添加input
										var input1 = $("<input>");
										input1.attr('type','hidden');
										input1.attr('name','mmdCode');
										input1.attr('value',mmdCode);
										//将表单放到body中
										$('body').append(form);
										form.append(input1);
										debugger;
										//form.submit();//提交表单
									}
					    		  },
				                  { 
				        			  text: '关闭', 
				        			  onclick: function (i, d) { d.hide();}
				        		  }], 
			        });
			  },
			  error: function(msg) {
				  $.ligerDialog.error("出错了，系统发生异常");
			   }
			});

    }
    //开始领料
    function startPickging(){
    	var rows = g.getSelectedRows();
    	if (rows.length<=0) { $.ligerDialog.warn('未选中领料单'); return; }
    	for(var i = 0; i<rows.length;i++){
    		if(rows[i].remark!="新增"){
    			$.ligerDialog.warn('温馨提示：第'+(i+1)+'个选中的领料单状态错误,只能开始新增的领料单');
    			return;
    		};
    	}
    	if(window.confirm('确认领料？')){
    		var ids = []; 
            for (var i in rows) { 
            	ids.push(rows[i].id);
            };
    		$.ajax({
  			  url:basePath+"rest/mauMaterialRecordManageAction/startPickgingMaterial?ids="+ids, 
  			  dataType: 'json',
  			  type: "post",
  			  success:function(msg){
  				  window.location.reload(true);
  			  },
  			  error: function(msg) {
  				  window.location.reload(true);
  			   }
  			});
    	}
    }
    //领料完成后的确认
    function endPickging(){
    	var rows = g.getSelectedRows();
    	if (rows.length<=0) { $.ligerDialog.warn('未选中领料单'); return; };
    	for(var i = 0; i<rows.length;i++){
    		if(rows[i].remark!="待出库"){
    			$.ligerDialog.warn('温馨提示：第'+(i+1)+'个选中的领料单状态错误,只能确认待出库的领料单');
    			return;
    		};
    	}
    	
    	$.ligerDialog
		.confirm(
				"<font color=red>您确认开始领料吗?" 
						+ "</font>",
				function(result) {
					if (result == true) {
			        	var ids = []; 
			            for (var i in rows) { 
			            	ids.push(rows[i].id);
			            };
			            $.ajax({
			      		  url:basePath+"rest/mauMaterialRecordManageAction/endPickgingMaterial?ids="+ids, 
			      		  dataType: 'json',
			      		  type: "post",
			      		  success:function(msg){
			      			  window.location.reload(true);
			      		  },
			      		  error: function(msg) {
			      			  window.location.reload(true);
			      		   }
			      		});
			         } else {
						return;
					}
				});
       
    }
    //给表格赋值
    function intialTable(list){
    	var length = list.length;
    	var str=""
     	for(var i=0;i<length;i++){
     	 	str+="<tr>";
	    	str+="<td>"+(i+1)+"</td>";
	    	str+="<td>"+"物料名称"+"</td>";
	    	str+="<td>"+list[i].materCode+"</td>";
	    	str+="<td>"+list[i].materAmount+"</td>";
	    	str+="<td>"+list[i].unit+"</td>";
	    	str+="<td>"+list[i].wsCode+"</td>";
	    	str+="</tr>";
     	}
     	for(var i=0;i<10-length;i++){
     		str+="<tr>";
     		str+="<td></td>";
     		str+="<td></td>";
     		str+="<td></td>";
     		str+="<td></td>";
     		str+="<td></td>";
     		str+="<td></td>";
     		str+="</tr>";
     	}
     	$("#firstrow").after(str);
    }
    //日期格式转换Sun Jul 23 2017 00:00:00 GMT+0800 转换为yyyy-mm-dd hh:mm:ss
    function Todate(num) {
        num = num + ""; //给字符串后就一个空格
        if(num.length<=0){return;}
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
    //多条件查询
    function ComprehensiveSerch(){
    	var pickingState= $("#pickingState").val();
    	var pickingwsCode= $("#pickingwsCode").val();
		var pickingmmrCode=$("#pickingmmrCode").val();
		var pickingmacCode=$("#pickingmacCode").val();
		var urlStr = basePath+'rest/mauMaterialRecordManageAction/getComSerchMauMaterialRecord?pickingState='
					+pickingState+'&pickingwsCode='+pickingwsCode+'&pickingmmrCode='+pickingmmrCode+'&pickingmacCode='+pickingmacCode;
		f_initGrid(urlStr);
    }
    //重置，为了解决小的bug，采用重新加载
    function restload(){
    	window.location.reload(true);
    }
    
    
    
    
    
    
    
    
   