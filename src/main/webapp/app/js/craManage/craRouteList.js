	var g,g1,g2,li;
	$(function(){
		 urlStr = basePath+"rest/craRouteManageAction/loadCraRouteManage",
		 f_initGrid(urlStr);
	}); 
	
   //初始化表格实现的是服务器端分页查询
    function f_initGrid(urlStr)
    { 
        g = $("#maingrid").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id'},
		            { display: '工序路线编码', name: 'routeCode' },
		            { display: '工序线路名称', name: 'routeName'},
		            { display: '创建人', name: 'createBy' },
		            { display: '创建时间', name: 'createDate'}
		        ], 
		        toolbar:{
		        	items:[
		        	{text: '增加',click: addNewRow, icon: 'add'},       
		        	{text: '修改',click: updateRow, icon: 'modify'},
		        	{text: '删除',click: deleteRow, icon: 'delete'},
		        	{text: '详情',click: detailsRow, icon: 'view'},
		        	{ line: true },
		        	]
        
		        },
        
		        url:urlStr, 
		        pageSize: 10, 
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        height:'94%',
		        enabledEdit: true, 
		        isScroll: false, 
		        checkbox:true,
	            width: '100%',
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
    
        $(document).bind('keydown.grid', function (event){
	            if (event.keyCode == 13) { 
//		                g.endEditToNext();
		          }
            });
    } 
    //新增一条工艺路线
    function addNewRow(){
    	var adddiv=$('#adddiv');
    	f_initGrid2();
        li = $.ligerDialog.open({
            height:350,
            width:1000,
            title : '新增一条工艺路线',
            showMax: true,
            showToggle: true,
            showMin: true,
            isResize: true,
            slide: false,
            target:adddiv,
            buttons: [{ 
						text: '删除行', 
						onclick: function (i, d) {
					    	var row = g2.getSelectedRow();
					    	if (!row) { alert('请选择要删除的行'); return; }
							g2.deleteSelectedRow();
						}
		    		  },
                      { 
    					text: '新增行', 
    					onclick: function (i, d) {
    						g2.addRow();
    					}
            		  },
                      { 
            			text: '保存', 
            			onclick: function (i, d) { 
            				var row={};
            				row["routeCode"]= $("#routeCode1").val();
            				//row["routeName"]=$("#routeName1").val();
            				row["createBy"] = $("#createBy1").val();
            				row["createDate"]=toDate2($("#createDate1").val());
            				var rows = g2.getSelectedRows();
            				var dataArr = [];
            				for(var j = 0;j < rows.length;j++){
            					var dataBean = new Object();
            					var bean = rows[j];
            					dataBean.routeCode =$("#routeCode1").val();
            					dataBean.seqCode = bean.seqCode;
            					//dataBean.seqName = bean.seqName;
            					dataBean.sort =bean.sort;
            					dataBean.createBy =  $("#createBy1").val();
            					dataBean.createDate =toDate2($("#createDate1").val());
            					dataBean.routeSeqCode =bean.routeSeqCode;
            					dataArr.push(dataBean);
            				}
            				var data = JSON.stringify(dataArr);
            				debugger;
            				$.ajax({
          					  url: basePath+"rest/craRouteManageAction/addCraRouteManage",
          					  dataType: 'json',
          					  data: row,
          					  success:function(msg1){
          						  $.ajax({
                  					  url: basePath+"rest/craRouteManageAction/addCraRouteSeqManage",
                  					  contentType: "application/json",
                  					  dataType: 'json',
                  					  data: data,
                  					  type: "post",
                  					  success:function(msg2){
//                  						  urlStr =basePath+"rest/craRouteManageAction/loadCraRouteManage",
//                            				  f_initGrid(urlStr);
//                  						  d.hide();
                  						  	  alert(msg2);
                  						  	  window.location.reload(true);
                  					  },
                  					  error: function() {
                  						   alert("出错了");
                  					       d.hide();
                  					   }
                  					});
          					  },
          					  error: function() {
          						   alert("出错了");
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
    function f_initGrid2()
    { 
        g2 = $("#adddiv2").ligerGrid({
		        columns: [
		            { display: '工序编码', name: 'seqCode',  width: 200,editor: { type: 'text' } },
		           /* { display: '工序名称', name: 'seqName',  width: 200,editor: { type: 'text' } },*/
		            { display: '排序', name: 'sort',  width: 200,editor: { type: 'text' } },
		            { display: '路线工序编码', name: 'routeSeqCode',  width: 200,editor: { type: 'text' } }
		        ], 
		        pageSize: 10, 
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        width: '94%',
		        height:'94%',
		        enabledEdit: true, 
		        isScroll: false, 
		        checkbox:true,
	            width: '100%',
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
            $(document).bind('keydown.grid', function (event){
	            if (event.keyCode == 13) { 
//		                g.endEditToNext();
		          }
            });
            debugger;
    } 
    
    //修改某条工艺路线 修改工艺路线的时候同时兼顾工艺路线明细
    function updateRow(){
    	var row = g.getSelectedRow();
    	if (!row) { alert('请选择要修改的一行'); return; }
    	f_open(row);
    }
    function f_open(row){
    	var modifydiv = $("#modifydiv");
    	var dynamicdiv = "modifydiv2";
    	var routeCode =row.routeCode;
    	$("#routeCode").val(row.routeCode);
    	$("#routeName").val(row.routeName);
    	$("#createBy").val(row.createBy);
    	$("#createDate").val(toDate1(row.createDate));
    	urlStr = basePath+"rest/craRouteManageAction/loadCraRouteSeqManage?routeCode="+routeCode;
    	f_initGrid1(urlStr,dynamicdiv);
        $.ligerDialog.open({
            height:350,
            width: 1000,
            title : '修改工艺路线',
            showMax: true,
            showToggle: false,
            showMin: true,
            isResize: true,
            slide: false,
            target:modifydiv,
            buttons: [{ 
						text: '删除行', 
						onclick: function (i, d) {
					    	var row = g1.getSelectedRow();
					    	if (!row) { alert('请选择要删除的行'); return; }
							g1.deleteSelectedRow();
						}
		    		  },
		              { 
						text: '新增行', 
						onclick: function (i, d) {
							g1.addRow();
						}
		    		  },
                      { 
            			text: '确认修改', 
            			onclick: function (i, d) { 
            				//修改craRoute表
            				row.routeCode = $("#routeCode").val();
            				row.routeName = $("#routeName").val();
            				row.createBy = $("#createBy").val();
            				row.createDate = toDate2($("#createDate").val());
            				//修改craRouteSeq表
            				var rows = g1.getSelectedRows();
            				var dataArr = [];
            				for(var j = 0;j < rows.length;j++){
            					var dataBean = new Object();
            					var bean = rows[j];
            					dataBean.id = bean.id;
            					dataBean.routeCode =bean.routeCode;
            					dataBean.seqCode = bean.seqCode;
            					//dataBean.seqName = bean.seqName;
            					dataBean.sort =bean.sort;
            					dataBean.createBy = bean.createBy;
            					dataBean.createDate =toDate3(bean.createDate);
            					dataBean.routeSeqCode =bean.routeSeqCode;
            					dataArr.push(dataBean);
            				}
            				var data = JSON.stringify(dataArr);
            				debugger;
            				$.ajax({
            					  url: basePath+"rest/craRouteManageAction/updateCraRouteManage",
            					  dataType: 'json',
            					  data: row,
            					  success:function(msg10){
            						  $.ajax({
                    					  url: basePath+"rest/craRouteManageAction/updateCraRouteSeqManage",
                    					  contentType: "application/json",
                    					  dataType: 'json',
                    					  data: data,
                    					  type: "post",
                    					  success:function(msg20){
                    						  urlStr =basePath+"rest/craRouteManageAction/loadCraRouteManage",
                              				  f_initGrid(urlStr);
                    						  d.hide();
                    					  },
                    					  error: function(msg21) {
                    						   alert("出错了");
                    					       d.hide();
                    					   }
                    					});
            					  },
            					  error: function(msg11) {
            						   alert("出错了");
            					       d.hide();
            					   }
            					});
            				debugger;
            				 }
            		   },
                       { 
            			  text: '关闭', 
            			  onclick: function (i, d) {
            				  window.location.reload(true);
            			  }
            		   }
                     ], 
        });
    } 
    //获取某条工艺路线详情
    function detailsRow(){
    	var row = g.getSelected();
    	var routeCode =row.routeCode;
    	var objDiv = document.createElement("div");
    	objDiv.style.display = "none";
    	var date = new Date();
    	objDiv.id = "div"+date.getSeconds();
    	var dynamicdiv = objDiv.id;
    	document.getElementById("detailRowDiv").appendChild(objDiv);
    	urlStr = basePath+"rest/craRouteManageAction/loadCraRouteSeqManage?routeCode="+routeCode;
		f_initGrid1(urlStr,dynamicdiv);
    	 $.ligerDialog.open({
             height:350,
             width: 1000,
             title : '工艺路线详情',
             showMax: true,
             showToggle: false,
             showMin: true,
             isResize: true,
             slide: false,
             target:objDiv,
             buttons: [{ 
             			  text: '关闭', 
             			  onclick: function (i, d) {
	             				  d.hide();
             				  }
             		   }], 
         });
    }
    function f_initGrid1(urlStr,dynamicdiv)
    { 
    	debugger;
        g1 = $("#"+dynamicdiv).ligerGrid({
		        columns: [
		            { display: '序号', name: 'id',  width: 110 ,editor: { type: 'text' }},
		            { display: '工序路线编码', name: 'routeCode',  width: 110,editor: { type: 'text' } },
		            { display: '工序编码', name: 'seqCode',  width: 110,editor: { type: 'text' } },
		            /*{ display: '工序名称', name: 'seqName',  width: 110,editor: { type: 'text' } },*/
		            { display: '排序', name: 'sort',  width: 110,editor: { type: 'text' } },
		            { display: '创建人', name: 'createBy',  width: 110,editor: { type: 'text' } },
		            { display: '创建时间', name: 'createDate',  width: 110,type: 'date',dateFormat: 'yyyy-MM-dd hh:mm:ss',editor: { type: 'date'} }
		        ], 
		        url:urlStr, 
		        pageSize: 10, 
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        width: '94%',
		        height:'94%',
		        enabledEdit: true, 
		        isScroll: false, 
		        checkbox:true,
	            width: '100%',
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
            $(document).bind('keydown.grid', function (event){
	            if (event.keyCode == 13) { 
//		                g.endEditToNext();
		          }
            });
            debugger;
    } 
    //删除某条工艺路
    function deleteRow()
    { 
    	var ids = []; 
    	var routeCodes = [];
        var rows = g.getSelecteds();
        for (var i in rows) {   
        	ids.push(rows[i].id);
        	routeCodes.push(rows[i].routeCode);
        }; 
    	$.ajax({
    		url:basePath+"rest/craRouteManageAction/deleteCraRouteManageByIds?ids="+ids, //请求的url地址
    	    dataType: "json",   //返回格式为json
    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    	    type: "post",   //请求方式
    	    success: function(msg) {
    	    	$.ajax({
    	    		url:basePath+"rest/craRouteManageAction/deleteCraRouteSeqByrouteCodes?routeCodes="+routeCodes, //请求的url地址
    	    		dataType: "json",   //返回格式为json
    	    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    	    	    type: "post",   //请求方式
        	    	success: function(msgs) {
        	    		alert(msgs);
        	    		urlStr = basePath+"rest/craRouteManageAction/loadCraRouteManage",
            			f_initGrid(urlStr);
            	    },
            	    error: function() {
            	       alert("删除明细表出错，小兵");
            	    }
    	    	});
    	    },
    	    error: function() {
    	       alert("删除成功");
    	    }
    	});
    }
    //根据工序路线编码来查询
    function serchByRouteCode(){
    	var routeCode =$("#serchByRouteCode").val();
    	if (routeCode=="请输入工序路线编码") { 
    		alert('请输入工序路线编码'); return; 
    	}else if(routeCode==""){
    		urlStr = basePath+"rest/craRouteManageAction/loadCraRouteManage",
    		f_initGrid(urlStr);
    	}else{
    		urlStr = basePath+"rest/craRouteManageAction/serchByRouteCode?routeCode="+routeCode,
    		f_initGrid(urlStr);
    	}
		
    }
    //日期格式转换2017-05-05 00:00:00 成2017-05-05 
    function toDate1(num){
    	var dateStr = num+"";
    	var str = dateStr.split(" ");
    	var date = str[0];
    	return date;
    }
    //日期格式转换2017-05-05 到 2017-05-05 00:00:00
    function toDate2(num){
    	var dateStr = num+"";
    	var dateNow = new Date();
    	var hour = dateNow.getHours()<10?0+""+dateNow.getHours():dateNow.getHours();
    	var mins = dateNow.getMinutes()<10?0+""+dateNow.getMinutes():dateNow.getMinutes();
    	var secs = dateNow.getSeconds()<10?0+""+dateNow.getSeconds():dateNow.getSeconds();
    	var date = dateStr+" "+hour+":"+mins+":"+secs;
    	return date;
    }

    //Thu Jun 15 2017 00:00:00 GMT+0800 (中国标准时间)转换为yyyy-mm-dd 00:00:00
    function toDate3(num) {
        num = num + ""; //给字符串后就一个空格
        var date = "";
        var month = new Array();
        month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4;
        month["May"] = 5; month["Jun"] = 6; month["Jul"] = 7; month["Aug"] = 8;
        month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
        var week = new Array();
        week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四";
        week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
        str = num.split(" "); //根据空格组成数组
        date = str[3] + "-"; //就是在2008的后面加一个“-”
        //通过修改这里可以得到你想要的格式
        date = date + month[str[1]] + "-" + str[2] + " " + str[4]; 
        //date=date+" 周"+week[str[0]];
        return date;
    }