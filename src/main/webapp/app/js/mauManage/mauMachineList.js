	var g;	
	$(function(){
		 urlStr = basePath+"rest/mauMachineManageAction/loadMauMachineManage",
		 f_initGrid(urlStr);
	}); 
	  //初始化表格实现的是服务器端分页查询
    function f_initGrid(urlStr)
    { 
        g = $("#maingrid").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id',  maxWidth: 150 },
		            { display: '创建日期', name: 'createDate',  maxWidth: 200 },
		            { display: '创建人', name: 'createBy',  maxWidth: 150 },
		            { display: '机台编码', name: 'macCode',  maxWidth: 150 },
		            { display: '机台名称', name: 'macName',  maxWidth: 200},
		            { display: '机台状态', name: 'macState',  maxWidth: 150 },
		            { display: '机台类型', name: 'macType',  maxWidth: 150 },
		            { display: '规格型号开始值', name: 'ggxhStart',  maxWidth: 150 },
		            { display: '规格型号结束值', name: 'ggxhEnd',  maxWidth: 150 },
		            { display: '优先级', name: 'macPrio',  maxWidth: 150 }
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
	            toolbar: {
                    items: [
					{ text: '添加', click: addNewRow, icon: 'add' },
					{ line: true },{ line: true },
                    { text: '修改', click: updateRow, icon: 'modify'},
                    { line: true },{ line: true },
                    { text: '删除', click: deleteRow, icon: 'delete' },
                    { text: '初始化机台生产速度', click: initSpeed, icon: 'modify' }
                    ]
                },
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

    //根据工序编号查询
    function serchByMacCode(){
    	var macCode =$("#serchByMacCode").val();
    	if (macCode=="请输入机台编码") { 
    		$.ligerDialog.warn('请输入查询的机台编码'); return; 
    	}
    	
    		urlStr = basePath+"rest/mauMachineManageAction/loadMauMachineManage?macCode="+macCode;
    		f_initGrid(urlStr);
    	
		
    }
    
    //删除选择的行集合
    function deleteRow()
    { 
    	var ids = []; 
        var rows = g.getSelecteds();
        if (rows.length <= 0) { alert('请选择行'); return; }
        debugger;
        for (var i in rows) {   
        	ids.push(rows[i].id);
        }; 
    	$.ajax({
    		url:basePath+"rest/mauMachineManageAction/deleteMauMachineManageByIds?ids="+ids, //请求的url地址
    	    dataType: "json",   //返回格式为json
    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    	    type: "post",   //请求方式
    	    success: function(msg) {
    	    	alert(msg);
    	    	urlStr = basePath+"rest/mauMachineManageAction/loadMauMachineManage",
    			f_initGrid(urlStr);
    	    },
    	    error: function() {
    	       alert("出错啦");
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
    	var urlStr = basePath+"rest/mauMachineManageAction/serchMacType";
    	var inputId = "macType";
    	createListBox(urlStr,inputId);
    	urlStr= basePath+"rest/mauMachineManageAction/serchMacPrio";
    	inputId = "macPrio";
    	createListBox(urlStr,inputId);
    	$("#id").val(row.id);
    	$("#createDate").val(toDate1(row.createDate));
    	$("#createBy").val(row.createBy);
    	$("#macCode").val(row.macCode);
    	$("#macName").val(row.macName);
    	$("#macState").val(row.macState);
    	$("#macType").val(row.macType);
    	$("#ggxhStart").val(row.ggxhStart);
    	$("#ggxhEnd").val(row.ggxhEnd);
    	$("#macPrio").val(row.macPrio);
    	var modifyform =  $("#modifyform");
    	debugger;
        $.ligerDialog.open({
            height:300,
            width: 350,
            title : '修改选中的行',
            showMax: true,
            showToggle: false,
            showMin: true,
            isResize: true,
            slide: false,
            target:modifyform,
            buttons: [{ 
            			text: '保存', 
            			onclick: function (i, d) { 
            				row.id = $("#id").val();
            				row.createDate = toDate2($("#createDate").val());
            				row.createBy = $("#createBy").val();
            				row.macCode = $("#macCode").val();
            				row.macName = $("#macName").val();
            				row.macState = $("#macState").val();
            				row.macType = $("#macType").val();
            				row.ggxhStart = $("#ggxhStart").val();
            				row.ggxhEnd = $("#ggxhEnd").val();
            				row.macPrio = $("#macPrio").val();
            				$.ajax({
            					  url: basePath+"rest/mauMachineManageAction/updateMauMachineManage",
            					  dataType: 'json',
            					  data:row,
            					  success:function(msg){
            						  $.ligerDialog.success(msg);
        						  	  window.location.reload(true);
            					  },
            					  error: function(msg) {
            						  $.ligerDialog.error("出错了");
            					       d.hide();
            					   }
            					});
            				 }
            		   },
                       { 
            			  text: '关闭', 
            			  onclick: function (i, d) {
            				  d.hide();}
            		   }
                     ], 
        });
    }

  //新增一行
    function addNewRow(){
    	var addform =  $("#addform");
    	var urlStr = basePath+"rest/mauMachineManageAction/serchMacType";
    	var inputId = "macType1";
    	createListBox(urlStr,inputId);
    	urlStr= basePath+"rest/mauMachineManageAction/serchMacPrio";
    	inputId = "macPrio1";
    	createListBox(urlStr,inputId);
        $.ligerDialog.open({
            height:300,
            width:350,
            title : '新增一行',
            showMax: true,
            showToggle: false,
            showMin: true,
            isResize: true,
            slide: false,
            target:addform,
            buttons: [{ 
            			text: '保存', 
            			onclick: function (i, d) {
            				var row ={};
            				row["createDate"] = toDate2($("#createDate1").val());
            				var testdate = toDate2($("#createDate1").val());
            				debugger;
            				row["createBy"] = $("#createBy1").val();
            				row["macCode"] = $("#macCode1").val();
            				row["macName"] = $("#macName1").val();
            				row["macState"] = $("#macState1").val();
            				row["macType"] = $("#macType1").val();
            				debugger;
            				row["ggxhStart"] = $("#ggxhStart1").val();
            				row["ggxhEnd"] = $("#ggxhEnd1").val();
            				row["macPrio"]= $("#macPrio1").val();
            				$.ajax({
            				  url: basePath+"rest/mauMachineManageAction/addMauMachineManage",
          					  dataType: 'json',
          					  data:row,
          					  success:function(msg){
          						$.ligerDialog.success(msg);
    						  	  window.location.reload(true);
          					  },
          					  error: function(msg) {
          						$.ligerDialog.error("新增出错");
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
    //实现下拉列表框 
    //"[{"text":"拉丝","id":"拉丝"},{"text":"绞线","id":"绞线"},{"text":"绝缘","id":"绝缘"},{"text":"成缆","id":"成缆"}]"
    function createListBox(urlStr,inputId){
    	var data = [];
    	$.ajax({
			  url: urlStr,
			  dataType: 'json',
			  success:function(list){
				for(var i = 0;i < list.length;i++){
					var dataBean = new Object();
					var te13 = list[i].value;
					dataBean.text = list[i].value;
					dataBean.id = list[i].value;
					data.push(dataBean);
				}
				JsonStr = JSON.stringify(data);
				var JsonObj =JSON.parse(JsonStr); 
				 $("#"+inputId).ligerComboBox({  
					width: 175,
					height:20,
					data:JsonObj,
					valueField: 'id',
		            textField: 'text',
					autocomplete: true,
					keySupport:true
		        });
			  },
			  error: function(msg) {
				  $.ligerDialog.error("出错");
			       d.hide();
			   }
			});
    }
    //日期格式转换2017-05-05 00:00:00 成2017-05-05 "2017-05-19 13:3:4"
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
    
    //初始化参数
    function initSpeed(){
    	$.ajax({
			  url:  basePath+"rest/mauMachineManageAction/initMachineSpeed",
			  dataType: 'json',
			  type: "Post",
			  contentType: "application/json; charset=utf-8",
			  success:function(json){
				  ajaxSuccess(json);
			  },
			  error: function(json) {
				  ajaxError(json);
			   }
			});
    }