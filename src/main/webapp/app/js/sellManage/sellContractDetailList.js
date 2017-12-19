	var g;
	$(function(){
		$.metadata.setType("attr", "validate");
        var v = $("#"+routeName).validate({
            debug: true,
            errorPlacement: function (lable, element)
            {
                if (element.hasClass("l-textarea"))
                {
                    element.ligerTip({ content: lable.html(), target: element[0] }); 
                }
                else if (element.hasClass("l-text-field"))
                {
                    element.parent().ligerTip({ content: lable.html(), target: element[0] });
                }
                else
                {
                    lable.appendTo(element.parents("td:first").next("td"));
                }
            },
            rules: {
			},
            success: function (lable)
            {
                lable.ligerHideTip();
                lable.remove();
            },
            submitHandler: function ()
            {
                $("#"+routeName+" .l-text,.l-textarea").ligerHideTip();
                //submitBPoorAdd();
            }
        });
		
		 //urlStr = basePath+"rest/sellContractDetailManageAction/loadSellContractDetailManage?scId="+row_id;
		 urlStr = basePath+"rest/sellContractDetailManageAction/loadMayBeData?planBatchId="+row_id;
		 //urlStr = basePath+"rest/sellContractDetailManageAction/listByEntity?scId="+row_id;
		 //alert(urlStr);
		 $('#deliveDate').ligerDateEditor({format:'yyyy-MM-dd hh:mm:ss',showTime:false});
		 f_initGrid(urlStr);
		 //initData();
	}); 
   //初始化表格实现的是服务器端分页查询
    function f_initGrid(urlStr)
    { 
        g = $("#maingrid").ligerGrid({
		        columns: [
		            { display: '序号', name: 'id',width:1,hide:true},
		            /*{ display: '批次编码', name: 'batCode'},
		            { display: '合同编码', name: 'scCode'},
		            { display: '生产通知单编码', name: 'pbatDetailCode'},*/
		            { display: '产品', name: 'pro_name',width:200},
		            { display: '规格型号', name: 'pro_ggxh',width:150},
		            { display: '产品颜色', name: 'pro_color',width:60},
		            { display: '生产通知单状态', name: 'pbat_detail_state',width:90},
		            { display: '交货日期', name: 'delive_date',width:90},
		            /*{ display: '要求数量', name: 'reqAmount'},
		            { display: '单位', name: 'reqUnit'},*/
		            //{ display: '总生产段长', name: 'pro_period_length_bat',width:150},
		            { display: '生产段长', name: 'pro_period_length' ,width:120},
		            { display: '已下发工单段长', name: 'pro_period_length_gd',width:250
		            	//字符串没法合计
		            	/*totalSummary:{
		            		align:'left',
		            		type:'sum',
		            		render:function(obj){
		            			return '<div style="width:120px;">已下发合计：'+obj.pro_period_length_gd+'轴</div>';
		            		}
		            	}*/
		            },
		            { display: '单位', name: 'req_unit',width:60},
		            
		            { display: '总长度', name: 'total_len',width:90,
		            	render:function(row,idx,val){
		            		if(val)
		            			return val+'米';
		            		else
		            			return '';
		            	}
		            },
		            { display: '已生成工单长度', name: 'complete_len',width:120,
		            	render:function(row,idx,val){
		            		if(val)
		            			return val+'米';
		            		else
		            			return '';
		            	}
		            },
		            /*{ display: '创建人', name: 'createBy' },
		            { display: '创建时间', name: 'createDate'},*/
		           /* { display: '操作', width:120,
		            	render:function(row,idx){
		            		var html = '';
		            		if(row.course_code){
		            			html = '已生成工单';
		            		}
		            		else{
		            			html += '<a href="javascript:void(0);" onclick=\"javascript:givePOrder(\'' + idx + '\');return false;\">生成工单</a>&nbsp;&nbsp;&nbsp;';
		            		}
	                    	html += '<a href="#" onclick="javascript:show(' + row.id + ');return false;">查看</a>';
	                        return html;
		            	}
		            }*/
		        ], 
		        url:urlStr, 
		        pageSize: 10, 
		        rownumbers: true,
		        isChecked: f_isChecked, 
		        onCheckRow: f_onCheckRow, 
		        onCheckAllRow: f_onCheckAllRow,
		        //width: '94%',
		        height:'60%',
		        enabledEdit: true, 
		        alternatingRow :true,
		        //isScroll: false, 
		        checkbox:false,
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
    
    /*function initData(){
    	var url = basePath + "rest/sellContractDetailManageAction/loadMayBeData?planBatchId="+row_id;
		$.ajax({
	        url: url,
	        type:"post",
	        dataType:"json",
	        async:true,
	        //data:parm,
	        success: function(json){
	        	if(!json.data) return ;
	        	if(!json.data.length) return;
	        	
	        	var d = json.data[0];
	        	var obj = new Object();
	        	obj.data = d;
	        	$('#iid').val(d.id);
	        	initSuccess('iden_form',obj);
	        },
	        error: function(json) {
	            ajaxError(json);
	        }
	    });
    }*/
    
  
    //多条件查询
    function ComprehensiveSerch(){
    	var batCode= $("#batCode").val();
    	var scCode= $("#scCode").val();
		var pbatDetailCode=$("#pbatDetailCode").val();
		var pbatDetailState=$("#pbatDetailState").val();
		debugger;
		var urlStr = basePath+'rest/sellContractDetailManageAction/getComprehensiveSerchsellContractDetail?batCode='
					+batCode+'&scCode='+scCode+'&pbatDetailCode='+pbatDetailCode+'&pbatDetailState='+pbatDetailState;
		f_initGrid(urlStr);
    }
    
    //下发生产通知单.  后台生成生产令
   function givePOrder(){
	   var ids = []; 
       var rows = g.getSelecteds();
       if (!rows[0]) {
    	   $.ligerDialog.warn('请选择行！'); 
    	   return; 
       }
	   for(var i = 0; i<rows.length;i++){
			if(rows[i].pbatDetailState=="已生成"){
				$.ligerDialog.warn('温馨提示：第'+(i+1)+'个状态错误，该生产通知单已经下发'); 
				return;
			};
		}
	   
		$.ligerDialog
		.confirm(
				"温馨提示：<font color=red>下发后的生产通知单不可以再修改！</font>,您确定要下发吗？",
				function(result) {
					if (result == true) {
			           	 for (var i in rows) {   
			               	ids.push(rows[i].id);
			               }; 
			               $.ajax({
			           		url:basePath+"rest/sellContractDetailManageAction/downSCDManageByIds?ids="+ids, //请求的url地址
			           	    dataType: "json",   //返回格式为json
			           	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			           	    type: "post",   //请求方式
			           	    success: function(msg) {
			           	    	$.ligerDialog.success('下发成功');
			           			g.reload();
			           	    },
			           	    error: function() {
			           	    	$.ligerDialog.error('发生了未知错误！');
			           	    }
			           	});
			        } else {
						return;
					}
				});
   }
   //返回到合同管理列表
   function returnhome(){
	   window.location.href = basePath+"rest/sellContractManageAction/toListPage";
   }
   /**
    * 预排产
    */
   function beforeSche(){
	   var ids = [];
	   var data = g.getSelecteds() ;
	   for(var i = 0;i < data.length;i++){
		   ids.push(data[i].id);
	   }
	   //发送ajax请求
	   $.ajax({
			url: basePath+"rest/schedulingManageAction/beforeScheduling",
			dataType: 'json',
			data: "ids="+ids,
			type: "post",
			success:function(data){
				if(data.success){
					$.ligerDialog.success("预排产成功", "提示内容", function(){});
					g.reload() ;
				}else {
					$.ligerDialog.error("预排产失败", "提示内容", function(){});
					g.reload() ;
				}
			}
		});
   }
   
   
   
   function formalSche () {
	   var ids = [];
	   var data = g.getSelecteds() ;
	   for(var i = 0;i < data.length;i++){
		   ids.push(data[i].id);
	   }
	   //发送ajax请求
	   $.ajax({
			url: basePath+"rest/schedulingManageAction/formalScheduling",
			dataType: 'json',
			data: "ids="+ids,
			type: "post",
			success:function(data){
				if(data.success){
					$.ligerDialog.success("排产成功", "提示内容", function(){});
					g.reload() ;
				}else{
					$.ligerDialog.error("排产失败", "提示内容", function(){});
					g.reload() ;
				}
			}
		});
   }
   
   function addRow_part(){
	   var proPeriodLength = $('#proPeriodLength').val();
	   var deliveDate= $('#deliveDate').val();
	   if(!proPeriodLength || proPeriodLength.length == 0){
		   alert('请录入段长');
		   return;
	   }
	   if(!deliveDate){
		   alert('请录入交货日期');
		   return;
	   }
   		
   		var reg=/^\d+(\.*\d{0,2})(\*\d+)((\+)\d+(\.*\d{0,2})(\*\d+))*$/;
		if(!reg.test(proPeriodLength)){
				alert("段长格式不正确，请重新设置！格式例：0.2*2+1.2*3");
				return false;
		}
		
		var row = g.getSelectedRow();
		if(!row ){
			alert("请选择一条明细");
			return;
		}
	  var did = row.id;
	   
	   var url = basePath+"rest/sellContractDetailManageAction/genernateGd?detailId="+did+"&proPeriodLength="+proPeriodLength+"&deliveDateStr="+deliveDate ; //请求的url地址
	   
	   $.ajax({
      		url:url, //请求的url地址
      	    dataType: "json",   //返回格式为json
      	    async: true, //请求是否异步，默认为异步，这也是ajax重要特性
      	    type: "post",   //请求方式
      	    success: function(json) {
      	    	//$.ligerDialog.success('下发成功');
      	    	ajaxSuccess(json);
      			g.reload();
      	    },
      	    error: function(json) {
      	    	//$.ligerDialog.error('发生了未知错误！');
      	    	ajaxError(json);
      	    }
      	});
   }
   
   