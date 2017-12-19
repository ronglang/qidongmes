<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <script src="<%=basePath %>core/plugin/LigerUI/lib/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript">
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
            window['g'] =
            $("#maingrid").ligerGrid({
            	url:'<%=basePath %>rest/storeObjManageAction/getStoreObjDate?time1=${time1}&time2=${time2}&batchCode=${batchCode}&objSort=${objSort}&objGgxh=${objGgxh}&address=${address}',
                height: '60%',
                columns: [
                { display: '批次号', name: 'batchCode', align: 'left'},
                { display: 'RFID编号', name: 'rfidCode'},
                { display: '物料名称', name: 'objSort'},
                { display: '型号', name: 'objGgxh' },
                { display: '数量',name:'acount'},
                { display: '位置',name:'address'},
                { display: '入库时间',name:'inDate'},
                { display: '供货商',name:'supplyAgent'}
                ],  
            	onDblClickRow:function(rowdata, rowindex, rowDomElement){
            		var address = rowdata.address;
            		alert(address);
            		//window.open("<%=basePath %>rest/mauProcessMonitoringManageAction/toListDetailPage?productid="+productid);
            	},
                rownumbers: true
            });
            $("#pageloading").hide();
        });

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
                inputWidth: 170, labelWidth: 90, space: 40,
                
                fields: [
                { display: "入库日期 ", name: "time1", newline: false, type: "date", validate: {required: true,minlength: 5 }},
                { display: "到 ", name: "time2", newline: false, type: "date", validate: {required: true,minlength: 5 } },
              	{ display: "批次号", name: "batchCode", newline: false, type: "text"},
              	{ display: "物料名称", name: "objSort", newline: false, type: "text"},
              	{ display: "型号", name: "objGgxh", newline: false, type: "text"},
              	{ display: "位置信息", name: "address", newline: false, type: "text"},
                ],
                buttons:[
                         {text:"搜索",click:function(){
                        	//form.valid();
                        	var time1= $("[name=time1]").val();
                        	var time2= $("[name=time2]").val();
                        	/* if(time1==''|| time2==''){
                        		alert("请输入日期");
                        		return;
                        	} */
							var batchCode=$("[name=batchCode]").val();
							var objSort=$("[name=objSort]").val();
							var objGgxh=$("[name=objGgxh]").val();
							var address=$("[name=address]").val();
							alert(time1+"+"+time2+"+"+batchCode+"+"+objSort+"+"+objGgxh+"+"+address);
							window.open('<%=basePath %>rest/storeObjManageAction/toStoreObjTable?time1='+time1+'&time2='+time2+'&batchCode='+batchCode+'&objSort='+objSort+'&objGgxh='+objGgxh+'&address='+address);
                         }}
                         ],
                validate  : true
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
    <div id="maingrid" style="overflow: hidden;">
    	
    </div>
   
  <div style="display:none;">
  
</div>
</body>
</html>

