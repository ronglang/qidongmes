<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String date = request.getParameter("date");
	String seqName = request.getParameter("seqName");
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
    <script type="text/javascript">
    
    var workday = "<%=date%>";
    var seqName = "<%=seqName%>";
    debugger;
        $(function ()
        {
            grid();
        });
		function grid(){
			window['g'] =
            $("#maingrid").ligerGrid({
                height: '100%',
                title:'生产令详情工序、机台、工时拆分表',
                url: '<%=basePath %>rest/plaProductOrderSeqHourseManageAction/getPlaProductOrderSeqHourse?date='+workday+'&seqName='+seqName,
                checkbox: true,
                columns: [
                { display: '机台编码', name: 'macname',width:'9%'  },
                { display: '工序编码', name: 'seqname',width:'9%' },
                { display: '工作日', name: 'workday',width:'6%'},
                { display: '轴名称', name: 'axisname',width:'11%' },
                { display: '产品规格型号', name: 'proggxh',width:'9%' },
                { display: '产品工艺编码', name: 'procraftcode',width:'9%' },
                { display: '每轴线长度', name: 'partlen',width:'6%' },
                { display: '长度单位', name: 'lenunit',width:'4%'},
                { display: '主操作手', name: 'employeeidmain' ,width:'7%' },
                { display: '副操作手', name: 'employeeidvice' ,width:'7%' },
                { display: '开工日期', name: 'startdate' ,width:'9%' },
                { display: '完工日期', name: 'enddate',width:'9%' },
                { display: '工序用时', name: 'seqhours' ,width:'4%'},
                { hide:'id',name:'id',width:1}
                ],
                toolbar: {
                    items: [
                    { text: '修改', click: updateRows, icon: 'modify' },
                    { line: true },
                    { text: '删除', click: deleteRows, icon:'delete' }
                    ]
                },
                rownumbers: true,
                usePager:false,	
               // autoFilter: true
            });
             

            $("#pageloading").hide();
		}
		
		function deleteRows(){
			// TODO 删除数据
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	var dataArray = [];
        	//debugger;
        	if(li.length<1 ){
        		//alert("请选择删除行(可多行删除)");
        		$.ligerDialog.warn('请选择删除行(可多行删除)');
        		return;
        	}
        	for(var i = 0; i<li.length;i++){
        		var bean = new Object();
        		var beans = li[i];
        		bean.id = beans.id;
        		dataArray.push(bean);
        	}
        	var date = JSON.stringify(dataArray);
        	debugger;
        	$.ligerDialog.confirm('是否确定删除', function (yes) { 
        		if(yes){
	        		$.ajax({
	            		url:'<%=basePath %>rest/plaProductOrderSeqHourseManageAction/delPlaProductOrderSeqHourse?data='+date,
	            		type:'POST',
	            		datatype:'JSON',
	            		success:function(msg){
	            			if(msg){
		            			$.ligerDialog.success('删除成功');
		            			grid();
	            			}else{
	            				$.ligerDialog.error('发生了未知错误');
	            			}
	            		},
	            		error:function(){
	            			$.ligerDialog.error('发生了未知错误');
	            			//grid();
	            		}
	            		
	            	}); 
        		}else{
        			return;
        		}
        	});
        	
        	
		}
		
		function updateRows(){
			//TODO 修改数据
			var manager = $("#maingrid").ligerGetGridManager();
        	var li = manager.getSelectedRows();
        	var dataArray = [];
        	//debugger;
        	if(li.length!=1 ){
        		//alert("请选择一行修改");
        		$.ligerDialog.warn('请选择一行修改');
        		return;
        	}
        	for(var i = 0; i<li.length;i++){
	        	var bean = new Object();
        		var beans = li[i];
        		bean.axisname = beans.axisname;
	        	bean.seqcode = beans.seqcode;
	        	bean.proggxh = beans.proggxh;
	        	bean.procraftcode = beans.procraftcode;
	        	bean.partlen = beans.partlen;
	        	bean.lenunit = beans.lenunit;
	        	bean.maccode = beans.maccode;
	        	bean.employeeidmain = beans.employeeidmain;
	        	bean.employeeidvice = beans.employeeidvice;
	        	bean.startdate = beans.startdate;
	        	bean.enddate = beans.enddate;
	        	bean.workday = beans.workday;
        		bean.id = beans.id;
        		dataArray.push(bean);
        		var date = JSON.stringify(dataArray);
        		var id = beans.id;
        		$.ligerDialog.open({

    		        height: 350,

    		        width: 850,

    		        title: '添加BOM材料消耗',

    		        url: "<%=basePath %>rest/plaProductOrderSeqHourseManageAction/toFormPage?data="+date,

    		        showMax: false,

    		        showMin: false,

    		        isResize: true,

    		        slide: false,

    		        name:'repairAgain',

    		        buttons: [{ text: '保存', onclick: function (item, dialog) {
    		        	/* //alert(dialog.options.data);
    		        	alert(aa); */
    		        	//dialog.frame
	    		        var seqcode = dialog.frame.$("[name=seqcode]").val();
			        	var proggxh = dialog.frame.$("[name=proggxh]").val();
			        	var procraftcode = dialog.frame.$("[name=procraftcode]").val();
			        	var partlen = dialog.frame.$("[name=partlen]").val();
			        	var lenunit = dialog.frame.$("[name=lenunit]").val();
			        	var maccode = dialog.frame.$("[name=maccode]").val();
			        	var employeemain = dialog.frame.$("[name=employeeidmain]").val();
			        	var employeevice = dialog.frame.$("[name=employeeidvice]").val();
			        	var startdate = dialog.frame.$("[name=startdate]").val();
			        	var enddate = dialog.frame.$("[name=enddate]").val();
			        	var axisname = dialog.frame.$("[name=axisname]").val();
			        	var workday = dialog.frame.$("[name=workday]").val();
    		        	//debugger;
    		        	if(seqcode=="" || proggxh=="" || procraftcode=="" || partlen =="" ||lenunit=="" ||
    		        		maccode==""||employeemain==""||employeevice==""||startdate==""||enddate==""||axisname==""
    		        		||workday==""
    		        	){
    		        		$.ligerDialog.warn('请将数据填写完整！');
    		        		return ;
    		        	}
    		        	var dataArray = [];
    		        	var bean = new Object();
    		        	bean.seqcode = seqcode;
    		        	bean.proggxh = proggxh;
    		        	bean.procraftcode = procraftcode;
    		        	bean.partlen = partlen;
    		        	bean.lenunit = lenunit;
    		        	bean.maccode = maccode;
    		        	bean.employeemain = employeemain;
    		        	bean.employeevice = employeevice;
    		        	bean.startdate = startdate;
    		        	bean.enddate = enddate;
    		        	bean.axisname = axisname;
    		        	bean.workday = workday;
    		        	dataArray.push(bean);
    	        		var date = JSON.stringify(dataArray);
    		        	$.ajax({
    		        		url:"<%=basePath %>rest/plaProductOrderSeqHourseManageAction/savePlaProductOrderSeqHourse?id="+id+"&data="+date,
    		        		type:'POST',
    		        		dataType:'JSON',
    		        		success:function(msg){
    		        			if(msg){
	    		        			dialog.close();
	    		        			$.ligerDialog.success('保存成功');
	    		        			grid();
    		        			}else{
    		        				$.ligerDialog.error('保存失败');
    		        			}
    		        		},
    		        		error:function(){
    		        			$.ligerDialog.error('保存失败,发生了未知错误！');
    		        			dialog.close();
    		        		}
    		        	});
    		        	grid();
    		        }

    		        },
    		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

    		    });
        		
        		
        	}
			
		}
		
		function addRows(){
		// TODO 增加数据
			$.ligerDialog.open({

		        height: 450,

		        width: 850,

		        title: '添加BOM材料消耗',

		        url: "<%=basePath %>rest/plaProductOrderSeqHourseManageAction/toFormPage",

		        showMax: false,

		        showMin: false,

		        isResize: true,

		        slide: false,

		        name:'repairAgain',

		        buttons: [{ text: '保存', onclick: function (item, dialog) {
		        	/* //alert(dialog.options.data);
		        	alert(aa); */
		        	var seqcode = dialog.frame.$("[name=seqcode]").val();
		        	var proggxh = dialog.frame.$("[name=proggxh]").val();
		        	var procraftcode = dialog.frame.$("[name=procraftcode]").val();
		        	var partlen = dialog.frame.$("[name=partlen]").val();
		        	var lenunit = dialog.frame.$("[name=lenunit]").val();
		        	var maccode = dialog.frame.$("[name=maccode]").val();
		        	var employeemain = dialog.frame.$("[name=employeeidmain]").val();
		        	var employeevice = dialog.frame.$("[name=employeeidvice]").val();
		        	var startdate = dialog.frame.$("[name=startdate]").val();
		        	var enddate = dialog.frame.$("[name=enddate]").val();
		        	var axisname = dialog.frame.$("[name=axisname]").val();
		        	var workday = dialog.frame.$("[name=workday]").val();
		        	debugger;
		        	if(seqcode=="" || proggxh=="" || procraftcode=="" || partlen =="" ||lenunit=="" ||
    		        		maccode==""||employeemain==""||employeevice==""||startdate==""||enddate==""||axisname==""
        		        		||workday==""){
		        		$.ligerDialog.warn('请将数据填写完整！');
		        		return ;
		        	}
		        	var dataArray = [];
		        	var bean = new Object();
		        	bean.seqcode = seqcode;
		        	bean.proggxh = proggxh;
		        	bean.procraftcode = procraftcode;
		        	bean.partlen = partlen;
		        	bean.lenunit = lenunit;
		        	bean.maccode = maccode;
		        	bean.employeemain = employeemain;
		        	bean.employeevice = employeevice;
		        	bean.startdate = startdate;
		        	bean.enddate = enddate;
		        	bean.axisname = axisname;
		        	bean.workday = workday;
		        	dataArray.push(bean);
	        		var date = JSON.stringify(dataArray);
		        	$.ajax({
		        		url:"<%=basePath %>rest/plaProductOrderSeqHourseManageAction/savePlaProductOrderSeqHourse",
		        		type:'POST',
		        		data:'data='+date,
		        		success:function(msg){
		        			if(msg){
    		        			dialog.close();
    		        			$.ligerDialog.success('保存成功');
			        			grid();
		        			}else{
		        				$.ligerDialog.error('保存失败');
		        			}
		        		},
		        		error:function(){
		        			$.ligerDialog.error('保存失败,发生了未知错误！');
		        			dialog.close();
		        		}
		        	});
		        	grid();
		        }

		        },
		            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }]

		    });

			
		}
		
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <div class="l-clear"></div>

    <div id="maingrid"></div>
   
  <div style="display:none;">
  
</div>
</body>
</html>


