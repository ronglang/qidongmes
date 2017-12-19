	var g;	
	$(function(){
		 urlStr = basePath+"rest/craSeqManageAction/loadCraSeqManage",
		 f_initGrid(urlStr);
	}); 
	
	
	
   //初始化表格实现的是服务器端分页查询
    function f_initGrid(urlStr)
    { 
        g = $("#maingrid").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id',  width: 110 },
		            { display: '创建时间', name: 'createDate',  width: 110 },
		            { display: '创建人', name: 'createBy',  width: 110 },
		            { display: '工序编号', name: 'seqCode',  width: 110 },
		            { display: '工序名称', name: 'seqName',  width: 110 },
		            { display: '工序类型', name: 'seqType',  width: 110 },
		            { display: '子工序的编码', name: 'childCode',  width: 140 }
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
    } 
    
    //删除选择的行集合
    function deleteRow()
    { 
    	var ids = []; 
        var rows = g.getSelecteds();
        for (var i in rows) {   
        	ids.push(rows[i].id);
        }; 
    	$.ajax({
    		url:basePath+"rest/craSeqManageAction/deleteCraSeqManageByIds?ids="+ids, //请求的url地址
    	    dataType: "json",   //返回格式为json
    	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
    	    type: "post",   //请求方式
    	    success: function(msg) {
    	    	urlStr = basePath+"rest/craSeqManageAction/loadCraSeqManage?ids="+ids,
    			f_initGrid(urlStr);
    	    	alert(msg);
    	    },
    	    error: function() {
    	       alert("出错啦，小兵");
    	    }
    	});
    }
    
    
    //修改选择的行
    function updateRow(){
    	var row = g.getSelectedRow();
    	if (!row) { alert('请选择行'); return; }
    	f_open(row);
    }
    function f_open(row){
    	$("#id").val(row.id);
    	var testdate = toDate1(row.createDate);
    	debugger;
    	$("#createDate").val(toDate1(row.createDate));
    	$("#createBy").val(row.createBy);
    	$("#seqCode").val(row.seqCode);
    	$("#seqName").val(row.seqName);
    	$("#seqType").val(row.seqType);
    	$("#childCode").val(row.childCode);
    	var editform =  $("#editform");
    	debugger;
        $.ligerDialog.open({
            height:240,
            width: 350,
            title : '修改选中的行',
            showMax: true,
            showToggle: false,
            showMin: true,
            isResize: true,
            slide: false,
            target:editform,
            buttons: [{ 
            			text: '保存', 
            			onclick: function (i, d) { 
            				row.id = $("#id").val();
            				var debugg3 =  toDate2($("#createDate").val());
            				row.createDate = toDate2($("#createDate").val());
            				debugger;
            				row.createBy = $("#createBy").val();
            				row.seqCode = $("#seqCode").val();
            				row.seqName = $("#seqName").val();
            				row.seqType = $("#seqType").val();
            				row.childCode = $("#childCode").val();
            				$.ajax({
            					  url: basePath+"rest/craSeqManageAction/updateCraSeqManage",
            					  dataType: 'json',
            					  data:row,
            					  success:function(msg){
            						  urlStr = basePath+"rest/craSeqManageAction/loadCraSeqManage",
                      				  f_initGrid(urlStr);
            						  alert(msg);
            						  d.hide();
            					  },
            					  error: function(msg) {
            						   alert("出错了");
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
    	alert("新增一行6");
        $.ligerDialog.open({
            height:220,
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
            				var debug = $("#createDate1").val();
            				var debug2 =toDate2(debug); 
            				debugger;
            				row["createDate"] = toDate2($("#createDate1").val());
            				row["createBy"] = $("#createBy1").val();
            				row["seqCode"] = $("#seqCode1").val();
            				row["seqName"] = $("#seqName1").val();
            				row["seqType"] = $("#seqType1").val();
            				row["childCode"] = $("#childCode1").val();
            				debugger;
            				$.ajax({
            				  url: basePath+"rest/craSeqManageAction/addCraSeqManage",
          					  dataType: 'json',
          					  data:row,
          					  success:function(msg){
          						  alert(msg);
          						  urlStr = basePath+"rest/craSeqManageAction/loadCraSeqManage",
	       						  f_initGrid(urlStr);
	       						  d.hide();
          					  },
          					  error: function(msg) {
          						   alert("新增哪里出错了");
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
   //根据工序编号查询
    function serchBySeqCode(){
    	var seqCode =$("#serchBySeqCode").val();
    	if (seqCode=="请输入工序编号") { 
    		alert('请输入查询的工序编号'); return; 
    	}else if(seqCode==""){
    		urlStr = basePath+"rest/craSeqManageAction/loadCraSeqManage",
    		f_initGrid(urlStr);
    	}else{
    		urlStr = basePath+"rest/craSeqManageAction/serchBySeqCode?seqCode="+seqCode,
    		f_initGrid(urlStr);
    	}
		
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
    //日期格式转换 Fri Oct 31 18:00:00 UTC+0800 2008转换为yyyy-mm-dd
    function toDate3(num) {
        num = num + ""; //给字符串后就一个空格
        var date = "";
        var month = new Array();
        month["Jan"] = 1; month["Feb"] = 2; month["Mar"] = 3; month["Apr"] = 4;
        month["May"] = 5; month["Jan"] = 6; month["Jul"] = 7; month["Aug"] = 8;
        month["Sep"] = 9; month["Oct"] = 10; month["Nov"] = 11; month["Dec"] = 12;
        var week = new Array();
        week["Mon"] = "一"; week["Tue"] = "二"; week["Wed"] = "三"; week["Thu"] = "四";
        week["Fri"] = "五"; week["Sat"] = "六"; week["Sun"] = "日";
        str = num.split(" "); //根据空格组成数组
        date = str[5] + "-"; //就是在2008的后面加一个“-”
        //通过修改这里可以得到你想要的格式
        date = date + month[str[1]] + "-" + str[2] + " " + str[3]; 
        //date=date+" 周"+week[str[0]];
        return date;
    }
    function Todate4(num){
    	var str=num+"";
    	var reg=new RegExp("/","g"); //创建正则RegExp对象 
    	 str = str.replace(reg, "-");
    	 str+=" 00:00:00";
    	 return str;
    }
    
 