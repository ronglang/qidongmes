 $(function (){
	 	//	queryMaterial();
        	queryTable();	
        });
		
       function queryTable(){
    	   var start = $("#start").val();
    	   var end = $("#end").val();
    	   var material = $("#material option:selected").val();
    	   var model = $("#model").val();
    	   var corlour = $("#corlour").val();
    	   var param = new Object();
    	   debugger;
    	   if (start !=null && start !="") {
    		   param.start = start;
    	   }
    	   if (end !=null && end !="") {
    		   param.end = end;
    	   }
    	   if (material !=null && material!="") {
    		   param.material = material;
    	   }
    	   if (model !=null&& model!="") {
    		   param.model = model;
    	   }
    	   if (corlour !=null && corlour!="") {
    		   param.corlour = corlour;
    	   }
    	   var para = JSON.stringify(param); 
        	 {
             	var maingid= $("#maingrid");
                 window['g'] =
                 $("#maingrid").ligerGrid({
                     height: '98%',
                   //  url:'<%=basePath%>rest/storeReturnManageAction/getReturnTable?param='+para,
                     url : basePath + "rest/"+ routeName + "Action/getReturnTable?param="+para,
                     title:'退料记录',
                     columns: [
                     { display: '批次', name: 'batchcode' },
                     { display: '材料', name: 'material' },
                     { display: 'RFID编号', name: 'rfidCode' },
                     { display: '型号', name: 'model' },
                     { display: '颜色', name: 'colour' },
                     { display: '数量', name: 'count' },
                     { display: '单位', name: 'unit' },
                     { display: '位置', name: 'poistion' },
                     { display: '叉车编号', name: 'forklift_code' },
                     { display: '机台', name: 'board' },
                     { display: '操作人', name: 'createBy' },
                     { display: '入库时间', name: 'storeDate' },
                     { display: '记录时间', name: 'formatCreateDate' },
                     { display: '备注', name: 'remark' }
                     ], rownumbers: true,
                 });
                  

                 $("#pageloading").hide();
                $("#start").ligerDateEditor();
                $("#end").ligerDateEditor();
                // $("#start").ligerDateEditor({ showTime: true,  labelWidth: 100, labelAlign: 'center' });
                // $("#end").ligerDateEditor({ showTime: true,  labelWidth: 100, labelAlign: 'center' });
        	 }  
        }
        
        //查询的时候
        function doSearch(){
        	queryTable();	
        }
        //加载材料,从数据字典加载
       /* function queryMaterial(){
        	$.post(basePath + "rest/"+ routeName + "Action/getMaterials",{},function(data){
        		var html = "";
        		for(var i= 0 ;i <data.length;i++){
        			html = html + "<option value="+data[i]+">"+data[i]+"</option>";
        		}
        	},"json");
        }*/
        //导出excel
        function getExcel(){
        	var start = $("#start").val();
     	   var end = $("#end").val();
     	   var material = $("#material option:selected").val();
     	   var model = $("#model").val();
     	   var corlour = $("#corlour").val();
     	   var param = new Object();
     	   debugger;
     	   if (start !=null && start !="") {
     		   param.start = start;
     	   }
     	   if (end !=null && end !="") {
     		   param.end = end;
     	   }
     	   if (material !=null && material!="") {
     		   param.material = material;
     	   }
     	   if (model !=null&& model!="") {
     		   param.model = model;
     	   }
     	   if (corlour !=null && corlour!="") {
     		   param.corlour = corlour;
     	   }
     	   var para = JSON.stringify(param); 
        	/*$.post(basePath + 'rest/'+ routeName + 'Action/excelExport',{params:para},function(data){
        		alert(data.msg);
        	},"json");*/
     	   var url =basePath + 'rest/'+ routeName + 'Action/excelExport?params='+para; 
        //	window.open(url, "-self");
     	  window.location.href=url;
        }
