<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id = request.getParameter("id");
	String flag = request.getParameter("flag");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/ligerui.all.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerForm.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerComboBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerCheckBoxList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/ligerUI/js/plugins/ligerListBox.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <style type="">
    #form3 input[name="proCode"]{
    	color:red;
    }
    </style>
    <script type="text/javascript">
    var list=[];
    var wstype=[{id:1,text:'正常出单'},{id:1,text:'插单'}];
    var url,length,j,id;
    var i = 0;
    var CustomersData = {
			Rows: list
		};
    var form,form2;
    var proType;
    var row_id = <%=id%>;
    var flag = <%=flag%>;
    //debugger;
    $(function ()
        {
    	if(row_id && !flag){
    		debugger;
    		setDatas(row_id);
    		$("#tj").hide();
    	}else if(row_id && flag){
    		debugger;
    		setDatas(row_id);
    		$("#des").hide();
    		$("#fo").hide();
    	}else{
	    	form();
	    	grid(url);
	    	form2();
    	}
        });
		
    function form(){
    	form = $("#form2").ligerForm({
            inputWidth: 150, labelWidth: 150, 
            fields: [
            	{ display: "合同编号", name: "scCode", newline:true,comboboxName:'scCode', type: "text",validate: { required: true, minlength: 1 }},
            	{ display: "签订日期", name: "scDate",  newline:false,comboboxName:'scDate',editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},type: "date" ,validate: { required: true, minlength: 1 }},
            	{ display: "客户名称", name: "cusName", newline:false,comboboxName:'cusName',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "客户编号", name: "cusCode", newline:true,comboboxName:'cusCode',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "订单经办人", name: "agentBy", newline:false,comboboxName:'agentBy',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "订单编号", name: "orderCode", newline:false,comboboxName:'orderCode',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "通知单类型", name: "wsType", newline:true,comboboxName:'orderCode',type: "select" ,validate: { required: true, minlength: 1 },editor: {valueField:'text',data:wstype}}
            ],
            validate: true
        });
    }
    
    
    function form_detail(){
    	form = $("#form2").ligerForm({
            inputWidth: 150, labelWidth: 150, 
            fields: [
            	{ display: "合同编号", name: "scCode", newline:true,comboboxName:'scCode', type: "text",validate: { required: true, minlength: 1 }},
            	{ display: "签订日期", name: "scDate",  newline:false,comboboxName:'scDate',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "客户名称", name: "cusName", newline:false,comboboxName:'cusName',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "客户编号", name: "cusCode", newline:true,comboboxName:'cusCode',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "订单经办人", name: "agentBy", newline:false,comboboxName:'agentBy',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "订单编号", name: "orderCode", newline:false,comboboxName:'orderCode',type: "text" ,validate: { required: true, minlength: 1 }},
            	{ display: "通知单类型", name: "wsType", newline:true,comboboxName:'orderCode',type: "select" ,validate: { required: true, minlength: 1 },editor: {valueField:'text',data:wstype}}
            ],
            validate: true
        });
    	liger.get('scCode').setDisabled(); //设置只读
    	liger.get('scDate').setDisabled(); //设置只读
    	liger.get('cusName').setDisabled(); //设置只读
    	liger.get('cusCode').setDisabled(); //设置只读
    	liger.get('agentBy').setDisabled(); //设置只读
    	liger.get('orderCode').setDisabled(); //设置只读
    }
    
    function form2(){
    	form2 = $("#form3").ligerForm({
            inputWidth: 170, labelWidth: 90, space:20,
            fields: [
				{ display: "产品类型", name: "proType", newline:true,comboboxName:'proType', type: "select",validate: { required: true, minlength: 5 },editor: {url:'<%=basePath %>rest/sellContractManageAction/getProType' ,valueField:'text', 
					onSelected: function (value)
	                 {
					var type = $("[name=proType]").val();
					if(type !=""){
	                    $.post("<%=basePath %>rest/sellContractManageAction/getProGgxh",{
	                    	proType:type
	                    },function(data,textStatus){
	                           var combo = liger.get('proGgxh');
	                            if (!combo) return;
	                            combo.set('data', data);
	                     },"json");
					}
             	 }}},
            	{ display: "规格型号", name: "proGgxh", newline:false,comboboxName:'proGgxh', type: "select",validate: { required: true, minlength: 5 },editor: {textField:'text',
            		onSelected: function (value)
	                 {
					var ggxh = $("[name=proGgxh]").val();
					if(ggxh !=""){
	                    $.post("<%=basePath %>rest/sellContractManageAction/getProColor",{
	                    	proGgxh:ggxh
	                    },function(data,textStatus){
	                           var combo = liger.get('proColor');
	                            if (!combo) return;
	                            combo.set('data', data);
	                     },"json");
					}
             	 }}},
            	{ display: "颜色", name: "proColor",  newline:false,comboboxName:'proColor',type: "select",validate: { required: true, minlength: 1,},editor: {textField:'text',onSelected:function(){
            		var type = $("[name=proType]").val();
            		var ggxh = $("[name=proGgxh]").val();
            		var color = $("[name=proColor]").val();
            		if(ggxh !="" && type !="" && color !="" ){
	                    $.post("<%=basePath %>rest/sellContractManageAction/getProCode",{
	                    	proGgxh:ggxh,
	                    	proType:type,
	                    	proColor:color
	                    },function(data,textStatus){
	                    	form2.setData(data[0]);
	                     },"json");
					}
            	  }}},
            	{ display: "要求段长", name: "reqPeriodLength", newline:false,comboboxName:'reqPeriodLength',type: "text",validate: { required: true, minlength: 1 }},
            	{ display: "生产段长", name: "proPeriodLength", newline:true,comboboxName:'reqPeriodLength',type: "text" ,validate: { required: true, minlength: 1 } },
            	{ display: "交货日期", name: "deliveDate", newline:false,comboboxName:'deliveDate',editor:{showTime:true,format:"yyyy-MM-dd hh:mm:ss"},type: "date" ,validate: { required: true, minlength: 5 }},
            	{ display: "产品工艺编码", name: "proCraftCode", newline:false,comboboxName:'proCraftCode',type: "select",validate: { required: true, minlength: 5 } ,editor: {url:'<%=basePath %>rest/sellContractManageAction/getProCraftCode' ,valueField:'text'}},
            	{ display: "产品编号", name: "proCode", newline:false,comboboxName:'proCode',type: "text"},
            ],
            validate: true
        });
    	liger.get('proCode').setDisabled(); //设置只读
    }
    
    function grid(url){
		window['g'] =
        $("#maingrid").ligerGrid({
            height: '80%',
            columns: [
            { display: '产品类型', name: 'proType',width:'12%'},
            { display: '规格型号', name: 'proGgxh',width:'12%'},
            { display: '颜色', name: 'proColor',width:'3%' },
            { display: '要求段长', name: 'reqPeriodLength' },
            { display: '生产段长', name: 'proPeriodLength' },
            { display: '轴数', name: 'reqAmount' ,width:'3%'},
            { display: '交货日期', name: 'deliveDate' },
            { display: '产品工艺编码', name: 'proCraftCode' },
            { display: '产品编号', name: 'proCode'},
            { display: '操作', name: '',width:'5%',render : function(row) {
            	var html = "";
            	if(row_id){
					html = '<a href="#" onclick="javascript:edit('
					+row.__index+ ');return false;">编辑</a>';
            		
            	}else{
            		html = '<a href="#" onclick="javascript:del('
    					+row.__index+ ');return false;">删除</a>';
            	}
				return html;		
           		}
            },
            { hide: 'id', name: 'id',width:1},
            ],
            data: CustomersData,
            usePager:false, 
            rownumbers: true,
        });
        $("#pageloading").hide();
	}
    
    
    function grid1(url){
		window['g'] =
        $("#maingrid").ligerGrid({
        	url:url,
            height: '80%',
            columns: [
            { display: '产品类型', name: 'proType',width:'12%'},
            { display: '规格型号', name: 'proGgxh',width:'12%'},
            { display: '颜色', name: 'proColor',width:'3%' },
            { display: '要求段长', name: 'reqPeriodLength' },
            { display: '生产段长', name: 'proPeriodLength' },
            { display: '轴数', name: 'reqAmount' ,width:'3%'},
            { display: '交货日期', name: 'deliveDate' },
            { display: '产品工艺编码', name: 'proCraftCode' },
            { display: '产品编号', name: 'proCode'},
            { display: '操作', name: '',width:'5%',render : function(row) {
            	var html = "";
            	if(row_id && !flag){
					html = '<a href="#" onclick="javascript:edit('
					+row.__index+ ');return false;">编辑</a>';
            		
            	}else if(row_id && flag){
            		html = '<a href="#" onclick="javascript:does('
    					+row.__index+ ');return false;">操作</a>';
            	}else{
            		html = '<a href="#" onclick="javascript:del('
    					+row.__index+ ');return false;">删除</a>';
            	}
				return html;		
           		}
            },
            { hide: 'id', name: 'id',width:1},
            ],
            usePager:false, 
            rownumbers: true,
        });
        $("#pageloading").hide();
	}
    
    function does(row){
    	alert(row);
    }
    
    function del(row) { 
    	list.remove(row);
    	var manager = $("#maingrid").ligerGetGridManager(); 
    	manager.deleteRow($("tr.l-grid-row",manager.gridbody)[0]); 
    }
    
    function add(){
    	
    	var va = form2.valid();
    	var proType = $("[name=proType]").val();
    	var deliveDate =  $("[name=deliveDate]").val();
    	var proGgxh = $("[name=proGgxh]").val();
    	var proColor = $("[name=proColor]").val();
    	var reqPeriodLength = $("[name=reqPeriodLength]").val();
    	var proPeriodLength = $("[name=proPeriodLength]").val();
    	var proCraftCode = $("[name=proCraftCode]").val();
    	var proCode = $("[name=proCode]").val();//$("[name=proCode]").val()
    	var reqAmount = getAxis(reqPeriodLength);
    	if(row_id){
        	if(va){
            	var reg = /(\.\d+)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)$/;
            	if(reg.test(reqPeriodLength) && reg.test(proPeriodLength) ){
            		var bean = new Object();
            	    bean.deliveDate = deliveDate;
            	    bean.proGgxh = proGgxh;
            	    bean.proColor = proColor;
            	    bean.reqAmount = reqAmount;
            	    bean.reqPeriodLength = reqPeriodLength;
            	    bean.proPeriodLength = proPeriodLength;
            	    bean.proCraftCode = proCraftCode;
            	    bean.proCode = proCode;
            	    bean.proType = proType;
            	    bean.id = id;
            	    list[j] = bean;
            	    CustomersData = { Rows: list };
            	    grid(CustomersData);
            	}else{
            		$.ligerDialog.warn("请选择输入正确的表达式   如:(0.2*2+1.2*3)");
                	return;
            	}
        	}else{
        		$.ligerDialog.warn("请选择将数据填写完整");
        		return;
        	}
    		
    	}else{
        	
        	if(va){
            	var reg1 = /(\.\d+)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)?(\+)?(\d+(\.\d+)?)?(\*)?(\d+(\.\d+)?)$/;
            	if(reg1.test(reqPeriodLength) && reg1.test(proPeriodLength) ){
            		for(var i=0;i<list.length;i++){
        	    		var ls = list[i];
        	    		if(ls.proGgxh==proGgxh && ls.proColor==proColor &&
        	    				ls.reqPeriodLength==reqPeriodLength &&
        	    				ls.proPeriodLength==proPeriodLength && ls.proCraftCode==proCraftCode && 
        	    				ls.proCode==proCode && ls.proType == proType ){
        	    			$.ligerDialog.warn("不得需重复加相同的数据,你可以修改生产段长的数量!!");
        	    			return;
        	    		}
        	    	}
            		var bean1 = new Object();
            	    bean1.deliveDate = deliveDate;
            	    bean1.proGgxh = proGgxh;
            	    bean1.proColor = proColor;
            	    bean1.reqAmount = reqAmount;
            	    bean1.reqPeriodLength = reqPeriodLength;
            	    bean1.proPeriodLength = proPeriodLength;
            	    bean1.proCraftCode = proCraftCode;
            	    bean1.proCode = proCode;
            	    bean1.proType = proType;
	            	list.push(bean1);
            	    //debugger;
            	    grid();
            	}else{
            		$.ligerDialog.warn("请选择输入正确的表达式   如:(0.2*2+1.2*3)");
                	return;
            	}
            		
    	    	
        	}else{
        		$.ligerDialog.warn("请选择将数据填写完整");
        		return;
        	}
    	}
    	
    	
    }
    
    
    function getAxis(axis){
    	var ss = axis.split("+");
		var dd = [];
		var ff = 0;
		for(var i=0;i<ss.length;i++){
			dd = ss[i].split("*");
			ff += parseInt(dd[dd.length-1]);
		}
		return ff;
    }
    
    function setDatas(row_id){
 	   $.ajax({
    		url:'<%=basePath %>rest/sellContractManageAction/getContracts?id='+row_id,
    		type:'POST',
    		dataType:'JSON',
    		success:function(sc){
    			form();
    			
    			var fo = new liger.get("form2");
    			fo.setData(sc);
    			url = '<%=basePath %>rest/sellContractManageAction/getContractsGrid?scCode='+sc.scCode;
 	    		grid1(url);
 	    		form2();
    		},
    		error:function(){
    			$.ligerDialog.error("发生了未知错误");
    		}
    		
    	});
 	   
 	   
    }
    
    
    function edit(rows){
    	j = rows;
    	$("#tj").show();
    	var manager = $("#maingrid").ligerGetGridManager();
    	list = manager.getData();
    	var li = manager.data.Rows[rows];
    	id = li.id;
    	//list = lis;
    	debugger;
    	if(i==0){
	    	length = list.length;
	    	i++;
    	}
    	
    	var fo = new liger.get("form3");    
    	fo.setData(li);
    	
    }
    
    
    
    Array.prototype.remove=function(dx){ 
      if(isNaN(dx)||dx>this.length){return false;} 
      for(var i=0,n=0;i<this.length;i++) 
      { 
        if(this[i]!=this[dx]) 
        { 
          this[n++]=this[i];
        } 
      } 
      this.length-=1 ;
    }
    
    
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
 <div class="l-clear"></div>

   <form id="form2"></form> 
   <div id="maingrid" ></div>
   <div id="des" style="border-bottom:1px solid #B0E2FF; margin-top: 10px;">
	段长格式要求说明：0.2*2+1.2*3 ,是0.2千米的2根，1.2千米的3根。 *代表乘，+是相加
	</div>
   <div id="fo" style="text-align: center;width: 100%;position: relative;"  >
		<form id="form3" action="" >
		</form>
		<div class="liger-button" id = 'tj' onclick="add()" style="float: right;position: absolute;right: 10px;margin-bottom: 1px; ">提交</div>
	</div>
  <div style="display:none;">
  
</div>
</body>
</html>


